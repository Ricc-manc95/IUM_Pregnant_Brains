// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


final class  extends Enum
{

    private static final TRAILER $VALUES[];
    public static final TRAILER HEADER;
    public static final TRAILER HEADER_COMMENT;
    public static final TRAILER HEADER_CRC;
    public static final TRAILER HEADER_EXTRA;
    public static final TRAILER HEADER_EXTRA_LEN;
    public static final TRAILER HEADER_NAME;
    public static final TRAILER INFLATER_NEEDS_INPUT;
    public static final TRAILER INFLATING;
    public static final TRAILER INITIALIZE_INFLATER;
    public static final TRAILER TRAILER;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        HEADER = new <init>("HEADER", 0);
        HEADER_EXTRA_LEN = new <init>("HEADER_EXTRA_LEN", 1);
        HEADER_EXTRA = new <init>("HEADER_EXTRA", 2);
        HEADER_NAME = new <init>("HEADER_NAME", 3);
        HEADER_COMMENT = new <init>("HEADER_COMMENT", 4);
        HEADER_CRC = new <init>("HEADER_CRC", 5);
        INITIALIZE_INFLATER = new <init>("INITIALIZE_INFLATER", 6);
        INFLATING = new <init>("INFLATING", 7);
        INFLATER_NEEDS_INPUT = new <init>("INFLATER_NEEDS_INPUT", 8);
        TRAILER = new <init>("TRAILER", 9);
        $VALUES = (new .VALUES[] {
            HEADER, HEADER_EXTRA_LEN, HEADER_EXTRA, HEADER_NAME, HEADER_COMMENT, HEADER_CRC, INITIALIZE_INFLATER, INFLATING, INFLATER_NEEDS_INPUT, TRAILER
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
