// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter;

import android.content.Context;
import android.view.View;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.utils.activity.ActivityUtils;
import com.google.android.calendar.utils.snackbar.SnackbarShower;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter:
//            ConferenceAdapter

public class VideoConferenceAdapter extends ConferenceAdapter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/conference/thirdparty/adapter/VideoConferenceAdapter);

    public VideoConferenceAdapter(Context context, SnackbarShower snackbarshower, Conference conference)
    {
        super(context, snackbarshower, conference);
    }

    protected final int getOnLongClickSuccessString()
    {
        return 0x7f13013c;
    }

    public void onClick(View view)
    {
        ActivityUtils.startActivityForUri(super.context, super.uri, TAG);
    }

}
