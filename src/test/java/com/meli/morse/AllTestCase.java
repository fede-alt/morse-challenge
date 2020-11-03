package com.meli.morse;

import com.meli.morse.model.SignalTestCase;
import com.meli.morse.model.TransmissionTestCase;
import com.meli.morse.service.MorseServiceImplTest;
import com.meli.morse.utils.MorseBitReaderTestCase;
import com.meli.morse.utils.SignalFactoryTestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SignalTestCase.class,
    TransmissionTestCase.class,
    SignalFactoryTestCase.class,
    MorseBitReaderTestCase.class,
    MorseServiceImplTest.class
})
public class AllTestCase {
}
