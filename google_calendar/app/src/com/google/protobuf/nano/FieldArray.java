// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf.nano;


// Referenced classes of package com.google.protobuf.nano:
//            FieldData

public final class FieldArray
    implements Cloneable
{

    public static final FieldData DELETED = new FieldData();
    public FieldData mData[];
    public int mFieldNumbers[];
    private boolean mGarbage;
    public int mSize;

    FieldArray()
    {
        this(10);
    }

    private FieldArray(int i)
    {
        mGarbage = false;
        int k = i << 2;
        i = 4;
        do
        {
label0:
            {
                int j = k;
                if (i < 32)
                {
                    if (k > (1 << i) - 12)
                    {
                        break label0;
                    }
                    j = (1 << i) - 12;
                }
                i = j / 4;
                mFieldNumbers = new int[i];
                mData = new FieldData[i];
                mSize = 0;
                return;
            }
            i++;
        } while (true);
    }

    static int idealByteArraySize(int i)
    {
        int j = 4;
        do
        {
label0:
            {
                int k = i;
                if (j < 32)
                {
                    if (i > (1 << j) - 12)
                    {
                        break label0;
                    }
                    k = (1 << j) - 12;
                }
                return k;
            }
            j++;
        } while (true);
    }

    final int binarySearch(int i)
    {
        int k;
label0:
        {
            k = mSize;
            int j = 0;
            for (k--; j <= k;)
            {
                int l = j + k >>> 1;
                int i1 = mFieldNumbers[l];
                if (i1 < i)
                {
                    j = l + 1;
                } else
                {
                    k = l;
                    if (i1 <= i)
                    {
                        break label0;
                    }
                    k = l - 1;
                }
            }

            k = ~j;
        }
        return k;
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        int j = mSize;
        FieldArray fieldarray = new FieldArray(j);
        System.arraycopy(mFieldNumbers, 0, fieldarray.mFieldNumbers, 0, j);
        for (int i = 0; i < j; i++)
        {
            if (mData[i] != null)
            {
                fieldarray.mData[i] = (FieldData)mData[i].clone();
            }
        }

        fieldarray.mSize = j;
        return fieldarray;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        Object aobj[];
        int ai[];
        int i;
        int j;
        if (!(obj instanceof FieldArray))
        {
            return false;
        }
        obj = (FieldArray)obj;
        if (mSize != ((FieldArray) (obj)).mSize)
        {
            return false;
        }
        aobj = mFieldNumbers;
        ai = ((FieldArray) (obj)).mFieldNumbers;
        j = mSize;
        i = 0;
_L10:
        if (i >= j) goto _L4; else goto _L3
_L3:
        if (aobj[i] == ai[i]) goto _L6; else goto _L5
_L5:
        i = 0;
_L11:
        if (i == 0)
        {
            break; /* Loop/switch isn't completed */
        }
        aobj = mData;
        obj = ((FieldArray) (obj)).mData;
        j = mSize;
        i = 0;
_L12:
        if (i >= j)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        if (aobj[i].equals(obj[i])) goto _L8; else goto _L7
_L7:
        i = 0;
_L13:
        if (i != 0) goto _L1; else goto _L9
_L9:
        return false;
_L6:
        i++;
          goto _L10
_L4:
        i = 1;
          goto _L11
_L8:
        i++;
          goto _L12
        i = 1;
          goto _L13
    }

    public final int hashCode()
    {
        int j = 17;
        for (int i = 0; i < mSize; i++)
        {
            j = (j * 31 + mFieldNumbers[i]) * 31 + mData[i].hashCode();
        }

        return j;
    }

}
