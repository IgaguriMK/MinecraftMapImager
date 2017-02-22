package net.kurikagononaka.mapImager;

import net.kurikagononaka.mapImager.model.InputModule;
import net.kurikagononaka.mapImager.model.OutputModule;
import net.kurikagononaka.mapImager.model.SortModule;
import net.kurikagononaka.mapImager.model.SeparateOutputModule;
import net.kurikagononaka.mapImager.model.map.MergedMap;
import net.kurikagononaka.mapImager.model.map.SingleMapFile;
import net.kurikagononaka.mapImager.model.output.MapMerger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.List;


/**
 *
 */
public class Main {
    public static void main(String[] args) {

        try {
            CommandLine cmd = parseArgs(args);

            InputModule inputModule = InputModule.newInstance(cmd);
            SortModule sortModule = SortModule.newInstance(cmd);
            OutputModule outputModule = OutputModule.newInstance(cmd);
            SeparateOutputModule separateOutputModule = SeparateOutputModule.newInstance(cmd);


            String[] files = cmd.getArgs();

            if (files.length == 0) {
                System.err.println("No input file.");
                System.exit(1);
            }

            System.err.println("Opening " + files.length + " files ...");

            List<SingleMapFile> singleMapFiles = inputModule.loadAll(files);

            separateOutputModule.writeToFile(singleMapFiles);

            System.err.println("Sorting images ...");
            sortModule.sort(singleMapFiles);

            System.err.println("Merging images ...");
            int count = 1;


            MapMerger merger = new MapMerger();
            MergedMap mergedMap = merger.merge(singleMapFiles);


            System.err.println("Writing image to file ...");
            outputModule.writeToFile(mergedMap);

        } catch (IOException e) {
            System.err.println("IO Error.");
            System.exit(1);
        }
    }

    private static CommandLine parseArgs(String[] args) {
        Options options = new Options();

        InputModule.registerOptions(options);
        SortModule.registerOptions(options);
        OutputModule.registerOptions(options);
        SeparateOutputModule.registerOptions(options);

        try {
            return new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        throw new Error("Not reachable");
    }
}


