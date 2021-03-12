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
        NauEspaial window = new NauEspaial();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Naus Espaials");
        window.setContentPane(new PanelNau());
        window.setSize(480, 560);
        window.setVisible(true);
    }
}

class PanelNau extends JPanel implements Runnable, KeyListener {

    static int numNaus = 10;
    public static ArrayList<Nau> nausEnemy;
    public static Nau nauMain;
    public static ArrayList<Laser> shoots = new ArrayList();
    public static boolean finish = false;

    public PanelNau() {
        nausEnemy = new ArrayList();

        for (int i = 0; i < numNaus; i++) {
            Random rand = new Random();
            int velocitat = (rand.nextInt(3) + 5) * 10;
            int posX = rand.nextInt(100) + 30;
            int posY = rand.nextInt(100) + 30;
            int dX = rand.nextInt(3) + 1;
            int dY = rand.nextInt(3) + 1;
            nausEnemy.add(new Nau(i, posX, posY, dX, dY, velocitat));
        }

        nauMain = new Nau(numNaus, 200, 400, 10, 0, 100);

        Thread n = new Thread(this);
        n.start();

        addKeyListener(this);
        setFocusable(true);
    }

    public void run() {
        System.out.println("Inici fil repintar");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (!finish) {
                    try {
                        Thread.sleep(1000);
                        Random rand = new Random();
                        Nau nau = nausEnemy.get(rand.nextInt(nausEnemy.size()));
                        shoots.add(new Laser(nau.getX(), nau.getY() + 100, 10, 100, true));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // Creamos un hilo y le pasamos el runnable
        Thread hilo = new Thread(runnable);
        hilo.start();
        while (!finish) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            } // espero 0,1 segons
            //System.out.println("Repintant");
            repaint();

            if (nausEnemy.size() <= 0) {
               
                endGame("You win the Game!", "InfoBox: You WIN!");
            }

            for (int i = 0; i < nausEnemy.size(); i++) {
                Nau nau = nausEnemy.get(i);

                if ((nauMain.getX() >= nau.getX() - 50 && nauMain.getX() <= nau.getX() + 50) && (nauMain.getY() >= nau.getY() && nauMain.getY() <= nau.getY() + 80)) {
                    
                    endGame("You lose the Game!", "InfoBox: You LOSE!");
                }

            }

        }
    }

    public static void endGame(String message, String title) {
        PanelNau.finish = true;
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < nausEnemy.size(); ++i) {
            Nau nau = nausEnemy.get(i);
            nau.pinta(g);
        }
        if (nauMain != null) 
            nauMain.pinta(g);

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

        if (ke.getKeyCode() == 37) {
            nauMain.setX(nauMain.getX() - 10);
        } else if (ke.getKeyCode() == 39) {
            nauMain.setX(nauMain.getX() + 10);
        } else if (ke.getKeyCode() == 32) {

            if (shoots.size() < 10) {
                shoots.add(new Laser(nauMain.getX(), nauMain.getY(), -10, 100, false));
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
    private int tz = 10;
    private Image image;
    private boolean viva = true;

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
        while (viva) {
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

    void destrueix() {
        this.viva = false;
    }
}

class Laser extends Thread {

    private int x, y;
    private int dsy, v;
    private int tx = 10;
    private int ty = 10;
    private Image image;
    private boolean enemyShoot;
    private boolean finalitza = false;

    public int getY() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Laser(int x, int y, int dsy, int v, boolean enemyShoot) {
        this.x = x + 50;
        this.y = y;
        this.dsy = dsy;
        this.v = v;
        this.enemyShoot = enemyShoot;
        image = new ImageIcon(Nau.class.getResource("laser.png")).getImage();
        Thread t = new Thread(this);
        t.start();
    }

    public int velocitat() {
        return v;
    }

    public void removeLaser() {
        for (int i = 0; i < PanelNau.shoots.size(); i++) {
            Laser laser = PanelNau.shoots.get(i);

            if (laser == this) {
                PanelNau.shoots.remove(i);
            }
        }
    }

    public void removeAllLasers() {
        for (int i = 0; i < PanelNau.shoots.size(); i++) {
                PanelNau.shoots.remove(i);
                PanelNau.shoots.get(i).finalitza = true;
        }
    }
            
    public synchronized void moure() {

        y = y + dsy;
        // si arriva als marges ...
        if (y >= 500 - ty || y <= ty) {
            this.removeLaser();
        } else {
            int i = 0;
            while (!finalitza && i < PanelNau.nausEnemy.size()) {

                Nau nau = PanelNau.nausEnemy.get(i);

                if (!enemyShoot && (this.x >= nau.getX() && this.x <= nau.getX() + 100) && (this.y >= nau.getY() && this.y <= nau.getY() + 100)) {
                    this.removeLaser();
                    this.finalitza = true;
                    nau.destrueix();
                    PanelNau.nausEnemy.remove(i);

                } else {
                    i++;
                }

            }

            if (enemyShoot && (this.x >= PanelNau.nauMain.getX() && this.x <= PanelNau.nauMain.getX() + 100) && (this.y >= PanelNau.nauMain.getY() && this.y <= PanelNau.nauMain.getY() + 100)) {
                this.removeAllLasers();
                this.finalitza = true;
                
                PanelNau.nauMain.destrueix();
                PanelNau.endGame("You lose the Game!", "InfoBox: You LOSE!");

            }
        }
    }

    public void pinta(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, x, y, null);
    }

    public void run() {
        while (!finalitza) {
            // System.out.println("Movent nau numero " + this.numero);
            try {
                Thread.sleep(this.v);
            } catch (Exception e) {
            }
            moure();

        }
    }
}
