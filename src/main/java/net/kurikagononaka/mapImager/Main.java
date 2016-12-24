package net.kurikagononaka.mapImager;

import net.kurikagononaka.mapImager.map.MapFile;
import net.kurikagononaka.mapImager.nbt.GzipBinary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;


/**
 *
 */
public class Main {
    public static void main(String[] args) {

        InputStream inputStream = Main.class.getResourceAsStream("/map_54.dat");

        try {
            if(args.length >= 1) {
                inputStream = new FileInputStream(args[0]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }

        try {
            GzipBinary gzipBinary = new GzipBinary(inputStream);

            System.out.println("size: " + gzipBinary.getBytes().length);

            MapFile map = new MapFile(gzipBinary.getBytes());

        } catch (IOException e) {
            System.err.println("Can't open file.");
            System.exit(1);
        }
    }
}


