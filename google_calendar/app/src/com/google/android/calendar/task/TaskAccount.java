// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.task;

import java.util.List;

public interface TaskAccount
{

    public abstract String getAccountName();

    public abstract Tasks getTasks();

    public abstract void loadTasks(long l, long l1, List list, boolean flag, boolean flag1, 
            int i);

    public abstract boolean shouldLoad(long l, long l1, boolean flag, boolean flag1);
}
