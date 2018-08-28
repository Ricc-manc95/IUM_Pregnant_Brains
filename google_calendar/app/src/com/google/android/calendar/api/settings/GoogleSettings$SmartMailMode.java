// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.settings;


// Referenced classes of package com.google.android.calendar.api.settings:
//            GoogleSettings

public static final class  extends Enum
{

    private static final IGNORE $VALUES[];
    public static final IGNORE CREATE;
    public static final IGNORE CREATE_PRIVATE;
    public static final IGNORE CREATE_SECRET;
    public static final IGNORE IGNORE;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/android/calendar/api/settings/GoogleSettings$SmartMailMode, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        CREATE = new <init>("CREATE", 0);
        CREATE_PRIVATE = new <init>("CREATE_PRIVATE", 1);
        CREATE_SECRET = new <init>("CREATE_SECRET", 2);
        IGNORE = new <init>("IGNORE", 3);
        $VALUES = (new .VALUES[] {
            CREATE, CREATE_PRIVATE, CREATE_SECRET, IGNORE
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
