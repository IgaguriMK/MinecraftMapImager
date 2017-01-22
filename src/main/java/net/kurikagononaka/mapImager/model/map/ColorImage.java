package net.kurikagononaka.mapImager.model.map;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

/**
 * Created by igaguri on 2017/01/22.
 */
public class ColorImage {

    private final Color[][] image;
    private final int width;
    private final int height;

    public ColorImage(int width, int height) {
        this.width = width;
        this.height = height;
        image = new Color[height][width];
    }

    public ColorImage(byte[] colorId, ColorTable colorTable, int width, int height) {
        this(height, width);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image[y][x] = colorTable.get(colorId[x + width * y]);
            }
        }
    }

    public Color get(int x, int y) {
        return image[y][x];
    }

    public Color get(Vector2 pos) {
        return get(pos.x, pos.y);
    }

    public BufferedImage image() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, image[y][x].asIntARGB());
            }
        }

        return bufferedImage;
    }
}
