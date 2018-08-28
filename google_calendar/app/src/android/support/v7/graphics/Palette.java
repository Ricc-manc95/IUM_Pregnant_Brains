// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;

import android.graphics.Bitmap;
import android.support.v4.util.ArrayMap;
import android.util.SparseBooleanArray;
import java.util.List;
import java.util.Map;

public final class Palette
{

    public static final Filter DEFAULT_FILTER = new _cls1();
    public final Swatch mDominantSwatch;
    public final Map mSelectedSwatches = new ArrayMap();
    public final List mSwatches;
    public final List mTargets;
    public final SparseBooleanArray mUsedColors = new SparseBooleanArray();

    public Palette(List list, List list1)
    {
        mSwatches = list;
        mTargets = list1;
        int i = 0x80000000;
        list = null;
        int k = mSwatches.size();
        for (int j = 0; j < k; j++)
        {
            list1 = (Swatch)mSwatches.get(j);
            if (((Swatch) (list1)).mPopulation > i)
            {
                i = ((Swatch) (list1)).mPopulation;
                list = list1;
            }
        }

        mDominantSwatch = list;
    }

    public static Builder from(Bitmap bitmap)
    {
        return new Builder(bitmap);
    }


    private class Swatch
    {

        private final int mBlue;
        private int mBodyTextColor;
        private boolean mGeneratedTextColors;
        private final int mGreen;
        private float mHsl[];
        public final int mPopulation;
        private final int mRed;
        public final int mRgb;
        private int mTitleTextColor;

        private final void ensureTextColorsGenerated()
        {
            int i;
            int j;
label0:
            {
                if (!mGeneratedTextColors)
                {
                    i = ColorUtils.calculateMinimumAlpha(-1, mRgb, 4.5F);
                    j = ColorUtils.calculateMinimumAlpha(-1, mRgb, 3F);
                    if (i == -1 || j == -1)
                    {
                        break label0;
                    }
                    mBodyTextColor = ColorUtils.setAlphaComponent(-1, i);
                    mTitleTextColor = ColorUtils.setAlphaComponent(-1, j);
                    mGeneratedTextColors = true;
                }
                return;
            }
            int l = ColorUtils.calculateMinimumAlpha(0xff000000, mRgb, 4.5F);
            int k = ColorUtils.calculateMinimumAlpha(0xff000000, mRgb, 3F);
            if (l != -1 && k != -1)
            {
                mBodyTextColor = ColorUtils.setAlphaComponent(0xff000000, l);
                mTitleTextColor = ColorUtils.setAlphaComponent(0xff000000, k);
                mGeneratedTextColors = true;
                return;
            }
            if (i != -1)
            {
                i = ColorUtils.setAlphaComponent(-1, i);
            } else
            {
                i = ColorUtils.setAlphaComponent(0xff000000, l);
            }
            mBodyTextColor = i;
            if (j != -1)
            {
                i = ColorUtils.setAlphaComponent(-1, j);
            } else
            {
                i = ColorUtils.setAlphaComponent(0xff000000, k);
            }
            mTitleTextColor = i;
            mGeneratedTextColors = true;
        }

        public final boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj == null || getClass() != obj.getClass())
                {
                    return false;
                }
                obj = (Swatch)obj;
                if (mPopulation != ((Swatch) (obj)).mPopulation || mRgb != ((Swatch) (obj)).mRgb)
                {
                    return false;
                }
            }
            return true;
        }

        public final float[] getHsl()
        {
            if (mHsl == null)
            {
                mHsl = new float[3];
            }
            ColorUtils.RGBToHSL(mRed, mGreen, mBlue, mHsl);
            return mHsl;
        }

        public final int hashCode()
        {
            return mRgb * 31 + mPopulation;
        }

        public final String toString()
        {
            StringBuilder stringbuilder = (new StringBuilder(getClass().getSimpleName())).append(" [RGB: #").append(Integer.toHexString(mRgb)).append(']').append(" [HSL: ").append(Arrays.toString(getHsl())).append(']').append(" [Population: ").append(mPopulation).append(']').append(" [Title Text: #");
            ensureTextColorsGenerated();
            stringbuilder = stringbuilder.append(Integer.toHexString(mTitleTextColor)).append(']').append(" [Body Text: #");
            ensureTextColorsGenerated();
            return stringbuilder.append(Integer.toHexString(mBodyTextColor)).append(']').toString();
        }

        public Swatch(int i, int j)
        {
            mRed = Color.red(i);
            mGreen = Color.green(i);
            mBlue = Color.blue(i);
            mRgb = i;
            mPopulation = j;
        }
    }


    private class Builder
    {

        public final Bitmap mBitmap;
        public final List mFilters = new ArrayList();
        public int mMaxColors;
        public int mResizeArea;
        public int mResizeMaxDimension;
        private final List mSwatches;
        public final List mTargets = new ArrayList();

        public Builder(Bitmap bitmap)
        {
            mMaxColors = 16;
            mResizeArea = 12544;
            mResizeMaxDimension = -1;
            if (bitmap == null || bitmap.isRecycled())
            {
                throw new IllegalArgumentException("Bitmap is not valid");
            } else
            {
                mFilters.add(Palette.DEFAULT_FILTER);
                mBitmap = bitmap;
                mSwatches = null;
                mTargets.add(Target.LIGHT_VIBRANT);
                mTargets.add(Target.VIBRANT);
                mTargets.add(Target.DARK_VIBRANT);
                mTargets.add(Target.LIGHT_MUTED);
                mTargets.add(Target.MUTED);
                mTargets.add(Target.DARK_MUTED);
                return;
            }
        }
    }


    private class _cls1
        implements Filter
    {

        public final boolean isAllowed(int i, float af[])
        {
            if (af[2] >= 0.95F)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                if (af[2] <= 0.05F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    if (af[0] >= 10F && af[0] <= 37F && af[1] <= 0.82F)
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        _cls1()
        {
        }
    }

}
