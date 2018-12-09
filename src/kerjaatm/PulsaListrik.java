/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Firiontina Argandini
 */
public class PulsaListrik extends Transaction{
   private int amount; // amount to withdraw
   private Keypad keypad; // reference to keypad

   // constant corresponding to menu option to cancel
   private final static int CANCELED = 6;

   public PulsaListrik(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad) {
      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
   }

   // perform transaction
   @Override
   public void execute() {
        screen.displayMessage("\nSilahkan Masukan Nomor Meter : ");
        int no_meter = keypad.getInput();
        amount = displayMenuOfAmounts();
            if (amount == CANCELED){
               screen.displayMessage("Canceling transaction...");
            }else{
                double available = super.getBankDatabase().getAvailableBalance
                (super.getAccountNumber());
               
                if(available < amount){
                   screen.displayMessageLine("Insuficient balance");
                } else{
                    super.getBankDatabase().debit(super.getAccountNumber(), amount);
//                    super.getBankDatabase().credit(super.getBankDatabase().getCompany(), amount);
                    screen.displayMessageLine("Pembelian Listrik Prabayar");
                    screen.displayMessageLine("ID Pelanggan     : " +getAccountNumber());
                    screen.displayMessageLine("Nomor Meter      : " +no_meter);
                    screen.displayMessageLine("Nominal          : " +amount);                    
               }
           }
   }

   // display a menu of withdrawal amounts and the option to cancel;
   // return the chosen amount or 0 if the user chooses to cancel
   private int displayMenuOfAmounts() {
      int userChoice = 0; // local variable to store return value

      Screen screen = getScreen(); // get screen reference
      
      // array of amounts to correspond to menu numbers
      int[] amounts = {0, 20, 50, 100, 200, 500};

      // loop while no valid choice has been made
      while (userChoice == 0) {
         // display the withdrawal menu
       
         screen.displayMessageLine("PLN Prabayar - Menu\n");
         screen.displayMessageLine("1 - $20");
         screen.displayMessageLine("2 - $50");
         screen.displayMessageLine("3 - $100");
         screen.displayMessageLine("4 - $200");
         screen.displayMessageLine("5 - $500");
         screen.displayMessageLine("6 - Cancel transaction");
         screen.displayMessage("\nPilih Nominal : ");

         int input = keypad.getInput(); // get user input through keypad
         
         // determine how to proceed based on the input value
         switch (input) {
            case 1: {
                userChoice = amounts[input];
                break;
            }// if the user chose a withdrawal amount 
            case 2: {
                userChoice = amounts[input];
                break;
            }// (i.e., chose option 1, 2, 3, 4 or 5), return the
            case 3: {
                userChoice = amounts[input];
                break;
            }// corresponding amount from amounts array
            case 4: {
                userChoice = amounts[input];
                break;
            }
            case 5: 
               userChoice = amounts[input]; // save user's choice
               break;       
            case CANCELED: // the user chose to cancel
               userChoice = CANCELED; // save user's choice
               break;
            default: // the user did not enter a value from 1-6
               screen.displayMessageLine(
                  "\nInvalid selection. Try again.");
         } 
      } 
      return userChoice; // return withdrawal amount or CANCELED
   }
}
