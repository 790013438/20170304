package javagame.timeandspace;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ViewportRatio extends JFrame{

    public static void main(String[] args) {
        final ViewportRatio viewportRatio = new ViewportRatio();
        viewportRatio.addWindowListener(new WindowAdapter() {
            public void windowClosing() {
//          processing exit
                viewportRatio.onWindowClosing();
//                233
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
           public void run() {
//             Create graphics
               viewportRatio.createAndShowGUI();
//               233
           }
        });
    }
//  processing exit
    protected void onWindowClosing() {
//        233
        System.exit(0);;
    }
//  设置背景
    public void createAndShowGUI() {
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        /**
         * Because the application is handling the drawing,
         * there is no need to respond to repaint methods.
         * The Component.setIgnoreRepaint() method takes care of ignoring extra paint messages.
         */
        canvas.setIgnoreRepaint(true);
        getContentPane().setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        setTitle("Vieport Ratio");
        setSize(640,640);
        getContentPane().add(canvas);
//        add KeyListener;
    }
}
