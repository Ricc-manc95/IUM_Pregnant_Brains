// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;

import java.io.Writer;

public final class LogWriter extends Writer
{

    private StringBuilder mBuilder;
    private final String mTag;

    public LogWriter(String s)
    {
        mBuilder = new StringBuilder(128);
        mTag = s;
    }

    private final void flushBuilder()
    {
        if (mBuilder.length() > 0)
        {
            StringBuilder stringbuilder = mBuilder;
            mBuilder.delete(0, mBuilder.length());
        }
    }

    public final void close()
    {
        flushBuilder();
    }

    public final void flush()
    {
        flushBuilder();
    }

    public final void write(char ac[], int i, int j)
    {
        int k = 0;
        while (k < j) 
        {
            char c = ac[i + k];
            if (c == '\n')
            {
                flushBuilder();
            } else
            {
                mBuilder.append(c);
            }
            k++;
        }
    }
}
