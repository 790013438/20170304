package javagame.util;

/*
 * 重写，熟悉结构
 */
/**
 * Adding a frame rate calculator will measure the application performance and verify that the window is redrawing.
 * Since most examples in this book measure the frame rate,
 * a frame rate calculator is a great place to begin.
 * This class is used to measure the frames per seconds(FPS) of the applications developed in this book; 
 * @author 79001
 *
 */
public class FrameRate {
    
    private long lastTimeLong;
    /**
     * the fourth letter of the Greek alphabet
     * Since the delta variables is rarely exactly one second,
     * 1000 milliseconds are subtracted from the delta variable to save the extra milliseconds.
     */
    private long deltaLong;
    private int frameCountInt;
    private String frameRateString;
    
    public void initialize() {
        lastTimeLong = System.currentTimeMillis();
        frameRateString = "FPS 0";
    }
    
    /**
     * The calculate() method should be called once for every rendered frame.
     * To calculate the frame rate,the current time is subtracted from the last time and stored in the delta variable.
     * The frame count is incremented each frame,and when the delta time is over one second,
     * the new FPS are generated.
     * Since the delta variable is rarely exactly one second,
     * 1000 milliseconds are subtracted from the delta variable to save the extra milliseconds.
     * Once the new frame rate is saved,the frame count is reset and the process begins again.
     */
    public void calculate() {
        /**
         * The System.currentTimeMillis() call returns the number of milliseconds since midnight,January 1,1970.
         * Depending on the operating system,the accuracy of the time measured can vary.
         * Some versions of Windows,for example,guarantee only 10 milliseconds of accuracy.
         */
        long currentTimeLong = System.currentTimeMillis();
        deltaLong += currentTimeLong - lastTimeLong;
        lastTimeLong = currentTimeLong;
        frameCountInt++;
        if(deltaLong > 1000) {
            deltaLong -= 1000;
            frameRateString = String.format("FPS %s", frameCountInt);
            frameCountInt = 0;
        }
    }
    
    public String getFrameRate() {
        return frameRateString;
    }
}