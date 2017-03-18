/*
 *  Main Author: Igaguri
 *  Copyright: 2017 Igaguri
 *  License: MIT LICENSE
 *           See README in repository.
 */

package net.kurikagononaka.mapImager.model.map;


public class Bound {
    private final int northIn;
    private final int southOver;
    private final int westIn;
    private final int eastOver;

    public Bound(int northIn, int southOver, int westIn, int eastOver) {

        if (northIn > southOver)
            throw new ArrayIndexOutOfBoundsException("northIn(" + northIn + ") bigger than southOver(" + southOver + ").");
        if (westIn > eastOver)
            throw new ArrayIndexOutOfBoundsException("westIn(" + westIn + ") bigger than eastOver(" + eastOver + ").");

        this.northIn = northIn;
        this.southOver = southOver;
        this.westIn = westIn;
        this.eastOver = eastOver;
    }

    public Bound(Vector2 northWest, Vector2 southEast) {
        this(northWest.y, southEast.y, northWest.x, southEast.x);
    }

    public boolean contains(Vector2 pos) {
        return northIn <= pos.y && pos.y < southOver
                && westIn <= pos.x && pos.x < eastOver;
    }

    public Vector2 northWest() {
        return new Vector2(westIn, northIn);
    }

    public Vector2 southEast() {
        return new Vector2(eastOver, southOver);
    }

    public Vector2 size() {
        return new Vector2(eastOver - westIn, southOver - northIn);
    }

    public Bound merge(Bound other) {
        Vector2 nw = Vector2.min(northWest(), other.northWest());
        Vector2 se = Vector2.max(southEast(), other.southEast());

        return new Bound(nw, se);
    }
}
