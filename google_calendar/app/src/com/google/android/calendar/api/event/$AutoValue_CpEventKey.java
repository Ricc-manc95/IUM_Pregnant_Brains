// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.api.event:
//            $$AutoValue_CpEventKey

abstract class $AutoValue_CpEventKey extends $$AutoValue_CpEventKey
{

    private volatile Optional uri;

    $AutoValue_CpEventKey(boolean flag, long l, long l1)
    {
        super(flag, l, l1);
    }

    public final Optional uri()
    {
        if (uri != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (uri == null)
        {
            uri = super.uri();
            if (uri == null)
            {
                throw new NullPointerException("uri() cannot return null");
            }
        }
        break MISSING_BLOCK_LABEL_46;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        this;
        JVM INSTR monitorexit ;
_L2:
        return uri;
    }
}
