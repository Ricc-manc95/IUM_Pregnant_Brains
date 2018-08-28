// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            zzze, zzzi, zzbdm

final class zzaLn extends zzaLn
{

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
                com.google.android.gms.common.api.zzf zzf = (com.google.android.gms.common.api.aLn)iterator.next();
                zzf.zwt();
                if (((.zzwt)zzaLn.get(zzf)).zzaKm == 0)
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
                private final zzze.zzb zzaLp;

                public final void zzxm()
                {
                    zzaLp.zzaLl.zzf(zzaLo);
                }

            _cls1(zzzh zzzh, ConnectionResult connectionresult)
            {
                zzaLp = zzze.zzb.this;
                zzaLo = connectionresult;
                super(zzzh);
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
                Object obj1 = (com.google.android.gms.common.api.aLn)iterator1.next();
                Object obj2 = (com.google.android.gms.common.internal.sage)zzaLn.get(obj1);
                ((com.google.android.gms.common.api.aLn) (obj1)).zwt();
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

            _cls2(zzzh zzzh, com.google.android.gms.common.internal.zzf.zzf zzf)
            {
                zzaLq = zzf;
                super(zzzh);
            }
                    }

                    obj2 = new _cls2(zzaLl, ((com.google.android.gms.common.internal.>) (obj2)));
                    obj2 = ((zzzi) (obj1)).zzaLQ.obtainMessage(1, obj2);
                    ((zzzi) (obj1)).zzaLQ.sendMessage(((android.os.Message) (obj2)));
                } else
                {
                    ((com.google.android.gms.common.api.ndMessage) (obj1)).za(((com.google.android.gms.common.internal.sage) (obj2)));
                }
            }
        }
    }

    public _cls2(zzze zzze1, Map map)
    {
        zzaLl = zzze1;
        super(zzze1);
        zzaLn = map;
    }
}
