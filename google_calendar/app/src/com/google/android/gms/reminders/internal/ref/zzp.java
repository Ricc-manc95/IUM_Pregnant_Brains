// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.zzah;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo

public class zzp extends zzo
    implements TaskId
{

    public zzp(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
    }

    public int describeContents()
    {
        return 0;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof TaskId))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zzah.zza(this, (TaskId)obj);
        }
    }

    public final Object freeze()
    {
        return new zzah(this);
    }

    public final String getClientAssignedId()
    {
        return getString(zziU("client_assigned_id"));
    }

    public final String getClientAssignedThreadId()
    {
        return getString(zziU("client_assigned_thread_id"));
    }

    public int hashCode()
    {
        return zzah.zza(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new zzah(this)).writeToParcel(parcel, i);
    }
}
