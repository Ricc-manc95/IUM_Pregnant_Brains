// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PathMeasure;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import android.view.animation.AnimationUtils;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            AndroidResources, ArgbEvaluator

public final class AnimatorInflaterCompat
{

    private static Animator createAnimatorFromXml(Context context, Resources resources, android.content.res.Resources.Theme theme, XmlPullParser xmlpullparser, AttributeSet attributeset, AnimatorSet animatorset, int i, float f)
        throws XmlPullParserException, IOException
    {
        ArrayList arraylist1;
        Object obj4;
        int i2;
        obj4 = null;
        arraylist1 = null;
        i2 = xmlpullparser.getDepth();
_L2:
        Object obj;
        int j;
        j = xmlpullparser.next();
        if (j == 3 && xmlpullparser.getDepth() <= i2 || j == 1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j != 2)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj = xmlpullparser.getName();
        ArrayList arraylist;
        if (((String) (obj)).equals("objectAnimator"))
        {
            obj = new ObjectAnimator();
            loadAnimator(context, resources, theme, attributeset, ((ValueAnimator) (obj)), f, xmlpullparser);
            j = 0;
        } else
        if (((String) (obj)).equals("animator"))
        {
            obj = loadAnimator(context, resources, theme, attributeset, null, f, xmlpullparser);
            j = 0;
        } else
        {
label0:
            {
                if (!((String) (obj)).equals("set"))
                {
                    break label0;
                }
                AnimatorSet animatorset1 = new AnimatorSet();
                obj = AndroidResources.STYLEABLE_ANIMATOR_SET;
                boolean flag;
                if (theme == null)
                {
                    obj = resources.obtainAttributes(attributeset, ((int []) (obj)));
                } else
                {
                    obj = theme.obtainStyledAttributes(attributeset, ((int []) (obj)), 0, 0);
                }
                flag = false;
                if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "ordering") != null)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j == 0)
                {
                    j = ((flag) ? 1 : 0);
                } else
                {
                    j = ((TypedArray) (obj)).getInt(0, 0);
                }
                createAnimatorFromXml(context, resources, theme, xmlpullparser, attributeset, (AnimatorSet)animatorset1, j, f);
                ((TypedArray) (obj)).recycle();
                j = 0;
                obj = animatorset1;
            }
        }
_L31:
        obj4 = obj;
        if (animatorset != null)
        {
            obj4 = obj;
            if (j == 0)
            {
                arraylist = arraylist1;
                if (arraylist1 == null)
                {
                    arraylist = new ArrayList();
                }
                arraylist.add(obj);
                obj4 = obj;
                arraylist1 = arraylist;
            }
        }
        if (true) goto _L2; else goto _L1
        if (!((String) (obj)).equals("propertyValuesHolder")) goto _L4; else goto _L3
_L3:
        AttributeSet attributeset1;
        attributeset1 = Xml.asAttributeSet(xmlpullparser);
        obj = null;
_L7:
        j = xmlpullparser.getEventType();
        if (j == 3 || j == 1) goto _L6; else goto _L5
_L5:
label1:
        {
            if (j == 2)
            {
                break label1;
            }
            xmlpullparser.next();
        }
          goto _L7
        Object obj1 = obj;
        if (!xmlpullparser.getName().equals("propertyValuesHolder")) goto _L9; else goto _L8
_L8:
        float f1;
        Object obj2;
        int ai[];
        TypedArray typedarray;
        String s;
        int k;
        int l;
        obj1 = AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER;
        if (theme == null)
        {
            typedarray = resources.obtainAttributes(attributeset1, ((int []) (obj1)));
        } else
        {
            typedarray = theme.obtainStyledAttributes(attributeset1, ((int []) (obj1)), 0, 0);
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "propertyName") != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            s = null;
        } else
        {
            s = typedarray.getString(3);
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueType") != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            l = 4;
        } else
        {
            l = typedarray.getInt(2, 4);
        }
        obj1 = null;
        j = l;
        k = xmlpullparser.next();
        if (k == 3 || k == 1) goto _L11; else goto _L10
