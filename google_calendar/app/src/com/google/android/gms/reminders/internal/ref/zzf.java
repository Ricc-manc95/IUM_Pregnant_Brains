// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.ExternalApplicationLink;
import com.google.android.gms.reminders.model.zzn;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzf extends zzo
    implements ExternalApplicationLink
{

    public zzf(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ExternalApplicationLink))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzn.zza(this, (ExternalApplicationLink)obj);
        }
    }

    public final Object freeze()
    {
        return new zzn(this);
    }

    public final Integer getApplication()
    {
        String s = zziU("link_application");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final String getId()
    {
        return getString(zziU("link_id"));
    }

    public int hashCode()
    {
        return zzn.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzn(this)).writeToParcel(parcel, i);
    }
}
