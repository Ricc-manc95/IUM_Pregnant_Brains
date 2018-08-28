// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;


// Referenced classes of package com.google.common.collect:
//            ImmutableBiMap, ImmutableSet, RegularImmutableMap, BiMap

final class RegularImmutableBiMap extends ImmutableBiMap
{

    public static final RegularImmutableBiMap EMPTY = new RegularImmutableBiMap();
    private final transient Object alternatingKeysAndValues[];
    private final transient RegularImmutableBiMap inverse;
    private final transient int keyHashTable[];
    private final transient int keyOffset;
    private final transient int size;

    private RegularImmutableBiMap()
    {
        keyHashTable = null;
        alternatingKeysAndValues = new Object[0];
        keyOffset = 0;
        size = 0;
        inverse = this;
    }

    private RegularImmutableBiMap(int ai[], Object aobj[], int i, RegularImmutableBiMap regularimmutablebimap)
    {
        keyHashTable = ai;
        alternatingKeysAndValues = aobj;
        keyOffset = 1;
        size = i;
        inverse = regularimmutablebimap;
    }

    RegularImmutableBiMap(Object aobj[], int i)
    {
        alternatingKeysAndValues = aobj;
        size = i;
        keyOffset = 0;
        int j;
        if (i >= 2)
        {
            j = ImmutableSet.chooseTableSize(i);
        } else
        {
            j = 0;
        }
        keyHashTable = RegularImmutableMap.createHashTable(aobj, i, j, 0);
        inverse = new RegularImmutableBiMap(RegularImmutableMap.createHashTable(aobj, i, j, 1), aobj, i, this);
    }

    final ImmutableSet createEntrySet()
    {
        return new RegularImmutableMap.EntrySet(this, alternatingKeysAndValues, keyOffset, size);
    }

    final ImmutableSet createKeySet()
    {
        return new RegularImmutableMap.KeySet(this, new RegularImmutableMap.KeysOrValuesAsList(alternatingKeysAndValues, keyOffset, size));
    }

    public final Object get(Object obj)
    {
        return RegularImmutableMap.get(keyHashTable, alternatingKeysAndValues, size, keyOffset, obj);
    }

    public final volatile BiMap inverse()
    {
        return inverse();
    }

    public final ImmutableBiMap inverse()
    {
        return inverse;
    }

    final boolean isPartialView()
    {
        return false;
    }

    public final int size()
    {
        return size;
    }

}
