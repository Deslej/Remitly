package org.example.remitly.BankTest.dtoTest;

import org.example.remitly.Bank.dto.BankDto;
import org.example.remitly.Bank.dto.BankHeadquartersDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankHeadquartersDtoTest {
    @Test
    public void testConstructor() {
        String address = "123 Main St";
        String bankName = "Test Bank";
        List<BankDto> branches = List.of(new BankDto(), new BankDto());
        String countryISO2 = "PL";
        String countryName = "Poland";
        boolean isHeadquarter = true;
        String swiftCode = "EXAMPLE123";

        BankHeadquartersDto dto = new BankHeadquartersDto(address, bankName, branches, countryISO2, countryName, isHeadquarter, swiftCode);

        assertEquals(address, dto.getAddress());
        assertEquals(bankName, dto.getBankName());
        assertEquals(branches, dto.getBranches());
        assertEquals(countryISO2, dto.getCountryISO2());
        assertEquals(countryName, dto.getCountryName());
        assertEquals(isHeadquarter, dto.isHeadquarter());
        assertEquals(swiftCode, dto.getSwiftCode());
    }
}
