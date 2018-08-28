// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import com.google.common.base.Strings;

// Referenced classes of package com.google.apps.xplat.logging:
//            LoggingApi, XLogLevel

class WithThrowableLoggingApi
    implements LoggingApi
{

    private final XLogLevel level;
    private final LoggerBackend.LoggerBackendApi loggerBackendApi;
    private final Throwable throwable;

    WithThrowableLoggingApi(LoggerBackend.LoggerBackendApi loggerbackendapi, XLogLevel xloglevel, Throwable throwable1)
    {
        loggerBackendApi = loggerbackendapi;
        level = xloglevel;
        throwable = throwable1;
    }

    public final void log(String s)
    {
        loggerBackendApi.log(level, s, throwable);
    }

    public final void log(String s, Object obj)
    {
        loggerBackendApi.log(level, Strings.lenientFormat(s, new Object[] {
            obj
        }), throwable);
    }

    public final void log(String s, Object obj, Object obj1)
    {
        loggerBackendApi.log(level, Strings.lenientFormat(s, new Object[] {
            obj, obj1
        }), throwable);
    }

    public final LoggingApi withCause(Throwable throwable1)
    {
        loggerBackendApi.log(XLogLevel.ERROR, "Duplicate cause", throwable1);
        return this;
    }
}
