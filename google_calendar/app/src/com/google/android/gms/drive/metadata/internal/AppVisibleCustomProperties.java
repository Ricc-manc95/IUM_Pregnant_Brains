// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.gms.drive.metadata.internal:
//            zza, zzc

public final class AppVisibleCustomProperties extends com.google.android.gms.common.internal.safeparcel.zza
    implements ReflectedParcelable, Iterable
{
    public static final class zza
    {

        public final Map zzbbI = new HashMap();

        public zza()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new com.google.android.gms.drive.metadata.internal.zza();
    public final int mVersionCode;
    public final List zzbbH;

    AppVisibleCustomProperties(int i, Collection collection)
    {
        mVersionCode = i;
        if (collection == null)
        {
            throw new NullPointerException("null reference");
        } else
        {
            zzbbH = new ArrayList(collection);
            return;
        }
    }

    AppVisibleCustomProperties(Collection collection)
    {
        this(1, collection);
    }

    private final Map asMap()
    {
        HashMap hashmap = new HashMap(zzbbH.size());
        com.google.android.gms.drive.metadata.internal.zzc zzc1;
        for (Iterator iterator1 = zzbbH.iterator(); iterator1.hasNext(); hashmap.put(zzc1.zzbbJ, zzc1.mValue))
        {
            zzc1 = (com.google.android.gms.drive.metadata.internal.zzc)iterator1.next();
        }

        return Collections.unmodifiableMap(hashmap);
    }

    public final boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null || obj.getClass() != getClass())
        {
            return false;
        } else
        {
            return asMap().equals(((AppVisibleCustomProperties)obj).asMap());
        }
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            zzbbH
        });
    }

    public final Iterator iterator()
    {
        return zzbbH.iterator();
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zzc(parcel, 2, zzbbH, false);
        zzc.zzJ(parcel, i);
    }

    static 
    {
        new AppVisibleCustomProperties((new zza()).zzbbI.values());
    }
}
