// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.Utils;

// Referenced classes of package com.google.android.calendar.groove:
//            CreateGrooveActivity

final class arg._cls1
    implements Consumer
{

    private final CreateGrooveActivity arg$1;

    public final void accept(Object obj)
    {
        CreateGrooveActivity creategrooveactivity = arg$1;
        obj = (Bitmap)obj;
        if (!creategrooveactivity.isDestroyed())
        {
            obj = new BitmapDrawable(creategrooveactivity.getResources(), Utils.getRtlAdjustedImage(creategrooveactivity, ((Bitmap) (obj))));
            creategrooveactivity.banner.setImageDrawable(((android.graphics.drawable.Drawable) (obj)));
            obj = ObjectAnimator.ofFloat(creategrooveactivity.banner, "alpha", new float[] {
                0.0F, 1.0F
            });
            ((ValueAnimator) (obj)).setDuration(210L);
            ((ValueAnimator) (obj)).setInterpolator(new LinearInterpolator());
            ((Animator) (obj)).start();
            creategrooveactivity.banner.requestLayout();
        }
    }

    (CreateGrooveActivity creategrooveactivity)
    {
        arg$1 = creategrooveactivity;
    }
}
