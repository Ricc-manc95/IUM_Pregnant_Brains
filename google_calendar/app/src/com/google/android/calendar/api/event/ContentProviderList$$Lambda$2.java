// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.database.Cursor;
import com.google.common.base.Function;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.api.event:
//            ContentProviderList

final class arg._cls1
    implements Function
{

    private final Map arg$1;

    public final Object apply(Object obj)
    {
        return ContentProviderList.lambda$getIcsEvents$2$ContentProviderList(arg$1, (Cursor)obj);
    }

    I(Map map)
    {
        arg$1 = map;
    }
}
