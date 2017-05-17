import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
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
        
        Shape srcShape = new Rectangle();
        Shape destShape = null;
        Point2D srcPoint2D = new Point2D.Float();
        Point2D destPoint2D = new Point2D.Float();
        Point2D pointSrcPoint2D[] = new Point2D[] {
                new Point2D.Float(),
                new Point2D.Float(),
                new Point2D.Float()
        };
        Point2D pointDestPoint2D[] = new Point2D[3];
        double[] doubleSrcDouble = new double[6];
        double[] doubleDestDouble = new double[6];
        float[] floatSrcFloat = new float[6];
        float[] floatDestFloat = new float[6];
        int srcOffInt = 0;
        int dstOffInt = 0;
        int numPtsInt = 3;
//        array is (x, y) pairs...
//        Point -> Point
        affineTransform.transform(srcPoint2D, destPoint2D);
//        Point[] -> Point[]
        affineTransform.transform(pointSrcPoint2D, srcOffInt, pointDestPoint2D, dstOffInt, numPtsInt);
//        double[] -> double[]
        affineTransform.transform(doubleSrcDouble, srcOffInt, doubleDestDouble, dstOffInt, numPtsInt);
//        float[] -> float[]
        affineTransform.transform(floatSrcFloat,  srcOffInt, floatDestFloat, dstOffInt, numPtsInt);
//        double[] -> float[]
        affineTransform.transform(doubleSrcDouble, srcOffInt, floatDestFloat, dstOffInt, numPtsInt);
//        float[] -> double[]
        affineTransform.transform(floatSrcFloat, srcOffInt, doubleDestDouble, dstOffInt, numPtsInt);
//        Shape -> Shape
        destShape = affineTransform.createTransformedShape(srcShape);
        System.out.println();
        
        affineTransform.rotate( Math.PI / 3.0 );
        affineTransform.translate(5.0, 7.0);
        Shape shape = new Rectangle(0, 0, 100, 150);
        Shape transformed = affineTransform.createTransformedShape(shape);
        
        PathIterator pitPathIterator = transformed.getPathIterator( null );
        float[] segFloat = new float[6];
        while( !pitPathIterator.isDone() ) {
            int segTypeInt = pitPathIterator.currentSegment( segFloat );
            String pointString = "(" + segFloat[0] + ", " + segFloat[1]+") ("+segFloat[2]+", "+segFloat[3]+") ("+segFloat[4]+", "+segFloat[5]+")";
            switch(segTypeInt) {
            case PathIterator.SEG_MOVETO:
                System.out.println("SEG_MOVETO:"+pointString);
                break;
            case PathIterator.SEG_LINETO:
                System.out.println("SEG_LINETO:"+pointString);
                break;
            case PathIterator.SEG_QUADTO:
                System.out.println("SEG_QUADTO:"+pointString);
                break;
            case PathIterator.SEG_CUBICTO:
                System.out.println("SEG_CUBICTO:"+pointString);
                break;
            case PathIterator.SEG_CLOSE:
                System.out.println("SEG_CLOSE:"+pointString);
                break;
            }
            pitPathIterator.next();
        }
    }
}
