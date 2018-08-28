// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;


// Referenced classes of package android.support.v7.graphics:
//            ColorCutQuantizer

final class fitBox
{

    public int mLowerIndex;
    public int mMaxBlue;
    public int mMaxGreen;
    public int mMaxRed;
    public int mMinBlue;
    public int mMinGreen;
    public int mMinRed;
    public int mPopulation;
    public int mUpperIndex;
    public final ColorCutQuantizer this$0;

    final void fitBox()
    {
        int ai[] = mColors;
        int ai1[] = mHistogram;
        int l = 0x80000000;
        int i2 = 0;
        int k = mLowerIndex;
        int j2 = 0x80000000;
        int i = 0x7fffffff;
        int j = 0x7fffffff;
        int k1 = 0x7fffffff;
        int j1 = 0x80000000;
        while (k <= mUpperIndex) 
        {
            int i1 = ai[k];
            int i3 = i2 + ai1[i1];
            int l2 = ColorCutQuantizer.quantizedRed(i1);
            int k2 = ColorCutQuantizer.quantizedGreen(i1);
            i2 = ColorCutQuantizer.quantizedBlue(i1);
            i1 = j2;
            if (l2 > j2)
            {
                i1 = l2;
            }
            int l1 = k1;
            if (l2 < k1)
            {
                l1 = l2;
            }
            k1 = j1;
            if (k2 > j1)
            {
                k1 = k2;
            }
            l2 = j;
            if (k2 < j)
            {
                l2 = k2;
            }
            j1 = l;
            if (i2 > l)
            {
                j1 = i2;
            }
            j = i;
            if (i2 < i)
            {
                j = i2;
            }
            k++;
            i2 = i3;
            l = j1;
            j1 = k1;
            j2 = i1;
            i = j;
            j = l2;
            k1 = l1;
        }
        mMinRed = k1;
        mMaxRed = j2;
        mMinGreen = j;
        mMaxGreen = j1;
        mMinBlue = i;
        mMaxBlue = l;
        mPopulation = i2;
    }

    final int getVolume()
    {
        return ((mMaxRed - mMinRed) + 1) * ((mMaxGreen - mMinGreen) + 1) * ((mMaxBlue - mMinBlue) + 1);
    }

    (int i, int j)
    {
        this$0 = ColorCutQuantizer.this;
        super();
        mLowerIndex = i;
        mUpperIndex = j;
        fitBox();
    }
}
