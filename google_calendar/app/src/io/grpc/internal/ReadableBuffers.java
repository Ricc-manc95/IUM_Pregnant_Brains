// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.io.InputStream;
import java.nio.charset.Charset;

// Referenced classes of package io.grpc.internal:
//            ReadableBuffer

public final class ReadableBuffers
{

    public static InputStream openStream(ReadableBuffer readablebuffer, boolean flag)
    {
        return new BufferInputStream(readablebuffer);
    }

    public static String readAsString(ReadableBuffer readablebuffer, Charset charset)
    {
        if (charset == null)
        {
            throw new NullPointerException(String.valueOf("charset"));
        }
        if (readablebuffer == null)
        {
            throw new NullPointerException(String.valueOf("buffer"));
        } else
        {
            int i = readablebuffer.readableBytes();
            byte abyte0[] = new byte[i];
            readablebuffer.readBytes(abyte0, 0, i);
            return new String(abyte0, charset);
        }
    }

    public static ReadableBuffer wrap(byte abyte0[], int i, int j)
    {
        return new ByteArrayWrapper(abyte0, i, j);
    }

    static 
    {
        new ByteArrayWrapper(new byte[0]);
    }

    private class BufferInputStream extends InputStream
        implements KnownLength
    {

        private final ReadableBuffer buffer;

        public final int available()
            throws IOException
        {
            return buffer.readableBytes();
        }

        public final void close()
            throws IOException
        {
            buffer.close();
        }

        public final int read()
        {
            if (buffer.readableBytes() == 0)
            {
                return -1;
            } else
            {
                return buffer.readUnsignedByte();
            }
        }

        public final int read(byte abyte0[], int i, int j)
            throws IOException
        {
            if (buffer.readableBytes() == 0)
            {
                return -1;
            } else
            {
                j = Math.min(buffer.readableBytes(), j);
                buffer.readBytes(abyte0, i, j);
                return j;
            }
        }

        public BufferInputStream(ReadableBuffer readablebuffer)
        {
            if (readablebuffer == null)
            {
                throw new NullPointerException(String.valueOf("buffer"));
            } else
            {
                buffer = (ReadableBuffer)readablebuffer;
                return;
            }
        }
    }


    private class ByteArrayWrapper extends AbstractReadableBuffer
    {

        private final byte bytes[];
        private final int end;
        private int offset;

        public final ReadableBuffer readBytes(int i)
        {
            if (readableBytes() < i)
            {
                throw new IndexOutOfBoundsException();
            } else
            {
                int j = offset;
                offset = offset + i;
                return new ByteArrayWrapper(bytes, j, i);
            }
        }

        public final void readBytes(byte abyte0[], int i, int j)
        {
            System.arraycopy(bytes, offset, abyte0, i, j);
            offset = offset + j;
        }

        public final int readUnsignedByte()
        {
            if (readableBytes() < 1)
            {
                throw new IndexOutOfBoundsException();
            } else
            {
                byte abyte0[] = bytes;
                int i = offset;
                offset = i + 1;
                return abyte0[i] & 0xff;
            }
        }

        public final int readableBytes()
        {
            return end - offset;
        }

        ByteArrayWrapper(byte abyte0[])
        {
            this(abyte0, 0, abyte0.length);
        }

        ByteArrayWrapper(byte abyte0[], int i, int j)
        {
            boolean flag1 = true;
            super();
            boolean flag;
            if (i >= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("offset must be >= 0"));
            }
            if (j >= 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("length must be >= 0"));
            }
            if (i + j <= abyte0.length)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("offset + length exceeds array boundary"));
            }
            if (abyte0 == null)
            {
                throw new NullPointerException(String.valueOf("bytes"));
            } else
            {
                bytes = (byte[])abyte0;
                offset = i;
                end = i + j;
                return;
            }
        }
    }

}
