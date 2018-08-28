// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.android;

import android.os.Bundle;
import android.os.Parcel;
import android.util.SparseArray;
import com.android.calendarcommon2.LogUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BundleUtil
{
    static final class IndentingPrintWriter
    {

        public String indent;
        private final Writer writer;

        final void println(String s)
        {
            try
            {
                writer.append(indent);
                writer.append(" ");
                writer.append(s);
                writer.flush();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                ThrowableExtension.STRATEGY.printStackTrace(s);
            }
        }

        IndentingPrintWriter(Writer writer1)
        {
            indent = "--";
            writer = writer1;
        }
    }

    static interface Printer
    {

        public abstract void printStats(IndentingPrintWriter indentingprintwriter, Object obj);
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/apps/calendar/util/android/BundleUtil);
    private static final Map printers;

    public BundleUtil()
    {
    }

    private static void addPrinter(String s, Printer printer)
    {
        try
        {
            printers.put(Class.forName(s), printer);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Printer printer)
        {
            LogUtils.e(TAG, "Unable to create printer for %s", new Object[] {
                s
            });
        }
    }

    public static void dumpStats(Bundle bundle)
    {
        PrintWriter printwriter = new PrintWriter(System.err);
        try
        {
            ((Printer)printers.get(android/os/Bundle)).printStats(new IndentingPrintWriter(printwriter), bundle);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Bundle bundle)
        {
            LogUtils.e(TAG, bundle, "Error dumping stats", new Object[0]);
        }
    }

    private static void dumpStats(IndentingPrintWriter indentingprintwriter, String s, Object obj)
    {
        int i = getParcelSize(obj);
        if (i < 1024)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        String s1;
        if (obj == null)
        {
            s1 = "[null]";
        } else
        {
            s1 = obj.getClass().getName();
        }
        indentingprintwriter.println((new StringBuilder(String.valueOf(s).length() + 20 + String.valueOf(s1).length())).append(s).append(" [size=").append(i).append("] ").append(s1).toString());
        if (obj == null)
        {
            s = null;
        } else
        {
            s = (Printer)printers.get(obj.getClass());
        }
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        s.printStats(indentingprintwriter, obj);
        return;
        indentingprintwriter;
        LogUtils.e(TAG, indentingprintwriter, "Error printing stats", new Object[0]);
        return;
    }

    private static Object getDeclaredField(Object obj, String s)
    {
        try
        {
            s = obj.getClass().getDeclaredField(s);
            s.setAccessible(true);
            obj = s.get(obj);
        }
        // Misplaced declaration of an exception variable
        catch (Object obj)
        {
            throw new RuntimeException(((Throwable) (obj)));
        }
        return obj;
    }

    public static int getParcelSize(Object obj)
    {
        Parcel parcel = Parcel.obtain();
        int i;
        parcel.writeValue(obj);
        i = parcel.dataPosition();
        parcel.recycle();
        return i;
        obj;
        parcel.recycle();
        throw obj;
    }

    static final void lambda$static$0$BundleUtil(IndentingPrintWriter indentingprintwriter, Object obj)
    {
        String s = String.valueOf(indentingprintwriter.indent);
        String s1 = String.valueOf("--");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        indentingprintwriter.indent = s;
        obj = getDeclaredField(obj, "mActive");
        for (int i = 0; i < Array.getLength(obj); i++)
        {
            dumpStats(indentingprintwriter, (new StringBuilder(20)).append("mActive[").append(i).append("]").toString(), Array.get(obj, i));
        }

        indentingprintwriter.indent = indentingprintwriter.indent.substring(0, indentingprintwriter.indent.length() - 2);
    }

    static final void lambda$static$1$BundleUtil(IndentingPrintWriter indentingprintwriter, Object obj)
    {
        String s = String.valueOf(indentingprintwriter.indent);
        String s1 = String.valueOf("--");
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        indentingprintwriter.indent = s;
        s = String.valueOf(getDeclaredField(obj, "mClassName"));
        indentingprintwriter.println((new StringBuilder(String.valueOf(s).length() + 2)).append("(").append(s).append(")").toString());
        dumpStats(indentingprintwriter, "mArguments", getDeclaredField(obj, "mArguments"));
        dumpStats(indentingprintwriter, "mSavedFragmentState", getDeclaredField(obj, "mSavedFragmentState"));
        indentingprintwriter.indent = indentingprintwriter.indent.substring(0, indentingprintwriter.indent.length() - 2);
    }

    static final void lambda$static$2$BundleUtil(IndentingPrintWriter indentingprintwriter, Bundle bundle)
    {
        Object obj = String.valueOf(indentingprintwriter.indent);
        String s = String.valueOf("--");
        if (s.length() != 0)
        {
            obj = ((String) (obj)).concat(s);
        } else
        {
            obj = new String(((String) (obj)));
        }
        indentingprintwriter.indent = ((String) (obj));
        String s1;
        for (obj = bundle.keySet().iterator(); ((Iterator) (obj)).hasNext(); dumpStats(indentingprintwriter, s1, bundle.get(s1)))
        {
            s1 = (String)((Iterator) (obj)).next();
        }

        indentingprintwriter.indent = indentingprintwriter.indent.substring(0, indentingprintwriter.indent.length() - 2);
    }

    static final void lambda$static$3$BundleUtil(IndentingPrintWriter indentingprintwriter, SparseArray sparsearray)
    {
        String s = String.valueOf(indentingprintwriter.indent);
        String s1 = String.valueOf("--");
        int i;
        if (s1.length() != 0)
        {
            s = s.concat(s1);
        } else
        {
            s = new String(s);
        }
        indentingprintwriter.indent = s;
        i = 0;
        while (i < sparsearray.size()) 
        {
            s = String.valueOf(Integer.toHexString(sparsearray.keyAt(i)));
            if (s.length() != 0)
            {
                s = "0x".concat(s);
            } else
            {
                s = new String("0x");
            }
            dumpStats(indentingprintwriter, s, sparsearray.valueAt(i));
            i++;
        }
        indentingprintwriter.indent = indentingprintwriter.indent.substring(0, indentingprintwriter.indent.length() - 2);
    }

    static 
    {
        printers = new HashMap();
        class .Lambda._cls0
            implements Printer
        {

            public static final Printer $instance = new .Lambda._cls0();

            public final void printStats(IndentingPrintWriter indentingprintwriter, Object obj)
            {
                BundleUtil.lambda$static$0$BundleUtil(indentingprintwriter, obj);
            }


            private .Lambda._cls0()
            {
            }
        }

        addPrinter("android.support.v4.app.FragmentManagerState", .Lambda._cls0..instance);
        class .Lambda._cls1
            implements Printer
        {

            public static final Printer $instance = new .Lambda._cls1();

            public final void printStats(IndentingPrintWriter indentingprintwriter, Object obj)
            {
                BundleUtil.lambda$static$1$BundleUtil(indentingprintwriter, obj);
            }


            private .Lambda._cls1()
            {
            }
        }

        addPrinter("android.support.v4.app.FragmentState", .Lambda._cls1..instance);
        class .Lambda._cls2
            implements Printer
        {

            public static final Printer $instance = new .Lambda._cls2();

            public final void printStats(IndentingPrintWriter indentingprintwriter, Object obj)
            {
                BundleUtil.lambda$static$2$BundleUtil(indentingprintwriter, (Bundle)obj);
            }


            private .Lambda._cls2()
            {
            }
        }

        Printer printer = .Lambda._cls2..instance;
        printers.put(android/os/Bundle, printer);
        class .Lambda._cls3
            implements Printer
        {

            public static final Printer $instance = new .Lambda._cls3();

            public final void printStats(IndentingPrintWriter indentingprintwriter, Object obj)
            {
                BundleUtil.lambda$static$3$BundleUtil(indentingprintwriter, (SparseArray)obj);
            }


            private .Lambda._cls3()
            {
            }
        }

        printer = .Lambda._cls3..instance;
        printers.put(android/util/SparseArray, printer);
    }
}
