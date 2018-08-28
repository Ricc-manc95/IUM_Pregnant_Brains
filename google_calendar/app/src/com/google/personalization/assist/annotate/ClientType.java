// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.personalization.assist.annotate;


public final class ClientType extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final ClientType $VALUES[];
    private static final ClientType BIGTOP;
    private static final ClientType DEBUG_FRONTEND;
    private static final ClientType GWS;
    private static final ClientType KEEP;
    private static final ClientType PINTO;
    private static final ClientType SANDCLOCK;
    private static final ClientType SAPINTO;
    private static final ClientType SAVED_ITEMS;
    private static final ClientType TASKS;
    public static final ClientType TIMELY;
    private static final ClientType UNKNOWN_CLIENT;
    public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
    public final int value;

    private ClientType(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static ClientType forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN_CLIENT;

        case 1: // '\001'
            return BIGTOP;

        case 5: // '\005'
            return DEBUG_FRONTEND;

        case 4: // '\004'
            return GWS;

        case 3: // '\003'
            return KEEP;

        case 9: // '\t'
            return PINTO;

        case 7: // '\007'
            return SANDCLOCK;

        case 10: // '\n'
            return SAPINTO;

        case 6: // '\006'
            return SAVED_ITEMS;

        case 8: // '\b'
            return TASKS;

        case 2: // '\002'
            return TIMELY;
        }
    }

    public static ClientType[] values()
    {
        return (ClientType[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN_CLIENT = new ClientType("UNKNOWN_CLIENT", 0, 0);
        BIGTOP = new ClientType("BIGTOP", 1, 1);
        DEBUG_FRONTEND = new ClientType("DEBUG_FRONTEND", 2, 5);
        GWS = new ClientType("GWS", 3, 4);
        KEEP = new ClientType("KEEP", 4, 3);
        PINTO = new ClientType("PINTO", 5, 9);
        SANDCLOCK = new ClientType("SANDCLOCK", 6, 7);
        SAPINTO = new ClientType("SAPINTO", 7, 10);
        SAVED_ITEMS = new ClientType("SAVED_ITEMS", 8, 6);
        TASKS = new ClientType("TASKS", 9, 8);
        TIMELY = new ClientType("TIMELY", 10, 2);
        $VALUES = (new ClientType[] {
            UNKNOWN_CLIENT, BIGTOP, DEBUG_FRONTEND, GWS, KEEP, PINTO, SANDCLOCK, SAPINTO, SAVED_ITEMS, TASKS, 
            TIMELY
        });
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return ClientType.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
