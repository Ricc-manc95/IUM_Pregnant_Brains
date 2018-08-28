// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.api.event;

import com.google.android.calendar.utils.proto.ProtoAdapter;
import com.google.calendar.v2a.shared.storage.proto.EventKey;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;

// Referenced classes of package com.google.android.calendar.api.event:
//            V2AEventKey

public static final class  extends ProtoAdapter
{

    protected final MessageLite parseFrom(byte abyte0[])
        throws InvalidProtocolBufferException
    {
        return (EventKey)GeneratedMessageLite.parseFrom(EventKey.DEFAULT_INSTANCE, abyte0);
    }

    public ()
    {
    }
}
