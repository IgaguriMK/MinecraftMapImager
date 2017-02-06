package net.kurikagononaka.mapImager;

import net.kurikagononaka.mapImager.input.MapFileLoader;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.map.MergedMap;
import net.kurikagononaka.mapImager.output.ColorImageWriter;
import org.apache.commons.cli.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 *
 */
public class Main {
    public static void main(String[] args) {

        Options options = new Options();

        Option outputOpt = Option.builder("o")
                .longOpt("output")
                .required(false)
                .hasArg()
                .build();

        options.addOption(outputOpt);

        try {
            CommandLine cmd = new DefaultParser().parse(options, args);

            String output = cmd.hasOption("o") ? cmd.getOptionValue("o") : "map.png";

            convert(cmd.getArgs(), output);

        } catch (IOException e) {
            System.err.println("IO Error.");
            System.exit(1);
        } catch (ParseException e) {

        }
    }

    private static void convert(String[] files, String output) throws IOException {
        List<InputStream> inputStreamList = new ArrayList<>();

        if (files.length == 0) {
            System.err.println("No input file.");
            System.exit(1);
        }

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if(o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }

                return o1.length() - o2.length();
            }
        });

        System.err.println("Opening " + files.length + " files ...");

        for (String a : files) {
            inputStreamList.add(new FileInputStream(a));
        }

        List<MapFile> mapFiles = new ArrayList<>();

        System.err.println("Parsing files...");

        for (InputStream inputStream : inputStreamList) {
            MapFile mapFile = new MapFileLoader().loadMapFile(inputStream);
            mapFiles.add(mapFile);
            inputStream.close();
        }


        System.err.println("Merging images ...");
        int count = 1;

        mapFiles.sort(null);
        MergedMap mergedMap = new MergedMap();

        for (MapFile mapFile : mapFiles) {
            System.err.println("Merging images (" + (count++) + "/" + mapFiles.size() + ") ...");
            mergedMap.addMap(mapFile);
        }

        System.err.println("Writing image to file ...");
        new ColorImageWriter().writeImage(mergedMap.getImage(), output);
    }
}


