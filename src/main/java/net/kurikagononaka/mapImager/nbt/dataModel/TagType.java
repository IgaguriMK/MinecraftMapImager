package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.InvalidTagIdException;
import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;
import net.kurikagononaka.mapImager.nbt.exception.UnknownTagIdException;

import javax.swing.text.html.HTML;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 *
 */
public enum TagType {

    TAG_End(0),
    TAG_Byte(1),
    TAG_Short(2),
    TAG_Int(3),
    TAG_Long(4),
    TAG_Float(5),
    TAG_Double(6),
    TAG_Byte_Array(7),
    TAG_String(8),
    TAG_List(9),
    TAG_Compound(10),
    TAG_Int_Array(11),;

    private final byte tagId;

    private TagType(final int id) {
        this.tagId = (byte) id;
    }

    public byte getId() {
        return tagId;
    }

    public static TagType parseTag(ByteBuffer byteBuffer) {
        try {
            return parseTag(byteBuffer.get());
        } catch (BufferUnderflowException e) {
            throw new SourceEndException("Input end at reading tag id @(" + byteBuffer.position() + ").", e);
        }
    }

    public static TagType parseTag(byte id) {
        if (id < 0) throw new InvalidTagIdException();

        switch (id) {
            case 0:
                return TAG_End;
            case 1:
                return TAG_Byte;
            case 2:
                return TAG_Short;
            case 3:
                return TAG_Int;
            case 4:
                return TAG_Long;
            case 5:
                return TAG_Float;
            case 6:
                return TAG_Double;
            case 7:
                return TAG_Byte_Array;
            case 8:
                return TAG_String;
            case 9:
                return TAG_List;
            case 10:
                return TAG_Compound;
            case 11:
                return TAG_Int_Array;
        }

        throw new UnknownTagIdException();
    }
}
