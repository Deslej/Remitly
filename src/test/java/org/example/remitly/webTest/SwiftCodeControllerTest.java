package org.example.remitly.webTest;

import org.example.remitly.Bank.Bank;
import org.example.remitly.Bank.BankService;
import org.example.remitly.Bank.dto.BankHeadquartersDto;
import org.example.remitly.web.SwiftCodeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;


import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SwiftCodeControllerTest {

    @Mock
    private BankService bankService;

    @InjectMocks
    private SwiftCodeController swiftCodeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(swiftCodeController).build();
    }


    @Test
    public void testSaveSwiftCode_Success() throws Exception {
        Bank newBank = new Bank();
        newBank.setSwiftCode("EX12345789");
        newBank.setCountryISO2("US");
        newBank.setCountryName("United States");
        newBank.setHeadquarter(true);
        newBank.setBankName("Test Bank");
        newBank.setAddress("address");

        when(bankService.findBySwiftCode("EX12345789")).thenReturn(null);

        mockMvc.perform(post("/v1/swift-codes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"swiftCode\":\"EX12345789\"," +
                                "\"bankName\":\"Test Bank\"," +
                                "\"countryISO2\":\"US\"," +
                                "\"countryName\":\"United States\"," +
                                "\"isHeadquarter\":true," +
                                "\"address\":\"address\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("SWIFT Code added successfully"));


        ArgumentCaptor<Bank> bankCaptor = ArgumentCaptor.forClass(Bank.class);
        verify(bankService, times(1)).saveBySwiftCode(bankCaptor.capture());

        Bank capturedBank = bankCaptor.getValue();
        assertEquals("EX12345789", capturedBank.getSwiftCode());
        assertEquals("Test Bank", capturedBank.getBankName());
        assertEquals("US", capturedBank.getCountryISO2());
        assertEquals("United States", capturedBank.getCountryName());
        assertTrue(capturedBank.isHeadquarter());
        assertEquals("address", capturedBank.getAddress());

    }


    @Test
    public void testSaveSwiftCode_AlreadyExists() throws Exception {
        Bank existingBank = new Bank();
        existingBank.setSwiftCode("EX12345789");
        existingBank.setBankName("Existing Bank");
        existingBank.setCountryISO2("US");
        existingBank.setCountryName("United States");
        existingBank.setHeadquarter(true);
        existingBank.setAddress("address");

        when(bankService.findBySwiftCode("EX12345789")).thenReturn(existingBank);

        mockMvc.perform(post("/v1/swift-codes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"swiftCode\":\"EX12345789\",\"bankName\":\"Existing Bank\", " +
                                "\"countryISO2\":\"US\",\"countryName\":\"United States\",\"isHeadquarter\":true,\"address\":\"address\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("SWIFT Code already exists"));

        verify(bankService, times(0)).saveBySwiftCode(existingBank);
    }

    @Test
    public void testSaveSwiftCode_InvalidData() throws Exception {
        Bank invalidBank = new Bank();
        invalidBank.setSwiftCode("");
        invalidBank.setBankName("Existing Bank");
        invalidBank.setCountryISO2("US");
        invalidBank.setCountryName("United States");
        invalidBank.setHeadquarter(true);
        invalidBank.setAddress("address");

        mockMvc.perform(post("/v1/swift-codes/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"swiftCode\":\"\",\"bankName\":\"Existing Bank\", " +
                                "\"countryISO2\":\"US\",\"countryName\":\"United States\",\"isHeadquarter\":true,\"address\":\"address\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("SWIFT Code cannot be empty")))
                .andExpect(content().string(containsString("SWIFT Code must be between 8 and 11 characters")));

        verify(bankService, times(0)).saveBySwiftCode(invalidBank);
    }

    @Test
    public void testFindBySwiftCode_Success() throws Exception {
        String swiftCode = "EX12345";
        Bank bank = new Bank();
        bank.setSwiftCode(swiftCode);
        bank.setHeadquarter(false);
        bank.setBankName("Test Bank");

        when(bankService.findBySwiftCode(swiftCode)).thenReturn(bank);

        mockMvc.perform(get("/v1/swift-codes/{swiftCode}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.swiftCode").value(swiftCode))
                .andExpect(jsonPath("$.bankName").value("Test Bank"));
    }

    @Test
    public void testFindBySwiftCode_NotFound() throws Exception {
        String swiftCode = "EX12345";
        when(bankService.findBySwiftCode(swiftCode)).thenReturn(null);

        mockMvc.perform(get("/v1/swift-codes/{swiftCode}", swiftCode))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No bank found for the provided swiftCode: " + swiftCode));
    }

    @Test
    public void testFindByCountryIso2Code_Success() throws Exception {
        String countryIso2Code = "US";
        Bank bank = new Bank();
        bank.setSwiftCode("EX12345");
        bank.setHeadquarter(false);
        bank.setBankName("Test Bank");
        when(bankService.findByCountryIso2Code(countryIso2Code)).thenReturn(Collections.singletonList(bank));

        mockMvc.perform(get("/v1/swift-codes/country/{countryISO2code}", countryIso2Code))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.swiftCodes[0].swiftCode").value("EX12345"))
                .andExpect(jsonPath("$.swiftCodes[0].bankName").value("Test Bank"));
    }

    @Test
    public void testFindByCountryIso2Code_NotFound() throws Exception {
        String countryIso2Code = "XX";
        when(bankService.findByCountryIso2Code(countryIso2Code)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/swift-codes/country/{countryISO2code}", countryIso2Code))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No bank found for the provided country: " + countryIso2Code));
    }

    @Test
    public void testDeleteSwiftCode_Success() throws Exception {
        String swiftCode = "EX12345";
        Bank bank = new Bank();
        bank.setSwiftCode(swiftCode);
        when(bankService.findBySwiftCode(swiftCode)).thenReturn(bank);

        mockMvc.perform(delete("/v1/swift-codes/{swiftCode}", swiftCode))
                .andExpect(status().isOk())
                .andExpect(content().string("SWIFT Code delete successfully"));

        verify(bankService, times(1)).deleteBySwiftCode(swiftCode);
    }

    @Test
    public void testDeleteSwiftCode_NotFound() throws Exception {
        String swiftCode = "EX12345";
        when(bankService.findBySwiftCode(swiftCode)).thenReturn(null);

        mockMvc.perform(delete("/v1/swift-codes/{swiftCode}", swiftCode))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No bank found for the provided swiftCode: " + swiftCode));
    }
}
