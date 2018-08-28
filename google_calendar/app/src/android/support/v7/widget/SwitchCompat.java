// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.text.AllCapsTransformationMethod;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Property;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, DrawableUtils, ViewUtils

public class SwitchCompat extends CompoundButton
{

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private static final Property THUMB_POS = new _cls1(java/lang/Float, "thumbPos");
    private boolean mHasThumbTint;
    private boolean mHasThumbTintMode;
    private boolean mHasTrackTint;
    private boolean mHasTrackTintMode;
    private int mMinFlingVelocity;
    private Layout mOffLayout;
    private Layout mOnLayout;
    private ObjectAnimator mPositionAnimator;
    private boolean mShowText;
    private boolean mSplitTrack;
    private int mSwitchBottom;
    private int mSwitchHeight;
    private int mSwitchLeft;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private int mSwitchRight;
    private int mSwitchTop;
    private TransformationMethod mSwitchTransformationMethod;
    private int mSwitchWidth;
    private final Rect mTempRect;
    private ColorStateList mTextColors;
    public CharSequence mTextOff;
    public CharSequence mTextOn;
    private final TextPaint mTextPaint;
    private Drawable mThumbDrawable;
    public float mThumbPosition;
    private int mThumbTextPadding;
    private ColorStateList mThumbTintList;
    private android.graphics.PorterDuff.Mode mThumbTintMode;
    private int mThumbWidth;
    private int mTouchMode;
    private int mTouchSlop;
    private float mTouchX;
    private float mTouchY;
    private Drawable mTrackDrawable;
    private ColorStateList mTrackTintList;
    private android.graphics.PorterDuff.Mode mTrackTintMode;
    private VelocityTracker mVelocityTracker;

    public SwitchCompat(Context context)
    {
        this(context, null);
    }

