// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.app.Application;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.sampling.PrimesSampling;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.libraries.performance.primes.transmitter.StackTraceTransmitter;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.nano.MessageNano;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logs.proto.wireless.performance.mobile.nano.CrashMetric;
import logs.proto.wireless.performance.mobile.nano.PrimesStats;
import logs.proto.wireless.performance.mobile.nano.ProcessStats;
import logs.proto.wireless.performance.mobile.nano.SystemHealthMetric;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            AbstractMetricService, PrimesStartupListener, AppLifecycleMonitor, PrimesLog, 
//            NoPiiString, Hashing, MetricRecorder, Supplier, 
//            CrashMetricExtensionProvider, PrimesHprofFile

final class CrashMetricService extends AbstractMetricService
    implements PrimesStartupListener
{
    static interface ActivityTracker
        extends AppLifecycleListener.OnActivityStarted, AppLifecycleListener.OnAppToBackground
    {
    }

    final class PrimesUncaughtExceptionHandler
        implements Thread.UncaughtExceptionHandler
    {

        public final Thread.UncaughtExceptionHandler handlerToWrap;
        private final CrashMetricService this$0;

        public final void uncaughtException(Thread thread, Throwable throwable)
        {
            Object obj1;
            Object obj2;
            Object obj3;
            int i;
            i = 1;
            obj3 = null;
            obj2 = null;
            obj1 = null;
            Object obj;
            Object obj4;
            Object obj5;
            Object obj6;
            Object obj7;
            boolean flag;
            if (metricRecorder.instrumentationSampling.isSampleRateExceeded())
            {
                i = 0;
            }
            if (i == 0)
            {
                break MISSING_BLOCK_LABEL_1364;
            }
            if (!persistCrashStatsEnabled) goto _L2; else goto _L1
_L1:
            obj = StrictMode.allowThreadDiskWrites();
            obj3 = obj2;
            obj2 = application.openFileOutput("primes_crash", 0);
            obj1 = obj2;
            obj3 = obj2;
            obj4 = obj2;
            ((FileOutputStream) (obj2)).flush();
            obj1 = obj2;
_L4:
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj5 = createCrashMetric(thread.getName(), throwable);
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            flag = persistCrashStatsEnabled;
            if (!flag || obj1 == null)
            {
                break MISSING_BLOCK_LABEL_256;
            }
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            i = ((MessageNano) (obj5)).computeSerializedSize();
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj5.cachedSize = i;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj4 = new byte[i];
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            MessageNano.toByteArray(((MessageNano) (obj5)), ((byte []) (obj4)), 0, obj4.length);
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            ((FileOutputStream) (obj1)).write(((byte []) (obj4)));
_L8:
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj4 = new SystemHealthMetric();
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj4.crashMetric = ((CrashMetric) (obj5));
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj5 = metricExtensionProvider;
            if (obj5 == null)
            {
                break MISSING_BLOCK_LABEL_352;
            }
            obj2 = obj;
            obj3 = obj1;
            obj4.metricExtension = metricExtensionProvider.getMetricExtension();
_L11:
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj5 = CrashMetricService.this;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            if (!((CrashMetricService) (obj5)).deferPrimesStats.getAndSet(false))
            {
                break MISSING_BLOCK_LABEL_444;
            }
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            ((CrashMetricService) (obj5)).recordStartupEvent(2, ((CrashMetricService) (obj5)).deferredPrevCrash);
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            ((CrashMetricService) (obj5)).recordStartupEvent(3, null);
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            recordSystemHealthMetric(null, true, ((SystemHealthMetric) (obj4)), null);
            obj4 = obj;
            obj5 = obj1;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            if (shutdown)
            {
                break MISSING_BLOCK_LABEL_606;
            }
            obj4 = obj;
            obj5 = obj1;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            if (!sendStackTraces)
            {
                break MISSING_BLOCK_LABEL_606;
            }
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj4 = stackTraceTransmitter;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj5 = activeComponentName;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            ((StackTraceTransmitter) (obj4)).send$5166KOBMC4NMOOBECSNL8Q3IDTRM2OJCCKTKOORFDKNMERRFCTM6ABR1DPI74RR9CGNMOQB2E9GN4QB5ECNN0PBICPNN4RB1DPHMABRGE9KMQPBJ5T76UK39D59N8SJ9DPJJMAAM0();
            obj5 = obj1;
            obj4 = obj;
_L15:
            obj6 = obj4;
            obj7 = obj5;
            obj2 = obj4;
            obj3 = obj5;
            obj = PrimesHprofFile.getHprofFile(application);
            obj6 = obj4;
            obj7 = obj5;
            obj2 = obj4;
            obj3 = obj5;
            if (!((File) (obj)).exists())
            {
                break MISSING_BLOCK_LABEL_677;
            }
            obj6 = obj4;
            obj7 = obj5;
            obj2 = obj4;
            obj3 = obj5;
            ((File) (obj)).delete();
            obj6 = obj4;
            obj7 = obj5;
            obj2 = obj4;
            obj3 = obj5;
            obj = PrimesHprofFile.getMiniHeapDumpHprofFile(application);
            obj6 = obj4;
            obj7 = obj5;
            obj2 = obj4;
            obj3 = obj5;
            if (!((File) (obj)).exists())
            {
                break MISSING_BLOCK_LABEL_748;
            }
            obj6 = obj4;
            obj7 = obj5;
            obj2 = obj4;
            obj3 = obj5;
            ((File) (obj)).delete();
            Object aobj2[];
            if (obj5 != null)
            {
                try
                {
                    ((FileOutputStream) (obj5)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj)
                {
                    obj = "Could not close file.";
                    obj1 = ((Object) (new Object[0]));
                    if (Log.isLoggable("CrashMetricService", 5))
                    {
                        if (obj1.length != 0)
                        {
                            obj = String.format(Locale.US, "Could not close file.", ((Object []) (obj1)));
                        }
                        Log.println(5, "CrashMetricService", ((String) (obj)));
                    }
                }
            }
            if (obj4 != null)
            {
                StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj4)));
            }
            if (handlerToWrap != null)
            {
                handlerToWrap.uncaughtException(thread, throwable);
            }
            return;
            obj2;
            obj2 = "IO failure creating empty file.";
            obj3 = obj1;
            obj4 = obj1;
            obj5 = ((Object) (new Object[0]));
            obj3 = obj1;
            obj4 = obj1;
            if (!Log.isLoggable("CrashMetricService", 5)) goto _L4; else goto _L3
_L3:
            obj3 = obj1;
            obj4 = obj1;
            if (obj5.length != 0) goto _L6; else goto _L5
_L5:
            obj3 = obj1;
            obj4 = obj1;
            Log.println(5, "CrashMetricService", ((String) (obj2)));
              goto _L4
_L6:
            obj3 = obj1;
            obj4 = obj1;
            obj2 = String.format(Locale.US, "IO failure creating empty file.", ((Object []) (obj5)));
              goto _L5
            obj2;
            obj4 = "IO failure storing crash.";
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            aobj2 = new Object[0];
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            if (!Log.isLoggable("CrashMetricService", 5)) goto _L8; else goto _L7
_L7:
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            if (aobj2.length != 0) goto _L10; else goto _L9
_L9:
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            Log.println(5, "CrashMetricService", ((String) (obj4)));
              goto _L8
            obj4;
            obj1 = obj7;
            obj = obj6;
_L14:
            obj2 = obj;
            obj3 = obj1;
            PrimesLog.log(5, "CrashMetricService", ((Throwable) (obj4)), "Failed to record crash.", new Object[0]);
            if (obj1 != null)
            {
                try
                {
                    ((FileOutputStream) (obj1)).close();
                }
                // Misplaced declaration of an exception variable
                catch (Object obj1)
                {
                    obj1 = "Could not close file.";
                    Object aobj[] = new Object[0];
                    if (Log.isLoggable("CrashMetricService", 5))
                    {
                        if (aobj.length != 0)
                        {
                            obj1 = String.format(Locale.US, "Could not close file.", aobj);
                        }
                        Log.println(5, "CrashMetricService", ((String) (obj1)));
                    }
                }
            }
            if (obj != null)
            {
                StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj)));
            }
            if (handlerToWrap != null)
            {
                handlerToWrap.uncaughtException(thread, throwable);
                return;
            }
            break MISSING_BLOCK_LABEL_786;
