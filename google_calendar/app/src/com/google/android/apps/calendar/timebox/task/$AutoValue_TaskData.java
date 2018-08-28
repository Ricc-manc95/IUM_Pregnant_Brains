// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.task;

import com.google.android.apps.calendar.timebox.TimeRange;
import java.util.Arrays;

// Referenced classes of package com.google.android.apps.calendar.timebox.task:
//            TaskData

class $AutoValue_TaskData extends TaskData
{

    private final String accountName;
    private final Long archivedTime;
    private final int color;
    private final Long createdTime;
    private final boolean done;
    private final String id;
    private final Long originalDueTimeMillis;
    private final String recurrenceId;
    private final boolean recurringSometimeToday;
    private final byte taskAssistanceProtoBytesInternal[];
    private final TimeRange timeRange;
    private final String title;
    private final boolean unscheduled;

    $AutoValue_TaskData(String s, String s1, int i, String s2, boolean flag, boolean flag1, boolean flag2, 
            String s3, Long long1, Long long2, Long long3, TimeRange timerange, byte abyte0[])
    {
        if (s == null)
        {
            throw new NullPointerException("Null id");
        }
        id = s;
        if (s1 == null)
        {
            throw new NullPointerException("Null accountName");
        }
        accountName = s1;
        color = i;
        if (s2 == null)
        {
            throw new NullPointerException("Null title");
        }
        title = s2;
        done = flag;
        unscheduled = flag1;
        recurringSometimeToday = flag2;
        recurrenceId = s3;
        archivedTime = long1;
        createdTime = long2;
        originalDueTimeMillis = long3;
        if (timerange == null)
        {
            throw new NullPointerException("Null timeRange");
        } else
        {
            timeRange = timerange;
            taskAssistanceProtoBytesInternal = abyte0;
            return;
        }
    }

    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof TaskData)
        {
            obj = (TaskData)obj;
            if (id.equals(((TaskData) (obj)).getId()) && accountName.equals(((TaskData) (obj)).getAccountName()) && color == ((TaskData) (obj)).getColor() && title.equals(((TaskData) (obj)).getTitle()) && done == ((TaskData) (obj)).isDone() && unscheduled == ((TaskData) (obj)).isUnscheduled() && recurringSometimeToday == ((TaskData) (obj)).isRecurringSometimeToday() && (recurrenceId != null ? recurrenceId.equals(((TaskData) (obj)).getRecurrenceId()) : ((TaskData) (obj)).getRecurrenceId() == null) && (archivedTime != null ? archivedTime.equals(((TaskData) (obj)).getArchivedTime()) : ((TaskData) (obj)).getArchivedTime() == null) && (createdTime != null ? createdTime.equals(((TaskData) (obj)).getCreatedTime()) : ((TaskData) (obj)).getCreatedTime() == null) && (originalDueTimeMillis != null ? originalDueTimeMillis.equals(((TaskData) (obj)).getOriginalDueTimeMillis()) : ((TaskData) (obj)).getOriginalDueTimeMillis() == null) && timeRange.equals(((TaskData) (obj)).getTimeRange()))
            {
                byte abyte0[] = taskAssistanceProtoBytesInternal;
                if (obj instanceof $AutoValue_TaskData)
                {
                    obj = (($AutoValue_TaskData)obj).taskAssistanceProtoBytesInternal;
                } else
                {
                    obj = ((TaskData) (obj)).getTaskAssistanceProtoBytesInternal();
                }
                if (Arrays.equals(abyte0, ((byte []) (obj))))
                {
                    return true;
                }
            }
            return false;
        } else
        {
            return false;
        }
    }

    public final String getAccountName()
    {
        return accountName;
    }

    public final Long getArchivedTime()
    {
        return archivedTime;
    }

    public final int getColor()
    {
        return color;
    }

    public final Long getCreatedTime()
    {
        return createdTime;
    }

    public final String getId()
    {
        return id;
    }

    public final Long getOriginalDueTimeMillis()
    {
        return originalDueTimeMillis;
    }

    public final String getRecurrenceId()
    {
        return recurrenceId;
    }

    final byte[] getTaskAssistanceProtoBytesInternal()
    {
        return taskAssistanceProtoBytesInternal;
    }

    public final TimeRange getTimeRange()
    {
        return timeRange;
    }

    public final String getTitle()
    {
        return title;
    }

    public int hashCode()
    {
        char c2 = '\u04CF';
        int l = 0;
        int i1 = id.hashCode();
        int j1 = accountName.hashCode();
        int k1 = color;
        int l1 = title.hashCode();
        char c;
        char c1;
        int i;
        int j;
        int k;
        if (done)
        {
            c = '\u04CF';
        } else
        {
            c = '\u04D5';
        }
        if (unscheduled)
        {
            c1 = '\u04CF';
        } else
        {
            c1 = '\u04D5';
        }
        if (!recurringSometimeToday)
        {
            c2 = '\u04D5';
        }
        if (recurrenceId == null)
        {
            i = 0;
        } else
        {
            i = recurrenceId.hashCode();
        }
        if (archivedTime == null)
        {
            j = 0;
        } else
        {
            j = archivedTime.hashCode();
        }
        if (createdTime == null)
        {
            k = 0;
        } else
        {
            k = createdTime.hashCode();
        }
        if (originalDueTimeMillis != null)
        {
            l = originalDueTimeMillis.hashCode();
        }
        return (((k ^ (j ^ (i ^ ((c1 ^ (c ^ ((((i1 ^ 0xf4243) * 0xf4243 ^ j1) * 0xf4243 ^ k1) * 0xf4243 ^ l1) * 0xf4243) * 0xf4243) * 0xf4243 ^ c2) * 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ l) * 0xf4243 ^ timeRange.hashCode()) * 0xf4243 ^ Arrays.hashCode(taskAssistanceProtoBytesInternal);
    }

    public final boolean isDone()
    {
        return done;
    }

    public final boolean isRecurringSometimeToday()
    {
        return recurringSometimeToday;
    }

    public final boolean isUnscheduled()
    {
        return unscheduled;
    }

    public final TaskData.Builder toBuilder()
    {
        class Builder extends TaskData.Builder
        {

            private String accountName;
            private Long archivedTime;
            private Integer color;
            private Long createdTime;
            private Boolean done;
            private String id;
            private Long originalDueTimeMillis;
            private String recurrenceId;
            private Boolean recurringSometimeToday;
            private byte taskAssistanceProtoBytesInternal[];
            private TimeRange timeRange;
            private String title;
            private Boolean unscheduled;

            public final TaskData build()
            {
                String s1 = "";
                if (id == null)
                {
                    s1 = String.valueOf("").concat(" id");
                }
                String s = s1;
                if (accountName == null)
                {
                    s = String.valueOf(s1).concat(" accountName");
                }
                s1 = s;
                if (color == null)
                {
                    s1 = String.valueOf(s).concat(" color");
                }
                s = s1;
                if (title == null)
                {
                    s = String.valueOf(s1).concat(" title");
                }
                s1 = s;
                if (done == null)
                {
                    s1 = String.valueOf(s).concat(" done");
                }
                s = s1;
                if (unscheduled == null)
                {
                    s = String.valueOf(s1).concat(" unscheduled");
                }
                s1 = s;
                if (recurringSometimeToday == null)
                {
                    s1 = String.valueOf(s).concat(" recurringSometimeToday");
                }
                s = s1;
                if (timeRange == null)
                {
                    s = String.valueOf(s1).concat(" timeRange");
                }
                if (!s.isEmpty())
                {
                    s = String.valueOf(s);
                    if (s.length() != 0)
                    {
                        s = "Missing required properties:".concat(s);
                    } else
                    {
                        s = new String("Missing required properties:");
                    }
                    throw new IllegalStateException(s);
                } else
                {
                    return new AutoValue_TaskData(id, accountName, color.intValue(), title, done.booleanValue(), unscheduled.booleanValue(), recurringSometimeToday.booleanValue(), recurrenceId, archivedTime, createdTime, originalDueTimeMillis, timeRange, taskAssistanceProtoBytesInternal);
                }
            }

            public final TaskData.Builder setAccountName(String s)
            {
                if (s == null)
                {
                    throw new NullPointerException("Null accountName");
                } else
                {
                    accountName = s;
                    return this;
                }
            }

            public final TaskData.Builder setArchivedTime(Long long1)
            {
                archivedTime = long1;
                return this;
            }

            public final TaskData.Builder setColor(int i)
            {
                color = Integer.valueOf(i);
                return this;
            }

            public final TaskData.Builder setCreatedTime(Long long1)
            {
                createdTime = long1;
                return this;
            }

            public final TaskData.Builder setDone(boolean flag)
            {
                done = Boolean.valueOf(flag);
                return this;
            }

            public final TaskData.Builder setId(String s)
            {
                if (s == null)
                {
                    throw new NullPointerException("Null id");
                } else
                {
                    id = s;
                    return this;
                }
            }

            public final TaskData.Builder setOriginalDueTimeMillis(Long long1)
            {
                originalDueTimeMillis = long1;
                return this;
            }

            public final TaskData.Builder setRecurrenceId(String s)
            {
                recurrenceId = s;
                return this;
            }

            public final TaskData.Builder setRecurringSometimeToday(boolean flag)
            {
                recurringSometimeToday = Boolean.valueOf(flag);
                return this;
            }

            public final TaskData.Builder setTaskAssistanceProtoBytesInternal(byte abyte0[])
            {
                taskAssistanceProtoBytesInternal = abyte0;
                return this;
            }

            public final TaskData.Builder setTimeRange(TimeRange timerange)
            {
                if (timerange == null)
                {
                    throw new NullPointerException("Null timeRange");
                } else
                {
                    timeRange = timerange;
                    return this;
                }
            }

            public final TaskData.Builder setTitle(String s)
            {
                if (s == null)
                {
                    throw new NullPointerException("Null title");
                } else
                {
                    title = s;
                    return this;
                }
            }

            public final TaskData.Builder setUnscheduled(boolean flag)
            {
                unscheduled = Boolean.valueOf(flag);
                return this;
            }

            public Builder()
            {
            }

            Builder(TaskData taskdata)
            {
                id = taskdata.getId();
                accountName = taskdata.getAccountName();
                color = Integer.valueOf(taskdata.getColor());
                title = taskdata.getTitle();
                done = Boolean.valueOf(taskdata.isDone());
                unscheduled = Boolean.valueOf(taskdata.isUnscheduled());
                recurringSometimeToday = Boolean.valueOf(taskdata.isRecurringSometimeToday());
                recurrenceId = taskdata.getRecurrenceId();
                archivedTime = taskdata.getArchivedTime();
                createdTime = taskdata.getCreatedTime();
                originalDueTimeMillis = taskdata.getOriginalDueTimeMillis();
                timeRange = taskdata.getTimeRange();
                taskAssistanceProtoBytesInternal = taskdata.getTaskAssistanceProtoBytesInternal();
            }
        }

        return new Builder(this);
    }

    public String toString()
    {
        String s = id;
        String s1 = accountName;
        int i = color;
        String s2 = title;
        boolean flag = done;
        boolean flag1 = unscheduled;
        boolean flag2 = recurringSometimeToday;
        String s3 = recurrenceId;
        String s4 = String.valueOf(archivedTime);
        String s5 = String.valueOf(createdTime);
        String s6 = String.valueOf(originalDueTimeMillis);
        String s7 = String.valueOf(timeRange);
        String s8 = Arrays.toString(taskAssistanceProtoBytesInternal);
        return (new StringBuilder(String.valueOf(s).length() + 230 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length())).append("TaskData{id=").append(s).append(", accountName=").append(s1).append(", color=").append(i).append(", title=").append(s2).append(", done=").append(flag).append(", unscheduled=").append(flag1).append(", recurringSometimeToday=").append(flag2).append(", recurrenceId=").append(s3).append(", archivedTime=").append(s4).append(", createdTime=").append(s5).append(", originalDueTimeMillis=").append(s6).append(", timeRange=").append(s7).append(", taskAssistanceProtoBytesInternal=").append(s8).append("}").toString();
    }
}
