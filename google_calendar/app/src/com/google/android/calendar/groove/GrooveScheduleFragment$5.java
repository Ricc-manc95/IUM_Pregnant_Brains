// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import com.android.calendarcommon2.LogUtils;
import com.android.volley.VolleyError;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment

final class A
    implements com.android.volley.eduleFragment._cls5
{

    public final void onErrorResponse(VolleyError volleyerror)
    {
        LogUtils.e(GrooveScheduleFragment.TAG, volleyerror, "Background request failed", new Object[0]);
    }

    A()
    {
    }
}
