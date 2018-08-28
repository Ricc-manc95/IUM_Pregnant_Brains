// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.calendar.v2.client.service.api.common;


public final class  extends Enum
{

    private static final SMS $VALUES[];
    public static final SMS ALERT;
    public static final SMS EMAIL;
    public static final SMS SMS;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        ALERT = new <init>("ALERT", 0);
        EMAIL = new <init>("EMAIL", 1);
        SMS = new <init>("SMS", 2);
        $VALUES = (new .VALUES[] {
            ALERT, EMAIL, SMS
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
