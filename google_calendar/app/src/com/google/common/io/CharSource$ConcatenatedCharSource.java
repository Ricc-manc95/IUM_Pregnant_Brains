// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.io;

import java.io.IOException;
import java.io.Reader;

// Referenced classes of package com.google.common.io:
//            CharSource, MultiReader

public final class sources extends CharSource
{

    private final Iterable sources;

    public final Reader openStream()
        throws IOException
    {
        return new MultiReader(sources.iterator());
    }

    public final String toString()
    {
        String s = String.valueOf(sources);
        return (new StringBuilder(String.valueOf(s).length() + 19)).append("CharSource.concat(").append(s).append(")").toString();
    }

    public (Iterable iterable)
    {
        if (iterable == null)
        {
            throw new NullPointerException();
        } else
        {
            sources = (Iterable)iterable;
            return;
        }
    }
}
