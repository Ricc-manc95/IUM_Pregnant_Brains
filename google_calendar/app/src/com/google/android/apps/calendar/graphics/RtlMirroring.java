// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics;


public abstract class RtlMirroring extends Enum
{

    private static final RtlMirroring $VALUES[];
    public static final RtlMirroring DO_NOT_MIRROR;
    public static final RtlMirroring MIRROR_IN_RTL;

    private RtlMirroring(String s, int i)
    {
        super(s, i);
    }

    RtlMirroring(String s, int i, byte byte0)
    {
        this(s, i);
    }

    public static RtlMirroring mirrorInRtlIf(boolean flag)
    {
        if (flag)
        {
            return MIRROR_IN_RTL;
        } else
        {
            return DO_NOT_MIRROR;
        }
    }

    public static RtlMirroring[] values()
    {
        return (RtlMirroring[])$VALUES.clone();
    }

    public abstract boolean mirrorInRtl();

    static 
    {
        DO_NOT_MIRROR = new _cls1("DO_NOT_MIRROR", 0);
        MIRROR_IN_RTL = new _cls2("MIRROR_IN_RTL", 1);
        $VALUES = (new RtlMirroring[] {
            DO_NOT_MIRROR, MIRROR_IN_RTL
        });
    }

    private class _cls1 extends RtlMirroring
    {

        public final boolean mirrorInRtl()
        {
            return false;
        }

        _cls1(String s, int i)
        {
            super(s, 0, (byte)0);
        }
    }


    private class _cls2 extends RtlMirroring
    {

        public final boolean mirrorInRtl()
        {
            return true;
        }

        _cls2(String s, int i)
        {
            super(s, 1, (byte)0);
        }
    }

}
