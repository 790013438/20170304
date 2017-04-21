package javagame.render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.FrameRate;

public class ActiveRenderingExample extends JFrame implements Runnable{

    private BufferStrategy bufferStrategy;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    private FrameRate frameRate;
    
    public ActiveRenderingExample(){
        frameRate=new FrameRate();
    }

    public static void main(String[] args) {
        final ActiveRenderingExample activeRenderingExample = new ActiveRenderingExample();
        activeRenderingExample.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                activeRenderingExample.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 自己类的绘制
                activeRenderingExample.createAndShowGUI();
            }
        });
    }

    public void onWindowClosing() {
        // 处理线程退出
        runningVolatileBoolean=false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.exit(0);
    }

    // 设置子组件大小，window窗口会根据内部的组件调整。只用一次
    protected void createAndShowGUI() {
        /**
         * The Window class and the Canvas class are the two classes available
         * to allow the creation of a BufferStrategy. By adding the canvas,you
         * can access the buffer strategy and force the size of the canvas to
         * exactly the size required, just like the JPanel example in the Hello
         * World application.
         */
        Canvas canvas = new Canvas();
        canvas.setSize(320, 240);
        canvas.setBackground(Color.GRAY);
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setTitle("Active Rendering");
        // Because the render loop does all the painting for this
        // application,the setIgnoreRepaint() flag can be set on the JFrame.
        setIgnoreRepaint(true);
        pack();
        /**
         * Causes this Window to be sized to fit the preferred size and layouts
         * of its subcomponents. The resulting width and height of the window
         * are automatically enlarged if either of dimensions is less than the
         * minimum size as specified by the previous call to the
         * {@code setMinimumSize} method.
         * <p>
         * If the window and/or its owner are not displayable yet, both of them
         * are made displayable before calculating the preferred size. The
         * Window is validated after its size is being calculated.
         *
         * @see Component#isDisplayable
         * @see #setMinimumSize
         */
        setVisible(true);
        /**
         * There is a weird error that happens if the BufferStrategy is created
         * before the window has been laid out: Exception in thread
         * "AWT-EventQueue-0" java.lang.IllegalStateException:Component must
         * have a valid peer 组件必须有一个有效的对等体 Calling the pack() method can solve
         * this.Be aware if the previous exception is thrown;make sure the
         * Component is visible or has been packed before attempting to create
         * the BufferStrategy.
         */
        canvas.createBufferStrategy(2);
        bufferStrategy=canvas.getBufferStrategy();
//      implements Runnable        
        gameThread = new Thread(this);
        gameThread.start();

    }
    public void run(){
        runningVolatileBoolean=true;
        frameRate.initialize();
        while(runningVolatileBoolean){
//            游戏画面处理
            gameLoop();
        }
    }
    public void gameLoop(){
//        后续章节会讲,render thread
        /**
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
//      22
      System.out.print("前：");
      System.out.println(bufferStrategy.contentsLost());
//      33
        do{          
            do{
                Graphics g=null;
                try{
                    g=bufferStrategy.getDrawGraphics();
                    g.clearRect(0,0,getWidth(),getHeight());
//                    具体内容
                    render(g);
                }catch(Exception e){                    
                }finally{
                    if(g!=null){
                        g.dispose();
                    }
                }
            }while(bufferStrategy.contentsRestored());
//            上次漏掉导致没有内容显示
            bufferStrategy.show();
        }while(bufferStrategy.contentsLost());
//      22
      System.out.print("后：");
      System.out.println(bufferStrategy.contentsLost());
//      33
    }
    private void render(Graphics g){
        frameRate.calculate();
        g.setColor(Color.GREEN);
        g.drawString(frameRate.getFrameRate(), 30, 30);
    }
}
