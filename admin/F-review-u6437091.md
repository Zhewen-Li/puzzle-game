Reviewer: Zhewen Li(u6437091)
Component: Mouse movement(current statw of all below):
 scene.setOnMousePressed
 scene.setOnMouseDragged
 scene.setOnScroll
 scene.setOnKeyTyped
 scene.setOnMouseReleased
 
Author: Lauren Nelson-Lee (u6378754)

Review Comments:

1. Best feature: well handled the mouse movement, making the game workable.
Board.java:543,
Board.java:556, 
Board.java:616.

2. Comments for the code were very detailed.

3. Suggestions: Maybe these mouse operations can be placed in seperated methods, currently all in startgame(Board.java: 474),
so it will be more convenient to call them in other uses.

4. When I run the Board.java and try to place one piece on the displayed board, the piece may go back to its original spot if I randomly click on the 
empty space. 

    scene.setOnMousePressed(event2 -> {
    
                //find which index/piece is clicked on
                double clickedPieceIndex = clickedOnPiece(imgpiecesArray, event2.getX(), event2.getY());
                
                ...
                
    scene.setOnMouseReleased(eventEndDrag ->{
    
                        //get the x and y positions of the circle on the board for which it would click onto
    
                        //need to account for rotation/flip changes!
                        double newX;
                        double newY;
    
                        if(!p.offset) {
                            newX = getBoardCircleCol(eventEndDrag.getX() - w / 2);
                            newY = getBoardCircleRow(eventEndDrag.getY() - h / 2);
                        } else{
                            newX = getBoardCircleCol(eventEndDrag.getX() - h/2);
                            newY = getBoardCircleRow(eventEndDrag.getY() - w/2);
                        }

    So, it may be better to fix the placing position of the piece if it is not clicked the second time, (maybe set a default value for click, and do 
    some if-else conditions) in case that the pieces return often. 
  
