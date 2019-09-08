Reviewer: Saffron Bannister (u6062525)
Component: Hints (current state of all below):
 getHint (Board, 385-417) ,
 hintButton (
 showHint (
 getting hint execution (Board, 696-700, within mouseRelease event)
Author: Zhewen Li (u6437091)

Review Comments:

1. Please comment your code!
2. When a placement without results is placed, the following exception occurs
    Exception in thread "JavaFX Application Thread" java.lang.IllegalArgumentException: bound must be positive
    	at java.util.Random.nextInt(Random.java:388)
    	at comp1110.ass2.gui.Board.getHint(Board.java:409)
    	at comp1110.ass2.gui.Board.lambda$null$3(Board.java:701)
    	at com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:86)
    	at com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:238)
    	at com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
    	at com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
    	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
    	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
    	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
    	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
    	at com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
    	at com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:54)
    	at javafx.event.Event.fireEvent(Event.java:198)
    	at javafx.scene.Scene$MouseHandler.process(Scene.java:3757)
    	at javafx.scene.Scene$MouseHandler.access$1500(Scene.java:3485)
    	at javafx.scene.Scene.impl_processMouseEvent(Scene.java:1762)
    	at javafx.scene.Scene$ScenePeerListener.mouseEvent(Scene.java:2494)
    	at com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:352)
    	at com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:275)
    	at java.security.AccessController.doPrivileged(Native Method)
    	at com.sun.javafx.tk.quantum.GlassViewEventHandler.lambda$handleMouseEvent$300(GlassViewEventHandler.java:388)
    	at com.sun.javafx.tk.quantum.QuantumToolkit.runWithoutRenderLock(QuantumToolkit.java:389)
    	at com.sun.javafx.tk.quantum.GlassViewEventHandler.handleMouseEvent(GlassViewEventHandler.java:387)
    	at com.sun.glass.ui.View.handleMouseEvent(View.java:555)
    	at com.sun.glass.ui.View.notifyMouse(View.java:937)
    	at com.sun.glass.ui.gtk.GtkApplication._runLoop(Native Method)
    	at com.sun.glass.ui.gtk.GtkApplication.lambda$null$450(GtkApplication.java:139)
    	at java.lang.Thread.run(Thread.java:748)
This seems to be an in issue with empty ArrayLists not equating to "null" (since they've been instantiated).
I would try to fix by replacing
        if (b2 != null) {
        //do thing
        }
    with:
        if (b2.size() > 0) {
        //do thing
        }
3. I don't think giving a player hints only after they have placed 4 tiles correctly is helpful to the player, but
I can see why with our current setup it's the only option. This does mean, though, that once InitializeBoard was
working properly you'd have to entirely change these
4. I would change the name of hintButton to be showHintButton


    // get hint after placing 4 pieces
    private String getHint(String placement, String pegStr){
        Set<String> solution = new HashSet<>();
        solution.addAll(Solutions.removeDupes(Solutions.solutionList(placement+pegStr)));

        String str ="";
        for(String s: solution){
            str += s;
        }

        String[] a1 = splitIntoFours(placement);
        String[] a2 = splitIntoFours(str);

        ArrayList<String> b1 = new ArrayList<>();
        ArrayList<String> b2 = new ArrayList<>();
        for(String a:a1){
            b1.add(a);
        }
        for(String a:a2){
            b2.add(a);
        }

        b2.removeAll(b1);
        Random rand = new Random();
        if (b2 != null) {
            String show = b2.get(rand.nextInt(b2.size()));
            System.out.println(b2);
            return show;
        }
        else{
            return null;
        }

    }


    // hint button
    private void hintButton(String placement){

        Button hint = new Button("Hint");
        hint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showHint(placement);
            }
        });
        hint.setLayoutX(VIEWER_WIDTH+220);
        hint.setLayoutY(VIEWER_HEIGHT/6+SQUARE_SIZE);
        reset.getChildren().add(hint);

    }

    // EventHandler of hint button
    private void showHint(String hint){
        drawOnBoard(hint);
    }

    private void startGame(Stage stage){
    (...)
                scene.setOnMouseReleased(eventEndDrag ->{
    (...)
                        // show hint after placing 4 pieces
                        if(placement.length() >= 16 && placement.length()<32){
                            String hint = getHint(placement,pegStr);
                            hintButton(hint);
                        }
