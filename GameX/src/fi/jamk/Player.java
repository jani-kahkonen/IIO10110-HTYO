/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 * @author Jani
 */
public class Player extends Entity{
    private static final String imagePath = "Kuvat/player.png";
    public Player( double x, double y ){
        super( x, y, imagePath );
        this.bullets = new ArrayList();
    }
    @Override
    public void render(Graphics2D g2){
        //Rotate image
        AffineTransform old = g2.getTransform();
        g2.rotate( Math.toRadians( this.getA() ), this.x + this.r, this.y + this.r );
        g2.drawImage( this.getImage(), (int) this.x, (int) this.y, (int) (this.r * 2), (int) (this.r * 2), null );
        g2.setTransform( old );
    }
    @Override
    public void update(){
        //Movement logic
        if( Listener.getU() == true) this.moveForward();
        if( Listener.getD() == true) this.moveBackward();
        if( Listener.getL() == true) this.roteteLeft();
        if( Listener.getR() == true) this.rotateRight();
        if( Listener.getF() == true) this.attack();
    }
    @Override
    public void attack(){
        //Bullet starting position 
        double bulletPosX = this.getX() + this.r + ( Math.cos( (270 + a) * Math.PI / 180 ) * r);
        double bulletPosY = this.getY() + this.r + ( Math.sin( (270 + a) * Math.PI / 180 ) * r);
        
        //Firing delay
        if (delay == 0) {
            //Create new bullet
            bullets.add( new Bullet( bulletPosX, bulletPosY, this.a ) );
            delay = 10;
        } else {
            delay -= 1;
        }
    }
    //Collision with
    @Override
    public boolean collides( Entity entity ) {
        if( entity instanceof Enemy ){
            return collidesEnemyWithPlayer( (Enemy)entity );
        }
        return false;
    }
    //Collision with Enemy
    public boolean collidesEnemyWithPlayer( Enemy enemy){
        return enemy.getBounds().intersects( this.getBounds() );
    }
}
