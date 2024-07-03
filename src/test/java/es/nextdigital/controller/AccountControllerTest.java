package es.nextdigital.controller;

import static org.junit.jupiter.api.Assertions.*;

import es.nextdigital.model.Movement;
import org.junit.jupiter.api.Test;

class AccountControllerTest {

    private AccountController accountController;


    @Test
    givenAClient_whenItMakesAMovementInAnAccount_thenItIsAddedTotheListOfMovements(){
        Movement movement = new Movement();
        accountController.makeMovement(movement);
        assertTrue(accountController.getAllMovements().contains(movement));

    }

}