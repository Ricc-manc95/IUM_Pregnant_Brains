// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event.conference;

import android.os.Parcelable;
import com.google.common.base.Platform;
import com.google.common.io.BaseEncoding;
import java.util.UUID;

// Referenced classes of package com.google.android.calendar.api.event.conference:
//            AutoValue_CreateConferenceRequest

public abstract class CreateConferenceRequest
    implements Parcelable
{
    public static abstract class ConferenceRequestStatus
        implements Parcelable
    {

        public abstract int getStatusCode();

        public ConferenceRequestStatus()
        {
        }
    }


    public CreateConferenceRequest()
    {
    }

    static CreateConferenceRequest newCreateRequest(String s)
    {
        s = ConferenceSolution.Key.create(s);
        BaseEncoding baseencoding = BaseEncoding.BASE32_HEX.lowerCase().omitPadding();
        byte abyte0[] = UUID.randomUUID().toString().getBytes();
        return new AutoValue_CreateConferenceRequest(Platform.nullToEmpty(baseencoding.encode(abyte0, 0, abyte0.length)), s, null);
    }

    public abstract ConferenceRequestStatus getConferenceRequestStatus();

    public abstract ConferenceSolution.Key getConferenceSolutionKey();

    public abstract String getRequestId();
}
