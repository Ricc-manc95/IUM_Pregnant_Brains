// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc.internal;


public final class  extends Enum
{

    private static final CT_ERROR $VALUES[];
    private static final CT_ERROR CT_ERROR;
    public static final CT_ERROR CT_INFO;
    private static final CT_ERROR CT_UNKNOWN;
    public static final CT_ERROR CT_WARNING;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        CT_UNKNOWN = new <init>("CT_UNKNOWN", 0);
        CT_INFO = new <init>("CT_INFO", 1);
        CT_WARNING = new <init>("CT_WARNING", 2);
        CT_ERROR = new <init>("CT_ERROR", 3);
        $VALUES = (new .VALUES[] {
            CT_UNKNOWN, CT_INFO, CT_WARNING, CT_ERROR
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
