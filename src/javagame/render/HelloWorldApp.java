package javagame.render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javagame.util.FrameRate;

public class HelloWorldApp extends JFrame{
	
	private FrameRate frameRate;

	public HelloWorldApp(){
		frameRate = new FrameRate();
	}
	public static void main(String[]args){
		final HelloWorldApp helloWorldApp=new HelloWorldApp();
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				helloWorldApp.createAndShowGUI();
			}
		});
	}
	
	protected void createAndShowGUI(){
		GamePanel gamePanel=new GamePanel();
//		下面写的类中定义的类
		gamePanel.setBackground(Color.WHITE);
		gamePanel.setPreferredSize(new Dimension(320,240));
		getContentPane().add(gamePanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hello World!");
		pack();
		/**
	     * Causes this Window to be sized to fit the preferred size
	     * and layouts of its subcomponents. The resulting width and
	     * height of the window are automatically enlarged if either
	     * of dimensions is less than the minimum size as specified
	     * by the previous call to the {@code setMinimumSize} method.
	     * <p>
	     * If the window and/or its owner are not displayable yet,
	     * both of them are made displayable before calculating
	     * the preferred size. The Window is validated after its
	     * size is being calculated.
	     *
	     * @see Component#isDisplayable
	     * @see #setMinimumSize
	     */
//		用设置窗口适应自己内组件的大小的
		frameRate.initialize();
		setVisible(true);
	}
	
	private class GamePanel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			onPaint(g);
//			below
		}
	}
	
	protected void onPaint(Graphics g){
		frameRate.calculate();
		g.setColor(Color.BLACK);
		g.drawString(frameRate.getFrameRate(), 30, 30);
		repaint();
	}
}
