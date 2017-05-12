package javagame.util;

/**
 * The Vector2f class is used to represent a location in space,
 * as well as a direction.
 * Notice that the 2D coordinate class has three values:x,y,and w.
 * In order to use the matrix transformations to perform translation,
 * there needs to be an extra coordinate value to get the matrix math to work.
 * So pay no attention to the extra "w" coordinate behind the curtain,it will be explained later.
 * Those of you familiar with vector algebra may look at the source code that follows and scream,
 * "Wait" Points and vectors are not the same.
 * Points have a w=1,but vectors cannot be translated,and should have a w=0.
 * I will cover that later too,so don't worry.
 * @author 79001
 *
 */
public class Vector2f {
    
    public float x;
    public float y;
    public float w;
    
    public Vector2f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.w = 1.0f;
    }
    
    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
        this.w = v.w;
    }
    
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
        this.w = 1.0f;
    }
    
    public Vector2f(float x, float y, float w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }
    
    public void translate(float translatex, float translatey) {
        x += translatex;
        y += translatey;
    }
    
    public void scale(float scalex, float scaley) {
        x *= scalex;
        y *= scaley;
    }
    
    public void rotate(float radian) {
        float temp = (float)(x * Math.cos(radian) - y * Math.sin(radian));
        y = (float)(x * Math.sin(radian) + y * Math.cos(radian));
        x = temp;
    }
    /**
     * Radian describes the plane angle subtended by a circular arc as the length of the arc divided by the radius of the arc.
     * @param radian
     */
    public void shear(float shearx, float sheary) {
        float temp = x + shearx * y;
        y = y + sheary * x;
        x = temp;
    }
    
}