// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;
import java.util.Arrays;

// Referenced classes of package com.google.android.gms.reminders.model:
//            Task, zzaf, zzah, zzl, 
//            zzr, zzt, zzab, zzn, 
//            TaskId, DateTime, Location, LocationGroup, 
//            RecurrenceInfo, ExternalApplicationLink

public class TaskEntity extends zza
    implements Task
{

    public static final android.os.Parcelable.Creator CREATOR = new zzaf();
    public final int mVersionCode;
    public final String zzavt;
    public final zzab zzcjA;
    public final zzn zzcjB;
    public final Long zzcjC;
    public final Long zzcjD;
    public final Integer zzcjd;
    public final Long zzcje;
    public final Long zzcjf;
    public final Boolean zzcjg;
    public final Boolean zzcjh;
    public final Boolean zzcji;
    public final Boolean zzcjj;
    public final Long zzcjk;
    public final Long zzcjp;
    public final byte zzcjq[];
    public final byte zzcjs[];
    public final Integer zzcjt;
    public final zzah zzcjv;
    public final zzl zzcjw;
    public final zzl zzcjx;
    public final zzr zzcjy;
    public final zzt zzcjz;

    TaskEntity(int i, zzah zzah1, Integer integer, String s, Long long1, Long long2, Boolean boolean1, 
            Boolean boolean2, Boolean boolean3, Boolean boolean4, Long long3, zzl zzl1, zzl zzl2, zzr zzr1, 
            zzt zzt1, Long long4, byte abyte0[], zzab zzab1, byte abyte1[], Integer integer1, zzn zzn1, 
            Long long5, Long long6)
    {
        zzcjv = zzah1;
        zzcjd = integer;
        zzavt = s;
        zzcje = long1;
        zzcjf = long2;
        zzcjg = boolean1;
        zzcjh = boolean2;
        zzcji = boolean3;
        zzcjj = boolean4;
        zzcjk = long3;
        zzcjw = zzl1;
        zzcjx = zzl2;
        zzcjy = zzr1;
        zzcjz = zzt1;
        zzcjp = long4;
        zzcjq = abyte0;
        zzcjA = zzab1;
        zzcjs = abyte1;
        zzcjt = integer1;
        zzcjB = zzn1;
        zzcjC = long5;
        zzcjD = long6;
        mVersionCode = i;
    }

    public TaskEntity(Task task)
    {
        this(task.getTaskId(), task.getTaskList(), task.getTitle(), task.getCreatedTimeMillis(), task.getArchivedTimeMs(), task.getArchived(), task.getDeleted(), task.getPinned(), task.getSnoozed(), task.getSnoozedTimeMillis(), task.getDueDate(), task.getEventDate(), task.getLocation(), task.getLocationGroup(), task.getLocationSnoozedUntilMs(), task.getExtensions(), task.getRecurrenceInfo(), task.getAssistance(), task.getExperiment(), task.getExternalApplicationLink(), task.getFiredTimeMillis(), task.getDueDateMillis(), false);
    }

    TaskEntity(TaskId taskid, Integer integer, String s, Long long1, Long long2, Boolean boolean1, Boolean boolean2, 
            Boolean boolean3, Boolean boolean4, Long long3, DateTime datetime, DateTime datetime1, Location location, LocationGroup locationgroup, 
            Long long4, byte abyte0[], RecurrenceInfo recurrenceinfo, byte abyte1[], Integer integer1, ExternalApplicationLink externalapplicationlink, Long long5, 
            Long long6, boolean flag)
    {
        mVersionCode = 3;
        zzcjd = integer;
        zzavt = s;
        zzcje = long1;
        zzcjf = long2;
        zzcjg = boolean1;
        zzcjh = boolean2;
        zzcji = boolean3;
        zzcjj = boolean4;
        zzcjk = long3;
        zzcjp = long4;
        zzcjq = abyte0;
        zzcjs = abyte1;
        zzcjt = integer1;
        zzcjC = long5;
        zzcjD = long6;
        if (flag)
        {
            zzcjv = (zzah)taskid;
            zzcjw = (zzl)datetime;
            zzcjx = (zzl)datetime1;
            zzcjy = (zzr)location;
            zzcjz = (zzt)locationgroup;
            zzcjA = (zzab)recurrenceinfo;
            taskid = (zzn)externalapplicationlink;
        } else
        {
            if (taskid == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzah(taskid);
            }
            zzcjv = taskid;
            if (datetime == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzl(datetime);
            }
            zzcjw = taskid;
            if (datetime1 == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzl(datetime1);
            }
            zzcjx = taskid;
            if (location == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzr(location);
            }
            zzcjy = taskid;
            if (locationgroup == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzt(locationgroup);
            }
            zzcjz = taskid;
            if (recurrenceinfo == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzab(recurrenceinfo);
            }
            zzcjA = taskid;
            if (externalapplicationlink == null)
            {
                taskid = null;
            } else
            {
                taskid = new zzn(externalapplicationlink);
            }
        }
        zzcjB = taskid;
    }

    public static boolean zza(Task task, Task task1)
    {
        TaskId taskid = task.getTaskId();
        TaskId taskid1 = task1.getTaskId();
        boolean flag;
        if (taskid == taskid1 || taskid != null && taskid.equals(taskid1))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            Integer integer = task.getTaskList();
            Integer integer2 = task1.getTaskList();
            if (integer == integer2 || integer != null && integer.equals(integer2))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                String s = task.getTitle();
                String s1 = task1.getTitle();
                if (s == s1 || s != null && s.equals(s1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    Long long1 = task.getCreatedTimeMillis();
                    Long long5 = task1.getCreatedTimeMillis();
                    if (long1 == long5 || long1 != null && long1.equals(long5))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag)
                    {
                        Long long2 = task.getArchivedTimeMs();
                        Long long6 = task1.getArchivedTimeMs();
                        if (long2 == long6 || long2 != null && long2.equals(long6))
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            Boolean boolean1 = task.getArchived();
                            Boolean boolean5 = task1.getArchived();
                            if (boolean1 == boolean5 || boolean1 != null && boolean1.equals(boolean5))
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (flag)
                            {
                                Boolean boolean2 = task.getDeleted();
                                Boolean boolean6 = task1.getDeleted();
                                if (boolean2 == boolean6 || boolean2 != null && boolean2.equals(boolean6))
                                {
                                    flag = true;
                                } else
                                {
                                    flag = false;
                                }
                                if (flag)
                                {
                                    Boolean boolean3 = task.getPinned();
                                    Boolean boolean7 = task1.getPinned();
                                    if (boolean3 == boolean7 || boolean3 != null && boolean3.equals(boolean7))
                                    {
                                        flag = true;
                                    } else
                                    {
                                        flag = false;
                                    }
                                    if (flag)
                                    {
                                        Boolean boolean4 = task.getSnoozed();
                                        Boolean boolean8 = task1.getSnoozed();
                                        if (boolean4 == boolean8 || boolean4 != null && boolean4.equals(boolean8))
                                        {
                                            flag = true;
                                        } else
                                        {
                                            flag = false;
                                        }
                                        if (flag)
                                        {
                                            Long long3 = task.getSnoozedTimeMillis();
                                            Long long7 = task1.getSnoozedTimeMillis();
                                            if (long3 == long7 || long3 != null && long3.equals(long7))
                                            {
                                                flag = true;
                                            } else
                                            {
                                                flag = false;
                                            }
                                            if (flag)
                                            {
                                                DateTime datetime = task.getDueDate();
                                                DateTime datetime2 = task1.getDueDate();
                                                if (datetime == datetime2 || datetime != null && datetime.equals(datetime2))
                                                {
                                                    flag = true;
                                                } else
                                                {
                                                    flag = false;
                                                }
                                                if (flag)
                                                {
                                                    DateTime datetime1 = task.getEventDate();
                                                    DateTime datetime3 = task1.getEventDate();
                                                    if (datetime1 == datetime3 || datetime1 != null && datetime1.equals(datetime3))
                                                    {
                                                        flag = true;
                                                    } else
                                                    {
                                                        flag = false;
                                                    }
                                                    if (flag)
                                                    {
                                                        Location location = task.getLocation();
                                                        Location location1 = task1.getLocation();
                                                        if (location == location1 || location != null && location.equals(location1))
                                                        {
                                                            flag = true;
                                                        } else
                                                        {
                                                            flag = false;
                                                        }
                                                        if (flag)
                                                        {
                                                            LocationGroup locationgroup = task.getLocationGroup();
                                                            LocationGroup locationgroup1 = task1.getLocationGroup();
                                                            if (locationgroup == locationgroup1 || locationgroup != null && locationgroup.equals(locationgroup1))
                                                            {
                                                                flag = true;
                                                            } else
                                                            {
                                                                flag = false;
                                                            }
                                                            if (flag)
                                                            {
                                                                Long long4 = task.getLocationSnoozedUntilMs();
                                                                Long long8 = task1.getLocationSnoozedUntilMs();
                                                                if (long4 == long8 || long4 != null && long4.equals(long8))
                                                                {
                                                                    flag = true;
                                                                } else
                                                                {
                                                                    flag = false;
                                                                }
                                                                if (flag)
                                                                {
                                                                    byte abyte0[] = task.getExtensions();
                                                                    byte abyte2[] = task1.getExtensions();
                                                                    if (abyte0 == abyte2 || abyte0 != null && abyte0.equals(abyte2))
                                                                    {
                                                                        flag = true;
                                                                    } else
                                                                    {
                                                                        flag = false;
                                                                    }
                                                                    if (flag)
                                                                    {
                                                                        RecurrenceInfo recurrenceinfo = task.getRecurrenceInfo();
                                                                        RecurrenceInfo recurrenceinfo1 = task1.getRecurrenceInfo();
                                                                        if (recurrenceinfo == recurrenceinfo1 || recurrenceinfo != null && recurrenceinfo.equals(recurrenceinfo1))
                                                                        {
                                                                            flag = true;
                                                                        } else
                                                                        {
                                                                            flag = false;
                                                                        }
                                                                        if (flag)
                                                                        {
                                                                            byte abyte1[] = task.getAssistance();
                                                                            byte abyte3[] = task1.getAssistance();
                                                                            if (abyte1 == abyte3 || abyte1 != null && abyte1.equals(abyte3))
                                                                            {
                                                                                flag = true;
                                                                            } else
                                                                            {
                                                                                flag = false;
                                                                            }
                                                                            if (flag)
                                                                            {
                                                                                Integer integer1 = task.getExperiment();
                                                                                Integer integer3 = task1.getExperiment();
                                                                                if (integer1 == integer3 || integer1 != null && integer1.equals(integer3))
                                                                                {
                                                                                    flag = true;
                                                                                } else
                                                                                {
                                                                                    flag = false;
                                                                                }
                                                                                if (flag)
                                                                                {
                                                                                    ExternalApplicationLink externalapplicationlink = task.getExternalApplicationLink();
                                                                                    ExternalApplicationLink externalapplicationlink1 = task1.getExternalApplicationLink();
                                                                                    if (externalapplicationlink == externalapplicationlink1 || externalapplicationlink != null && externalapplicationlink.equals(externalapplicationlink1))
                                                                                    {
                                                                                        flag = true;
                                                                                    } else
                                                                                    {
                                                                                        flag = false;
                                                                                    }
                                                                                    if (flag)
                                                                                    {
                                                                                        task = task.getFiredTimeMillis();
                                                                                        task1 = task1.getFiredTimeMillis();
                                                                                        if (task == task1 || task != null && task.equals(task1))
                                                                                        {
                                                                                            flag = true;
                                                                                        } else
                                                                                        {
                                                                                            flag = false;
                                                                                        }
                                                                                        if (flag)
                                                                                        {
                                                                                            return true;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static int zzc(Task task)
    {
        return Arrays.hashCode(new Object[] {
            task.getTaskId(), task.getTaskList(), task.getTitle(), task.getCreatedTimeMillis(), task.getArchivedTimeMs(), task.getArchived(), task.getDeleted(), task.getPinned(), task.getSnoozed(), task.getSnoozedTimeMillis(), 
            task.getDueDate(), task.getEventDate(), task.getLocation(), task.getLocationGroup(), task.getLocationSnoozedUntilMs(), task.getExtensions(), task.getRecurrenceInfo(), task.getAssistance(), task.getExperiment(), task.getExternalApplicationLink(), 
            task.getFiredTimeMillis()
        });
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Task))
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        } else
        {
            return zza(this, (Task)obj);
        }
    }

    public final Object freeze()
    {
        if (this == null)
        {
            throw null;
        } else
        {
            return this;
        }
    }

    public final Boolean getArchived()
    {
        return zzcjg;
    }

    public final Long getArchivedTimeMs()
    {
        return zzcjf;
    }

    public final byte[] getAssistance()
    {
        return zzcjs;
    }

    public final Long getCreatedTimeMillis()
    {
        return zzcje;
    }

    public final Boolean getDeleted()
    {
        return zzcjh;
    }

    public final DateTime getDueDate()
    {
        return zzcjw;
    }

    public final Long getDueDateMillis()
    {
        return zzcjD;
    }

    public final DateTime getEventDate()
    {
        return zzcjx;
    }

    public final Integer getExperiment()
    {
        return zzcjt;
    }

    public final byte[] getExtensions()
    {
        return zzcjq;
    }

    public final ExternalApplicationLink getExternalApplicationLink()
    {
        return zzcjB;
    }

    public final Long getFiredTimeMillis()
    {
        return zzcjC;
    }

    public final Location getLocation()
    {
        return zzcjy;
    }

    public final LocationGroup getLocationGroup()
    {
        return zzcjz;
    }

    public final Long getLocationSnoozedUntilMs()
    {
        return zzcjp;
    }

    public final Boolean getPinned()
    {
        return zzcji;
    }

    public final RecurrenceInfo getRecurrenceInfo()
    {
        return zzcjA;
    }

    public final Boolean getSnoozed()
    {
        return zzcjj;
    }

    public final Long getSnoozedTimeMillis()
    {
        return zzcjk;
    }

    public final TaskId getTaskId()
    {
        return zzcjv;
    }

    public final Integer getTaskList()
    {
        return zzcjd;
    }

    public final String getTitle()
    {
        return zzavt;
    }

    public int hashCode()
    {
        return zzc(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        boolean flag = true;
        parcel.writeInt(-45243);
        parcel.writeInt(0);
        int k = parcel.dataPosition();
        int j = mVersionCode;
        com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1, 4);
        parcel.writeInt(j);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 2, zzcjv, i, false);
        Object obj = zzcjd;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 3, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 4, zzavt, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 6, zzcjw, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 7, zzcjy, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 8, zzcjx, i, false);
        obj = zzcjg;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 9, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                j = 1;
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
        }
        obj = zzcjD;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 1001, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        obj = zzcjh;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 11, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                j = 1;
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
        }
        obj = zzcjf;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 12, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 13, zzcjz, i, false);
        obj = zzcjp;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 15, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 16, zzcjq, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 17, zzcjA, i, false);
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 18, zzcjs, false);
        obj = zzcje;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 19, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        obj = zzcjt;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 20, 4);
            parcel.writeInt(((Integer) (obj)).intValue());
        }
        obj = zzcji;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 22, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                j = 1;
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
        }
        obj = zzcjj;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 23, 4);
            if (((Boolean) (obj)).booleanValue())
            {
                j = ((flag) ? 1 : 0);
            } else
            {
                j = 0;
            }
            parcel.writeInt(j);
        }
        obj = zzcjk;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 24, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zza(parcel, 26, zzcjB, i, false);
        obj = zzcjC;
        if (obj != null)
        {
            com.google.android.gms.common.internal.safeparcel.zzc.zzb(parcel, 27, 8);
            parcel.writeLong(((Long) (obj)).longValue());
        }
        com.google.android.gms.common.internal.safeparcel.zzc.zzJ(parcel, k);
    }

}
