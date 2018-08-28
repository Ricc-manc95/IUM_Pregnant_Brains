// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;


// Referenced classes of package com.google.android.gms.reminders.model:
//            Task, zzah, zzl, zzr, 
//            zzt, zzab, zzn, TaskEntity, 
//            TaskId, DateTime, Location, LocationGroup, 
//            RecurrenceInfo, ExternalApplicationLink

public static final class zzcju
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

    public cationLink()
    {
    }

    public cationLink(Task task)
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
