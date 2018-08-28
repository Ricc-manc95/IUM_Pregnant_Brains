// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

// Referenced classes of package com.google.api.client.util:
//            DataMap, ClassInfo, FieldInfo

final class nextKeyIndex
    implements Iterator
{

    private FieldInfo currentFieldInfo;
    private boolean isComputed;
    private boolean isRemoved;
    private FieldInfo nextFieldInfo;
    private Object nextFieldValue;
    private int nextKeyIndex;
    private final DataMap this$0;

    public final boolean hasNext()
    {
        if (!isComputed)
        {
            isComputed = true;
            nextFieldValue = null;
            do
            {
                if (nextFieldValue != null)
                {
                    break;
                }
                int i = nextKeyIndex + 1;
                nextKeyIndex = i;
                if (i >= classInfo.names.size())
                {
                    break;
                }
                nextFieldInfo = classInfo.getFieldInfo((String)classInfo.names.get(nextKeyIndex));
                FieldInfo fieldinfo = nextFieldInfo;
                Object obj = object;
                nextFieldValue = FieldInfo.getFieldValue(fieldinfo.field, obj);
            } while (true);
        }
        return nextFieldValue != null;
    }

    public final Object next()
    {
        if (!hasNext())
        {
            throw new NoSuchElementException();
        } else
        {
            currentFieldInfo = nextFieldInfo;
            Object obj = nextFieldValue;
            isComputed = false;
            isRemoved = false;
            nextFieldInfo = null;
            nextFieldValue = null;
            return new nextFieldValue(DataMap.this, currentFieldInfo, obj);
        }
    }

    public final void remove()
    {
        boolean flag;
        if (currentFieldInfo != null && !isRemoved)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        } else
        {
            isRemoved = true;
            FieldInfo fieldinfo = currentFieldInfo;
            Object obj = object;
            FieldInfo.setFieldValue(fieldinfo.field, obj, null);
            return;
        }
    }

    ()
    {
        this$0 = DataMap.this;
        super();
        nextKeyIndex = -1;
    }
}
