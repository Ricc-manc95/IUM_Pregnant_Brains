// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            CompactHashMap

final class r extends r
{

    private final CompactHashMap this$0;

    final Object getOutput(int i)
    {
        return new pEntry(CompactHashMap.this, i);
    }

    pEntry()
    {
        this$0 = CompactHashMap.this;
        super(CompactHashMap.this, (byte)0);
    }
}
