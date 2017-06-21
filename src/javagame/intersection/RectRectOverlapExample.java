package javagame.intersection;

import javagame.util.SimpleFramework;
import javagame.util.Vector2f;

/**
 * To project the radius onto an axis, a single vector to the corner cannot be used,
 * because the rotation can cause the projection to be incorrect.
 * You must always project the width and height separately in order to project the correct radius.
 */
public class RectRectOverlapExample extends SimpleFramework{

    private Vector2f[] rect;
    private Vector2f[] rect0;
    private Vector2f rect1Pos;

    /**
     * Each rectangle testing for overlap has an array of points, 
     * a position, and a rotation in radians.
     * The intersection variable holds the state of the separating axis test.
     */

    /**
     * The processInput() method moves the second rectangle around using the mouse position.
     * The A and S keys rotate the first triangle and the Q and W keys rotate the second triangle.
     */
    protected void processInput (float delta) {
        super.processInput(delta);
        //convert mouse coordinate for testing
        rect1Pos = getWorldMousePosition();
    }

    public static void main (String[] args) {
        launchApp(new RectRectOverlapExample());
    }

}
