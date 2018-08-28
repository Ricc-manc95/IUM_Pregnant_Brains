// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.design.resources.MaterialResources;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.view.ViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.design.button:
//            MaterialButtonHelper

public class MaterialButton extends AppCompatButton
{

    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private android.graphics.PorterDuff.Mode iconTintMode;
    private final MaterialButtonHelper materialButtonHelper;

    public MaterialButton(Context context)
    {
        this(context, null);
    }

    public MaterialButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f01000f);
    }

    public MaterialButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        attributeset = ThemeEnforcement.obtainStyledAttributes(context, attributeset, R.styleable.MaterialButton, i, 0x7f140397, new int[0]);
        iconPadding = attributeset.getDimensionPixelSize(R.styleable.MaterialButton_iconPadding, 0);
        iconTintMode = ViewUtils.parseTintMode(attributeset.getInt(R.styleable.MaterialButton_iconTintMode, -1), android.graphics.PorterDuff.Mode.SRC_IN);
        iconTint = MaterialResources.getColorStateList(getContext(), attributeset, R.styleable.MaterialButton_iconTint);
        icon = MaterialResources.getDrawable(getContext(), attributeset, R.styleable.MaterialButton_icon);
        iconGravity = attributeset.getInteger(R.styleable.MaterialButton_iconGravity, 1);
        iconSize = attributeset.getDimensionPixelSize(R.styleable.MaterialButton_iconSize, 0);
        materialButtonHelper = new MaterialButtonHelper(this);
        MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
        materialbuttonhelper.insetLeft = attributeset.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetLeft, 0);
        materialbuttonhelper.insetRight = attributeset.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetRight, 0);
        materialbuttonhelper.insetTop = attributeset.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetTop, 0);
        materialbuttonhelper.insetBottom = attributeset.getDimensionPixelOffset(R.styleable.MaterialButton_android_insetBottom, 0);
        materialbuttonhelper.cornerRadius = attributeset.getDimensionPixelSize(R.styleable.MaterialButton_cornerRadius, 0);
        materialbuttonhelper.strokeWidth = attributeset.getDimensionPixelSize(R.styleable.MaterialButton_strokeWidth, 0);
        materialbuttonhelper.backgroundTintMode = ViewUtils.parseTintMode(attributeset.getInt(R.styleable.MaterialButton_backgroundTintMode, -1), android.graphics.PorterDuff.Mode.SRC_IN);
        materialbuttonhelper.backgroundTint = MaterialResources.getColorStateList(materialbuttonhelper.materialButton.getContext(), attributeset, R.styleable.MaterialButton_backgroundTint);
        materialbuttonhelper.strokeColor = MaterialResources.getColorStateList(materialbuttonhelper.materialButton.getContext(), attributeset, R.styleable.MaterialButton_strokeColor);
        materialbuttonhelper.rippleColor = MaterialResources.getColorStateList(materialbuttonhelper.materialButton.getContext(), attributeset, R.styleable.MaterialButton_rippleColor);
        materialbuttonhelper.buttonStrokePaint.setStyle(android.graphics.Paint.Style.STROKE);
        materialbuttonhelper.buttonStrokePaint.setStrokeWidth(materialbuttonhelper.strokeWidth);
        context = materialbuttonhelper.buttonStrokePaint;
        MaterialButton materialbutton;
        int j;
        int k;
        int l;
        if (materialbuttonhelper.strokeColor != null)
        {
            i = materialbuttonhelper.strokeColor.getColorForState(materialbuttonhelper.materialButton.getDrawableState(), 0);
        } else
        {
            i = 0;
        }
        context.setColor(i);
        i = ViewCompat.getPaddingStart(materialbuttonhelper.materialButton);
        j = materialbuttonhelper.materialButton.getPaddingTop();
        k = ViewCompat.getPaddingEnd(materialbuttonhelper.materialButton);
        l = materialbuttonhelper.materialButton.getPaddingBottom();
        materialbutton = materialbuttonhelper.materialButton;
        if (MaterialButtonHelper.IS_LOLLIPOP)
        {
            context = materialbuttonhelper.createBackgroundLollipop();
        } else
        {
            materialbuttonhelper.colorableBackgroundDrawableCompat = new GradientDrawable();
            materialbuttonhelper.colorableBackgroundDrawableCompat.setCornerRadius((float)materialbuttonhelper.cornerRadius + 1E-05F);
            materialbuttonhelper.colorableBackgroundDrawableCompat.setColor(-1);
            context = materialbuttonhelper.colorableBackgroundDrawableCompat;
            if (android.os.Build.VERSION.SDK_INT < 23 && !(context instanceof TintAwareDrawable))
            {
                context = new WrappedDrawableApi21(context);
            }
            materialbuttonhelper.tintableBackgroundDrawableCompat = context;
            materialbuttonhelper.tintableBackgroundDrawableCompat.setTintList(materialbuttonhelper.backgroundTint);
            if (materialbuttonhelper.backgroundTintMode != null)
            {
                materialbuttonhelper.tintableBackgroundDrawableCompat.setTintMode(materialbuttonhelper.backgroundTintMode);
            }
            materialbuttonhelper.rippleDrawableCompat = new GradientDrawable();
            materialbuttonhelper.rippleDrawableCompat.setCornerRadius((float)materialbuttonhelper.cornerRadius + 1E-05F);
            materialbuttonhelper.rippleDrawableCompat.setColor(-1);
            context = materialbuttonhelper.rippleDrawableCompat;
            if (android.os.Build.VERSION.SDK_INT < 23 && !(context instanceof TintAwareDrawable))
            {
                context = new WrappedDrawableApi21(context);
            }
            materialbuttonhelper.tintableRippleDrawableCompat = context;
            materialbuttonhelper.tintableRippleDrawableCompat.setTintList(materialbuttonhelper.rippleColor);
            context = new InsetDrawable(new LayerDrawable(new Drawable[] {
                materialbuttonhelper.tintableBackgroundDrawableCompat, materialbuttonhelper.tintableRippleDrawableCompat
            }), materialbuttonhelper.insetLeft, materialbuttonhelper.insetTop, materialbuttonhelper.insetRight, materialbuttonhelper.insetBottom);
        }
        materialbutton.AppCompatButton.setBackgroundDrawable(context);
        ViewCompat.setPaddingRelative(materialbuttonhelper.materialButton, materialbuttonhelper.insetLeft + i, materialbuttonhelper.insetTop + j, materialbuttonhelper.insetRight + k, materialbuttonhelper.insetBottom + l);
        attributeset.recycle();
        setCompoundDrawablePadding(iconPadding);
        updateIcon();
    }

    private final void updateIcon()
    {
        if (icon != null)
        {
            icon = icon.mutate();
            icon.setTintList(iconTint);
            if (iconTintMode != null)
            {
                icon.setTintMode(iconTintMode);
            }
            int i;
            int j;
            if (iconSize != 0)
            {
                i = iconSize;
            } else
            {
                i = icon.getIntrinsicWidth();
            }
            if (iconSize != 0)
            {
                j = iconSize;
            } else
            {
                j = icon.getIntrinsicHeight();
            }
            icon.setBounds(iconLeft, 0, i + iconLeft, j);
        }
        setCompoundDrawablesRelative(icon, null, null, null);
    }

    public ColorStateList getBackgroundTintList()
    {
        return getSupportBackgroundTintList();
    }

    public android.graphics.PorterDuff.Mode getBackgroundTintMode()
    {
        return getSupportBackgroundTintMode();
    }

    public final ColorStateList getSupportBackgroundTintList()
    {
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return materialButtonHelper.backgroundTint;
        } else
        {
            return super.getSupportBackgroundTintList();
        }
    }

    public final android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return materialButtonHelper.backgroundTintMode;
        } else
        {
            return super.getSupportBackgroundTintMode();
        }
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (android.os.Build.VERSION.SDK_INT == 21 && materialButtonHelper != null)
        {
            MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
            if (materialbuttonhelper.maskDrawableLollipop != null)
            {
                materialbuttonhelper.maskDrawableLollipop.setBounds(materialbuttonhelper.insetLeft, materialbuttonhelper.insetTop, k - i - materialbuttonhelper.insetRight, l - j - materialbuttonhelper.insetBottom);
            }
        }
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if (icon != null && iconGravity == 2)
        {
            j = (int)getPaint().measureText(getText().toString());
            if (iconSize == 0)
            {
                i = icon.getIntrinsicWidth();
            } else
            {
                i = iconSize;
            }
            j = (getMeasuredWidth() - j - ViewCompat.getPaddingEnd(this) - i - iconPadding - ViewCompat.getPaddingStart(this)) / 2;
            i = j;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                i = -j;
            }
            if (iconLeft != i)
            {
                iconLeft = i;
                updateIcon();
                return;
            }
        }
    }

    public void setBackground(Drawable drawable)
    {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundColor(int i)
    {
        MaterialButtonHelper materialbuttonhelper;
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        materialbuttonhelper = materialButtonHelper;
        if (!MaterialButtonHelper.IS_LOLLIPOP || materialbuttonhelper.backgroundDrawableLollipop == null) goto _L2; else goto _L1
_L1:
        materialbuttonhelper.backgroundDrawableLollipop.setColor(i);
_L4:
        return;
_L2:
        if (MaterialButtonHelper.IS_LOLLIPOP || materialbuttonhelper.colorableBackgroundDrawableCompat == null) goto _L4; else goto _L3
_L3:
        materialbuttonhelper.colorableBackgroundDrawableCompat.setColor(i);
        return;
        super.setBackgroundColor(i);
        return;
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
label0:
        {
            boolean flag;
            if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (drawable == getBackground())
                {
                    break label0;
                }
                MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
                materialbuttonhelper.backgroundOverwritten = true;
                materialbuttonhelper.materialButton.setSupportBackgroundTintList(materialbuttonhelper.backgroundTint);
                materialbuttonhelper.materialButton.setSupportBackgroundTintMode(materialbuttonhelper.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable);
            return;
        }
        getBackground().setState(drawable.getState());
    }

    public void setBackgroundResource(int i)
    {
        Drawable drawable = null;
        if (i != 0)
        {
            drawable = AppCompatResources.getDrawable(getContext(), i);
        }
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundTintList(ColorStateList colorstatelist)
    {
        setSupportBackgroundTintList(colorstatelist);
    }

    public void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        setSupportBackgroundTintMode(mode);
    }

    public void setCornerRadius(int i)
    {
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
            if (materialbuttonhelper.cornerRadius != i)
            {
                materialbuttonhelper.cornerRadius = i;
                if (MaterialButtonHelper.IS_LOLLIPOP && materialbuttonhelper.backgroundDrawableLollipop != null && materialbuttonhelper.strokeDrawableLollipop != null && materialbuttonhelper.maskDrawableLollipop != null)
                {
                    if (android.os.Build.VERSION.SDK_INT == 21)
                    {
                        GradientDrawable gradientdrawable;
                        if (MaterialButtonHelper.IS_LOLLIPOP && materialbuttonhelper.materialButton.getBackground() != null)
                        {
                            gradientdrawable = (GradientDrawable)((LayerDrawable)((InsetDrawable)((RippleDrawable)materialbuttonhelper.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(0);
                        } else
                        {
                            gradientdrawable = null;
                        }
                        gradientdrawable.setCornerRadius((float)i + 1E-05F);
                        if (MaterialButtonHelper.IS_LOLLIPOP && materialbuttonhelper.materialButton.getBackground() != null)
                        {
                            gradientdrawable = (GradientDrawable)((LayerDrawable)((InsetDrawable)((RippleDrawable)materialbuttonhelper.materialButton.getBackground()).getDrawable(0)).getDrawable()).getDrawable(1);
                        } else
                        {
                            gradientdrawable = null;
                        }
                        gradientdrawable.setCornerRadius((float)i + 1E-05F);
                    }
                    materialbuttonhelper.backgroundDrawableLollipop.setCornerRadius((float)i + 1E-05F);
                    materialbuttonhelper.strokeDrawableLollipop.setCornerRadius((float)i + 1E-05F);
                    materialbuttonhelper.maskDrawableLollipop.setCornerRadius((float)i + 1E-05F);
                } else
                if (!MaterialButtonHelper.IS_LOLLIPOP && materialbuttonhelper.colorableBackgroundDrawableCompat != null && materialbuttonhelper.rippleDrawableCompat != null)
                {
                    materialbuttonhelper.colorableBackgroundDrawableCompat.setCornerRadius((float)i + 1E-05F);
                    materialbuttonhelper.rippleDrawableCompat.setCornerRadius((float)i + 1E-05F);
                    materialbuttonhelper.materialButton.invalidate();
                    return;
                }
            }
        }
    }

    public void setCornerRadiusResource(int i)
    {
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            setCornerRadius(getResources().getDimensionPixelSize(i));
        }
    }

    public void setIconGravity(int i)
    {
        iconGravity = i;
    }

    public void setIconPadding(int i)
    {
        if (iconPadding != i)
        {
            iconPadding = i;
            setCompoundDrawablePadding(i);
        }
    }

    public void setIconResource(int i)
    {
        Drawable drawable = null;
        if (i != 0)
        {
            drawable = AppCompatResources.getDrawable(getContext(), i);
        }
        if (icon != drawable)
        {
            icon = drawable;
            updateIcon();
        }
    }

    public void setIconSize(int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("iconSize cannot be less than 0");
        }
        if (iconSize != i)
        {
            iconSize = i;
            updateIcon();
        }
    }

    public void setIconTintResource(int i)
    {
        ColorStateList colorstatelist = AppCompatResources.getColorStateList(getContext(), i);
        if (iconTint != colorstatelist)
        {
            iconTint = colorstatelist;
            updateIcon();
        }
    }

    final void setInternalBackground(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
    }

    public void setRippleColorResource(int i)
    {
        boolean flag1 = true;
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(getContext(), i);
            if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
            {
                i = ((flag1) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
                if (materialbuttonhelper.rippleColor != colorstatelist)
                {
                    materialbuttonhelper.rippleColor = colorstatelist;
                    if (MaterialButtonHelper.IS_LOLLIPOP && (materialbuttonhelper.materialButton.getBackground() instanceof RippleDrawable))
                    {
                        ((RippleDrawable)materialbuttonhelper.materialButton.getBackground()).setColor(colorstatelist);
                    } else
                    if (!MaterialButtonHelper.IS_LOLLIPOP && materialbuttonhelper.tintableRippleDrawableCompat != null)
                    {
                        materialbuttonhelper.tintableRippleDrawableCompat.setTintList(colorstatelist);
                        return;
                    }
                }
            }
        }
    }

    public void setStrokeColorResource(int i)
    {
        boolean flag2 = true;
        boolean flag1 = false;
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(getContext(), i);
            if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
            {
                i = ((flag2) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
                if (materialbuttonhelper.strokeColor != colorstatelist)
                {
                    materialbuttonhelper.strokeColor = colorstatelist;
                    Paint paint = materialbuttonhelper.buttonStrokePaint;
                    i = ((flag1) ? 1 : 0);
                    if (colorstatelist != null)
                    {
                        i = colorstatelist.getColorForState(materialbuttonhelper.materialButton.getDrawableState(), 0);
                    }
                    paint.setColor(i);
                    materialbuttonhelper.updateStroke();
                }
            }
        }
    }

    public void setStrokeWidth(int i)
    {
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            MaterialButtonHelper materialbuttonhelper = materialButtonHelper;
            if (materialbuttonhelper.strokeWidth != i)
            {
                materialbuttonhelper.strokeWidth = i;
                materialbuttonhelper.buttonStrokePaint.setStrokeWidth(i);
                materialbuttonhelper.updateStroke();
            }
        }
    }

    public void setStrokeWidthResource(int i)
    {
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            setStrokeWidth(getResources().getDimensionPixelSize(i));
        }
    }

    public final void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        MaterialButtonHelper materialbuttonhelper;
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        materialbuttonhelper = materialButtonHelper;
        if (materialbuttonhelper.backgroundTint == colorstatelist) goto _L4; else goto _L3
