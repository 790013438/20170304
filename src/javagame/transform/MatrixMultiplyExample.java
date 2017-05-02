package javagame.transform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MatrixMultiplyExample extends JFrame{
    
    private static final int SCREEN_W = 640;
    private static final int SCREEN_H = 480;

    public static void main(String[]args) {
        final MatrixMultiplyExample matrixMultipleExample = new MatrixMultiplyExample();
        matrixMultipleExample.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {  
//                2
//          processing exit
//                matrix3x3f.onWindowShutDonw();
//                233
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                2
//              Create graphics
//                matrix3x3f.createAndShowGUI();
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
//            2
//            233
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
    }
    
}
