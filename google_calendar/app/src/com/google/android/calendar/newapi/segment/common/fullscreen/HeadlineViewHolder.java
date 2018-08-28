// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common.fullscreen;

import android.content.Context;
import com.google.android.calendar.tiles.view.HeadlineTileView;

public final class HeadlineViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder
{

    public final HeadlineTileView view;

    private HeadlineViewHolder(HeadlineTileView headlinetileview)
    {
        super(headlinetileview);
        view = headlinetileview;
    }

    public static HeadlineViewHolder create(Context context)
    {
        return new HeadlineViewHolder(new HeadlineTileView(context));
    }
}
