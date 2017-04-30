package javagame.transform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;
import javagame.util.FrameRate;
import javagame.util.Vector2f;

public class VectorGraphicsExample extends JFrame implements Runnable{
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    private Vector2f[] polygonVector2fArray;
    private Vector2f[] worldVector2fArray;
    private float tx,ty;
    private static final int SCREEN_W = 640;
    private static final int SCREEN_H = 480;
    private float vx, vy;
    private float rot, rotStep;
    private float scale, scaleStep;
    private float sx, sxStep;
    private float sy, syStep;
    private boolean doRotateBoolean;
    private boolean doScaleBoolean;
    private boolean doTranslateBoolean;
    private boolean doXShearBoolean;
    private boolean doYShearBoolean;
    
    public static void main(String[] args){
        final VectorGraphicsExample vectorGraphicsExample = new VectorGraphicsExample();
        vectorGraphicsExample.addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent e){
//             processing exit
               vectorGraphicsExample.onWindowShutdown();
           }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//              Create graphics
                vectorGraphicsExample.createAndShowGUI();
            }
        });
    }
//    processing exit
    protected void onWindowShutdown(){
        try {
            runningVolatileBoolean = false;
            gameThread.join();
        } catch(Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }
//    crate graphics
    protected void createAndShowGUI(){
        Canvas canvas = new Canvas();
        canvas.setSize(640,480);
        canvas.setBackground(Color.BLACK);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Vector Graphics Example");
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
//        For full screen:mouse = new RelativeMouseInput( this );
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
//        线程处理行为
        gameThread = new Thread(this);
        gameThread.start();        
    }
    
    public void run(){
        runningVolatileBoolean = true;
        initialized();
        while(runningVolatileBoolean){
            gameLoop();
        }
    }
    
    /**
     * The initialized() method sets up the polygon ship and calls reset().
     */
    private void initialized(){
        frameRate = new FrameRate();
        frameRate.initialize();
        /**
         * The polygon object is copied into the world list before being transformed.
         * This way,the operations are not cumulative,but happend very frame.
         * Not only does this leave the original model untouched,
         * it allows multiple copies of the original to be used.
         */
        polygonVector2fArray = new Vector2f[]{new Vector2f(10, 0), new Vector2f(-10,8), new Vector2f(0, 0), new Vector2f(-10, -8)};
        worldVector2fArray = new Vector2f[polygonVector2fArray.length];
        reset();
    }

    /**
     * The reset method is responsible for setting all the transformation values back to their starting values.
     */
    private void reset(){
        tx = SCREEN_W / 2;
        ty = SCREEN_H / 2;
        vx = vy = 2;
        rot = 0.0f;
        rotStep = (float) Math.toRadians(1.0);
        scale = 1.0f;
        scaleStep = 0.1f;
        sx = sy = 0.0f;
        sxStep = syStep = 0.01f;
        doRotateBoolean = doScaleBoolean = doTranslateBoolean = false;
        doXShearBoolean = doYShearBoolean = false;
    }
    
    private void gameLoop(){ 
        processInput();
        processObjects();
        renderFrame();
        sleep(10L);
    }
    
    /**
     * There are keys mapped to the different transformation operations
     * R for rotation,S for shearing,T for translation,X for x-shearing,Y for y-shearing,and the spacebar to 
     * reset the values back to their starting values. 
     */
    private void processInput(){
        keyboardInputBoolean.poll();
        relativeMouseInputBoolean.poll();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_R)){
            doRotateBoolean = !doRotateBoolean;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_S)){  
            doScaleBoolean = !doScaleBoolean;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_T)){
            doTranslateBoolean = !doTranslateBoolean;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_X)){
            doXShearBoolean = !doXShearBoolean;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_Y)){
            doYShearBoolean = !doYShearBoolean;
        }
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)){
            reset();
        }
    }
    
    private void processObjects(){
//        copy data...
        for(int i = 0;i < polygonVector2fArray.length; ++i){
            worldVector2fArray[i] = new Vector2f(polygonVector2fArray[i]);
        }
        if(doScaleBoolean){
            scale += scaleStep;
            if(scale < 1.0 || scale > 5.0){
                scaleStep = -scaleStep;
            }
        }
        if(doRotateBoolean){
            rot += rotStep;
            if(rot < 0.0f || rot > 2 * Math.PI){
                rotStep = -rotStep;
            }
        }
        if(doTranslateBoolean){
            tx += vx;
            if(tx < 0 || tx > SCREEN_W){
                vx = -vx;
            }
            ty += vy;
            if(ty < 0 || ty > SCREEN_H){
                vy = -vy;
            }
        }   
        if(doXShearBoolean){
            sx += sxStep;
            if(Math.abs(sx) > 2.0){
                sxStep = -sxStep;
            }
        }
        if(doYShearBoolean){     
            sy += syStep;
            if(Math.abs(sy) > 2.0){
                syStep = -syStep;
            }
        }
        for(int i=0; i < worldVector2fArray.length; ++i){
            worldVector2fArray[i].shear(sx,sy);
            worldVector2fArray[i].scale(scale, scale);
            worldVector2fArray[i].rotate(rot);
            worldVector2fArray[i].translate(tx,ty);
        }
    }
//    缓存技术，包含设计画面
    private void renderFrame(){
        do{
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
//                    具体图形相应行为，然后逻辑处理
                    render(g);
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    if (g != null) {
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());;
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
        }while(bufferStrategy.contentsLost());
    }
//    设置画面
    private void render(Graphics g) {
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        g.setColor(Color.GREEN);
        frameRate.calculate();
        g.drawString(frameRate.getFrameRate(), 20, 20);
        g.drawString("Translate(T): "+doTranslateBoolean, 20, 35);
        g.drawString("Rolate(R) : "+doRotateBoolean, 20, 50);
        g.drawString("Scale(S) : "+doScaleBoolean, 20, 65);
        g.drawString("X-Shear(X) : "+doXShearBoolean, 20, 80);
        g.drawString("Y-Shear(Y) : "+doYShearBoolean, 20, 95);
        g.drawString("Press [SPACE] to reset", 20, 110);
        Vector2f S = worldVector2fArray[worldVector2fArray.length - 1];
        Vector2f P = null;
        for(int i=0; i< worldVector2fArray.length; ++i){
            P = worldVector2fArray[i];
            g.drawLine((int)S.x, (int)S.y, (int)P.x, (int)P.y);
            S = P;
        }
    }
    private void sleep(long sleep){
        try {
            Thread.sleep(sleep);
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
