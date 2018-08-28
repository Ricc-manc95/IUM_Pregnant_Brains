// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.api.client.util:
//            ArrayMap

final class this._cls0
    implements Iterator
{

    private int nextIndex;
    private boolean removed;
    private final ArrayMap this$0;

    public final boolean hasNext()
    {
        return nextIndex < size;
    }

    public final Object next()
    {
        int i = nextIndex;
        if (i == size)
        {
            throw new NoSuchElementException();
        } else
        {
            nextIndex = nextIndex + 1;
            return new nextIndex(ArrayMap.this, i);
        }
    }

    public final void remove()
    {
        int i = nextIndex - 1;
        if (removed || i < 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            removeFromDataIndexOfKey(i << 1);
            removed = true;
            return;
        }
    }

    ()
    {
        this$0 = ArrayMap.this;
        super();
    }
}
