package javagame.util;

/**
 * There are updates to the vector class that you need to be aware of before you move on 
 * to the intersection-testing algorithms and concepts.
 * The updates are covered in this chapter.
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

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
        this.w= 1.0f;
    }

    public Vector2f(float x, float y, float w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }
    
    public Vector2f(Vector2f v) {
        this.x = v.x;
        this.y = v.y;
        this.w = v.w;
    }

    public void translate(float tx, float ty) {
        x += tx;
        y += ty;
    }

    public void scale(float sx, float sy) {
        x *= sx;
        y *= sy;
    }

    public void rotate(float rad) {
        float temp = (float)(x * Math.cos(rad) - y * Math.sin(rad));
        y = (float)(x * Math.sin(rad) + y * Math.cos(rad));
        x = temp;
    }

    public void shear(float sx, float sy) {
        float tmp = x + sx * y;
        y = y + sy * x;
        x = tmp;
    }

    /*
     * The inv() method returns a new vector that is the inverse of the current vector.
     * The inverse of a vector is a new vector that ponts in the opposite direction.
     */
    public Vector2f inv() {
        return new Vector2f(-x, -y);
    }

    /*
     * The add() method returns a new vector that is the sum of the current vector and the given vector.
     */
    public Vector2f add(Vector2f v) {
        return new Vector2f(x + v.x, y + v.y);
    }

    /*
     * The sub() method returns a new vector that is the subtraction of the given vector and the current vector.
     * The arrow of the new vector will point to the first vector in the operation.
     * Sometimes it is easier to visualize subtraction as adding the inverse of a vector.
     */
    public Vector2f sub(Vector2f v) {
        return new Vector2f(x - v.x, y - v.y);
    }

    /*
     * The mul() method returns a new vector that is the product of the vector component and the scalar value.
     * Notice that negative values invert the vector.
     */
    public Vector2f mul(float scalar) {
        return new Vector2f(scalar * x, scalar * y);
    }

    /*
     * The div() method is the same as multiplying by one over the scalar.
     * Notice that no checks are made for dividing by zero.
     */
    public Vector2f div(float scalar) {
        return new Vector2f(x / scalar, y / scalar);
    }

    /*
     * To find the length of a vector, you use the Pythagorean Theorem.
     * Single lines are used for the absolute value |a|. 
     * Double lines are used for the length of a vector ||a||.
     */
    public float len() {
        return (float)Math.sqrt(x * x + y * y);
    }

    /*
     * Because the square root operation can take many computer cycles,
     * sometimes it is easier to just use the squared length value in comparisons.
     */
    public float lenSqr() {
        return x * x + y * y;
    }

    /*
     * The norm() method returns a normalized vector that has a length of one.
     * Many graphics equations can be simplified by using a vector with a length of one.
     * The little hat (a caret) above the variable in the diagrams indicates a unit vector.
     */
    public Vector2f norm() {
        return div(len());
    }

    /*
     * The perp() method returns a new vector that is perpendicular to the current vector.
     * This is useful for creating a normal vector that is needed in may of the equations in the following chapters.
     * Notice that either (-y, x) or (y, -x) produce a perpendicular vector.
     * The upside down T represents a perpendicular vector.
     */
    public Vector2f perp() {
        return new Vector2f(-y, x);
    }

    /*
     * The dot() method returns the dot product of two vectors.
     * This method of multiplying two vectors is very useful.
     * The dot product projects one vector onto another, forming a right triangle, which is very helpful when testing object intersections.
     */
    public float dot(Vector2f v) {
        return x * v.x + y * v.y;
    }

    /*
     * The angle() method returns the angle in radians created by the vector.
     * Notice the atan2() method is used to handle the cases where x is zero,
     * so no additional checks are needed.  */
    public float angle() {
        return (float)Math.atan2(y, x);
    } 

    /*
     * The polar() method takes an angle in radians ansd a radius and returns a vecor with the converted x and y coordiantes.
     */
    public static Vector2f polar(float angle, float radius) {
        return new Vector2f(
                radius * (float)Math.cos(angle),
                radius * (float)Math.sin(angle)
       );
    }

    /*
     * The last update is helpful for displaying the vector values as strings for debugging. 
     */
    public String toString() {
        return String.format("%s, %s", x, y);
    }

}
