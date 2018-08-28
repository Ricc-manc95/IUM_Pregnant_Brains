// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

// Referenced classes of package com.google.common.collect:
//            TransformedIterator

public final class terator extends TransformedIterator
{

    private final Function val$function;

    final Object transform(Object obj)
    {
        return val$function.apply(obj);
    }

    public terator(Function function1)
    {
        val$function = function1;
        super(final_iterator);
    }
}
