package comp1110.ass2.gui;


import comp1110.ass2.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.*;

/* The outlook of the Board was mainly created by Saffron Bannister.
   The placement and mouse operation was created by Lauren Nelson-Lee.
   The attributes (refresh, hint, instruction, congratulation sign) were created by Zhewen Li */

public class Board extends Application {
    private String undrawn;
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = SQUARE_SIZE * 10;
    private static final int VIEWER_HEIGHT = SQUARE_SIZE * 6 + 40;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    public final Group pieces = new Group();
    private final Group instructions =new Group();
    private final Group reset = new Group();
    private boolean noDifficulty = true;
    final private Slider difficulty = new Slider();
    private Boolean clickBool = true;
    private String totalPiecePegStr = "";
    private ImageView lastHint;
    private boolean prevHint = false;
    TextField textField;

    private String goal;

    private String strPieceA = "a9E0";
    private String strPieceB = "b9E0";
    private String strPieceC = "c9E0";
    private String strPieceD = "d9E0";
    private String strPieceE = "e9E0";
    private String strPieceF = "f9E0";
    private String strPieceG = "g9E0";
    private String strPieceH = "h9E0";

    private Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

    //method created by Saffron Bannister
    private void resetBoard() {
        Rectangle background = new Rectangle(0,0,BOARD_WIDTH,BOARD_HEIGHT);
        //Color bgcolour = Color.FLORALWHITE;
        Color bgcolour = Color.ROSYBROWN;
        background.setFill(bgcolour);
        Rectangle r0 = new Rectangle(SQUARE_SIZE/2-10,SQUARE_SIZE/2-10,VIEWER_WIDTH-SQUARE_SIZE,VIEWER_HEIGHT-80-SQUARE_SIZE/2-SQUARE_SIZE/6);
        //r0.setFill(Color.ORANGE);
        r0.setFill(Color.INDIGO);
        Rectangle r00 = new Rectangle(SQUARE_SIZE/2+10,SQUARE_SIZE/2+10,VIEWER_WIDTH-SQUARE_SIZE,VIEWER_HEIGHT-80-SQUARE_SIZE/2-SQUARE_SIZE/6);
        //r00.setFill(Color.PINK);
        r00.setFill(Color.ORCHID);
        //r00.setFill(Color.INDIGO);
        Rectangle r = new Rectangle(SQUARE_SIZE/2,SQUARE_SIZE/2,VIEWER_WIDTH-SQUARE_SIZE,VIEWER_HEIGHT-80-SQUARE_SIZE/2-SQUARE_SIZE/6);
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
        Text title = new Text (VIEWER_WIDTH+80,VIEWER_HEIGHT/6,"IQ-Twist");
        title.setFont(Font.font("Berlin Sans FB Demi"));
        title.setScaleX(4);
        title.setScaleY(4);
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

        pieces.getChildren().addAll(background);
        pieces.getChildren().addAll(r0);
        pieces.getChildren().addAll(r00);
        pieces.getChildren().addAll(r);
        pieces.getChildren().addAll(r2);
        pieces.getChildren().addAll(circles);
        pieces.getChildren().addAll(text);
    }

    // instruction button, created by Zhewen Li
    private void instructionButton(){
        Button ins = new Button("Instruction");
        ins.setOnAction(event -> showInstruction());
        ins.setLayoutX(VIEWER_WIDTH+220);
        ins.setLayoutY(VIEWER_HEIGHT/6+20);
        controls.getChildren().addAll(ins);

    }

    // adding instruction content by Zhewen Li
    private Text addContent(Text a){
        a.setFont(Font.font("Berlin Sans FB Demi",20));
        instructions.getChildren().addAll(a);
        return a;
    }

    // adding instruction window by Zhewen Li
    private void insWindow(){
        Rectangle r = new Rectangle(1.5*SQUARE_SIZE,1.5*SQUARE_SIZE,BOARD_WIDTH/3*2,BOARD_HEIGHT/4*3);
        r.setFill(Color.rgb(238,238,238,0.8));
        r.setStroke(Color.BURLYWOOD);
        r.setStrokeWidth(5);
        instructions.getChildren().addAll(r);
    }

