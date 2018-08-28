// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import sun.misc.Unsafe;

// Referenced classes of package com.google.protobuf:
//            ListFieldSchema, UnsafeUtil, LazyStringList, LazyStringArrayList, 
//            PrimitiveNonBoxingCollection, UnmodifiableLazyStringList, AbstractProtobufList

final class  extends ListFieldSchema
{

    private static final Class UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private static List mutableListAt(Object obj, long l, int i)
    {
        List list = (List)UnsafeUtil.MEMORY_ACCESSOR..getObject(obj, l);
        Object obj1;
        if (list.isEmpty())
        {
            if (list instanceof LazyStringList)
            {
                obj1 = new LazyStringArrayList(i);
            } else
            if ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof ))
            {
                obj1 = (()list).hCapacity(i);
            } else
            {
                obj1 = new ArrayList(i);
            }
            UnsafeUtil.MEMORY_ACCESSOR.hCapacity.putObject(obj, l, obj1);
        } else
        {
            if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass()))
            {
                obj1 = new ArrayList(list.size() + i);
                ((ArrayList) (obj1)).addAll(list);
                UnsafeUtil.MEMORY_ACCESSOR.UNMODIFIABLE_LIST_CLASS.putObject(obj, l, obj1);
                return ((List) (obj1));
            }
            if (list instanceof UnmodifiableLazyStringList)
            {
                obj1 = new LazyStringArrayList(list.size() + i);
                ((AbstractProtobufList) (obj1)).addAll((UnmodifiableLazyStringList)list);
                UnsafeUtil.MEMORY_ACCESSOR.UNMODIFIABLE_LIST_CLASS.putObject(obj, l, obj1);
                return ((List) (obj1));
            }
            obj1 = list;
            if (list instanceof PrimitiveNonBoxingCollection)
            {
                obj1 = list;
                if (list instanceof UNMODIFIABLE_LIST_CLASS)
                {
                    obj1 = list;
                    if (!((UNMODIFIABLE_LIST_CLASS)list).UNMODIFIABLE_LIST_CLASS())
                    {
                          = ((UNMODIFIABLE_LIST_CLASS)list).hCapacity(list.size() + i);
                        UnsafeUtil.MEMORY_ACCESSOR.hCapacity.putObject(obj, l, );
                        return ;
                    }
                }
            }
        }
        return ((List) (obj1));
    }

    final void makeImmutableListAt(Object obj, long l)
    {
        Object obj1 = (List)UnsafeUtil.MEMORY_ACCESSOR.hCapacity.getObject(obj, l);
        if (!(obj1 instanceof LazyStringList)) goto _L2; else goto _L1
_L1:
        obj1 = ((LazyStringList)obj1).getUnmodifiableView();
_L6:
        UnsafeUtil.MEMORY_ACCESSOR.hCapacity.putObject(obj, l, obj1);
_L4:
        return;
_L2:
        if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(obj1.getClass()))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!(obj1 instanceof PrimitiveNonBoxingCollection) || !(obj1 instanceof UNMODIFIABLE_LIST_CLASS))
        {
            break; /* Loop/switch isn't completed */
        }
        if (((UNMODIFIABLE_LIST_CLASS)obj1).UNMODIFIABLE_LIST_CLASS())
        {
            ((UNMODIFIABLE_LIST_CLASS)obj1).UNMODIFIABLE_LIST_CLASS();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        obj1 = Collections.unmodifiableList(((List) (obj1)));
        if (true) goto _L6; else goto _L5
_L5:
    }

    final void mergeListsAt(Object obj, Object obj1, long l)
    {
        obj1 = (List)UnsafeUtil.MEMORY_ACCESSOR.UNMODIFIABLE_LIST_CLASS.getObject(obj1, l);
        List list = mutableListAt(obj, l, ((List) (obj1)).size());
        int i = list.size();
        int j = ((List) (obj1)).size();
        if (i > 0 && j > 0)
        {
            list.addAll(((java.util.Collection) (obj1)));
        }
        if (i > 0)
        {
            obj1 = list;
        }
        UnsafeUtil.MEMORY_ACCESSOR.mutableListAt.putObject(obj, l, obj1);
    }

    final List mutableListAt(Object obj, long l)
    {
        return mutableListAt(obj, l, 10);
    }


    ()
    {
    }
}
