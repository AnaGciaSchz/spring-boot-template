package es.nextdigital.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import es.nextdigital.model.Account;
import es.nextdigital.model.Movement;
import es.nextdigital.repository.AccountRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        this.accountService = new AccountService(accountRepository);
    }

    @Test
    void givenAnAccount_whenTheMovementsAreRequested_thenTheyAreCorrectlyReturned() {

        when(accountRepository.getReferenceById(111111L)).thenReturn(Account.builder().movement(List.of(Movement.builder().build())).build());

        assertEquals(accountService.getMovements(111111L).size(), 1);


    }

}