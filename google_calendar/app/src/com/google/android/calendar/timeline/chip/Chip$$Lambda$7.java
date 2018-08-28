// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.Choreographer;
import android.view.View;
import com.android.bitmap.ReusableBitmap;
import com.google.android.apps.calendar.graphics.ChoreographerValidator;
import com.google.android.apps.calendar.graphics.drawable.CustomAlphaDrawable;
import com.google.android.apps.calendar.graphics.drawable.DrawableContainer;
import com.google.android.apps.calendar.graphics.drawable.ShaderFactory;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.apps.calendar.util.function.IntUnaryOperator;
import com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel;
import com.google.android.calendar.timeline.chip.image.Image;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip, ChipViewModel, ChipConstants, ChipBackgroundImage

final class arg._cls2
    implements Consumer
{

    private final Chip arg$1;
    private final BackgroundImageViewModel arg$2;

    public final void accept(Object obj)
    {
        Chip chip = arg$1;
        Object obj1 = arg$2;
        Object obj2 = (Image)obj;
        int i = chip.viewModel.getCornerRadius();
        obj = ((BackgroundImageViewModel) (obj1)).bottomLineStyle();
        int j = (int)(System.currentTimeMillis() - chip.requestBackgroundImageStartTimeMillis);
        float f;
        Resources resources;
        com.google.android.calendar.utils.rtl.bda._cls0 _lcls0;
        Lambda._cls0 _lcls0_1;
        Object obj3;
        boolean flag;
        int k;
        int l;
        if (!chip.requestBackgroundImageWasImmediate)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l = ChipConstants.backgroundImageFadeInDurationMillis(j);
        obj1 = chip.backgroundImage;
        resources = chip.getContext().getResources();
        _lcls0 = new com.google.android.calendar.utils.rtl.bda._cls0(chip.getContext().getResources().getConfiguration());
        if (((ChipBackgroundImage) (obj1)).reusableBitmap != null)
        {
            ((ChipBackgroundImage) (obj1)).reusableBitmap.releaseReference();
        }
        obj1.reusableBitmap = ((Image) (obj2)).reusableBitmap();
        f = i;
        obj3 = new RoundRectShape(new float[] {
            f, f, f, f, f, f, f, f
        }, null, null);
        _lcls0_1 = new Lambda._cls0(resources, ((Image) (obj2)));
        obj3 = new ShapeDrawable(((android.graphics.drawable.shapes.Shape) (obj3)));
        ((ShapeDrawable) (obj3)).setShaderFactory(_lcls0_1.toAndroid());
        ((ShapeDrawable) (obj3)).getPaint().setFilterBitmap(true);
        obj2 = ((Image) (obj2)).rtlMirroring();
        obj3.getClass();
        obj2 = new com.google.android.apps.calendar.graphics.drawable.it>(((Drawable) (obj3)), new com.google.android.apps.calendar.graphics.ambda._cls0(((Drawable) (obj3))), _lcls0, ((com.google.android.apps.calendar.graphics.RtlMirroring) (obj2)));
        if (obj == com.google.android.calendar.timeline.chip.image.ViewModel.BottomLineStyle.WITH_BOTTOM_LINE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            i = resources.getDimensionPixelSize(0x7f0e0068);
        } else
        {
            i = 0;
        }
        obj = new com.google.android.apps.calendar.graphics.drawable.da._cls2(0, 0);
        j = ((Drawable) (obj2)).getIntrinsicWidth();
        if (j == -1)
        {
            j = -1;
        } else
        {
            j = ((IntUnaryOperator) (obj)).apply(j);
        }
        obj = new com.google.android.apps.calendar.graphics.drawable.da._cls3(0, i);
        k = ((Drawable) (obj2)).getIntrinsicHeight();
        if (k == -1)
        {
            k = -1;
        } else
        {
            k = ((IntUnaryOperator) (obj)).apply(k);
        }
        obj = new com.google.android.apps.calendar.graphics.drawable.it>(((Drawable) (obj2)), new com.google.android.apps.calendar.graphics.drawable.da._cls4(((Drawable) (obj2)), 0, 0, 0, i), j, k);
        if (flag)
        {
            obj = new CustomAlphaDrawable(((Drawable) (obj)));
            ValueAnimator valueanimator = ValueAnimator.ofInt(new int[] {
                0, 255
            });
            valueanimator.setDuration(l);
            valueanimator.addUpdateListener(new com.google.android.apps.calendar.graphics.drawable.da._cls1(((CustomAlphaDrawable) (obj))));
            obj = new com.google.android.apps.calendar.graphics.drawable.it>(((Drawable) (obj)), valueanimator);
        }
        obj1 = ((ChipBackgroundImage) (obj1)).imageContainer;
        obj1.drawable = ((Drawable) (obj));
        ((DrawableContainer) (obj1)).updateDrawable();
        ((DrawableContainer) (obj1)).invalidateSelf();
        obj = chip.solidBackgroundAlphaValidator;
        if (((ChoreographerValidator) (obj)).isValid)
        {
            Choreographer.getInstance().postFrameCallback(((ChoreographerValidator) (obj)).validateFrameCallback);
            obj.isValid = false;
        }
    }

    dImageViewModel(Chip chip, BackgroundImageViewModel backgroundimageviewmodel)
    {
        arg$1 = chip;
        arg$2 = backgroundimageviewmodel;
    }
}
