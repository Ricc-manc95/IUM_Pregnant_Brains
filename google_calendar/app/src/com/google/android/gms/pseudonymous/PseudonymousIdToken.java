// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.pseudonymous;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.pseudonymous:
//            zza

public class PseudonymousIdToken extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.pseudonymous.zza();
    public String mValue;
    public final int mVersionCode;

    PseudonymousIdToken(int i, String s)
    {
        mVersionCode = i;
        mValue = s;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof PseudonymousIdToken)
        {
            obj = (PseudonymousIdToken)obj;
            String s = mValue;
            String s1 = ((PseudonymousIdToken) (obj)).mValue;
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return flag && mVersionCode == ((PseudonymousIdToken) (obj)).mVersionCode;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            mValue, Integer.valueOf(mVersionCode)
        });
    }

    public String toString()
    {
        int i = mVersionCode;
        String s = mValue;
        return (new StringBuilder(String.valueOf(s).length() + 34)).append("PseudonymousIdToken(").append(i).append(")[").append(s).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, mValue, false);
        zzc.zzJ(parcel, i);
    }

}
