package net.kurikagononaka.mapImager;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;


/**
 *
 */
public class Main {
    public static void main(String[] args) {

        try {
            InputStream inputStream = Main.class.getResourceAsStream("/map_54.dat");
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);

            int chunkSize = 64;
            byte[] buffer = new byte[chunkSize];

            while (true) {
                int readSize = gzipInputStream.read(buffer, 0, chunkSize);

                if (readSize == -1) break;

                for (int i = 0; i < readSize; i++) {
                    if(buffer[i] < 0) continue;

                    char ch = (char) buffer[i];

                    if(Character.isAlphabetic(ch) || Character.isDigit(ch) ) {
                        System.out.print(ch);
                    } else {
                        System.out.print(".");
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Can't open file.");
            System.exit(1);
        }

        System.out.print("\n");
    }
}


