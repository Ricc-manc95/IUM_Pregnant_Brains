// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.media.ImageCache;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui:
//            PromoDialog

public interface DialogBuilder
{

    public abstract PromoDialog build(Context context, ImageCache imagecache, com.google.identity.growth.proto.Promotion.PromoUi promoui);
}
