package es.nextdigital.controller;

import es.nextdigital.model.card.Card;
import es.nextdigital.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/cards")
    public ResponseEntity<Card> saveAnswers(@RequestBody Card card) {
        return ResponseEntity.ok(cardService.saveCard(card));
    }

    @PostMapping("/cards/{id}/withdraw")
    public ResponseEntity<String> getMoney(@PathVariable Long id, @RequestBody Double amount) {
        try{
            return ResponseEntity.ok().build();
        }
    }

}
