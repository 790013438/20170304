package javagame.transform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;
import javagame.util.FrameRate;

public class PolarCoordinateExample extends JFrame implements Runnable{
    
    private static final int SCREEN_W = 640;
    private static final int SCREEN_H = 480;
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private Point coord;
    
    public static void main(String[] args) {
        final PolarCoordinateExample polarCoordinateExample = new PolarCoordinateExample();
        polarCoordinateExample.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
    //          processing exit
                polarCoordinateExample.onWindowShutDown();
            }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//              Create graphics
                polarCoordinateExample.createAndShowGUI();
            }
        });
    }
//    processing exit
    protected void onWindowShutDown(){
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
        } catch (Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }
//    设置背景
    protected void createAndShowGUI() {
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
        setTitle("Polar Coordinate Example");
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
//        Add mouse listener
//        For full screen: mouse =new RelativeMouseInput(this);
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
//      线程处理行为
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    public void run(){   
        runningVolatileBoolean = true;
        initialize();
        while( runningVolatileBoolean ) {
            gameLoop();
        }
    }
    
    private void initialize() {
        frameRate = new FrameRate();
        frameRate.initialize();
        /**
         * The coord variable holds the screen position where the mouse is located,
         * offset by the center of the screen.
         */
        coord = new Point();
    }
    
//    设置画的大局
    public void gameLoop() {
//        让画暂存输入的内容，操作
        processInput();
//        让画动
        renderFrame();
        sleep(10L);
    }
    
    private void processInput() {
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
        coord = relativeMouseInputBoolean.getPosition();
    }
//  缓存技术，调用的method（方法）里包含设计画面
    private void renderFrame() {
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
                    if(g != null) {
                        g.dispose();
                    }
                }
            } while(bufferStrategy.contentsRestored());
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
    
    private void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * The conversion happens in the render() method.
     * @param g
     */
    private void render(Graphics g) {
        g.setFont(new Font("Courier New", Font.BOLD, 24));
        g.setColor(Color.GREEN);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 40);
        /**
         * The (cx,cy) coordinate represents the center of the screen and the (px,py)
         * coordinate offsets the current mouse position from the center of the screen.
         */
        int cx = SCREEN_W / 2;
        int cy = SCREEN_H / 2;
        g.setColor(Color.GRAY);
        g.drawLine(0, cy, SCREEN_W, cy);
        g.drawLine(cx, 0, cx, SCREEN_H);
        g.setColor(Color.GREEN);
        g.drawLine(cx, cy, coord.x, coord.y);
        /**
         * Because the y value is position in the down direction for screen coordinates,
         * the py variable is calculated differently.
         */
        int px = coord.x - cx;
        int py = cy - coord.y;
        double r = Math.sqrt(px * px + py * py);
        double rad = Math.atan2(py, px);
        double degrees = Math.toDegrees(rad);
        /**
         * The conversion from the (px,py) values to the polar coordinates is stored in the 
         * r variable for the distance and the rad variable for the angle in radinas.
         * Because the Math.atan2() method renturns the angle from (-180,180),
         * the degrees are adjusted to (0,360).
         */
        if(degrees < 0) {
            degrees = 360 + degrees;
        }
        /**
         * The (sx,sy) variables hod the conversion from the plar coordinates back to the Cartesian coordinates.
         */
        double sx = r * Math.cos(rad);
        double sy = r * Math.sin(rad);
        String polar = String.format("%.0f,%.0f\u00b0", r, degrees);
        g.drawString(polar, 20, 60);
        String cart = String.format("%.0f,%.0f", sx, sy);
        g.drawString(cart, 20, 80);
        g.setColor(Color.WHITE);
        g.drawString(String.format("(%s,%s)", px, py), coord.x, coord.y);
        g.setColor(Color.BLUE);
        g.drawArc((int)(cx - r), (int)(cy - r), (int)(2 * r), (int)(2 * r), 0, (int)degrees);
    }

}
