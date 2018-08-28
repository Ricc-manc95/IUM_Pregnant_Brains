// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.groove:
//            AnimatorHelper

public class BackButtonView extends ImageButton
{

    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/groove/BackButtonView);
    public int colorTheme;
    public int icon;

    public BackButtonView(final Context context)
    {
        this(context, null, 0);
        colorTheme = 1;
        icon = 0;
        setOnClickListener(new _cls2());
    }

    public BackButtonView(final Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
        colorTheme = 1;
        icon = 0;
        setOnClickListener(new _cls2());
    }

    public BackButtonView(final Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        colorTheme = 1;
        icon = 0;
        setOnClickListener(new _cls2());
    }

    public static AnimatorSet createAnimator(View view, boolean flag)
    {
        Object obj = view.findViewById(0x7f10011e);
        if (obj == null)
        {
            return null;
        }
        int i;
        int j;
        if (!flag)
        {
            i = 0;
            j = 1;
        } else
        {
            i = 1;
            j = 0;
        }
        view = new AnimatorSet();
        obj = ObjectAnimator.ofFloat(obj, "alpha", new float[] {
            (float)j, (float)i
        });
        ((ValueAnimator) (obj)).setDuration(210L);
        view.play(((android.animation.Animator) (obj)));
        return view;
    }

    static int getDrawableResId(int i, int j)
    {
        int k = 0x7f0201da;
        i;
        JVM INSTR tableswitch 0 1: default 28
    //                   0 113
    //                   1 53;
           goto _L1 _L2 _L3
_L1:
        LogUtils.wtf(TAG, "Unrecognized icon type for back button view: %d", new Object[] {
            Integer.valueOf(i)
        });
        i = 0x7f0201c3;
_L4:
        return i;
_L3:
        i = k;
        switch (j)
        {
        default:
            LogUtils.wtf(TAG, "Unrecognized theme for back button view: %d", new Object[] {
                Integer.valueOf(j)
            });
            return 0x7f0201da;

        case 0: // '\0'
            return 0x7f0201dc;

        case 2: // '\002'
            return 0x7f0201db;

        case 1: // '\001'
            break;
        }
        if (true) goto _L4; else goto _L2
_L2:
        switch (j)
        {
        default:
            LogUtils.wtf(TAG, "Unrecognized theme for back button view: %d", new Object[] {
                Integer.valueOf(j)
            });
            return 0x7f0201c3;

        case 0: // '\0'
            return 0x7f0201c5;

        case 1: // '\001'
            return 0x7f0201c3;

        case 2: // '\002'
            return 0x7f0201c4;
        }
    }

    public void setIcon(int i)
    {
        Resources resources;
        int j;
        int k;
        j = 0x7f130068;
        icon = i;
        setImageDrawable(getResources().getDrawable(getDrawableResId(icon, colorTheme)));
        resources = getResources();
        k = icon;
        i = j;
        k;
        JVM INSTR tableswitch 0 1: default 68
    //                   0 91
    //                   1 101;
           goto _L1 _L2 _L3
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        LogUtils.wtf(TAG, "Unrecognized icon type for back button view: %d", new Object[] {
            Integer.valueOf(k)
        });
        i = j;
_L5:
        setContentDescription(resources.getString(i));
        return;
_L3:
        i = 0x7f13010f;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void setTheme(int i, boolean flag)
    {
        if (colorTheme == i)
        {
            return;
        }
        colorTheme = i;
        if (!flag)
        {
            setImageDrawable(getResources().getDrawable(getDrawableResId(icon, colorTheme)));
            return;
        } else
        {
            AnimatorHelper.createFadeOutFadeInAnimator(this, new _cls1()).start();
            return;
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final Context val$context;

        public final void onClick(View view)
        {
            if (context instanceof Activity)
            {
                ((Activity)context).onBackPressed();
            }
        }

        _cls2()
        {
            context = context1;
            super();
        }
    }


    private class _cls1
        implements AnimatorHelper.FadeOutFadeInAnimatorListener
    {

        private final BackButtonView this$0;

        public final void fadeOutAnimationEnd()
        {
            setImageDrawable(getResources().getDrawable(BackButtonView.getDrawableResId(icon, colorTheme)));
        }

        _cls1()
        {
            this$0 = BackButtonView.this;
            super();
        }
    }

}
