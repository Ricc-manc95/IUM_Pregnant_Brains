// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.common.util.concurrent.FluentFuture;

public interface TaskDataLoader
{

    public abstract FluentFuture loadAsync(int i, int j);
}
