package javagame.util;

public class Matrix3x3f {
    
    private float[][] matrix = new float[3][3];
    
    public Matrix3x3f() {
    }
    
    public Matrix3x3f(float[][] m) {
//        setMatrix(m);
    }
    
    public void setMatrix(float[][] m) {
        this.matrix = m;
    }

    public Matrix3x3f add(Matrix3x3f m1) {
        return new Matrix3x3f(new float[][] {
            { 
                this.matrix[0][0] + m1.matrix[0][0],
                this.matrix[0][1] + m1.matrix[0][1],
                this.matrix[0][2] + m1.matrix[0][2] },
            {
                this.matrix[1][0] + m1.matrix[1][0],
                this.matrix[1][1] + m1.matrix[1][1],
                this.matrix[1][2] + m1.matrix[1][2] },
            {
                this.matrix[2][0] + m1.matrix[2][0],
                this.matrix[2][1] + m1.matrix[2][1],
                this.matrix[2][2] + m1.matrix[2][2] } }
        );
    }
    
}
