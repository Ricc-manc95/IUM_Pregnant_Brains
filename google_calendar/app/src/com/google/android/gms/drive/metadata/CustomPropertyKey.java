// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.gms.drive.metadata:
//            zzc

public class CustomPropertyKey extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.drive.metadata.zzc();
    private static final Pattern zzbbF = Pattern.compile("[\\w.!@$%^&*()/-]+");
    public final int mVersionCode;
    public final int mVisibility;
    public final String zzAZ;

    CustomPropertyKey(int i, String s, int j)
    {
        boolean flag1 = true;
        super();
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("key"));
        }
        if (!zzbbF.matcher(s).matches())
        {
            throw new IllegalArgumentException(String.valueOf("key name characters must be alphanumeric or one of .!@$%^&*()-_/"));
        }
        boolean flag = flag1;
        if (j != 0)
        {
            if (j == 1)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("visibility must be either PUBLIC or PRIVATE"));
        } else
        {
            mVersionCode = i;
            zzAZ = s;
            mVisibility = j;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != null)
        {
            if (obj == this)
            {
                return true;
            }
            if (obj instanceof CustomPropertyKey)
            {
                obj = (CustomPropertyKey)obj;
                if (((CustomPropertyKey) (obj)).zzAZ.equals(zzAZ) && ((CustomPropertyKey) (obj)).mVisibility == mVisibility)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode()
    {
        String s = zzAZ;
        int i = mVisibility;
        return (new StringBuilder(String.valueOf(s).length() + 11)).append(s).append(i).toString().hashCode();
    }

    public String toString()
    {
        String s = zzAZ;
        int i = mVisibility;
        return (new StringBuilder(String.valueOf(s).length() + 31)).append("CustomPropertyKey(").append(s).append(",").append(i).append(")").toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, zzAZ, false);
        j = mVisibility;
        zzc.zzb(parcel, 3, 4);
        parcel.writeInt(j);
        zzc.zzJ(parcel, i);
    }

}
