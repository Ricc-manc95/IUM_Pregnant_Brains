// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.content.Context;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.tiles.view.TextTileView;
import java.net.URI;

public final class MeetTileUpdater
{

    public final Context context;
    public final TextTileView flingingTile;
    public final TextTileView liveStreamTile;
    public final TextTileView moreOptionsTile;
    public final TextTileView phoneTile;
    public final TextTileView videoTile;

    MeetTileUpdater(TextTileView texttileview, TextTileView texttileview1, TextTileView texttileview2, TextTileView texttileview3, TextTileView texttileview4)
    {
        context = texttileview1.getContext();
        flingingTile = texttileview;
        videoTile = texttileview1;
        liveStreamTile = texttileview2;
        phoneTile = texttileview3;
        moreOptionsTile = texttileview4;
    }

    static String getLabel(Conference conference)
    {
        String s;
        try
        {
            Object obj = URI.create(conference.getUri());
            s = String.valueOf(((URI) (obj)).getHost());
            obj = String.valueOf(((URI) (obj)).getPath());
            if (((String) (obj)).length() != 0)
            {
                return s.concat(((String) (obj)));
            }
            s = new String(s);
        }
        catch (Exception exception)
        {
            return conference.getName();
        }
        return s;
    }
}
