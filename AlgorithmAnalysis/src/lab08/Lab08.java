package lab08;

/*
 *  Compilation:  javac Complex.java
 *  Execution:    java Complex
 *  Dependencies: StdOut.java
 *
 *  Data type for complex numbers.
 *
 *  The data type is "immutable" so once you create and initialize
 *  a Complex object, you cannot change it. The "final" keyword
 *  when declaring re and im enforces this rule, making it a
 *  compile-time error to change the .re or .im fields after
 *  they've been initialized.
 *
 *  % java Complex
 *  a            = 5.0 + 6.0i
 *  b            = -3.0 + 4.0i
 *  Re(a)        = 5.0
 *  Im(a)        = 6.0
 *  b + a        = 2.0 + 10.0i
 *  a - b        = 8.0 + 2.0i
 *  a * b        = -39.0 + 2.0i
 *  b * a        = -39.0 + 2.0i
 *  a / b        = 0.36 - 1.52i
 *  (a / b) * b  = 5.0 + 6.0i
 *  conj(a)      = 5.0 - 6.0i
 *  |a|          = 7.810249675906654
 *  tan(a)       = -6.685231390246571E-6 + 1.0000103108981198i
 *
 ******************************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  The {@code Complex} class represents a complex number.
 *  Complex numbers are immutable: their values cannot be changed after they
 *  are created.
 *  It includes methods for addition, subtraction, multiplication, division,
 *  conjugation, and other common functions on complex numbers.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/99scientific">Section 9.9</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
class Complex {
    double re;   // the real part
    double im;   // the imaginary part

    /**
     * Initializes a complex number from the specified real and imaginary parts.
     *
     * @param real the real part
     * @param imag the imaginary part
     */
    public Complex(double real, double imag) {
        re = real;
        im = imag;
    }

    /**
     * Returns a string representation of this complex number.
     *
     * @return a string representation of this complex number,
     *         of the form 34 - 56i.
     */
    public String toString() {
        if (im == 0) return re + "";
        if (re == 0) return im + "i";
        if (im <  0) return re + " - " + (-im) + "i";
        return re + " + " + im + "i";
    }

    /**
     * Returns the absolute value of this complex number.
     * This quantity is also known as the <em>modulus</em> or <em>magnitude</em>.
     *
     * @return the absolute value of this complex number
     */
    public double abs() {
        return Math.hypot(re, im);
    }

    /**
     * Returns the phase of this complex number.
     * This quantity is also known as the <em>angle</em> or <em>argument</em>.
     *
     * @return the phase of this complex number, a real number between -pi and pi
     */
    public double phase() {
        return Math.atan2(im, re);
    }

    /**
     * Returns the sum of this complex number and the specified complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is {@code (this + that)}
     */
    public Complex plus(Complex that) {
        double real = this.re + that.re;
        double imag = this.im + that.im;
        return new Complex(real, imag);
    }

    /**
     * Returns the result of subtracting the specified complex number from
     * this complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is {@code (this - that)}
     */
    public Complex minus(Complex that) {
        double real = this.re - that.re;
        double imag = this.im - that.im;
        return new Complex(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is {@code (this * that)}
     */
    public Complex times(Complex that) {
        double real = this.re * that.re - this.im * that.im;
        double imag = this.re * that.im + this.im * that.re;
        return new Complex(real, imag);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param  alpha the scalar
     * @return the complex number whose value is {@code (alpha * this)}
     */
    public Complex scale(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     * Returns the product of this complex number and the specified scalar.
     *
     * @param  alpha the scalar
     * @return the complex number whose value is {@code (alpha * this)}
     * @deprecated Replaced by {@link #scale(double)}.
     */
    @Deprecated
    public Complex times(double alpha) {
        return new Complex(alpha * re, alpha * im);
    }

    /**
     * Returns the complex conjugate of this complex number.
     *
     * @return the complex conjugate of this complex number
     */
    public Complex conjugate() {
        return new Complex(re, -im);
    }

    /**
     * Returns the reciprocal of this complex number.
     *
     * @return the complex number whose value is {@code (1 / this)}
     */
    public Complex reciprocal() {
        double scale = re*re + im*im;
        return new Complex(re / scale, -im / scale);
    }

    /**
     * Returns the real part of this complex number.
     *
     * @return the real part of this complex number
     */
    public double re() {
        return re;
    }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return the imaginary part of this complex number
     */
    public double im() {
        return im;
    }

    /**
     * Returns the result of dividing the specified complex number into
     * this complex number.
     *
     * @param  that the other complex number
     * @return the complex number whose value is {@code (this / that)}
     */
    public Complex divides(Complex that) {
        return this.times(that.reciprocal());
    }

    /**
     * Returns the complex exponential of this complex number.
     *
     * @return the complex exponential of this complex number
     */
    public Complex exp() {
        return new Complex(Math.exp(re) * Math.cos(im), Math.exp(re) * Math.sin(im));
    }

    /**
     * Returns the complex sine of this complex number.
     *
     * @return the complex sine of this complex number
     */
    public Complex sin() {
        return new Complex(Math.sin(re) * Math.cosh(im), Math.cos(re) * Math.sinh(im));
    }

    /**
     * Returns the complex cosine of this complex number.
     *
     * @return the complex cosine of this complex number
     */
    public Complex cos() {
        return new Complex(Math.cos(re) * Math.cosh(im), -Math.sin(re) * Math.sinh(im));
    }

    /**
     * Returns the complex tangent of this complex number.
     *
     * @return the complex tangent of this complex number
     */
    public Complex tan() {
        return sin().divides(cos());
    }


    /**
     * Unit tests the {@code Complex} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Complex a = new Complex(5.0, 6.0);
        Complex b = new Complex(-3.0, 4.0);
//        StdOut.println("a            = " + a);
//        StdOut.println("b            = " + b);
//        StdOut.println("Re(a)        = " + a.re());
//        StdOut.println("Im(a)        = " + a.im());
//        StdOut.println("b + a        = " + b.plus(a));
//        StdOut.println("a - b        = " + a.minus(b));
//        StdOut.println("a * b        = " + a.times(b));
//        StdOut.println("b * a        = " + b.times(a));
//        StdOut.println("a / b        = " + a.divides(b));
//        StdOut.println("(a / b) * b  = " + a.divides(b).times(b));
//        StdOut.println("conj(a)      = " + a.conjugate());
//        StdOut.println("|a|          = " + a.abs());
//        StdOut.println("tan(a)       = " + a.tan());
    }

}


class Solving {
    int n;
    int p;
    long primRoot;
    int[] A;
    Complex[] originalCoefficient;
    int fftLen;
    HashMap<Long, Integer> occurCount;

    public Solving (){
        InputReader ir = new InputReader(System.in);
        n = ir.nextInt();
        p = ir.nextInt();
        A = new int[n];
        for (int i = 0; i < n; ++ i) {
//            A[i] = (int) (Math.random() * 100000000);
            A[i] = ir.nextInt();
            A[i] %= p;
            System.out.print(A[i] + " ");
        }
        primRoot = getPrimitiveRoot(p);
        fftLen = calLen(p);
        originalCoefficient = new Complex[fftLen]; // 将多项式弄成偶数项
        for (int i = 0; i < fftLen; ++ i){
            originalCoefficient[i] = new Complex(0, 0);
        }
        setCoefficient();
    }

    private Complex getOmiga (int k, int n){
        return new Complex(Math.cos(- 2 * Math.PI * k / n), Math.sin(- 2 * Math.PI * k / n));
    }

    public void solve (){
        fft(1);
        for (int i = 0; i < fftLen; ++ i){
            originalCoefficient[i] = originalCoefficient[i].times(originalCoefficient[i]);
        }
        fft(-1);
        for (int i = 0; i < fftLen; ++ i){
            originalCoefficient[i] = new Complex((double)(Math.round(originalCoefficient[i].re)), (double)(Math.round(originalCoefficient[i].im)));
//            System.out.println(originalCoefficient[i]);
        }

        int count = 0;
        for (int i = 0; i < n; ++ i){
            if (A[i] == 0){
                ++ count;
            }
        }
        System.out.println(count * n * 2 - count);

        System.out.println((long)originalCoefficient[0].re);

        for (int i = 2; i < p; ++ i){
            System.out.println((long)originalCoefficient[occurCount.get((long)i)].re);
        }

    }

    private void fft (int on){
        change(originalCoefficient, fftLen);
        for (int l = 2; l <= fftLen; l *= 2){
            Complex c = new Complex(Math.cos((-on) * 2 * Math.PI / l), Math.sin((-on) * 2 * Math.PI / l));
            for (int j = 0; j < fftLen; j += l){
                Complex c2 = new Complex(1, 0);
                for(int k = j; k < j + l/2; ++ k){
                    Complex t1 = originalCoefficient[k];
                    Complex t2 = c2.times(originalCoefficient[k + l / 2]);
                    originalCoefficient[k] = t1.plus(t2);
                    originalCoefficient[k + l / 2] = t1.minus(t2);
                    c2 = c2.times(c);
                }
            }
        }
//        int height = Integer.toBinaryString(p).length();
//        for (int i = 1; i <= height; ++ i){
//            int j = (int) Math.pow(2, i);
//            for (int i1 = 0; i1 < fftLen / j; ++ i1) {
//                int k = j / 2;
//                for (int t1 = 0; t1 < k; ++t1) {
//                    int t2 = i1 << 1;
//                    int t3 = t2 + k;
//                    Complex temp = getOmiga(on * t1, j).times(originalCoefficient[t3]);
//                    originalCoefficient[t3] = originalCoefficient[t2].minus(temp);
//                    originalCoefficient[t2] = originalCoefficient[t2].plus(temp);
//                }
//            }
//        }
        if (on == -1){
            for (int i = 0; i < fftLen; ++ i)
                originalCoefficient[i].re /= fftLen;
        }
    }

    private static int calLen (Integer p){
        return (int)Math.pow(2, Integer.toBinaryString(p).length());
    }

    private void setCoefficient (){
        occurCount = new HashMap<>();
        for (int i = 0; i < p - 1; ++ i){
            occurCount.put(calRemain(primRoot, i, p), i);
        }

        for (int i = 0; i < n; ++ i){
            if (A[i] != 0) {
                int indexB = occurCount.get((long)A[i]);
                originalCoefficient[indexB] = originalCoefficient[indexB].plus(new Complex(1, 0));
            }
        }
    }

    private long getPrimitiveRoot (int p){
        long EulerFuncResult = p - 1;
        ArrayList<Integer> factors = factorization(EulerFuncResult);
        for (int i = 2; i < p; ++ i) {
            boolean isRoot = true;
            for (Integer j : factors) {
                if (calRemain(i, EulerFuncResult / j, p) == 1){
                    isRoot = false;
                }
            }
            if (isRoot)
                return i;
        }
        return 0;
    }

    private long calRemain (long base, long pow, long mod){
        long result = 1;
        base = base % mod;
        while (pow > 0) {
            if ((pow & 1) != 0){
                result = (result * base) % mod;
                base = (base * base) % mod;
            } else {
                base = (base * base) % mod;
            }
            pow = pow >> 1;
        }
        return result;
    }

    private ArrayList<Integer> factorization (long num){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 2; i < num; ++ i){
            if (num % i == 0) {
                num = num / i;
                res.add(i);
            }
        }
        return res;
    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

//    static int reverseBits(int x, int n)
//    {
//        int b = 0;
//        while (n != 0)
//        {
//            b <<= 1;
//            System.out.println(b | (x & 1));
//            b |= ( x & 1);
//            x >>= 1;
//            n --;
//        }
//        return b;
//    }

    private void swap (Complex[] a, int i, int j){
        Complex temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void change (Complex[] a, int len){
        int i, j, k;
        for (i = 1, j = len / 2; i < len - 1; i ++){
            if (i < j) {
                swap(a, i, j);
            }

            k = len / 2;
            while (j >= k){
                j -= k;
                k /= 2;
            }
            if (j < k)
                j += k;
        }
    }
}

public class Lab08 {
    public static void main(String[] args) {
        Solving s = new Solving();
        s.solve();
    }
}
