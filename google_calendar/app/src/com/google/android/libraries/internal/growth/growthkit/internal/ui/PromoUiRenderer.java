// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui;

import android.support.v4.app.FragmentActivity;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;

public interface PromoUiRenderer
{

    public abstract String convertElementId(com.google.identity.growth.proto.Promotion.PromoUi promoui);

    public abstract int convertPromoType$51666RRD5TJMURR7DHIIUQB4CLN78QBKF4NMESJFETQ6GBRGE9NN8RPFA1P6URBFEHKMURH4A1P6URBFALKJMAACCDNMQBR7DTNMER355TGMSP3IDTKM8BRCD5H74OBID5IN6BR9DPQ6ASJEC5M2UPRIDTRN8Q1FCTP6UTRKD1LMIT1FDHKMCPB3F5HMOP9F8TP6UTRKD15MIT23C5M6OOJ1CDLN692GE9NMQRQKF5O6AEO_0(com.google.identity.growth.proto.Promotion.PromoUi promoui);

    public abstract boolean render(FragmentActivity fragmentactivity, com.google.identity.growth.proto.Promotion.PromoUi promoui, PromoContext promocontext);

    public abstract boolean supportsUiType(com.google.identity.growth.proto.Promotion.PromoUi.UiType uitype);
}
