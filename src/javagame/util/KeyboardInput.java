package javagame.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
 * 为熟悉结构重写
 */
public class KeyboardInput implements KeyListener {
    
    private boolean[] keysBooleanArray;
    /**
     * It is difficult to test when a key is prssed for the first time.
     * If 20 keys need to be tracked,and some of those change behavior based on the game state,
     * it could become a very messy problem trying to keep track of all those states using code similar to this:
     * SimpleKeyboardExample.java
     * if(keys.keyDown(KeyEvent.VK_SPACE)){
     *     if(!space){
     *         System.out.println("VK_SPACE");
     *     }
     *     space = true;
     * } else {
     *     space = false;
     * }
     * To update the KeyboardInput class to keep track of initial key presses as well as the keyboard 
     * state,an array of integer values is added,These values will keep track of how 
     * many frames the key has been pressed.The code implementing the keyListener interface 
     * doesn't change at all,but the keyDown() method no longer pulls the value from the 
     * boolean keysBoolean array. 
     */
    private int[] polledInt;
    
    public KeyboardInput(){
        keysBooleanArray = new boolean[256];
        polledInt = new int[256];
    }
    
    public synchronized void keyPressed(KeyEvent e){   
        int keyCodeInt = e.getKeyCode();
        if(keyCodeInt >= 0 && keyCodeInt <= keysBooleanArray.length ){
            keysBooleanArray[keyCodeInt] = true;
        }
    }
    public synchronized void keyReleased(KeyEvent e){  
        int keyCodeInt = e.getKeyCode();
        if(keyCodeInt >= 0 && keyCodeInt <= keysBooleanArray.length ){
            keysBooleanArray[keyCodeInt] = false;
        }
    }
    public void keyTyped(KeyEvent e){
//        Not needed
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
    public boolean keyDown(int keyInt){
        return polledInt[keyInt] > 0;
    }
    
    public boolean keyDownOnce(int keyInt){
        return polledInt[keyInt] == 1;
    }
    /**
     * The poll() method,synchronized to protected the shared keys array,transfers the keyboard 
     * state from the boolean array to the integer array.If the key is down,the value is incremented,
     * otherwise it is set back to zero.The keyDown() method now checks if the value is not 
     * zero and a new method,keyDownOnce(),returns true when the value is exactly one.
     */
    public synchronized void poll(){
        for(int i = 0;i < keysBooleanArray.length;++i){
            if(keysBooleanArray[i]){
                polledInt[i]++;
            } else {
                polledInt[i] = 0;
            }
        }
    }
}