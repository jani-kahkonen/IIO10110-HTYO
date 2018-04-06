/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Arttu
 */
public class Menu extends JFrame implements ActionListener {
    private JButton             button1, button2; // NEW GAME, EXIT
    private JPanel              panel1, panel2; // highscorespaneeli, nappulatpaneeli
    private JComboBox           res; // resoluutio valikko
    private JTextField          name; // pelaajan nimi
    private JTextArea           label1; // highscores-alue
    private String              s; // tekstitiedoston rivi
    private StringBuilder       sb; // highscores tekstit
    private int                 WIDHT =640, HEIGHT = 400;
    private int i;
    
    public Menu() {       
        this.setLayout(new GridLayout(1,2));
        this.setTitle( "main menu" );
        this.setSize(640,400);
        this.setResizable(false);
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setLocationRelativeTo( null );
        //this.add(new JLabel(new ImageIcon(getClass().getResource("Kuvat/menu.png")))); // valikon taustakuva contentpane:n
        
        sb = new StringBuilder();
        FileReader fr;  // tiedoston avaaminen lukemista varten
        BufferedReader br;  // puskuroitu tiedoston lukeminen       
        try {
            fr = new FileReader("Highscores.txt");    // avataan
            br = new BufferedReader(fr);    // puskuroitu lukeminen
            
            while ((s = br.readLine()) != null) { // pelaajien tietojen haku rivittäin
                System.out.println(s);
                sb.append(s);
                sb.append(System.getProperty("line.separator")); // rivin vaihto
            }
            br.close();            
        } catch (Exception e) {

        }
        
        label1 = new JTextArea(sb.toString()); // textarea pelaajatietoineen
        label1.setEditable(false); // tekstin muokkaaminen estetty
        label1.setFont(new Font("Serif", Font.PLAIN, 20)); // tekstin fontti
        panel2 = new JPanel(); // uusi paneeli
        panel2.add(label1); // textarean lisäys paneeliin
        panel1 = new JPanel(); // uusi paneeli
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS)); // paneelin boxlayout, tasaus vasemmalle
        panel1.setBorder(BorderFactory.createEmptyBorder(50, 150, 500, 50)); // paneeliin tyhjät reunat
        
        this.add(panel2); // paneelin lisäys contentpane:n
        this.add(panel1); // paneelin lisäys contentpane:n      
        
        button1 = new JButton("NEW GAME"); // nappula
        button1.setAlignmentX(Component.LEFT_ALIGNMENT); // tasaus vasempaan
        button1.setMaximumSize(new Dimension(200,50)); // koon määritys
        button1.addActionListener(this); // tapahtumanseuranta
        button2 = new JButton("EXIT"); // nappula
        button2.setAlignmentX(Component.LEFT_ALIGNMENT); // tasaus vasempaan
        button2.setMaximumSize(new Dimension(200,50)); // koon määritys
        button2.addActionListener(this); // tapahtumanseuranta
        name = new JTextField("PLAYER NAME"); // tekstikenttä
        name.setAlignmentX(Component.LEFT_ALIGNMENT); // tasaus vasempaan
        name.addActionListener(this); // tapahtumanseuranta


        
        
        panel1.add(button1); // nappula paneeliin
        panel1.add(Box.createRigidArea(new Dimension(0,10))); // tyhjän välin lisäys
        panel1.add(name); // tekstikenttä paneeliin
        panel1.add(Box.createRigidArea(new Dimension(0,10))); // tyhjän välin lisäys
        panel1.add(Box.createRigidArea(new Dimension(0,10))); // tyhjän välin lisäys
        panel1.add(button2); // nappula paneeliin
        
        this.setVisible( true ); // asetetaan näkyväksi
    }

    @Override
    public void actionPerformed(ActionEvent e) { // nappulan tapahtumanseuranta
        JButton pressed = (JButton) e.getSource();
        if (pressed == button1) {
            new Game(WIDHT,HEIGHT).start(); // aloittaa pelin
            this.dispose(); // sulkee menun pelin alkaessa
        } else if (pressed == button2) {
            Runtime.getRuntime().exit(0); // lopettaa ohjelman
            }
        
    }

}