    // show instruction and back button by Zhewen Li
    private void showInstruction(){
        insWindow();
        Text title = new Text(2.5*SQUARE_SIZE, 2.5*SQUARE_SIZE,"INSTRUCTIONS");
        title.setFont(Font.font("Berlin Sans FB Demi",30));
        title.setFill(Color.rgb(78,52,46));


        addContent(new Text(2.5*SQUARE_SIZE, 3.5*SQUARE_SIZE,"1. Click on one piece:"));
        addContent(new Text(3.5*SQUARE_SIZE, 4.5*SQUARE_SIZE,"Press 'F' to flip the piece"));
        addContent(new Text(3.5*SQUARE_SIZE, 5.3*SQUARE_SIZE,"Scroll mouse to rotate the piece"));
        addContent(new Text(2.5*SQUARE_SIZE, 6.5*SQUARE_SIZE,"2. Hold '/' button for a hint"));
        addContent(new Text(2.5*SQUARE_SIZE, 7.5*SQUARE_SIZE,"3. Press 'Refresh' to reset the board"));

        Text in = addContent(new Text(7.8*SQUARE_SIZE,2.5*SQUARE_SIZE,"( Press 'Back' to close -> )"));
        in.setFill(Color.rgb(150,150,170));
        in.setScaleX(0.8);
        in.setScaleY(0.8);


        instructions.getChildren().addAll(title);

        Button cancel = new Button("Back");
        cancel.setOnAction(event -> instructions.getChildren().clear());
        cancel.setLayoutX(VIEWER_WIDTH+220);
        cancel.setLayoutY(VIEWER_HEIGHT/6+SQUARE_SIZE);

        instructions.getChildren().add(cancel);

    }

    //split the string by groups of four by Saffron Bannister
    private String[] splitIntoFours(String s) {
        assert (s.length() % 4 == 0);
        String[] fours = new String[s.length()/4];
        int j = 0;
        for (int i = 0; i < s.length(); i+=4) {
            fours[j] = s.substring(i,i+4);
            j++;
        }
        return fours;
    }

    //void function to draw a string s on the board (used for hints) by Saffron Bannister
    //with input from Lauren Nelson-Lee
    private void drawOnBoard(String s) {

        Image i = new Image(Viewer.class.getResource(URI_BASE + s.charAt(0) +".png").toString());
        ImageView piece = new ImageView();
        piece.setImage(i);

        //set the width/height
        piece.setOpacity(0.4);
        piece.setFitWidth(getWidth(s));
        piece.setPreserveRatio(true);
        piece.setSmooth(true);

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

        piece.setX(getX(s)-diffX);
        piece.setY(getY(s)+diffY);

        //flip
        piece.setScaleY(flip(s.charAt(3)));

        //set rotation
        piece.setRotate(getRotation(s.charAt(3)));
        //add to group pieces

        pieces.getChildren().add(piece);
        lastHint = piece;

    }

    //return -1 if the char should be flipped, return 1 otherwise. By Saffron Bannister.
    private double flip(char c) {
        int i = Integer.parseInt(String.valueOf(c));
        if (i < 4) {
            return 1;
        }
        else return -1;
    }

    //return the width for the piece. By Saffron Bannister
    private int getWidth(String s) {
        switch (s.charAt(0)) {
            case 'i':case 'j':case 'k':case 'l': return SQUARE_SIZE;
            case 'a':case 'b':case 'd':case 'f':case 'g':case 'h':  return SQUARE_SIZE*3;
            case 'e':return SQUARE_SIZE*2;
            case 'c': return SQUARE_SIZE*4;
        }
        return 0;
    }

