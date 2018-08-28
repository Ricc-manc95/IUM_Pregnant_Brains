// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.clearcut;

import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import java.util.List;

public interface ClearcutLogger
{

    public abstract void logPromoConditionsEvaluated(PromoContext promocontext, List list);

    public abstract void logPromoNotShownClientBlocked$51666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMIRJKCLP6SOBC5THMURBDDTN2UK3IDTMMUGRFDPQ6AU3K7D666RRD5TJMURR7DHIIUOBECHP6UQB45TM6IOJIC5P6IPBJ5TKMST35E9N62R1FCTP6UTRKD0NMESJFETQ6GQR9EGNMOQB6CLHNIORCCKNKESJFETQ6GIR9EH1M2R3CC9GM6QRJ4H26ARJPA9IM2SRFDOTIILG_0(PromoContext promocontext, int i);

    public abstract void logPromoNotShownControlGroup(PromoContext promocontext);

    public abstract void logPromoNotShownDeviceCapped(PromoContext promocontext);

    public abstract void logPromoShown(PromoContext promocontext);

    public abstract void logPromoTargetingEvaluated(PromoContext promocontext, boolean flag);

    public abstract void logPromoTriggered(PromoContext promocontext, int i);

    public abstract void logPromoUserAction(PromoContext promocontext, com.google.identity.growth.logging.proto.Client.PromoEvent.UserAction useraction);

    public abstract void logPromoUserDismissed(PromoContext promocontext);
}
