// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.Renderer;
import com.google.android.libraries.material.featurehighlight.ViewFinder;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightViewFinderFactory, FeatureHighlightFragment

public final class FeatureHighlightFragmentRenderer
    implements Renderer
{

    private final FeatureHighlightViewFinderFactory featureHighlightViewFinderFactory;

    public FeatureHighlightFragmentRenderer(FeatureHighlightViewFinderFactory featurehighlightviewfinderfactory)
    {
        featureHighlightViewFinderFactory = featurehighlightviewfinderfactory;
    }

    public final String convertElementId(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
label0:
        {
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = uitype1;
            if (uitype1 == null)
            {
                uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
            }
            if (uitype == com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_TAP_TARGET)
            {
                boolean flag;
                if (promoui.uiTemplateCase_ == 3)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    break label0;
                }
            }
            return null;
        }
        com.google.identity.growth.proto.Promotion.TapTargetUi taptargetui;
        if (promoui.uiTemplateCase_ == 3)
        {
            taptargetui = (com.google.identity.growth.proto.Promotion.TapTargetUi)promoui.uiTemplate_;
        } else
        {
            taptargetui = com.google.identity.growth.proto.Promotion.TapTargetUi.DEFAULT_INSTANCE;
        }
        if (taptargetui.targetCase_ == 1)
        {
            if (promoui.uiTemplateCase_ == 3)
            {
                promoui = (com.google.identity.growth.proto.Promotion.TapTargetUi)promoui.uiTemplate_;
            } else
            {
                promoui = com.google.identity.growth.proto.Promotion.TapTargetUi.DEFAULT_INSTANCE;
            }
            if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (promoui)).targetCase_ == 1)
            {
                return (String)((com.google.identity.growth.proto.Promotion.TapTargetUi) (promoui)).target_;
            }
        } else
        {
            if (promoui.uiTemplateCase_ == 3)
            {
                promoui = (com.google.identity.growth.proto.Promotion.TapTargetUi)promoui.uiTemplate_;
            } else
            {
                promoui = com.google.identity.growth.proto.Promotion.TapTargetUi.DEFAULT_INSTANCE;
            }
            if (((com.google.identity.growth.proto.Promotion.TapTargetUi) (promoui)).targetCase_ == 10)
            {
                return (String)((com.google.identity.growth.proto.Promotion.TapTargetUi) (promoui)).target_;
            }
        }
        return "";
    }

    public final int convertPromoType$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4A1P6URBFALKJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN692GE9NMQRQKF5O6AEO_0(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
        promoui = uitype;
        if (uitype == null)
        {
            promoui = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
        }
        if (promoui == com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_TAP_TARGET)
        {
            return android.support.v4.content.ModernAsyncTask.Status.FEATURE_HIGHLIGHT$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        } else
        {
            return android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        }
    }

    public final boolean render(FragmentActivity fragmentactivity, com.google.identity.growth.proto.Promotion.PromoUi promoui, PromoContext promocontext)
    {
        FeatureHighlightViewFinderFactory featurehighlightviewfinderfactory = featureHighlightViewFinderFactory;
        com.google.identity.growth.proto.Promotion.TapTargetUi taptargetui;
        if (promoui.uiTemplateCase_ == 3)
        {
            taptargetui = (com.google.identity.growth.proto.Promotion.TapTargetUi)promoui.uiTemplate_;
        } else
        {
            taptargetui = com.google.identity.growth.proto.Promotion.TapTargetUi.DEFAULT_INSTANCE;
        }
        if (featurehighlightviewfinderfactory.create(taptargetui).find(fragmentactivity, null) == null)
        {
            return false;
        } else
        {
            FeatureHighlightFragment featurehighlightfragment = new FeatureHighlightFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("promoui", new com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse(null, promoui));
            bundle.putParcelable("promoContext", promocontext);
            featurehighlightfragment.setArguments(bundle);
            fragmentactivity.mFragments.mHost.mFragmentManager.beginTransaction().add(featurehighlightfragment, "com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.FeatureHighlightFragment").commitAllowingStateLoss();
            return true;
        }
    }
}
