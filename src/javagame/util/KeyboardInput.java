package javagame.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyboardInput implements KeyListener{
    
    private boolean[] keysBoolean;
    /**
     * To update the KeyboardInput class to keep track of initial key presses as well as the keyboard 
     * state,an array of integer values is added,These values will keep track of how 
     * many frames the key has been pressed.The code implementing the keyListener interface 
     * doesn't change at all,but the keyDown() method no longer pulls the value from the 
     * boolean keysBoolean array. 
     */
    private int[] polledInt;
    
    public KeyboardInput(){
        keysBoolean=new boolean[256];
        polledInt=new int[256];
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
    public boolean keyDown(int keyCodeInt){
        return polledInt[keyCodeInt]>0;
    }
    public boolean keyDownOnce(int keyCode){
        return polledInt[keyCode]==1;
    }
    /**
     * The poll() method,synchronized to protected the shared keys array,transfers the keyboard 
     * state from the boolean array to the integer array.If the key is down,the value is incremented,
     * otherwise it is set back to zero.The keyDown() method now checks if the value is not 
     * zero and a new method,keyDownOnce(),returns true when the value is exactly one.
     */
    public synchronized void poll(){
        for(int i=0;i<keysBoolean.length;++i){
            if(keysBoolean[i]){
                polledInt[i]++;
            }else{
                polledInt[i]=0;
            }
        }
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
//        not needed
    }

}
