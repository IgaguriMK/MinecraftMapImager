/*
 *  Main Author: Igaguri
 *  Copyright: 2017 Igaguri
 *  License: MIT LICENSE
 *           See README in repository.
 */

package net.kurikagononaka.mapImager.model.nbt;


import net.kurikagononaka.mapImager.model.nbt.nbtMapper.NbtValue;

import java.util.Arrays;


public class MapFileNbt {
    @NbtValue("/data/scale")
    public int scale;

    @NbtValue("/data/dimension")
    public int dimension;

    @NbtValue("/data/height")
    public int height;

    @NbtValue("/data/width")
    public int width;

    @NbtValue("/data/xCenter")
    public int xCenter;

    @NbtValue("/data/zCenter")
    public int zCenter;

    @NbtValue("/data/colors")
    public byte[] colors;


    public MapFileNbt() {
    }

    public int getScale() {
        return scale;
    }

    public int getDimension() {
        return dimension;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getXCenter() {
        return xCenter;
    }

    public int getZCenter() {
        return zCenter;
    }

    public byte[] getColors() {
        return colors;
    }

    @Override
    public String toString() {
        return "MapFileNbt{" +
                "scale=" + scale +
                ", dimension=" + dimension +
                ", height=" + height +
                ", width=" + width +
                ", xCenter=" + xCenter +
                ", zCenter=" + zCenter +
                ", colors=" + Arrays.toString(colors) +
                '}';
    }
}
