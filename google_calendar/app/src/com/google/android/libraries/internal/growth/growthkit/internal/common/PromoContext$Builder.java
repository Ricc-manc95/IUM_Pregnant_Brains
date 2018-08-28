// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import com.google.protobuf.ByteString;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common:
//            PromoContext

public static abstract class a
{

    public abstract PromoContext build();

    public abstract a setAccountName(String s);

    public abstract a setActionTypeIntentMap(Map map);

    public abstract a setPromoId(com.google.identity.boq.growth.promoprovider.proto.entification entification);

    public abstract a setSerializedAdditionalData(ByteString bytestring);

    public abstract a setTriggeringEventTimeMs(long l);

    public a()
    {
    }
}
