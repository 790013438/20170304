package javagame.render;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class RenderThreadExample extends JFrame implements Runnable{
	
	private Thread gameThread;
	private volatile boolean runningVolatileBoolean;

	public static void main(String[]args){
		final RenderThreadExample renderThreadExample = new RenderThreadExample();
		renderThreadExample.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0){
				renderThreadExample.onWindowClosing();
			}
		});
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				renderThreadExample.createAndShowGUI();
			}
		});
	}
	protected void onWindowClosing(){
		try{			
			System.out.println("Stopping Thread....");
		}catch(Exception e){
			System.out.println(e);
		}
		System.exit(0);
//		Lastly, the program must be shut down by hand with the System.exit(0) call. 
//		Previously, when the JFrame.EXIT_ON_CLOSE flag was set in the JFrame, the program would terminate. 
//		Now that the application is handling the shutdown, the program will not end unless the exit method is called. 
	}
	public void createAndShowGUI(){
		setSize(320,240);
		setTitle("Render Thread");
		setVisible(true);
//		creating a custom render thread
		gameThread = new Thread(this);
//		thisÀ´×Ôimplements Runnable
		gameThread.start();
	}
	public void run(){
		runningVolatileBoolean=true;
		while(runningVolatileBoolean){
			System.out.println("Game Loop");
//			below
			sleep(10);
		}
	}
	private void sleep(long sleepLong){
		try{		
			Thread.sleep(sleepLong);
		}catch(InterruptedException e){
			System.out.println("Thread.sleep³ö´í");
		}
	}
}
