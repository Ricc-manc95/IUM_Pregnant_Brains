// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import com.google.common.base.Preconditions;

// Referenced classes of package com.google.common.collect:
//            ImmutableList

final class this._cls0 extends ImmutableList
{

    private final ze this$0;

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, ze);
        return new java.util.y(ternatingKeysAndValues[i * 2 + yOffset], ternatingKeysAndValues[i * 2 + (yOffset ^ 1)]);
    }

    public final boolean isPartialView()
    {
        return true;
    }

    public final int size()
    {
        return ze;
    }

    ()
    {
        this$0 = this._cls0.this;
        super();
    }
}
