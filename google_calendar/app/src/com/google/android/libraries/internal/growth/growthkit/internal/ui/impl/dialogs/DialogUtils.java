// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.dialogs;


public final class DialogUtils
{

    public static boolean isAlertDialogUi(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
label0:
        {
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = uitype1;
            if (uitype1 == null)
            {
                uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
            }
            if (uitype != com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_MATERIAL_DIALOG)
            {
                com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype2 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
                Object obj = uitype2;
                if (uitype2 == null)
                {
                    obj = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
                }
                if (obj != com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG)
                {
                    break label0;
                }
                if (promoui.uiTemplateCase_ == 2)
                {
                    promoui = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
                } else
                {
                    promoui = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
                }
                obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Style.forNumber(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (promoui)).style_);
                promoui = ((com.google.identity.growth.proto.Promotion.PromoUi) (obj));
                if (obj == null)
                {
                    promoui = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Style.UNKNOWN_STYLE;
                }
                if (promoui != com.google.identity.growth.proto.Promotion.GeneralPromptUi.Style.MATERIAL_ALERT_DIALOG)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isBottomSheetUi(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
label0:
        {
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype1 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
            com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype = uitype1;
            if (uitype1 == null)
            {
                uitype = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
            }
            if (uitype != com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_RATING_PREFERRED_BOTTOMSHEET)
            {
                com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype2 = com.google.identity.growth.proto.Promotion.PromoUi.UiType.forNumber(promoui.uiType_);
                Object obj = uitype2;
                if (uitype2 == null)
                {
                    obj = com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_NONE;
                }
                if (obj != com.google.identity.growth.proto.Promotion.PromoUi.UiType.UITYPE_DIALOG)
                {
                    break label0;
                }
                if (promoui.uiTemplateCase_ == 2)
                {
                    promoui = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
                } else
                {
                    promoui = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
                }
                obj = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Style.forNumber(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (promoui)).style_);
                promoui = ((com.google.identity.growth.proto.Promotion.PromoUi) (obj));
                if (obj == null)
                {
                    promoui = com.google.identity.growth.proto.Promotion.GeneralPromptUi.Style.UNKNOWN_STYLE;
                }
                if (promoui != com.google.identity.growth.proto.Promotion.GeneralPromptUi.Style.BLOCKING_BOTTOMSHEET)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }
}
