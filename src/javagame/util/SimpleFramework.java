package javagame.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class SimpleFramework extends JFrame implements Runnable {
    private volatile boolean volatileRunningBoolean;
    private Thread gameThread;
    private FrameRate frameRate;
    private BufferStrategy bufferStrategy;
    protected int appWidth;
    protected int appHeight;
    protected String appTitle;
    protected Color appBackground;
    protected Color appFPSColor;
    protected KeyboardInput keyboardInputBoolean;
    protected RelativeMouseInput relativeMouseInputBoolean;
    protected Color appBorder;
    protected Font appFont;
    protected float appBorderScale;
    protected boolean appMaintainRatio;
    protected long appSleep;
    protected float appWorldWidth;
    protected float appWorldHeight;

    public SimpleFramework () {
    }

    protected void createAndShowGUI () {
        setSize(320, 240);
        setTitle("Render Thread");
        setVisible(true);

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run () {
        volatileRunningBoolean = true;
        while (volatileRunningBoolean) {
            sleep(10);
        }
    }

    private void sleep (long sleep) {
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    protected void initialize () {}
    
    protected void processInput (float delta) {}
    
    protected Vector2f getWorldMousePosition () {
        return new Vector2f();
        //233
    }
    
    protected void updateObjects(float delta) {
    }
    
    protected void render(Graphics g) {}
    
    protected Matrix3x3f getViewportTransform () {
        return Matrix3x3f.identity();
        //233
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