_L10:
        if (!xmlpullparser.getName().equals("keyframe"))
        {
            break MISSING_BLOCK_LABEL_460;
        }
        if (j != 4) goto _L13; else goto _L12
_L12:
        obj2 = Xml.asAttributeSet(xmlpullparser);
        ai = AndroidResources.STYLEABLE_KEYFRAME;
        if (theme == null)
        {
            obj2 = resources.obtainAttributes(((AttributeSet) (obj2)), ai);
        } else
        {
            obj2 = theme.obtainStyledAttributes(((AttributeSet) (obj2)), ai, 0, 0);
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "value") != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0)
        {
            ai = null;
        } else
        {
            ai = ((TypedArray) (obj2)).peekValue(0);
        }
        if (ai != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L15; else goto _L14
_L14:
        j = ((TypedValue) (ai)).type;
        if (j >= 28 && j <= 31)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L15; else goto _L16
_L16:
        j = 3;
_L25:
        ((TypedArray) (obj2)).recycle();
_L13:
        obj2 = Xml.asAttributeSet(xmlpullparser);
        ai = AndroidResources.STYLEABLE_KEYFRAME;
        boolean flag1;
        if (theme == null)
        {
            ai = resources.obtainAttributes(((AttributeSet) (obj2)), ai);
        } else
        {
            ai = theme.obtainStyledAttributes(((AttributeSet) (obj2)), ai, 0, 0);
        }
        f1 = -1F;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "fraction") != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            f1 = ai.getFloat(3, -1F);
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "value") != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0)
        {
            obj2 = null;
        } else
        {
            obj2 = ai.peekValue(0);
        }
        if (obj2 != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (j != 4)
        {
            break MISSING_BLOCK_LABEL_1869;
        }
        if (!flag1) goto _L18; else goto _L17
_L17:
        k = ((TypedValue) (obj2)).type;
        if (k >= 28 && k <= 31)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0) goto _L18; else goto _L19
_L19:
        k = 3;
_L26:
        if (!flag1) goto _L21; else goto _L20
_L20:
        k;
        JVM INSTR tableswitch 0 3: default 760
    //                   0 1026
    //                   1 1080
    //                   2 760
    //                   3 1080;
           goto _L22 _L23 _L24 _L22 _L24
_L22:
        obj2 = null;
_L27:
        float f4;
        boolean flag2;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "interpolator") != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0)
        {
            k = 0;
        } else
        {
            k = ai.getResourceId(1, 0);
        }
        if (k > 0)
        {
            ((Keyframe) (obj2)).setInterpolator(AnimationUtils.loadInterpolator(context, k));
        }
        ai.recycle();
        ai = ((int []) (obj1));
        if (obj2 != null)
        {
            ai = ((int []) (obj1));
            if (obj1 == null)
            {
                ai = new ArrayList();
            }
            ai.add(obj2);
        }
        xmlpullparser.next();
        obj1 = ai;
        break MISSING_BLOCK_LABEL_460;
_L15:
        j = 0;
          goto _L25
_L18:
        k = 0;
          goto _L26
_L23:
        f4 = 0.0F;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "value") != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0)
        {
            f4 = ai.getFloat(0, 0.0F);
        }
        obj2 = Keyframe.ofFloat(f1, f4);
          goto _L27
_L24:
        flag2 = false;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "value") != null)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0)
        {
            k = ((flag2) ? 1 : 0);
        } else
        {
            k = ai.getInt(0, 0);
        }
        obj2 = Keyframe.ofInt(f1, k);
          goto _L27
_L21:
        if (k == 0)
        {
            obj2 = Keyframe.ofFloat(f1);
        } else
        {
            obj2 = Keyframe.ofInt(f1);
        }
          goto _L27
_L11:
        if (obj1 == null) goto _L29; else goto _L28
_L28:
        k = ((ArrayList) (obj1)).size();
        if (k <= 0) goto _L29; else goto _L30
