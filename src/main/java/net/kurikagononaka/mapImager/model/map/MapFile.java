/*
 *  Main Author: Igaguri
 *  Copyright: 2017 Igaguri
 *  License: MIT LICENSE
 *           See README in repository.
 */

package net.kurikagononaka.mapImager.model.map;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;

public interface MapFile {
    ColorImage getSizedColorImage();
    ColorImage getColorImage();

    Bound getBound();

    String getName();
}
