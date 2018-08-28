// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof.collect;

import java.util.Arrays;

public final class IntIntMap
{

    public static final int TABLE_SIZES[] = {
        5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 
        6421, 12853, 25717, 51437, 0x191dd, 0x323bf, 0x64787, 0xc8f4d, 0x191e9d, 0x323d49, 
        0x647a97, 0xc8f539, 0x191ea81, 0x323d521, 0x647aa43, 0xc8f5489, 0x191ea927, 0x323d525b, 0x647aa4bf, 0x7fffffed
    };
    public final int emptyValue;
    public int keys[];
    private int size;
    private int sizeIndex;
    public int values[];

    public IntIntMap()
    {
        this(-1);
    }

    private IntIntMap(int i)
    {
        emptyValue = -1;
        init();
    }

    static int hash(int i)
    {
        return (i << 1) - (i << 8);
    }

    public final int findKeyIndex(int i)
    {
        int l = keys.length;
        int j = ((i << 1) - (i << 8) & 0x7fffffff) % l;
        do
        {
            int k;
            do
            {
                if (values[j] == emptyValue || keys[j] == i)
                {
                    return j;
                }
                k = j + 1;
                j = k;
            } while (k < l);
            j = 0;
        } while (true);
    }

    public final void init()
    {
        sizeIndex = 0;
        keys = new int[TABLE_SIZES[sizeIndex]];
        values = new int[TABLE_SIZES[sizeIndex]];
        Arrays.fill(values, emptyValue);
    }

    public final int putIfAbsent(int i, int j)
    {
label0:
        {
label1:
            {
                boolean flag1 = true;
                boolean flag;
                if (j != emptyValue)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException(String.valueOf("Cannot add emptyValue to map"));
                }
                int k = findKeyIndex(i);
                if (values[k] == emptyValue)
                {
                    keys[k] = i;
                    size = size + 1;
                    values[k] = j;
                    if (size <= keys.length / 2)
                    {
                        break label0;
                    }
                    if (sizeIndex >= TABLE_SIZES.length - 1)
                    {
                        break label1;
                    }
                    int ai[] = keys;
                    int ai1[] = values;
                    sizeIndex = sizeIndex + 1;
                    keys = new int[TABLE_SIZES[sizeIndex]];
                    values = new int[TABLE_SIZES[sizeIndex]];
                    Arrays.fill(values, emptyValue);
                    j = size;
                    k = ai.length;
                    size = 0;
                    for (i = 0; i < k; i++)
                    {
                        if (ai1[i] != emptyValue)
                        {
                            putIfAbsent(ai[i], ai1[i]);
                        }
                    }

                } else
                {
                    return values[k];
                }
                if (j == size)
                {
                    i = ((flag1) ? 1 : 0);
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalStateException();
                }
                break label0;
            }
            throw new IllegalStateException("Too many items, you'd better use array map instead.");
        }
        return emptyValue;
    }

}
