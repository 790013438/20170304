package javagame.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javagame.util.SimpleFramework;

/**
 * The SimpleFrameworkTemplate can be used as a starting point for all of the examples that follow.
 * The framework hides the common code, making it easier to explore the new code in the examples.
 * The template extends the SimpleFramework class.
 */
public class SimpleFrameworkTemplate extends SimpleFramework{

    /*
     * The constructor is the place to set of the application's properties.
     * Setting the properties in the initialize() method is to late,
     * because some of them have already been used before the initialize() method is called.
     * Any properties whose default values are acceptable can be removed from the constructor. 
     * 233
     */
    public SimpleFrameworkTemplate() {
        appBackground = Color.WHITE;
        appBorder = Color.LIGHT_GRAY;
        appFont = new Font("Courier New", Font.PLAIN, 14);
        appBorderScale = 0.9f;
        appFPSColor = Color.BLACK;
        appWidth = 640;
        appHeight = 640;
        appMaintainRatio = true;
        appSleep = 10L;
        appTitle = "FramworkTemplate";
        appWorldWidth = 2.0f;
        appWorldHeight = 2.0f;
    }

    /*
     * The launchApp() method is used in the main method to start the application.
     */
    public static void main( String[] args ) {
        launchApp( new SimpleFrameworkTemplate() );
    }

    protected void initialize() {
        super.initialize();
    }

    protected void processInput(float delta) {
        super.processInput(delta);
    }

    protected void updateObjects(float delta) {
        super.updateObjects(delta);
    }

    protected void render(Graphics g) {
        super.render(g);
    }

    protected void terminate() {
        super.terminate();
    }

}
