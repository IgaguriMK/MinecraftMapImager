package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 *
 */
public class TagShort extends NamedTag {
    private short value;

    public TagShort(ByteBuffer byteBuffer) {
        super(byteBuffer);

        try {
            value = byteBuffer.getShort();
        } catch (BufferUnderflowException e) {
            throw new SourceEndException("Input end at reading short value @(" + byteBuffer.position() + ").", e);
        }
    }

    @Override
    public TagType type() {
        return TagType.TAG_Short;
    }

    public short getValue() {
        return value;
    }
}
