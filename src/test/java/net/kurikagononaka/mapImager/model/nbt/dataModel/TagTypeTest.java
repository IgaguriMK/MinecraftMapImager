package net.kurikagononaka.mapImager.model.nbt.dataModel;

import net.kurikagononaka.mapImager.model.nbt.exception.InvalidTagIdException;
import net.kurikagononaka.mapImager.model.nbt.exception.UnknownTagIdException;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertThat;

/**
 *
 */
public class TagTypeTest {

    @Test
    public void parseType() throws Exception {
        TagType tagEnd = TagType.parseTag((byte) 0x00);
        assertThat(tagEnd, is(TagType.TAG_End));

        TagType tagByte = TagType.parseTag((byte) 0x01);
        assertThat(tagByte, is(TagType.TAG_Byte));

        TagType tagIntArray = TagType.parseTag((byte) 0x0b);
        assertThat(tagIntArray, is(TagType.TAG_Int_Array));
    }

    @Test
    public void parseFromBuffer() throws Exception {
        TagType tagByte = TagType.parseTag(ByteBuffer.wrap(
                new byte[]{
                        0x01
                }));

        assertThat(tagByte, is(TagType.TAG_Byte));
    }

    @Test(expected = InvalidTagIdException.class)
    public void parseInvalidType() throws Exception {
        TagType.parseTag((byte) -1);
    }

    @Test(expected = UnknownTagIdException.class)
    public void parseUnknownType() throws Exception {
        TagType.parseTag((byte) 12);
    }
}