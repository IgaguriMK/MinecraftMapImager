package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 *
 */
public class TagByteArray extends NamedTag {

    private byte[] value;

    public TagByteArray(ByteBuffer byteBuffer) {
        super(byteBuffer);

        int size = byteBuffer.getInt();
        value = new byte[size];

        try {
            byteBuffer.get(value);
        } catch (BufferUnderflowException e) {
            throw new SourceEndException("Input end at reading byte array.", e);
        }
    }

    @Override
    public TagType type() {
        return TagType.TAG_Byte_Array;
    }

    public byte[] getValue() {
        return value;
    }
}
