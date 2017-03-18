/*
 *  Main Author: Igaguri
 *  Copyright: 2017 Igaguri
 *  License: MIT LICENSE
 *           See README in repository.
 */

package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;


public class MergedMap implements MapFile {

    private static Object lockObj = new Object();
    private static int count = 0;

    private Bound bound;
    private ColorImage image;
    private String name;

    public MergedMap() {
        bound = null;
        image = null;

        synchronized (lockObj) {
            count++;
            name = "Merge:" + count;
        }
    }

    public void addMap(MapFile mapFile) {

        if (image == null) {
            bound = mapFile.getBound();
            image = mapFile.getSizedColorImage();

            return;
        }

        Bound oldBound = bound;
        ColorImage oldImage = image;

        Bound newBound = mapFile.getBound();
        ColorImage newImage = mapFile.getSizedColorImage();

        bound = oldBound.merge(newBound);
        image = new ColorImage(bound.size());

        Vector2 oldOffset = Vector2.diff(oldBound.northWest(), bound.northWest());
        writeImage(image, oldImage, oldOffset);

        Vector2 newOffset = Vector2.diff(newBound.northWest(), bound.northWest());
        writeImage(image, newImage, newOffset);
    }

    private static void writeImage(ColorImage dest, ColorImage from, Vector2 offset) {
        for (int y = 0; y < from.getSize().y; y++) {
            for (int x = 0; x < from.getSize().x; x++) {
                byte id = from.get(x, y);

                if (id == 0) continue;

                dest.set(x + offset.x, y + offset.y, id);
            }
        }
    }

    @Override
    public ColorImage getColorImage() {
        return image;
    }

    @Override
    public ColorImage getSizedColorImage() {
        return image;
    }

    @Override
    public Bound getBound() {
        return bound;
    }

    public String getName() {
        return name;
    }
}
