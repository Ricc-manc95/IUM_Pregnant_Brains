// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;


// Referenced classes of package com.google.android.gms.internal:
//            zzcdj

public final class zzcdi
    implements Cloneable
{

    public static final zzcdj AE = new zzcdj();
    public int AG[];
    public zzcdj AH[];
    public int mSize;

    zzcdi()
    {
        this(10);
    }

    private zzcdi(int i)
    {
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
                AG = new int[i];
                AH = new zzcdj[i];
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

    public final Object clone()
        throws CloneNotSupportedException
    {
        int j = mSize;
        zzcdi zzcdi1 = new zzcdi(j);
        System.arraycopy(AG, 0, zzcdi1.AG, 0, j);
        for (int i = 0; i < j; i++)
        {
            if (AH[i] != null)
            {
                zzcdi1.AH[i] = (zzcdj)AH[i].clone();
            }
        }

        zzcdi1.mSize = j;
        return zzcdi1;
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
        if (!(obj instanceof zzcdi))
        {
            return false;
        }
        obj = (zzcdi)obj;
        if (mSize != ((zzcdi) (obj)).mSize)
        {
            return false;
        }
        aobj = AG;
        ai = ((zzcdi) (obj)).AG;
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
        aobj = AH;
        obj = ((zzcdi) (obj)).AH;
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
            j = (j * 31 + AG[i]) * 31 + AH[i].hashCode();
        }

        return j;
    }

    final int zzBa(int i)
    {
        int k;
label0:
        {
            k = mSize;
            int j = 0;
            for (k--; j <= k;)
            {
                int l = j + k >>> 1;
                int i1 = AG[l];
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

}
