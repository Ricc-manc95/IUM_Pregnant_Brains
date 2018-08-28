// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.graphics.Color;

public final class ColorUtils
{

    private static final ThreadLocal TEMP_ARRAY = new ThreadLocal();

    public static void RGBToHSL(int i, int j, int k, float af[])
    {
        float f3 = 0.0F;
        float f = (float)i / 255F;
        float f2 = (float)j / 255F;
        float f5 = (float)k / 255F;
        float f6 = Math.max(f, Math.max(f2, f5));
        float f7 = Math.min(f, Math.min(f2, f5));
        float f1 = f6 - f7;
        float f4 = (f6 + f7) / 2.0F;
        if (f6 == f7)
        {
            f = 0.0F;
            f1 = 0.0F;
        } else
        {
            if (f6 == f)
            {
                f = ((f2 - f5) / f1) % 6F;
            } else
            if (f6 == f2)
            {
                f = (f5 - f) / f1 + 2.0F;
            } else
            {
                f = (f - f2) / f1 + 4F;
            }
            f2 = f1 / (1.0F - Math.abs(f4 * 2.0F - 1.0F));
            f1 = f;
            f = f2;
        }
        f2 = (f1 * 60F) % 360F;
        f1 = f2;
        if (f2 < 0.0F)
        {
            f1 = f2 + 360F;
        }
        if (f1 < 0.0F)
        {
            f2 = 0.0F;
        } else
        {
            f2 = f1;
            if (f1 > 360F)
            {
                f2 = 360F;
            }
        }
        af[0] = f2;
        if (f < 0.0F)
        {
            f = 0.0F;
        } else
        if (f > 1.0F)
        {
            f = 1.0F;
        }
        af[1] = f;
        if (f4 < 0.0F)
        {
            f = f3;
        } else
        if (f4 > 1.0F)
        {
            f = 1.0F;
        } else
        {
            f = f4;
        }
        af[2] = f;
    }

    public static double calculateContrast(int i, int j)
    {
        if (Color.alpha(j) != 255)
        {
            throw new IllegalArgumentException((new StringBuilder("background can not be translucent: #")).append(Integer.toHexString(j)).toString());
        }
        int k = i;
        if (Color.alpha(i) < 255)
        {
            k = compositeColors(i, j);
        }
        double d = calculateLuminance(k) + 0.050000000000000003D;
        double d1 = calculateLuminance(j) + 0.050000000000000003D;
        return Math.max(d, d1) / Math.min(d, d1);
    }

    private static double calculateLuminance(int i)
    {
        double ad1[] = (double[])TEMP_ARRAY.get();
        double ad[] = ad1;
        if (ad1 == null)
        {
            ad = new double[3];
            TEMP_ARRAY.set(ad);
        }
        int j = Color.red(i);
        int k = Color.green(i);
        i = Color.blue(i);
        if (ad.length != 3)
        {
            throw new IllegalArgumentException("outXyz must have a length of 3.");
        }
        double d = (double)j / 255D;
        double d1;
        double d2;
        if (d < 0.04045D)
        {
            d /= 12.92D;
        } else
        {
            d = Math.pow((d + 0.055D) / 1.0549999999999999D, 2.3999999999999999D);
        }
        d1 = (double)k / 255D;
        if (d1 < 0.04045D)
        {
            d1 /= 12.92D;
        } else
        {
            d1 = Math.pow((d1 + 0.055D) / 1.0549999999999999D, 2.3999999999999999D);
        }
        d2 = (double)i / 255D;
        if (d2 < 0.04045D)
        {
            d2 /= 12.92D;
        } else
        {
            d2 = Math.pow((d2 + 0.055D) / 1.0549999999999999D, 2.3999999999999999D);
        }
        ad[0] = 100D * (0.41239999999999999D * d + 0.35759999999999997D * d1 + 0.18049999999999999D * d2);
        ad[1] = 100D * (0.21260000000000001D * d + 0.71519999999999995D * d1 + 0.0722D * d2);
        ad[2] = (d2 * 0.95050000000000001D + (d1 * 0.1192D + d * 0.019300000000000001D)) * 100D;
        return ad[1] / 100D;
    }

    public static int calculateMinimumAlpha(int i, int j, float f)
    {
        int l;
        l = 0;
        if (Color.alpha(j) != 255)
        {
            throw new IllegalArgumentException((new StringBuilder("background can not be translucent: #")).append(Integer.toHexString(j)).toString());
        }
        if (calculateContrast(i & 0xffffff | 0xff000000, j) >= (double)f) goto _L2; else goto _L1
_L1:
        char c = '\uFFFF';
_L4:
        return c;
_L2:
        int k;
        int i1;
        k = 255;
        i1 = 0;
_L6:
        c = k;
        if (i1 > 10) goto _L4; else goto _L3
_L3:
        c = k;
        if (k - l <= 1) goto _L4; else goto _L5
_L5:
        int j1 = (l + k) / 2;
        if (j1 < 0 || j1 > 255)
        {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        if (calculateContrast(i & 0xffffff | j1 << 24, j) < (double)f)
        {
            l = j1;
        } else
        {
            k = j1;
        }
        i1++;
          goto _L6
    }

    public static void colorToHSL(int i, float af[])
    {
        RGBToHSL(Color.red(i), Color.green(i), Color.blue(i), af);
    }

    public static int compositeColors(int i, int j)
    {
        boolean flag = false;
        int j1 = Color.alpha(j);
        int k1 = Color.alpha(i);
        int i1 = 255 - ((255 - j1) * (255 - k1)) / 255;
        int k = Color.red(i);
        int l = Color.red(j);
        int l1;
        if (i1 == 0)
        {
            k = 0;
        } else
        {
            k = (k * 255 * k1 + l * j1 * (255 - k1)) / (i1 * 255);
        }
        l = Color.green(i);
        l1 = Color.green(j);
        if (i1 == 0)
        {
            l = 0;
        } else
        {
            l = (l * 255 * k1 + l1 * j1 * (255 - k1)) / (i1 * 255);
        }
        i = Color.blue(i);
        j = Color.blue(j);
        if (i1 == 0)
        {
            i = ((flag) ? 1 : 0);
        } else
        {
            i = (i * 255 * k1 + j1 * j * (255 - k1)) / (i1 * 255);
        }
        return Color.argb(i1, k, l, i);
    }

    public static int setAlphaComponent(int i, int j)
    {
        if (j < 0 || j > 255)
        {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        } else
        {
            return 0xffffff & i | j << 24;
        }
    }

}
