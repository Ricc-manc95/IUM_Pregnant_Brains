// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.net;

import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.calendar.timely.net:
//            Client

final class arg._cls2
    implements Callable
{

    private final Client arg$1;
    private final Object arg$2;

    public final Object call()
    {
        Client client = arg$1;
        Object obj = arg$2;
        return client.dataProvider.apply(obj);
    }

    er(Client client, Object obj)
    {
        arg$1 = client;
        arg$2 = obj;
    }
}
