// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

// Referenced classes of package com.google.protobuf:
//            AbstractProtobufList, PrimitiveNonBoxingCollection, Internal

public final class LongArrayList extends AbstractProtobufList
    implements Internal.LongList, PrimitiveNonBoxingCollection, RandomAccess
{

    public static final LongArrayList EMPTY_LIST;
    private long array[];
    public int size;

    LongArrayList()
    {
        this(new long[10], 0);
    }

    private LongArrayList(long al[], int i)
    {
        array = al;
        size = i;
    }

    private final String makeOutOfBoundsExceptionMessage(int i)
    {
        int j = size;
        return (new StringBuilder(35)).append("Index:").append(i).append(", Size:").append(j).toString();
    }

    public final void add(int i, Object obj)
    {
        addLong(i, ((Long)obj).longValue());
    }

    public final boolean addAll(Collection collection)
    {
        boolean flag = false;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        Internal.checkNotNull(collection);
        if (!(collection instanceof LongArrayList))
        {
            flag = super.addAll(collection);
        } else
        {
            collection = (LongArrayList)collection;
            if (((LongArrayList) (collection)).size != 0)
            {
                if (0x7fffffff - size < ((LongArrayList) (collection)).size)
                {
                    throw new OutOfMemoryError();
                }
                int i = size + ((LongArrayList) (collection)).size;
                if (i > array.length)
                {
                    array = Arrays.copyOf(array, i);
                }
                System.arraycopy(((LongArrayList) (collection)).array, 0, array, size, ((LongArrayList) (collection)).size);
                size = i;
                modCount = modCount + 1;
                return true;
            }
        }
        return flag;
    }

    final void addLong(int i, long l)
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
            long al[] = new long[(size * 3) / 2 + 1];
            System.arraycopy(array, 0, al, 0, i);
            System.arraycopy(array, i, al, i + 1, size - i);
            array = al;
        }
        array[i] = l;
        size = size + 1;
        modCount = modCount + 1;
    }

    public final void addLong(long l)
    {
        addLong(size, l);
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
        if (!(obj instanceof LongArrayList))
        {
            return super.equals(obj);
        }
        obj = (LongArrayList)obj;
        flag = flag1;
        if (size == ((LongArrayList) (obj)).size)
        {
            obj = ((LongArrayList) (obj)).array;
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
            return Long.valueOf(array[i]);
        }
    }

    public final long getLong(int i)
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
            j = j * 31 + Internal.hashLong(array[i]);
        }

        return j;
    }

    public final Internal.LongList mutableCopyWithCapacity(int i)
    {
        if (i < size)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new LongArrayList(Arrays.copyOf(array, i), size);
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
        long l = array[i];
        if (i < size - 1)
        {
            System.arraycopy(array, i + 1, array, i, size - i);
        }
        size = size - 1;
        modCount = modCount + 1;
        return Long.valueOf(l);
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
                    if (!obj.equals(Long.valueOf(array[i])))
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
        long l = ((Long)obj).longValue();
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        } else
        {
            long l1 = array[i];
            array[i] = l;
            return Long.valueOf(l1);
        }
    }

    public final int size()
    {
        return size;
    }

    static 
    {
        LongArrayList longarraylist = new LongArrayList();
        EMPTY_LIST = longarraylist;
        longarraylist.isMutable = false;
    }
}
