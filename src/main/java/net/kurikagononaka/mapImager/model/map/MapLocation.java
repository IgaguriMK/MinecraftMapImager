package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;

/**
 * Created by igaguri on 2017/01/22.
 */
public class MapLocation {
    private final int blockPerPixel;
    private final int scale;
    private final Vector2 center;
    private final int pixels;

    private final Bound bound;

    public MapLocation(MapFileNbt nbt) {
        this.scale = nbt.getScale();
        this.blockPerPixel = 1 << this.scale;
        this.center = new Vector2(nbt.getXCenter(), nbt.getZCenter());
        this.pixels = nbt.width;

        int northIn = center.y - (pixels * blockPerPixel / 2);
        int southOver = center.y + (pixels * blockPerPixel / 2);
        int westIn = center.x - (pixels * blockPerPixel / 2);
        int eastOver = center.x + (pixels * blockPerPixel / 2);

        bound = new Bound(northIn, southOver, westIn, eastOver);
    }

    public Bound getBound() {
        return bound;
    }

    public int getBlockPerPixel() {
        return blockPerPixel;
    }

    public int getScale() {
        return scale;
    }
}
