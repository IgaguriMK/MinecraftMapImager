package net.kurikagononaka.mapImager.nbt.dataModel;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 *
 */
public class TagByteArrayTest {
    @Test
    public void type() throws Exception {
        TagByteArray tagByteArray = new TagByteArray(ByteBuffer.wrap(
                new byte[]{0x00, 0x00,
                        0x00, 0x00, 0x00, 0x02,
                        0x01, 0x02
                }));

        assertThat(tagByteArray.type(), is(TagType.TAG_Byte_Array));
    }

    @Test
    public void getValue() throws Exception {
        TagByteArray tagByteArray = new TagByteArray(ByteBuffer.wrap(
                new byte[]{0x00, 0x00,         // name length (0)
                        0x00, 0x00, 0x00, 0x02, // content length (2)
                        0x01, 0x02              // content
                }));

        byte[] val = tagByteArray.getValue();

        assertThat(val.length, is(2));
        assertThat(val[0], is((byte) 1));
        assertThat(val[1], is((byte) 2));
    }

}