_L3:
        materialbuttonhelper.backgroundTint = colorstatelist;
        if (!MaterialButtonHelper.IS_LOLLIPOP) goto _L6; else goto _L5
_L5:
        materialbuttonhelper.updateTintAndTintModeLollipop();
_L4:
        return;
_L6:
        if (materialbuttonhelper.tintableBackgroundDrawableCompat != null)
        {
            materialbuttonhelper.tintableBackgroundDrawableCompat.setTintList(materialbuttonhelper.backgroundTint);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (materialButtonHelper != null)
        {
            super.setSupportBackgroundTintList(colorstatelist);
            return;
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    public final void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        MaterialButtonHelper materialbuttonhelper;
        boolean flag;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        materialbuttonhelper = materialButtonHelper;
        if (materialbuttonhelper.backgroundTintMode == mode) goto _L4; else goto _L3
_L3:
        materialbuttonhelper.backgroundTintMode = mode;
        if (!MaterialButtonHelper.IS_LOLLIPOP) goto _L6; else goto _L5
_L5:
        materialbuttonhelper.updateTintAndTintModeLollipop();
_L4:
        return;
_L6:
        if (materialbuttonhelper.tintableBackgroundDrawableCompat != null && materialbuttonhelper.backgroundTintMode != null)
        {
            materialbuttonhelper.tintableBackgroundDrawableCompat.setTintMode(materialbuttonhelper.backgroundTintMode);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (materialButtonHelper != null)
        {
            super.setSupportBackgroundTintMode(mode);
            return;
        }
        if (true) goto _L4; else goto _L7
_L7:
    }
}
