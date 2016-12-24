package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.NbtParseException;
import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;

import java.nio.ByteBuffer;

/**
 *
 */
public class TagCompound extends NamedTag {

    private NamedTagMap valuesMap;

    public TagCompound(ByteBuffer byteBuffer) {
        super(byteBuffer);

        valuesMap = new NamedTagMap();

        try {
            while (true) {
                TagType tagType = TagType.parseTag(byteBuffer);

                switch (tagType) {
                    case TAG_End:
                        return;

                    case TAG_Byte:
                        valuesMap.put(new TagByte(byteBuffer));
                        break;

                    case TAG_Short:
                        valuesMap.put(new TagShort(byteBuffer));
                        break;

                    case TAG_Int:
                        valuesMap.put(new TagInt(byteBuffer));
                        break;

                    case TAG_Byte_Array:
                        valuesMap.put(new TagByteArray(byteBuffer));
                        break;

                    case TAG_Compound:
                        valuesMap.put(new TagCompound(byteBuffer));
                        break;

                    default:
                        throw new NbtParseException("Not implemented.");
                }
            }
        } catch (SourceEndException e) {
            throw new SourceEndException("Input end at reading compound tag @(" + byteBuffer.position() + ").", e);
        }
    }

    @Override
    public TagType type() {
        return TagType.TAG_Compound;
    }

    public NamedTag get(String name) {
        return valuesMap.get(name);
    }

    public int count() {
        return valuesMap.size();
    }
}
