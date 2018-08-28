// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

// Referenced classes of package com.google.protobuf:
//            AbstractProtobufList, PrimitiveNonBoxingCollection, Internal

final class BooleanArrayList extends AbstractProtobufList
    implements Internal.BooleanList, PrimitiveNonBoxingCollection, RandomAccess
{

    private static final BooleanArrayList EMPTY_LIST;
    private boolean array[];
    public int size;

    BooleanArrayList()
    {
        this(new boolean[10], 0);
    }

    private BooleanArrayList(boolean aflag[], int i)
    {
        array = aflag;
        size = i;
    }

    private final String makeOutOfBoundsExceptionMessage(int i)
    {
        int j = size;
        return (new StringBuilder(35)).append("Index:").append(i).append(", Size:").append(j).toString();
    }

    public final void add(int i, Object obj)
    {
        addBoolean(i, ((Boolean)obj).booleanValue());
    }

    public final boolean addAll(Collection collection)
    {
        boolean flag = false;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        Internal.checkNotNull(collection);
        if (!(collection instanceof BooleanArrayList))
        {
            flag = super.addAll(collection);
        } else
        {
            collection = (BooleanArrayList)collection;
            if (((BooleanArrayList) (collection)).size != 0)
            {
                if (0x7fffffff - size < ((BooleanArrayList) (collection)).size)
                {
                    throw new OutOfMemoryError();
                }
                int i = size + ((BooleanArrayList) (collection)).size;
                if (i > array.length)
                {
                    array = Arrays.copyOf(array, i);
                }
                System.arraycopy(((BooleanArrayList) (collection)).array, 0, array, size, ((BooleanArrayList) (collection)).size);
                size = i;
                modCount = modCount + 1;
                return true;
            }
        }
        return flag;
    }

    final void addBoolean(int i, boolean flag)
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
            boolean aflag[] = new boolean[(size * 3) / 2 + 1];
            System.arraycopy(array, 0, aflag, 0, i);
            System.arraycopy(array, i, aflag, i + 1, size - i);
            array = aflag;
        }
        array[i] = flag;
        size = size + 1;
        modCount = modCount + 1;
    }

    public final void addBoolean(boolean flag)
    {
        addBoolean(size, flag);
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
        if (!(obj instanceof BooleanArrayList))
        {
            return super.equals(obj);
        }
        obj = (BooleanArrayList)obj;
        flag = flag1;
        if (size == ((BooleanArrayList) (obj)).size)
        {
            obj = ((BooleanArrayList) (obj)).array;
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
            return Boolean.valueOf(array[i]);
        }
    }

    public final int hashCode()
    {
        int j = 1;
        for (int i = 0; i < size; i++)
        {
            j = j * 31 + Internal.hashBoolean(array[i]);
        }

        return j;
    }

    public final Internal.ProtobufList mutableCopyWithCapacity(int i)
    {
        if (i < size)
        {
            throw new IllegalArgumentException();
        } else
        {
            return new BooleanArrayList(Arrays.copyOf(array, i), size);
        }
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
        boolean flag = array[i];
        if (i < size - 1)
        {
            System.arraycopy(array, i + 1, array, i, size - i);
        }
        size = size - 1;
        modCount = modCount + 1;
        return Boolean.valueOf(flag);
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
                    if (!obj.equals(Boolean.valueOf(array[i])))
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
        boolean flag = ((Boolean)obj).booleanValue();
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        } else
        {
            boolean flag1 = array[i];
            array[i] = flag;
            return Boolean.valueOf(flag1);
        }
    }

    public final int size()
    {
        return size;
    }

    static 
    {
        BooleanArrayList booleanarraylist = new BooleanArrayList();
        EMPTY_LIST = booleanarraylist;
        booleanarraylist.isMutable = false;
    }
}
