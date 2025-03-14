package org.example.remitly.Bank;

import jakarta.transaction.Transactional;
import org.example.remitly.Bank.dto.BankDto;
import org.example.remitly.Bank.dto.BankHeadQuartersDtoMap;
import org.example.remitly.Bank.dto.BankHeadquartersDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    public List<Bank> findByCountryIso2Code(String countryIso2Code) {
        return bankRepository.findByCountryISO2(countryIso2Code);
    }
    public Bank findBySwiftCode(String swiftCode) {
        return bankRepository.findBySwiftCode(swiftCode);
    }
    @Transactional
    public void saveBySwiftCode(Bank bank) {
        bankRepository.save(bank);
    }
    @Transactional
    public void deleteBySwiftCode(String swiftCode) {
        bankRepository.deleteBySwiftCode(swiftCode);
    }
    public void saveAll(List<Bank> bankHeadquarters) {
        this.bankRepository.saveAll(bankHeadquarters);
    }
    public List<Bank> getBranchBanksByHeadquarterSwiftCode(String swiftCode) {
        return bankRepository.findBranchesByHeadquarterSwift(swiftCode);
    }
    public BankHeadquartersDto getBankHeadquartersDto(Bank bank) {
        List<BankDto> banks = getBranchBanksByHeadquarterSwiftCode(bank.getSwiftCode().substring(0,8))
                .stream()
                .map(BankHeadQuartersDtoMap::mapToBank)
                .toList();
        return BankHeadQuartersDtoMap.mapToBankHeadQuartersDto(bank, banks);
    }

}
