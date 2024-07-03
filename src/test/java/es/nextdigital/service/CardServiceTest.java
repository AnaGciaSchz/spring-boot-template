package es.nextdigital.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import es.nextdigital.exception.CardNotActivatedException;
import es.nextdigital.exception.InsufficientBalanceException;
import es.nextdigital.exception.InsufficientCreditScoreException;
import es.nextdigital.model.Account;
import es.nextdigital.model.card.CreditCard;
import es.nextdigital.model.card.DebitCard;
import es.nextdigital.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    public static final long VALID_ID = 111111L;
    public static final double ENOUGH_BALANCE = 10.0;
    public static final double ENOUGH_CREDIT = 10.0;
    public static final double NOT_ENOUGH_CREDIT_LIMIT = 2.0;
    public static final double NOT_ENOUGH_BALANCE_LIMIT = 2.0;
    private CardService cardService;
    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    void setUp() {
        this.cardService = new CardService(cardRepository);
    }

    @Test
    void givenAnActivatedDebitCardAndEnoughMoneyOnAccount_whenClientWantsToGetMoney_HeGetsIt(){
        when(cardRepository.getReferenceById(VALID_ID)).thenReturn(DebitCard.builder()
            .id(1L)
            .account(Account.builder()
                .balance(ENOUGH_BALANCE)
                .build())
            .isActivated(true)
            .build());
        assertEquals(cardService.getMoney(VALID_ID, 5.0),5.0);
    }

    @Test
    void givenAnActivatedCrebitCardAndEnoughCreditLimit_whenClientWantsToGetMoney_HeGetsIt(){
        when(cardRepository.getReferenceById(VALID_ID)).thenReturn(CreditCard.builder()
            .id(1L)
             .creditLimit(ENOUGH_CREDIT)
            .isActivated(true)
            .build());
        assertEquals(cardService.getMoney(VALID_ID, 5.0),5.0);
    }

    @Test
    void givenANonActivatedDebitCardAndEnoughMoneyOnAccount_whenClientWantsToGetMoney_HeDoesttGetIt(){
        when(cardRepository.getReferenceById(VALID_ID)).thenReturn(DebitCard.builder()
            .id(1L)
            .account(Account.builder()
                .balance(ENOUGH_BALANCE)
                .build())
            .isActivated(false)
            .build());
        assertThrows(CardNotActivatedException.class, () -> cardService.getMoney(VALID_ID, 5.0));
    }

    @Test
    void givenANonActivatedCrebitCardAndEnoughCreditLimit_whenClientWantsToGetMoney_HeDoesntGetIt(){
        when(cardRepository.getReferenceById(VALID_ID)).thenReturn(CreditCard.builder()
            .id(1L)
            .creditLimit(ENOUGH_BALANCE)
            .isActivated(false)
            .build());
        assertThrows(CardNotActivatedException.class, () -> cardService.getMoney(VALID_ID, 5.0));
    }

    @Test
    void givenAnActivatedDebitCardAndNotEnoughMoneyOnAccount_whenClientWantsToGetMoney_HeDoesntGetIt(){
        when(cardRepository.getReferenceById(VALID_ID)).thenReturn(DebitCard.builder()
            .id(1L)
            .account(Account.builder()
                .balance(NOT_ENOUGH_BALANCE_LIMIT)
                .build())
            .isActivated(true)
            .build());
        assertThrows(InsufficientBalanceException.class, () -> cardService.getMoney(VALID_ID, 5.0));
    }
    @Test
    void givenInvalidLanguage_thenItThrowsAnExceptionWhenWeGetByLanguage() {
        when(cardRepository.getReferenceById(VALID_ID)).thenReturn(CreditCard.builder()
            .id(1L)
            .creditLimit(NOT_ENOUGH_CREDIT_LIMIT)
            .isActivated(true)
            .build());
        assertThrows(InsufficientCreditScoreException.class, () -> cardService.getMoney(VALID_ID, 5.0));
    }
}