package javagame.input;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagame.util.SimpleKeyboardInput;

public class SimpleKeyboardExample extends JFrame implements Runnable{
    
    private SimpleKeyboardInput keyboardInputBoolean;
    private Thread gameThread;
    /**
     * Notice the volatile keyword with the declaration of the variable. Because this variable 
     * is a primitive type and is accessed from multiple threads, it is necessary to tell the compiler 
     * to always read this variable from memory. Without the keyword, the variable 
     * may be optimized with a cached value by the Java Virtual Machine (JVM), and the 
     * thread would be unable to stop.
     */
    private volatile boolean runningVolatileBoolean;
    private boolean spaceBoolean;
    
    public SimpleKeyboardExample(){
        keyboardInputBoolean=new SimpleKeyboardInput();
    }
    
    public static void main(String[]args){
        final SimpleKeyboardExample simpleKeyboardExample=new SimpleKeyboardExample();
        simpleKeyboardExample.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
//                22自己写处理关闭的过程
                simpleKeyboardExample.onWindowClosing();
            }
        });
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
//                自己写的界面
                simpleKeyboardExample.createAndShowGUI();
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
        addKeyListener(keyboardInputBoolean);
        setVisible(true);
//      线程处理行为，逻辑
        gameThread = new Thread(this);
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
//          具体处理
            gameLoop();
        }
    }
    /**
     * Inside the game loop,instead of clearing the image and displaying 
     * the frame rate,the game loop checks for the space bar or the arrow keys and 
     * prints out a message whenever the keys are pressed.
     */
    public void gameLoop(){
        /**
         * When checking the space bar,the example uses a variable to save the state of the key,
         * only printing to the console once every key press.
         */
        if(keyboardInputBoolean.keyDown(KeyEvent.VK_SPACE)){
//            private boolean spaceBoolean辅助变量
            if(!spaceBoolean){
                System.out.println("VK_SPACE");
            }
            spaceBoolean=true;
        }else{
            spaceBoolean=false;
        }
        /**
         * The arrow keys,however,continuously 
         * print their state to the console until they are released.
         */
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
        /**
         * Also, notice the sleep() method from the RenderThreadExample, which is used to slow 
         * the application down to a more reasonable speed. The sleep method takes a millisecond 
         * value and then suspends itself for that amount of time, allowing other threads to 
         * utilize the CPU.
         */
        try{            
            Thread.sleep(10);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
