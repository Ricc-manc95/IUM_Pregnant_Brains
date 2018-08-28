// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdf, zzcde, zzcdi, zzcdd, 
//            zzcdm

public final class zzakp extends zzcdf
{

    public int versionCode;
    public long zzbbv;
    public long zzbbw;
    public long zzbbx;

    public zzakp()
    {
        versionCode = 1;
        zzbbv = -1L;
        zzbbw = -1L;
        zzbbx = -1L;
        AC = null;
        AL = -1;
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = versionCode;
        int k = zzcde.zzAV(8);
        int l;
        int i1;
        int j1;
        int k1;
        long l1;
        if (i >= 0)
        {
            i = zzcde.zzAV(i);
        } else
        {
            i = 10;
        }
        l1 = zzbbv;
        l = zzcde.zzAV(16);
        i1 = zzcde.zzbu(l1 >> 63 ^ l1 << 1);
        l1 = zzbbw;
        j1 = zzcde.zzAV(24);
        k1 = zzcde.zzbu(l1 >> 63 ^ l1 << 1);
        l1 = zzbbx;
        return i + k + j + (l + i1) + (j1 + k1) + (zzcde.zzAV(32) + zzcde.zzbu(l1 >> 63 ^ l1 << 1));
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        boolean flag;
        if (!(obj instanceof zzakp))
        {
            return false;
        }
        obj = (zzakp)obj;
        if (versionCode != ((zzakp) (obj)).versionCode)
        {
            return false;
        }
        if (zzbbv != ((zzakp) (obj)).zzbbv)
        {
            return false;
        }
        if (zzbbw != ((zzakp) (obj)).zzbbw)
        {
            return false;
        }
        if (zzbbx != ((zzakp) (obj)).zzbbx)
        {
            return false;
        }
        if (AC == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (AC.mSize == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        if (((zzakp) (obj)).AC == null) goto _L1; else goto _L3
_L3:
        if (((zzakp) (obj)).AC.mSize == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return false;
        }
        if (true) goto _L1; else goto _L4
_L4:
        return AC.equals(((zzakp) (obj)).AC);
    }

    public final int hashCode()
    {
        boolean flag = false;
        int j = getClass().getName().hashCode();
        int k = versionCode;
        int l = (int)(zzbbv ^ zzbbv >>> 32);
        int i1 = (int)(zzbbw ^ zzbbw >>> 32);
        int j1 = (int)(zzbbx ^ zzbbx >>> 32);
        int i = ((flag) ? 1 : 0);
        if (AC != null)
        {
            if (AC.mSize == 0)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = ((flag) ? 1 : 0);
            } else
            {
                i = AC.hashCode();
            }
        }
        return i + (((((j + 527) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31;
    }

    public final zzcdm mergeFrom(zzcdd zzcdd1)
        throws IOException
    {
        do
        {
            int i = zzcdd1.zzakA();
            switch (i)
            {
            default:
                if (super.zza(zzcdd1, i))
                {
                    continue;
                }
                // fall through

            case 0: // '\0'
                return this;

            case 8: // '\b'
                versionCode = zzcdd1.zzakJ();
                break;

            case 16: // '\020'
                long l = zzcdd1.zzakK();
                zzbbv = -(l & 1L) ^ l >>> 1;
                break;

            case 24: // '\030'
                long l1 = zzcdd1.zzakK();
                zzbbw = -(l1 & 1L) ^ l1 >>> 1;
                break;

            case 32: // ' '
                long l2 = zzcdd1.zzakK();
                zzbbx = -(l2 & 1L) ^ l2 >>> 1;
                break;
            }
        } while (true);
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        int i = versionCode;
        zzcde1.zzAU(8);
        long l;
        if (i >= 0)
        {
            zzcde1.zzAU(i);
        } else
        {
            zzcde1.zzbt(i);
        }
        l = zzbbv;
        zzcde1.zzAU(16);
        zzcde1.zzbt(l >> 63 ^ l << 1);
        l = zzbbw;
        zzcde1.zzAU(24);
        zzcde1.zzbt(l >> 63 ^ l << 1);
        l = zzbbx;
        zzcde1.zzAU(32);
        zzcde1.zzbt(l >> 63 ^ l << 1);
        super.writeTo(zzcde1);
    }
}
