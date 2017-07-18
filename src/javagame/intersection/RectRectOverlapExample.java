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

    /**
     * The updateObjects() method copies each rectangle and then translates and rotates 
     * them so they can be tested for overlap.
     */
    protected void updateObjects () {
    }

    /**
     * The rectRectIntersection() method computes the center of each rectangle,
     * the four point axis values, and the width and height of each rectangle.
     * The sum of each width and height are compared to the distance of each center.
     * If they do not overlap, then the objects do not overlap.
     * To project the radius onto an axis, a single vector to the corner cannot be used,
     * because the rotation can cause the projection to be incorrect.
     * You must always project the width and height sparately in order to 
     * project the correct radius.
     */
    protected boolean rectRectIntersection (Vector2f[] A, Vector2f[] B) {
        
        //separating axis intersection algorithm
        Vector2f N0 = A[0].sub(A[1]).div(2.0f);
        Vector2f N1 = A[1].sub(A[2]).div(2.0f);
        Vector2f CA = A[0].add(A[2]).div(2.0f);

        float D0 = N0.len();
        float D1 = N1.len();
        N1 = N1.div(D1);
        N0 = N0.div(D0);

        Vector2f N2 = B[0].sub(B[1]).div(2.0f);
        Vector2f N3 = B[1].sub(B[2]).div(2.0f);
        Vector2f CB = B[0].add(B[2]).div(2.0f);

        float D2 = N2.len();
        float D3 = N3.len();
        N2 = N2.div(D2);
        N3 = N3.div(D3);

        Vector2f C = CA.sub(CB);

        float DA = D0;
        float DB = D2 * Math.abs(N2.dot(N0));
        DB += D3 * Math.abs(N3.dot(N0));

        if (DA + DB < Math.abs(C.dot(N0))) {
            return false;
        }

        DA = D1;
        DB = D2 * Math.abs(N2.dot(N1));
        DB += D3 * Math.abs(N3.dot(N1));

        if (DA + DB < Math.abs(C.dot(N2))) {
            return false;
        }

        DA = D2;
        DB = D0 * Math.abs(N0.dot(N2));
        DB += D1 * Math.abs(N1.dot(N2));

        if (DA + DB < Math.abs(C.dot(N2))) {
            return false;
        }

        DA = D3;
        DB = D0 * Math.abs(N0.dot(N3));
        DB += D1 * Math.abs(N1.dot(N3));

        if (DA + DB < Math.abs(C.dot(N3))) {
            return false;
        }

        return true;
    }

    /**
     * The render() method transforms the rectangles to screen coordinates and draws the rectangles in blue if they overlap,
     * and black otherwise.
     */
    protected void render () {
    }

    public static void main (String[] args) {
        launchApp(new RectRectOverlapExample());
    }

}
