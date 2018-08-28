// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.contract;

import android.net.Uri;

// Referenced classes of package com.google.android.apps.calendar.timely.contract:
//            TimelyContract

public final class 
{

    public static final Uri CONTENT_URI;

    static 
    {
        CONTENT_URI = Uri.withAppendedPath(TimelyContract.AUTHORITY_URI, "alerts");
    }
}
