// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.view.View;
import android.widget.ImageView;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleViewSegment

final class this._cls0
    implements com.google.android.calendar.utils.plier.CustomInsetHandler
{

    private final TitleViewSegment this$0;

    public final void onInsetsChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i)
    {
        windowTopInset = i;
        updateHeaderImageSize((ImageView)view);
    }

    etHandler()
    {
        this$0 = TitleViewSegment.this;
        super();
    }
}
