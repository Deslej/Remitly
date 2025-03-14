package org.example.remitly.BankTest;

import org.example.remitly.Bank.Bank;
import org.example.remitly.Bank.BankRepository;
import org.example.remitly.Bank.BankService;
import org.example.remitly.Bank.dto.BankHeadquartersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BankServiceTest {
    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankService bankService;

    private Bank bank;
    private Bank branchBank;
    private List<Bank> banks;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        bank = new Bank("123 Main St", "Test Bank", "PL", "Poland", true, "EXAMPLE123");
        branchBank = new Bank("456 Branch St", "Branch Bank", "PL", "Poland", false, "EXAMPLE123A");

        banks = Arrays.asList(bank, branchBank);
    }

    @Test
    void testFindByCountryIso2Code() {
        when(bankRepository.findByCountryISO2("PL")).thenReturn(banks);

        List<Bank> result = bankService.findByCountryIso2Code("PL");

        assertEquals(2, result.size());
        verify(bankRepository).findByCountryISO2("PL");
    }

    @Test
    void testFindBySwiftCode() {
        when(bankRepository.findBySwiftCode("EXAMPLE123")).thenReturn(bank);

        Bank result = bankService.findBySwiftCode("EXAMPLE123");

        assertNotNull(result);
        assertEquals("Test Bank", result.getBankName());
        verify(bankRepository).findBySwiftCode("EXAMPLE123");
    }

    @Test
    void testSaveBySwiftCode() {
        bankService.saveBySwiftCode(bank);

        verify(bankRepository).save(bank);
    }

    @Test
    void testDeleteBySwiftCode() {
        when(bankRepository.findBySwiftCode("EXAMPLE123")).thenReturn(bank);

        bankService.deleteBySwiftCode("EXAMPLE123");

        verify(bankRepository).deleteBySwiftCode("EXAMPLE123");
    }

    @Test
    void testSaveAll() {
        bankService.saveAll(banks);

        verify(bankRepository).saveAll(banks);
    }

    @Test
    void testGetBranchBanksByHeadquarterSwiftCode() {
        when(bankRepository.findBranchesByHeadquarterSwift("EXAMPLE123")).thenReturn(banks.subList(1, 2));

        List<Bank> result = bankService.getBranchBanksByHeadquarterSwiftCode("EXAMPLE123");

        assertEquals(1, result.size());
        verify(bankRepository).findBranchesByHeadquarterSwift("EXAMPLE123");
    }

    @Test
    void testGetBankHeadquartersDto() {
        when(bankRepository.findBranchesByHeadquarterSwift("EXAMPLE123".substring(0,8))).thenReturn(banks.subList(1, 2));

        BankHeadquartersDto result = bankService.getBankHeadquartersDto(bank);

        assertNotNull(result);
        assertEquals("Test Bank", result.getBankName());
        verify(bankRepository).findBranchesByHeadquarterSwift("EXAMPLE123".substring(0,8));
    }
}
