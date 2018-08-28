// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.animation.MotionSpec;
import android.support.design.appbar.AppBarLayout;
import android.support.design.bottomsheet.BottomSheetBehavior;
import android.support.design.expandable.ExpandableWidgetHelper;
import android.support.design.internal.CircularBorderDrawable;
import android.support.design.internal.DescendantOffsetUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.design.internal.VisibilityAwareImageButton;
import android.support.design.resources.MaterialResources;
import android.support.design.shadow.ShadowViewDelegate;
import android.support.design.stateful.ExtendableSavedState;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.List;

// Referenced classes of package android.support.design.floatingactionbutton:
//            FloatingActionButtonImplLollipop, FloatingActionButtonImpl

public class FloatingActionButton extends VisibilityAwareImageButton
{
    public static class BaseBehavior extends android.support.design.widget.CoordinatorLayout.Behavior
    {

        private boolean autoHideEnabled;
        private Rect tmpRect;

        private final boolean shouldUpdateVisibility(View view, FloatingActionButton floatingactionbutton)
        {
            android.support.design.widget.CoordinatorLayout.LayoutParams layoutparams = (android.support.design.widget.CoordinatorLayout.LayoutParams)floatingactionbutton.getLayoutParams();
            if (!autoHideEnabled)
            {
                return false;
            }
            if (layoutparams.mAnchorId != view.getId())
            {
                return false;
            }
            return ((VisibilityAwareImageButton) (floatingactionbutton)).userSetVisibility == 0;
        }

