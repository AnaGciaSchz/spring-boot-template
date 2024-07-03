package es.nextdigital.model.card;

import es.nextdigital.exception.CardNotActivatedException;
import es.nextdigital.exception.InsufficientCreditScoreException;
import es.nextdigital.model.Account;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CreditCard extends Card {

    private Double creditLimit;

    @Builder
    public CreditCard(Long id, Boolean isActivated, String pin, String bank, Double creditLimit, Account account) {
        super(id, isActivated, pin, bank, account);
        this.creditLimit = creditLimit;
    }

    public Double getMoney(Double amount) {
        if (this.isActivated) {
            if (amount <= creditLimit) {
                return creditLimit - amount;
            } else {
                throw new InsufficientCreditScoreException(this.id);
            }
        } else {
            throw new CardNotActivatedException(this.id);
        }
    }

}
