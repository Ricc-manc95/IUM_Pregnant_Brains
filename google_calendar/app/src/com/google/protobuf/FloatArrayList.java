// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

// Referenced classes of package com.google.protobuf:
//            AbstractProtobufList, PrimitiveNonBoxingCollection, Internal

final class FloatArrayList extends AbstractProtobufList
    implements Internal.FloatList, PrimitiveNonBoxingCollection, RandomAccess
{

    private static final FloatArrayList EMPTY_LIST;
    private float array[];
    public int size;

    FloatArrayList()
    {
        this(new float[10], 0);
    }

    private FloatArrayList(float af[], int i)
    {
        array = af;
        size = i;
    }

    private final String makeOutOfBoundsExceptionMessage(int i)
    {
        int j = size;
        return (new StringBuilder(35)).append("Index:").append(i).append(", Size:").append(j).toString();
    }

    public final void add(int i, Object obj)
    {
        addFloat(i, ((Float)obj).floatValue());
    }

    public final boolean addAll(Collection collection)
    {
        boolean flag = false;
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        Internal.checkNotNull(collection);
        if (!(collection instanceof FloatArrayList))
        {
            flag = super.addAll(collection);
        } else
        {
            collection = (FloatArrayList)collection;
            if (((FloatArrayList) (collection)).size != 0)
            {
                if (0x7fffffff - size < ((FloatArrayList) (collection)).size)
                {
                    throw new OutOfMemoryError();
                }
                int i = size + ((FloatArrayList) (collection)).size;
                if (i > array.length)
                {
                    array = Arrays.copyOf(array, i);
                }
                System.arraycopy(((FloatArrayList) (collection)).array, 0, array, size, ((FloatArrayList) (collection)).size);
                size = i;
                modCount = modCount + 1;
                return true;
            }
        }
        return flag;
    }

    final void addFloat(int i, float f)
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
            float af[] = new float[(size * 3) / 2 + 1];
            System.arraycopy(array, 0, af, 0, i);
            System.arraycopy(array, i, af, i + 1, size - i);
            array = af;
        }
        array[i] = f;
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
        if (!(obj instanceof FloatArrayList))
        {
            return super.equals(obj);
        }
        obj = (FloatArrayList)obj;
        flag = flag1;
        if (size == ((FloatArrayList) (obj)).size)
        {
            obj = ((FloatArrayList) (obj)).array;
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
            return Float.valueOf(array[i]);
        }
    }

    public final int hashCode()
    {
        int j = 1;
        for (int i = 0; i < size; i++)
        {
            j = j * 31 + Float.floatToIntBits(array[i]);
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
            return new FloatArrayList(Arrays.copyOf(array, i), size);
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
        float f = array[i];
        if (i < size - 1)
        {
            System.arraycopy(array, i + 1, array, i, size - i);
        }
        size = size - 1;
        modCount = modCount + 1;
        return Float.valueOf(f);
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
                    if (!obj.equals(Float.valueOf(array[i])))
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
        float f = ((Float)obj).floatValue();
        if (!super.isMutable)
        {
            throw new UnsupportedOperationException();
        }
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        } else
        {
            float f1 = array[i];
            array[i] = f;
            return Float.valueOf(f1);
        }
    }

    public final int size()
    {
        return size;
    }

    static 
    {
        FloatArrayList floatarraylist = new FloatArrayList();
        EMPTY_LIST = floatarraylist;
        floatarraylist.isMutable = false;
    }
}
