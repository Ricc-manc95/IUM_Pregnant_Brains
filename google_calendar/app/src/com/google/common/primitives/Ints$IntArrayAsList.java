// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class end extends AbstractList
    implements Serializable, RandomAccess
{

    public static final long serialVersionUID = 0L;
    public final int array[];
    public final int end;
    public final int start;

    public final boolean contains(Object obj)
    {
        if (!(obj instanceof Integer)) goto _L2; else goto _L1
_L1:
        int ai[];
        int i;
        int j;
        int k;
        ai = array;
        j = ((Integer)obj).intValue();
        i = start;
        k = end;
_L8:
        if (i >= k) goto _L4; else goto _L3
_L3:
        if (ai[i] != j) goto _L6; else goto _L5
_L5:
        if (i != -1)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
_L6:
        i++;
        continue; /* Loop/switch isn't completed */
_L4:
        i = -1;
        if (true) goto _L5; else goto _L2
_L2:
        return false;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof end)
            {
                obj = (end)obj;
                int j = size();
                if (((size) (obj)).size() != j)
                {
                    return false;
                }
                int i = 0;
                while (i < j) 
                {
                    if (array[start + i] != ((start) (obj)).array[((array) (obj)).start + i])
                    {
                        return false;
                    }
                    i++;
                }
            } else
            {
                return super.equals(obj);
            }
        }
        return true;
    }

    public final Object get(int i)
    {
        Preconditions.checkElementIndex(i, size());
        return Integer.valueOf(array[start + i]);
    }

    public final int hashCode()
    {
        int j = 1;
        for (int i = start; i < end; i++)
        {
            j = j * 31 + array[i];
        }

        return j;
    }

    public final int indexOf(Object obj)
    {
        int j;
        byte byte0;
        byte0 = -1;
        j = byte0;
        if (!(obj instanceof Integer)) goto _L2; else goto _L1
_L1:
        int ai[];
        int i;
        int k;
        ai = array;
        j = ((Integer)obj).intValue();
        i = start;
        k = end;
_L5:
        if (i >= k)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        if (ai[i] != j) goto _L4; else goto _L3
_L3:
        j = byte0;
        if (i >= 0)
        {
            j = i - start;
        }
_L2:
        return j;
_L4:
        i++;
          goto _L5
        i = -1;
          goto _L3
    }

    public final boolean isEmpty()
    {
        return false;
    }

    public final int lastIndexOf(Object obj)
    {
        int j;
        byte byte0;
        byte0 = -1;
        j = byte0;
        if (!(obj instanceof Integer)) goto _L2; else goto _L1
_L1:
        int ai[];
        int i;
        int k;
        ai = array;
        j = ((Integer)obj).intValue();
        k = start;
        i = end - 1;
_L5:
        if (i < k)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        if (ai[i] != j) goto _L4; else goto _L3
_L3:
        j = byte0;
        if (i >= 0)
        {
            j = i - start;
        }
_L2:
        return j;
_L4:
        i--;
          goto _L5
        i = -1;
          goto _L3
    }

    public final Object set(int i, Object obj)
    {
        obj = (Integer)obj;
        Preconditions.checkElementIndex(i, size());
        int j = array[start + i];
        int ai[] = array;
        int k = start;
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            ai[k + i] = ((Integer)obj).intValue();
            return Integer.valueOf(j);
        }
    }

    public final int size()
    {
        return end - start;
    }

    public final List subList(int i, int j)
    {
        Preconditions.checkPositionIndexes(i, j, size());
        if (i == j)
        {
            return Collections.emptyList();
        } else
        {
            return new <init>(array, start + i, start + j);
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(size() * 5);
        stringbuilder.append('[').append(array[start]);
        for (int i = start + 1; i < end; i++)
        {
            stringbuilder.append(", ").append(array[i]);
        }

        return stringbuilder.append(']').toString();
    }

    private (int ai[], int i, int j)
    {
        array = ai;
        start = i;
        end = j;
    }
}
