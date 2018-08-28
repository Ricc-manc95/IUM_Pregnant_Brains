// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitInstance

public interface HabitInstanceModifications
    extends Parcelable, HabitInstance
{

    public abstract boolean isModified();

    public abstract boolean isStatusModified();

    public abstract HabitInstanceModifications setStatus(int i, boolean flag);
}
