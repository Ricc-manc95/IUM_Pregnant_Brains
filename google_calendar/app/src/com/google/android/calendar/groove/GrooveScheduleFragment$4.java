// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.graphics.ColorCutQuantizer;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Target;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.bitmap.ReusableBitmap;
import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.android.bitmap.drawable.ExtendedBitmapDrawable;
import com.google.android.calendar.Utils;
import com.google.android.calendar.common.drawable.ListenableBitmapDrawable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveScheduleFragment, GrooveSummaryView

final class .Category
    implements com.android.volley.eduleFragment._cls4
{

    private final GrooveScheduleFragment this$0;
    private final boolean val$animateFromCategoryPage;
    private final com.google.android.calendar.groove.category. val$category;

    public final void onResponse(Object obj)
    {
label0:
        {
            GrooveSummaryView groovesummaryview;
            Object obj2;
            int i;
            {
                Object obj1 = (Bitmap)obj;
                obj = GrooveScheduleFragment.this;
                double d;
                double d1;
                float f1;
                float f3;
                int ai[];
                int j;
                int i1;
                int l1;
                if (((Fragment) (obj)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
                }
                obj = Utils.getRtlAdjustedImage(((android.content.Context) (obj)), ((Bitmap) (obj1)));
                backgroundDrawable.setDecodeDimensions(((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
                backgroundDrawable.setBounds(0, 0, ((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
                obj1 = new ReusableBitmap(((Bitmap) (obj)));
                obj1.height = ((Bitmap) (obj)).getHeight();
                obj1.width = ((Bitmap) (obj)).getWidth();
                backgroundDrawable.setBitmap(((ReusableBitmap) (obj1)));
                backgroundColor = ((Bitmap) (obj)).getPixel(0, 0);
                if (val$animateFromCategoryPage)
                {
                    GrooveScheduleFragment.startBackgroundTransitionAnimation(backgroundFrame, val$category.backgroundColor, backgroundColor, backgroundImage);
                } else
                {
                    backgroundFrame.setBackgroundColor(backgroundColor);
                    backgroundImage.setAlpha(1.0F);
                    obj1 = backgroundDrawable;
                    obj1.parallaxFraction = (2.5F + (float)screenList.indexOf(Integer.valueOf(4))) * 0.15F;
                    ((ExtendedBitmapDrawable) (obj1)).invalidateSelf();
                }
                matchColorThemeToBackgroundColor(backgroundColor);
                backgroundImage.setImageDrawable(backgroundDrawable);
                backgroundImage.getLayoutParams().ight = ((Bitmap) (obj)).getHeight();
                backgroundImage.requestLayout();
                groovesummaryview = summaryView;
                obj1 = GrooveScheduleFragment.this;
                i = backgroundColor;
                obj2 = Palette.from(((Bitmap) (obj)));
                obj = GrooveScheduleFragment.FAB_TARGET;
                if (!((android.support.v7.graphics.ment.FAB_TARGET) (obj2)).FAB_TARGET.contains(obj))
                {
                    ((android.support.v7.graphics.ment.FAB_TARGET) (obj2)).FAB_TARGET.add(obj);
                }
                obj = new <init>(i);
                ((android.support.v7.graphics.ment._cls6) (obj2))._fld6.add(obj);
                if (((android.support.v7.graphics.ment._cls6) (obj2))._fld6 == null)
                {
                    break label0;
                }
                obj = ((android.support.v7.graphics.ment._cls6) (obj2))._fld6;
                d1 = -1D;
                if (((android.support.v7.graphics.ment._cls6) (obj2)).ea > 0)
                {
                    i = ((Bitmap) (obj)).getWidth() * ((Bitmap) (obj)).getHeight();
                    d = d1;
                    if (i > ((android.support.v7.graphics.ea) (obj2)).ea)
                    {
                        d = Math.sqrt((double)((android.support.v7.graphics.ea) (obj2)).ea / (double)i);
                    }
                } else
                {
                    d = d1;
                    if (((android.support.v7.graphics.ea) (obj2)).xDimension > 0)
                    {
                        i = Math.max(((Bitmap) (obj)).getWidth(), ((Bitmap) (obj)).getHeight());
                        d = d1;
                        if (i > ((android.support.v7.graphics.xDimension) (obj2)).xDimension)
                        {
                            d = (double)((android.support.v7.graphics.xDimension) (obj2)).xDimension / (double)i;
                        }
                    }
                }
                if (d > 0.0D)
                {
                    obj = Bitmap.createScaledBitmap(((Bitmap) (obj)), (int)Math.ceil((double)((Bitmap) (obj)).getWidth() * d), (int)Math.ceil(d * (double)((Bitmap) (obj)).getHeight()), false);
                }
                i = ((Bitmap) (obj)).getWidth();
                j = ((Bitmap) (obj)).getHeight();
                ai = new int[i * j];
                ((Bitmap) (obj)).getPixels(ai, 0, i, 0, 0, i, j);
                i = ((android.support.v7.graphics.xDimension) (obj2)).s;
                if (((android.support.v7.graphics.s) (obj2)).s.isEmpty())
                {
                    obj1 = null;
                } else
                {
                    obj1 = (android.support.v7.graphics.s[])((android.support.v7.graphics.s) (obj2)).s.toArray(new android.support.v7.graphics.s[((android.support.v7.graphics.s) (obj2)).s.size()]);
                }
                obj1 = new ColorCutQuantizer(ai, i, ((android.support.v7.graphics.s []) (obj1)));
                if (obj != ((android.support.v7.graphics.s) (obj2)).s)
                {
                    ((Bitmap) (obj)).recycle();
                }
                obj2 = new Palette(((ColorCutQuantizer) (obj1)).mQuantizedColors, ((android.support.v7.graphics.zedColors) (obj2)).zedColors);
                l1 = ((Palette) (obj2)).mTargets.size();
                i = 0;
            }
            if (i < l1)
            {
                Target target = (Target)((Palette) (obj2)).mTargets.get(i);
                i1 = target.mWeights.length;
                float f = 0.0F;
                for (j = 0; j < i1;)
                {
                    f3 = target.mWeights[j];
                    f1 = f;
                    if (f3 > 0.0F)
                    {
                        f1 = f + f3;
                    }
                    j++;
                    f = f1;
                }

                if (f != 0.0F)
                {
                    int k = 0;
                    for (int j1 = target.mWeights.length; k < j1; k++)
                    {
                        if (target.mWeights[k] > 0.0F)
                        {
                            obj = target.mWeights;
                            obj[k] = obj[k] / f;
                        }
                    }

                }
                Map map = ((Palette) (obj2)).mSelectedSwatches;
                f = 0.0F;
                obj = null;
                int i2 = ((Palette) (obj2)).mSwatches.size();
                int l = 0;
                while (l < i2) 
                {
                    android.support.v7.graphics.eBitmapDrawable ebitmapdrawable = (android.support.v7.graphics.)((Palette) (obj2)).mSwatches.get(l);
                    float af[] = ebitmapdrawable.();
                    float f2;
                    float f4;
                    float f5;
                    int k1;
                    if (af[1] >= target.mSaturationTargets[0] && af[1] <= target.mSaturationTargets[2] && af[2] >= target.mLightnessTargets[0] && af[2] <= target.mLightnessTargets[2] && !((Palette) (obj2)).mUsedColors.get(ebitmapdrawable.))
                    {
                        k1 = 1;
                    } else
                    {
                        k1 = 0;
                    }
                    if (k1 == 0)
                    {
                        continue;
                    }
                    af = ebitmapdrawable.();
                    f2 = 0.0F;
                    f4 = 0.0F;
                    if (((Palette) (obj2)).mDominantSwatch != null)
                    {
                        k1 = ((Palette) (obj2)).mDominantSwatch.n;
                    } else
                    {
                        k1 = 1;
                    }
                    if (target.mWeights[0] > 0.0F)
                    {
                        f2 = target.mWeights[0] * (1.0F - Math.abs(af[1] - target.mSaturationTargets[1]));
                    }
                    if (target.mWeights[1] > 0.0F)
                    {
                        f4 = target.mWeights[1] * (1.0F - Math.abs(af[2] - target.mLightnessTargets[1]));
                    }
                    if (target.mWeights[2] > 0.0F)
                    {
                        f5 = target.mWeights[2];
                        f5 = ((float)ebitmapdrawable.n / (float)k1) * f5;
                    } else
                    {
                        f5 = 0.0F;
                    }
                    f2 = f5 + (f2 + f4);
                    if (obj == null || f2 > f)
                    {
                        f = f2;
                        obj = ebitmapdrawable;
                    }
                    l++;
                }
                if (obj != null && target.mIsExclusive)
                {
                    ((Palette) (obj2)).mUsedColors.append(((android.support.v7.graphics.n) (obj)).n, true);
                }
                map.put(target, obj);
                i++;
                break MISSING_BLOCK_LABEL_488;
            } else
            {
                ((Palette) (obj2)).mUsedColors.clear();
                obj = Target.VIBRANT;
                obj = (android.support.v7.graphics.n)((Palette) (obj2)).mSelectedSwatches.get(obj);
                if (obj != null)
                {
                    i = ((android.support.v7.graphics.) (obj)).;
                } else
                {
                    i = -1;
                }
                if (i == -1)
                {
                    obj = Target.LIGHT_VIBRANT;
                    obj = (android.support.v7.graphics.)((Palette) (obj2)).mSelectedSwatches.get(obj);
                    if (obj != null)
                    {
                        i = ((android.support.v7.graphics.) (obj)).;
                    } else
                    {
                        i = -1;
                    }
                }
                groovesummaryview.setFabColor(i);
                return;
            }
        }
        throw new AssertionError();
    }

    .Category()
    {
        this$0 = final_grooveschedulefragment;
        val$animateFromCategoryPage = flag;
        val$category = com.google.android.calendar.groove.category..this;
        super();
    }
}
