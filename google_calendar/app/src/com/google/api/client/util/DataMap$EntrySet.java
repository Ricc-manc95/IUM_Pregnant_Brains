// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.api.client.util:
//            DataMap, ClassInfo, FieldInfo

final class this._cls0 extends AbstractSet
{

    private final DataMap this$0;

    public final void clear()
    {
        Object obj;
        Object obj1;
        for (Iterator iterator1 = classInfo.names.iterator(); iterator1.hasNext(); FieldInfo.setFieldValue(((FieldInfo) (obj)).field, obj1, null))
        {
            obj = (String)iterator1.next();
            obj = classInfo.getFieldInfo(((String) (obj)));
            obj1 = object;
        }

    }

    public final boolean isEmpty()
    {
        for (Iterator iterator1 = classInfo.names.iterator(); iterator1.hasNext();)
        {
            Object obj = (String)iterator1.next();
            obj = classInfo.getFieldInfo(((String) (obj)));
            Object obj1 = object;
            if (FieldInfo.getFieldValue(((FieldInfo) (obj)).field, obj1) != null)
            {
                return false;
            }
        }

        return true;
    }

    public final Iterator iterator()
    {
        return new ator(DataMap.this);
    }

    public final int size()
    {
        Iterator iterator1 = classInfo.names.iterator();
        int i = 0;
        do
        {
            if (!iterator1.hasNext())
            {
                break;
            }
            Object obj = (String)iterator1.next();
            obj = classInfo.getFieldInfo(((String) (obj)));
            Object obj1 = object;
            if (FieldInfo.getFieldValue(((FieldInfo) (obj)).field, obj1) != null)
            {
                i++;
            }
        } while (true);
        return i;
    }

    ator()
    {
        this$0 = DataMap.this;
        super();
    }
}
