package com.company.lang;

import Jama.Matrix;

import java.util.Arrays;
import java.util.Iterator;

public abstract class ISolver implements Iterable<double[]> {
    protected final Matrix A, b;
    protected final double eps;

    public ISolver(double[][] A, double[] b, double eps) {
        this.A = new Matrix(A);
        this.b = new Matrix(b, b.length);
        this.eps = eps;
        if (
                this.A.getColumnDimension() != this.A.getRowDimension()
                        || this.A.getColumnDimension() != this.b.getRowDimension()
                        || this.b.getColumnDimension() != 1
                )
            throw new ParamsException("Invalid parameters' dimensions\n");
    }

    public double[] solve() {
        double[] ans = null;
        for (double[] x : this) {
            ans = x;
        }
        return ans;
    }


    @Override
    public Iterator<double[]> iterator() {
        return new ISolverIterator();
    }


    private class ISolverIterator implements Iterator<double[]> {
        private Matrix prevX;
        private Matrix curX;

        public ISolverIterator() {
            double[] q = new double[A.getRowDimension()];
            Arrays.fill(q, Double.POSITIVE_INFINITY);
            prevX = new Matrix(q, q.length);
            curX = new Matrix(new double[q.length], q.length).minus(prevX);
        }

        @Override
        public boolean hasNext() {
            return !ISolver.this.isPreciousEnoungh(curX.minus(prevX));
        }

        @Override
        public double[] next() {
            prevX = curX;
            curX = countNext();
            if (curX.getColumnDimension() != 1 || curX.getRowDimension() != A.getColumnDimension())
                throw new SolverException("Illegal return value of method \"countNext\" \n\tdimensions expected: " + A.getColumnDimension() + " x 1 \n\tgained: " + curX.getRowDimension() + " x " + curX.getColumnDimension() + "\n");
            return curX.getColumnPackedCopy();
        }
    }

    protected abstract Matrix countNext();

    protected abstract boolean isPreciousEnoungh(Matrix deltaX);


    public class SolverException extends RuntimeException {
        public SolverException(String message) {
            super(message);
        }
    }


    public class ParamsException extends RuntimeException {
        public ParamsException(String message) {
            super(message);
        }
    }
}
