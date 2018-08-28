// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.android.calendarcommon2.LogUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnumConverter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/api/event/EnumConverter);

    public EnumConverter()
    {
    }

    public static int convertApiToProviderAvailability(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(38)).append("Invalid availability value ").append(i).toString());

        case 0: // '\0'
            return 0;

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;
        }
    }

    public static int convertHabitStoreFlagsToStatus(String s)
    {
        boolean flag = true;
        byte byte0 = flag;
        if (s != null)
        {
            int i;
            try
            {
                i = Integer.decode(s).intValue();
            }
            catch (NumberFormatException numberformatexception)
            {
                LogUtils.w(TAG, numberformatexception, "Unable to decode: %s", new Object[] {
                    s
                });
                return 1;
            }
            if ((i & 0x80) != 0)
            {
                byte0 = 3;
            } else
            {
                byte0 = flag;
                if ((i & 0x100) != 0)
                {
                    return 2;
                }
            }
        }
        return byte0;
    }

    public static List convertProviderToApiAvailabilities(int ai[])
    {
        HashSet hashset = new HashSet(ai.length);
        int i = 0;
        while (i < ai.length) 
        {
            try
            {
                hashset.add(Integer.valueOf(convertProviderToApiAvailabilityOrThrow(ai[i])));
            }
            catch (IllegalStateException illegalstateexception)
            {
                LogUtils.w(TAG, illegalstateexception, "Skipped invalid availability", new Object[0]);
            }
            i++;
        }
        return new ArrayList(hashset);
    }

    public static int convertProviderToApiAvailability(int i)
    {
        try
        {
            i = convertProviderToApiAvailabilityOrThrow(i);
        }
        catch (IllegalStateException illegalstateexception)
        {
            LogUtils.w(TAG, illegalstateexception, "Defaulted to tentative for invalid availability", new Object[0]);
            return 2;
        }
        return i;
    }

    private static int convertProviderToApiAvailabilityOrThrow(int i)
    {
        switch (i)
        {
        default:
            throw new IllegalStateException((new StringBuilder(38)).append("Invalid availability value ").append(i).toString());

        case 0: // '\0'
            return 0;

        case 1: // '\001'
            return 1;

        case 2: // '\002'
            return 2;
        }
    }

    static int convertProviderToApiStatus(int i)
    {
        switch (i)
        {
        default:
            LogUtils.w(TAG, "Defaulted to confirmed for invalid status: %d", new Object[] {
                Integer.valueOf(i)
            });
            // fall through

        case 1: // '\001'
            return 0;

        case 0: // '\0'
            return 1;

        case 2: // '\002'
            return 2;
        }
    }

    public static int convertStatusToHabitStoreFlags(int i)
    {
        switch (i)
        {
        default:
            return 0;

        case 3: // '\003'
            return 128;

        case 2: // '\002'
            return 256;
        }
    }

}
