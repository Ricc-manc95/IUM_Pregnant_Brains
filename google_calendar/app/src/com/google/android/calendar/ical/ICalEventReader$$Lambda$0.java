// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.ical;

import android.content.ContentResolver;
import android.net.Uri;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.ical:
//            ICalEventReader

final class arg._cls3
    implements Callable
{

    private final ICalEventReader arg$1;
    private final ContentResolver arg$2;
    private final Uri arg$3;

    public final Object call()
    {
        return arg$1.lambda$parseUri$0$ICalEventReader(arg$2, arg$3);
    }

    (ICalEventReader icaleventreader, ContentResolver contentresolver, Uri uri)
    {
        arg$1 = icaleventreader;
        arg$2 = contentresolver;
        arg$3 = uri;
    }
}
