/*
 *  Main Author: Igaguri
 *  Copyright: 2017 Igaguri
 *  License: MIT LICENSE
 *           See README in repository.
 */

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
        System.err.println("Merging images ...");

        List<List<SingleMapFile>> mapChunks = Lists.partition(mapFiles, chunkSize(mapFiles.size()));

        List<MergedMap> mergedMaps = mapChunks.stream()
                .parallel()
                .map(MapMerger::mergeList)
                .collect(Collectors.toList());


        List<List<MergedMap>> mergedChunks = Lists.partition(mergedMaps, chunkSize(mergedMaps.size()));

        List<MergedMap> mergedMerged = mapChunks.stream()
                .parallel()
                .map(MapMerger::mergeList)
                .collect(Collectors.toList());

        return mergeList(mergedMerged);
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

        return  listSize / (THREAD_MULTIPLIER * Runtime.getRuntime().availableProcessors());
    }
}
