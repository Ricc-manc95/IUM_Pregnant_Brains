// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import com.google.apps.docs.diagnostics.impressions.proto.sessioninvariants.DistributionInvariants;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.Protobuf;
import com.google.protobuf.RawMessageInfo;
import com.google.protobuf.Schema;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

// Referenced classes of package com.google.android.calendar:
//            CalendarLoggingExtension

public static final class DEFAULT_INSTANCE extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class ActionType extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ActionType $VALUES[];
        public static final ActionType ACTIVITY_CREATE;
        public static final ActionType APPLICATION_VIEW;
        public static final ActionType CHANGE_EVENT;
        public static final ActionType CHANGE_GOAL;
        public static final ActionType CHANGE_REMINDER;
        public static final ActionType CREATE_EVENT;
        public static final ActionType CREATE_GOAL;
        public static final ActionType CREATE_REMINDER;
        public static final ActionType RESPONDED_EVENT;
        private static final ActionType UNKNOWN;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static ActionType forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return UNKNOWN;

            case 1: // '\001'
                return CREATE_EVENT;

            case 2: // '\002'
                return CHANGE_EVENT;

            case 3: // '\003'
                return RESPONDED_EVENT;

            case 4: // '\004'
                return APPLICATION_VIEW;

            case 5: // '\005'
                return CREATE_REMINDER;

            case 6: // '\006'
                return CHANGE_REMINDER;

            case 7: // '\007'
                return CREATE_GOAL;

            case 8: // '\b'
                return CHANGE_GOAL;

            case 9: // '\t'
                return ACTIVITY_CREATE;
            }
        }

        public static ActionType[] values()
        {
            return (ActionType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            UNKNOWN = new ActionType("UNKNOWN", 0, 0);
            CREATE_EVENT = new ActionType("CREATE_EVENT", 1, 1);
            CHANGE_EVENT = new ActionType("CHANGE_EVENT", 2, 2);
            RESPONDED_EVENT = new ActionType("RESPONDED_EVENT", 3, 3);
            APPLICATION_VIEW = new ActionType("APPLICATION_VIEW", 4, 4);
            CREATE_REMINDER = new ActionType("CREATE_REMINDER", 5, 5);
            CHANGE_REMINDER = new ActionType("CHANGE_REMINDER", 6, 6);
            CREATE_GOAL = new ActionType("CREATE_GOAL", 7, 7);
            CHANGE_GOAL = new ActionType("CHANGE_GOAL", 8, 8);
            ACTIVITY_CREATE = new ActionType("ACTIVITY_CREATE", 9, 9);
            $VALUES = (new ActionType[] {
                UNKNOWN, CREATE_EVENT, CHANGE_EVENT, RESPONDED_EVENT, APPLICATION_VIEW, CREATE_REMINDER, CHANGE_REMINDER, CREATE_GOAL, CHANGE_GOAL, ACTIVITY_CREATE
            });
            class _cls2
                implements com.google.protobuf.Internal.EnumVerifier
            {

                public final boolean isInRange(int i)
                {
 