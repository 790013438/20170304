package javagame.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener{

    private boolean[] keysBooleanArray;
    private int[] polledInt;

    public KeyboardInput() {
        keysBooleanArray = new boolean[256];
        polledInt = new int[256];
    }

    public synchronized void keyPressed (KeyEvent e) {
        int keyCodeInt = e.getKeyCode();
        if (keyCodeInt >= 0 && keyCodeInt < keysBooleanArray.length) {
            keysBooleanArray[keyCodeInt] = true;
        }
    }

    public synchronized void keyReleased (KeyEvent e) {
        int keyCodeInt = e.getKeyCode();
        if (keyCodeInt >= 0 && keyCodeInt < keysBooleanArray.length) {
            keysBooleanArray[keyCodeInt] = false;
        }
    }

    public void keyTyped (KeyEvent e) {
        //Not needed
    }

    public synchronized void poll() {
        for(int i = 0; i < keysBooleanArray.length; ++i) {
            if(keysBooleanArray[i]) {
                polledInt[i]++;
            } else {
                polledInt[i] = 0;
            }
        }
    }

    public boolean keyDown(int keyCode) {
        return polledInt[keyCode] > 0;
    }

    public boolean keyDonwOnce(int keyCode) {
        return polledInt[keyCode] == 1;
    }

}
