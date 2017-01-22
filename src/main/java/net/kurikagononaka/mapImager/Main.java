package net.kurikagononaka.mapImager;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;
import net.kurikagononaka.mapImager.nbtMapper.NbtMapper;
import net.kurikagononaka.mapImager.nbtMapper.Path;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 */
public class Main {
    public static void main(String[] args) {

        InputStream inputStream = Main.class.getResourceAsStream("/map_54.dat");

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

            System.out.println(tag.toString());

            MapFileNbt map = (MapFileNbt) NbtMapper.parse(MapFileNbt.class, tag);

            System.out.println(map);

        } catch (IOException e) {
            System.err.println("Can't open file.");
            System.exit(1);
        }
    }
}


