/**
 * In order to create a full screen application,the display mode needs to be changed.The
 * DisplayModeTest uses Swing components.
 */
package javagame.render;

import java.awt.Container;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DisplayModeExample extends JFrame{
    
    private JComboBox displayModesComboBox;
    private GraphicsDevice graphicsDevice;
    private DisplayMode currentDisplayMode;
    
    /**
     * The constructor contains the code necessary to save the current display mode.This 
     * mode matches the display mode for the user's system before the software is launched.
     * This display mode will be used to leave the full screen mode when the application 
     * returns to windowed mode.
     */
    public DisplayModeExample(){
        /**
        *
        * The <code>GraphicsEnvironment</code> class describes the collection
        * of {@link GraphicsDevice} objects and {@link java.awt.Font} objects
        * available to a Java(tm) application on a particular platform.
        * The resources in this <code>GraphicsEnvironment</code> might be local
        * or on a remote machine.  <code>GraphicsDevice</code> objects can be
        * screens, printers or image buffers and are the destination of
        * {@link Graphics2D} drawing methods.  Each <code>GraphicsDevice</code>
        * has a number of {@link GraphicsConfiguration} objects associated with
        * it.  These objects specify the different configurations in which the
        * <code>GraphicsDevice</code> can be used.
        * @see GraphicsDevice
        * @see GraphicsConfiguration
        */
        GraphicsEnvironment graphicsEvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphicsDevice=graphicsEvironment.getDefaultScreenDevice();
        currentDisplayMode=graphicsDevice.getDisplayMode();
    }
    
    public static void main(String[]args){
        final DisplayModeExample displayModeExample=new DisplayModeExample();
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//                22
//                自己类的方法createAndShowGUI创建图形界面
//                333
//                22今天见识了新的分配数组的方法
//                ArrayClassExample[] arrayClassExample=new ArrayClassExample[1];
//                System.out.println(arrayClassExample[0]==null);
//                System.out.println(arrayClassExample.length);
//                33
            }
        });
    }
//    22今天见识了新的分配数组的方法
//    class ArrayClassExample{
//    }
//    33
//    创建图形界面并设置
    protected void createAndShowGUI(){
        Container canvasContainer=getContentPane();
//        22
//        canvas.add()JPanel内容，给窗户糊纸
//        33
    }
    /**JPanel a flat or curved component, typically rectangular, 
     * that forms or is set into the surface of a door, wall, or ceiling.
     * The getMainPanel() method creates the Swing components.These include a combo box
     * listing the available display modes,a button to switch to full screen mode,and 
     * another button to switch back to windowed mode.
     */
    private JPanel getMainPanel(){
        JPanel panel=new JPanel();
//        22
//        displayModesComboBox=new JComboBox();列出东西
//        333
        return panel;
    }
    /**
     * The listDisplayModes() method 
     * returns a list of display modes in the wrapper class discussed previously.
     * @author Floyd
     */
    private DisplayModeWrapper[] listDisplayModes(){
        ArrayList<DisplayModeWrapper>displayModeWrapperArrayList=new ArrayList<DisplayModeWrapper>();
        for(DisplayMode mode:graphicsDevice.getDisplayModes()){
            DisplayModeWrapper wrap=new DisplayModeWrapper(mode);
            if(!displayModeWrapperArrayList.contains(wrap)){
                displayModeWrapperArrayList.add(wrap);
            }
        }
        return displayModeWrapperArrayList.toArray(new DisplayModeWrapper[0]);
    }
//    an inner class called DisplayModeWrapper.
    /**
     * The wrapper class uses the equals() method to only compare the 
     * width and height of the display modes and ont the bit-depth or refresh rate.Depending 
     * on the operating system,there may be many modes that differ only by bit depth 
     * and refresh rate.
     * @author 79001
     *
     */
    class DisplayModeWrapper{        
        private DisplayMode displayMode;        
        public DisplayModeWrapper(DisplayMode dm){
            displayMode=dm;
        }        
        public boolean equals(Object obj){
            DisplayModeWrapper otherDisplayModeWrapper=(DisplayModeWrapper)obj;
            if(displayMode.getWidth()!=otherDisplayModeWrapper.displayMode.getWidth()){
                return false;
            }
            if(displayMode.getHeight()!=otherDisplayModeWrapper.displayMode.getHeight()){
                return false;
            }
            return true;
        }
        /**
         * Not only does the wrapper allow searching the list for modes matching the width and height,
         * it overrides the toString() method to produce an easy to read list of values in the combo box.
         */
        public String toString(){
            return ""+displayMode.getWidth()+" x "+displayMode.getHeight();
        }
    }

}
