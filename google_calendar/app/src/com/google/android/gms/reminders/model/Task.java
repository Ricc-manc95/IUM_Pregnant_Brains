// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.internal.ReflectedParcelable;

// Referenced classes of package com.google.android.gms.reminders.model:
//            DateTime, ExternalApplicationLink, Location, LocationGroup, 
//            RecurrenceInfo, TaskId, zzah, zzl, 
//            zzr, zzt, zzab, zzn, 
//            TaskEntity

public interface Task
    extends Freezable, ReflectedParcelable
{
    public static final class Builder
    {

        public String zzavt;
        public TaskId zzcjc;
        public Integer zzcjd;
        private Long zzcje;
        public Long zzcjf;
        public Boolean zzcjg;
        public Boolean zzcjh;
        public Boolean zzcji;
        public Boolean zzcjj;
        public Long zzcjk;
        public DateTime zzcjl;
        private DateTime zzcjm;
        public Location zzcjn;
        private LocationGroup zzcjo;
        private Long zzcjp;
        public byte zzcjq[];
        public RecurrenceInfo zzcjr;
        public byte zzcjs[];
        private Integer zzcjt;
        private ExternalApplicationLink zzcju;

        public final Task build()
        {
            return new TaskEntity(zzcjc, zzcjd, zzavt, zzcje, zzcjf, zzcjg, zzcjh, zzcji, zzcjj, zzcjk, zzcjl, zzcjm, zzcjn, zzcjo, zzcjp, zzcjq, zzcjr, zzcjs, zzcjt, zzcju, null, null, true);
        }

        public Builder()
        {
        }

        public Builder(Task task)
        {
            Object obj1 = null;
            super();
            Object obj;
            if (task.getTaskId() == null)
            {
                obj = null;
            } else
            {
                obj = new zzah(task.getTaskId());
            }
            zzcjc = ((TaskId) (obj));
            zzcjd = task.getTaskList();
            zzavt = task.getTitle();
            zzcje = task.getCreatedTimeMillis();
            zzcjf = task.getArchivedTimeMs();
            zzcjg = task.getArchived();
            zzcjh = task.getDeleted();
            zzcji = task.getPinned();
            zzcjj = task.getSnoozed();
            zzcjk = task.getSnoozedTimeMillis();
            if (task.getDueDate() == null)
            {
                obj = null;
            } else
            {
                obj = new zzl(task.getDueDate());
            }
            zzcjl = ((DateTime) (obj));
            if (task.getEventDate() == null)
            {
                obj = null;
            } else
            {
                obj = new zzl(task.getEventDate());
            }
            zzcjm = ((DateTime) (obj));
            if (task.getLocation() == null)
            {
                obj = null;
            } else
            {
                obj = new zzr(task.getLocation());
            }
            zzcjn = ((Location) (obj));
            if (task.getLocationGroup() == null)
            {
                obj = null;
            } else
            {
                obj = new zzt(task.getLocationGroup());
            }
            zzcjo = ((LocationGroup) (obj));
            zzcjp = task.getLocationSnoozedUntilMs();
            zzcjq = task.getExtensions();
            if (task.getRecurrenceInfo() == null)
            {
                obj = null;
            } else
            {
                obj = new zzab(task.getRecurrenceInfo());
            }
            zzcjr = ((RecurrenceInfo) (obj));
            zzcjs = task.getAssistance();
            zzcjt = task.getExperiment();
            if (task.getExternalApplicationLink() == null)
            {
                task = obj1;
            } else
            {
                task = new zzn(task.getExternalApplicationLink());
            }
            zzcju = task;
        }
    }


    public abstract Boolean getArchived();

    public abstract Long getArchivedTimeMs();

    public abstract byte[] getAssistance();

    public abstract Long getCreatedTimeMillis();

    public abstract Boolean getDeleted();

    public abstract DateTime getDueDate();

    public abstract Long getDueDateMillis();

    public abstract DateTime getEventDate();

    public abstract Integer getExperiment();

    public abstract byte[] getExtensions();

    public abstract ExternalApplicationLink getExternalApplicationLink();

    public abstract Long getFiredTimeMillis();

    public abstract Location getLocation();

    public abstract LocationGroup getLocationGroup();

    public abstract Long getLocationSnoozedUntilMs();

    public abstract Boolean getPinned();

    public abstract RecurrenceInfo getRecurrenceInfo();

    public abstract Boolean getSnoozed();

    public abstract Long getSnoozedTimeMillis();

    public abstract TaskId getTaskId();

    public abstract Integer getTaskList();

    public abstract String getTitle();
}
