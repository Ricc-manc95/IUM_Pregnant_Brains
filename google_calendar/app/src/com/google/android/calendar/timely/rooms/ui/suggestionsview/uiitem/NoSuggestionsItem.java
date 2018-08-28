// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem:
//            UiItem

public class NoSuggestionsItem extends UiItem
{

    public final Drawable icon;
    public final String message;
    public final int textColor;

    public NoSuggestionsItem(Resources resources)
    {
        icon = resources.getDrawable(0x7f02020b);
        int i = resources.getColor(0x7f0d01f5);
        icon.mutate().setColorFilter(i, android.graphics.PorterDuff.Mode.SRC_IN);
        message = resources.getString(0x7f1301c9);
        textColor = i;
    }
}
