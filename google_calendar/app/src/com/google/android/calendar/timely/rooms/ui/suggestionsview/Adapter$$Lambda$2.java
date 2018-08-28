// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import android.view.View;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.MoreInLocationButton;

final class arg._cls1
    implements android.view.er
{

    private final MoreInLocationButton arg$1;

    public final void onClick(View view)
    {
        arg$1.clickCallback.run();
    }

    ionButton(MoreInLocationButton moreinlocationbutton)
    {
        arg$1 = moreinlocationbutton;
    }
}
