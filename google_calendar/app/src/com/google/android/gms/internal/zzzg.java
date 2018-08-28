// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzm;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzzw, zzaaq, zzzp, zzyx, 
//            zzyt, zzyv, zzzi, zzzv, 
//            zzys, zzzm

public final class zzzg extends GoogleApiClient
    implements zzzp.zza
{

    private final Context mContext;
    private final int zzaJh;
    private final GoogleApiAvailability zzaJj;
    private final com.google.android.gms.common.api.Api.zza zzaJk;
    private boolean zzaJn;
    private final zzg zzaKD;
    private final Map zzaKF;
    private final Lock zzaKy;
    private final zza zzaLA;
    private zzzm zzaLB;
    public final Map zzaLC;
    public Set zzaLD;
    private final zzzw zzaLE = new zzzw();
    private final ArrayList zzaLF;
    private Integer zzaLG;
    public Set zzaLH;
    public final zzaaq zzaLI;
    private final com.google.android.gms.common.internal.zzm.zza zzaLJ = new _cls1();
    private final zzm zzaLu;
    private zzzp zzaLv;
    public final Queue zzaLw = new LinkedList();
    private volatile boolean zzaLx;
    private long zzaLy;
    private long zzaLz;
    private final Looper zzrI;

    public zzzg(Context context, Lock lock, Looper looper, zzg zzg, GoogleApiAvailability googleapiavailability, com.google.android.gms.common.api.Api.zza zza1, Map map, 
            List list, List list1, Map map1, int i, int j, ArrayList arraylist, boolean flag)
    {
        zzaLv = null;
        zzaLy = 0x1d4c0L;
        zzaLz = 5000L;
        zzaLD = new HashSet();
        zzaLG = null;
        zzaLH = null;
        mContext = context;
        zzaKy = lock;
        zzaJn = false;
        zzaLu = new zzm(looper, zzaLJ);
        zzrI = looper;
        zzaLA = new zza(looper);
        zzaJj = googleapiavailability;
        zzaJh = i;
        if (zzaJh >= 0)
        {
            zzaLG = Integer.valueOf(j);
        }
        zzaKF = map;
        zzaLC = map1;
        zzaLF = arraylist;
        zzaLI = new zzaaq(zzaLC);
        for (context = list.iterator(); context.hasNext(); zzaLu.registerConnectionCallbacks(lock))
        {
            lock = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)context.next();
        }

        for (context = list1.iterator(); context.hasNext(); zzaLu.registerConnectionFailedListener(lock))
        {
            lock = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener)context.next();
        }

        zzaKD = zzg;
        zzaJk = zza1;
    }

    public static int zza(Iterable iterable, boolean flag)
    {
        iterable = iterable.iterator();
        boolean flag1 = false;
        com.google.android.gms.common.api.Api.zze zze1;
        for (; iterable.hasNext(); zze1.zzqo())
        {
            zze1 = (com.google.android.gms.common.api.Api.zze)iterable.next();
            if (zze1.zzpZ())
            {
                flag1 = true;
            }
        }

        return !flag1 ? 3 : 1;
    }

    static void zza(zzzg zzzg1)
    {
        zzzg1.zzaKy.lock();
        if (zzzg1.zzaLx)
        {
            zzzg1.zzaLu.zzaQl = true;
            zzzg1.zzaLv.connect();
        }
        zzzg1.zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzzg1.zzaKy.unlock();
        throw exception;
    }

    static void zzb(zzzg zzzg1)
    {
        zzzg1.zzaKy.lock();
        if (zzzg1.zzxx())
        {
            zzzg1.zzaLu.zzaQl = true;
            zzzg1.zzaLv.connect();
        }
        zzzg1.zzaKy.unlock();
        return;
        Exception exception;
        exception;
        zzzg1.zzaKy.unlock();
        throw exception;
    }

    private final void zzfI(int i)
    {
        if (zzaLG == null)
        {
            zzaLG = Integer.valueOf(i);
        } else
        if (zzaLG.intValue() != i)
        {
            String s = String.valueOf(zzfJ(i));
            String s1 = String.valueOf(zzfJ(zzaLG.intValue()));
            throw new IllegalStateException((new StringBuilder(String.valueOf(s).length() + 51 + String.valueOf(s1).length())).append("Cannot use sign-in mode: ").append(s).append(". Mode was already set to ").append(s1).toString());
        }
        if (zzaLv != null)
        {
            return;
        }
        Iterator iterator = zzaLC.values().iterator();
        i = 0;
        com.google.android.gms.common.api.Api.zze zze1;
        for (; iterator.hasNext(); zze1.zzqo())
        {
            zze1 = (com.google.android.gms.common.api.Api.zze)iterator.next();
            if (zze1.zzpZ())
            {
                i = 1;
            }
        }

        zzaLG.intValue();
        JVM INSTR tableswitch 1 3: default 200
    //                   1 256
    //                   2 271
    //                   3 200;
           goto _L1 _L2 _L3 _L1
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_271;
_L4:
        if (zzaJn)
        {
            zzaLv = new zzyx(mContext, zzaKy, zzrI, zzaJj, zzaLC, zzaKD, zzaKF, zzaJk, zzaLF, this);
            return;
        } else
        {
            zzaLv = new zzzi(mContext, this, zzaKy, zzrI, zzaJj, zzaLC, zzaKD, zzaKF, zzaJk, zzaLF, this);
            return;
        }
_L2:
        if (i == 0)
        {
            throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
        }
          goto _L4
        if (i != 0)
        {
            Context context = mContext;
            Lock lock = zzaKy;
            Looper looper = zzrI;
            GoogleApiAvailability googleapiavailability = zzaJj;
            Object obj = zzaLC;
            zzg zzg = zzaKD;
            Object obj1 = zzaKF;
            com.google.android.gms.common.api.Api.zza zza1 = zzaJk;
            ArrayList arraylist = zzaLF;
            ArrayMap arraymap = new ArrayMap();
            ArrayMap arraymap1 = new ArrayMap();
            for (obj = ((Map) (obj)).entrySet().iterator(); ((Iterator) (obj)).hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
                com.google.android.gms.common.api.Api.zze zze2 = (com.google.android.gms.common.api.Api.zze)entry.getValue();
                zze2.zzqo();
                if (zze2.zzpZ())
                {
                    arraymap.put((com.google.android.gms.common.api.Api.zzc)entry.getKey(), zze2);
                } else
                {
                    arraymap1.put((com.google.android.gms.common.api.Api.zzc)entry.getKey(), zze2);
                }
            }

            if (!arraymap.isEmpty())
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                throw new IllegalStateException(String.valueOf("CompositeGoogleApiClient should not be used without any APIs that require sign-in."));
            }
            obj = new ArrayMap();
            ArrayMap arraymap2 = new ArrayMap();
            for (Iterator iterator1 = ((Map) (obj1)).keySet().iterator(); iterator1.hasNext();)
            {
                Api api = (Api)iterator1.next();
                if (api.zzaII != null)
                {
                    com.google.android.gms.common.api.Api.zzf zzf = api.zzaII;
                    if (arraymap.containsKey(zzf))
                    {
                        ((Map) (obj)).put(api, (Integer)((Map) (obj1)).get(api));
                    } else
                    if (arraymap1.containsKey(zzf))
                    {
                        arraymap2.put(api, (Integer)((Map) (obj1)).get(api));
                    } else
                    {
                        throw new IllegalStateException("Each API in the apiTypeMap must have a corresponding client in the clients map.");
                    }
                } else
                {
                    throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
                }
            }

            obj1 = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            arraylist = (ArrayList)arraylist;
            int j = arraylist.size();
            for (i = 0; i < j;)
            {
                Object obj2 = arraylist.get(i);
                i++;
                obj2 = (zzyt)obj2;
                if (((Map) (obj)).containsKey(((zzyt) (obj2)).zzaGo))
                {
                    ((ArrayList) (obj1)).add(obj2);
                } else
                if (arraymap2.containsKey(((zzyt) (obj2)).zzaGo))
                {
                    arraylist1.add(obj2);
                } else
                {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the apiTypeMap");
                }
            }

            zzaLv = new zzyv(context, this, lock, looper, googleapiavailability, arraymap, arraymap1, zzg, zza1, null, ((ArrayList) (obj1)), arraylist1, ((Map) (obj)), arraymap2);
            return;
        }
          goto _L4
    }

    private static String zzfJ(int i)
    {
        switch (i)
        {
        default:
            return "UNKNOWN";

        case 3: // '\003'
            return "SIGN_IN_MODE_NONE";

        case 1: // '\001'
            return "SIGN_IN_MODE_REQUIRED";

        case 2: // '\002'
            return "SIGN_IN_MODE_OPTIONAL";
        }
    }

    public final ConnectionResult blockingConnect()
    {
        boolean flag2;
        flag2 = true;
        boolean flag;
        if (Looper.myLooper() != Looper.getMainLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("blockingConnect must not be called on the UI thread"));
        }
        zzaKy.lock();
        if (zzaJh < 0) goto _L2; else goto _L1
