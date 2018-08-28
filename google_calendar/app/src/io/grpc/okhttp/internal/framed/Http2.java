// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.okhttp.internal.framed;

import java.io.IOException;
import java.util.logging.Logger;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

// Referenced classes of package io.grpc.okhttp.internal.framed:
//            Variant, FrameReader, FrameWriter

public final class Http2
    implements Variant
{
    static final class FrameLogger
    {

        private static final String BINARY[];
        private static final String FLAGS[];
        private static final String TYPES[] = {
            "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"
        };

        static String formatHeader(boolean flag, int i, int j, byte byte0, byte byte1)
        {
            String s;
            String s2;
            if (byte0 < TYPES.length)
            {
                s2 = TYPES[byte0];
            } else
            {
                s2 = String.format("0x%02x", new Object[] {
                    Byte.valueOf(byte0)
                });
            }
            if (byte1 != 0) goto _L2; else goto _L1
_L1:
            s = "";
_L4:
            String s1;
            if (flag)
            {
                s1 = "<<";
            } else
            {
                s1 = ">>";
            }
            return String.format("%s 0x%08x %5d %-13s %s", new Object[] {
                s1, Integer.valueOf(i), Integer.valueOf(j), s2, s
            });
_L2:
            switch (byte0)
            {
            case 5: // '\005'
            default:
                if (byte1 < FLAGS.length)
                {
                    s1 = FLAGS[byte1];
                } else
                {
                    s1 = BINARY[byte1];
                }
                if (byte0 == 5 && (byte1 & 4) != 0)
                {
                    s = s1.replace("HEADERS", "PUSH_PROMISE");
                } else
                {
                    s = s1;
                    if (byte0 == 0)
                    {
                        s = s1;
                        if ((byte1 & 0x20) != 0)
                        {
                            s = s1.replace("PRIORITY", "COMPRESSED");
                        }
                    }
                }
                break;

            case 4: // '\004'
            case 6: // '\006'
                if (byte1 == 1)
                {
                    s = "ACK";
                } else
                {
                    s = BINARY[byte1];
                }
                break;

            case 2: // '\002'
            case 3: // '\003'
            case 7: // '\007'
            case 8: // '\b'
                s = BINARY[byte1];
                break;
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        static 
        {
            boolean flag = false;
            FLAGS = new String[64];
            BINARY = new String[256];
            for (int i = 0; i < BINARY.length; i++)
            {
                BINARY[i] = String.format("%8s", new Object[] {
                    Integer.toBinaryString(i)
                }).replace(' ', '0');
            }

            FLAGS[0] = "";
            FLAGS[1] = "END_STREAM";
            int ai[] = new int[1];
            ai[0] = 1;
            FLAGS[8] = "PADDED";
            for (int j = 0; j <= 0; j++)
            {
                int l = ai[j];
                FLAGS[l | 8] = String.valueOf(FLAGS[l]).concat("|PADDED");
            }

            FLAGS[4] = "END_HEADERS";
            FLAGS[32] = "PRIORITY";
            FLAGS[36] = "END_HEADERS|PRIORITY";
            int k = 0;
            int i1;
            do
            {
                i1 = ((flag) ? 1 : 0);
                if (k >= 3)
                {
                    break;
                }
                int j1 = (new int[] {
                    4, 32, 36
                })[k];
                for (i1 = 0; i1 <= 0; i1++)
                {
                    int k1 = ai[i1];
                    String as[] = FLAGS;
                    String s = FLAGS[k1];
                    String s1 = FLAGS[j1];
                    as[k1 | j1] = (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s1).length())).append(s).append('|').append(s1).toString();
                    as = FLAGS;
                    s = FLAGS[k1];
                    s1 = FLAGS[j1];
                    as[k1 | j1 | 8] = (new StringBuilder(String.valueOf(s).length() + 8 + String.valueOf(s1).length())).append(s).append('|').append(s1).append("|PADDED").toString();
                }

                k++;
            } while (true);
            for (; i1 < FLAGS.length; i1++)
            {
                if (FLAGS[i1] == null)
                {
                    FLAGS[i1] = BINARY[i1];
                }
            }

        }

        FrameLogger()
        {
        }
    }


    public static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    public static final Logger logger = Logger.getLogger(io/grpc/okhttp/internal/framed/Http2$FrameLogger.getName());

    public Http2()
    {
    }

    static transient IllegalArgumentException illegalArgument(String s, Object aobj[])
    {
        throw new IllegalArgumentException(String.format(s, aobj));
    }

    static transient IOException ioException(String s, Object aobj[])
        throws IOException
    {
        throw new IOException(String.format(s, aobj));
    }

    static int lengthWithoutPadding(int i, byte byte0, short word0)
        throws IOException
    {
        int j = i;
        if ((byte0 & 8) != 0)
        {
            j = i - 1;
        }
        if (word0 > j)
        {
            throw new IOException(String.format("PROTOCOL_ERROR padding %s > remaining length %s", new Object[] {
                Short.valueOf(word0), Integer.valueOf(j)
            }));
        } else
        {
            return (short)(j - word0);
        }
    }

    static int readMedium(BufferedSource bufferedsource)
        throws IOException
    {
        return (bufferedsource.readByte() & 0xff) << 16 | (bufferedsource.readByte() & 0xff) << 8 | bufferedsource.readByte() & 0xff;
    }

    static void writeMedium(BufferedSink bufferedsink, int i)
        throws IOException
    {
        bufferedsink.writeByte(i >>> 16 & 0xff);
        bufferedsink.writeByte(i >>> 8 & 0xff);
        bufferedsink.writeByte(i & 0xff);
    }

    public final FrameReader newReader(BufferedSource bufferedsource, boolean flag)
    {
        return new Reader(bufferedsource, 4096, true);
    }

    public final FrameWriter newWriter(BufferedSink bufferedsink, boolean flag)
    {
        return new Writer(bufferedsink, true);
    }


    private class Reader
        implements FrameReader
    {
        private class ContinuationSource
            implements Source
        {

            public byte flags;
            public int left;
            public int length;
            public short padding;
            private final BufferedSource source;
            public int streamId;

            public final void close()
                throws IOException
            {
            }

            public final long read(Buffer buffer, long l)
                throws IOException
            {
                while (left == 0) 
                {
                    source.skip(padding);
                    padding = 0;
                    if ((flags & 4) != 0)
                    {
                        return -1L;
                    }
                    int i = streamId;
                    int j = Http2.readMedium(source);
                    left = j;
                    length = j;
                    byte byte0 = source.readByte();
                    flags = source.readByte();
                    if (Http2.logger.isLoggable(Level.FINE))
                    {
                        Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$ContinuationSource", "readContinuationHeader", FrameLogger.formatHeader(true, streamId, length, byte0, flags));
                    }
                    streamId = source.readInt() & 0x7fffffff;
                    if (byte0 != 9)
                    {
                        throw Http2.ioException("%s != TYPE_CONTINUATION", new Object[] {
                            Byte.valueOf(byte0)
                        });
                    }
                    if (streamId != i)
                    {
                        throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
                    }
                }
                l = source.read(buffer, Math.min(l, left));
                if (l == -1L)
                {
                    return -1L;
                } else
                {
                    left = left - (int)l;
                    return l;
                }
            }

            public ContinuationSource(BufferedSource bufferedsource)
            {
                source = bufferedsource;
            }
        }


        private final ContinuationSource continuation;
        private final Hpack.Reader hpackReader;
        private final BufferedSource source;

        private final List readHeaderBlock(int i, short word0, byte byte0, int j)
            throws IOException
        {
            Object obj = continuation;
            continuation.left = i;
            obj.length = i;
            continuation.padding = word0;
            continuation.flags = byte0;
            continuation.streamId = j;
            for (obj = hpackReader; !((Hpack.Reader) (obj)).source.exhausted();)
            {
                i = ((Hpack.Reader) (obj)).source.readByte() & 0xff;
                if (i == 128)
                {
                    throw new IOException("index == 0");
                }
                if ((i & 0x80) == 128)
                {
                    j = ((Hpack.Reader) (obj)).readInt(i, 127) - 1;
                    if (j >= 0 && j <= Hpack.STATIC_HEADER_TABLE.length - 1)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        Header header = Hpack.STATIC_HEADER_TABLE[j];
                        ((Hpack.Reader) (obj)).headerList.add(header);
                    } else
                    {
                        i = (j - Hpack.STATIC_HEADER_TABLE.length) + (((Hpack.Reader) (obj)).nextDynamicTableIndex + 1);
                        if (i < 0 || i > ((Hpack.Reader) (obj)).dynamicTable.length - 1)
                        {
                            throw new IOException((new StringBuilder(34)).append("Header index too large ").append(j + 1).toString());
                        }
                        ((Hpack.Reader) (obj)).headerList.add(((Hpack.Reader) (obj)).dynamicTable[i]);
                    }
                } else
                if (i == 64)
                {
                    ((Hpack.Reader) (obj)).insertIntoDynamicTable(-1, new Header(Hpack.checkLowercase(((Hpack.Reader) (obj)).readByteString()), ((Hpack.Reader) (obj)).readByteString()));
                } else
                if ((i & 0x40) == 64)
                {
                    ((Hpack.Reader) (obj)).insertIntoDynamicTable(-1, new Header(((Hpack.Reader) (obj)).getName(((Hpack.Reader) (obj)).readInt(i, 63) - 1), ((Hpack.Reader) (obj)).readByteString()));
                } else
                if ((i & 0x20) == 32)
                {
                    obj.maxDynamicTableByteCount = ((Hpack.Reader) (obj)).readInt(i, 31);
                    if (((Hpack.Reader) (obj)).maxDynamicTableByteCount < 0 || ((Hpack.Reader) (obj)).maxDynamicTableByteCount > ((Hpack.Reader) (obj)).headerTableSizeSetting)
                    {
                        i = ((Hpack.Reader) (obj)).maxDynamicTableByteCount;
                        throw new IOException((new StringBuilder(45)).append("Invalid dynamic table size update ").append(i).toString());
                    }
                    ((Hpack.Reader) (obj)).adjustDynamicTableByteCount();
                } else
                if (i == 16 || i == 0)
                {
                    ByteString bytestring = Hpack.checkLowercase(((Hpack.Reader) (obj)).readByteString());
                    ByteString bytestring2 = ((Hpack.Reader) (obj)).readByteString();
                    ((Hpack.Reader) (obj)).headerList.add(new Header(bytestring, bytestring2));
                } else
                {
                    ByteString bytestring1 = ((Hpack.Reader) (obj)).getName(((Hpack.Reader) (obj)).readInt(i, 15) - 1);
                    ByteString bytestring3 = ((Hpack.Reader) (obj)).readByteString();
                    ((Hpack.Reader) (obj)).headerList.add(new Header(bytestring1, bytestring3));
                }
            }

            obj = hpackReader;
            ArrayList arraylist = new ArrayList(((Hpack.Reader) (obj)).headerList);
            ((Hpack.Reader) (obj)).headerList.clear();
            return arraylist;
        }

        public final void close()
            throws IOException
        {
            source.close();
        }

        public final boolean nextFrame(FrameReader.Handler handler)
            throws IOException
        {
            int i;
            boolean flag;
            int i1;
            boolean flag2;
            boolean flag5;
            flag2 = true;
            flag = false;
            flag5 = false;
            i = 0;
            try
            {
                source.require(9L);
                break MISSING_BLOCK_LABEL_24;
            }
            // Misplaced declaration of an exception variable
            catch (FrameReader.Handler handler)
            {
                flag2 = false;
            }
_L23:
            return flag2;
            byte byte0;
            byte byte1;
            int l;
            i1 = Http2.readMedium(source);
            if (i1 < 0 || i1 > 16384)
            {
                throw Http2.ioException("FRAME_SIZE_ERROR: %s", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            byte0 = source.readByte();
            byte1 = source.readByte();
            l = source.readInt() & 0x7fffffff;
            if (Http2.logger.isLoggable(Level.FINE))
            {
                Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Reader", "nextFrame", FrameLogger.formatHeader(true, l, i1, byte0, byte1));
            }
            byte0;
            JVM INSTR tableswitch 0 8: default 192
        //                       0 206
        //                       1 315
        //                       2 442
        //                       3 509
        //                       4 602
        //                       5 1021
        //                       6 1104
        //                       7 1195
        //                       8 1335;
               goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L1:
            source.skip(i1);
            return true;
_L2:
            boolean flag1;
            boolean flag3;
            if ((byte1 & 1) != 0)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            if ((byte1 & 0x20) != 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            }
            if ((byte1 & 8) != 0)
            {
                i = (short)(source.readByte() & 0xff);
            }
            int j = Http2.lengthWithoutPadding(i1, byte1, i);
            handler.data(flag3, l, source, j);
            source.skip(i);
            return true;
_L3:
            if (l == 0)
            {
                throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            }
            short word0;
            int k;
            boolean flag4;
            if ((byte1 & 1) != 0)
            {
                flag4 = true;
            } else
            {
                flag4 = false;
            }
            if ((byte1 & 8) != 0)
            {
                word0 = (short)(source.readByte() & 0xff);
            } else
            {
                word0 = 0;
            }
            Object obj;
            ErrorCode errorcode;
            int j1;
            int k1;
            long l1;
            if ((byte1 & 0x20) != 0)
            {
                source.readInt();
                source.readByte();
                handler.priority$514KIIAQ55B0____0();
                k = i1 - 5;
            } else
            {
                k = i1;
            }
            handler.headers$51D5KIA99HL62TJ15TQN8QBC5T66ISRK7D66IRPFCTP70OPFDTLMGT3KE0NMIRJKCLP6SOBC5TJ74OBDCLI2UI35C5I6ASJJ9LNM8P9R55B0____0$51D5KIA99HL62TJ15TQN8QBC5T66ISRK7D4IILG_0(flag4, l, readHeaderBlock(Http2.lengthWithoutPadding(k, byte1, word0), word0, byte1, l));
            return true;
_L4:
            if (i1 != 5)
            {
                throw Http2.ioException("TYPE_PRIORITY length: %d != 5", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            if (l == 0)
            {
                throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            } else
            {
                source.readInt();
                source.readByte();
                handler.priority$514KIIAQ55B0____0();
                return true;
            }
_L5:
            if (i1 != 4)
            {
                throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            if (l == 0)
            {
                throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            }
            k = source.readInt();
            obj = ErrorCode.fromHttp2(k);
            if (obj == null)
            {
                throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[] {
                    Integer.valueOf(k)
                });
            } else
            {
                handler.rstStream(l, ((ErrorCode) (obj)));
                return true;
            }
_L6:
            if (l != 0)
            {
                throw Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            }
            if ((byte1 & 1) != 0)
            {
                if (i1 != 0)
                {
                    throw Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                } else
                {
                    handler.ackSettings();
                    return true;
                }
            }
            if (i1 % 6 != 0)
            {
                throw Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            obj = new Settings();
            l = 0;
_L19:
            if (l >= i1) goto _L12; else goto _L11
_L11:
            j1 = source.readShort();
            k1 = source.readInt();
            k = j1;
            j1;
            JVM INSTR tableswitch 1 6: default 760
        //                       1 803
        //                       2 770
        //                       3 800
        //                       4 876
        //                       5 896
        //                       6 803;
               goto _L13 _L14 _L15 _L16 _L17 _L18 _L14
_L13:
            l += 6;
              goto _L19
_L15:
            k = j1;
            if (k1 != 0)
            {
                k = j1;
                if (k1 != 1)
                {
                    throw Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                }
            }
              goto _L14
_L16:
            k = 4;
_L14:
            if (k >= ((Settings) (obj)).values.length) goto _L13; else goto _L20
_L20:
            j1 = 1 << k;
            obj.set = ((Settings) (obj)).set | j1;
            obj.persistValue = ((Settings) (obj)).persistValue & ~j1;
            obj.persisted = ~j1 & ((Settings) (obj)).persisted;
            ((Settings) (obj)).values[k] = k1;
              goto _L13
_L17:
            k = 7;
            if (k1 >= 0) goto _L14; else goto _L21
_L21:
            throw Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
_L18:
            if (k1 < 16384)
            {
                break; /* Loop/switch isn't completed */
            }
            k = j1;
            if (k1 <= 0xffffff) goto _L14; else goto _L22
_L22:
            throw Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[] {
                Integer.valueOf(k1)
            });
_L12:
            handler.settings$51D4OQBF5TJN4S335TNMMQ3KEHO2UQBEEHIN4RJ1DGNMCSJ1DLIM8BQJCLQ78QBECTPJMAAM0(((Settings) (obj)));
            if ((((Settings) (obj)).set & 2) != 0)
            {
                k = ((Settings) (obj)).values[1];
            } else
            {
                k = -1;
            }
            if (k >= 0)
            {
                handler = hpackReader;
                if ((((Settings) (obj)).set & 2) != 0)
                {
                    k = ((Settings) (obj)).values[1];
                } else
                {
                    k = -1;
                }
                handler.headerTableSizeSetting = k;
                handler.maxDynamicTableByteCount = k;
                handler.adjustDynamicTableByteCount();
                return true;
            }
              goto _L23
_L7:
            if (l == 0)
            {
                throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            }
            word0 = flag;
            if ((byte1 & 8) != 0)
            {
                word0 = (short)(source.readByte() & 0xff);
            }
            source.readInt();
            readHeaderBlock(Http2.lengthWithoutPadding(i1 - 4, byte1, word0), word0, byte1, l);
            handler.pushPromise$514KIJ3AC5R62BRLEHKMOBQCD5PN8EP9AO______0(l);
            return true;
_L8:
            if (i1 != 8)
            {
                throw Http2.ioException("TYPE_PING length != 8: %s", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            if (l != 0)
            {
                throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
            }
            k = source.readInt();
            l = source.readInt();
            flag4 = flag5;
            if ((byte1 & 1) != 0)
            {
                flag4 = true;
            }
            handler.ping(flag4, k, l);
            return true;
_L9:
            if (i1 < 8)
            {
                throw Http2.ioException("TYPE_GOAWAY length < 8: %s", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            if (l != 0)
            {
                throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            }
            k = source.readInt();
            l = source.readInt();
            i1 -= 8;
            errorcode = ErrorCode.fromHttp2(l);
            if (errorcode == null)
            {
                throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", new Object[] {
                    Integer.valueOf(l)
                });
            }
            obj = ByteString.EMPTY;
            if (i1 > 0)
            {
                obj = source.readByteString(i1);
            }
            handler.goAway(k, errorcode, ((ByteString) (obj)));
            return true;
_L10:
            if (i1 != 4)
            {
                throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", new Object[] {
                    Integer.valueOf(i1)
                });
            }
            l1 = (long)source.readInt() & 0x7fffffffL;
            if (l1 == 0L)
            {
                throw Http2.ioException("windowSizeIncrement was 0", new Object[] {
                    Long.valueOf(l1)
                });
            } else
            {
                handler.windowUpdate(l, l1);
                return true;
            }
        }

        Reader(BufferedSource bufferedsource, int i, boolean flag)
        {
            source = bufferedsource;
            continuation = new ContinuationSource(source);
            hpackReader = new Hpack.Reader(4096, continuation);
        }
    }


    private class Writer
        implements FrameWriter
    {

        private final boolean client;
        private boolean closed;
        private final Buffer hpackBuffer = new Buffer();
        private final Hpack.Writer hpackWriter;
        private int maxFrameSize;
        private final BufferedSink sink;

        private final void frameHeader(int i, int j, byte byte0, byte byte1)
            throws IOException
        {
            if (Http2.logger.isLoggable(Level.FINE))
            {
                Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Writer", "frameHeader", FrameLogger.formatHeader(false, i, j, byte0, byte1));
            }
            if (j > maxFrameSize)
            {
                throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", new Object[] {
                    Integer.valueOf(maxFrameSize), Integer.valueOf(j)
                });
            }
            if ((0x80000000 & i) != 0)
            {
                throw Http2.illegalArgument("reserved bit set: %s", new Object[] {
                    Integer.valueOf(i)
                });
            } else
            {
                Http2.writeMedium(sink, j);
                sink.writeByte(byte0 & 0xff);
                sink.writeByte(byte1 & 0xff);
                sink.writeInt(0x7fffffff & i);
                return;
            }
        }

        public final void ackSettings(Settings settings1)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_24;
            settings1;
            this;
            JVM INSTR monitorexit ;
            throw settings1;
            int i = maxFrameSize;
            if ((settings1.set & 0x20) != 0)
            {
                i = settings1.values[5];
            }
            maxFrameSize = i;
            frameHeader(0, 0, (byte)4, (byte)1);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public final void close()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            closed = true;
            sink.close();
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public final void connectionPreface()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            boolean flag = client;
            if (flag)
            {
                break MISSING_BLOCK_LABEL_36;
            }
_L1:
            this;
            JVM INSTR monitorexit ;
            return;
            if (Http2.logger.isLoggable(Level.FINE))
            {
                Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Writer", "connectionPreface", String.format(">> CONNECTION %s", new Object[] {
                    Http2.CONNECTION_PREFACE.hex()
                }));
            }
            sink.write(Http2.CONNECTION_PREFACE.toByteArray());
            sink.flush();
              goto _L1
        }

        public final void data(boolean flag, int i, Buffer buffer, int j)
            throws IOException
        {
            byte byte0 = 0;
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_27;
            buffer;
            this;
            JVM INSTR monitorexit ;
            throw buffer;
            if (flag)
            {
                byte0 = 1;
            }
            frameHeader(i, j, (byte)0, byte0);
            if (j <= 0)
            {
                break MISSING_BLOCK_LABEL_62;
            }
            sink.write(buffer, j);
            this;
            JVM INSTR monitorexit ;
        }

        public final void flush()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_24;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public final void goAway(int i, ErrorCode errorcode, byte abyte0[])
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_24;
            errorcode;
            this;
            JVM INSTR monitorexit ;
            throw errorcode;
            if (errorcode.httpCode == -1)
            {
                throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            }
            frameHeader(0, abyte0.length + 8, (byte)7, (byte)0);
            sink.writeInt(i);
            sink.writeInt(errorcode.httpCode);
            if (abyte0.length > 0)
            {
                sink.write(abyte0);
            }
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public final int maxDataLength()
        {
            return maxFrameSize;
        }

        public final void ping(boolean flag, int i, int j)
            throws IOException
        {
            byte byte0 = 0;
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_29;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if (flag)
            {
                byte0 = 1;
            }
            frameHeader(0, 8, (byte)6, byte0);
            sink.writeInt(i);
            sink.writeInt(j);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public final void rstStream(int i, ErrorCode errorcode)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_24;
            errorcode;
            this;
            JVM INSTR monitorexit ;
            throw errorcode;
            if (errorcode.httpCode == -1)
            {
                throw new IllegalArgumentException();
            }
            frameHeader(i, 4, (byte)3, (byte)0);
            sink.writeInt(errorcode.httpCode);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        public final void settings(Settings settings1)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_24;
            settings1;
            this;
            JVM INSTR monitorexit ;
            throw settings1;
            frameHeader(0, Integer.bitCount(settings1.set) * 6, (byte)4, (byte)0);
            int i = 0;
_L7:
            if (i >= 10) goto _L2; else goto _L1
_L1:
            int j;
            if ((1 << i & settings1.set) != 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
              goto _L3
_L5:
            sink.writeShort(j);
            sink.writeInt(settings1.values[i]);
              goto _L4
_L2:
            sink.flush();
            this;
            JVM INSTR monitorexit ;
            return;
_L9:
            j = i;
              goto _L5
_L3:
            if (j == 0) goto _L4; else goto _L6
_L6:
            if (i != 4)
            {
                continue; /* Loop/switch isn't completed */
            }
            j = 3;
              goto _L5
_L4:
            i++;
              goto _L7
            if (i != 7) goto _L9; else goto _L8
_L8:
            j = 4;
              goto _L5
        }

        public final void synStream(boolean flag, boolean flag1, int i, int j, List list)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_21;
            }
            throw new UnsupportedOperationException();
            list;
            this;
            JVM INSTR monitorexit ;
            throw list;
            Hpack.Writer writer;
            int k;
            if (closed)
            {
                throw new IOException("closed");
            }
            if (closed)
            {
                throw new IOException("closed");
            }
            writer = hpackWriter;
            k = list.size();
            j = 0;
