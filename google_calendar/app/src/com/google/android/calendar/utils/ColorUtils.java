// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.graphics.Color;
import com.android.calendarcommon2.LogUtils;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.calendar.utils:
//            NumberUtils

public final class ColorUtils
{

    private static final Map updatedColors;

    public static int adjustLightness(int i, float f)
    {
        float f1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f7;
        float af[];
        af = new float[3];
        int j = Color.red(i);
        int k = Color.green(i);
        int l = Color.blue(i);
        if (j >= 0 && j < 256)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        if (k >= 0 && k < 256)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        if (l >= 0 && l < 256)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            throw new IllegalArgumentException();
        }
        f4 = (float)j / 255F;
        f2 = (float)k / 255F;
        f3 = (float)l / 255F;
        f5 = Math.max(f4, Math.max(f2, f3));
        f1 = Math.min(f4, Math.min(f2, f3));
        f7 = (f5 + f1) / 2.0F;
        af[2] = f7;
        if (f5 != f1) goto _L2; else goto _L1
_L1:
        af[1] = 0.0F;
        af[0] = 0.0F;
_L4:
        af[2] = Math.min(1.0F, af[2] * f);
        return hslToRgb(af[0], af[1], af[2]);
_L2:
        float f6;
        f6 = f5 - f1;
        if ((double)f7 > 0.5D)
        {
            f1 = f6 / (2.0F - f5 - f1);
        } else
        {
            f1 = f6 / (f1 + f5);
        }
        af[1] = f1;
        if (f5 != f4)
        {
            break; /* Loop/switch isn't completed */
        }
        f1 = (f2 - f3) / f6;
        if (f2 < f3)
        {
            i = 6;
        } else
        {
            i = 0;
        }
        f1 = (float)i + f1;
_L5:
        af[0] = f1 / 6F;
        if (true) goto _L4; else goto _L3
_L3:
        if (f5 == f2)
        {
            f1 = (f3 - f4) / f6 + 2.0F;
        } else
        {
            f1 = (f4 - f2) / f6 + 4F;
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public static int blend(int i, int j)
    {
        return blend(i, j, (float)Color.alpha(j) / 255F);
    }

    public static int blend(int i, int j, float f)
    {
        int k = Color.alpha(i);
        float f1 = NumberUtils.linearInterpolate(Color.red(i), Color.red(j), f);
        float f2 = NumberUtils.linearInterpolate(Color.green(i), Color.green(j), f);
        f = NumberUtils.linearInterpolate(Color.blue(i), Color.blue(j), f);
        return Color.argb(k, (int)f1, (int)f2, (int)f);
    }

    public static int getDisplayColorFromColor(int i)
    {
        int j;
        if (updatedColors.containsKey(Integer.valueOf(i)))
        {
            j = ((Integer)updatedColors.get(Integer.valueOf(i))).intValue();
        } else
        {
            j = i;
            if (!updatedColors.containsValue(Integer.valueOf(i)))
            {
                LogUtils.d("ColorUtils", "Found untenable color: %d", new Object[] {
                    Integer.valueOf(i)
                });
                float af[] = new float[3];
                int k = Color.red(i);
                int l = Color.green(i);
                int i1 = Color.blue(i);
                if (k >= 0 && k < 256)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (l >= 0 && l < 256)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (i1 >= 0 && i1 < 256)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                float f2 = (float)k / 255F;
                float f = (float)l / 255F;
                float f1 = (float)i1 / 255F;
                float f3 = Math.max(f2, Math.max(f, f1));
                float f4 = Math.min(f2, Math.min(f, f1));
                af[2] = f3;
                if (f3 == f4)
                {
                    af[1] = 0.0F;
                    af[0] = 0.0F;
                } else
                {
                    f4 = f3 - f4;
                    af[1] = f4 / f3;
                    if (f3 == f2)
                    {
                        f2 = (f - f1) / f4;
                        if (f < f1)
                        {
                            i = 6;
                        } else
                        {
                            i = 0;
                        }
                        f = (float)i + f2;
                    } else
                    if (f3 == f)
                    {
                        f = (f1 - f2) / f4 + 2.0F;
                    } else
                    {
                        f = (f2 - f) / f4 + 4F;
                    }
                    af[0] = f / 6F;
                }
                if (af[2] > 0.79F)
                {
                    af[1] = af[1] * 1.3F;
                    af[1] = Math.min(af[1], 1.0F);
                    af[2] = af[2] * 0.8F;
                }
                f2 = af[0];
                f = af[1];
                f1 = af[2];
                if (f2 >= 0.0F && f2 <= 1.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (f >= 0.0F && f <= 1.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (f1 >= 0.0F && f1 <= 1.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (f == 0.0F)
                {
                    i = Math.round(255F * f1);
                    return Color.rgb(i, i, i);
                }
                if (f2 >= 0.0F && f2 <= 1.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (f >= 0.0F && f <= 1.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                }
                if (f1 >= 0.0F && f1 <= 1.0F)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException();
                } else
                {
                    float af1[] = new float[3];
                    af1[0] = f2;
                    f2 = 0.5F * f1 * (2.0F - f);
                    af1[2] = f2;
                    af1[1] = (f1 * f) / (1.0F - Math.abs(f2 * 2.0F - 1.0F));
                    af1[1] = Math.min(af1[1], 1.0F);
                    return hslToRgb(af1[0], af1[1], af1[2]);
                }
            }
        }
        return j;
    }

    private static int hslToRgb(float f, float f1, float f2)
    {
        boolean flag1 = true;
        boolean flag;
        if (f >= 0.0F && f <= 1.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (f1 >= 0.0F && f1 <= 1.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (f2 >= 0.0F && f2 <= 1.0F)
        {
            flag = flag1;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        }
        if (f1 == 0.0F)
        {
            int i = Math.round(f2 * 255F);
            return Color.rgb(i, i, i);
        }
        if ((double)f2 < 0.5D)
        {
            f1 = (f1 + 1.0F) * f2;
        } else
        {
            f1 = (f2 + f1) - f2 * f1;
        }
        f2 = 2.0F * f2 - f1;
        return Color.rgb(Math.round(hue2rgb(f2, f1, 0.3333333F + f) * 255F), Math.round(hue2rgb(f2, f1, f) * 255F), Math.round(hue2rgb(f2, f1, f - 0.3333333F) * 255F));
    }

    private static float hue2rgb(float f, float f1, float f2)
    {
        if (f2 >= 0.0F) goto _L2; else goto _L1
_L1:
        float f3 = f2 + 1.0F;
_L8:
        if (f3 >= 0.1666667F) goto _L4; else goto _L3
_L3:
        f2 = f + (f1 - f) * 6F * f3;
_L6:
        return f2;
_L2:
        f3 = f2;
        if (f2 > 1.0F)
        {
            f3 = f2 - 1.0F;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        f2 = f1;
        if (f3 < 0.5F) goto _L6; else goto _L5
_L5:
        if (f3 < 0.6666667F)
        {
            return f + (f1 - f) * (0.6666667F - f3) * 6F;
        }
        return f;
        if (true) goto _L8; else goto _L7
_L7:
    }

    static 
    {
        HashMap hashmap = new HashMap();
        updatedColors = hashmap;
        hashmap.put(Integer.valueOf(0xfff83a22), Integer.valueOf(0xffd50000));
        updatedColors.put(Integer.valueOf(0xfffa573c), Integer.valueOf(0xfff4511e));
        updatedColors.put(Integer.valueOf(-35529), Integer.valueOf(0xffef6c00));
        updatedColors.put(Integer.valueOf(-21178), Integer.valueOf(0xfff09300));
        updatedColors.put(Integer.valueOf(0xfffad165), Integer.valueOf(0xfff6bf26));
        updatedColors.put(Integer.valueOf(0xfffbe983), Integer.valueOf(0xffe4c441));
        updatedColors.put(Integer.valueOf(0xffb3dc6c), Integer.valueOf(0xffc0ca33));
        updatedColors.put(Integer.valueOf(0xff7bd148), Integer.valueOf(0xff7cb342));
        updatedColors.put(Integer.valueOf(0xff16a765), Integer.valueOf(0xff0b8043));
        updatedColors.put(Integer.valueOf(0xff42d692), Integer.valueOf(0xff009688));
        updatedColors.put(Integer.valueOf(0xff92e1c0), Integer.valueOf(0xff33b679));
        updatedColors.put(Integer.valueOf(0xff9fe1e7), Integer.valueOf(0xff039be5));
        updatedColors.put(Integer.valueOf(0xff9fc6e7), Integer.valueOf(0xff4285f4));
        updatedColors.put(Integer.valueOf(0xff4986e7), Integer.valueOf(0xff3f51b5));
        updatedColors.put(Integer.valueOf(0xff9a9cff), Integer.valueOf(0xff7986cb));
        updatedColors.put(Integer.valueOf(0xffb99aff), Integer.valueOf(0xffb39ddb));
        updatedColors.put(Integer.valueOf(0xffa47ae2), Integer.valueOf(0xff9e69af));
        updatedColors.put(Integer.valueOf(0xffcd74e6), Integer.valueOf(0xff8e24aa));
        updatedColors.put(Integer.valueOf(0xffcca6ac), Integer.valueOf(0xffad1457));
        updatedColors.put(Integer.valueOf(0xfff691b2), Integer.valueOf(0xffd81b60));
        updatedColors.put(Integer.valueOf(0xffd06b64), Integer.valueOf(0xffe67c73));
        updatedColors.put(Integer.valueOf(0xffac725e), Integer.valueOf(0xff795548));
        updatedColors.put(Integer.valueOf(0xffc2c2c2), Integer.valueOf(0xff616161));
        updatedColors.put(Integer.valueOf(0xffcabdbf), Integer.valueOf(0xffa79b8e));
        updatedColors.put(Integer.valueOf(0xffdc2127), Integer.valueOf(0xffd50000));
        updatedColors.put(Integer.valueOf(-18312), Integer.valueOf(0xfff4511e));
        updatedColors.put(Integer.valueOf(0xfffbd75b), Integer.valueOf(0xfff6bf26));
        updatedColors.put(Integer.valueOf(0xff51b749), Integer.valueOf(0xff0b8043));
        updatedColors.put(Integer.valueOf(0xff7ae7bf), Integer.valueOf(0xff33b679));
        updatedColors.put(Integer.valueOf(0xff46d6db), Integer.valueOf(0xff039be5));
        updatedColors.put(Integer.valueOf(0xff5484ed), Integer.valueOf(0xff3f51b5));
        updatedColors.put(Integer.valueOf(0xffa4bdfc), Integer.valueOf(0xff7986cb));
        updatedColors.put(Integer.valueOf(0xffdbadff), Integer.valueOf(0xff8e24aa));
        updatedColors.put(Integer.valueOf(-30596), Integer.valueOf(0xffe67c73));
        updatedColors.put(Integer.valueOf(0xffe1e1e1), Integer.valueOf(0xff616161));
        updatedColors.put(Integer.valueOf(0xffd40000), Integer.valueOf(0xffd50000));
        updatedColors.put(Integer.valueOf(0xfff3501d), Integer.valueOf(0xfff4511e));
        updatedColors.put(Integer.valueOf(0xffee6b00), Integer.valueOf(0xffef6c00));
        updatedColors.put(Integer.valueOf(0xffef9200), Integer.valueOf(0xfff09300));
        updatedColors.put(Integer.valueOf(0xfff5be25), Integer.valueOf(0xfff6bf26));
        updatedColors.put(Integer.valueOf(0xffe2cb4c), Integer.valueOf(0xffe4c441));
        updatedColors.put(Integer.valueOf(0xffbfc932), Integer.valueOf(0xffc0ca33));
        updatedColors.put(Integer.valueOf(0xff7bb241), Integer.valueOf(0xff7cb342));
        updatedColors.put(Integer.valueOf(0xff0a7f42), Integer.valueOf(0xff0b8043));
        updatedColors.put(Integer.valueOf(0xff009587), Integer.valueOf(0xff009688));
        updatedColors.put(Integer.valueOf(0xff029ae4), Integer.valueOf(0xff039be5));
        updatedColors.put(Integer.valueOf(0xff4184f3), Integer.valueOf(0xff4285f4));
        updatedColors.put(Integer.valueOf(0xff3f5ca9), Integer.valueOf(0xff3f51b5));
        updatedColors.put(Integer.valueOf(0xff7885ca), Integer.valueOf(0xff7986cb));
        updatedColors.put(Integer.valueOf(0xffb29cda), Integer.valueOf(0xffb39ddb));
        updatedColors.put(Integer.valueOf(0xff8d23a9), Integer.valueOf(0xff8e24aa));
        updatedColors.put(Integer.valueOf(0xffac1356), Integer.valueOf(0xffad1457));
        updatedColors.put(Integer.valueOf(0xffd71a5f), Integer.valueOf(0xffd81b60));
        updatedColors.put(Integer.valueOf(0xffe57b72), Integer.valueOf(0xffe67c73));
        updatedColors.put(Integer.valueOf(0xff785447), Integer.valueOf(0xff795548));
        updatedColors.put(Integer.valueOf(0xff636363), Integer.valueOf(0xff616161));
    }
}
