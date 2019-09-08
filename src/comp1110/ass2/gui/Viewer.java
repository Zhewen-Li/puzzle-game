package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.ArrayList;
// Created by Saffron Bannister
/**
 * A very simple viewer for piece placements in the twist game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = SQUARE_SIZE * 10;
    private static final int VIEWER_HEIGHT = SQUARE_SIZE * 6 + 40;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    public final Group pieces = new Group();

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement  A valid placement string
     */
    void makePlacement(String placement) {
        resetBoard();
        // FIXED Task 4: implement the simple placement viewer
        String[] fours = splitIntoFours(placement);
        for (String s : fours) {
            drawOnBoard(s);
        }
    }

    void resetBoard() {
        Rectangle r = new Rectangle(SQUARE_SIZE/2,SQUARE_SIZE/2+SQUARE_SIZE/6,VIEWER_WIDTH-SQUARE_SIZE,VIEWER_HEIGHT-80-SQUARE_SIZE/2-SQUARE_SIZE/6);
        Color c = Color.rgb(150,150,170);
        r.setFill(c);
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8;j++) {
                int x = SQUARE_SIZE + (j) * SQUARE_SIZE+ SQUARE_SIZE/2;
                int y = SQUARE_SIZE * i + SQUARE_SIZE + SQUARE_SIZE/2;
                Circle circle = new Circle(x, y, SQUARE_SIZE/2);
                Circle circle1 = new Circle(x,y,SQUARE_SIZE/2-SQUARE_SIZE/4);
                Color c3 = Color.rgb(130,130,150);
                Color c2 = Color.rgb(110,110,130);
                circle1.setFill(c2);
                circle.setFill(c3);
                circles.add(circle);
                circles.add(circle1);
            }
        }
        Color c2 = Color.rgb(110,110,130);
        Rectangle r2 = new Rectangle(SQUARE_SIZE+SQUARE_SIZE/2,SQUARE_SIZE+SQUARE_SIZE/2,(SQUARE_SIZE)*7,(SQUARE_SIZE)*3);
        r2.setFill(c2);
        Text title = new Text (VIEWER_WIDTH/2-45,25,"VIEWER");
        title.setScaleX(3);
        title.setScaleY(3);
        Text a = new Text(SQUARE_SIZE-SQUARE_SIZE/4,SQUARE_SIZE+SQUARE_SIZE/2,"A");
        Text b = new Text(SQUARE_SIZE-SQUARE_SIZE/4,SQUARE_SIZE+SQUARE_SIZE/2+SQUARE_SIZE,"B");
        Text cc = new Text(SQUARE_SIZE-SQUARE_SIZE/4,SQUARE_SIZE+SQUARE_SIZE/2+2*SQUARE_SIZE,"C");
        Text d = new Text(SQUARE_SIZE-SQUARE_SIZE/4,SQUARE_SIZE+SQUARE_SIZE/2+3*SQUARE_SIZE,"D");
        Text one = new Text(2*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"1");
        Text two = new Text(3*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"2");
        Text three = new Text(4*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"3");
        Text four = new Text(5*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"4");
        Text five = new Text(6*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"5");
        Text six = new Text(7*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"6");
        Text seven = new Text(8*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"7");
        Text eight = new Text(9*SQUARE_SIZE-SQUARE_SIZE/2 - 5,SQUARE_SIZE - 5,"8");

        ArrayList<Text> text = new ArrayList<>();
        text.add(a);
        text.add(b);
        text.add(cc);
        text.add(d);
        text.add(title);
        text.add(one);
        text.add(two);
        text.add(three);
        text.add(four);
        text.add(five);
        text.add(six);
        text.add(seven);
        text.add(eight);

        pieces.getChildren().addAll(r);
        pieces.getChildren().addAll(r2);
        pieces.getChildren().addAll(circles);
        pieces.getChildren().addAll(text);
    }
    //split the string by groups of four
    String[] splitIntoFours(String s) {
        assert (s.length() % 4 == 0);
        String[] fours = new String[s.length()/4];
        int j = 0;
        for (int i = 0; i < s.length(); i+=4) {
            fours[j] = s.substring(i,i+4);
            j++;
        }
        return fours;
    }

    //void function to draw a string s on the board
    public void drawOnBoard(String s) {

        Image i = new Image(Viewer.class.getResource(URI_BASE + s.charAt(0) +".png").toString());
        ImageView piece = new ImageView();
        piece.setImage(i);

        //set the width/height
        piece.setFitWidth(getWidth(s));
        piece.setPreserveRatio(true);
        piece.setSmooth(true);


        // The folling lines of code were added by Lauren Nelson-Lee as a way of normalizing placement area
        // edited by Saffi to a) be based on SQUARE_SIZE and b) include all pieces
        int diffX = 0;
        int diffY = 0;
        double o = getRotation(s.charAt(3));
        if (o == 90 || o == 270){
            switch (s.charAt(0)) {
                case 'a': case 'b': case 'd':case 'f': diffX = SQUARE_SIZE/2; diffY = SQUARE_SIZE/2; break;
                case 'h': diffX= SQUARE_SIZE; diffY = SQUARE_SIZE; break;
                case 'c': diffX = SQUARE_SIZE+SQUARE_SIZE/2; diffY = SQUARE_SIZE+SQUARE_SIZE/2; break;

            }
        }
        //end Laurens addition///////////////////////////////////////////////

        //set x and y co-ordinates
        piece.setX(getX(s.charAt(1))-diffX);
        piece.setY(getY(s.charAt(2))+diffY);

        //flip
        piece.setScaleY(flip(s.charAt(3)));

        //set rotation
        piece.setRotate(getRotation(s.charAt(3)));
        //add to group pieces


        pieces.getChildren().add(piece);
    }

    //return -1 if the char should be flipped, return 1 otherwise
    private double flip(char c) {
        int i = Integer.parseInt(String.valueOf(c));
        if (i < 4) {
            return 1;
        }
        else return -1;
    }

    //return the width for the piece
    private int getWidth(String s) {
        switch (s.charAt(0)) {
            case 'i':case 'j':case 'k':case 'l': return SQUARE_SIZE;
            case 'a':case 'b':case 'd':case 'f':case 'g':case 'h':  return SQUARE_SIZE*3;
            case 'e':return SQUARE_SIZE*2;
            case 'c': return SQUARE_SIZE*4;
        }
        return 0;
    }
    //return the degrees rotation for the piece
    private double getRotation(char c) {
        switch (c) {
            case '0':case '4': return 0;
            case '1':case '5': return 90;
            case '2':case '6': return 180;
            case '3':case '7':return 270;
        }
        return 0;
    }

    //gets the desired Y co-ordinate
    private double getY(char c) {
        switch (c) {
            case 'A': return SQUARE_SIZE;
            case 'B': return 2*SQUARE_SIZE;
            case 'C': return 3*SQUARE_SIZE;
            case 'D':return 4*SQUARE_SIZE;
        }
        System.out.println("Invalid Y position");
        return 10000;
    }

    //gets the desired X co-ordinate
    private double getX(char c) {
        int i = Integer.parseInt(String.valueOf(c));
        if (i >= 1 && i <=8) {
            return i * SQUARE_SIZE;
        }
        else {
            System.out.println("Invalid X position");
            return 10000;
        }
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField ();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(SQUARE_SIZE);
        hb.setLayoutY(VIEWER_HEIGHT - SQUARE_SIZE);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("TwistGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);
        root.getChildren().add(pieces);

        makeControls();
        resetBoard();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
