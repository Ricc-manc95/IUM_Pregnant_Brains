// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

// Referenced classes of package com.google.api.client.util:
//            GenericData, DataMap, ClassInfo

final class  extends AbstractSet
{

    private final  dataEntrySet;
    private final GenericData this$0;

    public final void clear()
    {
        unknownFields.clear();
        dataEntrySet.r();
    }

    public final Iterator iterator()
    {
        return new ator(GenericData.this, dataEntrySet);
    }

    public final int size()
    {
        return unknownFields.size() + dataEntrySet.();
    }

    ator()
    {
        this$0 = GenericData.this;
        super();
        dataEntrySet = (dataEntrySet)(new DataMap(GenericData.this, classInfo.ignoreCase)).entrySet();
    }
}
