package es.nextdigital.model.card;

import es.nextdigital.exception.CardNotActivatedException;
import es.nextdigital.exception.InsufficientBalanceException;
import es.nextdigital.model.Account;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DebitCard extends Card {

    @Builder
    public DebitCard(Long id, Boolean isActivated, String pin, String bank, Account account) {
        super(id, isActivated, pin, bank, account);
    }

    public Double getMoney(Double amount) {
        Account account = this.getAccount();
        if (this.isActivated) {
            if (amount <= account.getBalance()) {
                return account.getBalance() - amount;
            } else {
                throw new InsufficientBalanceException(this.id);
            }
        } else {
            throw new CardNotActivatedException(this.id);
        }
    }

}