_L30:
        Keyframe akeyframe[] = (Keyframe)((ArrayList) (obj1)).get(0);
        Keyframe keyframe = (Keyframe)((ArrayList) (obj1)).get(k - 1);
        float f2 = keyframe.getFraction();
        int k1;
        if (f2 < 1.0F)
        {
            if (f2 < 0.0F)
            {
                keyframe.setFraction(1.0F);
            } else
            {
                ((ArrayList) (obj1)).add(((ArrayList) (obj1)).size(), createNewKeyframe(keyframe, 1.0F));
                k++;
            }
        }
        f2 = akeyframe.getFraction();
        k1 = k;
        if (f2 != 0.0F)
        {
            if (f2 < 0.0F)
            {
                akeyframe.setFraction(0.0F);
                k1 = k;
            } else
            {
                ((ArrayList) (obj1)).add(0, createNewKeyframe(akeyframe, 0.0F));
                k1 = k + 1;
            }
        }
        akeyframe = new Keyframe[k1];
        ((ArrayList) (obj1)).toArray(akeyframe);
        k = 0;
        while (k < k1) 
        {
            obj1 = akeyframe[k];
            if (((Keyframe) (obj1)).getFraction() < 0.0F)
            {
                if (k == 0)
                {
                    ((Keyframe) (obj1)).setFraction(0.0F);
                } else
                if (k == k1 - 1)
                {
                    ((Keyframe) (obj1)).setFraction(1.0F);
                } else
                {
                    int j1 = k + 1;
                    int l1 = k;
                    for (; j1 < k1 - 1 && akeyframe[j1].getFraction() < 0.0F; j1++)
                    {
                        l1 = j1;
                    }

                    float f3 = (akeyframe[l1 + 1].getFraction() - akeyframe[k - 1].getFraction()) / (float)((l1 - k) + 2);
                    j1 = k;
                    while (j1 <= l1) 
                    {
                        akeyframe[j1].setFraction(akeyframe[j1 - 1].getFraction() + f3);
                        j1++;
                    }
                }
            }
            k++;
        }
        PropertyValuesHolder propertyvaluesholder = PropertyValuesHolder.ofKeyframe(s, akeyframe);
        obj1 = propertyvaluesholder;
        if (j == 3)
        {
            propertyvaluesholder.setEvaluator(ArgbEvaluator.sInstance);
            obj1 = propertyvaluesholder;
        }
_L32:
        Object obj3 = obj1;
        if (obj1 == null)
        {
            obj3 = getPVH(typedarray, l, 0, 1, s);
        }
        obj1 = obj;
        if (obj3 != null)
        {
            obj1 = obj;
            if (obj == null)
            {
                obj1 = new ArrayList();
            }
            ((ArrayList) (obj1)).add(obj3);
        }
        typedarray.recycle();
_L9:
        xmlpullparser.next();
        obj = obj1;
          goto _L7
_L6:
        if (obj != null)
        {
            k = ((ArrayList) (obj)).size();
            PropertyValuesHolder apropertyvaluesholder[] = new PropertyValuesHolder[k];
            j = 0;
            do
            {
                obj1 = apropertyvaluesholder;
                if (j >= k)
                {
                    break;
                }
                apropertyvaluesholder[j] = (PropertyValuesHolder)((ArrayList) (obj)).get(j);
                j++;
            } while (true);
        } else
        {
            obj1 = null;
        }
        if (obj1 != null && obj4 != null && (obj4 instanceof ValueAnimator))
        {
            ((ValueAnimator)obj4).setValues(((PropertyValuesHolder []) (obj1)));
        }
        j = 1;
        obj = obj4;
          goto _L31
_L4:
        throw new RuntimeException((new StringBuilder("Unknown animator name: ")).append(xmlpullparser.getName()).toString());
_L1:
label2:
        {
            if (animatorset != null && arraylist1 != null)
            {
                context = new Animator[arraylist1.size()];
                resources = (ArrayList)arraylist1;
                int i1 = resources.size();
                j = 0;
                for (k = 0; k < i1;)
                {
                    theme = ((android.content.res.Resources.Theme) (resources.get(k)));
                    k++;
                    context[j] = (Animator)theme;
                    j++;
                }

                if (i != 0)
                {
                    break label2;
                }
                animatorset.playTogether(context);
            }
            return ((Animator) (obj4));
        }
        animatorset.playSequentially(context);
        return ((Animator) (obj4));
