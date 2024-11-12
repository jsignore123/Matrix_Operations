import java.math.BigDecimal;

public class MatrixOperations {

    /**
     * @param origMatrix 2 dimensional matrix representing linear equation coefficients
     * @param b array representing answers to each linear equation
     * ...Ax = b
     * @return origMatrix augmented with b, put into reduced row echelon form  using Gauss Jordan Elimination
     */
    public double[][] GaussJordanElimination(double[][] origMatrix, double[] b){
        int rows = origMatrix.length;
        int origCols = origMatrix[0].length;
        //make new array with appended column for answers
        double[][] A = new double[rows][origCols + 1];

        for(int i=0; i<rows; i++){
            //copy values from array
            System.arraycopy(origMatrix[i], 0, A[i], 0, origCols);
            //copy values from b to appended column at end of A
            A[i][origCols] = b[i];
        }

        //loop over each col
        for(int i=0; i<rows; i++){
            int pivotrow = i;
            //loop over rows in column, look for row with larger pivot
            for(int j=i+1; j<rows; j++){
                if(Math.abs(A[j][i]) > Math.abs(A[pivotrow][i])){
                    pivotrow = j;
                }
            }

            //if pivot is not 0, swap rows and row reduce
            if(A[pivotrow][i] != 0) {
                //swap rows so higher pivot is on top
                double[] rowtoreplace = A[i].clone();
                double[] rowhigherpivot = A[pivotrow].clone();
                A[i] = rowhigherpivot;
                A[pivotrow] = rowtoreplace;

                //create 0s below pivot
                for (int j = i + 1; j < rows; j++) {
                    double scalar = A[j][i] / A[i][i];
                    for (int k = i; k <= rows; k++) {
                        A[j][k] = A[j][k] - A[i][k] * scalar;
                    }
                }

                //create 0s above pivot
                for (int j = i - 1; j >= 0; j--) {
                    double scalar = A[j][i] / A[i][i];
                    for (int k = i; k <= rows; k++) {
                        A[j][k] = A[j][k] - A[i][k] * scalar;
                    }
                }
            }


        }

        //make each pivot 1 and scale answer (last col) accordingly
        for(int i=0; i<rows; i++){
            A[i][rows] = A[i][rows] / A[i][i];
            if(A[i][i] != 0){
                A[i][i] = 1;
            }
            else{
                for(int j=i+1; j<rows; j++){
                    if (A[i][j] != 0) {
                        A[i][j] = 1;
                        break;
                    }
                }
            }
        }

        //round every value to 2 decimal places
        for(int i=0; i<rows; i++){
            for(int j=0; j<rows+1; j++){
                A[i][j] = Math.round(A[i][j] * 100.0) / 100.0;
            }
        }



        return A;
    }

    /**
     * @param origMatrix 2D augmented matrix (linear equation coefficients paired with their answer as the last entry in their individual rows)
     * @return the matrix in reduced row echelon form, using Gauss Jordan elimination
     */
    public double[][] GaussJordanEliminationFullAugMatrix(double[][] origMatrix){
        int rows = origMatrix.length;
        int origCols = origMatrix[0].length;
        //make new array with appended column for answers
        double[][] A = new double[rows][origCols];

        for(int i=0; i<rows; i++){
            //copy values from array
            System.arraycopy(origMatrix[i], 0, A[i], 0, origCols);
        }

        //loop over each row
        for(int i=0; i<rows; i++){
            int pivotrow = i;
            //loop over, look for row with bigger pivot
            for(int j=i+1; j<rows; j++){
                if(Math.abs(A[j][i]) > Math.abs(A[pivotrow][i])){
                    pivotrow = j;
                }
            }

            //if pivot is not 0, swap rows and row reduce
            if(A[pivotrow][i] != 0) {
                //swap rows so higher pivot is on top
                double[] rowtoreplace = A[i].clone();
                double[] rowhigherpivot = A[pivotrow].clone();
                A[i] = rowhigherpivot;
                A[pivotrow] = rowtoreplace;

                //create 0s below pivot
                for (int j = i + 1; j < rows; j++) {
                    double scalar = A[j][i] / A[i][i];
                    for (int k = i; k <= rows; k++) {
                        A[j][k] = A[j][k] - A[i][k] * scalar;
                    }
                }

                //create 0s above pivot
                for (int j = i - 1; j >= 0; j--) {
                    double scalar = A[j][i] / A[i][i];
                    for (int k = i; k <= rows; k++) {
                        A[j][k] = A[j][k] - A[i][k] * scalar;
                    }
                }
            }
        }



        //make each pivot 1 and scale answer (last col) accordingly
        for(int i=0; i<rows; i++){
            A[i][rows] = A[i][rows] / A[i][i];
            if(A[i][i] != 0){
                A[i][i] = 1;
            }
            else{
                for(int j=i+1; j<rows; j++){
                    if (A[i][j] != 0) {
                        A[i][j] = 1;
                        break;
                    }
                }
            }
        }

        //round every value to 2 decimal places
        for(int i=0; i<rows; i++){
            for(int j=0; j<rows+1; j++){
                A[i][j] = Math.round(A[i][j] * 100.0) / 100.0;
            }
        }





        return A;
    }

