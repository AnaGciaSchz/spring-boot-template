package es.nextdigital.model.card;

import es.nextdigital.model.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public abstract class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Getter
    Boolean isActivated;
    @Getter
    String pin;
    @Getter
    String bank;
    @OneToOne
    @Getter
    Account account;


    public abstract Double getMoney(Double amount);
}
