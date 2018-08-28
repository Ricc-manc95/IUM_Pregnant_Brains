// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.CategoryInfo;
import com.google.android.gms.reminders.model.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzb extends zzo
    implements CategoryInfo
{

    public zzb(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public static boolean zza(DataHolder dataholder, int i, int j, String s)
    {
        String s1 = zzaw(s, "category_id");
        dataholder.zzj(s1, i);
        if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            String s2 = zzaw(s, "place_types");
            dataholder.zzj(s2, i);
            if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s2)))
            {
                s = zzaw(s, "display_name");
                dataholder.zzj(s, i);
                if (dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof CategoryInfo))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzd.zza(this, (CategoryInfo)obj);
        }
    }

    public final Object freeze()
    {
        return new zzd(this);
    }

    public final String getCategoryId()
    {
        return getString(zziU("category_id"));
    }

    public final String getDisplayName()
    {
        return getString(zziU("display_name"));
    }

    public final List getPlaceTypes()
    {
        ArrayList arraylist = new ArrayList();
        String s = getString(zziU("place_types"));
        if (!TextUtils.isEmpty(s))
        {
            android.text.TextUtils.SimpleStringSplitter simplestringsplitter = new android.text.TextUtils.SimpleStringSplitter(',');
            simplestringsplitter.setString(s);
            for (Iterator iterator = simplestringsplitter.iterator(); iterator.hasNext(); arraylist.add((String)iterator.next())) { }
        }
        return arraylist;
    }

    public int hashCode()
    {
        return zzd.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzd(this)).writeToParcel(parcel, i);
    }
}
