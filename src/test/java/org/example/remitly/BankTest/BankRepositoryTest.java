package org.example.remitly.BankTest;

import org.example.remitly.Bank.Bank;
import org.example.remitly.Bank.BankRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BankRepositoryTest {

    @Autowired
    private BankRepository bankRepository;

    @BeforeEach
    void setUp() {
        Bank bank = new Bank("123 Main St", "Test Bank", "PL", "Poland", true, "EXAMPLE123");
        Bank bank1 = new Bank("123 Main St", "Test Bank", "PL", "Poland", true, "EXAMPLEXXXX");
        Bank bank2 = new Bank("456 Another St", "Branch Bank", "PL", "Poland", false, "EXAMPLEX123");
        Bank bank3 = new Bank("789 Third St", "Branch Bank", "PL", "Poland", false, "EXAMPLEX321");
        bankRepository.save(bank1);
        bankRepository.save(bank2);
        bankRepository.save(bank3);
        bankRepository.save(bank);
    }

    @Test
    void testFindByCountryISO2() {
        List<Bank> banks = bankRepository.findByCountryISO2("PL");
        assertEquals(4, banks.size(), "Powinno być 3 banki w Polsce");
    }

    @Test
    void testFindBySwiftCode() {
        Bank bank = bankRepository.findBySwiftCode("EXAMPLE123");
        assertNotNull(bank, "Bank z tym SWIFT Code powinien istnieć");
        assertEquals("Test Bank", bank.getBankName(), "Nazwa banku powinna odpowiadać");
    }

    @Test
    void testDeleteBySwiftCode() {
        bankRepository.deleteBySwiftCode("EXAMPLE123");
        Bank bank = bankRepository.findBySwiftCode("EXAMPLE123");
        assertNull(bank, "Bank z tym SWIFT Code powinien zostać usunięty");
    }

    @Test
    void testFindBranchesByHeadquarterSwift() {
        List<Bank> branches = bankRepository.findBranchesByHeadquarterSwift("EXAMPLEX");
        assertEquals(2, branches.size(), "Powinno być 2 oddziały dla tego głównego oddziału");
    }
}