// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.view.horizontalcarousel;

import android.view.View;

// Referenced classes of package com.google.android.libraries.view.horizontalcarousel:
//            HorizontalCarousel

public static final class  extends android.support.v7.widget.izontalCarousel.ViewHolder
{

    public final String toString()
    {
        return String.format("%s %s", new Object[] {
            super.ng(), itemView.getTag()
        });
    }

    public (View view)
    {
        super(view);
        view.setTag(0x7f10000d, this);
    }
}
