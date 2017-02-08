package net.kurikagononaka.mapImager;

import net.kurikagononaka.mapImager.model.input.MapFileLoader;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.map.MergedMap;
import net.kurikagononaka.mapImager.model.output.ColorImageWriter;
import org.apache.commons.cli.*;

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

        Option inOrderOpt = Option.builder("i")
                .longOpt("in-order")
                .required(false)
                .build();

        options.addOption(inOrderOpt);


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

        List<MapFile> mapFiles = new ArrayList<>();

        for (String fileName : files) {
            MapFile mapFile = new MapFileLoader().loadMapFile(fileName);
            mapFiles.add(mapFile);
        }


        System.err.println("Merging images ...");
        int count = 1;

        MergedMap mergedMap = new MergedMap();

        for (MapFile mapFile : mapFiles) {
            System.err.println("Merging images (" + (count++) + "/" + mapFiles.size() + ") ...");
            mergedMap.addMap(mapFile);
        }

        System.err.println("Writing image to file ...");
        new ColorImageWriter().writeImage(mergedMap.getImage(), output);
    }
}


