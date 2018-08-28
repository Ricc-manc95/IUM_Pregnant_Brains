// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcelable;
import com.google.android.calendar.api.color.EventColor;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitContract, HabitDescriptor, HabitReminders

public interface Habit
    extends Parcelable
{

    public abstract EventColor getColorOverride();

    public abstract HabitContract getContract();

    public abstract HabitDescriptor getDescriptor();

    public abstract String getFingerprint();

    public abstract int getFitIntegrationStatus();

    public abstract HabitReminders getReminders();

    public abstract String getSummary();

    public abstract int getType();

    public abstract int getVisibility();

    public abstract boolean isFitIntegrationEnabled();
}
