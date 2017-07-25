package javagame.util;

public class FrameRate {
    /**
     * The FrameRate class is located in the javagame.util package.
     * All the utility code developed in this book goes into the utility package,
     * which you can later turn into a utility library.
     * This class is used to measure the frames per seconds (FPS) of the applictions 
     * developed in this book.
     * The FPS is stored as a string in the format "FPS 100".
     * This value is calculated every second.
     */
    private String frameRateString;
    private long lastTimeLong;
    private int frameCountInt;
    private long deltaLong;

    /**
     * The initialize() method needs to be called before the frame rate measurements begin.
     * This method initializes the frame rate string to 0, and the last time to the current time in  milliseconds.
     * The System.currentTimeMillis() call returns the number of milliseconds 
     * since midnight, January 1, 1970.
     * Depending on the operating system,
     * the accuracy of the time messured can vary.
     * Some version of Windows, for example, guarantee only 10 milliseconds of accuracy.
     */
    public void initialize() {
        frameRateString = "FPS 0";
        lastTimeLong = System.currentTimeMillis();
    }

    /**
     * The calculate() method should be called once for every rendered frame.
     * To calcluatte the frame rate, the current time is subtracted from the last time and 
     * stored in the delta variable.
     * The frame count is incremented each frame,
     * and when the delta time is over one second,
     * the new FPS are generated.
     * Since the delta variable is rarely exactly one second,
     * 1000 milliseconds are subtracted from the delta variable to
     * save the extra milliseconds. 
     */
    public void calculate() {
        long currentLong = System.currentTimeMillis();
        deltaLong += currentLong - lastTimeLong;
        lastTimeLong = currentLong;
        frameCountInt++;
        if (deltaLong > 1000) {
            deltaLong -= 1000;
            frameRateString = String.format("FPS %s", frameCountInt);
            frameCountInt = 0;
        }
    }

    public String getFrameRate () {
        return frameRateString;
    }
}
