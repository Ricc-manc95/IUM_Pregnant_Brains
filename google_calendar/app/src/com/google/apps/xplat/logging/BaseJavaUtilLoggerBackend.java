// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import java.util.logging.Logger;

// Referenced classes of package com.google.apps.xplat.logging:
//            LoggerBackend, XLogLevel

public final class BaseJavaUtilLoggerBackend
    implements LoggerBackend
{

    public final XLogLevel minLoggingLevel;

    public BaseJavaUtilLoggerBackend()
    {
        minLoggingLevel = XLogLevel.VERBOSE;
    }

    public final LoggerBackend.LoggerBackendApi create(Class class1)
    {
        return new _cls1();
    }

    private class _cls1
        implements LoggerBackend.LoggerBackendApi
    {

        private final BaseJavaUtilLoggerBackend this$0;
        private final Logger val$logger;

        private final LogRecord makeRecord(Logger logger1, XLogLevel xloglevel, String s)
        {
            xloglevel = new LogRecord(LogUtils.LOGGING_LEVELS_BY_INDEX[xloglevel.ordinal()], s);
            xloglevel.setLoggerName(logger1.getName());
            return xloglevel;
        }

        public final boolean isLoggable(XLogLevel xloglevel)
        {
            return xloglevel.ordinal() >= minLoggingLevel.ordinal() && logger.isLoggable(LogUtils.LOGGING_LEVELS_BY_INDEX[xloglevel.ordinal()]);
        }

        public final void log(XLogLevel xloglevel, String s)
        {
            try
            {
                xloglevel = makeRecord(logger, xloglevel, s);
                logger.log(xloglevel);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (XLogLevel xloglevel)
            {
                logger.logp(Level.SEVERE, "com.google.apps.xplat.logging.BaseJavaUtilLoggerBackend$1", "log", "Logging failure", xloglevel);
            }
        }

        public final void log(XLogLevel xloglevel, String s, Throwable throwable)
        {
            try
            {
                xloglevel = makeRecord(logger, xloglevel, s);
                xloglevel.setThrown(throwable);
                logger.log(xloglevel);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (XLogLevel xloglevel)
            {
                logger.logp(Level.SEVERE, "com.google.apps.xplat.logging.BaseJavaUtilLoggerBackend$1", "log", "Logging failure", xloglevel);
            }
        }

        _cls1()
        {
            this$0 = BaseJavaUtilLoggerBackend.this;
            logger = logger1;
            super();
        }
    }

}
