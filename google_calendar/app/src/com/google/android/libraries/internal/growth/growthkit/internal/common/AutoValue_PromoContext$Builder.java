// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.protobuf.ByteString;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common:
//            AutoValue_PromoContext, PromoContext

final class  extends 
{

    private String accountName;
    private ImmutableMap actionTypeIntentMap;
    private com.google.identity.boq.growth.promoprovider.proto.on promoId;
    private ByteString serializedAdditionalData;
    private Long triggeringEventTimeMs;

    public final PromoContext build()
    {
        if (actionTypeIntentMap == null)
        {
            actionTypeIntentMap = RegularImmutableMap.EMPTY;
        }
        String s2 = "";
        if (promoId == null)
        {
            s2 = String.valueOf("").concat(" promoId");
        }
        String s = s2;
        if (serializedAdditionalData == null)
        {
            s = String.valueOf(s2).concat(" serializedAdditionalData");
        }
        s2 = s;
        if (triggeringEventTimeMs == null)
        {
            s2 = String.valueOf(s).concat(" triggeringEventTimeMs");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_PromoContext(accountName, promoId, serializedAdditionalData, triggeringEventTimeMs.longValue(), actionTypeIntentMap);
        }
    }

    public final actionTypeIntentMap setAccountName(String s)
    {
        accountName = s;
        return this;
    }

    public final accountName setActionTypeIntentMap(Map map)
    {
        actionTypeIntentMap = ImmutableMap.copyOf(map);
        return this;
    }

    public final actionTypeIntentMap setPromoId(com.google.identity.boq.growth.promoprovider.proto.on on)
    {
        if (on == null)
        {
            throw new NullPointerException("Null promoId");
        } else
        {
            promoId = on;
            return this;
        }
    }

    public final promoId setSerializedAdditionalData(ByteString bytestring)
    {
        if (bytestring == null)
        {
            throw new NullPointerException("Null serializedAdditionalData");
        } else
        {
            serializedAdditionalData = bytestring;
            return this;
        }
    }

    public final serializedAdditionalData setTriggeringEventTimeMs(long l)
    {
        triggeringEventTimeMs = Long.valueOf(l);
        return this;
    }

    ()
    {
    }
}
