// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import com.google.android.calendar.api.habit.HabitModifications;

public interface GrooveEditScreenListener
{

    public abstract void onEditCancelled();

    public abstract void onEditFinished(HabitModifications habitmodifications);
}
