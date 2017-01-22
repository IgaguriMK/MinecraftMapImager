package net.kurikagononaka.mapImager.model.nbt.dataModel;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class TagCompoundTest {

    @Test
    public void type() throws Exception {
        TagCompound tagCompound = new TagCompound(ByteBuffer.wrap(
                new byte[]{
                        0x00, 0x00, // name
                        0x00        // end
                }));

        assertThat(tagCompound.type(), is(TagType.TAG_Compound));
    }

    @Test
    public void parseSingleContent() throws Exception {
        TagCompound tagCompound = new TagCompound(ByteBuffer.wrap(
                new byte[]{                // TAG_Compound {
                        0x00, 0x00,         // name : ""
                        0x01,               // TAG_Byte {
                        0x00, 0x01, 'a',    //      name  : "a"
                        0x01,               //      value : 1
                        //      }
                        0x00                // end
                }));

        assertThat(tagCompound.count(), is(1));

        assertThat(tagCompound.get("a"), is(instanceOf(TagByte.class)));
        TagByte tagByte = (TagByte) tagCompound.get("a");
        assertThat(tagByte.getValue(), is((byte) 1));
    }

    @Test
    public void parseSomeContent() throws Exception {
        TagCompound tagCompound = new TagCompound(ByteBuffer.wrap(
                new byte[]{                // TAG_Compound {
                        0x00, 0x00,         // name : ""
                        0x01,               // TAG_Byte {
                        0x00, 0x01, 'a',    //      name  : "a"
                        0x01,               //      value : 1
                        //      }
                        0x02,                   // TAG_Short {
                        0x00, 0x02, 'b', 'c',  //      name  : "a"
                        0x00, 0x02,             //      value : 2
                        //      }
                        0x00                // end
                }));

        assertThat(tagCompound.count(), is(2));

        assertThat(tagCompound.get("a"), is(instanceOf(TagByte.class)));
        TagByte tagByte = (TagByte) tagCompound.get("a");
        assertThat(tagByte.getValue(), is((byte) 1));

        assertThat(tagCompound.get("bc"), is(instanceOf(TagShort.class)));
        TagShort tagShort = (TagShort) tagCompound.get("bc");
        assertThat(tagShort.getValue(), is((short) 2));
    }
}