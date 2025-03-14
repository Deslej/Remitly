package org.example.remitly.loading;

import com.opencsv.CSVReader;
import org.example.remitly.Bank.Bank;
import org.example.remitly.Bank.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    List<Bank> bankHeadquarters = new ArrayList<>();
    BankService bankService;


    public DataLoader( BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("data/Interns_2025_SWIFT_CODES.csv");

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            String[] values;
            csvReader.readNext();

            while ((values = csvReader.readNext()) != null) {
                    bankHeadquarters.add(mapToBankHeadquarters(values));
            }
        }
        bankService.saveAll(bankHeadquarters);
    }

    private Bank mapToBankHeadquarters(String[] val) {
        String countryIso2Code = val[0].toUpperCase();
        String swiftCode = val[1];
        String name = val[3].toUpperCase();
        String adress = val[4];
        String countryName = val[6].toUpperCase();
        boolean isHeadquarter = val[1].endsWith("XXX");
        return new Bank(adress,name,countryIso2Code,countryName,isHeadquarter,swiftCode);
    }
}
