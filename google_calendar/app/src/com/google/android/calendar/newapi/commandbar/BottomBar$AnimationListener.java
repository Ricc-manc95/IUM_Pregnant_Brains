// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.animation.ValueAnimator;
import android.view.View;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBar

public final class this._cls0
    implements android.animation.Listener
{

    private final BottomBar this$0;

    public final void onAnimationUpdate(ValueAnimator valueanimator)
    {
        BottomBar bottombar = BottomBar.this;
        int i = ((Integer)valueanimator.getAnimatedValue()).intValue();
        int j = bottombar.getMeasuredHeight();
        ((View)bottombar.getParent()).setTranslationY(j - i);
        bottombar.onHeightChangedListener.HeightChanged();
    }

    protected ()
    {
        this$0 = BottomBar.this;
        super();
    }
}
