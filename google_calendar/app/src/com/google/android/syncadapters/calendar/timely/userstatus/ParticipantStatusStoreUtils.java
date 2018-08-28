// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.syncadapters.calendar.timely.userstatus;

import android.util.Base64;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.api.event.userstatus.AutoReply;
import com.google.android.calendar.api.event.userstatus.AutoValue_UserStatus;
import com.google.android.calendar.api.event.userstatus.OutOfOffice;
import com.google.android.calendar.api.event.userstatus.UserStatus;
import com.google.common.base.Platform;
import com.google.protobuf.nano.InvalidProtocolBufferNanoException;
import com.google.protobuf.nano.MessageNano;

public class ParticipantStatusStoreUtils
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/syncadapters/calendar/timely/userstatus/ParticipantStatusStoreUtils);

    public ParticipantStatusStoreUtils()
    {
    }

    public static String apiObjectToSerializedProto(UserStatus userstatus)
    {
        byte abyte0[] = null;
        if (userstatus == null)
        {
            return null;
        }
        Object obj = userstatus.getOutOfOffice();
        int i;
        if (obj == null)
        {
            userstatus = abyte0;
        } else
        {
            userstatus = new com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus();
            com.google.calendar.rosy.calendar.nano.UserStatusProto.OutOfOffice outofoffice = new com.google.calendar.rosy.calendar.nano.UserStatusProto.OutOfOffice();
            outofoffice.declineConflictingEvents = ((OutOfOffice) (obj)).isAutoDeclineEnabled();
            outofoffice.calendarDeclineMessage = ((OutOfOffice) (obj)).getCalendarDeclineMessage();
            if (((OutOfOffice) (obj)).getAutoReply() != null)
            {
                obj = ((OutOfOffice) (obj)).getAutoReply();
                com.google.calendar.rosy.calendar.nano.UserStatusProto.AutoReply autoreply = new com.google.calendar.rosy.calendar.nano.UserStatusProto.AutoReply();
                autoreply.enabled = ((AutoReply) (obj)).isEnabled();
                autoreply.subject = ((AutoReply) (obj)).getSubject();
                autoreply.body = ((AutoReply) (obj)).getBody();
                autoreply.restrictToContacts = ((AutoReply) (obj)).isRestrictToContacts();
                autoreply.restrictToDomain = ((AutoReply) (obj)).isRestrictToDomain();
                outofoffice.autoReply = autoreply;
            }
            if (outofoffice == null)
            {
                if (((com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus) (userstatus)).oneof_status_ == 0)
                {
                    userstatus.oneof_status_ = -1;
                }
                userstatus.outOfOffice = null;
            } else
            {
                userstatus.oneof_status_ = -1;
                userstatus.oneof_status_ = 0;
                userstatus.outOfOffice = outofoffice;
            }
        }
        i = userstatus.computeSerializedSize();
        userstatus.cachedSize = i;
        abyte0 = new byte[i];
        MessageNano.toByteArray(userstatus, abyte0, 0, abyte0.length);
        return Base64.encodeToString(abyte0, 8);
    }

    public static UserStatus serializedProtoToApiObject(String s)
    {
        boolean flag = true;
        s = Base64.decode(s, 8);
        s = (com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus)MessageNano.mergeFrom(new com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus(), s, 0, s.length);
        com.google.android.calendar.api.event.userstatus.OutOfOffice.Builder builder;
        com.google.calendar.rosy.calendar.nano.UserStatusProto.AutoReply autoreply;
        if (((com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus) (s)).oneof_status_ != 0)
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (((com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus) (s)).oneof_status_ != 0) goto _L4; else goto _L3
_L3:
        s = ((com.google.calendar.rosy.calendar.nano.UserStatusProto.UserStatus) (s)).outOfOffice;
_L6:
        autoreply = ((com.google.calendar.rosy.calendar.nano.UserStatusProto.OutOfOffice) (s)).autoReply;
        builder = (new com.google.android.calendar.api.event.userstatus..AutoValue_OutOfOffice.Builder()).setAutoDeclineEnabled(false).setAutoDeclineEnabled(((com.google.calendar.rosy.calendar.nano.UserStatusProto.OutOfOffice) (s)).declineConflictingEvents).setCalendarDeclineMessage(Platform.emptyToNull(((com.google.calendar.rosy.calendar.nano.UserStatusProto.OutOfOffice) (s)).calendarDeclineMessage));
        if (autoreply == null)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        s = (new com.google.android.calendar.api.event.userstatus..AutoValue_AutoReply.Builder()).setEnabled(false).setRestrictToContacts(false).setRestrictToDomain(true).setEnabled(autoreply.enabled).setSubject(Platform.emptyToNull(autoreply.subject)).setBody(Platform.emptyToNull(autoreply.body)).setRestrictToContacts(autoreply.restrictToContacts).setRestrictToDomain(autoreply.restrictToDomain).build();
_L7:
        return new AutoValue_UserStatus(builder.setAutoReply(s).build());
_L2:
        s = new AutoValue_UserStatus(null);
        return s;
        s;
_L5:
        LogUtils.e(TAG, s, "Cannot parse participant status", new Object[0]);
        return null;
        s;
        if (true) goto _L5; else goto _L4
_L4:
        s = null;
          goto _L6
        s = null;
          goto _L7
    }

}
