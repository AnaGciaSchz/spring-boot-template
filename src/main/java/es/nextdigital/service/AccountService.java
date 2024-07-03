package es.nextdigital.service;

import es.nextdigital.model.Account;
import es.nextdigital.model.Movement;
import es.nextdigital.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Movement> getMovements(Long id) {
         Account account = accountRepository.getReferenceById(id);
         return account.getMovement();
    }
}
