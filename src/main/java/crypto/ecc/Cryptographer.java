package crypto.ecc;

import crypto.ecc.math.CurveOverField;
import crypto.ecc.math.Point;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Cryptographer {

    private CurveOverField curve; //The curve to be used as
    private Point G; //The generator point

    public Cryptographer(CurveOverField curve, Point g) {
        this.curve = curve;
        G = g;
    }

    public PrivateKey generatePrivateKey() {
        return new PrivateKey(new BigInteger(256, new SecureRandom()));
    }

    public PublicKey generatePublicKeyFromPrivateKey(PrivateKey privateKey) throws Exception {
        return new PublicKey(curve.pointMultiplication(G, privateKey.getRaw()));
    }

    //TODO: Implement hashing method
    //TODO: Implement sign method (ECDA)
}
