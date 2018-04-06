/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arttu
 */
public class World{
    Image grassImg = getPlayerImg();
    Image topLeft = getTLImg();
    Image topRight = getTRImg();
    Image bottomLeft = getBLImg();
    Image bottomRight = getBRImg();
    private int     x = 0, y = 0;
    public int dx = 0, dy = 0;
     
    public World(int x, int y){
 
    }
    public void update(){
        y += dy;
        x += dx;
    }
    public void render(Graphics2D g2){
       
        for (int x=-1600;x<=1580;x+=32){
            for(int y=-1600;y<=1580;y+=32){
        g2.drawImage(grassImg, x, y, null);
            }
        }
        g2.drawImage(topLeft, -1600, -1600, null);
        g2.drawImage(topRight, 0, -1600, null);
        g2.drawImage(bottomLeft, -1600, -5, null);
        g2.drawImage(bottomRight, 0, 0, null);
    }
    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon(getClass().getResource("Kuvat/grass.png"), "grass.png"); 
        return ic.getImage();
    }
    
    public Image getTLImg(){
        ImageIcon ic2 = new ImageIcon(getClass().getResource("Kuvat/TopLeftMap.png"), "TopLeftMap.png");
        return ic2.getImage();
    }
    
    public Image getTRImg(){
        ImageIcon ic3 = new ImageIcon(getClass().getResource("Kuvat/TopRightMap.png"), "TopRightMap.png");
        return ic3.getImage();
    }
    
    public Image getBLImg(){
        ImageIcon ic4 = new ImageIcon(getClass().getResource("Kuvat/BottomLeftMap.png"), "BottomLeftMap.png");
        return ic4.getImage();
    }
    
    public Image getBRImg(){
        ImageIcon ic5 = new ImageIcon(getClass().getResource("Kuvat/BottomRightMap.png"), "BottomRightMap.png");
        return ic5.getImage();
    }
}