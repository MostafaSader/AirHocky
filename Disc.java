
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;



public class Main extends Application {
    public static PanDiscView view;

    @Override
    public void start(Stage primaryStage) {
        int a = 0;
        while(a <= 2000)
            System.out.println("b"+ a++);
        double width,height,border,goalwidth;
        width = 400;
        height= 600;
        border = 10;
        goalwidth = width / 8;
        view = new PanDiscView(width, height, goalwidth, border, 15, 30);
        view.getPan2().setY(50);

        AnimationTimer timer = new AnimationTimer() {
            double oldx,oldy,x,y;
            double oldTime,newTime;
            Disc pan1 = view.getPan1();
            Disc pan2 = view.getPan2();
            Disc disc = view.getDisc();
            Goal score1 = view.getScore1();
            Goal score2 = view.getScore2();
            int a = 0;

            @Override
            public void handle(long t){


                Point p = MouseInfo.getPointerInfo().getLocation();
                newTime = System.currentTimeMillis();
                pan1.setSpeedX(   (p.getX() - oldx) /  (newTime - oldTime)   );
                pan1.setSpeedY(   (p.getY() - oldy) /  (newTime - oldTime)   );
                pan1.setX(p.getX() - primaryStage.getX());
                pan1.setY(p.getY() - primaryStage.getY());
                disc.setX(disc.getSpeedX() * (newTime - oldTime) + disc.getX());
                disc.setY(disc.getSpeedY() * (newTime - oldTime) + disc.getY());
                if(pan2.getX() >= disc.getX())
                    pan2.setSpeedX(-1*Math.abs(disc.getSpeedX()) /2 );
                else pan2.setSpeedX( Math.abs(disc.getSpeedX()) /2 );
                pan2.setX(pan2.getSpeedX() * (newTime - oldTime) + pan2.getX());

                x = disc.getX();
                y = disc.getY();
                checkLows();
                oldx = p.getX();
                oldy = p.getY();
                oldTime = System.currentTimeMillis();
            }
            public void accident(Disc pan){
                disc.setSpeedX( pan1.getSpeedX()   - disc.getSpeedX() );
                disc.setSpeedY( pan1.getSpeedY()   - disc.getSpeedY() );
                //System.out.println("ac  " + pan1.getSpeedX());
            }

            public void checkLows(){
                if(disc.getX() >= width /2 - goalwidth && disc.getX() <= width /2 + goalwidth && disc.getY() >= height - 2* border){


                    disc.setSpeedX(0);
                    disc.setSpeedY(0);
                    disc.setX(width/2);
                    disc.setY(height/2);
                    score1.goal();

                }
                if(disc.getX() >= width /2 - goalwidth && disc.getX() <= width /2 + goalwidth && disc.getY() <=2* border){

                    score2.goal();

                    disc.setSpeedX(0);
                    disc.setSpeedY(0);
                    disc.setX(width/2);
                    disc.setY(height/2);

                }
                if(Math.sqrt(Math.pow(pan1.getX()-  disc.getX(),2) + Math.pow(pan1.getY()-  disc.getY(),2))
                        <= (pan1.getDisc().getRadius() + disc.getDisc().getRadius())){
                    disc.setX(x);
                    disc.setY(y);
                    accident(pan1);
                }

                if(Math.sqrt(Math.pow(pan2.getX()-  disc.getX(),2) + Math.pow(pan2.getY()-  disc.getY(),2))
                        <= (pan2.getDisc().getRadius() + disc.getDisc().getRadius())){
                    accident(pan2);
                    disc.setX(x);
                    disc.setY(y);
                }
                if((Math.abs(disc.getX()-width /2) >= width / 2 && Math.abs(disc.getY() - height /2) >= height / 2)){
                    disc.setSpeedX(disc.getSpeedX() * -1);
                    disc.setSpeedY(disc.getSpeedX() * -1);
                    disc.setX(x);
                    disc.setY(y);}
                else if(Math.abs(disc.getX() - (width - 2*border) /2) >= (width - 2 * border) / 2 ){
                    disc.setSpeedX(disc.getSpeedX() * -1);
                    disc.setX(x);
                    disc.setY(y);
                }
                else if(Math.abs(disc.getY() - (height - 2* border) /2) >= (height - 2*border) / 2){
                    disc.setSpeedY(disc.getSpeedY() * -1);
                    disc.setX(x);
                    disc.setY(y);
                }

            }
        };

        timer.start();

        primaryStage.setMaxWidth(width + 20);
        primaryStage.setMaxHeight(height+ 60);
        primaryStage.setScene(view.getScene());
        primaryStage.setTitle("Air Hocky");
        File img = new File("src/icon.png");
        primaryStage.getIcons().add(new javafx.scene.image.Image(img.toURI().toString()));
        primaryStage.show();
        //(new Thread(new run())).start();

    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }


}


