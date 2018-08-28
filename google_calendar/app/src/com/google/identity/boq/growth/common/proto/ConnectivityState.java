// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.boq.growth.common.proto;


public final class ConnectivityState extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final ConnectivityState $VALUES[];
    public static final ConnectivityState CONNECTIVITY_UNKNOWN;
    public static final ConnectivityState OFFLINE;
    public static final ConnectivityState ONLINE;
    private static final ConnectivityState UNRECOGNIZED;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    private final int value;

    private ConnectivityState(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static ConnectivityState forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return CONNECTIVITY_UNKNOWN;

        case 1: // '\001'
            return OFFLINE;

        case 2: // '\002'
            return ONLINE;
        }
    }

    public static ConnectivityState[] values()
    {
        return (ConnectivityState[])$VALUES.clone();
    }

    public final int getNumber()
    {
        if (this == UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            return value;
        }
    }

    static 
    {
        CONNECTIVITY_UNKNOWN = new ConnectivityState("CONNECTIVITY_UNKNOWN", 0, 0);
        OFFLINE = new ConnectivityState("OFFLINE", 1, 1);
        ONLINE = new ConnectivityState("ONLINE", 2, 2);
        UNRECOGNIZED = new ConnectivityState("UNRECOGNIZED", 3, -1);
        $VALUES = (new ConnectivityState[] {
            CONNECTIVITY_UNKNOWN, OFFLINE, ONLINE, UNRECOGNIZED
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return ConnectivityState.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
