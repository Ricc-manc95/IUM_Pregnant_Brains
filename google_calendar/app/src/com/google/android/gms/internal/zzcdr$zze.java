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

    private int Bw;
    private int networkType;

    private final AL zzalr()
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
        return zzalr();
    }

    protected final int computeSerializedSize()
    {
        int j = super.computeSerializedSize();
        int i = j;
        if (networkType != -1)
        {
            i = j + zzcde.zzaa(1, networkType);
        }
        j = i;
        if (Bw != 0)
        {
            j = i + zzcde.zzaa(2, Bw);
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
        if (!(obj instanceof Bw))
        {
            return false;
        }
        obj = (Bw)obj;
        if (networkType != ((networkType) (obj)).networkType)
        {
            return false;
        }
        if (Bw != ((Bw) (obj)).Bw)
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
        int j = getClass().getName().hashCode();
        int k = networkType;
        int l = Bw;
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
        return i + (((j + 527) * 31 + k) * 31 + l) * 31;
    }

    public final zzcdm mergeFrom(zzcdd zzcdd1)
        throws IOException
    {
_L5:
        int i = zzcdd1.zzakA();
        i;
        JVM INSTR lookupswitch 3: default 40
    //                   0: 49
    //                   8: 51
    //                   16: 159;
           goto _L1 _L2 _L3 _L4
_L1:
        if (super.zza(zzcdd1, i)) goto _L5; else goto _L2
_L2:
        return this;
_L3:
        int j = zzcdd1.zzakJ();
        switch (j)
        {
        case -1: 
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
            networkType = j;
            break;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        int k = zzcdd1.zzakJ();
        switch (k)
        {
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 100: // 'd'
            Bw = k;
            break;
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    public final void writeTo(zzcde zzcde1)
        throws IOException
    {
        if (networkType != -1)
        {
            zzcde1.zzY(1, networkType);
        }
        if (Bw != 0)
        {
            zzcde1.zzY(2, Bw);
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
        networkType = -1;
        Bw = 0;
        AC = null;
        AL = -1;
    }
}
