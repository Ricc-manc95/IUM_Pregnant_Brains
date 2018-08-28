// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            AbstractIndexedListIterator

final class Iterator extends AbstractIndexedListIterator
{

    private final l.inputs this$0;

    public final Object get(int i)
    {
        return inputs[i].iterator();
    }

    Iterator(int i)
    {
        this$0 = this._cls0.this;
        super(i);
    }
}
