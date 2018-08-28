// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdf, zzcde, zzcdi, zzcdd, 
//            zzcdm

public final class zzakq extends zzcdf
{

    public int versionCode;
    public int zzbbA;
    public long zzbbw;
    public String zzbby;
    public long zzbbz;

    public zzakq()
    {
        versionCode = 1;
        zzbby = "";
        zzbbz = -1L;
        zzbbw = -1L;
        zzbbA = -1;
        AC = null;
        AL = -1;
    }

    protected final int computeSerializedSize()
    {
        byte byte0 = 10;
        int j = super.computeSerializedSize();
        int i = versionCode;
        int k = zzcde.zzAV(8);
        String s;
        int i1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        long l2;
        if (i >= 0)
        {
            i = zzcde.zzAV(i);
        } else
        {
            i = 10;
        }
        s = zzbby;
        i1 = zzcde.zzAV(16);
        k1 = zzcde.zzb(s);
        l1 = zzcde.zzAV(k1);
        l2 = zzbbz;
        i2 = zzcde.zzAV(24);
        j2 = zzcde.zzbu(l2 >> 63 ^ l2 << 1);
        l2 = zzbbw;
        k2 = zzcde.zzAV(32);
        j = i + k + j + (k1 + l1 + i1) + (j2 + i2) + (zzcde.zzbu(l2 >> 63 ^ l2 << 1) + k2);
        i = j;
        if (zzbbA != -1)
        {
            int j1 = zzbbA;
            int l = zzcde.zzAV(40);
            i = byte0;
            if (j1 >= 0)
            {
                i = zzcde.zzAV(j1);
            }
            i = j + (i + l);
        }
        return i;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        boolean flag;
        if (!(obj instanceof zzakq))
        {
            return false;
        }
        obj = (zzakq)obj;
        if (versionCode != ((zzakq) (obj)).versionCode)
        {
            return false;
        }
        if (zzbby == null)
        {
            if (((zzakq) (obj)).zzbby != null)
            {
                return false;
            }
        } else
        if (!zzbby.equals(((zzakq) (obj)).zzbby))
        {
            return false;
        }
        if (zzbbz != ((zzakq) (obj)).zzbbz)
        {
            return false;
        }
        if (zzbbw != ((zzakq) (obj)).zzbbw)
        {
            return false;
        }
        if (zzbbA != ((zzakq) (obj)).zzbbA)
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
        if (((zzakq) (obj)).AC == null) goto _L1; else goto _L3
_L3:
        if (((zzakq) (obj)).AC.mSize == 0)
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
        return AC.equals(((zzakq) (obj)).AC);
    }

    public final int hashCode()
    {
        boolean flag = false;
        int k = getClass().getName().hashCode();
        int l = versionCode;
        int i;
        int j;
        int i1;
        int j1;
        int k1;
        if (zzbby == null)
        {
            i = 0;
        } else
        {
            i = zzbby.hashCode();
        }
        i1 = (int)(zzbbz ^ zzbbz >>> 32);
        j1 = (int)(zzbbw ^ zzbbw >>> 32);
        k1 = zzbbA;
        j = ((flag) ? 1 : 0);
        if (AC != null)
        {
            if (AC.mSize == 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (j != 0)
            {
                j = ((flag) ? 1 : 0);
            } else
            {
                j = AC.hashCode();
            }
        }
        return ((((i + ((k + 527) * 31 + l) * 31) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + j;
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

            case 18: // '\022'
                zzbby = zzcdd1.readString();
                break;

            case 24: // '\030'
                long l = zzcdd1.zzakK();
                zzbbz = -(l & 1L) ^ l >>> 1;
                break;

            case 32: // ' '
                long l1 = zzcdd1.zzakK();
                zzbbw = -(l1 & 1L) ^ l1 >>> 1;
                break;

            case 40: // '('
                zzbbA = zzcdd1.zzakJ();
                break;
            }
        } while (true);
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        int i = versionCode;
        zzcde1.zzAU(8);
        String s;
        long l;
        if (i >= 0)
        {
            zzcde1.zzAU(i);
        } else
        {
            zzcde1.zzbt(i);
        }
        s = zzbby;
        zzcde1.zzAU(18);
        zzcde1.zzmN(s);
        l = zzbbz;
        zzcde1.zzAU(24);
        zzcde1.zzbt(l >> 63 ^ l << 1);
        l = zzbbw;
        zzcde1.zzAU(32);
        zzcde1.zzbt(l >> 63 ^ l << 1);
        if (zzbbA != -1)
        {
            i = zzbbA;
            zzcde1.zzAU(40);
            if (i >= 0)
            {
                zzcde1.zzAU(i);
            } else
            {
                zzcde1.zzbt(i);
            }
        }
        super.writeTo(zzcde1);
    }
}
