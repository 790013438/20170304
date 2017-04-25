package javagame.input;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.KeyboardInput;

public class KeyboardExample extends JFrame implements Runnable{
    
    private KeyboardInput keyboardInputBoolean;
    private Thread gameThread;
    private volatile boolean runningVolatileBoolean;
    
    public KeyboardExample(){
        keyboardInputBoolean=new KeyboardInput();
    }

    public static void main(String[]args){
        final KeyboardExample keyboardExample=new KeyboardExample();
        keyboardExample.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent arg0){
//                自己写退出时的处理
                keyboardExample.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//                创建图形界面
                keyboardExample.createAndShowGUI();
            }
        });
    }
    protected void onWindowClosing(){
        try{           
            runningVolatileBoolean=false;
            gameThread.join();
        }catch(Exception e){
            System.out.println(e);
        }
        System.exit(0);
    }
    /**
     * 创建图形界面，行为逻辑处理在创建过程中new Thread()的线程里
     */
    protected void createAndShowGUI(){
        setTitle("Keyboard Input");
        setSize(320,240);
//        javagame.util包中的implements KeyListener
        addKeyListener(keyboardInputBoolean);
        setVisible(true);
//      线程处理行为，逻辑
        gameThread=new Thread(this);
        gameThread.start();
    }
    /**
     * The runnable interface contains a single method: public void run(). This 
     * method contains the render loop, and is called only once. When it returns, the thread 
     * is finished and cannot be used again. To keep the code in the run method from exiting 
     * until the application is done, the Boolean runningVolatileBoolean variable keeps the run method 
     * repeating.
     */
    public void run(){
        runningVolatileBoolean=true;
        while(runningVolatileBoolean){   
//               具体处理
            gameLoop();
        }
    }
    /**
     * Inside the game loop,instead of clearing the image and displaying 
     * the frame rate,the game loop checks for the space bar or the arrow keys and 
     * prints out a message whenever the keys are pressed.
     * Don't forget to call the KeyboardInput.pool() method every frame!
     */
    public void gameLoop(){
        /**
         * The poll() method,synchronized to protect the shared keys array,transfers the keyboard 
         * state from the boolean array to the integer array.
         */
        keyboardInputBoolean.poll();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)){
            System.out.println("VK_SPACE");
        }
        if(keyboardInputBoolean.keyDown(KeyEvent.VK_UP)){
            System.out.println("VK_UP");            
        }
        if(keyboardInputBoolean.keyDown(KeyEvent.VK_DOWN)){
            System.out.println("VK_DOWN");
        }
        if(keyboardInputBoolean.keyDown(KeyEvent.VK_LEFT)){
            System.out.println("VK_LEFT");
        }
        if(keyboardInputBoolean.keyDown(KeyEvent.VK_RIGHT)){
            System.out.println("VK_RIGHT");
        }
        try{            
            Thread.sleep(10);
        }catch(Exception e){
            System.out.println("sleep error");
        }
    }
}
