// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.gms.common.internal:
//            zzn, zzj, zzr, zzv

public abstract class zzf
{

    public final Context mContext;
    public final Handler mHandler;
    private int zzaPA;
    private final zzb zzaPB;
    private final zzc zzaPC;
    private final int zzaPD;
    private final String zzaPE;
    public AtomicInteger zzaPF;
    public int zzaPo;
    public long zzaPp;
    private long zzaPq;
    public int zzaPr;
    public long zzaPs;
    private final zzn zzaPt;
    private final Object zzaPu = new Object();
    private zzv zzaPv;
    public zzf zzaPw;
    private IInterface zzaPx;
    private final ArrayList zzaPy = new ArrayList();
    private zzh zzaPz;
    private final Object zzrY = new Object();

    protected zzf(Context context, Looper looper, zzn zzn1, GoogleApiAvailabilityLight googleapiavailabilitylight, int i, zzb zzb1, zzc zzc1, 
            String s)
    {
        zzaPA = 1;
        zzaPF = new AtomicInteger(0);
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("Context must not be null"));
        }
        mContext = (Context)context;
        if (looper == null)
        {
            throw new NullPointerException(String.valueOf("Looper must not be null"));
        }
        if (zzn1 == null)
        {
            throw new NullPointerException(String.valueOf("Supervisor must not be null"));
        }
        zzaPt = (zzn)zzn1;
        if (googleapiavailabilitylight == null)
        {
            throw new NullPointerException(String.valueOf("API availability must not be null"));
        } else
        {
            mHandler = new zzd(looper);
            zzaPD = i;
            zzaPB = zzb1;
            zzaPC = zzc1;
            zzaPE = s;
            return;
        }
    }

    static zzv zza(zzf zzf1, zzv zzv1)
    {
        zzf1.zzaPv = zzv1;
        return zzv1;
    }

    static Object zza(zzf zzf1)
    {
        return zzf1.zzaPu;
    }

    private final void zza(int i, IInterface iinterface)
    {
        boolean flag2 = true;
        boolean flag;
        boolean flag1;
        if (i == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (iinterface != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag == flag1)
        {
            flag = flag2;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        zzaPA = i;
        zzaPx = iinterface;
        i;
        JVM INSTR tableswitch 1 3: default 520
    //                   1 449
    //                   2 107
    //                   3 439;
           goto _L1 _L2 _L3 _L4
_L1:
        obj;
        JVM INSTR monitorexit ;
        return;
_L3:
        if (zzaPz == null) goto _L6; else goto _L5
_L5:
        Object obj1;
        String s;
        zzh zzh1;
        iinterface = String.valueOf(zzeD());
        obj1 = String.valueOf("com.google.android.gms");
        Log.e("GmsClient", (new StringBuilder(String.valueOf(iinterface).length() + 70 + String.valueOf(obj1).length())).append("Calling connect() while still connected, missing disconnect() for ").append(iinterface).append(" on ").append(((String) (obj1))).toString());
        obj1 = zzaPt;
        s = zzeD();
        zzh1 = zzaPz;
        if (zzaPE != null) goto _L8; else goto _L7
_L7:
        iinterface = mContext.getClass().getName();
_L11:
        ((zzn) (obj1)).zzb(s, "com.google.android.gms", zzh1, iinterface);
        zzaPF.incrementAndGet();
_L6:
        zzaPz = new zzh(zzaPF.get());
        obj1 = zzaPt;
        s = zzeD();
        zzh1 = zzaPz;
        if (zzaPE != null) goto _L10; else goto _L9
_L9:
        iinterface = mContext.getClass().getName();
_L12:
        if (!((zzn) (obj1)).zza(s, "com.google.android.gms", zzh1, iinterface))
        {
            iinterface = String.valueOf(zzeD());
            obj1 = String.valueOf("com.google.android.gms");
            Log.e("GmsClient", (new StringBuilder(String.valueOf(iinterface).length() + 34 + String.valueOf(obj1).length())).append("unable to connect to service: ").append(iinterface).append(" on ").append(((String) (obj1))).toString());
            i = zzaPF.get();
            mHandler.sendMessage(mHandler.obtainMessage(5, i, -1, new zzk(16, null)));
        }
          goto _L1
        iinterface;
        obj;
        JVM INSTR monitorexit ;
        throw iinterface;
_L8:
        iinterface = zzaPE;
          goto _L11
_L10:
        iinterface = zzaPE;
          goto _L12
_L4:
        zzaPq = System.currentTimeMillis();
          goto _L1
_L2:
        if (zzaPz == null) goto _L1; else goto _L13
_L13:
        obj1 = zzaPt;
        s = zzeD();
        zzh1 = zzaPz;
        if (zzaPE != null) goto _L15; else goto _L14
_L14:
        iinterface = mContext.getClass().getName();
_L16:
        ((zzn) (obj1)).zzb(s, "com.google.android.gms", zzh1, iinterface);
        zzaPz = null;
          goto _L1
_L15:
        iinterface = zzaPE;
          goto _L16
    }

    static void zza(zzf zzf1, int i, IInterface iinterface)
    {
        zzf1.zza(i, ((IInterface) (null)));
    }

    static void zza(zzf zzf1, ConnectionResult connectionresult)
    {
        zzf1.zzm(connectionresult);
    }

    private final boolean zza(int i, int j, IInterface iinterface)
    {
label0:
        {
            synchronized (zzrY)
            {
                if (zzaPA == i)
                {
                    break label0;
                }
            }
            return false;
        }
        zza(j, iinterface);
        obj;
        JVM INSTR monitorexit ;
        return true;
        iinterface;
        obj;
        JVM INSTR monitorexit ;
        throw iinterface;
    }

    static boolean zza(zzf zzf1, int i, int j, IInterface iinterface)
    {
        return zzf1.zza(i, j, iinterface);
    }

    static zzb zzb(zzf zzf1)
    {
        return zzf1.zzaPB;
    }

    static ArrayList zzc(zzf zzf1)
    {
        return zzf1.zzaPy;
    }

    static zzc zzd(zzf zzf1)
    {
        return zzf1.zzaPC;
    }

    private final void zzm(ConnectionResult connectionresult)
    {
        mHandler.sendMessage(mHandler.obtainMessage(3, zzaPF.get(), connectionresult.zzaEP, connectionresult.mPendingIntent));
    }

    public void disconnect()
    {
        zzaPF.incrementAndGet();
        ArrayList arraylist = zzaPy;
        arraylist;
        JVM INSTR monitorenter ;
        int j = zzaPy.size();
        int i = 0;
_L2:
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        synchronized ((zze)zzaPy.get(i))
        {
            zze.mListener = null;
        }
        i++;
        if (true) goto _L2; else goto _L1
        exception2;
        zze;
        JVM INSTR monitorexit ;
        throw exception2;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
_L1:
        zzaPy.clear();
        arraylist;
        JVM INSTR monitorexit ;
        synchronized (zzaPu)
        {
            zzaPv = null;
        }
        zza(1, ((IInterface) (null)));
        return;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
    }

    public final void dump$5166KOBMC4NMOOBECSNL6T3ID5N6EEQCD9GNCO9FD5NIUHJ9DHIK8PBJCDP6IS3KDTP3MJ3AC5R62BR9DSNL0SJ9DPQ5ESJ9EHIN4EQR9HL62TJ15TM62RJ75T9N8SJ9DPJJMAAM0(String s, PrintWriter printwriter)
    {
        Object obj1;
        int i;
        synchronized (zzrY)
        {
            i = zzaPA;
            obj1 = zzaPx;
        }
        printwriter.append(s).append("mConnectState=");
        i;
        JVM INSTR tableswitch 1 4: default 64
    //                   1 472
    //                   2 442
    //                   3 452
    //                   4 462;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        printwriter.print("UNKNOWN");
_L11:
        printwriter.append(" mService=");
        String s2;
        long l1;
        if (obj1 == null)
        {
            printwriter.println("null");
        } else
        {
            printwriter.append(zzeE()).append("@").println(Integer.toHexString(System.identityHashCode(((IInterface) (obj1)).asBinder())));
        }
        obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (zzaPq > 0L)
        {
            obj1 = printwriter.append(s).append("lastConnectedTime=");
            long l = zzaPq;
            String s1 = String.valueOf(((SimpleDateFormat) (obj)).format(new Date(zzaPq)));
            ((PrintWriter) (obj1)).println((new StringBuilder(String.valueOf(s1).length() + 21)).append(l).append(" ").append(s1).toString());
        }
        if (zzaPp <= 0L) goto _L7; else goto _L6
_L6:
        printwriter.append(s).append("lastSuspendedCause=");
        zzaPo;
        JVM INSTR tableswitch 1 2: default 244
    //                   1 515
    //                   2 526;
           goto _L8 _L9 _L10
_L10:
        break MISSING_BLOCK_LABEL_526;
_L8:
        printwriter.append(String.valueOf(zzaPo));
_L12:
        obj1 = printwriter.append(" lastSuspendedTime=");
        l1 = zzaPp;
        s2 = String.valueOf(((SimpleDateFormat) (obj)).format(new Date(zzaPp)));
        ((PrintWriter) (obj1)).println((new StringBuilder(String.valueOf(s2).length() + 21)).append(l1).append(" ").append(s2).toString());
_L7:
        if (zzaPs > 0L)
        {
            printwriter.append(s).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(zzaPr));
            s = printwriter.append(" lastFailedTime=");
            l1 = zzaPs;
            printwriter = String.valueOf(((SimpleDateFormat) (obj)).format(new Date(zzaPs)));
            s.println((new StringBuilder(String.valueOf(printwriter).length() + 21)).append(l1).append(" ").append(printwriter).toString());
        }
        return;
        s;
        obj;
        JVM INSTR monitorexit ;
        throw s;
_L3:
        printwriter.print("CONNECTING");
          goto _L11
_L4:
        printwriter.print("CONNECTED");
          goto _L11
_L5:
        printwriter.print("DISCONNECTING");
          goto _L11
_L2:
        printwriter.print("DISCONNECTED");
          goto _L11
_L9:
        printwriter.append("CAUSE_SERVICE_DISCONNECTED");
          goto _L12
        printwriter.append("CAUSE_NETWORK_LOST");
          goto _L12
    }

    public Account getAccount()
    {
        return null;
    }

    public final boolean isConnected()
    {
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (zzaPA == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final boolean isConnecting()
    {
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (zzaPA == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj;
        JVM INSTR monitorexit ;
        return flag;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void zza(int i, IBinder ibinder, Bundle bundle, int j)
    {
        mHandler.sendMessage(mHandler.obtainMessage(1, j, -1, new zzj(i, ibinder, bundle)));
    }

    public final void zza(zzf zzf1)
    {
        if (zzf1 == null)
        {
            throw new NullPointerException(String.valueOf("Connection progress callbacks cannot be null."));
        } else
        {
            zzaPw = (zzf)zzf1;
            zza(2, ((IInterface) (null)));
            return;
        }
    }

    public final void zza(zzr zzr1, Set set)
    {
        com.google.android.gms.common.internal.zzj zzj1;
        Bundle bundle = zzpF();
        zzj1 = new com.google.android.gms.common.internal.zzj(zzaPD);
        zzj1.zzaPZ = mContext.getPackageName();
        zzj1.zzaQc = bundle;
        if (set != null)
        {
            zzj1.zzaQb = (Scope[])set.toArray(new Scope[set.size()]);
        }
        if (!zzpZ()) goto _L2; else goto _L1
_L1:
        if (getAccount() != null)
        {
            set = getAccount();
        } else
        {
            set = new Account("<<default account>>", "com.google");
        }
        zzj1.zzaQd = set;
        if (zzr1 != null)
        {
            zzj1.zzaQa = zzr1.asBinder();
        }
_L7:
        zzr1 = ((zzr) (zzaPu));
        zzr1;
        JVM INSTR monitorenter ;
        if (zzaPv == null) goto _L4; else goto _L3
_L3:
        zzaPv.zza(new zzg(zzaPF.get()), zzj1);
_L5:
        return;
_L2:
        if (zzyQ())
        {
            zzj1.zzaQd = getAccount();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        Log.w("GmsClient", "mServiceBroker is null, client disconnected");
          goto _L5
        set;
        zzr1;
        JVM INSTR monitorexit ;
        try
        {
            throw set;
        }
        // Misplaced declaration of an exception variable
        catch (zzr zzr1)
        {
            Log.w("GmsClient", "service died");
            mHandler.sendMessage(mHandler.obtainMessage(4, zzaPF.get(), 1));
            return;
        }
        // Misplaced declaration of an exception variable
        catch (zzr zzr1)
        {
            Log.w("GmsClient", "Remote exception occurred", zzr1);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (zzr zzr1)
        {
            throw zzr1;
        }
        // Misplaced declaration of an exception variable
        catch (zzr zzr1)
        {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", zzr1);
        }
        zzm(new ConnectionResult(8, null, "IGmsServiceBroker.getService failed."));
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public abstract String zzeD();

    public abstract String zzeE();

    public abstract IInterface zzi(IBinder ibinder);

    public Bundle zzpF()
    {
        return new Bundle();
    }

    public boolean zzpZ()
    {
        return false;
    }

    public final boolean zzqo()
    {
        return false;
    }

    public final Intent zzqp()
    {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    public final Bundle zzvJ()
    {
        return null;
    }

    public final boolean zzwt()
    {
        return true;
    }

    public final IBinder zzwu()
    {
label0:
        {
            synchronized (zzaPu)
            {
                if (zzaPv != null)
                {
                    break label0;
                }
            }
            return null;
        }
        IBinder ibinder = zzaPv.asBinder();
        obj;
        JVM INSTR monitorexit ;
        return ibinder;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final IInterface zzyP()
        throws DeadObjectException
    {
        Object obj = zzrY;
        obj;
        JVM INSTR monitorenter ;
        if (zzaPA == 4)
        {
            throw new DeadObjectException();
        }
        break MISSING_BLOCK_LABEL_28;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        if (!isConnected())
        {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
        IInterface iinterface;
        boolean flag;
        if (zzaPx != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_73;
        }
        throw new IllegalStateException(String.valueOf("Client is connected but service is null"));
        iinterface = zzaPx;
        obj;
        JVM INSTR monitorexit ;
        return iinterface;
    }

    public boolean zzyQ()
    {
        return false;
    }

    protected Set zzyR()
    {
        return Collections.EMPTY_SET;
    }


    private class zzd extends Handler
    {

        private final com.google.android.gms.common.internal.zzf zzaPH;

        public final void handleMessage(Message message)
        {
            Object obj = null;
            boolean flag2 = true;
            if (zzaPH.zzaPF.get() != message.arg1)
            {
                boolean flag = flag2;
                if (message.what != 2)
                {
                    flag = flag2;
                    if (message.what != 1)
                    {
                        if (message.what == 5)
                        {
                            flag = flag2;
                        } else
                        {
                            flag = false;
                        }
                    }
                }
                if (flag)
                {
                    message = (zze)message.obj;
                    message.zzyT();
                    message.unregister();
                }
                return;
            }
            if ((message.what == 1 || message.what == 5) && !zzaPH.isConnecting())
            {
                message = (zze)message.obj;
                message.zzyT();
                message.unregister();
                return;
            }
            if (message.what == 3)
            {
                if (message.obj instanceof PendingIntent)
                {
                    obj = (PendingIntent)message.obj;
                }
                message = new ConnectionResult(message.arg2, ((PendingIntent) (obj)));
                zzaPH.zzaPw.zzg(message);
                obj = zzaPH;
                obj.zzaPr = ((ConnectionResult) (message)).zzaEP;
                obj.zzaPs = System.currentTimeMillis();
                return;
            }
            if (message.what == 4)
            {
                com.google.android.gms.common.internal.zzf.zza(zzaPH, 4, null);
                if (com.google.android.gms.common.internal.zzf.zzb(zzaPH) != null)
                {
                    com.google.android.gms.common.internal.zzf.zzb(zzaPH).onConnectionSuspended(message.arg2);
                }
                obj = zzaPH;
                obj.zzaPo = message.arg2;
                obj.zzaPp = System.currentTimeMillis();
                com.google.android.gms.common.internal.zzf.zza(zzaPH, 4, 1, null);
                return;
            }
            if (message.what == 2 && !zzaPH.isConnected())
            {
                message = (zze)message.obj;
                message.zzyT();
                message.unregister();
                return;
            }
            boolean flag1;
            if (message.what == 2 || message.what == 1 || message.what == 5)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_466;
            }
            synchronized ((zze)message.obj)
            {
                obj = ((zze) (message)).mListener;
                if (((zze) (message)).zzaPI)
                {
                    String s = String.valueOf(message);
                    Log.w("GmsClient", (new StringBuilder(String.valueOf(s).length() + 47)).append("Callback proxy ").append(s).append(" being reused. This is not safe.").toString());
                }
            }
            if (obj != null)
            {
                try
                {
                    message.zzA(obj);
                }
                catch (RuntimeException runtimeexception)
                {
                    message.zzyT();
                    throw runtimeexception;
                }
            } else
            {
                message.zzyT();
            }
            message;
            JVM INSTR monitorenter ;
            message.zzaPI = true;
            message;
            JVM INSTR monitorexit ;
            message.unregister();
            return;
            exception;
            message;
            JVM INSTR monitorexit ;
            throw exception;
            Exception exception1;
            exception1;
            message;
            JVM INSTR monitorexit ;
            throw exception1;
            int i = message.what;
            Log.wtf("GmsClient", (new StringBuilder(45)).append("Don't know how to handle message: ").append(i).toString(), new Exception());
            return;
        }

        public zzd(Looper looper)
        {
            zzaPH = zzf.this;
            super(looper);
        }

        private class zzb
        {

            public abstract void onConnected(Bundle bundle);

            public abstract void onConnectionSuspended(int i);
        }

    }


    private class zzh
        implements ServiceConnection
    {

        private final com.google.android.gms.common.internal.zzf zzaPH;
        private final int zzaPK;

        public final void onServiceConnected(ComponentName componentname, IBinder ibinder)
        {
            if (ibinder == null)
            {
                com.google.android.gms.common.internal.zzf.zza(zzaPH, new ConnectionResult(8, null, "ServiceBroker IBinder is null"));
                return;
            }
            Object obj = com.google.android.gms.common.internal.zzf.zza(zzaPH);
            obj;
            JVM INSTR monitorenter ;
            com.google.android.gms.common.internal.zzf zzf1 = zzaPH;
            if (ibinder != null) goto _L2; else goto _L1
_L1:
            componentname = null;
_L4:
            com.google.android.gms.common.internal.zzf.zza(zzf1, componentname);
            obj;
            JVM INSTR monitorexit ;
            componentname = zzaPH;
            int i = zzaPK;
            ((com.google.android.gms.common.internal.zzf) (componentname)).mHandler.sendMessage(((com.google.android.gms.common.internal.zzf) (componentname)).mHandler.obtainMessage(5, i, -1, componentname. new zzk(0, null)));
            return;
_L2:
            componentname = ibinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (componentname == null)
            {
                break MISSING_BLOCK_LABEL_124;
            }
            if (componentname instanceof zzv)
            {
                componentname = (zzv)componentname;
                continue; /* Loop/switch isn't completed */
            }
            componentname = new zzv.zza.zza(ibinder);
            if (true) goto _L4; else goto _L3
_L3:
            componentname;
            obj;
            JVM INSTR monitorexit ;
            throw componentname;
        }

        public final void onServiceDisconnected(ComponentName componentname)
        {
            synchronized (com.google.android.gms.common.internal.zzf.zza(zzaPH))
            {
                com.google.android.gms.common.internal.zzf.zza(zzaPH, null);
            }
            zzaPH.mHandler.sendMessage(zzaPH.mHandler.obtainMessage(4, zzaPK, 1));
            return;
            exception;
            componentname;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public zzh(int i)
        {
            zzaPH = zzf.this;
            super();
            zzaPK = i;
        }
    }


    private class zzk extends zza
    {
        private class zza extends zze
        {
            private class zze
            {

                public Object mListener;
                private final com.google.android.gms.common.internal.zzf zzaPH;
                public boolean zzaPI;

                public final void unregister()
                {
                    this;
                    JVM INSTR monitorenter ;
                    mListener = null;
                    this;
                    JVM INSTR monitorexit ;
                    synchronized (com.google.android.gms.common.internal.zzf.zzc(zzaPH))
                    {
                        com.google.android.gms.common.internal.zzf.zzc(zzaPH).remove(this);
                    }
                    return;
                    obj;
                    this;
                    JVM INSTR monitorexit ;
                    throw obj;
                    exception;
                    obj;
                    JVM INSTR monitorexit ;
                    throw exception;
                }

                protected abstract void zzA(Object obj);

                protected abstract void zzyT();

                public zze(Object obj)
                {
                    zzaPH = zzf.this;
                    super();
                    mListener = obj;
                    zzaPI = false;
                }
            }


            private final int statusCode;
            private final Bundle zzaPG;
            private final com.google.android.gms.common.internal.zzf zzaPH;

            protected final void zzA(Object obj)
            {
                Object obj1 = null;
                if ((Boolean)obj != null) goto _L2; else goto _L1
_L1:
                com.google.android.gms.common.internal.zzf.zza(zzaPH, 1, null);
_L4:
                return;
_L2:
                switch (statusCode)
                {
                default:
                    com.google.android.gms.common.internal.zzf.zza(zzaPH, 1, null);
                    obj = obj1;
                    if (zzaPG != null)
                    {
                        obj = (PendingIntent)zzaPG.getParcelable("pendingIntent");
                    }
                    zzn(new ConnectionResult(statusCode, ((PendingIntent) (obj))));
                    return;

                case 0: // '\0'
                    if (!zzyS())
                    {
                        com.google.android.gms.common.internal.zzf.zza(zzaPH, 1, null);
                        zzn(new ConnectionResult(8, null));
                        return;
                    }
                    break;

                case 10: // '\n'
                    com.google.android.gms.common.internal.zzf.zza(zzaPH, 1, null);
                    throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            protected abstract void zzn(ConnectionResult connectionresult);

            protected abstract boolean zzyS();

            protected final void zzyT()
            {
            }

            protected zza(int i, Bundle bundle)
            {
                zzaPH = zzf.this;
                super(Boolean.valueOf(true));
                statusCode = i;
                zzaPG = bundle;
            }
        }


        private final com.google.android.gms.common.internal.zzf zzaPH;

        protected final void zzn(ConnectionResult connectionresult)
        {
            zzaPH.zzaPw.zzg(connectionresult);
            com.google.android.gms.common.internal.zzf zzf1 = zzaPH;
            zzf1.zzaPr = connectionresult.zzaEP;
            zzf1.zzaPs = System.currentTimeMillis();
        }

        protected final boolean zzyS()
        {
            zzaPH.zzaPw.zzg(ConnectionResult.zzaIj);
            return true;
        }

        public zzk(int i, Bundle bundle)
        {
            zzaPH = zzf.this;
            super(i, bundle);
        }
    }


    private class zzj extends zza
    {

        private final com.google.android.gms.common.internal.zzf zzaPH;
        private final IBinder zzaPL;

        protected final void zzn(ConnectionResult connectionresult)
        {
            if (com.google.android.gms.common.internal.zzf.zzd(zzaPH) != null)
            {
                com.google.android.gms.common.internal.zzf.zzd(zzaPH).onConnectionFailed(connectionresult);
            }
            com.google.android.gms.common.internal.zzf zzf1 = zzaPH;
            zzf1.zzaPr = connectionresult.zzaEP;
            zzf1.zzaPs = System.currentTimeMillis();
        }

        protected final boolean zzyS()
        {
            String s;
            try
            {
                s = zzaPL.getInterfaceDescriptor();
            }
            catch (RemoteException remoteexception)
            {
                Log.w("GmsClient", "service probably died");
                return false;
            }
            if (!zzaPH.zzeE().equals(s))
            {
                String s1 = String.valueOf(zzaPH.zzeE());
                Log.e("GmsClient", (new StringBuilder(String.valueOf(s1).length() + 34 + String.valueOf(s).length())).append("service descriptor mismatch: ").append(s1).append(" vs. ").append(s).toString());
            } else
            {
                IInterface iinterface = zzaPH.zzi(zzaPL);
                if (iinterface != null && com.google.android.gms.common.internal.zzf.zza(zzaPH, 2, 3, iinterface))
                {
                    com.google.android.gms.common.internal.zzf zzf1 = zzaPH;
                    if (com.google.android.gms.common.internal.zzf.zzb(zzaPH) != null)
                    {
                        com.google.android.gms.common.internal.zzf.zzb(zzaPH).onConnected(null);
                    }
                    return true;
                }
            }
            return false;
        }

        public zzj(int i, IBinder ibinder, Bundle bundle)
        {
            zzaPH = zzf.this;
            super(i, bundle);
            zzaPL = ibinder;
        }

        private class zzc
        {

            public abstract void onConnectionFailed(ConnectionResult connectionresult);
        }

    }


    private class zzf
    {

        public abstract void zzg(ConnectionResult connectionresult);
    }


    private class zzg extends zzu.zza
    {

        private com.google.android.gms.common.internal.zzf zzaPJ;
        private final int zzaPK;

        public final void zza(int i, Bundle bundle)
        {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        public final void zza(int i, IBinder ibinder, Bundle bundle)
        {
            if (zzaPJ == null)
            {
                throw new NullPointerException(String.valueOf("onPostInitComplete can be called only once per call to getRemoteService"));
            } else
            {
                zzaPJ.zza(i, ibinder, bundle, zzaPK);
                zzaPJ = null;
                return;
            }
        }

        public zzg(int i)
        {
            zzaPJ = com.google.android.gms.common.internal.zzf.this;
            zzaPK = i;
        }
    }

}