_L1:
        Exception exception;
        boolean flag1;
        if (zzaLG != null)
        {
            flag1 = flag2;
        } else
        {
            flag1 = false;
        }
        if (flag1) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException(String.valueOf("Sign-in mode should have been set explicitly by auto-manage."));
        exception;
        zzaKy.unlock();
        throw exception;
_L2:
        if (zzaLG != null) goto _L6; else goto _L5
_L5:
        zzaLG = Integer.valueOf(zza(zzaLC.values(), false));
_L4:
        ConnectionResult connectionresult;
        zzfI(zzaLG.intValue());
        zzaLu.zzaQl = true;
        connectionresult = zzaLv.blockingConnect();
        zzaKy.unlock();
        return connectionresult;
_L6:
        if (zzaLG.intValue() != 2) goto _L4; else goto _L7
_L7:
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }

    public final ConnectionResult blockingConnect(long l, TimeUnit timeunit)
    {
        boolean flag = true;
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("blockingConnect must not be called on the UI thread"));
        }
        if (timeunit == null)
        {
            throw new NullPointerException(String.valueOf("TimeUnit must not be null"));
        }
        zzaKy.lock();
        if (zzaLG != null) goto _L2; else goto _L1
_L1:
        zzaLG = Integer.valueOf(zza(zzaLC.values(), false));
