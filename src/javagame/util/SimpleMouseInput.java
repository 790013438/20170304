package javagame.util;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class SimpleMouseInput implements MouseListener,MouseMotionListener,MouseWheelListener{
    
    private boolean[] mouseBooleanArray;
    private Point mousePosPoint;
    private Point currentPosPoint;
    private static final int BUTTON_COUNT=3;
    private int notchesInt;
    private int polledNotchesInt;
    private int[] polledIntArray;
    
    public SimpleMouseInput(){
        mousePosPoint=new Point(0,0);
        currentPosPoint=new Point(0,0);
        mouseBooleanArray=new boolean[BUTTON_COUNT];
        polledIntArray=new int[BUTTON_COUNT];
    }
    
    /**
     * Server, it is not possible for two invocations of synchronized methods on the same object to interleave.
     * When one thread is executing a synchronized method for an object, all other 
     * threads that invoke synchronized methods for the same object block (suspend execution) until the first thread is done with the object.
     * Second, when a synchronized method exits, it automatically establishes a happens-before relationship with any subsequent invocation of 
     * a synchronized method for the same object.
     * This guarantees that changes to the state of the object are visible to all threads.
     * Just like the keyboardInputBoolean 
     * listener,the pressed and released methods track the state of the relativeMouseInputBoolean buttons,
     * while the clicked method is ignored.To determine which relativeMouseInputBoolean button was pressed,
     * the relativeMouseInputBoolean event contains the following method:public int MouseEvent.getButton()
     * The button number starts at one instead of zero,which represents no button,and it is 
     * necessary to subtract one from the button number to reference the relativeMouseInputBoolean button array.
     * @param e
     */
    public synchronized void mousePressed(MouseEvent e){
        int buttonInt=e.getButton()-1;
        if(buttonInt>=0&&buttonInt<mouseBooleanArray.length){
            mouseBooleanArray[buttonInt]=true;
        }
    }
    public synchronized void mouseReleased(MouseEvent e){
        int buttonInt=e.getButton()-1;
        if(buttonInt>=0&&buttonInt<mouseBooleanArray.length){
            mouseBooleanArray[buttonInt]=false;
        }
    }
    public void mouseClicked(MouseEvent e){
//        Not needed
    }
    /**
     * All four of these methods-entered,exited,dragged,and moved-capture the position 
     * of the relativeMouseInputBoolean while informing the program if teh relativeMouseInputBoolean is entering or leaving the component 
     * listening for relativeMouseInputBoolean events.The following method gets the current position of the relativeMouseInputBoolean
     */
    public synchronized void mouseEntered(MouseEvent e){
        mouseMoved(e);
    }
    public synchronized void mouseExited(MouseEvent e){
        mouseMoved(e);
    }
    public synchronized void mouseDragged(MouseEvent e){
        mouseMoved(e);
    }
    public synchronized void mouseMoved(MouseEvent e){  
        currentPosPoint=e.getPoint();
    }
    /**
     * The following method returns the clicks of the clicks of the relativeMouseInputBoolean wheel.If the number is negative,
     * the wheel has been moved away from the user.If the value is positive,the wheel has 
     * been moved toward the user.
     */
    public synchronized void mouseWheelMoved(MouseWheelEvent e){ 
        notchesInt+=e.getWheelRotation();
    }
    public synchronized void poll(){
        mousePosPoint=new Point(currentPosPoint);
        polledNotchesInt=notchesInt;
        notchesInt=0;
        for(int i=0;i<mouseBooleanArray.length;++i){
            if(mouseBooleanArray[i]){
                polledIntArray[i]++;
            }else{
                polledIntArray[i]=0;
            }
        }
    }
    public Point getPosition(){
        return mousePosPoint;
    }
    public int getNotches(){
        return polledNotchesInt;
    }
    public boolean buttonDown(int button){
        return polledIntArray[button-1]>0;
    }
    public boolean buttonDownOnce(int button){
        return polledIntArray[button-1]==1;
    }

}
