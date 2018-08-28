// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.google.android.calendar.utils.animation.QuantumInterpolators;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            MoreOptionsSheet

public final class MoreOptionsSheetController
    implements MoreOptionsSheet.OnMoreOptionsItemSelectedListener
{

    public Callback listener;
    public MoreOptionsSheet moreOptionsSheet;

    public MoreOptionsSheetController()
    {
    }

    public final void onMoreOptionsItemSelected(int i)
    {
        MoreOptionsSheet moreoptionssheet = moreOptionsSheet;
        Animation animation = AnimationUtils.loadAnimation(moreoptionssheet.getContext(), 0x7f06000e);
        animation.setInterpolator(QuantumInterpolators.FAST_OUT_GENTLE_IN);
        animation.setAnimationListener(new MoreOptionsSheet._cls2(moreoptionssheet));
        Animation animation1 = AnimationUtils.loadAnimation(moreoptionssheet.getContext(), 0x7f06000d);
        moreoptionssheet.panelView.startAnimation(animation);
        moreoptionssheet.scrimView.startAnimation(animation1);
        switch (i)
        {
        default:
            return;

        case 1: // '\001'
            listener.onProposeNewTimeClicked();
            return;

        case 2: // '\002'
            listener.onAddNoteClicked();
            break;
        }
    }

    private class Callback
    {

        public abstract void onAddNoteClicked();

        public abstract void onProposeNewTimeClicked();
    }

}
