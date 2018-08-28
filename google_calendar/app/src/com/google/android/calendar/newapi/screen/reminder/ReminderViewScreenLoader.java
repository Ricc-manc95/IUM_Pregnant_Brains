// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.reminder;

import android.content.Context;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.newapi.common.AsyncTaskLoader;
import com.google.android.calendar.newapi.common.CompositeLoader;
import com.google.android.calendar.newapi.common.loader.SettingsLoader;
import com.google.android.calendar.newapi.common.loader.TaskLoader;
import com.google.android.calendar.task.TaskConnection;
import com.google.android.calendar.timely.TimelineTask;
import com.google.android.calendar.utils.account.AccountUtil;
import com.google.android.gms.reminders.model.Task;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.reminder:
//            ReminderViewScreenModel

final class ReminderViewScreenLoader extends CompositeLoader
{

    private final GoogleSettings settings;
    private SettingsLoader settingsLoader;
    private final Task task;
    private TaskLoader taskLoader;
    private final TimelineTask timelineItem;

    ReminderViewScreenLoader(Context context, TimelineTask timelinetask, ReminderViewScreenModel reminderviewscreenmodel, TaskConnection taskconnection)
    {
        Object obj = null;
        super();
        timelineItem = timelinetask;
        Task task1;
        if (reminderviewscreenmodel == null)
        {
            task1 = null;
        } else
        {
            task1 = reminderviewscreenmodel.task;
        }
        task = task1;
        if (reminderviewscreenmodel == null)
        {
            reminderviewscreenmodel = obj;
        } else
        {
            reminderviewscreenmodel = reminderviewscreenmodel.settings;
        }
        settings = reminderviewscreenmodel;
        if (task == null)
        {
            context = new TaskLoader(context, timelinetask.accountName, (String)timelinetask.id, taskconnection);
            taskLoader = context;
            super.loaders.add(context);
        }
        if (settings == null)
        {
            context = new SettingsLoader(AccountUtil.newGoogleAccount(timelinetask.accountName));
            settingsLoader = context;
            super.loaders.add(context);
        }
    }

    public final Object getResult()
    {
        Object obj1 = timelineItem;
        Object obj;
        Task task1;
        if (taskLoader == null)
        {
            task1 = task;
        } else
        {
            task1 = (Task)taskLoader.getResult();
        }
        if (settingsLoader == null)
        {
            obj = settings;
        } else
        {
            obj = settingsLoader;
            if (((AsyncTaskLoader) (obj)).getResult() instanceof GoogleSettings)
            {
                obj = (GoogleSettings)((AsyncTaskLoader) (obj)).getResult();
            } else
            {
                obj = null;
            }
        }
        obj1 = new ReminderViewScreenModel(((TimelineTask) (obj1)));
        ((ReminderViewScreenModel) (obj1)).setTask(task1);
        obj1.settings = ((GoogleSettings) (obj));
        return obj1;
    }
}
