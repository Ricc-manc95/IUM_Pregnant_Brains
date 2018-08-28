// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.usernotificationsframework.logging;

import android.content.Context;
import com.android.calendarcommon2.LogUtils;
import com.google.apps.xplat.logging.AndroidFileLogger;
import com.google.common.time.Clock;
import java.io.File;
import org.joda.time.Instant;

// Referenced classes of package com.google.android.apps.calendar.usernotificationsframework.logging:
//            NotificationLog

public final class arg._cls1
    implements Runnable
{

    private final Context arg$1;

    public final void run()
    {
        Object obj = arg$1;
        if (NotificationLog.fileLogger != null)
        {
            LogUtils.wtf(NotificationLog.TAG, "Attempt to call init method twice.", new Object[0]);
        } else
        {
            obj = new File(((Context) (obj)).getFilesDir(), "notification_logs");
            if ((((File) (obj)).exists() || ((File) (obj)).mkdirs()) && AndroidFileLogger.fileLoggingPossible(((File) (obj))))
            {
                obj = AndroidFileLogger.createAndroidFileLogger(((File) (obj)));
            } else
            {
                obj = null;
            }
            NotificationLog.fileLogger = ((AndroidFileLogger) (obj));
            if (obj != null)
            {
                obj = NotificationLog.fileLogger;
                long l = NotificationLog.LOG_FILES_MAX_AGE;
                long l1 = ((AndroidFileLogger) (obj)).clock.now().iMillis;
                AndroidFileLogger.deleteFiles(AndroidFileLogger.getFilesFromDirectory(((AndroidFileLogger) (obj)).logDirectory, l1 - l));
                return;
            }
        }
    }

    public (Context context)
    {
        arg$1 = context;
    }
}
