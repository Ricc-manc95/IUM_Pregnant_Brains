// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.calendar.AllInOneCalendarActivity;
import com.google.android.calendar.Utils;
import com.google.android.calendar.launch.LaunchIntentConstants;
import java.util.List;

// Referenced classes of package com.google.android.calendar.launch.uri:
//            BaseUriHandler

public final class CatchAllCalendarUriHandler extends BaseUriHandler
{

    public CatchAllCalendarUriHandler(Intent intent, List list, List list1, List list2)
    {
        super(intent, list, list1, list2);
    }

    public final void handle(Activity activity)
    {
        Intent intent = (Intent)mIntent.clone();
        if (LaunchIntentConstants.openCalendarAction == null)
        {
            LaunchIntentConstants.openCalendarAction = String.valueOf(activity.getPackageName()).concat(".OPEN_CALENDAR");
        }
        intent.setAction(LaunchIntentConstants.openCalendarAction);
        intent.setClass(activity, com/google/android/calendar/AllInOneCalendarActivity);
        Utils.setThirdPartySourceIfNone(intent);
        activity.startActivity(intent);
    }
}
