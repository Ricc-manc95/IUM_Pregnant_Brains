// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.rpc;


public final class Code extends Enum
    implements com.google.protobuf.Internal.EnumLite
{

    private static final Code $VALUES[];
    private static final Code ABORTED;
    private static final Code ALREADY_EXISTS;
    public static final Code CANCELLED;
    private static final Code DATA_LOSS;
    public static final Code DEADLINE_EXCEEDED;
    private static final Code FAILED_PRECONDITION;
    public static final Code INTERNAL;
    public static final Code INVALID_ARGUMENT;
    private static final Code NOT_FOUND;
    private static final Code OK;
    private static final Code OUT_OF_RANGE;
    public static final Code PERMISSION_DENIED;
    public static final Code RESOURCE_EXHAUSTED;
    public static final Code UNAUTHENTICATED;
    public static final Code UNAVAILABLE;
    private static final Code UNIMPLEMENTED;
    public static final Code UNKNOWN;
    public static final Code UNRECOGNIZED;
    public final int value;

    private Code(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }

    public static Code forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return OK;

        case 1: // '\001'
            return CANCELLED;

        case 2: // '\002'
            return UNKNOWN;

        case 3: // '\003'
            return INVALID_ARGUMENT;

        case 4: // '\004'
            return DEADLINE_EXCEEDED;

        case 5: // '\005'
            return NOT_FOUND;

        case 6: // '\006'
            return ALREADY_EXISTS;

        case 7: // '\007'
            return PERMISSION_DENIED;

        case 16: // '\020'
            return UNAUTHENTICATED;

        case 8: // '\b'
            return RESOURCE_EXHAUSTED;

        case 9: // '\t'
            return FAILED_PRECONDITION;

        case 10: // '\n'
            return ABORTED;

        case 11: // '\013'
            return OUT_OF_RANGE;

        case 12: // '\f'
            return UNIMPLEMENTED;

        case 13: // '\r'
            return INTERNAL;

        case 14: // '\016'
            return UNAVAILABLE;

        case 15: // '\017'
            return DATA_LOSS;
        }
    }

    public static Code[] values()
    {
        return (Code[])$VALUES.clone();
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
        OK = new Code("OK", 0, 0);
        CANCELLED = new Code("CANCELLED", 1, 1);
        UNKNOWN = new Code("UNKNOWN", 2, 2);
        INVALID_ARGUMENT = new Code("INVALID_ARGUMENT", 3, 3);
        DEADLINE_EXCEEDED = new Code("DEADLINE_EXCEEDED", 4, 4);
        NOT_FOUND = new Code("NOT_FOUND", 5, 5);
        ALREADY_EXISTS = new Code("ALREADY_EXISTS", 6, 6);
        PERMISSION_DENIED = new Code("PERMISSION_DENIED", 7, 7);
        UNAUTHENTICATED = new Code("UNAUTHENTICATED", 8, 16);
        RESOURCE_EXHAUSTED = new Code("RESOURCE_EXHAUSTED", 9, 8);
        FAILED_PRECONDITION = new Code("FAILED_PRECONDITION", 10, 9);
        ABORTED = new Code("ABORTED", 11, 10);
        OUT_OF_RANGE = new Code("OUT_OF_RANGE", 12, 11);
        UNIMPLEMENTED = new Code("UNIMPLEMENTED", 13, 12);
        INTERNAL = new Code("INTERNAL", 14, 13);
        UNAVAILABLE = new Code("UNAVAILABLE", 15, 14);
        DATA_LOSS = new Code("DATA_LOSS", 16, 15);
        UNRECOGNIZED = new Code("UNRECOGNIZED", 17, -1);
        $VALUES = (new Code[] {
            OK, CANCELLED, UNKNOWN, INVALID_ARGUMENT, DEADLINE_EXCEEDED, NOT_FOUND, ALREADY_EXISTS, PERMISSION_DENIED, UNAUTHENTICATED, RESOURCE_EXHAUSTED, 
            FAILED_PRECONDITION, ABORTED, OUT_OF_RANGE, UNIMPLEMENTED, INTERNAL, UNAVAILABLE, DATA_LOSS, UNRECOGNIZED
        });
        new _cls2();
    }

    private class _cls2
        implements com.google.protobuf.Internal.EnumVerifier
    {

        public final boolean isInRange(int i)
        {
            return Code.forNumber(i) != null;
        }

        _cls2()
        {
        }
    }

}
