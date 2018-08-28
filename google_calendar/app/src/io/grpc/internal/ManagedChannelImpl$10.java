// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;

import io.grpc.Status;

final class val.t extends io.grpc.lPicker
{

    private final io.grpc.t panicPickResult;
    private final Throwable val$t;

    public final io.grpc.t pickSubchannel$5166IRPFCTP70OPF9HNM2P22C5M62RJ3CLP28K39CDLL6TB2CDK62RJECLM42SJ7ECTIIJ39DSNMESJGCCNKORR1CH162R31DPHMASH4A1KM6QQICLPNAR3K7C______0()
    {
        return panicPickResult;
    }

    ()
    {
        val$t = throwable;
        super();
        panicPickResult = io.grpc.t.withDrop(Status.INTERNAL.withDescription("Panic! This is a bug!").withCause(val$t));
    }
}
