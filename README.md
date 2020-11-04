# morse-challenge


## Resumen del Enunciado 📝

Se pide implementar en JAVA :

**1.** Una función decodeBits2Morse que dada una secuencia de bits, retorne un string con el resultado en MORSE.

**2.** Una función translate2Human que tome el string en MORSE y retorne un string legible por un humano.

✴️ **BONUS:**

**A.** Diseñar una API que permita traducir texto de MORSE a lenguaje humano y
visceversa.

**B.** Hostear la API en un cloud público (como app engine o cloud foundry) y enviar la
URL para consulta

-------------------------------------------------------------------------------
## Resolución

**1)** [decodeBits2Morse](https://github.com/fede-alt/morse-challenge/blob/master/src/main/java/com/meli/morse/utils/MorseBitReader.java#L30)

**2)** [MorseTranslator::translate](https://github.com/fede-alt/morse-challenge/blob/master/src/main/java/com/meli/morse/utils/MorseTranslator.java#L45)

**2)(_inversa_)** [TextTranslator::translate](https://github.com/fede-alt/morse-challenge/blob/master/src/main/java/com/meli/morse/utils/TextTranslator.java#L34)

-------------------------------------------------------------------------------

 ### BONUS - [API](https://github.com/fede-alt/morse-challenge/blob/master/src/main/java/com/meli/morse/service/MorseService.java#L15)
 
   - HOST: 34.95.148.121
   
   - PUERTO: 9090
   
   - ENDPOINTS:   _(ejemplos con curl)_
   
     - **POST /translate/morse2text**

            curl --location --request POST 'http://34.95.148.121:9090/translate/morse2text' \
              --header 'Content-Type: application/json' \
              --data-raw '{
                  "text" : ".- . -.-"
              }'


     - **POST /translate/text2morse**

            curl --location --request POST 'http://34.95.148.121:9090/translate/text2morse' \
              --header 'Content-Type: application/json' \
              --data-raw '{
                  "text" : "hola"
              }'


     - **POST /translate/binary2morse**

            curl --location --request POST 'http://34.95.148.121:9090/translate/binary2morse' \
              --header 'Content-Type: application/json' \
              --data-raw '{
                  "text" : "101110001000111010111"
              }'
      
      
   
  ### SWAGGER:  
  
  - **[(DOCUMENTACIÓN / PLAYGROUND)](http://34.95.148.121:9090/translate/swagger-ui/)**


-------------------------------------------------------------------------------
# MODELADO

## 1. BitDecoder

La idea inicial es decodificar una secuencia de bits a morse. Para enfrentar este problema modelé las siguiente clases en base a objetos que pueden representan una secuencia de bits.
El bit es un dígito binario, que puede tomar dos valores 0 o 1, por ende puede ser representado con un boolean. Éste forma parte de un número, por lo que el orden tiene importancia y como estos vienen en secuencia puedos llevarlos a una lista para introducir el aspecto de orden.

Generalizando, una secuencia de bits puede interpretarse como una señal, donde puedo decir que existen dos tipos de señales:

   -señal en alto (**Pulso**) 
   
   o
   
   -señal en baja (**Pausa**)
   
   ![Signal](https://www.electronicspoint.com/themes/user/site/default/asset/img/articles/line-graph-digital-signal.jpg)
   
Cada señal posee una duración.
 
Abstrayendome otro nivel puedo definir una **Transmisión** como un conjunto ordenado de estas señales. 
Resumiendo tenemos los siguientes conjuntos:
```
     {bit} ϵ {Señal} ϵ {Transmision}
```
Por enunciado sé que las señales de la transmisión son consistentes, en otras palabras, esto quiere decir que puntos y guiones son distinguibles, asi como también lo son las pausas, los espacios entre caracter y los espacios entre palabras.
Siguiendo las [reglas básicas temporales](https://en.wikipedia.org/wiki/Morse_code#Representation,_timing,_and_speeds) del lenguaje morse se puede definir un contexto para distinguir las señales.

La lógica que elegí fue la siguiente:
Sea la mínima duración de una señal, se define la **tolerancia** como la relación obligatoria que debe existir entre cada tipo de interpretación de esa señal y su mínimo.

Se propone entonces la existencia de un **Contexto** de transmisión que permita dicha distincion, y que de esta manera cada señal pueda ser intepretada a su significado morse. 
Por ejemplo, siguiendo el standard de timing de las [reglas basicas](https://en.wikipedia.org/wiki/Morse_code#Representation,_timing,_and_speeds):
Tenemos que un punto tiene una unidad de duración, mientras que el guión tiene tres. La relacion temporal entonces es 1 Tguión = 3 Tpunto, de aquí la **tolerancia** para identificar un guión será valor de 3 unidades minimas, o superior.


El modelo es apto para uso recursivo, es decir que puede utilizarse en una aplicacion que contenga un listener del input de un usuario, por ejemplo un KeyListener, siempre y cuando ese input sea llevado a una lista de Boolean.

![Diagrama](https://i.imgur.com/5zhR8kb.jpg)

  _Simplificación en diagrama de clases_


#### Condiciones 📑

Para definir el contexto es necesario que la transmisión contenga ambas posibilidades de interpretación de la señal. (Es decir que no debe suceder tener una secuencia de 0s y 1s de longitud fija, pues estos serán interpretados como la mínima unidad morse respectiva, punto o guión)
El motivo es justamente asignar esa minima duracion de señal al punto morse, o pausa.
Esto quiere decir que no se podrá traducir una señal 111000111 como "- -", pues el contexto que propuse llevará a una interpretacion ".."
En resumen, debe estar presente aquel caracter de menor duracion al que se quiera representar, por ejemplo si desea representar un guión, la transmision deberá contener al menos un punto, y de manera análoga para las Pausas.

#### Configurables: 🔧
  **tolerance:** la tolerancia podrá ser configurable. El número de cada tolerancia debe respetar a la tolerancia de nivel inferior. Por ejemplo, la tolerancia de un word-space debe ser mayor a la de un char-space. Se declara en el [application.yml](https://github.com/fede-alt/morse-challenge/blob/master/src/main/resources/application.yml)
  
  Campo **coerce:**  forzado de la traducción, equivale a ignorar "basura" o "interferencia" (fallas de parseo por caracteres inválidos). Forma parte de la request (boolean).
  
 
#### Manejo de errores:
  En el caso de que no se ignore la interferencia se lanzará una MorseException con la información suficiente para ubicar el problema, que en este caso será el index del char y su contenido.

-------------------------------------------------------------------------------


## 2. Traductor morse a texto
Como este método será ser utilizado por el servicio y además debe tener una inverso, me propuse utilizar dos diccionarios que representan una Look Up Table (LUT) respectivamente para cada proceso, siempre utilizando de base un diccionario de entrada, que será customizable siguiendo el contrato de (char->morseTerm)
La idea es granular el problema una vez parseado el texto:

  Para cada caracter de texto, se busca una traducción a morse.
  
  Para cada término morse se busca su traducción a texto.
  
  #### Manejo de errores:
  En el caso de que no poder traducir un termino morse o un caracter "humano" se lanzará una MorseException con la información suficiente para ubicar el problema, que en este caso será el index inicial del termino y su contenido.  
  
  #### Condiciones 📑

 Manejo de espacios: Se entiende que varios espacios entre palabras juntos no tienen sentido y por ende se llevan a un solo espacio para estandarizar.
 
 Cualquier traducción se devuelve trimmeada (es decir que es imposible que esté wrappeada entre espacios).
 
 Toda traducción a texto será devuelta en mayúscula.
  
  
#### Configurables: 🔧

  _Parametros configurables de la API en su [application.yml](https://github.com/fede-alt/morse-challenge/blob/master/src/main/resources/application.yml)_
  
  **translator.diccionary:** el diccionario está declarado en el YML posibilitando insertar más símbolos.
  
  Campo **coerce:**  forzado de la traducción, equivale a ignorar la imposibilidad de traducción de algun término. Forma parte de la request (boolean).
  
  
-------------------------------------------------------------------------------
  

## Herramientas utilizadas 🛠️

* [JAVA 1.8](https://www.java.com/) - Lenguaje de programación
* [SpringFramework](https://spring.io/) - Framework
* [Maven 3](https://maven.apache.org/) - Manejador de dependencias
* [Swagger 3](https://swagger.io/) - Documentación de la API


## HOW TOs 

- **Build:
   En el directorio raiz del pom.xml correr:
   
       mvn clean install compile
  
- **Run:
   Correr el jar generado referenciando al archivo de configuración [application.yml](https://github.com/fede-alt/morse-challenge/blob/master/src/main/resources/application.yml).
   Ejemplo:
   
      java -jar <JAR_FILE> <YML_CONFIG_FILE>
