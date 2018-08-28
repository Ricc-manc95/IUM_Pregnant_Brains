// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.cp;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.apps.calendar.timebox.cp:
//            EventsQueryInfo

final class arg._cls5
    implements Callable
{

    private final Context arg$1;
    private final Uri arg$2;
    private final String arg$3;
    private final String arg$4[];
    private final String arg$5;

    public final Object call()
    {
        Context context = arg$1;
        Uri uri = arg$2;
        String s = arg$3;
        String as[] = arg$4;
        String s1 = arg$5;
        return context.getContentResolver().query(uri, EventsQueryInfo.PROJECTION, s, as, s1);
    }

    (Context context, Uri uri, String s, String as[], String s1)
    {
        arg$1 = context;
        arg$2 = uri;
        arg$3 = s;
        arg$4 = as;
        arg$5 = s1;
    }
}
