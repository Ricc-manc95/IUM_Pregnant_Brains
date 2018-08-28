// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.roomtile.meetings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.calendar.common.drawable.CircledDrawable;
import com.google.android.calendar.tiles.view.BadgedIconTile;
import com.google.android.calendar.tiles.view.TileView;

public class RoomTile extends BadgedIconTile
{

    public ImageView audioIcon;
    public View audioSeparator;
    public View audioView;
    public ImageView capacityBadge;
    public TextView capacityView;
    public View featuresBadge;
    public TextView featuresView;
    public TextView locationSeparator;
    public TextView locationView;
    public TextView nameView;
    public View secondLine;
    public View srbContent;
    public View structuredContent;
    public TextView unstructuredContent;
    public ImageView videoIcon;
    public View videoSeparator;
    public View videoView;

    public RoomTile(Context context)
    {
        super(context);
    }

    public RoomTile(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected final View createContentView(LayoutInflater layoutinflater)
    {
        return layoutinflater.inflate(0x7f05014a, null);
    }

    protected final void onContentViewSet(View view)
    {
        super.onContentViewSet(view);
        unstructuredContent = (TextView)findViewById(0x7f10034e);
    }

    public final TileView setIconDrawable(Drawable drawable)
    {
        Object obj = drawable;
        if (drawable != null)
        {
            obj = new CircledDrawable(getResources(), drawable);
        }
        return super.setIconDrawable(((Drawable) (obj)));
    }

    public final TileView setIconDrawable(Drawable drawable, boolean flag)
    {
        Object obj = drawable;
        if (drawable != null)
        {
            obj = drawable;
            if (flag)
            {
                obj = new CircledDrawable(getResources(), drawable);
            }
        }
        return super.setIconDrawable(((Drawable) (obj)));
    }
}
