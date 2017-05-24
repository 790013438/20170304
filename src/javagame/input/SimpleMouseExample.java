package javagame.input;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.SimpleMouseInput;
import javagame.util.FrameRate;

public class SimpleMouseExample extends JFrame implements Runnable{
    
    private KeyboardInput keyboardInputBoolean;
    private SimpleMouseInput simpleMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate; 
    private boolean drawingLineBoolean;
    private ArrayList<Point>pointArrayList=new ArrayList<Point>();
    private int colorIndex;
    private Color[] COLORSArray={Color.BLACK,Color.GREEN,Color.YELLOW,Color.BLUE};
    
    public SimpleMouseExample(){
        frameRate=new FrameRate();
    }
    
    public static void main(String[]args){
        final SimpleMouseExample simpleMouseExample=new SimpleMouseExample();
        simpleMouseExample.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
//                22Processing exit
//                simpleMouseExample.onWindowClosing();
//                333
            }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//                Create graphics
                simpleMouseExample.createAndShowGUI();
            }
        });
    }
    protected void onWindowClosing(){
       try{           
           runningVolatileBoolean=false;
           gameThread.join();
       }catch(Exception e){
           System.out.println(e);
       }
       System.exit(0);
    }
    protected void createAndShowGUI(){
        Canvas canvas=new Canvas();
        canvas.setSize(640,480);
        canvas.setBackground(Color.WHITE);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Simple Mouse Example");
        setIgnoreRepaint(true);
        /**
         * Causes this Window to be sized to fit the preferred size
         * and layouts of its subcomponents. The resulting width and
         * height of the window are automatically enlarged if either
         * of dimensions is less than the minimum size as specified
         * by the previous call to the {@code setMinimumSize} method.
         * <p>
         * If the window and/or its owner are not display yet,
         * both of them are made display before calculating
         * the preferred size. The Window is validated after its
         * size is being calculated.
         *
         * @see Component#isDisplayable
         * @see #setMinimumSize
         */
        pack();
//        add key listener
        /**
         * When the application first starts up,it will not process keyboardInputBoolean 
         * events if they have not been added to the JFrame.Once the canvas object receives 
         * focus,it will receive keyboardInputBoolean input,but until it is selected the keyboardInputBoolean input is 
         * received only by the JFrame.
         */
        keyboardInputBoolean=new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
//        add relativeMouseInputBoolean listener
        simpleMouseInputBoolean=new SimpleMouseInput();
        canvas.addMouseListener(simpleMouseInputBoolean);
        canvas.addMouseMotionListener(simpleMouseInputBoolean);
        canvas.addMouseWheelListener(simpleMouseInputBoolean);
        
        setVisible(true);
        canvas.createBufferStrategy(2);
        /**
         * The <code>BufferStrategy</code> class represents the mechanism with which
         * to organize complex memory on a particular <code>Canvas</code> or
         * <code>Window</code>.  Hardware and software limitations determine whether and
         * how a particular buffer strategy can be implemented.  These limitations
         * are detectable through the capabilities of the
         * <code>GraphicsConfiguration</code> used when creating the
         * <code>Canvas</code> or <code>Window</code>.
         * <p>
         */
        bufferStrategy=canvas.getBufferStrategy();
        /**
         * Once the canvas object receives focus,it will receive keyboardInputBoolean input,
         * but until it is selected the keyboardInputBoolean input is received only by the JFrame.
         */
        canvas.requestFocus();
//      线程处理行为，逻辑
        gameThread=new Thread(this);
        gameThread.start();
    }
    public void run(){
        runningVolatileBoolean=true;
//        相对比较的帧
        frameRate.initialize();
        while(runningVolatileBoolean){ 
            gameLoop();
        }
    }
    public void gameLoop(){
        processInput();
        renderFrame();
        sleep(10L);
    }
    /**
     * In the game loop,the following method call has been added.
     * Inside this method,both the keyboardInputBoolean and relativeMouseInputBoolean are polled to make their data available.
     * When the relativeMouseInputBoolean button is first pressed,the drawing flag is set.     
     */
    private void processInput(){
        keyboardInputBoolean.poll();
        simpleMouseInputBoolean.poll();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)){
            System.out.println("VK_SPACE");
        }
//        if button pressed for first time,
//        start drawing pointArrayList
        /**
         * For each frame that the left relativeMouseInputBoolean button is down,
         * a now point is added to the line's data structure.
         * When the button is released,the flag is cleared,and a null object is added to the list,
         * marking the end of the line.
         * This way,the data structure can hold all the pointArrayList at the same time.
         */
        if(simpleMouseInputBoolean.buttonDownOnce(MouseEvent.BUTTON1)){
            drawingLineBoolean=true;
        }
