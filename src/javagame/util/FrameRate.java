package javagame.util;
/**
 * Adding a frame rate calculator will measure 
 * the application performance and verify that the window is redrawing.
 * Since most examples in this book measure the frame rate,
 * a frame rate calculator is a great place to begin.
 * This class is used to measure the frames per seconds(FPS) of the applications 
 * developed in this book. 
 * @author 79001
 *
 */
public class FrameRate{
    
    /**
     * the fourth letter of the Greek alphabet ( Δ , δ )
     * Since the delta variables is rarely exactly one second,
     * 1000 milliseconds are subtracted from the delta variable to save the extra milliseconds.
     */
    private long deltaLong;
    private long lastTimeLong;
    private int frameCountInt;
    private String frameRateString;
    
    public void initialize(){
        lastTimeLong=System.currentTimeMillis();
        frameRateString="FPS 0";
    }
    /**
     * Once the new frame rate is saved,
     * the frame count is reset and the process begins agin.
     */
    public void calculate(){
        long currentLong=System.currentTimeMillis();
        deltaLong+=currentLong-lastTimeLong;
        lastTimeLong=currentLong;
        frameCountInt++;
        if(deltaLong>1000){
            deltaLong-=1000;
            frameRateString=String.format("FPS %s", frameCountInt);
            frameCountInt=0;
        }
        
    }
    
    public String getFrameRate(){
        return frameRateString;
    }
}