// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.common;

import android.content.Context;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.common:
//            UserNotificationBroadcastReceiver

final class arg._cls2
    implements AsyncFunction
{

    private final Context arg$1;
    private final String arg$2;

    public final ListenableFuture apply(Object obj)
    {
        return UserNotificationBroadcastReceiver.lambda$onReceive$0$UserNotificationBroadcastReceiver$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FDHGMSPPFAPNMIP1R55666RRD5TJMURR7DHIIUORFDLMMURHFELQ6IR1FCDNMSORLE9P6ARJK5T66ISRKCLN62OJCCL37AT3LE9IJM___0(arg$1, arg$2);
    }

    (Context context, String s)
    {
        arg$1 = context;
        arg$2 = s;
    }
}
