// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import android.os.Parcel;
import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.protobuf.MessageLite;

// Referenced classes of package com.google.android.calendar.api.event:
//            $AutoValue_V2AEventKey, V2AEventKey

final class AutoValue_V2AEventKey extends $AutoValue_V2AEventKey
{

    public static final android.os.Parcelable.Creator CREATOR = new _cls1();
    public static final V2AEventKey.EventKeyAdapter EVENT_KEY_ADAPTER = new V2AEventKey.EventKeyAdapter();

    AutoValue_V2AEventKey(EventKey eventkey)
    {
        super(eventkey);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeByteArray(getV2Key().toByteArray());
    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            return new AutoValue_V2AEventKey((EventKey)AutoValue_V2AEventKey.EVENT_KEY_ADAPTER.fromParcel(parcel));
        }

        public final Object[] newArray(int i)
        {
            return new AutoValue_V2AEventKey[i];
        }

        _cls1()
        {
        }
    }

}
