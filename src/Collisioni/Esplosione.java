/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Collisioni;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Esplosione {
    
     private int x, y;
    private Image image[];
    private boolean visible;
    private int larghezza;
    private int altezza;
    private int numImmagine;
       
    private boolean terminaGioco=false;

    public boolean isTerminaGioco() {
        return terminaGioco;
    }

    public void setTerminaGioco(boolean terminaGioco) {
        this.terminaGioco = terminaGioco;
    }
    
        

    public Esplosione(int x, int y) {
        image=new Image[12];
        
        for (int i=0;i<image.length;i++){
            String nome="../images/explod"+(i+1)+".png";
            ImageIcon ii = new ImageIcon(this.getClass().getResource(nome));        
            image[i] = ii.getImage();  
        }
              
        visible = true;
        this.x = x;
        this.y = y;
        larghezza=image[0].getWidth(null);
        altezza=image[0].getHeight(null);
        numImmagine=0;
    }
    
      public Rectangle getDimensione(){
       return new Rectangle(x,y,larghezza,altezza);
   }


    public Image getImage() {
        return image[numImmagine];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

        

    public boolean isVisible() {
        return visible;
    }

    public void move() {
       numImmagine++;
       if (numImmagine>11){
           numImmagine=11;
           terminaGioco=true;
       }
           
    }
    
}

