/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Jani
 */
public abstract class Entity {
    //Custom attributes:
    protected Entity    player, enemy;
    protected ArrayList bullets;
    protected double    x = 0, y = 0;   //X-axis, Y-axis
    protected double    a = 0, r = 0;   //Angle, Radius
    private final Image image;
    private String      imagePath = null;
    
    //Default attributes:
    private boolean     status = false; //Alive/Dead
    private int         health = 5;
    protected int       delay = 15;     //Attacking delay
    protected int       speed = 5;      //Entity speed
    public Entity( double x, double y, double a ){
        this.image = null;
        this.x = x;
        this.y = y;
        this.status = true;
    }
    public Entity( double x, double y, String imagePath ){
        this.imagePath = imagePath;
        this.image = loadImage();
        this.x = x;
        this.y = y;
        this.r = image.getWidth( null ) / 2;
        this.status = true;
    }
    //Abstract methods, player enemy, bullet
    public abstract void render( Graphics2D g2 );
    public abstract void update();  
    public abstract void attack();
    public abstract boolean collides( Entity entity );
    
    public void moveForward(){
        x += (Math.sin(a * ( Math.PI / 180) ) * +speed);
        y += (Math.cos(a * ( Math.PI / 180) ) * -speed);
    }
    public void moveBackward(){
        x -= (Math.sin(a * ( Math.PI / 180) ) * +speed);
        y -= (Math.cos(a * ( Math.PI / 180) ) * -speed);
    }
    public void roteteLeft(){
        this.a = (int)(a - ( speed / 1.5 ));
    }
    public void rotateRight(){
        this.a = (int)(a + ( speed / 1.5 ));
    }
    //Get x
    public double getX(){
        return x;
    }
    //Get y
    public double getY(){
        return y;
    }
    //Get angle
    public double getA(){
        return a;
    }
    //Get radius
    public double getR(){
        return r;
    }
    //Get image file path
    public String getImagePath(){
        return imagePath;
    }
    //Get image
    public Image getImage(){
        return image;
    }
    //Load image file from image path
    public final Image loadImage(){
        return new ImageIcon( getClass().getResource( imagePath ) ).getImage();
    }
    //Get circle Bounds
    public Rectangle getBounds(){
        return new Rectangle( (int)x, (int)y, (int)(r * 2), (int)(r * 2) );
    }
    //Get status ( status = true ( ALIVE ), status = false ( DEAD ) )
    public boolean getStatus(){
        return status;
    }
    public void setStatus( boolean status){
        this.status = status;
    }
    public void setPlayer( Entity player ){
        this.player = player;
    }
    public void setSpeed( int speed ){
        this.speed = speed;
    }
    public ArrayList getBullets(){
        return bullets;
    }
    public int getHealth(){
        return health;
    }
    //If collision detected
    public void hit(){
        health--;
        if( health <= 0){
            status = false;
        }
    }
}
