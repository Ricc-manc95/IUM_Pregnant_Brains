// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.analytics.internal.Command;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package com.google.analytics.tracking.android:
//            AnalyticsThread, Utils, StandardExceptionParser, ExceptionParser, 
//            GAServiceProxy, ServiceProxy, MetaModel, MetaModelInitializer

final class GAThread extends Thread
    implements AnalyticsThread
{

    public static GAThread sInstance;
    public volatile boolean mAppOptOut;
    public volatile String mClientId;
    private volatile boolean mClosed;
    public volatile List mCommands;
    private final Context mContext;
    private volatile boolean mDisabled;
    public volatile String mInstallCampaign;
    public volatile MetaModel mMetaModel;
    public volatile ServiceProxy mServiceProxy;
    private final LinkedBlockingQueue queue = new LinkedBlockingQueue();

    GAThread(Context context)
    {
        super("GAThread");
        mDisabled = false;
        mClosed = false;
        if (context != null)
        {
            mContext = context.getApplicationContext();
        } else
        {
            mContext = context;
        }
        start();
    }

    private static String getAndClearCampaign(Context context)
    {
        Object obj;
        byte abyte0[];
        int i;
        obj = context.openFileInput("gaInstallData");
        abyte0 = new byte[8192];
        i = ((FileInputStream) (obj)).read(abyte0, 0, 8192);
        if (((FileInputStream) (obj)).available() <= 0)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Too much campaign data, ignoring it.").toString());
        ((FileInputStream) (obj)).close();
        context.deleteFile("gaInstallData");
        return null;
        String s;
        try
        {
            ((FileInputStream) (obj)).close();
            context.deleteFile("gaInstallData");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("No campaign data found.");
            return null;
        }
        catch (IOException ioexception)
        {
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Error reading campaign data.").toString());
            context.deleteFile("gaInstallData");
            return null;
        }
        if (i > 0)
        {
            break MISSING_BLOCK_LABEL_160;
        }
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Campaign file is empty.").toString());
        return null;
        obj = new String(abyte0, 0, i);
        s = (new StringBuilder("Campaign found: ")).append(((String) (obj))).toString();
        (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s);
        return ((String) (obj));
    }

    private final String initializeClientId()
    {
        Object obj1;
        Object obj2;
        Object obj3;
        obj2 = null;
        obj3 = null;
        obj1 = null;
        Object obj;
        FileInputStream fileinputstream;
        int i;
        fileinputstream = mContext.openFileInput("gaClientId");
        obj = new byte[128];
        i = fileinputstream.read(((byte []) (obj)), 0, 128);
        if (fileinputstream.available() > 0)
        {
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("clientId file seems corrupted, deleting it.").toString());
            fileinputstream.close();
            mContext.deleteFile("gaInstallData");
        }
        if (i > 0) goto _L2; else goto _L1
_L1:
        Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("clientId file seems empty, deleting it.").toString());
        fileinputstream.close();
        mContext.deleteFile("gaInstallData");
        obj = obj1;
_L8:
        obj1 = obj;
        if (obj == null)
        {
            obj = UUID.randomUUID().toString().toLowerCase();
            obj1 = obj;
            if (!storeClientId(((String) (obj))))
            {
                obj1 = "0";
            }
        }
        return ((String) (obj1));
_L2:
        obj = new String(((byte []) (obj)), 0, i);
        fileinputstream.close();
        continue; /* Loop/switch isn't completed */
        obj;
        obj = obj2;
_L6:
        Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Error reading clientId file, deleting it.").toString());
        mContext.deleteFile("gaInstallData");
        continue; /* Loop/switch isn't completed */
        obj;
        obj = obj3;
_L4:
        Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("cliendId file doesn't have long value, deleting it.").toString());
        mContext.deleteFile("gaInstallData");
        continue; /* Loop/switch isn't completed */
        obj1;
        if (true) goto _L4; else goto _L3
_L3:
        obj1;
        if (true) goto _L6; else goto _L5
