package org.example.remitly.BankTest.dtoTest;


import org.example.remitly.Bank.dto.BankDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankDtoTest {
    @Test
    public void testConstructor() {
        String address = "123 Main St";
        String bankName = "Test Bank";
        String countryISO2 = "PL";
        String swiftCode = "EXAMPLE123";

        BankDto bank = new BankDto(address, bankName, countryISO2, true, swiftCode);
        assertEquals(address, bank.getAddress());
        assertEquals(bankName, bank.getBankName());
        assertEquals(countryISO2, bank.getCountryISO2());
        assertTrue(bank.isHeadquarter());
        assertEquals(swiftCode,bank.getSwiftCode());
    }
}
