// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.drive.query.internal:
//            zza, zzs, FilterHolder, zzj, 
//            zzx

public class zzr extends zza
{

    public static final android.os.Parcelable.Creator CREATOR = new zzs();
    public final int mVersionCode;
    public final zzx zzbdb;
    public final List zzbdq;

    zzr(int i, zzx zzx, List list)
    {
        mVersionCode = i;
        zzbdb = zzx;
        zzbdq = list;
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int j = parcel.dataPosition();
        zzc.zza(parcel, 1, zzbdb, i, false);
        zzc.zzc(parcel, 2, zzbdq, false);
        i = mVersionCode;
        zzc.zzb(parcel, 1000, 4);
        parcel.writeInt(i);
        zzc.zzJ(parcel, j);
    }

    public final Object zza(zzj zzj1)
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = zzbdq.iterator(); iterator.hasNext(); arraylist.add(((FilterHolder)iterator.next()).zzaXj.zza(zzj1))) { }
        return zzj1.zzb(zzbdb, arraylist);
    }

}
