package net.kurikagononaka.mapImager;

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

            int chunkSize = 16;

            GzipBinary gzipBinary = new GzipBinary(inputStream, chunkSize);
            List<GzipBinary.Chunk> chunks = gzipBinary.getChunks();

            for(GzipBinary.Chunk c : chunks){

                int readSize = c.getSize();
                byte[] buffer = c.getBytes();

                for(int i = 0; i < chunkSize; i++) {

                    if(i > 0) System.out.print(" ");

                    if(i < readSize) {
                        System.out.print(String.format("%02x", buffer[i]));
                    } else {
                        System.out.print("  ");
                    }
                }

                System.out.print(" | ");

                for (int i = 0; i < readSize; i++) {
                    if(buffer[i] < 0) {
                        System.out.print(".");
                        continue;
                    }

                    char ch = (char) buffer[i];

                    if(Character.isAlphabetic(ch) || Character.isDigit(ch) ) {
                        System.out.print(ch);
                    } else {
                        System.out.print(".");
                    }
                }

                System.out.println();
            }

            System.out.println();
            System.out.println("Total: " + gzipBinary.getBytes().length);

        } catch (IOException e) {
            System.err.println("Can't open file.");
            System.exit(1);
        }
    }
}


