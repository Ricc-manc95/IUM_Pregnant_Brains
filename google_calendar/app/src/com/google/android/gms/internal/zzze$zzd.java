// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.gms.internal:
//            zzbdq, zzze, zzzi, zzbea

final class zzaLm extends zzbdq
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
                            com.google.android.gms.common.api.Api.zzc zzc = (com.google.android.gms.common.api.Api.zzc)((Iterator) (obj1)).next();
                            if (zzze2.zzaKV.zzaLR.containsKey(zzc))
                            {
                                if (zzze2.zzxn())
                                {
                                    zzze2.zzxq();
                                }
                            } else
                            {
                                arraylist.add((com.google.android.gms.common.api.Api.zze)zzze2.zzaKV.zzaLC.get(zzc));
                            }
                        } while (true);
                        if (!arraylist.isEmpty())
                        {
                            zzze2.zzaLk.add(zzzj.zzaLY.submit(new zzze.zzc(zzze2, arraylist)));
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
                                com.google.android.gms.common.api.Api.zzc zzc1 = (com.google.android.gms.common.api.Api.zzc)((Iterator) (obj1)).next();
                                if (zzze2.zzaKV.zzaLR.containsKey(zzc1))
                                {
                                    if (zzze2.zzxn())
                                    {
                                        zzze2.zzxq();
                                    }
                                } else
                                {
                                    arraylist1.add((com.google.android.gms.common.api.Api.zze)zzze2.zzaKV.zzaLC.get(zzc1));
                                }
                            } while (true);
                            if (!arraylist1.isEmpty())
                            {
                                zzze2.zzaLk.add(zzzj.zzaLY.submit(new zzze.zzc(zzze2, arraylist1)));
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

            _cls1(zzzh zzzh, zzze zzze1, zzbea zzbea1)
            {
                zzaLs = zzze1;
                zzaLt = zzbea1;
                super(zzzh);
            }
            }

            zzbea = new _cls1(zzze1, zzze1, zzbea);
            zzbea = zzzi1.zzaLQ.obtainMessage(1, zzbea);
            zzzi1.zzaLQ.sendMessage(zzbea);
            return;
        }
    }

    _cls1(zzze zzze1)
    {
        zzaLm = new WeakReference(zzze1);
    }
}
