package javagame.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

public class RelativeMouseInput implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    private boolean[] buttonsBooleanArray;
    private final static int BUTTON_COUNT = 3;
    private int notchesInt;
    private Point currentPosPoint;
    private int[] polledInt;
    private int polledNotchesInt;
    private Point mousePosPoint;
    private boolean relativeBoolean;
    private Component component;
    private int dxInt, dyInt;
    private Robot robot;
    
    public RelativeMouseInput(Component component) {
        buttonsBooleanArray = new boolean[BUTTON_COUNT];
        polledInt = new int[BUTTON_COUNT];
        currentPosPoint = new Point(0, 0);
        mousePosPoint = new Point(0, 0);
        this.component = component;
        try {
            robot = new Robot();
        } catch( Exception e ) {
            System.out.println(e);
        }
    }
    
    public synchronized void mousePressed(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if( buttonInt >= 0 && buttonInt < buttonsBooleanArray.length ) {
            buttonsBooleanArray[buttonInt] = true;
        }
    }
    
    public synchronized void mouseReleased(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if(buttonInt >= 0 && buttonInt < buttonsBooleanArray.length ) {
            buttonsBooleanArray[buttonInt] = false;
        }
    }
    
    public void mouseClicked(MouseEvent e) {
//        Not needed
    }
    
    public synchronized void mouseEntered(MouseEvent e) {
        mouseMoved(e);
    }
    
    public synchronized void mouseExited(MouseEvent e) {
        mouseMoved(e);
    }
    
    public synchronized void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }
    
    /*
     *New code has been added to the mouseMoved() method.
     *If in relative mode,the distance is computed as the difference from the center and then the mouse cursor is re-centered.
     *Because the mouse coordinates and component coordinates are both relative values,
     *there is no need to convert these values.
     * Finally,during the poll method,the mouse position can be either relative or absolute.
     * The delta variables are reset inside the poll method along with all the other variables.
     */
    public synchronized void mouseMoved(MouseEvent e) {
        if ( isRelative() ) {
            Point p = e.getPoint();
//            类的方法
            Point center = getComponentCenter();
            dxInt += p.x - center.x;
            dyInt += p.y - center.y; 
            centerMouse();
        } else {
            currentPosPoint = e.getPoint();
        }
        
    }
    
    public synchronized void mouseWheelMoved(MouseWheelEvent e) {
        notchesInt += e.getWheelRotation();
    }
    
    public void poll() {
        if(false) {
            mousePosPoint = new Point(dxInt, dyInt);
        } else {
            mousePosPoint = new Point(currentPosPoint); 
        }
        
        polledNotchesInt= notchesInt;
        notchesInt = 0;
        
        for( int i = 0; i < buttonsBooleanArray.length; ++i ) {
            if(buttonsBooleanArray[i]) {
                polledInt[i]++;
            } else {
                polledInt[i] = 0;
            }
        }
    }
    
    public boolean buttonDown(int buttonInt) {
        return polledInt[buttonInt - 1] > 0;
    }
    
    public boolean buttonDownOnce(int buttonInt) {
        return polledInt[buttonInt - 1] == 1;
    }
    
    public int getNotches() {
        return polledNotchesInt;
    }
    
    public Point getPosition() {
        return mousePosPoint;
    }
    
    public boolean isRelative() {
         return relativeBoolean;
    }
    
    public Point getComponentCenter() {
        int w = component.getWidth();
        int h = component.getHeight();
        return new Point(w / 2, h / 2);
    }
    /**
     * If in relative mode,the distance is computed as the difference 
     * from the center and then the mouse cursor is re-centered.
     * Because the mouse coordinates and component coordinates are both relative values,
     * there is no need to convert these values.
     * IIt is important to realize that re-centering the mouse works because when the mouse's 
     * new position and the current position are the same,asking the Robot class to reposition 
     * the mouse to the same location does not generate new mouse events.
     * If this behavior ever changes,the relative mouse class will always generate mouse events,
     * even when the mouse isn't moving.
     * Checking if the current and new positions are different before 
     * requesting a re-center would solve this problem if the mouse behavior ever chnges in the future. 
     */
    public void centerMouse() {
        if( robot != null && component.isShowing() ) {
            Point centerPoint = getComponentCenter();
            /**
             * Although it makes drawing easier by using relative pixel values,
             * positioning the mouse to the relative center of the window does
             * not take into account the location of the window and could place
             * the mouse cursor so far away from the window that is would stop
             * receiving mouse events.
             * Converting to screen coordinates using the SwingUilities class solves this problem.
             */
            SwingUtilities.convertPointToScreen(centerPoint, component);
            robot.mouseMove(centerPoint.x, centerPoint.y);
        }
    }
    
    public void setRelative(boolean relativeBoolean) {
        this.relativeBoolean = relativeBoolean;
        if(relativeBoolean) {
            centerMouse();
        }
    }
    
}