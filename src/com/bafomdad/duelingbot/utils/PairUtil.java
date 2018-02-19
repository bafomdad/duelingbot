package com.bafomdad.duelingbot.utils;

/**
 * Created by bafomdad on 2/12/2018.
 */
public class PairUtil<K, V> {

    private final K left;
    private final V right;

    public static <K, V> PairUtil<K, V> createPair(K left, V right) {

        return new PairUtil<K, V>(left, right);
    }

    public PairUtil(K left, V right) {

        this.left = left;
        this.right = right;
    }

    public K getX() {

        return left;
    }

    public V getY() {

        return right;
    }

    public K getLeft() {

        return left;
    }

    public V getRight() {

        return right;
    }
}
