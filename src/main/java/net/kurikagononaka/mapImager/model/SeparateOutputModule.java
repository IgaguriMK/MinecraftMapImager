package net.kurikagononaka.mapImager.model;

import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.map.SingleMapFile;
import net.kurikagononaka.mapImager.model.output.ColorImageWriter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SeparateOutputModule {

    public static SeparateOutputModule newInstance(CommandLine cmd) throws IOException{

        if(!cmd.hasOption("s")) {
            return new SeparateOutputModule() {
                @Override
                public void writeToFile(List<SingleMapFile> mapFiles) throws IOException {}
            };
        }

        String output =  cmd.getOptionValue("s");

        Path outputPath = Paths.get(output);

        File outputDir = outputPath.toFile();

        if( outputDir.exists() ) {
            if (!outputDir.isDirectory()) {
                throw new IOException(outputDir.toString() + " is not directory.");
            }
        } else {
            if(! outputDir.mkdirs() ) {
                throw new IOException("Cannot make directory " + outputDir.toString());
            }
        }

        return new SeparateOutputModule(outputPath);
    }

    public static void registerOptions(Options options) {
        Option outputOpt = Option.builder("s")
                .longOpt("separate")
                .required(false)
                .hasArg()
                .build();

        options.addOption(outputOpt);
    }


    private Path outputDir;
    private ColorImageWriter colorImageWriter;

    private SeparateOutputModule(Path outputDir, ColorImageWriter colorImageWriter) {
        this.outputDir = outputDir;
        this.colorImageWriter = colorImageWriter;
    }

    private SeparateOutputModule(Path outputDir) {
        this(outputDir, new ColorImageWriter());
    }

    public SeparateOutputModule() {
    }

    public void writeToFile(List<SingleMapFile> mapFiles) throws IOException {

        System.err.println("Writing separate images ...");

        for (MapFile m : mapFiles) {
            String baseName = FilenameUtils.getBaseName(m.getName());
            Path filePath = Paths.get(baseName + ".png");
            Path allPath = outputDir.resolve(filePath);

            colorImageWriter.writeImage(m.getColorImage(), allPath.toString());
        }
    }
}
