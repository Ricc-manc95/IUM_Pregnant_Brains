// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            FluentIterable

public final class le extends FluentIterable
{

    private final Iterable val$fromIterable;
    private final Function val$function;

    public final Iterator iterator()
    {
        Iterator iterator1 = val$fromIterable.iterator();
        Function function1 = val$function;
        if (function1 == null)
        {
            throw new NullPointerException();
        } else
        {
            return new <init>(iterator1, function1);
        }
    }

    public le()
    {
        val$fromIterable = iterable;
        val$function = function1;
        super();
    }
}
