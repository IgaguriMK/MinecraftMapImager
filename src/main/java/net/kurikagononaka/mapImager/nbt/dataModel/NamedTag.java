package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.NbtParseException;
import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/**
 *
 */
public abstract class NamedTag implements NbtTag {

    private String name;

    protected NamedTag(ByteBuffer byteBuffer) {

        try {
            int nameLength = byteBuffer.getShort();

            if (nameLength < 0) throw new NbtParseException("Invalid name length.");

            if (nameLength == 0) {
                name = "";
                return;
            }

            byte[] rawName = new byte[nameLength];
            byteBuffer.get(rawName);

            name = new String(rawName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // Should not reach (UTF-8 is Java default encoding)
            e.printStackTrace();
            System.exit(1);
        } catch (BufferUnderflowException e) {
            throw new SourceEndException("Input end at reading tag name @(" + byteBuffer.position() + ").", e);
        }
    }

    public String name() {
        return name;
    }
}
