// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.view.animation.Animation;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            MoreOptionsSheet

final class this._cls0
    implements android.view.animation.Listener
{

    private final MoreOptionsSheet this$0;

    public final void onAnimationEnd(Animation animation)
    {
        setVisibility(8);
    }

    public final void onAnimationRepeat(Animation animation)
    {
    }

    public final void onAnimationStart(Animation animation)
    {
    }

    ()
    {
        this$0 = MoreOptionsSheet.this;
        super();
    }
}
