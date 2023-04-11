package util;

public class Pair<A, B> {
    A left;
    B right;

    public Pair(A left, B right) {
        this.left = left;
        this.right = right;
    }

    public A left() {
        return left;
    }

    public B right() {
        return right;
    }
}
