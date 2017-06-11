package javagame.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;

public class RelativeMouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {

    private boolean[] buttonBooleanArray;
    private final static int BUTTON_COUNT = 3;
    private Point currentPosPoint;
    private int notchesInt;
    private int[] polledInt;
    private int polledNotchesInt;
    private Point mousePosPoint;
    private boolean relativeBoolean;
    private int dxInt, dyInt;
    private Component component;
    private Robot robot;

    public RelativeMouseInput (Component component) {
        buttonBooleanArray = new boolean[BUTTON_COUNT];
        polledInt = new int[BUTTON_COUNT];
        currentPosPoint = new Point(0, 0);
        mousePosPoint = new Point(0, 0);
        this.component = component;
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public synchronized void mousePressed(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if(buttonInt >= 0 && buttonInt < buttonBooleanArray.length) {
            buttonBooleanArray[buttonInt] = true;
        }
    }

    public synchronized void mouseReleased(MouseEvent e) {
        int buttonInt = e.getButton() - 1;
        if (buttonInt >= 0 && buttonInt < buttonBooleanArray.length) {
            buttonBooleanArray[buttonInt] = false;
        }
    }

    public void mouseClicked(MouseEvent e) {
        //Not needed
    }

    public synchronized void mouseEntered (MouseEvent e) {
        mouseMoved(e);
    }

    public synchronized void mouseExited (MouseEvent e) {
        mouseMoved(e);
    }

    public synchronized void mouseDragged (MouseEvent e) {
        mouseMoved(e);
    }

    public synchronized void mouseMoved (MouseEvent e) {
        if (isRelative()) {
            Point p = e.getPoint();
            //类的方法
            Point center = getComponentCenter();
            dxInt += p.x - center.x;
            dyInt += p.y - center.y;
            centerMouse();
        } else {
            currentPosPoint = e.getPoint();
        }
    }

    public synchronized void mouseWheelMoved (MouseWheelEvent e) {
        notchesInt += e.getWheelRotation();
    }

    public boolean isRelative () {
        return relativeBoolean;
    }

    public void setRelative(boolean relativeBoolean) {
        this.relativeBoolean = relativeBoolean;
    }

    public synchronized void poll() {
        if (isRelative()) {
            mousePosPoint = new Point(dxInt, dyInt);
        } else {
            mousePosPoint = new Point(currentPosPoint);
        }
        dxInt = dyInt = 0;
        
        polledNotchesInt = notchesInt;
        notchesInt = 0;
        
        for (int i = 0; i < buttonBooleanArray.length; ++i) {
            if (buttonBooleanArray[i]) {
                polledInt[i]++;
            } else {
                polledInt[i] = 0;
            }
        }
    }

    public boolean buttonDown (int keyInt) {
        return polledInt[keyInt - 1] > 0;
    }

    public boolean buttonDownOnce(int keyInt) {
        return polledInt[keyInt - 1] == 1;
    }

    public int getNotches() {
        return polledNotchesInt;
    }

    public Point getPosition() {
        return mousePosPoint;
    }

    private Point getComponentCenter() {
        int w = component.getWidth();
        int h = component.getHeight();
        return new Point(w / 2, h / 2);
    }

    public void centerMouse() {
        if ( robot != null && component.isShowing()) {
            Point center = getComponentCenter();
            SwingUtilities.convertPointToScreen(center, component);
            robot.mouseMove(center.x, center.y);
        }
    }
}
