package javagame.render;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.print.attribute.standard.PrinterLocation;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.FrameRate;

public class FullScreenRenderingExample extends JFrame implements Runnable{
    
    private GraphicsDevice graphicsDevice;
    private DisplayMode currentDisplayMode;
    private BufferStrategy bufferStrategy;
    private volatile boolean runningVolatileBoolean;
    private Thread gameThread;
    private FrameRate frameRate;
    
    public FullScreenRenderingExample(){
        frameRate=new FrameRate();
    }
    
    public static void main(String[]args){
        final FullScreenRenderingExample fullScreenRenderingExample=new FullScreenRenderingExample();
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//                自己类的方法createAndShowGUI创建图形界面
                fullScreenRenderingExample.createAndShowGUI();
            }
        });
    }
    /**
     * 创建图形界面，行为逻辑处理在创建过程中new Thread()的线程里
     */
    public void createAndShowGUI(){
        setIgnoreRepaint(true);
        setUndecorated(true);
        setBackground(Color.BLACK);   
        GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice=graphicsEnvironment.getDefaultScreenDevice();
        currentDisplayMode=graphicsDevice.getDisplayMode();
        if(!graphicsDevice.isFullScreenSupported()){
            System.err.println("ERROR:Not Supported!!!");
            System.exit(0);
        }
        graphicsDevice.setFullScreenWindow(this);
//        自己写的类
        graphicsDevice.setDisplayMode(getDisplayMode());
        /**
         * 缓存的创建,得到frame的
         */
        createBufferStrategy(2);
        bufferStrategy=getBufferStrategy();
        /**
         * Because the JFrame is undecorated,there are no controls to close the window.
         * The following code will shut down the application when users press the Escape key.
         */
        this.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
//                    自己写的类
                    shutDown();
                }
            }
        });
//        线程处理行为，逻辑
        gameThread=new Thread(this);
        gameThread.start();
    }
    /**
     * 关闭
     * Because there is no way to close the window,there is no need for a window listener.
     * When the Escape key is pressed and the example is shut down,the display mode is 
     * returned to normal after the game loop is shut down.
     */
    protected void shutDown(){
        try{    
            runningVolatileBoolean=false;
            gameThread.join();
            System.out.println("Game loop stopped!!!");
            graphicsDevice.setDisplayMode(currentDisplayMode);
            graphicsDevice.setFullScreenWindow(null);
            System.out.println("Display Restored...");
        }catch(Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }
    /**
     * In this example,the display mode is hard coded to 800 x 600,32 bit for simplicity.In 
     * an actual production application,the available display modes would be enumerated as 
     * shown in the previous example.Make sure you change this code if this display mode 
     * isn't supported on your system.
     * @return
     */
    private DisplayMode getDisplayMode(){
        return new DisplayMode(800,600,32,DisplayMode.REFRESH_RATE_UNKNOWN);
    }
    public void run(){
        runningVolatileBoolean=true;
//        帧相对的
        frameRate.initialize();
        while(runningVolatileBoolean){
//        具体处理
            gameLoop();
        }
    }
    /**
     * 缓存技术
     * The <code>BufferStrategy</code> class represents the mechanism with which
     * to organize complex memory on a particular <code>Canvas</code> or
     * <code>Window</code>.  Hardware and software limitations determine whether and
     * how a particular buffer strategy can be implemented.  These limitations
     * are detectable through the capabilities of the
     * <code>GraphicsConfiguration</code> used when creating the
     * <code>Canvas</code> or <code>Window</code>.
     * <p>
     * It is worth noting that the terms <i>buffer</i> and <i>surface</i> are meant
     * to be synonymous: an area of contiguous memory, either in video device
     * memory or in system memory.
     * <p>
     * There are several types of complex buffer strategies, including
     * sequential ring buffering and blit buffering.
     * Sequential ring buffering (i.e., double or triple
     * buffering) is the most common; an application draws to a single <i>back
     * buffer</i> and then moves the contents to the front (display) in a single
     * step, either by copying the data or moving the video pointer.
     * Moving the video pointer exchanges the buffers so that the first buffer
     * drawn becomes the <i>front buffer</i>, or what is currently displayed on the
     * device; this is called <i>page flipping</i>.
     * <p>
     * Alternatively, the contents of the back buffer can be copied, or
     * <i>blitted</i> forward in a chain instead of moving the video pointer.
     * <pre>{@code
     * Double buffering:
     *
     *                    ***********         ***********
     *                    *         * ------> *         *
     * [To display] <---- * Front B *   Show  * Back B. * <---- Rendering
     *                    *         * <------ *         *
     *                    ***********         ***********
     *
     * Triple buffering:
     *
     * [To      ***********         ***********        ***********
     * display] *         * --------+---------+------> *         *
     *    <---- * Front B *   Show  * Mid. B. *        * Back B. * <---- Rendering
     *          *         * <------ *         * <----- *         *
     *          ***********         ***********        ***********
     *
     * }</pre>
     * <p>
     * Here is an example of how buffer strategies can be created and used:
     * <pre><code>
     *
     * // Check the capabilities of the GraphicsConfiguration
     * ...
     *
     * // Create our component
     * Window w = new Window(gc);
     *
     * // Show our window
     * w.setVisible(true);
     *
     * // Create a general double-buffering strategy
     * w.createBufferStrategy(2);
     * BufferStrategy strategy = w.getBufferStrategy();
     *
     * // Main loop
     * while (!done) {
     *     // Prepare for rendering the next frame
     *     // ...
     *
     *     // Render single frame
     *     do {
     *         // The following loop ensures that the contents of the drawing buffer
     *         // are consistent in case the underlying surface was recreated
     *         do {
     *             // Get a new graphics context every time through the loop
     *             // to make sure the strategy is validated
     *             Graphics graphics = strategy.getDrawGraphics();
     *
     *             // Render to graphics
     *             // ...
     *
     *             // Dispose the graphics
     *             graphics.dispose();
     *
     *             // Repeat the rendering if the drawing buffer contents
     *             // were restored
     *         } while (strategy.contentsRestored());
     *
     *         // Display the buffer
     *         strategy.show();
     *
     *         // Repeat the rendering if the drawing buffer was lost
     *     } while (strategy.contentsLost());
     * }
     *
     * // Dispose the window
     * w.setVisible(false);
     * w.dispose();
     * </code></pre>
     *
     * @see java.awt.Window
     * @see java.awt.Canvas
     * @see java.awt.GraphicsConfiguration
     * @see VolatileImage
     * @author Michael Martak
     * @since 1.4
     */
    public void gameLoop(){
        do{            
            do{
                Graphics g=null;
                try{              
                    g=bufferStrategy.getDrawGraphics();
                    g.clearRect(0, 0, getWidth(), getHeight());
//                    具体图形相应行为，然后逻辑处理
                    render(g);
                }catch(Exception e){
                    System.out.println(e);
                }finally{
                    if(g!=null){
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());
            /**
             * 很重要的一句
             */
            bufferStrategy.show();
        }while(bufferStrategy.contentsLost());
    }
    private void render(Graphics g){
        frameRate.calculate();
        g.setColor(Color.GREEN);
        g.drawString(frameRate.getFrameRate(), 30, 30);
        g.drawString("Press ESC to exit...", 30, 60);
    }

}
