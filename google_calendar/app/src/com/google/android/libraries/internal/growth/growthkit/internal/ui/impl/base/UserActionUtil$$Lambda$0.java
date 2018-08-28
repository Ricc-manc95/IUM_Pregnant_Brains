// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base;

import com.google.android.libraries.internal.growth.growthkit.internal.clearcut.ClearcutLogger;
import com.google.android.libraries.internal.growth.growthkit.internal.common.PromoContext;
import com.google.common.base.Receiver;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base:
//            UserActionUtil

final class arg._cls3
    implements Receiver
{

    private final UserActionUtil arg$1;
    private final com.google.identity.growth.proto.tion arg$2;
    private final PromoContext arg$3;

    public final void accept(Object obj)
    {
        obj = arg$1;
        com.google.identity.growth.proto.tion tion = arg$2;
        PromoContext promocontext = arg$3;
        switch (tion.ordinal())
        {
        default:
            ((UserActionUtil) (obj)).clearcutLogger.logPromoUserAction(promocontext, com.google.identity.growth.logging.proto.ion.ACTION_UNKNOWN);
            return;

        case 2: // '\002'
            ((UserActionUtil) (obj)).clearcutLogger.logPromoUserAction(promocontext, com.google.identity.growth.logging.proto.ion.ACTION_POSITIVE);
            return;

        case 3: // '\003'
            ((UserActionUtil) (obj)).clearcutLogger.logPromoUserAction(promocontext, com.google.identity.growth.logging.proto.ion.ACTION_NEGATIVE);
            return;

        case 1: // '\001'
            ((UserActionUtil) (obj)).clearcutLogger.logPromoUserDismissed(promocontext);
            return;
        }
    }

    (UserActionUtil useractionutil, com.google.identity.growth.proto.tion tion, PromoContext promocontext)
    {
        arg$1 = useractionutil;
        arg$2 = tion;
        arg$3 = promocontext;
    }
}
