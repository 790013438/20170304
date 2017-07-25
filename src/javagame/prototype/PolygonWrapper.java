package javagame.prototype;

import javagame.util.Matrix3x3f;
import javagame.util.Vector2f;

import java.util.List;

/**
 * Created by 79001 on 2017/7/19.
 */
public class PolygonWrapper {
    /**
     * This bounding box determines if the object is outside the bounds of the screen 
     * and needs to be wrapped to the other side.
     * the polygonwrapper class, located in the javagame.prototype package, 
     * contains methods to wrap polygons and positions.
     */
    private float worldWidth;
    private float worldHeight;
    private Vector2f worldMin;
    private Vector2f worldMax;

    /**
     * The constructor takes the width and height of the screen in world coordinates 
     * and computes the minimum and maximum vector values.
     */
    public PolygonWrapper (float worldWidth, float worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        worldMax = new Vector2f(worldWidth / 2.0f, worldHeight / 2.0f);
        worldMin = worldMin.inv();
    }

    /**
     * Creating a render list that contains copies of each object solves the problem.
     * The objects can be drawn and the entire list can be used to check for collisions.
     * The objects that wrap never get stuck because once the middle of the object is outside the bounds of the world,
     * it is wrapped by adjusting the position by the width or height of the world..
     * By always placing the wrapped position inside the bounds,
     * the object can never get stuck.
     */

    /**
     * The hasLeftWorld() method checks the given position against the minium and maximum world coordinates.
     */
    public boolean hasLeftWorld (Vector2f position) {
        return position.x < worldMin.x || position.x > worldMax.x || position.y < worldMin.y || position.y > worldMax.y;
    }

    /**
     * thre wrapposition() method returns a wrapped position if the given position has left the bounds of the world.
     */
    public Vector2f wrapPosition (Vector2f position) {
        Vector2f wrapped = new Vector2f(position);
        if (position.x < worldMin.x) {
            wrapped.x = position.x + worldWidth;
        } else if (position.x > worldMax.x) {
            wrapped.x = position.x - worldWidth;
        }
        if (position.y < worldMin.y) {
            wrapped.y = position.y + worldHeight;
        } else if (position.y > worldMax.y) {
            wrapped.y = position.y - worldHeight;
        }
        return wrapped;
    }
    
    /**
     * Once the position of the object is wrapped, the AABB for the object is computed by finding the minimum and maximum (x, y) values.
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
     * If the object is wrapped to two cardinal directions, such as south and east,
     * it is also wrapped to the north and west.
     * Each of the four diagonal directions is tested.
     */
    public void wrapPolygon (Vector2f[] poly, List<Vector2f[]> renderList) {
        Vector2f min = getMin(poly);
        Vector2f max = getMax(poly);

        boolean north = max.y > worldMax.y;
        boolean south = min.y < worldMin.y;
        boolean west = min.x < worldMin.x;
        boolean east = max.x > worldMax.x;

        if (west) {
            renderList.add(wrapEast(poly));
        }
        if (east) {
            renderList.add(wrapWest(poly));
        }
        if (north) {
            renderList.add(wrapSouth(poly));
        }
        if (south) {
            renderList.add(wrapNorth(poly));
        }
        if (north && west) {
            renderList.add(wrapSouthEast(poly));
        }
        if (north && east) {
            renderList.add(wrapSouthWest(poly));
        }
        if (south && west) {
            renderList.add(wrapNorthEast(poly));
        }
        if (south && east) {
            renderList.add(wrapNorthWest(poly));
        }
    }

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
    private Vector2f[] wrapNorth (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(0.0f, worldHeight));
    }

    private Vector2f[] wrapSouth (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(0.0f, -worldHeight));
    }

    //方向不太对 - > west用east
    private Vector2f[] wrapEast (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(worldWidth, 0.0f));
    }

    private Vector2f[] wrapWest (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(-worldWidth, 0.0f));
    }

    private Vector2f[] wrapNorthWest (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(-worldWidth, worldHeight));
    }

    private Vector2f[] wrapNorthEast (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(worldWidth, worldHeight));
    }

    private Vector2f[] wrapSouthEast (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(worldWidth, -worldHeight));
    }

    private Vector2f[] wrapSouthWest (Vector2f[] poly) {
        return transform(poly, Matrix3x3f.translate(-worldWidth, -worldHeight));
    }
}
