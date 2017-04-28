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
public class RelativeMouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {

    private static final int BUTTON_COUNT = 3;
    private boolean[] mouseBooleanArray;
    private Point currentPosPoint;
    private boolean relativeBoolean;
    private Component component;
    private int dxInt, dyInt;
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
     * <p>
     * Note that some platforms require special privileges or extensions to
     * access low-level input control. If the current platform configuration
     * does not allow input control, an <code>AWTException</code> will be thrown
     * when trying to construct Robot objects. For example, X-Window systems
     * will throw the exception if the XTEST 2.2 standard extension is not
     * supported (or not enabled) by the X server.
     * <p>
     * Applications that use Robot for purposes other than self-testing should
     * handle these error conditions gracefully.
     *
     * @author Robi Khan
     * @since 1.3
     */
    private Robot robot;

    public RelativeMouseInput(Component component) {
        mouseBooleanArray = new boolean[BUTTON_COUNT];
        this.component = component;
        /**
         * To accomplish this,
         * the Robot class is used to keep the mouse in the center of the window.
         */
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println(e);
        }
        currentPosPoint = new Point( 0 , 0);
    }

    public synchronized void mousePressed(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if (buttonInt >= 0 && buttonInt < mouseBooleanArray.length) {
            mouseBooleanArray[buttonInt] = true;
        }
    }

    public synchronized void mouseReleased(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if (buttonInt >= 0 && buttonInt < mouseBooleanArray.length) {
            mouseBooleanArray[buttonInt] = false;
        }
    }

    public void mouseClicked(MouseEvent e) {
        // Not needed
    }

    public synchronized void mouseEntered(MouseEvent e) {
        // 2
        // mouseMoved(e);
        // 33
    }

    public synchronized void mouseExited(MouseEvent e) {
        // 2
        // mouseMoved(e);
        // 33
    }

    /**
     * A flag is added to the RelativeMouseInput class to allow both relative
     * and absolute mouse movement. It is possible that a game will need both
     * absolute and relative mouse modes at some point, so the class allows
     * switching this at runtime. New code has been added to the mouseMoved()
     * method. If in relative mode,the distance is computed as the difference
     * from the center and then the mouse cursor is re-centered. Because the
     * mouse coordinates and component coordinates are both relative values,
     * there is no need to convert these values.
     */
    public synchronized void mouseMoved(MouseEvent e) {
        if (isRelative()) {
            // 相对
            Point p = e.getPoint();
            // 自己的方法
            Point center = getComponentCenter();
            dxInt += p.x - center.x;
            dyInt += p.y - center.y;
            // 2
            // centerMouse();
            // 33
        } else {
            currentPosPoint = e.getPoint();
        }
    }

    public synchronized void mouseDragged(MouseEvent e) {
        // 2
        // mouseMoved(e);
        // 33
    }

    public synchronized void mouseWheelMoved(MouseWheelEvent e) {
        // 2
        // 33
    }

    public boolean isRelative() {
        return relativeBoolean;
    }

    /**
     * The center of the window is computed at runtime so even if the window is
     * resized, the mouse will stay in the center. The relative center point
     * must be converted to absolute screen coordinates. The upper left pixel in
     * a window is (0,0) no matter where the window is located on the desktop.
     * If moving the window around the desktop changed the value of the upper
     * left pixel, graphics programming would be very difficult. Although it
     * makes drawing easier by using relative pixel values, positioning the
     * mouse to the relative center of the window does not take into account the
     * location of the window and could place the mouse cursor so far away from
     * the window that it would stop receiving mouse events. Converting to
     * screen coordinates using the SwingUtilities class solves this problem.
     * 
     * @return
     */
    public Point getComponentCenter() {
        int w = component.getWidth();
        int h = component.getHeight();
        return new Point(w / 2, h / 2);
    }

    private void centerMouse() {
         if( robot != null && component.isShowing() ){
             Point centerPoint = getComponentCenter();
             SwingUtilities.convertPointToScreen( centerPoint ,component );
             robot.mouseMove(centerPoint.x, centerPoint.y);
         }
    }
}