    //return the height for the piece. By Saffron Bannister
    private int getHeight(String s) {
        switch (s.charAt(0)) {
            case 'a':case 'b':case 'd':case 'e':case 'f': return SQUARE_SIZE*2;
            case 'c':case 'h': return SQUARE_SIZE;
            case 'g':return SQUARE_SIZE*3;
        }
        return 0;
    }
    //return the degrees rotation for the piece. By Saffron Bannister
    private double getRotation(char c) {
        switch (c) {
            case '0':case '4': return 0;
            case '1':case '5': return 90;
            case '2':case '6': return 180;
            case '3':case '7':return 270;
        }
        return 0;
    }

    //gets the desired Y co-ordinate. By Saffron Bannister
    private double getY(String s) {
        char c = s.charAt(2);
        switch (c) {
            case 'A': return SQUARE_SIZE;
            case 'B': return 2*SQUARE_SIZE;
            case 'C': return 3*SQUARE_SIZE;
            case 'D':return 4*SQUARE_SIZE;
            case 'E': switch (s.charAt(0)) {
                case 'a':case 'c':case 'e':case 'g': return SQUARE_SIZE*6;
                case 'b':case 'd':case 'f':case 'h': return SQUARE_SIZE*9;
            }
        }

        System.out.println("Invalid Y position");
        return 10000;
    }

    //gets the desired X co-ordinate by Saffron Bannister
    private double getX(String s) {
        int i = Integer.parseInt(String.valueOf(s.charAt(1)));
        if (i >= 1 && i <=8) {
            return i * SQUARE_SIZE;
        }
        else if (i == 9) {
            switch (s.charAt(0)) {
                case 'a': case 'b': return SQUARE_SIZE;
                case 'c':case 'd': return SQUARE_SIZE*4;
                case 'e':case 'f': return SQUARE_SIZE*8;
                case 'g':case 'h': return SQUARE_SIZE*11;
            }
        }
        System.out.println("Invalid X position");
        return 10000;

    }

    // reset button by Zhewen Li
    private void resetButton(Stage stage) {

        Button refresh = new Button("Refresh");
        refresh.setOnAction(e -> resetAll(stage));
        //VIEWER_WIDTH+100,VIEWER_HEIGHT/6
        refresh.setLayoutX(VIEWER_WIDTH+220);
        refresh.setLayoutY(VIEWER_HEIGHT/6-15);
        controls.getChildren().add(refresh);

    }

    // EventHandler of reset button by Zhewen Li
    private void resetAll(Stage stage) {

        root.getChildren().clear();
        startGame(stage);

    }

    // get hint after placing 4 pieces by Zhewen Li, edited by Saffron Bannister
    private String getHint(){
        String str = goal;

        String[] a1 = splitIntoFours(totalPiecePegStr);
        String[] a2 = splitIntoFours(str);

        ArrayList<String> b1 = new ArrayList<>();
        ArrayList<String> b2 = new ArrayList<>();
        Collections.addAll(b1, a1);
        Collections.addAll(b2, a2);

        b2.removeAll(b1);
        Random rand = new Random();
        if (b2.size() > 0) {
            String show = b2.get(rand.nextInt(b2.size()));
            System.out.println(b2);
            return show;
        }
        else{
            return null;
        }

    }

    // hint button by Zhewen Li - replaced with / command
    private void hintButton(String placement){
        Button hint = new Button("Hint");
        hint.setOnAction(event -> showHint(placement));
        hint.setLayoutX(VIEWER_WIDTH+220);
        hint.setLayoutY(VIEWER_HEIGHT/6+SQUARE_SIZE);
        reset.getChildren().add(hint);

    }

