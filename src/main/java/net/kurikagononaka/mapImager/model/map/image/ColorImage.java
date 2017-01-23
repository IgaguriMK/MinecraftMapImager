package net.kurikagononaka.mapImager.model.map.image;

import net.kurikagononaka.mapImager.model.map.Vector2;

import java.awt.image.BufferedImage;

/**
 * Created by igaguri on 2017/01/22.
 */
public class ColorImage {

    private final byte[][] image;
    private final int width;
    private final int height;

    public ColorImage(int width, int height) {
        this.width = width;
        this.height = height;
        image = new byte[height][width];
    }

    public ColorImage(Vector2 size) {
        this(size.x, size.y);
    }

    public ColorImage(byte[] colorId, int width, int height) {
        this(height, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image[y][x] = colorId[x + width * y];
            }
        }
    }

    public byte get(int x, int y) {
        if(x < 0 || width <= x) return 0;
        if(y < 0 || height <= y) return 0;

        return image[y][x];
    }

    public byte get(Vector2 pos) {
        return get(pos.x, pos.y);
    }

    public void set(int x, int y, byte id) {
        if(x < 0 || width <= x) throw new ArrayIndexOutOfBoundsException("Illegal argument x: " + x + ".");
        if(y < 0 || height <= y) throw new ArrayIndexOutOfBoundsException("Illegal argument y: " + y + ".");

        image[y][x] = id;
    }

    public BufferedImage image(ColorTable colorTable) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, colorTable.get(image[y][x]).asIntARGB());
            }
        }

        return bufferedImage;
    }

    public  Vector2 getSize() {
        return new Vector2(width, height);
    }
}
