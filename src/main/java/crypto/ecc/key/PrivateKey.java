package crypto.ecc.key;

import java.math.BigInteger;

public class PrivateKey {
    private BigInteger raw;

    public PrivateKey(BigInteger privateKey) {
        this.raw = privateKey;
    }

    public void printPrivetKey() {
        System.out.println(raw.toString(16));
    }

    public BigInteger getRaw() {
        return raw;
    }

    public void setRaw(BigInteger raw) {
        this.raw = raw;
    }

}
