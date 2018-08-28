// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FoldingWriter extends FilterWriter
{

    private static final char FOLD_PATTERN[] = {
        '\r', '\n', ' '
    };
    private final int foldLength;
    private int lineLength;
    private final Log log = LogFactory.getLog(net/fortuna/ical4j/data/FoldingWriter);

    public FoldingWriter(Writer writer, int i)
    {
        super(writer);
        foldLength = Math.min(i, 75);
    }

    public final void write(int i)
        throws IOException
    {
        write(new char[] {
            (char)i
        }, 0, 1);
    }

    public final void write(String s, int i, int j)
        throws IOException
    {
        write(s.toCharArray(), i, j);
    }

    public final void write(char ac[], int i, int j)
        throws IOException
    {
        int k = i;
        while (k <= (i + j) - 1) 
        {
            if (log.isTraceEnabled())
            {
                Log log1 = log;
                char c = ac[k];
                int l = lineLength;
                log1.trace((new StringBuilder(35)).append("char [").append(c).append("], line length [").append(l).append("]").toString());
            }
            if (lineLength >= foldLength)
            {
                super.write(FOLD_PATTERN, 0, FOLD_PATTERN.length);
                lineLength = 1;
            }
            super.write(ac[k]);
            if (ac[k] == '\r' || ac[k] == '\n')
            {
                lineLength = 0;
            } else
            {
                lineLength = lineLength + 1;
            }
            k++;
        }
    }

}
