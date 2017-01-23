package net.kurikagononaka.mapImager;

import net.kurikagononaka.mapImager.input.MapFileLoader;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.map.MergedMap;
import net.kurikagononaka.mapImager.output.ColorImageWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            List<InputStream> inputStreamList = new ArrayList<>();

            if (args.length >= 1) {
                for(String a : args) {
                    inputStreamList.add(new FileInputStream(a));
                }
            } else {
                inputStreamList.add(Main.class.getResourceAsStream("/map_54.dat"));
                inputStreamList.add(Main.class.getResourceAsStream("/map_55.dat"));
            }

            MergedMap mergedMap = new MergedMap();

            for(InputStream inputStream : inputStreamList) {
                MapFile mapFile = new MapFileLoader().loadMapFile(inputStream);

                mergedMap.addMap(mapFile);

                inputStream.close();
            }

            new ColorImageWriter().writeImage(mergedMap.getImage(), "map.png");

        } catch (IOException e) {
            System.err.println("IO Error.");
            System.exit(1);
        }
    }
}