    // Set difficulty for generating the starting placement
    // method created by Saffron Bannister
    private void makeDifficulty() {
        noDifficulty = false;

        difficulty.setMin(1);
        difficulty.setMax(4);
        difficulty.setValue(0);
        difficulty.setShowTickLabels(true);
        difficulty.setShowTickMarks(true);
        difficulty.setMajorTickUnit(1);
        difficulty.setMinorTickCount(0);
        difficulty.setSnapToTicks(true);
        difficulty.setLayoutX(VIEWER_WIDTH+100);
        difficulty.setLayoutY(VIEWER_HEIGHT/6+SQUARE_SIZE*3);
        controls.getChildren().add(difficulty);

        Label difficultyCaption = new Label("Difficulty:");
        difficultyCaption.setTextFill(Color.BLACK);
        difficultyCaption.setLayoutX(VIEWER_WIDTH+100);
        difficultyCaption.setLayoutY(VIEWER_HEIGHT/6+SQUARE_SIZE*3-20);
        controls.getChildren().add(difficultyCaption);
    }

    // EventHandler of hint button, created by Zhewen Li
    private void showHint(String hint){
        drawOnBoard(hint);
    }

    // initialise starting placement by Lauren Nelson-Lee
    private void setStartingPlacement(String placement){
        char [] sList = placement.toCharArray();
        for(int i = 0; i < placement.length(); i = i+4){
            Image img = new Image(Viewer.class.getResource(URI_BASE + sList[i] +".png").toString());
            ImageView peg = new ImageView();
            peg.setImage(img);
            peg.setPreserveRatio(true);
            peg.setSmooth(true);

            double x = getBoardCircleColX(placement.substring(i+1,i+2));
            double y = getBoardCircleRowY(placement.substring(i+2,i+3));
            peg.setX(x);
            peg.setY(y);
            pieces.getChildren().add(peg);

        }
    }

    // initialising pieces by Lauren Nelson-Lee
    private PieceJFX initialisePieces(String pieceStr, int x, int y){
        return new PieceJFX(pieceStr,x,y);
    }

    // initialising ImageView of pieces by Lauren Nelson-Lee
    private ImageView initialisePieceView(PieceJFX piece){
        ImageView pieceImage = piece.getImagePiece();
        pieceImage.setFitWidth(getWidth(piece.pieceStr));
        pieceImage.setFitHeight(getHeight(piece.pieceStr));
        return pieceImage;
    }

