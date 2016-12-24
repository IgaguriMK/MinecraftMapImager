package net.kurikagononaka.mapImager.nbt.dataModel;

import java.util.LinkedHashMap;

/**
 *
 */
public class NamedTagMap extends LinkedHashMap<String, NamedTag> {

    public NamedTagMap() {
        super();
    }

    public NamedTag put(NamedTag value) {
        return super.put(value.name(), value);
    }
}
