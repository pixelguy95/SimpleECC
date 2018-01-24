package crypto.ecc;

import crypto.ecc.math.CurveOverField;
import crypto.ecc.math.Point;

import java.math.BigInteger;

public class DefinedCurves {

    /**
     * SECP256k1 curve defined here: http://www.secg.org/sec2-v2.pdf
     * @return A new crypto object with the given curve.
     */
    public static Cryptographer SECP256k1() {
        BigInteger a = new BigInteger("0", 16);
        BigInteger b = new BigInteger("7", 16);
        BigInteger p = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFC2F", 16);

        CurveOverField curve = new CurveOverField(a, b, p);

        BigInteger Gx = new BigInteger("79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798", 16);
        BigInteger Gy = new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8", 16);
        Point G = new Point(Gx, Gy);

        return new Cryptographer(curve, G);
    }

    /**
     * SECP521k1 curve defined here: http://www.secg.org/sec2-v2.pdf
     * @return A new crypto object with the given curve.
     */
    public static Cryptographer SECP521k1() {
        BigInteger a = new BigInteger("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFC", 16);
        BigInteger b = new BigInteger("0051953EB9618E1C9A1F929A21A0B68540EEA2DA725B99B315F3" +
                "B8B489918EF109E156193951EC7E937B1652C0BD3BB1BF073573DF88" +
                "3D2C34F1EF451FD46B503F00", 16);
        BigInteger p = new BigInteger("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF" +
                "FFFFFFFFFFFFFFFFFFFFFFFF", 16);

        CurveOverField curve = new CurveOverField(a, b, p);

        BigInteger Gx = new BigInteger("00C6858E06B70404E9CD9E3ECB662395B4429C648139053F" +
                "B521F828AF606B4D3DBAA14B5E77EFE75928FE1DC127A2FFA8DE3348" +
                "B3C1856A429BF97E7E31C2E5BD66", 16);
        BigInteger Gy = new BigInteger("011839296A789A3BC0045C8A5FB4" +
                "2C7D1BD998F54449579B446817AFBD17273E662C97EE72995EF42640" +
                "C550B9013FAD0761353C7086A272C24088BE94769FD16650", 16);
        Point G = new Point(Gx, Gy);

        return new Cryptographer(curve, G);
    }
}
