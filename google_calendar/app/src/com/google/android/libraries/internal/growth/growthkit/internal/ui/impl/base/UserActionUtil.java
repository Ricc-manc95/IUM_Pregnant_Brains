// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import android.content.Context;
import android.content.Intent;
import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PerAccountProvider;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.concurrent.MoreFutures;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.MessageStore;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.PromotionKeysHelper;
import com.google.common.base.Optional;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class UserActionUtil
{

    public static final Logger logger = new Logger();
    public final ClearcutLogger clearcutLogger;
    public final Context context;
    public final Optional intentBuilder;
    private final PerAccountProvider presentedPromosStore;

    public UserActionUtil(Context context1, PerAccountProvider peraccountprovider, ClearcutLogger clearcutlogger, Optional optional)
    {
        context = context1;
        presentedPromosStore = peraccountprovider;
        clearcutLogger = clearcutlogger;
        intentBuilder = optional;
    }

    public static int getActionTypeFromAction$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH48TIMSPBIC5M50SJFDLO78LB94H0M6T39DTN3MAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN6921CDQ6IRREAHSN0P9R0(com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action action)
    {
        com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType actiontype = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(action.actionType_);
        action = actiontype;
        if (actiontype == null)
        {
            action = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_UNKNOWN;
        }
        switch (action.ordinal())
        {
        default:
            return android.support.v4.content.ModernAsyncTask.Status.ACTION_UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP485HN8QBFDPA7IS357C______0;

        case 3: // '\003'
            return android.support.v4.content.ModernAsyncTask.Status.ACTION_DISMISS$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP485HN8QBFDPA7IS357C______0;

        case 4: // '\004'
            return android.support.v4.content.ModernAsyncTask.Status.ACTION_ACKNOWLEDGE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP485HN8QBFDPA7IS357C______0;

        case 2: // '\002'
            return android.support.v4.content.ModernAsyncTask.Status.ACTION_NEGATIVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP485HN8QBFDPA7IS357C______0;

        case 1: // '\001'
            return android.support.v4.content.ModernAsyncTask.Status.ACTION_POSITIVE$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP485HN8QBFDPA7IS357C______0;

        case 0: // '\0'
            return android.support.v4.content.ModernAsyncTask.Status.ACTION_UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP485HN8QBFDPA7IS357C______0;
        }
    }

    public static com.google.identity.growth.proto.CampaignManagement.UserAction getUserActionFromAction(com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action action)
    {
        com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType actiontype = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.forNumber(action.actionType_);
        action = actiontype;
        if (actiontype == null)
        {
            action = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Action.ActionType.ACTION_UNKNOWN;
        }
        switch (action.ordinal())
        {
        default:
            return com.google.identity.growth.proto.CampaignManagement.UserAction.UNKNOWN_ACTION;

        case 3: // '\003'
            return com.google.identity.growth.proto.CampaignManagement.UserAction.DISMISSED;

        case 2: // '\002'
            return com.google.identity.growth.proto.CampaignManagement.UserAction.NEGATIVE_RESPONSE;

        case 1: // '\001'
            return com.google.identity.growth.proto.CampaignManagement.UserAction.POSITIVE_RESPONSE;
        }
    }

    public static void putExtraData(String s, Intent intent, List list)
    {
        Iterator iterator = list.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            com.google.identity.growth.proto.Promotion.KeyValuePair keyvaluepair = (com.google.identity.growth.proto.Promotion.KeyValuePair)iterator.next();
            if (keyvaluepair.valueTypesCase_ == 2)
            {
                Object obj = keyvaluepair.key_;
                if (keyvaluepair.valueTypesCase_ == 2)
                {
                    list = (String)keyvaluepair.valueTypes_;
                } else
                {
                    list = "";
                }
                intent.putExtra(((String) (obj)), list);
            } else
            if (keyvaluepair.valueTypesCase_ == 3)
            {
                if (keyvaluepair.valueTypesCase_ == 3)
                {
                    obj = com.google.identity.growth.proto.Promotion.KeyValuePair.ClientValue.forNumber(((Integer)keyvaluepair.valueTypes_).intValue());
                    list = ((List) (obj));
                    if (obj == null)
                    {
                        list = com.google.identity.growth.proto.Promotion.KeyValuePair.ClientValue.CLIENT_VALUE_UNKNOWN;
                    }
                } else
                {
                    list = com.google.identity.growth.proto.Promotion.KeyValuePair.ClientValue.CLIENT_VALUE_UNKNOWN;
                }
                switch (list.ordinal())
                {
                case 1: // '\001'
                    if (s != null)
                    {
                        intent.putExtra(keyvaluepair.key_, s);
                    }
                    break;
                }
            }
        } while (true);
    }

    public final void persistUserChoice(PromoContext promocontext, com.google.identity.growth.proto.CampaignManagement.UserAction useraction)
    {
        Object obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
        Object obj1 = promocontext.promoId();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo presentedpromo = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        presentedpromo.promoId_ = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj1));
        obj1 = promocontext.serializedAdditionalData();
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        presentedpromo = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo.Builder) (obj)).instance;
        if (obj1 == null)
        {
            throw new NullPointerException();
        }
        presentedpromo.serializedAdditionalData_ = ((com.google.protobuf.ByteString) (obj1));
        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
        obj1 = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo.Builder) (obj)).instance;
        if (useraction == null)
        {
            throw new NullPointerException();
        }
        if (useraction == com.google.identity.growth.proto.CampaignManagement.UserAction.UNRECOGNIZED)
        {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        } else
        {
            obj1.actionType_ = useraction.value;
            obj1 = (com.google.protobuf.Timestamp.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)Timestamp.DEFAULT_INSTANCE.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
            long l = TimeUnit.MILLISECONDS.toSeconds(promocontext.triggeringEventTimeMs());
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).copyOnWrite();
            ((Timestamp)((com.google.protobuf.Timestamp.Builder) (obj1)).instance).seconds_ = l;
            ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
            ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo)((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo.Builder) (obj)).instance).impressionTime_ = (Timestamp)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj1)).build();
            obj = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosRequest.PresentedPromo)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
            class .Lambda._cls0
                implements Receiver
            {

                private final UserActionUtil arg$1;
                private final com.google.identity.growth.proto.CampaignManagement.UserAction arg$2;
                private final PromoContext arg$3;

                public final void accept(Object obj2)
                {
                    obj2 = arg$1;
                    com.google.identity.growth.proto.CampaignManagement.UserAction useraction1 = arg$2;
                    PromoContext promocontext1 = arg$3;
                    switch (useraction1.ordinal())
                    {
                    default:
                        ((UserActionUtil) (obj2)).clearcutLogger.logPromoUserAction(promocontext1, com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction.ACTION_UNKNOWN);
                        return;

                    case 2: // '\002'
                        ((UserActionUtil) (obj2)).clearcutLogger.logPromoUserAction(promocontext1, com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction.ACTION_POSITIVE);
                        return;

                    case 3: // '\003'
                        ((UserActionUtil) (obj2)).clearcutLogger.logPromoUserAction(promocontext1, com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction.ACTION_NEGATIVE);
                        return;

                    case 1: // '\001'
                        ((UserActionUtil) (obj2)).clearcutLogger.logPromoUserDismissed(promocontext1);
                        return;
                    }
                }

            .Lambda._cls0(com.google.identity.growth.proto.CampaignManagement.UserAction useraction, PromoContext promocontext)
            {
                arg$1 = UserActionUtil.this;
                arg$2 = useraction;
                arg$3 = promocontext;
            }
            }

            class .Lambda._cls1
                implements Receiver
            {

                public static final Receiver $instance = new .Lambda._cls1();

                public final void accept(Object obj2)
                {
                    obj2 = (Throwable)obj2;
                    UserActionUtil.logger.w(((Throwable) (obj2)), "Failed to persist dialog button click.", new Object[0]);
                }


            private .Lambda._cls1()
            {
            }
            }

            MoreFutures.addCallback(((MessageStore)presentedPromosStore.forAccount(promocontext.accountName())).put(PromotionKeysHelper.of(promocontext.promoId()), ((com.google.protobuf.MessageLite) (obj))), new .Lambda._cls0(useraction, promocontext), .Lambda._cls1..instance);
            return;
        }
    }

}