_L9:
            if (j >= k) goto _L2; else goto _L1
_L1:
            ByteString bytestring;
            Object obj;
            bytestring = ((Header)list.get(j)).name.toAsciiLowercase();
            obj = (Integer)Hpack.NAME_TO_FIRST_INDEX.get(bytestring);
            if (obj == null) goto _L4; else goto _L3
_L3:
            writer.writeInt(((Integer) (obj)).intValue() + 1, 15, 0);
            bytestring = ((Header)list.get(j)).value;
            writer.writeInt(bytestring.size(), 127, 0);
            obj = writer.out;
            if (bytestring != null)
            {
                break MISSING_BLOCK_LABEL_187;
            }
            throw new IllegalArgumentException("byteString == null");
            bytestring.write(((Buffer) (obj)));
              goto _L5
_L4:
            byte abyte0[];
            int l;
            obj = writer.out;
            Segment segment = ((Buffer) (obj)).writableSegment(1);
            abyte0 = segment.data;
            l = segment.limit;
            segment.limit = l + 1;
            abyte0[l] = (byte)0;
            obj.size = ((Buffer) (obj)).size + 1L;
            obj = (Buffer)obj;
            writer.writeInt(bytestring.size(), 127, 0);
            obj = writer.out;
            if (bytestring != null)
            {
                break MISSING_BLOCK_LABEL_296;
            }
            throw new IllegalArgumentException("byteString == null");
            bytestring.write(((Buffer) (obj)));
            bytestring = ((Header)list.get(j)).value;
            writer.writeInt(bytestring.size(), 127, 0);
            obj = writer.out;
            if (bytestring != null)
            {
                break MISSING_BLOCK_LABEL_355;
            }
            throw new IllegalArgumentException("byteString == null");
            bytestring.write(((Buffer) (obj)));
              goto _L5
