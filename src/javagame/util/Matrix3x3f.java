package javagame.util;

/*
 * The reason the matrix is such a powerful tool is because operations such as transformations and rotaions can be stored into a single matrix and then 
 * applied to objects all at once.
 * Although it is not necessary for a single object,if 
 * hundreds or thousands of polygons need to be transformed,the savings can really add up.
 */
public class Matrix3x3f {

    private float[][] matrix = new float[3][3];
    
    public Matrix3x3f() {
    }

    public Matrix3x3f( float[][] m ) {
        setMatrix(m);
    }

    public void setMatrix(float[][] m) {
        this.matrix = m;
    }

    public Matrix3x3f add(Matrix3x3f m1) {
        return new Matrix3x3f( new float[][] {
            {
                this.matrix[0][0] + m1.matrix[0][0],
                this.matrix[0][1] + m1.matrix[0][1],
                this.matrix[0][2] + m1.matrix[0][2]},
            {
                this.matrix[1][0] + m1.matrix[1][0],
                this.matrix[1][1] + m1.matrix[1][1],
                this.matrix[1][2] + m1.matrix[1][2]},
            {
                this.matrix[2][0] + m1.matrix[2][0],
                this.matrix[2][1] + m1.matrix[2][1],
                this.matrix[2][2] + m1.matrix[2][2]}
        });
    }

    public Matrix3x3f sub(Matrix3x3f m1) {
        return new Matrix3x3f( new float[][] {
            {
                this.matrix[0][0] - m1.matrix[0][0],
                this.matrix[0][1] - m1.matrix[0][1],
                this.matrix[0][2] - m1.matrix[0][2]},
            {
                this.matrix[1][0] - m1.matrix[1][0],
                this.matrix[1][1] - m1.matrix[1][1],
                this.matrix[1][2] - m1.matrix[1][2]},
            {
                this.matrix[2][0] - m1.matrix[2][0],
                this.matrix[2][1] - m1.matrix[2][1],
                this.matrix[2][2] - m1.matrix[2][2]}
        });
    }

    public Matrix3x3f mul(Matrix3x3f m1) {
        return new Matrix3x3f( new float[][] {
            {
                 this.matrix[0][0] * m1.matrix[0][0] //******
                +this.matrix[0][1] * m1.matrix[1][0] //M[0, 0]
                +this.matrix[0][2] * m1.matrix[2][0],//******
                 this.matrix[0][0] * m1.matrix[0][1] //****** 
                +this.matrix[0][1] * m1.matrix[1][1] //M[0, 1]
                +this.matrix[0][2] * m1.matrix[2][1],//******
                 this.matrix[0][0] * m1.matrix[0][2] //******
                +this.matrix[0][1] * m1.matrix[1][2] //M[0, 2]
                +this.matrix[0][2] * m1.matrix[2][2]},//******
            {
                 this.matrix[1][0] * m1.matrix[0][0] //******
                +this.matrix[1][1] * m1.matrix[1][0] //M[1, 0]
                +this.matrix[1][2] * m1.matrix[2][0],//******
                 this.matrix[1][0] * m1.matrix[0][1] //******
                +this.matrix[1][1] * m1.matrix[1][1] //M[1, 1]
                +this.matrix[1][2] * m1.matrix[2][1],//******
                 this.matrix[1][0] * m1.matrix[0][2] //******
                +this.matrix[1][1] * m1.matrix[1][2] //M[1, 2]
                +this.matrix[1][2] * m1.matrix[2][2]},//******
            {
                 this.matrix[2][0] * m1.matrix[0][0] //******
                +this.matrix[2][1] * m1.matrix[1][0] //M[2, 0]
                +this.matrix[2][2] * m1.matrix[2][0],//******
                 this.matrix[2][0] * m1.matrix[0][1] //******
                +this.matrix[2][1] * m1.matrix[1][1] //M[2, 1]
                +this.matrix[2][2] * m1.matrix[2][1],//******
                 this.matrix[2][0] * m1.matrix[0][2] //******
                +this.matrix[2][1] * m1.matrix[1][2] //M[2, 2]
                +this.matrix[2][2] * m1.matrix[2][2]}//******
        });
    }

    public Vector2f mul(Vector2f vec) {
        return new Vector2f(
                 vec.x * this.matrix[0][0] //
                +vec.y * this.matrix[1][0] //V.x
                +vec.w * this.matrix[2][0],//
                 vec.x * this.matrix[0][1] //V.y
                +vec.y * this.matrix[1][1] //
                +vec.w * this.matrix[2][1],
                 vec.x * this.matrix[0][2] //
                +vec.y * this.matrix[1][2]
                +vec.w * this.matrix[2][2]
        );
    }
    
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 3; ++i) {
            stringBuilder.append("[");
            stringBuilder.append(matrix[i][0]);
            stringBuilder.append(",\t");
            stringBuilder.append(matrix[i][1]);
            stringBuilder.append(",'t");
            stringBuilder.append(matrix[i][2]);
            stringBuilder.append("]\n");
        }
        return stringBuilder.toString();
    }
    
    public static Matrix3x3f zero(){
        return new Matrix3x3f(new float[][] {
            { 0,0f, 0.0f, 0.0f },
            { 0.0f, 0.0f, 0.0f },
            { 0.0f, 0.0f, 0.0f }
        });
    }

    public static Matrix3x3f identity() {
        return new Matrix3x3f(new float[][] {
            { 1.0f, 0.0f, 0.0f},
            { 0.0f, 1.0f, 0.0f},
            { 0.0f, 0.0f, 1.0f}
        });
    }

    public static Matrix3x3f translate(Vector2f v) {
        return translate(v.x, v.y);
    }

    public static Matrix3x3f translate(float x, float y) {
        return new Matrix3x3f(new float[][] {
            {1.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f},
            {x, y, 1.0f}
        });
    }

    public static Matrix3x3f scale(Vector2f v) {
        return scale(v.x, v.y);
    }

    public static Matrix3x3f scale(float x, float y) {
        return new Matrix3x3f(new float[][] {
            {x, 0.0f, 0.0f},
            {0.0f, y, 0.0f},
            {0.0f, 0.0f, 1.0f}
        });
    }

    public static Matrix3x3f shear(Vector2f v) {
        return shear(v.x, v.y);
    }

    public static Matrix3x3f shear(float x, float y) {
        return new Matrix3x3f(new float[][] {
            { 1.0f, y, 0.0f },
            { x, 1.0f, 0.0f },
            {0.0f, 0.0f, 1.0f}
        });
    }

     // 屏幕y轴正方向向下
    public static Matrix3x3f rotate(float radian) {
        return new Matrix3x3f(new float[][] {
            { (float)Math.cos(radian), (float)Math.sin(radian), 0.0F},
            { (float) -Math.sin(radian), (float)Math.cos(radian), 0.0F},
            { 0.0f, 0.0f, 1.0f }    
        });
    }


}
