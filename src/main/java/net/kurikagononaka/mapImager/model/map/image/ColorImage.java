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

    public ColorImage(byte[] colorId, int width, int height) {
        this(height, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image[y][x] = colorId[x + width * y];
            }
        }
    }

    public byte get(int x, int y) {
        return image[y][x];
    }

    public byte get(Vector2 pos) {
        return get(pos.x, pos.y);
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
}
