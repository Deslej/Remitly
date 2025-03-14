package org.example.remitly.SwiftCode.dto;

import org.example.remitly.Bank.Bank;
import org.example.remitly.SwiftCode.SwiftCode;


public class SwiftCodeMapper {
    public  static SwiftCode mapFromHeadquartestToSwiftCode(Bank bank) {
        return new SwiftCode(
                bank.getAddress(),
                bank.getBankName(),
                bank.getCountryISO2(),
                bank.isHeadquarter(),
                bank.getSwiftCode()
        );
    }


}
