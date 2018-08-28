// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.HistoryApi;
import com.google.android.gms.fitness.request.DataReadRequest;

public final class zzapy
    implements HistoryApi
{

    public zzapy()
    {
    }

    public final PendingResult readData(GoogleApiClient googleapiclient, DataReadRequest datareadrequest)
    {
        return googleapiclient.zza(new _cls6(googleapiclient, datareadrequest));
    }

    private class _cls6 extends zzaos.zza
    {

        private final DataReadRequest zzbjh;

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            zzb1 = (zzaos)zzb1;
            zza zza1 = new zza(this);
            ((zzaph)zzb1.zzyP()).zza(new DataReadRequest(zzbjh, zza1));
        }

        protected final Result zzb(Status status)
        {
            return DataReadResult.createFailed(status, zzbjh.zzbhv, zzbjh.zzbjU);
        }

        _cls6(GoogleApiClient googleapiclient, DataReadRequest datareadrequest)
        {
            zzbjh = datareadrequest;
            super(googleapiclient);
        }

        private class zza extends zzaoy.zza
        {

            private final zzyq.zzb zzaNb;
            private int zzbjl;
            private DataReadResult zzbjm;

            public final void zza(DataReadResult datareadresult)
            {
                this;
                JVM INSTR monitorenter ;
                if (zzbjm != null) goto _L2; else goto _L1
_L1:
                zzbjm = datareadresult;
_L8:
                zzbjl = zzbjl + 1;
                if (zzbjl == zzbjm.zzbln)
                {
                    zzaNb.setResult(zzbjm);
                }
                this;
                JVM INSTR monitorexit ;
                return;
_L2:
                DataReadResult datareadresult1;
                datareadresult1 = zzbjm;
                for (Iterator iterator = datareadresult.zzbhy.iterator(); iterator.hasNext(); DataReadResult.zza((DataSet)iterator.next(), datareadresult1.zzbhy)) { }
                break MISSING_BLOCK_LABEL_102;
                datareadresult;
                this;
                JVM INSTR monitorexit ;
                throw datareadresult;
                datareadresult = datareadresult.zzblm.iterator();
_L5:
                Object obj;
                Iterator iterator1;
                if (!datareadresult.hasNext())
                {
                    continue; /* Loop/switch isn't completed */
                }
                obj = (Bucket)datareadresult.next();
                iterator1 = datareadresult1.zzblm.iterator();
_L4:
                Bucket bucket;
                if (!iterator1.hasNext())
                {
                    break MISSING_BLOCK_LABEL_264;
                }
                bucket = (Bucket)iterator1.next();
                boolean flag;
                if (bucket.zzadP == ((Bucket) (obj)).zzadP && bucket.zzbhw == ((Bucket) (obj)).zzbhw && bucket.zzbhx == ((Bucket) (obj)).zzbhx && bucket.zzbhz == ((Bucket) (obj)).zzbhz)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag) goto _L4; else goto _L3
_L3:
                obj = ((Bucket) (obj)).zzbhy.iterator();
                while (((Iterator) (obj)).hasNext()) 
                {
                    DataReadResult.zza((DataSet)((Iterator) (obj)).next(), bucket.zzbhy);
                }
                  goto _L5
                datareadresult1.zzblm.add(obj);
                if (true) goto _L5; else goto _L6
_L6:
                if (true) goto _L8; else goto _L7
_L7:
            }

            zza(zzyq.zzb zzb1)
            {
                zzbjl = 0;
                zzbjm = null;
                zzaNb = zzb1;
            }
        }

    }

}
