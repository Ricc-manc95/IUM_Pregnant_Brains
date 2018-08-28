// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.drive:
//            zzh

public class Permission extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzh();
    public final int mVersionCode;
    public String zzaXl;
    public int zzaXm;
    public String zzaXn;
    public String zzaXo;
    public int zzaXp;
    public boolean zzaXq;

    Permission(int i, String s, int j, String s1, String s2, int k, boolean flag)
    {
        mVersionCode = i;
        zzaXl = s;
        zzaXm = j;
        zzaXn = s1;
        zzaXo = s2;
        zzaXp = k;
        zzaXq = flag;
    }

    public boolean equals(Object obj)
    {
        boolean flag2 = true;
        if (obj != null && obj.getClass() == getClass()) goto _L2; else goto _L1
_L1:
        boolean flag1 = false;
_L4:
        return flag1;
_L2:
        flag1 = flag2;
        if (obj == this) goto _L4; else goto _L3
_L3:
        obj = (Permission)obj;
        String s = zzaXl;
        String s1 = ((Permission) (obj)).zzaXl;
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || zzaXm != ((Permission) (obj)).zzaXm || zzaXp != ((Permission) (obj)).zzaXp)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = flag2;
        if (zzaXq == ((Permission) (obj)).zzaXq) goto _L4; else goto _L5
_L5:
        return false;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzaXl, Integer.valueOf(zzaXm), Integer.valueOf(zzaXp), Boolean.valueOf(zzaXq)
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        byte byte0;
        int j;
        byte0 = -1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        j = parcel.dataPosition();
        i = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(i);
        zzaXm;
        JVM INSTR tableswitch 256 258: default 68
    //                   256 237
    //                   257 237
    //                   258 237;
           goto _L1 _L2 _L2 _L2
_L1:
        i = 0;
_L5:
        String s;
        boolean flag;
        if (i == 0)
        {
            s = null;
        } else
        {
            s = zzaXl;
        }
        zzc.zza(parcel, 2, s, false);
        zzaXm;
        JVM INSTR tableswitch 256 258: default 112
    //                   256 250
    //                   257 250
    //                   258 250;
           goto _L3 _L4 _L4 _L4
_L3:
        i = 0;
_L6:
        if (i == 0)
        {
            i = -1;
        } else
        {
            i = zzaXm;
        }
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(i);
        zzc.zza(parcel, 4, zzaXn, false);
        zzc.zza(parcel, 5, zzaXo, false);
        switch (zzaXp)
        {
        default:
            i = 0;
            break;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            break MISSING_BLOCK_LABEL_263;
        }
_L7:
        if (i == 0)
        {
            i = byte0;
        } else
        {
            i = zzaXp;
        }
        zzc.zzb(parcel, 6, 4);
        parcel.writeInt(i);
        flag = zzaXq;
        zzc.zzb(parcel, 7, 4);
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
        return;
_L2:
        i = 1;
          goto _L5
_L4:
        i = 1;
          goto _L6
        i = 1;
          goto _L7
    }

}
