package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 *
 */
public class TagByte extends NamedTag {

    private byte value;

    public TagByte(ByteBuffer byteBuffer) {
        super(byteBuffer);

        try {
            value = byteBuffer.get();
        } catch (BufferUnderflowException e) {
            throw new SourceEndException("Input end at reading byte value @(" + byteBuffer.position() + ").", e);
        }
    }

    @Override
    public TagType type() {
        return TagType.TAG_Byte;
    }

    public byte getValue() {
        return value;
    }
}
