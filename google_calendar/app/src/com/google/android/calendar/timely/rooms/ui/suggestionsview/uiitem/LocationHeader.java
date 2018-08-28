// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem;

import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui.suggestionsview.uiitem:
//            UiItem

public class LocationHeader extends UiItem
{

    public final Runnable audioClickCallback;
    public final Runnable capacityClickCallback;
    public final String contentDescription;
    public final boolean hasSuggestions;
    public final Runnable headerClickCallback;
    public final String message;
    public final boolean requireAudio;
    public final boolean requireVideo;
    public final int requiredSeats;
    public final int state;
    public final Runnable videoClickCallback;

    public LocationHeader(String s, String s1, List list, Runnable runnable, boolean flag, int i, boolean flag1, 
            boolean flag2, int j, Runnable runnable1, Runnable runnable2, Runnable runnable3)
    {
        message = s;
        contentDescription = s1;
        headerClickCallback = runnable;
        hasSuggestions = flag;
        requiredSeats = i;
        requireVideo = flag1;
        requireAudio = flag2;
        state = j;
        capacityClickCallback = runnable1;
        videoClickCallback = runnable2;
        audioClickCallback = runnable3;
    }
}
