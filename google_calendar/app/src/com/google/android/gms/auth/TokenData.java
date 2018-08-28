// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.gms.auth:
//            zzl

public class TokenData extends zza
    implements ReflectedParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new zzl();
    public final int mVersionCode;
    public final String zzajP;
    public final Long zzajQ;
    public final boolean zzajR;
    public final boolean zzajS;
    public final List zzajT;

    TokenData(int i, String s, Long long1, boolean flag, boolean flag1, List list)
    {
        mVersionCode = i;
        if (TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("Given String is empty or null");
        } else
        {
            zzajP = s;
            zzajQ = long1;
            zzajR = flag;
            zzajS = flag1;
            zzajT = list;
            return;
        }
    }

    public static TokenData zzd(Bundle bundle, String s)
    {
        bundle.setClassLoader(com/google/android/gms/auth/TokenData.getClassLoader());
        bundle = bundle.getBundle(s);
        if (bundle == null)
        {
            return null;
        } else
        {
            bundle.setClassLoader(com/google/android/gms/auth/TokenData.getClassLoader());
            return (TokenData)bundle.getParcelable("TokenData");
        }
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof TokenData)
        {
            if (TextUtils.equals(zzajP, ((TokenData) (obj = (TokenData)obj)).zzajP))
            {
                Long long1 = zzajQ;
                Long long2 = ((TokenData) (obj)).zzajQ;
                boolean flag;
                if (long1 == long2 || long1 != null && long1.equals(long2))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && zzajR == ((TokenData) (obj)).zzajR && zzajS == ((TokenData) (obj)).zzajS)
                {
                    List list = zzajT;
                    obj = ((TokenData) (obj)).zzajT;
                    if (list == obj || list != null && list.equals(obj))
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
            }
        }
        return false;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzajP, zzajQ, Boolean.valueOf(zzajR), Boolean.valueOf(zzajS), zzajT
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        i = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(i);
        Object obj = zzajP;
        if (obj != null)
        {
            parcel.writeInt(-65534);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeString(((String) (obj)));
            int l = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(l - i);
            parcel.setDataPosition(l);
        }
        obj = zzajQ;
        if (obj != null)
        {
            zzc.zzb(parcel, 3, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        boolean flag1 = zzajR;
        zzc.zzb(parcel, 4, 4);
        if (flag1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        flag1 = zzajS;
        zzc.zzb(parcel, 5, 4);
        if (flag1)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        parcel.writeInt(i);
        obj = zzajT;
        if (obj != null)
        {
            parcel.writeInt(-65530);
            parcel.writeInt(0);
            i = parcel.dataPosition();
            parcel.writeStringList(((List) (obj)));
            int j = parcel.dataPosition();
            parcel.setDataPosition(i - 4);
            parcel.writeInt(j - i);
            parcel.setDataPosition(j);
        }
        i = parcel.dataPosition();
        parcel.setDataPosition(k - 4);
        parcel.writeInt(i - k);
        parcel.setDataPosition(i);
    }

}
