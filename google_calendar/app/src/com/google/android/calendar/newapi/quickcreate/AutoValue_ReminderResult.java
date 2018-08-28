// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;


// Referenced classes of package com.google.android.calendar.newapi.quickcreate:
//            ReminderResult

final class AutoValue_ReminderResult extends ReminderResult
{

    private final ReminderResult.TaskAssistanceInfo taskAssistance;
    private final String title;

    AutoValue_ReminderResult(String s, ReminderResult.TaskAssistanceInfo taskassistanceinfo)
    {
        if (s == null)
        {
            throw new NullPointerException("Null title");
        } else
        {
            title = s;
            taskAssistance = taskassistanceinfo;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (!(obj instanceof ReminderResult))
        {
            break MISSING_BLOCK_LABEL_65;
        }
        obj = (ReminderResult)obj;
        if (!title.equals(((ReminderResult) (obj)).getTitle()))
        {
            break; /* Loop/switch isn't completed */
        }
        if (taskAssistance != null) goto _L4; else goto _L3
_L3:
        if (((ReminderResult) (obj)).getTaskAssistance() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!taskAssistance.equals(((ReminderResult) (obj)).getTaskAssistance())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final ReminderResult.TaskAssistanceInfo getTaskAssistance()
    {
        return taskAssistance;
    }

    public final String getTitle()
    {
        return title;
    }

    public final int hashCode()
    {
        int j = title.hashCode();
        int i;
        if (taskAssistance == null)
        {
            i = 0;
        } else
        {
            i = taskAssistance.hashCode();
        }
        return i ^ 0xf4243 * (j ^ 0xf4243);
    }

    public final String toString()
    {
        String s = title;
        String s1 = String.valueOf(taskAssistance);
        return (new StringBuilder(String.valueOf(s).length() + 39 + String.valueOf(s1).length())).append("ReminderResult{title=").append(s).append(", taskAssistance=").append(s1).append("}").toString();
    }
}
