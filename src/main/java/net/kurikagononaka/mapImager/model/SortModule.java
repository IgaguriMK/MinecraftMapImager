package net.kurikagononaka.mapImager.model;

import net.kurikagononaka.mapImager.model.map.SingleMapFile;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by igaguri on 2017/02/08.
 */
public class SortModule implements Comparator<SingleMapFile> {

    public static SortModule newInstance(CommandLine cmd) {
        SortModule sortModule = new SortModule();

        if (!cmd.hasOption("i")) {
            sortModule.addComparator(1, fileNameSorter);
            sortModule.addComparator(0, scaleSorter);
        }

        return sortModule;
    }

    public static void registerOptions(Options options) {
        Option inOrderOpt = Option.builder("i")
                .longOpt("in-order")
                .required(false)
                .build();

        options.addOption(inOrderOpt);
    }


    private PriorityQueue<Entry> comparators;

    private SortModule() {
        comparators = new PriorityQueue<>();
    }

    public void sort(List<SingleMapFile> singleMapFiles) {
        singleMapFiles.sort(this);
    }

    private void addComparator(int priority, Comparator<SingleMapFile> comparator) {
        comparators.add(new Entry(priority, comparator));
    }

    @Override
    public int compare(SingleMapFile o1, SingleMapFile o2) {

        for (Entry e : comparators) {
            int cmp = e.get().compare(o1, o2);

            if (cmp != 0) return cmp;
        }

        return 0;
    }

    private static class Entry implements Comparable<Entry> {
        private final int priority;
        private final Comparator<SingleMapFile> comparator;

        public Entry(int priority, Comparator<SingleMapFile> comparator) {
            this.priority = priority;
            this.comparator = comparator;
        }

        public Comparator<SingleMapFile> get() {
            return comparator;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(Entry o) {
            return this.priority - o.getPriority();
        }
    }

    private static Comparator<SingleMapFile> fileNameSorter = (o1, o2) -> {

        int lenCmp = o1.getName().length() - o2.getName().length();

        if (lenCmp != 0) return lenCmp;

        return o1.getName().compareTo(o2.getName());
    };

    private static Comparator<SingleMapFile> scaleSorter = Comparator.comparingInt(m -> -m.scale());
}
