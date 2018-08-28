// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

// Referenced classes of package android.support.transition:
//            ViewGroupOverlayImpl

final class ViewGroupOverlayApi18
    implements ViewGroupOverlayImpl
{

    private final ViewGroupOverlay mViewGroupOverlay;

    ViewGroupOverlayApi18(ViewGroup viewgroup)
    {
        mViewGroupOverlay = viewgroup.getOverlay();
    }

    public final void add(View view)
    {
        mViewGroupOverlay.add(view);
    }

    public final void remove(View view)
    {
        mViewGroupOverlay.remove(view);
    }
}
