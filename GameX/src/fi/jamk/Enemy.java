/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Jani
 */
public class Enemy extends Entity {
    private static final String imagePath = "Kuvat/enemy.png";
    public Enemy( double x, double y, Entity player ){
        super( x, y, imagePath );
        this.player = player;
    }
    @Override
    public void render(Graphics2D g2) {
        //Rotate image
        AffineTransform old = g2.getTransform();    
        g2.rotate( this.getA(), this.x + this.r, this.y + this.r );
        g2.drawImage( this.getImage(), (int) this.x, (int) this.y, (int) (this.r * 2), (int) (this.r * 2), null );
        g2.setTransform( old );
    }
    @Override
    public void update(){
        //Rotate towards player
        this.a = Math.atan2( player.getY() - this.getY(), player.getX() - this.getX() );
        //Move toward player
        x += speed * Math.cos( this.getA() );
        y += speed * Math.sin( this.getA() );
    }
    @Override
    public void attack() {
        //Hit delay
        if (delay == 0) {
            player.hit();
            delay = 15;
        } else {
            delay -= 1;
        }
    }
    //Collision with
    @Override
    public boolean collides( Entity entity ) {
        if ( entity instanceof Enemy ){
            return collidesEnemyWithEnemy( (Enemy)entity );
        }
        return false;
    }
    //Collision with Enemy
    public boolean collidesEnemyWithEnemy( Enemy enemy ){
        return this.getBounds().intersects( this.getBounds() );
    }
}
