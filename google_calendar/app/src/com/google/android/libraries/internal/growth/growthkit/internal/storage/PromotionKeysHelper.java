// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage;

import android.text.TextUtils;
import java.util.TreeSet;

public final class PromotionKeysHelper
{

    public static String of(com.google.identity.boq.growth.promoprovider.proto.PromoProvider.PromoIdentification promoidentification)
    {
        boolean flag1 = true;
        boolean flag;
        if (promoidentification != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (promoidentification.mendelId_.size() > 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return TextUtils.join(",", new TreeSet(promoidentification.mendelId_));
        }
    }

    public static String of(com.google.identity.growth.proto.Promotion.ClearcutEvent clearcutevent)
    {
        boolean flag1 = true;
        boolean flag;
        if (clearcutevent != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if ((clearcutevent.bitField0_ & 1) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if ((clearcutevent.bitField0_ & 2) == 2)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            int i = clearcutevent.logSource_;
            int j = clearcutevent.eventCode_;
            return (new StringBuilder(23)).append(i).append(",").append(j).toString();
        }
    }

    public static String of(com.google.identity.growth.proto.Promotion.VisualElementEvent visualelementevent)
    {
        boolean flag1 = true;
        boolean flag;
        if (visualelementevent != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (visualelementevent.nodeIdPath_.size() != 0)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            return TextUtils.join(",", visualelementevent.nodeIdPath_);
        }
    }
}
