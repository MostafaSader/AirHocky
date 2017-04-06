

//Mostafa Sader 
//Air hockyGame(Graphic Part Build)
package sample;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.File;


public class PanDiscView {
    private double width, height, goalWidth, borderThickness, discRadius, panRadius;
    
    Group root = new Group();
    private Goal score1;
    private Goal score2;
    private Disc pan1 = new Disc(panRadius);
    private Disc pan2 = new Disc(panRadius);
    private Disc disc = new Disc(discRadius);
    {
        pan1.setX((width-2*borderThickness)/2);
        pan1.setY(height-(borderThickness+panRadius));
    }

    public PanDiscView(double width, double height, double goalWidth, double borderThickness, double discRadius,
                       double panRadius)
    {
        this.borderThickness = borderThickness;
        this.discRadius = discRadius;
        this.goalWidth = goalWidth;
        this.width = width;
        this.panRadius = panRadius;
        this.height = height;
        score1 = new Goal(1,Color.RED,width,height,borderThickness);
        score2 = new Goal(2,Color.BLUE,width,height,borderThickness);
    }

    public Goal getScore1() {
        return score1;
    }

    public Goal getScore2() {
        return score2;
    }

    
    public Group getGroup(){
        return root;
    }
    public javafx.scene.Scene getScene(){
        return createScene();   
    }
    
    public Disc getPan1(){
        return pan1;
    }
    public  Disc getPan2(){
        return pan2;
    }
    
    public Disc getDisc(){
        return disc;
    }
    
    
    
    private javafx.scene.Scene createScene(){
        //component Definition
        pan1.getDisc().setStroke(Color.WHITE);
        pan1.getDisc().setStrokeWidth(2);
        pan1.getDisc().setFill(Color.RED);
        pan1.getDisc().setRadius(panRadius);
        //====================================================================
        
        pan2.getDisc().setFill(Color.BLUE);
        pan2.getDisc().setStroke(Color.WHITE);
        pan2.getDisc().setStrokeWidth(2);
        pan2.getDisc().setRadius(panRadius);
        pan2.setX((width - borderThickness) / 2);
        //====================================================================
        disc.setX(width / 2);
        disc.setY(height / 2);
        disc.getDisc().setFill(Color.BLACK);
        disc.getDisc().setStroke(Color.WHITE);
        disc.getDisc().setStrokeWidth(3);
        disc.getDisc().setRadius(discRadius);
        //====================================================================
        Circle middleCircle = new    Circle(width / 2, height/2, goalWidth);
        middleCircle.setFill(Color.TRANSPARENT);
        middleCircle.setStrokeWidth(5);
        middleCircle.setStroke(Color.BLACK);
        //====================================================================
        Rectangle layout = new Rectangle(width, height, Color.BLACK);
        //====================================================================
        Line middleLine     = new    Line(0,height/2,width,height/2);
        middleLine.setFill(Color.BLACK);
        middleLine.setStroke(Color.BLACK);
        middleLine.setStrokeWidth(5);
        //====================================================================
        Arc gateWay1        = new    Arc(width / 2,height,goalWidth,goalWidth,0,180);
        gateWay1.setStroke(Color.BLACK);
        gateWay1.setFill(Color.TRANSPARENT);
        gateWay1.setStrokeWidth(5);
        //====================================================================
        Arc gateWay2        = new    Arc(width / 2,0,goalWidth,goalWidth,0,-180);
        gateWay2.setFill(Color.TRANSPARENT);
        gateWay2.setStroke(Color.BLACK);
        gateWay2.setStrokeWidth(5);
        //====================================================================
        Arc gateWay1Dashed  = new    Arc(width / 2,height,goalWidth+50,goalWidth+50,0,180);
        gateWay1Dashed.getStrokeDashArray().addAll(10d,5d);
        gateWay1Dashed.setFill(Color.TRANSPARENT);
        gateWay1Dashed.setStroke(Color.BLACK);
        //====================================================================
        Arc gateWay2Dashed  = new    Arc(width / 2,0,goalWidth + 50,goalWidth + 50,0,-180);
        gateWay2Dashed.getStrokeDashArray().addAll(10d,5d);
        gateWay2Dashed.setFill(Color.TRANSPARENT);
        gateWay2Dashed.setStroke(Color.BLACK);
        //====================================================================
        Rectangle goalArea1    = new Rectangle(width / 2 - goalWidth , height - borderThickness, goalWidth*2, borderThickness);
        goalArea1.setFill(Color.RED);
        //====================================================================
        Rectangle goalArea2    = new Rectangle(width / 2 - goalWidth , 0, goalWidth*2, borderThickness);
        goalArea2.setFill(Color.BLUE);
        //====================================================================
         
        //====================================================================
        File img = new File("src/bck.jpg");
        ImageView backgrnd = new ImageView(img.toURI().toString());
        backgrnd.setLayoutX(borderThickness);
        backgrnd.setLayoutY(borderThickness);
        backgrnd.setFitWidth(width - 2*borderThickness);
        backgrnd.setFitHeight(height - 2* borderThickness);
        //====================================================================
        root.getChildren().add(layout);
        root.getChildren().add(backgrnd);
        root.getChildren().add(middleCircle);
        root.getChildren().add(middleLine);
        root.getChildren().add(gateWay1Dashed);
        root.getChildren().add(gateWay2Dashed);
        root.getChildren().add(goalArea1);
        root.getChildren().add(goalArea2);
        root.getChildren().add(pan1.getDisc());
        root.getChildren().add(pan2.getDisc());
        root.getChildren().add(disc.getDisc());
        root.getChildren().add(gateWay1);
        root.getChildren().add(gateWay2);
        
        score1.addToGroup(root);
        score2.addToGroup(root);
      
        Scene scene = new Scene(root,width,height);
        scene.setCursor(Cursor.NONE);
        return scene;
    }   
}



