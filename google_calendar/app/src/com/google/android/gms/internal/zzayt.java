// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

// Referenced classes of package com.google.android.gms.internal:
//            zzayu

public class zzayt extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzayu();
    public final boolean mUseLargePictureForCp2Images;
    public final int mVersionCode;
    public final int zzbTi;
    public final int zzbTj;

    zzayt(int i, int j, int k, boolean flag)
    {
        mVersionCode = i;
        zzbTi = j;
        zzbTj = k;
        mUseLargePictureForCp2Images = flag;
    }

    public static zzayt zza(com.google.android.gms.people.Images.LoadImageOptions loadimageoptions)
    {
        com.google.android.gms.people.Images.LoadImageOptions loadimageoptions1 = loadimageoptions;
        if (loadimageoptions == null)
        {
            loadimageoptions1 = com.google.android.gms.people.Images.LoadImageOptions.DEFAULT;
        }
        return new zzayt(1, loadimageoptions1.imageSize, loadimageoptions1.avatarOptions, loadimageoptions1.useLargePictureForCp2Images);
    }

    public String toString()
    {
        return (new com.google.android.gms.common.internal.zzaa.zza(this)).zzh("imageSize", Integer.valueOf(zzbTi)).zzh("avatarOptions", Integer.valueOf(zzbTj)).zzh("useLargePictureForCp2Images", Boolean.valueOf(mUseLargePictureForCp2Images)).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        i = 1;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        int k = zzbTi;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(k);
        k = zzbTj;
        zzc.zzb(parcel, 2, 4);
        parcel.writeInt(k);
        boolean flag = mUseLargePictureForCp2Images;
        zzc.zzb(parcel, 3, 4);
        if (!flag)
        {
            i = 0;
        }
        parcel.writeInt(i);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

}
