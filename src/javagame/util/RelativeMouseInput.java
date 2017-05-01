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

/**
 * 重新写，为了熟悉结构
 */
/**
 * At first it may seem obvious, but the program only receives mouse events when
 * the mouse is in the window. Once the mouse leaves the window,the coordinates
 * where the mouse pointer is are no longer valid in the application. To make
 * this worse,when in full screen mode, the mouse simple stops when it reaches
 * the edge of the screen and doesn't continue registering events. Depending on
 * the needs of the application,relative mouse movement may be needed.
 * 
 * @author 79001
 *
 */
public class RelativeMouseInput implements MouseListener, MouseMotionListener, MouseWheelListener{
    
    private boolean[] mouseBooleanArray;
    private static final int BUTTON_COUNT = 3;
    private boolean relativeBoolean;
    private Point currentPosPoint;
    private int notchesInt;
    private Point mousePosPoint;
    private int dxInt, dyInt;
    private int polledNotchesInt;
    private int[] polledInt;
    /**
     * This class is used to generate native system input events for the
     * purposes of test automation, self-running demos, and other applications
     * where control of the mouse and keyboard is needed. The primary purpose of
     * Robot is to facilitate automated testing of Java platform
     * implementations.
     * <p>
     * Using the class to generate input events differs from posting events to
     * the AWT event queue or AWT components in that the events are generated in
     * the platform's native input queue. For example,
     * <code>Robot.mouseMove</code> will actually move the mouse cursor instead
     * of just generating mouse move events.
     * Applications that use Robot for purposes other than self-testing should
     * handle these error conditions gracefully.
     *
     * @author Robi Khan
     * @since 1.3
     */
    private Robot robot;
    private Component component;
    
    public RelativeMouseInput(Component component) {
        mouseBooleanArray = new boolean[BUTTON_COUNT];
        currentPosPoint = new Point(0, 0);
        mousePosPoint = new Point(0, 0);
        polledInt = new int[BUTTON_COUNT];
        /**
         * To acomplish this,
         * the Robot class is used to keep the mouse in the center of the window.
         * <p>
         * Note that some platforms require special privileges or extensions to
         * access low-level input control. If the current platform configuration
         * does not allow input control, an <code>AWTException</code> will be thrown
         * when trying to construct Robot objects. For example, X-Window systems
         * will throw the exception if the XTEST 2.2 standard extension is not
         * supported (or not enabled) by the X server.
         * <p>
         */
        try {
            robot = new Robot();
        } catch(Exception e) {
            System.out.println(e);
        }
        this.component = component;
    }
    
    /**
     * If the input was handled outside of the game loop,the state could change at any time.
     * Also,multiple keys may be down simultaneously,so handling each event by itself 
     * doesn't let the user combine keys.To simplify input processing,the keyboard events 
     * are saved and make available to the game loop.Finally,after using the synchronized keyword to protect the mouse state array 
     * when it is being accessed from multiple threads,it gives access to the current mouse 
     * state with the buttonDown(int button) method.
     * @param keyCodeInt
     * @return
     */
    
    public synchronized void mousePressed(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if( buttonInt >= 0 && buttonInt <= mouseBooleanArray.length ) {
            mouseBooleanArray[buttonInt] = true;
        }
    }
    
    public synchronized void mouseReleased(MouseEvent e) {   
        int buttonInt = e.getButton() - 1;
        if( buttonInt >= 0 && buttonInt <= mouseBooleanArray.length ) {
            mouseBooleanArray[buttonInt] = false;
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
    
    public synchronized void mouseMoved(MouseEvent e) {
        if( isRelative() ){
           Point p = e.getPoint();
//           类的方法
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
    
    public boolean isRelative() {
        return relativeBoolean;
    }
    
    public void setRelative(boolean relativeBoolean) {
        this.relativeBoolean = relativeBoolean;
        if( relativeBoolean ) {
            centerMouse();
        }
    }
    
    /**
     * If in relative mode,the distance is computed as the difference 
     * from the center and then the mouse cursor is re-centered.
     * Because the mouse coordiantes and component corrdiantes are both relative values,
     * there is no need to convert these values.
     * IIt is important to realize that re-centering the mouse works because when the mouse's 
     * new positiion and the current position are the same,asking the Robot class to reposition 
     * the mouse to the same location does not generate new mouse events.
     * If this behavior ever changes,the relative mouse class will always generate mouse events,
     * even when the mouse isn't moving.
     * Checking if the current and new positions are different before 
     * requesting a re-center would solve this problem if the mouse behavior ever chnges in the future. 
     */
    public void centerMouse() {
        if( robot != null && component.isShowing() ) {
//            自己人（类里）的方法
            Point centerPoint = getComponentCenter();
            /**
             * Althought it makes drawing easier by using relative pixel values,
             * positioning the mouse to the relative center of the window does
             * not take in ot account the lacation of the window and could place
             * the mouse cursor so far away from the window that is would stop
             * receivving mousee events.
             * Converting to screen cooordinates using the SwingUilities class solves this problem.
             */
            SwingUtilities.convertPointToScreen(centerPoint, component);
            robot.mouseMove(centerPoint.x, centerPoint.y);
        }
    }
    
    public Point getComponentCenter() {
        int w = component.getWidth();
        int h = component.getHeight();
        return new Point(w / 2, h / 2);
    }
    
    /**
     * Finally,during the poll method,the mouse position can be either rlative or absolute.
     * The delta variables are reset indide the poll method along with all the other variables.
     */
    public synchronized void poll() {
        if(isRelative()) {
            mousePosPoint = new Point(dxInt, dxInt);
        } else {
            mousePosPoint = new Point(currentPosPoint);
        }
        dxInt = dyInt = 0;
        
        polledNotchesInt = notchesInt;
        notchesInt = 0;
        
        for( int i = 0; i < mouseBooleanArray.length; ++i) {
            if( mouseBooleanArray[i] ) {
                polledInt[i]++;
            } else {
                polledInt[i] = 0;
            }
        }
    }
    
    public boolean buttonDown(int button) {
        return polledInt[button-1] > 0;
    }
    
    public boolean buttonDownOnce(int button) {
        return polledInt[button-1] == 1;
    }
    
    public int getNotches() {
        return polledNotchesInt;
    }
    
    public Point getPosition() {
        return mousePosPoint;
    }
    
}