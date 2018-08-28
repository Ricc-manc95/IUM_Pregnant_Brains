// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.api.client.util:
//            ArrayMap, ClassInfo, Data, FieldInfo, 
//            DataMap

public class GenericData extends AbstractMap
    implements Cloneable
{
    final class EntryIterator
        implements Iterator
    {

        private final Iterator fieldIterator;
        private boolean startedUnknown;
        private final Iterator unknownIterator;

        public final boolean hasNext()
        {
            return fieldIterator.hasNext() || unknownIterator.hasNext();
        }

        public final Object next()
        {
            if (!startedUnknown)
            {
                if (fieldIterator.hasNext())
                {
                    return (java.util.Map.Entry)fieldIterator.next();
                }
                startedUnknown = true;
            }
            return (java.util.Map.Entry)unknownIterator.next();
        }

        public final void remove()
        {
            if (startedUnknown)
            {
                unknownIterator.remove();
            }
            fieldIterator.remove();
        }

        EntryIterator(DataMap.EntrySet entryset)
        {
            fieldIterator = (DataMap.EntryIterator)entryset.iterator();
            unknownIterator = unknownFields.entrySet().iterator();
        }
    }

    final class EntrySet extends AbstractSet
    {

        private final DataMap.EntrySet dataEntrySet;
        private final GenericData this$0;

        public final void clear()
        {
            unknownFields.clear();
            dataEntrySet.clear();
        }

        public final Iterator iterator()
        {
            return new EntryIterator(dataEntrySet);
        }

        public final int size()
        {
            return unknownFields.size() + dataEntrySet.size();
        }

        EntrySet()
        {
            this$0 = GenericData.this;
            super();
            dataEntrySet = (DataMap.EntrySet)(new DataMap(GenericData.this, classInfo.ignoreCase)).entrySet();
        }
    }

    public static final class Flags extends Enum
    {

        private static final Flags $VALUES[];
        public static final Flags IGNORE_CASE;

        public static Flags[] values()
        {
            return (Flags[])$VALUES.clone();
        }

        static 
        {
            IGNORE_CASE = new Flags("IGNORE_CASE", 0);
            $VALUES = (new Flags[] {
                IGNORE_CASE
            });
        }

        private Flags(String s, int i)
        {
            super(s, 0);
        }
    }


    public final ClassInfo classInfo;
    public Map unknownFields;

    public GenericData()
    {
        this(EnumSet.noneOf(com/google/api/client/util/GenericData$Flags));
    }

    public GenericData(EnumSet enumset)
    {
        unknownFields = new ArrayMap();
        classInfo = ClassInfo.of(getClass(), enumset.contains(Flags.IGNORE_CASE));
    }

    public GenericData clone()
    {
        GenericData genericdata;
        try
        {
            genericdata = (GenericData)super.clone();
            Data.deepCopy(this, genericdata);
            genericdata.unknownFields = (Map)Data.clone(unknownFields);
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new IllegalStateException(clonenotsupportedexception);
        }
        return genericdata;
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public Set entrySet()
    {
        return new EntrySet();
    }

    public final Object get(Object obj)
    {
        if (!(obj instanceof String))
        {
            return null;
        }
        String s = (String)obj;
        obj = classInfo.getFieldInfo(s);
        if (obj != null)
        {
            return FieldInfo.getFieldValue(((FieldInfo) (obj)).field, this);
        }
        obj = s;
        if (classInfo.ignoreCase)
        {
            obj = s.toLowerCase();
        }
        return unknownFields.get(obj);
    }

    public volatile Object put(Object obj, Object obj1)
    {
        return put((String)obj, obj1);
    }

    public final Object put(String s, Object obj)
    {
        Object obj1 = classInfo.getFieldInfo(s);
        if (obj1 != null)
        {
            s = ((String) (FieldInfo.getFieldValue(((FieldInfo) (obj1)).field, this)));
            FieldInfo.setFieldValue(((FieldInfo) (obj1)).field, this, obj);
            return s;
        }
        obj1 = s;
        if (classInfo.ignoreCase)
        {
            obj1 = s.toLowerCase();
        }
        return unknownFields.put(obj1, obj);
    }

    public final void putAll(Map map)
    {
        java.util.Map.Entry entry;
        for (map = map.entrySet().iterator(); map.hasNext(); set((String)entry.getKey(), entry.getValue()))
        {
            entry = (java.util.Map.Entry)map.next();
        }

    }

    public final Object remove(Object obj)
    {
        if (!(obj instanceof String))
        {
            return null;
        }
        String s = (String)obj;
        if (classInfo.getFieldInfo(s) != null)
        {
            throw new UnsupportedOperationException();
        }
        obj = s;
        if (classInfo.ignoreCase)
        {
            obj = s.toLowerCase();
        }
        return unknownFields.remove(obj);
    }

    public GenericData set(String s, Object obj)
    {
        Object obj1 = classInfo.getFieldInfo(s);
        if (obj1 != null)
        {
            FieldInfo.setFieldValue(((FieldInfo) (obj1)).field, this, obj);
            return this;
        }
        obj1 = s;
        if (classInfo.ignoreCase)
        {
            obj1 = s.toLowerCase();
        }
        unknownFields.put(obj1, obj);
        return this;
    }
}
