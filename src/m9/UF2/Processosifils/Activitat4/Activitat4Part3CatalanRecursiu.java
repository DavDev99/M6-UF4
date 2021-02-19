/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat4;

/**
 *
 * @author PC-Casa
 */
public class Activitat4Part3CatalanRecursiu {
        // A recursive function to find nth catalan number 
  
    static int catalan(int n) { 
        int res = 0; 
          
        // Base case 
        if (n <= 1) { 
            return 1; 
        } 
        for (int i = 0; i < n; i++) { 
            res += catalan(i) * catalan(n - i - 1); 
        } 
        return res; 
    } 
  
    public static void main(String[] args) { 
        for (int i = 0; i < 10; i++) { 
            System.out.print(catalan(i) + " "); 
        } 
    } 
}
