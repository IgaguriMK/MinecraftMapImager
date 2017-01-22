package net.kurikagononaka.mapImager;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import net.kurikagononaka.mapImager.model.map.ColorTable;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;
import net.kurikagononaka.mapImager.nbtMapper.NbtMapper;

import javax.imageio.ImageIO;
import java.io.*;


/**
 *
 */
public class Main {
    public static void main(String[] args) {

        InputStream inputStream = Main.class.getResourceAsStream("/map_55.dat");

        try {
            if (args.length >= 1) {
                inputStream = new FileInputStream(args[0]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            System.exit(1);
        }

        try {
            NBTInputStream nbtInputStream = new NBTInputStream(inputStream);

            Tag<?> tag = nbtInputStream.readTag();


            MapFileNbt mapNbt = (MapFileNbt) NbtMapper.parse(MapFileNbt.class, tag);

            MapFile mapFile = new MapFile(mapNbt, new ColorTable());

            ImageIO.write(mapFile.getColorImage().image(), "png", new File("map.png"));

        } catch (IOException e) {
            System.err.println("Can't open file.");
            System.exit(1);
        }
    }
}


