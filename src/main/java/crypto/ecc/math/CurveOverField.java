package crypto.ecc.math;

import java.math.BigInteger;

public class CurveOverField {

    private BigInteger a, b, prime;

    /**
     * Creates a new curve object of the form:
     * y^2 = x^3 + ax + b
     *
     * @param a The a variable in the equation
     * @param b The b variable in the equation
     * @param p The variable that defines the field the curve is over. usually a prime, thus named prime.
     */
    public CurveOverField(BigInteger a, BigInteger b, BigInteger p) {
        this.a = a;
        this.b = b;
        this.prime = p;
    }

    /**
     * Gives back the same point but inverted over x-axis.
     * Takes the field into account when inverting.
     *
     * @param P The point you want to inverse.
     * @return The inverted point.
     */
    public Point inversePoint(Point P) {
        return new Point(P.getX(), P.getY().negate().mod(prime));
    }

    /**
     * Checks if a point is on the curve.
     * @param P the point
     * @return true if on the curve, false otherwise. Point at infinity is always considered to be on the curve.
     */
    public boolean validatePoint(Point P) {
        if(P.isPointAtInfinity())
            return true;

        return P.getY().pow(2).subtract((P.getX().pow(3).add(P.getX().multiply(a)).add(b))).mod(prime).equals(BigInteger.ZERO);
    }

    /**
     * An elliptic add function. Tried to implement it as clearly as possible. It handles a lot of cases such as points
     * being at infinity, the points being the coincident. In the latter case the tangent of the curve at that point
     * is used as lambda. All the equations can be found at: https://en.wikipedia.org/wiki/Elliptic_curve_point_multiplication
     *
     * Q + P = R --->
     *
     * In case of P and Q being on curve and not coincident:
     * Lambda = (Yq - Yp) * (Xq - Xp)^-1 mod prime
     *
     * In case of P and Q being on the curve but coincident:
     * Lambda = (3Xp^2 + a) * (2Yp)^-1 mod prime
     *
     * The rest of the equations are the same for both:
     * Xr = (Lambda^2 - Xp - Xq) mod prime
     * Yr = (Lambda * (Xp - Xr)) mod prime
     *
     * @param P Point P that should be added to Q
     * @param Q Point Q that should be added to P
     * @return The new point produced by the addition.
     * @throws Exception This exception is thrown if something goes really wrong. Why is it not it's own specific
     * exception? Because I am lazy
     */
    public Point ECAdd(Point P, Point Q) throws Exception {

        if(!(validatePoint(P) && validatePoint(Q)) || (P.isPointAtInfinity() && Q.isPointAtInfinity())) {
            throw new IllegalArgumentException("Q and P needs to be valid points on the curve and both can't " +
                    "be at infinity");
        }

        if(P.isPointAtInfinity())
            return Q;
        else if(Q.isPointAtInfinity())
            return P;
        else if(P.equals(inversePoint(Q)))
            return Point.PAI;
        else if(Q.equals(inversePoint(P)))
            return Point.PAI;
        else {

            BigInteger lambda, Rx, Ry;
            if(P.equals(Q)) {
                lambda = P.getX().pow(2).multiply(new BigInteger("3", 10)).add(a)
                        .multiply(P.getY().multiply(new BigInteger("2", 10)).modInverse(prime));

            }
            else {
                lambda = Q.getY().subtract(P.getY()).multiply(Q.getX().subtract(P.getX()).modInverse(prime));
            }

            Rx = (lambda.pow(2).subtract(P.getX()).subtract(Q.getX())).mod(prime);
            Ry = (lambda.multiply((P.getX().subtract(Rx))).subtract(P.getY())).mod(prime);

            Point res = new Point(Rx, Ry);

            if(!validatePoint(res)) {
                throw new Exception("The resulting point is a piece of shit");
            }

            return res;
        }
    }

    /**
     * This is an implementation of the Double-and-add method. A much faster way of multiplying points with huge numbers.
     * @param G The Point you want to multiply. Usually the so called generator point.
     * @param d The number you want to multiply the point with. Can be enormous, but is limited by the field.
     *          See: http://www.secg.org/sec2-v2.pdf
     * @return The point produced by the multiplication.
     * @throws Exception Exception thrown by the ECAdd function in case something goes really wrong.
     */
    public Point pointMultiplication(Point G, BigInteger d) throws Exception {
        Point N = G;
        Point Q = Point.PAI;

        for(int i = 0; i < d.bitLength(); i++) {
            if (d.testBit(i))
                Q = ECAdd(Q, N);
            N = ECAdd(N, N);
        }

        return Q;
    }
}
