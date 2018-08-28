// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.content.Context;
import com.google.android.gms.common.api.PendingResult;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitFilterOptions, HabitModifications, HabitDescriptor

public interface HabitClient
{

    public abstract PendingResult count(HabitFilterOptions habitfilteroptions);

    public abstract PendingResult create(HabitModifications habitmodifications);

    public abstract void initialize(Context context);

    public abstract PendingResult list(HabitFilterOptions habitfilteroptions);

    public abstract PendingResult read(HabitDescriptor habitdescriptor);

    public abstract PendingResult read(HabitDescriptor ahabitdescriptor[]);

    public abstract PendingResult update(HabitModifications habitmodifications);
}
