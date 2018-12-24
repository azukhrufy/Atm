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
public class TiketKeretaApi extends Transaction{
    private double amount;
    private Keypad keypad; 
    private PembayaranKeretaApi pembayaranKA;

    public TiketKeretaApi(int userAccountNumber, Screen atmScreen, 
        BankDatabase atmBankDatabase, Keypad atmKeypad, PembayaranKeretaApi atmPembayaranKA){
        super(userAccountNumber, atmScreen, atmBankDatabase);
        keypad = atmKeypad;
        pembayaranKA = atmPembayaranKA;
    }

    @Override
    public void execute() {
        if(cl.printMsg() == 1){screen.displayMessage("\nInput Booking Code : ");}
        if(cl.printMsg() == 2){screen.displayMessage("\nSilahkan Masukan Kode Booking : ");}
        int kode_booking = keypad.getInput();
        
        if(pembayaranKA.idtransexist(kode_booking) != false){
            amount = pembayaranKA.getJumlahpembayaran(kode_booking);
            if(getBankDatabase().getAvailableBalance(getAccountNumber()) > amount){
                if(pembayaranKA.getStatus(kode_booking) != true){
                    getBankDatabase().debit(getAccountNumber(),amount);
                    pembayaranKA.BayarKA(getAccountNumber(), kode_booking, amount);
                    double available = super.getBankDatabase().getAvailableBalance(super.getAccountNumber());
                    if(pembayaranKA.getStatus(kode_booking) == true){
                        if(cl.printMsg() == 1){
                        screen.displayMessageLine("Train Ticket Payment");
                        screen.displayMessageLine("Customer ID      : " +getAccountNumber());
                        screen.displayMessageLine("Booking Code     : " +kode_booking);
                        screen.displayMessageLine("Name             : " +pembayaranKA.getNama(kode_booking));
                        screen.displayMessageLine("Departure From   : " +pembayaranKA.getDepature(kode_booking));
                        screen.displayMessageLine("Destination      : " +pembayaranKA.getArrived(kode_booking));
                        screen.displayMessageLine("Class            : " +pembayaranKA.getTrainClass(kode_booking));
                        screen.displayMessageLine("Amount           : " +amount); 
                        }
                        if(cl.printMsg() == 2){
                        screen.displayMessageLine("Pembayaran Tiket Kereta Api");
                        screen.displayMessageLine("ID Pelanggan     : " +getAccountNumber());
                        screen.displayMessageLine("Kode Booking     : " +kode_booking);
                        screen.displayMessageLine("Nama Pembeli     : " +pembayaranKA.getNama(kode_booking));
                        screen.displayMessageLine("Keberangkatan    : " +pembayaranKA.getDepature(kode_booking));
                        screen.displayMessageLine("Kedatangan       : " +pembayaranKA.getArrived(kode_booking));
                        screen.displayMessageLine("Kelas            : " +pembayaranKA.getKelas(kode_booking));
                        screen.displayMessageLine("Nominal          : " +amount); 
                        }
                    }
                    else {
                        if(cl.printMsg() == 1){screen.displayMessageLine("Payment failed..");}
                        if(cl.printMsg() == 2){screen.displayMessageLine("Pembayaran Gagal..");}
                    }
                }
                else {
                    if(cl.printMsg() == 1){screen.displayMessageLine("Payment failed, that transaction number has been paid");}
                    if(cl.printMsg() == 2){screen.displayMessageLine("Pembayaran gagal, nomor transaksi tersebut sudah dibayarkan");}
                }
            }
            else {
                if(cl.printMsg() == 1){screen.displayMessageLine("Insufficient balance..");}
                if(cl.printMsg() == 2){screen.displayMessageLine("Saldo tidak mencukupi..");}
            }
        }
        else {
            if(cl.printMsg() == 1){screen.displayMessageLine("Customer ID is invalid");}
            if(cl.printMsg() == 2){screen.displayMessageLine("ID Pelanggan tidak valid");}
        }
    }
}