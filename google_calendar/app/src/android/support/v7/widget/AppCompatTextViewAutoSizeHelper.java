// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package android.support.v7.widget:
//            AppCompatEditText

final class AppCompatTextViewAutoSizeHelper
{

    private static final RectF TEMP_RECTF = new RectF();
    private static ConcurrentHashMap sTextViewMethodByNameCache = new ConcurrentHashMap();
    public float mAutoSizeMaxTextSizeInPx;
    public float mAutoSizeMinTextSizeInPx;
    public float mAutoSizeStepGranularityInPx;
    public int mAutoSizeTextSizesInPx[];
    public int mAutoSizeTextType;
    public final Context mContext;
    public boolean mHasPresetAutoSizeValues;
    public boolean mNeedsAutoSizeText;
    private TextPaint mTempTextPaint;
    public final TextView mTextView;

    AppCompatTextViewAutoSizeHelper(TextView textview)
    {
        mAutoSizeTextType = 0;
        mNeedsAutoSizeText = false;
        mAutoSizeStepGranularityInPx = -1F;
        mAutoSizeMinTextSizeInPx = -1F;
        mAutoSizeMaxTextSizeInPx = -1F;
        mAutoSizeTextSizesInPx = new int[0];
        mHasPresetAutoSizeValues = false;
        mTextView = textview;
        mContext = mTextView.getContext();
    }

    static int[] cleanupAutoSizePresetSizes(int ai[])
    {
        int k = ai.length;
        if (k != 0) goto _L2; else goto _L1
_L1:
        return ai;
_L2:
        Arrays.sort(ai);
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < k; i++)
        {
            int l = ai[i];
            if (l > 0 && Collections.binarySearch(arraylist, Integer.valueOf(l)) < 0)
            {
                arraylist.add(Integer.valueOf(l));
            }
        }

