// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.List;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            ListFieldSchema, UnsafeUtil

final class  extends ListFieldSchema
{

    final void makeImmutableListAt(Object obj, long l)
    {
        (()UnsafeUtil.MEMORY_ACCESSOR..getObject(obj, l)).();
    }

    final void mergeListsAt(Object obj, Object obj1, long l)
    {
        Object obj2 = ()UnsafeUtil.MEMORY_ACCESSOR..getObject(obj, l);
          = ()UnsafeUtil.MEMORY_ACCESSOR..getObject(obj1, l);
        int i = (() (obj2)).();
        int j = .();
        obj1 = obj2;
        if (i > 0)
        {
            obj1 = obj2;
            if (j > 0)
            {
                obj1 = obj2;
                if (!(() (obj2)).())
                {
                    obj1 = (() (obj2)).hCapacity(j + i);
                }
                ((hCapacity) (obj1)).hCapacity();
            }
        }
        obj2 = ;
        if (i > 0)
        {
            obj2 = obj1;
        }
        UnsafeUtil.MEMORY_ACCESSOR.hCapacity.putObject(obj, l, obj2);
    }

    final List mutableListAt(Object obj, long l)
    {
        hCapacity hcapacity1 = (hCapacity)UnsafeUtil.MEMORY_ACCESSOR.hCapacity.getObject(obj, l);
        hCapacity hcapacity = hcapacity1;
        if (!hcapacity1.hCapacity())
        {
            int i = hcapacity1.hCapacity();
            if (i == 0)
            {
                i = 10;
            } else
            {
                i <<= 1;
            }
            hcapacity = hcapacity1.hCapacity(i);
            UnsafeUtil.MEMORY_ACCESSOR.hCapacity.putObject(obj, l, hcapacity);
        }
        return hcapacity;
    }

    ()
    {
    }
}
