// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.os.Parcelable;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitDescriptor

public interface HabitInstance
    extends Parcelable
{

    public abstract HabitDescriptor getHabitParentDescriptor();

    public abstract int getParentType();

    public abstract int getStatus();

    public abstract boolean getStatusInferred();
}
