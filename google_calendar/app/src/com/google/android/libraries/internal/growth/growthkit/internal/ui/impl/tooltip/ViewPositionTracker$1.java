// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;


// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip:
//            ViewPositionTracker

final class this._cls0
    implements android.view.DrawListener
{

    private final ViewPositionTracker this$0;

    public final boolean onPreDraw()
    {
        calculateViewPosition();
        return true;
    }

    ()
    {
        this$0 = ViewPositionTracker.this;
        super();
    }
}
