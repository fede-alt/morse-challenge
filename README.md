# MELI morse-challenge


## Enunciado 📝

Se pide implementar en cualquier JAVA :

**1.** Una función decodeBits2Morse que dada una secuencia de bits, retorne un string con el resultado en MORSE.

**2.** Una función translate2Human que tome el string en MORSE y retorne un string legible por un humano. Se puede utilizar la tabla debajo como referencia.

✴️ **BONUS:**

**A.** Diseñar una API que permita traducir texto de MORSE a lenguaje humano y
visceversa.

**B.** Hostear la API en un cloud público (como app engine o cloud foundry) y enviar la
URL para consulta


-------------------------------------------------------------------------------



## Comenzando el modelado 🚀

### 1. BitDecoder

La idea inicial es decodificar una secuencia de bits a morse. Para enfrentar este problema se modelaron las siguiente clases en base a los objetos que representan una secuencia de bits.
El bit es un digito binario, que puede tomar dos valores 0 o 1, por ende puede ser representado con un boolean. Este digito forma parte de un numero, por lo que el orden tiene importancia y como estos vienen en secuencia puedos llevarlos a una lista para introducir el aspecto de orden.

Generalizando una secuencia de bits puede interpretarse como una señal, donde puedo decir que existen dos tipos de señales:
   -señal en alto (**Pulso**) 
   o 
   -señal en baja (**Pausa**)
 
Abstrayendome otro nivel puedo definir una **Transmisión** como un conjunto ordenado de estas señales. 
Resumiendo tenemos los siguientes conjuntos:
```
     {bit} ϵ {Señal} ϵ {Transmision}
```
Por enunciado sé que las señales de la transmision son consistentes, en otras palabras, esto quiere decir que puntos y guiones son distinguibles, asi como tambien lo son las pausas, los espacios entre caracter y los espacios entre palabras.
Siguiendo las [reglas basicas](https://en.wikipedia.org/wiki/Morse_code#Representation,_timing,_and_speeds) del lenguaje morse se puede definir un contexto para distinguir las señales.

La logica elegida fue la siguiente:
Sea la minima duracion de una señal, se define la **tolerancia** como la relacion obligatoria que debe existir entre cada tipo de interpretacion de esa señal (caracter morse).

Se propone entonces la existencia de un **Contexto** de transmisión que permita dicha distincion, y que de esta manera cada señal pueda ser intepretada a su significado morse. 
Por ejemplo, siguiendo el standard de timing de las [reglas basicas](https://en.wikipedia.org/wiki/Morse_code#Representation,_timing,_and_speeds)
Tenemos que un punto tiene una unidad de duracion, mientras que el guion tiene tres. La relacion temporal entonces es 1 guion = 3 puntos, de aquí la **tolerancia** para identificar un guion será valor de 3 unidades minimas, o superior.



##### Condiciones 📑

Para definir el contexto es necesario que los bits computados contengan ambas posibilidades de interpretacion de la señal.
El motivo es justamente asignar esa minima duracion de señal al punto morse, o pausa.
Esto quiere decir que no se podrá traducir una señal 111000111 como "- -", pues el contexto que propuse llevará a una interpretacion ".."
En resumen, deben estar presentes todos los "caracteres" morse. (punto , guion / pausa, char-space, word-space)

##### Configurables: 🔧
  **tolerancia:** la tolerancia podra ser configurable en el YML. El numero de cada tolerancia debe respetar a la tolerancia de nivel inferior. Por ejemplo, la tolerancia de un word-space debe ser mayor a la de un char-space.
  

-------------------------------------------------------------------------------


### 2. Traductor morse a texto
Como este metodo debe ser utilizado por la API y debe tener un metodo inverso me propuse utilizar un diccionario/mapa de preferencia configurable, inyectivo y inversible.
Usando un mapa bi direccional de google [BiMap](https://guava.dev/releases/19.0/api/docs/com/google/common/collect/BiMap.html) se definen las relaciones brindadas en la tabla.
La idea es granular el problema:

  Para cada caracter de texto, se busca una traduccion a morse.
  
  Para cada termino morse se busca su traduccion a texto.
  
  
  
##### Configurables: 🔧
  _Parametros configurables de la API en su application.yml_
  **interferencia:** llamo interferencia o "ruido" a algo que no se le encuentra traduccion, posibilitando ignorarlo y traducir el resto (coerce) o reportarlo (Exception), se configura con un booleano equivalente a ignorarla.
  **diccionario:** el diccionario esta declarado en el YML posibilitando insertar mas simbolos.
  
##### Manejo de errores:
  En el caso de que no se ignore la transferencia se lanzara una MorseException con la informacion suficiente para ubicar el problema.
  
  
  
-------------------------------------------------------------------------------
  

## Herramientas utilizadas 🛠️

* [JAVA 1.8](https://www.java.com/) - Lenguaje de programacion
* [Spring](https://spring.io/) - El framework web
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [Swagger](https://swagger.io/) - Documentacion
