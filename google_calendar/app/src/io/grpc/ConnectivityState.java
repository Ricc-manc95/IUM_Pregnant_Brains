// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


public final class ConnectivityState extends Enum
{

    private static final ConnectivityState $VALUES[];
    public static final ConnectivityState CONNECTING;
    public static final ConnectivityState IDLE;
    public static final ConnectivityState READY;
    public static final ConnectivityState SHUTDOWN;
    public static final ConnectivityState TRANSIENT_FAILURE;

    private ConnectivityState(String s, int i)
    {
        super(s, i);
    }

    public static ConnectivityState[] values()
    {
        return (ConnectivityState[])$VALUES.clone();
    }

    static 
    {
        CONNECTING = new ConnectivityState("CONNECTING", 0);
        READY = new ConnectivityState("READY", 1);
        TRANSIENT_FAILURE = new ConnectivityState("TRANSIENT_FAILURE", 2);
        IDLE = new ConnectivityState("IDLE", 3);
        SHUTDOWN = new ConnectivityState("SHUTDOWN", 4);
        $VALUES = (new ConnectivityState[] {
            CONNECTING, READY, TRANSIENT_FAILURE, IDLE, SHUTDOWN
        });
    }
}
