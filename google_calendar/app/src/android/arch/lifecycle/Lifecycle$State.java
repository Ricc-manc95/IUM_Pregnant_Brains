// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;


public final class  extends Enum
{

    private static final RESUMED $VALUES[];
    public static final RESUMED CREATED;
    public static final RESUMED DESTROYED;
    public static final RESUMED INITIALIZED;
    public static final RESUMED RESUMED;
    public static final RESUMED STARTED;

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        DESTROYED = new <init>("DESTROYED", 0);
        INITIALIZED = new <init>("INITIALIZED", 1);
        CREATED = new <init>("CREATED", 2);
        STARTED = new <init>("STARTED", 3);
        RESUMED = new <init>("RESUMED", 4);
        $VALUES = (new .VALUES[] {
            DESTROYED, INITIALIZED, CREATED, STARTED, RESUMED
        });
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
