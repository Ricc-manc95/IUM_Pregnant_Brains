// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.zzv;
import com.google.android.gms.reminders.LoadRemindersOptions;
import com.google.android.gms.reminders.RemindersApi;
import com.google.android.gms.reminders.UpdateRecurrenceOptions;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.Location;
import com.google.android.gms.reminders.model.Recurrence;
import com.google.android.gms.reminders.model.RecurrenceEnd;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.RecurrenceStart;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskId;
import com.google.android.gms.reminders.model.Time;
import com.google.android.gms.reminders.model.zzab;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class zzbbp
    implements RemindersApi
{

    private static final String zzcgp[] = {
        "/", " ", "(", ")", "{", "}", "&", "|", "\"", "\t", 
        "\r", "\n", "\0", ".", "-"
    };

    public zzbbp()
    {
    }

    private static void zza(DateTime datetime)
    {
        boolean flag3 = true;
        boolean flag;
        boolean flag2;
        if (datetime.getYear() != null && datetime.getMonth() != null && datetime.getDay() != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || datetime.getYear() == null && datetime.getMonth() == null && datetime.getDay() == null)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid DateTime, year/month/day must all be set or unset together."));
        }
        if (flag)
        {
            String s;
            if (datetime.getMonth().intValue() > 0 && datetime.getMonth().intValue() <= 12)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            s = String.valueOf(datetime.getMonth());
            s = (new StringBuilder(String.valueOf(s).length() + 42)).append("Invalid month ").append(s).append(", should be in range [1, 12]").toString();
            if (!flag2)
            {
                throw new IllegalArgumentException(String.valueOf(s));
            }
            if (datetime.getDay().intValue() > 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            s = String.valueOf(datetime.getDay());
            s = (new StringBuilder(String.valueOf(s).length() + 27)).append("Invalid day ").append(s).append(", should be >=1").toString();
            if (!flag2)
            {
                throw new IllegalArgumentException(String.valueOf(s));
            }
        }
        if (datetime.getAbsoluteTimeMs() != null || Boolean.TRUE.equals(datetime.getUnspecifiedFutureTime()) || flag)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid DateTime, must either contain an absolute time, a year/month/day, or be set to an unspecified future time."));
        }
        if (!Boolean.TRUE.equals(datetime.getUnspecifiedFutureTime()) || datetime.getAbsoluteTimeMs() == null && !flag)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Invalid DateTime, unspecified_future_time cannot be set together with absolute_time or year/month/day"));
        }
        datetime = datetime.getTime();
        if (datetime != null)
        {
            String s1;
            boolean flag1;
            if (datetime.getHour().intValue() >= 0 && datetime.getHour().intValue() < 24)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            s1 = String.valueOf(datetime.getHour());
            s1 = (new StringBuilder(String.valueOf(s1).length() + 13)).append("Invalid hour:").append(s1).toString();
            if (!flag1)
            {
                throw new IllegalArgumentException(String.valueOf(s1));
            }
            if (datetime.getMinute().intValue() >= 0 && datetime.getMinute().intValue() < 60)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            s1 = String.valueOf(datetime.getMinute());
            s1 = (new StringBuilder(String.valueOf(s1).length() + 15)).append("Invalid minute:").append(s1).toString();
            if (!flag1)
            {
                throw new IllegalArgumentException(String.valueOf(s1));
            }
            if (datetime.getSecond().intValue() >= 0 && datetime.getSecond().intValue() < 60)
            {
                flag1 = flag3;
            } else
            {
                flag1 = false;
            }
            datetime = String.valueOf(datetime.getSecond());
            datetime = (new StringBuilder(String.valueOf(datetime).length() + 15)).append("Invalid second:").append(datetime).toString();
            if (!flag1)
            {
                throw new IllegalArgumentException(String.valueOf(datetime));
            }
        }
    }

    private static void zza(Location location)
    {
        if (location != null && location.getLocationType() != null)
        {
            boolean flag;
            if (location.getLat() == null && location.getLng() == null && location.getDisplayAddress() == null && location.getGeoFeatureId() == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("If providing a locationType you cannot provide lat/lng, address, or any other location identifying attributes."));
            }
        }
    }

    private static void zza(Recurrence recurrence)
    {
        boolean flag2 = true;
        if (recurrence.getFrequency() == null)
        {
            throw new NullPointerException(String.valueOf("Must provide Recurrence.frequency on create"));
        }
        if (recurrence.getRecurrenceStart() == null)
        {
            throw new NullPointerException(String.valueOf("Must provide Recurrence.recurrence_start on create"));
        }
        if (recurrence.getRecurrenceStart().getStartDateTime() == null)
        {
            throw new NullPointerException(String.valueOf("Must provide RecurrenceStart.start_date_time on create"));
        }
        zza(recurrence.getRecurrenceStart().getStartDateTime());
        if (recurrence.getRecurrenceEnd() != null)
        {
            recurrence = recurrence.getRecurrenceEnd();
            boolean flag;
            if (recurrence.getAutoRenew() == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("RecurrenceEnd.auto_renew is readonly"));
            }
            if (recurrence.getAutoRenewUntil() == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("RecurrenceEnd.auto_renew_until is readonly"));
            }
            if (recurrence.getNumOccurrences() != null)
            {
                boolean flag1;
                if (recurrence.getNumOccurrences().intValue() <= 1000)
                {
                    flag1 = flag2;
                } else
                {
                    flag1 = false;
                }
                if (!flag1)
                {
                    throw new IllegalArgumentException(String.valueOf("RecurrenceEnd.num_occurrences must be <= 1000"));
                }
            } else
            if (recurrence.getEndDateTime() != null)
            {
                zza(recurrence.getEndDateTime());
            }
        }
    }

    private static void zza(Task task)
    {
        boolean flag1 = true;
        boolean flag;
        if (task.getDueDate() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("task.due_date is determined by recurrence and should not be set"));
        }
        if (task.getTaskId() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("task.task_id field is readonly"));
        }
        if (task.getRecurrenceInfo() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("task.recurrence_info field is readonly"));
        }
        if (task.getLocation() == null)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("task.location not supported for recurrences."));
        } else
        {
            return;
        }
    }

    private static Task zzb(Task task)
    {
        Task task1;
label0:
        {
            if (!Boolean.TRUE.equals(task.getSnoozed()))
            {
                task1 = task;
                if (!Boolean.TRUE.equals(task.getPinned()))
                {
                    break label0;
                }
            }
            if (!Boolean.TRUE.equals(task.getArchived()))
            {
                task1 = task;
                if (!Boolean.TRUE.equals(task.getDeleted()))
                {
                    break label0;
                }
            }
            task = new com.google.android.gms.reminders.model.Task.Builder(task);
            task.zzcjg = Boolean.valueOf(false);
            task.zzcjh = Boolean.valueOf(false);
            task1 = task.build();
        }
        return task1;
    }

    private static void zziT(String s)
    {
        boolean flag;
        if (!TextUtils.isEmpty(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("empty recurrence id"));
        }
        String as[] = zzcgp;
        int j = as.length;
        boolean flag1;
        for (int i = 0; i < j; i++)
        {
            String s1 = as[i];
            if (!s.contains(s1))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException(String.format("recurrence id must not contain %s", new Object[] {
                    s1
                }));
            }
        }

    }

    public final PendingResult batchUpdateReminder(GoogleApiClient googleapiclient, List list)
    {
        if (list == null)
        {
            throw new NullPointerException(String.valueOf("New tasks required on update."));
        }
        ArrayList arraylist = new ArrayList();
        Task task;
        boolean flag;
        for (list = list.iterator(); list.hasNext(); arraylist.add(zzb(task)))
        {
            task = (Task)list.next();
            if (task == null)
            {
                throw new NullPointerException(String.valueOf("New task required on update."));
            }
            if (task.getTaskId() == null)
            {
                throw new NullPointerException(String.valueOf("Task id required on update."));
            }
            if (task.getLocation() != null)
            {
                zza(task.getLocation());
            }
            if (task.getDueDate() == null)
            {
                continue;
            }
            zza(task.getDueDate());
            if (task.getLocation() == null && task.getLocationGroup() == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalArgumentException(String.valueOf("Cannot snooze to both location and time."));
            }
        }

        return googleapiclient.zza(new _cls14(googleapiclient, arraylist));
    }

    public final PendingResult changeRecurrence(GoogleApiClient googleapiclient, String s, String s1, Recurrence recurrence, Task task, UpdateRecurrenceOptions updaterecurrenceoptions)
    {
        boolean flag1 = false;
        if (recurrence == null)
        {
            throw new NullPointerException(String.valueOf("new_recurrence required"));
        }
        if (task == null)
        {
            throw new NullPointerException(String.valueOf("task required"));
        }
        boolean flag;
        if (task.getDeleted() == null || !task.getDeleted().booleanValue())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("task.deleted field is readonly"));
        }
        flag = flag1;
        if (!TextUtils.equals(s, s1))
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("new recurrenceId must be different than existing recurrenceId"));
        }
        if (task.getTaskList() == null)
        {
            throw new NullPointerException(String.valueOf("Must set task list"));
        }
        if (updaterecurrenceoptions == null)
        {
            throw new NullPointerException(String.valueOf("updateRecurrenceOption required"));
        }
        zziT(s);
        zziT(s1);
        zza(recurrence);
        zza(task);
        task = new com.google.android.gms.reminders.model.Task.Builder(task);
        com.google.android.gms.reminders.model.RecurrenceInfo.Builder builder = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder();
        builder.zzciV = s1;
        if (recurrence != null)
        {
            s1 = (Recurrence)recurrence.freeze();
        } else
        {
            s1 = null;
        }
        builder.zzciU = s1;
        s1 = new zzab(builder.zzciU, builder.zzciV, builder.zzciW, builder.zzciX, true);
        if (s1 != null)
        {
            s1 = (RecurrenceInfo)s1.freeze();
        } else
        {
            s1 = null;
        }
        task.zzcjr = s1;
        return googleapiclient.zza(new _cls3(googleapiclient, s, zzb(task.build()), updaterecurrenceoptions));
    }

    public final PendingResult createRecurrence(GoogleApiClient googleapiclient, String s, Recurrence recurrence, Task task)
    {
        boolean flag1 = false;
        boolean flag;
        if (!zzv.zzdg(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Must provide recurrenceId on create"));
        }
        if (recurrence == null)
        {
            throw new NullPointerException(String.valueOf("Must provide recurrence rule on create."));
        }
        if (task == null)
        {
            throw new NullPointerException(String.valueOf("Must provide reminder template on create."));
        }
        if (task.getTaskList() == null)
        {
            throw new NullPointerException(String.valueOf("Must provide task list on create"));
        }
        if (!Boolean.TRUE.equals(task.getDeleted()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Task.deleted field is readonly."));
        }
        if (task.getDueDate() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Cannot set due_date on recurring reminder"));
        }
        flag = flag1;
        if (task.getLocation() == null)
        {
            flag = true;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Cannot set location on recurring reminder"));
        }
        zziT(s);
        zza(recurrence);
        zza(task);
        task = new com.google.android.gms.reminders.model.Task.Builder(task);
        com.google.android.gms.reminders.model.RecurrenceInfo.Builder builder = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder();
        builder.zzciV = s;
        if (recurrence != null)
        {
            s = (Recurrence)recurrence.freeze();
        } else
        {
            s = null;
        }
        builder.zzciU = s;
        s = new zzab(builder.zzciU, builder.zzciV, builder.zzciW, builder.zzciX, true);
        if (s != null)
        {
            s = (RecurrenceInfo)s.freeze();
        } else
        {
            s = null;
        }
        task.zzcjr = s;
        return googleapiclient.zza(new _cls18(googleapiclient, zzb(task.build())));
    }

    public final PendingResult createReminder(GoogleApiClient googleapiclient, Task task)
    {
        boolean flag2 = true;
        if (task == null)
        {
            throw new NullPointerException(String.valueOf("Must provide task on create."));
        }
        if (task.getTaskList() == null)
        {
            throw new NullPointerException(String.valueOf("Must provide task list on create"));
        }
        boolean flag;
        if (!Boolean.TRUE.equals(task.getDeleted()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Task.deleted field is readonly."));
        }
        if (task.getRecurrenceInfo() == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Task recurrence info field is readonly."));
        }
        if (task.getDueDate() != null)
        {
            zza(task.getDueDate());
            boolean flag1;
            if (task.getLocation() == null && task.getLocationGroup() == null)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                throw new IllegalArgumentException(String.valueOf("Cannot snooze to both location and time."));
            }
        }
        zza(task.getLocation());
        return googleapiclient.zza(new _cls13(googleapiclient, null, zzb(task), null));
    }

    public final PendingResult deleteRecurrence(GoogleApiClient googleapiclient, String s, UpdateRecurrenceOptions updaterecurrenceoptions)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Must provide client-assigned recurrence id."));
        }
        if (updaterecurrenceoptions == null)
        {
            throw new NullPointerException(String.valueOf("updateRecurrenceOption required"));
        } else
        {
            return googleapiclient.zza(new _cls2(googleapiclient, s, updaterecurrenceoptions));
        }
    }

    public final PendingResult deleteReminder(GoogleApiClient googleapiclient, TaskId taskid)
    {
        if (taskid == null)
        {
            throw new NullPointerException(String.valueOf("Task id required on delete."));
        } else
        {
            return googleapiclient.zza(new _cls15(googleapiclient, taskid));
        }
    }

    public final PendingResult loadReminders(GoogleApiClient googleapiclient, LoadRemindersOptions loadremindersoptions)
    {
        return googleapiclient.zza(new _cls1(googleapiclient, loadremindersoptions));
    }

    public final PendingResult makeTaskRecurring(GoogleApiClient googleapiclient, TaskId taskid, String s, Recurrence recurrence, Task task)
    {
        if (taskid == null)
        {
            throw new NullPointerException(String.valueOf("task_id required"));
        }
        if (recurrence == null)
        {
            throw new NullPointerException(String.valueOf("recurrence required"));
        }
        if (task == null)
        {
            throw new NullPointerException(String.valueOf("task required"));
        }
        boolean flag;
        if (!Boolean.TRUE.equals(task.getDeleted()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("Task.deleted field is readonly."));
        }
        if (task.getTaskList() == null)
        {
            throw new NullPointerException(String.valueOf("Must set task list"));
        }
        zziT(s);
        zza(recurrence);
        zza(task);
        task = new com.google.android.gms.reminders.model.Task.Builder(task);
        com.google.android.gms.reminders.model.RecurrenceInfo.Builder builder = new com.google.android.gms.reminders.model.RecurrenceInfo.Builder();
        builder.zzciV = s;
        if (recurrence != null)
        {
            s = (Recurrence)recurrence.freeze();
        } else
        {
            s = null;
        }
        builder.zzciU = s;
        s = new zzab(builder.zzciU, builder.zzciV, builder.zzciW, builder.zzciX, true);
        if (s != null)
        {
            s = (RecurrenceInfo)s.freeze();
        } else
        {
            s = null;
        }
        task.zzcjr = s;
        if (taskid != null)
        {
            taskid = (TaskId)taskid.freeze();
        } else
        {
            taskid = null;
        }
        task.zzcjc = taskid;
        return googleapiclient.zza(new _cls4(googleapiclient, zzb(task.build())));
    }

    public final PendingResult updateRecurrence(GoogleApiClient googleapiclient, String s, Task task, UpdateRecurrenceOptions updaterecurrenceoptions)
    {
        if (s == null)
        {
            throw new NullPointerException(String.valueOf("Must provide client-assigned recurrence id."));
        }
        if (task == null)
        {
            throw new NullPointerException(String.valueOf("Must provide new task template"));
        }
        if (updaterecurrenceoptions == null)
        {
            throw new NullPointerException(String.valueOf("updateRecurrenceOption required"));
        } else
        {
            return googleapiclient.zza(new _cls19(googleapiclient, s, zzb(task), updaterecurrenceoptions));
        }
    }

    public final PendingResult updateReminder(GoogleApiClient googleapiclient, Task task)
    {
        return batchUpdateReminder(googleapiclient, Arrays.asList(new Task[] {
            task
        }));
    }


    private class _cls14 extends zzbbo
    {

        private final List zzcgC;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            Object obj = zzcgC;
            zzb1 = zzbbl1.zzaKD;
            if (((zzg) (zzb1)).zzafe != null)
            {
                zzb1 = ((zzg) (zzb1)).zzafe.name;
            } else
            {
                zzb1 = null;
            }
            if (TextUtils.isEmpty(zzb1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            }
            zzb1 = new ArrayList();
            Task task;
            for (obj = ((List) (obj)).iterator(); ((Iterator) (obj)).hasNext(); zzb1.add(new TaskEntity(task)))
            {
                task = (Task)((Iterator) (obj)).next();
                if (task.getTaskId().getClientAssignedId() == null)
                {
                    throw new NullPointerException("null reference");
                }
            }

            ((zzbbj)zzbbl1.zzyP()).zza(new zzbbl.zzd(this), zzb1);
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls14(GoogleApiClient googleapiclient, List list)
        {
            zzcgC = list;
            super(googleapiclient);
        }
    }


    private class _cls3 extends zzbbo
    {

        private final String zzcgs;
        private final UpdateRecurrenceOptions zzcgt;
        private final Task zzcgu;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            String s = zzcgs;
            Task task = zzcgu;
            UpdateRecurrenceOptions updaterecurrenceoptions = zzcgt;
            zzb1 = zzbbl1.zzaKD;
            if (((zzg) (zzb1)).zzafe != null)
            {
                zzb1 = ((zzg) (zzb1)).zzafe.name;
            } else
            {
                zzb1 = null;
            }
            if (TextUtils.isEmpty(zzb1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            } else
            {
                ((zzbbj)zzbbl1.zzyP()).zzb(new zzbbl.zzd(this), s, new TaskEntity(task), updaterecurrenceoptions);
                return;
            }
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls3(GoogleApiClient googleapiclient, String s, Task task, UpdateRecurrenceOptions updaterecurrenceoptions)
        {
            zzcgs = s;
            zzcgu = task;
            zzcgt = updaterecurrenceoptions;
            super(googleapiclient);
        }
    }


    private class _cls18 extends zzbbo
    {

        private final Task zzcgu;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            Task task = zzcgu;
            zzb1 = zzbbl1.zzaKD;
            if (((zzg) (zzb1)).zzafe != null)
            {
                zzb1 = ((zzg) (zzb1)).zzafe.name;
            } else
            {
                zzb1 = null;
            }
            if (TextUtils.isEmpty(zzb1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            }
            if (task == null)
            {
                throw new NullPointerException("null reference");
            } else
            {
                ((zzbbj)zzbbl1.zzyP()).zzc(new zzbbl.zzd(this), new TaskEntity(task));
                return;
            }
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls18(GoogleApiClient googleapiclient, Task task)
        {
            zzcgu = task;
            super(googleapiclient);
        }
    }


    private class _cls13 extends zzbbo
    {

        private final zzzv zzcgA;
        private final CreateReminderOptions zzcgB;
        private final Task zzcgu;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzb1 = (zzbbl)zzb1;
            if (zzcgB == null)
            {
                zzb1.zza(this, zzcgu, null, zzb.zzcfK);
                return;
            } else
            {
                zzb1 = zzcgu;
                zzb1 = zzcgA;
                throw new NoSuchMethodError();
            }
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls13(GoogleApiClient googleapiclient, CreateReminderOptions createreminderoptions, Task task, zzzv zzzv)
        {
            zzcgB = createreminderoptions;
            zzcgu = task;
            zzcgA = zzzv;
            super(googleapiclient);
        }
    }


    private class _cls2 extends zzbbo
    {

        private final String zzcgs;
        private final UpdateRecurrenceOptions zzcgt;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            String s = zzcgs;
            UpdateRecurrenceOptions updaterecurrenceoptions = zzcgt;
            zzb1 = zzbbl1.zzaKD;
            if (((zzg) (zzb1)).zzafe != null)
            {
                zzb1 = ((zzg) (zzb1)).zzafe.name;
            } else
            {
                zzb1 = null;
            }
            if (TextUtils.isEmpty(zzb1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            } else
            {
                ((zzbbj)zzbbl1.zzyP()).zza(new zzbbl.zzd(this), s, updaterecurrenceoptions);
                return;
            }
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls2(GoogleApiClient googleapiclient, String s, UpdateRecurrenceOptions updaterecurrenceoptions)
        {
            zzcgs = s;
            zzcgt = updaterecurrenceoptions;
            super(googleapiclient);
        }
    }


    private class _cls15 extends zzbbo
    {

        private final TaskId zzcgD;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            TaskId taskid = zzcgD;
            if (taskid == null)
            {
                throw new NullPointerException("null reference");
            }
            zzb1 = zzbbl1.zzaKD;
            if (((zzg) (zzb1)).zzafe != null)
            {
                zzb1 = ((zzg) (zzb1)).zzafe.name;
            } else
            {
                zzb1 = null;
            }
            if (TextUtils.isEmpty(zzb1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            }
            if (taskid.getClientAssignedId() == null)
            {
                throw new NullPointerException("null reference");
            } else
            {
                ((zzbbj)zzbbl1.zzyP()).zza(new zzbbl.zzd(this), new zzah(taskid));
                return;
            }
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls15(GoogleApiClient googleapiclient, TaskId taskid)
        {
            zzcgD = taskid;
            super(googleapiclient);
        }
    }


    private class _cls1 extends zzbbo
    {

        private final LoadRemindersOptions zzcgq;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            class _cls1 extends zzbbg
            {

                private final _cls1 zzcgr;

                public final void zza(DataHolder dataholder, Status status)
                {
                    if (dataholder == null)
                    {
                        dataholder = null;
                    } else
                    {
                        dataholder = new RemindersBuffer(dataholder);
                    }
                    zzcgr.zzb(new zzb(dataholder, status));
                }

                _cls1()
                {
                    zzcgr = _cls1.this;
                    super();
                }

                private class zzb
                    implements com.google.android.gms.reminders.RemindersApi.LoadRemindersResult
                {

                    private final Status zzahG;
                    private final RemindersBuffer zzcgF;

                    public final RemindersBuffer getRemindersBuffer()
                    {
                        return zzcgF;
                    }

                    public final Status getStatus()
                    {
                        return zzahG;
                    }

                    zzb(RemindersBuffer remindersbuffer, Status status)
                    {
                        zzcgF = remindersbuffer;
                        zzahG = status;
                    }
                }

            }

            _cls1 _lcls1 = new _cls1();
            Object obj;
            if (zzcgq == null)
            {
                zzb1 = LoadRemindersOptions.zzcfL;
            } else
            {
                zzb1 = zzcgq;
            }
            obj = zzbbl1.zzaKD;
            if (((zzg) (obj)).zzafe != null)
            {
                obj = ((zzg) (obj)).zzafe.name;
            } else
            {
                obj = null;
            }
            if (TextUtils.isEmpty(((CharSequence) (obj))))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            } else
            {
                ((zzbbj)zzbbl1.zzyP()).zza(_lcls1, zzb1);
                return;
            }
        }

        protected final Result zzb(Status status)
        {
            return new zzb(null, status);
        }

        _cls1(GoogleApiClient googleapiclient, LoadRemindersOptions loadremindersoptions)
        {
            zzcgq = loadremindersoptions;
            super(googleapiclient);
        }
    }


    private class _cls4 extends zzbbo
    {

        private final Task zzcgu;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzbbl zzbbl1 = (zzbbl)zzb1;
            Task task = zzcgu;
            zzb1 = zzbbl1.zzaKD;
            if (((zzg) (zzb1)).zzafe != null)
            {
                zzb1 = ((zzg) (zzb1)).zzafe.name;
            } else
            {
                zzb1 = null;
            }
            if (TextUtils.isEmpty(zzb1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            } else
            {
                ((zzbbj)zzbbl1.zzyP()).zzd(new zzbbl.zzd(this), new TaskEntity(task));
                return;
            }
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls4(GoogleApiClient googleapiclient, Task task)
        {
            zzcgu = task;
            super(googleapiclient);
        }
    }


    private class _cls19 extends zzbbo
    {

        private final String zzcgs;
        private final UpdateRecurrenceOptions zzcgt;
        private final Task zzcgu;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzb1 = (zzbbl)zzb1;
            String s = zzcgs;
            Task task = zzcgu;
            UpdateRecurrenceOptions updaterecurrenceoptions = zzcgt;
            ((zzbbj)zzb1.zzyP()).zza(new zzbbl.zzd(this), s, new TaskEntity(task), updaterecurrenceoptions);
        }

        public final Result zzb(Status status)
        {
            return status;
        }

        _cls19(GoogleApiClient googleapiclient, String s, Task task, UpdateRecurrenceOptions updaterecurrenceoptions)
        {
            zzcgs = s;
            zzcgu = task;
            zzcgt = updaterecurrenceoptions;
            super(googleapiclient);
        }
    }

}