_L4:
        zzfI(zzaLG.intValue());
        zzaLu.zzaQl = true;
        timeunit = zzaLv.blockingConnect(l, timeunit);
        zzaKy.unlock();
        return timeunit;
_L2:
        if (zzaLG.intValue() != 2) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        timeunit;
        zzaKy.unlock();
        throw timeunit;
    }

    public final void connect()
    {
        boolean flag;
        flag = false;
        zzaKy.lock();
        if (zzaJh < 0) goto _L2; else goto _L1
_L1:
        if (zzaLG != null)
        {
            flag = true;
        }
        if (flag) goto _L4; else goto _L3
_L3:
        throw new IllegalStateException(String.valueOf("Sign-in mode should have been set explicitly by auto-manage."));
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
_L2:
        if (zzaLG != null) goto _L6; else goto _L5
_L5:
        zzaLG = Integer.valueOf(zza(zzaLC.values(), false));
_L4:
        connect(zzaLG.intValue());
        zzaKy.unlock();
        return;
_L6:
        if (zzaLG.intValue() != 2) goto _L4; else goto _L7
_L7:
        throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }

    public final void connect(int i)
    {
        boolean flag1 = true;
        zzaKy.lock();
        boolean flag = flag1;
        if (i != 3)
        {
            flag = flag1;
            String s;
            Exception exception;
            if (i != 1)
            {
                if (i == 2)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
            }
        }
        s = (new StringBuilder(33)).append("Illegal sign-in mode: ").append(i).toString();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        throw new IllegalArgumentException(String.valueOf(s));
        exception;
        zzaKy.unlock();
        throw exception;
        zzfI(i);
        zzaLu.zzaQl = true;
        zzaLv.connect();
        zzaKy.unlock();
        return;
    }

    public final void disconnect()
    {
        zzaKy.lock();
        Object obj;
        zzaLI.release();
        if (zzaLv != null)
        {
            zzaLv.disconnect();
        }
        obj = zzaLE;
        for (Iterator iterator = ((zzzw) (obj)).zzaro.iterator(); iterator.hasNext();)
        {
            ((zzzv)iterator.next()).mListener = null;
        }

        break MISSING_BLOCK_LABEL_84;
        obj;
        zzaKy.unlock();
        throw obj;
        ((zzzw) (obj)).zzaro.clear();
        zzyq.zza zza1;
        for (obj = zzaLw.iterator(); ((Iterator) (obj)).hasNext(); zza1.cancel())
        {
            zza1 = (zzyq.zza)((Iterator) (obj)).next();
            ((zzys) (zza1)).zzaKd.set(null);
        }

        zzaLw.clear();
        obj = zzaLv;
        if (obj == null)
        {
            zzaKy.unlock();
            return;
        }
        zzxx();
        zzm zzm1 = zzaLu;
        zzm1.zzaQl = false;
        zzm1.zzaQm.incrementAndGet();
        zzaKy.unlock();
        return;
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.append(s).append("mContext=").println(mContext);
        printwriter.append(s).append("mResuming=").print(zzaLx);
        printwriter.append(" mWorkQueue.size()=").print(zzaLw.size());
        zzaaq zzaaq1 = zzaLI;
        printwriter.append(" mUnconsumedApiCalls.size()=").println(zzaaq1.zzaNo.size());
        if (zzaLv != null)
        {
            zzaLv.dump(s, filedescriptor, printwriter, as);
        }
    }

    public final Looper getLooper()
    {
        return zzrI;
    }

    public final boolean isConnected()
    {
        return zzaLv != null && zzaLv.isConnected();
    }

    public final boolean isConnecting()
    {
        return zzaLv != null && zzaLv.isConnecting();
    }

    public final void registerConnectionCallbacks(com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks)
    {
        zzaLu.registerConnectionCallbacks(connectioncallbacks);
    }

    public final void registerConnectionFailedListener(com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener)
    {
        zzaLu.registerConnectionFailedListener(onconnectionfailedlistener);
    }

    public final com.google.android.gms.common.api.Api.zze zza(com.google.android.gms.common.api.Api.zzc zzc1)
    {
        zzc1 = (com.google.android.gms.common.api.Api.zze)zzaLC.get(zzc1);
        if (zzc1 == null)
        {
            throw new NullPointerException(String.valueOf("Appropriate Api was not requested."));
        } else
        {
            return zzc1;
        }
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        boolean flag;
        if (zza1.zzaJQ != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("This task can not be enqueued (it's probably a Batch or malformed)"));
        }
        boolean flag1 = zzaLC.containsKey(zza1.zzaJQ);
        String s;
        if (zza1.zzaGo != null)
        {
            s = zza1.zzaGo.mName;
        } else
        {
            s = "the API";
        }
        s = (new StringBuilder(String.valueOf(s).length() + 65)).append("GoogleApiClient is not configured to use ").append(s).append(" required for this call.").toString();
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf(s));
        }
        zzaKy.lock();
        if (zzaLv != null)
        {
            break MISSING_BLOCK_LABEL_161;
        }
        zzaLw.add(zza1);
        zzaKy.unlock();
        return zza1;
        zza1 = zzaLv.zza(zza1);
        zzaKy.unlock();
        return zza1;
        zza1;
        zzaKy.unlock();
        throw zza1;
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        boolean flag;
        if (zza1.zzaJQ != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("This task can not be executed (it's probably a Batch or malformed)"));
        }
        boolean flag1 = zzaLC.containsKey(zza1.zzaJQ);
        String s;
        if (zza1.zzaGo != null)
        {
            s = zza1.zzaGo.mName;
        } else
        {
            s = "the API";
        }
        s = (new StringBuilder(String.valueOf(s).length() + 65)).append("GoogleApiClient is not configured to use ").append(s).append(" required for this call.").toString();
        if (!flag1)
        {
            throw new IllegalArgumentException(String.valueOf(s));
        }
        zzaKy.lock();
        if (zzaLv == null)
        {
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
        }
        break MISSING_BLOCK_LABEL_165;
        zza1;
        zzaKy.unlock();
        throw zza1;
        if (!zzaLx)
        {
            break MISSING_BLOCK_LABEL_258;
        }
        zzaLw.add(zza1);
        zzyq.zza zza2;
        for (; !zzaLw.isEmpty(); zza2.zzM(Status.zzaJv))
        {
            zza2 = (zzyq.zza)zzaLw.remove();
            Object obj = zzaLI;
            ((zzaaq) (obj)).zzaNo.add(zza2);
            obj = ((zzaaq) (obj)).zzaNp;
            ((zzys) (zza2)).zzaKd.set(obj);
        }

        zzaKy.unlock();
        return zza1;
        zza1 = zzaLv.zzb(zza1);
        zzaKy.unlock();
        return zza1;
    }

    public final void zzc(ConnectionResult connectionresult)
    {
        if (!zzaJj.isPlayServicesPossiblyUpdating(mContext, connectionresult.zzaEP))
        {
            zzxx();
        }
        if (zzaLx) goto _L2; else goto _L1
_L1:
        zzm zzm1;
        zzm1 = zzaLu;
        boolean flag;
        if (Looper.myLooper() == zzm1.mHandler.getLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("onConnectionFailure must only be called on the Handler thread"));
        }
        zzm1.mHandler.removeMessages(1);
        Object obj = zzm1.zzrY;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        int k;
        int l;
        arraylist = new ArrayList(zzm1.zzaQk);
        k = zzm1.zzaQm.get();
        arraylist = (ArrayList)arraylist;
        l = arraylist.size();
        int i = 0;
