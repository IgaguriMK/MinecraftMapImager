package net.kurikagononaka.mapImager.input;

import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.stream.NBTInputStream;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.nbt.MapFileNbt;
import net.kurikagononaka.mapImager.nbtMapper.NbtMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by igaguri on 2017/01/23.
 */
public class MapFileLoader {

    public MapFileLoader() {
    }

    public MapFile loadMapFile(File file) throws IOException {
        return loadMapFile(new FileInputStream(file));
    }

    public MapFile loadMapFile(InputStream inputStream) throws IOException {
        NBTInputStream nbtInputStream = new NBTInputStream(inputStream);

        Tag<?> tag = nbtInputStream.readTag();


        MapFileNbt mapNbt = (MapFileNbt) NbtMapper.parse(MapFileNbt.class, tag);

        return new MapFile(mapNbt);
    }
}
