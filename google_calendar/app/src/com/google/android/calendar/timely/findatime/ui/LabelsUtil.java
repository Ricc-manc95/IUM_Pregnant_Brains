// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.findatime.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.Utils;
import com.google.calendar.v2.client.service.api.time.DateTime;
import com.google.calendar.v2.client.service.api.time.TimeZone;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.timely.findatime.ui:
//            DurationTimeframe

public class LabelsUtil
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/timely/findatime/ui/LabelsUtil);

    public LabelsUtil()
    {
    }

    static String getDateBasedTimeframeLabel(Fragment fragment, DurationTimeframe durationtimeframe, boolean flag)
    {
        return getDateBasedTimeframeLabel(fragment, durationtimeframe, false, true);
    }

    private static String getDateBasedTimeframeLabel(Fragment fragment, DurationTimeframe durationtimeframe, boolean flag, boolean flag1)
    {
        int i;
        String s;
        int j;
        long l;
        if (flag)
        {
            j = 0x10000;
        } else
        {
            j = 0;
        }
        l = durationtimeframe.customDate.getMillis();
        s = durationtimeframe.customDate.getTimeZone().getId();
        durationtimeframe.timeframeOption;
        JVM INSTR tableswitch 3 4: default 60
    //                   3 128
    //                   4 146;
           goto _L1 _L2 _L3
_L3:
        break MISSING_BLOCK_LABEL_146;
_L1:
        LogUtils.wtf(TAG, "Unexpected type of timeframe received!", new Object[0]);
        i = -1;
_L4:
        if (fragment.mHost == null)
        {
            durationtimeframe = null;
        } else
        {
            durationtimeframe = (FragmentActivity)fragment.mHost.mActivity;
        }
        durationtimeframe = Utils.tZUtils.formatDateRange(durationtimeframe, l, l, j, s);
        return fragment.requireContext().getResources().getString(i, new Object[] {
            durationtimeframe
        });
_L2:
        if (flag1)
        {
            i = 0x7f13021c;
        } else
        {
            i = 0x7f1301f2;
        }
          goto _L4
        if (flag1)
        {
            i = 0x7f13021a;
        } else
        {
            i = 0x7f1301f4;
        }
          goto _L4
    }

    private static String getDurationLabel(Resources resources, int i, boolean flag)
    {
        StringBuilder stringbuilder = new StringBuilder();
        int j = i / 60;
        int k = i % 60;
        if (j != 0)
        {
            String s;
            if (flag)
            {
                s = resources.getString(0x7f130201, new Object[] {
                    Integer.valueOf(j)
                });
            } else
            {
                s = resources.getQuantityString(0x7f120018, j, new Object[] {
                    Integer.valueOf(j)
                });
            }
            stringbuilder.append(s);
            if (k != 0)
            {
                stringbuilder.append(resources.getString(0x7f130202));
            }
        }
        if (i == 0 || k != 0)
        {
            if (flag)
            {
                resources = resources.getString(0x7f130203, new Object[] {
                    Integer.valueOf(k)
                });
            } else
            {
                resources = resources.getQuantityString(0x7f120019, k, new Object[] {
                    Integer.valueOf(k)
                });
            }
            stringbuilder.append(resources);
        }
        return stringbuilder.toString();
    }

    public static ArrayList getDurationLabels(Resources resources, ArrayList arraylist, boolean flag)
    {
        ArrayList arraylist1 = new ArrayList();
        for (int i = 0; i < arraylist.size(); i++)
        {
            arraylist1.add(getDurationLabel(resources, ((Integer)arraylist.get(i)).intValue(), false));
        }

        if (flag)
        {
            arraylist1.add(resources.getString(0x7f130200));
        }
        return arraylist1;
    }

    static String getTimeframeDurationLabel(Fragment fragment, DurationTimeframe durationtimeframe, boolean flag)
    {
        int i = durationtimeframe.timeframeOption;
        String s;
        boolean flag1;
        if (i == 3 || i == 4)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            s = getDateBasedTimeframeLabel(fragment, durationtimeframe, true, false);
        } else
        {
            s = (String)durationtimeframe.timeframeComposableWithDurationLabels.get(durationtimeframe.timeframeOption);
        }
        durationtimeframe = getDurationLabel(fragment.requireContext().getResources(), durationtimeframe.durationInMinutes, false);
        return fragment.requireContext().getResources().getString(0x7f1301f3, new Object[] {
            durationtimeframe, s
        });
    }

}
