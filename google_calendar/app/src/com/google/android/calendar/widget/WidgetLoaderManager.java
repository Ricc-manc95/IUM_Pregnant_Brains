// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.widget;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.widget:
//            WidgetEventLoader

final class WidgetLoaderManager
{

    public final WidgetEventLoader widgetEventLoader;

    WidgetLoaderManager(Context context)
    {
        LogUtils.d("CalendarWidget", "initLoader", new Object[0]);
        widgetEventLoader = new WidgetEventLoader(context);
    }
}
