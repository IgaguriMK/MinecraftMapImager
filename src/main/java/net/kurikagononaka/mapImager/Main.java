package net.kurikagononaka.mapImager;

import net.kurikagononaka.mapImager.input.MapFileLoader;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.output.ColorImageWriter;

import java.io.*;


/**
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            InputStream inputStream;

            if (args.length >= 1) {
                inputStream = new FileInputStream(args[0]);
            } else {
                inputStream = Main.class.getResourceAsStream("/map_54.dat");
            }

            MapFile mapFile = new MapFileLoader().loadMapFile(inputStream);

            new ColorImageWriter().writeImage(mapFile.getColorImage(), "map.png");

        } catch (IOException e) {
            System.err.println("IO Error.");
            System.exit(1);
        }
    }
}


