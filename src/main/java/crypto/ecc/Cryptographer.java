package crypto.ecc;

import crypto.ecc.key.KeyPair;
import crypto.ecc.key.PrivateKey;
import crypto.ecc.key.PublicKey;
import crypto.ecc.math.CurveOverField;
import crypto.ecc.math.Point;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Cryptographer {

    private CurveOverField curve;   //The curve to be used as
    private Point G;                //The generator point

    /**
     * This class is for generating private and public keys for the curve you have chosen.
     * Will eventually support message signing also.
     * @param curve The curve you want to use.
     * @param g The generator point you want to use.
     */
    Cryptographer(CurveOverField curve, Point g) {
        this.curve = curve;
        G = g;
    }

    /**
     * Generates an appropriate private key for the chosen curve.
     * I am unsure of how safe this secure random is. But I assume that it is ok.
     * @return The new private key.
     */
    public PrivateKey generatePrivateKey() {
        return new PrivateKey(new BigInteger(curve.privateKeySize(), new SecureRandom()));
    }

    /**
     * Given a certain private key this method will return the corresponding public key using the Elliptic Curve multiplication
     * function
     * @param privateKey The private key you want to generate a public key from
     * @return A new public key in the form of a point on the Elliptic curve.
     * @throws Exception thrown only if something goes really really wrong.
     */
    public PublicKey generatePublicKeyFromPrivateKey(PrivateKey privateKey) throws Exception {
        return new PublicKey(curve.pointMultiplication(G, privateKey.getRaw()));
    }

    /**
     * This method generates a new keypair from scratch using a sufficiently random private key.
     * @return New KeyPair
     * @throws Exception This is thrown if something in the Elliptic curve math has gone really wrong.
     */
    public KeyPair generateNewKeyPair() throws Exception {
        PrivateKey pk = generatePrivateKey();
        return new KeyPair(generatePublicKeyFromPrivateKey(pk), pk);
    }

    //TODO: Implement hashing method
    //TODO: Implement sign method (ECDA)
}
