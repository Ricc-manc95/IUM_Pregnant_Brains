// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.graphics.drawable.Drawable;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.tiles.view.BadgedIconTile;
import com.google.android.calendar.tiles.view.TileView;

final class arg._cls1
    implements Consumer
{

    private final BadgedIconTile arg$1;

    public final void accept(Object obj)
    {
        arg$1.setIconDrawable((Drawable)obj);
    }

    (BadgedIconTile badgedicontile)
    {
        arg$1 = badgedicontile;
    }
}
