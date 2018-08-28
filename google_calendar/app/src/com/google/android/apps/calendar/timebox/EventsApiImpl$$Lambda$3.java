// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox;

import com.google.android.apps.calendar.util.function.BiFunction;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timebox:
//            EventsApiImpl

public final class 
    implements BiFunction
{

    public static final BiFunction $instance = new <init>();

    public final Object apply(Object obj, Object obj1)
    {
        return EventsApiImpl.lambda$searchAsync$3$EventsApiImpl((List)obj, (List)obj1);
    }


    private ()
    {
    }
}