_L10:
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            obj4 = String.format(Locale.US, "IO failure storing crash.", aobj2);
              goto _L9
            obj5;
            obj6 = obj;
            obj7 = obj1;
            obj2 = obj;
            obj3 = obj1;
            PrimesLog.log(5, "CrashMetricService", ((Throwable) (obj5)), "Exception while getting crash metric extension!", new Object[0]);
              goto _L11
            obj1;
            obj = obj2;
_L13:
            if (obj3 != null)
            {
                try
                {
                    ((FileOutputStream) (obj3)).close();
                }
                catch (IOException ioexception)
                {
                    String s = "Could not close file.";
                    Object aobj1[] = new Object[0];
                    if (Log.isLoggable("CrashMetricService", 5))
                    {
                        if (aobj1.length != 0)
                        {
                            s = String.format(Locale.US, "Could not close file.", aobj1);
                        }
                        Log.println(5, "CrashMetricService", s);
                    }
                }
            }
            if (obj != null)
            {
                StrictMode.setThreadPolicy(((android.os.StrictMode.ThreadPolicy) (obj)));
            }
            if (handlerToWrap != null)
            {
                handlerToWrap.uncaughtException(thread, throwable);
            }
            throw obj1;
            obj1;
            obj = null;
            continue; /* Loop/switch isn't completed */
            obj1;
            if (true) goto _L13; else goto _L12
