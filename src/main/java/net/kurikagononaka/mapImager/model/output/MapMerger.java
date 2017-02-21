package net.kurikagononaka.mapImager.model.output;

import com.google.common.collect.Lists;
import net.kurikagononaka.mapImager.model.map.MapFile;
import net.kurikagononaka.mapImager.model.map.MergedMap;
import net.kurikagononaka.mapImager.model.map.SingleMapFile;

import java.util.List;
import java.util.stream.Collectors;

public class MapMerger {

    public static final int PARALLEL_THRESHOLD = 32;
    public static final int THREAD_MULTIPLIER = 2;

    public MapMerger() {
    }

    public MergedMap merge(List<SingleMapFile> mapFiles) {
        int chunkSize = chunkSize(mapFiles.size());

        List<List<SingleMapFile>> mapChunks = Lists.partition(mapFiles, chunkSize);

        List<MergedMap> mergedMaps = mapChunks.stream()
                .parallel()
                .map(MapMerger::mergeList)
                .collect(Collectors.toList());

        return mergeList(mergedMaps);
    }

    private static MergedMap mergeList(List<? extends MapFile> mapFiles) {
        MergedMap mergedMap = new MergedMap();

        for (MapFile m : mapFiles) {
            System.out.println("Merging: " + mergedMap.getName() + " << " + m.getName());
            mergedMap.addMap(m);
        }

        return mergedMap;
    }


    private static int chunkSize(int listSize) {
        if (listSize < PARALLEL_THRESHOLD) return listSize;

        return 2 * listSize / Runtime.getRuntime().availableProcessors();
    }
}
