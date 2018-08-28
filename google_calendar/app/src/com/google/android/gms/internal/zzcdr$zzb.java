// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.internal:
//            zzcdf, zzcdp, zzcde, zzcdk, 
//            zzcdi, zzcdd, zzcdm

public final class AL extends zzcdf
    implements Cloneable
{

    private byte AZ[];
    private String Ba;
    private byte Bb[][];
    private boolean Bc;

    private final AL zzalk()
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
        if (Bb != null && Bb.length > 0)
        {
            al.Bb = (byte[][])Bb.clone();
        }
        return al;
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return zzalk();
    }

    protected final int computeSerializedSize()
    {
        boolean flag = false;
        int j = super.computeSerializedSize();
        int i = j;
        if (!Arrays.equals(AZ, zzcdp.AU))
        {
            byte abyte0[] = AZ;
            i = zzcde.zzAV(8);
            int k = zzcde.zzAV(abyte0.length);
            i = j + (abyte0.length + k + i);
        }
        j = i;
        if (Bb != null)
        {
            j = i;
            if (Bb.length > 0)
            {
                int l = 0;
                int j1 = 0;
                for (j = ((flag) ? 1 : 0); j < Bb.length;)
                {
                    byte abyte1[] = Bb[j];
                    int l1 = l;
                    int k1 = j1;
                    if (abyte1 != null)
                    {
                        k1 = j1 + 1;
                        j1 = zzcde.zzAV(abyte1.length);
                        l1 = l + (abyte1.length + j1);
                    }
                    j++;
                    l = l1;
                    j1 = k1;
                }

                j = i + l + j1 * 1;
            }
        }
        i = j;
        if (Bc)
        {
            i = j + (zzcde.zzAV(24) + 1);
        }
        j = i;
        if (Ba != null)
        {
            j = i;
            if (!Ba.equals(""))
            {
                String s = Ba;
                j = zzcde.zzAV(32);
                int i1 = zzcde.zzb(s);
                j = i + (i1 + zzcde.zzAV(i1) + j);
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
        if (!Arrays.equals(AZ, ((AZ) (obj)).AZ))
        {
            return false;
        }
        if (Ba == null)
        {
            if (((Ba) (obj)).Ba != null)
            {
                return false;
            }
        } else
        if (!Ba.equals(((Ba) (obj)).Ba))
        {
            return false;
        }
        if (!zzcdk.zza(Bb, ((Bb) (obj)).Bb))
        {
            return false;
        }
        if (Bc != ((Bc) (obj)).Bc)
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
        int k = getClass().getName().hashCode();
        int l = Arrays.hashCode(AZ);
        int i;
        char c;
        int j;
        int i1;
        if (Ba == null)
        {
            i = 0;
        } else
        {
            i = Ba.hashCode();
        }
        i1 = zzcdk.zzd(Bb);
        if (Bc)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
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
        return (c + ((i + ((k + 527) * 31 + l) * 31) * 31 + i1) * 31) * 31 + j;
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
                AZ = zzcdd1.readBytes();
                break;

            case 18: // '\022'
                int k = zzcdp.zzc(zzcdd1, 18);
                byte abyte0[][];
                int j;
                if (Bb == null)
                {
                    j = 0;
                } else
                {
                    j = Bb.length;
                }
                abyte0 = new byte[k + j][];
                k = j;
                if (j != 0)
                {
                    System.arraycopy(Bb, 0, abyte0, 0, j);
                    k = j;
                }
                for (; k < abyte0.length - 1; k++)
                {
                    abyte0[k] = zzcdd1.readBytes();
                    zzcdd1.zzakA();
                }

                abyte0[k] = zzcdd1.readBytes();
                Bb = abyte0;
                break;

            case 24: // '\030'
                boolean flag;
                if (zzcdd1.zzakJ() != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                Bc = flag;
                break;

            case 34: // '"'
                Ba = zzcdd1.readString();
                break;
            }
        } while (true);
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        if (!Arrays.equals(AZ, zzcdp.AU))
        {
            zzcde1.zzb(1, AZ);
        }
        if (Bb != null && Bb.length > 0)
        {
            for (int i = 0; i < Bb.length; i++)
            {
                byte abyte0[] = Bb[i];
                if (abyte0 != null)
                {
                    zzcde1.zzb(2, abyte0);
                }
            }

        }
        if (Bc)
        {
            zzcde1.zzj(3, Bc);
        }
        if (Ba != null && !Ba.equals(""))
        {
            zzcde1.zzs(4, Ba);
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
        AZ = zzcdp.AU;
        Ba = "";
        Bb = zzcdp.AT;
        Bc = false;
        AC = null;
        AL = -1;
    }
}
