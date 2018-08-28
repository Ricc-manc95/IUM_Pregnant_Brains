// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.AutoSizeableTextView;
import android.support.v4.widget.TextViewCompat;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

// Referenced classes of package android.support.v7.widget:
//            AppCompatTextViewAutoSizeHelper, AppCompatDrawableManager, TintInfo, TintTypedArray, 
//            AppCompatEditText

final class AppCompatTextHelper
{

    public boolean mAsyncFontPending;
    public final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTopTint;
    public Typeface mFontTypeface;
    public int mStyle;
    private final TextView mView;

    AppCompatTextHelper(TextView textview)
    {
        mStyle = 0;
        mView = textview;
        mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(mView);
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appcompatdrawablemanager, int i)
    {
        context = appcompatdrawablemanager.getTintList(context, i);
        if (context != null)
        {
            appcompatdrawablemanager = new TintInfo();
            appcompatdrawablemanager.mHasTintList = true;
            appcompatdrawablemanager.mTintList = context;
            return appcompatdrawablemanager;
        } else
        {
            return null;
        }
    }

    private final void updateTypefaceAndStyle(Context context, TintTypedArray tinttypedarray)
    {
        int i;
        i = android.support.v7.appcompat.R.styleable.TextAppearance_android_textStyle;
        int l = mStyle;
        mStyle = tinttypedarray.mWrapped.getInt(i, l);
        i = android.support.v7.appcompat.R.styleable.TextAppearance_android_fontFamily;
        if (tinttypedarray.mWrapped.hasValue(i)) goto _L2; else goto _L1
_L1:
        i = android.support.v7.appcompat.R.styleable.TextAppearance_fontFamily;
        if (!tinttypedarray.mWrapped.hasValue(i)) goto _L3; else goto _L2
_L2:
        int i1;
        int j1;
        mFontTypeface = null;
        int j = android.support.v7.appcompat.R.styleable.TextAppearance_fontFamily;
        if (tinttypedarray.mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.TextAppearance_fontFamily;
        } else
        {
            j = android.support.v7.appcompat.R.styleable.TextAppearance_android_fontFamily;
        }
        if (context.isRestricted()) goto _L5; else goto _L4
_L4:
        context = new _cls1();
        i1 = mStyle;
        j1 = tinttypedarray.mWrapped.getResourceId(j, 0);
        if (j1 != 0) goto _L7; else goto _L6
_L6:
        context = null;
_L11:
        mFontTypeface = context;
        Context context1;
        TypedValue typedvalue;
        boolean flag;
        if (mFontTypeface == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        int k;
        try
        {
            mAsyncFontPending = flag;
        }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
        // Misplaced declaration of an exception variable
        catch (Context context) { }
_L5:
        if (mFontTypeface == null)
        {
            context = tinttypedarray.mWrapped.getString(j);
            if (context != null)
            {
                mFontTypeface = Typeface.create(context, mStyle);
            }
        }
_L9:
        return;
_L7:
        if (tinttypedarray.mTypedValue == null)
        {
            tinttypedarray.mTypedValue = new TypedValue();
        }
        context1 = tinttypedarray.mContext;
        typedvalue = tinttypedarray.mTypedValue;
        if (context1.isRestricted())
        {
            context = null;
            continue; /* Loop/switch isn't completed */
        }
        context = ResourcesCompat.loadFont(context1, j1, typedvalue, i1, context, null, true);
        continue; /* Loop/switch isn't completed */
_L3:
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_typeface;
        if (!tinttypedarray.mWrapped.hasValue(k)) goto _L9; else goto _L8
_L8:
        mAsyncFontPending = false;
        k = android.support.v7.appcompat.R.styleable.TextAppearance_android_typeface;
        switch (tinttypedarray.mWrapped.getInt(k, 1))
        {
        default:
            return;

        case 1: // '\001'
            mFontTypeface = Typeface.SANS_SERIF;
            return;

        case 2: // '\002'
            mFontTypeface = Typeface.SERIF;
            return;

        case 3: // '\003'
            mFontTypeface = Typeface.MONOSPACE;
            break;
        }
        return;
        if (true) goto _L11; else goto _L10
_L10:
    }

    final void applyCompoundDrawablesTints()
    {
        if (mDrawableLeftTint != null || mDrawableTopTint != null || mDrawableRightTint != null || mDrawableBottomTint != null)
        {
            android.graphics.drawable.Drawable adrawable[] = mView.getCompoundDrawables();
            Object obj = adrawable[0];
            TintInfo tintinfo = mDrawableLeftTint;
            if (obj != null && tintinfo != null)
            {
                AppCompatDrawableManager.tintDrawable(((android.graphics.drawable.Drawable) (obj)), tintinfo, mView.getDrawableState());
            }
            obj = adrawable[1];
            tintinfo = mDrawableTopTint;
            if (obj != null && tintinfo != null)
            {
                AppCompatDrawableManager.tintDrawable(((android.graphics.drawable.Drawable) (obj)), tintinfo, mView.getDrawableState());
            }
            obj = adrawable[2];
            tintinfo = mDrawableRightTint;
            if (obj != null && tintinfo != null)
            {
                AppCompatDrawableManager.tintDrawable(((android.graphics.drawable.Drawable) (obj)), tintinfo, mView.getDrawableState());
            }
            android.graphics.drawable.Drawable drawable = adrawable[3];
            obj = mDrawableBottomTint;
            if (drawable != null && obj != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable, ((TintInfo) (obj)), mView.getDrawableState());
            }
        }
        if (mDrawableStartTint != null || mDrawableEndTint != null)
        {
            android.graphics.drawable.Drawable adrawable1[] = mView.getCompoundDrawablesRelative();
            Object obj1 = adrawable1[0];
            TintInfo tintinfo1 = mDrawableStartTint;
            if (obj1 != null && tintinfo1 != null)
            {
                AppCompatDrawableManager.tintDrawable(((android.graphics.drawable.Drawable) (obj1)), tintinfo1, mView.getDrawableState());
            }
            android.graphics.drawable.Drawable drawable1 = adrawable1[2];
            obj1 = mDrawableEndTint;
            if (drawable1 != null && obj1 != null)
            {
                AppCompatDrawableManager.tintDrawable(drawable1, ((TintInfo) (obj1)), mView.getDrawableState());
            }
        }
    }

