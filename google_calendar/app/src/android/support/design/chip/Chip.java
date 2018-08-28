// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.design.animation.MotionSpec;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.resources.MaterialResources;
import android.support.design.resources.TextAppearance;
import android.support.design.ripple.RippleUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v4.text.BidiFormatter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

// Referenced classes of package android.support.design.chip:
//            ChipDrawable

public class Chip extends AppCompatCheckBox
    implements ChipDrawable.Delegate
{
    final class ChipTouchHelper extends ExploreByTouchHelper
    {

        private final Chip this$0;

        protected final int getVirtualViewAt(float f, float f1)
        {
            return !hasCloseIcon() || !getCloseIconTouchBounds().contains(f, f1) ? -1 : 0;
        }

        protected final void getVisibleVirtualViews(List list)
        {
            if (hasCloseIcon())
            {
                list.add(Integer.valueOf(0));
            }
        }

        protected final boolean onPerformActionForVirtualView(int i, int j, Bundle bundle)
        {
            if (j == 16 && i == 0)
            {
                return performCloseIconClick();
            } else
            {
                return false;
            }
        }

        protected final void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            Object obj;
            boolean flag;
            if (chipDrawable != null && chipDrawable.checkable)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            accessibilitynodeinfocompat.mInfo.setCheckable(flag);
            obj = android/support/design/chip/Chip.getName();
            accessibilitynodeinfocompat.mInfo.setClassName(((CharSequence) (obj)));
            if (chipDrawable != null)
            {
                obj = chipDrawable.rawText;
            } else
            {
                obj = "";
            }
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                accessibilitynodeinfocompat.mInfo.setText(((CharSequence) (obj)));
                return;
            } else
            {
                accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj)));
                return;
            }
        }

        protected final void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            if (hasCloseIcon())
            {
                Object obj = Chip.this;
                if (((Chip) (obj)).chipDrawable != null)
                {
                    obj = ((Chip) (obj)).chipDrawable;
                }
                boolean flag;
                if (false)
                {
                    accessibilitynodeinfocompat.mInfo.setContentDescription(null);
                } else
                {
                    Object obj1 = getText();
                    Context context = getContext();
                    if (TextUtils.isEmpty(((CharSequence) (obj1))))
                    {
                        obj1 = "";
                    }
                    obj1 = context.getString(0x7f130344, new Object[] {
                        obj1
                    }).trim();
                    accessibilitynodeinfocompat.mInfo.setContentDescription(((CharSequence) (obj1)));
                }
                obj = getCloseIconTouchBoundsInt();
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(((Rect) (obj)));
                obj = android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK;
                accessibilitynodeinfocompat.mInfo.addAction((android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat) (obj)).mAction);
                flag = isEnabled();
                accessibilitynodeinfocompat.mInfo.setEnabled(flag);
                return;
            } else
            {
                accessibilitynodeinfocompat.mInfo.setContentDescription("");
                Rect rect1 = Chip.EMPTY_BOUNDS;
                accessibilitynodeinfocompat.mInfo.setBoundsInParent(rect1);
                return;
            }
        }

        ChipTouchHelper(Chip chip1)
        {
            this$0 = Chip.this;
            super(chip1);
        }
    }


    public static final Rect EMPTY_BOUNDS = new Rect();
    private static final int SELECTED_STATE[] = {
        0x10100a1
    };
    public ChipDrawable chipDrawable;
    private boolean closeIconFocused;
    private boolean closeIconHovered;
    private boolean closeIconPressed;
    private boolean deferredCheckedValue;
    private int focusedVirtualView;
    private final android.support.v4.content.res.ResourcesCompat.FontCallback fontCallback;
    public android.view.View.OnClickListener onCloseIconClickListener;
    private final Rect rect;
    private final RectF rectF;
    private RippleDrawable ripple;
    private final ChipTouchHelper touchHelper;

    public Chip(Context context)
    {
        this(context, null);
    }

    public Chip(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010007);
    }

    public Chip(Context context, AttributeSet attributeset, int i)
    {
        ChipDrawable chipdrawable;
        Object obj = null;
        super(context, attributeset, i);
        focusedVirtualView = 0x80000000;
        rect = new Rect();
        rectF = new RectF();
        fontCallback = new _cls1();
        if (attributeset != null)
        {
            if (attributeset.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null)
            {
                throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
            }
            if (attributeset.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null)
            {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            }
            if (attributeset.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null)
            {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            }
            if (attributeset.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null)
            {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            }
            if (attributeset.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null)
            {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            }
            if (!attributeset.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeset.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeset.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeset.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1)
            {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            }
        }
        chipdrawable = new ChipDrawable(context);
        attributeset = ThemeEnforcement.obtainStyledAttributes(chipdrawable.context, attributeset, R.styleable.Chip, i, 0x7f1403a2, new int[0]);
        context = MaterialResources.getColorStateList(chipdrawable.context, attributeset, R.styleable.Chip_chipBackgroundColor);
        if (chipdrawable.chipBackgroundColor != context)
        {
            chipdrawable.chipBackgroundColor = context;
            chipdrawable.onStateChange(chipdrawable.getState());
        }
        float f = attributeset.getDimension(R.styleable.Chip_chipMinHeight, 0.0F);
        if (chipdrawable.chipMinHeight != f)
        {
            chipdrawable.chipMinHeight = f;
            chipdrawable.invalidateSelf();
            context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
            if (context != null)
            {
                context.onChipDrawableSizeChange();
            }
        }
        f = attributeset.getDimension(R.styleable.Chip_chipCornerRadius, 0.0F);
        if (chipdrawable.chipCornerRadius != f)
        {
            chipdrawable.chipCornerRadius = f;
            chipdrawable.invalidateSelf();
        }
        context = MaterialResources.getColorStateList(chipdrawable.context, attributeset, R.styleable.Chip_chipStrokeColor);
        if (chipdrawable.chipStrokeColor != context)
        {
            chipdrawable.chipStrokeColor = context;
            chipdrawable.onStateChange(chipdrawable.getState());
        }
        f = attributeset.getDimension(R.styleable.Chip_chipStrokeWidth, 0.0F);
        if (chipdrawable.chipStrokeWidth != f)
        {
            chipdrawable.chipStrokeWidth = f;
            chipdrawable.chipPaint.setStrokeWidth(f);
            chipdrawable.invalidateSelf();
        }
        context = MaterialResources.getColorStateList(chipdrawable.context, attributeset, R.styleable.Chip_rippleColor);
        if (chipdrawable.rippleColor != context)
        {
            chipdrawable.rippleColor = context;
            float f1;
            if (chipdrawable.useCompatRipple)
            {
                context = RippleUtils.convertToRippleDrawableColor(chipdrawable.rippleColor);
            } else
            {
                context = null;
            }
            chipdrawable.compatRippleColor = context;
            chipdrawable.onStateChange(chipdrawable.getState());
        }
        chipdrawable.setText(attributeset.getText(R.styleable.Chip_android_text));
        context = chipdrawable.context;
        i = R.styleable.Chip_android_textAppearance;
        if (!attributeset.hasValue(i)) goto _L2; else goto _L1
_L1:
        i = attributeset.getResourceId(i, 0);
        if (i == 0) goto _L2; else goto _L3
_L3:
        context = new TextAppearance(context, i);
_L8:
        chipdrawable.setTextAppearance(context);
        attributeset.getInt(R.styleable.Chip_android_ellipsize, 0);
        JVM INSTR tableswitch 1 3: default 628
    //                   1 1705
    //                   2 1716
    //                   3 1727;
           goto _L4 _L5 _L6 _L7
_L4:
        break; /* Loop/switch isn't completed */
_L7:
        break MISSING_BLOCK_LABEL_1727;
_L9:
        boolean flag;
        if (attributeset.getBoolean(R.styleable.Chip_chipIconVisible, false) || attributeset.getBoolean(R.styleable.Chip_chipIconEnabled, false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        chipdrawable.setChipIconVisible(flag);
        chipdrawable.setChipIcon(MaterialResources.getDrawable(chipdrawable.context, attributeset, R.styleable.Chip_chipIcon));
        chipdrawable.setChipIconTint(MaterialResources.getColorStateList(chipdrawable.context, attributeset, R.styleable.Chip_chipIconTint));
        chipdrawable.setChipIconSize(attributeset.getDimension(R.styleable.Chip_chipIconSize, 0.0F));
        if (attributeset.getBoolean(R.styleable.Chip_closeIconVisible, false) || attributeset.getBoolean(R.styleable.Chip_closeIconEnabled, false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        chipdrawable.setCloseIconVisible(flag);
        chipdrawable.setCloseIcon(MaterialResources.getDrawable(chipdrawable.context, attributeset, R.styleable.Chip_closeIcon));
        chipdrawable.setCloseIconTint(MaterialResources.getColorStateList(chipdrawable.context, attributeset, R.styleable.Chip_closeIconTint));
        f1 = attributeset.getDimension(R.styleable.Chip_closeIconSize, 0.0F);
        if (chipdrawable.closeIconSize != f1)
        {
            chipdrawable.closeIconSize = f1;
            chipdrawable.invalidateSelf();
            if (chipdrawable.closeIconVisible && chipdrawable.closeIcon != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
                if (context != null)
                {
                    context.onChipDrawableSizeChange();
                }
            }
        }
        chipdrawable.setCheckable(attributeset.getBoolean(R.styleable.Chip_android_checkable, false));
        if (attributeset.getBoolean(R.styleable.Chip_checkedIconVisible, false) || attributeset.getBoolean(R.styleable.Chip_checkedIconEnabled, false))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        chipdrawable.setCheckedIconVisible(flag);
        chipdrawable.setCheckedIcon(MaterialResources.getDrawable(chipdrawable.context, attributeset, R.styleable.Chip_checkedIcon));
        context = chipdrawable.context;
        i = R.styleable.Chip_showMotionSpec;
        if (attributeset.hasValue(i))
        {
            i = attributeset.getResourceId(i, 0);
            if (i != 0)
            {
                MotionSpec.createFromResource(context, i);
            }
        }
        context = chipdrawable.context;
        i = R.styleable.Chip_hideMotionSpec;
        if (attributeset.hasValue(i))
        {
            i = attributeset.getResourceId(i, 0);
            if (i != 0)
            {
                MotionSpec.createFromResource(context, i);
            }
        }
        f1 = attributeset.getDimension(R.styleable.Chip_chipStartPadding, 0.0F);
        if (chipdrawable.chipStartPadding != f1)
        {
            chipdrawable.chipStartPadding = f1;
            chipdrawable.invalidateSelf();
            context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
            if (context != null)
            {
                context.onChipDrawableSizeChange();
            }
        }
        chipdrawable.setIconStartPadding(attributeset.getDimension(R.styleable.Chip_iconStartPadding, 0.0F));
        chipdrawable.setIconEndPadding(attributeset.getDimension(R.styleable.Chip_iconEndPadding, 0.0F));
        f1 = attributeset.getDimension(R.styleable.Chip_textStartPadding, 0.0F);
        if (chipdrawable.textStartPadding != f1)
        {
            chipdrawable.textStartPadding = f1;
            chipdrawable.invalidateSelf();
            context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
            if (context != null)
            {
                context.onChipDrawableSizeChange();
            }
        }
        f1 = attributeset.getDimension(R.styleable.Chip_textEndPadding, 0.0F);
        if (chipdrawable.textEndPadding != f1)
        {
            chipdrawable.textEndPadding = f1;
            chipdrawable.invalidateSelf();
            context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
            if (context != null)
            {
                context.onChipDrawableSizeChange();
            }
        }
        f1 = attributeset.getDimension(R.styleable.Chip_closeIconStartPadding, 0.0F);
        if (chipdrawable.closeIconStartPadding != f1)
        {
            chipdrawable.closeIconStartPadding = f1;
            chipdrawable.invalidateSelf();
            if (chipdrawable.closeIconVisible && chipdrawable.closeIcon != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
                if (context != null)
                {
                    context.onChipDrawableSizeChange();
                }
            }
        }
        f1 = attributeset.getDimension(R.styleable.Chip_closeIconEndPadding, 0.0F);
        if (chipdrawable.closeIconEndPadding != f1)
        {
            chipdrawable.closeIconEndPadding = f1;
            chipdrawable.invalidateSelf();
            if (chipdrawable.closeIconVisible && chipdrawable.closeIcon != null)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
                if (context != null)
                {
                    context.onChipDrawableSizeChange();
                }
            }
        }
        f1 = attributeset.getDimension(R.styleable.Chip_chipEndPadding, 0.0F);
        if (chipdrawable.chipEndPadding != f1)
        {
            chipdrawable.chipEndPadding = f1;
            chipdrawable.invalidateSelf();
            context = (ChipDrawable.Delegate)chipdrawable._flddelegate.get();
            if (context != null)
            {
                context.onChipDrawableSizeChange();
            }
        }
        chipdrawable.maxWidth = attributeset.getDimensionPixelSize(R.styleable.Chip_android_maxWidth, 0x7fffffff);
        attributeset.recycle();
        if (chipDrawable != chipdrawable)
        {
            context = chipDrawable;
            if (context != null)
            {
                context._flddelegate = new WeakReference(null);
            }
            chipDrawable = chipdrawable;
            chipDrawable._flddelegate = new WeakReference(this);
            if (RippleUtils.USE_FRAMEWORK_RIPPLE)
            {
                ripple = new RippleDrawable(RippleUtils.convertToRippleDrawableColor(chipDrawable.rippleColor), chipDrawable, null);
                attributeset = chipDrawable;
                if (((ChipDrawable) (attributeset)).useCompatRipple)
                {
                    attributeset.useCompatRipple = false;
                    if (((ChipDrawable) (attributeset)).useCompatRipple)
                    {
                        context = RippleUtils.convertToRippleDrawableColor(((ChipDrawable) (attributeset)).rippleColor);
                    } else
                    {
                        context = null;
                    }
                    attributeset.compatRippleColor = context;
                    attributeset.onStateChange(attributeset.getState());
                }
                ViewCompat.setBackground(this, ripple);
            } else
            {
                attributeset = chipDrawable;
                if (!((ChipDrawable) (attributeset)).useCompatRipple)
                {
                    attributeset.useCompatRipple = true;
                    if (((ChipDrawable) (attributeset)).useCompatRipple)
                    {
                        context = RippleUtils.convertToRippleDrawableColor(((ChipDrawable) (attributeset)).rippleColor);
                    } else
                    {
                        context = null;
                    }
                    attributeset.compatRippleColor = context;
                    attributeset.onStateChange(attributeset.getState());
                }
                ViewCompat.setBackground(this, chipDrawable);
            }
        }
        touchHelper = new ChipTouchHelper(this);
        ViewCompat.setAccessibilityDelegate(this, touchHelper);
        ViewCompat.setImportantForAccessibility(this, 1);
        setOutlineProvider(new _cls2());
        setChecked(deferredCheckedValue);
        chipdrawable.shouldDrawText = false;
        setText(chipdrawable.rawText);
        setEllipsize(chipdrawable.truncateAt);
        setIncludeFontPadding(false);
        if (chipDrawable != null)
        {
            context = chipDrawable.textAppearance;
        } else
        {
            context = null;
        }
        if (context != null)
        {
            context = obj;
            if (chipDrawable != null)
            {
                context = chipDrawable.textAppearance;
            }
            attributeset = getPaint();
            attributeset.drawableState = chipDrawable.getState();
            context.updateDrawState(getContext(), attributeset, fontCallback);
        }
        setSingleLine();
        setGravity(0x800013);
        updatePaddingInternal();
        return;
_L2:
        context = null;
          goto _L8
_L5:
        chipdrawable.truncateAt = android.text.TextUtils.TruncateAt.START;
          goto _L9
_L6:
        chipdrawable.truncateAt = android.text.TextUtils.TruncateAt.MIDDLE;
          goto _L9
        chipdrawable.truncateAt = android.text.TextUtils.TruncateAt.END;
          goto _L9
    }

    private final boolean handleAccessibilityExit(MotionEvent motionevent)
    {
        if (motionevent.getAction() != 10)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        motionevent = android/support/v4/widget/ExploreByTouchHelper.getDeclaredField("mHoveredVirtualViewId");
        motionevent.setAccessible(true);
        if (((Integer)motionevent.get(touchHelper)).intValue() == 0x80000000)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        motionevent = android/support/v4/widget/ExploreByTouchHelper.getDeclaredMethod("updateHoveredVirtualView", new Class[] {
            Integer.TYPE
        });
        motionevent.setAccessible(true);
        motionevent.invoke(touchHelper, new Object[] {
            Integer.valueOf(0x80000000)
        });
        return true;
        motionevent;
        Log.e("Chip", "Unable to send Accessibility Exit event", motionevent);
_L2:
        return false;
        motionevent;
        Log.e("Chip", "Unable to send Accessibility Exit event", motionevent);
        continue; /* Loop/switch isn't completed */
        motionevent;
        Log.e("Chip", "Unable to send Accessibility Exit event", motionevent);
        continue; /* Loop/switch isn't completed */
        motionevent;
        Log.e("Chip", "Unable to send Accessibility Exit event", motionevent);
        if (true) goto _L2; else goto _L1
_L1:
    }

    private final boolean moveFocus(boolean flag)
    {
        if (focusedVirtualView == 0x80000000)
        {
            setFocusedVirtualView(-1);
        }
        if (flag)
        {
            if (focusedVirtualView == -1)
            {
                setFocusedVirtualView(0);
                return true;
            }
        } else
        if (focusedVirtualView == 0)
        {
            setFocusedVirtualView(-1);
            return true;
        }
        return false;
    }

    private final void setFocusedVirtualView(int i)
    {
        if (focusedVirtualView != i)
        {
            if (focusedVirtualView == 0 && closeIconFocused)
            {
                closeIconFocused = false;
                refreshDrawableState();
            }
            focusedVirtualView = i;
            if (i == 0 && !closeIconFocused)
            {
                closeIconFocused = true;
                refreshDrawableState();
            }
        }
    }

    private final void updatePaddingInternal()
    {
        if (!TextUtils.isEmpty(getText()) && chipDrawable != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        Object obj;
label0:
        {
label1:
            {
                float f = chipDrawable.chipStartPadding;
                float f2 = chipDrawable.chipEndPadding;
                float f3 = chipDrawable.textStartPadding;
                f2 = chipDrawable.textEndPadding + (f + f2 + f3);
                if (chipDrawable.chipIconVisible)
                {
                    obj = chipDrawable;
                    float f1;
                    Drawable drawable1;
                    if (((ChipDrawable) (obj)).chipIcon != null)
                    {
                        Drawable drawable = ((ChipDrawable) (obj)).chipIcon;
                        obj = drawable;
                        if (drawable instanceof WrappedDrawable)
                        {
                            obj = ((WrappedDrawable)drawable).getWrappedDrawable();
                        }
                    } else
                    {
                        obj = null;
                    }
                    if (obj != null)
                    {
                        break label1;
                    }
                }
                f1 = f2;
                if (chipDrawable.checkedIcon == null)
                {
                    break label0;
                }
                f1 = f2;
                if (!chipDrawable.checkedIconVisible)
                {
                    break label0;
                }
                f1 = f2;
                if (!isChecked())
                {
                    break label0;
                }
            }
            f1 = chipDrawable.iconStartPadding + chipDrawable.iconEndPadding + chipDrawable.chipIconSize + f2;
        }
        f2 = f1;
        if (chipDrawable.closeIconVisible)
        {
            obj = chipDrawable;
            if (((ChipDrawable) (obj)).closeIcon != null)
            {
                drawable1 = ((ChipDrawable) (obj)).closeIcon;
                obj = drawable1;
                if (drawable1 instanceof WrappedDrawable)
                {
                    obj = ((WrappedDrawable)drawable1).getWrappedDrawable();
                }
            } else
            {
                obj = null;
            }
            f2 = f1;
            if (obj != null)
            {
                f2 = f1 + (chipDrawable.closeIconStartPadding + chipDrawable.closeIconEndPadding + chipDrawable.closeIconSize);
            }
        }
        if ((float)getPaddingEnd() != f2)
        {
            ViewCompat.setPaddingRelative(this, ViewCompat.getPaddingStart(this), getPaddingTop(), (int)f2, getPaddingBottom());
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    protected boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        return handleAccessibilityExit(motionevent) || touchHelper.dispatchHoverEvent(motionevent) || super.dispatchHoverEvent(motionevent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        ChipTouchHelper chiptouchhelper;
        boolean flag2;
        flag2 = false;
        chiptouchhelper = touchHelper;
        if (keyevent.getAction() == 1) goto _L2; else goto _L1
_L1:
        int i = keyevent.getKeyCode();
        i;
        JVM INSTR lookupswitch 7: default 88
    //                   19: 114
    //                   20: 114
    //                   21: 114
    //                   22: 114
    //                   23: 174
    //                   61: 217
    //                   66: 174;
           goto _L2 _L3 _L3 _L3 _L3 _L4 _L5 _L4
_L2:
        boolean flag = false;
_L11:
        if (flag) goto _L7; else goto _L6
_L6:
        flag = flag2;
        if (!super.dispatchKeyEvent(keyevent)) goto _L8; else goto _L7
_L7:
        flag = true;
_L8:
        return flag;
_L3:
        if (!keyevent.hasNoModifiers()) goto _L2; else goto _L9
_L9:
        int j;
        int k;
        boolean flag1;
        j = ExploreByTouchHelper.keyToDirection(i);
        k = keyevent.getRepeatCount();
        i = 0;
        flag1 = false;
_L13:
        flag = flag1;
        if (i >= k + 1) goto _L11; else goto _L10
_L10:
        flag = flag1;
        if (!chiptouchhelper.moveFocus(j, null)) goto _L11; else goto _L12
_L12:
        i++;
        flag1 = true;
          goto _L13
_L4:
        if (!keyevent.hasNoModifiers() || keyevent.getRepeatCount() != 0) goto _L2; else goto _L14
_L14:
        if (((ExploreByTouchHelper) (chiptouchhelper)).mKeyboardFocusedVirtualViewId != 0x80000000)
        {
            if (!chiptouchhelper.onPerformActionForVirtualView(((ExploreByTouchHelper) (chiptouchhelper)).mKeyboardFocusedVirtualViewId, 16, null));
        }
        flag = true;
          goto _L11
_L5:
        if (!keyevent.hasNoModifiers())
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = chiptouchhelper.moveFocus(2, null);
          goto _L11
        if (!keyevent.hasModifiers(1)) goto _L2; else goto _L15
_L15:
        flag = chiptouchhelper.moveFocus(1, null);
          goto _L11
    }

    protected void drawableStateChanged()
    {
        boolean flag = true;
        boolean flag2 = false;
        super.drawableStateChanged();
        boolean flag1 = flag2;
        if (chipDrawable != null)
        {
            flag1 = flag2;
            if (ChipDrawable.isStateful(chipDrawable.closeIcon))
            {
                ChipDrawable chipdrawable = chipDrawable;
                int ai[];
                int i;
                int j;
                if (isEnabled())
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                i = j;
                if (closeIconFocused)
                {
                    i = j + 1;
                }
                j = i;
                if (closeIconHovered)
                {
                    j = i + 1;
                }
                i = j;
                if (closeIconPressed)
                {
                    i = j + 1;
                }
                j = i;
                if (isChecked())
                {
                    j = i + 1;
                }
                ai = new int[j];
                if (isEnabled())
                {
                    ai[0] = 0x101009e;
                    j = ((flag) ? 1 : 0);
                } else
                {
                    j = 0;
                }
                i = j;
                if (closeIconFocused)
                {
                    ai[j] = 0x101009c;
                    i = j + 1;
                }
                j = i;
                if (closeIconHovered)
                {
                    ai[i] = 0x1010367;
                    j = i + 1;
                }
                i = j;
                if (closeIconPressed)
                {
                    ai[j] = 0x10100a7;
                    i = j + 1;
                }
                if (isChecked())
                {
                    ai[i] = 0x10100a1;
                }
                flag1 = chipdrawable.setCloseIconState(ai);
            }
        }
        if (flag1)
        {
            invalidate();
        }
    }

    final RectF getCloseIconTouchBounds()
    {
        rectF.setEmpty();
        if (hasCloseIcon())
        {
            ChipDrawable chipdrawable = chipDrawable;
            RectF rectf = rectF;
            Rect rect1 = chipdrawable.getBounds();
            rectf.setEmpty();
            boolean flag;
            if (chipdrawable.closeIconVisible && chipdrawable.closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                float f = chipdrawable.chipEndPadding + chipdrawable.closeIconEndPadding + chipdrawable.closeIconSize + chipdrawable.closeIconStartPadding + chipdrawable.textEndPadding;
                if (DrawableCompat.getLayoutDirection(chipdrawable) == 0)
                {
                    rectf.right = rect1.right;
                    rectf.left = rectf.right - f;
                } else
                {
                    rectf.left = rect1.left;
                    rectf.right = f + (float)rect1.left;
                }
                rectf.top = rect1.top;
                rectf.bottom = rect1.bottom;
            }
        }
        return rectF;
    }

    final Rect getCloseIconTouchBoundsInt()
    {
        RectF rectf = getCloseIconTouchBounds();
        rect.set((int)rectf.left, (int)rectf.top, (int)rectf.right, (int)rectf.bottom);
        return rect;
    }

    public android.text.TextUtils.TruncateAt getEllipsize()
    {
        if (chipDrawable != null)
        {
            return chipDrawable.truncateAt;
        } else
        {
            return null;
        }
    }

    public void getFocusedRect(Rect rect1)
    {
        if (focusedVirtualView == 0)
        {
            rect1.set(getCloseIconTouchBoundsInt());
            return;
        } else
        {
            super.getFocusedRect(rect1);
            return;
        }
    }

    public CharSequence getText()
    {
        if (chipDrawable != null)
        {
            return chipDrawable.rawText;
        } else
        {
            return "";
        }
    }

    final boolean hasCloseIcon()
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).closeIcon != null)
            {
                Drawable drawable = ((ChipDrawable) (obj)).closeIcon;
                obj = drawable;
                if (drawable instanceof WrappedDrawable)
                {
                    obj = ((WrappedDrawable)drawable).getWrappedDrawable();
                }
            } else
            {
                obj = null;
            }
            if (obj != null)
            {
                return true;
            }
        }
        return false;
    }

    public final void onChipDrawableSizeChange()
    {
        updatePaddingInternal();
        requestLayout();
        invalidateOutline();
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if (isChecked())
        {
            mergeDrawableStates(ai, SELECTED_STATE);
        }
        return ai;
    }

    protected void onDraw(Canvas canvas)
    {
        float f;
        float f1;
        float f2;
        boolean flag1 = true;
        if (TextUtils.isEmpty(getText()) || chipDrawable == null || chipDrawable.shouldDrawText)
        {
            super.onDraw(canvas);
            return;
        }
        int i = canvas.save();
        ChipDrawable chipdrawable = chipDrawable;
        boolean flag;
        if (chipDrawable != null)
        {
            f = chipDrawable.chipStartPadding;
        } else
        {
            f = 0.0F;
        }
        if (chipdrawable.chipIconVisible && chipdrawable.chipIcon != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            if (chipdrawable.checkedIconVisible && chipdrawable.checkedIcon != null && chipdrawable.currentChecked)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_203;
            }
        }
        f1 = chipdrawable.iconStartPadding + chipdrawable.chipIconSize + chipdrawable.iconEndPadding;
_L1:
        if (chipDrawable != null)
        {
            f2 = chipDrawable.textStartPadding;
        } else
        {
            f2 = 0.0F;
        }
        f = f2 + (f1 + f);
        if (ViewCompat.getLayoutDirection(this) != 0)
        {
            f = -f;
        }
        canvas.translate(f, 0.0F);
        super.onDraw(canvas);
        canvas.restoreToCount(i);
        return;
        f1 = 0.0F;
          goto _L1
    }

    protected void onFocusChanged(boolean flag, int i, Rect rect1)
    {
        ChipTouchHelper chiptouchhelper;
        if (flag)
        {
            setFocusedVirtualView(-1);
        } else
        {
            setFocusedVirtualView(0x80000000);
        }
        invalidate();
        super.onFocusChanged(flag, i, rect1);
        chiptouchhelper = touchHelper;
        if (((ExploreByTouchHelper) (chiptouchhelper)).mKeyboardFocusedVirtualViewId != 0x80000000)
        {
            int j = ((ExploreByTouchHelper) (chiptouchhelper)).mKeyboardFocusedVirtualViewId;
            if (((ExploreByTouchHelper) (chiptouchhelper)).mKeyboardFocusedVirtualViewId == j)
            {
                chiptouchhelper.mKeyboardFocusedVirtualViewId = 0x80000000;
                chiptouchhelper.sendEventForVirtualView(j, 8);
            }
        }
        if (flag)
        {
            chiptouchhelper.moveFocus(i, rect1);
        }
    }

    public boolean onHoverEvent(MotionEvent motionevent)
    {
        motionevent.getActionMasked();
        JVM INSTR tableswitch 7 10: default 36
    //                   7 42
    //                   8 36
    //                   9 36
    //                   10 312;
           goto _L1 _L2 _L1 _L1 _L3
_L1:
        return super.onHoverEvent(motionevent);
_L2:
        rectF.setEmpty();
        if (chipDrawable == null) goto _L5; else goto _L4
_L4:
        boolean flag;
        Object obj = chipDrawable;
        boolean flag1;
        if (((ChipDrawable) (obj)).closeIcon != null)
        {
            Drawable drawable = ((ChipDrawable) (obj)).closeIcon;
            obj = drawable;
            if (drawable instanceof WrappedDrawable)
            {
                obj = ((WrappedDrawable)drawable).getWrappedDrawable();
            }
        } else
        {
            obj = null;
        }
        if (obj == null) goto _L5; else goto _L6
_L6:
        flag = true;
_L7:
        if (flag)
        {
            obj = chipDrawable;
            RectF rectf = rectF;
            Rect rect1 = ((ChipDrawable) (obj)).getBounds();
            rectf.setEmpty();
            if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                float f = ((ChipDrawable) (obj)).chipEndPadding + ((ChipDrawable) (obj)).closeIconEndPadding + ((ChipDrawable) (obj)).closeIconSize + ((ChipDrawable) (obj)).closeIconStartPadding + ((ChipDrawable) (obj)).textEndPadding;
                if (DrawableCompat.getLayoutDirection(((Drawable) (obj))) == 0)
                {
                    rectf.right = rect1.right;
                    rectf.left = rectf.right - f;
                } else
                {
                    rectf.left = rect1.left;
                    rectf.right = f + (float)rect1.left;
                }
                rectf.top = rect1.top;
                rectf.bottom = rect1.bottom;
            }
        }
        flag1 = rectF.contains(motionevent.getX(), motionevent.getY());
        if (closeIconHovered != flag1)
        {
            closeIconHovered = flag1;
            refreshDrawableState();
        }
        continue; /* Loop/switch isn't completed */
_L5:
        flag = false;
          goto _L7
_L3:
        if (closeIconHovered)
        {
            closeIconHovered = false;
            refreshDrawableState();
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag2;
        boolean flag3;
        boolean flag4;
        flag3 = false;
        flag4 = false;
        flag2 = false;
        keyevent.getKeyCode();
        JVM INSTR lookupswitch 5: default 64
    //                   21: 79
    //                   22: 116
    //                   23: 167
    //                   61: 213
    //                   66: 167;
           goto _L1 _L2 _L3 _L4 _L5 _L4
_L1:
        boolean flag1 = flag2;
_L7:
        if (flag1)
        {
            invalidate();
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
_L2:
        flag1 = flag2;
        if (keyevent.hasNoModifiers())
        {
            flag1 = flag3;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                flag1 = true;
            }
            flag1 = moveFocus(flag1);
        }
        continue; /* Loop/switch isn't completed */
_L3:
        flag1 = flag2;
        if (keyevent.hasNoModifiers())
        {
            boolean flag;
            if (ViewCompat.getLayoutDirection(this) == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag1 = flag4;
            if (!flag)
            {
                flag1 = true;
            }
            flag1 = moveFocus(flag1);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        switch (focusedVirtualView)
        {
        default:
            flag1 = flag2;
            break;

        case -1: 
            performClick();
            return true;

        case 0: // '\0'
            performCloseIconClick();
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L7; else goto _L6
_L6:
        return true;
_L5:
        byte byte0;
        if (keyevent.hasNoModifiers())
        {
            byte0 = 2;
        } else
        if (keyevent.hasModifiers(1))
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        flag1 = flag2;
        if (byte0 != 0)
        {
            android.view.ViewParent viewparent = getParent();
            Object obj = this;
            View view;
            do
            {
                view = ((View) (obj)).focusSearch(byte0);
                if (view == null || view == this)
                {
                    break;
                }
                obj = view;
            } while (view.getParent() == viewparent);
            flag1 = flag2;
            if (view != null)
            {
                view.requestFocus();
                return true;
            }
        }
        if (true) goto _L7; else goto _L8
_L8:
    }

    public PointerIcon onResolvePointerIcon(MotionEvent motionevent, int i)
    {
        if (getCloseIconTouchBounds().contains(motionevent.getX(), motionevent.getY()) && isEnabled())
        {
            return PointerIcon.getSystemIcon(getContext(), 1002);
        } else
        {
            return null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        int i;
        boolean flag1;
        flag1 = false;
        i = motionevent.getActionMasked();
        rectF.setEmpty();
        if (chipDrawable == null) goto _L2; else goto _L1
_L1:
        boolean flag;
        boolean flag2;
        Object obj = chipDrawable;
        if (((ChipDrawable) (obj)).closeIcon != null)
        {
            Drawable drawable = ((ChipDrawable) (obj)).closeIcon;
            obj = drawable;
            if (drawable instanceof WrappedDrawable)
            {
                obj = ((WrappedDrawable)drawable).getWrappedDrawable();
            }
        } else
        {
            obj = null;
        }
        if (obj == null) goto _L2; else goto _L3
_L3:
        flag = true;
_L9:
        if (flag)
        {
            obj = chipDrawable;
            RectF rectf = rectF;
            Rect rect1 = ((ChipDrawable) (obj)).getBounds();
            rectf.setEmpty();
            if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                float f = ((ChipDrawable) (obj)).chipEndPadding + ((ChipDrawable) (obj)).closeIconEndPadding + ((ChipDrawable) (obj)).closeIconSize + ((ChipDrawable) (obj)).closeIconStartPadding + ((ChipDrawable) (obj)).textEndPadding;
                if (DrawableCompat.getLayoutDirection(((Drawable) (obj))) == 0)
                {
                    rectf.right = rect1.right;
                    rectf.left = rectf.right - f;
                } else
                {
                    rectf.left = rect1.left;
                    rectf.right = f + (float)rect1.left;
                }
                rectf.top = rect1.top;
                rectf.bottom = rect1.bottom;
            }
        }
        flag2 = rectF.contains(motionevent.getX(), motionevent.getY());
        i;
        JVM INSTR tableswitch 0 3: default 244
    //                   0 310
    //                   1 372
    //                   2 338
    //                   3 414;
           goto _L4 _L5 _L6 _L7 _L8
_L4:
        flag = false;
_L11:
        if (flag || super.onTouchEvent(motionevent))
        {
            flag1 = true;
        }
        return flag1;
_L2:
        flag = false;
          goto _L9
_L5:
        if (!flag2) goto _L4; else goto _L10
_L10:
        if (!closeIconPressed)
        {
            closeIconPressed = true;
            refreshDrawableState();
        }
        flag = true;
          goto _L11
_L7:
        if (!closeIconPressed) goto _L4; else goto _L12
_L12:
        if (!flag2 && closeIconPressed)
        {
            closeIconPressed = false;
            refreshDrawableState();
        }
        flag = true;
          goto _L11
_L6:
        if (!closeIconPressed) goto _L8; else goto _L13
_L13:
        performCloseIconClick();
        i = 1;
_L14:
        flag = i;
        if (closeIconPressed)
        {
            closeIconPressed = false;
            refreshDrawableState();
            flag = i;
        }
          goto _L11
_L8:
        i = 0;
          goto _L14
    }

    public final boolean performCloseIconClick()
    {
        playSoundEffect(0);
        boolean flag;
        if (onCloseIconClickListener != null)
        {
            onCloseIconClickListener.onClick(this);
            flag = true;
        } else
        {
            flag = false;
        }
        touchHelper.sendEventForVirtualView(0, 1);
        return flag;
    }

    public void setBackground(Drawable drawable)
    {
        if (drawable != chipDrawable && drawable != ripple)
        {
            throw new UnsupportedOperationException("Do not set the background; Chip manages its own background drawable.");
        } else
        {
            super.setBackground(drawable);
            return;
        }
    }

    public void setBackgroundColor(int i)
    {
        throw new UnsupportedOperationException("Do not set the background color; Chip manages its own background drawable.");
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        if (drawable != chipDrawable && drawable != ripple)
        {
            throw new UnsupportedOperationException("Do not set the background drawable; Chip manages its own background drawable.");
        } else
        {
            super.setBackgroundDrawable(drawable);
            return;
        }
    }

    public void setBackgroundResource(int i)
    {
        throw new UnsupportedOperationException("Do not set the background resource; Chip manages its own background drawable.");
    }

    public void setBackgroundTintList(ColorStateList colorstatelist)
    {
        throw new UnsupportedOperationException("Do not set the background tint list; Chip manages its own background drawable.");
    }

    public void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        throw new UnsupportedOperationException("Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckableResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setCheckable(chipdrawable.context.getResources().getBoolean(i));
        }
    }

    public void setChecked(boolean flag)
    {
        if (chipDrawable == null)
        {
            deferredCheckedValue = flag;
        } else
        if (chipDrawable.checkable)
        {
            isChecked();
            super.setChecked(flag);
            return;
        }
    }

    public void setCheckedIconEnabledResource(int i)
    {
        setCheckedIconVisible(i);
    }

    public void setCheckedIconResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setCheckedIcon(AppCompatResources.getDrawable(chipdrawable.context, i));
        }
    }

    public void setCheckedIconVisible(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setCheckedIconVisible(chipdrawable.context.getResources().getBoolean(i));
        }
    }

    public void setChipBackgroundColorResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(chipdrawable.context, i);
            if (chipdrawable.chipBackgroundColor != colorstatelist)
            {
                chipdrawable.chipBackgroundColor = colorstatelist;
                chipdrawable.onStateChange(chipdrawable.getState());
            }
        }
    }

    public void setChipCornerRadius(float f)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            if (chipdrawable.chipCornerRadius != f)
            {
                chipdrawable.chipCornerRadius = f;
                chipdrawable.invalidateSelf();
            }
        }
    }

    public void setChipCornerRadiusResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            float f = chipdrawable.context.getResources().getDimension(i);
            if (chipdrawable.chipCornerRadius != f)
            {
                chipdrawable.chipCornerRadius = f;
                chipdrawable.invalidateSelf();
            }
        }
    }

    public void setChipEndPadding(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).chipEndPadding != f)
            {
                obj.chipEndPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setChipEndPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).chipEndPadding != f)
            {
                obj.chipEndPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setChipIconEnabledResource(int i)
    {
        setChipIconVisible(i);
    }

    public void setChipIconResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setChipIcon(AppCompatResources.getDrawable(chipdrawable.context, i));
        }
    }

    public void setChipIconSize(float f)
    {
        if (chipDrawable != null)
        {
            chipDrawable.setChipIconSize(f);
        }
    }

    public void setChipIconSizeResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setChipIconSize(chipdrawable.context.getResources().getDimension(i));
        }
    }

    public void setChipIconTintResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setChipIconTint(AppCompatResources.getColorStateList(chipdrawable.context, i));
        }
    }

    public void setChipIconVisible(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setChipIconVisible(chipdrawable.context.getResources().getBoolean(i));
        }
    }

    public void setChipMinHeight(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).chipMinHeight != f)
            {
                obj.chipMinHeight = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setChipMinHeightResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).chipMinHeight != f)
            {
                obj.chipMinHeight = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setChipStartPadding(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).chipStartPadding != f)
            {
                obj.chipStartPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setChipStartPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).chipStartPadding != f)
            {
                obj.chipStartPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setChipStrokeColorResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(chipdrawable.context, i);
            if (chipdrawable.chipStrokeColor != colorstatelist)
            {
                chipdrawable.chipStrokeColor = colorstatelist;
                chipdrawable.onStateChange(chipdrawable.getState());
            }
        }
    }

    public void setChipStrokeWidth(float f)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            if (chipdrawable.chipStrokeWidth != f)
            {
                chipdrawable.chipStrokeWidth = f;
                chipdrawable.chipPaint.setStrokeWidth(f);
                chipdrawable.invalidateSelf();
            }
        }
    }

    public void setChipStrokeWidthResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            float f = chipdrawable.context.getResources().getDimension(i);
            if (chipdrawable.chipStrokeWidth != f)
            {
                chipdrawable.chipStrokeWidth = f;
                chipdrawable.chipPaint.setStrokeWidth(f);
                chipdrawable.invalidateSelf();
            }
        }
    }

    public void setChipTextResource(int i)
    {
        setText(getResources().getString(i));
    }

    public void setCloseIconEnabledResource(int i)
    {
        setCloseIconVisible(i);
    }

    public void setCloseIconEndPadding(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).closeIconEndPadding != f)
            {
                obj.closeIconEndPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                boolean flag;
                if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                    if (obj != null)
                    {
                        ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                    }
                }
            }
        }
    }

    public void setCloseIconEndPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).closeIconEndPadding != f)
            {
                obj.closeIconEndPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                    if (obj != null)
                    {
                        ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                    }
                }
            }
        }
    }

    public void setCloseIconResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setCloseIcon(AppCompatResources.getDrawable(chipdrawable.context, i));
        }
    }

    public void setCloseIconSize(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).closeIconSize != f)
            {
                obj.closeIconSize = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                boolean flag;
                if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                    if (obj != null)
                    {
                        ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                    }
                }
            }
        }
    }

    public void setCloseIconSizeResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).closeIconSize != f)
            {
                obj.closeIconSize = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                    if (obj != null)
                    {
                        ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                    }
                }
            }
        }
    }

    public void setCloseIconStartPadding(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).closeIconStartPadding != f)
            {
                obj.closeIconStartPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                boolean flag;
                if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                    if (obj != null)
                    {
                        ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                    }
                }
            }
        }
    }

    public void setCloseIconStartPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).closeIconStartPadding != f)
            {
                obj.closeIconStartPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                if (((ChipDrawable) (obj)).closeIconVisible && ((ChipDrawable) (obj)).closeIcon != null)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                    if (obj != null)
                    {
                        ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                    }
                }
            }
        }
    }

    public void setCloseIconTintResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setCloseIconTint(AppCompatResources.getColorStateList(chipdrawable.context, i));
        }
    }

    public void setCloseIconVisible(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setCloseIconVisible(chipdrawable.context.getResources().getBoolean(i));
        }
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        if (drawable != null)
        {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable2 != null)
        {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else
        {
            super.setCompoundDrawables(drawable, drawable1, drawable2, drawable3);
            return;
        }
    }

    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        if (drawable != null)
        {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable2 != null)
        {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else
        {
            super.setCompoundDrawablesRelative(drawable, drawable1, drawable2, drawable3);
            return;
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int j, int k, int l)
    {
        if (i != 0)
        {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (k != 0)
        {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else
        {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, j, k, l);
            return;
        }
    }

    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        if (drawable != null)
        {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (drawable2 != null)
        {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else
        {
            super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable1, drawable2, drawable3);
            return;
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(int i, int j, int k, int l)
    {
        if (i != 0)
        {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if (k != 0)
        {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        } else
        {
            super.setCompoundDrawablesWithIntrinsicBounds(i, j, k, l);
            return;
        }
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        if (drawable != null)
        {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if (drawable2 != null)
        {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        } else
        {
            super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable1, drawable2, drawable3);
            return;
        }
    }

    public void setEllipsize(android.text.TextUtils.TruncateAt truncateat)
    {
        if (chipDrawable != null)
        {
            if (truncateat == android.text.TextUtils.TruncateAt.MARQUEE)
            {
                throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
            }
            super.setEllipsize(truncateat);
            if (chipDrawable != null)
            {
                chipDrawable.truncateAt = truncateat;
                return;
            }
        }
    }

    public void setHideMotionSpecResource(int i)
    {
        if (chipDrawable != null)
        {
            MotionSpec.createFromResource(chipDrawable.context, i);
        }
    }

    public void setIconEndPadding(float f)
    {
        if (chipDrawable != null)
        {
            chipDrawable.setIconEndPadding(f);
        }
    }

    public void setIconEndPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setIconEndPadding(chipdrawable.context.getResources().getDimension(i));
        }
    }

    public void setIconStartPadding(float f)
    {
        if (chipDrawable != null)
        {
            chipDrawable.setIconStartPadding(f);
        }
    }

    public void setIconStartPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setIconStartPadding(chipdrawable.context.getResources().getDimension(i));
        }
    }

    public void setLines(int i)
    {
        if (i > 1)
        {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        } else
        {
            super.setLines(i);
            return;
        }
    }

    public void setMaxLines(int i)
    {
        if (i > 1)
        {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        } else
        {
            super.setMaxLines(i);
            return;
        }
    }

    public void setMaxWidth(int i)
    {
        super.setMaxWidth(i);
        if (chipDrawable != null)
        {
            chipDrawable.maxWidth = i;
        }
    }

    public void setMinLines(int i)
    {
        if (i > 1)
        {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        } else
        {
            super.setMinLines(i);
            return;
        }
    }

    public void setRippleColorResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(chipdrawable.context, i);
            if (chipdrawable.rippleColor != colorstatelist)
            {
                chipdrawable.rippleColor = colorstatelist;
                if (chipdrawable.useCompatRipple)
                {
                    colorstatelist = RippleUtils.convertToRippleDrawableColor(chipdrawable.rippleColor);
                } else
                {
                    colorstatelist = null;
                }
                chipdrawable.compatRippleColor = colorstatelist;
                chipdrawable.onStateChange(chipdrawable.getState());
            }
        }
    }

    public void setShowMotionSpecResource(int i)
    {
        if (chipDrawable != null)
        {
            MotionSpec.createFromResource(chipDrawable.context, i);
        }
    }

    public void setSingleLine(boolean flag)
    {
        if (!flag)
        {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        } else
        {
            super.setSingleLine(flag);
            return;
        }
    }

    public void setText(CharSequence charsequence, android.widget.TextView.BufferType buffertype)
    {
        if (chipDrawable != null)
        {
            Object obj = charsequence;
            if (charsequence == null)
            {
                obj = "";
            }
            charsequence = BidiFormatter.getInstance();
            charsequence = charsequence.unicodeWrap(((CharSequence) (obj)), ((BidiFormatter) (charsequence)).mDefaultTextDirectionHeuristicCompat, true);
            if (chipDrawable.shouldDrawText)
            {
                charsequence = null;
            }
            super.setText(charsequence, buffertype);
            if (chipDrawable != null)
            {
                chipDrawable.setText(((CharSequence) (obj)));
                return;
            }
        }
    }

    public void setTextAppearance(int i)
    {
        TextPaint textpaint = null;
        super.setTextAppearance(i);
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setTextAppearance(new TextAppearance(chipdrawable.context, i));
        }
        TextAppearance textappearance;
        if (chipDrawable != null)
        {
            textappearance = chipDrawable.textAppearance;
        } else
        {
            textappearance = null;
        }
        if (textappearance != null)
        {
            if (chipDrawable != null)
            {
                textappearance = chipDrawable.textAppearance;
            } else
            {
                textappearance = null;
            }
            textappearance.updateMeasureState(getContext(), getPaint(), fontCallback);
            textappearance = textpaint;
            if (chipDrawable != null)
            {
                textappearance = chipDrawable.textAppearance;
            }
            textpaint = getPaint();
            textpaint.drawableState = chipDrawable.getState();
            textappearance.updateDrawState(getContext(), textpaint, fontCallback);
        }
    }

    public void setTextAppearance(Context context, int i)
    {
        Object obj1 = null;
        super.setTextAppearance(context, i);
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setTextAppearance(new TextAppearance(chipdrawable.context, i));
        }
        Object obj;
        if (chipDrawable != null)
        {
            obj = chipDrawable.textAppearance;
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            if (chipDrawable != null)
            {
                obj = chipDrawable.textAppearance;
            } else
            {
                obj = null;
            }
            ((TextAppearance) (obj)).updateMeasureState(context, getPaint(), fontCallback);
            context = obj1;
            if (chipDrawable != null)
            {
                context = chipDrawable.textAppearance;
            }
            obj = getPaint();
            obj.drawableState = chipDrawable.getState();
            context.updateDrawState(getContext(), ((TextPaint) (obj)), fontCallback);
        }
    }

    public void setTextAppearanceResource(int i)
    {
        if (chipDrawable != null)
        {
            ChipDrawable chipdrawable = chipDrawable;
            chipdrawable.setTextAppearance(new TextAppearance(chipdrawable.context, i));
        }
        setTextAppearance(getContext(), i);
    }

    public void setTextEndPadding(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).textEndPadding != f)
            {
                obj.textEndPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setTextEndPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).textEndPadding != f)
            {
                obj.textEndPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setTextStartPadding(float f)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            if (((ChipDrawable) (obj)).textStartPadding != f)
            {
                obj.textStartPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }

    public void setTextStartPaddingResource(int i)
    {
        if (chipDrawable != null)
        {
            Object obj = chipDrawable;
            float f = ((ChipDrawable) (obj)).context.getResources().getDimension(i);
            if (((ChipDrawable) (obj)).textStartPadding != f)
            {
                obj.textStartPadding = f;
                ((ChipDrawable) (obj)).invalidateSelf();
                obj = (ChipDrawable.Delegate)((ChipDrawable) (obj))._flddelegate.get();
                if (obj != null)
                {
                    ((ChipDrawable.Delegate) (obj)).onChipDrawableSizeChange();
                }
            }
        }
    }


    private class _cls1 extends android.support.v4.content.res.ResourcesCompat.FontCallback
    {

        private final Chip this$0;

        public final void onFontRetrievalFailed(int i)
        {
        }

        public final void onFontRetrieved(Typeface typeface)
        {
            setText(getText());
            requestLayout();
            invalidate();
        }

        _cls1()
        {
            this$0 = Chip.this;
            super();
        }
    }


    private class _cls2 extends ViewOutlineProvider
    {

        private final Chip this$0;

        public final void getOutline(View view, Outline outline)
        {
            if (chipDrawable != null)
            {
                chipDrawable.getOutline(outline);
                return;
            } else
            {
                outline.setAlpha(0.0F);
                return;
            }
        }

        _cls2()
        {
            this$0 = Chip.this;
            super();
        }
    }

}
