package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;

public interface MapFile {
    ColorImage getSizedColorImage();
    ColorImage getColorImage();

    Bound getBound();

    String getName();
}
