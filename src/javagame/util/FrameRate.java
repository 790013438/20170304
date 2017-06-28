package javagame.util;

/**All the utility code developed in this book goes into the utility package,which you can later turn into a utility libray.
The class is used to measure the frames per seconds () of the applications developed in this book.
The FPS is stored as string in the format "".This value is calculated every second.*/
public class FrameRate {

    private String frameRateString;
    private long lastTimeLong;
    private long deltaLong;
    private int frameCountInt;

    //The initialize() method needs to be called before the frame rate measurements begin.
    //This method initializes the frame rate string to 0, and the last time to the current time in  milliseconds.
    public void initialize() {
        frameRateString = "FPS 0";
        lastTimeLong = System.currentTimeMillis();
    }

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
