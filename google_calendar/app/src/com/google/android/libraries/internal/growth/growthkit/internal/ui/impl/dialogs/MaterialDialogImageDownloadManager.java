// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.ImageDownloadManager;
import com.google.common.base.Absent;
import com.google.common.base.Optional;
import com.google.common.base.Present;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs:
//            DialogUtils

public final class MaterialDialogImageDownloadManager
    implements ImageDownloadManager
{

    private final Context context;
    private final ImageCache imageCache;

    public MaterialDialogImageDownloadManager(Context context1, ImageCache imagecache)
    {
        imageCache = imagecache;
        context = context1;
    }

    public final Optional downloadImageIfNeeded(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
label0:
        {
            if (DialogUtils.isBottomSheetUi(promoui) || DialogUtils.isAlertDialogUi(promoui))
            {
                boolean flag;
                if (promoui.uiTemplateCase_ == 2)
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
            return Absent.INSTANCE;
        }
        Object obj;
        if (promoui.uiTemplateCase_ == 2)
        {
            obj = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
        } else
        {
            obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        }
        obj = ((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (obj)).imageUrl_;
        if (TextUtils.isEmpty(((CharSequence) (obj))))
        {
            return Absent.INSTANCE;
        }
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowmanager.getDefaultDisplay().getMetrics(displaymetrics);
        int i;
        int j;
        if (DialogUtils.isAlertDialogUi(promoui))
        {
            j = displaymetrics.widthPixels;
            i = -1;
        } else
        if (DialogUtils.isBottomSheetUi(promoui))
        {
            i = (int)((double)((float)48 * displaymetrics.density) + 0.5D);
            j = i;
        } else
        {
            return Absent.INSTANCE;
        }
        promoui = imageCache.downloadImage(((String) (obj)), j, i);
        if (promoui == null)
        {
            throw new NullPointerException();
        } else
        {
            return new Present(promoui);
        }
    }
}
