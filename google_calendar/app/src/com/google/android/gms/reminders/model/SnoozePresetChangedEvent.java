// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import com.google.android.gms.common.data.Freezable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            CustomizedSnoozePreset

public interface SnoozePresetChangedEvent
    extends Freezable
{

    public abstract String getAccountName();

    public abstract CustomizedSnoozePreset getCustomizedSnoozePreset();
}