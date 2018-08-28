// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view.overlay;

import android.view.View;
import java.lang.ref.WeakReference;

// Referenced classes of package com.google.android.calendar.common.view.overlay:
//            OverlayFragment

public static final class reference
    implements android.view.er
{

    public View contentView;
    private final WeakReference reference;

    public final void onGlobalLayout()
    {
        OverlayFragment overlayfragment = (OverlayFragment)reference.get();
        if (overlayfragment != null)
        {
            overlayfragment.onGlobalLayout(contentView, this);
        }
    }

    public (OverlayFragment overlayfragment, View view)
    {
        contentView = view;
        reference = new WeakReference(overlayfragment);
    }
}