    final boolean isAutoSizeEnabled()
    {
        AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper = mAutoSizeTextHelper;
        boolean flag;
        if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag && appcompattextviewautosizehelper.mAutoSizeTextType != 0;
    }

    final void loadFromAttributes(AttributeSet attributeset, int i)
    {
        Context context = mView.getContext();
        AppCompatDrawableManager appcompatdrawablemanager = AppCompatDrawableManager.get();
        Object obj = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextHelper, i, 0));
        int j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_textAppearance;
        int i1 = ((TintTypedArray) (obj)).mWrapped.getResourceId(j, -1);
        j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableLeft;
            mDrawableLeftTint = createTintInfo(context, appcompatdrawablemanager, ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0));
        }
        j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableTop;
            mDrawableTopTint = createTintInfo(context, appcompatdrawablemanager, ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0));
        }
        j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableRight;
            mDrawableRightTint = createTintInfo(context, appcompatdrawablemanager, ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0));
        }
        j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableBottom;
            mDrawableBottomTint = createTintInfo(context, appcompatdrawablemanager, ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0));
        }
        j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart;
            mDrawableStartTint = createTintInfo(context, appcompatdrawablemanager, ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0));
        }
        j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd;
        if (((TintTypedArray) (obj)).mWrapped.hasValue(j))
        {
            j = android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd;
            mDrawableEndTint = createTintInfo(context, appcompatdrawablemanager, ((TintTypedArray) (obj)).mWrapped.getResourceId(j, 0));
        }
        ((TintTypedArray) (obj)).mWrapped.recycle();
        boolean flag4 = mView.getTransformationMethod() instanceof PasswordTransformationMethod;
        boolean flag1 = false;
        boolean flag2 = false;
        j = 0;
        boolean flag = false;
        obj = null;
        int ai[] = null;
        Object obj1 = null;
        int ai1[] = null;
        if (i1 != -1)
        {
            Object obj3 = new TintTypedArray(context, context.obtainStyledAttributes(i1, android.support.v7.appcompat.R.styleable.TextAppearance));
            j = ((flag) ? 1 : 0);
            flag1 = flag2;
            if (!flag4)
            {
                int j1 = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
                j = ((flag) ? 1 : 0);
                flag1 = flag2;
                if (((TintTypedArray) (obj3)).mWrapped.hasValue(j1))
                {
                    j = 1;
                    int k = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
                    flag1 = ((TintTypedArray) (obj3)).mWrapped.getBoolean(k, false);
                }
            }
            updateTypefaceAndStyle(context, ((TintTypedArray) (obj3)));
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                int l = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor;
                ai = ((int []) (obj));
                if (((TintTypedArray) (obj3)).mWrapped.hasValue(l))
                {
                    ai = ((TintTypedArray) (obj3)).getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
                }
                l = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint;
                if (((TintTypedArray) (obj3)).mWrapped.hasValue(l))
                {
                    ai1 = ((TintTypedArray) (obj3)).getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint);
                }
                l = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink;
                float f;
                float f1;
                float f2;
                float f3;
                android.content.res.ColorStateList colorstatelist;
                TintTypedArray tinttypedarray;
                int k1;
                boolean flag3;
                if (((TintTypedArray) (obj3)).mWrapped.hasValue(l))
                {
                    obj = ((TintTypedArray) (obj3)).getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink);
                    obj1 = ai;
                    ai = ai1;
                } else
                {
                    obj1 = ai;
                    obj = null;
                    ai = ai1;
                }
            } else
            {
                obj1 = null;
                obj = null;
            }
            ((TintTypedArray) (obj3)).mWrapped.recycle();
        } else
        {
            Object obj2 = null;
            obj = null;
            ai = ((int []) (obj1));
            obj1 = obj2;
        }
        tinttypedarray = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.TextAppearance, i, 0));
        l = j;
        flag3 = flag1;
        if (!flag4)
        {
            k1 = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
            l = j;
            flag3 = flag1;
            if (tinttypedarray.mWrapped.hasValue(k1))
            {
                l = 1;
                j = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
                flag3 = tinttypedarray.mWrapped.getBoolean(j, false);
            }
        }
        colorstatelist = ((android.content.res.ColorStateList) (obj));
        ai1 = ai;
        obj3 = obj1;
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            j = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor;
            if (tinttypedarray.mWrapped.hasValue(j))
            {
                obj1 = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
            }
            j = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint;
            if (tinttypedarray.mWrapped.hasValue(j))
            {
                ai = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorHint);
            }
            j = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink;
            colorstatelist = ((android.content.res.ColorStateList) (obj));
            ai1 = ai;
            obj3 = obj1;
            if (tinttypedarray.mWrapped.hasValue(j))
            {
                colorstatelist = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColorLink);
                obj3 = obj1;
                ai1 = ai;
            }
        }
        updateTypefaceAndStyle(context, tinttypedarray);
        tinttypedarray.mWrapped.recycle();
        if (obj3 != null)
        {
            mView.setTextColor(((android.content.res.ColorStateList) (obj3)));
        }
        if (ai1 != null)
        {
            mView.setHintTextColor(ai1);
        }
        if (colorstatelist != null)
        {
            mView.setLinkTextColor(colorstatelist);
        }
        if (!flag4 && l)
        {
            mView.setAllCaps(flag3);
        }
        if (mFontTypeface != null)
        {
            mView.setTypeface(mFontTypeface, mStyle);
        }
        ai = mAutoSizeTextHelper;
        f1 = -1F;
        f2 = -1F;
        f = -1F;
        obj = ((AppCompatTextViewAutoSizeHelper) (ai)).mContext.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextView, i, 0);
        if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeTextType))
        {
            ai.mAutoSizeTextType = ((TypedArray) (obj)).getInt(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeStepGranularity))
        {
            f = ((TypedArray) (obj)).getDimension(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeStepGranularity, -1F);
        }
        if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMinTextSize))
        {
            f1 = ((TypedArray) (obj)).getDimension(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMinTextSize, -1F);
        }
        if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMaxTextSize))
        {
            f2 = ((TypedArray) (obj)).getDimension(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1F);
        }
        if (((TypedArray) (obj)).hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizePresetSizes))
        {
            i = ((TypedArray) (obj)).getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
            if (i > 0)
            {
                obj1 = ((TypedArray) (obj)).getResources().obtainTypedArray(i);
                j = ((TypedArray) (obj1)).length();
                ai1 = new int[j];
                if (j > 0)
                {
                    for (i = 0; i < j; i++)
                    {
                        ai1[i] = ((TypedArray) (obj1)).getDimensionPixelSize(i, -1);
                    }

                    ai.mAutoSizeTextSizesInPx = AppCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes(ai1);
                    ai.setupAutoSizeUniformPresetSizesConfiguration();
                }
                ((TypedArray) (obj1)).recycle();
            }
        }
        ((TypedArray) (obj)).recycle();
        if (!(((AppCompatTextViewAutoSizeHelper) (ai)).mTextView instanceof AppCompatEditText))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            if (((AppCompatTextViewAutoSizeHelper) (ai)).mAutoSizeTextType == 1)
            {
                if (!((AppCompatTextViewAutoSizeHelper) (ai)).mHasPresetAutoSizeValues)
                {
                    obj = ((AppCompatTextViewAutoSizeHelper) (ai)).mContext.getResources().getDisplayMetrics();
                    f3 = f1;
                    if (f1 == -1F)
                    {
                        f3 = TypedValue.applyDimension(2, 12F, ((android.util.DisplayMetrics) (obj)));
                    }
                    f1 = f2;
                    if (f2 == -1F)
                    {
                        f1 = TypedValue.applyDimension(2, 112F, ((android.util.DisplayMetrics) (obj)));
                    }
                    f2 = f;
                    if (f == -1F)
                    {
                        f2 = 1.0F;
                    }
                    ai.validateAndSetAutoSizeTextTypeUniformConfiguration(f3, f1, f2);
                }
                ai.setupAutoSizeText();
            }
        } else
        {
            ai.mAutoSizeTextType = 0;
        }
        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && mAutoSizeTextHelper.mAutoSizeTextType != 0)
        {
            ai = mAutoSizeTextHelper.mAutoSizeTextSizesInPx;
            if (ai.length > 0)
            {
                if ((float)mView.getAutoSizeStepGranularity() != -1F)
                {
                    mView.setAutoSizeTextTypeUniformWithConfiguration(Math.round(mAutoSizeTextHelper.mAutoSizeMinTextSizeInPx), Math.round(mAutoSizeTextHelper.mAutoSizeMaxTextSizeInPx), Math.round(mAutoSizeTextHelper.mAutoSizeStepGranularityInPx), 0);
                } else
                {
                    mView.setAutoSizeTextTypeUniformWithPresetSizes(ai, 0);
                }
            }
        }
        attributeset = new TintTypedArray(context, context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextView));
        i = android.support.v7.appcompat.R.styleable.AppCompatTextView_firstBaselineToTopHeight;
        i = ((TintTypedArray) (attributeset)).mWrapped.getDimensionPixelSize(i, -1);
        j = android.support.v7.appcompat.R.styleable.AppCompatTextView_lastBaselineToBottomHeight;
        j = ((TintTypedArray) (attributeset)).mWrapped.getDimensionPixelSize(j, -1);
        l = android.support.v7.appcompat.R.styleable.AppCompatTextView_lineHeight;
        l = ((TintTypedArray) (attributeset)).mWrapped.getDimensionPixelSize(l, -1);
        ((TintTypedArray) (attributeset)).mWrapped.recycle();
        if (i != -1)
        {
            TextViewCompat.setFirstBaselineToTopHeight(mView, i);
        }
        if (j != -1)
        {
            TextViewCompat.setLastBaselineToBottomHeight(mView, j);
        }
        if (l != -1)
        {
            TextViewCompat.setLineHeight(mView, l);
        }
    }

    final void onSetTextAppearance(Context context, int i)
    {
        TintTypedArray tinttypedarray = new TintTypedArray(context, context.obtainStyledAttributes(i, android.support.v7.appcompat.R.styleable.TextAppearance));
        i = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
        if (tinttypedarray.mWrapped.hasValue(i))
        {
            i = android.support.v7.appcompat.R.styleable.TextAppearance_textAllCaps;
            boolean flag = tinttypedarray.mWrapped.getBoolean(i, false);
            mView.setAllCaps(flag);
        }
        if (android.os.Build.VERSION.SDK_INT < 23)
        {
            i = android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor;
            if (tinttypedarray.mWrapped.hasValue(i))
            {
                android.content.res.ColorStateList colorstatelist = tinttypedarray.getColorStateList(android.support.v7.appcompat.R.styleable.TextAppearance_android_textColor);
                if (colorstatelist != null)
                {
                    mView.setTextColor(colorstatelist);
                }
            }
        }
        updateTypefaceAndStyle(context, tinttypedarray);
        tinttypedarray.mWrapped.recycle();
        if (mFontTypeface != null)
        {
            mView.setTypeface(mFontTypeface, mStyle);
        }
    }

    final void setAutoSizeTextTypeUniformWithConfiguration(int i, int j, int k, int l)
        throws IllegalArgumentException
    {
        AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper = mAutoSizeTextHelper;
        boolean flag;
        if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            android.util.DisplayMetrics displaymetrics = appcompattextviewautosizehelper.mContext.getResources().getDisplayMetrics();
            appcompattextviewautosizehelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(l, i, displaymetrics), TypedValue.applyDimension(l, j, displaymetrics), TypedValue.applyDimension(l, k, displaymetrics));
            if (appcompattextviewautosizehelper.setupAutoSizeText())
            {
                appcompattextviewautosizehelper.autoSizeText();
            }
        }
    }

    final void setAutoSizeTextTypeUniformWithPresetSizes(int ai[], int i)
        throws IllegalArgumentException
    {
        int ai1[];
        int ai2[];
        AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper;
        boolean flag1;
        int k;
        flag1 = false;
        appcompattextviewautosizehelper = mAutoSizeTextHelper;
        boolean flag;
        if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_179;
        }
        k = ai.length;
        if (k <= 0) goto _L2; else goto _L1