_L8:
        if (i >= l) goto _L4; else goto _L3
_L3:
        Object obj1 = arraylist.get(i);
        int j = i + 1;
        obj1 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener)obj1;
        if (zzm1.zzaQl && zzm1.zzaQm.get() == k) goto _L6; else goto _L5
_L5:
        obj;
        JVM INSTR monitorexit ;
_L9:
        connectionresult = zzaLu;
        connectionresult.zzaQl = false;
        ((zzm) (connectionresult)).zzaQm.incrementAndGet();
_L2:
        return;
_L6:
        i = j;
        if (!zzm1.zzaQk.contains(obj1)) goto _L8; else goto _L7
_L7:
        ((com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) (obj1)).onConnectionFailed(connectionresult);
        i = j;
          goto _L8
        connectionresult;
        obj;
        JVM INSTR monitorexit ;
        throw connectionresult;
_L4:
        obj;
        JVM INSTR monitorexit ;
          goto _L9
    }

    public final void zze(int i, boolean flag)
    {
        Object obj1;
        if (i == 1 && !flag && !zzaLx)
        {
            zzaLx = true;
            if (zzaLB == null)
            {
                zzaLB = zzaJj.zza(mContext.getApplicationContext(), new zzb());
            }
            zzaLA.sendMessageDelayed(zzaLA.obtainMessage(1), zzaLy);
            zzaLA.sendMessageDelayed(zzaLA.obtainMessage(2), zzaLz);
        }
        zzys azzys[] = (zzys[])zzaLI.zzaNo.toArray(zzaaq.zzaNn);
        int l = azzys.length;
        for (int j = 0; j < l; j++)
        {
            azzys[j].zzN(zzaaq.zzaNm);
        }

        obj1 = zzaLu;
        boolean flag1;
        if (Looper.myLooper() == ((zzm) (obj1)).mHandler.getLooper())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            throw new IllegalStateException(String.valueOf("onUnintentionalDisconnection must only be called on the Handler thread"));
        }
        ((zzm) (obj1)).mHandler.removeMessages(1);
        Object obj = ((zzm) (obj1)).zzrY;
        obj;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        int j1;
        int k1;
        obj1.zzaQn = true;
        arraylist = new ArrayList(((zzm) (obj1)).zzaQi);
        j1 = ((zzm) (obj1)).zzaQm.get();
        arraylist = (ArrayList)arraylist;
        k1 = arraylist.size();
        int k = 0;
