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
public class KeretaApi {
    private int nomortransaksi;
    private String nama;
    private String depature;
    private String arrived;
    private String kelas;
    private double jumlahpembayaran;
    private boolean status;
    
    public KeretaApi(int notrans, String pembeli, String Depature, String Arrived, String Kelas, double jumyar){
        nomortransaksi = notrans;
        nama = pembeli;
        depature = Depature;
        arrived = Arrived;
        kelas = Kelas;
        jumlahpembayaran = jumyar;
        status = false;
    }
    
    
    public boolean validatetransaksi(int notrans){
        if (nomortransaksi != notrans) {
            return false;
        }
        else {
            return true;
        }
    }
  
    public int getNomortransaksi(){
        return nomortransaksi;
    }
  
    public double getJumlahpembayaran(){
        return jumlahpembayaran;
    }
  
    public boolean getStatus(){
        return status;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getDepature(){
        return depature;
    }
    
    public String getArrived(){
        return arrived;
    }
    
    public String getKelas(){
        return kelas;
    }
  
    public void changeStat(double jumkir){
        if(jumkir == jumlahpembayaran){
            status = true;
        }
    }
}