_L2:
            long l1;
            l1 = hpackBuffer.size;
            j = (int)Math.min(maxFrameSize, l1);
            byte byte0;
            byte byte1;
            if (l1 == (long)j)
            {
                byte0 = 4;
            } else
            {
                byte0 = 0;
            }
            break MISSING_BLOCK_LABEL_537;
_L10:
            frameHeader(i, j, (byte)1, byte1);
            sink.write(hpackBuffer, j);
            if (l1 <= (long)j) goto _L7; else goto _L6
_L6:
            l1 -= j;
_L8:
            if (l1 <= 0L)
            {
                break; /* Loop/switch isn't completed */
            }
            j = (int)Math.min(maxFrameSize, l1);
            l1 -= j;
            if (l1 == 0L)
            {
                byte0 = 4;
            } else
            {
                byte0 = 0;
            }
            frameHeader(i, j, (byte)9, byte0);
            sink.write(hpackBuffer, j);
            if (true) goto _L8; else goto _L7
_L7:
            this;
            JVM INSTR monitorexit ;
            return;
_L5:
            j++;
              goto _L9
            byte1 = byte0;
            if (flag)
            {
                byte1 = (byte)(byte0 | 1);
            }
              goto _L10
        }

        public final void windowUpdate(int i, long l)
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            if (closed)
            {
                throw new IOException("closed");
            }
            break MISSING_BLOCK_LABEL_26;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
            if (l != 0L && l <= 0x7fffffffL)
            {
                break MISSING_BLOCK_LABEL_58;
            }
            throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[] {
                Long.valueOf(l)
            });
            frameHeader(i, 4, (byte)8, (byte)0);
            sink.writeInt((int)l);
            sink.flush();
            this;
            JVM INSTR monitorexit ;
        }

        Writer(BufferedSink bufferedsink, boolean flag)
        {
            sink = bufferedsink;
            client = flag;
            hpackWriter = new Hpack.Writer(hpackBuffer);
            maxFrameSize = 16384;
        }
    }

}