_L5:
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        filenotfoundexception = ((FileNotFoundException) (obj1));
        continue; /* Loop/switch isn't completed */
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private static String printStackTrace(Throwable throwable)
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        PrintStream printstream = new PrintStream(bytearrayoutputstream);
        ThrowableExtension.printStackTrace(throwable, printstream);
        printstream.flush();
        return new String(bytearrayoutputstream.toByteArray());
    }

    private final boolean storeClientId(String s)
    {
        try
        {
            FileOutputStream fileoutputstream = mContext.openFileOutput("gaClientId", 0);
            fileoutputstream.write(s.getBytes());
            fileoutputstream.close();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Error creating clientId file.").toString());
            return false;
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Error writing to clientId file.").toString());
            return false;
        }
        return true;
    }

    public final void dispatch()
    {
        _cls2 _lcls2 = new _cls2();
        queue.add(_lcls2);
    }

    final void fillAppParameters(Map map)
    {
        Object obj;
        String s;
        String s1;
        String s3;
        obj = mContext.getPackageManager();
        s = mContext.getPackageName();
        s3 = ((PackageManager) (obj)).getInstallerPackageName(s);
        s1 = null;
        Object obj1 = ((PackageManager) (obj)).getPackageInfo(mContext.getPackageName(), 0);
        if (obj1 == null) goto _L2; else goto _L1
_L1:
        obj = ((PackageManager) (obj)).getApplicationLabel(((PackageInfo) (obj1)).applicationInfo).toString();
        obj1 = ((PackageInfo) (obj1)).versionName;
        s1 = ((String) (obj1));
_L5:
        if (!map.containsKey("appName"))
        {
            map.put("appName", obj);
        }
        if (!map.containsKey("appVersion"))
        {
            map.put("appVersion", s1);
        }
        if (!map.containsKey("appId"))
        {
            map.put("appId", s);
        }
        if (!map.containsKey("appInstallerId"))
        {
            map.put("appInstallerId", s3);
        }
        map.put("apiVersion", "1");
        return;
        obj;
        obj = s;
_L3:
        String s2 = (new StringBuilder("Error retrieving package info: appName set to ")).append(((String) (obj))).toString();
        Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s2).toString());
        continue; /* Loop/switch isn't completed */
        android.content.pm.PackageManager.NameNotFoundException namenotfoundexception;
        namenotfoundexception;
        if (true) goto _L3; else goto _L2
_L2:
        obj = s;
        if (true) goto _L5; else goto _L4
