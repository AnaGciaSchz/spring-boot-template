package es.nextdigital.service;


import es.nextdigital.model.card.Card;
import es.nextdigital.model.card.DebitCard.DebitCardBuilder;
import es.nextdigital.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public static Card saveCard(DebitCardBuilder nextDigitalBank) {
    }

    public Double getMoney(Long id, Double amount) {
        Card card = cardRepository.getReferenceById(id);
        return card.getMoney(amount);
    }
}
