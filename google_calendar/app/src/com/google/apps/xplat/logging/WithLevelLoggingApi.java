// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import com.google.common.base.Strings;

// Referenced classes of package com.google.apps.xplat.logging:
//            LoggingApi, WithThrowableLoggingApi, XLogLevel

class WithLevelLoggingApi
    implements LoggingApi
{

    private final XLogLevel level;
    private final LoggerBackend.LoggerBackendApi loggerBackendApi;

    WithLevelLoggingApi(LoggerBackend.LoggerBackendApi loggerbackendapi, XLogLevel xloglevel)
    {
        loggerBackendApi = loggerbackendapi;
        level = xloglevel;
    }

    public final void log(String s)
    {
        loggerBackendApi.log(level, s);
    }

    public final void log(String s, Object obj)
    {
        loggerBackendApi.log(level, Strings.lenientFormat(s, new Object[] {
            obj
        }));
    }

    public final void log(String s, Object obj, Object obj1)
    {
        loggerBackendApi.log(level, Strings.lenientFormat(s, new Object[] {
            obj, obj1
        }));
    }

    public final LoggingApi withCause(Throwable throwable)
    {
        return new WithThrowableLoggingApi(loggerBackendApi, level, throwable);
    }
}
