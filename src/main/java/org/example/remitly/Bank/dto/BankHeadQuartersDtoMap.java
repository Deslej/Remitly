package org.example.remitly.Bank.dto;

import org.example.remitly.Bank.Bank;

import java.util.List;

public class BankHeadQuartersDtoMap {
    public static BankHeadquartersDto mapToBankHeadQuartersDto(Bank bank, List<BankDto> branches) {
        return new BankHeadquartersDto(
                bank.getAddress(),
                bank.getBankName(),
                branches,
                bank.getCountryISO2(),
                bank.getCountryName(),
                bank.isHeadquarter(),
                bank.getSwiftCode()
        );
    }
    public static BankDto mapToBank(Bank bank) {
        return new BankDto(bank.getAddress(),
                bank.getBankName(),
                bank.getCountryISO2(),
                bank.isHeadquarter(),
                bank.getSwiftCode());
    }
}
