package es.nextdigital.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import es.nextdigital.model.Account;
import es.nextdigital.model.card.Card;
import es.nextdigital.model.card.DebitCard;
import es.nextdigital.service.CardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardControllerIntegrationTest {

    public static final String BANK = "NextDigitalBank";
    public static final String PIN = "245698";
    public static final long ID = 1L;
    private CardService cardService;

    @Test
    @DisplayName("Happy Path Test: A card is correctly saved into a repository")
    void givenCorrectCard_whenSaveCard_thenReturnCardobject() throws Exception {
        Card savedCard = cardService.saveCard(DebitCard.builder().id(ID).isActivated(false).pin(PIN).bank(BANK).account(
            Account.builder().build()).build());

        assertNotNull(savedCard);
        assertEquals(PIN, savedCard.getPin());
        assertEquals(BANK, savedCard.getBank());
        assertEquals(false, savedCard.getIsActivated());
    }

}
