// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.common.base.Function;

// Referenced classes of package com.google.android.calendar.api.event:
//            Event, GooglePrivateData

public final class GooglePrivateDataHelper
{

    public static boolean hasEveryoneDeclined(Event event)
    {
        class .Lambda._cls1
            implements Function
        {

            public static final Function $instance = new .Lambda._cls1();

            public final Object apply(Object obj)
            {
                return Boolean.valueOf(((GooglePrivateData)obj).hasEveryoneDeclined());
            }


            private .Lambda._cls1()
            {
            }
        }

        Function function = .Lambda._cls1..instance;
        GooglePrivateData googleprivatedata = event.getGooglePrivateData();
        event = googleprivatedata;
        if (googleprivatedata == null)
        {
            event = GooglePrivateData.DEFAULT;
        }
        return ((Boolean)function.apply(event)).booleanValue();
    }

    public static boolean isEveryoneDeclinedDismissed(Event event)
    {
        class .Lambda._cls2
            implements Function
        {

            public static final Function $instance = new .Lambda._cls2();

            public final Object apply(Object obj)
            {
                return Boolean.valueOf(((GooglePrivateData)obj).isEveryoneDeclinedDismissed());
            }


            private .Lambda._cls2()
            {
            }
        }

        Function function = .Lambda._cls2..instance;
        GooglePrivateData googleprivatedata = event.getGooglePrivateData();
        event = googleprivatedata;
        if (googleprivatedata == null)
        {
            event = GooglePrivateData.DEFAULT;
        }
        return ((Boolean)function.apply(event)).booleanValue();
    }
}
