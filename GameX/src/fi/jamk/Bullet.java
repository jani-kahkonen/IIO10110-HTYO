/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Jani
 */
public class Bullet extends Entity{
    //Bullet movement speed
    private final int SPEED = 20;
    public Bullet( double x, double y, double a ){
        super( x, y, a );
        this.x = x;
        this.y = y;
        this.a = a;
    }
    @Override
    public void render( Graphics2D g2 ){
        g2.setColor(new Color(90, 90, 90));
        g2.fillOval( (int)this.x, (int)this.y, 6, 6);
    }
    @Override
    public void update(){
        //Trajectory
        x += (int) (Math.sin(a * (Math.PI/180)) * +SPEED);
        y += (int) (Math.cos(a * (Math.PI/180)) * -SPEED);
    }
    @Override
    public void attack() {
        
    }
    //Collision with
    @Override
    public boolean collides(Entity entity) {
        if( entity instanceof Enemy ){
            return collidesEnemyWithBullet( (Enemy)entity );
        }
        return false;
    }
    //Collision with Enemy
    public boolean collidesEnemyWithBullet( Enemy enemy){
        return enemy.getBounds().intersects( this.getBounds() );
    }
    @Override
    public Rectangle getBounds(){
        return new Rectangle( (int)x, (int)y, (int)(6), (int)(6) );
    }
}
