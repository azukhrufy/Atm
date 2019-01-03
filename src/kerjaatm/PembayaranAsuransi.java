/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class PembayaranAsuransi extends Transaction {
   private double amount;
   private Keypad keypad;
   private final static int CANCELED = 6;
   private DataAsuransi asuransi;

   
   public PembayaranAsuransi(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, DataAsuransi atmAsuransi) 
      {
            super(userAccountNumber, atmScreen, atmBankDatabase);
            keypad = atmKeypad;
            asuransi = atmAsuransi;
      }

    @Override
    public void execute() {
      //BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      Scanner in = new Scanner(System.in);   
      int noPolis = 0;
      
      do{
          if(cl.printMsg() == 1){screen.displayMessage("Input your Insurance Policy number :");}
          if(cl.printMsg() == 2){screen.displayMessage("Masukkan nomor Polis Asuransi anda :");}  
        try{
            noPolis = in.nextInt();
            break;
        }
        catch(Exception e)
        {
            if(cl.printMsg() == 1){System.out.println("!! INPUT CAN ONLY BE NUMBERS !!\n");}
            if(cl.printMsg() == 2){System.out.println("!! INPUT HANYA BOLEH ANGKA !!\n");}
            in.nextLine();
        }
      }while(noPolis<100);
      
      if(asuransi.cekNoPolis(noPolis)){
            amount = asuransi.getJumlahPremi(noPolis);
            if(getBankDatabase().getAvailableBalance(getAccountNumber()) > amount)
            {
                getBankDatabase().debit(getAccountNumber(), amount);
                if(cl.printMsg() == 1){
                screen.displayMessageLine("\n------------------------------------------"
                   + "\nTRANSACTION SUCCESS"
                   + "\n\nInsurance Payment"
                   + "\nPolicy Number     : "+noPolis+""
                   + "\nName            : "+asuransi.getNamaPelAsuransi(noPolis)+""
                   + "\nPayment Amount    : $"+amount+" "
                   + "\n\nThank you for using our service"
                   + "\n------------------------------------------\n\n");
                }
                if(cl.printMsg() == 2){
                screen.displayMessageLine("\n------------------------------------------"
                   + "\nTRANSAKSI BERHASIL"
                   + "\n\nPembayaran Asuransi"
                   + "\nNomor Polis     : "+noPolis+""
                   + "\nNama            : "+asuransi.getNamaPelAsuransi(noPolis)+""
                   + "\nJumlah Premi    : $"+amount+" "
                   + "\n\nTerimakasih telah menggunakan layanan kami"
                   + "\n------------------------------------------\n\n");
                }
            } else {
                if(cl.printMsg() == 1){screen.displayMessageLine("Insufficient balance..");}
                if(cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
            }
      } else {
          if(cl.printMsg() == 1){screen.displayMessage("\n!!! Invalid Policy Number !!!\n");}
          if(cl.printMsg() == 2){screen.displayMessage("\n!!! Nomor Polis tidak valid !!!\n");}
        execute();
      }

    } 
}

