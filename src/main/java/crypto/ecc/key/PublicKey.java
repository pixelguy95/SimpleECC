package crypto.ecc.key;

import crypto.ecc.math.Point;

public class PublicKey {

    private Point raw;

    public PublicKey(Point pub) {
        this.raw = pub;
    }

    public void printPublicKey() {
        raw.printPoint();
    }

    public Point getRaw() {
        return raw;
    }

    public void setRaw(Point raw) {
        this.raw = raw;
    }
}
