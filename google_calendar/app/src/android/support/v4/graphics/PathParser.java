// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.graphics;

import android.graphics.Path;
import java.util.ArrayList;

public final class PathParser
{

    public static boolean canMorph(PathDataNode apathdatanode[], PathDataNode apathdatanode1[])
    {
_L2:
        return false;
        if (apathdatanode == null || apathdatanode1 == null || apathdatanode.length != apathdatanode1.length) goto _L2; else goto _L1
_L1:
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= apathdatanode.length)
                {
                    break label1;
                }
                if (apathdatanode[i].mType != apathdatanode1[i].mType || apathdatanode[i].mParams.length != apathdatanode1[i].mParams.length)
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L2; else goto _L3
_L3:
        return true;
    }

    static float[] copyOfRange(float af[], int i, int j)
    {
        if (j < 0)
        {
            throw new IllegalArgumentException();
        }
        i = af.length;
        if (i < 0)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            i = Math.min(j, i);
            float af1[] = new float[j];
            System.arraycopy(af, 0, af1, 0, i);
            return af1;
        }
    }

    public static PathDataNode[] createNodesFromPathData(String s)
    {
        if (s == null)
        {
            return null;
        }
        ArrayList arraylist = new ArrayList();
        int i = 1;
        int j = 0;
        int k;
        for (; i < s.length(); i = k)
        {
            do
            {
                if (i >= s.length())
                {
                    break;
                }
                char c = s.charAt(i);
                if (((c - 65) * (c - 90) <= 0 || (c - 97) * (c - 122) <= 0) && c != 'e' && c != 'E')
                {
                    break;
                }
                i++;
            } while (true);
            String s1 = s.substring(j, i).trim();
            if (s1.length() > 0)
            {
                float af[] = getFloats(s1);
                arraylist.add(new PathDataNode(s1.charAt(0), af));
            }
            k = i + 1;
            j = i;
        }

        if (i - j == 1 && j < s.length())
        {
            arraylist.add(new PathDataNode(s.charAt(j), new float[0]));
        }
        return (PathDataNode[])arraylist.toArray(new PathDataNode[arraylist.size()]);
    }

    public static Path createPathFromPathData(String s)
    {
        Path path = new Path();
        PathDataNode apathdatanode[] = createNodesFromPathData(s);
        if (apathdatanode != null)
        {
            try
            {
                PathDataNode.nodesToPath(apathdatanode, path);
            }
            catch (RuntimeException runtimeexception)
            {
                throw new RuntimeException((new StringBuilder("Error in parsing ")).append(s).toString(), runtimeexception);
            }
            return path;
        } else
        {
            return null;
        }
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode apathdatanode[])
    {
        if (apathdatanode == null)
        {
            return null;
        }
        PathDataNode apathdatanode1[] = new PathDataNode[apathdatanode.length];
        for (int i = 0; i < apathdatanode.length; i++)
        {
            apathdatanode1[i] = new PathDataNode(apathdatanode[i]);
        }

        return apathdatanode1;
    }

    private static float[] getFloats(String s)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        if (s.charAt(0) == 'z' || s.charAt(0) == 'Z')
        {
            return new float[0];
        }
        float af[];
        ExtractFloatResult extractfloatresult;
        int l1;
        try
        {
            af = new float[s.length()];
            extractfloatresult = new ExtractFloatResult();
            l1 = s.length();
        }
        catch (NumberFormatException numberformatexception)
        {
            throw new RuntimeException((new StringBuilder("error in parsing \"")).append(s).append("\"").toString(), numberformatexception);
        }
        i1 = 1;
        l = 0;
        if (i1 >= l1) goto _L2; else goto _L1
_L1:
        extractfloatresult.mEndWithNegOrDot = false;
        k = 0;
        i = 0;
        j = 0;
        j1 = i1;
_L16:
        if (j1 >= s.length()) goto _L4; else goto _L3
_L3:
        s.charAt(j1);
        JVM INSTR lookupswitch 6: default 295
    //                   32: 340
    //                   44: 340
    //                   45: 148
    //                   46: 351
    //                   69: 367
    //                   101: 367;
           goto _L5 _L6 _L6 _L7 _L8 _L9 _L9
_L7:
        if (j1 == i1 || k != 0) goto _L5; else goto _L10
_L10:
        extractfloatresult.mEndWithNegOrDot = true;
        j = i;
        k = 1;
        i = 0;
          goto _L11
