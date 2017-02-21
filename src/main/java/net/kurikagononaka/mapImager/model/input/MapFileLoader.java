package net.kurikagononaka.mapImager.model.input;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import net.kurikagononaka.mapImager.model.map.SingleMapFile;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;
import net.kurikagononaka.mapImager.model.nbt.nbtMapper.NbtMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by igaguri on 2017/01/23.
 */
public class MapFileLoader {

    public MapFileLoader() {
    }

    public SingleMapFile loadMapFile(String fileName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(fileName)) {
            NBTInputStream nbtInputStream = new NBTInputStream(inputStream);

            Tag<?> tag = nbtInputStream.readTag();

            MapFileNbt mapNbt = (MapFileNbt) NbtMapper.parse(MapFileNbt.class, tag);

            File file = new File(fileName);
            return new SingleMapFile(mapNbt, file.getName());
        }
    }
}
