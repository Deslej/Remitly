package org.example.remitly.Bank;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankRepository extends CrudRepository<Bank, Long> {
    List<Bank> findByCountryISO2(String countryISO2);
    Bank findBySwiftCode(String swiftCode);
    void deleteBySwiftCode(String swiftCode);
    @Query(value = "SELECT address, BANK_NAME, COUNTRY_ISO2 , COUNTRY_NAME, is_headquarter, SWIFT_CODE " +
            "FROM banks " +
            "WHERE is_headquarter = false AND swift_code LIKE CONCAT(:swiftCode, '%')",
            nativeQuery = true)
    List<Bank> findBranchesByHeadquarterSwift(@Param("swiftCode") String swiftCode);

}
