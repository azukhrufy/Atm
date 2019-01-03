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
public class PembayaranTiketPesawat extends Transaction {
   private double amount;
   private Keypad keypad;
   private final static int CANCELED = 6;
   private DataTiketPesawat tiketpesawat;

   
   public PembayaranTiketPesawat(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase, Keypad atmKeypad, DataTiketPesawat atmTiketPesawat) 
      {
            super(userAccountNumber, atmScreen, atmBankDatabase);
            keypad = atmKeypad;
            tiketpesawat = atmTiketPesawat;
      }

    @Override
    public void execute() {
      //BankDatabase bankDatabase = getBankDatabase();
      Screen screen = getScreen();
      Scanner in = new Scanner(System.in);   
      int kodePesan = 0;
      
      do{
          if(cl.printMsg() == 1){screen.displayMessage("Input your flight ticket reservation code :");}
          if(cl.printMsg() == 2){screen.displayMessage("Masukkan kode pemesanan tiket pesawat anda :");}
        try{
            kodePesan = in.nextInt();
            break;
        }
        catch(Exception e)
        {
            if(cl.printMsg() == 1){System.out.println("!! INPUT CAN ONLY BE NUMBERS !!\n");}
            if(cl.printMsg() == 2){System.out.println("!! INPUT HANYA BOLEH ANGKA !!\n");}
            in.nextLine();
        }
      }while(kodePesan<100);
      
      if(tiketpesawat.cekKodePesan(kodePesan)){
            amount = tiketpesawat.getJumlahBayar(kodePesan);
            if(getBankDatabase().getAvailableBalance(getAccountNumber()) > amount)
            {
                getBankDatabase().debit(getAccountNumber(), amount);
                if(cl.printMsg() == 1){
                screen.displayMessageLine("\n------------------------------------------"
                   + "\nTRANSACTION SUCCESS"
                   + "\n\nFlight Ticket Payment"
                   + "\nBooking Code        : "+kodePesan+""
                   + "\nName                : "+tiketpesawat.getNamaPemesan(kodePesan)+""
                   + "\nNumber of Seats     : "+tiketpesawat.getJumlahSeat(kodePesan)+""
                   + "\nPayment Amount      : $"+tiketpesawat.getJumlahBayar(kodePesan)+""
                   + "\nFlight Number       : "+tiketpesawat.getNoPenerbangan(kodePesan)+""
                   + "\n\nThank you for using our service"
                   + "\n------------------------------------------\n\n");
                }
                if(cl.printMsg() == 2){
                screen.displayMessageLine("\n------------------------------------------"
                   + "\nTRANSAKSI BERHASIL"
                   + "\n\nPembayaran Tiket Pesawat"
                   + "\nKode Booking        : "+kodePesan+""
                   + "\nNama                : "+tiketpesawat.getNamaPemesan(kodePesan)+""
                   + "\nJumlah Seat         : "+tiketpesawat.getJumlahSeat(kodePesan)+""
                   + "\nJumlah Pembayaran   : $"+tiketpesawat.getJumlahBayar(kodePesan)+""
                   + "\nNo Penerbangan      : "+tiketpesawat.getNoPenerbangan(kodePesan)+""
                   + "\n\nTerimakasih telah menggunakan layanan kami"
                   + "\n------------------------------------------\n\n");
                }
            } else {
              if(cl.printMsg() == 1){screen.displayMessageLine("Insufficient balance..");}
              if(cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
            }
      } else {
        if(cl.printMsg() == 2){screen.displayMessage("\n!!! That Booking Code is Invalid !!!\n");} 
        if(cl.printMsg() == 2){screen.displayMessage("\n!!! Kode Booking Tidak Valid !!!\n");} 
        execute();
      }

    }
}
