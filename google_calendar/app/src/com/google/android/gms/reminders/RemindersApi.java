// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import java.util.List;

// Referenced classes of package com.google.android.gms.reminders:
//            UpdateRecurrenceOptions, LoadRemindersOptions

public interface RemindersApi
{

    public abstract PendingResult batchUpdateReminder(GoogleApiClient googleapiclient, List list);

    public abstract PendingResult changeRecurrence(GoogleApiClient googleapiclient, String s, String s1, Recurrence recurrence, Task task, UpdateRecurrenceOptions updaterecurrenceoptions);

    public abstract PendingResult createRecurrence(GoogleApiClient googleapiclient, String s, Recurrence recurrence, Task task);

    public abstract PendingResult createReminder(GoogleApiClient googleapiclient, Task task);

    public abstract PendingResult deleteRecurrence(GoogleApiClient googleapiclient, String s, UpdateRecurrenceOptions updaterecurrenceoptions);

    public abstract PendingResult deleteReminder(GoogleApiClient googleapiclient, TaskId taskid);

    public abstract PendingResult loadReminders(GoogleApiClient googleapiclient, LoadRemindersOptions loadremindersoptions);

    public abstract PendingResult makeTaskRecurring(GoogleApiClient googleapiclient, TaskId taskid, String s, Recurrence recurrence, Task task);

    public abstract PendingResult updateRecurrence(GoogleApiClient googleapiclient, String s, Task task, UpdateRecurrenceOptions updaterecurrenceoptions);

    public abstract PendingResult updateReminder(GoogleApiClient googleapiclient, Task task);
}
