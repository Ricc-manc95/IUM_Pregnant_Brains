// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            ArrayListMultimapGwtSerializationDependencies, CompactHashMap, CollectPreconditions, AbstractMapBasedMultimap, 
//            Serialization, AbstractMultimap, AbstractListMultimap

public final class ArrayListMultimap extends ArrayListMultimapGwtSerializationDependencies
{

    public static final long serialVersionUID = 0L;
    private transient int expectedValuesPerKey;

    public ArrayListMultimap()
    {
        this(12, 3);
    }

    private ArrayListMultimap(int i, int j)
    {
        super(new CompactHashMap(12));
        CollectPreconditions.checkNonnegative(3, "expectedValuesPerKey");
        expectedValuesPerKey = 3;
    }

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        expectedValuesPerKey = 3;
        int i = objectinputstream.readInt();
        setMap(new CompactHashMap());
        Serialization.populateMultimap(this, objectinputstream, i);
    }

    private final void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        Serialization.writeMultimap(this, objectoutputstream);
    }

    public final volatile Map asMap()
    {
        return super.asMap();
    }

    public final volatile void clear()
    {
        super.clear();
    }

    public final volatile boolean containsEntry(Object obj, Object obj1)
    {
        return super.containsEntry(obj, obj1);
    }

    final volatile Collection createCollection()
    {
        return createCollection();
    }

    final List createCollection()
    {
        return new ArrayList(expectedValuesPerKey);
    }

    public final volatile Collection entries()
    {
        return super.entries();
    }

    public final volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public final volatile List get(Object obj)
    {
        return super.get(obj);
    }

    public final volatile int hashCode()
    {
        return super.hashCode();
    }

    public final volatile Set keySet()
    {
        return super.keySet();
    }

    public final volatile boolean put(Object obj, Object obj1)
    {
        return super.put(obj, obj1);
    }

    public final volatile boolean remove(Object obj, Object obj1)
    {
        return super.remove(obj, obj1);
    }

    public final volatile List removeAll(Object obj)
    {
        return super.removeAll(obj);
    }

    public final volatile int size()
    {
        return super.size();
    }

    public final volatile String toString()
    {
        return super.toString();
    }
}
