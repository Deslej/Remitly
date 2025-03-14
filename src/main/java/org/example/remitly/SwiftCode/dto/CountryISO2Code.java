package org.example.remitly.SwiftCode.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.example.remitly.SwiftCode.SwiftCode;

import java.util.List;

@JsonPropertyOrder({"countryISO2", "countryName", "swiftCodes"})
public class CountryISO2Code {
    private String countryISO2;
    private String countryName;
    private List<SwiftCode> swiftCodes;

    public CountryISO2Code(String countryISO2, List<SwiftCode> swiftCodes, String countryName) {
        this.countryISO2 = countryISO2;
        this.swiftCodes = swiftCodes;
        this.countryName = countryName;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<SwiftCode> getSwiftCodes() {
        return swiftCodes;
    }

    public void setSwiftCodes(List<SwiftCode> swiftCodes) {
        this.swiftCodes = swiftCodes;
    }
}
