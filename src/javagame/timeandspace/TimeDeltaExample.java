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

import javagame.util.FrameRate;
import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;

public class TimeDeltaExample extends JFrame implements Runnable {
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private float angleFloat;
    private float stepLong;
    private long sleepLong;
    private Canvas canvas;

    public static void main(String[] args) {
        final TimeDeltaExample timeDeltaExample = new TimeDeltaExample();
        timeDeltaExample.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                timeDeltaExample.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//           2
                timeDeltaExample.createAndShowGUI();
//            233
            }
        });
    }
    
    protected void onWindowClosing() {
        try {
//        2
            runningVolatileBoolean = false;
            gameThread.join();
//        233
        } catch( Exception e ) {
            System.out.println(e);
        }
        System.exit(0);
    }
    
    protected void createAndShowGUI() {
        canvas = new Canvas();
        canvas.setSize(480, 480);
        canvas.setBackground(Color.WHITE);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Time Delta Example");
        /**
         * Because the render loop does all the painting for this application, 
         * the setIgnoreRepaint() flag can be set on the JFrame.
         */
        setIgnoreRepaint(true);
        pack();
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
//        2
        gameThread = new Thread(this);
        gameThread.start();
//        233
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
//            2
            gameLoop( nsPerFrame / 1.0E9 );
//            233
            lastTime = curTime;
        }
    }
    
    private void initialize() {
        frameRate = new FrameRate();
        frameRate.initialize();
        angleFloat = 0.0f;
        stepLong = (float) Math.PI / 2.0f;
    }
    
    private void gameLoop(double delta) {
        processInput(delta);
        updateObjects(delta);
//        2
        renderFrame();
//        233
        sleep(sleepLong);
    }
    
    public void processInput(double delta) {
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_UP)) {
            sleepLong += 10;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_DOWN)) {
            sleepLong -= 10;
        }
        if(sleepLong > 1000) {
            sleepLong = 1000;
        }
        if(sleepLong < 0) {
            sleepLong = 0;
        }
    }
    
    public void updateObjects(double delta) {
        angleFloat += stepLong * delta;
        if(angleFloat > 2 * Math.PI) {
            angleFloat -= 2 * Math.PI;
        }
    }
    
    public void renderFrame() {
        do {
            do {
                Graphics g = null;
                try{
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
//                    2
                    render(g);
//                    233
                } catch ( Exception e ) {
                    
                } finally {
                    if( g != null ) {
                        g.dispose();
                    }
                }
            } while(bufferStrategy.contentsLost());
            bufferStrategy.show();
        } while(bufferStrategy.contentsLost());
    }
    
    public void sleep( Long sleep ) {
        try {
            Thread.sleep(sleep);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
        g.drawString("Up arrow increases sleep time", 20, 35);
        g.drawString("Down arraow decreases sleep time", 20, 50);
        g.drawString("Sleep time (ms): " + sleepLong, 20, 65);
        int x = canvas.getWidth() / 4;
        int y = canvas.getHeight() / 4;
        int w = canvas.getWidth() / 2;
        int h = canvas.getHeight() / 2;
        g.drawOval(x, y, w, h);
//        polar -> coords
        float rw = w / 2;
        float rh = h / 2;
        int rx = (int)(rw * Math.cos(angleFloat));
        int ry = (int)(rh * Math.sin(angleFloat));
        int cx = (int)(rx + w);
        int cy = (int)(ry + h);
//        draw clock hand
        g.drawLine(w, h, cx, cy);
//        draw dot at end of hand
        g.drawRect(cx - 2, cy - 2, 4, 4);
    }
    
}
