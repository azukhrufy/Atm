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
    private int amount; 
    private Keypad keypad; 
    
    private final static int CANCELED = 6;


    public PulsaListrik(int atmCompanyNumber, Screen atmScreen, 
        BankDatabase atmBankDatabase, Keypad atmKeypad) {
        
        super(atmCompanyNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
    }

    @Override
    public void execute() {
        boolean flag = false;
        
        do{
            screen.displayMessage("\nSilahkan Masukan Nomor Meter : ");
            try{
                int no_meter = keypad.getInput();
                flag = true;
                
                if (no_meter > 0){
                    amount = displayMenuOfAmounts();
            
                    if (amount == CANCELED){
                        screen.displayMessage("Membatalkan Transaksi...");
                    }else{
                        double available = super.getBankDatabase().getAvailableBalance
                        (super.getAccountNumber());
               
                        if(available < amount){
                            screen.displayMessageLine("Saldo tidak mencukupi");
                        } else{
                            super.getBankDatabase().debit(super.getAccountNumber(), amount);
                            screen.displayMessageLine("Pembelian Listrik Prabayar");
                            screen.displayMessageLine("ID Pelanggan     : " +getAccountNumber());
                            screen.displayMessageLine("Nomor Meter      : " +no_meter);
                            screen.displayMessageLine("Nominal          : " +amount);                    
                        }
                    }
                } else{
                    screen.displayMessageLine("Input Invalid.");
                    flag = false;
                }
            }
            catch(Exception e){
                screen.displayMessageLine("Input Invalid.");
                keypad.getString();
                flag = false;
            }
        } while (!flag);
    }
   
    private int displayMenuOfAmounts() {
        int userChoice = 0; 
        Screen screen = getScreen(); 
        int[] amounts = {0, 20, 50, 100, 200, 500};

        while (userChoice == 0) {
            screen.displayMessageLine("PLN Prabayar - Menu\n");
            screen.displayMessageLine("1 - $20");
            screen.displayMessageLine("2 - $50");
            screen.displayMessageLine("3 - $100");
            screen.displayMessageLine("4 - $200");
            screen.displayMessageLine("5 - $500");
            screen.displayMessageLine("6 - Batalkan Transaksi");
            screen.displayMessage("\nPilih Nominal : ");

            int input = keypad.getInput(); 
            switch (input) {
                case 1: {
                    userChoice = amounts[input];
                    break;
                }
                case 2: {
                    userChoice = amounts[input];
                    break;
                }
                case 3: {
                    userChoice = amounts[input];
                    break;
                }
                case 4: {
                    userChoice = amounts[input];
                    break;
                }
                case 5: {
                    userChoice = amounts[input]; 
                    break;
                }        
                case CANCELED: {
                    userChoice = CANCELED;
                    break;
                }
                default: {
                    screen.displayMessageLine("\nInvalid selection. Try again.");
                }
            } 
        } 
        return userChoice; 
    }
}
