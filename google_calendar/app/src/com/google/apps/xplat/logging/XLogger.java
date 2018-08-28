// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;


// Referenced classes of package com.google.apps.xplat.logging:
//            LoggerBackendConfig, LoggerBackend, WithLevelLoggingApi, NoOpLoggingApi, 
//            XLogLevel, LoggingApi

public final class XLogger
{

    private volatile LoggerBackend lastUsedLoggerBackend;
    private final Object lock = new Object();
    private volatile LoggerBackend.LoggerBackendApi loggerBackendApi;
    private final Class sourceClass;

    public XLogger(Class class1)
    {
        sourceClass = class1;
    }

    private final LoggerBackend.LoggerBackendApi getBackendApi()
    {
        LoggerBackend loggerbackend = LoggerBackendConfig.getBackend();
        if (loggerBackendApi == null || lastUsedLoggerBackend != loggerbackend)
        {
            synchronized (lock)
            {
                if (loggerBackendApi == null || lastUsedLoggerBackend != loggerbackend)
                {
                    lastUsedLoggerBackend = loggerbackend;
                    loggerBackendApi = lastUsedLoggerBackend.create(sourceClass);
                }
            }
        }
        return loggerBackendApi;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final LoggingApi getLoggingApi(XLogLevel xloglevel)
    {
        LoggerBackend.LoggerBackendApi loggerbackendapi = getBackendApi();
        if (loggerbackendapi.isLoggable(xloglevel))
        {
            return new WithLevelLoggingApi(loggerbackendapi, xloglevel);
        } else
        {
            return new NoOpLoggingApi();
        }
    }
}