        if (k == arraylist.size())
        {
            continue;
        }
        k = arraylist.size();
        int ai1[] = new int[k];
        int j = 0;
        do
        {
            ai = ai1;
            if (j >= k)
            {
                continue;
            }
            ai1[j] = ((Integer)arraylist.get(j)).intValue();
            j++;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    private static Method getTextViewMethod(String s)
    {
        Method method;
        Method method1;
        try
        {
            method1 = (Method)sTextViewMethodByNameCache.get(s);
        }
        catch (Exception exception)
        {
            Log.w("ACTVAutoSizeHelper", (new StringBuilder("Failed to retrieve TextView#")).append(s).append("() method").toString(), exception);
            return null;
        }
        method = method1;
        if (method1 != null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        method1 = android/widget/TextView.getDeclaredMethod(s, new Class[0]);
        method = method1;
        if (method1 == null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        method1.setAccessible(true);
        sTextViewMethodByNameCache.put(s, method1);
        method = method1;
        return method;
    }

    private final Object invokeAndReturnWithDefault(Object obj, String s, Object obj1)
    {
        obj = getTextViewMethod(s).invoke(obj, new Object[0]);
        if (obj != null);
        return obj;
        obj;
        Log.w("ACTVAutoSizeHelper", (new StringBuilder("Failed to invoke TextView#")).append(s).append("() method").toString(), ((Throwable) (obj)));
        return obj1;
        obj;
        throw obj;
    }

    final void autoSizeText()
    {
        boolean flag;
        if (!(mTextView instanceof AppCompatEditText))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && mAutoSizeTextType != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!mNeedsAutoSizeText) goto _L4; else goto _L3
_L3:
        if (mTextView.getMeasuredHeight() <= 0 || mTextView.getMeasuredWidth() <= 0) goto _L1; else goto _L5
_L5:
        RectF rectf;
        RectF rectf1;
        int i;
        Exception exception;
        int j;
        if (((Boolean)invokeAndReturnWithDefault(mTextView, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue())
        {
            i = 0x100000;
        } else
        {
            i = mTextView.getMeasuredWidth() - mTextView.getTotalPaddingLeft() - mTextView.getTotalPaddingRight();
        }
        j = mTextView.getHeight() - mTextView.getCompoundPaddingBottom() - mTextView.getCompoundPaddingTop();
        if (i <= 0 || j <= 0) goto _L1; else goto _L6
_L6:
        rectf = TEMP_RECTF;
        rectf;
        JVM INSTR monitorenter ;
        TEMP_RECTF.setEmpty();
        TEMP_RECTF.right = i;
        TEMP_RECTF.bottom = j;
        rectf1 = TEMP_RECTF;
        i = mAutoSizeTextSizesInPx.length;
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_228;
        }
        throw new IllegalStateException("No available text sizes to choose from.");
        exception;
        rectf;
        JVM INSTR monitorexit ;
        throw exception;
        int k;
        int l;
        l = i - 1;
        k = 0;
        i = 1;
_L20:
        if (i > l) goto _L8; else goto _L7
_L7:
        CharSequence charsequence;
        Object obj;
        int j1;
        j1 = (i + l) / 2;
        k = mAutoSizeTextSizesInPx[j1];
        charsequence = mTextView.getText();
        obj = mTextView.getTransformationMethod();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_309;
        }
        obj = ((TransformationMethod) (obj)).getTransformation(charsequence, mTextView);
        if (obj != null)
        {
            charsequence = ((CharSequence) (obj));
        }
        int i1 = mTextView.getMaxLines();
        if (mTempTextPaint != null) goto _L10; else goto _L9
_L9:
        mTempTextPaint = new TextPaint();
_L16:
        Object obj1;
        mTempTextPaint.set(mTextView.getPaint());
        mTempTextPaint.setTextSize(k);
        obj1 = (android.text.Layout.Alignment)invokeAndReturnWithDefault(mTextView, "getLayoutAlignment", android.text.Layout.Alignment.ALIGN_NORMAL);
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L12; else goto _L11
_L11:
        k = Math.round(rectf1.right);
        obj = (TextDirectionHeuristic)invokeAndReturnWithDefault(mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
        obj1 = android.text.StaticLayout.Builder.obtain(charsequence, 0, charsequence.length(), mTempTextPaint, k).setAlignment(((android.text.Layout.Alignment) (obj1))).setLineSpacing(mTextView.getLineSpacingExtra(), mTextView.getLineSpacingMultiplier()).setIncludePad(mTextView.getIncludeFontPadding()).setBreakStrategy(mTextView.getBreakStrategy()).setHyphenationFrequency(mTextView.getHyphenationFrequency());
        float f1;
        if (i1 == -1)
        {
            k = 0x7fffffff;
        } else
        {
            k = i1;
        }
        obj = ((android.text.StaticLayout.Builder) (obj1)).setMaxLines(k).setTextDirection(((TextDirectionHeuristic) (obj))).build();
_L17:
        if (i1 == -1) goto _L14; else goto _L13
_L13:
        if (((StaticLayout) (obj)).getLineCount() <= i1 && ((StaticLayout) (obj)).getLineEnd(((StaticLayout) (obj)).getLineCount() - 1) == charsequence.length()) goto _L14; else goto _L15
_L10:
        mTempTextPaint.reset();
          goto _L16
_L12:
        k = Math.round(rectf1.right);
        float f = mTextView.getLineSpacingMultiplier();
        float f2 = mTextView.getLineSpacingExtra();
        boolean flag1 = mTextView.getIncludeFontPadding();
        obj = new StaticLayout(charsequence, mTempTextPaint, k, ((android.text.Layout.Alignment) (obj1)), f, f2, flag1);
          goto _L17
_L14:
        if ((float)((StaticLayout) (obj)).getHeight() > rectf1.bottom)
        {
            k = 0;
        } else
        {
            k = 1;
        }
          goto _L18
_L8:
        f1 = mAutoSizeTextSizesInPx[k];
        if (f1 != mTextView.getTextSize())
        {
            setTextSizeInternal(0, f1);
        }
        rectf;
        JVM INSTR monitorexit ;
_L4:
        mNeedsAutoSizeText = true;
        return;
_L15:
        k = 0;
_L18:
        if (k != 0)
        {
            i1 = j1 + 1;
            k = i;
            i = i1;
        } else
        {
            k = j1 - 1;
            l = k;
        }
        if (true) goto _L20; else goto _L19
_L19:
    }

    final void setTextSizeInternal(int i, float f)
    {
        Object obj;
        boolean flag;
        if (mContext == null)
        {
            obj = Resources.getSystem();
        } else
        {
            obj = mContext.getResources();
        }
        f = TypedValue.applyDimension(i, f, ((Resources) (obj)).getDisplayMetrics());
        if (f == mTextView.getPaint().getTextSize())
        {
            break MISSING_BLOCK_LABEL_114;
        }
        mTextView.getPaint().setTextSize(f);
        flag = mTextView.isInLayout();
        if (mTextView.getLayout() == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        mNeedsAutoSizeText = false;
        obj = getTextViewMethod("nullLayouts");
        if (obj != null)
        {
            try
            {
                ((Method) (obj)).invoke(mTextView, new Object[0]);
            }
            catch (Exception exception)
            {
                Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", exception);
            }
        }
        if (!flag)
        {
            mTextView.requestLayout();
        } else
        {
            mTextView.forceLayout();
        }
        mTextView.invalidate();
    }

    final boolean setupAutoSizeText()
    {
        int i;
        if (!(mTextView instanceof AppCompatEditText))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && mAutoSizeTextType == 1)
        {
            if (!mHasPresetAutoSizeValues || mAutoSizeTextSizesInPx.length == 0)
            {
                float f = Math.round(mAutoSizeMinTextSizeInPx);
                i = 1;
                for (; Math.round(mAutoSizeStepGranularityInPx + f) <= Math.round(mAutoSizeMaxTextSizeInPx); f += mAutoSizeStepGranularityInPx)
                {
                    i++;
                }

                int ai[] = new int[i];
                f = mAutoSizeMinTextSizeInPx;
                for (int j = 0; j < i; j++)
                {
                    ai[j] = Math.round(f);
                    f += mAutoSizeStepGranularityInPx;
                }

                mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(ai);
            }
            mNeedsAutoSizeText = true;
        } else
        {
            mNeedsAutoSizeText = false;
        }
        return mNeedsAutoSizeText;
    }

    final boolean setupAutoSizeUniformPresetSizesConfiguration()
    {
        int i = mAutoSizeTextSizesInPx.length;
        boolean flag;
        if (i > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mHasPresetAutoSizeValues = flag;
        if (mHasPresetAutoSizeValues)
        {
            mAutoSizeTextType = 1;
            mAutoSizeMinTextSizeInPx = mAutoSizeTextSizesInPx[0];
            mAutoSizeMaxTextSizeInPx = mAutoSizeTextSizesInPx[i - 1];
            mAutoSizeStepGranularityInPx = -1F;
        }
        return mHasPresetAutoSizeValues;
    }

    final void validateAndSetAutoSizeTextTypeUniformConfiguration(float f, float f1, float f2)
        throws IllegalArgumentException
    {
        if (f <= 0.0F)
        {
            throw new IllegalArgumentException((new StringBuilder("Minimum auto-size text size (")).append(f).append("px) is less or equal to (0px)").toString());
        }
        if (f1 <= f)
        {
            throw new IllegalArgumentException((new StringBuilder("Maximum auto-size text size (")).append(f1).append("px) is less or equal to minimum auto-size text size (").append(f).append("px)").toString());
        }
        if (f2 <= 0.0F)
        {
            throw new IllegalArgumentException((new StringBuilder("The auto-size step granularity (")).append(f2).append("px) is less or equal to (0px)").toString());
        } else
        {
            mAutoSizeTextType = 1;
            mAutoSizeMinTextSizeInPx = f;
            mAutoSizeMaxTextSizeInPx = f1;
            mAutoSizeStepGranularityInPx = f2;
            mHasPresetAutoSizeValues = false;
            return;
        }
    }

}
