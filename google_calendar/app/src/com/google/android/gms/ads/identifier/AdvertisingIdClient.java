// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.zza;
import com.google.android.gms.internal.zzcr;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class AdvertisingIdClient
{

    private final Context mContext;
    private com.google.android.gms.common.zza zzsp;
    private zzcr zzsq;
    private boolean zzsr;
    private Object zzss;
    private zza zzst;
    private final long zzsu = -1L;

    private AdvertisingIdClient(Context context, long l, boolean flag)
    {
        zzss = new Object();
        if (context == null)
        {
            throw new NullPointerException("null reference");
        }
        if (flag)
        {
            Context context1 = context.getApplicationContext();
            if (context1 != null)
            {
                context = context1;
            }
            mContext = context;
        } else
        {
            mContext = context;
        }
        zzsr = false;
    }

    public static Info getAdvertisingIdInfo(Context context)
        throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
    {
        float f1;
        boolean flag;
        boolean flag1;
        boolean flag2;
        f1 = 0.0F;
        flag1 = false;
        flag2 = false;
        flag = flag1;
        Object obj = GooglePlayServicesUtilLight.getRemoteContext(context);
        float f;
        f = f1;
        flag = flag2;
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_72;
        }
        flag = flag1;
        obj = ((Context) (obj)).getSharedPreferences("google_ads_flags", 1);
        flag = flag1;
        flag1 = ((SharedPreferences) (obj)).getBoolean("gads:ad_id_app_context:enabled", false);
        flag = flag1;
        f = ((SharedPreferences) (obj)).getFloat("gads:ad_id_app_context:ping_ratio", 0.0F);
        flag = flag1;
_L2:
        context = new AdvertisingIdClient(context, -1L, flag);
        context.zze(false);
        obj = context.getInfo();
        context.zza(((Info) (obj)), flag, f, null);
        context.finish();
        return ((Info) (obj));
        Object obj1;
        obj1;
        Log.w("AdvertisingIdClient", "Error while reading from SharedPreferences ", ((Throwable) (obj1)));
        f = f1;
        if (true) goto _L2; else goto _L1
_L1:
        obj1;
        context.zza(null, flag, f, ((Throwable) (obj1)));
        context.finish();
        return null;
        obj1;
        context.finish();
        throw obj1;
    }

    private final Info getInfo()
        throws IOException
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("Calling this from your main thread can lead to deadlock");
        }
        this;
        JVM INSTR monitorenter ;
        if (zzsr)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        Object obj = zzss;
        obj;
        JVM INSTR monitorenter ;
        if (zzst == null || !zzst.zzsz)
        {
            throw new IOException("AdvertisingIdClient is not connected.");
        }
        break MISSING_BLOCK_LABEL_72;
        Exception exception1;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
        obj;
        this;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        JVM INSTR monitorexit ;
        zze(false);
        if (!zzsr)
        {
            throw new IOException("AdvertisingIdClient cannot reconnect.");
        }
        break MISSING_BLOCK_LABEL_108;
        Exception exception;
        exception;
        throw new IOException("AdvertisingIdClient cannot reconnect.", exception);
        if (zzsp == null)
        {
            throw new NullPointerException("null reference");
        }
        if (zzsq == null)
        {
            throw new NullPointerException("null reference");
        }
        Info info = new Info(zzsq.getId(), zzsq.zzf(true));
        this;
        JVM INSTR monitorexit ;
        exception = ((Exception) (zzss));
        exception;
        JVM INSTR monitorenter ;
        if (zzst == null)
        {
            break MISSING_BLOCK_LABEL_202;
        }
        zzst.zzsy.countDown();
        try
        {
            zzst.join();
        }
        catch (InterruptedException interruptedexception) { }
        if (zzsu > 0L)
        {
            zzst = new zza(zzsu);
        }
        return info;
        exception;
        throw new IOException("Remote exception");
        info;
        exception;
        JVM INSTR monitorexit ;
        throw info;
    }

    private final void zza(Info info, boolean flag, float f, Throwable throwable)
    {
        if (Math.random() > (double)f)
        {
            return;
        }
        Bundle bundle = new Bundle();
        String s;
        if (flag)
        {
            s = "1";
        } else
        {
            s = "0";
        }
        bundle.putString("app_context", s);
        if (info != null)
        {
            if (info.zzsB)
            {
                s = "1";
            } else
            {
                s = "0";
            }
            bundle.putString("limit_ad_tracking", s);
        }
        if (info != null && info.zzsA != null)
        {
            bundle.putString("ad_id_size", Integer.toString(info.zzsA.length()));
        }
        if (throwable != null)
        {
            bundle.putString("error", throwable.getClass().getName());
        }
        info = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for (throwable = bundle.keySet().iterator(); throwable.hasNext(); info.appendQueryParameter(s, bundle.getString(s)))
        {
            s = (String)throwable.next();
        }

        (new _cls1(info.build().toString())).start();
    }

    private static zzcr zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONNKUJ17CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNMERBJ5TKMST35E9N62R1FF9T66SHR0(com.google.android.gms.common.zza zza1)
        throws IOException
    {
        Object obj;
        try
        {
            obj = TimeUnit.MILLISECONDS;
            if (Looper.myLooper() == Looper.getMainLooper())
            {
                throw new IllegalStateException("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
            }
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.gms.common.zza zza1)
        {
            throw new IOException("Interrupted exception");
        }
        // Misplaced declaration of an exception variable
        catch (com.google.android.gms.common.zza zza1)
        {
            throw new IOException(zza1);
        }
        if (zza1.zzaIh)
        {
            throw new IllegalStateException("Cannot call get on this connection more than once");
        }
        zza1.zzaIh = true;
        zza1 = (IBinder)zza1.zzaIi.poll(10000L, ((TimeUnit) (obj)));
        if (zza1 != null)
        {
            break MISSING_BLOCK_LABEL_138;
        }
        throw new TimeoutException("Timed out waiting for the service connection");
_L1:
        obj = zza1.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_127;
        }
        if (obj instanceof zzcr)
        {
            return (zzcr)obj;
        }
        zza1 = new com.google.android.gms.internal.zzcr.zza.zza(zza1);
        return zza1;
        if (zza1 == null)
        {
            return null;
        }
          goto _L1
    }

    private final void zze(boolean flag)
        throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("Calling this from your main thread can lead to deadlock");
        }
        this;
        JVM INSTR monitorenter ;
        if (zzsr)
        {
            finish();
        }
        zzsp = zzf(mContext);
        zzsq = zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TJMQSPFCDNMQRBFDONNKUJ17CKKOORFDKNMERRFCTM6ABR1DPI74RR9CGNMERBJ5TKMST35E9N62R1FF9T66SHR0(zzsp);
        zzsr = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static com.google.android.gms.common.zza zzf(Context context)
        throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
    {
        com.google.android.gms.common.zza zza1;
        Intent intent;
        boolean flag;
        flag = false;
        try
        {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            throw new GooglePlayServicesNotAvailableException(9);
        }
        switch (GoogleApiAvailabilityLight.zzaIo.isGooglePlayServicesAvailable(context))
        {
        case 1: // '\001'
        default:
            throw new IOException("Google Play services not available");

        case 0: // '\0'
        case 2: // '\002'
            zza1 = new com.google.android.gms.common.zza();
            break;
        }
        intent = new Intent("com.google.android.gms.ads.identifier.service.START");
        intent.setPackage("com.google.android.gms");
label0:
        {
            com.google.android.gms.common.stats.zza.zzzN();
            context.getClass().getName();
            if (com.google.android.gms.common.stats.zza.zzc(context, intent))
            {
                Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
                break label0;
            }
            try
            {
                flag = context.bindService(intent, zza1, 1);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                throw new IOException(context);
            }
        }
        while (!flag) 
        {
            throw new IOException("Connection failure");
        }
        return zza1;
    }

    protected final void finalize()
        throws Throwable
    {
        finish();
        super.finalize();
    }

    public final void finish()
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("Calling this from your main thread can lead to deadlock");
        }
        this;
        JVM INSTR monitorenter ;
        if (mContext != null && zzsp != null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        try
        {
            if (zzsr)
            {
                com.google.android.gms.common.stats.zza.zzzN();
                com.google.android.gms.common.stats.zza.zza(mContext, zzsp);
            }
        }
        catch (IllegalArgumentException illegalargumentexception) { }
        catch (Throwable throwable) { }
        zzsr = false;
        zzsq = null;
        zzsp = null;
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private class zza extends Thread
    {

        private WeakReference zzsw;
        private long zzsx;
        public CountDownLatch zzsy;
        public boolean zzsz;

        public final void run()
        {
            AdvertisingIdClient advertisingidclient;
            if (zzsy.await(zzsx, TimeUnit.MILLISECONDS))
            {
                break MISSING_BLOCK_LABEL_41;
            }
            advertisingidclient = (AdvertisingIdClient)zzsw.get();
            if (advertisingidclient == null)
            {
                break MISSING_BLOCK_LABEL_41;
            }
            advertisingidclient.finish();
            zzsz = true;
_L1:
            return;
            InterruptedException interruptedexception;
            interruptedexception;
            AdvertisingIdClient advertisingidclient1 = (AdvertisingIdClient)zzsw.get();
            if (advertisingidclient1 != null)
            {
                advertisingidclient1.finish();
                zzsz = true;
                return;
            }
              goto _L1
        }

        public zza(long l)
        {
            zzsw = new WeakReference(AdvertisingIdClient.this);
            zzsx = l;
            zzsy = new CountDownLatch(1);
            zzsz = false;
            start();
        }
    }


    private class Info
    {

        public final String zzsA;
        public final boolean zzsB;

        public final String toString()
        {
            String s = zzsA;
            boolean flag = zzsB;
            return (new StringBuilder(String.valueOf(s).length() + 7)).append("{").append(s).append("}").append(flag).toString();
        }

        public Info(String s, boolean flag)
        {
            zzsA = s;
            zzsB = flag;
        }
    }


    private class _cls1 extends Thread
    {

        private final String zzsv;

        public final void run()
        {
            String s;
            new com.google.android.gms.ads.identifier.zza();
            s = zzsv;
            HttpURLConnection httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
            int i = httpurlconnection.getResponseCode();
            if (i >= 200 && i < 300)
            {
                break MISSING_BLOCK_LABEL_95;
            }
            Log.w("HttpUrlPinger", (new StringBuilder(String.valueOf(s).length() + 65)).append("Received non-success response code ").append(i).append(" from pinging URL: ").append(s).toString());
            httpurlconnection.disconnect();
            return;
            Exception exception;
            exception;
            httpurlconnection.disconnect();
            throw exception;
            Object obj;
            obj;
            String s1 = String.valueOf(((IndexOutOfBoundsException) (obj)).getMessage());
            Log.w("HttpUrlPinger", (new StringBuilder(String.valueOf(s).length() + 32 + String.valueOf(s1).length())).append("Error while parsing ping URL: ").append(s).append(". ").append(s1).toString(), ((Throwable) (obj)));
            return;
            obj;
_L2:
            String s2 = String.valueOf(((Exception) (obj)).getMessage());
            Log.w("HttpUrlPinger", (new StringBuilder(String.valueOf(s).length() + 27 + String.valueOf(s2).length())).append("Error while pinging URL: ").append(s).append(". ").append(s2).toString(), ((Throwable) (obj)));
            return;
            obj;
            if (true) goto _L2; else goto _L1
_L1:
        }

        _cls1(String s)
        {
            zzsv = s;
            super();
        }
    }

}
