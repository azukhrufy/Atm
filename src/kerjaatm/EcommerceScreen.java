/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Acer
 */
public class EcommerceScreen extends Transaction {
    private double transferamount; // amount to withdraw
   private Keypad keypad; // reference to keypad
   private CashDispenser cashDispenser; // reference to cash dispenser
   private final static int CANCELED = 0;
   private PembayaranEcommerce eCommerce;
   
   public EcommerceScreen(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, 
      CashDispenser atmCashDispenser,DepositSlot atmDepositSlot, PembayaranEcommerce atmeCommerce) {

      // initialize superclass variables
      super(userAccountNumber, atmScreen, atmBankDatabase);
      keypad = atmKeypad;
      eCommerce = atmeCommerce;
      
   }
   @Override
   public void execute() {
       BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      if (cl.printMsg() == 1){screen.displayMessage("Input your Ecommerce transaction number :");}
      if (cl.printMsg() == 2){screen.displayMessage("Masukkan nomor transaksi Ecommerce anda :");}
      int tujuan = keypad.getInput();
     if(eCommerce.idtransexist(tujuan) != false){
       transferamount = eCommerce.getJumlahpembayaran(tujuan);
       if(getBankDatabase().getAvailableBalance(getAccountNumber()) > transferamount){
           if(eCommerce.getStatus(tujuan) != true){
           getBankDatabase().debit(getAccountNumber(),transferamount);
           eCommerce.BayarEcommerce(getAccountNumber(), tujuan, transferamount);
           if(eCommerce.getStatus(tujuan) == true){
               if (cl.printMsg() == 1){screen.displayMessageLine("Transfer in amount of $"+transferamount+" to transaction id "+tujuan+" is successful");}
               if (cl.printMsg() == 2){screen.displayMessageLine("Transfer sebesar $"+transferamount+" pada ID transaksi "+tujuan+" Telah berhasil");}
           }
           else {
               if (cl.printMsg() == 1){screen.displayMessageLine("Transfer Failed..");}
               if (cl.printMsg() == 2){screen.displayMessageLine("Transfer Gagal..");}
           }
           }
           else {
               if (cl.printMsg() == 1){screen.displayMessageLine("Transsfer failed, that transaction number has been paid");}
               if (cl.printMsg() == 2){screen.displayMessageLine("Transfer gagal, Nomor transaksi tersebut sudah dibayarkan");}
           }
       }
       else {
           if (cl.printMsg() == 1){screen.displayMessageLine("Not enough account balance..");}
           if (cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
       }
     }
     else {
         if (cl.printMsg() == 1){screen.displayMessageLine("That transaction ID is not valid");}
         if (cl.printMsg() == 2){screen.displayMessageLine("ID transaksi tidak valid");}
     }
   } 
   
}