_L18:
        extractfloatresult.mEndWithNegOrDot = true;
        j = i;
        k = 1;
        i = 0;
          goto _L11
_L4:
        extractfloatresult.mEndPosition = j1;
        j = extractfloatresult.mEndPosition;
        if (i1 >= j) goto _L13; else goto _L12
_L12:
        i = l + 1;
        af[l] = Float.parseFloat(s.substring(i1, j));
_L14:
        int k1;
        if (extractfloatresult.mEndWithNegOrDot)
        {
            i1 = j;
            l = i;
        } else
        {
            i1 = j + 1;
            l = i;
        }
        break MISSING_BLOCK_LABEL_51;
_L2:
        af = copyOfRange(af, 0, l);
        return af;
_L13:
        i = l;
          goto _L14
_L5:
        k1 = 0;
        k = j;
        j = i;
        i = k1;
_L11:
        if (k != 0) goto _L4; else goto _L15
_L15:
        k1 = j1 + 1;
        j1 = i;
        i = j;
        j = k;
        k = j1;
        j1 = k1;
          goto _L16
_L6:
        j = i;
        k = 1;
        i = 0;
          goto _L11
_L8:
        if (i != 0) goto _L18; else goto _L17
_L17:
        i = 0;
        k = j;
        j = 1;
          goto _L11
