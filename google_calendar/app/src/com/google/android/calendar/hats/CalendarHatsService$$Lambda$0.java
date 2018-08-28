// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.hats;

import android.content.Context;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.hats:
//            CalendarHatsService

public final class arg._cls2
    implements Consumer
{

    private final CalendarHatsService arg$1;
    private final Context arg$2;

    public final void accept(Object obj)
    {
        arg$1.downloadSurveyIfSupported(arg$2, ((Boolean)obj).booleanValue());
    }

    public (CalendarHatsService calendarhatsservice, Context context)
    {
        arg$1 = calendarhatsservice;
        arg$2 = context;
    }
}
