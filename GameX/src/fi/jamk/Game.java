/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jani
 */
public class Game implements Runnable{
    private static Listener listener;
    private Entity          player, enemy;
    private Level           level;
    private Thread          thread;
    private Camera          camera;
    private World           world;
    private final String    TITLE = "Untitled Game";
    private int             WIDTH = 600, HEIGHT = 400;
    private boolean         running = false;
    private ArrayList<Entity>     enemies;
    private ArrayList<Bullet>     bullets;
    private long timer = System.nanoTime();
    private long enemyDelay = 25000;
    private int spawnCount = 1;
    private int kills = 0;
    public Game( int WIDTH, int HEIGHT ){
        listener = new Listener();
        level = new Level( WIDTH, HEIGHT, TITLE, this ); 
        
        world = new World( 0, 0 );
        player = new Player( 50, 50 );
        enemies = new ArrayList();   
        
        camera = new Camera();
        camera.setLevel( level );
        camera.setPlayer( player );
        
        listener.setLevel( level );
        listener.init();
        
        level.setListener( listener );
        level.init();   
    }
    public synchronized void start(){
        running = true;     (thread = new Thread(this)).start();
    }
    public synchronized void stop() throws InterruptedException{
        running = false;    thread.join();
    }
    //Game loop
    @Override
    public void run(){
        while(running){
            try {
                level.panelLevel.repaint();
                update();
                collision();
                Thread.sleep(20);
            } catch (InterruptedException e) {}
        }
    }
    public void render( Graphics2D g2 ){
        camera.render( g2 );
        world.render( g2 );
        
        //Draw enemies
        for( int i = 0; i < enemies.size(); i++ ){
            enemy = enemies.get(i);
            enemy.render( g2 ); 
        }
        player.render( g2 );
        //Draw bullets
        bullets = player.getBullets();
        for( int i = 0; i < bullets.size(); i++ ){
            Bullet b = bullets.get(i);
            b.render( g2 );
        }
    }
    public void update(){
        //Spawn enemies
        spawnEnemies();
        //Update Enemies
        for( int i = 0; i < enemies.size(); i++ ){
            enemy = enemies.get(i);
            enemy.update();
        }
        //Update Bullets
        bullets = player.getBullets();
        for( int i = 0; i < bullets.size(); i++ ){
            Bullet b = bullets.get(i);
            b.update();
        }
        player.update();
        camera.update();
        //Enemy status
        for( int i = 0; i < enemies.size(); i++){
            if( enemies.get(i).getStatus() == false){
                enemies.remove(i--);kills++;
             }
         }
        //Player status
        if(player.getStatus() == false){
            level.gameMode = 1;
            level.setStatistics( kills );
        }
    }
    public void collision(){
        //Collision Enemy-Enemy
        for( int i = 0; i < enemies.size(); i++ ){
            Entity e = enemies.get(i);
            for( int j = 0; j < enemies.size(); j++ ){
                Entity k = enemies.get(j);
                enemies.get(i).setSpeed(5);
                if( e.collides(k) == true ){
                    enemies.get(i).setSpeed(0);
                }
            }
        }    
        //Collision Bullet-Enemy
        bullets = player.getBullets();
        for( int i = 0; i < bullets.size(); i++ ){
            Bullet b = bullets.get(i);
            for( int j = 0; j < enemies.size(); j++ ){
                enemy = enemies.get(j);
                if( b.collides(enemy) ){
                    enemy.hit();
                    bullets.remove(i--); break;
                }
            }
        }      
        //Collision Player-Enemy
        for( int j = 0; j < enemies.size(); j++ ){ 
            enemy = enemies.get(j);
            enemies.get(j).setSpeed(5);
            if(player.collides(enemy) == true){
                enemies.get(j).setSpeed(0);
                enemy.attack();
            }
        }
    }
    public void spawnEnemies(){
        //Spawn enemie
        long elapsed = (System.nanoTime() - timer) / 1000000;
        if(elapsed > enemyDelay){
            for( int i = 0; i < spawnCount; i++ ){
                enemies.add( new Enemy( rand( -2500, 2500), rand(-2500, 2500), player ) ); 
                timer = System.nanoTime();
            }
            spawnCount++;
        }
    }
    //Random position
    public static int rand(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    public static void main(String[] args){
        Menu menu = new Menu();
    }
}
