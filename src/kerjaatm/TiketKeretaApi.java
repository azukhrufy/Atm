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
        screen.displayMessage("\nSilahkan Masukan Kode Booking : ");
        int kode_booking = keypad.getInput();
        
        if(pembayaranKA.idtransexist(kode_booking) != false){
            amount = pembayaranKA.getJumlahpembayaran(kode_booking);
            if(getBankDatabase().getAvailableBalance(getAccountNumber()) > amount){
                if(pembayaranKA.getStatus(kode_booking) != true){
                    getBankDatabase().debit(getAccountNumber(),amount);
                    pembayaranKA.BayarKA(getAccountNumber(), kode_booking, amount);
                    double available = super.getBankDatabase().getAvailableBalance(super.getAccountNumber());
                    if(pembayaranKA.getStatus(kode_booking) == true){
                        screen.displayMessageLine("Pembayaran Tiket Kereta Api");
                        screen.displayMessageLine("ID Pelanggan     : " +getAccountNumber());
                        screen.displayMessageLine("Kode Booking     : " +kode_booking);
                        screen.displayMessageLine("Nama Pembeli     : " +pembayaranKA.getNama(kode_booking));
                        screen.displayMessageLine("Keberangkatan    : " +pembayaranKA.getDepature(kode_booking));
                        screen.displayMessageLine("Kedatangan       : " +pembayaranKA.getArrived(kode_booking));
                        screen.displayMessageLine("Kelas            : " +pembayaranKA.getKelas(kode_booking));
                        screen.displayMessageLine("Nominal          : " +amount); 
                    }
                    else {
                        screen.displayMessageLine("Pembayaran Gagal..");
                    }
                }
                else {
                    screen.displayMessageLine("Pembayaran gagal, nomor transaksi tersebut sudah dibayarkan");
                }
            }
            else {
                screen.displayMessageLine("Saldo tidak mencukupi..");
            }
        }
        else {
            screen.displayMessageLine("ID Pelanggan tidak valid");
        }
    }
}