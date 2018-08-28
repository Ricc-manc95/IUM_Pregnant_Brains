// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.stats.zza;
import java.util.HashMap;
import java.util.Set;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzn

final class zzo extends zzn
    implements android.os.Handler.Callback
{

    private final Handler mHandler;
    private final HashMap zzaQq = new HashMap();
    private final com.google.android.gms.common.stats.zza zzaQr = com.google.android.gms.common.stats.zza.zzzN();
    private final long zzaQs = 5000L;
    private final Context zzws;

    zzo(Context context)
    {
        zzws = context.getApplicationContext();
        mHandler = new Handler(context.getMainLooper(), this);
    }

    static HashMap zza(zzo zzo1)
    {
        return zzo1.zzaQq;
    }

    private final boolean zza(zza zza1, ServiceConnection serviceconnection, String s)
    {
        if (serviceconnection == null)
        {
            throw new NullPointerException(String.valueOf("ServiceConnection must not be null"));
        }
        HashMap hashmap = zzaQq;
        hashmap;
        JVM INSTR monitorenter ;
        zzb zzb1 = (zzb)zzaQq.get(zza1);
        if (zzb1 != null) goto _L2; else goto _L1
_L1:
        zzb1 = new zzb(zza1);
        zzb1.zza(serviceconnection, s);
        zzb1.zzcV(s);
        zzaQq.put(zza1, zzb1);
        zza1 = zzb1;
_L7:
        boolean flag = ((zzb) (zza1)).zzaQw;
        hashmap;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        mHandler.removeMessages(0, zza1);
        if (zzb1.zzaQv.contains(serviceconnection))
        {
            zza1 = String.valueOf(zza1);
            throw new IllegalStateException((new StringBuilder(String.valueOf(zza1).length() + 81)).append("Trying to bind a GmsServiceConnection that was already connected before.  config=").append(zza1).toString());
        }
        break MISSING_BLOCK_LABEL_165;
        zza1;
        hashmap;
        JVM INSTR monitorexit ;
        throw zza1;
        zzb1.zza(serviceconnection, s);
        zzb1.mState;
        JVM INSTR tableswitch 1 2: default 234
    //                   1 200
    //                   2 222;
           goto _L3 _L4 _L5
_L4:
        serviceconnection.onServiceConnected(zzb1.zzaql, zzb1.zzsE);
        zza1 = zzb1;
        continue; /* Loop/switch isn't completed */
_L5:
        zzb1.zzcV(s);
        zza1 = zzb1;
        continue; /* Loop/switch isn't completed */
_L3:
        zza1 = zzb1;
        if (true) goto _L7; else goto _L6
_L6:
    }

    static Context zzb(zzo zzo1)
    {
        return zzo1.zzws;
    }

    private final void zzb(zza zza1, ServiceConnection serviceconnection, String s)
    {
        if (serviceconnection == null)
        {
            throw new NullPointerException(String.valueOf("ServiceConnection must not be null"));
        }
        s = zzaQq;
        s;
        JVM INSTR monitorenter ;
        zzb zzb1 = (zzb)zzaQq.get(zza1);
        if (zzb1 != null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        zza1 = String.valueOf(zza1);
        throw new IllegalStateException((new StringBuilder(String.valueOf(zza1).length() + 50)).append("Nonexistent connection status for service config: ").append(zza1).toString());
        zza1;
        s;
        JVM INSTR monitorexit ;
        throw zza1;
        if (!zzb1.zzaQv.contains(serviceconnection))
        {
            zza1 = String.valueOf(zza1);
            throw new IllegalStateException((new StringBuilder(String.valueOf(zza1).length() + 76)).append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=").append(zza1).toString());
        }
        Object obj = zzb1.zzaQy.zzaQr;
        obj = zzb1.zzaQy.zzws;
        com.google.android.gms.common.stats.zza.zzb$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UKR5E9R6IOR58DNMSRJ5CDQ6IRRE7CKLC___0();
        zzb1.zzaQv.remove(serviceconnection);
        if (zzb1.zzaQv.isEmpty())
        {
            zza1 = mHandler.obtainMessage(0, zza1);
            mHandler.sendMessageDelayed(zza1, zzaQs);
        }
        s;
        JVM INSTR monitorexit ;
    }

    static zza zzc(zzo zzo1)
    {
        return zzo1.zzaQr;
    }

    public final boolean handleMessage(Message message)
    {
        zza zza1;
        switch (message.what)
        {
        default:
            return false;

        case 0: // '\0'
            zza1 = (zza)message.obj;
            break;
        }
        message = zzaQq;
        message;
        JVM INSTR monitorenter ;
        zzb zzb1 = (zzb)zzaQq.get(zza1);
        if (zzb1 == null)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        if (zzb1.zzaQv.isEmpty())
        {
            if (zzb1.zzaQw)
            {
                com.google.android.gms.common.stats.zza zza2 = zzb1.zzaQy.zzaQr;
                com.google.android.gms.common.stats.zza.zza(zzb1.zzaQy.zzws, zzb1.zzaQu);
                zzb1.zzaQw = false;
                zzb1.mState = 2;
            }
            zzaQq.remove(zza1);
        }
        message;
        JVM INSTR monitorexit ;
        return true;
        Exception exception;
        exception;
        message;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean zza(ComponentName componentname, ServiceConnection serviceconnection, String s)
    {
        return zza(new zza(componentname), serviceconnection, s);
    }

    public final boolean zza(String s, String s1, ServiceConnection serviceconnection, String s2)
    {
        return zza(new zza(s, s1), serviceconnection, s2);
    }

    public final void zzb(ComponentName componentname, ServiceConnection serviceconnection, String s)
    {
        zzb(new zza(componentname), serviceconnection, s);
    }

    public final void zzb(String s, String s1, ServiceConnection serviceconnection, String s2)
    {
        zzb(new zza(s, s1), serviceconnection, s2);
    }

    private class zzb
    {

        public int mState;
        public final zza zzaQu = new zza();
        public final Set zzaQv = new HashSet();
        public boolean zzaQw;
        private final zza zzaQx;
        public final zzo zzaQy;
        public ComponentName zzaql;
        public IBinder zzsE;

        public final void zza(ServiceConnection serviceconnection, String s)
        {
            zzo.zzc(zzaQy);
            zzo.zzb(zzaQy);
            zzaQx.zzzm();
            com.google.android.gms.common.stats.zza.zza$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7D662RJ4E9NMIP1FCDNMST35DPQ2UKR5E9R6IOR58DNMSRJ5CDQ6IRRE7D66KOBMC4NMOOBECSNL6T3ID5N6EEQCC5N68SJFD5I2UORFDPQ6ARJK5T4MST35DPQ3MAAM0();
            zzaQv.add(serviceconnection);
        }

        public final void zzcV(String s)
        {
            mState = 3;
            zzo.zzc(zzaQy);
            s = zzo.zzb(zzaQy);
            Intent intent = zzaQx.zzzm();
            zza zza1 = zzaQu;
            boolean flag;
            if (com.google.android.gms.common.stats.zza.zzc(s, intent))
            {
                Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
                flag = false;
            } else
            {
                flag = s.bindService(intent, zza1, 129);
            }
            zzaQw = flag;
            if (zzaQw)
            {
                break MISSING_BLOCK_LABEL_93;
            }
            mState = 2;
            zzo.zzc(zzaQy);
            com.google.android.gms.common.stats.zza.zza(zzo.zzb(zzaQy), zzaQu);
            return;
            s;
        }

        public zzb(zza zza1)
        {
            zzaQy = zzo.this;
            super();
            zzaQx = zza1;
            class zza
                implements ServiceConnection
            {

                private final zzb zzaQz;

                public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
                {
                    HashMap hashmap = com.google.android.gms.common.internal.zzo.zza(zzaQz.zzaQy);
                    hashmap;
                    JVM INSTR monitorenter ;
                    zzaQz.zzsE = ibinder;
                    zzaQz.zzaql = componentname;
                    for (Iterator iterator = zzaQz.zzaQv.iterator(); iterator.hasNext(); ((ServiceConnection)iterator.next()).onServiceConnected(componentname, ibinder)) { }
                    break MISSING_BLOCK_LABEL_78;
                    componentname;
                    hashmap;
                    JVM INSTR monitorexit ;
                    throw componentname;
                    zzaQz.mState = 1;
                    hashmap;
                    JVM INSTR monitorexit ;
                }

                public final void onServiceDisconnected(ComponentName componentname)
                {
                    HashMap hashmap = com.google.android.gms.common.internal.zzo.zza(zzaQz.zzaQy);
                    hashmap;
                    JVM INSTR monitorenter ;
                    zzaQz.zzsE = null;
                    zzaQz.zzaql = componentname;
                    for (Iterator iterator = zzaQz.zzaQv.iterator(); iterator.hasNext(); ((ServiceConnection)iterator.next()).onServiceDisconnected(componentname)) { }
                    break MISSING_BLOCK_LABEL_74;
                    componentname;
                    hashmap;
                    JVM INSTR monitorexit ;
                    throw componentname;
                    zzaQz.mState = 2;
                    hashmap;
                    JVM INSTR monitorexit ;
                }

                public zza()
                {
                    zzaQz = zzb.this;
                    super();
                }
            }

            mState = 2;
        }
    }


    private class zza
    {

        private final String mAction;
        private final String zzaQt;
        private final ComponentName zzaql;

        public final boolean equals(Object obj)
        {
            if (this != obj) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            if (!(obj instanceof zza))
            {
                return false;
            }
            obj = (zza)obj;
            Object obj1 = mAction;
            String s = ((zza) (obj)).mAction;
            boolean flag;
            if (obj1 == s || obj1 != null && obj1.equals(s))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            obj1 = zzaql;
            obj = ((zza) (obj)).zzaql;
            if (obj1 == obj || obj1 != null && obj1.equals(obj))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag) goto _L1; else goto _L3
_L3:
            return false;
        }

        public final int hashCode()
        {
            return Arrays.hashCode(new Object[] {
                mAction, zzaql
            });
        }

        public final String toString()
        {
            if (mAction == null)
            {
                return zzaql.flattenToString();
            } else
            {
                return mAction;
            }
        }

        public final Intent zzzm()
        {
            if (mAction != null)
            {
                return (new Intent(mAction)).setPackage(zzaQt);
            } else
            {
                return (new Intent()).setComponent(zzaql);
            }
        }

        public zza(ComponentName componentname)
        {
            mAction = null;
            zzaQt = null;
            if (componentname == null)
            {
                throw new NullPointerException("null reference");
            } else
            {
                zzaql = (ComponentName)componentname;
                return;
            }
        }

        public zza(String s, String s1)
        {
            if (TextUtils.isEmpty(s))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            }
            mAction = s;
            if (TextUtils.isEmpty(s1))
            {
                throw new IllegalArgumentException("Given String is empty or null");
            } else
            {
                zzaQt = s1;
                zzaql = null;
                return;
            }
        }
    }

}
