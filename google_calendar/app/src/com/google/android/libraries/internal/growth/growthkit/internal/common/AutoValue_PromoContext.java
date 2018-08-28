// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common:
//            PromoContext

final class AutoValue_PromoContext extends PromoContext
{

    private final String accountName;
    private final ImmutableMap actionTypeIntentMap;
    private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId;
    private final ByteString serializedAdditionalData;
    private final long triggeringEventTimeMs;

    AutoValue_PromoContext(String s, com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoidentification, ByteString bytestring, long l, ImmutableMap immutablemap)
    {
        accountName = s;
        promoId = promoidentification;
        serializedAdditionalData = bytestring;
        triggeringEventTimeMs = l;
        actionTypeIntentMap = immutablemap;
    }

    public final String accountName()
    {
        return accountName;
    }

    public final ImmutableMap actionTypeIntentMap()
    {
        return actionTypeIntentMap;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (obj instanceof PromoContext)
            {
                obj = (PromoContext)obj;
                if ((accountName != null ? !accountName.equals(((PromoContext) (obj)).accountName()) : ((PromoContext) (obj)).accountName() != null) || (!promoId.equals(((PromoContext) (obj)).promoId()) || !serializedAdditionalData.equals(((PromoContext) (obj)).serializedAdditionalData()) || triggeringEventTimeMs != ((PromoContext) (obj)).triggeringEventTimeMs() || !actionTypeIntentMap.equals(((PromoContext) (obj)).actionTypeIntentMap())))
                {
                    return false;
                }
            } else
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i;
        if (accountName == null)
        {
            i = 0;
        } else
        {
            i = accountName.hashCode();
        }
        return ((((i ^ 0xf4243) * 0xf4243 ^ promoId.hashCode()) * 0xf4243 ^ serializedAdditionalData.hashCode()) * 0xf4243 ^ (int)(triggeringEventTimeMs >>> 32 ^ triggeringEventTimeMs)) * 0xf4243 ^ actionTypeIntentMap.hashCode();
    }

    public final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId()
    {
        return promoId;
    }

    public final ByteString serializedAdditionalData()
    {
        return serializedAdditionalData;
    }

    public final String toString()
    {
        String s = accountName;
        String s1 = String.valueOf(promoId);
        String s2 = String.valueOf(serializedAdditionalData);
        long l = triggeringEventTimeMs;
        String s3 = String.valueOf(actionTypeIntentMap);
        return (new StringBuilder(String.valueOf(s).length() + 129 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length())).append("PromoContext{accountName=").append(s).append(", promoId=").append(s1).append(", serializedAdditionalData=").append(s2).append(", triggeringEventTimeMs=").append(l).append(", actionTypeIntentMap=").append(s3).append("}").toString();
    }

    public final long triggeringEventTimeMs()
    {
        return triggeringEventTimeMs;
    }
}
