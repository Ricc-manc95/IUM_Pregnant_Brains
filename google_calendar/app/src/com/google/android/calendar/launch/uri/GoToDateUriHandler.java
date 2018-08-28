// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.uri;

import android.content.Intent;
import android.net.Uri;
import java.util.Arrays;
import java.util.Set;

// Referenced classes of package com.google.android.calendar.launch.uri:
//            BaseUriHandler

public final class GoToDateUriHandler extends BaseUriHandler
{

    public GoToDateUriHandler(Intent intent)
    {
        super(intent, Arrays.asList(new String[] {
            "http", "https"
        }), Arrays.asList(new String[] {
            "www.google.com", "calendar.google.com"
        }), Arrays.asList(new String[] {
            "/calendar/m?event.*", "/calendar/render.*"
        }));
    }

    public final boolean matches()
    {
        Set set;
        if (super.matches())
        {
            if (!(set = mIntent.getData().getQueryParameterNames()).contains("eid") && (set.contains("mode") || set.contains("date")))
            {
                return true;
            }
        }
        return false;
    }
}
