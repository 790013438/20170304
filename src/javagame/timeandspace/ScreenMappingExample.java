package javagame.timeandspace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.FrameRate;
import javagame.util.KeyboardInput;
import javagame.util.Matrix3x3f;
import javagame.util.RelativeMouseInput;
import javagame.util.Vector2f;

public class ScreenMappingExample extends JFrame implements Runnable {
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private Vector2f[] triangle;
    private Vector2f[] triangleWorld;
    private Vector2f[] rect;
    private Vector2f[] rectWorld;
    private Canvas canvas;

    public static void main(String[] args) {
        final ScreenMappingExample screenMappingExample = new ScreenMappingExample();
        screenMappingExample.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//          processing exit
                screenMappingExample.onWinowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
//              Create graphics
                screenMappingExample.createAndShowGUI();
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
        initialize();
        long curTime = System.nanoTime();
        long lastTime = curTime;
        double nsPerFrame;
        while( runningVolatileBoolean ) {
            curTime = System.nanoTime();
            nsPerFrame = curTime - lastTime;
            gameLoop(nsPerFrame / 1.0E9);
            lastTime = curTime;
        }
    }
    
    private void initialize() {
        frameRate = new FrameRate();
        frameRate.initialize();
        triangle = new Vector2f[] { new Vector2f(0.0f, 0.5f), new Vector2f(-0.5f, -0.5f), new Vector2f(0.5f, -0.5f) };
        triangleWorld = new Vector2f[triangle.length];
        rect = new Vector2f[] { new Vector2f(-1.0f, 1.0f), new Vector2f(1.0f, 1.0f), new Vector2f(1.0f, -1.0f), new Vector2f(-1.0f, -1.0f) };
        rectWorld = new Vector2f[rect.length];
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
    }
    
    private void updateObjects(double delta) {
    }
    
    private void renderFrame() {
        do {
            do {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * The render() method creates the viewport matrix and multiplies the triangle points,
     * mapping the values from the world space to screen space.
     * Also,a border rectangle is drawn around the edges.
     * Try removing the -1 from the screen width and screen height calculation,
     * and see what happens to the border rectangle.
     * @param g
     */
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
        float worldWidth = 2.0f;
        float worldHeight = 2.0f;
        /*
         * Notice in the previous example code that 1 is subtracted from the screen width and 
         * height before they are used.
         * This is because the actual pixel values are mapped to the screen,
         * and although the screen width may be 10,the pixels go from 0-9,just like an array.
         * If the actual width were used,when the world coordinates were converted to screen coordinates,
         * the last row and column would be drawn off the screen,which is not the desired intent.
         * Setting the screen width to width - 1 and the screen height to height - 1 keeps this from happening.
         */
        float screenWidth = canvas.getWidth() - 1;
        float screenHeight = canvas.getHeight() - 1;
        float sx = screenWidth / worldWidth;
        float sy = screenHeight / worldHeight;
        float tx = screenWidth / 2.0f;
        float ty = screenHeight / 2.0f;
        Matrix3x3f viewpoint = Matrix3x3f.scale(sx, -sy);
        viewpoint = viewpoint.mul(Matrix3x3f.translate(tx, ty));
        for(int i = 0; i < triangle.length; ++i ) {
            triangleWorld[i] = viewpoint.mul(triangle[i]);
        }
        drawPolygon(g, triangleWorld);
        for(int i = 0; i < rect.length; ++i) {
            rectWorld[i] = viewpoint.mul(rect[i]);
        }
        drawPolygon(g, rectWorld);
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
