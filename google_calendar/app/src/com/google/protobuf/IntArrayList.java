// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

// Referenced classes of package com.google.protobuf:
//            AbstractProtobufList, PrimitiveNonBoxingCollection, Internal

public final class IntArrayList extends AbstractProtobufList
    implements Internal.IntList, PrimitiveNonBoxingCollection, RandomAccess
{

    public static final IntArrayList EMPTY_LIST;
    private int array[];
    public int size;

    IntArrayList()
    {
        this(new int[10], 0);
    }

    private IntArrayList(int ai[], int i)
    {
        array = ai;
        size = i;
    }

    private final String makeOutOfBoundsExceptionMessage(int i)
    {
        int j = size;
        return (new StringBuilder(35)).append("Index:").append(i).append(", Size:").append(j).toString();
    }

    public final void add(int i, Object obj)
    {
        addInt(i, ((Integer)obj).intValue());
    }

    public final boolean addAll(Collection collection)
    {
        boolean flag = false;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        Internal.checkNotNull(collection);
        if (!(collection instanceof IntArrayList))
        {
            flag = super.addAll(collection);
        } else
        {
            collection = (IntArrayList)collection;
            if (((IntArrayList) (collection)).size != 0)
            {
                if (0x7fffffff - size < ((IntArrayList) (collection)).size)
                {
                    throw new OutOfMemoryError();
                }
                int i = size + ((IntArrayList) (collection)).size;
                if (i > array.length)
                {
                    array = Arrays.copyOf(array, i);
                }
                System.arraycopy(((IntArrayList) (collection)).array, 0, array, size, ((IntArrayList) (collection)).size);
                size = i;
                modCount = modCount + 1;
                return true;
            }
        }
        return flag;
    }

    public final void addInt(int i)
    {
        addInt(size, i);
    }

    final void addInt(int i, int j)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (i < 0 || i > size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
        if (size < array.length)
        {
            System.arraycopy(array, i, array, i + 1, size - i);
        } else
        {
            int ai[] = new int[(size * 3) / 2 + 1];
            System.arraycopy(array, 0, ai, 0, i);
            System.arraycopy(array, i, ai, i + 1, size - i);
            array = ai;
        }
        array[i] = j;
        size = size + 1;
        modCount = modCount + 1;
    }

    public final boolean equals(Object obj)
    {
        boolean flag1 = false;
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        if (!(obj instanceof IntArrayList))
        {
            return super.equals(obj);
        }
        obj = (IntArrayList)obj;
        flag = flag1;
        if (size == ((IntArrayList) (obj)).size)
        {
            obj = ((IntArrayList) (obj)).array;
            int i = 0;
label0:
            do
            {
label1:
                {
                    if (i >= size)
                    {
                        break label1;
                    }
                    flag = flag1;
                    if (array[i] != obj[i])
                    {
                        break label0;
                    }
                    i++;
                }
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        return true;
    }

    public final Object get(int i)
    {
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        } else
        {
            return Integer.valueOf(array[i]);
        }
    }

    public final int getInt(int i)
    {
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        } else
        {
            return array[i];
        }
    }

    public final int hashCode()
    {
        int j = 1;
        for (int i = 0; i < size; i++)
        {
            j = j * 31 + array[i];
        }

        return j;
    }

    public final Internal.IntList mutableCopyWithCapacity(int i)
    {
        if (i < size)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new IntArrayList(Arrays.copyOf(array, i), size);
        }
    }

    public final volatile Internal.ProtobufList mutableCopyWithCapacity(int i)
    {
        return mutableCopyWithCapacity(i);
    }

    public final Object remove(int i)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
        int j = array[i];
        if (i < size - 1)
        {
            System.arraycopy(array, i + 1, array, i, size - i);
        }
        size = size - 1;
        modCount = modCount + 1;
        return Integer.valueOf(j);
    }

    public final boolean remove(Object obj)
    {
        boolean flag1 = false;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < size)
                {
                    if (!obj.equals(Integer.valueOf(array[i])))
                    {
                        break label0;
                    }
                    System.arraycopy(array, i + 1, array, i, size - i);
                    size = size - 1;
                    modCount = modCount + 1;
                    flag = true;
                }
                return flag;
            }
            i++;
        } while (true);
    }

    protected final void removeRange(int i, int j)
    {
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (j < i)
        {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        } else
        {
            System.arraycopy(array, j, array, i, size - j);
            size = size - (j - i);
            modCount = modCount + 1;
            return;
        }
    }

    public final Object set(int i, Object obj)
    {
        int j = ((Integer)obj).intValue();
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        } else
        {
            int k = array[i];
            array[i] = j;
            return Integer.valueOf(k);
        }
    }

    public final int size()
    {
        return size;
    }

    static 
    {
        IntArrayList intarraylist = new IntArrayList();
        EMPTY_LIST = intarraylist;
        intarraylist.isMutable = false;
    }
}
