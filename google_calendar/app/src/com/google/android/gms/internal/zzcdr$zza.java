// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdf, zzcde, zzcdi, zzcdd, 
//            zzcdm

public final class AL extends zzcdf
    implements Cloneable
{

    private int AX;
    private String AY;
    private String version;

    private final AL zzali()
    {
        AL al;
        try
        {
            al = (AL)super.zzakT();
        }
        catch (CloneNotSupportedException clonenotsupportedexception)
        {
            throw new AssertionError(clonenotsupportedexception);
        }
        return al;
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return zzali();
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (AX != 0)
        {
            i = j + zzcde.zzaa(1, AX);
        }
        j = i;
        if (AY != null)
        {
            j = i;
            if (!AY.equals(""))
            {
                String s = AY;
                j = zzcde.zzAV(16);
                int k = zzcde.zzb(s);
                j = i + (k + zzcde.zzAV(k) + j);
            }
        }
        i = j;
        if (version != null)
        {
            i = j;
            if (!version.equals(""))
            {
                String s1 = version;
                i = zzcde.zzAV(24);
                int l = zzcde.zzb(s1);
                i = j + (l + zzcde.zzAV(l) + i);
            }
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
        if (!(obj instanceof ))
        {
            return false;
        }
        obj = ()obj;
        if (AX != ((AX) (obj)).AX)
        {
            return false;
        }
        if (AY == null)
        {
            if (((AY) (obj)).AY != null)
            {
                return false;
            }
        } else
        if (!AY.equals(((AY) (obj)).AY))
        {
            return false;
        }
        if (version == null)
        {
            if (((version) (obj)).version != null)
            {
                return false;
            }
        } else
        if (!version.equals(((version) (obj)).version))
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
        int i1 = AX;
        int i;
        int j;
        int k;
        if (AY == null)
        {
            i = 0;
        } else
        {
            i = AY.hashCode();
        }
        if (version == null)
        {
            j = 0;
        } else
        {
            j = version.hashCode();
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
        return (j + (i + ((l + 527) * 31 + i1) * 31) * 31) * 31 + k;
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
                AX = zzcdd1.zzakJ();
                break;

            case 18: // '\022'
                AY = zzcdd1.readString();
                break;

            case 26: // '\032'
                version = zzcdd1.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        if (AX != 0)
        {
            zzcde1.zzY(1, AX);
        }
        if (AY != null && !AY.equals(""))
        {
            zzcde1.zzs(2, AY);
        }
        if (version != null && !version.equals(""))
        {
            zzcde1.zzs(3, version);
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
        AX = 0;
        AY = "";
        version = "";
        AC = null;
        AL = -1;
    }
}
