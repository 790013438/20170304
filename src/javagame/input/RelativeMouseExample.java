package javagame.input;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.RelativeMouseInput;
import javagame.util.KeyboardInput;
import javagame.util.FrameRate;

public class RelativeMouseExample extends JFrame implements Runnable{
    
    private Canvas canvas;
    private RelativeMouseInput relativeMouseInputBoolean;
    private KeyboardInput keyboardInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private Point point = new Point(0,0);
    private boolean disableCursorBoolean = false;
    
    public RelativeMouseExample(){
        frameRate = new FrameRate();
    }

    public static void main(String[]args){
        final RelativeMouseExample relativeMouseExample=new RelativeMouseExample();
        relativeMouseExample.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
//            processing exit
                relativeMouseExample.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//              Create graphics
                relativeMouseExample.createAndShowGUI();
            }
        });
    }
    protected void onWindowClosing(){
        try{            
            runningVolatileBoolean = false;
            gameThread.join();
        }catch(Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }
    protected void createAndShowGUI(){
        canvas = new Canvas();
        canvas.setSize(640,480);
        canvas.setBackground(Color.WHITE);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Relative Mouse Example");
        /**
         * Because the render loop does all the painting for this application, 
         * the setIgnoreRepaint() flag can be set on the JFrame.
         */
        setIgnoreRepaint(true);
        pack();
//        Add key listeners
        keyboardInputBoolean=new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
//        Add mouse listener
        relativeMouseInputBoolean=new RelativeMouseInput(canvas);
        canvas.addMouseListener(relativeMouseInputBoolean);
        canvas.addMouseMotionListener(relativeMouseInputBoolean);
        canvas.addMouseWheelListener(relativeMouseInputBoolean);
        setVisible(true);
        /**
         * The window class and the Canvas class are the two classes 
         * available to allow the creation of a BufferStrategy.
         * By adding the canvas,
         * you can access the buffer strategy and force the size of the canvas to exactly the size required,
         * just like the JPanel example in he Hello World application.
         */
        canvas.createBufferStrategy(2);
        bufferStrategy=canvas.getBufferStrategy();
        /**
         * Once the canvas object receives focus,it will receive keyboard input,
         * but until it is selected the keyboard input is received only by the JFrame.
         */
        canvas.requestFocus();
//      线程处理行为，逻辑
        gameThread=new Thread(this);
        gameThread.start();
    }
    public void run(){
        runningVolatileBoolean=true;
        frameRate.initialize();
        while(runningVolatileBoolean){
            gameLoop();
        }
    }
    /**
     * The game loop now performs rendering as discussed in the previous section. 
     */
    private void gameLoop(){
        processInput();
        renderFrame();
        sleep(10L);
    }

    private void processInput(){   
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
        Point p = relativeMouseInputBoolean.getPosition();
        if(relativeMouseInputBoolean.isRelative()){
            point.x += p.x;
            point.y += p.y;
        } else {
            point.x = p.x;
            point.y = p.y;
        }
//        wrap rectangle around the screen
        if(point.x+25 < 0){
            point.x = canvas.getWidth() - 1;
        }else if( point.x > canvas.getWidth() - 1){
            point.x = -25;
        }
        if( point.y + 25 < 0 ){
            point.y = canvas.getHeight() - 1;
        }else if( point.y > canvas.getHeight() - 1 ){
            point.y = -25;
        }
        
//      Toggle relative
        if(keyboardInputBoolean.keyDownOnce( KeyEvent.VK_SPACE )){
            relativeMouseInputBoolean.setRelative( !relativeMouseInputBoolean.isRelative() );
        }
        
//        Toggle cursor
        if(keyboardInputBoolean.keyDownOnce( KeyEvent.VK_C )){
            disableCursorBoolean = !disableCursorBoolean;
            if(disableCursorBoolean){
                disableCursor();
            } else {
//                setCoursor( Cursor.DEFAULT_CURSOR ) is deprecated
                setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
            }
        }
    }
    
    private void disableCursor(){
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Image image = toolKit.createImage("");
        Point point = new Point(0,0);
        String name = "CanBeAnything";
        Cursor cursor = toolKit.createCustomCursor(image, point, name);
        setCursor(cursor);
    }
    
    /**Using the getDrawGraphics(),contentsLost(),show(),and dispose() methods,
     * the render loop controls the application painting.
     * Once the graphics object is available,
     * and the previous screen has been cleared,the scene can be rendered.
     * By using a BufferStrategy class,it doesn't matter if the program is full screen and using page flipping,
     * or if it is windowed and using double buffering-it is handled behind the scenes.
     */
    private void renderFrame(){ 
        do{   
            do{
                /**
                 * To use the BufferStrategy in a render loop,
                 * a graphics object is created using the getDrawGraphics() method.
                 * This graphics object will draw to the off-screen surface.
                 * Once the graphics object is available,it is used exactly like the graphics 
                 * object passed in the paint() method of the JPanel class.
                 */
                Graphics g = null;
                /**
                 * Notice that the code is wrapped in a try/finally block.
                 */
                try{                
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    /**
                     * Once the graphics object is available,it is used exactly like the graphics 
                     * object passed in the paint() method of the JPanel class.
                     */
//                  具体图形相应行为，然后逻辑处理
                    render(g);
                }catch(Exception e){
                    System.out.println(e);
                }finally{
                    /**
                     * Unlike other painting code,because this graphics object has been created,
                     * it must be disposed of when the render loop is finished.
                     * Not calling the Graphics.dispose() method can cause a memory leak and crash the program.
                     */
                    if(g != null){
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());
            /**
             * The show() method performs either double-buffering/image copying or page flapping/pointer 
             * swapping to display the image.
             */
            bufferStrategy.show();
            /**
             * It is important to call contentsLost() to make sure the off-screen surface is available.
             * Some operating systems let users AltTab away from full-screen applications,
             * causing the off-screen image to be unavailable.
             */
        }while(bufferStrategy.contentsLost());
    }
    
    private void render(Graphics g){   
        g.setColor(Color.BLACK);
        frameRate.calculate();
        g.drawString(relativeMouseInputBoolean.getPosition().toString(),20,20);
        g.drawString("Relative: "+relativeMouseInputBoolean.isRelative(),20, 35);
        g.drawString("Press Space to switch mouse modes", 20, 50);
        g.drawString("Press C to toggle cursor",20,65);
        g.setColor(Color.BLACK);
        g.drawRect(point.x, point.y, 25, 25);
    }
    
    private void sleep(long sleep){
        try{            
            Thread.sleep(sleep);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}