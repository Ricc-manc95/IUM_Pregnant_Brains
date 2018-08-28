// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.tiles.view;

import android.content.Context;
import android.content.res.Resources;

// Referenced classes of package com.google.android.calendar.tiles.view:
//            TextTileView, TileView

public class AvatarTileView extends TextTileView
{

    public AvatarTileView(Context context)
    {
        super(context);
        Tile.Dimensions dimensions = Tile.Dimensions.AVATAR_ICON_SIZE;
        setIconSize(context.getResources().getDimensionPixelOffset(dimensions.dimenResId));
    }

    public int adjustTileHeight(int i)
    {
        i = super.adjustTileHeight(i);
        Tile.Dimensions dimensions = Tile.Dimensions.MEDIUM_TILE_MIN_HEIGHT;
        return Math.max(i, getContext().getResources().getDimensionPixelOffset(dimensions.dimenResId));
    }
}
