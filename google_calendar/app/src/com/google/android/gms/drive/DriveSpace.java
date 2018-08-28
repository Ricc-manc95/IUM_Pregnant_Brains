// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;

// Referenced classes of package com.google.android.gms.drive:
//            zzg

public class DriveSpace extends zza
    implements ReflectedParcelable
{

    private static final DriveSpace APP_DATA_FOLDER;
    public static final android.os.Parcelable.Creator CREATOR = new zzg();
    private static final DriveSpace DRIVE;
    private static final DriveSpace PHOTOS;
    private static final Set zzaWU;
    public final String mName;
    public final int mVersionCode;

    DriveSpace(int i, String s)
    {
        mVersionCode = i;
        if (s == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            mName = (String)s;
            return;
        }
    }

    private DriveSpace(String s)
    {
        this(1, s);
    }

    public boolean equals(Object obj)
    {
        if (obj == null || obj.getClass() != com/google/android/gms/drive/DriveSpace)
        {
            return false;
        } else
        {
            return mName.equals(((DriveSpace)obj).mName);
        }
    }

    public int hashCode()
    {
        return 0x4a54c0de ^ mName.hashCode();
    }

    public String toString()
    {
        return mName;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 2, mName, false);
        zzc.zzJ(parcel, i);
    }

    static 
    {
        DRIVE = new DriveSpace("DRIVE");
        APP_DATA_FOLDER = new DriveSpace("APP_DATA_FOLDER");
        PHOTOS = new DriveSpace("PHOTOS");
        DriveSpace drivespace = DRIVE;
        DriveSpace drivespace1 = APP_DATA_FOLDER;
        DriveSpace drivespace2 = PHOTOS;
        com.google.android.gms.common.util.zza zza1 = new com.google.android.gms.common.util.zza(3);
        zza1.add(drivespace);
        zza1.add(drivespace1);
        zza1.add(drivespace2);
        zzaWU = Collections.unmodifiableSet(zza1);
        TextUtils.join(",", zzaWU.toArray());
        Pattern.compile("[A-Z0-9_]*");
    }
}
