// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.graphics;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public final class ColorCutQuantizer
{

    private static final Comparator VBOX_COMPARATOR_VOLUME = new _cls1();
    public final int mColors[];
    private final Palette.Filter mFilters[];
    public final int mHistogram[];
    public final List mQuantizedColors;
    private final float mTempHsl[];

    public ColorCutQuantizer(int ai[], int i, Palette.Filter afilter[])
    {
        int k;
        mTempHsl = new float[3];
        mFilters = afilter;
        afilter = new int[32768];
        mHistogram = afilter;
        for (int j = 0; j < ai.length; j++)
        {
            int i1 = ai[j];
            int k2 = Color.red(i1);
            int l3 = Color.green(i1);
            i1 = Color.blue(i1) >> 3 & 0x1f | ((k2 >> 3 & 0x1f) << 10 | (l3 >> 3 & 0x1f) << 5);
            ai[j] = i1;
            afilter[i1] = afilter[i1] + 1;
        }

        k = 0;
        for (int j1 = 0; j1 < 32768; j1++)
        {
            if (afilter[j1] > 0)
            {
                int l2 = Color.rgb((j1 >> 10 & 0x1f) << 3 & 0xff, (j1 >> 5 & 0x1f) << 3 & 0xff, (j1 & 0x1f) << 3 & 0xff);
                ColorUtils.colorToHSL(l2, mTempHsl);
                if (shouldIgnoreColor(l2, mTempHsl))
                {
                    afilter[j1] = 0;
                }
            }
            if (afilter[j1] > 0)
            {
                k++;
            }
        }

        ai = new int[k];
        mColors = ai;
        int k1 = 0;
        for (int i3 = 0; i3 < 32768; i3++)
        {
            if (afilter[i3] > 0)
            {
                int i4 = k1 + 1;
                ai[k1] = i3;
                k1 = i4;
            }
        }

        if (k > i) goto _L2; else goto _L1
_L1:
        mQuantizedColors = new ArrayList();
        k = ai.length;
        for (i = 0; i < k; i++)
        {
            int l1 = ai[i];
            mQuantizedColors.add(new Palette.Swatch(Color.rgb((l1 >> 10 & 0x1f) << 3 & 0xff, (l1 >> 5 & 0x1f) << 3 & 0xff, (l1 & 0x1f) << 3 & 0xff), afilter[l1]));
        }

          goto _L3
_L2:
        afilter = new PriorityQueue(i, VBOX_COMPARATOR_VOLUME);
        afilter.offer(new Vbox(0, mColors.length - 1));
_L9:
        if (afilter.size() >= i)
        {
            break MISSING_BLOCK_LABEL_756;
        }
        ai = (Vbox)afilter.poll();
        if (ai == null || (((Vbox) (ai)).mUpperIndex + 1) - ((Vbox) (ai)).mLowerIndex <= 1)
        {
            break MISSING_BLOCK_LABEL_756;
        }
        boolean flag;
        if ((((Vbox) (ai)).mUpperIndex + 1) - ((Vbox) (ai)).mLowerIndex > 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException("Can not split a box with only 1 color");
        }
        flag = ((Vbox) (ai)).mMaxRed - ((Vbox) (ai)).mMinRed;
        int i2 = ((Vbox) (ai)).mMaxGreen - ((Vbox) (ai)).mMinGreen;
        int j3 = ((Vbox) (ai)).mMaxBlue - ((Vbox) (ai)).mMinBlue;
        int ai1[];
        Vbox vbox;
        int ai2[];
        if (flag >= i2 && flag >= j3)
        {
            flag = -3;
        } else
        if (i2 >= flag && i2 >= j3)
        {
            flag = -2;
        } else
        {
            flag = -1;
        }
        ai1 = ((Vbox) (ai))._fld0.mColors;
        ai2 = ((Vbox) (ai))._fld0.mHistogram;
        modifySignificantOctet(ai1, flag, ((Vbox) (ai)).mLowerIndex, ((Vbox) (ai)).mUpperIndex);
        Arrays.sort(ai1, ((Vbox) (ai)).mLowerIndex, ((Vbox) (ai)).mUpperIndex + 1);
        modifySignificantOctet(ai1, flag, ((Vbox) (ai)).mLowerIndex, ((Vbox) (ai)).mUpperIndex);
        j3 = ((Vbox) (ai)).mPopulation / 2;
        flag = ((Vbox) (ai)).mLowerIndex;
        i2 = 0;
_L6:
        if (flag > ((Vbox) (ai)).mUpperIndex)
        {
            break MISSING_BLOCK_LABEL_747;
        }
        i2 += ai2[ai1[flag]];
        if (i2 < j3) goto _L5; else goto _L4
_L4:
        flag = Math.min(((Vbox) (ai)).mUpperIndex - 1, flag);
_L7:
        vbox = ((Vbox) (ai))._fld0. new Vbox(flag + 1, ((Vbox) (ai)).mUpperIndex);
        ai.mUpperIndex = ((flag) ? 1 : 0);
        ai.fitBox();
        afilter.offer(vbox);
        afilter.offer(ai);
        continue; /* Loop/switch isn't completed */
_L5:
        flag++;
          goto _L6
        flag = ((Vbox) (ai)).mLowerIndex;
          goto _L7
        ai = new ArrayList(afilter.size());
        afilter = afilter.iterator();
        do
        {
            if (!afilter.hasNext())
            {
                break;
            }
            Object obj = (Vbox)afilter.next();
            int ai3[] = ((Vbox) (obj))._fld0.mColors;
            int ai4[] = ((Vbox) (obj))._fld0.mHistogram;
            int j2 = 0;
            int l = 0;
            i = 0;
            int j4 = 0;
            for (int k3 = ((Vbox) (obj)).mLowerIndex; k3 <= ((Vbox) (obj)).mUpperIndex; k3++)
            {
                int k4 = ai3[k3];
                int l4 = ai4[k4];
                j4 += l4;
                j2 += (k4 >> 10 & 0x1f) * l4;
                l += (k4 >> 5 & 0x1f) * l4;
                i += (k4 & 0x1f) * l4;
            }

            obj = new Palette.Swatch(Color.rgb(Math.round((float)j2 / (float)j4) << 3 & 0xff, Math.round((float)l / (float)j4) << 3 & 0xff, Math.round((float)i / (float)j4) << 3 & 0xff), j4);
            if (!shouldIgnoreColor(((Palette.Swatch) (obj)).mRgb, ((Palette.Swatch) (obj)).getHsl()))
            {
                ai.add(obj);
            }
        } while (true);
        mQuantizedColors = ai;
_L3:
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    static void modifySignificantOctet(int ai[], int i, int j, int k)
    {
        int l = j;
        i;
        JVM INSTR tableswitch -3 -1: default 32
    //                   -3 32
    //                   -2 33
    //                   -1 81;
           goto _L1 _L1 _L2 _L3
_L1:
        return;
_L2:
        while (l <= k) 
        {
            i = ai[l];
            ai[l] = i & 0x1f | ((i >> 5 & 0x1f) << 10 | (i >> 10 & 0x1f) << 5);
            l++;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        while (j <= k) 
        {
            i = ai[j];
            ai[j] = i >> 10 & 0x1f | ((i & 0x1f) << 10 | (i >> 5 & 0x1f) << 5);
            j++;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    static int quantizedBlue(int i)
    {
        return i & 0x1f;
    }

    static int quantizedGreen(int i)
    {
        return i >> 5 & 0x1f;
    }

    static int quantizedRed(int i)
    {
        return i >> 10 & 0x1f;
    }

    private final boolean shouldIgnoreColor(int i, float af[])
    {
        boolean flag;
        boolean flag1;
        flag1 = false;
        flag = flag1;
        if (mFilters == null) goto _L2; else goto _L1
_L1:
        flag = flag1;
        if (mFilters.length <= 0) goto _L2; else goto _L3
_L3:
        int j;
        int k;
        k = mFilters.length;
        j = 0;
_L8:
        flag = flag1;
        if (j >= k) goto _L2; else goto _L4
_L4:
        if (mFilters[j].isAllowed(i, af)) goto _L6; else goto _L5
_L5:
        flag = true;
_L2:
        return flag;
_L6:
        j++;
        if (true) goto _L8; else goto _L7
_L7:
    }


    private class Vbox
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

        Vbox(int i, int j)
        {
            this$0 = ColorCutQuantizer.this;
            super();
            mLowerIndex = i;
            mUpperIndex = j;
            fitBox();
        }
    }


    private class _cls1
        implements Comparator
    {

        public final int compare(Object obj, Object obj1)
        {
            obj = (Vbox)obj;
            return ((Vbox)obj1).getVolume() - ((Vbox) (obj)).getVolume();
        }

        _cls1()
        {
        }
    }

}
