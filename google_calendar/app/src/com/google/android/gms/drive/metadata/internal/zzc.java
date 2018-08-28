// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            zzd

public class zzc extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public final String mValue;
    public final int mVersionCode;
    public final CustomPropertyKey zzbbJ;

    zzc(int i, CustomPropertyKey custompropertykey, String s)
    {
        mVersionCode = i;
        if (custompropertykey == null)
        {
            throw new NullPointerException(String.valueOf("key"));
        } else
        {
            zzbbJ = custompropertykey;
            mValue = s;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (obj == null || obj.getClass() != getClass())
        {
            return false;
        }
        obj = (zzc)obj;
        Object obj1 = zzbbJ;
        CustomPropertyKey custompropertykey = ((zzc) (obj)).zzbbJ;
        boolean flag;
        if (obj1 == custompropertykey || obj1 != null && obj1.equals(custompropertykey))
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
        obj1 = mValue;
        obj = ((zzc) (obj)).mValue;
        if (obj1 == obj || obj1 != null && obj1.equals(obj))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L1; else goto _L3
_L3:
        return false;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzbbJ, mValue
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, zzbbJ, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 3, mValue, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, j);
    }

}
