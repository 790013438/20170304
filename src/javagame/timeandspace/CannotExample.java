package javagame.timeandspace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;

public class CannotExample extends JFrame implements Runnable {
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;

    public static void main(String[] args) {
        final CannotExample cannotExample = new CannotExample();
        cannotExample.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //processing exit
                cannotExample.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Create graphics
                cannotExample.createAndShowGUI();
//                233
            }
        });
    }

//  processing exit
    protected void onWindowClosing() {
        try {
            runningVolatileBoolean = false;
            gameThread.join();
        } catch ( Exception e ) {
            System.out.println(e);
        }
        System.exit(0);
    }

//  设置背景
    protected void createAndShowGUI() {
        Canvas canvas = new Canvas();
        canvas.setSize(640, 480);
        canvas.setBackground(Color.WHITE);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Cannon Exxample");
        /**
         * Because the render loop does all the painting for this application, 
         * the setIgnoreRepaint() flag can be set on the JFrame.
         */
        setIgnoreRepaint(true);
        /**
         * Causes this Window to be sized to fit the preferred size
         * and layouts of its subcomponents. The resulting width and
         * height of the window are automatically enlarged if either
         * of dimensions is less than the minimum size as specified
         * by the previous call to the {@code setMinimumSize} method.
         * <p>
         * If the window and/or its owner are not displayable yet,
         * both of them are made displayable before calculating
         * the preferred size. The Window is validated after its
         * size is being calculated.
         */
        pack();
        //add key Listener
        keyboardInputBoolean = new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
        //add mouse listener,mouse motion listener,mouse wheel listener
        relativeMouseInputBoolean = new RelativeMouseInput(canvas);
        canvas.addMouseListener(relativeMouseInputBoolean);
        canvas.addMouseMotionListener(relativeMouseInputBoolean);
        canvas.addMouseWheelListener(relativeMouseInputBoolean);
        setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        /**
         * The window class and the Canvas class are the two classes 
         * available to allow the creation of a BufferStrategy.
         * By adding the canvas,
         * you can access the buffer strategy and force the size of the canvas to exactly the size required,
         * just like the JPanel example in he Hello World application.
         */
        canvas.requestFocus();
        gameThread new Thread();
        gameThread.start();
    }

    public void run() {
        runningVolatileBoolean = true;
        //initialize();
        //233
        long curTimeLong = System.nanoTime();
        long lastTimeLong =curTimeLong;
        double nsPerFrame;
        while( runningVolatileBoolean ) {
            curTime = System.nanoTime();
            nsPerFrame = curTime - lastTime;
            /*
             * Notice that 1 second equals 1.0E9 nanoseconds.
             * Dividing the elapsed time by the number of nanoseconds per second produces the elapsed time per second.
             * Originally, my code used milliseconds,but my processor was so fast that there wasn't enought precision,and nothing moved.
             * Althought it may work on a slower computer,stick with the nanoseconds.
             */
            //gameLoop(nsPerFrame / 1.0E9 );
            //233
            lastTimeLong = curTimeLong;
        }
    }

    private void initialize() {
        //frameRate = new FrameRate();
        //233
    }

    private void gameLoop(double delta) {
        //processInput(delta);
        //updateObjects(delta);
        //renderFrame();
        //sleep();
        //233
    }
    
}
