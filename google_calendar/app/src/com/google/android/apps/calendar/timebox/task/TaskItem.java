// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.Item;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskData

public interface TaskItem
    extends Item
{

    public abstract TaskData getTaskData();

    public abstract TaskItem setDone(boolean flag);
}
