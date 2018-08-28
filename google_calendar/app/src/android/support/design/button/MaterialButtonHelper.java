// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.button;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.ripple.RippleUtils;

// Referenced classes of package android.support.design.button:
//            MaterialButtonBackgroundDrawable, MaterialButton

final class MaterialButtonHelper
{

    public static final boolean IS_LOLLIPOP = true;
    public GradientDrawable backgroundDrawableLollipop;
    public boolean backgroundOverwritten;
    public ColorStateList backgroundTint;
    public android.graphics.PorterDuff.Mode backgroundTintMode;
    private final Rect bounds = new Rect();
    public final Paint buttonStrokePaint = new Paint(1);
    public GradientDrawable colorableBackgroundDrawableCompat;
    public int cornerRadius;
    public int insetBottom;
    public int insetLeft;
    public int insetRight;
    public int insetTop;
    public GradientDrawable maskDrawableLollipop;
    public final MaterialButton materialButton;
    private final RectF rectF = new RectF();
    public ColorStateList rippleColor;
    public GradientDrawable rippleDrawableCompat;
    public ColorStateList strokeColor;
    public GradientDrawable strokeDrawableLollipop;
    public int strokeWidth;
    public Drawable tintableBackgroundDrawableCompat;
    public Drawable tintableRippleDrawableCompat;

    public MaterialButtonHelper(MaterialButton materialbutton)
    {
        backgroundOverwritten = false;
        materialButton = materialbutton;
    }

    final Drawable createBackgroundLollipop()
    {
        backgroundDrawableLollipop = new GradientDrawable();
        backgroundDrawableLollipop.setCornerRadius((float)cornerRadius + 1E-05F);
        backgroundDrawableLollipop.setColor(-1);
        updateTintAndTintModeLollipop();
        strokeDrawableLollipop = new GradientDrawable();
        strokeDrawableLollipop.setCornerRadius((float)cornerRadius + 1E-05F);
        strokeDrawableLollipop.setColor(0);
        strokeDrawableLollipop.setStroke(strokeWidth, strokeColor);
        InsetDrawable insetdrawable = new InsetDrawable(new LayerDrawable(new Drawable[] {
            backgroundDrawableLollipop, strokeDrawableLollipop
        }), insetLeft, insetTop, insetRight, insetBottom);
        maskDrawableLollipop = new GradientDrawable();
        maskDrawableLollipop.setCornerRadius((float)cornerRadius + 1E-05F);
        maskDrawableLollipop.setColor(-1);
        return new MaterialButtonBackgroundDrawable(RippleUtils.convertToRippleDrawableColor(rippleColor), insetdrawable, maskDrawableLollipop);
    }

    final void updateStroke()
    {
        if (IS_LOLLIPOP && strokeDrawableLollipop != null)
        {
            materialButton.setInternalBackground(createBackgroundLollipop());
        } else
        if (!IS_LOLLIPOP)
        {
            materialButton.invalidate();
            return;
        }
    }

    final void updateTintAndTintModeLollipop()
    {
        if (backgroundDrawableLollipop != null)
        {
            backgroundDrawableLollipop.setTintList(backgroundTint);
            if (backgroundTintMode != null)
            {
                backgroundDrawableLollipop.setTintMode(backgroundTintMode);
            }
        }
    }

}
