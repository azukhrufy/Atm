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
import java.util.Scanner;

public class Keypad {
    private Scanner input; // reads data from the command line

    public Keypad() {
        input = new Scanner(System.in);
    }

    public int getInput() {
        return input.nextInt(); // user enters an integer
    }

    public boolean IsInteger() {
        return input.hasNextInt();
    }
    
    public String getString(){
       return input.nextLine();
    }
    
    public boolean isString(){
       return input.hasNext();
    }
}