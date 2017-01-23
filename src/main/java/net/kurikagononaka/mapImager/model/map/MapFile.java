package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;

/**
 * Created by igaguri on 2017/01/22.
 */
public class MapFile {
    private final MapLocation mapLocation;
    private final ColorImage colorImage;

    public MapFile(MapFileNbt mapFileNbt) {
        mapLocation = new MapLocation(mapFileNbt);
        colorImage = new ColorImage(mapFileNbt.colors, mapFileNbt.width, mapFileNbt.height);
    }

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public ColorImage getColorImage() {
        return colorImage;
    }
}
