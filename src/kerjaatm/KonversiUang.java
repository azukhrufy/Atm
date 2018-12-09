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
public class KonversiUang extends Transaction{

    public KonversiUang(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase) {
        super(userAccountNumber, atmScreen, atmBankDatabase);
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();

      // get the available balance for the account involved
      double availableBalance = 
         bankDatabase.getAvailableBalance(getAccountNumber());

      // get the total balance for the account involved
      double totalBalance = 
         bankDatabase.getTotalBalance(getAccountNumber());
      
      // display the balance information on the screen
      screen.displayMessageLine("\nBalance Information:");
      screen.displayMessage(" - Available balance: "); 
      screen.displayAmount(availableBalance);
      screen.displayMessage("\n - Total balance:     ");
      screen.displayAmount(totalBalance);
      screen.displayMessageLine(""); //To change body of generated methods, choose Tools | Templates.
    }
    
}
