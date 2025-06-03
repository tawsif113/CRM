package com.crm.serviceImpl;

import com.crm.enumTypes.AccountType;
import com.crm.exception.NotFoundException;
import com.crm.model.*;
import com.crm.repository.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final PaymentTermsRepository paymentTermsRepository;
    private final AccountRepository accountRepository;
    private final PriceListRepository priceListRepository;
    private final AccountLedgerRepository accountLedgerRepository;
    private final SalesPersonRepository salesPersonRepository;

    ///  TESTING PURPOSES ONLY

    @PostConstruct
    public void init() {
        // Payment Terms
        if (paymentTermsRepository.count() == 0) {
            PaymentTerms net30 = new PaymentTerms();
            net30.setTermsName("Net 30 Days");
            net30.setNetDays(30);
            net30.setDescription("Payment due in 30 days");
            paymentTermsRepository.save(net30);
        }

        // Accounts
        if (accountRepository.count() == 0) {
            Account receivable = new Account();
            receivable.setAccountName("Receivable Account");
            receivable.setAccountCode("ACC-RECV-001");
            receivable.setAccountType(AccountType.RECEIVABLE);
            accountRepository.save(receivable);

            Account advance = new Account();
            advance.setAccountName("Advance Account");
            advance.setAccountCode("ACC-ADV-001");
            advance.setAccountType(AccountType.ADVANCE);
            accountRepository.save(advance);
        }

        // Price List
        if (priceListRepository.count() == 0) {
            PriceList defaultPriceList = new PriceList();
            defaultPriceList.setPriceListName("Standard Price List");
            defaultPriceList.setCurrencyCode("USD");
            defaultPriceList.setMarkupPercentage(0.1);
            priceListRepository.save(defaultPriceList);
        }

        if (accountLedgerRepository.count() == 0) {
            AccountingLedger ledger = new AccountingLedger();
            ledger.setDescription("Initial Ledger Entry");

            Account receivableAccount = accountRepository.findByAccountCode("ACC-RECV-001")
                    .orElseThrow(() -> new NotFoundException("Receivable Account not found"));

            ledger.setAccount(receivableAccount.getAccountName());

            accountLedgerRepository.save(ledger);
        }

        if (salesPersonRepository.count() == 0) {
            SalesPerson salesperson = new SalesPerson();
            salesperson.setFirstName("John");
            salesperson.setLastName("Doe");
            salesperson.setEmail("john.doe@example.com");
            salesPersonRepository.save(salesperson);
        }

    }


}
