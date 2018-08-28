// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.identity.boq.growth.common.proto;

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

// Referenced classes of package com.google.identity.boq.growth.common.proto:
//            ClientProto

public static final class ClientId.value extends GeneratedMessageLite
    implements MessageLiteOrBuilder
{
    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder
        implements MessageLiteOrBuilder
    {

        Builder()
        {
            super(ClientProto.Client.DEFAULT_INSTANCE);
        }
    }

    public static final class ClientId extends Enum
        implements com.google.protobuf.Internal.EnumLite
    {

        private static final ClientId $VALUES[];
        private static final ClientId ANDROID_MWEB;
        private static final ClientId ANDROID_NATIVE_NDN;
        private static final ClientId APPS_RECOMMENDATION;
        private static final ClientId CLIENT_UNSPECIFIED;
        private static final ClientId GOOGLE_TIPS;
        private static final ClientId GROWTH_KIT;
        public static final ClientId GROWTH_KIT_ANDROID;
        private static final ClientId GROWTH_KIT_ANDROID_GMSCORE;
        private static final ClientId MOBILE_WEB_APP;
        private static final ClientId NEW_DEVICE_NOTIFICATION;
        private static final ClientId SECURITY_ADVISOR;
        private static final ClientId WEB_APPLICATION;
        public static final com.google.protobuf.Internal.EnumVerifier internalVerifier = new _cls2();
        public final int value;

        public static ClientId forNumber(int i)
        {
            switch (i)
            {
            default:
                return null;

            case 0: // '\0'
                return CLIENT_UNSPECIFIED;

            case 1: // '\001'
                return GROWTH_KIT;

            case 2: // '\002'
                return NEW_DEVICE_NOTIFICATION;

            case 3: // '\003'
                return WEB_APPLICATION;

            case 4: // '\004'
                return GOOGLE_TIPS;

            case 5: // '\005'
                return APPS_RECOMMENDATION;

            case 6: // '\006'
                return GROWTH_KIT_ANDROID;

            case 7: // '\007'
                return GROWTH_KIT_ANDROID_GMSCORE;

            case 8: // '\b'
                return ANDROID_NATIVE_NDN;

            case 9: // '\t'
                return ANDROID_MWEB;

            case 10: // '\n'
                return SECURITY_ADVISOR;

            case 11: // '\013'
                return MOBILE_WEB_APP;
            }
        }

        public static ClientId[] values()
        {
            return (ClientId[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        static 
        {
            CLIENT_UNSPECIFIED = new ClientId("CLIENT_UNSPECIFIED", 0, 0);
            GROWTH_KIT = new ClientId("GROWTH_KIT", 1, 1);
            NEW_DEVICE_NOTIFICATION = new ClientId("NEW_DEVICE_NOTIFICATION", 2, 2);
            WEB_APPLICATION = new ClientId("WEB_APPLICATION", 3, 3);
            GOOGLE_TIPS = new ClientId("GOOGLE_TIPS", 4, 4);
            APPS_RECOMMENDATION = new ClientId("APPS_RECOMMENDATION", 5, 5);
            GROWTH_KIT_ANDROID = new ClientId("GROWTH_KIT_ANDROID", 6, 6);
            GROWTH_KIT_ANDROID_GMSCORE = new ClientId("GROWTH_KIT_ANDROID_GMSCORE", 7, 7);
       