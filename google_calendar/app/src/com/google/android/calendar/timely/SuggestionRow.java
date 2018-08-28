// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely:
//            TimelineSuggestion

public final class SuggestionRow
{

    public final int bucket;
    public final String headerName;
    public final boolean isBestTime;
    public final List omittedAttendees;
    public final TimelineSuggestion suggestion;
    public final int type;

    public SuggestionRow()
    {
        headerName = null;
        suggestion = null;
        isBestTime = false;
        omittedAttendees = null;
        type = 3;
        bucket = -1;
    }

    public SuggestionRow(TimelineSuggestion timelinesuggestion, boolean flag, int i)
    {
        headerName = null;
        suggestion = timelinesuggestion;
        isBestTime = flag;
        omittedAttendees = null;
        type = 0;
        bucket = i;
    }

    public SuggestionRow(String s, int i)
    {
        headerName = s;
        suggestion = null;
        isBestTime = false;
        omittedAttendees = null;
        type = 1;
        bucket = i;
    }

    public SuggestionRow(List list)
    {
        headerName = null;
        suggestion = null;
        isBestTime = false;
        omittedAttendees = new ArrayList(list);
        type = 2;
        bucket = -1;
    }

    public final boolean equals(Object obj)
    {
        if (obj instanceof SuggestionRow)
        {
            obj = (SuggestionRow)obj;
            String s = headerName;
            String s1 = ((SuggestionRow) (obj)).headerName;
            boolean flag;
            if (s == s1 || s != null && s.equals(s1))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                TimelineSuggestion timelinesuggestion = suggestion;
                TimelineSuggestion timelinesuggestion1 = ((SuggestionRow) (obj)).suggestion;
                if (timelinesuggestion == timelinesuggestion1 || timelinesuggestion != null && timelinesuggestion.equals(timelinesuggestion1))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag && isBestTime == ((SuggestionRow) (obj)).isBestTime)
                {
                    List list = omittedAttendees;
                    List list1 = ((SuggestionRow) (obj)).omittedAttendees;
                    if (list == list1 || list != null && list.equals(list1))
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && type == ((SuggestionRow) (obj)).type)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return Arrays.hashCode(new Object[] {
            headerName, suggestion, Boolean.valueOf(isBestTime), Integer.valueOf(type), omittedAttendees
        });
    }
}