    // main method of the game by Lauren Nelson-Lee, Zhewen Li and Saffron Bannister
    private void startGame(Stage stage){

        stage.setTitle("IQ-Twist");
        resetButton(stage);
        instructionButton();
        if (noDifficulty) makeDifficulty();

        root.getChildren().add(pieces);
        root.getChildren().add(controls);
        root.getChildren().add(instructions);
        root.getChildren().add(reset);

        undrawn = "abcdefgh";
        resetBoard();
        //drawStartingPieces();

        InitialiseBoard start = new InitialiseBoard((int) difficulty.getValue() - 1);
        goal = InitialiseBoard.getGoal();
        stage.setScene(scene);

        Circle c = new Circle(60,298,1);
        c.setFill(Color.BLACK);
        root.getChildren().add(c);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Lines below written by Lauren Nelson-Lee

        //initialising the pieces
        //Piece A
        PieceJFX pieceA = initialisePieces(strPieceA,700,400);
        ImageView pieceAimage = initialisePieceView(pieceA);
        //Piece B
        PieceJFX pieceB = initialisePieces(strPieceB,700,510);
        ImageView pieceBimage = initialisePieceView(pieceB);
        //Piece C
        PieceJFX pieceC = initialisePieces(strPieceC,50,550);
        ImageView pieceCimage = initialisePieceView(pieceC);
        //Piece D
        PieceJFX pieceD = initialisePieces(strPieceD,70,400);
        ImageView pieceDimage = initialisePieceView(pieceD);
        //Piece E
        PieceJFX pieceE = initialisePieces(strPieceE,530,510);
        ImageView pieceEimage = initialisePieceView(pieceE);
        //Piece F
        PieceJFX pieceF = initialisePieces(strPieceF,500,370);
        ImageView pieceFimage = initialisePieceView(pieceF);
        //Piece G
        PieceJFX pieceG = initialisePieces(strPieceG,320,450);
        ImageView pieceGimage = initialisePieceView(pieceG);
        //Piece H
        PieceJFX pieceH = initialisePieces(strPieceH,290,370);
        ImageView pieceHimage = initialisePieceView(pieceH);

        //Make lists of the object pieces vs the image pieces
        ArrayList<PieceJFX> piecesArray = new ArrayList<>();
        piecesArray.add(pieceA); piecesArray.add(pieceB);piecesArray.add(pieceC);piecesArray.add(pieceD);
        piecesArray.add(pieceE);piecesArray.add(pieceF);piecesArray.add(pieceG);piecesArray.add(pieceH);

        ArrayList<ImageView> imgpiecesArray = new ArrayList<>();
        imgpiecesArray.add(pieceAimage); imgpiecesArray.add(pieceBimage);imgpiecesArray.add(pieceCimage);
        imgpiecesArray.add(pieceDimage);imgpiecesArray.add(pieceEimage);imgpiecesArray.add(pieceFimage);
        imgpiecesArray.add(pieceGimage);imgpiecesArray.add(pieceHimage);

        pieces.getChildren().addAll(imgpiecesArray);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //add peg images to board
        String pegStr = start.getPlacement();
        setStartingPlacement(pegStr);

        //Show hint when forward slash key is pressed
        scene.setOnKeyPressed(eventForwardSlash ->{
            if(totalPiecePegStr != null && eventForwardSlash.getCode() == KeyCode.SLASH) {
                if (!prevHint){
                    String hint = getHint();
                    showHint(hint);
                    prevHint = true;
                }
            }
        });

        //Remove hint after key is released
        scene.setOnKeyReleased(eventForwardSlashRelease->{
            if(totalPiecePegStr != null && eventForwardSlashRelease.getCode() == KeyCode.SLASH) {
                pieces.getChildren().remove(lastHint);
                prevHint = false;
            }
        });

        // mouse operation
        scene.setOnMousePressed(mouseClick -> {

            //find which index/piece is clicked on
            double clickedPieceIndex = clickedOnPiece(imgpiecesArray, mouseClick.getX(), mouseClick.getY());

            //drag piece and place it on board
            if(clickedPieceIndex >= 0){
                changeClickBool(true);
                ImageView clickedPiece = imgpiecesArray.get((int) clickedPieceIndex);
                PieceJFX p = piecesArray.get((int) clickedPieceIndex);
                double w = clickedPiece.getFitWidth();
                double h = clickedPiece.getFitHeight();

                scene.setOnMouseDragged(eventDrag ->{
                    clickedPiece.setX(eventDrag.getX()-w/2);
                    clickedPiece.setY(eventDrag.getY()-h/2);
                });

                //rotate piece --> issues where rotated piece is offset
                scene.setOnScroll(event -> {
                    //update piece string
                    String current = p.getPieceStr();
                    String currOrient = current.substring(3);
                    String newRot = NumAfterRotation(currOrient);
                    String newStr = current.substring(0,3) + newRot;
                    p.changePieceStr(newStr);
                    p.orientation = Integer.parseInt(newRot);

                    int angle = 0;
                    //rotate image piece, also account for offset that rotating causes
                    switch (newRot){
                        case "0": case "6": angle = 0; p.changeOffset(false); break;
                        case "1": case "7": angle = 90; p.changeOffset(true); break;
                        case "2": case "4": angle = 180; p.changeOffset(false); break;
                        case "3": case "5": angle = 270; p.changeOffset(true); break;
                    }

                    clickedPiece.setRotationAxis(Rotate.Z_AXIS);
                    clickedPiece.setRotate(angle);

                });



                //flip piece --> button 'f'
                scene.setOnKeyTyped(eventFlip ->{
                    //rotate around y axis
                    if (eventFlip.getCharacter().equals("f")){
                        //update piece string
                        String oldOrient = p.pieceStr.substring(3);
                        String orient = newFlip(oldOrient);
                        String current = p.getPieceStr();
                        String newStr = current.substring(0,3) + orient;
                        p.changePieceStr(newStr);

                        //update image
                        clickedPiece.setRotationAxis(Rotate.Z_AXIS);
                        if(Integer.parseInt(oldOrient) < 4){
                            clickedPiece.setScaleX(-1);
                        }else{
                            clickedPiece.setScaleX(1);
                        }
                        switch (oldOrient){
                            case "1": case "7":clickedPiece.setRotate(270); break;
                            case "3": case "5":clickedPiece.setRotate(90); break;

                        }
                    }
                });


                //Send back to original place if it was not placed on the board
                scene.setOnMouseReleased(eventEndDrag ->{

                    //get the x and y positions of the circle on the board for which it would click onto

                    //need to account for rotation/flip changes
                    double newX;
                    double newY;

                    if(!p.offset) {
                        newX = getBoardCircleCol(eventEndDrag.getX() - w / 2);
                        newY = getBoardCircleRow(eventEndDrag.getY() - h / 2);
                    } else{
                        newX = getBoardCircleCol(eventEndDrag.getX() - h/2);
                        newY = getBoardCircleRow(eventEndDrag.getY() - w/2);
                    }

                    //create the placement string to test if each step is valid
                    //need to account for rotation/flip changes!
                    StringBuilder placement = new StringBuilder();
                    for(PieceJFX eachPiece: piecesArray){
                        String u = eachPiece.getPieceStr();
                        //for the particular piece p, get the string for which it would be if placed
                        if(eachPiece == p){
                            String current = p.getPieceStr();
                            //System.out.println(newY);
                            u = current.substring(0,1) +getBoardNumber(newX) +getBoardLetter(newY) +current.substring(3);
                        }
                        //don't include pieces that haven't been placed -> must be of length 4 (else weren't placed correctly)
                        if(u.length() == 4) {
                            if (!u.substring(1, 3).equals("9E")) {
                                placement.append(u);
                            }
                        }
                    }

                    System.out.println("placement: " +placement);
                    totalPiecePegStr = placement +pegStr;
                    TwistGame g = new TwistGame();
                    boolean isValid = TwistGame.isPlacementStringValid(placement +pegStr);
                    //above function counts an empty string as incorrect so statement below accounts for this
                    if(placement.toString().equals("")){
                        isValid = true;
                    }

                    if(((newX < 0 || newY < 0 || !isValid )&& clickBool)){
                        changeClickBool(false);
                        //send back to original position
                        clickedPiece.setX(p.initialX);
                        clickedPiece.setY(p.initialY);

                        //rotate to original position
                        p.changeOffset(false);
                        clickedPiece.setRotate(0);
                        clickedPiece.setScaleX(1);

                        //update piece object
                        String current = p.getPieceStr();
                        String newStr = current.substring(0,1) +"9E0"; // +current.substring(3);
                        p.changePieceStr(newStr);
                    }else if (clickBool){
                        changeClickBool(false);
                        //click into board place -> improve code accounting for offsets
                        if(!p.offset){
                            clickedPiece.setX(newX);
                            clickedPiece.setY(newY);
                        } else if (p.getPieceStr().substring(0,1).equals("e") || p.getPieceStr().substring(0,1).equals("g")){
                            clickedPiece.setX(newX);
                            clickedPiece.setY(newY);
                        } else if(p.getPieceStr().substring(0,1).equals("c")){
                            clickedPiece.setX(newX-SQUARE_SIZE*3/2);
                            clickedPiece.setY(newY+SQUARE_SIZE*3/2);
                        } else if(p.getPieceStr().substring(0,1).equals("h")){
                            clickedPiece.setX(newX-SQUARE_SIZE);
                            clickedPiece.setY(newY+SQUARE_SIZE);
                        } else {
                            clickedPiece.setX(newX-(SQUARE_SIZE/2));
                            clickedPiece.setY(newY+SQUARE_SIZE/2);
                        }

                        //update piece object
                        String current = p.getPieceStr();
                        String newStr = current.substring(0,1) +getBoardNumber(newX) +getBoardLetter(newY) +current.substring(3);
                        p.changePieceStr(newStr);

                        // check completion
                        if(placement.length() == 32){
                            completion(placement.toString(),stage);
                        }


                    }else{
                        changeClickBool(false);
                    }
                });
            }
        });

        stage.show();

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void start(Stage primaryStage) throws Exception {
        startGame(primaryStage);
    }

    // by Lauren Nelson-Lee
    public String NumAfterRotation(String letter){
        int intLet = Integer.parseInt(letter);
        if(intLet != 3 && intLet != 7){
            return Integer.toString(intLet + 1);
        } else if(intLet == 3){
            return "0";
        } else{
            return "4";
        }

    }


    //New string number after flipped in y axis by Lauren Nelson-Lee
    public String newFlip(String initial){
        switch (initial) {
            case "3":
                return "7";
            case "7":
                return "3";
            default:
                return Integer.toString(6 - Integer.parseInt(initial));
        }
    }

    //Find which piece was clicked on
    private double clickedOnPiece(ArrayList<ImageView> piecesArray, double xpos, double ypos){
        //iterate through each piece and see if it was the one clicked on
        for (int o = 0; o < piecesArray.size(); o++){
            double pXpos = piecesArray.get(o).getX();
            double pYpos = piecesArray.get(o).getY();

            if (xpos > pXpos && xpos < (pXpos + piecesArray.get(o).getFitWidth()) &&
                    ypos > pYpos && ypos < (pYpos + piecesArray.get(o).getFitHeight())){
                return o;
            }
        }
        return -1;
    }

    //Get x position of top left of circle on board, by Lauren Nelson-Lee
    public double getBoardCircleCol(double xpos){

        if (xpos > 60 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE-SQUARE_SIZE/2) {
            //column 1
            return 60;
        } else if (xpos >= 60 -SQUARE_SIZE/2 + SQUARE_SIZE && xpos < 60 + SQUARE_SIZE * 2-SQUARE_SIZE/2) {
            //column 2
            return 60 + SQUARE_SIZE;
        } else if (xpos >= 60 + SQUARE_SIZE * 2 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE * 3 -SQUARE_SIZE/2) {
            //column 3
            return 60 + SQUARE_SIZE*2;
        } else if (xpos >= 60 + SQUARE_SIZE * 3 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE * 4-SQUARE_SIZE/2) {
            //column 4
            return 60 + SQUARE_SIZE*3;
        } else if (xpos >= 60 + SQUARE_SIZE * 4 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE * 5-SQUARE_SIZE/2) {
            //column 5
            return 60 + SQUARE_SIZE*4;
        } else if (xpos >= 60 + SQUARE_SIZE * 5 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE * 6-SQUARE_SIZE/2) {
            //column 6
            return 60 + SQUARE_SIZE*5;
        } else if (xpos >= 60 + SQUARE_SIZE * 6 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE * 7-SQUARE_SIZE/2) {
            //column 7
            return 60 + SQUARE_SIZE*6;
        } else if (xpos >= 60 + SQUARE_SIZE * 7 -SQUARE_SIZE/2 && xpos < 60 + SQUARE_SIZE * 8-SQUARE_SIZE/2) {
            //column 8
            return 60 + SQUARE_SIZE*7;
        } else {
            return -1;
        }
    }

    //Get y position of top left of circle on board by Lauren Nelson-Lee
    public double getBoardCircleRow(double ypos){
        if(ypos < 298 -SQUARE_SIZE/2 && ypos > 298 - SQUARE_SIZE -SQUARE_SIZE/2){
            //row 4
            return 298 - SQUARE_SIZE;
        }else if(ypos <= 298 - SQUARE_SIZE -SQUARE_SIZE/2 && ypos > 298 - SQUARE_SIZE*2 -SQUARE_SIZE/2){
            //row 3
            return 298 - SQUARE_SIZE*2;
        }else if(ypos <= 298 - SQUARE_SIZE*2 -SQUARE_SIZE/2 && ypos > 298 - SQUARE_SIZE*3 -SQUARE_SIZE/2){
            //row 2
            return 298 - SQUARE_SIZE*3;
        }else if(ypos <= 298 - SQUARE_SIZE*3 -SQUARE_SIZE/2 && ypos > 298 - SQUARE_SIZE*4 -SQUARE_SIZE/2){
            //row 1
            return 298 - SQUARE_SIZE*4;
        }else{
            return -1;
        }
    }

    //Get x position of top left of circle on board given board circle number, by Lauren Nelson-Lee
    public double getBoardCircleColX(String column){
        return 60 + SQUARE_SIZE*(Double.parseDouble(column)-1)-SQUARE_SIZE/3;
    }

    //Get y position of top left of circle on board given row letter, by Lauren Nelson-Lee
    public double getBoardCircleRowY(String row){
        switch (row) {
            case "D":
                //row 4
                return 298 - SQUARE_SIZE - SQUARE_SIZE / 3;
            case "C":
                //row 3
                return 298 - SQUARE_SIZE * 2 - SQUARE_SIZE / 3;
            case "B":
                //row 2
                return 298 - SQUARE_SIZE * 3 - SQUARE_SIZE / 3;
            case "A":
                //row 1
                return 298 - SQUARE_SIZE * 4 - SQUARE_SIZE / 3;
            default:
                return -1;
        }
    }

    // gets board letter from ypos by Lauren Nelson-Lee
    public String getBoardLetter(double ypos){
        switch((int) ypos){
            case 298 - SQUARE_SIZE: return "D";
            case 298 - SQUARE_SIZE*2: return "C";
            case 298 - SQUARE_SIZE*3: return "B";
            case 298 - SQUARE_SIZE*4: return "A";
        }
        return "";
    }

    //by Lauren Nelson-Lee
    public String getBoardNumber(double xpos){
        switch((int) xpos){
            case 60: return "1";
            case 60 + SQUARE_SIZE: return "2";
            case 60 + SQUARE_SIZE*2: return "3";
            case 60 + SQUARE_SIZE*3: return "4";
            case 60 + SQUARE_SIZE*4: return "5";
            case 60 + SQUARE_SIZE*5: return "6";
            case 60 + SQUARE_SIZE*6: return "7";
            case 60 + SQUARE_SIZE*7: return "8";
        }
        return "";
    }

    // the congratulation sign when the current game complete successfully by Zhewen Li
    private void completion(String placement, Stage stage){
        StringBuilder checkChar = new StringBuilder();
        for(int i=0;i+4<=placement.length();i+=4){
            checkChar.append(placement.charAt(i));
        }
        System.out.println(checkChar);
        if(checkChar.toString().contentEquals("abcdefgh")){
            insWindow();
            Text text = new Text(5*SQUARE_SIZE, 4*SQUARE_SIZE,"Well Done ! ");
            text.setFont(Font.font("Berlin Sans FB Demi", 50));
            text.setFill(Color.rgb(161,97,105));

            // the gif graphic (source and licence are listed in the originality)
            Image img = new Image(Board.class.getResource(URI_BASE+"welldone.gif").toString());
            ImageView pic = new ImageView();
            pic.setImage(img);
            pic.setPreserveRatio(true);
            pic.setSmooth(true);

            pic.setX(4.8*SQUARE_SIZE);
            pic.setY(4.8*SQUARE_SIZE);

            Button a = new Button("Continue");
            a.setOnAction(event -> {
                resetAll(stage);
                instructions.getChildren().clear();
                reset.getChildren().clear();
            });
            a.setLayoutX(9*SQUARE_SIZE);
            a.setLayoutY(9*SQUARE_SIZE);

            instructions.getChildren().addAll(a);
            instructions.getChildren().addAll(pic);
            instructions.getChildren().addAll(text);


        }
    }

    private void changeClickBool(Boolean bool){
        clickBool = bool;
    }


}
