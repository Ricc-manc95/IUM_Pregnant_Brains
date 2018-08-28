// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            TaskId, zzag

public class zzah extends zza
    implements TaskId
{

    public static final android.os.Parcelable.Creator CREATOR = new zzag();
    public final int mVersionCode;
    public final String zzcjE;
    public final String zzcjF;

    zzah(int i, String s, String s1)
    {
        zzcjE = s;
        zzcjF = s1;
        mVersionCode = i;
    }

    public zzah(TaskId taskid)
    {
        this(taskid.getClientAssignedId(), taskid.getClientAssignedThreadId());
    }

    public zzah(String s, String s1)
    {
        this(2, s, s1);
    }

    public static int zza(TaskId taskid)
    {
        return Arrays.hashCode(new Object[] {
            taskid.getClientAssignedId(), taskid.getClientAssignedThreadId()
        });
    }

    public static boolean zza(TaskId taskid, TaskId taskid1)
    {
        String s = taskid.getClientAssignedId();
        String s1 = taskid1.getClientAssignedId();
        boolean flag;
        if (s == s1 || s != null && s.equals(s1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            taskid = taskid.getClientAssignedThreadId();
            taskid1 = taskid1.getClientAssignedThreadId();
            if (taskid == taskid1 || taskid != null && taskid.equals(taskid1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                return true;
            }
        }
        return false;
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
            return zza(this, (TaskId)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final String getClientAssignedId()
    {
        return zzcjE;
    }

    public final String getClientAssignedThreadId()
    {
        return zzcjF;
    }

    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            getClientAssignedId(), getClientAssignedThreadId()
        });
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        i = parcel.dataPosition();
        int j = mVersionCode;
        zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        zzc.zza(parcel, 3, zzcjE, false);
        zzc.zza(parcel, 4, zzcjF, false);
        zzc.zzJ(parcel, i);
    }

}