_L2:
        if (k >= k1)
        {
            break MISSING_BLOCK_LABEL_347;
        }
        Object obj2 = arraylist.get(k);
        int i1 = k + 1;
        obj2 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)obj2;
        if (!((zzm) (obj1)).zzaQl || ((zzm) (obj1)).zzaQm.get() != j1)
        {
            break MISSING_BLOCK_LABEL_347;
        }
        k = i1;
        if (!((zzm) (obj1)).zzaQi.contains(obj2)) goto _L2; else goto _L1
_L1:
        ((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) (obj2)).onConnectionSuspended(i);
        k = i1;
          goto _L2
        obj1;
        obj;
        JVM INSTR monitorexit ;
        throw obj1;
        ((zzm) (obj1)).zzaQj.clear();
        obj1.zzaQn = false;
        obj;
        JVM INSTR monitorexit ;
        zzm zzm1 = zzaLu;
        zzm1.zzaQl = false;
        zzm1.zzaQm.incrementAndGet();
        if (i == 2)
        {
            zzaLu.zzaQl = true;
            zzaLv.connect();
        }
        return;
    }

    public final void zzu(Bundle bundle)
    {
        zzm zzm1;
        int j;
        int k;
        k = 1;
        j = 0;
        for (; !zzaLw.isEmpty(); zzb((zzyq.zza)zzaLw.remove())) { }
        zzm1 = zzaLu;
        boolean flag;
        if (Looper.myLooper() == zzm1.mHandler.getLooper())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException(String.valueOf("onConnectionSuccess must only be called on the Handler thread"));
        }
        Object obj = zzm1.zzrY;
        obj;
        JVM INSTR monitorenter ;
        boolean flag1;
        if (!zzm1.zzaQn)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        throw new IllegalStateException();
        bundle;
        obj;
        JVM INSTR monitorexit ;
        throw bundle;
        zzm1.mHandler.removeMessages(1);
        zzm1.zzaQn = true;
        ArrayList arraylist;
        Object obj1;
        int i;
        int l;
        if (zzm1.zzaQj.size() == 0)
        {
            i = k;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_165;
        }
        throw new IllegalStateException();
        arraylist = new ArrayList(zzm1.zzaQi);
        k = zzm1.zzaQm.get();
        arraylist = (ArrayList)arraylist;
        l = arraylist.size();
        i = j;
