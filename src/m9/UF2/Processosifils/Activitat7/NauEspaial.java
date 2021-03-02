/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF2.Processosifils.Activitat7;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author David
 */
public class NauEspaial extends javax.swing.JFrame {

    public NauEspaial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 300, Short.MAX_VALUE));
        pack();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(NauEspaial.class.getName()).log(
                    java.util.logging.Level.SEVERE, null, ex);
        }
        NauEspaial f = new NauEspaial();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Naus Espaials");
        f.setContentPane(new PanelNau());
        f.setSize(480, 560);
        f.setVisible(true);
    }
}

class PanelNau extends JPanel implements Runnable, KeyListener {

    static int numNaus = 10;
    Nau[] nauEnemy;
    Nau main;
    public static ArrayList<Laser> shoots = new ArrayList();

    public PanelNau() {
        nauEnemy = new Nau[numNaus];
        
        for (int i = 0; i < nauEnemy.length; i++) {
            Random rand = new Random();
            int velocitat = (rand.nextInt(3) + 5) * 10;
            int posX = rand.nextInt(100) + 30;
            int posY = rand.nextInt(100) + 30;
            int dX = rand.nextInt(3) + 1;
            int dY = rand.nextInt(3) + 1;
            nauEnemy[i] = new Nau(i, posX, posY, dX, dY, velocitat);
        }
        
        main = new Nau(numNaus, 200, 400, 10, 0, 100);
        
        Thread n = new Thread(this);
        n.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void run() {
        System.out.println("Inici fil repintar");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            } // espero 0,1 segons
            //System.out.println("Repintant");
            repaint();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < nauEnemy.length; ++i) {
            nauEnemy[i].pinta(g);
        }
        main.pinta(g);
        
        for (int i = 0; i < shoots.size(); i++) {
            Laser laser = shoots.get(i);
            laser.pinta(g);
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {


    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println(ke.getKeyCode());
        
        if (ke.getKeyCode() == 37) {
            main.setX(main.getX() - 10);
        } else if (ke.getKeyCode() == 39) {
            main.setX(main.getX() + 10);
        }else if (ke.getKeyCode() == 32) {
            
            if(shoots.size() < 10){
               shoots.add(new Laser(main.getX(), main.getY(), -10, 100)); 
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}

class Nau extends Thread {

    private int numero;
    private int x, y;
    private int dsx, dsy, v;
    private int tx = 10;
    private int ty = 10;
    private Image image;

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public Nau(int numero, int x, int y, int dsx, int dsy, int v) {
        this.numero = numero;
        this.x = x;
        this.y = y;
        this.dsx = dsx;
        this.dsy = dsy;
        this.v = v;
        image = new ImageIcon(Nau.class.getResource("nau.png")).getImage();
        Thread t = new Thread(this);
        t.start();
    }

    public int velocitat() {
        return v;
    }

    public void moure() {

        x = x + dsx;
        y = y + dsy;
        // si arriva als marges ...
        if (x >= 450 - tx || x <= tx) {
            dsx = -dsx;
        }
        if (y >= 500 - ty || y <= ty) {
            dsy = -dsy;
        }
    }

    public void pinta(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, x, y, null);
    }

    public void run() {
        while (true) {
            // System.out.println("Movent nau numero " + this.numero);
            try {
                Thread.sleep(this.v);
            } catch (Exception e) {
            }
            if (numero != PanelNau.numNaus) {
                moure();
            }
        }
    }
}

class Laser extends Thread {

    private int x, y;
    private int dsy, v;
    private int tx = 10;
    private int ty = 10;
    private Image image;

    public int getY() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Laser( int x, int y, int dsy, int v) {
        this.x = x + 50;
        this.y = y;
        this.dsy = dsy;
        this.v = v;
        image = new ImageIcon(Nau.class.getResource("laser.png")).getImage();
        Thread t = new Thread(this);
        t.start();
    }

    public int velocitat() {
        return v;
    }

    public void moure() {

        y = y + dsy;
        // si arriva als marges ...
        if (y >= 500 - ty || y <= ty) {
            for (int i = 0; i < PanelNau.shoots.size(); i++) {
                Laser laser = PanelNau.shoots.get(i);
                if (laser == this) {
                    PanelNau.shoots.remove(i);
                }
            }
        }
    }

    public void pinta(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, x, y, null);
    }

    public void run() {
        while (true) {
            // System.out.println("Movent nau numero " + this.numero);
            try {
                Thread.sleep(this.v);
            } catch (Exception e) {
            }
            moure();

        }
    }
}
