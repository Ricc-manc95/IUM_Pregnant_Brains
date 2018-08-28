// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import android.support.v4.app.FragmentActivity;
import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.PromoUiRenderer;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.Renderer;
import java.util.Map;
import javax.inject.Provider;

public final class PromoUiRendererImpl
    implements PromoUiRenderer
{

    private static final Logger logger = new Logger();
    private final Map rendererMap;

    public PromoUiRendererImpl(Map map)
    {
        rendererMap = map;
    }

    public final String convertElementId(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        Map map = rendererMap;
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype2 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = uitype2;
        if (uitype2 == null)
        {
            uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
        }
        if (map.containsKey(uitype))
        {
            Map map1 = rendererMap;
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype3 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype1 = uitype3;
            if (uitype3 == null)
            {
                uitype1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
            }
            return ((Renderer)((Provider)map1.get(uitype1)).get()).convertElementId(promoui);
        } else
        {
            return null;
        }
    }

    public final int convertPromoType$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4A1P6URBFALKJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN692GE9NMQRQKF5O6AEO_0(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        Map map = rendererMap;
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype2 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = uitype2;
        if (uitype2 == null)
        {
            uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
        }
        if (map.containsKey(uitype))
        {
            Map map1 = rendererMap;
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype3 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype1 = uitype3;
            if (uitype3 == null)
            {
                uitype1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
            }
            return ((Renderer)((Provider)map1.get(uitype1)).get()).convertPromoType$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4A1P6URBFALKJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN692GE9NMQRQKF5O6AEO_0(promoui);
        } else
        {
            return android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        }
    }

    public final boolean render(FragmentActivity fragmentactivity, com.google.identity.growth.proto.Promotion.PromoUi promoui, PromoContext promocontext)
    {
        Map map = rendererMap;
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
        Object obj = uitype;
        if (uitype == null)
        {
            obj = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
        }
        obj = (Provider)map.get(obj);
        if (obj != null)
        {
            return ((Renderer)((Provider) (obj)).get()).render(fragmentactivity, promoui, promocontext);
        } else
        {
            fragmentactivity = logger;
            return false;
        }
    }

    public final boolean supportsUiType(com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype)
    {
        return rendererMap.containsKey(uitype);
    }

}
