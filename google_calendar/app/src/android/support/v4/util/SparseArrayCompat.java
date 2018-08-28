// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;


// Referenced classes of package android.support.v4.util:
//            ContainerHelpers

public final class SparseArrayCompat
    implements Cloneable
{

    public static final Object DELETED = new Object();
    public boolean mGarbage;
    public int mKeys[];
    public int mSize;
    public Object mValues[];

    public SparseArrayCompat()
    {
        this(10);
    }

    public SparseArrayCompat(int i)
    {
        mGarbage = false;
        if (i == 0)
        {
            mKeys = ContainerHelpers.EMPTY_INTS;
            mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else
        {
            i = ContainerHelpers.idealByteArraySize(i << 2) / 4;
            mKeys = new int[i];
            mValues = new Object[i];
        }
        mSize = 0;
    }

    private final SparseArrayCompat clone()
    {
        Object obj;
        try
        {
            obj = (SparseArrayCompat)super.clone();
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            return null;
        }
        try
        {
            obj.mKeys = (int[])mKeys.clone();
            obj.mValues = (Object[])((Object []) (mValues)).clone();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            return ((SparseArrayCompat) (obj));
        }
        return ((SparseArrayCompat) (obj));
    }

    public final void append(int i, Object obj)
    {
        if (mSize != 0 && i <= mKeys[mSize - 1])
        {
            put(i, obj);
            return;
        }
        if (mGarbage && mSize >= mKeys.length)
        {
            gc();
        }
        int j = mSize;
        if (j >= mKeys.length)
        {
            int k = ContainerHelpers.idealByteArraySize(j + 1 << 2) / 4;
            int ai[] = new int[k];
            Object aobj[] = new Object[k];
            System.arraycopy(mKeys, 0, ai, 0, mKeys.length);
            System.arraycopy(((Object) (mValues)), 0, ((Object) (aobj)), 0, mValues.length);
            mKeys = ai;
            mValues = aobj;
        }
        mKeys[j] = i;
        mValues[j] = obj;
        mSize = j + 1;
    }

    public final volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public final void gc()
    {
        int l = mSize;
        int ai[] = mKeys;
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
                    ai[j] = ai[i];
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

    public final Object get(int i)
    {
        i = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if (i < 0 || mValues[i] == DELETED)
        {
            return null;
        } else
        {
            return mValues[i];
        }
    }

    public final void put(int i, Object obj)
    {
        int j = ContainerHelpers.binarySearch(mKeys, mSize, i);
        if (j >= 0)
        {
            mValues[j] = obj;
            return;
        }
        int k = ~j;
        if (k < mSize && mValues[k] == DELETED)
        {
            mKeys[k] = i;
            mValues[k] = obj;
            return;
        }
        j = k;
        if (mGarbage)
        {
            j = k;
            if (mSize >= mKeys.length)
            {
                gc();
                j = ~ContainerHelpers.binarySearch(mKeys, mSize, i);
            }
        }
        if (mSize >= mKeys.length)
        {
            int l = ContainerHelpers.idealByteArraySize(mSize + 1 << 2) / 4;
            int ai[] = new int[l];
            Object aobj[] = new Object[l];
            System.arraycopy(mKeys, 0, ai, 0, mKeys.length);
            System.arraycopy(((Object) (mValues)), 0, ((Object) (aobj)), 0, mValues.length);
            mKeys = ai;
            mValues = aobj;
        }
        if (mSize - j != 0)
        {
            System.arraycopy(mKeys, j, mKeys, j + 1, mSize - j);
            System.arraycopy(((Object) (mValues)), j, ((Object) (mValues)), j + 1, mSize - j);
        }
        mKeys[j] = i;
        mValues[j] = obj;
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
