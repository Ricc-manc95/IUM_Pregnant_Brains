// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;

import android.os.Process;
import android.os.StatFs;
import android.util.Log;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.time.Clock;
import com.google.common.time.SystemClock;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.joda.time.Instant;

// Referenced classes of package com.google.apps.xplat.logging:
//            AndroidAsyncFileWriter, XLogLevel, AsyncFileWriter

public class AndroidFileLogger
{

    private static final String TAG = com/google/apps/xplat/logging/AndroidFileLogger.getSimpleName();
    private AsyncFileWriter asyncWriter;
    public Clock clock;
    public final File logDirectory;

    private AndroidFileLogger(File file, AsyncFileWriter asyncfilewriter, Clock clock1)
    {
        logDirectory = file;
        asyncWriter = asyncfilewriter;
        clock = clock1;
    }

    public static AndroidFileLogger createAndroidFileLogger(File file)
    {
        boolean flag;
        if (!file.exists())
        {
            flag = file.mkdirs();
        } else
        if (file.isDirectory())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return null;
        }
        Object obj = new SystemClock();
        Object obj1 = new Date(((Clock) (obj)).now().iMillis);
        obj1 = String.format("%s.log", new Object[] {
            (new SimpleDateFormat("yyyyMMdd", Locale.US)).format(((Date) (obj1)))
        });
        AsyncFileWriter asyncfilewriter = AndroidAsyncFileWriter.createWriter(file, ((String) (obj1)));
        if (asyncfilewriter == null)
        {
            obj = TAG;
            file = String.valueOf(obj1);
            if (file.length() != 0)
            {
                file = "Error creating file writer for: ".concat(file);
            } else
            {
                file = new String("Error creating file writer for: ");
            }
            Log.e(((String) (obj)), file);
            return null;
        } else
        {
            return new AndroidFileLogger(file, asyncfilewriter, ((Clock) (obj)));
        }
    }

    public static void deleteFiles(ImmutableList immutablelist)
    {
        ImmutableList immutablelist1 = (ImmutableList)immutablelist;
        int k = immutablelist1.size();
        int i = 0;
        immutablelist = (UnmodifiableIterator)null;
        do
        {
            if (i >= k)
            {
                break;
            }
            immutablelist = ((ImmutableList) (immutablelist1.get(i)));
            int j = i + 1;
            immutablelist = (File)immutablelist;
            i = j;
            if (!immutablelist.delete())
            {
                String s = TAG;
                immutablelist = String.valueOf(immutablelist.getName());
                if (immutablelist.length() != 0)
                {
                    immutablelist = "Failed to remove old logfile: ".concat(immutablelist);
                } else
                {
                    immutablelist = new String("Failed to remove old logfile: ");
                }
                Log.w(s, immutablelist);
                i = j;
            }
        } while (true);
    }

    public static boolean fileLoggingPossible(File file)
    {
        file = new StatFs(file.getPath());
        long l = file.getBlockSizeLong();
        l = file.getAvailableBlocksLong() * l;
        if (l < 0x8000000L)
        {
            Log.w(TAG, (new StringBuilder(74)).append("Not enough free memory to enable logging: ").append(l).append(" < 134217728").toString());
            return false;
        } else
        {
            return true;
        }
    }

    public static ImmutableList getFilesFromDirectory(File file, long l)
    {
        boolean flag1 = false;
        boolean flag;
        if (l == -1L || l > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("invalid maxModificationTimeMs"));
        }
        if (!file.isDirectory() || !file.exists())
        {
            return ImmutableList.of();
        }
        file = file.listFiles();
        com.google.common.collect.ImmutableList.Builder builder = new com.google.common.collect.ImmutableList.Builder();
        for (int i = ((flag1) ? 1 : 0); i < file.length; i++)
        {
            Object obj = file[i];
            if (!((File) (obj)).isDirectory() && (l == -1L || ((File) (obj)).lastModified() < l))
            {
                obj = (com.google.common.collect.ImmutableList.Builder)builder.add(obj);
            }
        }

        builder.forceCopy = true;
        return ImmutableList.asImmutableList(builder.contents, builder.size);
    }

    public final void log(String s, XLogLevel xloglevel, String s1)
    {
        Object obj;
        int i;
        int j;
        if (asyncWriter == null)
        {
            throw new NullPointerException();
        }
        obj = new Date(clock.now().iMillis);
        obj = (new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US)).format(((Date) (obj)));
        i = Process.myPid();
        j = Process.myTid();
        xloglevel.ordinal();
        JVM INSTR tableswitch 0 4: default 104
    //                   0 168
    //                   1 175
    //                   2 182
    //                   3 189
    //                   4 196;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        char c = '?';
_L8:
        s = String.format("%1s %s %s %c %2s: %3s\r\n", new Object[] {
            obj, Integer.valueOf(i), Integer.valueOf(j), Character.valueOf(c), s, s1
        });
        asyncWriter.write(s);
        return;
_L2:
        c = 'V';
        continue; /* Loop/switch isn't completed */
_L3:
        c = 'D';
        continue; /* Loop/switch isn't completed */
_L4:
        c = 'I';
        continue; /* Loop/switch isn't completed */
_L5:
        c = 'W';
        continue; /* Loop/switch isn't completed */
_L6:
        c = 'E';
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void log(String s, XLogLevel xloglevel, String s1, Throwable throwable)
    {
        if (asyncWriter == null)
        {
            throw new NullPointerException();
        } else
        {
            log(s, xloglevel, s1);
            s = Throwables.getStackTraceAsString(throwable);
            asyncWriter.write(s);
            return;
        }
    }

    static 
    {
        TimeUnit.DAYS.toMillis(4L);
    }
}