    public SwitchCompat(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f0100df);
    }

    public SwitchCompat(Context context, AttributeSet attributeset, int i)
    {
        Object obj;
        super(context, attributeset, i);
        mThumbTintList = null;
        mThumbTintMode = null;
        mHasThumbTint = false;
        mHasThumbTintMode = false;
        mTrackTintList = null;
        mTrackTintMode = null;
        mHasTrackTint = false;
        mHasTrackTintMode = false;
        mVelocityTracker = VelocityTracker.obtain();
        mTempRect = new Rect();
        mTextPaint = new TextPaint(1);
        obj = getResources();
        mTextPaint.density = ((Resources) (obj)).getDisplayMetrics().density;
        obj = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.SwitchCompat, i, 0));
        mThumbDrawable = ((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.SwitchCompat_android_thumb);
        if (mThumbDrawable != null)
        {
            mThumbDrawable.setCallback(this);
        }
        mTrackDrawable = ((TintTypedArray) (obj)).getDrawable(android.support.v7.appcompat.R.styleable.SwitchCompat_track);
        if (mTrackDrawable != null)
        {
            mTrackDrawable.setCallback(this);
        }
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_android_textOn;
        mTextOn = ((TintTypedArray) (obj)).mWrapped.getText(i);
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_android_textOff;
        mTextOff = ((TintTypedArray) (obj)).mWrapped.getText(i);
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_showText;
        mShowText = ((TintTypedArray) (obj)).mWrapped.getBoolean(i, true);
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_thumbTextPadding;
        mThumbTextPadding = ((TintTypedArray) (obj)).mWrapped.getDimensionPixelSize(i, 0);
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_switchMinWidth;
        mSwitchMinWidth = ((TintTypedArray) (obj)).mWrapped.getDimensionPixelSize(i, 0);
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_switchPadding;
        mSwitchPadding = ((TintTypedArray) (obj)).mWrapped.getDimensionPixelSize(i, 0);
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_splitTrack;
        mSplitTrack = ((TintTypedArray) (obj)).mWrapped.getBoolean(i, false);
        attributeset = ((TintTypedArray) (obj)).getColorStateList(android.support.v7.appcompat.R.styleable.SwitchCompat_thumbTint);
        if (attributeset != null)
        {
            mThumbTintList = attributeset;
            mHasThumbTint = true;
        }
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_thumbTintMode;
        attributeset = DrawableUtils.parseTintMode(((TintTypedArray) (obj)).mWrapped.getInt(i, -1), null);
        if (mThumbTintMode != attributeset)
        {
            mThumbTintMode = attributeset;
            mHasThumbTintMode = true;
        }
        if ((mHasThumbTint || mHasThumbTintMode) && mThumbDrawable != null && (mHasThumbTint || mHasThumbTintMode))
        {
            mThumbDrawable = mThumbDrawable.mutate();
            if (mHasThumbTint)
            {
                mThumbDrawable.setTintList(mThumbTintList);
            }
            if (mHasThumbTintMode)
            {
                mThumbDrawable.setTintMode(mThumbTintMode);
            }
            if (mThumbDrawable.isStateful())
            {
                mThumbDrawable.setState(getDrawableState());
            }
        }
        attributeset = ((TintTypedArray) (obj)).getColorStateList(android.support.v7.appcompat.R.styleable.SwitchCompat_trackTint);
        if (attributeset != null)
        {
            mTrackTintList = attributeset;
            mHasTrackTint = true;
        }
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_trackTintMode;
        attributeset = DrawableUtils.parseTintMode(((TintTypedArray) (obj)).mWrapped.getInt(i, -1), null);
        if (mTrackTintMode != attributeset)
        {
            mTrackTintMode = attributeset;
            mHasTrackTintMode = true;
        }
        if ((mHasTrackTint || mHasTrackTintMode) && mTrackDrawable != null && (mHasTrackTint || mHasTrackTintMode))
        {
            mTrackDrawable = mTrackDrawable.mutate();
            if (mHasTrackTint)
            {
                mTrackDrawable.setTintList(mTrackTintList);
            }
            if (mHasTrackTintMode)
            {
                mTrackDrawable.setTintMode(mTrackTintMode);
            }
            if (mTrackDrawable.isStateful())
            {
                mTrackDrawable.setState(getDrawableState());
            }
        }
        i = android.support.v7.appcompat.R.styleable.SwitchCompat_switchTextAppearance;
        i = ((TintTypedArray) (obj)).mWrapped.getResourceId(i, 0);
        if (i == 0) goto _L2; else goto _L1
_L1:
        int j;
        TintTypedArray tinttypedarray = new TintTypedArray(context, context.obtainStyledAttributes(i, android.support.v7.appcompat.R.styleable.TextAppearance));
        attributeset = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
        if (attributeset != null)
        {
            mTextColors = attributeset;
        } else
        {
            mTextColors = getTextColors();
        }
        i = android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize;
        i = tinttypedarray.mWrapped.getDimensionPixelSize(i, 0);
        if (i != 0 && (float)i != mTextPaint.getTextSize())
        {
            mTextPaint.setTextSize(i);
            requestLayout();
        }
        i = android.support.v7.appcompat.R.styleable.TextAppearance_android_typeface;
        i = tinttypedarray.mWrapped.getInt(i, -1);
        j = android.support.v7.appcompat.R.styleable.TextAppearance_android_textStyle;
        j = tinttypedarray.mWrapped.getInt(j, -1);
        i;
        JVM INSTR tableswitch 1 3: default 784
    //                   1 957
    //                   2 964
    //                   3 971;
           goto _L3 _L4 _L5 _L6
_L6:
        break MISSING_BLOCK_LABEL_971;
_L3:
        attributeset = null;
_L7:
        if (j > 0)
        {
            float f;
            boolean flag;
            if (attributeset == null)
            {
                attributeset = Typeface.defaultFromStyle(j);
            } else
            {
                attributeset = Typeface.create(attributeset, j);
            }
            setSwitchTypeface(attributeset);
            if (attributeset != null)
            {
                i = attributeset.getStyle();
            } else
            {
                i = 0;
            }
            i = j & ~i;
            attributeset = mTextPaint;
            if ((i & 1) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            attributeset.setFakeBoldText(flag);
            attributeset = mTextPaint;
            if ((i & 2) != 0)
            {
                f = -0.25F;
            } else
            {
                f = 0.0F;
            }
            attributeset.setTextSkewX(f);
        } else
        {
            mTextPaint.setFakeBoldText(false);
            mTextPaint.setTextSkewX(0.0F);
            setSwitchTypeface(attributeset);
        }
        i = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
        if (tinttypedarray.mWrapped.getBoolean(i, false))
        {
            mSwitchTransformationMethod = new AllCapsTransformationMethod(getContext());
        } else
        {
            mSwitchTransformationMethod = null;
        }
        tinttypedarray.mWrapped.recycle();
_L2:
        ((TintTypedArray) (obj)).mWrapped.recycle();
        context = ViewConfiguration.get(context);
        mTouchSlop = context.getScaledTouchSlop();
        mMinFlingVelocity = context.getScaledMinimumFlingVelocity();
        refreshDrawableState();
        setChecked(isChecked());
        return;
_L4:
        attributeset = Typeface.SANS_SERIF;
          goto _L7
_L5:
        attributeset = Typeface.SERIF;
          goto _L7
        attributeset = Typeface.MONOSPACE;
          goto _L7
    }

    private final int getThumbOffset()
    {
        float f;
        if (ViewUtils.isLayoutRtl(this))
        {
            f = 1.0F - mThumbPosition;
        } else
        {
            f = mThumbPosition;
        }
        return (int)(f * (float)getThumbScrollRange() + 0.5F);
    }

    private final int getThumbScrollRange()
    {
        if (mTrackDrawable != null)
        {
            Rect rect1 = mTempRect;
            mTrackDrawable.getPadding(rect1);
            Rect rect;
            if (mThumbDrawable != null)
            {
                rect = DrawableUtils.getOpticalBounds(mThumbDrawable);
            } else
            {
                rect = DrawableUtils.INSETS_NONE;
            }
            return mSwitchWidth - mThumbWidth - rect1.left - rect1.right - rect.left - rect.right;
        } else
        {
            return 0;
        }
    }

    private final Layout makeLayout(CharSequence charsequence)
    {
        if (mSwitchTransformationMethod != null)
        {
            charsequence = mSwitchTransformationMethod.getTransformation(charsequence, this);
        }
        TextPaint textpaint = mTextPaint;
        int i;
        if (charsequence != null)
        {
            i = (int)Math.ceil(Layout.getDesiredWidth(charsequence, mTextPaint));
        } else
        {
            i = 0;
        }
        return new StaticLayout(charsequence, textpaint, i, android.text.Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
    }

    private final void setSwitchTypeface(Typeface typeface)
    {
        if (mTextPaint.getTypeface() != null && !mTextPaint.getTypeface().equals(typeface) || mTextPaint.getTypeface() == null && typeface != null)
        {
            mTextPaint.setTypeface(typeface);
            requestLayout();
            invalidate();
        }
    }

    public void draw(Canvas canvas)
    {
        Rect rect = mTempRect;
        int j = mSwitchLeft;
        int i1 = mSwitchTop;
        int k1 = mSwitchRight;
        int j1 = mSwitchBottom;
        int l1 = j + getThumbOffset();
        Object obj;
        int i;
        if (mThumbDrawable != null)
        {
            obj = DrawableUtils.getOpticalBounds(mThumbDrawable);
        } else
        {
            obj = DrawableUtils.INSETS_NONE;
        }
        if (mTrackDrawable != null)
        {
            mTrackDrawable.getPadding(rect);
            int i2 = rect.left;
            int k;
            int l;
            if (obj != null)
            {
                i = j;
                if (((Rect) (obj)).left > rect.left)
                {
                    i = j + (((Rect) (obj)).left - rect.left);
                }
                if (((Rect) (obj)).top > rect.top)
                {
                    j = (((Rect) (obj)).top - rect.top) + i1;
                } else
                {
                    j = i1;
                }
                l = k1;
                if (((Rect) (obj)).right > rect.right)
                {
                    l = k1 - (((Rect) (obj)).right - rect.right);
                }
                if (((Rect) (obj)).bottom > rect.bottom)
                {
                    k = j1 - (((Rect) (obj)).bottom - rect.bottom);
                } else
                {
                    k = j1;
                }
            } else
            {
                k = j1;
                l = i1;
                i = j;
                j = l;
                l = k1;
            }
            mTrackDrawable.setBounds(i, j, l, k);
            i = i2 + l1;
        } else
        {
            i = l1;
        }
        if (mThumbDrawable != null)
        {
            mThumbDrawable.getPadding(rect);
            j = i - rect.left;
            i = i + mThumbWidth + rect.right;
            mThumbDrawable.setBounds(j, i1, i, j1);
            obj = getBackground();
            if (obj != null)
            {
                ((Drawable) (obj)).setHotspotBounds(j, i1, i, j1);
            }
        }
        super.draw(canvas);
    }

    public void drawableHotspotChanged(float f, float f1)
    {
        super.drawableHotspotChanged(f, f1);
        if (mThumbDrawable != null)
        {
            mThumbDrawable.setHotspot(f, f1);
        }
        if (mTrackDrawable != null)
        {
            mTrackDrawable.setHotspot(f, f1);
        }
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        int ai[] = getDrawableState();
        boolean flag1 = false;
        Drawable drawable = mThumbDrawable;
        boolean flag = flag1;
        if (drawable != null)
        {
            flag = flag1;
            if (drawable.isStateful())
            {
                flag = drawable.setState(ai) | false;
            }
        }
        drawable = mTrackDrawable;
        flag1 = flag;
        if (drawable != null)
        {
            flag1 = flag;
            if (drawable.isStateful())
            {
                flag1 = flag | drawable.setState(ai);
            }
        }
        if (flag1)
        {
            invalidate();
        }
    }

    public int getCompoundPaddingLeft()
    {
        int i;
        if (!ViewUtils.isLayoutRtl(this))
        {
            i = super.getCompoundPaddingLeft();
        } else
        {
            int j = super.getCompoundPaddingLeft() + mSwitchWidth;
            i = j;
            if (!TextUtils.isEmpty(getText()))
            {
                return j + mSwitchPadding;
            }
        }
        return i;
    }

    public int getCompoundPaddingRight()
    {
        int i;
        if (ViewUtils.isLayoutRtl(this))
        {
            i = super.getCompoundPaddingRight();
        } else
        {
            int j = super.getCompoundPaddingRight() + mSwitchWidth;
            i = j;
            if (!TextUtils.isEmpty(getText()))
            {
                return j + mSwitchPadding;
            }
        }
        return i;
    }

    public void jumpDrawablesToCurrentState()
    {
        super.jumpDrawablesToCurrentState();
        if (mThumbDrawable != null)
        {
            mThumbDrawable.jumpToCurrentState();
        }
        if (mTrackDrawable != null)
        {
            mTrackDrawable.jumpToCurrentState();
        }
        if (mPositionAnimator != null && mPositionAnimator.isStarted())
        {
            mPositionAnimator.end();
            mPositionAnimator = null;
        }
    }

    protected int[] onCreateDrawableState(int i)
    {
        int ai[] = super.onCreateDrawableState(i + 1);
        if (isChecked())
        {
            mergeDrawableStates(ai, CHECKED_STATE_SET);
        }
        return ai;
    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        Object obj = mTempRect;
        int ai[] = mTrackDrawable;
        Object obj1;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        if (ai != null)
        {
            ai.getPadding(((Rect) (obj)));
        } else
        {
            ((Rect) (obj)).setEmpty();
        }
        l = mSwitchTop;
        i1 = mSwitchBottom;
        j1 = ((Rect) (obj)).top;
        k1 = ((Rect) (obj)).bottom;
        obj1 = mThumbDrawable;
        if (ai != null)
        {
            if (mSplitTrack && obj1 != null)
            {
                Rect rect = DrawableUtils.getOpticalBounds(((Drawable) (obj1)));
                ((Drawable) (obj1)).copyBounds(((Rect) (obj)));
                obj.left = ((Rect) (obj)).left + rect.left;
                obj.right = ((Rect) (obj)).right - rect.right;
                int i = canvas.save();
                canvas.clipRect(((Rect) (obj)), android.graphics.Region.Op.DIFFERENCE);
                ai.draw(canvas);
                canvas.restoreToCount(i);
            } else
            {
                ai.draw(canvas);
            }
        }
        k = canvas.save();
        if (obj1 != null)
        {
            ((Drawable) (obj1)).draw(canvas);
        }
        if (mThumbPosition > 0.5F)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            obj = mOnLayout;
        } else
        {
            obj = mOffLayout;
        }
        if (obj != null)
        {
            ai = getDrawableState();
            if (mTextColors != null)
            {
                mTextPaint.setColor(mTextColors.getColorForState(ai, 0));
            }
            mTextPaint.drawableState = ai;
            int l1;
            if (obj1 != null)
            {
                obj1 = ((Drawable) (obj1)).getBounds();
                j = ((Rect) (obj1)).left;
                j = ((Rect) (obj1)).right + j;
            } else
            {
                j = getWidth();
            }
            j /= 2;
            l1 = ((Layout) (obj)).getWidth() / 2;
            l = (l + j1 + (i1 - k1)) / 2;
            i1 = ((Layout) (obj)).getHeight() / 2;
            canvas.translate(j - l1, l - i1);
            ((Layout) (obj)).draw(canvas);
        }
        canvas.restoreToCount(k);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName("android.widget.Switch");
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        CharSequence charsequence;
        CharSequence charsequence1;
label0:
        {
            super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
            accessibilitynodeinfo.setClassName("android.widget.Switch");
            if (isChecked())
            {
                charsequence = mTextOn;
            } else
            {
                charsequence = mTextOff;
            }
            if (!TextUtils.isEmpty(charsequence))
            {
                charsequence1 = accessibilitynodeinfo.getText();
                if (!TextUtils.isEmpty(charsequence1))
                {
                    break label0;
                }
                accessibilitynodeinfo.setText(charsequence);
            }
            return;
        }
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(charsequence1).append(' ').append(charsequence);
        accessibilitynodeinfo.setText(stringbuilder);
    }

    protected void onLayout(boolean flag, int i, int j, int k, int l)
    {
        boolean flag1 = false;
        super.onLayout(flag, i, j, k, l);
        if (mThumbDrawable != null)
        {
            Rect rect = mTempRect;
            Rect rect1;
            if (mTrackDrawable != null)
            {
                mTrackDrawable.getPadding(rect);
            } else
            {
                rect.setEmpty();
            }
            rect1 = DrawableUtils.getOpticalBounds(mThumbDrawable);
            j = Math.max(0, rect1.left - rect.left);
            i = Math.max(0, rect1.right - rect.right);
        } else
        {
            j = 0;
            i = ((flag1) ? 1 : 0);
        }
        if (ViewUtils.isLayoutRtl(this))
        {
            k = getPaddingLeft() + j;
            l = (mSwitchWidth + k) - j - i;
        } else
        {
            l = getWidth() - getPaddingRight() - i;
            k = i + (j + (l - mSwitchWidth));
        }
        getGravity() & 0x70;
        JVM INSTR lookupswitch 2: default 148
    //                   16: 220
    //                   80: 254;
           goto _L1 _L2 _L3
_L1:
        j = getPaddingTop();
        i = mSwitchHeight + j;
_L5:
        mSwitchLeft = k;
        mSwitchTop = j;
        mSwitchBottom = i;
        mSwitchRight = l;
        return;
_L2:
        j = ((getPaddingTop() + getHeight()) - getPaddingBottom()) / 2 - mSwitchHeight / 2;
        i = mSwitchHeight + j;
        continue; /* Loop/switch isn't completed */
_L3:
        i = getHeight() - getPaddingBottom();
        j = i - mSwitchHeight;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public void onMeasure(int i, int j)
    {
        int j1 = 0;
        if (mShowText)
        {
            if (mOnLayout == null)
            {
                mOnLayout = makeLayout(mTextOn);
            }
            if (mOffLayout == null)
            {
                mOffLayout = makeLayout(mTextOff);
            }
        }
        Rect rect = mTempRect;
        int k;
        int l;
        int i1;
        int k1;
        int l1;
        if (mThumbDrawable != null)
        {
            mThumbDrawable.getPadding(rect);
            l = mThumbDrawable.getIntrinsicWidth() - rect.left - rect.right;
            k = mThumbDrawable.getIntrinsicHeight();
        } else
        {
            k = 0;
            l = 0;
        }
        if (mShowText)
        {
            i1 = Math.max(mOnLayout.getWidth(), mOffLayout.getWidth()) + (mThumbTextPadding << 1);
        } else
        {
            i1 = 0;
        }
        mThumbWidth = Math.max(i1, l);
        if (mTrackDrawable != null)
        {
            mTrackDrawable.getPadding(rect);
            l = mTrackDrawable.getIntrinsicHeight();
        } else
        {
            rect.setEmpty();
            l = j1;
        }
        l1 = rect.left;
        k1 = rect.right;
        j1 = k1;
        i1 = l1;
        if (mThumbDrawable != null)
        {
            rect = DrawableUtils.getOpticalBounds(mThumbDrawable);
            i1 = Math.max(l1, rect.left);
            j1 = Math.max(k1, rect.right);
        }
        i1 = Math.max(mSwitchMinWidth, j1 + (i1 + mThumbWidth * 2));
        k = Math.max(l, k);
        mSwitchWidth = i1;
        mSwitchHeight = k;
        super.onMeasure(i, j);
        if (getMeasuredHeight() < k)
        {
            setMeasuredDimension(getMeasuredWidthAndState(), k);
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        super.onPopulateAccessibilityEvent(accessibilityevent);
        CharSequence charsequence;
        if (isChecked())
        {
            charsequence = mTextOn;
        } else
        {
            charsequence = mTextOff;
        }
        if (charsequence != null)
        {
            accessibilityevent.getText().add(charsequence);
        }
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        float f7;
        boolean flag1;
        boolean flag2;
        f7 = 1.0F;
        flag1 = false;
        flag2 = true;
        mVelocityTracker.addMovement(motionevent);
        motionevent.getActionMasked();
        JVM INSTR tableswitch 0 3: default 52
    //                   0 62
    //                   1 492
    //                   2 268
    //                   3 492;
           goto _L1 _L2 _L3 _L4 _L3
_L1:
        flag2 = super.onTouchEvent(motionevent);
_L6:
        return flag2;
_L2:
        float f = motionevent.getX();
        float f4 = motionevent.getY();
        if (isEnabled())
        {
            int i = ((flag1) ? 1 : 0);
            if (mThumbDrawable != null)
            {
                i = getThumbOffset();
                mThumbDrawable.getPadding(mTempRect);
                int k = mSwitchTop;
                int l = mTouchSlop;
                int i1 = (i + mSwitchLeft) - mTouchSlop;
                int j1 = mThumbWidth;
                int k1 = mTempRect.left;
                int l1 = mTempRect.right;
                int i2 = mTouchSlop;
                int j2 = mSwitchBottom;
                int k2 = mTouchSlop;
                i = ((flag1) ? 1 : 0);
                if (f > (float)i1)
                {
                    i = ((flag1) ? 1 : 0);
                    if (f < (float)(j1 + i1 + k1 + l1 + i2))
                    {
                        i = ((flag1) ? 1 : 0);
                        if (f4 > (float)(k - l))
                        {
                            i = ((flag1) ? 1 : 0);
                            if (f4 < (float)(j2 + k2))
                            {
                                i = 1;
                            }
                        }
                    }
                }
            }
            if (i != 0)
            {
                mTouchMode = 1;
                mTouchX = f;
                mTouchY = f4;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        float f2;
        float f6;
        switch (mTouchMode)
        {
        case 0: // '\0'
        default:
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            float f1 = motionevent.getX();
            float f5 = motionevent.getY();
            if (Math.abs(f1 - mTouchX) > (float)mTouchSlop || Math.abs(f5 - mTouchY) > (float)mTouchSlop)
            {
                mTouchMode = 2;
                getParent().requestDisallowInterceptTouchEvent(true);
                mTouchX = f1;
                mTouchY = f5;
                return true;
            }
            continue; /* Loop/switch isn't completed */

        case 2: // '\002'
            float f8 = motionevent.getX();
            int j = getThumbScrollRange();
            f2 = f8 - mTouchX;
            if (j != 0)
            {
                f2 /= j;
            } else
            if (f2 > 0.0F)
            {
                f2 = 1.0F;
            } else
            {
                f2 = -1F;
            }
            f6 = f2;
            if (ViewUtils.isLayoutRtl(this))
            {
                f6 = -f2;
            }
            f6 += mThumbPosition;
            break;
        }
        if (f6 >= 0.0F)
        {
            break; /* Loop/switch isn't completed */
        }
        f2 = 0.0F;
_L7:
        if (f2 != mThumbPosition)
        {
            mTouchX = f8;
            mThumbPosition = f2;
            invalidate();
            return true;
        }
        if (true) goto _L6; else goto _L5
_L5:
        f2 = f7;
        if (f6 <= 1.0F)
        {
            f2 = f6;
        }
          goto _L7
          goto _L6
_L3:
        if (mTouchMode == 2)
        {
            mTouchMode = 0;
            boolean flag;
            boolean flag3;
            boolean flag4;
            if (motionevent.getAction() == 1 && isEnabled())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            flag4 = isChecked();
            if (flag)
            {
                mVelocityTracker.computeCurrentVelocity(1000);
                float f3 = mVelocityTracker.getXVelocity();
                MotionEvent motionevent1;
                if (Math.abs(f3) > (float)mMinFlingVelocity)
                {
                    if (ViewUtils.isLayoutRtl(this))
                    {
                        if (f3 < 0.0F)
                        {
                            flag3 = true;
                        } else
                        {
                            flag3 = false;
                        }
                    } else
                    if (f3 > 0.0F)
                    {
                        flag3 = true;
                    } else
                    {
                        flag3 = false;
                    }
                } else
                if (mThumbPosition > 0.5F)
                {
                    flag3 = true;
                } else
                {
                    flag3 = false;
                }
            } else
            {
                flag3 = flag4;
            }
            if (flag3 != flag4)
            {
                playSoundEffect(0);
            }
            setChecked(flag3);
            motionevent1 = MotionEvent.obtain(motionevent);
            motionevent1.setAction(3);
            super.onTouchEvent(motionevent1);
            motionevent1.recycle();
            super.onTouchEvent(motionevent);
            return true;
        }
        mTouchMode = 0;
        mVelocityTracker.clear();
        if (true) goto _L1; else goto _L8
_L8:
    }

    public void setChecked(boolean flag)
    {
        float f = 1.0F;
        super.setChecked(flag);
        flag = isChecked();
        if (getWindowToken() != null && ViewCompat.isLaidOut(this))
        {
            if (!flag)
            {
                f = 0.0F;
            }
            mPositionAnimator = ObjectAnimator.ofFloat(this, THUMB_POS, new float[] {
                f
            });
            mPositionAnimator.setDuration(250L);
            mPositionAnimator.setAutoCancel(true);
            mPositionAnimator.start();
            return;
        }
        if (mPositionAnimator != null)
        {
            mPositionAnimator.cancel();
        }
        if (!flag)
        {
            f = 0.0F;
        }
        mThumbPosition = f;
        invalidate();
    }

    public void setCustomSelectionActionModeCallback(android.view.ActionMode.Callback callback)
    {
        super.setCustomSelectionActionModeCallback(TextViewCompat.wrapCustomSelectionActionModeCallback(this, callback));
    }

    public void setSwitchMinWidth(int i)
    {
        mSwitchMinWidth = i;
        requestLayout();
    }

    public void setSwitchPadding(int i)
    {
        mSwitchPadding = i;
        requestLayout();
    }

    public void setThumbResource(int i)
    {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), i);
        if (mThumbDrawable != null)
        {
            mThumbDrawable.setCallback(null);
        }
        mThumbDrawable = drawable;
        if (drawable != null)
        {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void setThumbTextPadding(int i)
    {
        mThumbTextPadding = i;
        requestLayout();
    }

    public void setTrackResource(int i)
    {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), i);
        if (mTrackDrawable != null)
        {
            mTrackDrawable.setCallback(null);
        }
        mTrackDrawable = drawable;
        if (drawable != null)
        {
            drawable.setCallback(this);
        }
        requestLayout();
    }

    public void toggle()
    {
        boolean flag;
        if (!isChecked())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        setChecked(flag);
    }

    protected boolean verifyDrawable(Drawable drawable)
    {
        return super.verifyDrawable(drawable) || drawable == mThumbDrawable || drawable == mTrackDrawable;
    }


    private class _cls1 extends Property
    {

        public final Object get(Object obj)
        {
            return Float.valueOf(((SwitchCompat)obj).mThumbPosition);
        }

        public final void set(Object obj, Object obj1)
        {
            obj = (SwitchCompat)obj;
            obj.mThumbPosition = ((Float)obj1).floatValue();
            ((SwitchCompat) (obj)).invalidate();
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
