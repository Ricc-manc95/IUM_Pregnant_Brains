// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// Referenced classes of package io.grpc.internal:
//            KeepAliveManager

final class this._cls0
    implements Runnable
{

    private final KeepAliveManager this$0;

    public final void run()
    {
        boolean flag1;
        pingFuture = null;
        flag1 = false;
        KeepAliveManager keepalivemanager = KeepAliveManager.this;
        keepalivemanager;
        JVM INSTR monitorenter ;
        if (KeepAliveManager.access$100$5166IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI7CKKOQBF5TJN4S335TKMST35E9N62R1F9DIMAS21DHKNCPADC5N62PR5E8I56T31EHIJM___0(KeepAliveManager.this) != android.support.v4.content.tus.PING_SCHEDULED._fld9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L2; else goto _L1
_L1:
        boolean flag = true;
        KeepAliveManager.access$102$5166IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI7D66IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI4H9N8OBKCKTIIJ39DSNMESJGCCNMIRJKCLP6SOBC5T5MAPBG85M6ITJ59LGMSOB7CLP28KRKC5Q6AEO_0(KeepAliveManager.this, android.support.v4.content.tus.PING_SENT._fld9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0);
        shutdownFuture = scheduler.schedule(shutdown, keepAliveTimeoutInNanos, TimeUnit.NANOSECONDS);
_L4:
        keepalivemanager;
        JVM INSTR monitorexit ;
        if (flag)
        {
            keepAlivePinger.ping();
        }
        return;
_L2:
        flag = flag1;
        if (KeepAliveManager.access$100$5166IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI7CKKOQBF5TJN4S335TKMST35E9N62R1F9DIMAS21DHKNCPADC5N62PR5E8I56T31EHIJM___0(KeepAliveManager.this) != android.support.v4.content.tus.PING_DELAYED._fld9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0) goto _L4; else goto _L3
_L3:
        pingFuture = scheduler.schedule(sendPing, nextKeepaliveTime - ticker.read(), TimeUnit.NANOSECONDS);
        KeepAliveManager.access$102$5166IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI7D66IRPFCTP70OPFD5N78PBIDPGMOBQBCLIN0GBCD5R6AJB1DPGMEPBI4H9N8OBKCKTIIJ39DSNMESJGCCNMIRJKCLP6SOBC5T5MAPBG85M6ITJ59LGMSOB7CLP28KRKC5Q6AEO_0(KeepAliveManager.this, android.support.v4.content.tus.PING_SCHEDULED._fld9HKMUBR7E9O66BR9DPQ6ASJEC5M2UIR5CLO42R39EPIKQOBEC5JMASH4ADQ62T357C______0);
        flag = flag1;
          goto _L4
        Exception exception;
        exception;
        keepalivemanager;
        JVM INSTR monitorexit ;
        throw exception;
    }

    cker()
    {
        this$0 = KeepAliveManager.this;
        super();
    }
}
