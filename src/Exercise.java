import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Exercise {
    public static void main( String[] args ) {
//        AffineTransform.setToIdentity();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToIdentity();
        affineTransform.rotate(Math.toRadians(1.0));
        affineTransform.scale(1.0, 0.5);
        affineTransform.translate(50, 0);
        affineTransform.shear(5, 0);
        
        Shape srcShare = new Rectangle();
        Shape destShape = null;
        Point2D srcPoint = new Point2D.Float();
        Point2D destPoint = new Point2D.Float();
        Point2D pointSrc[] = new Point2D[] {
                new Point2D.Float(),
                new Point2D.Float(),
                new Point2D.Float()
        };
        Point2D pointDest[] = new Point2D[3];
        double[] doubleSrc = new double[6];
        double[] doubleDest = new double[6];
        float[] floatSrc = new float[6];
        float[] floatDest = new float[6];
        int srcOff = 0;
        int dstOff = 0;
        int numPts = 3;
//        array is (x, y) pairs...
    }
}