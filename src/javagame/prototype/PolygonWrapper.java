package javagame.prototype;

import javagame.util.Vector2f;

/**
 * Created by 79001 on 2017/7/19.
 */
public class PolygonWrapper {
    /**
     * This bounding box determines if the object is outside the bounds of the screen 
     * and needs to be wrapped to the other side.
     * The PolygonWrapper class, located in the javagame.prototype package, 
     * contains methods to wrap polygons and positions.
     */

    /**
     * The constructor takes the width and height of the screen in world coordinates 
     * and computes the minimum and maximum vector values.
     */
    /**
     * The wrapped position is adjusted ty the width and height of the world 
     * so the new position is never outside the bounds of the world.
     * The bounds of the poygon to be wrapped are computed as follows:
     */
    //PolygonWrapper.java
    private Vector2f getMin (Vector2f[] poly) {
        Vector2f min = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
        for (Vector2f v : poly) {
            min.x = Math.min(v.x, min.x);
            min.y = Math.min(v.y, min.y);
        }
        return min;
    }

    private Vector2f getMax (Vector2f[] poly) {
        Vector2f max = new Vector2f(-Float.MAX_VALUE, -Float.MAX_VALUE);
        for (Vector2f v : poly) {
            max.x = Math.max(v.x, max.x);
            max.y = Math.max(v.y, max.y);
        }
        return max;
    }

    /**
     * The wrapPolygon() method computes the minimum and maximum values and uses these to determine 
     * if the object needs to be wrapped to the north, south, east, or west.
     * If the object is wrapped to two cardianl directions, such as south and east, 
     * it is also wrapped to the north and west.
     * Each of the four diagonal directions is tested.
     */

    /**
     * Each wrapped polygon is copied and added to the render list using the transform method;
     */
    private Vector2f[] transform (Vector2f[] poly, Matrix3x3f mat) {
        Vector2f[] copy = new Vector2f[poly.length];
        for (int i = 0; i < poly.length; ++i) {
            copy[i] = mat.mul(poly[i]);
        }
        return copy;
    }

    /**
     * The polygon is wrapped to the eight directions by adding or substracting the width and height of the world.
     */
}
