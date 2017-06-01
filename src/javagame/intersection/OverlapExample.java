package java.intersection;

import java.awt.*;
import java.awt.event.*;

import javagame.util.*
//Because the rectangle is not rotated, it is an easy shape to use for intersection testing.
//The shape can be represented with only a minimum and a maximum value.
public class OverlapExample extends SimpleFramework {
    //Because the rectangle is not rotated, it is an easy shape to use for intersection testing.
    //The shape can be represented with only a minimum and a maximum value.
    provate void drawAABB(Graphics g, Vector2f min, Vector2f max){
        
        Matrix3x3f view = getViewportTransform();
        
        Vector2f topLeft = new Vector2f(min.x, max.y);
        topLeft = view.mul(topLeft);
        
        Vector2f bottomRight = new Vector2f(max.x, min.y);
        bottomRight = view.mul((bottomRight);
        
        int rectX = (int)bopLeft.x;
        int rectY = (int)topLeft.y;
        int rectWidth = (int)(bottomRight.x - topLeft.x);
        int rectHeight = (int)(bottomRight.y - topLeft.y);
        g.drawRect(rectX, rectY, rectWidth, rectHeight);
    }

    //Because the shape is not rotated, you simple need to check if there's a point betwwen 
    //both x and y minimum and maximum values.
    //That's all that is needed to check if a point is within an AABB.
    private boolean pointInAABB (Vector2f p, Vector2f min, Vector2f max) {
        return p.x > min.x && p.x <max.x &&
            p.y > min.y && p.y < max.y;
    }

    //Checking if two AABBs overlop is also easy.
    //If any of the values do not overlap, then there is no intersection.
    private boolean intersectAABB (Vector2f minA, Vector2f maxA, 
        Vector2f minB, Vector2f maxB) {
        if (minA.x > maxB.x || minB.x > maxA.x) return false;
        if (minA.y > maxB.y || minB.y > maxA.y) return false;
        return true;
    }

    //Circle are a useful shape to use for intersections testing.
    //Circles can be represented as a center point and a radius.
    //You draw a circle so that it stretches with the size of the window the asme way you drew the AABB.
    private void drawOval (Graphics g, Vector2f center, float radius) {
        Matrix3x3f view = getViewportTransform();
        
        Vector2f topLeft = new Vector2f(center.x - radius, center.y + radius);
        topLeft = view.mul(topLeft);
        Vector2f bottomRight = new Vector2f(center.x + radius, center.y - radius);
        bottomRight = view.mul(bottomRight);
        int circleX = (int)topLeft.x;
        int circleY = (int)topLeft.y;
        int circleWidth = (int)(bottomRight.x - topLeft.x);
        int circleHeight = (int)(bottomRight.y - topLeft.y);
        g.drawOval(circleX,circleY, circleWidth, circleWidth);
    }

    //To check if a point is inside a circle, you compare the distance from the point in the 
    //center of the circle to the radius.
    //If the radius is larger than the distance, the point is inside the circle.
    //Because the square root method can be slow, it is easier to check the squared distance.
    //if a < b then a^2 < b^2
    //Most of the comparisions using the distance in the following examples use the squared 
    //distance for comparison.
    //This is illustrated in the following method:
    private boolean pointInCircle (Vector2f p, Vector2f c, float r) {
        Vector2f dist = p.sub(c);
        return dist.lenSqr() < r*r;
    }

    //The test to check if two circles overlap is very similar to the point test.
    //If the distance from the circles' centers is larger than the sum of the radii,
    //they do not overlap.
    //The code that tests for overlapping circles is as follows:
    private boolean intersectCircle (Vector2f c0, float r0, Vector2f c1, float r1) {
        Vector2f c = c0.sub(c1);
        float r = r0 + r1;
        return c.lenSqr() < r*r;
    }

    //To determine whether a circle and an AABB overlap, you calculate the distance from 
    //the circle to the AABB.
    //The circle's center will either be outside the AABB or inside the AABB.
    //If the circle's center is less than the minimum AABB value,
    //the distance between them is the difference between the minimum and the circle center.
    //If the circle's center is greater than the maximum, the distance between them is the difference 
    //between the maximum and the center.
    //If the center is greater than the minimum but less than the maximum,
    //it is located inside the AABB and no further testing is required.
    //Using the calculated distance for each x and y, 
    //the closest point on the AABB is found and can be used to est against the circle radius distance 
    //exactly like the previous examples.
    //The algorithm can be written as follows:
    /*private boolean intersectCircleAABB (Vector2f c, float r, Vector2f min, Vector2f max) {
      float dx = 0.0f;
      if (c.x < min.x) dx = c.x - min.x;
      if (c.x > max.x) dx = c.x - max.x;

      float dy = 0.0f;
      if (c.y < min.y) dy = c.y - min.y;
      if (c.y > max.y) dy = c.y - max.y;

      float d = dx * dx + dy * dy;
      return d < r*r;
      }*/
    //It is possible to optimize this code by combining the dx and dy variables into a single variable,
    //performing the square and accumulation in he same line.
    //The optimized version is as follows:
    private boolean intersectCircleAABBB (Vector2f c, float r, Vector2f min, Vector2f max) {
        float d = 0.0f;
        if (c.x < min.x) d += (c.x - min.x)*(c.x - min.x);
        if (c.x > max.x) d += (c.x - max.x)*(c.x - max.x);
        if (c.y < min.y) d += (c.y - min.y)*(c.y - min.y);
        if (c.y > max.y) d += (c.y - max.y)*(c.y - max.y); 
        return d < r*r;
    }
}
