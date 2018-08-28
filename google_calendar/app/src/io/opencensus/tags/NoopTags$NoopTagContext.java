// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.tags;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

// Referenced classes of package io.opencensus.tags:
//            TagContext

final class  extends TagContext
{

    public static final TagContext INSTANCE = new <init>();

    protected final Iterator getIterator()
    {
        return Collections.emptySet().iterator();
    }


    private ()
    {
    }
}
