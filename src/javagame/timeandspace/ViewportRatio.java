package javagame.timeandspace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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

public class ViewportRatio extends JFrame implements Runnable {
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private float worldWidth;
    private float worldHeight;
    private Canvas canvas;
    private FrameRate frameRate;
    private Vector2f[] tri;
    private Vector2f[] triWorld;

    public static void main(String[] args) {
        final ViewportRatio viewportRatio = new ViewportRatio();
        viewportRatio.addWindowListener(new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
//          processing exit
                viewportRatio.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
//             Create graphics
               viewportRatio.createAndShowGUI();
           }
        });
    }
//  processing exit
    protected void onWindowClosing() {
        try {
            runningVolatileBoolean = false;
            gameThread.join();
        } catch(Exception e) {
            System.out.println(e);
        }
        System.exit(0);;
    }
//  设置背景
    protected void createAndShowGUI() {
        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        setTitle("Vieport Ratio");
        setSize(640,640);
        getContentPane().add(canvas);
//        add KeyListener;
        keyboardInputBoolean = new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
//        add MouseLitener,MouseMotionListener,MouseWheelListener
        relativeMouseInputBoolean = new RelativeMouseInput(canvas);
        canvas.addMouseListener(relativeMouseInputBoolean);
        canvas.addMouseMotionListener(relativeMouseInputBoolean);
        canvas.addMouseWheelListener(relativeMouseInputBoolean);
        getContentPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                onComponentResized(e);
            }
        });
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
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    /**
     * If the adjusted viewport is centered in the current window,
     * then no matter what size the window is,
     * the viewport will grow or shrink to the largest size possible that still 
     * maintains the correct aspect ratio.
     * The new size is found using the ratio of the two windows:
     * new width/ new height: viewport width/ viewport height
     * To calculate the new size of the viewport,either the screen width or screen height can 
     * be chosen to calculate the other value so that the ratio will remain correct.
     * Although you can perform checks to determine which dimension to use,
     * it is just as easy to pick one,see if the new ratio will fit in the current window,
     * and if it is too big,use the other one.
     * @param e
     */
    protected void onComponentResized(ComponentEvent e) {
        Dimension size = getContentPane().getSize();
        int vw = size.width * 3 / 4;
        int vh = size.height * 3 / 4;
        int vx = (size.width - vw) / 2;
        int vy = (size.height - vh) / 2;
        int newW = vw;
        int newH = (int)(vw * worldHeight / worldWidth);
        if(newH > vh) {
            newW = (int) (vh * worldWidth / worldHeight);
            newH = vh;
        }
//        center
        vx += (vw - newW) / 2;
        vy += (vh - newH) / 2;
        canvas.setLocation(vx, vy);
        canvas.setSize(newW, newH);
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
        tri = new Vector2f[] { new Vector2f(0.0f, 2.25f), new Vector2f(-4.0f, -2.25f), new Vector2f(4.0f, -2.25f) };
        triWorld = new Vector2f[tri.length];
        worldWidth = 16.0f;
        worldHeight = 9.0f;
    }
    
    public void gameLoop(double delta) {
        processInput(delta);
        updateObject(delta);
        renderFrame();
        sleep(10L);
    }
    
    private void processInput(double delta) {
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
    }
    
    public void updateObject(double delta) {
    }
    
    public void renderFrame() {
        do {
            do {
                /**
                 * To use the BufferStrategy in a render loop, a graphics object
                 * is created using the getDrawGraphics() method. 
                 * This graphics object will draw to the off-screen surface. 
                 */
                Graphics g = null;
                try {
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    /**
                     * Once the graphics object is available,it is used exactly like the graphics 
                     * object passed in the paint() method of the JPanel class.
                     */
//                    具体图形画面, 变化
                    render(g);
                } catch(Exception e) {
                    System.out.println(e);
                } finally {
                    if( g != null ) {
                        g.dispose();
                    }
                }
            } while(bufferStrategy.contentsLost());
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
        } while(bufferStrategy.contentsLost());
    }
    
    public void sleep(long sleepLong) {
        try {
            Thread.sleep(sleepLong);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
        float sx = (canvas.getWidth() - 1) / worldWidth;
        float sy = (canvas.getHeight() - 1) / worldHeight;
        float tx = (canvas.getWidth() - 1) / 2.0f;
        float ty = (canvas.getHeight() - 1) / 2.0f;
        Matrix3x3f viewport = Matrix3x3f.identify();
        viewport = viewport.mul(Matrix3x3f.scale(sx, sy));
        viewport = viewport.mul(Matrix3x3f.translate(tx, ty));
        for(int i = 0; i < tri.length; ++i) {
            triWorld[i] = viewport.mul(tri[i]);
        }
        drawPolygon(g, triWorld);
    }

    public void drawPolygon(Graphics g, Vector2f[] poloygonVector2f) {
        Vector2f P;
        Vector2f S = poloygonVector2f[poloygonVector2f.length - 1];
        for(int i = 0; i < poloygonVector2f.length; ++i ) {
            P = poloygonVector2f[i];
            g.drawLine((int)S.x, (int)S.y, (int)P.x, (int)P.y);
            S = P; 
        }
    }
}
