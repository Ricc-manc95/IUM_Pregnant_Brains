// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.sync.impl;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.os.ConfigurationCompat;
import android.support.v4.os.LocaleListCompat;
import android.support.v4.os.LocaleListInterface;
import android.text.TextUtils;
import com.google.android.libraries.internal.growth.growthkit.internal.accounts.AccountManager;
import com.google.android.libraries.internal.growth.growthkit.internal.common.BuildInfo;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable;
import com.google.android.libraries.internal.growth.growthkit.internal.pseudonymous.PseudonymousIdHelper;
import com.google.android.libraries.internal.growth.growthkit.internal.rpc.GrowthApiClient;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.sync.PromotionSync;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.protobuf.GeneratedMessageLite;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;

public final class PromotionSyncImpl
    implements PromotionSync
{

    public static final Logger logger = new Logger();
    public final AccountManager accountManager;
    public final MessageStore cappedPromotionStoreProvider;
    public final Clock clock;
    public final Context context;
    public final ListeningExecutorService executor;
    public final GrowthApiClient growthApiClient;
    public final PerAccountProvider monitoredEventClearcutStoreProvider;
    public final PerAccountProvider monitoredEventVisualElementStoreProvider;
    private final String packageName;
    public final PerAccountProvider presentedPromoStoreProvider;
    public final PerAccountProvider promotionStoreProvider;
    private final PseudonymousIdHelper pseudonymousIdHelper;
    public final Provider setWriteDebugInfo;
    private final ListenableFuture sharedPrefsFuture;
    private final Provider syncGaiaAccounts;
    private final Provider syncOverrideCountry;
    private final Provider syncZwiebackAccounts;
    public final Map uiImageDownloadManager;
    private final Optional versionCode;
    private final Optional versionName;

    public PromotionSyncImpl(Context context1, ListenableFuture listenablefuture, ListeningExecutorService listeningexecutorservice, PerAccountProvider peraccountprovider, PerAccountProvider peraccountprovider1, MessageStore messagestore, PerAccountProvider peraccountprovider2, 
            PerAccountProvider peraccountprovider3, GrowthApiClient growthapiclient, AccountManager accountmanager, String s, Optional optional, Optional optional1, PseudonymousIdHelper pseudonymousidhelper, 
            Provider provider, Provider provider1, Provider provider2, Provider provider3, Clock clock1, Map map)
    {
        context = context1;
        sharedPrefsFuture = listenablefuture;
        executor = listeningexecutorservice;
        promotionStoreProvider = peraccountprovider1;
        cappedPromotionStoreProvider = messagestore;
        growthApiClient = growthapiclient;
        accountManager = accountmanager;
        presentedPromoStoreProvider = peraccountprovider;
        monitoredEventClearcutStoreProvider = peraccountprovider2;
        monitoredEventVisualElementStoreProvider = peraccountprovider3;
        packageName = s;
        versionCode = optional;
        versionName = optional1;
        pseudonymousIdHelper = pseudonymousidhelper;
        setWriteDebugInfo = provider;
        syncGaiaAccounts = provider1;
        syncZwiebackAccounts = provider2;
        syncOverrideCountry = provider3;
        clock = clock1;
        uiImageDownloadManager = map;
    }

    static final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse lambda$sync$10$PromotionSyncImpl$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NM4RRH5TJN4RRNEHK2US3IDTMMUS3IDTR6IP35E8NN0SJFEHNIUK3IDTMMUK3IDTR6IP35E8I4EPBKA1P6URBFED96ASRGDTN76P9R9HL62TJ15TM62RJ75TB6UQB47CKKOORFDKNMERRFCTM6ABR9CHIMST39EHSIUOJFE4NMESJFETQ6GBRGE9NMQRRGE9NNCQB4CLP2US3IDTQ6UBQGE9NMQRQGE9NNCQB4CLP28HR5EH874RRDDTPL4PBJE1NMSSR57C______0(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse)
    {
        return getpromosresponse;
    }

    static final ListenableFuture lambda$sync$12$PromotionSyncImpl(List list)
        throws Exception
    {
        return new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(list), true);
    }

    static final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse lambda$sync$6$PromotionSyncImpl$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NM4RRH5TJN4RRNEHK2US3IDTMMUS3IDTR6IP35E8NN0SJFEHNIUK3IDTMMUK3IDTR6IP35E8I4EPBKA1P6URBFED96ASRGDTN76P9R9HL62TJ15TM62RJ75TB6UQB47CKKOORFDKNMERRFCTM6ABR9CHIMST39EHSIUOJFE4NMESJFETQ6GBRGE9NMQRRGE9NNCQB4CLP2US3IDTQ6UBQGE9NMQRQGE9NNCQB4CLP28HR5EH874RRDDTPL4PBJE1NMSSR57C______0(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse)
    {
        return getpromosresponse;
    }

    static final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse lambda$sync$8$PromotionSyncImpl(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse)
        throws Exception
    {
        return getpromosresponse;
    }

    static final ListenableFuture lambda$syncAllAccounts$0$PromotionSyncImpl(List list)
        throws Exception
    {
        return new com.google.common.util.concurrent.CollectionFuture.ListFuture(ImmutableList.copyOf(list), true);
    }

    final com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder getRequestHeaderBuilder()
    {
        Object obj1 = (com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj = packageName;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        Object obj2 = (com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier)((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.Builder) (obj1)).instance;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj2.appIdCase_ = 4;
        obj2.appId_ = obj;
        if (versionCode.isPresent())
        {
            obj = String.valueOf(versionCode.get());
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            obj2 = (com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier)((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.Builder) (obj1)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj2.bitField0_ = ((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (obj2)).bitField0_ | 0x20;
            obj2.appVersion_ = ((String) (obj));
        }
        if (versionName.isPresent())
        {
            obj = (String)versionName.get();
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            obj2 = (com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier)((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier.Builder) (obj1)).instance;
            if (obj == null)
            {
                throw new NullPointerException();
            }
            obj2.bitField0_ = ((com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier) (obj2)).bitField0_ | 0x40;
            obj2.appVersionName_ = ((String) (obj));
        }
        obj = (com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj2 = (com.google.identity.boq.growth.common.proto.ClientProto.Client.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.common.proto.ClientProto.Client.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj3 = com.google.identity.boq.growth.common.proto.ClientProto.Client.ClientId.GROWTH_KIT_ANDROID;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        Object obj4 = (com.google.identity.boq.growth.common.proto.ClientProto.Client)((com.google.identity.boq.growth.common.proto.ClientProto.Client.Builder) (obj2)).instance;
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        obj4.bitField0_ = ((com.google.identity.boq.growth.common.proto.ClientProto.Client) (obj4)).bitField0_ | 1;
        obj4.clientId_ = ((com.google.identity.boq.growth.common.proto.ClientProto.Client.ClientId) (obj3)).value;
        int i = BuildInfo.getChangelistNumber();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj3 = (com.google.identity.boq.growth.common.proto.ClientProto.Client)((com.google.identity.boq.growth.common.proto.ClientProto.Client.Builder) (obj2)).instance;
        obj3.bitField0_ = ((com.google.identity.boq.growth.common.proto.ClientProto.Client) (obj3)).bitField0_ | 2;
        obj3.version_ = i;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj3 = (com.google.identity.boq.growth.common.proto.ClientProto.Client)((com.google.identity.boq.growth.common.proto.ClientProto.Client.Builder) (obj2)).instance;
        obj3.applicationIdentifier_ = (com.google.identity.boq.growth.common.proto.AppProto.ApplicationIdentifier)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
        obj3.bitField0_ = ((com.google.identity.boq.growth.common.proto.ClientProto.Client) (obj3)).bitField0_ | 4;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        ((com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader)((com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder) (obj)).instance).queryingApplication_ = (com.google.identity.boq.growth.common.proto.ClientProto.Client)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).build();
        ConfigurationCompat.getLocales(context.getResources().getConfiguration());
        obj2 = LocaleListCompat.IMPL.get(0);
        obj1 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj2 = ((Locale) (obj2)).toLanguageTag();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj3 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.Builder) (obj1)).instance;
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj3.bitField0_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo) (obj3)).bitField0_ | 4;
        obj3.lang_ = ((String) (obj2));
        obj2 = com.google.gaia.stats.BondUserAgent.Os.ANDROID_OS;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj3 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.Builder) (obj1)).instance;
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj3.bitField0_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo) (obj3)).bitField0_ | 8;
        obj3.os_ = ((com.google.gaia.stats.BondUserAgent.Os) (obj2)).value;
        obj2 = String.valueOf(android.os.Build.VERSION.SDK_INT);
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj3 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.Builder) (obj1)).instance;
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj3.bitField0_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo) (obj3)).bitField0_ | 0x10;
        obj3.osVersion_ = ((String) (obj2));
        obj2 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        obj3 = Build.BRAND;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj4 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo.Builder) (obj2)).instance;
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        obj4.bitField0_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo) (obj4)).bitField0_ | 1;
        obj4.deviceBrandName_ = ((String) (obj3));
        obj3 = Build.DISPLAY;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj4 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo.Builder) (obj2)).instance;
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        obj4.bitField0_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo) (obj4)).bitField0_ | 2;
        obj4.deviceMarketingName_ = ((String) (obj3));
        obj3 = Build.MODEL;
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
        obj4 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo.Builder) (obj2)).instance;
        if (obj3 == null)
        {
            throw new NullPointerException();
        }
        obj4.bitField0_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo) (obj4)).bitField0_ | 4;
        obj4.deviceModel_ = ((String) (obj3));
        obj2 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.BondHardwareInfo)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).build();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
        obj3 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.Builder) (obj1)).instance;
        if (obj2 == null)
        {
            throw new NullPointerException();
        }
        obj3.hardwareInfo_ = obj2;
        obj3.hardwareInfoCase_ = 7;
        obj2 = (String)syncOverrideCountry.get();
        if (!TextUtils.isEmpty(((CharSequence) (obj2))))
        {
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo deviceinfo = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo)((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo.Builder) (obj1)).instance;
            if (obj2 == null)
            {
                throw new NullPointerException();
            }
            deviceinfo.bitField0_ = deviceinfo.bitField0_ | 2;
            deviceinfo.country_ = ((String) (obj2));
        }
        obj1 = (com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        obj2 = (com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader)((com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        } else
        {
            obj2.deviceDescription_ = ((com.google.identity.boq.growth.common.proto.DeviceInfoProto.DeviceInfo) (obj1));
            return ((com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder) (obj));
        }
    }

    final ListenableFuture sync(com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder builder, String s)
    {
        AsyncCloseable asynccloseable = AsyncCloseable.fromFuture(((MessageStore)presentedPromoStoreProvider.forAccount(s)).getAll());
        class .Lambda._cls4
            implements Function
        {

            private final PromotionSyncImpl arg$1;
            private final com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder arg$2;
            private final String arg$3;

            public final Object apply(Object obj)
            {
                PromotionSyncImpl promotionsyncimpl = arg$1;
                Object obj1 = arg$2;
                String s1 = arg$3;
                Object obj2 = (Map)obj;
                obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.Builder) (obj)).instance).header_ = (com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
                obj2 = ((Map) (obj2)).values();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                obj1 = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.Builder) (obj)).instance;
                if (!((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest) (obj1)).presentedPromo_.isModifiable())
                {
                    obj1.presentedPromo_ = GeneratedMessageLite.mutableCopy(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest) (obj1)).presentedPromo_);
                }
                obj1 = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest) (obj1)).presentedPromo_;
                Internal.checkNotNull(obj2);
                if (obj2 instanceof LazyStringList)
                {
                    List list = ((LazyStringList)obj2).getUnderlyingElements();
                    obj2 = (LazyStringList)obj1;
                    int j1 = ((List) (obj1)).size();
                    for (obj1 = list.iterator(); ((Iterator) (obj1)).hasNext();)
                    {
                        Object obj3 = ((Iterator) (obj1)).next();
                        if (obj3 == null)
                        {
                            int i = ((LazyStringList) (obj2)).size();
                            obj = (new StringBuilder(37)).append("Element at index ").append(i - j1).append(" is null.").toString();
                            for (int j = ((LazyStringList) (obj2)).size() - 1; j >= j1; j--)
                            {
                                ((LazyStringList) (obj2)).remove(j);
                            }

                            throw new NullPointerException(((String) (obj)));
                        }
                        if (obj3 instanceof ByteString)
                        {
                            ((LazyStringList) (obj2)).add((ByteString)obj3);
                        } else
                        {
                            ((LazyStringList) (obj2)).add((String)obj3);
                        }
                    }

                } else
                if (obj2 instanceof PrimitiveNonBoxingCollection)
                {
                    ((List) (obj1)).addAll((Collection)obj2);
                } else
                {
                    if ((obj1 instanceof ArrayList) && (obj2 instanceof Collection))
                    {
                        obj4 = (ArrayList)obj1;
                        int k = ((List) (obj1)).size();
                        ((ArrayList) (obj4)).ensureCapacity(((Collection)obj2).size() + k);
                    }
                    int k1 = ((List) (obj1)).size();
                    obj2 = ((Iterable) (obj2)).iterator();
                    while (((Iterator) (obj2)).hasNext()) 
                    {
                        obj4 = ((Iterator) (obj2)).next();
                        if (obj4 == null)
                        {
                            int l = ((List) (obj1)).size();
                            obj = (new StringBuilder(37)).append("Element at index ").append(l - k1).append(" is null.").toString();
                            for (int i1 = ((List) (obj1)).size() - 1; i1 >= k1; i1--)
                            {
                                ((List) (obj1)).remove(i1);
                            }

                            throw new NullPointerException(((String) (obj)));
                        }
                        ((List) (obj1)).add(obj4);
                    }
                }
                obj1 = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.AdditionalUsers.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.promoprovider.proto.PromoProvider.AdditionalUsers.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                obj2 = promotionsyncimpl.accountManager.getAccountsNames().iterator();
                do
                {
                    if (!((Iterator) (obj2)).hasNext())
                    {
                        break;
                    }
                    Object obj4 = (String)((Iterator) (obj2)).next();
                    if (!TextUtils.equals(((CharSequence) (obj4)), s1))
                    {
                        obj4 = promotionsyncimpl.accountManager.getAccountId(((String) (obj4)));
                        if (obj4 != null)
                        {
                            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
                            com.google.identity.boq.growth.promoprovider.proto.PromoProvider.AdditionalUsers additionalusers = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.AdditionalUsers)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.AdditionalUsers.Builder) (obj1)).instance;
                            if (obj4 == null)
                            {
                                throw new NullPointerException();
                            }
                            if (!additionalusers.signedInUserId_.isModifiable())
                            {
                                additionalusers.signedInUserId_ = GeneratedMessageLite.mutableCopy(additionalusers.signedInUserId_);
                            }
                            additionalusers.signedInUserId_.add(obj4);
                        }
                    }
                } while (true);
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.Builder) (obj)).instance).additionalUsers_ = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.AdditionalUsers)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
                if (((Boolean)promotionsyncimpl.setWriteDebugInfo.get()).booleanValue())
                {
                    ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                    ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.Builder) (obj)).instance).writeDebugInfo_ = true;
                }
                return (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
            }

            .Lambda._cls4(com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder builder, String s)
            {
                arg$1 = PromotionSyncImpl.this;
                arg$2 = builder;
                arg$3 = s;
            }
        }

        builder = new .Lambda._cls4(builder, s);
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.State state = (com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.State)asynccloseable.state.getAndSet(com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.State.TRANSFORMED);
        class .Lambda._cls5
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;
            private final String arg$2;

            public final ListenableFuture apply(Object obj)
            {
                PromotionSyncImpl promotionsyncimpl = arg$1;
                String s1 = arg$2;
                obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest)obj;
                return promotionsyncimpl.growthApiClient.getPromos(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest) (obj)), s1);
            }

            .Lambda._cls5(String s)
            {
                arg$1 = PromotionSyncImpl.this;
                arg$2 = s;
            }
        }

        class .Lambda._cls6
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;
            private final String arg$2;

            public final ListenableFuture apply(Object obj)
            {
                PromotionSyncImpl promotionsyncimpl = arg$1;
                String s1 = arg$2;
                com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse)obj;
                if (((Boolean)promotionsyncimpl.setWriteDebugInfo.get()).booleanValue())
                {
                    obj = PromotionSyncImpl.logger;
                    obj = getpromosresponse.debugInfoKey_;
                }
                HashMap hashmap = new HashMap(getpromosresponse.promo_.size());
                Iterator iterator = getpromosresponse.promo_.iterator();
                while (iterator.hasNext()) 
                {
                    com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)iterator.next();
                    if (promotion.promoId_ == null)
                    {
                        obj = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                    } else
                    {
                        obj = promotion.promoId_;
                    }
                    hashmap.put(PromotionKeysHelper.of(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj))), promotion);
                }
                class .Lambda._cls14
                    implements Function
                {

                    private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse arg$1;

                    public final Object apply(Object obj1)
                    {
                        return PromotionSyncImpl.lambda$sync$6$PromotionSyncImpl$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NM4RRH5TJN4RRNEHK2US3IDTMMUS3IDTR6IP35E8NN0SJFEHNIUK3IDTMMUK3IDTR6IP35E8I4EPBKA1P6URBFED96ASRGDTN76P9R9HL62TJ15TM62RJ75TB6UQB47CKKOORFDKNMERRFCTM6ABR9CHIMST39EHSIUOJFE4NMESJFETQ6GBRGE9NMQRRGE9NNCQB4CLP2US3IDTQ6UBQGE9NMQRQGE9NNCQB4CLP28HR5EH874RRDDTPL4PBJE1NMSSR57C______0(arg$1);
                    }

                        .Lambda._cls14(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse)
                        {
                            arg$1 = getpromosresponse;
                        }
                }

                return AbstractTransformFuture.create(((MessageStore)promotionsyncimpl.promotionStoreProvider.forAccount(s1)).clearAndPutAll(hashmap), new .Lambda._cls14(getpromosresponse), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls6(String s)
            {
                arg$1 = PromotionSyncImpl.this;
                arg$2 = s;
            }
        }

        class .Lambda._cls7
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;
            private final String arg$2;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                Object obj2 = arg$2;
                com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse)obj;
                HashMap hashmap;
                HashMap hashmap1;
                Iterator iterator;
                if (getpromosresponse.clientMonitoring_ == null)
                {
                    obj = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring.DEFAULT_INSTANCE;
                } else
                {
                    obj = getpromosresponse.clientMonitoring_;
                }
                hashmap = new HashMap(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring) (obj)).eventToMonitor_.size());
                if (getpromosresponse.clientMonitoring_ == null)
                {
                    obj = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring.DEFAULT_INSTANCE;
                } else
                {
                    obj = getpromosresponse.clientMonitoring_;
                }
                hashmap1 = new HashMap(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring) (obj)).eventToMonitor_.size());
                if (getpromosresponse.clientMonitoring_ == null)
                {
                    obj = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring.DEFAULT_INSTANCE;
                } else
                {
                    obj = getpromosresponse.clientMonitoring_;
                }
                iterator = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring) (obj)).eventToMonitor_.iterator();
                do
                {
                    if (iterator.hasNext())
                    {
                        com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring.MonitoredEvent monitoredevent = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring.MonitoredEvent)iterator.next();
                        switch (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.ClientMonitoring.MonitoredEvent.EventCase.forNumber(monitoredevent.eventCase_).ordinal())
                        {
                        case 0: // '\0'
                            String s1;
                            if (monitoredevent.eventCase_ == 1)
                            {
                                obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)monitoredevent.event_;
                            } else
                            {
                                obj = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
                            }
                            s1 = PromotionKeysHelper.of(((com.google.identity.growth.proto.Promotion.ClearcutEvent) (obj)));
                            if (monitoredevent.eventCase_ == 1)
                            {
                                obj = (com.google.identity.growth.proto.Promotion.ClearcutEvent)monitoredevent.event_;
                            } else
                            {
                                obj = com.google.identity.growth.proto.Promotion.ClearcutEvent.DEFAULT_INSTANCE;
                            }
                            hashmap.put(s1, obj);
                            break;

                        case 1: // '\001'
                            String s2;
                            if (monitoredevent.eventCase_ == 2)
                            {
                                obj = (com.google.identity.growth.proto.Promotion.VisualElementEvent)monitoredevent.event_;
                            } else
                            {
                                obj = com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE;
                            }
                            s2 = PromotionKeysHelper.of(((com.google.identity.growth.proto.Promotion.VisualElementEvent) (obj)));
                            if (monitoredevent.eventCase_ == 2)
                            {
                                obj = (com.google.identity.growth.proto.Promotion.VisualElementEvent)monitoredevent.event_;
                            } else
                            {
                                obj = com.google.identity.growth.proto.Promotion.VisualElementEvent.DEFAULT_INSTANCE;
                            }
                            hashmap1.put(s2, obj);
                            break;
                        }
                    } else
                    {
                        obj = new ArrayList();
                        ((List) (obj)).add(((MessageStore)((PromotionSyncImpl) (obj1)).monitoredEventClearcutStoreProvider.forAccount(((String) (obj2)))).clearAndPutAll(hashmap));
                        ((List) (obj)).add(((MessageStore)((PromotionSyncImpl) (obj1)).monitoredEventVisualElementStoreProvider.forAccount(((String) (obj2)))).clearAndPutAll(hashmap1));
                        obj = new com.google.common.util.concurrent.Futures.FutureCombiner(false, ImmutableList.copyOf(((Iterable) (obj))));
                        class .Lambda._cls13
                            implements Callable
                        {

                            private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse arg$1;

                            public final Object call()
                            {
                                return PromotionSyncImpl.lambda$sync$8$PromotionSyncImpl(arg$1);
                            }

                        .Lambda._cls13(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse)
                        {
                            arg$1 = getpromosresponse;
                        }
                        }

                        obj2 = new .Lambda._cls13(getpromosresponse);
                        obj1 = ((PromotionSyncImpl) (obj1)).executor;
                        return new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj)).allMustSucceed, ((java.util.concurrent.Executor) (obj1)), ((Callable) (obj2)));
                    }
                } while (true);
            }

            .Lambda._cls7(String s)
            {
                arg$1 = PromotionSyncImpl.this;
                arg$2 = s;
            }
        }

        class .Lambda._cls8
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;

            public final ListenableFuture apply(Object obj)
            {
                PromotionSyncImpl promotionsyncimpl = arg$1;
                obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse)obj;
                HashMap hashmap = new HashMap(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse) (obj)).cappedPromo_.size());
                com.google.identity.boq.growth.promoprovider.proto.PromoProvider.CappedPromotion cappedpromotion;
                for (Iterator iterator = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse) (obj)).cappedPromo_.iterator(); iterator.hasNext(); hashmap.put(String.valueOf(cappedpromotion.impressionCappingId_), cappedpromotion))
                {
                    cappedpromotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.CappedPromotion)iterator.next();
                }

                class .Lambda._cls12
                    implements Function
                {

                    private final com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse arg$1;

                    public final Object apply(Object obj1)
                    {
                        return PromotionSyncImpl.lambda$sync$10$PromotionSyncImpl$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NM4RRH5TJN4RRNEHK2US3IDTMMUS3IDTR6IP35E8NN0SJFEHNIUK3IDTMMUK3IDTR6IP35E8I4EPBKA1P6URBFED96ASRGDTN76P9R9HL62TJ15TM62RJ75TB6UQB47CKKOORFDKNMERRFCTM6ABR9CHIMST39EHSIUOJFE4NMESJFETQ6GBRGE9NMQRRGE9NNCQB4CLP2US3IDTQ6UBQGE9NMQRQGE9NNCQB4CLP28HR5EH874RRDDTPL4PBJE1NMSSR57C______0(arg$1);
                    }

                        .Lambda._cls12(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse getpromosresponse)
                        {
                            arg$1 = getpromosresponse;
                        }
                }

                return AbstractTransformFuture.create(promotionsyncimpl.cappedPromotionStoreProvider.putAll(hashmap), new .Lambda._cls12(((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse) (obj))), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
            }

            .Lambda._cls8()
            {
                arg$1 = PromotionSyncImpl.this;
            }
        }

        class .Lambda._cls9
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;

            public final ListenableFuture apply(Object obj)
            {
                Object obj1 = arg$1;
                obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse)obj;
                ArrayList arraylist = new ArrayList();
                Iterator iterator = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse) (obj)).promo_.iterator();
                do
                {
                    if (!iterator.hasNext())
                    {
                        break;
                    }
                    com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)iterator.next();
                    Map map = ((PromotionSyncImpl) (obj1)).uiImageDownloadManager;
                    com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype;
                    if (promotion.ui_ == null)
                    {
                        obj = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                    } else
                    {
                        obj = promotion.ui_;
                    }
                    uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(((com.google.identity.growth.proto.Promotion.PromoUi) (obj)).uiType_);
                    obj = uitype;
                    if (uitype == null)
                    {
                        obj = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
                    }
                    obj = (Provider)map.get(obj);
                    if (obj != null)
                    {
                        ImageDownloadManager imagedownloadmanager = (ImageDownloadManager)((Provider) (obj)).get();
                        if (promotion.ui_ == null)
                        {
                            obj = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                        } else
                        {
                            obj = promotion.ui_;
                        }
                        obj = imagedownloadmanager.downloadImageIfNeeded(((com.google.identity.growth.proto.Promotion.PromoUi) (obj)));
                        if (((Optional) (obj)).isPresent())
                        {
                            arraylist.add((ListenableFuture)((Optional) (obj)).get());
                        }
                    }
                } while (true);
                obj = new com.google.common.util.concurrent.Futures.FutureCombiner(false, ImmutableList.copyOf(arraylist));
                class .Lambda._cls11
                    implements AsyncCallable
                {

                    private final List arg$1;

                    public final ListenableFuture call()
                    {
                        return PromotionSyncImpl.lambda$sync$12$PromotionSyncImpl(arg$1);
                    }

                        .Lambda._cls11(List list)
                        {
                            arg$1 = list;
                        }
                }

                .Lambda._cls11 _lcls11 = new .Lambda._cls11(arraylist);
                obj1 = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
                return new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj)).allMustSucceed, ((java.util.concurrent.Executor) (obj1)), _lcls11);
            }

            .Lambda._cls9()
            {
                arg$1 = PromotionSyncImpl.this;
            }
        }

        class .Lambda._cls10
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;
            private final String arg$2;

            public final ListenableFuture apply(Object obj)
            {
                obj = arg$1;
                String s1 = arg$2;
                return ((MessageStore)((PromotionSyncImpl) (obj)).presentedPromoStoreProvider.forAccount(s1)).clearAndPutAll(Collections.emptyMap());
            }

            .Lambda._cls10(String s)
            {
                arg$1 = PromotionSyncImpl.this;
                arg$2 = s;
            }
        }

        if (!state.equals(com.google.android.libraries.internal.growth.growthkit.internal.concurrent.AsyncCloseable.State.INITIAL))
        {
            throw new IllegalStateException(Strings.lenientFormat("Attempting to transform or close an AsyncCloseable that was already in state %s", new Object[] {
                state
            }));
        } else
        {
            return (new AsyncCloseable(AbstractTransformFuture.create(asynccloseable.future, builder, directexecutor), asynccloseable.closeableList)).transformAsync(new .Lambda._cls5(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).transformAsync(new .Lambda._cls6(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).transformAsync(new .Lambda._cls7(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).transformAsync(new .Lambda._cls8(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).transformAsync(new .Lambda._cls9(), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE).transformAsyncAndClose(new .Lambda._cls10(s), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        }
    }

    public final ListenableFuture syncAllAccounts()
    {
        Object obj1 = new ArrayList();
        class .Lambda._cls3
            implements Function
        {

            private final PromotionSyncImpl arg$1;

            public final Object apply(Object obj2)
            {
                PromotionSyncImpl promotionsyncimpl = arg$1;
                obj2 = (SharedPreferences)obj2;
                Locale locale = promotionsyncimpl.context.getResources().getConfiguration().locale;
                ((SharedPreferences) (obj2)).edit().putString("SYNC_LANGUAGE", locale.toLanguageTag()).putLong("LAST_SYNC_TIME", promotionsyncimpl.clock.currentTimeMillis()).apply();
                return null;
            }

            .Lambda._cls3()
            {
                arg$1 = PromotionSyncImpl.this;
            }
        }

        ((List) (obj1)).add(AbstractTransformFuture.create(sharedPrefsFuture, new .Lambda._cls3(), executor));
        if (((Boolean)syncGaiaAccounts.get()).booleanValue())
        {
            String s;
            for (Iterator iterator = accountManager.getAccountsNames().iterator(); iterator.hasNext(); ((List) (obj1)).add(sync(getRequestHeaderBuilder(), s)))
            {
                s = (String)iterator.next();
            }

        }
        class .Lambda._cls2
            implements AsyncFunction
        {

            private final PromotionSyncImpl arg$1;

            public final ListenableFuture apply(Object obj2)
            {
                PromotionSyncImpl promotionsyncimpl = arg$1;
                Object obj3 = (GcorePseudonymousIdToken)obj2;
                if (((GcorePseudonymousIdToken) (obj3)).getValue() == null)
                {
                    if (true)
                    {
                        return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
                    } else
                    {
                        return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
                    }
                }
                obj2 = promotionsyncimpl.getRequestHeaderBuilder();
                obj3 = ((GcorePseudonymousIdToken) (obj3)).getValue();
                ((com.google.protobuf.GeneratedMessageLite.Builder) (obj2)).copyOnWrite();
                com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader growthrequestheader = (com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader)((com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder) (obj2)).instance;
                if (obj3 == null)
                {
                    throw new NullPointerException();
                } else
                {
                    growthrequestheader.pseudonymousId_ = ((String) (obj3));
                    return promotionsyncimpl.sync(((com.google.identity.boq.growth.common.proto.RequestHeader.GrowthRequestHeader.Builder) (obj2)), null);
                }
            }

            .Lambda._cls2()
            {
                arg$1 = PromotionSyncImpl.this;
            }
        }

        class .Lambda._cls0
            implements AsyncCallable
        {

            private final List arg$1;

            public final ListenableFuture call()
            {
                return PromotionSyncImpl.lambda$syncAllAccounts$0$PromotionSyncImpl(arg$1);
            }

            .Lambda._cls0(List list)
            {
                arg$1 = list;
            }
        }

        Object obj;
        com.google.common.util.concurrent.MoreExecutors.DirectExecutor directexecutor;
        if (((Boolean)syncZwiebackAccounts.get()).booleanValue())
        {
            obj = AbstractTransformFuture.create(pseudonymousIdHelper.getPseudonymousIdToken(), new .Lambda._cls2(), executor);
        } else
        if (true)
        {
            obj = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
        } else
        {
            obj = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
        }
        ((List) (obj1)).add(obj);
        obj = new com.google.common.util.concurrent.Futures.FutureCombiner(false, ImmutableList.copyOf(((Iterable) (obj1))));
        obj1 = new .Lambda._cls0(((List) (obj1)));
        directexecutor = com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE;
        return new CombinedFuture(((com.google.common.util.concurrent.Futures.FutureCombiner) (obj)).futures, ((com.google.common.util.concurrent.Futures.FutureCombiner) (obj)).allMustSucceed, directexecutor, ((AsyncCallable) (obj1)));
    }

}
