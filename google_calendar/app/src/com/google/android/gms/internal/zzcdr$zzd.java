// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdf, zzcdk, zzcde, zzcdi, 
//            zzcdd, zzcdm

public final class AL extends zzcdf
    implements Cloneable
{

    private static volatile e Bv[];
    private String value;
    private String zzaz;

    public static AL[] zzaln()
    {
        if (Bv == null)
        {
            synchronized (zzcdk.AK)
            {
                if (Bv == null)
                {
                    Bv = new Bv[0];
                }
            }
        }
        return Bv;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final Bv zzalp()
    {
        Bv bv;
        try
        {
            bv = (Bv)super.zzakT();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError(clonenotsupportedexception);
        }
        return bv;
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return zzalp();
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (zzaz != null)
        {
            i = j;
            if (!zzaz.equals(""))
            {
                String s = zzaz;
                i = zzcde.zzAV(8);
                int k = zzcde.zzb(s);
                i = j + (k + zzcde.zzAV(k) + i);
            }
        }
        j = i;
        if (value != null)
        {
            j = i;
            if (!value.equals(""))
            {
                String s1 = value;
                j = zzcde.zzAV(16);
                int l = zzcde.zzb(s1);
                j = i + (l + zzcde.zzAV(l) + j);
            }
        }
        return j;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        boolean flag;
        if (!(obj instanceof ))
        {
            return false;
        }
        obj = ()obj;
        if (zzaz == null)
        {
            if (((zzaz) (obj)).zzaz != null)
            {
                return false;
            }
        } else
        if (!zzaz.equals(((zzaz) (obj)).zzaz))
        {
            return false;
        }
        if (value == null)
        {
            if (((value) (obj)).value != null)
            {
                return false;
            }
        } else
        if (!value.equals(((value) (obj)).value))
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
        if (((e) (obj)).AC == null) goto _L1; else goto _L3
_L3:
        if (((AC) (obj)).AC.mSize == 0)
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
        return AC.equals(((ls) (obj)).AC);
    }

    public final int hashCode()
    {
        boolean flag = false;
        int l = getClass().getName().hashCode();
        int i;
        int j;
        int k;
        if (zzaz == null)
        {
            i = 0;
        } else
        {
            i = zzaz.hashCode();
        }
        if (value == null)
        {
            j = 0;
        } else
        {
            j = value.hashCode();
        }
        k = ((flag) ? 1 : 0);
        if (AC != null)
        {
            if (AC.mSize == 0)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if (k != 0)
            {
                k = ((flag) ? 1 : 0);
            } else
            {
                k = AC.hashCode();
            }
        }
        return (j + (i + (l + 527) * 31) * 31) * 31 + k;
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

            case 10: // '\n'
                zzaz = zzcdd1.readString();
                break;

            case 18: // '\022'
                value = zzcdd1.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        if (zzaz != null && !zzaz.equals(""))
        {
            zzcde1.zzs(1, zzaz);
        }
        if (value != null && !value.equals(""))
        {
            zzcde1.zzs(2, value);
        }
        super.writeTo(zzcde1);
    }

    public final zzcdf zzakT()
        throws CloneNotSupportedException
    {
        return (eTo)(Object)zzakU();
    }

    public final zzcdm zzakU()
        throws CloneNotSupportedException
    {
        return (U)clone();
    }

    public ()
    {
        zzaz = "";
        value = "";
        AC = null;
        AL = -1;
    }
}
