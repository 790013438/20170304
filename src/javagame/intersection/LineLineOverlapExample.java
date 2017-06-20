package javagame.intersection;

import java.awt.*;
import java.awt.event.MouseEvent;
import javagame.util.*;

public class LineLineOverlapExample extends SimpleFramework{

    public LineLineOverlapExample () {
        appWidth = 640;
        appHeight = 640;
        appSleep = 10L;
        appTitle = "Line Line Overlap";
        appBackground = Color.WHITE;
        appFPSColor = Color.BLACK;
    }

    protected void initialize () {
        super.initialize();
        P = new Vector2f(-0.6f, 0.4f);
        Q = new Vector2f(0.6f, -0.4f);
        start = new Vector2f(0.8f, 0.8f);
    }

    protected void updateObject (float delta) {
        super.updateObject(delta);
        overlap = lineLineOverlap();
    }

    private boolean lineLineOverlap (Vector2f A, Vector2f B, Vector2f P, Vector2f Q) {
        Vector2f C0 = A.add(B).div(2.0f);
    }

    public static void main(String[] args) {
        launchApp(new LineLineOverlapExample());
    }

}
