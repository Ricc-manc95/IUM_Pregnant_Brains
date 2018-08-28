// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Deadline;

// Referenced classes of package io.grpc.internal:
//            ClientStream

final class val.deadline
    implements val.deadline
{

    private final Deadline val$deadline;

    public final void runWith(val.deadline deadline1)
    {
        deadline1.m.setDeadline(val$deadline);
    }

    ()
    {
        val$deadline = deadline1;
        super();
    }
}
