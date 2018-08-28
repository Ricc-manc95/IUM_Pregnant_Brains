// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzg;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzzp, zzzi, zzzg, zzzh, 
//            zzaag

final class zzyv
    implements zzzp
{

    private final Context mContext;
    public final zzzg zzaKo;
    public final zzzi zzaKp;
    public final zzzi zzaKq;
    private final Map zzaKr;
    private final Set zzaKs = Collections.newSetFromMap(new WeakHashMap());
    private final com.google.android.gms.common.api.Api.zze zzaKt;
    public Bundle zzaKu;
    public ConnectionResult zzaKv;
    public ConnectionResult zzaKw;
    public boolean zzaKx;
    public final Lock zzaKy;
    private int zzaKz;

    zzyv(Context context, zzzg zzzg1, Lock lock, Looper looper, GoogleApiAvailabilityLight googleapiavailabilitylight, Map map, Map map1, 
            zzg zzg, com.google.android.gms.common.api.Api.zza zza1, com.google.android.gms.common.api.Api.zze zze, ArrayList arraylist, ArrayList arraylist1, Map map2, Map map3)
    {
        zzaKv = null;
        zzaKw = null;
        zzaKx = false;
        zzaKz = 0;
        mContext = context;
        zzaKo = zzzg1;
        zzaKy = lock;
        zzaKt = zze;
        zzaKp = new zzzi(context, zzaKo, lock, looper, googleapiavailabilitylight, map1, null, map3, null, arraylist1, new zza());
        zzaKq = new zzzi(context, zzaKo, lock, looper, googleapiavailabilitylight, map, zzg, map2, zza1, arraylist, new zzb());
        context = new ArrayMap();
        for (zzzg1 = map1.keySet().iterator(); zzzg1.hasNext(); context.put((com.google.android.gms.common.api.Api.zzc)zzzg1.next(), zzaKp)) { }
        for (zzzg1 = map.keySet().iterator(); zzzg1.hasNext(); context.put((com.google.android.gms.common.api.Api.zzc)zzzg1.next(), zzaKq)) { }
        zzaKr = Collections.unmodifiableMap(context);
    }

    private final void zza(ConnectionResult connectionresult)
    {
        zzaKz;
        JVM INSTR tableswitch 1 2: default 28
    //                   1 57
    //                   2 49;
           goto _L1 _L2 _L3
_L1:
        Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
_L5:
        zzaKz = 0;
        return;
_L3:
        zzaKo.zzc(connectionresult);
_L2:
        zzxa();
        if (true) goto _L5; else goto _L4
_L4:
    }

    static void zzb(zzyv zzyv1)
    {
        ConnectionResult connectionresult;
        boolean flag1;
        flag1 = true;
        connectionresult = zzyv1.zzaKv;
        if (connectionresult == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        if (connectionresult.zzaEP == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L3
_L3:
        flag = true;
_L27:
        if (!flag) goto _L5; else goto _L4
_L4:
        connectionresult = zzyv1.zzaKw;
        if (connectionresult == null) goto _L7; else goto _L6
_L6:
        if (connectionresult.zzaEP == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L7; else goto _L8
_L8:
        flag = true;
_L15:
        if (flag) goto _L10; else goto _L9
_L9:
        if (zzyv1.zzaKw != null && zzyv1.zzaKw.zzaEP == 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L11; else goto _L10
_L10:
        zzyv1.zzaKz;
        JVM INSTR tableswitch 1 2: default 108
    //                   1 165
    //                   2 154;
           goto _L12 _L13 _L14
_L12:
        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
_L16:
        zzyv1.zzaKz = 0;
_L18:
        return;
_L2:
        flag = false;
        continue; /* Loop/switch isn't completed */
_L7:
        flag = false;
          goto _L15
_L14:
        zzyv1.zzaKo.zzu(zzyv1.zzaKu);
_L13:
        zzyv1.zzxa();
          goto _L16
_L11:
        if (zzyv1.zzaKw == null) goto _L18; else goto _L17
_L17:
        if (zzyv1.zzaKz == 1)
        {
            zzyv1.zzxa();
            return;
        }
        zzyv1.zza(zzyv1.zzaKw);
        zzyv1 = zzyv1.zzaKp;
        if (!((zzzi) (zzyv1)).zzaLS.disconnect()) goto _L18; else goto _L19
_L19:
        ((zzzi) (zzyv1)).zzaLR.clear();
        return;
_L5:
        if (zzyv1.zzaKv == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        connectionresult = zzyv1.zzaKw;
        if (connectionresult == null) goto _L21; else goto _L20
_L20:
        if (connectionresult.zzaEP == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L21; else goto _L22
_L22:
        flag = flag1;
_L24:
        if (flag)
        {
            zzzi zzzi1 = zzyv1.zzaKq;
            if (zzzi1.zzaLS.disconnect())
            {
                zzzi1.zzaLR.clear();
            }
            zzyv1.zza(zzyv1.zzaKv);
            return;
        }
        break; /* Loop/switch isn't completed */
_L21:
        flag = false;
        if (true) goto _L24; else goto _L23
_L23:
        if (zzyv1.zzaKv == null || zzyv1.zzaKw == null) goto _L18; else goto _L25
_L25:
        ConnectionResult connectionresult1 = zzyv1.zzaKv;
        if (zzyv1.zzaKq.zzaLU < zzyv1.zzaKp.zzaLU)
        {
            connectionresult1 = zzyv1.zzaKw;
        }
        zzyv1.zza(connectionresult1);
        return;
        if (true) goto _L27; else goto _L26
_L26:
    }

    private final boolean zzc(zzyq.zza zza1)
    {
        zza1 = zza1.zzaJQ;
        if (!zzaKr.containsKey(zza1))
        {
            throw new IllegalArgumentException(String.valueOf("GoogleApiClient is not configured to use the API required for this call."));
        } else
        {
            return ((zzzi)zzaKr.get(zza1)).equals(zzaKq);
        }
    }

    private final void zzxa()
    {
        for (Iterator iterator = zzaKs.iterator(); iterator.hasNext(); ((zzaag)iterator.next()).zzqn()) { }
        zzaKs.clear();
    }

    private final PendingIntent zzxc()
    {
        if (zzaKt == null)
        {
            return null;
        } else
        {
            return PendingIntent.getActivity(mContext, System.identityHashCode(zzaKo), zzaKt.zzqp(), 0x8000000);
        }
    }

    public final ConnectionResult blockingConnect()
    {
        throw new UnsupportedOperationException();
    }

    public final ConnectionResult blockingConnect(long l, TimeUnit timeunit)
    {
        throw new UnsupportedOperationException();
    }

    public final void connect()
    {
        zzaKz = 2;
        zzaKx = false;
        zzaKw = null;
        zzaKv = null;
        zzaKp.connect();
        zzaKq.connect();
    }

    public final void disconnect()
    {
        zzaKw = null;
        zzaKv = null;
        zzaKz = 0;
        zzzi zzzi1 = zzaKp;
        if (zzzi1.zzaLS.disconnect())
        {
            zzzi1.zzaLR.clear();
        }
        zzzi1 = zzaKq;
        if (zzzi1.zzaLS.disconnect())
        {
            zzzi1.zzaLR.clear();
        }
        zzxa();
    }

    public final void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        printwriter.append(s).append("authClient").println(":");
        zzaKq.dump(String.valueOf(s).concat("  "), filedescriptor, printwriter, as);
        printwriter.append(s).append("anonClient").println(":");
        zzaKp.dump(String.valueOf(s).concat("  "), filedescriptor, printwriter, as);
    }

    public final boolean isConnected()
    {
        boolean flag1;
        flag1 = true;
        zzaKy.lock();
        if (!zzaKp.isConnected()) goto _L2; else goto _L1
_L1:
        boolean flag = flag1;
        if (zzaKq.isConnected()) goto _L4; else goto _L3
_L3:
        int i;
        if (zzaKw != null && zzaKw.zzaEP == 4)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        flag = flag1;
        if (i != 0) goto _L4; else goto _L5
_L5:
        i = zzaKz;
        if (i != 1) goto _L2; else goto _L6
_L6:
        flag = flag1;
_L4:
        zzaKy.unlock();
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L7
_L7:
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final boolean isConnecting()
    {
        zzaKy.lock();
        int i = zzaKz;
        boolean flag;
        if (i == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        zzaKy.unlock();
        return flag;
        Exception exception;
        exception;
        zzaKy.unlock();
        throw exception;
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        if (zzc(zza1))
        {
            boolean flag;
            if (zzaKw != null && zzaKw.zzaEP == 4)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                zza1.zzM(new Status(4, null, zzxc()));
                return zza1;
            } else
            {
                return zzaKq.zza(zza1);
            }
        } else
        {
            return zzaKp.zza(zza1);
        }
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        if (zzc(zza1))
        {
            boolean flag;
            if (zzaKw != null && zzaKw.zzaEP == 4)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                zza1.zzM(new Status(4, null, zzxc()));
                return zza1;
            } else
            {
                return zzaKq.zzb(zza1);
            }
        } else
        {
            return zzaKp.zzb(zza1);
        }
    }

    private class zza
        implements zzzp.zza
    {

        private final zzyv zzaKA;

        public final void zzc(ConnectionResult connectionresult)
        {
            zzaKA.zzaKy.lock();
            zzaKA.zzaKv = connectionresult;
            zzyv.zzb(zzaKA);
            zzaKA.zzaKy.unlock();
            return;
            connectionresult;
            zzaKA.zzaKy.unlock();
            throw connectionresult;
        }

        public final void zze(int i, boolean flag)
        {
            boolean flag1;
            flag1 = true;
            zzaKA.zzaKy.lock();
            if (zzaKA.zzaKx || zzaKA.zzaKw == null) goto _L2; else goto _L1
_L1:
            zzyv zzyv1;
            if (zzaKA.zzaKw.zzaEP != 0)
            {
                flag1 = false;
            }
              goto _L3
_L2:
            zzaKA.zzaKx = false;
            zzyv1 = zzaKA;
            zzyv1.zzaKo.zze(i, flag);
            zzyv1.zzaKw = null;
            zzyv1.zzaKv = null;
            zzaKA.zzaKy.unlock();
            return;
_L4:
            zzaKA.zzaKx = true;
            zzaKA.zzaKq.onConnectionSuspended(i);
            zzaKA.zzaKy.unlock();
            return;
            Exception exception;
            exception;
            zzaKA.zzaKy.unlock();
            throw exception;
_L3:
            if (flag1) goto _L4; else goto _L2
        }

        public final void zzu(Bundle bundle)
        {
            zzaKA.zzaKy.lock();
            zzyv zzyv1 = zzaKA;
            if (zzyv1.zzaKu != null) goto _L2; else goto _L1
_L1:
            zzyv1.zzaKu = bundle;
_L4:
            zzaKA.zzaKv = ConnectionResult.zzaIj;
            zzyv.zzb(zzaKA);
            zzaKA.zzaKy.unlock();
            return;
_L2:
            if (bundle == null) goto _L4; else goto _L3
_L3:
            zzyv1.zzaKu.putAll(bundle);
              goto _L4
            bundle;
            zzaKA.zzaKy.unlock();
            throw bundle;
        }

        zza()
        {
            zzaKA = zzyv.this;
            super();
        }
    }


    private class zzb
        implements zzzp.zza
    {

        private final zzyv zzaKA;

        public final void zzc(ConnectionResult connectionresult)
        {
            zzaKA.zzaKy.lock();
            zzaKA.zzaKw = connectionresult;
            zzyv.zzb(zzaKA);
            zzaKA.zzaKy.unlock();
            return;
            connectionresult;
            zzaKA.zzaKy.unlock();
            throw connectionresult;
        }

        public final void zze(int i, boolean flag)
        {
            zzaKA.zzaKy.lock();
            if (!zzaKA.zzaKx)
            {
                break MISSING_BLOCK_LABEL_67;
            }
            zzaKA.zzaKx = false;
            zzyv zzyv1 = zzaKA;
            zzyv1.zzaKo.zze(i, flag);
            zzyv1.zzaKw = null;
            zzyv1.zzaKv = null;
            zzaKA.zzaKy.unlock();
            return;
            zzaKA.zzaKx = true;
            zzaKA.zzaKp.onConnectionSuspended(i);
            zzaKA.zzaKy.unlock();
            return;
            Exception exception;
            exception;
            zzaKA.zzaKy.unlock();
            throw exception;
        }

        public final void zzu(Bundle bundle)
        {
            zzaKA.zzaKy.lock();
            zzaKA.zzaKw = ConnectionResult.zzaIj;
            zzyv.zzb(zzaKA);
            zzaKA.zzaKy.unlock();
            return;
            bundle;
            zzaKA.zzaKy.unlock();
            throw bundle;
        }

        zzb()
        {
            zzaKA = zzyv.this;
            super();
        }
    }

}
