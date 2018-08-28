// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl;

import android.util.Log;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogEventBuilder;
import com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.streamz.StreamzIncrements;
import com.google.android.libraries.streamz.CellFieldTuple;
import com.google.android.libraries.streamz.GcoreClearcutStreamzLogger;
import com.google.android.libraries.streamz.GenericMetric;
import com.google.android.libraries.streamz.StreamzMessageProducer;
import com.google.common.base.Joiner;
import com.google.common.util.concurrent.FutureCallback;
import java.util.RandomAccess;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.rpc.impl:
//            GrowthApiClientImpl

final class val.accountName
    implements FutureCallback
{

    private final GrowthApiClientImpl this$0;
    private final String val$accountName;

    public final void onFailure(Throwable throwable)
    {
        boolean flag = true;
        GrowthApiClientImpl.logger.w(throwable, "Failed to fetch promotions.", new Object[0]);
        Object obj = streamzIncrements;
        syncsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            appPackageName, "ERROR"
        }));
        throwable = ((StreamzIncrements) (obj)).streamzLogger;
        obj = new com.google.android.libraries.streamz.gger.GcoreMessageProducer(((StreamzIncrements) (obj)).metricFactory);
        if (((StreamzMessageProducer) (obj)).incrementRequest.batch_.size() != 0)
        {
            flag = false;
        }
        if (!flag)
        {
            ((GcoreClearcutStreamzLogger) (throwable)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj))).setLogSourceName(((GcoreClearcutStreamzLogger) (throwable)).logSourceName).logAsync();
        }
        StreamzIncrements.incrementCounts = 0;
    }

    public final void onSuccess(Object obj)
    {
        obj = (com.google.identity.boq.growth.promoprovider.proto.sResponse)obj;
        boolean flag;
        if (obj != null && Log.isLoggable(GrowthApiClientImpl.logger.tag, 3))
        {
            Object obj1 = new Joiner(String.valueOf('\n'));
            obj = ((com.google.identity.boq.growth.promoprovider.proto.sResponse) (obj)).promo_;
            class .Lambda._cls0
                implements Function
            {

                public static final Function $instance = new .Lambda._cls0();

                public final Object apply(Object obj2)
                {
                    com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion promotion = (com.google.identity.boq.growth.promoprovider.proto.PromoProvider.GetPromosResponse.Promotion)obj2;
                    Object obj3;
                    int i;
                    int j;
                    if (promotion.promoId_ == null)
                    {
                        obj2 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                    } else
                    {
                        obj2 = promotion.promoId_;
                    }
                    i = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj2)).impressionCappingId_;
                    if (promotion.promoId_ == null)
                    {
                        obj2 = com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification.DEFAULT_INSTANCE;
                    } else
                    {
                        obj2 = promotion.promoId_;
                    }
                    j = ((com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification) (obj2)).mendelId_.getInt(0);
                    if (promotion.ui_ == null)
                    {
                        obj2 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                    } else
                    {
                        obj2 = promotion.ui_;
                    }
                    obj3 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(((com.google.identity.growth.proto.Promotion.PromoUi) (obj2)).uiType_);
                    obj2 = obj3;
                    if (obj3 == null)
                    {
                        obj2 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
                    }
                    if (((com.google.identity.growth.proto.Promotion.PromoUi.UiType) (obj2)).equals(com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DO_NOT_DISPLAY))
                    {
                        obj2 = " [CONTROL GROUP]";
                    } else
                    {
                        obj2 = "";
                    }
                    if (promotion.ui_ == null)
                    {
                        obj3 = com.google.identity.growth.proto.Promotion.PromoUi.DEFAULT_INSTANCE;
                    } else
                    {
                        obj3 = promotion.ui_;
                    }
                    return String.format("Id: %s (%s)%s%s", new Object[] {
                        Integer.valueOf(i), Integer.valueOf(j), obj2, DebugUtil.getPromoTitle(((com.google.identity.growth.proto.Promotion.PromoUi) (obj3)))
                    });
                }


            private .Lambda._cls0()
            {
            }
            }

            Function function = .Lambda._cls0..instance;
            if (obj instanceof RandomAccess)
            {
                obj = new com.google.common.collect.omAccessList(((java.util.List) (obj)), function);
            } else
            {
                obj = new com.google.common.collect.entialList(((java.util.List) (obj)), function);
            }
            obj = ((Iterable) (obj)).iterator();
            ((Joiner) (obj1)).appendTo(new StringBuilder(), ((java.util.Iterator) (obj))).toString();
            if (val$accountName != null)
            {
                obj = String.format(" for account %s", new Object[] {
                    val$accountName
                });
            } else
            {
                obj = " for signed-out user";
            }
            obj = GrowthApiClientImpl.logger;
        }
        obj = GrowthApiClientImpl.logger;
        obj1 = streamzIncrements;
        syncsCounter.doRecord(Long.valueOf(1L), new CellFieldTuple(new Object[] {
            appPackageName, "OK"
        }));
        obj = ((StreamzIncrements) (obj1)).streamzLogger;
        obj1 = new com.google.android.libraries.streamz.gger.GcoreMessageProducer(((StreamzIncrements) (obj1)).metricFactory);
        if (((StreamzMessageProducer) (obj1)).incrementRequest.batch_.size() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            ((GcoreClearcutStreamzLogger) (obj)).gcoreClearcutLogger.newEvent(((com.google.android.libraries.gcoreclient.clearcut.GcoreClearcutMessageProducer) (obj1))).setLogSourceName(((GcoreClearcutStreamzLogger) (obj)).logSourceName).logAsync();
        }
        StreamzIncrements.incrementCounts = 0;
    }

    .Lambda._cls0()
    {
        this$0 = final_growthapiclientimpl;
        val$accountName = String.this;
        super();
    }
}
