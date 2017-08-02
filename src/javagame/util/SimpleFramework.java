package javagame.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class SimpleFramework extends JFrame implements Runnable {
    private volatile boolean volatileRunningBoolean;
    private Thread gameThread;
    private FrameRate frameRate;
    private BufferStrategy bufferStrategy;
    protected int appWidth = 640;
    protected int appHeight = 480;
    protected String appTitle = "TBD-Title";
    protected Color appBackground = Color.BLACK;
    protected Color appFPSColor = Color.GREEN;
    protected KeyboardInput keyboardInputBoolean;
    protected RelativeMouseInput relativeMouseInputBoolean;
    protected Color appBorder = Color.LIGHT_GRAY;
    protected Font appFont = new Font("Courier New", Font.PLAIN, 14);
    protected float appBorderScale = 0.8f;
    protected boolean appMaintainRatio = false;
    protected long appSleep = 10L;
    protected float appWorldWidth = 2.0f;
    protected float appWorldHeight = 2.0f;
    protected Canvas canvas;

    public SimpleFramework () {
    }

    protected void createAndShowGUI () {
        canvas = new Canvas();
        canvas.setBackground(appBackground);
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setLocationByPlatform(true);
        setSize(320, 240);
        if (appMaintainRatio) {
            getContentPane().setBackground(appBorder);
            setSize(appWidth, appHeight);
            setLayout(null);
            getContentPane().addComponentListener(new ComponentAdapter() {
                public void componentResized (ComponentEvent e) {
                    onComponentResized(e);
                }
            });
        } else {
            canvas.setSize(appWidth, appHeight);
            pack();
        }
        setTitle(appTitle);
        keyboardInputBoolean = new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
        relativeMouseInputBoolean = new RelativeMouseInput(canvas);
        canvas.addMouseListener(relativeMouseInputBoolean);
        canvas.addMouseMotionListener(relativeMouseInputBoolean);
        canvas.addMouseWheelListener(relativeMouseInputBoolean);
        setVisible(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    protected void onComponentResized (ComponentEvent e) {
        Dimension size = getContentPane().getSize();
        int vw = (int)(size.width * appBorderScale);
        int vh = (int)(size.height * appBorderScale);
        int vx = (size.width - vw) / 2;
        int vy = (size.height - vh) / 2;
        int newW = vw;
        int newH = (int)(vw * appWorldHeight / appWorldWidth);
        if (newH > vh) {
            newW = (int)(vh * appWorldWidth / appWorldHeight);
            newH = vh;
        }
        //center
        vx += (vw - newW) / 2;
        vy += (vh - newH) / 2;
        canvas.setLocation(vx, vy);
        canvas.setSize(newW, newH);
    }

    public void run () {
        volatileRunningBoolean = true;
        initialize();
        long curTime = System.nanoTime();
        long lastTime = curTime;
        double nsPerFrame;
        while (volatileRunningBoolean) {
            curTime = System.nanoTime();
            nsPerFrame = curTime - lastTime;
            gameLoop((float)(nsPerFrame / 1.0E9));
            lastTime = curTime;
        }
        terminate();
    }

    protected void gameLoop (float delta) {
        processInput(delta);
        updateObjects(delta);
    }

    private void sleep (long sleep) {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    protected void initialize () {
        frameRate = new FrameRate();
        frameRate.initialize();
    }
    
    protected void processInput (float delta) {}
    
    protected Vector2f getWorldMousePosition () {
        Matrix3x3f screenToWorld = getReverseViewportTransform();
        Point mousePos = relativeMouseInputBoolean.getPosition();
        Vector2f screenPos = new Vector2f(mousePos.x, mousePos.y);
        return screenToWorld.mul(screenPos);
    }

    protected Vector2f getRelativeWorldMousePosition () {
        float sx = appWorldWidth / (canvas.getWidth() - 1);
        float sy = appWorldHeight / (canvas.getHeight() - 1);
        Matrix3x3f viewport = Matrix3x3f.scale(sx, -sy);
        Point p = relativeMouseInputBoolean.getPosition();
        return viewport.mul(new Vector2f(p.x, p.y));
    }
    
    protected void updateObjects(float delta) {
    }
    
    protected void render(Graphics g) {}
    
    protected Matrix3x3f getViewportTransform () {
        return Utility.createViewport (appWorldWidth, appWorldHeight, canvas.getWidth(), canvas.getHeight());
    }

    protected Matrix3x3f getReverseViewportTransform () {
        return Utility.createReverseViewport(appWorldWidth, appWorldHeight, canvas.getWidth(), canvas.getHeight());
    }
    
    protected void terminate () {}

    /**
     * To shut down the rendering thread, the volatileRunnginBoolean variable is 
     * set to false.
     * However, the render thread may have just read the value of the volatileRunnginBoolean variable,
     * and be asleep.
     * To make sure that the thread has stopped,the join() method is called.
     * This method will wait until the thread has ended 
     * and the run method has returned.
     * If a timeout value is not passed into the join method, 
     * this will block forever until the thread ends,
     * so make sure the thread is going to end if you don't provide a timeout.
     * Lastly, the program must be shut down by hand with the System.exit(0) call.
     * Previously, when the JFrame.EXIT_ON_CLOSE flag was set in the JFame,
     * the program would terminate.
     * Now that the application is handling the shutdown,
     * the program will not end unless the exit method is called.
     * If the application hangs after shutting down,
     * it usually because the programmer forgeot to call Systtem.exit();
     */
    protected void onWindowClosing () {
        try {
            volatileRunningBoolean = false;
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }

    protected static void launchApp (final SimpleFramework simpleFramework) {
        /**
         * Once the application is shut down, the render thread should stop.
         * By adding a window listener, the program can respond to the window closing event.
         * In this case, the program calls onWindowClosing().
         * If you program needs to shut down resources and close files that 
         * are not longer needed, this is the place to do it.
         */
        simpleFramework.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                simpleFramework.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable () {
            public void run () {
                simpleFramework.createAndShowGUI();
            }
        });
    }

}
