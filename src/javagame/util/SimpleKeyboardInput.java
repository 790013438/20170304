package javagame.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * The problem is,the keyboard is a piece of hardware maintained by the operating system.
 * The operating system,and not the software,generate and dispatch keyboard 
 * events to whatever applications have focus.There is nothing stopping the user from 
 * switching from a game window to a web browser and checking email.Because of 
 * this,all the keyboard events arrive on a different thread and need to be made available 
 * to the game loop.
 * @author 79001
 *
 */
public class SimpleKeyboardInput implements KeyListener{
    /**
     * It keeps a boolean array of 256 keys,which are all the virtual key codes that need to be 
     * sampled.It stores the state of the key-true if down,false otherwise-in the key state 
     * array.
     */
    private boolean[]keysBoolean;
    
    public SimpleKeyboardInput(){
        keysBoolean=new boolean[256];
    }
    /**
     * If the input was handled outside of the game loop,the state could change at any time.
     * Also,multiple keys may be down simultaneously,so handling each event by itself 
     * doesn't let the user combine keys.To simplify input processing,the keyboard events 
     * are saved and make available to the game loop.Finally,after using the synchronized keyword to protect the key state array 
     * when it is being accessed from multiple threads,it gives access to the current key 
     * state with the keyDown(int keyCode) method.
     * @param keyCodeInt
     * @return
     */
    public synchronized boolean keyDown(int keyCodeInt){
//        22越界？？？
        return keysBoolean[keyCodeInt];
//        33
    }
    /**
     * When a key is pressed,the keyPressed() method is called.
     */
    public synchronized void keyPressed(KeyEvent e){
        int keyCode=e.getKeyCode();
        if(keyCode>=0&&keyCode<keysBoolean.length){
            keysBoolean[keyCode]=true;
        }
    }
    /**
     * As expected,the keyReleased() method is called when the same key is released.
     */
    public synchronized void keyReleased(KeyEvent e){   
        int keyCode=e.getKeyCode();
        if(keyCode>=0&&keyCode<keysBoolean.length){
            keysBoolean[keyCode]=false;
        }
    }
    /**
     * The keyTyped() method is called only 
     * after a key is pressed and released.The action keys and modifier keys,such as Shift or 
     * the arrow keys,do not generate a keyTyped() event.this event will be explored in 
     * Chapter 11,which covers text. 
     */
    public synchronized void keyTyped(KeyEvent e){
//        Not needed
    }

}
