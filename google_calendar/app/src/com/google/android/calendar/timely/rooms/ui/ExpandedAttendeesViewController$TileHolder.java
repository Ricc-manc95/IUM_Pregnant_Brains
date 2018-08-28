// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.calendar.tiles.view.BadgedIconTile;

final class name extends android.support.v7.widget.roller.TileHolder
{

    public final TextView name;
    public final BadgedIconTile tile;

    static name create(ViewGroup viewgroup)
    {
        viewgroup = (BadgedIconTile)LayoutInflater.from(viewgroup.getContext()).inflate(0x7f050025, viewgroup, false);
        return new <init>(viewgroup, (TextView)viewgroup.findViewById(0x7f10038f));
    }

    private (BadgedIconTile badgedicontile, TextView textview)
    {
        super(badgedicontile);
        tile = badgedicontile;
        name = textview;
    }
}
