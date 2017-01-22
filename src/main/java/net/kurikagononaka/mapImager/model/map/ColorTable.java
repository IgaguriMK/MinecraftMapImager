package net.kurikagononaka.mapImager.model.map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by igaguri on 2017/01/22.
 */
public class ColorTable {
    private Color[] table;

    public ColorTable(Color[] table) {
        this.table = table;
    }

    public ColorTable() {
        Color[] baseTable = new Color[]{
                new Color(0, 0, 0, 0),
                new Color(127, 178, 56, 255),
                new Color(247, 233, 163, 255),
                new Color(199, 199, 199, 255),
                new Color(255, 0, 0, 255),
                new Color(160, 160, 255, 255),
                new Color(167, 167, 167, 255),
                new Color(0, 124, 0, 255),
                new Color(255, 255, 255, 255),
                new Color(164, 168, 184, 255),
                new Color(151, 109, 77, 255),
                new Color(112, 112, 112, 255),
                new Color(64, 64, 255, 255),
                new Color(143, 119, 72, 255),
                new Color(255, 252, 245, 255),
                new Color(216, 127, 51, 255),
                new Color(178, 76, 216, 255),
                new Color(102, 153, 216, 255),
                new Color(229, 229, 51, 255),
                new Color(127, 204, 25, 255),
                new Color(242, 127, 165, 255),
                new Color(76, 76, 76, 255),
                new Color(153, 153, 153, 255),
                new Color(76, 127, 153, 255),
                new Color(127, 63, 178, 255),
                new Color(51, 76, 178, 255),
                new Color(102, 76, 51, 255),
                new Color(102, 127, 51, 255),
                new Color(153, 51, 51, 255),
                new Color(25, 25, 25, 255),
                new Color(250, 238, 77, 255),
                new Color(92, 219, 213, 255),
                new Color(74, 128, 255, 255),
                new Color(0, 217, 58, 255),
                new Color(129, 86, 49, 255),
                new Color(112, 2, 0, 255),
        };

        List<Color> tableList = new ArrayList<>();

        for(Color baseColor : baseTable) {
            tableList.add(baseColor.multScalar(128));
            tableList.add(baseColor.multScalar(220));
            tableList.add(baseColor);
            tableList.add(baseColor.multScalar(135));
        }

        this.table =  tableList.toArray(new Color[]{});
    }

    public Color get(Byte id) {
        if(id >= table.length)
            return new Color(0, 255, 255, 128);

        return table[id];
    }
}
