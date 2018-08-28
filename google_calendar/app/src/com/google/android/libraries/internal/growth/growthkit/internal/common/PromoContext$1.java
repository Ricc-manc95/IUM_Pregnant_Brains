// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import android.os.Parcel;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.common:
//            PromoContext

final class ilder
    implements android.os.tor
{

    public final Object createFromParcel(Parcel parcel)
    {
        String s = parcel.readString();
        com.google.protobuf.contrib.android.rcelableProto rcelableproto = (com.google.protobuf.contrib.android.rcelableProto)parcel.readParcelable(com/google/identity/boq/growth/promoprovider/proto/PromoProvider$PromoIdentification.getClassLoader());
        ByteString bytestring = ByteString.copyFrom(parcel.createByteArray());
        long l = parcel.readLong();
        return PromoContext.builder().setAccountName(s).setPromoId((com.google.identity.boq.growth.promoprovider.proto.romoIdentification)rcelableproto.getMessage(com.google.identity.boq.growth.promoprovider.proto.romoIdentification.DEFAULT_INSTANCE, ExtensionRegistryLite.getGeneratedRegistry())).setSerializedAdditionalData(bytestring).setTriggeringEventTimeMs(l).setActionTypeIntentMap(PromoContext.readActionIntentMapFromParcel(parcel)).build();
    }

    public final Object[] newArray(int i)
    {
        return new PromoContext[i];
    }

    ilder()
    {
    }
}