_L29:
        obj1 = null;
          goto _L32
        k = j;
          goto _L26
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f)
    {
        if (keyframe.getType() == Float.TYPE)
        {
            return Keyframe.ofFloat(f);
        }
        if (keyframe.getType() == Integer.TYPE)
        {
            return Keyframe.ofInt(f);
        } else
        {
            return Keyframe.ofObject(f);
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedarray, int i, int j, int k, String s)
    {
        android.support.v4.graphics.PathParser.PathDataNode apathdatanode[];
        android.support.v4.graphics.PathParser.PathDataNode apathdatanode1[];
        PathDataEvaluator pathdataevaluator;
        int l;
        boolean flag;
        int i1;
        boolean flag1;
        int j1;
        Object obj = typedarray.peekValue(j);
        if (obj != null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            j1 = ((TypedValue) (obj)).type;
        } else
        {
            j1 = 0;
        }
        obj = typedarray.peekValue(k);
        if (obj != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i1 = ((TypedValue) (obj)).type;
        } else
        {
            i1 = 0;
        }
        l = i;
        if (i != 4) goto _L2; else goto _L1
_L1:
        if (!flag1) goto _L4; else goto _L3
_L3:
        if (j1 >= 28 && j1 <= 31)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L5; else goto _L4
_L4:
        if (!flag) goto _L7; else goto _L6
_L6:
        if (i1 >= 28 && i1 <= 31)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L7; else goto _L5
_L5:
        l = 3;
_L2:
        if (l == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (l != 2) goto _L9; else goto _L8
_L8:
        obj = typedarray.getString(j);
        typedarray = typedarray.getString(k);
        apathdatanode = PathParser.createNodesFromPathData(((String) (obj)));
        apathdatanode1 = PathParser.createNodesFromPathData(typedarray);
        if (apathdatanode == null && apathdatanode1 == null)
        {
            break MISSING_BLOCK_LABEL_772;
        }
        if (apathdatanode == null) goto _L11; else goto _L10
_L10:
        pathdataevaluator = new PathDataEvaluator();
        if (apathdatanode1 == null) goto _L13; else goto _L12
_L7:
        l = 0;
          goto _L2
_L12:
        if (!PathParser.canMorph(apathdatanode, apathdatanode1))
        {
            throw new InflateException((new StringBuilder(" Can't morph from ")).append(((String) (obj))).append(" to ").append(typedarray).toString());
        }
        s = PropertyValuesHolder.ofObject(s, pathdataevaluator, new Object[] {
            apathdatanode, apathdatanode1
        });
_L15:
        return s;
_L13:
        return PropertyValuesHolder.ofObject(s, pathdataevaluator, new Object[] {
            apathdatanode
        });
_L11:
        if (apathdatanode1 != null)
        {
            return PropertyValuesHolder.ofObject(s, new PathDataEvaluator(), new Object[] {
                apathdatanode1
            });
        }
        break MISSING_BLOCK_LABEL_772;
_L9:
        ArgbEvaluator argbevaluator = null;
        if (l == 3)
        {
            argbevaluator = ArgbEvaluator.sInstance;
        }
        if (i != 0)
        {
            if (flag1)
            {
                float f;
                if (j1 == 5)
                {
                    f = typedarray.getDimension(j, 0.0F);
                } else
                {
                    f = typedarray.getFloat(j, 0.0F);
                }
                if (flag)
                {
                    float f2;
                    if (i1 == 5)
                    {
                        f2 = typedarray.getDimension(k, 0.0F);
                    } else
                    {
                        f2 = typedarray.getFloat(k, 0.0F);
                    }
                    typedarray = PropertyValuesHolder.ofFloat(s, new float[] {
                        f, f2
                    });
                } else
                {
                    typedarray = PropertyValuesHolder.ofFloat(s, new float[] {
                        f
                    });
                }
            } else
            {
                float f1;
                if (i1 == 5)
                {
                    f1 = typedarray.getDimension(k, 0.0F);
                } else
                {
                    f1 = typedarray.getFloat(k, 0.0F);
                }
                typedarray = PropertyValuesHolder.ofFloat(s, new float[] {
                    f1
                });
            }
        } else
        if (flag1)
        {
            if (j1 == 5)
            {
                i = (int)typedarray.getDimension(j, 0.0F);
            } else
            {
                if (j1 >= 28 && j1 <= 31)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    i = typedarray.getColor(j, 0);
                } else
                {
                    i = typedarray.getInt(j, 0);
                }
            }
            if (flag)
            {
                if (i1 == 5)
                {
                    j = (int)typedarray.getDimension(k, 0.0F);
                } else
                {
                    if (i1 >= 28 && i1 <= 31)
                    {
                        j = 1;
                    } else
                    {
                        j = 0;
                    }
                    if (j != 0)
                    {
                        j = typedarray.getColor(k, 0);
                    } else
                    {
                        j = typedarray.getInt(k, 0);
                    }
                }
                typedarray = PropertyValuesHolder.ofInt(s, new int[] {
                    i, j
                });
            } else
            {
                typedarray = PropertyValuesHolder.ofInt(s, new int[] {
                    i
                });
            }
        } else
        if (flag)
        {
            if (i1 == 5)
            {
                i = (int)typedarray.getDimension(k, 0.0F);
            } else
            {
                if (i1 >= 28 && i1 <= 31)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    i = typedarray.getColor(k, 0);
                } else
                {
                    i = typedarray.getInt(k, 0);
                }
            }
            typedarray = PropertyValuesHolder.ofInt(s, new int[] {
                i
            });
        } else
        {
            typedarray = null;
        }
        s = typedarray;
        if (typedarray == null) goto _L15; else goto _L14
_L14:
        s = typedarray;
        if (argbevaluator == null) goto _L15; else goto _L16
_L16:
        typedarray.setEvaluator(argbevaluator);
        return typedarray;
        return null;
    }

    public static Animator loadAnimator(Context context, Resources resources, android.content.res.Resources.Theme theme, int i, float f)
        throws android.content.res.Resources.NotFoundException
    {
        XmlResourceParser xmlresourceparser;
        XmlResourceParser xmlresourceparser1;
        XmlResourceParser xmlresourceparser2;
        xmlresourceparser = null;
        xmlresourceparser2 = null;
        xmlresourceparser1 = null;
        XmlResourceParser xmlresourceparser3 = resources.getAnimation(i);
        xmlresourceparser1 = xmlresourceparser3;
        xmlresourceparser = xmlresourceparser3;
        xmlresourceparser2 = xmlresourceparser3;
        context = createAnimatorFromXml(context, resources, theme, xmlresourceparser3, Xml.asAttributeSet(xmlresourceparser3), null, 0, 1.0F);
        if (xmlresourceparser3 != null)
        {
            xmlresourceparser3.close();
        }
        return context;
        context;
        xmlresourceparser = xmlresourceparser1;
        resources = new android.content.res.Resources.NotFoundException((new StringBuilder("Can't load animation resource ID #0x")).append(Integer.toHexString(i)).toString());
        xmlresourceparser = xmlresourceparser1;
        resources.initCause(context);
        xmlresourceparser = xmlresourceparser1;
        throw resources;
        context;
        if (xmlresourceparser != null)
        {
            xmlresourceparser.close();
        }
        throw context;
        context;
        xmlresourceparser = xmlresourceparser2;
        resources = new android.content.res.Resources.NotFoundException((new StringBuilder("Can't load animation resource ID #0x")).append(Integer.toHexString(i)).toString());
        xmlresourceparser = xmlresourceparser2;
        resources.initCause(context);
        xmlresourceparser = xmlresourceparser2;
        throw resources;
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, ValueAnimator valueanimator, float f, XmlPullParser xmlpullparser)
        throws android.content.res.Resources.NotFoundException
    {
        Object obj = AndroidResources.STYLEABLE_ANIMATOR;
        Object obj1;
        ObjectAnimator objectanimator;
        int i;
        int j;
        int k;
        int l;
        long l1;
        long l2;
        if (theme == null)
        {
            obj = resources.obtainAttributes(attributeset, ((int []) (obj)));
        } else
        {
            obj = theme.obtainStyledAttributes(attributeset, ((int []) (obj)), 0, 0);
        }
        obj1 = AndroidResources.STYLEABLE_PROPERTY_ANIMATOR;
        if (theme == null)
        {
            resources = resources.obtainAttributes(attributeset, ((int []) (obj1)));
        } else
        {
            resources = theme.obtainStyledAttributes(attributeset, ((int []) (obj1)), 0, 0);
        }
        float f1;
        float f2;
        android.graphics.Path path;
        float af[];
        PathMeasure pathmeasure;
        float af1[];
        PathMeasure pathmeasure1;
        float af2[];
        if (valueanimator == null)
        {
            theme = new ValueAnimator();
        } else
        {
            theme = valueanimator;
        }
        j = 300;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "duration") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = ((TypedArray) (obj)).getInt(1, 300);
        }
        l1 = i;
        j = 0;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "startOffset") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = ((TypedArray) (obj)).getInt(2, 0);
        }
        l2 = i;
        j = 4;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueType") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = ((TypedArray) (obj)).getInt(7, 4);
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueFrom") != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L2; else goto _L1
_L1:
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "valueTo") != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L2; else goto _L3
_L3:
        j = i;
        if (i != 4) goto _L5; else goto _L4
