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
public class PembayaranKeretaApi {
    private KeretaApi[] KA;
    
    public PembayaranKeretaApi() {
        KA = new KeretaApi[3];
        KA[0] = new KeretaApi(81101, "Zukhruf", "Bandung", "Surabaya", "Ekonomi", 95);
        KA[1] = new KeretaApi(81102, "Fadul", "Bandung", "Cimahi", "Eksekutif", 12);
        KA[2] = new KeretaApi(81102, "Faza", "Garut", "Cimindi", "Bisnis", 80);
    }
    
    private KeretaApi getKA(int idtrans) {
        for(int i=0;i<KA.length;i++){
            if(KA[i].getNomortransaksi() == idtrans){
               return KA[i];
            }
        }
        return null; 
    } 
   
    public int getNomortransaksi(int notrans){
        return getKA(notrans).getNomortransaksi();
    }
    
    public double getJumlahpembayaran(int notrans){
        return getKA(notrans).getJumlahpembayaran();
    }
    
    public void BayarKA(int userAccountNumber, int notrans,double amount){
        getKA(notrans).changeStat(amount);
    }
    
    public boolean getStatus(int notrans){
        return getKA(notrans).getStatus();
    }
    
    public String getNama(int notrans){
        return getKA(notrans).getNama();
    }
    
    public String getDepature(int notrans){
        return getKA(notrans).getDepature();
    }
    
    public String getArrived(int notrans){
        return getKA(notrans).getArrived();
    }
    
    public String getKelas(int notrans){
        return getKA(notrans).getKelas();
    }
    
    public boolean idtransexist(int notrans){
        if(getKA(notrans) != null){
           return true;
        }
        else {
           return false;
        }
    }   
}
