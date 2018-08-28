// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.Renderer;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs:
//            DialogUtils, PromoUiDialogFragment

public final class DialogRenderer
    implements Renderer
{

    public DialogRenderer()
    {
    }

    public final String convertElementId(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        return null;
    }

    public final int convertPromoType$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4A1P6URBFALKJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN692GE9NMQRQKF5O6AEO_0(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        boolean flag;
        if (promoui.uiTemplateCase_ == 2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        }
        if (DialogUtils.isAlertDialogUi(promoui))
        {
            return android.support.v4.content.ModernAsyncTask.Status.DIALOG$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        }
        if (DialogUtils.isBottomSheetUi(promoui))
        {
            return android.support.v4.content.ModernAsyncTask.Status.BOTTOM_SHEET$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        } else
        {
            return android.support.v4.content.ModernAsyncTask.Status.UNKNOWN$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FDHKM4SJ1E9KMASPFD5N78PBIDPGMOBR7E9NNET385TJN4RRNEHK6MQBK5TM6IPJ5CDSM6R355T3N4RRNEHK4MQBK8DGMOR32C5HMMSP4A1P6URBFAHSN0P9R0;
        }
    }

    public final boolean render(FragmentActivity fragmentactivity, com.google.identity.growth.proto.Promotion.PromoUi promoui, PromoContext promocontext)
    {
        PromoUiDialogFragment promouidialogfragment = new PromoUiDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("promoui", new com.google.protobuf.contrib.android.ProtoParsers.InternalDontUse(null, promoui));
        bundle.putParcelable("promoContext", promocontext);
        promouidialogfragment.setArguments(bundle);
        promouidialogfragment.show(fragmentactivity.mFragments.mHost.mFragmentManager, "com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.PromoUiDialogFragment");
        return true;
    }
}
