package org.example.remitly.SwiftCode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"address", "bankName", "countryISO2", "isHeadquarter", "swiftCode"})
public class SwiftCode {
    private String address;
    private String bankName;
    private String countryISO2;
    private boolean headquarter;
    private String swiftCode;

    public SwiftCode() {}

    public SwiftCode(String address, String bankName, String countryISO2, boolean headquarter, String swiftCode) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.headquarter = headquarter;
        this.swiftCode = swiftCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }
    @JsonProperty("isHeadquarter")
    public boolean isHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(boolean headquarter) {
        this.headquarter = headquarter;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
