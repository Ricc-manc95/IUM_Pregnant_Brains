// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;


// Referenced classes of package com.google.android.calendar.newapi.screen:
//            ViewScreen

final class arg._cls1
    implements com.google.android.calendar.newapi.commandbar.nged
{

    private final ViewScreen arg$1;

    public final void onHeightChanged()
    {
        arg$1.adjustExtraCommandBarPadding();
    }

    tChanged(ViewScreen viewscreen)
    {
        arg$1 = viewscreen;
    }
}
