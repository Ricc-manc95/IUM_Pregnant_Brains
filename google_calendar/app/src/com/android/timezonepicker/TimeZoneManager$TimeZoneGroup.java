// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.timezonepicker;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.android.timezonepicker:
//            TimeZoneManager, TimeZoneParams

static final class addId
{

    public List ids;
    public TimeZoneParams timeZone;

    final void addId(String s)
    {
        s = s.substring(s.lastIndexOf('/') + 1).replace('_', ' ');
        if (!s.toLowerCase().startsWith("gmt"))
        {
            ids.add(s);
        }
    }

    (TimeZoneParams timezoneparams)
    {
        ids = new ArrayList();
        timeZone = timezoneparams;
        addId(timezoneparams.getId());
    }
}
