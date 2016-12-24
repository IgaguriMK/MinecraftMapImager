package net.kurikagononaka.mapImager.nbt.dataModel;

import net.kurikagononaka.mapImager.nbt.exception.SourceEndException;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 *
 */
public class NamedTagTest {

    @Test
    public void name() throws Exception {
        TagByte tagByte = new TagByte(ByteBuffer.wrap(new byte[]{0x00, 0x04, 't', 'e', 's', 't',  0x02}));

        assertThat(tagByte.name(), is("test"));
    }

    @Test(expected = SourceEndException.class)
    public void cannotReadName() throws Exception {
        TagByte tagByte = new TagByte(ByteBuffer.wrap(new byte[]{0x00, 0x01}));
    }
}