package javagame.transform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;
import javagame.util.Vector2f;
import javagame.util.FrameRate;
import javagame.util.Matrix3x3f;

public class MatrixMultiplyExample extends JFrame implements Runnable{
    
    private static final int SCREEN_W = 640;
    private static final int SCREEN_H = 480;
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private float earthRotFloat, earthDeltaFloat;
    private float moonRot,moonDelta;
    private boolean showStars;
    private int[] stars;
    private Random rand = new Random();
    
    public MatrixMultiplyExample() {
        
    }

    public static void main(String[]args) {
        final MatrixMultiplyExample matrixMultipleExample = new MatrixMultiplyExample();
        matrixMultipleExample.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {  
//                2
//          processing exit
                matrixMultipleExample.onWindowShutDown();
//                233
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                2
//              Create graphics
                matrixMultipleExample.createAndShowGUI();
//                233
            }
        });
    }
    
    protected void onWindowShutDown() {
        /**
         * The runnable interface contains a single method: public void run(). 
         * This method contains the render loop, and is called only once. 
         * When it returns, the thread is finished and cannot be used again. 
         * To keep the code in the run method from exiting until the application is done, 
         * the Boolean running variable keeps the run method repeating.
         * Once the application is shut down, the render thread should stop. 
         * By adding a window listener, 
         * the program can respond to the window closing event. 
         * In this case, the program calls onWindowClosing(). 
         * If your program needs to shut down resources and close files that are no longer needed, 
         * this is the place to do it.
         * To shut down the rendering thread, the running variable is set to false. 
         * However, the render thread may have just read the value of the running variable, and be asleep. 
         * To make sure that the thread has stopped, the join() method is called. 
         * This method will wait until the thread has ended and the run method has returned. 
         * If a timeout value is not passed into the join() method, 
         * this will block forever until the thread ends, 
         * so make sure the thread is going to end if you don’t provide a timeout.
         * Lastly, the program must be shut down by hand with the System.exit(0) call. 
         * Previously, when the JFrame.EXIT_ON_CLOSE flag was set in the JFrame, 
         * the program would terminate. 
         * Now that the application is handling the shutdown, 
         * the program will not end unless the exit method is called. 
         * If the application hangs after shutting down, 
         * it is usually because the programmer forgot to call System.exit().
         */
        try {
            runningVolatileBoolean = false;
            gameThread.join();
        } catch(Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }
//  设置背景
    protected void createAndShowGUI(){
        Canvas canvas = new Canvas();
        canvas.setSize(SCREEN_W, SCREEN_H);
        canvas.setBackground(Color.BLACK);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Matrix Multiply Example");
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
//        Add key listeners
        keyboardInputBoolean = new KeyboardInput();
        canvas.addKeyListener(keyboardInputBoolean);
//        Add relativeMouseInputBoolean listener
//        For full screen : relativeMouseInputBoolean = new RelativeMouseInput( this );
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
    
    public void run() {
        runningVolatileBoolean = true;
        initialize();
        while( runningVolatileBoolean ) {
//        2
            gameLoop();
//            233
        }
    }
    
    private void initialize() {
        frameRate = new FrameRate();
        frameRate.initialize();
        earthDeltaFloat = (float)Math.toRadians(0.5);
        moonDelta = (float)Math.toRadians(2.5);
        showStars = true;
        stars = new int[1000];
        for(int i=0; i < stars.length - 1; i += 2) {
            stars[i] = rand.nextInt(SCREEN_W);
            stars[i + 1] = rand.nextInt(SCREEN_H);
            
        }
    }
    
    private void gameLoop() {
        processInput();
        renderFrame();
//        2
        sleep(10L);
//        233
    }
    
    private void processInput() {
//        2
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)) {
            showStars = !showStars;
        }
//        233
    }
    
    private void renderFrame() {
        do {
            do {
                Graphics g = null;
                try{
                    g = bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
//                    2
                    render(g);
//                    233
                } catch (Exception e){
                    System.out.println(e);
                } finally {
                    if( g != null) {
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());
            bufferStrategy.show();
        }while(bufferStrategy.contentsLost());
    }
    
    private void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void render(Graphics g) {
        g.setColor(Color.GREEN);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
        g.drawString("Press [SPACE] to toggle stars", 20, 35);
        if(showStars) {
            g.setColor(Color.WHITE);
            for(int i = 0; i < stars.length - 1; i +=2 ) {
                g.fillRect(stars[i], stars[i + 1], 1, 1);
            }
        }
//        draw the sum...
        /**
         * The identity matrix is used as a starting point,
         * and then multiplied by a translation matrix that moves to the center of the screen.
         */
        Matrix3x3f sunMatrix = Matrix3x3f.identity();
        sunMatrix = sunMatrix.mul(Matrix3x3f.translate(SCREEN_W / 2, SCREEN_H / 2));
        /**
         * Thre sun vector is then multiplied by the matrix,
         * placing the sun's center at the center of the screen.
         * Althought it is only a single point,
         * an entire polygon model could be multiplied by the matrix transforming all the coordinates.
         */
//        乘向量转化为点
        Vector2f sun = sunMatrix.mul(new Vector2f() );
        g.setColor(Color.YELLOW);
        g.fillOval((int)sun.x - 50, (int)sun.y - 50, 100, 100);
//        draw Earth's Orbit
        g.setColor(Color.WHITE);
        g.drawOval((int)sun.x - SCREEN_W / 4, (int)sun.y - SCREEN_W / 4, SCREEN_W / 2, SCREEN_W / 2);
//        draw the Earth
        Matrix3x3f earthMatrix = Matrix3x3f.translate(SCREEN_W / 4,0);
//        2
//        Matrix3x3f earthMatrix = Matrix3x3f.translate(0, SCREEN_W / 4);
//        233
        earthMatrix = earthMatrix.mul(Matrix3x3f.rotate(earthRotFloat));
        earthMatrix = earthMatrix.mul(sunMatrix);
        earthRotFloat += earthDeltaFloat;
        Vector2f earthVector2f = earthMatrix.mul(new Vector2f());
        g.setColor(Color.BLUE);
        g.fillOval((int)earthVector2f.x - 10, (int)earthVector2f.y - 10, 20, 20);
//        draw the Moon
        Matrix3x3f moonMat = Matrix3x3f.translate(30, 0);
        moonMat = moonMat.mul(Matrix3x3f.rotate(moonRot));
        moonMat = moonMat.mul(earthMatrix);
        moonRot += moonDelta;
        Vector2f moon = moonMat.mul(new Vector2f());
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval((int)moon.x - 5, (int)moon.y - 5, 10, 10);
    }
    
}