//        if the button is down,and line point
        if(simpleMouseInputBoolean.buttonDown(MouseEvent.BUTTON1)){
            pointArrayList.add(simpleMouseInputBoolean.getPosition());
//            if( the button is not down but we were drawing,
//            add a null to break up the lines
        }else if(drawingLineBoolean){
            pointArrayList.add(null);
            drawingLineBoolean=false;
        }
        /**
         * When the C key is pressed,the lines are cleared from the data structure.
         * This lets the users start over if they are lacking artistic skills(like me)!
         */
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_C)){
            pointArrayList.clear();
        }
    }
    /**
     * 缓存技术
     * The <code>BufferStrategy</code> class represents the mechanism with which
     * to organize complex memory on a particular <code>Canvas</code> or
     * <code>Window</code>.  Hardware and software limitations determine whether and
     * how a particular buffer strategy can be implemented.  These limitations
     * are detectable through the capabilities of the
     * <code>GraphicsConfiguration</code> used when creating the
     * <code>Canvas</code> or <code>Window</code>.
     * <p>
     * It is worth noting that the terms <i>buffer</i> and <i>surface</i> are meant
     * to be synonymous: an area of contiguous memory, either in video device
     * memory or in system memory.
     * <p>
     * There are several types of complex buffer strategies, including
     * sequential ring buffering and blit buffering.
     * Sequential ring buffering (i.e., double or triple
     * buffering) is the most common; an application draws to a single <i>back
     * buffer</i> and then moves the contents to the front (display) in a single
     * step, either by copying the data or moving the video pointer.
     * Moving the video pointer exchanges the buffers so that the first buffer
     * drawn becomes the <i>front buffer</i>, or what is currently displayed on the
     * device; this is called <i>page flipping</i>.
     * <p>
     * Alternatively, the contents of the back buffer can be copied, or
     * <i>blitted</i> forward in a chain instead of moving the video pointer.
     * <pre>{@code
     * Double buffering:
     *
     *                    ***********         ***********
     *                    *         * ------> *         *
     * [To display] <---- * Front B *   Show  * Back B. * <---- Rendering
     *                    *         * <------ *         *
     *                    ***********         ***********
     *
     * Triple buffering:
     *
     * [To      ***********         ***********        ***********
     * display] *         * --------+---------+------> *         *
     *    <---- * Front B *   Show  * Mid. B. *        * Back B. * <---- Rendering
     *          *         * <------ *         * <----- *         *
     *          ***********         ***********        ***********
     *
     * }</pre>
     * <p>
     * Here is an example of how buffer strategies can be created and used:
     * <pre><code>
     *
     * // Check the capabilities of the GraphicsConfiguration
     * ...
     *
     * // Create our component
     * Window w = new Window(gc);
     *
     * // Show our window
     * w.setVisible(true);
     *
     * // Create a general double-buffering strategy
     * w.createBufferStrategy(2);
     * BufferStrategy strategy = w.getBufferStrategy();
     *
     * // Main loop
     * while (!done) {
     *     // Prepare for rendering the next frame
     *     // ...
     *
     *     // Render single frame
     *     do {
     *         // The following loop ensures that the contents of the drawing buffer
     *         // are consistent in case the underlying surface was recreated
     *         do {
     *             // Get a new graphics context every time through the loop
     *             // to make sure the strategy is validated
     *             Graphics graphics = strategy.getDrawGraphics();
     *
     *             // Render to graphics
     *             // ...
     *
     *             // Dispose the graphics
     *             graphics.dispose();
     *
     *             // Repeat the rendering if the drawing buffer contents
     *             // were restored
     *         } while (strategy.contentsRestored());
     *
     *         // Display the buffer
     *         strategy.show();
     *
     *         // Repeat the rendering if the drawing buffer was lost
     *     } while (strategy.contentsLost());
     * }
     *
     * // Dispose the window
     * w.setVisible(false);
     * w.dispose();
     * </code></pre>
     *
     * @see java.awt.Window
     * @see java.awt.Canvas
     * @see java.awt.GraphicsConfiguration
     * @see VolatileImage
     * @author Michael Martak
     * @since 1.4
     */
    private void renderFrame(){
        do{            
            do{        
                Graphics g=null;
                try{                
                    g=bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
//                  具体图形相应行为，然后逻辑处理
                    render(g);
                }catch(Exception e){
                    System.out.println(e);
                }finally{
                    if(g!=null){
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());
            bufferStrategy.show();
        }while(bufferStrategy.contentsLost());
    }
    private void render(Graphics g){
        /**
         * The % operator will keep the value between (–3, 3). 
         * Because the modulus operation of a negative value is either zero or a negative value, 
         * the absolute value is used to keep the array index between (0, size –1).
         */
        colorIndex+=simpleMouseInputBoolean.getNotches();
        Color color=COLORSArray[Math.abs(colorIndex%COLORSArray.length)];
        g.setColor(color);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 30, 30);
        g.drawString("Use relativeMouseInputBoolean to draw lines", 30, 45);
        g.drawString("Press C to clear lines", 30, 60);
        g.drawString("Mouse Wheel cycles colors", 30, 75);
        g.drawString(simpleMouseInputBoolean.getPosition().toString(), 30, 90);
        /**
         * Finally, the lines are drawn. Because null values are inserted into the data structure, 
         * code is added to make sure lines are drawn only if neither point is null.
         */
        for(int i=0;i<pointArrayList.size()-1;++i){
            Point p1=pointArrayList.get(i);
            Point p2=pointArrayList.get(i+1);
//            adding a null into the list is used
//            for breaking up the lines when
//            there are two or more lines
//            that are not connected
            if(!(p1==null||p2==null)){
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
    private void sleep(long sleepLong){
        try{
            Thread.sleep(sleepLong);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
