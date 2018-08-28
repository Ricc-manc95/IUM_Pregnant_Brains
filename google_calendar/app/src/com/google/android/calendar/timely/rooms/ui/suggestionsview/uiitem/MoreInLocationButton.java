// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem;

import android.content.res.Resources;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem:
//            UiItem

public class MoreInLocationButton extends UiItem
{

    public final Runnable clickCallback;
    public final String message;

    public MoreInLocationButton(Runnable runnable, Resources resources)
    {
        clickCallback = runnable;
        message = resources.getString(0x7f130445);
    }
}
