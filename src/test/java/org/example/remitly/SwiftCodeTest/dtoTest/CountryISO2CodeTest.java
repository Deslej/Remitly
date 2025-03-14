package org.example.remitly.SwiftCodeTest.dtoTest;

import org.example.remitly.SwiftCode.SwiftCode;
import org.example.remitly.SwiftCode.dto.CountryISO2Code;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryISO2CodeTest {

    @Test
    public void testConstructor() {
        String countryISO2 = "PL";
        String countryName = "Poland";
        List<SwiftCode> swiftCodeList = List.of(new SwiftCode());

        CountryISO2Code code = new CountryISO2Code( countryISO2,swiftCodeList,countryName);

        assertEquals(countryISO2, code.getCountryISO2());
        assertEquals(countryName, code.getCountryName());
        assertEquals(swiftCodeList, code.getSwiftCodes());

    }
}