    /**
     * @param A Matrix mxn
     * @param b Vector of n entries
     * @return A * b
     */
    public double[] MatrixVectorMultiplication(double[][] A, double[] b) {
        int Cols = A[0].length;
        int Rows = A.length;
        int VectorEntries = b.length;
        if(VectorEntries != Cols){
            System.out.println("ERROR: Vector does not have the same number of entries as Matrix has columns, cannot perform multiplication");
            return new double[0];
        }

        double[] result = new double[Rows];

        //matrix x vector:
        //for each row in A
        for(int r=0; r<Rows; r++){
            //for each element in each row in A
            for(int c=0; c<Cols; c++){
                //add current element at r,c in Matrix * rth element in vector to rth element in resultant vector
                //(multiply each column of each row of A by each column of b and add them to get that row's sum)
                result[r] += A[r][c] * b[c];
            }
        }

        return result;
    }

    /**
     * @param b vector
     * @return new vector with all of b's elements rounded to 3 decimal places
     */
    public BigDecimal[] roundVector(double[] b){
        int Rows = b.length;

        BigDecimal[] correctRounding = new BigDecimal[Rows];

        //round every value to 2 decimal places
        //had to use BigDecimal to correct for rounding errors
        //  (double is not enough memory for higher decimals and would cause rounding errors)
        for(int r=0; r<Rows; r++){
            correctRounding[r] = BigDecimal.valueOf(Math.round((b[r] * 1000)) / 1000.0);
        }
        return correctRounding;
    }

    /**
     * @param A mxn matrix
     * @param column number between 0 and n-1 (0 indexing in program)
     * @return the column at the given column index
     */
    public double[] getColumn(double[][] A, int column){
        int ACols = A[0].length;
        int ARows = A.length;

        if(column-1 > ACols || column<0){
            System.out.println("ERROR: column index out of scope of matrix");
            return new double[0];
        }

        double[] col = new double[ARows];

        for(int r=0; r<ARows; r++){
            col[r] = A[r][column];
        }

        return col;
    }

    /**
     * @param A mxn matrix
     * @param B nxj matrix
     * note: j and m may be the same but needn't be
     * @return A * B, or AB
     */
    public double[][] MatrixMatrixMultiplication(double[][] A, double[][] B){
        int ACols = A[0].length;
        int ARows = A.length;
        int BCols = B[0].length;
        int BRows = B.length;
        if(BRows != ACols){
            System.out.println("ERROR: Matrix B does not have the same number of rows as Matrix A has columns, cannot perform multiplication");
            return new double[0][0];
        }

        double[][] result = new double[ARows][BCols];

        //multiply A by each column in B
        for(int c=0; c<BCols; c++){
            double[] thisColumn = this.MatrixVectorMultiplication(A, this.getColumn(B, c));

            //set each row in column c to result from matrix vector multiplication
            for(int r=0; r<ARows; r++){
                result[r][c] = thisColumn[r];
            }
        }

        return result;

    }

    /**
     * @param A mxn matrix
     * @param power power to raise A to
     * @return A^power
     */
    public double[][] MatrixToPower(double[][] A, int power){
        double[][] result = A.clone();

        //multiply A by A, power number of times
        for(int p=1; p<power; p++){
            result = this.MatrixMatrixMultiplication(result, A);
        }

        return result;
    }

    //you can define matrices and perform operations on them here:
    public static void main(String[] argv){

    }
}
