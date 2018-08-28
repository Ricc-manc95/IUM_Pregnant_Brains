// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.prefs;

import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.prefs:
//            PrefService

public final class arg._cls1
    implements Consumer
{

    private final PrefService arg$1;

    public final void accept(Object obj)
    {
        PrefService prefservice = arg$1;
        obj = prefservice.getPrimarySettings((Map)(ImmutableMap)obj);
        if (obj instanceof GoogleSettings)
        {
            prefservice.holidaysColor = ((GoogleSettings)obj).getHolidayColor();
        }
    }

    public (PrefService prefservice)
    {
        arg$1 = prefservice;
    }
}
