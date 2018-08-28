// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.ClearcutLoggerApi;
import com.google.android.gms.clearcut.LogEventParcelable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.zzd;

// Referenced classes of package com.google.android.gms.internal:
//            zzym

public final class zzye extends zzd
    implements ClearcutLoggerApi
{

    public zzye(Context context)
    {
        super(context, ClearcutLogger.API, null, new zzym());
    }

    public final PendingResult logEvent(LogEventParcelable logeventparcelable)
    {
        return super.zza(2, new zzb(logeventparcelable, super.zzaIU));
    }

    private class zzb extends zzyq.zza
    {

        private final LogEventParcelable zzaIa;

        public final boolean equals(Object obj)
        {
            if (!(obj instanceof zzb))
            {
                return false;
            } else
            {
                obj = (zzb)obj;
                return zzaIa.equals(((zzb) (obj)).zzaIa);
            }
        }

        public final String toString()
        {
            String s = String.valueOf(zzaIa);
            return (new StringBuilder(String.valueOf(s).length() + 20)).append("LogEventMethodImpl(").append(s).append(")").toString();
        }

        protected final void zza(com.google.android.gms.common.api.Api.zzb zzb1)
            throws RemoteException
        {
            boolean flag1 = true;
            zzb1 = (zzyf)zzb1;
            class _cls1 extends zzyh.zza
            {

                private final zzb zzaIb;

                public final void zzH(Status status)
                {
                    throw new UnsupportedOperationException();
                }

                public final void zzI(Status status)
                {
                    zzaIb.zzb(status);
                }

                _cls1()
                {
                    zzaIb = zzb.this;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            LogEventParcelable logeventparcelable1;
            try
            {
                LogEventParcelable logeventparcelable = zzaIa;
                if (logeventparcelable.extensionProducer != null && logeventparcelable.logEvent.Bj.length == 0)
                {
                    logeventparcelable.logEvent.Bj = logeventparcelable.extensionProducer.toProtoBytes();
                }
                if (logeventparcelable.clientVisualElementsProducer != null && logeventparcelable.logEvent.Bp.length == 0)
                {
                    logeventparcelable.logEvent.Bp = logeventparcelable.clientVisualElementsProducer.toProtoBytes();
                }
                logeventparcelable.logEventBytes = com.google.android.gms.internal.zzcdm.zzf(logeventparcelable.logEvent);
            }
            // Misplaced declaration of an exception variable
            catch (com.google.android.gms.common.api.Api.zzb zzb1)
            {
                Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", zzb1);
                zzb1 = new Status(10, "MessageProducer");
                boolean flag;
                if (((Status) (zzb1)).zzaEP <= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    flag = flag1;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    throw new IllegalArgumentException(String.valueOf("Failed result must not be success"));
                } else
                {
                    zzb(zzb(zzb1));
                    return;
                }
            }
            logeventparcelable1 = zzaIa;
            ((zzyi)zzb1.zzyP()).zza(_lcls1, logeventparcelable1);
        }

        protected final Result zzb(Status status)
        {
            return status;
        }

        zzb(LogEventParcelable logeventparcelable, GoogleApiClient googleapiclient)
        {
            super(ClearcutLogger.API, googleapiclient);
            zzaIa = logeventparcelable;
        }
    }

}
