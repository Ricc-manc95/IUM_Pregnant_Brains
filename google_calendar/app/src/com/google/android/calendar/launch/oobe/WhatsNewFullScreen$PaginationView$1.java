// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.launch.oobe;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.ViewTreeObserver;

// Referenced classes of package com.google.android.calendar.launch.oobe:
//            WhatsNewFullScreen

final class this._cls1
    implements android.view.ullScreen.PaginationView._cls1
{

    private final tViewTreeObserver this$1;

    public final boolean onPreDraw()
    {
        this._cls1 _lcls1 = this._cls1.this;
        int i = _lcls1.tWidth();
        if (i == 0)
        {
            i = 0;
        } else
        {
            int j = _lcls1.tHeight() / 2;
            _lcls1.ts = new ShapeDrawable[4];
            int k = i / 2;
            int l = _lcls1.tMargin / 2;
            int i1 = _lcls1.tRadius;
            int j1 = _lcls1.tRadius;
            int k1 = _lcls1.tMargin;
            for (i = 0; i < 4; i++)
            {
                ShapeDrawable shapedrawable = new ShapeDrawable(new OvalShape());
                int l1 = (_lcls1.tRadius * 2 + _lcls1.tMargin) * i + (k - l - i1 - (j1 * 2 + k1) * 1);
                shapedrawable.setBounds(l1 - _lcls1.tRadius, j - _lcls1.tRadius, l1 + _lcls1.tRadius, _lcls1.tRadius + j);
                _lcls1.ts[i] = shapedrawable;
            }

            i = 1;
        }
        if (i == 0)
        {
            questLayout();
            return false;
        } else
        {
            tViewTreeObserver().removeOnPreDrawListener(this);
            return true;
        }
    }

    Q(WhatsNewFullScreen whatsnewfullscreen)
    {
        this$1 = this._cls1.this;
        super();
    }
}