        private final boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorlayout, AppBarLayout appbarlayout, FloatingActionButton floatingactionbutton)
        {
            if (!shouldUpdateVisibility(appbarlayout, floatingactionbutton))
            {
                return false;
            }
            if (tmpRect == null)
            {
                tmpRect = new Rect();
            }
            floatingactionbutton = tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorlayout, appbarlayout, floatingactionbutton);
            int i = ((Rect) (floatingactionbutton)).bottom;
            throw new NoSuchMethodError();
        }

        private final boolean updateFabVisibilityForBottomSheet(View view, FloatingActionButton floatingactionbutton)
        {
            if (!shouldUpdateVisibility(view, floatingactionbutton))
            {
                return false;
            }
            android.support.design.widget.CoordinatorLayout.LayoutParams layoutparams = (android.support.design.widget.CoordinatorLayout.LayoutParams)floatingactionbutton.getLayoutParams();
            int i = view.getTop();
            int j = floatingactionbutton.getHeight() / 2;
            if (i < layoutparams.topMargin + j)
            {
                floatingactionbutton.hide(null, false);
            } else
            {
                floatingactionbutton.show(null, false);
            }
            return true;
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, Rect rect)
        {
            coordinatorlayout = floatingactionbutton.shadowPadding;
            rect.set(floatingactionbutton.getLeft() + ((Rect) (coordinatorlayout)).left, floatingactionbutton.getTop() + ((Rect) (coordinatorlayout)).top, floatingactionbutton.getRight() - ((Rect) (coordinatorlayout)).right, floatingactionbutton.getBottom() - ((Rect) (coordinatorlayout)).bottom);
            return true;
        }

        public final volatile boolean getInsetDodgeRect(CoordinatorLayout coordinatorlayout, View view, Rect rect)
        {
            return getInsetDodgeRect(coordinatorlayout, (FloatingActionButton)view, rect);
        }

        public void onAttachedToLayoutParams(android.support.design.widget.CoordinatorLayout.LayoutParams layoutparams)
        {
            if (layoutparams.dodgeInsetEdges == 0)
            {
                layoutparams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, View view)
        {
            if (view instanceof AppBarLayout)
            {
                updateFabVisibilityForAppBarLayout(coordinatorlayout, (AppBarLayout)view, floatingactionbutton);
            } else
            {
                coordinatorlayout = view.getLayoutParams();
                boolean flag;
                if (coordinatorlayout instanceof android.support.design.widget.CoordinatorLayout.LayoutParams)
                {
                    flag = ((android.support.design.widget.CoordinatorLayout.LayoutParams)coordinatorlayout).mBehavior instanceof BottomSheetBehavior;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    updateFabVisibilityForBottomSheet(view, floatingactionbutton);
                    return false;
                }
            }
            return false;
        }

        public final volatile boolean onDependentViewChanged(CoordinatorLayout coordinatorlayout, View view, View view1)
        {
            return onDependentViewChanged(coordinatorlayout, (FloatingActionButton)view, view1);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorlayout, FloatingActionButton floatingactionbutton, int i)
        {
            List list;
            int j;
            boolean flag;
            int k;
            flag = false;
            list = coordinatorlayout.getDependencies(floatingactionbutton);
            k = list.size();
            j = 0;
_L7:
            if (j >= k) goto _L2; else goto _L1
_L1:
            Object obj = (View)list.get(j);
            if (!(obj instanceof AppBarLayout)) goto _L4; else goto _L3
_L3:
            if (!updateFabVisibilityForAppBarLayout(coordinatorlayout, (AppBarLayout)obj, floatingactionbutton)) goto _L5; else goto _L2
_L2:
            coordinatorlayout.onLayoutChild(floatingactionbutton, i);
            Rect rect = floatingactionbutton.shadowPadding;
            if (rect != null && rect.centerX() > 0 && rect.centerY() > 0)
            {
                obj = (android.support.design.widget.CoordinatorLayout.LayoutParams)floatingactionbutton.getLayoutParams();
                android.view.ViewGroup.LayoutParams layoutparams;
                boolean flag1;
                if (floatingactionbutton.getRight() >= coordinatorlayout.getWidth() - ((android.support.design.widget.CoordinatorLayout.LayoutParams) (obj)).rightMargin)
                {
                    i = rect.right;
                } else
                if (floatingactionbutton.getLeft() <= ((android.support.design.widget.CoordinatorLayout.LayoutParams) (obj)).leftMargin)
                {
                    i = -rect.left;
                } else
                {
                    i = 0;
                }
                if (floatingactionbutton.getBottom() >= coordinatorlayout.getHeight() - ((android.support.design.widget.CoordinatorLayout.LayoutParams) (obj)).bottomMargin)
                {
                    j = rect.bottom;
                } else
                {
                    j = ((flag) ? 1 : 0);
                    if (floatingactionbutton.getTop() <= ((android.support.design.widget.CoordinatorLayout.LayoutParams) (obj)).topMargin)
                    {
                        j = -rect.top;
                    }
                }
                if (j != 0)
                {
                    ViewCompat.offsetTopAndBottom(floatingactionbutton, j);
                }
                if (i != 0)
                {
                    ViewCompat.offsetLeftAndRight(floatingactionbutton, i);
                }
            }
            return true;
_L4:
            layoutparams = ((View) (obj)).getLayoutParams();
            if (layoutparams instanceof android.support.design.widget.CoordinatorLayout.LayoutParams)
            {
                flag1 = ((android.support.design.widget.CoordinatorLayout.LayoutParams)layoutparams).mBehavior instanceof BottomSheetBehavior;
            } else
            {
                flag1 = false;
            }
            if (flag1 && updateFabVisibilityForBottomSheet(((View) (obj)), floatingactionbutton)) goto _L2; else goto _L5
_L5:
            j++;
            if (true) goto _L7; else goto _L6
_L6:
        }

        public volatile boolean onLayoutChild(CoordinatorLayout coordinatorlayout, View view, int i)
        {
            return onLayoutChild(coordinatorlayout, (FloatingActionButton)view, i);
        }

        public BaseBehavior()
        {
            autoHideEnabled = true;
        }

        public BaseBehavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
            context = context.obtainStyledAttributes(attributeset, R.styleable.FloatingActionButton_Behavior_Layout);
            autoHideEnabled = context.getBoolean(R.styleable.FloatingActionButton_Behavior_Layout_behavior_autoHide, true);
            context.recycle();
        }
    }

    public static class Behavior extends BaseBehavior
    {

        public Behavior()
        {
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }

    public static class OnVisibilityChangedListener
    {
    }

    final class ShadowDelegateImpl
        implements ShadowViewDelegate
    {

        private final FloatingActionButton this$0;

        public final float getRadius()
        {
            FloatingActionButton floatingactionbutton = FloatingActionButton.this;
            return (float)floatingactionbutton.getSizeDimension(floatingactionbutton.size) / 2.0F;
        }

        public final boolean isCompatPaddingEnabled()
        {
            return compatPadding;
        }

        public final void setBackgroundDrawable(Drawable drawable)
        {
            VisibilityAwareImageButton.this.setBackgroundDrawable(drawable);
        }

        public final void setShadowPadding(int i, int j, int k, int l)
        {
            shadowPadding.set(i, j, k, l);
            setPadding(imagePadding + i, imagePadding + j, imagePadding + k, imagePadding + l);
        }

        ShadowDelegateImpl()
        {
            this$0 = FloatingActionButton.this;
            super();
        }
    }


    private ColorStateList backgroundTint;
    private android.graphics.PorterDuff.Mode backgroundTintMode;
    private int borderWidth;
    public boolean compatPadding;
    private int customSize;
    private final ExpandableWidgetHelper expandableWidgetHelper;
    private final AppCompatImageHelper imageHelper;
    public int imagePadding;
    private FloatingActionButtonImpl impl;
    private int maxImageSize;
    private ColorStateList rippleColor;
    public final Rect shadowPadding;
    public int size;
    private final Rect touchArea;

    public FloatingActionButton(Context context)
    {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f01000b);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset, int i)
    {
        float f;
        float f2;
        float f3;
        Object obj;
        TypedArray typedarray;
        int j;
        obj = null;
        super(context, attributeset, i);
        shadowPadding = new Rect();
        touchArea = new Rect();
        typedarray = ThemeEnforcement.obtainStyledAttributes(context, attributeset, R.styleable.FloatingActionButton, i, 0x7f140360, new int[0]);
        backgroundTint = MaterialResources.getColorStateList(context, typedarray, R.styleable.FloatingActionButton_backgroundTint);
        backgroundTintMode = ViewUtils.parseTintMode(typedarray.getInt(R.styleable.FloatingActionButton_backgroundTintMode, -1), null);
        rippleColor = MaterialResources.getColorStateList(context, typedarray, R.styleable.FloatingActionButton_rippleColor);
        size = typedarray.getInt(R.styleable.FloatingActionButton_fabSize, -1);
        customSize = typedarray.getDimensionPixelSize(R.styleable.FloatingActionButton_fabCustomSize, 0);
        borderWidth = typedarray.getDimensionPixelSize(R.styleable.FloatingActionButton_borderWidth, 0);
        f = typedarray.getDimension(R.styleable.FloatingActionButton_elevation, 0.0F);
        f2 = typedarray.getDimension(R.styleable.FloatingActionButton_hoveredFocusedTranslationZ, 0.0F);
        f3 = typedarray.getDimension(R.styleable.FloatingActionButton_pressedTranslationZ, 0.0F);
        compatPadding = typedarray.getBoolean(R.styleable.FloatingActionButton_useCompatPadding, false);
        maxImageSize = typedarray.getDimensionPixelSize(R.styleable.FloatingActionButton_maxImageSize, 0);
        j = R.styleable.FloatingActionButton_showMotionSpec;
        if (!typedarray.hasValue(j)) goto _L2; else goto _L1
_L1:
        j = typedarray.getResourceId(j, 0);
        if (j == 0) goto _L2; else goto _L3
_L3:
        MotionSpec motionspec = MotionSpec.createFromResource(context, j);
_L5:
        int k = R.styleable.FloatingActionButton_hideMotionSpec;
        MotionSpec motionspec1 = obj;
        if (typedarray.hasValue(k))
        {
            k = typedarray.getResourceId(k, 0);
            motionspec1 = obj;
            if (k != 0)
            {
                motionspec1 = MotionSpec.createFromResource(context, k);
            }
        }
        typedarray.recycle();
        imageHelper = new AppCompatImageHelper(this);
        imageHelper.loadFromAttributes(attributeset, i);
        expandableWidgetHelper = new ExpandableWidgetHelper(this);
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.setBackgroundDrawable(backgroundTint, backgroundTintMode, rippleColor, borderWidth);
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        context = impl;
        if (((FloatingActionButtonImpl) (context)).elevation != f)
        {
            context.elevation = f;
            context.onElevationsChanged(((FloatingActionButtonImpl) (context)).elevation, ((FloatingActionButtonImpl) (context)).hoveredFocusedTranslationZ, ((FloatingActionButtonImpl) (context)).pressedTranslationZ);
        }
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        context = impl;
        if (((FloatingActionButtonImpl) (context)).hoveredFocusedTranslationZ != f2)
        {
            context.hoveredFocusedTranslationZ = f2;
            context.onElevationsChanged(((FloatingActionButtonImpl) (context)).elevation, ((FloatingActionButtonImpl) (context)).hoveredFocusedTranslationZ, ((FloatingActionButtonImpl) (context)).pressedTranslationZ);
        }
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        context = impl;
        if (((FloatingActionButtonImpl) (context)).pressedTranslationZ != f3)
        {
            context.pressedTranslationZ = f3;
            context.onElevationsChanged(((FloatingActionButtonImpl) (context)).elevation, ((FloatingActionButtonImpl) (context)).hoveredFocusedTranslationZ, ((FloatingActionButtonImpl) (context)).pressedTranslationZ);
        }
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        context = impl;
        i = maxImageSize;
        if (((FloatingActionButtonImpl) (context)).maxImageSize != i)
        {
            context.maxImageSize = i;
            float f1 = ((FloatingActionButtonImpl) (context)).imageMatrixScale;
            context.imageMatrixScale = f1;
            attributeset = ((FloatingActionButtonImpl) (context)).tmpMatrix;
            context.calculateImageMatrixFromScale(f1, attributeset);
            ((FloatingActionButtonImpl) (context)).view.setImageMatrix(attributeset);
        }
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.showMotionSpec = motionspec;
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.hideMotionSpec = motionspec1;
        setScaleType(android.widget.ImageView.ScaleType.MATRIX);
        return;
_L2:
        motionspec = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    private static int resolveAdjustedSize(int i, int j)
    {
        int k = android.view.View.MeasureSpec.getMode(j);
        int l = android.view.View.MeasureSpec.getSize(j);
        j = i;
        switch (k)
        {
        default:
            throw new IllegalArgumentException();

        case -2147483648: 
            j = Math.min(i, l);
            // fall through

        case 0: // '\0'
            return j;

        case 1073741824: 
            return l;
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.onDrawableStateChanged(getDrawableState());
    }

    public ColorStateList getBackgroundTintList()
    {
        return backgroundTint;
    }

    public android.graphics.PorterDuff.Mode getBackgroundTintMode()
    {
        return backgroundTintMode;
    }

    final int getSizeDimension(int i)
    {
        do
        {
            if (customSize != 0)
            {
                return customSize;
            }
            Resources resources = getResources();
            switch (i)
            {
            case 0: // '\0'
            default:
                return resources.getDimensionPixelSize(0x7f0e00f7);

            case -1: 
                if (Math.max(resources.getConfiguration().screenWidthDp, resources.getConfiguration().screenHeightDp) < 470)
                {
                    return getSizeDimension(1);
                }
                i = 0;
                break;

            case 1: // '\001'
                return resources.getDimensionPixelSize(0x7f0e00f6);
            }
        } while (true);
    }

    final void hide(final OnVisibilityChangedListener listener, boolean flag)
    {
        boolean flag2 = true;
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        boolean flag1;
        if (listener == null)
        {
            listener = null;
        } else
        {
            listener = new _cls1();
        }
        if (floatingactionbuttonimpl.view.getVisibility() == 0)
        {
            if (floatingactionbuttonimpl.animState == 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        } else
        if (floatingactionbuttonimpl.animState != 2)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            if (floatingactionbuttonimpl.currentAnimator != null)
            {
                floatingactionbuttonimpl.currentAnimator.cancel();
            }
            if (ViewCompat.isLaidOut(floatingactionbuttonimpl.view) && !floatingactionbuttonimpl.view.isInEditMode())
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                Object obj;
                if (floatingactionbuttonimpl.hideMotionSpec != null)
                {
                    obj = floatingactionbuttonimpl.hideMotionSpec;
                } else
                {
                    if (floatingactionbuttonimpl.defaultHideMotionSpec == null)
                    {
                        floatingactionbuttonimpl.defaultHideMotionSpec = MotionSpec.createFromResource(floatingactionbuttonimpl.view.getContext(), 0x7f070001);
                    }
                    obj = floatingactionbuttonimpl.defaultHideMotionSpec;
                }
                obj = floatingactionbuttonimpl.createAnimator(((MotionSpec) (obj)), 0.0F, 0.0F, 0.0F);
                ((AnimatorSet) (obj)).addListener(new FloatingActionButtonImpl._cls1(floatingactionbuttonimpl, false, listener));
                ((AnimatorSet) (obj)).start();
            } else
            {
                floatingactionbuttonimpl.view.internalSetVisibility(4, false);
                if (listener != null)
                {
                    listener.onHidden();
                    return;
                }
            }
        }
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.jumpDrawableToCurrentState();
    }

    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        if (floatingactionbuttonimpl.requirePreDrawListener())
        {
            if (floatingactionbuttonimpl.preDrawListener == null)
            {
                floatingactionbuttonimpl.preDrawListener = new FloatingActionButtonImpl._cls3(floatingactionbuttonimpl);
            }
            floatingactionbuttonimpl.view.getViewTreeObserver().addOnPreDrawListener(floatingactionbuttonimpl.preDrawListener);
        }
    }

    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        if (floatingactionbuttonimpl.preDrawListener != null)
        {
            floatingactionbuttonimpl.view.getViewTreeObserver().removeOnPreDrawListener(floatingactionbuttonimpl.preDrawListener);
            floatingactionbuttonimpl.preDrawListener = null;
        }
    }

    protected void onMeasure(int i, int j)
    {
        int k = getSizeDimension(size);
        imagePadding = (k - maxImageSize) / 2;
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.updatePadding();
        i = Math.min(resolveAdjustedSize(k, i), resolveAdjustedSize(k, j));
        setMeasuredDimension(shadowPadding.left + i + shadowPadding.right, i + shadowPadding.top + shadowPadding.bottom);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if (!(parcelable instanceof ExtendableSavedState))
        {
            super.onRestoreInstanceState(parcelable);
        } else
        {
            Object obj = (ExtendableSavedState)parcelable;
            super.onRestoreInstanceState(((AbsSavedState) (obj)).mSuperState);
            parcelable = expandableWidgetHelper;
            obj = (Bundle)((ExtendableSavedState) (obj)).extendableStates.get("expandableWidgetHelper");
            parcelable.expanded = ((Bundle) (obj)).getBoolean("expanded", false);
            parcelable.expandedComponentIdHint = ((Bundle) (obj)).getInt("expandedComponentIdHint", 0);
            if (((ExpandableWidgetHelper) (parcelable)).expanded)
            {
                android.view.ViewParent viewparent = ((ExpandableWidgetHelper) (parcelable)).widget.getParent();
                if (viewparent instanceof CoordinatorLayout)
                {
                    ((CoordinatorLayout)viewparent).dispatchDependentViewsChanged(((ExpandableWidgetHelper) (parcelable)).widget);
                    return;
                }
            }
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        ExtendableSavedState extendablesavedstate = new ExtendableSavedState(super.onSaveInstanceState());
        SimpleArrayMap simplearraymap = extendablesavedstate.extendableStates;
        ExpandableWidgetHelper expandablewidgethelper = expandableWidgetHelper;
        Bundle bundle = new Bundle();
        bundle.putBoolean("expanded", expandablewidgethelper.expanded);
        bundle.putInt("expandedComponentIdHint", expandablewidgethelper.expandedComponentIdHint);
        simplearraymap.put("expandableWidgetHelper", bundle);
        return extendablesavedstate;
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if (motionevent.getAction() == 0)
        {
            Rect rect = touchArea;
            boolean flag;
            if (ViewCompat.isLaidOut(this))
            {
                rect.set(0, 0, getWidth(), getHeight());
                rect.left = rect.left + shadowPadding.left;
                rect.top = rect.top + shadowPadding.top;
                rect.right = rect.right - shadowPadding.right;
                rect.bottom = rect.bottom - shadowPadding.bottom;
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && !touchArea.contains((int)motionevent.getX(), (int)motionevent.getY()))
            {
                return false;
            }
        }
        return super.onTouchEvent(motionevent);
    }

    public void setBackgroundColor(int i)
    {
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
    }

    public void setBackgroundResource(int i)
    {
    }

    public void setBackgroundTintList(ColorStateList colorstatelist)
    {
        if (backgroundTint != colorstatelist)
        {
            backgroundTint = colorstatelist;
            if (impl == null)
            {
                impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
            }
            FloatingActionButtonImpl floatingactionbuttonimpl = impl;
            if (floatingactionbuttonimpl.shapeDrawable != null)
            {
                floatingactionbuttonimpl.shapeDrawable.setTintList(colorstatelist);
            }
            if (floatingactionbuttonimpl.borderDrawable != null)
            {
                floatingactionbuttonimpl.borderDrawable.setBorderTint(colorstatelist);
            }
        }
    }

    public void setBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if (backgroundTintMode != mode)
        {
            backgroundTintMode = mode;
            if (impl == null)
            {
                impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
            }
            FloatingActionButtonImpl floatingactionbuttonimpl = impl;
            if (floatingactionbuttonimpl.shapeDrawable != null)
            {
                floatingactionbuttonimpl.shapeDrawable.setTintMode(mode);
            }
        }
    }

    public void setCompatElevation(float f)
    {
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        if (floatingactionbuttonimpl.elevation != f)
        {
            floatingactionbuttonimpl.elevation = f;
            floatingactionbuttonimpl.onElevationsChanged(floatingactionbuttonimpl.elevation, floatingactionbuttonimpl.hoveredFocusedTranslationZ, floatingactionbuttonimpl.pressedTranslationZ);
        }
    }

    public void setCompatElevationResource(int i)
    {
        setCompatElevation(getResources().getDimension(i));
    }

    public void setCompatHoveredFocusedTranslationZ(float f)
    {
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        if (floatingactionbuttonimpl.hoveredFocusedTranslationZ != f)
        {
            floatingactionbuttonimpl.hoveredFocusedTranslationZ = f;
            floatingactionbuttonimpl.onElevationsChanged(floatingactionbuttonimpl.elevation, floatingactionbuttonimpl.hoveredFocusedTranslationZ, floatingactionbuttonimpl.pressedTranslationZ);
        }
    }

    public void setCompatHoveredFocusedTranslationZResource(int i)
    {
        setCompatHoveredFocusedTranslationZ(getResources().getDimension(i));
    }

    public void setCompatPressedTranslationZ(float f)
    {
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        if (floatingactionbuttonimpl.pressedTranslationZ != f)
        {
            floatingactionbuttonimpl.pressedTranslationZ = f;
            floatingactionbuttonimpl.onElevationsChanged(floatingactionbuttonimpl.elevation, floatingactionbuttonimpl.hoveredFocusedTranslationZ, floatingactionbuttonimpl.pressedTranslationZ);
        }
    }

    public void setCompatPressedTranslationZResource(int i)
    {
        setCompatPressedTranslationZ(getResources().getDimension(i));
    }

    public void setCustomSize(int i)
    {
        if (i < 0)
        {
            throw new IllegalArgumentException("Custom size must be non-negative");
        } else
        {
            customSize = i;
            return;
        }
    }

    public void setExpandedComponentIdHint(int i)
    {
        expandableWidgetHelper.expandedComponentIdHint = i;
    }

    public void setHideMotionSpecResource(int i)
    {
        MotionSpec motionspec = MotionSpec.createFromResource(getContext(), i);
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.hideMotionSpec = motionspec;
    }

    public void setImageDrawable(Drawable drawable)
    {
        super.setImageDrawable(drawable);
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        drawable = impl;
        float f = ((FloatingActionButtonImpl) (drawable)).imageMatrixScale;
        drawable.imageMatrixScale = f;
        android.graphics.Matrix matrix = ((FloatingActionButtonImpl) (drawable)).tmpMatrix;
        drawable.calculateImageMatrixFromScale(f, matrix);
        ((FloatingActionButtonImpl) (drawable)).view.setImageMatrix(matrix);
    }

    public void setImageResource(int i)
    {
        imageHelper.setImageResource(i);
    }

    public void setRippleColor(int i)
    {
        ColorStateList colorstatelist = ColorStateList.valueOf(i);
        if (rippleColor != colorstatelist)
        {
            rippleColor = colorstatelist;
            if (impl == null)
            {
                impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
            }
            impl.setRippleColor(rippleColor);
        }
    }

    public void setShowMotionSpecResource(int i)
    {
        MotionSpec motionspec = MotionSpec.createFromResource(getContext(), i);
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        impl.showMotionSpec = motionspec;
    }

    public void setSize(int i)
    {
        customSize = 0;
        if (i != size)
        {
            size = i;
            requestLayout();
        }
    }

    final void show(final OnVisibilityChangedListener listener, boolean flag)
    {
        boolean flag2 = true;
        if (impl == null)
        {
            impl = new FloatingActionButtonImplLollipop(this, new ShadowDelegateImpl());
        }
        FloatingActionButtonImpl floatingactionbuttonimpl = impl;
        boolean flag1;
        if (listener == null)
        {
            listener = null;
        } else
        {
            listener = new _cls1();
        }
        if (floatingactionbuttonimpl.view.getVisibility() != 0)
        {
            if (floatingactionbuttonimpl.animState == 2)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
        } else
        if (floatingactionbuttonimpl.animState != 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            if (floatingactionbuttonimpl.currentAnimator != null)
            {
                floatingactionbuttonimpl.currentAnimator.cancel();
            }
            if (ViewCompat.isLaidOut(floatingactionbuttonimpl.view) && !floatingactionbuttonimpl.view.isInEditMode())
            {
                flag1 = flag2;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                if (floatingactionbuttonimpl.view.getVisibility() != 0)
                {
                    floatingactionbuttonimpl.view.setAlpha(0.0F);
                    floatingactionbuttonimpl.view.setScaleY(0.0F);
                    floatingactionbuttonimpl.view.setScaleX(0.0F);
                    floatingactionbuttonimpl.imageMatrixScale = 0.0F;
                    android.graphics.Matrix matrix = floatingactionbuttonimpl.tmpMatrix;
                    floatingactionbuttonimpl.calculateImageMatrixFromScale(0.0F, matrix);
                    floatingactionbuttonimpl.view.setImageMatrix(matrix);
                }
                Object obj;
                if (floatingactionbuttonimpl.showMotionSpec != null)
                {
                    obj = floatingactionbuttonimpl.showMotionSpec;
                } else
                {
                    if (floatingactionbuttonimpl.defaultShowMotionSpec == null)
                    {
                        floatingactionbuttonimpl.defaultShowMotionSpec = MotionSpec.createFromResource(floatingactionbuttonimpl.view.getContext(), 0x7f070002);
                    }
                    obj = floatingactionbuttonimpl.defaultShowMotionSpec;
                }
                obj = floatingactionbuttonimpl.createAnimator(((MotionSpec) (obj)), 1.0F, 1.0F, 1.0F);
                ((AnimatorSet) (obj)).addListener(new FloatingActionButtonImpl._cls2(floatingactionbuttonimpl, false, listener));
                ((AnimatorSet) (obj)).start();
            } else
            {
                floatingactionbuttonimpl.view.internalSetVisibility(0, false);
                floatingactionbuttonimpl.view.setAlpha(1.0F);
                floatingactionbuttonimpl.view.setScaleY(1.0F);
                floatingactionbuttonimpl.view.setScaleX(1.0F);
                floatingactionbuttonimpl.imageMatrixScale = 1.0F;
                android.graphics.Matrix matrix1 = floatingactionbuttonimpl.tmpMatrix;
                floatingactionbuttonimpl.calculateImageMatrixFromScale(1.0F, matrix1);
                floatingactionbuttonimpl.view.setImageMatrix(matrix1);
                if (listener != null)
                {
                    listener.onShown();
                    return;
                }
            }
        }
    }


    private class _cls1
        implements FloatingActionButtonImpl.InternalVisibilityChangedListener
    {

        private final FloatingActionButton this$0;
        private final OnVisibilityChangedListener val$listener;

        public final void onHidden()
        {
            throw new NoSuchMethodError();
        }

        public final void onShown()
        {
            throw new NoSuchMethodError();
        }

        _cls1()
        {
            this$0 = FloatingActionButton.this;
            listener = onvisibilitychangedlistener;
            super();
        }
    }

}
