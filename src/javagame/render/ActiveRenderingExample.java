package javagame.render;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ActiveRenderingExample extends JFrame{
	
	public static void main(String[]args){
		final ActiveRenderingExample activeRenderingExample=new ActiveRenderingExample();
		activeRenderingExample.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				
			}
		});
	}

}
