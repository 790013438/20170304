package javagame.util;
//Fundamental 2d game programming with java
public class FrameRate {

	private long lastTimeLong;	
	private String frameRateString;
	private long deltaLong;
//	delta->Èý½ÇÖÞ,the fourth letter of the Greek alphabet ( ¦¤ , ¦Ä ), transliterated as ¡®d.¡¯.
	private int frameCountInt;
	
	public void initialize(){
		lastTimeLong=System.currentTimeMillis();
		frameRateString="FPS 0";
	}
	public void calculate(){
		long currentLong=System.currentTimeMillis();
		deltaLong+=currentLong-lastTimeLong;
		lastTimeLong=currentLong;
		frameCountInt++;
		if(deltaLong>1000){
			deltaLong-=100;
			frameRateString=String.format("FPS %s",frameCountInt);
			frameCountInt=0;
		}
	}
	public String getFrameRate(){
		return frameRateString;
	}
}
