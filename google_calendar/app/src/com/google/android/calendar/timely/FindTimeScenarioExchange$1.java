// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely;

import android.content.Context;
import com.android.emailcommon.service.EmailServiceProxy;
import com.google.android.calendar.utils.exchange.ExchangeUtil;

final class oxyProvider
    implements oxyProvider
{

    public final EmailServiceProxy get(Context context)
    {
        return ExchangeUtil.getEasServiceProxy(context);
    }

    oxyProvider()
    {
    }
}
