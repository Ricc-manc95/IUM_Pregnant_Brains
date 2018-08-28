// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            ExternalApplicationLink, zzm

public class zzn extends zza
    implements ExternalApplicationLink
{

    public static final android.os.Parcelable.Creator CREATOR = new zzm();
    public final int mVersionCode;
    public final String zzGm;
    public final Integer zzcie;

    zzn(int i, Integer integer, String s)
    {
        zzcie = integer;
        zzGm = s;
        mVersionCode = i;
    }

    public zzn(ExternalApplicationLink externalapplicationlink)
    {
        this(externalapplicationlink.getApplication(), externalapplicationlink.getId());
    }

    private zzn(Integer integer, String s)
    {
        this(1, integer, s);
    }

    public static int zza(ExternalApplicationLink externalapplicationlink)
    {
        return Arrays.hashCode(new Object[] {
            externalapplicationlink.getApplication(), externalapplicationlink.getId()
        });
    }

    public static boolean zza(ExternalApplicationLink externalapplicationlink, ExternalApplicationLink externalapplicationlink1)
    {
        Integer integer = externalapplicationlink.getApplication();
        Integer integer1 = externalapplicationlink1.getApplication();
        boolean flag;
        if (integer == integer1 || integer != null && integer.equals(integer1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            externalapplicationlink = externalapplicationlink.getId();
            externalapplicationlink1 = externalapplicationlink1.getId();
            if (externalapplicationlink == externalapplicationlink1 || externalapplicationlink != null && externalapplicationlink.equals(externalapplicationlink1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ExternalApplicationLink))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (ExternalApplicationLink)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final Integer getApplication()
    {
        return zzcie;
    }

    public final String getId()
    {
        return zzGm;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getApplication(), getId()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        Integer integer = zzcie;
        if (integer != null)
        {
            zzc.zzb(parcel, 2, 4);
            parcel.writeInt(integer.intValue());
        }
        zzc.zza(parcel, 3, zzGm, false);
        zzc.zzJ(parcel, i);
    }

}
