package net.kurikagononaka.mapImager.model;

import net.kurikagononaka.mapImager.model.input.MapFileLoader;
import net.kurikagononaka.mapImager.model.map.SingleMapFile;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igaguri on 2017/02/08.
 */
public class InputModule {

    public static InputModule newInstance(CommandLine cmd) {
        int dimension = cmd.hasOption("d") ? Integer.valueOf(cmd.getOptionValue("o")) : 0;

        return new InputModule(dimension);
    }

    public static void registerOptions(Options options) {
        Option dimensionOpt = Option.builder("d")
                .longOpt("dimension")
                .required(false)
                .build();

        options.addOption(dimensionOpt);
    }


    private MapFileLoader loader;
    private int dimension;

    public InputModule(int dimension) {
        this.loader = new MapFileLoader();
        this.dimension = dimension;
    }

    public List<SingleMapFile> loadAll(String[] files) {
        List<SingleMapFile> singleMapFiles = new ArrayList<>();

        for (String fileName : files) {
            SingleMapFile singleMapFile = load(fileName);

            if (filter(singleMapFile))
                singleMapFiles.add(singleMapFile);
        }

        return singleMapFiles;
    }

    private SingleMapFile load(String fileName) {
        try {
            return loader.loadMapFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean filter(SingleMapFile singleSingleMapFile) {
        return singleSingleMapFile.getDimension() == dimension;
    }
}
