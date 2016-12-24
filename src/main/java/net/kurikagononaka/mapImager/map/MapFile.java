package net.kurikagononaka.mapImager.map;

import net.kurikagononaka.mapImager.nbt.dataModel.TagByte;
import net.kurikagononaka.mapImager.nbt.dataModel.TagCompound;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

/**
 *
 */
public class MapFile {
    private int scale;
    private int dimension;
    private int height;
    private int width;
    private int xCenter;
    private int yCenter;
    private byte[] colors;


    public MapFile(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length - 1);
        byteBuffer.put(Arrays.copyOf(bytes, bytes.length - 1));

        TagCompound root = new TagCompound(byteBuffer);
        TagCompound data = (TagCompound) root.get("data");

        TagByte scale = (TagByte) data.get("scale");
        TagByte dimension = (TagByte) data.get("scale");
    }
}
