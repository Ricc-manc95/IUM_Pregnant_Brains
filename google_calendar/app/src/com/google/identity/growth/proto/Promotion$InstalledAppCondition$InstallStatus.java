// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.growth.proto;


// Referenced classes of package com.google.identity.growth.proto:
//            Promotion

public static final class value extends Enum
    implements com.google.protobuf.nstallStatus
{

    private static final forNumber $VALUES[];
    public static final forNumber INSTALLED;
    public static final forNumber NOT_INSTALLED;
    public static final forNumber UNKNOWN;
    public static final com.google.protobuf.nstallStatus internalVerifier = new _cls2();
    private final int value;

    public static value forNumber(int i)
    {
        switch (i)
        {
        default:
            return null;

        case 0: // '\0'
            return UNKNOWN;

        case 1: // '\001'
            return NOT_INSTALLED;

        case 2: // '\002'
            return INSTALLED;
        }
    }

    public static INSTALLED[] values()
    {
        return (INSTALLED[])$VALUES.clone();
    }

    public final int getNumber()
    {
        return value;
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0, 0);
        NOT_INSTALLED = new <init>("NOT_INSTALLED", 1, 1);
        INSTALLED = new <init>("INSTALLED", 2, 2);
        $VALUES = (new .VALUES[] {
            UNKNOWN, NOT_INSTALLED, INSTALLED
        });
        class _cls2
            implements com.google.protobuf.Internal.EnumVerifier
        {

            public final boolean isInRange(int i)
            {
                return Promotion.InstalledAppCondition.InstallStatus.forNumber(i) != null;
            }

            _cls2()
            {
            }
        }

    }

    private _cls2(String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
