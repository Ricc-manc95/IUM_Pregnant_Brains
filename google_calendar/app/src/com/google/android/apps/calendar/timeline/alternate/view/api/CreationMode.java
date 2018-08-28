// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.api;


public final class CreationMode extends Enum
{

    private static final CreationMode $VALUES[];
    private static final CreationMode NEW;
    public static final CreationMode OLD;
    public final boolean automaticTimeout;
    public final boolean scrim;
    public final boolean tapToDismiss;

    private CreationMode(String s, int i, boolean flag, boolean flag1, boolean flag2)
    {
        super(s, i);
        automaticTimeout = flag;
        tapToDismiss = flag1;
        scrim = flag2;
    }

    public static CreationMode[] values()
    {
        return (CreationMode[])$VALUES.clone();
    }

    static 
    {
        OLD = new CreationMode("OLD", 0, true, true, false);
        NEW = new CreationMode("NEW", 1, false, false, true);
        $VALUES = (new CreationMode[] {
            OLD, NEW
        });
    }
}
