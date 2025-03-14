package org.example.remitly.Bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "banks")
@JsonPropertyOrder({"address", "bankName", "countryISO2", "countryName", "isHeadquarter", "swiftCode"})
@JsonIgnoreProperties(ignoreUnknown = false)
public class Bank {
    @NotEmpty(message = "Address name cannot be empty")
    private String address;
    @NotBlank(message = "Bank name cannot be empty")
    private String bankName;
    @NotBlank(message = "Country ISO2 cannot be empty")
    @Column(name = "country_iso2", length = 2)
    private String countryISO2;
    @NotBlank(message = "Country name cannot be empty")
    private String countryName;
    @NotNull(message = "Headquarter status is required")
    private Boolean isHeadquarter;
    @Id
    @Size(min = 8, max = 11, message = "SWIFT Code must be between 8 and 11 characters")
    @NotBlank(message = "SWIFT Code cannot be empty")
    @Column(name = "swift_code", unique = true, length = 11)
    private String swiftCode;

    public Bank() {
    }

    public Bank(String address, String bankName, String countryISO2, String countryName, boolean isHeadquarter, String swiftCode) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
    }

    @JsonProperty("isHeadquarter")
    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public void setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
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
}
