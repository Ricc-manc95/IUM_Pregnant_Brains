// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.calendarcommon2;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.regex.Pattern;

public final class LogUtils
{

    private static boolean crashOnWtf = false;
    private static Boolean debugLoggingEnabledForTests = null;
    public static int maxEnabledLogLevel = 3;

    public static void checkStateOrWtf(String s, boolean flag)
    {
        if (!flag)
        {
            wtf(s, "Illegal state", new Object[0]);
        }
    }

    public static transient int d(String s, String s1, Object aobj[])
    {
        boolean flag;
        if (maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 3);
        }
        if (flag)
        {
            safeFormat(s1, aobj);
        }
        return 0;
    }

    public static transient int d(String s, Throwable throwable, String s1, Object aobj[])
    {
        boolean flag;
        if (maxEnabledLogLevel > 3)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 3))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 3);
        }
        if (flag)
        {
            safeFormat(s1, aobj);
        }
        return 0;
    }

    public static transient int e(String s, String s1, Object aobj[])
    {
        int j = 0;
        boolean flag;
        if (maxEnabledLogLevel > 6)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 6))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 6);
        }
        if (flag)
        {
            j = Log.e(s, safeFormat(s1, aobj));
        }
        return j;
    }

    public static transient int e(String s, Throwable throwable, String s1, Object aobj[])
    {
        int j = 0;
        boolean flag;
        if (maxEnabledLogLevel > 6)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 6))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 6);
        }
        if (flag)
        {
            j = Log.e(s, safeFormat(s1, aobj), throwable);
        }
        return j;
    }

    public static String getLogTag(Class class1)
    {
        String s = class1.getSimpleName();
        class1 = s;
        if (s.length() > 23)
        {
            class1 = s.substring(0, 23);
        }
        return class1;
    }

    public static transient int i(String s, String s1, Object aobj[])
    {
        boolean flag;
        if (maxEnabledLogLevel > 4)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 4))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 4);
        }
        if (flag)
        {
            safeFormat(s1, aobj);
        }
        return 0;
    }

    public static transient int i(String s, Throwable throwable, String s1, Object aobj[])
    {
        boolean flag;
        if (maxEnabledLogLevel > 4)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 4))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 4);
        }
        if (flag)
        {
            safeFormat(s1, aobj);
        }
        return 0;
    }

    public static boolean isLoggableFixed(String s, int j)
    {
        if (Log.isLoggable(s, j))
        {
            return true;
        } else
        {
            return Log.isLoggable(s, j);
        }
    }

    public static transient void logOnFailure(ListenableFuture listenablefuture, final String tag, final String format, final Object args[])
    {
        tag = new _cls3();
        format = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        if (tag == null)
        {
            throw new NullPointerException();
        } else
        {
            listenablefuture.addListener(new com.google.common.util.concurrent.Futures.CallbackListener(listenablefuture, tag), format);
            return;
        }
    }

    public static transient FutureCallback newFailureLoggingCallback(final Consumer consumer, final String tag, final String format, final Object args[])
    {
        return new _cls1();
    }

    public static transient FutureCallback newStrongFailureLoggingCallback(final Consumer consumer, final String tag, final String format, final Object args[])
    {
        return new _cls2();
    }

    public static transient String safeFormat(String s, Object aobj[])
    {
        String s1;
        try
        {
            s1 = String.format(s, aobj);
        }
        catch (Exception exception)
        {
            s = (new StringBuilder("<")).append(exception.getClass().getSimpleName()).append("> ").append(s).append(" <args: ");
            int k = aobj.length;
            for (int j = 0; j < k; j++)
            {
                Object obj = aobj[j];
                s.append("{").append(obj).append("}");
            }

            s.append(">");
            return s.toString();
        }
        return s1;
    }

    public static String sanitizeAccountName(String s, String s1)
    {
        if (TextUtils.isEmpty(s1))
        {
            return "";
        }
        String s2 = String.valueOf("account:");
        if (TextUtils.isEmpty(s1))
        {
            s = "";
        } else
        {
            if (maxEnabledLogLevel <= 2);
            s = String.valueOf(s1.hashCode());
        }
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            return s2.concat(s);
        } else
        {
            return new String(s2);
        }
    }

    public static String sanitizeName(String s, String s1)
    {
        if (TextUtils.isEmpty(s1))
        {
            return "";
        } else
        {
            if (maxEnabledLogLevel <= 2);
            return String.valueOf(s1.hashCode());
        }
    }

    public static transient int v(String s, String s1, Object aobj[])
    {
        boolean flag;
        if (maxEnabledLogLevel > 2)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 2))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 2);
        }
        if (flag)
        {
            safeFormat(s1, aobj);
        }
        return 0;
    }

    public static transient int v(String s, Throwable throwable, String s1, Object aobj[])
    {
        boolean flag;
        if (maxEnabledLogLevel > 2)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 2))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 2);
        }
        if (flag)
        {
            safeFormat(s1, aobj);
        }
        return 0;
    }

    public static transient int w(String s, String s1, Object aobj[])
    {
        int j = 0;
        boolean flag;
        if (maxEnabledLogLevel > 5)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 5))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 5);
        }
        if (flag)
        {
            j = Log.w(s, safeFormat(s1, aobj));
        }
        return j;
    }

    public static transient int w(String s, Throwable throwable, String s1, Object aobj[])
    {
        int j = 0;
        boolean flag;
        if (maxEnabledLogLevel > 5)
        {
            flag = false;
        } else
        if (Log.isLoggable(s, 5))
        {
            flag = true;
        } else
        {
            flag = Log.isLoggable(s, 5);
        }
        if (flag)
        {
            j = Log.w(s, safeFormat(s1, aobj), throwable);
        }
        return j;
    }

    public static transient int wtf(String s, String s1, Object aobj[])
    {
        int j = Log.wtf(s, safeFormat(s1, aobj), new Error());
        if (crashOnWtf)
        {
            throw new IllegalStateException(safeFormat(s1, aobj));
        } else
        {
            return j;
        }
    }

    public static transient int wtf(String s, Throwable throwable, String s1, Object aobj[])
    {
        return Log.wtf(s, safeFormat(s1, aobj), throwable);
    }

    static 
    {
        Pattern.compile("GMT([-+]\\d{4})$");
    }

    private class _cls3
        implements FutureCallback
    {

        private final Object val$args[];
        private final String val$format;
        private final String val$tag;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e(tag, throwable, format, args);
        }

        public final void onSuccess(Object obj)
        {
        }

        _cls3()
        {
            tag = s;
            format = s1;
            args = aobj;
            super();
        }
    }


    private class _cls1
        implements FutureCallback
    {

        private final Object val$args[];
        private final Consumer val$consumer;
        private final String val$format;
        private final String val$tag;

        public final void onFailure(Throwable throwable)
        {
            LogUtils.e(tag, throwable, format, args);
        }

        public final void onSuccess(Object obj)
        {
            consumer.accept(obj);
        }

        _cls1()
        {
            consumer = consumer1;
            tag = s;
            format = s1;
            args = aobj;
            super();
        }
    }


    private class _cls2
        implements FutureCallback
    {

        private final Object val$args[];
        private final Consumer val$consumer;
        private final String val$format;
        private final String val$tag;

        public final void onFailure(Throwable throwable)
        {
            if (!(throwable instanceof CancellationException))
            {
                LogUtils.e(tag, throwable, format, args);
            }
        }

        public final void onSuccess(Object obj)
        {
            consumer.accept(obj);
        }

        _cls2()
        {
            consumer = consumer1;
            tag = s;
            format = s1;
            args = aobj;
            super();
        }
    }

}
