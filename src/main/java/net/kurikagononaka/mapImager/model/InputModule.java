package net.kurikagononaka.mapImager.model;

import net.kurikagononaka.mapImager.model.input.MapFileLoader;
import net.kurikagononaka.mapImager.model.map.MapFile;
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

    public List<MapFile> loadAll(String[] files) {
        List<MapFile> mapFiles = new ArrayList<>();

        for (String fileName : files) {
            MapFile mapFile = load(fileName);

            if(filter(mapFile))
                mapFiles.add(mapFile);
        }

        return mapFiles;
    }

    private MapFile load(String fileName) {
        try {
            return loader.loadMapFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean filter(MapFile singleMapFile) {
        return singleMapFile.getDimension() == dimension;
    }
}
