// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.habit;

import android.text.TextUtils;
import com.google.android.apps.calendar.goals.common.GoalStoreUtils;
import com.google.android.calendar.utils.habit.HabitInstancesUtil;

// Referenced classes of package com.google.android.calendar.api.habit:
//            HabitUtil

public final class HabitIdTypeUtil extends HabitInstancesUtil
{

    public static String createHabitIdTypeStringFromApiType(String s, int i)
    {
        i = GoalStoreUtils.apiTypeToProtoType(i);
        return (new StringBuilder(String.valueOf(s).length() + 12)).append(s).append(",").append(i).toString();
    }

    public static String[] parseHabitIdAndType(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            String as[] = s.split(",");
            s = as;
            if (as.length > 1)
            {
                as[1] = String.valueOf(GoalStoreUtils.protoTypeToApiType(Integer.parseInt(as[1])));
                return as;
            }
        }
        return s;
    }

    public static int parseHabitType(String s)
    {
        if (!TextUtils.isEmpty(s))
        {
            if ((s = parseHabitIdAndType(s)) != null && s.length >= 2)
            {
                int i;
                try
                {
                    i = HabitUtil.checkType(Integer.parseInt(s[1]));
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    return 0;
                }
                // Misplaced declaration of an exception variable
                catch (String s)
                {
                    return 0;
                }
                return i;
            }
        }
        return 0;
    }
}
