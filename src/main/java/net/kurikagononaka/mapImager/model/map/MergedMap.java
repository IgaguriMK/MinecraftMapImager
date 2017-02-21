package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;

/**
 * Created by igaguri on 2017/01/23.
 */
public class MergedMap {
    private Bound bound;

    private ColorImage image;

    public MergedMap() {
        bound = null;
        image = null;
    }

    public void addMap(SingleMapFile singleMapFile) {

        if(image == null) {
            bound = singleMapFile.getBound();
            image = singleMapFile.getSizedColorImage();

            return;
        }

        Bound oldBound = bound;
        ColorImage oldImage = image;

        Bound newBound = singleMapFile.getBound();
        ColorImage newImage = singleMapFile.getSizedColorImage();

        bound = oldBound.merge(newBound);
        image = new ColorImage(bound.size());

        Vector2 oldOffset = Vector2.diff(oldBound.northWest(), bound.northWest());
        writeImage(image, oldImage, oldOffset);

        Vector2 newOffset = Vector2.diff(newBound.northWest(), bound.northWest());
        writeImage(image, newImage, newOffset);
    }

    private static void writeImage(ColorImage dest, ColorImage from, Vector2 offset) {
        for(int y = 0; y < from.getSize().y; y++) {
            for(int x = 0; x < from.getSize().x; x++) {
                byte id = from.get(x, y);

                if(id == 0) continue;

                dest.set(x + offset.x, y + offset.y, id);
            }
        }
    }

    public ColorImage getImage() {
        return image;
    }
}
