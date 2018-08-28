// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.fitness.data:
//            zzb

public final class Application extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzb();
    public static final Application GOOGLE_PLAY_SERVICES = new Application("com.google.android.gms", null);
    public final String packageName;
    public final String version;
    public final int versionCode;
    public final String zzbhs;

    Application(int i, String s, String s1)
    {
        versionCode = i;
        if (s == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            packageName = (String)s;
            version = "";
            zzbhs = s1;
            return;
        }
    }

    private Application(String s, String s1)
    {
        this(1, s, s1);
    }

    public static Application fromPackage(String s)
    {
        if ("com.google.android.gms".equals(s))
        {
            return GOOGLE_PLAY_SERVICES;
        } else
        {
            return new Application(s, null);
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag2 = false;
        if (this == obj) goto _L2; else goto _L1
_L1:
        boolean flag1 = flag2;
        if (!(obj instanceof Application)) goto _L4; else goto _L3
_L3:
        obj = (Application)obj;
        if (!packageName.equals(((Application) (obj)).packageName)) goto _L6; else goto _L5
_L5:
        boolean flag;
        String s = version;
        String s1 = ((Application) (obj)).version;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L7
_L7:
        s = zzbhs;
        obj = ((Application) (obj)).zzbhs;
        if (s == obj || s != null && s.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L6; else goto _L8
_L8:
        flag = true;
_L10:
        flag1 = flag2;
        if (!flag) goto _L4; else goto _L2
_L2:
        flag1 = true;
_L4:
        return flag1;
_L6:
        flag = false;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            packageName, version, zzbhs
        });
    }

    public final String toString()
    {
        return String.format("Application{%s:%s:%s}", new Object[] {
            packageName, version, zzbhs
        });
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        zzc.zza(parcel, 1, packageName, false);
        zzc.zza(parcel, 2, version, false);
        zzc.zza(parcel, 3, zzbhs, false);
        int j = versionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
