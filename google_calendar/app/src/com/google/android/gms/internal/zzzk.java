// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.util.zza;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.gms.internal:
//            zzyp, zzyn, zzaaa, zzyl, 
//            zzyy, zzyz

public final class zzzk
    implements android.os.Handler.Callback
{

    public static final Status zzaLZ = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status zzaMa = new Status(4, "The user must be signed in to make this API call.");
    private static zzzk zzaMc;
    public static final Object zzun = new Object();
    private final Context mContext;
    public final Handler mHandler;
    private final GoogleApiAvailability zzaJj;
    public final Map zzaKE = new ConcurrentHashMap(5, 0.75F, 1);
    private long zzaLy;
    private long zzaLz;
    private long zzaMb;
    private int zzaMd;
    public final AtomicInteger zzaMe = new AtomicInteger(1);
    public final AtomicInteger zzaMf = new AtomicInteger(0);
    private zzyz zzaMg;
    private final Set zzaMh = new com.google.android.gms.common.util.zza();
    private final Set zzaMi = new com.google.android.gms.common.util.zza();

    private zzzk(Context context, Looper looper, GoogleApiAvailability googleapiavailability)
    {
        zzaLz = 5000L;
        zzaLy = 0x1d4c0L;
        zzaMb = 10000L;
        zzaMd = -1;
        zzaMg = null;
        mContext = context;
        mHandler = new Handler(looper, this);
        zzaJj = googleapiavailability;
    }

    static int zza(zzzk zzzk1, int i)
    {
        zzzk1.zzaMd = i;
        return i;
    }

    static Handler zza(zzzk zzzk1)
    {
        return zzzk1.mHandler;
    }

    public static zzzk zzaz(Context context)
    {
        synchronized (zzun)
        {
            if (zzaMc == null)
            {
                Object obj1 = new HandlerThread("GoogleApiHandler", 9);
                ((HandlerThread) (obj1)).start();
                obj1 = ((HandlerThread) (obj1)).getLooper();
                zzaMc = new zzzk(context.getApplicationContext(), ((Looper) (obj1)), GoogleApiAvailability.zzaIm);
            }
            context = zzaMc;
        }
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    static Context zzb(zzzk zzzk1)
    {
        return zzzk1.mContext;
    }

    private final void zzb(zzd zzd1)
    {
        zzyn zzyn1 = zzd1.zzaIT;
        if (!zzaKE.containsKey(zzyn1))
        {
            zzaKE.put(zzyn1, new zza(zzd1));
        }
        zzd1 = (zza)zzaKE.get(zzyn1);
        if (((zza) (zzd1)).zzaKB.zzpZ())
        {
            zzaMi.add(zzyn1);
        }
        zzd1.connect();
    }

    static long zzc(zzzk zzzk1)
    {
        return zzzk1.zzaLz;
    }

    static long zzd(zzzk zzzk1)
    {
        return zzzk1.zzaLy;
    }

    static zzyz zze(zzzk zzzk1)
    {
        return zzzk1.zzaMg;
    }

    static Set zzf(zzzk zzzk1)
    {
        return zzzk1.zzaMh;
    }

    static GoogleApiAvailability zzg(zzzk zzzk1)
    {
        return zzzk1.zzaJj;
    }

    static long zzh(zzzk zzzk1)
    {
        return zzzk1.zzaMb;
    }

    static int zzi(zzzk zzzk1)
    {
        return zzzk1.zzaMd;
    }

    static Map zzj(zzzk zzzk1)
    {
        return zzzk1.zzaKE;
    }

    public static zzzk zzxE()
    {
        Object obj = zzun;
        obj;
        JVM INSTR monitorenter ;
        if (zzaMc == null)
        {
            throw new NullPointerException(String.valueOf("Must guarantee manager is non-null before using getInstance"));
        }
        break MISSING_BLOCK_LABEL_30;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        zzzk zzzk1 = zzaMc;
        obj;
        JVM INSTR monitorexit ;
        return zzzk1;
    }

    public final boolean handleMessage(Message message)
    {
        int i = 0;
        message.what;
        JVM INSTR tableswitch 1 11: default 64
    //                   1 100
    //                   2 342
    //                   3 417
    //                   4 533
    //                   5 328
    //                   6 417
    //                   7 726
    //                   8 802
    //                   9 862
    //                   10 1003
    //                   11 417;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L4 _L7 _L8 _L9 _L10 _L4
_L1:
        i = message.what;
        Log.w("GoogleApiManager", (new StringBuilder(31)).append("Unknown message id: ").append(i).toString());
        return false;
_L2:
        Object obj;
        message = (zzyp)message.obj;
        obj = ((zzyp) (message)).zzaIK.keySet().iterator();
_L13:
        zza zza2;
        zzyn zzyn2;
        if (!((Iterator) (obj)).hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        zzyn2 = (zzyn)((Iterator) (obj)).next();
        zza2 = (zza)zzaKE.get(zzyn2);
        if (zza2 != null) goto _L12; else goto _L11
_L11:
        message.zza(zzyn2, new ConnectionResult(13));
_L14:
        return true;
_L12:
        if (zza2.zzaKB.isConnected())
        {
            message.zza(zzyn2, ConnectionResult.zzaIj);
        } else
        {
            Handler handler4 = zza2.zzaMr.mHandler;
            if (Looper.myLooper() != handler4.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (zza2.zzaMq != null)
            {
                Handler handler5 = zza2.zzaMr.mHandler;
                if (Looper.myLooper() != handler5.getLooper())
                {
                    throw new IllegalStateException("Must be called on the handler thread");
                }
                message.zza(zzyn2, zza2.zzaMq);
            } else
            {
                Handler handler3 = zza2.zzaMr.mHandler;
                if (Looper.myLooper() != handler3.getLooper())
                {
                    throw new IllegalStateException("Must be called on the handler thread");
                }
                zza2.zzaMm.add(message);
            }
        }
          goto _L13
_L6:
        zzb((zzd)message.obj);
          goto _L14
_L3:
        message = zzaKE.values().iterator();
        while (message.hasNext()) 
        {
            obj = (zza)message.next();
            Handler handler2 = ((zza) (obj)).zzaMr.mHandler;
            if (Looper.myLooper() != handler2.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            obj.zzaMq = null;
            ((zza) (obj)).connect();
        }
          goto _L14
_L4:
        zzaaa zzaaa1 = (zzaaa)message.obj;
        obj = (zza)zzaKE.get(zzaaa1.zzaMT.zzaIT);
        message = ((Message) (obj));
        if (obj == null)
        {
            zzb(zzaaa1.zzaMT);
            message = (zza)zzaKE.get(zzaaa1.zzaMT.zzaIT);
        }
        if (((zza) (message)).zzaKB.zzpZ() && zzaMf.get() != zzaaa1.zzaMS)
        {
            zzaaa1.zzaMR.zzK(zzaLZ);
            message.signOut();
        } else
        {
            message.zza(zzaaa1.zzaMR);
        }
          goto _L14
_L5:
        i = message.arg1;
        obj = (ConnectionResult)message.obj;
        Iterator iterator = zzaKE.values().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_1145;
            }
            message = (zza)iterator.next();
        } while (((zza) (message)).zzaMo != i);
_L15:
        if (message != null)
        {
            String s = String.valueOf(zzaJj.getErrorString(((ConnectionResult) (obj)).zzaEP));
            obj = String.valueOf(((ConnectionResult) (obj)).zzaIk);
            message.zzO(new Status(17, (new StringBuilder(String.valueOf(s).length() + 69 + String.valueOf(obj).length())).append("Error resolution was canceled by the user, original error message: ").append(s).append(": ").append(((String) (obj))).toString()));
        } else
        {
            Log.wtf("GoogleApiManager", (new StringBuilder(76)).append("Could not find API instance ").append(i).append(" while trying to fail enqueued calls.").toString(), new Exception());
        }
          goto _L14
_L7:
        if (zzaKE.containsKey(message.obj))
        {
            message = (zza)zzaKE.get(message.obj);
            Handler handler = ((zza) (message)).zzaMr.mHandler;
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (((zza) (message)).zzaLx)
            {
                message.connect();
            }
        }
          goto _L14
_L8:
        zzyn zzyn1;
        for (message = zzaMi.iterator(); message.hasNext(); ((zza)zzaKE.remove(zzyn1)).signOut())
        {
            zzyn1 = (zzyn)message.next();
        }

        zzaMi.clear();
          goto _L14
_L9:
        if (zzaKE.containsKey(message.obj))
        {
            zza zza1 = (zza)zzaKE.get(message.obj);
            message = zza1.zzaMr.mHandler;
            if (Looper.myLooper() != message.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (zza1.zzaLx)
            {
                zza1.zzxR();
                if (zza1.zzaMr.zzaJj.isGooglePlayServicesAvailable(zza1.zzaMr.mContext) == 18)
                {
                    message = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                } else
                {
                    message = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                zza1.zzO(message);
                zza1.zzaKB.disconnect();
            }
        }
          goto _L14
_L10:
        if (zzaKE.containsKey(message.obj))
        {
            message = (zza)zzaKE.get(message.obj);
            Handler handler1 = ((zza) (message)).zzaMr.mHandler;
            if (Looper.myLooper() != handler1.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (((zza) (message)).zzaKB.isConnected() && ((zza) (message)).zzaMn.size() == 0)
            {
                zzyy zzyy1 = ((zza) (message)).zzaMl;
                if (!zzyy1.zzaKN.isEmpty() || !zzyy1.zzaKO.isEmpty())
                {
                    i = 1;
                }
                if (i != 0)
                {
                    message.zzxS();
                } else
                {
                    ((zza) (message)).zzaKB.disconnect();
                }
            }
        }
          goto _L14
        message = null;
          goto _L15
    }

    public final boolean zzc(ConnectionResult connectionresult, int i)
    {
        boolean flag1 = false;
        boolean flag;
        if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || zzaJj.isUserResolvableError(connectionresult.zzaEP))
        {
            GoogleApiAvailability googleapiavailability = zzaJj;
            Context context = mContext;
            PendingIntent pendingintent;
            if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                pendingintent = connectionresult.mPendingIntent;
            } else
            {
                pendingintent = googleapiavailability.getErrorResolutionPendingIntent(context, connectionresult.zzaEP, 0);
            }
            if (pendingintent != null)
            {
                googleapiavailability.zza(context, connectionresult.zzaEP, null, PendingIntent.getActivity(context, 0, GoogleApiActivity.zzb(context, pendingintent, i, true), 0x8000000));
            }
            flag1 = true;
        }
        return flag1;
    }


    private class zza
        implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener, zzyu
    {

        private final zzyn zzaIT;
        public final com.google.android.gms.common.api.Api.zze zzaKB;
        public boolean zzaLx;
        private final Queue zzaMj = new LinkedList();
        public final zzyy zzaMl = new zzyy();
        public final Set zzaMm = new HashSet();
        public final Map zzaMn = new HashMap();
        public final int zzaMo;
        private final zzaah zzaMp;
        public ConnectionResult zzaMq;
        public final zzzk zzaMr;

        private final void zzb(zzyl zzyl1)
        {
            zzyl1.zza(zzaMl, zzaKB.zzpZ());
            try
            {
                zzyl1.zza(this);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (zzyl zzyl1)
            {
                onConnectionSuspended(1);
            }
            zzaKB.disconnect();
        }

        private final void zzj(ConnectionResult connectionresult)
        {
            for (Iterator iterator = zzaMm.iterator(); iterator.hasNext(); ((zzyp)iterator.next()).zza(zzaIT, connectionresult)) { }
            zzaMm.clear();
        }

        public final void connect()
        {
            Handler handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (zzaKB.isConnected() || zzaKB.isConnecting())
            {
                return;
            }
            zzaKB.zzwt();
            if (zzzk.zzi(zzaMr) != 0)
            {
                com.google.android.gms.internal.zzzk.zza(zzaMr, com.google.android.gms.internal.zzzk.zzg(zzaMr).isGooglePlayServicesAvailable(zzzk.zzb(zzaMr)));
                if (zzzk.zzi(zzaMr) != 0)
                {
                    onConnectionFailed(new ConnectionResult(zzzk.zzi(zzaMr), null));
                    return;
                }
            }
            zzb zzb1 = zzaMr. new zzb(zzaKB, zzaIT);
            if (zzaKB.zzpZ())
            {
                zzaah zzaah1 = zzaMp;
                if (zzaah1.zzaLd != null)
                {
                    zzaah1.zzaLd.disconnect();
                }
                if (zzaah1.zzaMY)
                {
                    Object obj = zzl.zzae(zzaah1.mContext);
                    obj = ((zzl) (obj)).zzbZ(((zzl) (obj)).zzca("defaultGoogleSignInAccount"));
                    if (obj == null)
                    {
                        obj = new HashSet();
                    } else
                    {
                        obj = new HashSet(new ArrayList(((GoogleSignInOptions) (obj)).zzalr));
                    }
                    zzaah1.zzalx = ((Set) (obj));
                    zzaah1.zzaKD = new zzg(null, zzaah1.zzalx, null, 0, null, null, null, zzbdn.zzcmh);
                }
                zzaah1.zzaLd = (zzbdm)zzaah1.zzaIG.zza(zzaah1.mContext, zzaah1.mHandler.getLooper(), zzaah1.zzaKD, zzaah1.zzaKD.zzaPO, zzaah1, zzaah1);
                zzaah1.zzaMZ = zzb1;
                zzaah1.zzaLd.connect();
            }
            zzaKB.zza(zzb1);
        }

        public final void onConnected(Bundle bundle)
        {
            if (Looper.myLooper() == com.google.android.gms.internal.zzzk.zza(zzaMr).getLooper())
            {
                zzxL();
                return;
            } else
            {
                class _cls1
                    implements Runnable
                {

                    private final zza zzaMs;

                    public final void run()
                    {
                        zzaMs.zzxL();
                    }

                _cls1()
                {
                    zzaMs = zza.this;
                    super();
                }
                }

                com.google.android.gms.internal.zzzk.zza(zzaMr).post(new _cls1());
                return;
            }
        }

        public final void onConnectionFailed(ConnectionResult connectionresult)
        {
            Handler handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (zzaMp != null)
            {
                zzaMp.zzaLd.disconnect();
            }
            handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            zzaMq = null;
            com.google.android.gms.internal.zzzk.zza(zzaMr, -1);
            zzj(connectionresult);
            if (connectionresult.zzaEP != 4) goto _L2; else goto _L1
_L1:
            zzO(zzzk.zzaMa);
_L4:
            return;
_L2:
            if (zzaMj.isEmpty())
            {
                zzaMq = connectionresult;
                return;
            }
            synchronized (zzzk.zzun)
            {
                if (zzzk.zze(zzaMr) == null || !zzzk.zzf(zzaMr).contains(zzaIT))
                {
                    break MISSING_BLOCK_LABEL_186;
                }
                zzzk.zze(zzaMr).zzb(connectionresult, zzaMo);
            }
            return;
            connectionresult;
            obj;
            JVM INSTR monitorexit ;
            throw connectionresult;
            obj;
            JVM INSTR monitorexit ;
            if (!zzaMr.zzc(connectionresult, zzaMo))
            {
                if (connectionresult.zzaEP == 18)
                {
                    zzaLx = true;
                }
                if (zzaLx)
                {
                    com.google.android.gms.internal.zzzk.zza(zzaMr).sendMessageDelayed(Message.obtain(com.google.android.gms.internal.zzzk.zza(zzaMr), 7, zzaIT), zzzk.zzc(zzaMr));
                    return;
                } else
                {
                    connectionresult = String.valueOf(zzaIT.zzaGo.mName);
                    zzO(new Status(17, (new StringBuilder(String.valueOf(connectionresult).length() + 38)).append("API: ").append(connectionresult).append(" is not available on this device.").toString()));
                    return;
                }
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public final void onConnectionSuspended(int i)
        {
            if (Looper.myLooper() == com.google.android.gms.internal.zzzk.zza(zzaMr).getLooper())
            {
                zzxM();
                return;
            } else
            {
                class _cls2
                    implements Runnable
                {

                    private final zza zzaMs;

                    public final void run()
                    {
                        zzaMs.zzxM();
                    }

                _cls2()
                {
                    zzaMs = zza.this;
                    super();
                }
                }

                com.google.android.gms.internal.zzzk.zza(zzaMr).post(new _cls2());
                return;
            }
        }

        public final void signOut()
        {
            Handler handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            zzO(zzzk.zzaLZ);
            zzaMl.zza(false, zzzk.zzaLZ);
            for (Iterator iterator = zzaMn.keySet().iterator(); iterator.hasNext(); zza(new zzyl.zze((zzzv.zzb)iterator.next(), new TaskCompletionSource()))) { }
            zzaKB.disconnect();
        }

        public final void zzO(Status status)
        {
            Handler handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            for (Iterator iterator = zzaMj.iterator(); iterator.hasNext(); ((zzyl)iterator.next()).zzK(status)) { }
            zzaMj.clear();
        }

        public final void zza(ConnectionResult connectionresult, Api api, int i)
        {
            if (Looper.myLooper() == com.google.android.gms.internal.zzzk.zza(zzaMr).getLooper())
            {
                onConnectionFailed(connectionresult);
                return;
            } else
            {
                class _cls3
                    implements Runnable
                {

                    private final zza zzaMs;
                    private final ConnectionResult zzaMt;

                    public final void run()
                    {
                        zzaMs.onConnectionFailed(zzaMt);
                    }

                _cls3(ConnectionResult connectionresult)
                {
                    zzaMs = zza.this;
                    zzaMt = connectionresult;
                    super();
                }
                }

                com.google.android.gms.internal.zzzk.zza(zzaMr).post(new _cls3(connectionresult));
                return;
            }
        }

        public final void zza(zzyl zzyl1)
        {
            Handler handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            if (zzaKB.isConnected())
            {
                zzb(zzyl1);
                zzxS();
                return;
            }
            zzaMj.add(zzyl1);
            if (zzaMq != null)
            {
                zzyl1 = zzaMq;
                boolean flag;
                if (((ConnectionResult) (zzyl1)).zzaEP != 0 && ((ConnectionResult) (zzyl1)).mPendingIntent != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    onConnectionFailed(zzaMq);
                    return;
                }
            }
            connect();
        }

        final void zzxL()
        {
            Object obj;
            obj = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != ((Handler) (obj)).getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            }
            zzaMq = null;
            zzj(ConnectionResult.zzaIj);
            zzxR();
            obj = zzaMn.values().iterator();
_L2:
            if (!((Iterator) (obj)).hasNext())
            {
                break MISSING_BLOCK_LABEL_101;
            }
            ((Iterator) (obj)).next();
            new TaskCompletionSource();
            continue; /* Loop/switch isn't completed */
            DeadObjectException deadobjectexception;
            deadobjectexception;
            onConnectionSuspended(1);
            zzaKB.disconnect();
            for (; zzaKB.isConnected() && !zzaMj.isEmpty(); zzb((zzyl)zzaMj.remove())) { }
            zzxS();
            return;
            RemoteException remoteexception;
            remoteexception;
            if (true) goto _L2; else goto _L1
_L1:
        }

        final void zzxM()
        {
            Handler handler = com.google.android.gms.internal.zzzk.zza(zzaMr);
            if (Looper.myLooper() != handler.getLooper())
            {
                throw new IllegalStateException("Must be called on the handler thread");
            } else
            {
                zzaMq = null;
                zzaLx = true;
                zzaMl.zza(true, zzaaq.zzaNm);
                com.google.android.gms.internal.zzzk.zza(zzaMr).sendMessageDelayed(Message.obtain(com.google.android.gms.internal.zzzk.zza(zzaMr), 7, zzaIT), zzzk.zzc(zzaMr));
                com.google.android.gms.internal.zzzk.zza(zzaMr).sendMessageDelayed(Message.obtain(com.google.android.gms.internal.zzzk.zza(zzaMr), 9, zzaIT), com.google.android.gms.internal.zzzk.zzd(zzaMr));
                com.google.android.gms.internal.zzzk.zza(zzaMr, -1);
                return;
            }
        }

        final void zzxR()
        {
            if (zzaLx)
            {
                com.google.android.gms.internal.zzzk.zza(zzaMr).removeMessages(9, zzaIT);
                com.google.android.gms.internal.zzzk.zza(zzaMr).removeMessages(7, zzaIT);
                zzaLx = false;
            }
        }

        final void zzxS()
        {
            com.google.android.gms.internal.zzzk.zza(zzaMr).removeMessages(10, zzaIT);
            com.google.android.gms.internal.zzzk.zza(zzaMr).sendMessageDelayed(com.google.android.gms.internal.zzzk.zza(zzaMr).obtainMessage(10, zzaIT), zzzk.zzh(zzaMr));
        }

        public zza(zzd zzd1)
        {
            zzaMr = zzzk.this;
            super();
            zzaMq = null;
            zzaKB = zzd1.buildApiClient(com.google.android.gms.internal.zzzk.zza(zzzk.this).getLooper(), this);
            if (zzaKB instanceof zzal)
            {
                zzal.zzzA();
            }
            zzaIT = zzd1.zzaIT;
            zzaMo = zzd1.mId;
            if (zzaKB.zzpZ())
            {
                zzaMp = zzd1.createSignInCoordinator(zzzk.zzb(zzzk.this), com.google.android.gms.internal.zzzk.zza(zzzk.this));
                return;
            } else
            {
                zzaMp = null;
                return;
            }
        }

        private class zzb
            implements com.google.android.gms.common.internal.zzf.zzf, zzaah.zza
        {

            public final zzyn zzaIT;
            public final com.google.android.gms.common.api.Api.zze zzaKB;
            public zzr zzaLh;
            public final zzzk zzaMr;
            public boolean zzaMu;
            public Set zzalx;

            public final void zzb(zzr zzr, Set set)
            {
                if (zzr == null || set == null)
                {
                    Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                    zzi(new ConnectionResult(4));
                } else
                {
                    zzaLh = zzr;
                    zzalx = set;
                    if (zzaMu && zzaLh != null)
                    {
                        zzaKB.zza(zzaLh, zzalx);
                        return;
                    }
                }
            }

            public final void zzg(ConnectionResult connectionresult)
            {
                class _cls1
                    implements Runnable
                {

                    private final ConnectionResult zzaMt;
                    private final zzb zzaMv;

                    public final void run()
                    {
                        boolean flag;
                        if (zzaMt.zzaEP == 0)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                        if (flag)
                        {
                            zzaMv.zzaMu = true;
                            if (zzaMv.zzaKB.zzpZ())
                            {
                                zzb zzb1 = zzaMv;
                                if (zzb1.zzaMu && zzb1.zzaLh != null)
                                {
                                    zzb1.zzaKB.zza(zzb1.zzaLh, zzb1.zzalx);
                                }
                                return;
                            } else
                            {
                                zzaMv.zzaKB.zza(null, Collections.emptySet());
                                return;
                            }
                        } else
                        {
                            ((zza)zzzk.zzj(zzaMv.zzaMr).get(zzaMv.zzaIT)).onConnectionFailed(zzaMt);
                            return;
                        }
                    }

                    _cls1(ConnectionResult connectionresult)
                    {
                        zzaMv = zzb.this;
                        zzaMt = connectionresult;
                        super();
                    }
                }

                com.google.android.gms.internal.zzzk.zza(zzaMr).post(new _cls1(connectionresult));
            }

            public final void zzi(ConnectionResult connectionresult)
            {
                zza zza1 = (zza)zzzk.zzj(zzaMr).get(zzaIT);
                Handler handler = com.google.android.gms.internal.zzzk.zza(zza1.zzaMr);
                if (Looper.myLooper() != handler.getLooper())
                {
                    throw new IllegalStateException("Must be called on the handler thread");
                } else
                {
                    zza1.zzaKB.disconnect();
                    zza1.onConnectionFailed(connectionresult);
                    return;
                }
            }

            public zzb(com.google.android.gms.common.api.Api.zze zze1, zzyn zzyn1)
            {
                zzaMr = zzzk.this;
                super();
                zzaLh = null;
                zzalx = null;
                zzaMu = false;
                zzaKB = zze1;
                zzaIT = zzyn1;
            }
        }

    }

}
