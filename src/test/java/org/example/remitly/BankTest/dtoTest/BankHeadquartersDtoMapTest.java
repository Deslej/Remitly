package org.example.remitly.BankTest.dtoTest;

import org.example.remitly.Bank.Bank;
import org.example.remitly.Bank.dto.BankDto;
import org.example.remitly.Bank.dto.BankHeadQuartersDtoMap;
import org.example.remitly.Bank.dto.BankHeadquartersDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BankHeadquartersDtoMapTest {
    @Test
    public void testMapToBank() {
        String address = "123 Main St";
        String bankName = "Test Bank";
        String countryISO2 = "PL";
        String countryName = "Poland";
        boolean isHeadquarter = true;
        String swiftCode = "EXAMPLE123";

        Bank bank = new Bank(address, bankName,  countryISO2, countryName, isHeadquarter, swiftCode);

        BankDto bankDto = BankHeadQuartersDtoMap.mapToBank(bank);

        assertEquals(address, bankDto.getAddress());
        assertEquals(bankName, bankDto.getBankName());
        assertEquals(countryISO2, bankDto.getCountryISO2());
        assertTrue(bankDto.isHeadquarter());
        assertEquals(swiftCode,bankDto.getSwiftCode());
    }
    @Test
    public void testMapToHeadquartersDtoMap() {
        String address = "123 Main St";
        String bankName = "Test Bank";
        String countryISO2 = "PL";
        String countryName = "Poland";
        List<BankDto> branches = List.of(new BankDto(), new BankDto());
        boolean isHeadquarter = true;
        String swiftCode = "EXAMPLE123";

        Bank bank = new Bank(address, bankName,  countryISO2, countryName, isHeadquarter, swiftCode);

        BankHeadquartersDto dto = BankHeadQuartersDtoMap.mapToBankHeadQuartersDto(bank,branches);

        assertEquals(address, dto.getAddress());
        assertEquals(bankName, dto.getBankName());
        assertEquals(branches, dto.getBranches());
        assertEquals(countryISO2, dto.getCountryISO2());
        assertEquals(countryName, dto.getCountryName());
        assertEquals(isHeadquarter, dto.isHeadquarter());
        assertEquals(swiftCode, dto.getSwiftCode());
    }

}