_L4:
    }

    final void fillExceptionParameters(Map map)
    {
        Object obj = (String)map.get("rawException");
        if (obj != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        map.remove("rawException");
        obj = new ByteArrayInputStream(Utils.hexDecode(((String) (obj))));
        Object obj1;
        obj = new ObjectInputStream(((java.io.InputStream) (obj)));
        obj1 = ((ObjectInputStream) (obj)).readObject();
        ((ObjectInputStream) (obj)).close();
        if (!(obj1 instanceof Throwable)) goto _L1; else goto _L3
_L3:
        obj = (Throwable)obj1;
        ArrayList arraylist = new ArrayList();
        map.put("exDescription", (new StandardExceptionParser(mContext, arraylist)).getDescription((String)map.get("exceptionThreadName"), ((Throwable) (obj))));
        return;
        map;
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("IOException reading exception").toString());
        return;
        map;
        Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("ClassNotFoundException reading exception").toString());
        return;
    }

    public final LinkedBlockingQueue getQueue()
    {
        return queue;
    }

    public final Thread getThread()
    {
        return this;
    }

    public final void requestAppOptOut(final GoogleAnalytics.AppOptOutCallback callback)
    {
        callback = new _cls4();
        queue.add(callback);
    }

    public final void requestClientId(final AnalyticsThread.ClientIdCallback callback)
    {
        callback = new _cls5();
        queue.add(callback);
    }

    public final void run()
    {
        Object obj;
        Object obj1;
        boolean flag;
        try
        {
            Thread.sleep(5000L);
        }
        catch (InterruptedException interruptedexception)
        {
            Log.w("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("sleep interrupted in GAThread initialize").toString());
        }
        try
        {
            if (mServiceProxy == null)
            {
                mServiceProxy = new GAServiceProxy(mContext, this);
            }
            mServiceProxy.createService();
            mCommands = new ArrayList();
            mCommands.add(new Command("appendVersion", "_v", "ma1b6"));
            mCommands.add(new Command("appendQueueTime", "qt", null));
            mCommands.add(new Command("appendCacheBuster", "z", null));
            mMetaModel = new MetaModel();
            MetaModelInitializer.set(mMetaModel);
            mAppOptOut = mContext.getFileStreamPath("gaOptOut").exists();
            mClientId = initializeClientId();
            mInstallCampaign = getAndClearCampaign(mContext);
        }
        catch (Throwable throwable)
        {
            String s = (new StringBuilder("Error initializing the GAThread: ")).append(printStackTrace(throwable)).toString();
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(s).toString());
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Google Analytics will not start up.").toString());
            mDisabled = true;
        }
        flag = mClosed;
        obj = (Runnable)queue.take();
        if (!mDisabled)
        {
            ((Runnable) (obj)).run();
        }
        continue; /* Loop/switch isn't completed */
        obj1;
        try
        {
            obj1 = ((InterruptedException) (obj1)).toString();
            (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(((String) (obj1)));
        }
        // Misplaced declaration of an exception variable
        catch (Object obj1)
        {
            obj1 = (new StringBuilder("Error on GAThread: ")).append(printStackTrace(((Throwable) (obj1)))).toString();
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append(((String) (obj1))).toString());
            Log.e("GAV2", (new StringBuilder()).append(Thread.currentThread().toString()).append(": ").append("Google Analytics is shutting down.").toString());
            mDisabled = true;
        }
        if (true) goto _L2; else goto _L1
_L1:
        break MISSING_BLOCK_LABEL_343;
_L2:
        break MISSING_BLOCK_LABEL_177;
    }

    public final void sendHit(final Map hitCopy)
    {
        hitCopy = new HashMap(hitCopy);
        final long hitTime = System.currentTimeMillis();
        hitCopy.put("hitTime", Long.toString(hitTime));
        hitCopy = new _cls1();
        queue.add(hitCopy);
    }

    private class _cls2
        implements Runnable
    {

        private final GAThread this$0;

        public final void run()
        {
            mServiceProxy.dispatch();
        }

        _cls2()
        {
            this$0 = GAThread.this;
            super();
        }
    }


    private class _cls4
        implements Runnable
    {

        private final GAThread this$0;
        private final GoogleAnalytics.AppOptOutCallback val$callback;

        public final void run()
        {
            callback.reportAppOptOut(mAppOptOut);
        }

        _cls4()
        {
            this$0 = GAThread.this;
            callback = appoptoutcallback;
            super();
        }
    }


    private class _cls5
        implements Runnable
    {

        private final GAThread this$0;
        private final AnalyticsThread.ClientIdCallback val$callback;

        public final void run()
        {
            callback.reportClientId(mClientId);
        }

        _cls5()
        {
            this$0 = GAThread.this;
            callback = clientidcallback;
            super();
        }
    }


    private class _cls1
        implements Runnable
    {

        private final GAThread this$0;
        private final Map val$hitCopy;
        private final long val$hitTime;

        public final void run()
        {
            hitCopy.put("clientId", mClientId);
            if (mAppOptOut) goto _L2; else goto _L1
_L1:
            Object obj;
            obj = GAThread.this;
            obj = hitCopy;
            if (((Map) (obj)).get("sampleRate") == null) goto _L4; else goto _L3
_L3:
            double d = Utils.safeParseDouble((String)((Map) (obj)).get("sampleRate"));
            if (d > 0.0D) goto _L6; else goto _L5
_L5:
            boolean flag = true;
_L8:
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
_L2:
            return;
_L6:
            if (d < 100D)
            {
                obj = (String)((Map) (obj)).get("clientId");
                if (obj != null && (double)(Math.abs(((String) (obj)).hashCode()) % 10000) >= d * 100D)
                {
                    flag = true;
                    continue; /* Loop/switch isn't completed */
                }
            }
_L4:
            flag = false;
            if (true) goto _L8; else goto _L7
_L7:
            if (!TextUtils.isEmpty(mInstallCampaign))
            {
                hitCopy.put("campaign", mInstallCampaign);
                mInstallCampaign = null;
            }
            fillAppParameters(hitCopy);
            Object obj1 = GAThread.this;
            obj1 = hitCopy;
            Object obj2 = Utils.filterCampaign((String)((Map) (obj1)).get("campaign"));
            if (!TextUtils.isEmpty(((CharSequence) (obj2))))
            {
                obj2 = Utils.parseURLParameters(((String) (obj2)));
                ((Map) (obj1)).put("campaignContent", ((Map) (obj2)).get("utm_content"));
                ((Map) (obj1)).put("campaignMedium", ((Map) (obj2)).get("utm_medium"));
                ((Map) (obj1)).put("campaignName", ((Map) (obj2)).get("utm_campaign"));
                ((Map) (obj1)).put("campaignSource", ((Map) (obj2)).get("utm_source"));
                ((Map) (obj1)).put("campaignKeyword", ((Map) (obj2)).get("utm_term"));
                ((Map) (obj1)).put("campaignId", ((Map) (obj2)).get("utm_id"));
                ((Map) (obj1)).put("gclid", ((Map) (obj2)).get("gclid"));
                ((Map) (obj1)).put("dclid", ((Map) (obj2)).get("dclid"));
                ((Map) (obj1)).put("gmob_t", ((Map) (obj2)).get("gmob_t"));
            }
            fillExceptionParameters(hitCopy);
            Object obj4 = mMetaModel;
            obj1 = hitCopy;
            HashMap hashmap = new HashMap();
            Iterator iterator = ((Map) (obj1)).entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                Object obj3 = (java.util.Map.Entry)iterator.next();
                obj2 = (String)((java.util.Map.Entry) (obj3)).getKey();
                if (((String) (obj2)).startsWith("&"))
                {
                    obj1 = new MetaModel.MetaInfo(((String) (obj2)).substring(1), null, null);
                } else
                {
                    obj1 = obj2;
                    if (((String) (obj2)).contains("*"))
                    {
                        obj1 = ((String) (obj2)).substring(0, ((String) (obj2)).indexOf("*"));
                    }
                    obj1 = (MetaModel.MetaInfo)((MetaModel) (obj4)).mMetaInfos.get(obj1);
                }
                if (obj1 != null)
                {
                    String s = ((MetaModel.MetaInfo) (obj1)).getUrlParam((String)((java.util.Map.Entry) (obj3)).getKey());
                    if (s != null)
                    {
                        obj3 = (String)((java.util.Map.Entry) (obj3)).getValue();
                        obj2 = obj3;
                        if (((MetaModel.MetaInfo) (obj1)).mFormatter != null)
                        {
                            obj2 = ((MetaModel.MetaInfo) (obj1)).mFormatter.format(((String) (obj3)));
                        }
                        if (obj2 != null && !((String) (obj2)).equals(((MetaModel.MetaInfo) (obj1)).mDefaultValue))
                        {
                            hashmap.put(s, obj2);
                        }
                    }
                }
            } while (true);
            ServiceProxy serviceproxy = mServiceProxy;
            long l = hitTime;
            obj1 = GAThread.this;
            obj4 = hitCopy;
            obj2 = (String)((Map) (obj4)).get("internalHitUrl");
            obj1 = obj2;
            if (obj2 == null)
            {
                if (((Map) (obj4)).containsKey("useSecure"))
                {
                    if (Utils.safeParseBoolean((String)((Map) (obj4)).get("useSecure")))
                    {
                        obj1 = "https://ssl.google-analytics.com/collect";
                    } else
                    {
                        obj1 = "http://www.google-analytics.com/collect";
                    }
                } else
                {
                    obj1 = "https://ssl.google-analytics.com/collect";
                }
            }
            serviceproxy.putHit(hashmap, l, ((String) (obj1)), mCommands);
            return;
        }

        _cls1()
        {
            this$0 = GAThread.this;
            hitCopy = map;
            hitTime = l;
            super();
        }
    }

}
