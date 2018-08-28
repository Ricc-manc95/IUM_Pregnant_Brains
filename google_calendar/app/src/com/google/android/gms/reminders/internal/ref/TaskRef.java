// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.reminders.internal.ref;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.reminders.model.DateTime;
import com.google.android.gms.reminders.model.ExternalApplicationLink;
import com.google.android.gms.reminders.model.Location;
import com.google.android.gms.reminders.model.LocationGroup;
import com.google.android.gms.reminders.model.RecurrenceInfo;
import com.google.android.gms.reminders.model.Task;
import com.google.android.gms.reminders.model.TaskEntity;
import com.google.android.gms.reminders.model.TaskId;

// Referenced classes of package com.google.android.gms.reminders.internal.ref:
//            zzo, zze, zzf, zzi, 
//            zzg, zza, zzh, zzc, 
//            zzb, zzm, zzl, zzp

public class TaskRef extends zzo
    implements Task
{

    private zzh zzchA;
    private boolean zzchB;
    private zzl zzchC;
    private boolean zzchD;
    private zzf zzchE;
    private boolean zzchr;
    private zzp zzchs;
    private boolean zzcht;
    private zze zzchu;
    private boolean zzchv;
    private zze zzchw;
    private boolean zzchx;
    private zzi zzchy;
    private boolean zzchz;

    public TaskRef(DataHolder dataholder, int i)
    {
        this(dataholder, i, "");
    }

    private TaskRef(DataHolder dataholder, int i, String s)
    {
        super(dataholder, i, s);
        zzchr = false;
        zzcht = false;
        zzchv = false;
        zzchx = false;
        zzchz = false;
        zzchB = false;
        zzchD = false;
    }

    public int describeContents()
    {
        return 0;
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
            return TaskEntity.zza(this, (Task)obj);
        }
    }

    public final Object freeze()
    {
        return new TaskEntity(this);
    }

    public final Boolean getArchived()
    {
        return Boolean.valueOf(getBoolean(zziU("archived")));
    }

    public final Long getArchivedTimeMs()
    {
        String s = zziU("archived_time_ms");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final byte[] getAssistance()
    {
        return getByteArray(zziU("assistance"));
    }

    public final Long getCreatedTimeMillis()
    {
        String s = zziU("created_time_millis");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final Boolean getDeleted()
    {
        return Boolean.valueOf(getBoolean(zziU("deleted")));
    }

    public final DateTime getDueDate()
    {
        if (!zzcht)
        {
            zzcht = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("due_date_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zze.zza(dataholder, i, k, s))
            {
                zzchu = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("due_date_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzchu = new zze(dataholder1, j, s1);
            }
        }
        return zzchu;
    }

    public final Long getDueDateMillis()
    {
        String s = zziU("due_date_millis");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final DateTime getEventDate()
    {
        if (!zzchv)
        {
            zzchv = true;
            DataHolder dataholder = zzaKT;
            int i = zzaNQ;
            int k = zzaNR;
            String s = String.valueOf(zzchp);
            String s2 = String.valueOf("event_date_");
            if (s2.length() != 0)
            {
                s = s.concat(s2);
            } else
            {
                s = new String(s);
            }
            if (zze.zza(dataholder, i, k, s))
            {
                zzchw = null;
            } else
            {
                DataHolder dataholder1 = zzaKT;
                int j = zzaNQ;
                String s1 = String.valueOf(zzchp);
                String s3 = String.valueOf("event_date_");
                if (s3.length() != 0)
                {
                    s1 = s1.concat(s3);
                } else
                {
                    s1 = new String(s1);
                }
                zzchw = new zze(dataholder1, j, s1);
            }
        }
        return zzchw;
    }

    public final Integer getExperiment()
    {
        String s = zziU("experiment");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final byte[] getExtensions()
    {
        return getByteArray(zziU("extensions"));
    }

    public final ExternalApplicationLink getExternalApplicationLink()
    {
        boolean flag = true;
        if (zzchD) goto _L2; else goto _L1
_L1:
        zzchD = true;
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        String s = zzchp;
        String s1 = zzf.zzaw(s, "link_application");
        dataholder.zzj(s1, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            break MISSING_BLOCK_LABEL_126;
        }
        s = zzf.zzaw(s, "link_id");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_126;
        }
_L3:
        if (flag)
        {
            zzchE = null;
        } else
        {
            zzchE = new zzf(zzaKT, zzaNQ, zzchp);
        }
_L2:
        return zzchE;
        flag = false;
          goto _L3
    }

    public final Long getFiredTimeMillis()
    {
        String s = zziU("fired_time_millis");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final Location getLocation()
    {
        if (zzchx) goto _L2; else goto _L1
_L1:
        boolean flag;
        zzchx = true;
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        String s1 = zzchp;
        String s = zzi.zzaw(s1, "lat");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = zzi.zzaw(s1, "lng");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = zzi.zzaw(s1, "name");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = zzi.zzaw(s1, "radius_meters");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = zzi.zzaw(s1, "location_type");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = String.valueOf(s1);
        String s2 = String.valueOf("location_");
        if (s2.length() != 0)
        {
            s = s.concat(s2);
        } else
        {
            s = new String(s);
        }
        if (!zzg.zza(dataholder, i, j, s))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = zzi.zzaw(s1, "display_address");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = String.valueOf(s1);
        s2 = String.valueOf("address_");
        if (s2.length() != 0)
        {
            s = s.concat(s2);
        } else
        {
            s = new String(s);
        }
        if (!zza.zza(dataholder, i, j, s))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        s = zzi.zzaw(s1, "location_alias_id");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        flag = true;
_L3:
        if (flag)
        {
            zzchy = null;
        } else
        {
            zzchy = new zzi(zzaKT, zzaNQ, zzchp);
        }
_L2:
        return zzchy;
        flag = false;
          goto _L3
    }

    public final LocationGroup getLocationGroup()
    {
        boolean flag = true;
        if (zzchz) goto _L2; else goto _L1
_L1:
        zzchz = true;
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        String s = zzchp;
        String s1 = zzh.zzaw(s, "location_query");
        dataholder.zzj(s1, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            break MISSING_BLOCK_LABEL_152;
        }
        s1 = zzh.zzaw(s, "location_query_type");
        dataholder.zzj(s1, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)) || !zzc.zza(dataholder, i, j, s) || !zzb.zza(dataholder, i, j, s))
        {
            break MISSING_BLOCK_LABEL_152;
        }
_L3:
        if (flag)
        {
            zzchA = null;
        } else
        {
            zzchA = new zzh(zzaKT, zzaNQ, zzchp);
        }
_L2:
        return zzchA;
        flag = false;
          goto _L3
    }

    public final Long getLocationSnoozedUntilMs()
    {
        String s = zziU("location_snoozed_until_ms");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final Boolean getPinned()
    {
        return Boolean.valueOf(getBoolean(zziU("pinned")));
    }

    public final RecurrenceInfo getRecurrenceInfo()
    {
        boolean flag = true;
        if (zzchB) goto _L2; else goto _L1
_L1:
        zzchB = true;
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        String s = zzchp;
        if (!zzm.zza(dataholder, i, j, s))
        {
            break MISSING_BLOCK_LABEL_178;
        }
        String s1 = zzl.zzaw(s, "recurrence_id");
        dataholder.zzj(s1, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            break MISSING_BLOCK_LABEL_178;
        }
        s1 = zzl.zzaw(s, "recurrence_master");
        dataholder.zzj(s1, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            break MISSING_BLOCK_LABEL_178;
        }
        s = zzl.zzaw(s, "recurrence_exceptional");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_178;
        }
_L3:
        if (flag)
        {
            zzchC = null;
        } else
        {
            zzchC = new zzl(zzaKT, zzaNQ, zzchp);
        }
_L2:
        return zzchC;
        flag = false;
          goto _L3
    }

    public final Boolean getSnoozed()
    {
        return Boolean.valueOf(getBoolean(zziU("snoozed")));
    }

    public final Long getSnoozedTimeMillis()
    {
        String s = zziU("snoozed_time_millis");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Long.valueOf(getLong(s));
        }
    }

    public final TaskId getTaskId()
    {
        boolean flag = true;
        if (zzchr) goto _L2; else goto _L1
_L1:
        zzchr = true;
        DataHolder dataholder = zzaKT;
        int i = zzaNQ;
        int j = zzaNR;
        String s = zzchp;
        String s1 = zzp.zzaw(s, "client_assigned_id");
        dataholder.zzj(s1, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s1)))
        {
            break MISSING_BLOCK_LABEL_128;
        }
        s = zzp.zzaw(s, "client_assigned_thread_id");
        dataholder.zzj(s, i);
        if (!dataholder.zzaNW[j].isNull(i, dataholder.zzaNV.getInt(s)))
        {
            break MISSING_BLOCK_LABEL_128;
        }
_L3:
        if (flag)
        {
            zzchs = null;
        } else
        {
            zzchs = new zzp(zzaKT, zzaNQ, zzchp);
        }
_L2:
        return zzchs;
        flag = false;
          goto _L3
    }

    public final Integer getTaskList()
    {
        String s = zziU("task_list");
        if (zzcO(s))
        {
            return null;
        } else
        {
            return Integer.valueOf(getInteger(s));
        }
    }

    public final String getTitle()
    {
        return getString(zziU("title"));
    }

    public int hashCode()
    {
        return TaskEntity.zzc(this);
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        (new TaskEntity(this)).writeToParcel(parcel, i);
    }
}
