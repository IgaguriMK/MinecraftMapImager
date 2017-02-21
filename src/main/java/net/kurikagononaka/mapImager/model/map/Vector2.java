package net.kurikagononaka.mapImager.model.map;

/**
 * Created by igaguri on 2017/01/22.
 */
public class Vector2 {
    public final int x;
    public final int y;

    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 add(Vector2 u, Vector2 v) {
        return new Vector2(u.x + v.x, u.y + v.y);
    }

    public static Vector2 diff(Vector2 u, Vector2 v) {
        return new Vector2(u.x - v.x, u.y - v.y);
    }

    public static Vector2 mul(Vector2 v, int a) {
        return new Vector2(v.x * a, v.y * a);
    }

    public static Vector2 div(Vector2 v, int a) {
        return new Vector2(v.x / a, v.y / a);
    }

    public static Vector2 min(Vector2 u, Vector2 v) {
        return new Vector2(
                (u.x < v.x) ? u.x : v.x,
                (u.y < v.y) ? u.y : v.y
        );
    }

    public static Vector2 max(Vector2 u, Vector2 v) {
        return new Vector2(
                (u.x > v.x) ? u.x : v.x,
                (u.y > v.y) ? u.y : v.y
        );
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return y * 128 + x;
    }

    @Override
    public boolean equals(Object obj) {
        Vector2 o = (Vector2) obj;

        return x == o.x && y == o.y;
    }

    public static Vector2 ZERO = new Vector2(0, 0);
}
