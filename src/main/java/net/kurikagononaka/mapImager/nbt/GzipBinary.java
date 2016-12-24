package net.kurikagononaka.mapImager.nbt;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 *
 */
public class GzipBinary {
    private static final int defaultChunkSize = 1024;

    private List<Chunk> chunks;

    public GzipBinary(InputStream stream) throws IOException {
        this(stream, defaultChunkSize);
    }

    public GzipBinary(InputStream stream, int chunkSize) throws IOException {

        GZIPInputStream gzipInputStream = new GZIPInputStream(stream);

        chunks = new ArrayList<>();

        byte[] buffer = new byte[chunkSize];

        while (true) {
            int readSize = gzipInputStream.read(buffer, 0, chunkSize);

            if( readSize <=0 ) break;

            chunks.add(new Chunk(Arrays.copyOf(buffer, readSize)));
        }

        gzipInputStream.close();
        stream.close();


    }

    public byte[] getBytes() {

        int size = 0;

        for(Chunk c : chunks) {
            size += c.getSize();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(size);

        for(Chunk c : chunks) {
            byteBuffer.put(c.getBytes());
        }

        return byteBuffer.array();
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public static class Chunk {
        private byte[] bytes;

        public Chunk(byte[] bytes) {
            this.bytes = bytes;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public int getSize()
        {
            return bytes.length;
        }
    }
}
