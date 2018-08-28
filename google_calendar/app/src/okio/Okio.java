// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package okio;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

// Referenced classes of package okio:
//            RealBufferedSink, RealBufferedSource, Sink, BufferedSink, 
//            Source, BufferedSource

public final class Okio
{

    public static final Logger logger = Logger.getLogger(okio/Okio.getName());

    private Okio()
    {
    }

    public static BufferedSink buffer(Sink sink1)
    {
        return new RealBufferedSink(sink1);
    }

    public static BufferedSource buffer(Source source1)
    {
        return new RealBufferedSource(source1);
    }

    static boolean isAndroidGetsocknameError(AssertionError assertionerror)
    {
        return assertionerror.getCause() != null && assertionerror.getMessage() != null && assertionerror.getMessage().contains("getsockname failed");
    }

    public static Sink sink(final Socket socket)
        throws IOException
    {
        if (socket == null)
        {
            throw new IllegalArgumentException("socket == null");
        }
        final _cls4 timeout = new _cls4();
        socket = socket.getOutputStream();
        if (socket == null)
        {
            throw new IllegalArgumentException("out == null");
        }
        if (timeout == null)
        {
            throw new IllegalArgumentException("timeout == null");
        } else
        {
            return new AsyncTimeout._cls1(timeout, new _cls1());
        }
    }

    public static Source source(final Socket socket)
        throws IOException
    {
        if (socket == null)
        {
            throw new IllegalArgumentException("socket == null");
        }
        final _cls4 timeout = new _cls4();
        socket = socket.getInputStream();
        if (socket == null)
        {
            throw new IllegalArgumentException("in == null");
        }
        if (timeout == null)
        {
            throw new IllegalArgumentException("timeout == null");
        } else
        {
            return new AsyncTimeout._cls2(timeout, new _cls2());
        }
    }


    private class _cls4 extends AsyncTimeout
    {

        private final Socket val$socket;

        protected final IOException newTimeoutException(IOException ioexception)
        {
            SocketTimeoutException sockettimeoutexception = new SocketTimeoutException("timeout");
            if (ioexception != null)
            {
                sockettimeoutexception.initCause(ioexception);
            }
            return sockettimeoutexception;
        }

        protected final void timedOut()
        {
            try
            {
                socket.close();
                return;
            }
            catch (Exception exception)
            {
                Okio.logger.log(Level.WARNING, (new StringBuilder("Failed to close timed out socket ")).append(socket).toString(), exception);
                return;
            }
            catch (AssertionError assertionerror)
            {
                if (Okio.isAndroidGetsocknameError(assertionerror))
                {
                    Okio.logger.log(Level.WARNING, (new StringBuilder("Failed to close timed out socket ")).append(socket).toString(), assertionerror);
                    return;
                } else
                {
                    throw assertionerror;
                }
            }
        }

        _cls4()
        {
            socket = socket1;
            super();
        }
    }


    private class _cls1
        implements Sink
    {

        private final OutputStream val$out;
        private final Timeout val$timeout;

        public final void close()
            throws IOException
        {
            out.close();
        }

        public final void flush()
            throws IOException
        {
            out.flush();
        }

        public final String toString()
        {
            return (new StringBuilder("sink(")).append(out).append(")").toString();
        }

        public final void write(Buffer buffer1, long l)
            throws IOException
        {
            Util.checkOffsetAndCount(buffer1.size, 0L, l);
            do
            {
                if (l <= 0L)
                {
                    break;
                }
                timeout.throwIfReached();
                Segment segment = buffer1.head;
                int i = (int)Math.min(l, segment.limit - segment.pos);
                out.write(segment.data, segment.pos, i);
                segment.pos = segment.pos + i;
                long l1 = l - (long)i;
                buffer1.size = buffer1.size - (long)i;
                l = l1;
                if (segment.pos == segment.limit)
                {
                    buffer1.head = segment.pop();
                    SegmentPool.recycle(segment);
                    l = l1;
                }
            } while (true);
        }

        _cls1()
        {
            timeout = timeout1;
            out = outputstream;
            super();
        }
    }


    private class _cls2
        implements Source
    {

        private final InputStream val$in;
        private final Timeout val$timeout;

        public final void close()
            throws IOException
        {
            in.close();
        }

        public final long read(Buffer buffer1, long l)
            throws IOException
        {
            if (l < 0L)
            {
                throw new IllegalArgumentException((new StringBuilder("byteCount < 0: ")).append(l).toString());
            }
            if (l == 0L)
            {
                return 0L;
            }
            Segment segment;
            int i;
            try
            {
                timeout.throwIfReached();
                segment = buffer1.writableSegment(1);
                i = (int)Math.min(l, 8192 - segment.limit);
                i = in.read(segment.data, segment.limit, i);
            }
            // Misplaced declaration of an exception variable
            catch (Buffer buffer1)
            {
                if (Okio.isAndroidGetsocknameError(buffer1))
                {
                    throw new IOException(buffer1);
                } else
                {
                    throw buffer1;
                }
            }
            if (i == -1)
            {
                return -1L;
            }
            segment.limit = segment.limit + i;
            buffer1.size = buffer1.size + (long)i;
            return (long)i;
        }

        public final String toString()
        {
            return (new StringBuilder("source(")).append(in).append(")").toString();
        }

        _cls2()
        {
            timeout = timeout1;
            in = inputstream;
            super();
        }
    }

}
