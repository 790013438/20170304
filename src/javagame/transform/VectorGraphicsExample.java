package javagame.transform;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;
import javagame.util.RelativeMouseInput;

public class VectorGraphicsExample extends JFrame{
    
    private KeyboardInput keyboardInputBoolean;
    private RelativeMouseInput relativeMouseInputBoolean;
    
    public static void main(String[] args){
        final VectorGraphicsExample vectorGraphicsExample = new VectorGraphicsExample();
        vectorGraphicsExample.addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent e){    
//               2
//             processing exit
               vectorGraphicsExample.onWindowShutdown();
//               33
           }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//                2
//              Create graphics
//                vectorGraphicsExample.createAndShowGUI();
//                33
            }
        });
    }
//    processing exit
    protected void onWindowShutdown(){        
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
//        2
//        Add mouse listener
//        relativeMouseInputBoolean = new RelativeMouseInput();
//        33
//        2
//        33
    }

}
