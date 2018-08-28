// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.util.Collection;

// Referenced classes of package com.google.common.collect:
//            ImmutableMap, BiMap, ImmutableSet, ImmutableCollection

public abstract class ImmutableBiMap extends ImmutableMap
    implements BiMap
{

    ImmutableBiMap()
    {
    }

    final ImmutableCollection createValues()
    {
        throw new AssertionError("should never be called");
    }

    public volatile BiMap inverse()
    {
        return inverse();
    }

    public abstract ImmutableBiMap inverse();

    public final volatile ImmutableCollection values()
    {
        return (ImmutableSet)values();
    }

    public Collection values()
    {
        return (ImmutableSet)((ImmutableBiMap)inverse()).keySet();
    }

    Object writeReplace()
    {
        return new SerializedForm();
    }

    private class SerializedForm extends ImmutableMap.SerializedForm
    {

        public static final long serialVersionUID = 0L;

        final Object readResolve()
        {
            return createMap(new Builder());
        }

        SerializedForm()
        {
        }

        private class Builder extends ImmutableMap.Builder
        {

            public final ImmutableMap build()
            {
                if (size == 0)
                {
                    return RegularImmutableBiMap.EMPTY;
                } else
                {
                    entriesUsed = true;
                    return new RegularImmutableBiMap(alternatingKeysAndValues, size);
                }
            }

            public final ImmutableMap.Builder put(Object obj, Object obj1)
            {
                super.put(obj, obj1);
                return this;
            }

            public final ImmutableMap.Builder put(java.util.Map.Entry entry)
            {
                super.put(entry);
                return this;
            }

            public final ImmutableMap.Builder putAll(Iterable iterable)
            {
                super.putAll(iterable);
                return this;
            }

            public Builder()
            {
            }
        }

    }

}
