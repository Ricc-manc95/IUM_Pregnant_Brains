// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.location.fullscreen;

import com.google.android.calendar.api.event.location.EventLocation;
import com.google.common.util.concurrent.FutureCallback;

// Referenced classes of package com.google.android.calendar.newapi.segment.location.fullscreen:
//            LocationEditFullScreenController

final class tion
    implements FutureCallback
{

    private final LocationEditFullScreenController this$0;
    private final int val$acceptedSuggestionId;
    private final tion val$suggestion;

    public final void onFailure(Throwable throwable)
    {
        if (val$acceptedSuggestionId == currentAcceptedSuggestionId)
        {
            onStructuredLocationRetrieved(LocationEditFullScreenController.createLegacyLocation(val$suggestion));
        }
    }

    public final void onSuccess(Object obj)
    {
        obj = (EventLocation)obj;
        if (val$acceptedSuggestionId == currentAcceptedSuggestionId)
        {
            onStructuredLocationRetrieved(((EventLocation) (obj)));
        }
    }

    tion()
    {
        this$0 = final_locationeditfullscreencontroller;
        val$acceptedSuggestionId = i;
        val$suggestion = tion.this;
        super();
    }
}
