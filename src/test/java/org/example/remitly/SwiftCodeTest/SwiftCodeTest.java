package org.example.remitly.SwiftCodeTest;

import org.example.remitly.SwiftCode.SwiftCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwiftCodeTest {
    @Test
            public void testConstructor() {
        String address = "123 Main St";
        String bankName = "Test Bank";
        String countryISO2 = "PL";
        boolean isHeadquarter = true;
        String swiftCode = "EXAMPLE123";

        SwiftCode code = new SwiftCode(address, bankName,  countryISO2, isHeadquarter, swiftCode);

        assertEquals(address, code.getAddress());
        assertEquals(bankName, code.getBankName());
        assertEquals(countryISO2, code.getCountryISO2());
        assertTrue(code.isHeadquarter());
        assertEquals(swiftCode,code.getSwiftCode());
    }

}
