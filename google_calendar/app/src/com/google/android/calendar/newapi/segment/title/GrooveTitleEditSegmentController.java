// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.title;

import android.animation.Animator;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.google.android.calendar.api.color.EntityColor;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.newapi.model.TitleHolder;
import com.google.android.calendar.newapi.model.TitleModification;
import com.google.android.calendar.newapi.model.edit.EditScreenModel;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;

// Referenced classes of package com.google.android.calendar.newapi.segment.title:
//            TitleEditSegment

public class GrooveTitleEditSegmentController extends EditSegmentController
    implements TitleEditSegment.Listener
{

    public GrooveTitleEditSegmentController()
    {
    }

    private final void updateView(boolean flag)
    {
        boolean flag1 = true;
        ((TitleEditSegment)super.view).setTitle(((TitleHolder)(EditScreenModel)super.model).getTitle());
        Object obj = (TitleEditSegment)super.view;
        int i;
        if (((EditScreenModel)super.model).readOnly() || !((TitleModification)(EditScreenModel)super.model).canModifyTitle())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        obj = ((TitleEditSegment) (obj)).titleEditText;
        if (i != 0)
        {
            flag1 = false;
        }
        ((ShinobiEditText) (obj)).setEnabled(flag1);
        obj = (TitleEditSegment)super.view;
        i = ((EditScreenModel)super.model).getColor().getValue();
        if (flag && ViewCompat.isAttachedToWindow(((TitleEditSegment) (obj)).rippleView))
        {
            int j = ((TitleEditSegment) (obj)).getWidth() / 2;
            int k = ((TitleEditSegment) (obj)).getHeight() / 2;
            float f = (float)(Math.hypot(((TitleEditSegment) (obj)).getWidth(), ((TitleEditSegment) (obj)).getHeight()) / 2D);
            Animator animator = ViewAnimationUtils.createCircularReveal(((TitleEditSegment) (obj)).rippleView, j, k, 0.0F, f);
            animator.addListener(new TitleEditSegment._cls2(((TitleEditSegment) (obj)), i));
            animator.setStartDelay(200L);
            animator.start();
            return;
        } else
        {
            ((TitleEditSegment) (obj)).setBackgroundColor(i);
            return;
        }
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = new TitleEditSegment(layoutinflater.getContext(), null);
        layoutinflater.mListener = this;
        return layoutinflater;
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        updateView(EditSegmentController.animationsOn);
    }

    public final void onColorChanged()
    {
        updateView(EditSegmentController.animationsOn);
    }

    protected final void onInitialize()
    {
        updateView(false);
    }

    public final void onKeyboardDone()
    {
    }

    public final void onTitleChanged(String s)
    {
        ((TitleModification)(EditScreenModel)super.model).setTitle(s);
    }

    public final boolean onTitleTouched()
    {
        return false;
    }
}
