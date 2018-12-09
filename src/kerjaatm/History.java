/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Azukhrufy
 */
public class History extends Transaction {

    public History(int currentAccountNumber, Screen screen, BankDatabase bankDatabase){
        super(currentAccountNumber,screen,bankDatabase);
    }

    @Override
    public void execute() {
        getBankDatabase().showHistory(getAccountNumber());
    }
}

