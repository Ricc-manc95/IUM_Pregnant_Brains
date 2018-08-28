// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.chip;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

// Referenced classes of package android.support.design.chip:
//            Chip, ChipDrawable

final class nit> extends ViewOutlineProvider
{

    private final Chip this$0;

    public final void getOutline(View view, Outline outline)
    {
        if (chipDrawable != null)
        {
            chipDrawable.getOutline(outline);
            return;
        } else
        {
            outline.setAlpha(0.0F);
            return;
        }
    }

    wable()
    {
        this$0 = Chip.this;
        super();
    }
}
