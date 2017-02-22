package net.kurikagononaka.mapImager.model;

import net.kurikagononaka.mapImager.model.map.MergedMap;
import net.kurikagononaka.mapImager.model.output.ColorImageWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.IOException;

/**
 * Created by igaguri on 2017/02/08.
 */
public class OutputModule {

    public static OutputModule newInstance(CommandLine cmd) {
        String output = cmd.hasOption("o") ? cmd.getOptionValue("o") : "map.png";

        return new OutputModule(output);
    }

    public static void registerOptions(Options options) {
        Option outputOpt = Option.builder("o")
                .longOpt("output")
                .required(false)
                .hasArg()
                .build();

        options.addOption(outputOpt);
    }


    private String outputFileName;
    private ColorImageWriter colorImageWriter;

    public OutputModule(String outputFileName, ColorImageWriter colorImageWriter) {
        this.outputFileName = outputFileName;
        this.colorImageWriter = colorImageWriter;
    }

    public OutputModule(String outputFileName) {
        this(outputFileName, new ColorImageWriter());
    }

    public void writeToFile(MergedMap mergedMap) throws IOException {
        System.err.println("Writing image to file ...");
        colorImageWriter.writeImage(mergedMap.getColorImage(), outputFileName);
    }
}
