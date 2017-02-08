package net.kurikagononaka.mapImager.model.nbt.nbtMapper;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by igaguri on 2017/01/22.
 */
public class Path {
    private String name;
    private Path child;

    public Path(String name) {
        this.name = name;
    }

    public Path(String name, Path child) {
        this.name = name;
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public Path getChild() {
        return child;
    }

    public boolean hasChild() {
        return child != null;
    }

    public static Path parse(String string) {
        String[] split = string.split("/");

        return parse(new LinkedList<>(Arrays.asList(split)));
    }

    @Override
    public String toString() {
        if (child == null) {
            return name;
        }

        return name + "/" + child.toString();
    }

    public static Path parse(LinkedList<String> strings) {

        if (strings.isEmpty()) return null;

        String name = strings.pop();
        Path child = parse(strings);

        return new Path(name, child);
    }
}
