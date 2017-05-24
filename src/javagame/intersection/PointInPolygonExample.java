package javagame.intersection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javagame.util.Matrix3x3f;
import javagame.util.SimpleFramework;
import javagame.util.Utility;
import javagame.util.Vector2f;

public class PointInPolygonExample extends SimpleFramework{

    private boolean selectedBoolean;
    private ArrayList<Vector2f> poly;
    private ArrayList<Vector2f> polyCpy;
    private ArrayList<Vector2f> inside;
    private ArrayList<Vector2f> outside;
    private Vector2f mousePosPoint;
    private boolean windingBoolean;
    private static final int MAX_POINTS = 10000;

    public PointInPolygonExample() {
        appWidth = 640;
        appHeight = 640;
        appTitle = "Point In Polygon Example";
        appBackground = Color.BLACK;
        appFPSColor = Color.GREEN;
    }

    public static void main(String[] args) {
        launchApp(new PointInPolygonExample());
    }

    protected void initialize() {
        super.initialize();
        //Polygon points and lists of point inside
        //and outside the polygon
        poly = new ArrayList<Vector2f>();
        polyCpy = new ArrayList<Vector2f>();
        inside = new ArrayList<Vector2f>();
        outside = new ArrayList<Vector2f>();
        mousePosPoint = new Vector2f();
    }
    
    protected void processInput(float delta) {
        super.processInput(delta);
        //draw polygon for algorithm testing
        mousePosPoint = getWorldMousePosition();
        if(keyboardInputBoolean.keyDownOnce(KeyEvent.VK_SPACE)) {
            windingBoolean = !windingBoolean;
        }
        if (relativeMouseInputBoolean.buttonDownOnce(MouseEvent.BUTTON1)) {
            poly.add(mousePosPoint);
        }
        if (relativeMouseInputBoolean.buttonDownOnce(MouseEvent.BUTTON3)) {
            poly.clear();
        }
    }
    
    protected void updateObjects(float delta) {
        super.updateObjects(delta);
        //see if the relativeMouseInputBoolean is inside the polygon
        selectedBoolean = pointInPolygon(mousePosPoint, poly, windingBoolean);
        //test random points against the polygon
        Random rand = new Random();
        inside.clear();
        outside.clear(); 
        for(int i = 0; i < MAX_POINTS; ++i) {
            float x = rand.nextFloat() * 2.0f - 1.0f;
            float y = rand.nextFloat() * 2.0f - 1.0f;
            Vector2f point = new Vector2f(x, y);
            if (pointInPolygon(point, poly, windingBoolean)) {
                inside.add(point);
            } else {
                outside.add(point);
            }
        }
    }

    //PointInPolygonExample.java
    private boolean pointInPolygon( Vector2f point, List<Vector2f> poly, boolean windingBoolean) { 
        // point in polygon algorithm         
        int inside = 0; 
        
        //Don't bother if there are not at least three lines in the polygon.
        if( poly.size() > 2 ) {
            
            //Start with the last and first points.
            Vector2f start = poly.get( poly.size() - 1 );
            //Set startAbove to true if the first polygon point is above the y value of the point to check.
            boolean startAbove = start.y >= point.y;
            
            //Loop through all the points in the polygon.
            for(int i = 0; i < poly.size(); ++i) {
                
                //Since the code gets the last point in the polygon,start width the first.    
                Vector2f end = poly.get(i);
                //Set endAbove to true if the second polygon point is above the y value of the point to check.
                boolean endAbove = end.y >= point.y;
                
                //If both startAbove and endAbove are equal, then they are either both above or both below the point and there cannot be an intersection.
                if(startAbove != endAbove) {
                    //TBD - test ahead or behind
                    //Once the x intersection value is value is found, it is compared to the point's x value.
                    //If the intersecton value is smaller than the point's value, the intersection is behind the point,
                    //and no intersection occurs.
                    float m = (end.y - start.y) / (end.x - start.x);
                    float x = start.x + (point.y - start.y) / m;
                    if(x >= point.x) {
                        if (windingBoolean) {
                            inside += startAbove ? 1 : -1;
                        } else {
                            inside = inside == 1 ? 0 : 1;
                        }
                    }
                }
                //Save the startAbove state.
                startAbove = endAbove;
                //Make the end point the start point.
                start = end;
            }
        }
        
        //Return the result
        return inside != 0;
    }

    protected void render(Graphics g) {
        super.render(g);
        //render instructions
        g.drawString("Winding: " + (windingBoolean ? "ON" : "OFF"), 20, 35);
        String mouse = String.format("Mouse: (%.2f, %.2f)", mousePosPoint.x, mousePosPoint.y);
        g.drawString(mouse, 20 ,50);
        g.drawString("Left-Click to add points", 20, 65);
        g.drawString("Right-Click to clear points", 20, 80);
        g.drawString("Space Bar to toggle winding",20, 95);
        //2
        g.drawString("23333333333333333", 20, 110);
        g.drawString(poly.toString(), 20, 125);
        g.drawString(polyCpy.toString(), 20, 140);
        //233
        Matrix3x3f viewMatrix3x3f = getViewportTransform();
        //2
        g.drawString(poly.size()+"", 20, 155);
        //233
        //draw test polygon
        if (poly.size() > 1) {
            polyCpy.clear(); 
            for (Vector2f vector: poly) {
                polyCpy.add(viewMatrix3x3f.mul(vector));
            }
            g.setColor(selectedBoolean ? Color.GREEN : Color.BLUE);
            Utility.drawPolygon(g, polyCpy);
        }
        //draw inside point blue,outside points red
        g.setColor(Color.BLUE);
        for (Vector2f vector:inside) {
            Vector2f point = viewMatrix3x3f.mul(vector);
            g.fillRect((int)point.x, (int)point.y, 1, 1);
        }
        g.setColor(Color.RED);
        for (Vector2f vector: outside) {
            Vector2f point = viewMatrix3x3f.mul(vector);
            g.fillRect((int) point.x, (int) point.y, 1, 1);
        }
    }

}
