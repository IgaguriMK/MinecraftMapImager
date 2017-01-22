package net.kurikagononaka.mapImager.model.map;

/**
 * Created by igaguri on 2017/01/22.
 */
public class Color {
    private final short red;
    private final short green;
    private final short blue;
    private final short alpha;

    public Color(short red, short green, short blue, short alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(int r, int g, int b, int a) {
        this((short) r, (short) g, (short) b, (short) a);
    }

    public Color(int argb) {
        this.red = (short) ((argb >> 24) & 0xFF);
        this.green = (short) ((argb >> 16) & 0xFF);
        this.blue = (short) ((argb >> 8) & 0xFF);
        this.alpha = (short) (argb & 0xFF);
    }

    public int asIntARGB() {
        return (alpha & 0xff) << 24
                | (red & 0xff) << 16
                | (green & 0xff) << 8
                | (blue & 0xff)
                ;
    }

    public Color multScalar(int mult) {
        int r = mult * red / 255;
        int g = mult * green / 255;
        int b = mult * blue / 255;

        return new Color((short) r, (short) g, (short) b, alpha);
    }

    public Color append(int argb, Color over) {
        if (argb == 0) return over;

        return append(new Color(argb), over);
    }

    public Color append(Color base, Color over) {
        if (base.alpha == 0) return over;

        Color bm = base.multScalar(255 - over.alpha);
        Color om = over.multScalar(over.alpha);

        short a = (byte) (over.alpha - (base.alpha * (255 - over.alpha) / 255));

        return new Color(bm.red + om.red, bm.green + om.green, bm.blue + om.blue, a);
    }
}
