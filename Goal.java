/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author msade
 */
public class Goal {
    private Text scoreLable = new Text();
    private int score = 0;
    private Text goalText = new Text("..::Goal::..");
    private double width,height,boarder;
    private int playerNO;
    
    
    public Goal(int playerNO , Color color,double width,double height,double boarder){
        scoreLable.setFill(color);
        goalText.setFill(color);
        this.width = width;
        this.height = height;
        this.playerNO =playerNO;
        this.boarder =boarder;
    }
    public void goal(){
        double time = 0;
        double positionY = goalText.getTranslateY();
        if(System.currentTimeMillis() - time >= 100)
        score++;
        
        goalText.setVisible(true);
        AnimationTimer showText = new AnimationTimer() {
            double positionX = -100;
            double PositionY;
            double realPositionY = goalText.getTranslateY();
            boolean isup = true;
            
            @Override
            public void handle(long now) {  //Goal Text Animation
                goalText.setTranslateX(positionX);
                positionX += 5;
                if(isup==true){
                    goalText.setTranslateY(goalText.getTranslateY() + PositionY);
                    PositionY++;
                if(PositionY == 15)
                    isup = false;
                }
                else {goalText.setTranslateY(goalText.getTranslateY() - PositionY);
                PositionY--;
                if(PositionY == 0)
                isup = true;
                }
                if(goalText.getTranslateX() >= width*2){
                    stop();
                    goalText.setVisible(false);
                    goalText.setTranslateY(realPositionY);
                }
            }
        };
        
        showText.start();
        scoreLable.setText(""+score);
    }
    public void addToGroup(Group root){
        scoreLable.setVisible(true);
        goalText.setVisible(false);
        scoreLable.setFont(Font.font("Bauhaus 93",50));
        goalText.setFont(Font.font("Bauhaus 93",70));
        if(playerNO == 1){
            scoreLable.setTranslateX(boarder + 10);
            scoreLable.setTranslateY(height / 2 + 50);
            goalText.setLayoutX(-150);
            goalText.setLayoutY(height - height / 4);
            System.out.println("goal  " +height);
        }
         if(playerNO == 2){
            scoreLable.setLayoutX(boarder + 10);
            scoreLable.setLayoutY(height / 2 - 10);
            goalText.setLayoutX(-150);
            goalText.setLayoutY(height / 4);
        }
        scoreLable.setText(""+score);
        root.getChildren().add(scoreLable);
        root.getChildren().add(goalText);
    }
    
    
}

