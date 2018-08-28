// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdm, zzcdi, zzcdj, zzcdd, 
//            zzcdp, zzcdo, zzcdk, zzcde

public abstract class zzcdf extends zzcdm
{

    public zzcdi AC;

    public zzcdf()
    {
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return zzakT();
    }

    protected int computeSerializedSize()
    {
        int j = 0;
        int k;
        if (AC != null)
        {
            int i = 0;
            do
            {
                k = i;
                if (j >= AC.mSize)
                {
                    break;
                }
                i += AC.AH[j].computeSerializedSize();
                j++;
            } while (true);
        } else
        {
            k = 0;
        }
        return k;
    }

    public void writeTo(zzcde zzcde)
        throws IOException
    {
        if (AC != null)
        {
            int i = 0;
            while (i < AC.mSize) 
            {
                AC.AH[i].writeTo(zzcde);
                i++;
            }
        }
    }

    protected final boolean zza(zzcdd zzcdd1, int i)
        throws IOException
    {
        int k = zzcdd1.Av - zzcdd1.As;
        if (!zzcdd1.zzAI(i))
        {
            return false;
        }
        int j = i >>> 3;
        int i1 = zzcdd1.Av - zzcdd1.As - k;
        Object obj;
        zzcdo zzcdo1;
        if (i1 == 0)
        {
            zzcdd1 = zzcdp.AU;
        } else
        {
            obj = new byte[i1];
            int j1 = zzcdd1.As;
            System.arraycopy(zzcdd1.buffer, k + j1, obj, 0, i1);
            zzcdd1 = ((zzcdd) (obj));
        }
        zzcdo1 = new zzcdo(i, zzcdd1);
        if (AC == null)
        {
            AC = new zzcdi();
            zzcdd1 = null;
        } else
        {
            zzcdd1 = AC;
            i = zzcdd1.zzBa(j);
            if (i < 0 || ((zzcdi) (zzcdd1)).AH[i] == zzcdi.AE)
            {
                zzcdd1 = null;
            } else
            {
                zzcdd1 = ((zzcdi) (zzcdd1)).AH[i];
            }
        }
        obj = zzcdd1;
        if (zzcdd1 == null)
        {
            obj = new zzcdj();
            zzcdd1 = AC;
            i = zzcdd1.zzBa(j);
            if (i >= 0)
            {
                ((zzcdi) (zzcdd1)).AH[i] = ((zzcdj) (obj));
            } else
            {
                i = ~i;
                if (i < ((zzcdi) (zzcdd1)).mSize && ((zzcdi) (zzcdd1)).AH[i] == zzcdi.AE)
                {
                    ((zzcdi) (zzcdd1)).AG[i] = j;
                    ((zzcdi) (zzcdd1)).AH[i] = ((zzcdj) (obj));
                } else
                {
                    if (((zzcdi) (zzcdd1)).mSize >= ((zzcdi) (zzcdd1)).AG.length)
                    {
                        int l = zzcdi.idealByteArraySize(((zzcdi) (zzcdd1)).mSize + 1 << 2) / 4;
                        int ai[] = new int[l];
                        zzcdj azzcdj[] = new zzcdj[l];
                        System.arraycopy(((zzcdi) (zzcdd1)).AG, 0, ai, 0, ((zzcdi) (zzcdd1)).AG.length);
                        System.arraycopy(((zzcdi) (zzcdd1)).AH, 0, azzcdj, 0, ((zzcdi) (zzcdd1)).AH.length);
                        zzcdd1.AG = ai;
                        zzcdd1.AH = azzcdj;
                    }
                    if (((zzcdi) (zzcdd1)).mSize - i != 0)
                    {
                        System.arraycopy(((zzcdi) (zzcdd1)).AG, i, ((zzcdi) (zzcdd1)).AG, i + 1, ((zzcdi) (zzcdd1)).mSize - i);
                        System.arraycopy(((zzcdi) (zzcdd1)).AH, i, ((zzcdi) (zzcdd1)).AH, i + 1, ((zzcdi) (zzcdd1)).mSize - i);
                    }
                    ((zzcdi) (zzcdd1)).AG[i] = j;
                    ((zzcdi) (zzcdd1)).AH[i] = ((zzcdj) (obj));
                    zzcdd1.mSize = ((zzcdi) (zzcdd1)).mSize + 1;
                }
            }
        }
        ((zzcdj) (obj)).AJ.add(zzcdo1);
        return true;
    }

    public zzcdf zzakT()
        throws CloneNotSupportedException
    {
        zzcdf zzcdf1 = (zzcdf)super.zzakU();
        zzcdk.zza(this, zzcdf1);
        return zzcdf1;
    }

    public zzcdm zzakU()
        throws CloneNotSupportedException
    {
        return (zzcdf)clone();
    }
}
