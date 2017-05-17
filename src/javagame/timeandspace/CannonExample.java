package javagame.timeandspace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.Matrix3x3f;
import javagame.util.RelativeMouseInput;
import javagame.util.FrameRate;
import javagame.util.Vector2f;

public class CannonExample extends JFrame implements Runnable {
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private Vector2f velocityVector2f;
    private float cannonRotFloat, cannonDeltaFloat;
    private Vector2f[] cannonVector2fArray;
    private Vector2f[] cannonCpy;
    private Vector2f bullet;
    private Vector2f bulletCpy;
    private Canvas canvas;

    public static void main(String[] args) {
        final CannonExample cannotExample = new CannonExample();
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
        canvas = new Canvas();
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
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        runningVolatileBoolean = true;
        initialize();
        long curTimeLong = System.nanoTime();
        long lastTimeLong =curTimeLong;
        double nsPerFrame;
        while( runningVolatileBoolean ) {
            curTimeLong = System.nanoTime();
            nsPerFrame = curTimeLong - lastTimeLong;
            /*
             * Notice that 1 second equals 1.0E9 nanoseconds.
             * Dividing the elapsed time by the number of nanoseconds per second produces the elapsed time per second.
             * Originally, my code used milliseconds,but my processor was so fast that there wasn't enought precision,and nothing moved.
             * Although it may work on a slower computer,stick with the nanoseconds.
             */
            gameLoop(nsPerFrame / 1.0E9 );
            lastTimeLong = curTimeLong;
        }
    }

    private void initialize() {
        frameRate = new FrameRate();
        frameRate.initialize();
        velocityVector2f = new Vector2f();
        cannonRotFloat = 0.0f;
        cannonDeltaFloat = (float)Math.toRadians(90.0);
        cannonVector2fArray = new Vector2f[]{ new Vector2f(-0.5f, 0.125f), //top-left
                    new Vector2f(0.5f, 0.125f), //top-right
                    new Vector2f(0.5f, -0.125f), //bottom-right
                    new Vector2f(-0.5f, -0.125f)
        };
        cannonCpy = new Vector2f[cannonVector2fArray.length];
        Matrix3x3f scale = Matrix3x3f.scale(0.75f, 0.75f);
        for(int i = 0; i < cannonVector2fArray.length; ++i ) {
            cannonVector2fArray[i] = scale.mul(cannonVector2fArray[i]);
        }
    }

    private void gameLoop(double delta) {
        processInput(delta);
        updateObjects(delta);
        renderFrame();
        sleep(10L);
    }
    
    private void processInput(double delta) {
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_A)) {
            cannonRotFloat +=cannonDeltaFloat * delta;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_D)) {
            cannonRotFloat -= cannonDeltaFloat * delta;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)) {
            //new velocity
            Matrix3x3f mat = Matrix3x3f.translate(7.0f, 0.0f);
            mat = mat.mul(Matrix3x3f.rotate(cannonRotFloat));
            velocityVector2f = mat.mul(new Vector2f());
            //place bullet at cannon end
            mat = Matrix3x3f.translate(.375f, 0.0f);
            mat = mat.mul(Matrix3x3f.rotate(cannonRotFloat));
            mat = mat.mul(Matrix3x3f.translate(-2.0f, -2.0f));
            bullet = mat.mul(new Vector2f());
        }
    }

    private void updateObjects(double delta) {
        Matrix3x3f mat = Matrix3x3f.identify();
        mat = mat.mul(Matrix3x3f.rotate(cannonRotFloat));
        mat = mat.mul(Matrix3x3f.translate(-2.0f, -2.0f));
        for(int i = 0; i < cannonVector2fArray.length; ++i) {
            cannonCpy[i] = mat.mul(cannonVector2fArray[i]);
        }
        if(bullet != null) {
            velocityVector2f.y += -9.8f * delta;
            bullet.x += velocityVector2f.x * delta;
            bullet.y += velocityVector2f.y * delta;
            bulletCpy = new Vector2f(bullet);
            if(bullet.y < -2.5f) {
                bullet = null;
            }
        }
    }
    
    private void renderFrame() {
        do {
            do{
                Graphics g = null;
                try {
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    render(g);
                } catch(Exception e) {
                    System.out.println(e);
                } finally {
                    if( g != null ) {
                        g.dispose();
                    }
                }
            } while(bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while(bufferStrategy.contentsLost());
    }
    
    private void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    private void render(Graphics g) {
        g.setColor(Color.BLACK);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
        g.drawString("(A) to raise, (D) to lower", 20, 35);
        g.drawString("Press Space to fire cannot", 20, 50);
        String vel = String.format("Velocity (%.2f, %.2f)", velocityVector2f.x, velocityVector2f.y);
        g.drawString(vel, 20, 65);
        float worldWidth = 5.0f;
        float worldHeight = 5.0f;
        float screenWidth = canvas.getWidth() - 1;
        float screenHeight = canvas.getHeight() - 1;
        float sx = screenWidth / worldWidth;
        float sy = -screenHeight / worldHeight;
        Matrix3x3f viewport = Matrix3x3f.scale(sx, sy);
        float tx = screenWidth / 2.0f;
        float ty = screenHeight /2.0f;
        viewport =viewport.mul(Matrix3x3f.translate(tx, ty));
        for (int i = 0; i < cannonVector2fArray.length; ++i) {
            cannonCpy[i] = viewport.mul(cannonCpy[i]);
        }
        drawPolygon(g, cannonCpy);
        if(bullet != null) {
            bulletCpy = viewport.mul(bulletCpy);
            g.drawRect((int)bulletCpy.x - 2, (int)bulletCpy.y - 2, 4, 4);
        }
    }
    
    private void drawPolygon(Graphics g, Vector2f[] polygon) {
        Vector2f P;
        Vector2f S = polygon[polygon.length - 1];
        for(int i = 0; i < polygon.length; ++i) {
            P = polygon[i];
            g.drawLine((int)S.x, (int)S.y, (int)P.x, (int)P.y);
            S = P;
        }
    }
    
}
