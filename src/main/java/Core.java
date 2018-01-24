import crypto.ecc.Cryptographer;
import crypto.ecc.DefinedCurves;
import crypto.ecc.key.KeyPair;
import crypto.ecc.key.PrivateKey;
import crypto.ecc.key.PublicKey;

import java.math.BigInteger;

public class Core {

    public static void main(String[] args) throws Exception {
        Cryptographer crypto = DefinedCurves.SECP256k1();

        /*
            Generates a random private and public key.
        */
        System.out.println("Random keys: ");
        KeyPair kp = crypto.generateNewKeyPair();
        kp.getPrivateKey().printPrivetKey();
        kp.getPublicKey().printPublicKey();

        System.out.println("-----------------------");
        /*
            Takes a predefined private key and generates the public key from that.
            This example is from chapter 4 of Mastering bitcoin by Andreas A.
        */
        System.out.println("Predefined private key: ");
        PrivateKey privateKey = new PrivateKey(new BigInteger("1E99423A4ED27608A15A2616A2B0E9E52CED330AC530EDCC32C8FFC6A526AEDD", 16));
        PublicKey publicKey = crypto.generatePublicKeyFromPrivateKey(privateKey);
        privateKey.printPrivetKey();
        publicKey.printPublicKey();

    }
}
