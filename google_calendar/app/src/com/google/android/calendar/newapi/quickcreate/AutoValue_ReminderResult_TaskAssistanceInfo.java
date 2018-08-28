// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate;

import java.util.Arrays;

final class AutoValue_ReminderResult_TaskAssistanceInfo extends ReminderResult.TaskAssistanceInfo
{

    private final String annotationHint;
    private final byte assistance[];
    private final String assistanceQuery;

    AutoValue_ReminderResult_TaskAssistanceInfo(String s, byte abyte0[], String s1)
    {
        if (s == null)
        {
            throw new NullPointerException("Null annotationHint");
        }
        annotationHint = s;
        assistance = abyte0;
        if (s1 == null)
        {
            throw new NullPointerException("Null assistanceQuery");
        } else
        {
            assistanceQuery = s1;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof ReminderResult.TaskAssistanceInfo)
        {
            ReminderResult.TaskAssistanceInfo taskassistanceinfo = (ReminderResult.TaskAssistanceInfo)obj;
            if (annotationHint.equals(taskassistanceinfo.getAnnotationHint()))
            {
                byte abyte0[] = assistance;
                if (taskassistanceinfo instanceof AutoValue_ReminderResult_TaskAssistanceInfo)
                {
                    obj = ((AutoValue_ReminderResult_TaskAssistanceInfo)taskassistanceinfo).assistance;
                } else
                {
                    obj = taskassistanceinfo.getAssistance();
                }
                if (Arrays.equals(abyte0, ((byte []) (obj))) && assistanceQuery.equals(taskassistanceinfo.getAssistanceQuery()))
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

    public final String getAnnotationHint()
    {
        return annotationHint;
    }

    public final byte[] getAssistance()
    {
        return assistance;
    }

    public final String getAssistanceQuery()
    {
        return assistanceQuery;
    }

    public final int hashCode()
    {
        return ((annotationHint.hashCode() ^ 0xf4243) * 0xf4243 ^ Arrays.hashCode(assistance)) * 0xf4243 ^ assistanceQuery.hashCode();
    }

    public final String toString()
    {
        String s = annotationHint;
        String s1 = Arrays.toString(assistance);
        String s2 = assistanceQuery;
        return (new StringBuilder(String.valueOf(s).length() + 66 + String.valueOf(s1).length() + String.valueOf(s2).length())).append("TaskAssistanceInfo{annotationHint=").append(s).append(", assistance=").append(s1).append(", assistanceQuery=").append(s2).append("}").toString();
    }
}
