/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kerjaatm;

/**
 *
 * @author Rinaldi
 */
public class ChangeLang {

    private Screen screen;
    private Keypad keypad;
    private int bhs;
    private static int choose;

    public ChangeLang() {
        screen = new Screen();
    }

    public int printMsg() {
        if (choose == 1) {
            return 1;
        } else {
            if (choose == 2) {
                return 2;
            } else {
                return 0;
            }
        }
    }

    public void Sel(int cp) {
        choose = cp;
    }

//    public void Pil() {
//        choose = 2;
//    }
//    public void displayMessageEn(int lang) {
//        if (lang == 1) {
//            screen.displayMessage("Testttt"); //contoh comment yang bisa dipanggil
//        }
//    }
}
