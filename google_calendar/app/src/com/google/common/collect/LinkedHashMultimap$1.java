// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.common.collect:
//            LinkedHashMultimap, AbstractMapEntry, AbstractMultimap

final class lueEntry.successorInMultimap
    implements Iterator
{

    private lueEntry nextEntry;
    private final LinkedHashMultimap this$0;
    private lueEntry toRemove;

    public final boolean hasNext()
    {
        return nextEntry != multimapHeaderEntry;
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            lueEntry lueentry = nextEntry;
            toRemove = lueentry;
            nextEntry = nextEntry.successorInMultimap;
            return lueentry;
        }
    }

    public final void remove()
    {
        boolean flag;
        if (toRemove != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
        } else
        {
            AbstractMultimap.this.remove(toRemove.getKey(), toRemove.getValue());
            toRemove = null;
            return;
        }
    }

    lueEntry()
    {
        this$0 = LinkedHashMultimap.this;
        super();
        nextEntry = multimapHeaderEntry.successorInMultimap;
    }
}