_L1:
        ai2 = new int[k];
        if (i != 0) goto _L4; else goto _L3
_L3:
        ai1 = Arrays.copyOf(ai, k);
_L6:
        appcompattextviewautosizehelper.mAutoSizeTextSizesInPx = AppCompatTextViewAutoSizeHelper.cleanupAutoSizePresetSizes(ai1);
        if (!appcompattextviewautosizehelper.setupAutoSizeUniformPresetSizesConfiguration())
        {
            throw new IllegalArgumentException((new StringBuilder("None of the preset sizes is valid: ")).append(Arrays.toString(ai)).toString());
        }
        break; /* Loop/switch isn't completed */
_L4:
        android.util.DisplayMetrics displaymetrics = appcompattextviewautosizehelper.mContext.getResources().getDisplayMetrics();
        int j = ((flag1) ? 1 : 0);
        do
        {
            ai1 = ai2;
            if (j >= k)
            {
                break;
            }
            ai2[j] = Math.round(TypedValue.applyDimension(i, ai[j], displaymetrics));
            j++;
        } while (true);
        if (true) goto _L6; else goto _L5
_L2:
        appcompattextviewautosizehelper.mHasPresetAutoSizeValues = false;
_L5:
        if (appcompattextviewautosizehelper.setupAutoSizeText())
        {
            appcompattextviewautosizehelper.autoSizeText();
        }
    }

    final void setAutoSizeTextTypeWithDefaults(int i)
    {
        AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper;
        appcompattextviewautosizehelper = mAutoSizeTextHelper;
        boolean flag;
        if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 1: default 48
    //                   0 79
    //                   1 118;
           goto _L3 _L4 _L5
_L3:
        throw new IllegalArgumentException((new StringBuilder("Unknown auto-size text type: ")).append(i).toString());
_L4:
        appcompattextviewautosizehelper.mAutoSizeTextType = 0;
        appcompattextviewautosizehelper.mAutoSizeMinTextSizeInPx = -1F;
        appcompattextviewautosizehelper.mAutoSizeMaxTextSizeInPx = -1F;
        appcompattextviewautosizehelper.mAutoSizeStepGranularityInPx = -1F;
        appcompattextviewautosizehelper.mAutoSizeTextSizesInPx = new int[0];
        appcompattextviewautosizehelper.mNeedsAutoSizeText = false;
_L2:
        return;
_L5:
        android.util.DisplayMetrics displaymetrics = appcompattextviewautosizehelper.mContext.getResources().getDisplayMetrics();
        appcompattextviewautosizehelper.validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12F, displaymetrics), TypedValue.applyDimension(2, 112F, displaymetrics), 1.0F);
        if (appcompattextviewautosizehelper.setupAutoSizeText())
        {
            appcompattextviewautosizehelper.autoSizeText();
            return;
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    final void setTextSize(int i, float f)
    {
        boolean flag1 = true;
        if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
        {
            AppCompatTextViewAutoSizeHelper appcompattextviewautosizehelper = mAutoSizeTextHelper;
            boolean flag;
            if (!(appcompattextviewautosizehelper.mTextView instanceof AppCompatEditText))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag && appcompattextviewautosizehelper.mAutoSizeTextType != 0)
            {
                flag = flag1;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                mAutoSizeTextHelper.setTextSizeInternal(i, f);
            }
        }
    }

    private class _cls1 extends android.support.v4.content.res.ResourcesCompat.FontCallback
    {

        private final AppCompatTextHelper this$0;
        private final WeakReference val$textViewWeak;

        public final void onFontRetrievalFailed(int i)
        {
        }

        public final void onFontRetrieved(Typeface typeface)
        {
            AppCompatTextHelper appcompattexthelper = AppCompatTextHelper.this;
            Object obj = textViewWeak;
            if (appcompattexthelper.mAsyncFontPending)
            {
                appcompattexthelper.mFontTypeface = typeface;
                obj = (TextView)((WeakReference) (obj)).get();
                if (obj != null)
                {
                    ((TextView) (obj)).setTypeface(typeface, appcompattexthelper.mStyle);
                }
            }
        }

        _cls1()
        {
            this$0 = AppCompatTextHelper.this;
            textViewWeak = weakreference;
            super();
        }
    }

}
