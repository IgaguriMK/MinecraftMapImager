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

    private final int north;
    private final int south;
    private final int west;
    private final int east;

    public MapLocation(MapFileNbt nbt) {
        this.scale = nbt.getScale();
        this.blockPerPixel = 1 << this.scale;
        this.center = new Vector2(nbt.getXCenter(), nbt.getYCenter());
        this.pixels = nbt.width;

        north = center.y - (pixels * blockPerPixel / 2);
        south = center.y + (pixels * blockPerPixel / 2) - 1;
        west = center.x - (pixels * blockPerPixel / 2);
        east = center.x + (pixels * blockPerPixel / 2) - 1;
    }

    public Vector2 pixelLocation(Vector2 worldPosition) {
        Vector2 fromCenter = Vector2.diff(worldPosition, center);
        Vector2 pixelPos = Vector2.add(Vector2.div(fromCenter, blockPerPixel), new Vector2(pixels / 2, pixels / 2));

        return pixelPos;
    }

    public Vector2[] cornerBlocks() {
        return new Vector2[]{
                new Vector2(east, north),
                new Vector2(east, south),
                new Vector2(west, north),
                new Vector2(west, south)
        };
    }

    @Override
    public String toString() {
        return "MapLocation{" +
                "blockPerPixel=" + blockPerPixel +
                ", scale=" + scale +
                ", center=" + center +
                ", pixels=" + pixels +
                ", north=" + north +
                ", south=" + south +
                ", west=" + west +
                ", east=" + east +
                '}';
    }
}
