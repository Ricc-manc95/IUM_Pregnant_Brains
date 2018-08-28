// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;


// Referenced classes of package android.support.v4.util:
//            ContainerHelpers

public final class LongSparseArray
    implements Cloneable
{

    public static final Object DELETED = new Object();
    public boolean mGarbage;
    public long mKeys[];
    public int mSize;
    public Object mValues[];

    public LongSparseArray()
    {
        this(10);
    }

    private LongSparseArray(int i)
    {
        mGarbage = false;
        i = ContainerHelpers.idealByteArraySize(80) / 8;
        mKeys = new long[i];
        mValues = new Object[i];
        mSize = 0;
    }

    private final LongSparseArray clone()
    {
        Object obj;
        try
        {
            obj = (LongSparseArray)super.clone();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return null;
        }
        try
        {
            obj.mKeys = (long[])mKeys.clone();
            obj.mValues = (Object[])((Object []) (mValues)).clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            return ((LongSparseArray) (obj));
        }
        return ((LongSparseArray) (obj));
    }

    public final void clear()
    {
        int j = mSize;
        Object aobj[] = mValues;
        for (int i = 0; i < j; i++)
        {
            aobj[i] = null;
        }

        mSize = 0;
        mGarbage = false;
    }

    public final volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public final void gc()
    {
        int l = mSize;
        long al[] = mKeys;
        Object aobj[] = mValues;
        int i = 0;
        int j;
        int k;
        for (j = 0; i < l; j = k)
        {
            Object obj = aobj[i];
            k = j;
            if (obj != DELETED)
            {
                if (i != j)
                {
                    al[j] = al[i];
                    aobj[j] = obj;
                    aobj[i] = null;
                }
                k = j + 1;
            }
            i++;
        }

        mGarbage = false;
        mSize = j;
    }

    public final Object get(long l)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if (i < 0 || mValues[i] == DELETED)
        {
            return null;
        } else
        {
            return mValues[i];
        }
    }

    public final void put(long l, Object obj)
    {
        int i = ContainerHelpers.binarySearch(mKeys, mSize, l);
        if (i >= 0)
        {
            mValues[i] = obj;
            return;
        }
        int j = ~i;
        if (j < mSize && mValues[j] == DELETED)
        {
            mKeys[j] = l;
            mValues[j] = obj;
            return;
        }
        i = j;
        if (mGarbage)
        {
            i = j;
            if (mSize >= mKeys.length)
            {
                gc();
                i = ~ContainerHelpers.binarySearch(mKeys, mSize, l);
            }
        }
        if (mSize >= mKeys.length)
        {
            int k = ContainerHelpers.idealByteArraySize(mSize + 1 << 3) / 8;
            long al[] = new long[k];
            Object aobj[] = new Object[k];
            System.arraycopy(mKeys, 0, al, 0, mKeys.length);
            System.arraycopy(((Object) (mValues)), 0, ((Object) (aobj)), 0, mValues.length);
            mKeys = al;
            mValues = aobj;
        }
        if (mSize - i != 0)
        {
            System.arraycopy(mKeys, i, mKeys, i + 1, mSize - i);
            System.arraycopy(((Object) (mValues)), i, ((Object) (mValues)), i + 1, mSize - i);
        }
        mKeys[i] = l;
        mValues[i] = obj;
        mSize = mSize + 1;
    }

    public final String toString()
    {
        if (mGarbage)
        {
            gc();
        }
        if (mSize <= 0)
        {
            return "{}";
        }
        StringBuilder stringbuilder = new StringBuilder(mSize * 28);
        stringbuilder.append('{');
        int i = 0;
        while (i < mSize) 
        {
            if (i > 0)
            {
                stringbuilder.append(", ");
            }
            if (mGarbage)
            {
                gc();
            }
            stringbuilder.append(mKeys[i]);
            stringbuilder.append('=');
            if (mGarbage)
            {
                gc();
            }
            Object obj = mValues[i];
            if (obj != this)
            {
                stringbuilder.append(obj);
            } else
            {
                stringbuilder.append("(this Map)");
            }
            i++;
        }
        stringbuilder.append('}');
        return stringbuilder.toString();
    }

}