_L12:
            obj4;
            obj1 = null;
            obj = null;
              goto _L14
            obj4;
            obj1 = null;
              goto _L14
            Exception exception;
            exception;
            obj1 = obj4;
            obj4 = exception;
              goto _L14
_L2:
            obj = null;
            obj1 = null;
              goto _L4
            obj4 = null;
            obj5 = null;
              goto _L15
        }

        PrimesUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtexceptionhandler)
        {
            this$0 = CrashMetricService.this;
            super();
            handlerToWrap = uncaughtexceptionhandler;
        }
    }


    public volatile NoPiiString activeComponentName;
    private volatile ActivityTracker activityNameTracker;
    private final AppLifecycleMonitor appLifecycleMonitor;
    public final AtomicBoolean deferPrimesStats;
    public volatile CrashMetric deferredPrevCrash;
    private final int estimatedCount;
    private final AtomicBoolean isPrimesExceptionHandlerDefaultHandler = new AtomicBoolean();
    public final CrashMetricExtensionProvider metricExtensionProvider;
    public final boolean persistCrashStatsEnabled;
    public final boolean sendStackTraces;
    private final boolean shouldSendStartupMetric;
    public final StackTraceTransmitter stackTraceTransmitter;

    CrashMetricService(MetricTransmitter metrictransmitter, CrashMetricExtensionProvider crashmetricextensionprovider, StackTraceTransmitter stacktracetransmitter, boolean flag, Supplier supplier, Supplier supplier1, Application application, 
            float f, boolean flag1, boolean flag2)
    {
        super(metrictransmitter, application, supplier, supplier1, android.support.v4.content.ModernAsyncTask.Status.SAME_THREAD$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFE1IN4PJFE9MM2RJ3CKNN0SJ9DLIN6BQDCLQ74QB3A9IM6RRICHIN492IELN4IRHR0);
        if (stacktracetransmitter == null)
        {
            throw new NullPointerException();
        }
        boolean flag3;
        if (f > 0.0F && f <= 100F)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (!flag3)
        {
            throw new IllegalArgumentException(String.valueOf("StartupSamplePercentage should be a floating number > 0 and <= 100."));
        }
        appLifecycleMonitor = AppLifecycleMonitor.getInstance(application);
        metrictransmitter = new ProbabilitySampler(f / 100F);
        boolean flag4;
        if (((ProbabilitySampler) (metrictransmitter)).samplingRate == 1.0F || ((ProbabilitySampler) (metrictransmitter)).random.nextFloat() <= ((ProbabilitySampler) (metrictransmitter)).samplingRate)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        shouldSendStartupMetric = flag4;
        estimatedCount = (int)(100F / f);
        metricExtensionProvider = crashmetricextensionprovider;
        stackTraceTransmitter = stacktracetransmitter;
        sendStackTraces = flag;
        deferPrimesStats = new AtomicBoolean(flag1);
        persistCrashStatsEnabled = flag2;
    }

    private final CrashMetric readAndClearStoredCrash()
    {
        File file;
        if (ThreadUtil.sMainThread == null)
        {
            ThreadUtil.sMainThread = Looper.getMainLooper().getThread();
        }
        boolean flag;
        if (Thread.currentThread() == ThreadUtil.sMainThread)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            throw new RuntimeException("Must be called on a background thread");
        }
        file = new File(super.application.getFilesDir(), "primes_crash");
        if (!file.exists())
        {
            break MISSING_BLOCK_LABEL_213;
        }
        Object obj = "found persisted crash";
        Object aobj1[] = new Object[0];
        if (!Log.isLoggable("CrashMetricService", 3)) goto _L2; else goto _L1