_L2:
        if (i >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = arraylist.get(i);
        j = i + 1;
        obj1 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks)obj1;
        if (!zzm1.zzaQl || !zzm1.zzaQh.isConnected() || zzm1.zzaQm.get() != k)
        {
            break; /* Loop/switch isn't completed */
        }
        i = j;
        if (zzm1.zzaQj.contains(obj1))
        {
            continue; /* Loop/switch isn't completed */
        }
        ((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) (obj1)).onConnected(bundle);
        i = j;
        if (true) goto _L2; else goto _L1
_L1:
        zzm1.zzaQj.clear();
        zzm1.zzaQn = false;
        obj;
        JVM INSTR monitorexit ;
    }

    final boolean zzxx()
    {
        if (!zzaLx)
        {
            return false;
        }
        zzaLx = false;
        zzaLA.removeMessages(2);
        zzaLA.removeMessages(1);
        if (zzaLB != null)
        {
            zzaLB.unregister();
            zzaLB = null;
        }
        return true;
    }

    final boolean zzxy()
    {
        boolean flag;
        flag = false;
        zzaKy.lock();
        Set set = zzaLH;
        if (set == null)
        {
            zzaKy.unlock();
            return false;
        }
        boolean flag1 = zzaLH.isEmpty();
        if (!flag1)
        {
            flag = true;
        }
        zzaKy.unlock();
        return flag;
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    private class _cls1
        implements com.google.android.gms.common.internal.zzm.zza
    {

        private final zzzg zzaLK;

        public final boolean isConnected()
        {
            return zzaLK.isConnected();
        }

        public final Bundle zzvJ()
        {
            return null;
        }

        _cls1()
        {
            zzaLK = zzzg.this;
            super();
        }
    }


    private class zza extends Handler
    {

        private final zzzg zzaLK;

        public final void handleMessage(Message message)
        {
            switch (message.what)
            {
            default:
                int i = message.what;
                Log.w("GoogleApiClientImpl", (new StringBuilder(31)).append("Unknown message id: ").append(i).toString());
                return;

            case 1: // '\001'
                zzzg.zzb(zzaLK);
                return;

            case 2: // '\002'
                zzzg.zza(zzaLK);
                break;
            }
        }

        zza(Looper looper)
        {
            zzaLK = zzzg.this;
            super(looper);
        }
    }


    private class zzb extends zzzm.zza
    {

        private WeakReference zzaLO;

        public final void zzwN()
        {
            zzzg zzzg1 = (zzzg)zzaLO.get();
            if (zzzg1 == null)
            {
                return;
            } else
            {
                zzzg.zza(zzzg1);
                return;
            }
        }

        zzb()
        {
            zzaLO = new WeakReference(zzzg.this);
        }
    }

}
