package net.kurikagononaka.mapImager.model.nbt.dataModel;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class TagByteTest {

    @Test
    public void type() throws Exception {
        TagByte tagByte = new TagByte(ByteBuffer.wrap(new byte[]{0x00, 0x00, 0x02}));

        assertThat(tagByte.type(), is(TagType.TAG_Byte));
    }

    @Test
    public void getValue() throws Exception {
        TagByte tagByte = new TagByte(ByteBuffer.wrap(new byte[]{0x00, 0x00, 0x02}));

        assertThat(tagByte.getValue(), is((byte) 2));
    }
}