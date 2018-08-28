// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.flair;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Trace;
import android.util.DisplayMetrics;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.gms.common.util.IOUtils;
import com.google.protobuf.GeneratedMessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.List;
import java.util.Locale;

// Referenced classes of package com.google.android.apps.calendar.flair:
//            GrooveFlairAllocatorImpl, NoOpFlairAllocator, FlairAllocatorImpl, FlairAllocator, 
//            GrooveFlairAllocator

public class FlairAllocatorFactory
{

    public static final GrooveFlairAllocator GROOVE_ALLOCATOR = new GrooveFlairAllocatorImpl();
    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/flair/FlairAllocatorFactory);
    private static FlairAllocator allocator;
    public static Context applicationContext;
    public static String densityLabelDirectory;
    public static String flairUrl;

    public FlairAllocatorFactory()
    {
    }

    public static FlairAllocator getAllocator()
    {
        Object obj = null;
        com/google/android/apps/calendar/flair/FlairAllocatorFactory;
        JVM INSTR monitorenter ;
        if (applicationContext != null || allocator != null) goto _L2; else goto _L1
_L1:
        LogUtils.e(TAG, "Flair Allocator requested before context", new Object[0]);
_L8:
        com/google/android/apps/calendar/flair/FlairAllocatorFactory;
        JVM INSTR monitorexit ;
        return ((FlairAllocator) (obj));
_L2:
        obj = allocator;
        if (obj != null) goto _L4; else goto _L3
_L3:
        List list;
        final Locale locale;
        Trace.beginSection("FlairAllocatorFactory.initAllocator");
        locale = Locale.getDefault();
        list = readFlairDataForLocale(applicationContext, locale.toString());
        obj = list;
        if (list != null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        obj = readFlairDataForLocale(applicationContext, locale.getLanguage().toLowerCase());
        if (obj == null) goto _L6; else goto _L5
_L5:
        if (!((List) (obj)).isEmpty()) goto _L7; else goto _L6
_L6:
        allocator = new NoOpFlairAllocator();
_L9:
        int i = Math.min(Math.max(applicationContext.getResources().getDisplayMetrics().densityDpi, 160), 480);
        if (i <= 160)
        {
            obj = "mdpi";
        } else
        if (i <= 240)
        {
            obj = "hdpi";
        } else
        {
            if (i > 320)
            {
                continue; /* Loop/switch isn't completed */
            }
            obj = "xhdpi";
        }
_L12:
        densityLabelDirectory = ((String) (obj));
        applicationContext = null;
        Trace.endSection();
_L4:
        obj = allocator;
          goto _L8
_L7:
        allocator = new FlairAllocatorImpl(((List) (obj)), new _cls1());
          goto _L9
        obj;
        Trace.endSection();
        throw obj;
        obj;
        com/google/android/apps/calendar/flair/FlairAllocatorFactory;
        JVM INSTR monitorexit ;
        throw obj;
_L11:
        LogUtils.e(TAG, "Unknown density for flairs: %d", new Object[] {
            Integer.valueOf(i)
        });
        obj = "hdpi";
        break MISSING_BLOCK_LABEL_141;
        if (i > 640) goto _L11; else goto _L10
_L10:
        obj = "xxhdpi";
          goto _L12
    }

    public static String getAssistFlairUrlString(String s)
    {
        if (getAllocator() == null)
        {
            return null;
        } else
        {
            String s1 = flairUrl;
            String s2 = densityLabelDirectory;
            return (new StringBuilder(String.valueOf(s1).length() + 9 + String.valueOf(s2).length() + String.valueOf(s).length())).append(s1).append(s2).append("/img_").append(s).append(".png").toString();
        }
    }

    public static String getFlairUrlString(String s)
    {
        FlairAllocator flairallocator = getAllocator();
        if (flairallocator == null)
        {
            s = null;
        } else
        {
            s = flairallocator.allocateFlair(s);
        }
        return getFlairUrlStringFromKey(s);
    }

    public static String getFlairUrlStringFromKey(String s)
    {
        while (s == null || getAllocator() == null) 
        {
            return null;
        }
        String s1 = flairUrl;
        String s2 = densityLabelDirectory;
        return (new StringBuilder(String.valueOf(s1).length() + 9 + String.valueOf(s2).length() + String.valueOf(s).length())).append(s1).append(s2).append("/img_").append(s).append(".jpg").toString();
    }

    public static String getGrooveFlairUrlString(int i)
    {
        String s;
        if (getAllocator() != null)
        {
            if ((s = GROOVE_ALLOCATOR.allocateFlair(i)) != null)
            {
                String s1 = flairUrl;
                String s2 = densityLabelDirectory;
                return (new StringBuilder(String.valueOf(s1).length() + 9 + String.valueOf(s2).length() + String.valueOf(s).length())).append(s1).append(s2).append("/img_").append(s).append(".jpg").toString();
            }
        }
        return null;
    }

    public static void init()
    {
        class .Lambda._cls0
            implements Runnable
        {

            public static final Runnable $instance = new .Lambda._cls0();

            public final void run()
            {
                FlairAllocatorFactory.getAllocator();
            }


            private .Lambda._cls0()
            {
            }
        }

        CalendarExecutor.DISK.execute(.Lambda._cls0..instance);
    }

    public static boolean isFlairUrl(String s)
    {
        return s != null && s.startsWith("https://ssl.gstatic.com/tmly/f8944938hffheth4ew890ht4i8/flairs/");
    }

    private static List readFlairDataForLocale(Context context, String s)
    {
        s = String.format("flairs/flairdata_%s.pb", new Object[] {
            s
        });
        try
        {
            context = context.getAssets().open(s);
            s = new ByteArrayOutputStream();
            IOUtils.copyStream(context, s, true, 1024);
            context = s.toByteArray();
            context = ((com.google.calendar.v2.libs.proto.FlairProto.FlairList)GeneratedMessageLite.parseFrom(com.google.calendar.v2.libs.proto.FlairProto.FlairList.DEFAULT_INSTANCE, context)).flair_;
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            return null;
        }
        return context;
    }

    public static void setContext(Context context, boolean flag)
    {
        com/google/android/apps/calendar/flair/FlairAllocatorFactory;
        JVM INSTR monitorenter ;
        if (allocator != null)
        {
            LogUtils.i(TAG, "Allocator reset by .setContext", new Object[0]);
        }
        applicationContext = context.getApplicationContext();
        allocator = null;
        if (flag)
        {
            context = "https://ssl.gstatic.com/tmly/f8944938hffheth4ew890ht4i8/flairs/staging/";
        } else
        {
            context = "https://ssl.gstatic.com/tmly/f8944938hffheth4ew890ht4i8/flairs/";
        }
        flairUrl = context;
        com/google/android/apps/calendar/flair/FlairAllocatorFactory;
        JVM INSTR monitorexit ;
        return;
        context;
        throw context;
    }


    private class _cls1
        implements Function
    {

        private final Locale val$locale;
        private final BreakIterator val$wordIterator;

        private final String[] apply(String s)
        {
            String s1;
            ArrayList arraylist;
            if (TextUtils.isEmpty(s))
            {
                return new String[0];
            }
            s1 = s.toLowerCase(locale);
            arraylist = new ArrayList();
            s = wordIterator;
            s;
            JVM INSTR monitorenter ;
            int i;
            int j;
            wordIterator.setText(s1);
            j = wordIterator.first();
            i = wordIterator.next();
_L2:
            if (i == -1)
            {
                break; /* Loop/switch isn't completed */
            }
            int k;
            String s2 = s1.substring(j, i);
            if (!TextUtils.isEmpty(s2) && Character.isLetterOrDigit(s2.charAt(0)))
            {
                arraylist.add(s2);
            }
            k = wordIterator.next();
            j = i;
            i = k;
            if (true) goto _L2; else goto _L1
_L1:
            s;
            JVM INSTR monitorexit ;
            return (String[])arraylist.toArray(new String[arraylist.size()]);
            Exception exception;
            exception;
            s;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public final volatile Object apply(Object obj)
        {
            return apply((String)obj);
        }

        _cls1()
        {
            locale = locale1;
            wordIterator = breakiterator;
            super();
        }
    }

}
