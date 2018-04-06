/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Graphics2D;

/**
 *
 * @author Jani
 */
public class Camera {
    private Level level;
    private Entity player;
    private int xOffset = 0, yOffset = 0;
    public void setLevel( Level level ){
        this.level = level;
    }
    public void setPlayer( Entity player ){
        this.player = player;
    }
    public void update(){
        //Panel center point
        xOffset = (int)(player.getX() + ( 100 / 2 ) - ( level.getW() / 2 ));
        yOffset = (int)(player.getY() + ( 100 / 2 ) - ( level.getH() / 2));
    }
    public void render(Graphics2D g2){
        //Coordinate transform
        g2.translate( -xOffset, -yOffset);
    }
}
