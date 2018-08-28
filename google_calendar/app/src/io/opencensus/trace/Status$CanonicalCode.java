// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.opencensus.trace;


public final class value extends Enum
{

    private static final UNAUTHENTICATED $VALUES[];
    public static final UNAUTHENTICATED ABORTED;
    public static final UNAUTHENTICATED ALREADY_EXISTS;
    public static final UNAUTHENTICATED CANCELLED;
    public static final UNAUTHENTICATED DATA_LOSS;
    public static final UNAUTHENTICATED DEADLINE_EXCEEDED;
    public static final UNAUTHENTICATED FAILED_PRECONDITION;
    public static final UNAUTHENTICATED INTERNAL;
    public static final UNAUTHENTICATED INVALID_ARGUMENT;
    public static final UNAUTHENTICATED NOT_FOUND;
    public static final UNAUTHENTICATED OK;
    public static final UNAUTHENTICATED OUT_OF_RANGE;
    public static final UNAUTHENTICATED PERMISSION_DENIED;
    public static final UNAUTHENTICATED RESOURCE_EXHAUSTED;
    public static final UNAUTHENTICATED UNAUTHENTICATED;
    public static final UNAUTHENTICATED UNAVAILABLE;
    public static final UNAUTHENTICATED UNIMPLEMENTED;
    public static final UNAUTHENTICATED UNKNOWN;
    public final int value;

    public static value[] values()
    {
        return (value[])$VALUES.clone();
    }

    static 
    {
        OK = new <init>("OK", 0, 0);
        CANCELLED = new <init>("CANCELLED", 1, 1);
        UNKNOWN = new <init>("UNKNOWN", 2, 2);
        INVALID_ARGUMENT = new <init>("INVALID_ARGUMENT", 3, 3);
        DEADLINE_EXCEEDED = new <init>("DEADLINE_EXCEEDED", 4, 4);
        NOT_FOUND = new <init>("NOT_FOUND", 5, 5);
        ALREADY_EXISTS = new <init>("ALREADY_EXISTS", 6, 6);
        PERMISSION_DENIED = new <init>("PERMISSION_DENIED", 7, 7);
        RESOURCE_EXHAUSTED = new <init>("RESOURCE_EXHAUSTED", 8, 8);
        FAILED_PRECONDITION = new <init>("FAILED_PRECONDITION", 9, 9);
        ABORTED = new <init>("ABORTED", 10, 10);
        OUT_OF_RANGE = new <init>("OUT_OF_RANGE", 11, 11);
        UNIMPLEMENTED = new <init>("UNIMPLEMENTED", 12, 12);
        INTERNAL = new <init>("INTERNAL", 13, 13);
        UNAVAILABLE = new <init>("UNAVAILABLE", 14, 14);
        DATA_LOSS = new <init>("DATA_LOSS", 15, 15);
        UNAUTHENTICATED = new <init>("UNAUTHENTICATED", 16, 16);
        $VALUES = (new .VALUES[] {
            OK, CANCELLED, UNKNOWN, INVALID_ARGUMENT, DEADLINE_EXCEEDED, NOT_FOUND, ALREADY_EXISTS, PERMISSION_DENIED, RESOURCE_EXHAUSTED, FAILED_PRECONDITION, 
            ABORTED, OUT_OF_RANGE, UNIMPLEMENTED, INTERNAL, UNAVAILABLE, DATA_LOSS, UNAUTHENTICATED
        });
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