_L9:
        k = j;
        j = i;
        i = 1;
          goto _L11
    }

    private class PathDataNode
    {

        public float mParams[];
        public char mType;

        private static void drawArc(Path path, float f, float f1, float f2, float f3, float f4, float f5, float f6, 
                boolean flag, boolean flag1)
        {
_L3:
            double d;
            double d1;
            double d2;
            double d3;
            double d4;
            double d5;
            double d7;
            double d9;
            double d10;
            double d11;
            double d14;
            double d15;
            d5 = Math.toRadians(f6);
            d14 = Math.cos(d5);
            d15 = Math.sin(d5);
            d4 = ((double)f * d14 + (double)f1 * d15) / (double)f4;
            d7 = ((double)(-f) * d15 + (double)f1 * d14) / (double)f5;
            d = ((double)f2 * d14 + (double)f3 * d15) / (double)f4;
            d3 = ((double)(-f2) * d15 + (double)f3 * d14) / (double)f5;
            d10 = d4 - d;
            d9 = d7 - d3;
            d2 = (d4 + d) / 2D;
            d1 = (d7 + d3) / 2D;
            d11 = d10 * d10 + d9 * d9;
            if (d11 != 0.0D) goto _L2; else goto _L1
_L1:
            Log.w("PathParser", " Points are coincident");
_L4:
            return;
_L2:
            double d16;
label0:
            {
                d16 = 1.0D / d11 - 0.25D;
                if (d16 >= 0.0D)
                {
                    break label0;
                }
                Log.w("PathParser", (new StringBuilder("Points are too far apart ")).append(d11).toString());
                float f7 = (float)(Math.sqrt(d11) / 1.9999899999999999D);
                f4 *= f7;
                f5 *= f7;
            }
              goto _L3
            double d12 = Math.sqrt(d16);
            d10 *= d12;
            d9 *= d12;
            double d17;
            double d18;
            double d19;
            double d20;
            double d21;
            double d22;
            int i;
            int j;
            if (flag == flag1)
            {
                d2 -= d9;
                d1 = d10 + d1;
            } else
            {
                d2 = d9 + d2;
                d1 -= d10;
            }
            d4 = Math.atan2(d7 - d1, d4 - d2);
            d3 = Math.atan2(d3 - d1, d - d2) - d4;
            if (d3 >= 0.0D)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            d = d3;
            if (flag1 != flag)
            {
                if (d3 > 0.0D)
                {
                    d = d3 - 6.2831853071795862D;
                } else
                {
                    d = d3 + 6.2831853071795862D;
                }
            }
            d16 = d2 * (double)f4;
            d17 = d1 * (double)f5;
            d18 = f4;
            d19 = f5;
            d3 = f;
            d2 = f1;
            j = (int)Math.ceil(Math.abs((4D * d) / 3.1415926535897931D));
            d20 = Math.cos(d5);
            d21 = Math.sin(d5);
            d5 = Math.cos(d4);
            d7 = Math.sin(d4);
            d1 = -d18 * d20 * d7 - d19 * d21 * d5;
            d5 = d5 * (d19 * d20) + d7 * (-d18 * d21);
            d22 = d / (double)j;
            i = 0;
            d = d5;
            while (i < j) 
            {
                d9 = d4 + d22;
                double d13 = Math.sin(d9);
                double d23 = Math.cos(d9);
                double d6 = (d18 * d20 * d23 + (d16 * d14 - d17 * d15)) - d19 * d21 * d13;
                double d8 = d19 * d20 * d13 + (d18 * d21 * d23 + (d16 * d15 + d17 * d14));
                d10 = -d18 * d20 * d13 - d19 * d21 * d23;
                d13 = d13 * (-d18 * d21) + d23 * (d19 * d20);
                d23 = Math.tan((d9 - d4) / 2D);
                d4 = (Math.sin(d9 - d4) * (Math.sqrt(d23 * (3D * d23) + 4D) - 1.0D)) / 3D;
                path.rLineTo(0.0F, 0.0F);
                path.cubicTo((float)(d1 * d4 + d3), (float)(d * d4 + d2), (float)(d6 - d4 * d10), (float)(d8 - d4 * d13), (float)d6, (float)d8);
                i++;
                d = d13;
                d1 = d10;
                d4 = d9;
                d2 = d8;
                d3 = d6;
            }
              goto _L4
        }

        public static void nodesToPath(PathDataNode apathdatanode[], Path path)
        {
            float af[];
            char c;
            int i;
            af = new float[6];
            i = 0;
            c = 'm';
_L32:
            float f;
            float f1;
            float f2;
            float f3;
            float f4;
            float f5;
            float af1[];
            char c1;
            if (i >= apathdatanode.length)
            {
                break; /* Loop/switch isn't completed */
            }
            c1 = apathdatanode[i].mType;
            af1 = apathdatanode[i].mParams;
            f3 = af[0];
            f2 = af[1];
            f5 = af[2];
            f4 = af[3];
            f = af[4];
            f1 = af[5];
            c1;
            JVM INSTR lookupswitch 20: default 244
        //                       65: 557
        //                       67: 544
        //                       72: 538
        //                       76: 532
        //                       77: 532
        //                       81: 551
        //                       83: 551
        //                       84: 532
        //                       86: 538
        //                       90: 504
        //                       97: 557
        //                       99: 544
        //                       104: 538
        //                       108: 532
        //                       109: 532
        //                       113: 551
        //                       115: 551
        //                       116: 532
        //                       118: 538
        //                       122: 504;
               goto _L1 _L2 _L3 _L4 _L5 _L5 _L6 _L6 _L5 _L4 _L7 _L2 _L3 _L4 _L5 _L5 _L6 _L6 _L5 _L4 _L7
_L1:
            byte byte0 = 2;
_L29:
            float f6;
            float f7;
            int j;
            j = 0;
            f6 = f2;
            f2 = f3;
            f3 = f6;
            f6 = f5;
            f7 = f4;
_L28:
            if (j >= af1.length)
            {
                break MISSING_BLOCK_LABEL_2327;
            }
            c1;
            JVM INSTR lookupswitch 18: default 436
        //                       65: 2181
        //                       67: 1239
        //                       72: 979
        //                       76: 873
        //                       77: 687
        //                       81: 1710
        //                       83: 1482
        //                       84: 1910
        //                       86: 1075
        //                       97: 2023
        //                       99: 1124
        //                       104: 928
        //                       108: 804
        //                       109: 564
        //                       113: 1611
        //                       115: 1334
        //                       116: 1789
        //                       118: 1024;
               goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26
_L22:
            break; /* Loop/switch isn't completed */
_L9:
            break MISSING_BLOCK_LABEL_2181;
_L8:
            f4 = f1;
            f1 = f3;
            f3 = f2;
            f2 = f1;
            f1 = f;
            f5 = f6;
            f = f4;
            f4 = f7;
_L30:
            j += byte0;
            float f8 = f1;
            float f20 = f3;
            c = c1;
            f7 = f4;
            f6 = f5;
            f1 = f;
            f = f8;
            f3 = f2;
            f2 = f20;
            if (true) goto _L28; else goto _L27
_L7:
            path.close();
            path.moveTo(f, f1);
            f4 = f1;
            f5 = f;
            f2 = f1;
            f3 = f;
            byte0 = 2;
              goto _L29
_L5:
            byte0 = 2;
              goto _L29
_L4:
            byte0 = 1;
              goto _L29
_L3:
            byte0 = 6;
              goto _L29
_L6:
            byte0 = 4;
              goto _L29
_L2:
            byte0 = 7;
              goto _L29
_L27:
            f2 += af1[j];
            f3 += af1[j + 1];
            if (j > 0)
            {
                path.rLineTo(af1[j], af1[j + 1]);
                float f9 = f2;
                f2 = f1;
                f1 = f;
                f4 = f7;
                f = f2;
                f5 = f6;
                f2 = f3;
                f3 = f9;
            } else
            {
                path.rMoveTo(af1[j], af1[j + 1]);
                float f21 = f3;
                float f10 = f2;
                f4 = f7;
                f = f3;
                f5 = f6;
                f1 = f2;
                f2 = f21;
                f3 = f10;
            }
              goto _L30
_L13:
            f2 = af1[j];
            f3 = af1[j + 1];
            if (j > 0)
            {
                path.lineTo(af1[j], af1[j + 1]);
                float f11 = f2;
                f2 = f1;
                f1 = f;
                f4 = f7;
                f = f2;
                f5 = f6;
                f2 = f3;
                f3 = f11;
            } else
            {
                path.moveTo(af1[j], af1[j + 1]);
                float f22 = f3;
                float f12 = f2;
                f4 = f7;
                f = f3;
                f5 = f6;
                f1 = f2;
                f2 = f22;
                f3 = f12;
            }
              goto _L30
_L21:
            path.rLineTo(af1[j], af1[j + 1]);
            f4 = af1[j];
            f3 += af1[j + 1];
            float f13 = f2 + f4;
            f2 = f1;
            f1 = f;
            f4 = f7;
            f = f2;
            f5 = f6;
            f2 = f3;
            f3 = f13;
              goto _L30
_L12:
            path.lineTo(af1[j], af1[j + 1]);
            f3 = af1[j];
            float f14 = af1[j + 1];
            f2 = f1;
            f1 = f;
            f4 = f7;
            f = f2;
            f5 = f6;
            f2 = f14;
              goto _L30
_L20:
            path.rLineTo(af1[j], 0.0F);
            f4 = af1[j];
            float f15 = f2 + f4;
            f2 = f;
            f4 = f7;
            f = f1;
            f5 = f6;
            f1 = f2;
            f2 = f3;
            f3 = f15;
              goto _L30
_L11:
            path.lineTo(af1[j], f3);
            f4 = af1[j];
            f2 = f3;
            f3 = f4;
            float f16 = f;
            f4 = f7;
            f = f1;
            f5 = f6;
            f1 = f16;
              goto _L30
_L26:
            path.rLineTo(0.0F, af1[j]);
            f4 = af1[j];
            float f17 = f;
            float f23 = f3 + f4;
            f3 = f2;
            f = f1;
            f4 = f7;
            f5 = f6;
            f1 = f17;
            f2 = f23;
              goto _L30
_L17:
            path.lineTo(f2, af1[j]);
            float f18 = af1[j];
            f3 = f;
            float f24 = f2;
            f = f1;
            f4 = f7;
            f5 = f6;
            f1 = f3;
            f2 = f18;
            f3 = f24;
              goto _L30
_L19:
            path.rCubicTo(af1[j], af1[j + 1], af1[j + 2], af1[j + 3], af1[j + 4], af1[j + 5]);
            f5 = f2 + af1[j + 2];
            f4 = f3 + af1[j + 3];
            f6 = af1[j + 4];
            f3 += af1[j + 5];
            f6 = f2 + f6;
            f2 = f1;
            f1 = f;
            f = f2;
            f2 = f3;
            f3 = f6;
              goto _L30
_L10:
            path.cubicTo(af1[j], af1[j + 1], af1[j + 2], af1[j + 3], af1[j + 4], af1[j + 5]);
            f3 = af1[j + 4];
            f2 = af1[j + 5];
            f6 = af1[j + 2];
            f4 = af1[j + 3];
            f5 = f1;
            f1 = f;
            f = f5;
            f5 = f6;
              goto _L30
_L24:
            f5 = 0.0F;
            float f19;
            boolean flag;
            boolean flag1;
            if (c == 'c' || c == 's' || c == 'C' || c == 'S')
            {
                f5 = f3 - f7;
                f4 = f2 - f6;
            } else
            {
                f4 = 0.0F;
            }
            path.rCubicTo(f4, f5, af1[j], af1[j + 1], af1[j + 2], af1[j + 3]);
            f5 = f2 + af1[j];
            f4 = f3 + af1[j + 1];
            f6 = af1[j + 2];
            f3 += af1[j + 3];
            f6 = f2 + f6;
            f2 = f1;
            f1 = f;
            f = f2;
            f2 = f3;
            f3 = f6;
              goto _L30
_L15:
            if (c == 'c' || c == 's' || c == 'C' || c == 'S')
            {
                f3 = 2.0F * f3 - f7;
                f2 = 2.0F * f2 - f6;
            }
            path.cubicTo(f2, f3, af1[j], af1[j + 1], af1[j + 2], af1[j + 3]);
            f5 = af1[j];
            f4 = af1[j + 1];
            f3 = af1[j + 2];
            f6 = af1[j + 3];
            f2 = f1;
            f1 = f;
            f = f2;
            f2 = f6;
              goto _L30
_L23:
            path.rQuadTo(af1[j], af1[j + 1], af1[j + 2], af1[j + 3]);
            f5 = f2 + af1[j];
            f4 = f3 + af1[j + 1];
            f6 = af1[j + 2];
            f3 += af1[j + 3];
            f6 = f2 + f6;
            f2 = f1;
            f1 = f;
            f = f2;
            f2 = f3;
            f3 = f6;
              goto _L30
_L14:
            path.quadTo(af1[j], af1[j + 1], af1[j + 2], af1[j + 3]);
            f5 = af1[j];
            f4 = af1[j + 1];
            f3 = af1[j + 2];
            f6 = af1[j + 3];
            f2 = f1;
            f1 = f;
            f = f2;
            f2 = f6;
              goto _L30
_L25:
            if (c == 'q' || c == 't' || c == 'Q' || c == 'T')
            {
                f4 = f3 - f7;
                f5 = f2 - f6;
            } else
            {
                f4 = 0.0F;
                f5 = 0.0F;
            }
            path.rQuadTo(f5, f4, af1[j], af1[j + 1]);
            f5 = f2 + f5;
            f4 = f3 + f4;
            f6 = af1[j];
            f3 += af1[j + 1];
            f6 = f2 + f6;
            f2 = f1;
            f1 = f;
            f = f2;
            f2 = f3;
            f3 = f6;
              goto _L30
_L16:
label0:
            {
                if (c != 'q' && c != 't' && c != 'Q')
                {
                    f5 = f3;
                    f4 = f2;
                    if (c != 'T')
                    {
                        break label0;
                    }
                }
                f4 = 2.0F * f2 - f6;
                f5 = 2.0F * f3 - f7;
            }
            path.quadTo(f4, f5, af1[j], af1[j + 1]);
            f3 = af1[j];
            f2 = af1[j + 1];
            f6 = f5;
            f5 = f4;
            f7 = f1;
            f1 = f;
            f4 = f6;
            f = f7;
              goto _L30
_L18:
            f4 = af1[j + 5];
            f5 = af1[j + 6];
            f6 = af1[j];
            f7 = af1[j + 1];
            f19 = af1[j + 2];
            if (af1[j + 3] != 0.0F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (af1[j + 4] != 0.0F)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            drawArc(path, f2, f3, f4 + f2, f5 + f3, f6, f7, f19, flag, flag1);
            f5 = f2 + af1[j + 5];
            f4 = f3 + af1[j + 6];
            f6 = f1;
            f1 = f;
            f2 = f4;
            f3 = f5;
            f = f6;
              goto _L30
            f4 = af1[j + 5];
            f5 = af1[j + 6];
            f6 = af1[j];
            f7 = af1[j + 1];
            f19 = af1[j + 2];
            if (af1[j + 3] != 0.0F)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (af1[j + 4] != 0.0F)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            drawArc(path, f2, f3, f4, f5, f6, f7, f19, flag, flag1);
            f5 = af1[j + 5];
            f4 = af1[j + 6];
            f6 = f1;
            f1 = f;
            f2 = f4;
            f3 = f5;
            f = f6;
              goto _L30
            af[0] = f2;
            af[1] = f3;
            af[2] = f6;
            af[3] = f7;
            af[4] = f;
            af[5] = f1;
            c = apathdatanode[i].mType;
            i++;
            if (true) goto _L32; else goto _L31
_L31:
        }

        PathDataNode(char c, float af[])
        {
            mType = c;
            mParams = af;
        }

        PathDataNode(PathDataNode pathdatanode)
        {
            mType = pathdatanode.mType;
            mParams = PathParser.copyOfRange(pathdatanode.mParams, 0, pathdatanode.mParams.length);
        }
    }


    private class ExtractFloatResult
    {

        public int mEndPosition;
        public boolean mEndWithNegOrDot;

        ExtractFloatResult()
        {
        }
    }

}