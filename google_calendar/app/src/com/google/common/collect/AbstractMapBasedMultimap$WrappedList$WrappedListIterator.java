// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap

final class r extends r
    implements ListIterator
{

    private final getDelegateListIterator this$1;

    private final ListIterator getDelegateListIterator()
    {
        ();
        if ( != super.originalDelegate)
        {
            throw new ConcurrentModificationException();
        } else
        {
            return (ListIterator)super.delegateIterator;
        }
    }

    public final void add(Object obj)
    {
        boolean flag = delegateIterator();
        getDelegateListIterator().add(obj);
        obj = this._cls1.this.getDelegateListIterator;
        obj.totalSize = ((AbstractMapBasedMultimap) (obj)).totalSize + 1;
        if (flag)
        {
            _mth1();
        }
    }

    public final boolean hasPrevious()
    {
        return getDelegateListIterator().hasPrevious();
    }

    public final int nextIndex()
    {
        return getDelegateListIterator().nextIndex();
    }

    public final Object previous()
    {
        return getDelegateListIterator().previous();
    }

    public final int previousIndex()
    {
        return getDelegateListIterator().previousIndex();
    }

    public final void set(Object obj)
    {
        getDelegateListIterator().set(obj);
    }

    r()
    {
        this$1 = this._cls1.this;
        super(r.this);
    }

    public r(int i)
    {
        this$1 = this._cls1.this;
        super(r.this, ((List)r.this.r).listIterator(i));
    }
}
