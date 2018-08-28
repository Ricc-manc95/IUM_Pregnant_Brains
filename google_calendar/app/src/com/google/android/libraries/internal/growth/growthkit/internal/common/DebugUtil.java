// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.common;


public final class DebugUtil
{

    public static StringBuilder getPromoTitle(com.google.identity.growth.proto.Promotion.PromoUi promoui)
    {
        StringBuilder stringbuilder = new StringBuilder();
        com.google.identity.growth.proto.Promotion.PromoUi.UiTemplateCase.forNumber(promoui.uiTemplateCase_).ordinal();
        JVM INSTR tableswitch 0 4: default 52
    //                   0 69
    //                   1 52
    //                   2 104
    //                   3 139
    //                   4 174;
           goto _L1 _L2 _L1 _L3 _L4 _L5
_L1:
        if (stringbuilder.length() > 0)
        {
            stringbuilder.insert(0, ". Title: ");
        }
        return stringbuilder;
_L2:
        if (promoui.uiTemplateCase_ == 2)
        {
            promoui = (com.google.identity.growth.proto.Promotion.GeneralPromptUi)promoui.uiTemplate_;
        } else
        {
            promoui = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        }
        stringbuilder.append(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (promoui)).headlineText_);
        continue; /* Loop/switch isn't completed */
_L3:
        if (promoui.uiTemplateCase_ == 4)
        {
            promoui = (com.google.identity.growth.proto.Promotion.AndroidNotificationUi)promoui.uiTemplate_;
        } else
        {
            promoui = com.google.identity.growth.proto.Promotion.AndroidNotificationUi.DEFAULT_INSTANCE;
        }
        stringbuilder.append(((com.google.identity.growth.proto.Promotion.AndroidNotificationUi) (promoui)).title_);
        continue; /* Loop/switch isn't completed */
_L4:
        if (promoui.uiTemplateCase_ == 5)
        {
            promoui = (com.google.identity.growth.proto.Promotion.TooltipUi)promoui.uiTemplate_;
        } else
        {
            promoui = com.google.identity.growth.proto.Promotion.TooltipUi.DEFAULT_INSTANCE;
        }
        stringbuilder.append(((com.google.identity.growth.proto.Promotion.TooltipUi) (promoui)).headlineText_);
        continue; /* Loop/switch isn't completed */
_L5:
        if (promoui.uiTemplateCase_ == 6)
        {
            promoui = (com.google.identity.growth.proto.Promotion.PermissionPromptUi)promoui.uiTemplate_;
        } else
        {
            promoui = com.google.identity.growth.proto.Promotion.PermissionPromptUi.DEFAULT_INSTANCE;
        }
        if (((com.google.identity.growth.proto.Promotion.PermissionPromptUi) (promoui)).warmupPromptUi_ == null)
        {
            promoui = com.google.identity.growth.proto.Promotion.GeneralPromptUi.DEFAULT_INSTANCE;
        } else
        {
            promoui = ((com.google.identity.growth.proto.Promotion.PermissionPromptUi) (promoui)).warmupPromptUi_;
        }
        stringbuilder.append(((com.google.identity.growth.proto.Promotion.GeneralPromptUi) (promoui)).headlineText_);
        if (true) goto _L1; else goto _L6
_L6:
    }
}
