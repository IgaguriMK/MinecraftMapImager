package net.kurikagononaka.mapImager.model.output;

import net.kurikagononaka.mapImager.model.map.image.ColorImage;
import net.kurikagononaka.mapImager.model.map.image.ColorTable;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by igaguri on 2017/01/23.
 */
public class ColorImageWriter {

    private static final String pngPattern = ".*\\.png$";
    private static final String jpegPattern = ".*\\.jpe?g$";
    private static final String bmpPattern = ".*\\.bmp$";

    private final ColorTable colorTable;

    public ColorImageWriter(ColorTable colorTable) {
        this.colorTable = colorTable;
    }

    public ColorImageWriter() {
        this(new ColorTable());
    }

    public void writeImage(ColorImage image, String name) throws IOException {

        String format;
        File fileName;

        if (Pattern.matches(pngPattern, name)) {
            format = "png";
            fileName = new File(name);
        } else if (Pattern.matches(jpegPattern, name)) {
            format = "jpeg";
            fileName = new File(name);
        } else if (Pattern.matches(bmpPattern, name)) {
            format = "bmp";
            fileName = new File(name);
        } else {
            format = "png";
            fileName = new File(name + ".png");
        }

        ImageIO.write(image.image(colorTable), format, fileName);
    }
}
