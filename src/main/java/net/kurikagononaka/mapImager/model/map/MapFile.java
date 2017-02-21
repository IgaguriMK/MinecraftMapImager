package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;

public interface MapFile {
    ColorImage getSizedColorImage();

    Bound getBound();

    String getName();
}
