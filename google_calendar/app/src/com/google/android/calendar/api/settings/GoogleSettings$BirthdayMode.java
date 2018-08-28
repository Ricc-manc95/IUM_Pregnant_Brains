// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;


// Referenced classes of package com.google.android.calendar.api.settings:
//            GoogleSettings

public static final class  extends Enum
{

    private static final NONE $VALUES[];
    public static final NONE CONTACTS;
    public static final NONE GPLUS_AND_CONTACTS;
    public static final NONE NONE;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        GPLUS_AND_CONTACTS = new <init>("GPLUS_AND_CONTACTS", 0);
        CONTACTS = new <init>("CONTACTS", 1);
        NONE = new <init>("NONE", 2);
        $VALUES = (new .VALUES[] {
            GPLUS_AND_CONTACTS, CONTACTS, NONE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
