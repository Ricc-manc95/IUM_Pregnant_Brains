// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.internal.ref.zzo;
import com.google.android.gms.reminders.model.CustomizedSnoozePreset;
import com.google.android.gms.reminders.model.SnoozePresetChangedEvent;
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzae;
import com.google.android.gms.reminders.model.zzh;
import com.google.android.gms.reminders.zzf;

public final class zzbbn extends zzo
    implements SnoozePresetChangedEvent
{

    private CustomizedSnoozePreset zzcgo;

    public zzbbn(DataHolder dataholder, int i)
    {
        super(dataholder, i);
    }

    public final Object freeze()
    {
        return new zzae(this);
    }

    public final String getAccountName()
    {
        return getString("account_name");
    }

    public final CustomizedSnoozePreset getCustomizedSnoozePreset()
    {
        if (zzcgo != null)
        {
            return zzcgo;
        }
        com.google.android.gms.reminders.model.CustomizedSnoozePreset.Builder builder = new com.google.android.gms.reminders.model.CustomizedSnoozePreset.Builder();
        Object obj;
        if (!zzcO("morning_customized_time"))
        {
            if (zzcO("morning_customized_time"))
            {
                obj = null;
            } else
            {
                obj = Long.valueOf(getLong("morning_customized_time"));
            }
            obj = zzf.zzaI(((Long) (obj)).longValue());
            if (obj != null)
            {
                obj = (Time)((Time) (obj)).freeze();
            } else
            {
                obj = null;
            }
            builder.zzchL = ((Time) (obj));
        }
        if (!zzcO("afternoon_customized_time"))
        {
            if (zzcO("afternoon_customized_time"))
            {
                obj = null;
            } else
            {
                obj = Long.valueOf(getLong("afternoon_customized_time"));
            }
            obj = zzf.zzaI(((Long) (obj)).longValue());
            if (obj != null)
            {
                obj = (Time)((Time) (obj)).freeze();
            } else
            {
                obj = null;
            }
            builder.zzchM = ((Time) (obj));
        }
        if (!zzcO("evening_customized_time"))
        {
            if (zzcO("evening_customized_time"))
            {
                obj = null;
            } else
            {
                obj = Long.valueOf(getLong("evening_customized_time"));
            }
            obj = zzf.zzaI(((Long) (obj)).longValue());
            if (obj != null)
            {
                obj = (Time)((Time) (obj)).freeze();
            } else
            {
                obj = null;
            }
            builder.zzchN = ((Time) (obj));
        }
        zzcgo = new zzh(builder.zzchL, builder.zzchM, builder.zzchN, true);
        return zzcgo;
    }
}
