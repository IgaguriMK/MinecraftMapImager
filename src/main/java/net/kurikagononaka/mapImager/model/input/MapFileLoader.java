package net.kurikagononaka.mapImager.model.input;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;
import net.kurikagononaka.mapImager.model.nbt.nbtMapper.NbtMapper;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by igaguri on 2017/01/23.
 */
public class MapFileLoader {

    public MapFileLoader() {
    }

    public MapFile loadMapFile(String fileName) throws IOException {
        try(FileInputStream inputStream = new FileInputStream(fileName)) {
            NBTInputStream nbtInputStream = new NBTInputStream(inputStream);

            Tag<?> tag = nbtInputStream.readTag();

            MapFileNbt mapNbt = (MapFileNbt) NbtMapper.parse(MapFileNbt.class, tag);

            return new MapFile(mapNbt, fileName);
        }
    }
}
