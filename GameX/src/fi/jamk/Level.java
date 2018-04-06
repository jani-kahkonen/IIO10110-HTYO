/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.*;

/**
 *
 * @author Jani
 */
public class Level{
    private Game            game;
    private Listener        listener;
    protected PanelLevel    panelLevel;
    protected FrameLevel    frameLevel;
    protected int           gameMode = 2;
    private int             kills = 0, bulletsFired = 0;
    private final String    TITLE;
    private int             width = 0, height = 0;
    public Level( int width, int height, String TITLE, Game game ){
        this.width = width;
        this.height = height;
        this.TITLE = TITLE;
        this.game = game;
    }
    public void init(){
        this.panelLevel = new PanelLevel();
        this.frameLevel = new FrameLevel();
    }
    //Panel
    public class PanelLevel extends JPanel{
        public PanelLevel(){
            this.setPreferredSize( new Dimension( width, height) );
        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            switch (gameMode) {
                 case 1: drawMenu(g); break; 
                 case 2: game.render( g2 ); break;
            }
        }
        public void drawMenu(Graphics g) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Consolas", Font.PLAIN, 40)); 
            g.drawString("Kills: " +kills ,100, 100);
        }
    }
    //Window
    public class FrameLevel extends JFrame{
        public FrameLevel(){
            this.setTitle( TITLE );
            this.setResizable( true );
            this.setLayout( new GridLayout() );
            this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            this.addComponentListener( listener.panelListener );
            this.addKeyListener( listener.inputListener );
            this.add( panelLevel );
            this.pack();
            this.setLocationRelativeTo( null );
            this.setVisible( true );
        }
    }
    public void setListener( Listener listener){
        this.listener = listener;
    }
    public void updatePanelSize(){
        this.width = panelLevel.getWidth();
        this.height = panelLevel.getHeight();
    }
    public int getW(){
        return width;
    }
    public int getH(){
        return height;
    }
    public void setStatistics( int kills ){
        this.kills = kills;
    }
}