_L4:
        attributeset = ((TypedArray) (obj)).peekValue(5);
        if (attributeset != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            k = ((TypedValue) (attributeset)).type;
        } else
        {
            k = 0;
        }
        attributeset = ((TypedArray) (obj)).peekValue(6);
        if (attributeset != null)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j != 0)
        {
            l = ((TypedValue) (attributeset)).type;
        } else
        {
            l = 0;
        }
        if (i == 0) goto _L7; else goto _L6
_L6:
        if (k >= 28 && k <= 31)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L8; else goto _L7
_L7:
        if (j == 0) goto _L10; else goto _L9
_L9:
        if (l >= 28 && l <= 31)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L10; else goto _L8
_L8:
        j = 3;
_L5:
        attributeset = getPVH(((TypedArray) (obj)), j, 5, 6, "");
        if (attributeset != null)
        {
            theme.setValues(new PropertyValuesHolder[] {
                attributeset
            });
        }
_L2:
        theme.setDuration(l1);
        theme.setStartDelay(l2);
        j = 0;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "repeatCount") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = ((TypedArray) (obj)).getInt(3, 0);
        }
        theme.setRepeatCount(i);
        j = 1;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "repeatMode") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = j;
        } else
        {
            i = ((TypedArray) (obj)).getInt(4, 1);
        }
        theme.setRepeatMode(i);
        if (resources == null)
        {
            break MISSING_BLOCK_LABEL_1081;
        }
        objectanimator = (ObjectAnimator)theme;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "pathData") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            obj1 = null;
        } else
        {
            obj1 = resources.getString(1);
        }
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1180;
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "propertyXName") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            valueanimator = null;
        } else
        {
            valueanimator = resources.getString(2);
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "propertyYName") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            attributeset = null;
        } else
        {
            attributeset = resources.getString(3);
        }
        if (valueanimator == null && attributeset == null)
        {
            throw new InflateException((new StringBuilder()).append(resources.getPositionDescription()).append(" propertyXName or propertyYName is needed for PathData").toString());
        }
        break; /* Loop/switch isn't completed */
