// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.commandbar;

import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.calendar.utils.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.commandbar:
//            BottomBar

public class CommandBar extends BottomBar
{

    public Button followUpActionView;
    public Button leftActionView;

    public CommandBar(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    final void animate(boolean flag)
    {
        boolean flag3 = true;
        boolean flag2 = false;
        if (!animationsEnabled)
        {
            Object obj;
            int ai[];
            boolean flag1;
            int j;
            int l;
            if (!flag)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            obj = container;
            ai = primaryActionIds;
            l = ai.length;
            j = 0;
            while (j < l) 
            {
                View view = ((ViewGroup) (obj)).findViewById(ai[j]);
                if (view != null)
                {
                    int k;
                    if (flag1)
                    {
                        k = 0;
                    } else
                    {
                        k = 8;
                    }
                    view.setVisibility(k);
                }
                j++;
            }
            obj = super.descriptionView;
            int i;
            if (!flag)
            {
                i = ((flag3) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (obj != null)
            {
                if (i != 0)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                ((View) (obj)).setVisibility(i);
            }
            obj = leftActionView;
            if (obj != null)
            {
                if (flag)
                {
                    i = 0;
                } else
                {
                    i = 8;
                }
                ((View) (obj)).setVisibility(i);
            }
            obj = followUpActionView;
            if (obj != null)
            {
                if (flag)
                {
                    i = ((flag2) ? 1 : 0);
                } else
                {
                    i = 8;
                }
                ((View) (obj)).setVisibility(i);
            }
            return;
        }
        Object obj2 = new ArrayList();
        Object obj1 = new ArrayList();
        AnimatorSet animatorset;
        if (flag)
        {
            ((List) (obj2)).addAll(AnimationUtils.setFadeAnimations(container, false, primaryActionIds));
            ((List) (obj2)).addAll(AnimationUtils.setFadeAnimations(false, new View[] {
                descriptionView
            }));
            ((List) (obj1)).addAll(AnimationUtils.setFadeAnimations(true, new View[] {
                leftActionView
            }));
            ((List) (obj1)).addAll(AnimationUtils.setFadeAnimations(true, new View[] {
                followUpActionView
            }));
        } else
        {
            ((List) (obj2)).addAll(AnimationUtils.setFadeAnimations(false, new View[] {
                leftActionView
            }));
            ((List) (obj2)).addAll(AnimationUtils.setFadeAnimations(false, new View[] {
                followUpActionView
            }));
            ((List) (obj1)).addAll(AnimationUtils.setFadeAnimations(container, true, primaryActionIds));
            ((List) (obj1)).addAll(AnimationUtils.setFadeAnimations(true, new View[] {
                descriptionView
            }));
        }
        animatorset = new AnimatorSet();
        animatorset.playTogether(((java.util.Collection) (obj2)));
        obj2 = new AnimatorSet();
        ((AnimatorSet) (obj2)).playTogether(((java.util.Collection) (obj1)));
        obj1 = new AnimatorSet();
        ((AnimatorSet) (obj1)).play(animatorset).before(((android.animation.Animator) (obj2)));
        ((AnimatorSet) (obj1)).start();
    }

    protected final int getActionContainerResId()
    {
        return 0x7f100254;
    }

    protected final int getContainerResId()
    {
        return 0x7f100254;
    }

    final void initialize(int i, int ai[])
    {
        super.initialize(i, ai);
        followUpActionView = (Button)container.findViewById(0x7f100257);
        leftActionView = (Button)container.findViewById(0x7f100256);
        initializeButton(followUpActionView);
        initializeButton(leftActionView);
    }
}
