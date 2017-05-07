package javagame.timeandspace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;

public class ScreenMappingExample extends JFrame implements Runnable {
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;

    public static void main(String[] args) {
        final ScreenMappingExample screenMappingExample = new ScreenMappingExample();
        screenMappingExample.addWindowListener(new WindowAdapter() {
            public void windowClosing() {
//          processing exit
                screenMappingExample.onWinowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
//            2
//              Create graphics
                screenMappingExample.createAndShowGUI();
//           233 
            }
        });
    }
    
    public void onWinowClosing() {
        try{    
            runningVolatileBoolean = false;
            gameThread.join();
        } catch (Exception e) {
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
        setTitle("Screen Mapping Example");
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
//      Add key listeners
        keyboardInputBoolean = new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
//      Add mouse listener
//      For full screen: mouse =new RelativeMouseInput(this);
        relativeMouseInputBoolean = new RelativeMouseInput(canvas);
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
        bufferStrategy = canvas.getBufferStrategy();
        /**
         * Once the canvas object receives focus,it will receive keyboard input,
         * but until it is selected the keyboard input is received only by the JFrame.
         */
        canvas.requestFocus();
//      线程处理行为,设置画
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void run() {
        runningVolatileBoolean = true;
//        2
//        initialize();
//        233
        long curTime = System.nanoTime();
        long lastTime = curTime;
        double nsPerFrame;
        while( runningVolatileBoolean ) {
            curTime = System.nanoTime();
            nsPerFrame = curTime - lastTime;
//            2
//            gameLoop(nsPerFrame / 1.0E9);
//            233
            lastTime = curTime;
        }
    }
    
    private void initialize() {
        
    }
    
    private void gameLoop() {
        
    }
    
}
