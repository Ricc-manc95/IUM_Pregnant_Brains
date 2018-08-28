// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter;

import android.content.Context;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.conference.Conference;
import com.google.android.calendar.api.event.conference.ConferenceData;
import com.google.android.calendar.api.event.conference.ConferenceSolution;
import com.google.android.calendar.utils.phone.PhoneUtil;
import com.google.android.calendar.utils.snackbar.SnackbarShower;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Platform;
import com.google.common.base.Present;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference.thirdparty.adapter:
//            VideoConferenceAdapter, PhoneConferenceAdapter, MoreConferenceAdapter, SipConferenceAdapter, 
//            ConferenceAdapter

public class ConferenceDataAdapter
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/conference/thirdparty/adapter/ConferenceDataAdapter);
    public final Optional moreEntryPoint;
    public final String notes;
    public final Optional phoneEntryPoint;
    public final Optional sipEntryPoint;
    public final String solutionName;
    public final Optional videoEntryPoint;

    private ConferenceDataAdapter(String s, Uri uri, ConferenceAdapter conferenceadapter, ConferenceAdapter conferenceadapter1, ConferenceAdapter conferenceadapter2, ConferenceAdapter conferenceadapter3, String s1)
    {
        solutionName = Platform.nullToEmpty(s);
        if (uri == null)
        {
            s = Absent.INSTANCE;
        } else
        {
            new Present(uri);
        }
        if (conferenceadapter == null)
        {
            s = Absent.INSTANCE;
        } else
        {
            s = new Present(conferenceadapter);
        }
        videoEntryPoint = s;
        if (conferenceadapter1 == null)
        {
            s = Absent.INSTANCE;
        } else
        {
            s = new Present(conferenceadapter1);
        }
        phoneEntryPoint = s;
        if (conferenceadapter2 == null)
        {
            s = Absent.INSTANCE;
        } else
        {
            s = new Present(conferenceadapter2);
        }
        sipEntryPoint = s;
        if (conferenceadapter3 == null)
        {
            s = Absent.INSTANCE;
        } else
        {
            s = new Present(conferenceadapter3);
        }
        moreEntryPoint = s;
        notes = s1;
    }

    public static ConferenceDataAdapter fromConferenceData(Context context, SnackbarShower snackbarshower, ConferenceData conferencedata)
    {
        String s1;
        Uri uri;
        MoreConferenceAdapter moreconferenceadapter;
        SipConferenceAdapter sipconferenceadapter;
        PhoneConferenceAdapter phoneconferenceadapter;
        VideoConferenceAdapter videoconferenceadapter;
        ImmutableList immutablelist;
        int i;
        int k;
        boolean flag = context instanceof PhoneUtil;
        Object obj = String.valueOf(context);
        obj = (new StringBuilder(String.valueOf(obj).length() + 35)).append("Context must be PhoneUtil, but was ").append(((String) (obj))).toString();
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        s1 = null;
        uri = null;
        if (conferencedata.getConferenceSolution() != null)
        {
            s1 = conferencedata.getConferenceSolution().getName();
            uri = Uri.parse(conferencedata.getConferenceSolution().getIconUri()).normalizeScheme();
        }
        sipconferenceadapter = null;
        moreconferenceadapter = null;
        immutablelist = (ImmutableList)conferencedata.getConferences();
        k = immutablelist.size();
        obj = (UnmodifiableIterator)null;
        videoconferenceadapter = null;
        i = 0;
        phoneconferenceadapter = null;
_L22:
        String s2;
        Conference conference;
        int j;
        if (i >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj1 = immutablelist.get(i);
        i++;
        conference = (Conference)obj1;
        j = conference.getType();
        s2 = conference.getEntryPointType();
        if (s2 == null) goto _L2; else goto _L1
_L1:
        String s = s2;
        if (!"unknown".equals(s2)) goto _L3; else goto _L2
_L2:
        j;
        JVM INSTR tableswitch 0 5: default 232
    //                   0 340
    //                   1 346
    //                   2 352
    //                   3 358
    //                   4 364
    //                   5 370;
           goto _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L4:
        s = "unknown";
_L3:
        s.hashCode();
        JVM INSTR lookupswitch 4: default 280
    //                   113882: 421
    //                   3357525: 406
    //                   106642798: 391
    //                   112202875: 376;
           goto _L11 _L12 _L13 _L14 _L15
_L11:
        j = -1;
_L17:
        switch (j)
        {
        default:
            LogUtils.w(TAG, "Unknown entry point type: %s", new Object[] {
                conference.getEntryPointType()
            });
            break;

        case 0: // '\0'
            if (videoconferenceadapter == null)
            {
                videoconferenceadapter = new VideoConferenceAdapter(context, snackbarshower, conference);
            }
            break;

        case 1: // '\001'
            if (phoneconferenceadapter == null)
            {
                phoneconferenceadapter = new PhoneConferenceAdapter(context, snackbarshower, (PhoneUtil)context, conference);
            }
            break;

        case 2: // '\002'
            if (moreconferenceadapter == null)
            {
                moreconferenceadapter = new MoreConferenceAdapter(context, snackbarshower, conference);
            }
            break;

        case 3: // '\003'
            if (sipconferenceadapter == null)
            {
                sipconferenceadapter = new SipConferenceAdapter(context, snackbarshower, (PhoneUtil)context, conference);
            }
            break;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        s = "unknown";
          goto _L3
_L6:
        s = "video";
          goto _L3
_L7:
        s = "video";
          goto _L3
_L8:
        s = "video";
          goto _L3
_L9:
        s = "phone";
          goto _L3
_L10:
        s = "more";
          goto _L3
_L15:
        if (!s.equals("video")) goto _L11; else goto _L16
_L16:
        j = 0;
          goto _L17
_L14:
        if (!s.equals("phone")) goto _L11; else goto _L18
_L18:
        j = 1;
          goto _L17
_L13:
        if (!s.equals("more")) goto _L11; else goto _L19
_L19:
        j = 2;
          goto _L17
_L12:
        if (!s.equals("sip")) goto _L11; else goto _L20
_L20:
        j = 3;
          goto _L17
        if (true) goto _L22; else goto _L21
_L21:
        return new ConferenceDataAdapter(s1, uri, videoconferenceadapter, phoneconferenceadapter, sipconferenceadapter, moreconferenceadapter, conferencedata.getNotes());
    }

}
