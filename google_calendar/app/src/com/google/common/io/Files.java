// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.File;
import java.nio.charset.Charset;

// Referenced classes of package com.google.common.io:
//            CharSource

public final class Files
{

    public static CharSource asCharSource(File file, Charset charset)
    {
        return new ByteSource.AsCharSource(new FileByteSource(file), charset);
    }

    static 
    {
        new _cls2();
        new _cls3();
    }

    private class FileByteSource extends ByteSource
    {

        private final File file;

        public final InputStream openStream()
            throws IOException
        {
            return new FileInputStream(file);
        }

        public final byte[] read()
            throws IOException
        {
            Closer closer = new Closer(Closer.SUPPRESSOR);
            FileInputStream fileinputstream = (FileInputStream)openStream();
            if (fileinputstream == null)
            {
                break MISSING_BLOCK_LABEL_33;
            }
            closer.stack.addFirst(fileinputstream);
            byte abyte0[];
            fileinputstream = (FileInputStream)fileinputstream;
            abyte0 = ByteStreams.toByteArray(fileinputstream, fileinputstream.getChannel().size());
            closer.close();
            return abyte0;
            Object obj;
            obj;
            throw closer.rethrow(((Throwable) (obj)));
            obj;
            closer.close();
            throw obj;
        }

        public final Optional sizeIfKnown()
        {
            if (file.isFile())
            {
                Long long1 = Long.valueOf(file.length());
                if (long1 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    return new Present(long1);
                }
            } else
            {
                return Absent.INSTANCE;
            }
        }

        public final String toString()
        {
            String s = String.valueOf(file);
            return (new StringBuilder(String.valueOf(s).length() + 20)).append("Files.asByteSource(").append(s).append(")").toString();
        }

        FileByteSource(File file1)
        {
            if (file1 == null)
            {
                throw new NullPointerException();
            } else
            {
                file = (File)file1;
                return;
            }
        }
    }


    private class _cls2 extends TreeTraverser
    {

        public final String toString()
        {
            return "Files.fileTreeTraverser()";
        }

        _cls2()
        {
        }
    }


    private class _cls3
    {

        _cls3()
        {
        }
    }

}
