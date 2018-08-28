// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public final class AtomicBackoff
{
    public final class State
    {

        public final long savedValue;
        public final AtomicBackoff this$0;

        public State(long l)
        {
            this$0 = AtomicBackoff.this;
            super();
            savedValue = l;
        }
    }


    public static final Logger log = Logger.getLogger(io/grpc/internal/AtomicBackoff.getName());
    public final String name;
    public final AtomicLong value = new AtomicLong();

    public AtomicBackoff(String s, long l)
    {
        boolean flag;
        if (l > 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("value must be positive"));
        } else
        {
            name = s;
            value.set(l);
            return;
        }
    }

}
