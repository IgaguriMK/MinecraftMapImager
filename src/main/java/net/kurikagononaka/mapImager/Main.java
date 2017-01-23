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
                for(int i = 0; i <= 58; i++) {
                    InputStream is = Main.class.getResourceAsStream("/map_" + i + ".dat");

                    if(is != null) {
                        inputStreamList.add(is);
                    }
                }
            }

            List<MapFile> mapFiles = new ArrayList<>();

            for(InputStream inputStream : inputStreamList) {
                MapFile mapFile = new MapFileLoader().loadMapFile(inputStream);
                mapFiles.add(mapFile);
                inputStream.close();
            }

            mapFiles.sort(null);
            MergedMap mergedMap = new MergedMap();

            for(MapFile mapFile : mapFiles) {
                mergedMap.addMap(mapFile);
            }

            new ColorImageWriter().writeImage(mergedMap.getImage(), "map.png");

        } catch (IOException e) {
            System.err.println("IO Error.");
            System.exit(1);
        }
    }
}


