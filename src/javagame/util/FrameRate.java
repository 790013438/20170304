package javagame.util;

/**
 * This class is used to measure the frames per seconds (FPS) of the applications developed in this book.
 * The FPS is stored as a string in the format "FPS 100".
 * This value is calculate every second.
 */
public class FrameRate {

    private long lastTimeLong;
    private int frameCount;
    private String frameRateString;
    private long deltaLong;

    /*
     *The initialize() method needs to be called before the frame rate measurements begin.
     * This method initializes the frame rate string to 0, and the last time to the current time in milliseconds.
     */
    public void initialize() {
        lastTimeLong = System.currentTimeMillis();
        frameRateString = "FPS 0";
    } 

    /*
     * The calculate() method should be called once for every rendered frame.
     * To calculate the frame rate,the current time is subtracted from the last time and stored in the delta variable.
     * The frame count is incremented each frame, and when the delta time is over one second, the new FPS are generated.
     */
    public void calculate() {
        long currentTimeLong = System.currentTimeMillis();
        deltaLong += currentTimeLong - lastTimeLong;
        lastTimeLong = currentTimeLong;
        frameCount++;
        if(deltaLong > 1000) {
            deltaLong -= 1000;
            frameRateString = String.format("FPS %s", frameCount);
            frameCount = 0;
        }
    }

    public String getFrameRate() {
        return frameRateString;
    }

}
