package org.example.remitly.SwiftCodeTest.dtoTest;


import org.example.remitly.Bank.Bank;
import org.example.remitly.SwiftCode.SwiftCode;
import org.example.remitly.SwiftCode.dto.SwiftCodeMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwiftCodeMapperTest {
    @Test
    public void testMapper(){
        String address = "123 Main St";
        String bankName = "Test Bank";
        String countryISO2 = "PL";
        String countryName = "Poland";
        boolean isHeadquarter = true;
        String swiftCode = "EXAMPLE123";

        Bank bank = new Bank(address, bankName,  countryISO2, countryName, isHeadquarter, swiftCode);
        SwiftCode code = SwiftCodeMapper.mapFromHeadquartestToSwiftCode(bank);

        assertEquals(address, code.getAddress());
        assertEquals(bankName, code.getBankName());
        assertEquals(countryISO2, code.getCountryISO2());
        assertTrue(code.isHeadquarter());
        assertEquals(swiftCode,code.getSwiftCode());

    }
}
