package crypto.ecc.math;

import java.math.BigInteger;

public class Point {
    private BigInteger x, y;
    private boolean pointAtInfinity = false;

    public static final Point PAI = new Point();

    public Point() {
        this.x = BigInteger.ZERO;
        this.y = BigInteger.ZERO;
        this.pointAtInfinity = true;
    }

    public Point(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public void printPoint() {
        System.out.println("x: " + x.toString(16));
        System.out.println("y: " + y.toString(16));
    }

    public boolean isPointAtInfinity() {
        return pointAtInfinity;
    }

    public void setPointAtInfinity(boolean pointAtInfinity) {
        this.pointAtInfinity = pointAtInfinity;
    }

    public BigInteger getX() {
        return x;
    }

    public void setX(BigInteger x) {
        this.x = x;
    }

    public BigInteger getY() {
        return y;
    }

    public void setY(BigInteger y) {
        this.y = y;
    }

    public boolean equals(Point p){
        return this.getX().equals(p.getX()) && this.getY().equals(p.getY());
    }
}
