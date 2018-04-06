/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.event.*;

/**
 *
 * @author Jani
 */
public class Listener{
    private Level           level;
    protected InputListener inputListener;
    protected PanelListener panelListener;
    protected static boolean u, d, l, r, f;
    public void init(){
        inputListener = new InputListener();
        panelListener = new PanelListener();
    }
    //Key listener
    public class InputListener extends KeyAdapter{
        @Override
        public void keyPressed( KeyEvent e ){
            switch ( e.getKeyCode() ){
                case KeyEvent.VK_UP:
                    d = false;
                    u = true;
                    break;
                case KeyEvent.VK_DOWN:
                    u = false;
                    d = true;
                    break;
                case KeyEvent.VK_LEFT:
                    l = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    r = true;
                    break;
                case KeyEvent.VK_SPACE:
                    f = true;
                    break;
            }
        }
        @Override
        public void keyReleased( KeyEvent e){
            switch ( e.getKeyCode() ){
                case KeyEvent.VK_UP:
                    u = false;
                    break;
                case KeyEvent.VK_DOWN:
                    d = false;
                    break;
                case KeyEvent.VK_LEFT:
                    l = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    r = false;
                    break;
                case KeyEvent.VK_SPACE:
                    f = false;
                    break;
            }
        }
    }
    //Window listener
    public class PanelListener extends ComponentAdapter{
        @Override
        public void componentResized( ComponentEvent e ){
            level.updatePanelSize();
        }
    }
    public void setLevel( Level level ){
        this.level = level;
    }
    //Getters for player
    public static boolean getU(){
        return u;
    }
    public static boolean getD(){
        return d;
    }
    public static boolean getL(){
        return l;
    }
    public static boolean getR(){
        return r;
    }
    public static boolean getF(){
        return f;
    }
}
