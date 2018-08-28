// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.common.collect:
//            AbstractMapBasedMultimap

class delegateIterator
    implements Iterator
{

    public final Iterator delegateIterator;
    public final Collection originalDelegate;
    public final this._cls1 this$1;

    public boolean hasNext()
    {
        delegateIterator.this.delegateIterator();
        if (this._cls1.this.delegateIterator != originalDelegate)
        {
            throw new ConcurrentModificationException();
        } else
        {
            return delegateIterator.hasNext();
        }
    }

    public Object next()
    {
        delegateIterator.this.delegateIterator();
        if (this._cls1.this.delegateIterator != originalDelegate)
        {
            throw new ConcurrentModificationException();
        } else
        {
            return delegateIterator.next();
        }
    }

    public void remove()
    {
        delegateIterator.remove();
        AbstractMapBasedMultimap abstractmapbasedmultimap = this._cls1.this.delegateIterator;
        abstractmapbasedmultimap.totalSize = abstractmapbasedmultimap.totalSize - 1;
        _mth1();
    }

    ()
    {
        this$1 = this._cls1.this;
        super();
        originalDelegate = this._cls1.this.originalDelegate;
         = _fld1;
        if (this._cls1.this instanceof List)
        {
             = ((List)this._cls1.this).listIterator();
        } else
        {
             = iterator();
        }
        delegateIterator = delegateIterator.this;
    }

    delegateIterator(Iterator iterator)
    {
        this$1 = this._cls1.this;
        super();
        originalDelegate = this._cls1.this.originalDelegate;
        delegateIterator = iterator;
    }
}
