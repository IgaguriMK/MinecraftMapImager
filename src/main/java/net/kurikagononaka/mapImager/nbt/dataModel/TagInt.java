package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 *
 */
public class TagInt extends NamedTag {

    private int value;

    public TagInt(ByteBuffer byteBuffer) {
        super(byteBuffer);

        try {
            value = byteBuffer.getInt();
        } catch (BufferUnderflowException e) {
            throw new SourceEndException("Input end at reading int value.", e);
        }
    }

    @Override
    public TagType type() {
        return TagType.TAG_Int;
    }

    public int getValue() {
        return value;
    }
}
