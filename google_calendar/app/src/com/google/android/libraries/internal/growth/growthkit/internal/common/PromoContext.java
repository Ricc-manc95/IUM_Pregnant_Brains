// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableMap;
import com.google.protobuf.ByteString;
import com.google.protobuf.Internal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class PromoContext
    implements Parcelable
{
    public static abstract class Builder
    {

        public abstract PromoContext build();

        public abstract Builder setAccountName(String s);

        public abstract Builder setActionTypeIntentMap(Map map);

        public abstract Builder setPromoId(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoidentification);

        public abstract Builder setSerializedAdditionalData(ByteString bytestring);

        public abstract Builder setTriggeringEventTimeMs(long l);

        public Builder()
        {
        }
    }


    public static final android.os.Parcelable.Creator CREATOR = new _cls1();

    public PromoContext()
    {
    }

    public static Builder builder()
    {
        return (new AutoValue_PromoContext.Builder()).setSerializedAdditionalData(ByteString.EMPTY).setActionTypeIntentMap(RegularImmutableMap.EMPTY);
    }

    static ImmutableMap readActionIntentMapFromParcel(Parcel parcel)
    {
        com.google.common.collect.ImmutableMap.Builder builder1 = new com.google.common.collect.ImmutableMap.Builder();
        int j = parcel.readInt();
        for (int i = 0; i < j; i++)
        {
            builder1.put(com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(parcel.readInt()), (Intent)parcel.readParcelable(android/content/Intent.getClassLoader()));
        }

        return builder1.build();
    }

    public abstract String accountName();

    public abstract ImmutableMap actionTypeIntentMap();

    public int describeContents()
    {
        return 0;
    }

    public abstract com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoId();

    public abstract ByteString serializedAdditionalData();

    public abstract long triggeringEventTimeMs();

    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(accountName());
        parcel.writeParcelable(new com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse(null, promoId()), i);
        Object obj = serializedAdditionalData();
        int j = ((ByteString) (obj)).size();
        byte abyte0[];
        if (j == 0)
        {
            abyte0 = Internal.EMPTY_BYTE_ARRAY;
        } else
        {
            abyte0 = new byte[j];
            ((ByteString) (obj)).copyToInternal(abyte0, 0, 0, j);
        }
        parcel.writeByteArray(abyte0);
        parcel.writeLong(triggeringEventTimeMs());
        abyte0 = actionTypeIntentMap();
        parcel.writeInt(abyte0.size());
        for (abyte0 = abyte0.entrySet().iterator(); abyte0.hasNext(); parcel.writeParcelable((Parcelable)((java.util.Map.Entry) (obj)).getValue(), i))
        {
            obj = (java.util.Map.Entry)abyte0.next();
            parcel.writeInt(((com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType)((java.util.Map.Entry) (obj)).getKey()).value);
        }

    }


    private class _cls1
        implements android.os.Parcelable.Creator
    {

        public final Object createFromParcel(Parcel parcel)
        {
            String s = parcel.readString();
            com.google.protobuf.contrib.android.ProtoParsers.ParcelableProto parcelableproto = (com.google.protobuf.contrib.android.ProtoParsers.ParcelableProto)parcel.readParcelable(com/google/identity/boq/growth/promoprovider/proto/PromoProvider$PromoIdentification.getClassLoader());
            ByteString bytestring = ByteString.copyFrom(parcel.createByteArray());
            long l = parcel.readLong();
            return PromoContext.builder().setAccountName(s).setPromoId((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification)parcelableproto.getMessage(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE, ExtensionRegistryLite.getGeneratedRegistry())).setSerializedAdditionalData(bytestring).setTriggeringEventTimeMs(l).setActionTypeIntentMap(PromoContext.readActionIntentMapFromParcel(parcel)).build();
        }

        public final Object[] newArray(int i)
        {
            return new PromoContext[i];
        }

        _cls1()
        {
        }
    }

}
