// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.feedback;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.feedback:
//            zzd

public class LogOptions extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzd();
    public boolean mIncludeRadioLogs;
    public String mLogFilter;
    public final int mVersionCode;

    LogOptions(int i, String s, boolean flag)
    {
        mVersionCode = i;
        mLogFilter = s;
        mIncludeRadioLogs = flag;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        zzc.zza(parcel, 2, mLogFilter, false);
        boolean flag = mIncludeRadioLogs;
        zzc.zzb(parcel, 3, 4);
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
