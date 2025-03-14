package org.example.remitly.web;

import jakarta.validation.Valid;
import org.example.remitly.Bank.Bank;
import org.example.remitly.Bank.BankService;

import org.example.remitly.Bank.dto.BankHeadquartersDto;
import org.example.remitly.SwiftCode.dto.CountryISO2Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/swift-codes/")
public class SwiftCodeController {
    BankService bankService;


    public SwiftCodeController(BankService bankService) {
        this.bankService = bankService;

    }

    @GetMapping("/{swiftCode}")
    public  ResponseEntity<?> findBySwiftCode(@PathVariable("swiftCode") String swiftCode) {
        Bank bank = bankService.findBySwiftCode(swiftCode);

        if (bank == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No bank found for the provided swiftCode: " + swiftCode);
        }

        if (bank.isHeadquarter()) {
            BankHeadquartersDto bankHeadquartersDto = bankService.getBankHeadquartersDto(bank);
            return ResponseEntity.ok(bankHeadquartersDto);
        }

        return ResponseEntity.ok(bank);
    }
    @GetMapping("/country/{countryISO2code}")
    public ResponseEntity<?> findByCountryIso2Code(@PathVariable("countryISO2code") String countryIso2Code) {
        List<Bank> banks = bankService.findByCountryIso2Code(countryIso2Code);
        if (banks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bank found for the provided country: " + countryIso2Code);
        }else {
            return ResponseEntity.ok(CountryISO2Mapper.mapCountryISO2Code(banks));
        }
    }

    @PostMapping("")
    public ResponseEntity<String> saveSwiftCode(@Valid @RequestBody Bank bank1, BindingResult bindingResult) {
        Bank bank= bankService.findBySwiftCode(bank1.getSwiftCode());

        if (bindingResult.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));

            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }

        if(bank==null) {
            bankService.saveBySwiftCode(bank1);
            return ResponseEntity.ok("SWIFT Code added successfully");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("SWIFT Code already exists");
        }
    }

    @DeleteMapping("/{swiftCode}")
    public ResponseEntity<String> deleteSwiftCode(@PathVariable("swiftCode") String swiftCode) {
        Bank bank = bankService.findBySwiftCode(swiftCode);
        if(bank==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bank found for the provided swiftCode: " + swiftCode);
        }else{
            bankService.deleteBySwiftCode(swiftCode);
            return ResponseEntity.ok("SWIFT Code delete successfully");
        }
    }

}
