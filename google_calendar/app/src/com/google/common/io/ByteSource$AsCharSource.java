// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

// Referenced classes of package com.google.common.io:
//            CharSource, ByteSource

final class charset extends CharSource
{

    private final Charset charset;
    private final ByteSource this$0;

    public final Reader openStream()
        throws IOException
    {
        return new InputStreamReader(ByteSource.this.openStream(), charset);
    }

    public final String read()
        throws IOException
    {
        return new String(ByteSource.this.read(), charset);
    }

    public final String toString()
    {
        String s = Object.this.toString();
        String s1 = String.valueOf(charset);
        return (new StringBuilder(String.valueOf(s).length() + 15 + String.valueOf(s1).length())).append(s).append(".asCharSource(").append(s1).append(")").toString();
    }

    (Charset charset1)
    {
        this$0 = ByteSource.this;
        super();
        if (charset1 == null)
        {
            throw new NullPointerException();
        } else
        {
            charset = (Charset)charset1;
            return;
        }
    }
}
