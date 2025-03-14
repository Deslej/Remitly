package org.example.remitly.SwiftCode.dto;

import org.example.remitly.Bank.Bank;
import org.example.remitly.SwiftCode.SwiftCode;

import java.util.ArrayList;
import java.util.List;

public class CountryISO2Mapper {
    public static CountryISO2Code mapCountryISO2Code(List<Bank> bankHeadquarters) {
        String countryISO2Code = bankHeadquarters.get(0).getCountryISO2();
        String countryName = bankHeadquarters.get(0).getCountryName();
        List<SwiftCode> swiftCodes = new ArrayList<>();
        bankHeadquarters.stream().map(SwiftCodeMapper::mapFromHeadquartestToSwiftCode).forEach(swiftCodes::add);
        return new CountryISO2Code(
                countryISO2Code,
                swiftCodes,
                countryName
        );
    }
}
