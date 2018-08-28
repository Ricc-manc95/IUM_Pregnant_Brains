// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.annotation;

import android.location.Location;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.annotation:
//            RequestFactory

final class arg._cls1
    implements Consumer
{

    private final RequestFactory arg$1;

    public final void accept(Object obj)
    {
        RequestFactory.lambda$create$0$RequestFactory(arg$1, (Location)obj);
    }

    (RequestFactory requestfactory)
    {
        arg$1 = requestfactory;
    }
}
