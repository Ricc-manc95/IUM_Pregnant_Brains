// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.protobuf:
//            AbstractProtobufList

public final class ProtobufArrayList extends AbstractProtobufList
{

    public static final ProtobufArrayList EMPTY_LIST;
    private final List list;

    ProtobufArrayList()
    {
        this(((List) (new ArrayList(10))));
    }

    private ProtobufArrayList(List list1)
    {
        list = list1;
    }

    public final void add(int i, Object obj)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            list.add(i, obj);
            modCount = modCount + 1;
            return;
        }
    }

    public final Object get(int i)
    {
        return list.get(i);
    }

    public final Internal.ProtobufList mutableCopyWithCapacity(int i)
    {
        if (i < size())
        {
            throw new IllegalArgumentException();
        } else
        {
            ArrayList arraylist = new ArrayList(i);
            arraylist.addAll(list);
            return new ProtobufArrayList(arraylist);
        }
    }

    public final Object remove(int i)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            Object obj = list.remove(i);
            modCount = modCount + 1;
            return obj;
        }
    }

    public final Object set(int i, Object obj)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        } else
        {
            obj = list.set(i, obj);
            modCount = modCount + 1;
            return obj;
        }
    }

    public final int size()
    {
        return list.size();
    }

    static 
    {
        ProtobufArrayList protobufarraylist = new ProtobufArrayList();
        EMPTY_LIST = protobufarraylist;
        protobufarraylist.isMutable = false;
    }
}
