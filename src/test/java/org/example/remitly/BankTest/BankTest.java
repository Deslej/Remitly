package org.example.remitly.BankTest;

import org.example.remitly.Bank.Bank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {

    @Test
    public void testConstructor() {
        String address = "123 Main St";
        String bankName = "Test Bank";
        String countryISO2 = "PL";
        String countryName = "Poland";
        boolean isHeadquarter = true;
        String swiftCode = "EXAMPLE123";

        Bank bank = new Bank(address, bankName,  countryISO2, countryName, isHeadquarter, swiftCode);

        assertEquals(address, bank.getAddress());
        assertEquals(bankName, bank.getBankName());
        assertEquals(countryISO2, bank.getCountryISO2());
        assertEquals(countryName, bank.getCountryName());
        assertTrue(bank.isHeadquarter());
        assertEquals(swiftCode,bank.getSwiftCode());
    }
}
