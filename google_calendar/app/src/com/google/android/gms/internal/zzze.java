// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzr;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// Referenced classes of package com.google.android.gms.internal:
//            zzzh, zzbdm, zzzi, zzzj, 
//            zzzg, zzzd

public final class zzze
    implements zzzh
{

    public final Context mContext;
    private final com.google.android.gms.common.api.Api.zza zzaJk;
    public final zzg zzaKD;
    private final Map zzaKF;
    public final GoogleApiAvailabilityLight zzaKH;
    private ConnectionResult zzaKL;
    public final zzzi zzaKV;
    private int zzaKY;
    public int zzaKZ;
    public final Lock zzaKy;
    public int zzaLa;
    private final Bundle zzaLb = new Bundle();
    private final Set zzaLc = new HashSet();
    public zzbdm zzaLd;
    public int zzaLe;
    public boolean zzaLf;
    public boolean zzaLg;
    public zzr zzaLh;
    public boolean zzaLi;
    public boolean zzaLj;
    public ArrayList zzaLk;

    public zzze(zzzi zzzi1, zzg zzg1, Map map, GoogleApiAvailabilityLight googleapiavailabilitylight, com.google.android.gms.common.api.Api.zza zza1, Lock lock, Context context)
    {
        zzaKZ = 0;
        zzaLk = new ArrayList();
        zzaKV = zzzi1;
        zzaKD = zzg1;
        zzaKF = map;
        zzaKH = googleapiavailabilitylight;
        zzaJk = zza1;
        zzaKy = lock;
        mContext = context;
    }

    private final void zzaq(boolean flag)
    {
        if (zzaLd != null)
        {
            if (zzaLd.isConnected() && flag)
            {
                zzaLd.zzUk();
            }
            zzaLd.disconnect();
            zzaLh = null;
        }
    }

    private static String zzfH(int i)
    {
        switch (i)
        {
        default:
            return "UNKNOWN";

        case 0: // '\0'
            return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";

        case 1: // '\001'
            return "STEP_GETTING_REMOTE_SERVICE";
        }
    }

    private final void zzxs()
    {
        ArrayList arraylist = (ArrayList)zzaLk;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            ((Future)obj).cancel(true);
        }

        zzaLk.clear();
    }

    public final void begin()
    {
        HashMap hashmap;
label0:
        {
            zzaKV.zzaLR.clear();
            zzaLf = false;
            zzaKL = null;
            zzaKZ = 0;
            zzaLe = 2;
            zzaLg = false;
            zzaLi = false;
            hashmap = new HashMap();
            Iterator iterator = zzaKF.keySet().iterator();
label1:
            do
            {
label2:
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                    Api api = (Api)iterator.next();
                    Object obj = zzaKV.zzaLC;
                    if (api.zzaII != null)
                    {
                        obj = (com.google.android.gms.common.api.Api.zze)((Map) (obj)).get(api.zzaII);
                        int i = ((Integer)zzaKF.get(api)).intValue();
                        if (((com.google.android.gms.common.api.Api.zze) (obj)).zzpZ())
                        {
                            zzaLf = true;
                            if (i < zzaLe)
                            {
                                zzaLe = i;
                            }
                            if (i != 0)
                            {
                                Set set = zzaLc;
                                if (api.zzaII == null)
                                {
                                    break label2;
                                }
                                set.add(api.zzaII);
                            }
                        }
                        hashmap.put(obj, new zza(api, i));
                    } else
                    {
                        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
                    }
                }
            } while (true);
            throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
        }
        if (zzaLf)
        {
            zzaKD.zzaPP = Integer.valueOf(System.identityHashCode(zzaKV.zzaKo));
            zze zze1 = new zze();
            zzaLd = (zzbdm)zzaJk.zza(mContext, zzaKV.zzaKo.getLooper(), zzaKD, zzaKD.zzaPO, zze1, zze1);
        }
        zzaLa = zzaKV.zzaLC.size();
        zzaLk.add(zzzj.zzaLY.submit(new zzb(hashmap)));
    }

    public final void connect()
    {
    }

    public final boolean disconnect()
    {
        zzxs();
        zzaq(true);
        zzaKV.zzh(null);
        return true;
    }

    public final void onConnected(Bundle bundle)
    {
        if (zzfG(1))
        {
            if (bundle != null)
            {
                zzaLb.putAll(bundle);
            }
            if (zzxn())
            {
                zzxq();
                return;
            }
        }
    }

    public final void onConnectionSuspended(int i)
    {
        zzf(new ConnectionResult(8, null));
    }

    public final zzyq.zza zza(zzyq.zza zza1)
    {
        zzaKV.zzaKo.zzaLw.add(zza1);
        return zza1;
    }

    public final void zza(ConnectionResult connectionresult, Api api, int i)
    {
        if (zzfG(1))
        {
            zzb(connectionresult, api, i);
            if (zzxn())
            {
                zzxq();
                return;
            }
        }
    }

    public final zzyq.zza zzb(zzyq.zza zza1)
    {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }

    final void zzb(ConnectionResult connectionresult, Api api, int i)
    {
        boolean flag = true;
        if (i == 2) goto _L2; else goto _L1
_L1:
        if (i != 1) goto _L4; else goto _L3
_L3:
        Map map;
        if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = 1;
        } else
        if (zzaKH.getErrorResolutionIntent(connectionresult.zzaEP) != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L5; else goto _L4
_L4:
        i = ((flag) ? 1 : 0);
        if (zzaKL == null) goto _L7; else goto _L6
_L6:
        if (0x7fffffff >= zzaKY) goto _L5; else goto _L8
_L8:
        i = ((flag) ? 1 : 0);
_L7:
        if (i != 0)
        {
            zzaKL = connectionresult;
            zzaKY = 0x7fffffff;
        }
_L2:
        map = zzaKV.zzaLR;
        if (api.zzaII != null)
        {
            map.put(api.zzaII, connectionresult);
            return;
        } else
        {
            throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
        }
_L5:
        i = 0;
        if (true) goto _L7; else goto _L9
_L9:
    }

    final boolean zze(ConnectionResult connectionresult)
    {
label0:
        {
            boolean flag2 = false;
            if (zzaLe != 2)
            {
                boolean flag1 = flag2;
                if (zzaLe != 1)
                {
                    break label0;
                }
                boolean flag;
                if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                flag1 = flag2;
                if (flag)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    final void zzf(ConnectionResult connectionresult)
    {
        boolean flag1 = true;
        zzxs();
        boolean flag;
        if (connectionresult.zzaEP != 0 && connectionresult.mPendingIntent != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            flag1 = false;
        }
        zzaq(flag1);
        zzaKV.zzh(connectionresult);
        zzaKV.zzaLV.zzc(connectionresult);
    }

    final boolean zzfG(int i)
    {
        if (zzaKZ != i)
        {
            Object obj = zzaKV.zzaKo;
            Object obj1 = new StringWriter();
            ((GoogleApiClient) (obj)).dump("", null, new PrintWriter(((java.io.Writer) (obj1))), null);
            Log.w("GoogleApiClientConnecting", ((StringWriter) (obj1)).toString());
            obj = String.valueOf(this);
            Log.w("GoogleApiClientConnecting", (new StringBuilder(String.valueOf(obj).length() + 23)).append("Unexpected callback in ").append(((String) (obj))).toString());
            int j = zzaLa;
            Log.w("GoogleApiClientConnecting", (new StringBuilder(33)).append("mRemainingConnections=").append(j).toString());
            obj = String.valueOf(zzfH(zzaKZ));
            obj1 = String.valueOf(zzfH(i));
            Log.wtf("GoogleApiClientConnecting", (new StringBuilder(String.valueOf(obj).length() + 70 + String.valueOf(obj1).length())).append("GoogleApiClient connecting is in step ").append(((String) (obj))).append(" but received callback for step ").append(((String) (obj1))).toString(), new Exception());
            zzf(new ConnectionResult(8, null));
            return false;
        } else
        {
            return true;
        }
    }

    final boolean zzxn()
    {
        zzaLa = zzaLa - 1;
        if (zzaLa > 0)
        {
            return false;
        }
        if (zzaLa < 0)
        {
            zzzg zzzg1 = zzaKV.zzaKo;
            StringWriter stringwriter = new StringWriter();
            zzzg1.dump("", null, new PrintWriter(stringwriter), null);
            Log.w("GoogleApiClientConnecting", stringwriter.toString());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            zzf(new ConnectionResult(8, null));
            return false;
        }
        if (zzaKL != null)
        {
            zzaKV.zzaLU = zzaKY;
            zzf(zzaKL);
            return false;
        } else
        {
            return true;
        }
    }

    final void zzxo()
    {
        if (zzaLa == 0 && (!zzaLf || zzaLg))
        {
            ArrayList arraylist = new ArrayList();
            zzaKZ = 1;
            zzaLa = zzaKV.zzaLC.size();
            Iterator iterator = zzaKV.zzaLC.keySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                com.google.android.gms.common.api.Api.zzc zzc1 = (com.google.android.gms.common.api.Api.zzc)iterator.next();
                if (zzaKV.zzaLR.containsKey(zzc1))
                {
                    if (zzxn())
                    {
                        zzxq();
                    }
                } else
                {
                    arraylist.add((com.google.android.gms.common.api.Api.zze)zzaKV.zzaLC.get(zzc1));
                }
            } while (true);
            if (!arraylist.isEmpty())
            {
                zzaLk.add(zzzj.zzaLY.submit(new zzc(arraylist)));
                return;
            }
        }
    }

    final void zzxq()
    {
        Object obj;
        obj = zzaKV;
        ((zzzi) (obj)).zzaKy.lock();
        ((zzzi) (obj)).zzaKo.zzxx();
        obj.zzaLS = new zzzd(((zzzi) (obj)));
        ((zzzi) (obj)).zzaLS.begin();
        ((zzzi) (obj)).zzaLP.signalAll();
        ((zzzi) (obj)).zzaKy.unlock();
        zzzj.zzaLY.execute(new _cls1());
        if (zzaLd != null)
        {
            if (zzaLi)
            {
                zzaLd.zza(zzaLh, zzaLj);
            }
            zzaq(false);
        }
        com.google.android.gms.common.api.Api.zzc zzc1;
        for (obj = zzaKV.zzaLR.keySet().iterator(); ((Iterator) (obj)).hasNext(); ((com.google.android.gms.common.api.Api.zze)zzaKV.zzaLC.get(zzc1)).disconnect())
        {
            zzc1 = (com.google.android.gms.common.api.Api.zzc)((Iterator) (obj)).next();
        }

        break MISSING_BLOCK_LABEL_186;
        Exception exception;
        exception;
        ((zzzi) (obj)).zzaKy.unlock();
        throw exception;
        Bundle bundle;
        if (zzaLb.isEmpty())
        {
            bundle = null;
        } else
        {
            bundle = zzaLb;
        }
        zzaKV.zzaLV.zzu(bundle);
        return;
    }

    final void zzxr()
    {
        zzaLf = false;
        zzaKV.zzaKo.zzaLD = Collections.emptySet();
        Iterator iterator = zzaLc.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            com.google.android.gms.common.api.Api.zzc zzc1 = (com.google.android.gms.common.api.Api.zzc)iterator.next();
            if (!zzaKV.zzaLR.containsKey(zzc1))
            {
                zzaKV.zzaLR.put(zzc1, new ConnectionResult(17, null));
            }
        } while (true);
    }

    private class zza
        implements com.google.android.gms.common.internal.zzf.zzf
    {

        private final Api zzaGo;
        public final int zzaKm;
        private final WeakReference zzaLm;

        public final void zzg(ConnectionResult connectionresult)
        {
            zzze zzze1;
            boolean flag2;
            flag2 = true;
            zzze1 = (zzze)zzaLm.get();
            if (zzze1 == null)
            {
                return;
            }
            boolean flag;
            if (Looper.myLooper() == zzze1.zzaKV.zzaKo.getLooper())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("onReportServiceBinding must be called on the GoogleApiClient handler thread"));
            }
            zzze1.zzaKy.lock();
            boolean flag3 = zzze1.zzfG(0);
            if (!flag3)
            {
                zzze1.zzaKy.unlock();
                return;
            }
            boolean flag1;
            if (connectionresult.zzaEP == 0)
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                break MISSING_BLOCK_LABEL_117;
            }
            zzze1.zzb(connectionresult, zzaGo, zzaKm);
            if (zzze1.zzxn())
            {
                zzze1.zzxo();
            }
            zzze1.zzaKy.unlock();
            return;
            connectionresult;
            zzze1.zzaKy.unlock();
            throw connectionresult;
        }

        public zza(Api api, int i)
        {
            zzaLm = new WeakReference(zzze.this);
            zzaGo = api;
            zzaKm = i;
        }
    }


    private class zze
        implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    {

        private final zzze zzaLl;

        public final void onConnected(Bundle bundle)
        {
            zzaLl.zzaLd.zza(zzaLl. new zzd());
        }

        public final void onConnectionFailed(ConnectionResult connectionresult)
        {
            zzaLl.zzaKy.lock();
            if (!zzaLl.zze(connectionresult)) goto _L2; else goto _L1
_L1:
            zzaLl.zzxr();
            zzaLl.zzxo();
_L4:
            zzaLl.zzaKy.unlock();
            return;
_L2:
            zzaLl.zzf(connectionresult);
            if (true) goto _L4; else goto _L3
_L3:
            connectionresult;
            zzaLl.zzaKy.unlock();
            throw connectionresult;
        }

        public final void onConnectionSuspended(int i)
        {
        }

        zze()
        {
            zzaLl = zzze.this;
            super();
        }

        private class zzd extends zzbdq
        {

            private final WeakReference zzaLm;

            public final void zzb(zzbea zzbea)
            {
                zzze zzze1 = (zzze)zzaLm.get();
                if (zzze1 == null)
                {
                    return;
                } else
                {
                    zzzi zzzi1 = zzze1.zzaKV;
                    class _cls1 extends zzzi.zza
                    {

                        private final zzze zzaLs;
                        private final zzbea zzaLt;

                        public final void zzxm()
                        {
                            zzze zzze2;
                            Object obj;
                            boolean flag2;
                            boolean flag4;
                            flag4 = false;
                            flag2 = false;
                            zzze2 = zzaLs;
                            obj = zzaLt;
                            if (!zzze2.zzfG(0)) goto _L2; else goto _L1
_L1:
                            Object obj1;
                            obj1 = ((zzbea) (obj)).zzaQR;
                            boolean flag;
                            if (((ConnectionResult) (obj1)).zzaEP == 0)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                            if (!flag) goto _L4; else goto _L3
_L3:
                            obj1 = ((zzbea) (obj)).zzcmt;
                            obj = ((zzaf) (obj1)).zzaQR;
                            flag = flag2;
                            if (((ConnectionResult) (obj)).zzaEP == 0)
                            {
                                flag = true;
                            }
                            if (flag) goto _L6; else goto _L5
_L5:
                            obj1 = String.valueOf(obj);
                            Log.wtf("GoogleApiClientConnecting", (new StringBuilder(String.valueOf(obj1).length() + 48)).append("Sign-in succeeded with resolve account failure: ").append(((String) (obj1))).toString(), new Exception());
                            zzze2.zzf(((ConnectionResult) (obj)));
_L2:
                            return;
_L6:
                            zzze2.zzaLg = true;
                            zzze2.zzaLh = com.google.android.gms.common.internal.zzr.zza.zzdc(((zzaf) (obj1)).zzaPk);
                            zzze2.zzaLi = ((zzaf) (obj1)).zzaLi;
                            zzze2.zzaLj = ((zzaf) (obj1)).zzaQS;
                            if (zzze2.zzaLa == 0 && (!zzze2.zzaLf || zzze2.zzaLg))
                            {
                                ArrayList arraylist = new ArrayList();
                                zzze2.zzaKZ = 1;
                                zzze2.zzaLa = zzze2.zzaKV.zzaLC.size();
                                obj1 = zzze2.zzaKV.zzaLC.keySet().iterator();
                                do
                                {
                                    if (!((Iterator) (obj1)).hasNext())
                                    {
                                        break;
                                    }
                                    com.google.android.gms.common.api.Api.zzc zzc1 = (com.google.android.gms.common.api.Api.zzc)((Iterator) (obj1)).next();
                                    if (zzze2.zzaKV.zzaLR.containsKey(zzc1))
                                    {
                                        if (zzze2.zzxn())
                                        {
                                            zzze2.zzxq();
                                        }
                                    } else
                                    {
                                        arraylist.add((com.google.android.gms.common.api.Api.zze)zzze2.zzaKV.zzaLC.get(zzc1));
                                    }
                                } while (true);
                                if (!arraylist.isEmpty())
                                {
                                    zzze2.zzaLk.add(zzzj.zzaLY.submit(zzze2. new zzc(arraylist)));
                                    return;
                                }
                            }
                            continue; /* Loop/switch isn't completed */
_L4:
label0:
                            {
                                if (zzze2.zzaLe != 2)
                                {
                                    boolean flag3 = flag4;
                                    if (zzze2.zzaLe != 1)
                                    {
                                        break label0;
                                    }
                                    boolean flag1;
                                    if (((ConnectionResult) (obj1)).zzaEP != 0 && ((ConnectionResult) (obj1)).mPendingIntent != null)
                                    {
                                        flag1 = true;
                                    } else
                                    {
                                        flag1 = false;
                                    }
                                    flag3 = flag4;
                                    if (flag1)
                                    {
                                        break label0;
                                    }
                                }
                                flag3 = true;
                            }
                            if (flag3)
                            {
                                zzze2.zzxr();
                                if (zzze2.zzaLa == 0 && (!zzze2.zzaLf || zzze2.zzaLg))
                                {
                                    ArrayList arraylist1 = new ArrayList();
                                    zzze2.zzaKZ = 1;
                                    zzze2.zzaLa = zzze2.zzaKV.zzaLC.size();
                                    obj1 = zzze2.zzaKV.zzaLC.keySet().iterator();
                                    do
                                    {
                                        if (!((Iterator) (obj1)).hasNext())
                                        {
                                            break;
                                        }
                                        com.google.android.gms.common.api.Api.zzc zzc2 = (com.google.android.gms.common.api.Api.zzc)((Iterator) (obj1)).next();
                                        if (zzze2.zzaKV.zzaLR.containsKey(zzc2))
                                        {
                                            if (zzze2.zzxn())
                                            {
                                                zzze2.zzxq();
                                            }
                                        } else
                                        {
                                            arraylist1.add((com.google.android.gms.common.api.Api.zze)zzze2.zzaKV.zzaLC.get(zzc2));
                                        }
                                    } while (true);
                                    if (!arraylist1.isEmpty())
                                    {
                                        zzze2.zzaLk.add(zzzj.zzaLY.submit(zzze2. new zzc(arraylist1)));
                                        return;
                                    }
                                }
                            } else
                            {
                                zzze2.zzf(((ConnectionResult) (obj1)));
                                return;
                            }
                            if (true) goto _L2; else goto _L7
_L7:
                        }

                    _cls1(zzzh zzzh1, zzze zzze1, zzbea zzbea1)
                    {
                        zzaLs = zzze1;
                        zzaLt = zzbea1;
                        super(zzzh1);
                    }
                    }

                    zzbea = new _cls1(zzze1, zzze1, zzbea);
                    zzbea = zzzi1.zzaLQ.obtainMessage(1, zzbea);
                    zzzi1.zzaLQ.sendMessage(zzbea);
                    return;
                }
            }

            zzd()
            {
                zzaLm = new WeakReference(zzze.this);
            }
        }

    }


    private class zzb extends zzf
    {
        private class zzf
            implements Runnable
        {

            private final zzze zzaLl;

            public void run()
            {
                zzaLl.zzaKy.lock();
                boolean flag = Thread.interrupted();
                if (flag)
                {
                    zzaLl.zzaKy.unlock();
                    return;
                }
                zzxm();
                zzaLl.zzaKy.unlock();
                return;
                RuntimeException runtimeexception;
                runtimeexception;
                zzzi zzzi1 = zzaLl.zzaKV;
                android.os.Message message = zzzi1.zzaLQ.obtainMessage(2, runtimeexception);
                zzzi1.zzaLQ.sendMessage(message);
                zzaLl.zzaKy.unlock();
                return;
                Exception exception;
                exception;
                zzaLl.zzaKy.unlock();
                throw exception;
            }

            protected abstract void zzxm();

            zzf()
            {
                zzaLl = zzze.this;
                super();
            }
        }


        public final zzze zzaLl;
        private final Map zzaLn;

        public final void zzxm()
        {
            boolean flag;
label0:
            {
                Iterator iterator = zzaLn.keySet().iterator();
                flag = false;
                do
                {
                    if (!iterator.hasNext())
                    {
                        break label0;
                    }
                    com.google.android.gms.common.api.Api.zze zze1 = (com.google.android.gms.common.api.Api.zze)iterator.next();
                    zze1.zzwt();
                    if (((zza)zzaLn.get(zze1)).zzaKm == 0)
                    {
                        break;
                    }
                    flag = true;
                } while (true);
                flag = true;
            }
            int i;
            if (flag)
            {
                i = zzaLl.zzaKH.isGooglePlayServicesAvailable(zzaLl.mContext);
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                Object obj = new ConnectionResult(i, null);
                zzzi zzzi1 = zzaLl.zzaKV;
                class _cls1 extends zzzi.zza
                {

                    private final ConnectionResult zzaLo;
                    private final zzb zzaLp;

                    public final void zzxm()
                    {
                        zzaLp.zzaLl.zzf(zzaLo);
                    }

                _cls1(zzzh zzzh1, ConnectionResult connectionresult)
                {
                    zzaLp = zzb.this;
                    zzaLo = connectionresult;
                    super(zzzh1);
                }
                }

                obj = new _cls1(zzaLl, ((ConnectionResult) (obj)));
                obj = zzzi1.zzaLQ.obtainMessage(1, obj);
                zzzi1.zzaLQ.sendMessage(((android.os.Message) (obj)));
            } else
            {
                if (zzaLl.zzaLf)
                {
                    zzaLl.zzaLd.connect();
                }
                Iterator iterator1 = zzaLn.keySet().iterator();
                while (iterator1.hasNext()) 
                {
                    Object obj1 = (com.google.android.gms.common.api.Api.zze)iterator1.next();
                    Object obj2 = (com.google.android.gms.common.internal.zzf.zzf)zzaLn.get(obj1);
                    ((com.google.android.gms.common.api.Api.zze) (obj1)).zzwt();
                    if (i != 0)
                    {
                        obj1 = zzaLl.zzaKV;
                        class _cls2 extends zzzi.zza
                        {

                            private final com.google.android.gms.common.internal.zzf.zzf zzaLq;

                            public final void zzxm()
                            {
                                zzaLq.zzg(new ConnectionResult(16, null));
                            }

                _cls2(zzzh zzzh1, com.google.android.gms.common.internal.zzf.zzf zzf1)
                {
                    zzaLq = zzf1;
                    super(zzzh1);
                }
                        }

                        obj2 = new _cls2(zzaLl, ((com.google.android.gms.common.internal.zzf.zzf) (obj2)));
                        obj2 = ((zzzi) (obj1)).zzaLQ.obtainMessage(1, obj2);
                        ((zzzi) (obj1)).zzaLQ.sendMessage(((android.os.Message) (obj2)));
                    } else
                    {
                        ((com.google.android.gms.common.api.Api.zze) (obj1)).zza(((com.google.android.gms.common.internal.zzf.zzf) (obj2)));
                    }
                }
            }
        }

        public zzb(Map map)
        {
            zzaLl = zzze.this;
            super();
            zzaLn = map;
        }
    }


    private class zzc extends zzf
    {

        private final zzze zzaLl;
        private final ArrayList zzaLr;

        public final void zzxm()
        {
            zzzg zzzg1 = zzaLl.zzaKV.zzaKo;
            zzze zzze1 = zzaLl;
            Object obj;
            int j;
            if (zzze1.zzaKD == null)
            {
                obj = Collections.emptySet();
            } else
            {
                obj = new HashSet(zzze1.zzaKD.zzaIZ);
                Map map = zzze1.zzaKD.zzaPN;
                Iterator iterator = map.keySet().iterator();
                while (iterator.hasNext()) 
                {
                    Api api = (Api)iterator.next();
                    Map map1 = zzze1.zzaKV.zzaLR;
                    if (api.zzaII != null)
                    {
                        if (!map1.containsKey(api.zzaII))
                        {
                            ((Set) (obj)).addAll(((com.google.android.gms.common.internal.zzg.zza)map.get(api)).zzalx);
                        }
                    } else
                    {
                        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
                    }
                }
            }
            zzzg1.zzaLD = ((Set) (obj));
            obj = (ArrayList)zzaLr;
            j = ((ArrayList) (obj)).size();
            for (int i = 0; i < j;)
            {
                Object obj1 = ((ArrayList) (obj)).get(i);
                i++;
                ((com.google.android.gms.common.api.Api.zze)obj1).zza(zzaLl.zzaLh, zzaLl.zzaKV.zzaKo.zzaLD);
            }

        }

        public zzc(ArrayList arraylist)
        {
            zzaLl = zzze.this;
            super();
            zzaLr = arraylist;
        }
    }


    private class _cls1
        implements Runnable
    {

        private final zzze zzaLl;

        public final void run()
        {
            GoogleApiAvailabilityLight.zzas(zzaLl.mContext);
        }

        _cls1()
        {
            zzaLl = zzze.this;
            super();
        }
    }

}
