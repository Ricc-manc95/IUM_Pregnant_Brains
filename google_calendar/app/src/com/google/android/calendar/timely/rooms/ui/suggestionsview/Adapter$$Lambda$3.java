// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview;

import android.view.View;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem.SuggestedRoom;

final class arg._cls1
    implements android.view.er
{

    private final SuggestedRoom arg$1;

    public final void onClick(View view)
    {
        view = arg$1;
        ((SuggestedRoom) (view)).consumer.accept(view);
    }

    om(SuggestedRoom suggestedroom)
    {
        arg$1 = suggestedroom;
    }
}