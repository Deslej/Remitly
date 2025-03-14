package org.example.remitly.SwiftCodeTest.dtoTest;

import org.example.remitly.Bank.Bank;
import org.example.remitly.SwiftCode.dto.CountryISO2Code;
import org.example.remitly.SwiftCode.dto.CountryISO2Mapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountryISO2MapperTest {
    @Test
    public void testMapCountryISO2Code(){

            Bank bank1 = new Bank("123 Main St", "Test Bank", "PL", "Poland", true, "EXAMPLE123");
            Bank bank2 = new Bank("456 Elm St", "Sample Bank", "PL", "Poland", false, "EXAMPLE456");

            List<Bank> bankList = new ArrayList<>();
            bankList.add(bank1);
            bankList.add(bank2);

            CountryISO2Code result = CountryISO2Mapper.mapCountryISO2Code(bankList);

            assertNotNull(result);
            assertEquals("PL", result.getCountryISO2());
            assertEquals("Poland", result.getCountryName());
            assertEquals(2, result.getSwiftCodes().size());


    }
}
