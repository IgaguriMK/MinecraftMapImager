package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;

/**
 * Created by igaguri on 2017/01/22.
 */
public class SingleMapFile {
    private final MapLocation mapLocation;
    private final ColorImage colorImage;
    private final String name;
    private final int dimension;

    public SingleMapFile(MapFileNbt mapFileNbt, String name) {
        mapLocation = new MapLocation(mapFileNbt);
        colorImage = new ColorImage(mapFileNbt.colors, mapFileNbt.width, mapFileNbt.height);
        this.name = name;
        this.dimension = mapFileNbt.getDimension();
    }

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public ColorImage getColorImage() {
        return colorImage;
    }

    public int getDimension() {
        return dimension;
    }

    public ColorImage getSizedColorImage() {
        Vector2 newSize = mapLocation.getBound().size();
        ColorImage sizedImage = new ColorImage(newSize);

        int bpp = mapLocation.getBlockPerPixel();

        for (int y = 0; y < newSize.y; y++) {
            for (int x = 0; x < newSize.x; x++) {
                sizedImage.set(x, y, colorImage.get(x / bpp, y / bpp));
            }
        }

        return sizedImage;
    }

    public int scale() {
        return mapLocation.getScale();
    }

    public String getName() {
        return name;
    }
}