_L10:
        j = 0;
        if (true) goto _L5; else goto _L11
_L11:
        path = PathParser.createPathFromPathData(((String) (obj1)));
        pathmeasure = new PathMeasure(path, false);
        f1 = 0.0F;
        obj1 = new ArrayList();
        ((ArrayList) (obj1)).add(Float.valueOf(0.0F));
        do
        {
            f2 = f1 + pathmeasure.getLength();
            ((ArrayList) (obj1)).add(Float.valueOf(f2));
            f1 = f2;
        } while (pathmeasure.nextContour());
        pathmeasure1 = new PathMeasure(path, false);
        k = Math.min(100, (int)(f2 / (0.5F * f)) + 1);
        af1 = new float[k];
        af = new float[k];
        af2 = new float[2];
        f1 = f2 / (float)(k - 1);
        f = 0.0F;
        i = 0;
        for (j = 0; j < k; j++)
        {
            pathmeasure1.getPosTan(f - ((Float)((ArrayList) (obj1)).get(i)).floatValue(), af2, null);
            af1[j] = af2[0];
            af[j] = af2[1];
            f += f1;
            if (i + 1 < ((ArrayList) (obj1)).size() && f > ((Float)((ArrayList) (obj1)).get(i + 1)).floatValue())
            {
                i++;
                pathmeasure1.nextContour();
            }
        }

        obj1 = null;
        if (valueanimator != null)
        {
            valueanimator = PropertyValuesHolder.ofFloat(valueanimator, af1);
        } else
        {
            valueanimator = null;
        }
        if (attributeset != null)
        {
            obj1 = PropertyValuesHolder.ofFloat(attributeset, af);
        }
        if (valueanimator == null)
        {
            objectanimator.setValues(new PropertyValuesHolder[] {
                obj1
            });
        } else
        if (obj1 == null)
        {
            objectanimator.setValues(new PropertyValuesHolder[] {
                valueanimator
            });
        } else
        {
            objectanimator.setValues(new PropertyValuesHolder[] {
                valueanimator, obj1
            });
        }
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "interpolator") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 0;
        } else
        {
            i = ((TypedArray) (obj)).getResourceId(0, 0);
        }
        if (i > 0)
        {
            theme.setInterpolator(AnimationUtils.loadInterpolator(context, i));
        }
        ((TypedArray) (obj)).recycle();
        if (resources != null)
        {
            resources.recycle();
        }
        return theme;
        if (xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", "propertyName") != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            attributeset = null;
        } else
        {
            attributeset = resources.getString(0);
        }
        objectanimator.setPropertyName(attributeset);
        break MISSING_BLOCK_LABEL_1081;
    }

    private class PathDataEvaluator
        implements TypeEvaluator
    {

        private android.support.v4.graphics.PathParser.PathDataNode mNodeArray[];

        public final Object evaluate(float f, Object obj, Object obj1)
        {
            obj = (android.support.v4.graphics.PathParser.PathDataNode[])obj;
            obj1 = (android.support.v4.graphics.PathParser.PathDataNode[])obj1;
            if (!PathParser.canMorph(((android.support.v4.graphics.PathParser.PathDataNode []) (obj)), ((android.support.v4.graphics.PathParser.PathDataNode []) (obj1))))
            {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            if (mNodeArray == null || !PathParser.canMorph(mNodeArray, ((android.support.v4.graphics.PathParser.PathDataNode []) (obj))))
            {
                mNodeArray = PathParser.deepCopyNodes(((android.support.v4.graphics.PathParser.PathDataNode []) (obj)));
            }
            for (int i = 0; i < obj.length; i++)
            {
                android.support.v4.graphics.PathParser.PathDataNode pathdatanode = mNodeArray[i];
                Object obj2 = obj[i];
                Object obj3 = obj1[i];
                for (int j = 0; j < ((android.support.v4.graphics.PathParser.PathDataNode) (obj2)).mParams.length; j++)
                {
                    pathdatanode.mParams[j] = ((android.support.v4.graphics.PathParser.PathDataNode) (obj2)).mParams[j] * (1.0F - f) + ((android.support.v4.graphics.PathParser.PathDataNode) (obj3)).mParams[j] * f;
                }

            }

            return mNodeArray;
        }

        PathDataEvaluator()
        {
        }
    }

}
