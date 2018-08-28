// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.textfield;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.animation.AnimationUtils;
import android.support.design.internal.CheckableImageButton;
import android.support.design.internal.CollapsingTextHelper;
import android.support.design.internal.DescendantOffsetUtils;
import android.support.design.internal.ThemeEnforcement;
import android.support.design.internal.ViewUtils;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.TintTypedArray;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

// Referenced classes of package android.support.design.textfield:
//            IndicatorViewController, CutoutDrawable

public class TextInputLayout extends LinearLayout
{
    public static final class AccessibilityDelegate extends AccessibilityDelegateCompat
    {

        private final TextInputLayout layout;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            Object obj = null;
            boolean flag4 = false;
            super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            view = layout.editText;
            CharSequence charsequence;
            Editable editable;
            CharSequence charsequence1;
            TextInputLayout textinputlayout;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            if (view != null)
            {
                editable = view.getText();
            } else
            {
                editable = null;
            }
            view = layout;
            if (((TextInputLayout) (view)).hintEnabled)
            {
                charsequence1 = ((TextInputLayout) (view)).hint;
            } else
            {
                charsequence1 = null;
            }
            view = layout;
            if (((TextInputLayout) (view)).indicatorViewController.errorEnabled)
            {
                view = ((TextInputLayout) (view)).indicatorViewController.errorText;
            } else
            {
                view = null;
            }
            textinputlayout = layout;
            charsequence = obj;
            if (textinputlayout.counterEnabled)
            {
                charsequence = obj;
                if (textinputlayout.counterOverflowed)
                {
                    charsequence = obj;
                    if (textinputlayout.counterView != null)
                    {
                        charsequence = textinputlayout.counterView.getContentDescription();
                    }
                }
            }
            if (!TextUtils.isEmpty(editable))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!TextUtils.isEmpty(charsequence1))
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (!TextUtils.isEmpty(view))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 || !TextUtils.isEmpty(charsequence))
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            if (flag)
            {
                accessibilitynodeinfocompat.mInfo.setText(editable);
            } else
            if (flag2)
            {
                accessibilitynodeinfocompat.mInfo.setText(charsequence1);
            }
            if (flag2)
            {
                boolean flag5;
                if (android.os.Build.VERSION.SDK_INT >= 26)
                {
                    accessibilitynodeinfocompat.mInfo.setHintText(charsequence1);
                } else
                {
                    accessibilitynodeinfocompat.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charsequence1);
                }
                if (!flag && flag2)
                {
                    flag5 = true;
                } else
                {
                    flag5 = false;
                }
                if (android.os.Build.VERSION.SDK_INT >= 26)
                {
                    accessibilitynodeinfocompat.mInfo.setShowingHintText(flag5);
                } else
                {
                    Bundle bundle = accessibilitynodeinfocompat.mInfo.getExtras();
                    if (bundle != null)
                    {
                        int i = bundle.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0);
                        byte byte0 = flag4;
                        if (flag5)
                        {
                            byte0 = 4;
                        }
                        bundle.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i & -5 | byte0);
                    }
                }
            }
            if (flag3)
            {
                if (!flag1)
                {
                    view = charsequence;
                }
                accessibilitynodeinfocompat.mInfo.setError(view);
                accessibilitynodeinfocompat.mInfo.setContentInvalid(true);
            }
        }

        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            Object obj = null;
            super.onPopulateAccessibilityEvent(view, accessibilityevent);
            view = layout.editText;
            if (view != null)
            {
                view = view.getText();
            } else
            {
                view = null;
            }
            if (TextUtils.isEmpty(view))
            {
                TextInputLayout textinputlayout = layout;
                view = obj;
                if (textinputlayout.hintEnabled)
                {
                    view = textinputlayout.hint;
                }
            }
            if (!TextUtils.isEmpty(view))
            {
                accessibilityevent.getText().add(view);
            }
        }

        public AccessibilityDelegate(TextInputLayout textinputlayout)
        {
            layout = textinputlayout;
        }
    }

    static final class SavedState extends AbsSavedState
    {

        public static final android.os.Parcelable.Creator CREATOR = new _cls1();
        public CharSequence error;
        public boolean isPasswordToggledVisible;

        public final String toString()
        {
            String s = Integer.toHexString(System.identityHashCode(this));
            String s1 = String.valueOf(error);
            return (new StringBuilder(String.valueOf(s).length() + 35 + String.valueOf(s1).length())).append("TextInputLayout.SavedState{").append(s).append(" error=").append(s1).append("}").toString();
        }

        public final void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(error, parcel, i);
            if (isPasswordToggledVisible)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            parcel.writeInt(i);
        }


        SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            error = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            boolean flag;
            if (parcel.readInt() == 1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            isPasswordToggledVisible = flag;
        }

        SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }

        class _cls1
            implements android.os.Parcelable.ClassLoaderCreator
        {

            public final Object createFromParcel(Parcel parcel)
            {
                return new SavedState(parcel, null);
            }

            public final Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public final Object[] newArray(int i)
            {
                return new SavedState[i];
            }

                _cls1()
                {
                }
        }

    }


    private ValueAnimator animator;
    private GradientDrawable boxBackground;
    private int boxBackgroundColor;
    private int boxBackgroundMode;
    private final int boxBottomOffsetPx;
    private final int boxCollapsedPaddingTopPx;
    private float boxCornerRadiusBottomEnd;
    private float boxCornerRadiusBottomStart;
    private float boxCornerRadiusTopEnd;
    private float boxCornerRadiusTopStart;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private final int boxStrokeWidthDefaultPx;
    private final int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    public final CollapsingTextHelper collapsingTextHelper;
    public boolean counterEnabled;
    private int counterMaxLength;
    private final int counterOverflowTextAppearance;
    public boolean counterOverflowed;
    private final int counterTextAppearance;
    public TextView counterView;
    private ColorStateList defaultHintTextColor;
    private final int defaultStrokeColor;
    private final int disabledColor;
    public EditText editText;
    private Drawable editTextOriginalDrawable;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean hasPasswordToggleTintList;
    private boolean hasPasswordToggleTintMode;
    private boolean hasReconstructedEditTextBackground;
    public CharSequence hint;
    public boolean hintAnimationEnabled;
    public boolean hintEnabled;
    private boolean hintExpanded;
    private final int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    public final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    private boolean isProvidingHint;
    private Drawable originalEditTextEndDrawable;
    private CharSequence originalHint;
    private CharSequence passwordToggleContentDesc;
    private Drawable passwordToggleDrawable;
    private Drawable passwordToggleDummyDrawable;
    private boolean passwordToggleEnabled;
    private ColorStateList passwordToggleTintList;
    private android.graphics.PorterDuff.Mode passwordToggleTintMode;
    private CheckableImageButton passwordToggleView;
    private boolean passwordToggledVisible;
    public boolean restoringSavedState;
    private final Rect tmpRect;
    private final RectF tmpRectF;

    public TextInputLayout(Context context)
    {
        this(context, null);
    }

    public TextInputLayout(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010029);
    }

    public TextInputLayout(Context context, AttributeSet attributeset, int i)
    {
        Object obj;
        super(context, attributeset, i);
        indicatorViewController = new IndicatorViewController(this);
        tmpRect = new Rect();
        tmpRectF = new RectF();
        collapsingTextHelper = new CollapsingTextHelper(this);
        setOrientation(1);
        setWillNotDraw(false);
        setAddStatesFromChildren(true);
        inputFrame = new FrameLayout(context);
        inputFrame.setAddStatesFromChildren(true);
        addView(inputFrame);
        obj = collapsingTextHelper;
        obj.textSizeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        if (((CollapsingTextHelper) (obj)).view.getHeight() <= 0 || ((CollapsingTextHelper) (obj)).view.getWidth() <= 0) goto _L2; else goto _L1
_L1:
        float f;
        int j;
        float f1 = ((CollapsingTextHelper) (obj)).currentTextSize;
        ((CollapsingTextHelper) (obj)).calculateUsingTextSize(((CollapsingTextHelper) (obj)).collapsedTextSize);
        CollapsingTextHelper collapsingtexthelper;
        int l;
        boolean flag;
        boolean flag1;
        boolean flag2;
        if (((CollapsingTextHelper) (obj)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (obj)).textPaint.measureText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = ((CollapsingTextHelper) (obj)).collapsedTextGravity;
        if (((CollapsingTextHelper) (obj)).isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        j = Gravity.getAbsoluteGravity(l, j);
        j & 0x70;
        JVM INSTR lookupswitch 2: default 240
    //                   48: 2539
    //                   80: 2522;
           goto _L3 _L4 _L5
_L3:
        obj.collapsedDrawY = ((((CollapsingTextHelper) (obj)).textPaint.descent() - ((CollapsingTextHelper) (obj)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (obj)).textPaint.descent()) + (float)((CollapsingTextHelper) (obj)).collapsedBounds.centerY();
_L29:
        j & 0x800007;
        JVM INSTR lookupswitch 2: default 316
    //                   1: 2565
    //                   5: 2587;
           goto _L6 _L7 _L8
_L6:
        obj.collapsedDrawX = ((CollapsingTextHelper) (obj)).collapsedBounds.left;
_L30:
        ((CollapsingTextHelper) (obj)).calculateUsingTextSize(((CollapsingTextHelper) (obj)).expandedTextSize);
        if (((CollapsingTextHelper) (obj)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (obj)).textPaint.measureText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = ((CollapsingTextHelper) (obj)).expandedTextGravity;
        if (((CollapsingTextHelper) (obj)).isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        j = Gravity.getAbsoluteGravity(l, j);
        j & 0x70;
        JVM INSTR lookupswitch 2: default 432
    //                   48: 2636
    //                   80: 2619;
           goto _L9 _L10 _L11
_L9:
        obj.expandedDrawY = ((((CollapsingTextHelper) (obj)).textPaint.descent() - ((CollapsingTextHelper) (obj)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (obj)).textPaint.descent()) + (float)((CollapsingTextHelper) (obj)).expandedBounds.centerY();
_L31:
        j & 0x800007;
        JVM INSTR lookupswitch 2: default 508
    //                   1: 2662
    //                   5: 2684;
           goto _L12 _L13 _L14
_L12:
        obj.expandedDrawX = ((CollapsingTextHelper) (obj)).expandedBounds.left;
_L32:
        if (((CollapsingTextHelper) (obj)).expandedTitleTexture != null)
        {
            ((CollapsingTextHelper) (obj)).expandedTitleTexture.recycle();
            obj.expandedTitleTexture = null;
        }
        ((CollapsingTextHelper) (obj)).calculateUsingTextSize(f1);
        obj.useTexture = false;
        if (((CollapsingTextHelper) (obj)).useTexture && ((CollapsingTextHelper) (obj)).expandedTitleTexture == null && !((CollapsingTextHelper) (obj)).expandedBounds.isEmpty() && !TextUtils.isEmpty(((CollapsingTextHelper) (obj)).textToDraw))
        {
            ((CollapsingTextHelper) (obj)).calculateOffsets(0.0F);
            obj.textureAscent = ((CollapsingTextHelper) (obj)).textPaint.ascent();
            obj.textureDescent = ((CollapsingTextHelper) (obj)).textPaint.descent();
            j = Math.round(((CollapsingTextHelper) (obj)).textPaint.measureText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length()));
            i1 = Math.round(((CollapsingTextHelper) (obj)).textureDescent - ((CollapsingTextHelper) (obj)).textureAscent);
            if (j > 0 && i1 > 0)
            {
                obj.expandedTitleTexture = Bitmap.createBitmap(j, i1, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(((CollapsingTextHelper) (obj)).expandedTitleTexture)).drawText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length(), 0.0F, (float)i1 - ((CollapsingTextHelper) (obj)).textPaint.descent(), ((CollapsingTextHelper) (obj)).textPaint);
                if (((CollapsingTextHelper) (obj)).texturePaint == null)
                {
                    obj.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(((CollapsingTextHelper) (obj)).view);
        ((CollapsingTextHelper) (obj)).calculateOffsets(((CollapsingTextHelper) (obj)).expandedFraction);
_L2:
        obj = collapsingTextHelper;
        obj.positionInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        if (((CollapsingTextHelper) (obj)).view.getHeight() <= 0 || ((CollapsingTextHelper) (obj)).view.getWidth() <= 0) goto _L16; else goto _L15
_L15:
        f1 = ((CollapsingTextHelper) (obj)).currentTextSize;
        ((CollapsingTextHelper) (obj)).calculateUsingTextSize(((CollapsingTextHelper) (obj)).collapsedTextSize);
        if (((CollapsingTextHelper) (obj)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (obj)).textPaint.measureText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = ((CollapsingTextHelper) (obj)).collapsedTextGravity;
        if (((CollapsingTextHelper) (obj)).isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        j = Gravity.getAbsoluteGravity(l, j);
        j & 0x70;
        JVM INSTR lookupswitch 2: default 760
    //                   48: 2908
    //                   80: 2891;
           goto _L17 _L18 _L19
_L17:
        obj.collapsedDrawY = ((((CollapsingTextHelper) (obj)).textPaint.descent() - ((CollapsingTextHelper) (obj)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (obj)).textPaint.descent()) + (float)((CollapsingTextHelper) (obj)).collapsedBounds.centerY();
_L33:
        j & 0x800007;
        JVM INSTR lookupswitch 2: default 836
    //                   1: 2934
    //                   5: 2956;
           goto _L20 _L21 _L22
_L20:
        obj.collapsedDrawX = ((CollapsingTextHelper) (obj)).collapsedBounds.left;
_L34:
        ((CollapsingTextHelper) (obj)).calculateUsingTextSize(((CollapsingTextHelper) (obj)).expandedTextSize);
        if (((CollapsingTextHelper) (obj)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (obj)).textPaint.measureText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = ((CollapsingTextHelper) (obj)).expandedTextGravity;
        if (((CollapsingTextHelper) (obj)).isRtl)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        j = Gravity.getAbsoluteGravity(l, j);
        j & 0x70;
        JVM INSTR lookupswitch 2: default 952
    //                   48: 3005
    //                   80: 2988;
           goto _L23 _L24 _L25
_L23:
        obj.expandedDrawY = ((((CollapsingTextHelper) (obj)).textPaint.descent() - ((CollapsingTextHelper) (obj)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (obj)).textPaint.descent()) + (float)((CollapsingTextHelper) (obj)).expandedBounds.centerY();
_L35:
        j & 0x800007;
        JVM INSTR lookupswitch 2: default 1028
    //                   1: 3031
    //                   5: 3053;
           goto _L26 _L27 _L28
_L26:
        obj.expandedDrawX = ((CollapsingTextHelper) (obj)).expandedBounds.left;
_L36:
        if (((CollapsingTextHelper) (obj)).expandedTitleTexture != null)
        {
            ((CollapsingTextHelper) (obj)).expandedTitleTexture.recycle();
            obj.expandedTitleTexture = null;
        }
        ((CollapsingTextHelper) (obj)).calculateUsingTextSize(f1);
        obj.useTexture = false;
        if (((CollapsingTextHelper) (obj)).useTexture && ((CollapsingTextHelper) (obj)).expandedTitleTexture == null && !((CollapsingTextHelper) (obj)).expandedBounds.isEmpty() && !TextUtils.isEmpty(((CollapsingTextHelper) (obj)).textToDraw))
        {
            ((CollapsingTextHelper) (obj)).calculateOffsets(0.0F);
            obj.textureAscent = ((CollapsingTextHelper) (obj)).textPaint.ascent();
            obj.textureDescent = ((CollapsingTextHelper) (obj)).textPaint.descent();
            int k = Math.round(((CollapsingTextHelper) (obj)).textPaint.measureText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length()));
            i1 = Math.round(((CollapsingTextHelper) (obj)).textureDescent - ((CollapsingTextHelper) (obj)).textureAscent);
            if (k > 0 && i1 > 0)
            {
                obj.expandedTitleTexture = Bitmap.createBitmap(k, i1, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(((CollapsingTextHelper) (obj)).expandedTitleTexture)).drawText(((CollapsingTextHelper) (obj)).textToDraw, 0, ((CollapsingTextHelper) (obj)).textToDraw.length(), 0.0F, (float)i1 - ((CollapsingTextHelper) (obj)).textPaint.descent(), ((CollapsingTextHelper) (obj)).textPaint);
                if (((CollapsingTextHelper) (obj)).texturePaint == null)
                {
                    obj.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(((CollapsingTextHelper) (obj)).view);
        ((CollapsingTextHelper) (obj)).calculateOffsets(((CollapsingTextHelper) (obj)).expandedFraction);
_L16:
        collapsingTextHelper.setCollapsedTextGravity(0x800033);
        attributeset = ThemeEnforcement.obtainTintedStyledAttributes(context, attributeset, R.styleable.TextInputLayout, i, 0x7f140365, new int[0]);
        i = R.styleable.TextInputLayout_hintEnabled;
        hintEnabled = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(i, true);
        i = R.styleable.TextInputLayout_android_hint;
        obj = ((TintTypedArray) (attributeset)).mWrapped.getText(i);
        if (hintEnabled)
        {
            if (!TextUtils.equals(((CharSequence) (obj)), hint))
            {
                hint = ((CharSequence) (obj));
                collapsingtexthelper = collapsingTextHelper;
                if (obj == null || !obj.equals(collapsingtexthelper.text))
                {
                    collapsingtexthelper.text = ((CharSequence) (obj));
                    collapsingtexthelper.textToDraw = null;
                    if (collapsingtexthelper.expandedTitleTexture != null)
                    {
                        collapsingtexthelper.expandedTitleTexture.recycle();
                        collapsingtexthelper.expandedTitleTexture = null;
                    }
                    collapsingtexthelper.recalculate();
                }
                if (!hintExpanded)
                {
                    openCutout();
                }
            }
            sendAccessibilityEvent(2048);
        }
        i = R.styleable.TextInputLayout_hintAnimationEnabled;
        hintAnimationEnabled = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(i, true);
        boxBottomOffsetPx = context.getResources().getDimensionPixelOffset(0x7f0e02db);
        boxLabelCutoutPaddingPx = context.getResources().getDimensionPixelOffset(0x7f0e02de);
        i = R.styleable.TextInputLayout_boxCollapsedPaddingTop;
        boxCollapsedPaddingTopPx = ((TintTypedArray) (attributeset)).mWrapped.getDimensionPixelOffset(i, 0);
        i = R.styleable.TextInputLayout_boxCornerRadiusTopStart;
        boxCornerRadiusTopStart = ((TintTypedArray) (attributeset)).mWrapped.getDimension(i, 0.0F);
        i = R.styleable.TextInputLayout_boxCornerRadiusTopEnd;
        boxCornerRadiusTopEnd = ((TintTypedArray) (attributeset)).mWrapped.getDimension(i, 0.0F);
        i = R.styleable.TextInputLayout_boxCornerRadiusBottomEnd;
        boxCornerRadiusBottomEnd = ((TintTypedArray) (attributeset)).mWrapped.getDimension(i, 0.0F);
        i = R.styleable.TextInputLayout_boxCornerRadiusBottomStart;
        boxCornerRadiusBottomStart = ((TintTypedArray) (attributeset)).mWrapped.getDimension(i, 0.0F);
        i = R.styleable.TextInputLayout_boxBackgroundColor;
        boxBackgroundColor = ((TintTypedArray) (attributeset)).mWrapped.getColor(i, 0);
        i = R.styleable.TextInputLayout_boxStrokeColor;
        focusedStrokeColor = ((TintTypedArray) (attributeset)).mWrapped.getColor(i, 0);
        boxStrokeWidthDefaultPx = context.getResources().getDimensionPixelSize(0x7f0e02e0);
        boxStrokeWidthFocusedPx = context.getResources().getDimensionPixelSize(0x7f0e02e1);
        boxStrokeWidthPx = boxStrokeWidthDefaultPx;
        i = R.styleable.TextInputLayout_boxBackgroundMode;
        setBoxBackgroundMode(((TintTypedArray) (attributeset)).mWrapped.getInt(i, 0));
        i = R.styleable.TextInputLayout_android_textColorHint;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(i))
        {
            obj = attributeset.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
            focusedTextColor = ((ColorStateList) (obj));
            defaultHintTextColor = ((ColorStateList) (obj));
        }
        defaultStrokeColor = ContextCompat.getColor(context, 0x7f0d015b);
        disabledColor = ContextCompat.getColor(context, 0x7f0d015c);
        hoveredStrokeColor = ContextCompat.getColor(context, 0x7f0d015e);
        i = R.styleable.TextInputLayout_hintTextAppearance;
        if (((TintTypedArray) (attributeset)).mWrapped.getResourceId(i, -1) != -1)
        {
            i = R.styleable.TextInputLayout_hintTextAppearance;
            setHintTextAppearance(((TintTypedArray) (attributeset)).mWrapped.getResourceId(i, 0));
        }
        i = R.styleable.TextInputLayout_errorTextAppearance;
        i = ((TintTypedArray) (attributeset)).mWrapped.getResourceId(i, 0);
        j = R.styleable.TextInputLayout_errorEnabled;
        flag = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(j, false);
        j = R.styleable.TextInputLayout_helperTextTextAppearance;
        j = ((TintTypedArray) (attributeset)).mWrapped.getResourceId(j, 0);
        l = R.styleable.TextInputLayout_helperTextEnabled;
        flag1 = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(l, false);
        l = R.styleable.TextInputLayout_helperText;
        context = ((TintTypedArray) (attributeset)).mWrapped.getText(l);
        l = R.styleable.TextInputLayout_counterEnabled;
        flag2 = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(l, false);
        l = R.styleable.TextInputLayout_counterMaxLength;
        setCounterMaxLength(((TintTypedArray) (attributeset)).mWrapped.getInt(l, -1));
        l = R.styleable.TextInputLayout_counterTextAppearance;
        counterTextAppearance = ((TintTypedArray) (attributeset)).mWrapped.getResourceId(l, 0);
        l = R.styleable.TextInputLayout_counterOverflowTextAppearance;
        counterOverflowTextAppearance = ((TintTypedArray) (attributeset)).mWrapped.getResourceId(l, 0);
        l = R.styleable.TextInputLayout_passwordToggleEnabled;
        passwordToggleEnabled = ((TintTypedArray) (attributeset)).mWrapped.getBoolean(l, false);
        passwordToggleDrawable = attributeset.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable);
        l = R.styleable.TextInputLayout_passwordToggleContentDescription;
        passwordToggleContentDesc = ((TintTypedArray) (attributeset)).mWrapped.getText(l);
        l = R.styleable.TextInputLayout_passwordToggleTint;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(l))
        {
            hasPasswordToggleTintList = true;
            passwordToggleTintList = attributeset.getColorStateList(R.styleable.TextInputLayout_passwordToggleTint);
        }
        l = R.styleable.TextInputLayout_passwordToggleTintMode;
        if (((TintTypedArray) (attributeset)).mWrapped.hasValue(l))
        {
            hasPasswordToggleTintMode = true;
            l = R.styleable.TextInputLayout_passwordToggleTintMode;
            passwordToggleTintMode = ViewUtils.parseTintMode(((TintTypedArray) (attributeset)).mWrapped.getInt(l, -1), null);
        }
        ((TintTypedArray) (attributeset)).mWrapped.recycle();
        attributeset = indicatorViewController;
        if (((IndicatorViewController) (attributeset)).helperTextEnabled != flag1)
        {
            if (((IndicatorViewController) (attributeset)).captionAnimator != null)
            {
                ((IndicatorViewController) (attributeset)).captionAnimator.cancel();
            }
            if (flag1)
            {
                attributeset.helperTextView = new AppCompatTextView(((IndicatorViewController) (attributeset)).context);
                ((IndicatorViewController) (attributeset)).helperTextView.setId(0x7f100046);
                ((IndicatorViewController) (attributeset)).helperTextView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(((IndicatorViewController) (attributeset)).helperTextView, 1);
                int i1 = ((IndicatorViewController) (attributeset)).helperTextTextAppearance;
                attributeset.helperTextTextAppearance = i1;
                if (((IndicatorViewController) (attributeset)).helperTextView != null)
                {
                    obj = ((IndicatorViewController) (attributeset)).helperTextView;
                    if (android.os.Build.VERSION.SDK_INT >= 23)
                    {
                        ((TextView) (obj)).setTextAppearance(i1);
                    } else
                    {
                        ((TextView) (obj)).setTextAppearance(((TextView) (obj)).getContext(), i1);
                    }
                }
                attributeset.addIndicator(((IndicatorViewController) (attributeset)).helperTextView, 1);
            } else
            {
                if (((IndicatorViewController) (attributeset)).captionAnimator != null)
                {
                    ((IndicatorViewController) (attributeset)).captionAnimator.cancel();
                }
                if (((IndicatorViewController) (attributeset)).captionDisplayed == 2)
                {
                    attributeset.captionToShow = 0;
                }
                attributeset.updateCaptionViewsVisibility(((IndicatorViewController) (attributeset)).captionDisplayed, ((IndicatorViewController) (attributeset)).captionToShow, attributeset.shouldAnimateCaptionView(((IndicatorViewController) (attributeset)).helperTextView, null));
                attributeset.removeIndicator(((IndicatorViewController) (attributeset)).helperTextView, 1);
                attributeset.helperTextView = null;
                ((IndicatorViewController) (attributeset)).textInputView.updateEditTextBackground();
                ((IndicatorViewController) (attributeset)).textInputView.updateTextInputBoxState();
            }
            attributeset.helperTextEnabled = flag1;
        }
        if (TextUtils.isEmpty(context))
        {
            if (indicatorViewController.helperTextEnabled)
            {
                context = indicatorViewController;
                if (((IndicatorViewController) (context)).helperTextEnabled)
                {
                    if (((IndicatorViewController) (context)).captionAnimator != null)
                    {
                        ((IndicatorViewController) (context)).captionAnimator.cancel();
                    }
                    if (((IndicatorViewController) (context)).captionAnimator != null)
                    {
                        ((IndicatorViewController) (context)).captionAnimator.cancel();
                    }
                    if (((IndicatorViewController) (context)).captionDisplayed == 2)
                    {
                        context.captionToShow = 0;
                    }
                    context.updateCaptionViewsVisibility(((IndicatorViewController) (context)).captionDisplayed, ((IndicatorViewController) (context)).captionToShow, context.shouldAnimateCaptionView(((IndicatorViewController) (context)).helperTextView, null));
                    context.removeIndicator(((IndicatorViewController) (context)).helperTextView, 1);
                    context.helperTextView = null;
                    ((IndicatorViewController) (context)).textInputView.updateEditTextBackground();
                    ((IndicatorViewController) (context)).textInputView.updateTextInputBoxState();
                    context.helperTextEnabled = false;
                }
            }
        } else
        {
            if (!indicatorViewController.helperTextEnabled)
            {
                attributeset = indicatorViewController;
                if (!((IndicatorViewController) (attributeset)).helperTextEnabled)
                {
                    if (((IndicatorViewController) (attributeset)).captionAnimator != null)
                    {
                        ((IndicatorViewController) (attributeset)).captionAnimator.cancel();
                    }
                    attributeset.helperTextView = new AppCompatTextView(((IndicatorViewController) (attributeset)).context);
                    ((IndicatorViewController) (attributeset)).helperTextView.setId(0x7f100046);
                    ((IndicatorViewController) (attributeset)).helperTextView.setVisibility(4);
                    ViewCompat.setAccessibilityLiveRegion(((IndicatorViewController) (attributeset)).helperTextView, 1);
                    int j1 = ((IndicatorViewController) (attributeset)).helperTextTextAppearance;
                    attributeset.helperTextTextAppearance = j1;
                    if (((IndicatorViewController) (attributeset)).helperTextView != null)
                    {
                        TextView textview = ((IndicatorViewController) (attributeset)).helperTextView;
                        if (android.os.Build.VERSION.SDK_INT >= 23)
                        {
                            textview.setTextAppearance(j1);
                        } else
                        {
                            textview.setTextAppearance(textview.getContext(), j1);
                        }
                    }
                    attributeset.addIndicator(((IndicatorViewController) (attributeset)).helperTextView, 1);
                    attributeset.helperTextEnabled = true;
                }
            }
            attributeset = indicatorViewController;
            if (((IndicatorViewController) (attributeset)).captionAnimator != null)
            {
                ((IndicatorViewController) (attributeset)).captionAnimator.cancel();
            }
            attributeset.helperText = context;
            ((IndicatorViewController) (attributeset)).helperTextView.setText(context);
            if (((IndicatorViewController) (attributeset)).captionDisplayed != 2)
            {
                attributeset.captionToShow = 2;
            }
            attributeset.updateCaptionViewsVisibility(((IndicatorViewController) (attributeset)).captionDisplayed, ((IndicatorViewController) (attributeset)).captionToShow, attributeset.shouldAnimateCaptionView(((IndicatorViewController) (attributeset)).helperTextView, context));
        }
        setHelperTextTextAppearance(j);
        context = indicatorViewController;
        if (((IndicatorViewController) (context)).errorEnabled != flag)
        {
            if (((IndicatorViewController) (context)).captionAnimator != null)
            {
                ((IndicatorViewController) (context)).captionAnimator.cancel();
            }
            if (flag)
            {
                context.errorView = new AppCompatTextView(((IndicatorViewController) (context)).context);
                ((IndicatorViewController) (context)).errorView.setId(0x7f100045);
                j = ((IndicatorViewController) (context)).errorTextAppearance;
                context.errorTextAppearance = j;
                if (((IndicatorViewController) (context)).errorView != null)
                {
                    ((IndicatorViewController) (context)).textInputView.setTextAppearanceCompatWithErrorFallback(((IndicatorViewController) (context)).errorView, j);
                }
                ((IndicatorViewController) (context)).errorView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(((IndicatorViewController) (context)).errorView, 1);
                context.addIndicator(((IndicatorViewController) (context)).errorView, 0);
            } else
            {
                context.hideError();
                context.removeIndicator(((IndicatorViewController) (context)).errorView, 0);
                context.errorView = null;
                ((IndicatorViewController) (context)).textInputView.updateEditTextBackground();
                ((IndicatorViewController) (context)).textInputView.updateTextInputBoxState();
            }
            context.errorEnabled = flag;
        }
        setErrorTextAppearance(i);
        if (counterEnabled != flag2)
        {
            if (flag2)
            {
                counterView = new AppCompatTextView(getContext());
                counterView.setId(0x7f100044);
                counterView.setMaxLines(1);
                setTextAppearanceCompatWithErrorFallback(counterView, counterTextAppearance);
                indicatorViewController.addIndicator(counterView, 2);
                if (editText == null)
                {
                    updateCounter(0);
                } else
                {
                    updateCounter(editText.getText().length());
                }
            } else
            {
                indicatorViewController.removeIndicator(counterView, 2);
                counterView = null;
            }
            counterEnabled = flag2;
        }
        if (passwordToggleDrawable != null && (hasPasswordToggleTintList || hasPasswordToggleTintMode))
        {
            context = passwordToggleDrawable;
            if (android.os.Build.VERSION.SDK_INT < 23 && !(context instanceof TintAwareDrawable))
            {
                context = new WrappedDrawableApi21(context);
            }
            passwordToggleDrawable = context.mutate();
            if (hasPasswordToggleTintList)
            {
                passwordToggleDrawable.setTintList(passwordToggleTintList);
            }
            if (hasPasswordToggleTintMode)
            {
                passwordToggleDrawable.setTintMode(passwordToggleTintMode);
            }
            if (passwordToggleView != null && passwordToggleView.getDrawable() != passwordToggleDrawable)
            {
                passwordToggleView.setImageDrawable(passwordToggleDrawable);
            }
        }
        ViewCompat.setImportantForAccessibility(this, 2);
        return;
_L5:
        obj.collapsedDrawY = ((CollapsingTextHelper) (obj)).collapsedBounds.bottom;
          goto _L29
_L4:
        obj.collapsedDrawY = (float)((CollapsingTextHelper) (obj)).collapsedBounds.top - ((CollapsingTextHelper) (obj)).textPaint.ascent();
          goto _L29
_L7:
        obj.collapsedDrawX = (float)((CollapsingTextHelper) (obj)).collapsedBounds.centerX() - f / 2.0F;
          goto _L30
_L8:
        obj.collapsedDrawX = (float)((CollapsingTextHelper) (obj)).collapsedBounds.right - f;
          goto _L30
_L11:
        obj.expandedDrawY = ((CollapsingTextHelper) (obj)).expandedBounds.bottom;
          goto _L31
_L10:
        obj.expandedDrawY = (float)((CollapsingTextHelper) (obj)).expandedBounds.top - ((CollapsingTextHelper) (obj)).textPaint.ascent();
          goto _L31
_L13:
        obj.expandedDrawX = (float)((CollapsingTextHelper) (obj)).expandedBounds.centerX() - f / 2.0F;
          goto _L32
_L14:
        obj.expandedDrawX = (float)((CollapsingTextHelper) (obj)).expandedBounds.right - f;
          goto _L32
_L19:
        obj.collapsedDrawY = ((CollapsingTextHelper) (obj)).collapsedBounds.bottom;
          goto _L33
_L18:
        obj.collapsedDrawY = (float)((CollapsingTextHelper) (obj)).collapsedBounds.top - ((CollapsingTextHelper) (obj)).textPaint.ascent();
          goto _L33
_L21:
        obj.collapsedDrawX = (float)((CollapsingTextHelper) (obj)).collapsedBounds.centerX() - f / 2.0F;
          goto _L34
_L22:
        obj.collapsedDrawX = (float)((CollapsingTextHelper) (obj)).collapsedBounds.right - f;
          goto _L34
_L25:
        obj.expandedDrawY = ((CollapsingTextHelper) (obj)).expandedBounds.bottom;
          goto _L35
_L24:
        obj.expandedDrawY = (float)((CollapsingTextHelper) (obj)).expandedBounds.top - ((CollapsingTextHelper) (obj)).textPaint.ascent();
          goto _L35
_L27:
        obj.expandedDrawX = (float)((CollapsingTextHelper) (obj)).expandedBounds.centerX() - f / 2.0F;
          goto _L36
_L28:
        obj.expandedDrawX = (float)((CollapsingTextHelper) (obj)).expandedBounds.right - f;
          goto _L36
    }

    private final void animateToExpansionFraction(float f)
    {
        if (collapsingTextHelper.expandedFraction == f)
        {
            return;
        }
        if (animator == null)
        {
            animator = new ValueAnimator();
            animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            animator.setDuration(167L);
            animator.addUpdateListener(new _cls3());
        }
        animator.setFloatValues(new float[] {
            collapsingTextHelper.expandedFraction, f
        });
        animator.start();
    }

    private final void applyBoxAttributes()
    {
        if (boxBackground == null)
        {
            return;
        }
        boxBackgroundMode;
        JVM INSTR tableswitch 1 2: default 36
    //                   1 245
    //                   2 253;
           goto _L1 _L2 _L3
_L1:
        break; /* Loop/switch isn't completed */
_L3:
        break MISSING_BLOCK_LABEL_253;
_L4:
        if (editText != null && boxBackgroundMode == 2)
        {
            if (editText.getBackground() != null)
            {
                editTextOriginalDrawable = editText.getBackground();
            }
            ViewCompat.setBackground(editText, null);
        }
        if (editText != null && boxBackgroundMode == 1 && editTextOriginalDrawable != null)
        {
            ViewCompat.setBackground(editText, editTextOriginalDrawable);
        }
        if (boxStrokeWidthPx >= 0 && boxStrokeColor != 0)
        {
            boxBackground.setStroke(boxStrokeWidthPx, boxStrokeColor);
        }
        GradientDrawable gradientdrawable = boxBackground;
        float af[];
        boolean flag;
        if (ViewCompat.getLayoutDirection(this) == 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            af = new float[8];
            af[0] = boxCornerRadiusTopStart;
            af[1] = boxCornerRadiusTopStart;
            af[2] = boxCornerRadiusTopEnd;
            af[3] = boxCornerRadiusTopEnd;
            af[4] = boxCornerRadiusBottomEnd;
            af[5] = boxCornerRadiusBottomEnd;
            af[6] = boxCornerRadiusBottomStart;
            af[7] = boxCornerRadiusBottomStart;
        } else
        {
            af = new float[8];
            af[0] = boxCornerRadiusTopEnd;
            af[1] = boxCornerRadiusTopEnd;
            af[2] = boxCornerRadiusTopStart;
            af[3] = boxCornerRadiusTopStart;
            af[4] = boxCornerRadiusBottomStart;
            af[5] = boxCornerRadiusBottomStart;
            af[6] = boxCornerRadiusBottomEnd;
            af[7] = boxCornerRadiusBottomEnd;
        }
        gradientdrawable.setCornerRadii(af);
        boxBackground.setColor(boxBackgroundColor);
        invalidate();
        return;
_L2:
        boxStrokeWidthPx = 0;
          goto _L4
        if (focusedStrokeColor == 0)
        {
            focusedStrokeColor = focusedTextColor.getColorForState(getDrawableState(), focusedTextColor.getDefaultColor());
        }
          goto _L4
    }

    private final int calculateLabelMarginTop()
    {
        if (!hintEnabled)
        {
            return 0;
        }
        switch (boxBackgroundMode)
        {
        default:
            return 0;

        case 0: // '\0'
        case 1: // '\001'
            return (int)collapsingTextHelper.getCollapsedTextHeight();

        case 2: // '\002'
            return (int)(collapsingTextHelper.getCollapsedTextHeight() / 2.0F);
        }
    }

    private final boolean cutoutEnabled()
    {
        return hintEnabled && !TextUtils.isEmpty(hint) && (boxBackground instanceof CutoutDrawable);
    }

    private final void onApplyBoxBackgroundMode()
    {
        if (boxBackgroundMode != 0) goto _L2; else goto _L1
_L1:
        boxBackground = null;
_L4:
        if (boxBackgroundMode != 0)
        {
            updateInputLayoutMargins();
        }
        updateTextInputBoxBounds();
        return;
_L2:
        if (boxBackgroundMode == 2 && hintEnabled && !(boxBackground instanceof CutoutDrawable))
        {
            boxBackground = new CutoutDrawable();
        } else
        if (!(boxBackground instanceof GradientDrawable))
        {
            boxBackground = new GradientDrawable();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private final void openCutout()
    {
        if (!cutoutEnabled())
        {
            return;
        }
        RectF rectf = tmpRectF;
        CollapsingTextHelper collapsingtexthelper = collapsingTextHelper;
        boolean flag = collapsingtexthelper.calculateIsRtl(collapsingtexthelper.text);
        float f;
        if (!flag)
        {
            f = collapsingtexthelper.collapsedBounds.left;
        } else
        {
            f = (float)collapsingtexthelper.collapsedBounds.right - collapsingtexthelper.calculateCollapsedTextWidth();
        }
        rectf.left = f;
        rectf.top = collapsingtexthelper.collapsedBounds.top;
        if (!flag)
        {
            f = rectf.left + collapsingtexthelper.calculateCollapsedTextWidth();
        } else
        {
            f = collapsingtexthelper.collapsedBounds.right;
        }
        rectf.right = f;
        rectf.bottom = (float)collapsingtexthelper.collapsedBounds.top + collapsingtexthelper.getCollapsedTextHeight();
        rectf.left = rectf.left - (float)boxLabelCutoutPaddingPx;
        rectf.top = rectf.top - (float)boxLabelCutoutPaddingPx;
        rectf.right = rectf.right + (float)boxLabelCutoutPaddingPx;
        rectf.bottom = rectf.bottom + (float)boxLabelCutoutPaddingPx;
        ((CutoutDrawable)boxBackground).setCutout(rectf.left, rectf.top, rectf.right, rectf.bottom);
    }

    private static void recursiveSetEnabled(ViewGroup viewgroup, boolean flag)
    {
        int j = viewgroup.getChildCount();
        for (int i = 0; i < j; i++)
        {
            View view = viewgroup.getChildAt(i);
            view.setEnabled(flag);
            if (view instanceof ViewGroup)
            {
                recursiveSetEnabled((ViewGroup)view, flag);
            }
        }

    }

    private final void setErrorEnabled(boolean flag)
    {
        IndicatorViewController indicatorviewcontroller = indicatorViewController;
        if (indicatorviewcontroller.errorEnabled != flag)
        {
            if (indicatorviewcontroller.captionAnimator != null)
            {
                indicatorviewcontroller.captionAnimator.cancel();
            }
            if (flag)
            {
                indicatorviewcontroller.errorView = new AppCompatTextView(indicatorviewcontroller.context);
                indicatorviewcontroller.errorView.setId(0x7f100045);
                int i = indicatorviewcontroller.errorTextAppearance;
                indicatorviewcontroller.errorTextAppearance = i;
                if (indicatorviewcontroller.errorView != null)
                {
                    indicatorviewcontroller.textInputView.setTextAppearanceCompatWithErrorFallback(indicatorviewcontroller.errorView, i);
                }
                indicatorviewcontroller.errorView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(indicatorviewcontroller.errorView, 1);
                indicatorviewcontroller.addIndicator(indicatorviewcontroller.errorView, 0);
            } else
            {
                indicatorviewcontroller.hideError();
                indicatorviewcontroller.removeIndicator(indicatorviewcontroller.errorView, 0);
                indicatorviewcontroller.errorView = null;
                indicatorviewcontroller.textInputView.updateEditTextBackground();
                indicatorviewcontroller.textInputView.updateTextInputBoxState();
            }
            indicatorviewcontroller.errorEnabled = flag;
        }
    }

    private final void updateInputLayoutMargins()
    {
        android.widget.LinearLayout.LayoutParams layoutparams = (android.widget.LinearLayout.LayoutParams)inputFrame.getLayoutParams();
        int i = calculateLabelMarginTop();
        if (i != layoutparams.topMargin)
        {
            layoutparams.topMargin = i;
            inputFrame.requestLayout();
        }
    }

    private final void updatePasswordToggleView()
    {
        if (editText != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!passwordToggleEnabled) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && !passwordToggledVisible) goto _L4; else goto _L5
_L5:
        flag = true;
_L7:
        if (flag)
        {
            if (passwordToggleView == null)
            {
                passwordToggleView = (CheckableImageButton)LayoutInflater.from(getContext()).inflate(0x7f05004e, inputFrame, false);
                passwordToggleView.setImageDrawable(passwordToggleDrawable);
                passwordToggleView.setContentDescription(passwordToggleContentDesc);
                inputFrame.addView(passwordToggleView);
                passwordToggleView.setOnClickListener(new _cls2());
            }
            if (editText != null && ViewCompat.getMinimumHeight(editText) <= 0)
            {
                editText.setMinimumHeight(ViewCompat.getMinimumHeight(passwordToggleView));
            }
            passwordToggleView.setVisibility(0);
            passwordToggleView.setChecked(passwordToggledVisible);
            if (passwordToggleDummyDrawable == null)
            {
                passwordToggleDummyDrawable = new ColorDrawable();
            }
            passwordToggleDummyDrawable.setBounds(0, 0, passwordToggleView.getMeasuredWidth(), 1);
            Drawable adrawable[] = editText.getCompoundDrawablesRelative();
            if (adrawable[2] != passwordToggleDummyDrawable)
            {
                originalEditTextEndDrawable = adrawable[2];
            }
            editText.setCompoundDrawablesRelative(adrawable[0], adrawable[1], passwordToggleDummyDrawable, adrawable[3]);
            passwordToggleView.setPadding(editText.getPaddingLeft(), editText.getPaddingTop(), editText.getPaddingRight(), editText.getPaddingBottom());
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
        if (passwordToggleView != null && passwordToggleView.getVisibility() == 0)
        {
            passwordToggleView.setVisibility(8);
        }
        if (passwordToggleDummyDrawable != null)
        {
            Drawable adrawable1[] = editText.getCompoundDrawablesRelative();
            if (adrawable1[2] == passwordToggleDummyDrawable)
            {
                editText.setCompoundDrawablesRelative(adrawable1[0], adrawable1[1], originalEditTextEndDrawable, adrawable1[3]);
                passwordToggleDummyDrawable = null;
                return;
            }
        }
        if (true) goto _L1; else goto _L8
_L8:
    }

    private final void updateTextInputBoxBounds()
    {
        if (boxBackgroundMode != 0 && boxBackground != null && editText != null && getRight() != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i1 = editText.getLeft();
        if (editText == null) goto _L4; else goto _L3
_L3:
        boxBackgroundMode;
        JVM INSTR tableswitch 1 2: default 72
    //                   1 317
    //                   2 329;
           goto _L4 _L5 _L6
_L4:
        int i = 0;
_L7:
        int j = editText.getRight();
        int k1 = editText.getBottom() + boxBottomOffsetPx;
        int l;
        if (boxBackgroundMode == 2)
        {
            int l1 = boxStrokeWidthFocusedPx / 2;
            l = boxStrokeWidthFocusedPx / 2;
            int i2 = boxStrokeWidthFocusedPx / 2;
            k1 = boxStrokeWidthFocusedPx / 2 + k1;
            j -= i2;
            l = i - l;
            i1 = l1 + i1;
            i = k1;
        } else
        {
            l = i;
            i = k1;
        }
        boxBackground.setBounds(i1, l, j, i);
        applyBoxAttributes();
        if (editText != null)
        {
            Object obj = editText.getBackground();
            if (obj != null)
            {
                Drawable drawable = ((Drawable) (obj));
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj))))
                {
                    drawable = ((Drawable) (obj)).mutate();
                }
                obj = new Rect();
                DescendantOffsetUtils.getDescendantRect(this, editText, ((Rect) (obj)));
                obj = drawable.getBounds();
                if (((Rect) (obj)).left != ((Rect) (obj)).right)
                {
                    Rect rect = new Rect();
                    drawable.getPadding(rect);
                    i = ((Rect) (obj)).left;
                    int k = rect.left;
                    l = ((Rect) (obj)).right;
                    int j1 = rect.right;
                    drawable.setBounds(i - k, ((Rect) (obj)).top, (j1 << 1) + l, editText.getBottom());
                    return;
                }
            }
        }
        if (true) goto _L1; else goto _L5
_L5:
        i = editText.getTop();
          goto _L7
_L6:
        i = editText.getTop() + calculateLabelMarginTop();
          goto _L7
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (!(view instanceof EditText)) goto _L2; else goto _L1
_L1:
        float f;
        android.widget.FrameLayout.LayoutParams layoutparams1 = new android.widget.FrameLayout.LayoutParams(layoutparams);
        layoutparams1.gravity = layoutparams1.gravity & 0xffffff8f | 0x10;
        inputFrame.addView(view, layoutparams1);
        inputFrame.setLayoutParams(layoutparams);
        updateInputLayoutMargins();
        view = (EditText)view;
        if (editText != null)
        {
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        editText = view;
        float f1;
        int j;
        if (boxBackgroundMode == 0)
        {
            boxBackground = null;
        } else
        if (boxBackgroundMode == 2 && hintEnabled && !(boxBackground instanceof CutoutDrawable))
        {
            boxBackground = new CutoutDrawable();
        } else
        if (!(boxBackground instanceof GradientDrawable))
        {
            boxBackground = new GradientDrawable();
        }
        if (boxBackgroundMode != 0)
        {
            updateInputLayoutMargins();
        }
        updateTextInputBoxBounds();
        view = new AccessibilityDelegate(this);
        if (editText != null)
        {
            ViewCompat.setAccessibilityDelegate(editText, view);
        }
        if (editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L4; else goto _L3
_L3:
        view = collapsingTextHelper;
        layoutparams = editText.getTypeface();
        view.expandedTypeface = layoutparams;
        view.collapsedTypeface = layoutparams;
        if (((CollapsingTextHelper) (view)).view.getHeight() <= 0 || ((CollapsingTextHelper) (view)).view.getWidth() <= 0) goto _L4; else goto _L5
_L5:
        f1 = ((CollapsingTextHelper) (view)).currentTextSize;
        view.calculateUsingTextSize(((CollapsingTextHelper) (view)).collapsedTextSize);
        if (((CollapsingTextHelper) (view)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = ((CollapsingTextHelper) (view)).collapsedTextGravity;
        if (((CollapsingTextHelper) (view)).isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 300
    //                   48: 1903
    //                   80: 1888;
           goto _L6 _L7 _L8
_L6:
        view.collapsedDrawY = ((((CollapsingTextHelper) (view)).textPaint.descent() - ((CollapsingTextHelper) (view)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (view)).textPaint.descent()) + (float)((CollapsingTextHelper) (view)).collapsedBounds.centerY();
_L60:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 368
    //                   1: 1926
    //                   5: 1946;
           goto _L9 _L10 _L11
_L9:
        view.collapsedDrawX = ((CollapsingTextHelper) (view)).collapsedBounds.left;
_L48:
        view.calculateUsingTextSize(((CollapsingTextHelper) (view)).expandedTextSize);
        if (((CollapsingTextHelper) (view)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = ((CollapsingTextHelper) (view)).expandedTextGravity;
        if (((CollapsingTextHelper) (view)).isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 472
    //                   48: 1990
    //                   80: 1975;
           goto _L12 _L13 _L14
_L12:
        view.expandedDrawY = ((((CollapsingTextHelper) (view)).textPaint.descent() - ((CollapsingTextHelper) (view)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (view)).textPaint.descent()) + (float)((CollapsingTextHelper) (view)).expandedBounds.centerY();
_L49:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 540
    //                   1: 2013
    //                   5: 2033;
           goto _L15 _L16 _L17
_L15:
        view.expandedDrawX = ((CollapsingTextHelper) (view)).expandedBounds.left;
_L50:
        if (((CollapsingTextHelper) (view)).expandedTitleTexture != null)
        {
            ((CollapsingTextHelper) (view)).expandedTitleTexture.recycle();
            view.expandedTitleTexture = null;
        }
        view.calculateUsingTextSize(f1);
        view.useTexture = false;
        if (((CollapsingTextHelper) (view)).useTexture && ((CollapsingTextHelper) (view)).expandedTitleTexture == null && !((CollapsingTextHelper) (view)).expandedBounds.isEmpty() && !TextUtils.isEmpty(((CollapsingTextHelper) (view)).textToDraw))
        {
            view.calculateOffsets(0.0F);
            view.textureAscent = ((CollapsingTextHelper) (view)).textPaint.ascent();
            view.textureDescent = ((CollapsingTextHelper) (view)).textPaint.descent();
            i = Math.round(((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length()));
            int k = Math.round(((CollapsingTextHelper) (view)).textureDescent - ((CollapsingTextHelper) (view)).textureAscent);
            if (i > 0 && k > 0)
            {
                view.expandedTitleTexture = Bitmap.createBitmap(i, k, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(((CollapsingTextHelper) (view)).expandedTitleTexture)).drawText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length(), 0.0F, (float)k - ((CollapsingTextHelper) (view)).textPaint.descent(), ((CollapsingTextHelper) (view)).textPaint);
                if (((CollapsingTextHelper) (view)).texturePaint == null)
                {
                    view.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(((CollapsingTextHelper) (view)).view);
        view.calculateOffsets(((CollapsingTextHelper) (view)).expandedFraction);
_L4:
        view = collapsingTextHelper;
        f = editText.getTextSize();
        if (((CollapsingTextHelper) (view)).expandedTextSize == f) goto _L19; else goto _L18
_L18:
        view.expandedTextSize = f;
        if (((CollapsingTextHelper) (view)).view.getHeight() <= 0 || ((CollapsingTextHelper) (view)).view.getWidth() <= 0) goto _L19; else goto _L20
_L20:
        f1 = ((CollapsingTextHelper) (view)).currentTextSize;
        view.calculateUsingTextSize(((CollapsingTextHelper) (view)).collapsedTextSize);
        if (((CollapsingTextHelper) (view)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = ((CollapsingTextHelper) (view)).collapsedTextGravity;
        if (((CollapsingTextHelper) (view)).isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 776
    //                   48: 2231
    //                   80: 2216;
           goto _L21 _L22 _L23
_L21:
        view.collapsedDrawY = ((((CollapsingTextHelper) (view)).textPaint.descent() - ((CollapsingTextHelper) (view)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (view)).textPaint.descent()) + (float)((CollapsingTextHelper) (view)).collapsedBounds.centerY();
_L51:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 844
    //                   1: 2254
    //                   5: 2274;
           goto _L24 _L25 _L26
_L24:
        view.collapsedDrawX = ((CollapsingTextHelper) (view)).collapsedBounds.left;
_L52:
        view.calculateUsingTextSize(((CollapsingTextHelper) (view)).expandedTextSize);
        if (((CollapsingTextHelper) (view)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = ((CollapsingTextHelper) (view)).expandedTextGravity;
        if (((CollapsingTextHelper) (view)).isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 948
    //                   48: 2318
    //                   80: 2303;
           goto _L27 _L28 _L29
_L27:
        view.expandedDrawY = ((((CollapsingTextHelper) (view)).textPaint.descent() - ((CollapsingTextHelper) (view)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (view)).textPaint.descent()) + (float)((CollapsingTextHelper) (view)).expandedBounds.centerY();
_L53:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 1016
    //                   1: 2341
    //                   5: 2361;
           goto _L30 _L31 _L32
_L30:
        view.expandedDrawX = ((CollapsingTextHelper) (view)).expandedBounds.left;
_L54:
        if (((CollapsingTextHelper) (view)).expandedTitleTexture != null)
        {
            ((CollapsingTextHelper) (view)).expandedTitleTexture.recycle();
            view.expandedTitleTexture = null;
        }
        view.calculateUsingTextSize(f1);
        view.useTexture = false;
        if (((CollapsingTextHelper) (view)).useTexture && ((CollapsingTextHelper) (view)).expandedTitleTexture == null && !((CollapsingTextHelper) (view)).expandedBounds.isEmpty() && !TextUtils.isEmpty(((CollapsingTextHelper) (view)).textToDraw))
        {
            view.calculateOffsets(0.0F);
            view.textureAscent = ((CollapsingTextHelper) (view)).textPaint.ascent();
            view.textureDescent = ((CollapsingTextHelper) (view)).textPaint.descent();
            i = Math.round(((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length()));
            int l = Math.round(((CollapsingTextHelper) (view)).textureDescent - ((CollapsingTextHelper) (view)).textureAscent);
            if (i > 0 && l > 0)
            {
                view.expandedTitleTexture = Bitmap.createBitmap(i, l, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(((CollapsingTextHelper) (view)).expandedTitleTexture)).drawText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length(), 0.0F, (float)l - ((CollapsingTextHelper) (view)).textPaint.descent(), ((CollapsingTextHelper) (view)).textPaint);
                if (((CollapsingTextHelper) (view)).texturePaint == null)
                {
                    view.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(((CollapsingTextHelper) (view)).view);
        view.calculateOffsets(((CollapsingTextHelper) (view)).expandedFraction);
_L19:
        i = editText.getGravity();
        collapsingTextHelper.setCollapsedTextGravity(i & 0xffffff8f | 0x30);
        view = collapsingTextHelper;
        if (((CollapsingTextHelper) (view)).expandedTextGravity == i) goto _L34; else goto _L33
_L33:
        view.expandedTextGravity = i;
        if (((CollapsingTextHelper) (view)).view.getHeight() <= 0 || ((CollapsingTextHelper) (view)).view.getWidth() <= 0) goto _L34; else goto _L35
_L35:
        f1 = ((CollapsingTextHelper) (view)).currentTextSize;
        view.calculateUsingTextSize(((CollapsingTextHelper) (view)).collapsedTextSize);
        if (((CollapsingTextHelper) (view)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = ((CollapsingTextHelper) (view)).collapsedTextGravity;
        if (((CollapsingTextHelper) (view)).isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 1264
    //                   48: 2559
    //                   80: 2544;
           goto _L36 _L37 _L38
_L36:
        view.collapsedDrawY = ((((CollapsingTextHelper) (view)).textPaint.descent() - ((CollapsingTextHelper) (view)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (view)).textPaint.descent()) + (float)((CollapsingTextHelper) (view)).collapsedBounds.centerY();
_L55:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 1332
    //                   1: 2582
    //                   5: 2602;
           goto _L39 _L40 _L41
_L39:
        view.collapsedDrawX = ((CollapsingTextHelper) (view)).collapsedBounds.left;
_L56:
        view.calculateUsingTextSize(((CollapsingTextHelper) (view)).expandedTextSize);
        if (((CollapsingTextHelper) (view)).textToDraw != null)
        {
            f = ((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = ((CollapsingTextHelper) (view)).expandedTextGravity;
        if (((CollapsingTextHelper) (view)).isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 1436
    //                   48: 2646
    //                   80: 2631;
           goto _L42 _L43 _L44
_L42:
        view.expandedDrawY = ((((CollapsingTextHelper) (view)).textPaint.descent() - ((CollapsingTextHelper) (view)).textPaint.ascent()) / 2.0F - ((CollapsingTextHelper) (view)).textPaint.descent()) + (float)((CollapsingTextHelper) (view)).expandedBounds.centerY();
_L57:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 1504
    //                   1: 2669
    //                   5: 2689;
           goto _L45 _L46 _L47
_L45:
        view.expandedDrawX = ((CollapsingTextHelper) (view)).expandedBounds.left;
_L58:
        if (((CollapsingTextHelper) (view)).expandedTitleTexture != null)
        {
            ((CollapsingTextHelper) (view)).expandedTitleTexture.recycle();
            view.expandedTitleTexture = null;
        }
        view.calculateUsingTextSize(f1);
        view.useTexture = false;
        if (((CollapsingTextHelper) (view)).useTexture && ((CollapsingTextHelper) (view)).expandedTitleTexture == null && !((CollapsingTextHelper) (view)).expandedBounds.isEmpty() && !TextUtils.isEmpty(((CollapsingTextHelper) (view)).textToDraw))
        {
            view.calculateOffsets(0.0F);
            view.textureAscent = ((CollapsingTextHelper) (view)).textPaint.ascent();
            view.textureDescent = ((CollapsingTextHelper) (view)).textPaint.descent();
            i = Math.round(((CollapsingTextHelper) (view)).textPaint.measureText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length()));
            int i1 = Math.round(((CollapsingTextHelper) (view)).textureDescent - ((CollapsingTextHelper) (view)).textureAscent);
            if (i > 0 && i1 > 0)
            {
                view.expandedTitleTexture = Bitmap.createBitmap(i, i1, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(((CollapsingTextHelper) (view)).expandedTitleTexture)).drawText(((CollapsingTextHelper) (view)).textToDraw, 0, ((CollapsingTextHelper) (view)).textToDraw.length(), 0.0F, (float)i1 - ((CollapsingTextHelper) (view)).textPaint.descent(), ((CollapsingTextHelper) (view)).textPaint);
                if (((CollapsingTextHelper) (view)).texturePaint == null)
                {
                    view.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(((CollapsingTextHelper) (view)).view);
        view.calculateOffsets(((CollapsingTextHelper) (view)).expandedFraction);
_L34:
        editText.addTextChangedListener(new _cls1());
        if (defaultHintTextColor == null)
        {
            defaultHintTextColor = editText.getHintTextColors();
        }
        if (hintEnabled)
        {
            if (TextUtils.isEmpty(hint))
            {
                originalHint = editText.getHint();
                view = originalHint;
                if (hintEnabled)
                {
                    if (!TextUtils.equals(view, hint))
                    {
                        hint = view;
                        layoutparams = collapsingTextHelper;
                        if (view == null || !view.equals(((CollapsingTextHelper) (layoutparams)).text))
                        {
                            layoutparams.text = view;
                            layoutparams.textToDraw = null;
                            if (((CollapsingTextHelper) (layoutparams)).expandedTitleTexture != null)
                            {
                                ((CollapsingTextHelper) (layoutparams)).expandedTitleTexture.recycle();
                                layoutparams.expandedTitleTexture = null;
                            }
                            layoutparams.recalculate();
                        }
                        if (!hintExpanded)
                        {
                            openCutout();
                        }
                    }
                    sendAccessibilityEvent(2048);
                }
                editText.setHint(null);
            }
            isProvidingHint = true;
        }
        if (counterView != null)
        {
            updateCounter(editText.getText().length());
        }
        indicatorViewController.adjustIndicatorPadding();
        updatePasswordToggleView();
        updateLabelState(false, true);
        return;
_L8:
        view.collapsedDrawY = ((CollapsingTextHelper) (view)).collapsedBounds.bottom;
        continue; /* Loop/switch isn't completed */
_L7:
        view.collapsedDrawY = (float)((CollapsingTextHelper) (view)).collapsedBounds.top - ((CollapsingTextHelper) (view)).textPaint.ascent();
        continue; /* Loop/switch isn't completed */
_L10:
        view.collapsedDrawX = (float)((CollapsingTextHelper) (view)).collapsedBounds.centerX() - f / 2.0F;
          goto _L48
_L11:
        view.collapsedDrawX = (float)((CollapsingTextHelper) (view)).collapsedBounds.right - f;
          goto _L48
_L14:
        view.expandedDrawY = ((CollapsingTextHelper) (view)).expandedBounds.bottom;
          goto _L49
_L13:
        view.expandedDrawY = (float)((CollapsingTextHelper) (view)).expandedBounds.top - ((CollapsingTextHelper) (view)).textPaint.ascent();
          goto _L49
_L16:
        view.expandedDrawX = (float)((CollapsingTextHelper) (view)).expandedBounds.centerX() - f / 2.0F;
          goto _L50
_L17:
        view.expandedDrawX = (float)((CollapsingTextHelper) (view)).expandedBounds.right - f;
          goto _L50
_L23:
        view.collapsedDrawY = ((CollapsingTextHelper) (view)).collapsedBounds.bottom;
          goto _L51
_L22:
        view.collapsedDrawY = (float)((CollapsingTextHelper) (view)).collapsedBounds.top - ((CollapsingTextHelper) (view)).textPaint.ascent();
          goto _L51
_L25:
        view.collapsedDrawX = (float)((CollapsingTextHelper) (view)).collapsedBounds.centerX() - f / 2.0F;
          goto _L52
_L26:
        view.collapsedDrawX = (float)((CollapsingTextHelper) (view)).collapsedBounds.right - f;
          goto _L52
_L29:
        view.expandedDrawY = ((CollapsingTextHelper) (view)).expandedBounds.bottom;
          goto _L53
_L28:
        view.expandedDrawY = (float)((CollapsingTextHelper) (view)).expandedBounds.top - ((CollapsingTextHelper) (view)).textPaint.ascent();
          goto _L53
_L31:
        view.expandedDrawX = (float)((CollapsingTextHelper) (view)).expandedBounds.centerX() - f / 2.0F;
          goto _L54
_L32:
        view.expandedDrawX = (float)((CollapsingTextHelper) (view)).expandedBounds.right - f;
          goto _L54
_L38:
        view.collapsedDrawY = ((CollapsingTextHelper) (view)).collapsedBounds.bottom;
          goto _L55
_L37:
        view.collapsedDrawY = (float)((CollapsingTextHelper) (view)).collapsedBounds.top - ((CollapsingTextHelper) (view)).textPaint.ascent();
          goto _L55
_L40:
        view.collapsedDrawX = (float)((CollapsingTextHelper) (view)).collapsedBounds.centerX() - f / 2.0F;
          goto _L56
_L41:
        view.collapsedDrawX = (float)((CollapsingTextHelper) (view)).collapsedBounds.right - f;
          goto _L56
_L44:
        view.expandedDrawY = ((CollapsingTextHelper) (view)).expandedBounds.bottom;
          goto _L57
_L43:
        view.expandedDrawY = (float)((CollapsingTextHelper) (view)).expandedBounds.top - ((CollapsingTextHelper) (view)).textPaint.ascent();
          goto _L57
_L46:
        view.expandedDrawX = (float)((CollapsingTextHelper) (view)).expandedBounds.centerX() - f / 2.0F;
          goto _L58
_L47:
        view.expandedDrawX = (float)((CollapsingTextHelper) (view)).expandedBounds.right - f;
          goto _L58
_L2:
        super.addView(view, i, layoutparams);
        return;
        if (true) goto _L60; else goto _L59
_L59:
    }

    public void dispatchProvideAutofillStructure(ViewStructure viewstructure, int i)
    {
        CharSequence charsequence;
        boolean flag;
        if (originalHint == null || editText == null)
        {
            super.dispatchProvideAutofillStructure(viewstructure, i);
            return;
        }
        flag = isProvidingHint;
        isProvidingHint = false;
        charsequence = editText.getHint();
        editText.setHint(originalHint);
        super.dispatchProvideAutofillStructure(viewstructure, i);
        editText.setHint(charsequence);
        isProvidingHint = flag;
        return;
        viewstructure;
        editText.setHint(charsequence);
        isProvidingHint = flag;
        throw viewstructure;
    }

    protected void dispatchRestoreInstanceState(SparseArray sparsearray)
    {
        restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparsearray);
        restoringSavedState = false;
    }

    public void draw(Canvas canvas)
    {
        if (boxBackground != null)
        {
            boxBackground.draw(canvas);
        }
        super.draw(canvas);
        if (hintEnabled)
        {
            CollapsingTextHelper collapsingtexthelper = collapsingTextHelper;
            int i = canvas.save();
            if (collapsingtexthelper.textToDraw != null && collapsingtexthelper.drawTitle)
            {
                float f3 = collapsingtexthelper.currentDrawX;
                float f2 = collapsingtexthelper.currentDrawY;
                float f;
                float f1;
                boolean flag;
                if (collapsingtexthelper.useTexture && collapsingtexthelper.expandedTitleTexture != null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    f = collapsingtexthelper.textureAscent * collapsingtexthelper.scale;
                } else
                {
                    collapsingtexthelper.textPaint.ascent();
                    f = 0.0F;
                    collapsingtexthelper.textPaint.descent();
                }
                f1 = f2;
                if (flag)
                {
                    f1 = f2 + f;
                }
                if (collapsingtexthelper.scale != 1.0F)
                {
                    canvas.scale(collapsingtexthelper.scale, collapsingtexthelper.scale, f3, f1);
                }
                if (flag)
                {
                    canvas.drawBitmap(collapsingtexthelper.expandedTitleTexture, f3, f1, collapsingtexthelper.texturePaint);
                } else
                {
                    canvas.drawText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length(), f3, f1, collapsingtexthelper.textPaint);
                }
            }
            canvas.restoreToCount(i);
        }
    }

    protected void drawableStateChanged()
    {
        float f;
        CollapsingTextHelper collapsingtexthelper;
        int i;
        boolean flag = true;
        if (inDrawableStateChanged)
        {
            return;
        }
        inDrawableStateChanged = true;
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        float f1;
        int j;
        boolean flag1;
        if (ViewCompat.isLaidOut(this) && isEnabled())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        updateLabelState(flag1, false);
        updateEditTextBackground();
        updateTextInputBoxBounds();
        updateTextInputBoxState();
        if (collapsingTextHelper == null) goto _L2; else goto _L1
_L1:
        collapsingtexthelper = collapsingTextHelper;
        collapsingtexthelper.state = ai;
        if (collapsingtexthelper.collapsedTextColor != null && collapsingtexthelper.collapsedTextColor.isStateful() || collapsingtexthelper.expandedTextColor != null && collapsingtexthelper.expandedTextColor.isStateful())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L4; else goto _L3
_L3:
        i = ((flag) ? 1 : 0);
        if (collapsingtexthelper.view.getHeight() <= 0) goto _L6; else goto _L5
_L5:
        i = ((flag) ? 1 : 0);
        if (collapsingtexthelper.view.getWidth() <= 0) goto _L6; else goto _L7
_L7:
        f1 = collapsingtexthelper.currentTextSize;
        collapsingtexthelper.calculateUsingTextSize(collapsingtexthelper.collapsedTextSize);
        if (collapsingtexthelper.textToDraw != null)
        {
            f = collapsingtexthelper.textPaint.measureText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = collapsingtexthelper.collapsedTextGravity;
        if (collapsingtexthelper.isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 264
    //                   48: 701
    //                   80: 684;
           goto _L8 _L9 _L10
_L8:
        collapsingtexthelper.collapsedDrawY = ((collapsingtexthelper.textPaint.descent() - collapsingtexthelper.textPaint.ascent()) / 2.0F - collapsingtexthelper.textPaint.descent()) + (float)collapsingtexthelper.collapsedBounds.centerY();
_L20:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 340
    //                   1: 727
    //                   5: 748;
           goto _L11 _L12 _L13
_L11:
        collapsingtexthelper.collapsedDrawX = collapsingtexthelper.collapsedBounds.left;
_L21:
        collapsingtexthelper.calculateUsingTextSize(collapsingtexthelper.expandedTextSize);
        if (collapsingtexthelper.textToDraw != null)
        {
            f = collapsingtexthelper.textPaint.measureText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        j = collapsingtexthelper.expandedTextGravity;
        if (collapsingtexthelper.isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(j, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 456
    //                   48: 795
    //                   80: 778;
           goto _L14 _L15 _L16
_L14:
        collapsingtexthelper.expandedDrawY = ((collapsingtexthelper.textPaint.descent() - collapsingtexthelper.textPaint.ascent()) / 2.0F - collapsingtexthelper.textPaint.descent()) + (float)collapsingtexthelper.expandedBounds.centerY();
_L22:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 532
    //                   1: 821
    //                   5: 842;
           goto _L17 _L18 _L19
_L17:
        collapsingtexthelper.expandedDrawX = collapsingtexthelper.expandedBounds.left;
_L23:
        if (collapsingtexthelper.expandedTitleTexture != null)
        {
            collapsingtexthelper.expandedTitleTexture.recycle();
            collapsingtexthelper.expandedTitleTexture = null;
        }
        collapsingtexthelper.calculateUsingTextSize(f1);
        collapsingtexthelper.useTexture = false;
        if (collapsingtexthelper.useTexture && collapsingtexthelper.expandedTitleTexture == null && !collapsingtexthelper.expandedBounds.isEmpty() && !TextUtils.isEmpty(collapsingtexthelper.textToDraw))
        {
            collapsingtexthelper.calculateOffsets(0.0F);
            collapsingtexthelper.textureAscent = collapsingtexthelper.textPaint.ascent();
            collapsingtexthelper.textureDescent = collapsingtexthelper.textPaint.descent();
            i = Math.round(collapsingtexthelper.textPaint.measureText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length()));
            int k = Math.round(collapsingtexthelper.textureDescent - collapsingtexthelper.textureAscent);
            if (i > 0 && k > 0)
            {
                collapsingtexthelper.expandedTitleTexture = Bitmap.createBitmap(i, k, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(collapsingtexthelper.expandedTitleTexture)).drawText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length(), 0.0F, (float)k - collapsingtexthelper.textPaint.descent(), collapsingtexthelper.textPaint);
                if (collapsingtexthelper.texturePaint == null)
                {
                    collapsingtexthelper.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(collapsingtexthelper.view);
        collapsingtexthelper.calculateOffsets(collapsingtexthelper.expandedFraction);
        i = ((flag) ? 1 : 0);
_L6:
        i |= 0;
_L24:
        if (i != 0)
        {
            invalidate();
        }
        inDrawableStateChanged = false;
        return;
_L10:
        collapsingtexthelper.collapsedDrawY = collapsingtexthelper.collapsedBounds.bottom;
          goto _L20
_L9:
        collapsingtexthelper.collapsedDrawY = (float)collapsingtexthelper.collapsedBounds.top - collapsingtexthelper.textPaint.ascent();
          goto _L20
_L12:
        collapsingtexthelper.collapsedDrawX = (float)collapsingtexthelper.collapsedBounds.centerX() - f / 2.0F;
          goto _L21
_L13:
        collapsingtexthelper.collapsedDrawX = (float)collapsingtexthelper.collapsedBounds.right - f;
          goto _L21
_L16:
        collapsingtexthelper.expandedDrawY = collapsingtexthelper.expandedBounds.bottom;
          goto _L22
_L15:
        collapsingtexthelper.expandedDrawY = (float)collapsingtexthelper.expandedBounds.top - collapsingtexthelper.textPaint.ascent();
          goto _L22
_L18:
        collapsingtexthelper.expandedDrawX = (float)collapsingtexthelper.expandedBounds.centerX() - f / 2.0F;
          goto _L23
_L19:
        collapsingtexthelper.expandedDrawX = (float)collapsingtexthelper.expandedBounds.right - f;
          goto _L23
_L4:
        i = 0;
          goto _L6
_L2:
        i = 0;
          goto _L24
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        super.onLayout(flag, i, j, k, l);
        if (boxBackground != null)
        {
            updateTextInputBoxBounds();
        }
        if (!hintEnabled || editText == null) goto _L2; else goto _L1
_L1:
        Object obj;
        int i1;
        obj = tmpRect;
        DescendantOffsetUtils.getDescendantRect(this, editText, ((Rect) (obj)));
        i = ((Rect) (obj)).left;
        k = editText.getCompoundPaddingLeft() + i;
        i1 = ((Rect) (obj)).right - editText.getCompoundPaddingRight();
        boxBackgroundMode;
        JVM INSTR tableswitch 1 2: default 112
    //                   1 326
    //                   2 283;
           goto _L3 _L4 _L5
_L3:
        i = getPaddingTop();
_L7:
        CollapsingTextHelper collapsingtexthelper = collapsingTextHelper;
        int j1 = ((Rect) (obj)).top + editText.getCompoundPaddingTop();
        int k1 = ((Rect) (obj)).bottom - editText.getCompoundPaddingBottom();
        if (!CollapsingTextHelper.rectEquals(collapsingtexthelper.expandedBounds, k, j1, i1, k1))
        {
            collapsingtexthelper.expandedBounds.set(k, j1, i1, k1);
            collapsingtexthelper.boundsChanged = true;
            collapsingtexthelper.onBoundsChanged();
        }
        obj = collapsingTextHelper;
        j = l - j - getPaddingBottom();
        if (!CollapsingTextHelper.rectEquals(((CollapsingTextHelper) (obj)).collapsedBounds, k, i, i1, j))
        {
            ((CollapsingTextHelper) (obj)).collapsedBounds.set(k, i, i1, j);
            obj.boundsChanged = true;
            ((CollapsingTextHelper) (obj)).onBoundsChanged();
        }
        collapsingTextHelper.recalculate();
        if (cutoutEnabled() && !hintExpanded)
        {
            openCutout();
        }
_L2:
        return;
_L5:
        if (boxBackgroundMode == 1 || boxBackgroundMode == 2)
        {
            i = boxBackground.getBounds().top - calculateLabelMarginTop();
        } else
        {
            throw new IllegalStateException();
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (boxBackgroundMode == 1 || boxBackgroundMode == 2)
        {
            i = boxBackground.getBounds().top + boxCollapsedPaddingTopPx;
        } else
        {
            throw new IllegalStateException();
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    protected void onMeasure(int i, int j)
    {
        updatePasswordToggleView();
        super.onMeasure(i, j);
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
label0:
        {
            if (!(parcelable instanceof SavedState))
            {
                super.onRestoreInstanceState(parcelable);
                return;
            }
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(((AbsSavedState) (parcelable)).mSuperState);
            CharSequence charsequence = ((SavedState) (parcelable)).error;
            if (!indicatorViewController.errorEnabled)
            {
                if (TextUtils.isEmpty(charsequence))
                {
                    break label0;
                }
                setErrorEnabled(true);
            }
            if (!TextUtils.isEmpty(charsequence))
            {
                IndicatorViewController indicatorviewcontroller = indicatorViewController;
                if (indicatorviewcontroller.captionAnimator != null)
                {
                    indicatorviewcontroller.captionAnimator.cancel();
                }
                indicatorviewcontroller.errorText = charsequence;
                indicatorviewcontroller.errorView.setText(charsequence);
                if (indicatorviewcontroller.captionDisplayed != 1)
                {
                    indicatorviewcontroller.captionToShow = 1;
                }
                indicatorviewcontroller.updateCaptionViewsVisibility(indicatorviewcontroller.captionDisplayed, indicatorviewcontroller.captionToShow, indicatorviewcontroller.shouldAnimateCaptionView(indicatorviewcontroller.errorView, charsequence));
            } else
            {
                indicatorViewController.hideError();
            }
        }
        if (((SavedState) (parcelable)).isPasswordToggledVisible)
        {
            passwordVisibilityToggleRequested(true);
        }
        requestLayout();
    }

    public Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        if (indicatorViewController.errorShouldBeShown())
        {
            CharSequence charsequence;
            if (indicatorViewController.errorEnabled)
            {
                charsequence = indicatorViewController.errorText;
            } else
            {
                charsequence = null;
            }
            savedstate.error = charsequence;
        }
        savedstate.isPasswordToggledVisible = passwordToggledVisible;
        return savedstate;
    }

    public final void passwordVisibilityToggleRequested(boolean flag)
    {
        if (passwordToggleEnabled)
        {
            int i = editText.getSelectionEnd();
            boolean flag1;
            if (editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                editText.setTransformationMethod(null);
                passwordToggledVisible = true;
            } else
            {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                passwordToggledVisible = false;
            }
            passwordToggleView.setChecked(passwordToggledVisible);
            if (flag)
            {
                passwordToggleView.jumpDrawablesToCurrentState();
            }
            editText.setSelection(i);
        }
    }

    public void setBoxBackgroundColor(int i)
    {
        if (boxBackgroundColor != i)
        {
            boxBackgroundColor = i;
            applyBoxAttributes();
        }
    }

    public void setBoxBackgroundColorResource(int i)
    {
        setBoxBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    public void setBoxBackgroundMode(int i)
    {
        if (i == boxBackgroundMode)
        {
            return;
        }
        boxBackgroundMode = i;
        if (boxBackgroundMode != 0) goto _L2; else goto _L1
_L1:
        boxBackground = null;
_L4:
        if (boxBackgroundMode != 0)
        {
            updateInputLayoutMargins();
        }
        updateTextInputBoxBounds();
        return;
_L2:
        if (boxBackgroundMode == 2 && hintEnabled && !(boxBackground instanceof CutoutDrawable))
        {
            boxBackground = new CutoutDrawable();
        } else
        if (!(boxBackground instanceof GradientDrawable))
        {
            boxBackground = new GradientDrawable();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void setBoxStrokeColor(int i)
    {
        if (focusedStrokeColor != i)
        {
            focusedStrokeColor = i;
            updateTextInputBoxState();
        }
    }

    public void setCounterMaxLength(int i)
    {
        if (counterMaxLength != i)
        {
            if (i > 0)
            {
                counterMaxLength = i;
            } else
            {
                counterMaxLength = -1;
            }
            if (counterEnabled)
            {
                if (editText == null)
                {
                    i = 0;
                } else
                {
                    i = editText.getText().length();
                }
                updateCounter(i);
            }
        }
    }

    public void setEnabled(boolean flag)
    {
        recursiveSetEnabled(this, flag);
        super.setEnabled(flag);
    }

    public void setErrorTextAppearance(int i)
    {
        IndicatorViewController indicatorviewcontroller = indicatorViewController;
        indicatorviewcontroller.errorTextAppearance = i;
        if (indicatorviewcontroller.errorView != null)
        {
            indicatorviewcontroller.textInputView.setTextAppearanceCompatWithErrorFallback(indicatorviewcontroller.errorView, i);
        }
    }

    public void setHelperTextTextAppearance(int i)
    {
        indicatorViewController.setHelperTextAppearance(i);
    }

    public void setHintTextAppearance(int i)
    {
        CollapsingTextHelper collapsingtexthelper;
        boolean flag;
        flag = true;
        collapsingtexthelper = collapsingTextHelper;
        Object obj = collapsingtexthelper.view.getContext();
        obj = new TintTypedArray(((Context) (obj)), ((Context) (obj)).obtainStyledAttributes(i, android.support.v7.appcompat.R.styleable.TextAppearance));
        int k = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(k))
        {
            collapsingtexthelper.collapsedTextColor = ((TintTypedArray) (obj)).getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
        }
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(k))
        {
            k = android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize;
            int i1 = (int)collapsingtexthelper.collapsedTextSize;
            collapsingtexthelper.collapsedTextSize = ((TintTypedArray) (obj)).mWrapped.getDimensionPixelSize(k, i1);
        }
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_shadowColor;
        collapsingtexthelper.collapsedShadowColor = ((TintTypedArray) (obj)).mWrapped.getInt(k, 0);
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_shadowDx;
        collapsingtexthelper.collapsedShadowDx = ((TintTypedArray) (obj)).mWrapped.getFloat(k, 0.0F);
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_shadowDy;
        collapsingtexthelper.collapsedShadowDy = ((TintTypedArray) (obj)).mWrapped.getFloat(k, 0.0F);
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_shadowRadius;
        collapsingtexthelper.collapsedShadowRadius = ((TintTypedArray) (obj)).mWrapped.getFloat(k, 0.0F);
        ((TintTypedArray) (obj)).mWrapped.recycle();
        collapsingtexthelper.collapsedTypeface = collapsingtexthelper.readFontFamilyTypeface(i);
        if (collapsingtexthelper.view.getHeight() <= 0 || collapsingtexthelper.view.getWidth() <= 0) goto _L2; else goto _L1
_L1:
        float f;
        float f1 = collapsingtexthelper.currentTextSize;
        collapsingtexthelper.calculateUsingTextSize(collapsingtexthelper.collapsedTextSize);
        int l;
        if (collapsingtexthelper.textToDraw != null)
        {
            f = collapsingtexthelper.textPaint.measureText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = collapsingtexthelper.collapsedTextGravity;
        if (collapsingtexthelper.isRtl)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 348
    //                   48: 764
    //                   80: 747;
           goto _L3 _L4 _L5
_L3:
        collapsingtexthelper.collapsedDrawY = ((collapsingtexthelper.textPaint.descent() - collapsingtexthelper.textPaint.ascent()) / 2.0F - collapsingtexthelper.textPaint.descent()) + (float)collapsingtexthelper.collapsedBounds.centerY();
_L15:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 420
    //                   1: 790
    //                   5: 811;
           goto _L6 _L7 _L8
_L6:
        collapsingtexthelper.collapsedDrawX = collapsingtexthelper.collapsedBounds.left;
_L16:
        collapsingtexthelper.calculateUsingTextSize(collapsingtexthelper.expandedTextSize);
        if (collapsingtexthelper.textToDraw != null)
        {
            f = collapsingtexthelper.textPaint.measureText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length());
        } else
        {
            f = 0.0F;
        }
        l = collapsingtexthelper.expandedTextGravity;
        if (collapsingtexthelper.isRtl)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = 0;
        }
        i = Gravity.getAbsoluteGravity(l, i);
        i & 0x70;
        JVM INSTR lookupswitch 2: default 532
    //                   48: 857
    //                   80: 840;
           goto _L9 _L10 _L11
_L9:
        collapsingtexthelper.expandedDrawY = ((collapsingtexthelper.textPaint.descent() - collapsingtexthelper.textPaint.ascent()) / 2.0F - collapsingtexthelper.textPaint.descent()) + (float)collapsingtexthelper.expandedBounds.centerY();
_L17:
        i & 0x800007;
        JVM INSTR lookupswitch 2: default 604
    //                   1: 883
    //                   5: 904;
           goto _L12 _L13 _L14
_L12:
        collapsingtexthelper.expandedDrawX = collapsingtexthelper.expandedBounds.left;
_L18:
        if (collapsingtexthelper.expandedTitleTexture != null)
        {
            collapsingtexthelper.expandedTitleTexture.recycle();
            collapsingtexthelper.expandedTitleTexture = null;
        }
        collapsingtexthelper.calculateUsingTextSize(f1);
        collapsingtexthelper.useTexture = false;
        if (collapsingtexthelper.useTexture && collapsingtexthelper.expandedTitleTexture == null && !collapsingtexthelper.expandedBounds.isEmpty() && !TextUtils.isEmpty(collapsingtexthelper.textToDraw))
        {
            collapsingtexthelper.calculateOffsets(0.0F);
            collapsingtexthelper.textureAscent = collapsingtexthelper.textPaint.ascent();
            collapsingtexthelper.textureDescent = collapsingtexthelper.textPaint.descent();
            i = Math.round(collapsingtexthelper.textPaint.measureText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length()));
            int j = Math.round(collapsingtexthelper.textureDescent - collapsingtexthelper.textureAscent);
            if (i > 0 && j > 0)
            {
                collapsingtexthelper.expandedTitleTexture = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
                (new Canvas(collapsingtexthelper.expandedTitleTexture)).drawText(collapsingtexthelper.textToDraw, 0, collapsingtexthelper.textToDraw.length(), 0.0F, (float)j - collapsingtexthelper.textPaint.descent(), collapsingtexthelper.textPaint);
                if (collapsingtexthelper.texturePaint == null)
                {
                    collapsingtexthelper.texturePaint = new Paint(3);
                }
            }
        }
        ViewCompat.postInvalidateOnAnimation(collapsingtexthelper.view);
        collapsingtexthelper.calculateOffsets(collapsingtexthelper.expandedFraction);
_L2:
        focusedTextColor = collapsingTextHelper.collapsedTextColor;
        if (editText != null)
        {
            updateLabelState(false, false);
            updateInputLayoutMargins();
        }
        return;
_L5:
        collapsingtexthelper.collapsedDrawY = collapsingtexthelper.collapsedBounds.bottom;
          goto _L15
_L4:
        collapsingtexthelper.collapsedDrawY = (float)collapsingtexthelper.collapsedBounds.top - collapsingtexthelper.textPaint.ascent();
          goto _L15
_L7:
        collapsingtexthelper.collapsedDrawX = (float)collapsingtexthelper.collapsedBounds.centerX() - f / 2.0F;
          goto _L16
_L8:
        collapsingtexthelper.collapsedDrawX = (float)collapsingtexthelper.collapsedBounds.right - f;
          goto _L16
_L11:
        collapsingtexthelper.expandedDrawY = collapsingtexthelper.expandedBounds.bottom;
          goto _L17
_L10:
        collapsingtexthelper.expandedDrawY = (float)collapsingtexthelper.expandedBounds.top - collapsingtexthelper.textPaint.ascent();
          goto _L17
_L13:
        collapsingtexthelper.expandedDrawX = (float)collapsingtexthelper.expandedBounds.centerX() - f / 2.0F;
          goto _L18
_L14:
        collapsingtexthelper.expandedDrawX = (float)collapsingtexthelper.expandedBounds.right - f;
          goto _L18
    }

    public void setPasswordVisibilityToggleContentDescription(int i)
    {
        CharSequence charsequence;
        if (i != 0)
        {
            charsequence = getResources().getText(i);
        } else
        {
            charsequence = null;
        }
        passwordToggleContentDesc = charsequence;
        if (passwordToggleView != null)
        {
            passwordToggleView.setContentDescription(charsequence);
        }
    }

    public void setPasswordVisibilityToggleDrawable(int i)
    {
        Drawable drawable;
        if (i != 0)
        {
            drawable = AppCompatResources.getDrawable(getContext(), i);
        } else
        {
            drawable = null;
        }
        passwordToggleDrawable = drawable;
        if (passwordToggleView != null)
        {
            passwordToggleView.setImageDrawable(drawable);
        }
    }

    final void setTextAppearanceCompatWithErrorFallback(TextView textview, int i)
    {
        boolean flag = true;
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L2; else goto _L1
_L1:
        textview.setTextAppearance(i);
_L3:
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        i = textview.getTextColors().getDefaultColor();
        if (i != -65281)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        i = ((flag) ? 1 : 0);
_L4:
        if (i != 0)
        {
            Exception exception;
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                textview.setTextAppearance(0x7f14020d);
            } else
            {
                textview.setTextAppearance(textview.getContext(), 0x7f14020d);
            }
            textview.setTextColor(ContextCompat.getColor(getContext(), 0x7f0d0361));
        }
        return;
_L2:
        textview.setTextAppearance(textview.getContext(), i);
          goto _L3
        exception;
        i = ((flag) ? 1 : 0);
          goto _L4
        i = 0;
          goto _L4
    }

    final void updateCounter(int i)
    {
        boolean flag1 = counterOverflowed;
        if (counterMaxLength == -1)
        {
            counterView.setText(String.valueOf(i));
            counterView.setContentDescription(null);
            counterOverflowed = false;
        } else
        {
            if (ViewCompat.getAccessibilityLiveRegion(counterView) == 1)
            {
                ViewCompat.setAccessibilityLiveRegion(counterView, 0);
            }
            boolean flag;
            if (i > counterMaxLength)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            counterOverflowed = flag;
            if (flag1 != counterOverflowed)
            {
                TextView textview = counterView;
                int j;
                if (counterOverflowed)
                {
                    j = counterOverflowTextAppearance;
                } else
                {
                    j = counterTextAppearance;
                }
                setTextAppearanceCompatWithErrorFallback(textview, j);
                if (counterOverflowed)
                {
                    ViewCompat.setAccessibilityLiveRegion(counterView, 1);
                }
            }
            counterView.setText(getContext().getString(0x7f130104, new Object[] {
                Integer.valueOf(i), Integer.valueOf(counterMaxLength)
            }));
            counterView.setContentDescription(getContext().getString(0x7f130103, new Object[] {
                Integer.valueOf(i), Integer.valueOf(counterMaxLength)
            }));
        }
        if (editText != null && flag1 != counterOverflowed)
        {
            updateLabelState(false, false);
            updateEditTextBackground();
        }
    }

    final void updateEditTextBackground()
    {
        Drawable drawable;
        if (editText != null)
        {
            if ((drawable = editText.getBackground()) != null)
            {
                int i = android.os.Build.VERSION.SDK_INT;
                if (i == 21 || i == 22)
                {
                    Drawable drawable1 = editText.getBackground();
                    if (drawable1 != null && !hasReconstructedEditTextBackground)
                    {
                        Drawable drawable2 = drawable1.getConstantState().newDrawable();
                        if (drawable1 instanceof DrawableContainer)
                        {
                            hasReconstructedEditTextBackground = android.support.design.internal.DrawableUtils.setContainerConstantStateV9((DrawableContainer)drawable1, drawable2.getConstantState());
                        }
                        if (!hasReconstructedEditTextBackground)
                        {
                            ViewCompat.setBackground(editText, drawable2);
                            hasReconstructedEditTextBackground = true;
                            onApplyBoxBackgroundMode();
                        }
                    }
                }
                if (DrawableUtils.canSafelyMutateDrawable(drawable))
                {
                    drawable = drawable.mutate();
                }
                if (indicatorViewController.errorShouldBeShown())
                {
                    IndicatorViewController indicatorviewcontroller = indicatorViewController;
                    int j;
                    if (indicatorviewcontroller.errorView != null)
                    {
                        j = indicatorviewcontroller.errorView.getCurrentTextColor();
                    } else
                    {
                        j = -1;
                    }
                    drawable.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(j, android.graphics.PorterDuff.Mode.SRC_IN));
                    return;
                }
                if (counterOverflowed && counterView != null)
                {
                    drawable.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(counterView.getCurrentTextColor(), android.graphics.PorterDuff.Mode.SRC_IN));
                    return;
                } else
                {
                    DrawableCompat.clearColorFilter(drawable);
                    editText.refreshDrawableState();
                    return;
                }
            }
        }
    }

    final void updateLabelState(boolean flag, boolean flag1)
    {
        boolean flag4;
        boolean flag5;
        boolean flag7;
        flag5 = false;
        boolean flag6 = isEnabled();
        boolean flag2;
        if (editText != null && !TextUtils.isEmpty(editText.getText()))
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (editText != null && editText.hasFocus())
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        flag7 = indicatorViewController.errorShouldBeShown();
        if (defaultHintTextColor != null)
        {
            collapsingTextHelper.setCollapsedTextColor(defaultHintTextColor);
            collapsingTextHelper.setExpandedTextColor(defaultHintTextColor);
        }
        if (flag6) goto _L2; else goto _L1
_L1:
        collapsingTextHelper.setCollapsedTextColor(ColorStateList.valueOf(disabledColor));
        collapsingTextHelper.setExpandedTextColor(ColorStateList.valueOf(disabledColor));
_L8:
        if (!flag2 && (!isEnabled() || !flag4 && !flag7)) goto _L4; else goto _L3
_L3:
        if (flag1 || hintExpanded)
        {
            if (animator != null && animator.isRunning())
            {
                animator.cancel();
            }
            Object obj;
            CollapsingTextHelper collapsingtexthelper;
            if (flag && hintAnimationEnabled)
            {
                animateToExpansionFraction(1.0F);
            } else
            {
                collapsingTextHelper.setExpansionFraction(1.0F);
            }
            hintExpanded = false;
            if (cutoutEnabled())
            {
                openCutout();
            }
        }
_L6:
        return;
_L2:
        if (flag7)
        {
            collapsingtexthelper = collapsingTextHelper;
            obj = indicatorViewController;
            if (((IndicatorViewController) (obj)).errorView != null)
            {
                obj = ((IndicatorViewController) (obj)).errorView.getTextColors();
            } else
            {
                obj = null;
            }
            collapsingtexthelper.setCollapsedTextColor(((ColorStateList) (obj)));
        } else
        if (counterOverflowed && counterView != null)
        {
            collapsingTextHelper.setCollapsedTextColor(counterView.getTextColors());
        } else
        if (flag4 && focusedTextColor != null)
        {
            collapsingTextHelper.setCollapsedTextColor(focusedTextColor);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (!flag1 && hintExpanded) goto _L6; else goto _L5
_L5:
        if (animator != null && animator.isRunning())
        {
            animator.cancel();
        }
        if (flag && hintAnimationEnabled)
        {
            animateToExpansionFraction(0.0F);
        } else
        {
            collapsingTextHelper.setExpansionFraction(0.0F);
        }
        if (cutoutEnabled())
        {
            boolean flag3 = flag5;
            if (!((CutoutDrawable)boxBackground).cutoutBounds.isEmpty())
            {
                flag3 = true;
            }
            if (flag3 && cutoutEnabled())
            {
                ((CutoutDrawable)boxBackground).setCutout(0.0F, 0.0F, 0.0F, 0.0F);
            }
        }
        hintExpanded = true;
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    final void updateTextInputBoxState()
    {
        boolean flag1 = true;
        if (boxBackground != null && boxBackgroundMode != 0)
        {
            boolean flag;
            if (editText != null && editText.hasFocus())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (editText == null || !editText.isHovered())
            {
                flag1 = false;
            }
            if (boxBackgroundMode == 2)
            {
                if (!isEnabled())
                {
                    boxStrokeColor = disabledColor;
                } else
                if (indicatorViewController.errorShouldBeShown())
                {
                    IndicatorViewController indicatorviewcontroller = indicatorViewController;
                    int i;
                    if (indicatorviewcontroller.errorView != null)
                    {
                        i = indicatorviewcontroller.errorView.getCurrentTextColor();
                    } else
                    {
                        i = -1;
                    }
                    boxStrokeColor = i;
                } else
                if (flag)
                {
                    boxStrokeColor = focusedStrokeColor;
                } else
                if (flag1)
                {
                    boxStrokeColor = hoveredStrokeColor;
                } else
                {
                    boxStrokeColor = defaultStrokeColor;
                }
                if ((flag1 || flag) && isEnabled())
                {
                    boxStrokeWidthPx = boxStrokeWidthFocusedPx;
                } else
                {
                    boxStrokeWidthPx = boxStrokeWidthDefaultPx;
                }
                applyBoxAttributes();
                return;
            }
        }
    }

    private class _cls3
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        private final TextInputLayout this$0;

        public final void onAnimationUpdate(ValueAnimator valueanimator)
        {
            collapsingTextHelper.setExpansionFraction(((Float)valueanimator.getAnimatedValue()).floatValue());
        }

        _cls3()
        {
            this$0 = TextInputLayout.this;
            super();
        }
    }


    private class _cls2
        implements android.view.View.OnClickListener
    {

        private final TextInputLayout this$0;

        public final void onClick(View view)
        {
            passwordVisibilityToggleRequested(false);
        }

        _cls2()
        {
            this$0 = TextInputLayout.this;
            super();
        }
    }


    private class _cls1
        implements TextWatcher
    {

        private final TextInputLayout this$0;

        public final void afterTextChanged(Editable editable)
        {
            TextInputLayout textinputlayout = TextInputLayout.this;
            boolean flag;
            if (!restoringSavedState)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            textinputlayout.updateLabelState(flag, false);
            if (counterEnabled)
            {
                updateCounter(editable.length());
            }
        }

        public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        _cls1()
        {
            this$0 = TextInputLayout.this;
            super();
        }
    }

}
