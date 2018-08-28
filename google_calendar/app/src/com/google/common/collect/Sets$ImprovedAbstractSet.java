// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Collection;

// Referenced classes of package com.google.common.collect:
//            Sets

abstract class  extends AbstractSet
{

    public boolean removeAll(Collection collection)
    {
        return Sets.removeAllImpl(this, collection);
    }

    public boolean retainAll(Collection collection)
    {
        if (collection == null)
        {
            throw new NullPointerException();
        } else
        {
            return super.retainAll((Collection)collection);
        }
    }

    ()
    {
    }
}
