// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package io.grpc;


public final class SecurityLevel extends Enum
{

    private static final SecurityLevel $VALUES[];
    private static final SecurityLevel INTEGRITY;
    public static final SecurityLevel NONE;
    public static final SecurityLevel PRIVACY_AND_INTEGRITY;

    private SecurityLevel(String s, int i)
    {
        super(s, i);
    }

    public static SecurityLevel[] values()
    {
        return (SecurityLevel[])$VALUES.clone();
    }

    static 
    {
        NONE = new SecurityLevel("NONE", 0);
        INTEGRITY = new SecurityLevel("INTEGRITY", 1);
        PRIVACY_AND_INTEGRITY = new SecurityLevel("PRIVACY_AND_INTEGRITY", 2);
        $VALUES = (new SecurityLevel[] {
            NONE, INTEGRITY, PRIVACY_AND_INTEGRITY
        });
    }
}
