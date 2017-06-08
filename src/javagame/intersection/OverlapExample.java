package javagame.intersection;

import java.awt.*;
import java.awt.event.*;

import javagame.util.*;
//Because the rectangle is not rotated, it is an easy shape to use for intersection testing.
//The shape can be represented with only a minimum and a maximum value.
public class OverlapExample extends SimpleFramework {
    //mouse variables
    private Vector2f mousePos;
    //AABB variables
    private Vector2f min0, max0;
    private Vector2f min1, max1;
    private float f1;
    private Vector2f rect0Pos;
    private Vector2f rect1Pos;
    private Vector2f mouseDelta;
    private boolean clicked;
    private boolean dragging;
    private Vector2f min0Cpy, max0Cpy; 
    private Vector2f min1Cpy, max1Cpy;
    private boolean rect0Moving;
    private boolean rect1Moving;
    private boolean rect0Collision;
    private boolean rect1Collision;
    //circle variables
    private Vector2f c0, c0Pos;
    private float r0;
    private boolean circle0Moving;
    private boolean circle0Collision;
    //
    //233
    private Vector2f c1, c1Pos;
    private float r1;
    private boolean circle1Moving;
    private boolean circle1Collision;

    public OverlapExample () {
        appHeight = 640;
        appWidth = 640;
        appTitle = "OverlapExample";
        appBackground = Color.WHITE;
        appFPSColor = Color.BLACK;
    }

    protected void initialize () {
        super.initialize();
        mousePos = new Vector2f();
        min0 = new Vector2f(-0.25f, -0.25f);
        max0 = new Vector2f(0.25f, 0.25f);
        min1 = new Vector2f(-0.3f, -0.3f);
        max1 = new Vector2f(0.3f, 0.3f);
        
        r0 = 0.25f;
        r1 = 0.125f;
        
       reset();
    } 

    private void reset () {
        rect0Pos = new Vector2f();
        rect1Pos = new Vector2f(0.25f, 0.5f);
        c0Pos = new Vector2f(-0.60f, -0.60f);
        c1Pos = new Vector2f(0.6f, 0.6f);
    } 

    protected void processInput (float delta) {
        super.processInput(delta);
        //reset objects on space bar
        if (keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)) {
            reset();
        }
        //convert screen coordinates to world coordinates
        //for intersection testing
        Vector2f pos = getWorldMousePosition();
        mouseDelta = pos.sub(mousePos);
        mousePos = pos;
        
        clicked = relativeMouseInputBoolean.buttonDownOnce(MouseEvent.BUTTON1);
        dragging = relativeMouseInputBoolean.buttonDown(MouseEvent.BUTTON1);
    }

    protected void updateObjects (float delta) {
        super.updateObjects(delta);
        //calculate the AABB minimum and maximum values
        Matrix3x3f mat = Matrix3x3f.translate(rect0Pos.x, rect0Pos.y);
        min0Cpy = mat.mul(min0);
        max0Cpy = mat.mul(max0);
        mat = Matrix3x3f.translate(rect1Pos.x, rect1Pos.y);
        min1Cpy = mat.mul(min1);
        max1Cpy = mat.mul(max1);
        //position the circles
        mat = Matrix3x3f.translate(c0Pos.x, c0Pos.y);
        c0 = mat.mul(new Vector2f());
        mat = Matrix3x3f.translate(c1Pos.x, c1Pos.y);
        c1 = mat.mul(new Vector2f());
        //test for click and drag of objects
        if (clicked && pointInAABB(mousePos, min0Cpy, max0Cpy)) {
            rect0Moving = true;
        }
        if (clicked && pointInAABB(mousePos, min1Cpy, max1Cpy)) {
            rect1Moving = true;
        } 
        if (clicked && pointInCircle(mousePos, c0Pos, r0)) {
            circle0Moving = true;
        } 
        if (clicked && pointInCircle(mousePos, c1Pos, r1)) {
            circle1Moving = true;
        } 
        rect0Moving = rect0Moving && dragging;
        if (rect0Moving) {
            rect0Pos = rect0Pos.add(mouseDelta);
        }
        rect1Moving = rect1Moving && dragging;
        if (rect1Moving) {
            rect1Pos = rect0Pos.add(mouseDelta);
        }
        circle0Moving = circle0Moving && dragging;
        if (circle0Moving) {
            c0Pos = c0Pos.add(mouseDelta);
        } 
        circle1Moving = circle1Moving && dragging;
        if (circle1Moving) {
            c1Pos = c1Pos.add(mouseDelta);
        }
        rect0Collision = false;
        rect1Collision = false;
        circle0Collision = false;
        circle1Collision = false;
        //perform intersection testing
        if (intersectAABB(min0Cpy, max0Cpy, min1Cpy, max1Cpy)) {
            rect0Collision = true;
            rect1Collision = true;
        }
        if (intersectCircle(c0, r0, c1, r1)) {
            circle0Collision = true;
            circle1Collision = true;
        }
        if (intersectCircleAABB(c0, r0, min0Cpy, max0Cpy)) {
            circle0Collision = true;
            rect0Collision = true;
        }
        if (intersectCircleAABB(c0, r0, min1Cpy, max1Cpy)) {
            circle0Collision = true;
            rect1Collision = true;
        }
        if (intersectCircleAABB(c1, r1, min0Cpy, max0Cpy)) {
            circle1Collision = true;
            rect0Collision =true;
        }
        if (intersectCircleAABB(c1, r1, min1Cpy, max1Cpy)) {
            circle1Collision = true;
            rect1Collision = true;
        }
    }

    //Because the rectangle is not rotated, it is an easy shape to use for intersection testing.
    //The shape can be represented with only a minimum and a maximum value.
    private void drawAABB(Graphics g, Vector2f min, Vector2f max){
        
        Matrix3x3f view = getViewportTransform();
        
        Vector2f topLeft = new Vector2f(min.x, max.y);
        topLeft = view.mul(topLeft);
        
        Vector2f bottomRight = new Vector2f(max.x, min.y);
        bottomRight = view.mul(bottomRight);
        
        int rectX = (int)topLeft.x;
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

    //Checking if two AABBs overlap is also easy.
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
    //Most of the comparisons using the distance in the following examples use the squared 
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
    private boolean intersectCircleAABB (Vector2f c, float r, Vector2f min, Vector2f max) {
        float d = 0.0f;
        if (c.x < min.x) d += (c.x - min.x)*(c.x - min.x);
        if (c.x > max.x) d += (c.x - max.x)*(c.x - max.x);
        if (c.y < min.y) d += (c.y - min.y)*(c.y - min.y);
        if (c.y > max.y) d += (c.y - max.y)*(c.y - max.y); 
        return d < r*r;
    }

    protected void render (Graphics g) {
        super.render(g);
        //render instructions
        g.drawString("Dragging:"+dragging, 20, 35);
        g.drawString("Click and hold to drag shapes", 20, 50);
        g.drawString("Press [SPACE] to reset", 20, 65);
        //render objects
        g.setColor(rect0Collision ? Color.BLACK : Color.BLUE);
        drawAABB(g, min0Cpy, max0Cpy);
        
        g.setColor(rect1Collision ? Color.BLACK : Color.BLUE);
        drawAABB(g, min1Cpy, max1Cpy);
        
        g.setColor(circle0Collision ? Color.BLACK : Color.BLUE);
        drawOval(g, c0, r0);
        
        g.setColor(circle1Collision ? Color.BLACK : Color.BLUE);
        drawOval(g, c1, r1);
    }
}
