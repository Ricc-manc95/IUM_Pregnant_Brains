// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof.collect;


// Referenced classes of package com.google.android.libraries.performance.primes.hprof.collect:
//            IntIntMap

public final class IntObjectMap
{

    public static final Object DELETED = new Object();
    public int keys[];
    public int size;
    private int sizeIndex;
    public Object values[];

    public IntObjectMap()
    {
        init();
    }

    public final int findKeyIndex(int i)
    {
        int j;
        int k;
        boolean flag;
        int j1;
        j1 = keys.length;
        j = IntIntMap.hash(i);
        flag = false;
        j = (j & 0x7fffffff) % j1;
        k = 0;
_L6:
        if (values[j] != DELETED) goto _L2; else goto _L1
_L1:
        boolean flag1;
        int l;
        l = k;
        flag1 = flag;
        if (!flag)
        {
            flag1 = true;
            l = j;
        }
_L4:
        int i1 = j + 1;
        k = l;
        flag = flag1;
        j = i1;
        if (i1 >= j1)
        {
            j = 0;
            k = l;
            flag = flag1;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (values[j] == null)
        {
            if (flag)
            {
                j = k;
            }
            return j;
        }
        l = k;
        flag1 = flag;
        if (keys[j] != i) goto _L4; else goto _L3
_L3:
        return j;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void init()
    {
        sizeIndex = 0;
        keys = new int[IntIntMap.TABLE_SIZES[sizeIndex]];
        values = new Object[IntIntMap.TABLE_SIZES[sizeIndex]];
    }

    public final Object putIfAbsent(int i, Object obj)
    {
label0:
        {
label1:
            {
                boolean flag = false;
                if (obj == null)
                {
                    throw new NullPointerException();
                }
                int j = findKeyIndex(i);
                if (values[j] == null || values[j] == DELETED)
                {
                    keys[j] = i;
                    size = size + 1;
                    values[j] = obj;
                    if (size <= keys.length / 2)
                    {
                        break label0;
                    }
                    if (sizeIndex >= IntIntMap.TABLE_SIZES.length - 1)
                    {
                        break label1;
                    }
                    obj = keys;
                    Object aobj[] = values;
                    sizeIndex = sizeIndex + 1;
                    keys = new int[IntIntMap.TABLE_SIZES[sizeIndex]];
                    values = new Object[IntIntMap.TABLE_SIZES[sizeIndex]];
                    j = size;
                    int k = obj.length;
                    size = 0;
                    for (i = 0; i < k; i++)
                    {
                        if (aobj[i] != null && aobj[i] != DELETED)
                        {
                            putIfAbsent(obj[i], aobj[i]);
                        }
                    }

                } else
                {
                    return values[j];
                }
                i = ((flag) ? 1 : 0);
                if (j == size)
                {
                    i = 1;
                }
                if (i == 0)
                {
                    throw new IllegalStateException();
                }
                break label0;
            }
            throw new IllegalStateException("Too many items, you'd better use array map instead.");
        }
        return null;
    }

    public final Object remove(int i)
    {
        i = findKeyIndex(i);
        if (values[i] != null && values[i] != DELETED)
        {
            Object obj = values[i];
            values[i] = DELETED;
            size = size - 1;
            return obj;
        } else
        {
            return null;
        }
    }

}
