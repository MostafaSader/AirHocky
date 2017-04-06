
package sample;

import javafx.scene.shape.Circle;



public class Disc {
    private Circle disc = new Circle();
    private double speedX,speedY;
    
    public Disc(double discR){
        disc.setRadius(discR);
    }
    public Circle getDisc() {
        return disc;
    }
    
    public void setX(double x){
        this.disc.setTranslateX(x);
    }
    public void setY(double y){
        this.disc.setTranslateY(y);
    }
    public double getX(){
        return this.disc.getTranslateX();
    }
    public double getY(){
        return this.disc.getTranslateY();
    }

    public double getSpeedX() {
        return speedX;
    }
    

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
    
    
        
}
