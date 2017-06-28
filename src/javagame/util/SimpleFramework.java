package javagame.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SimpleFramework extends JFrame implements Runnable{

    protected Color appBackground = Color.BLACK; 
    protected boolean appMaintainRatio = false;
    protected Color appBorder = Color.LIGHT_GRAY;
    protected int appWidth = 640;
    protected int appHeight = 480;
    protected String appTitle = "TBD-Title";
    protected long appSleep = 10L;
    protected float appBorderScale = 0.8f;
    protected float appWorldWidth = 2.0f;
    protected float appWorldHeight = 2.0f;
    protected Font appFont = new Font("Courier New", Font.PLAIN, 14);
    protected Color appFPSColor = Color.GREEN;
    protected KeyboardInput keyboardInputBoolean;
    protected RelativeMouseInput relativeMouseInputBoolean;
    protected Canvas canvas;
    protected BufferStrategy bufferStrategy;
    protected Thread gameThread;
    protected boolean runningVolatileBoolean;
    protected FrameRate frameRate;

    public SimpleFramework() {
    }

    protected static void launchApp(final SimpleFramework  simpleframework) {
        simpleframework.addWindowListener(new WindowAdapter(){
            //处理退出
            public void WindowClosing(WindowEvent e) {
                simpleframework.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                simpleframework.createAndShowGUI();
            }
        });
    }

    protected void onWindowClosing() {
        try {
            runningVolatileBoolean = false;
            gameThread.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }

    public void createAndShowGUI() {
        canvas = new Canvas();
        //The background color of the main game area.
        canvas.setBackground(appBackground);
        //The Component.setIgnoreRePaint() method takes care of ignoring extra paint messages.
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setLocationByPlatform(true);
        //True if the aspect ratio should be maintained.
        if (appMaintainRatio) {
            //The color of the border when the aspect ratio is maintained.
            getContentPane().setBackground(appBorder);
            //appHeight - The startup width of the window.
            setSize(appWidth, appHeight);
            //appHeight - The startup height of the window.
            canvas.setSize(appWidth, appHeight);
            setLayout(null);
            /**
             * When the component's size, location, or visibility changes, 
             * the relevant method in the listener object is invoked,
             * and the ComponentEvent is passed to is.
             */
            getContentPane().addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
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
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        gameThread = new Thread(this);
        gameThread.start();
    }

    protected void onComponentResized(ComponentEvent e) {
        Dimension sizeDimension = getContentPane().getSize();
        int vw = (int)(sizeDimension.width * appBorderScale);
        int vh = (int)(sizeDimension.height * appBorderScale);
        int vx = (sizeDimension.width - vw) / 2;
        int vy = (sizeDimension.height - vh) / 2;
        int newW = vw;
        int newH = (int)(vw * appWorldHeight / appWorldWidth);
        if(newH > vh) {
            newW = (int)(vw * appWorldHeight / appWorldWidth);
            newH = vh;
        }
        //center
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
        while(runningVolatileBoolean) {
            curTime = System.nanoTime();
            nsPerFrame = curTime - lastTime;
            gameLoop((float)(nsPerFrame / 1.0E9));
            lastTime = curTime;
        }
        terminate();
    }

    protected void initialize() {
        frameRate = new FrameRate();
        frameRate.initialize();
    }

    protected void gameLoop (float delta) {
        processInput(delta);
        updateObjects(delta);
        renderFrame();
        sleep(appSleep);
    }

    protected void terminate () {
    }

    protected void processInput (float delta) {
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
    }

    protected void updateObjects (float delta) {
    }

    private void renderFrame () {
        do {
            do {
                Graphics g = null;
                try {
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
                    render(g);
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    if (g != null) {
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

    protected void render (Graphics g) {
        g.setFont(appFont);
        g.setColor(appFPSColor);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
    }

    protected Matrix3x3f getViewportTransform () {
        return Utility.createViewport(appWorldWidth, appWorldHeight, canvas.getWidth(), canvas.getHeight());
    }

    protected Matrix3x3f getReverseViewportTransform() {
        return Utility.createReverseViewport(appWorldWidth, appWorldHeight, canvas.getWidth(), canvas.getHeight());
    }

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
}