_L1:
        if (aobj1.length != 0)
        {
            break MISSING_BLOCK_LABEL_120;
        }
_L3:
        Log.println(3, "CrashMetricService", ((String) (obj)));
_L2:
        obj = new CrashMetric();
        if (readAndDeleteStoredCrash(file, ((CrashMetric) (obj))))
        {
            return ((CrashMetric) (obj));
        }
        break MISSING_BLOCK_LABEL_133;
        obj = String.format(Locale.US, "found persisted crash", aobj1);
          goto _L3
        obj = "could not delete crash file";
        Object aobj[];
        aobj = new Object[0];
        if (!Log.isLoggable("CrashMetricService", 5))
        {
            break MISSING_BLOCK_LABEL_213;
        }
        if (aobj.length != 0) goto _L5; else goto _L4
_L4:
        Log.println(5, "CrashMetricService", ((String) (obj)));
        break MISSING_BLOCK_LABEL_213;
_L5:
        obj = String.format(Locale.US, "could not delete crash file", aobj);
        if (true) goto _L4; else goto _L6
_L6:
        Object obj1;
        obj1;
        PrimesLog.log(3, "CrashMetricService", ((Throwable) (obj1)), "IO failure", new Object[0]);
        break MISSING_BLOCK_LABEL_213;
        obj1;
        PrimesLog.log(3, "CrashMetricService", ((Throwable) (obj1)), "Unexpected SecurityException", new Object[0]);
        return null;
    }

    private static boolean readAndDeleteStoredCrash(File file, CrashMetric crashmetric)
        throws IOException
    {
        int i = 0;
        long l = file.length();
        if (l <= 0L || l >= 0x7fffffffL) goto _L2; else goto _L1
_L1:
        int j = (int)l;
        FileInputStream fileinputstream;
        byte abyte0[];
        abyte0 = new byte[j];
        fileinputstream = new FileInputStream(file);
_L4:
        Object obj;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = fileinputstream;
        i += fileinputstream.read(abyte0, i, j - i);
        if (true) goto _L4; else goto _L3
_L3:
        obj = fileinputstream;
        MessageNano.mergeFrom(crashmetric, abyte0, 0, abyte0.length);
        crashmetric = fileinputstream;
_L6:
        obj = crashmetric;
        boolean flag = file.delete();
        if (crashmetric != null)
        {
            crashmetric.close();
        }
        return flag;
_L2:
        crashmetric.hasCrashed = Boolean.valueOf(true);
        crashmetric = null;
        if (true) goto _L6; else goto _L5
_L5:
        file;
        obj = null;
_L8:
        if (obj != null)
        {
            ((FileInputStream) (obj)).close();
        }
        throw file;
        file;
        if (true) goto _L8; else goto _L7
_L7:
    }

    final CrashMetric createCrashMetric(String s, Throwable throwable)
    {
        CrashMetric crashmetric;
        Object obj = null;
        boolean flag = true;
        crashmetric = new CrashMetric();
        NoPiiString nopiistring = activeComponentName;
        int i;
        if (nopiistring != null)
        {
            obj = nopiistring.toString();
        }
        crashmetric.activeComponentName = ((String) (obj));
        crashmetric.hasCrashed = Boolean.valueOf(true);
        crashmetric.threadName = s;
        s = throwable.getClass();
        if (s == java/lang/OutOfMemoryError)
        {
            i = 2;
        } else
        if (java/lang/NullPointerException.isAssignableFrom(s))
        {
            i = 1;
        } else
        if (java/lang/RuntimeException.isAssignableFrom(s))
        {
            i = 3;
        } else
        if (java/lang/Error.isAssignableFrom(s))
        {
            i = 4;
        } else
        {
            i = 0;
        }
        crashmetric.crashType = i;
        crashmetric.crashClassName = throwable.getClass().getName();
        s = new StringWriter();
        obj = new PrintWriter(s);
        ThrowableExtension.STRATEGY.printStackTrace(throwable, ((PrintWriter) (obj)));
        throwable = s.toString();
        s = new StringBuilder();
        throwable = Pattern.compile("([^:^\n]+).*((?:\n\\s*at [^:~\n]*:?~?[0-9]*[^\n]*)+)(?:(\nCaused by: )([^:^\n]+).*((?:\n\\s*at [^:~\n]*:?~?[0-9]*[^\n]*)+))?(?:(\nCaused by: )([^:^\n]+).*((?:\n\\s*at [^:~\n]*:?~?[0-9]*[^\n]*)+))?").matcher(throwable);
        if (!throwable.find()) goto _L2; else goto _L1
_L1:
        i = ((flag) ? 1 : 0);
_L3:
        if (i > throwable.groupCount() || throwable.group(i) == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s.append(throwable.group(i));
        i++;
        if (true) goto _L3; else goto _L2
_L2:
        crashmetric.hashedStackTrace = Hashing.hash(s.toString());
_L5:
        try
        {
            crashmetric.processStats = new ProcessStats();
            crashmetric.processStats.androidProcessStats = ProcessStatsCapture.getAndroidProcessStats(null, super.application);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            PrimesLog.log(5, "CrashMetricService", s, "Failed to get process stats.", new Object[0]);
            return crashmetric;
        }
        return crashmetric;
        s;
        s = String.valueOf(s);
        s = (new StringBuilder(String.valueOf(s).length() + 38)).append("Failed to generate hashed stack trace.").append(s).toString();
        throwable = ((Throwable) (new Object[0]));
        if (Log.isLoggable("CrashMetricService", 5))
        {
            if (throwable.length != 0)
            {
                s = String.format(Locale.US, s, throwable);
            }
            Log.println(5, "CrashMetricService", s);
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void onFirstActivityCreated()
    {
        boolean flag;
        String s = "onFirstActivityCreated";
        Object aobj[] = new Object[0];
        if (Log.isLoggable("CrashMetricService", 3))
        {
            if (aobj.length != 0)
            {
                s = String.format(Locale.US, "onFirstActivityCreated", aobj);
            }
            Log.println(3, "CrashMetricService", s);
        }
        if (deferPrimesStats.get()) goto _L2; else goto _L1
_L1:
        if (!super.metricRecorder.instrumentationSampling.isSampleRateExceeded())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || !shouldSendStartupMetric) goto _L4; else goto _L3
_L3:
        ((ScheduledExecutorService)super.executorServiceSupplier.get()).submit(new _cls2());
_L2:
        activityNameTracker = new _cls1();
        appLifecycleMonitor.register(activityNameTracker);
        return;
_L4:
        String s1 = "Startup metric for 'PRIMES_FIRST_ACTIVITY_LAUNCHED' dropped.";
        Object aobj1[] = new Object[0];
        if (Log.isLoggable("CrashMetricService", 4))
        {
            if (aobj1.length != 0)
            {
                s1 = String.format(Locale.US, "Startup metric for 'PRIMES_FIRST_ACTIVITY_LAUNCHED' dropped.", aobj1);
            }
            Log.println(4, "CrashMetricService", s1);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public final void onPrimesInitialize()
    {
        Object obj;
        Object aobj[];
        boolean flag;
        obj = "onPrimesInitialize";
        aobj = new Object[0];
        if (Log.isLoggable("CrashMetricService", 3))
        {
            if (aobj.length != 0)
            {
                obj = String.format(Locale.US, "onPrimesInitialize", aobj);
            }
            Log.println(3, "CrashMetricService", ((String) (obj)));
        }
        if (!persistCrashStatsEnabled) goto _L2; else goto _L1
_L1:
        obj = "persistent crash enabled.";
        aobj = new Object[0];
        if (Log.isLoggable("CrashMetricService", 3))
        {
            if (aobj.length != 0)
            {
                obj = String.format(Locale.US, "persistent crash enabled.", aobj);
            }
            Log.println(3, "CrashMetricService", ((String) (obj)));
        }
        obj = readAndClearStoredCrash();
_L10:
        if (deferPrimesStats.get()) goto _L4; else goto _L3
_L3:
        if (!super.metricRecorder.instrumentationSampling.isSampleRateExceeded())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag || obj == null && !shouldSendStartupMetric) goto _L6; else goto _L5
_L5:
        recordStartupEvent(2, ((CrashMetric) (obj)));
_L8:
        return;
        obj;
        PrimesLog.log(5, "CrashMetricService", ((Throwable) (obj)), "Unexpected failure: ", new Object[0]);
_L2:
        obj = null;
        continue; /* Loop/switch isn't completed */
_L6:
        obj = "Startup metric for 'PRIMES_CRASH_MONITORING_INITIALIZED' dropped.";
        aobj = new Object[0];
        if (!Log.isLoggable("CrashMetricService", 4)) goto _L8; else goto _L7
_L7:
        if (aobj.length != 0)
        {
            obj = String.format(Locale.US, "Startup metric for 'PRIMES_CRASH_MONITORING_INITIALIZED' dropped.", aobj);
        }
        Log.println(4, "CrashMetricService", ((String) (obj)));
        return;
_L4:
        deferredPrevCrash = ((CrashMetric) (obj));
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    final void recordStartupEvent(int i, CrashMetric crashmetric)
    {
        SystemHealthMetric systemhealthmetric = new SystemHealthMetric();
        systemhealthmetric.primesStats = new PrimesStats();
        systemhealthmetric.primesStats.estimatedCount = Integer.valueOf(estimatedCount);
        systemhealthmetric.primesStats.primesEvent = i;
        if (crashmetric != null)
        {
            systemhealthmetric.primesStats.primesDebugMessage = new logs.proto.wireless.performance.mobile.nano.PrimesStats.PrimesDebugMessage();
            systemhealthmetric.primesStats.primesDebugMessage.previousCrash = crashmetric;
        }
        recordSystemHealthMetric(null, true, systemhealthmetric, null);
    }

    final void setActiveComponentName(NoPiiString nopiistring)
    {
        String s;
        Object aobj[];
        if (nopiistring == null)
        {
            s = null;
        } else
        {
            s = nopiistring.toString();
        }
        s = String.valueOf(s);
        if (s.length() != 0)
        {
            s = "activeComponentName: ".concat(s);
        } else
        {
            s = new String("activeComponentName: ");
        }
        aobj = new Object[0];
        if (Log.isLoggable("CrashMetricService", 3))
        {
            if (aobj.length != 0)
            {
                s = String.format(Locale.US, s, aobj);
            }
            Log.println(3, "CrashMetricService", s);
        }
        activeComponentName = nopiistring;
    }

    final void shutdownService()
    {
        if (activityNameTracker != null)
        {
            appLifecycleMonitor.unregister(activityNameTracker);
            activityNameTracker = null;
        }
        if (isPrimesExceptionHandlerDefaultHandler.get() && (Thread.getDefaultUncaughtExceptionHandler() instanceof PrimesUncaughtExceptionHandler))
        {
            Thread.setDefaultUncaughtExceptionHandler(((PrimesUncaughtExceptionHandler)Thread.getDefaultUncaughtExceptionHandler()).handlerToWrap);
        }
    }

    private class _cls2
        implements Runnable
    {

        private final CrashMetricService this$0;

        public final void run()
        {
            recordStartupEvent(3, null);
        }

        _cls2()
        {
            this$0 = CrashMetricService.this;
            super();
        }
    }


    private class _cls1
        implements ActivityTracker
    {

        public final CrashMetricService this$0;

        public final void onActivityStarted(Activity activity)
        {
            CrashMetricService crashmetricservice = CrashMetricService.this;
            Object obj = activity.getClass();
            if (!TextUtils.isEmpty(null))
            {
                activity = String.valueOf(null);
                obj = String.valueOf(((Class) (obj)).getSimpleName());
                if (((String) (obj)).length() != 0)
                {
                    activity = activity.concat(((String) (obj)));
                } else
                {
                    activity = new String(activity);
                }
                activity = new NoPiiString(activity);
            } else
            {
                activity = new NoPiiString(((Class) (obj)).getSimpleName());
            }
            crashmetricservice.setActiveComponentName(activity);
        }

        public final void onAppToBackground(Activity activity)
        {
            setActiveComponentName(null);
            class _cls1
                implements Runnable
            {

                private final _cls1 this$1;

                public final void run()
                {
                    CrashMetricService crashmetricservice = _fld0;
                    if (crashmetricservice.deferPrimesStats.getAndSet(false))
                    {
                        crashmetricservice.recordStartupEvent(2, crashmetricservice.deferredPrevCrash);
                        crashmetricservice.recordStartupEvent(3, null);
                    }
                }

                _cls1()
                {
                    this$1 = _cls1.this;
                    super();
                }
            }

            if (deferPrimesStats.get())
            {
                ((ScheduledExecutorService)executorServiceSupplier.get()).submit(new _cls1());
            }
        }

        _cls1()
        {
            this$0 = CrashMetricService.this;
            super();
        }
    }

}
