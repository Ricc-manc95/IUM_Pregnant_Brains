// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;


// Referenced classes of package com.google.android.gms.reminders.model:
//            SnoozePresetChangedEvent, CustomizedSnoozePreset

public final class zzae
    implements SnoozePresetChangedEvent
{

    private final String zzajr;
    private final CustomizedSnoozePreset zzcgo;

    public zzae(SnoozePresetChangedEvent snoozepresetchangedevent)
    {
        zzajr = snoozepresetchangedevent.getAccountName();
        zzcgo = snoozepresetchangedevent.getCustomizedSnoozePreset();
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

    public final String getAccountName()
    {
        return zzajr;
    }

    public final CustomizedSnoozePreset getCustomizedSnoozePreset()
    {
        return zzcgo;
    }
}
