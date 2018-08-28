// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.android.calendar.event.LaunchInfoActivity;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.api.event.V2AEventKey;
import com.google.android.calendar.launch.LaunchIntentConstants;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.common.base.Optional;

public final class LaunchInfoActivityUtils
{

    public static Intent getLaunchFillInIntent(Context context, Object obj)
    {
        Intent intent = new Intent();
        Uri uri;
        if (obj instanceof CpEventKey)
        {
            uri = (Uri)((EventKey)obj).uri().orNull();
        } else
        if (obj instanceof V2AEventKey)
        {
            uri = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, 0L);
        } else
        if ((obj instanceof Long) && ((Long)obj).longValue() >= 0L)
        {
            uri = ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, ((Long)obj).longValue());
        } else
        {
            uri = null;
        }
        if (uri != null)
        {
            intent.setFlags(0x1000c000);
            if (LaunchIntentConstants.viewAction == null)
            {
                LaunchIntentConstants.viewAction = String.valueOf(context.getPackageName()).concat(".EVENT_VIEW");
            }
            intent.setAction(LaunchIntentConstants.viewAction);
            intent.setPackage(context.getPackageName());
            intent.setClass(context, com/android/calendar/event/LaunchInfoActivity);
            intent.setDataAndType(uri, "vnd.android.cursor.item/event");
            if (obj instanceof EventKey)
            {
                context = (EventKey)obj;
                obj = (new StringBuilder(context.getClass().getSimpleName())).append('|');
                context.serializeInternal(((StringBuilder) (obj)));
                intent.putExtra("eventkey", ((StringBuilder) (obj)).toString());
            }
            return intent;
        } else
        {
            intent.setClass(context, com/android/calendar/event/LaunchInfoActivity);
            intent.setData(android.provider.CalendarContract.Events.CONTENT_URI);
            intent.addCategory("android.intent.category.APP_CALENDAR");
            return intent;
        }
    }

    public static boolean redirectIfMandatoryPermissionsNotGranted(Activity activity)
    {
        if (!AndroidPermissionUtils.hasMandatoryPermissions(activity))
        {
            Log.w("CalUtils", "Mandatory Permissions not granted. Redirecting to LaunchInfoActivity");
            Intent intent = new Intent();
            intent.setClass(activity, com/android/calendar/event/LaunchInfoActivity);
            activity.startActivity(intent);
            activity.finish();
            return true;
        } else
        {
            return false;
        }
    }
}
