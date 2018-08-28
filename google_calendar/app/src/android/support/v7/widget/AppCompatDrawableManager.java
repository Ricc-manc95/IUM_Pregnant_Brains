// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.TintAwareDrawable;
import android.support.v4.graphics.drawable.WrappedDrawableApi21;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ContainerHelpers;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.content.res.AppCompatResources;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v7.widget:
//            ThemeUtils, DrawableUtils, TintInfo

public final class AppCompatDrawableManager
{

    private static final int COLORFILTER_COLOR_BACKGROUND_MULTIPLY[] = {
        0x7f020035, 0x7f02000d, 0x7f020034
    };
    private static final int COLORFILTER_COLOR_CONTROL_ACTIVATED[] = {
        0x7f02004e, 0x7f020050, 0x7f02000f, 0x7f020047, 0x7f020048, 0x7f02004a, 0x7f02004c, 0x7f020049, 0x7f02004b, 0x7f02004d
    };
    private static final int COLORFILTER_TINT_COLOR_CONTROL_NORMAL[] = {
        0x7f020051, 0x7f02004f, 0x7f020000
    };
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final android.graphics.PorterDuff.Mode DEFAULT_MODE;
    public static AppCompatDrawableManager INSTANCE;
    private static final int TINT_CHECKABLE_BUTTON_LIST[] = {
        0x7f020003, 0x7f020008
    };
    private static final int TINT_COLOR_CONTROL_NORMAL[] = {
        0x7f020016, 0x7f02003f, 0x7f02001d, 0x7f020018, 0x7f020019, 0x7f02001c, 0x7f02001b
    };
    private static final int TINT_COLOR_CONTROL_STATE_LIST[] = {
        0x7f020045, 0x7f020052
    };
    private ArrayMap mDelegates;
    public final Object mDrawableCacheLock = new Object();
    public final WeakHashMap mDrawableCaches = new WeakHashMap(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArrayCompat mKnownDrawableIdTags;
    private WeakHashMap mTintLists;
    private TypedValue mTypedValue;

    public AppCompatDrawableManager()
    {
    }

    private final boolean addDrawableToCache(Context context, long l, Drawable drawable)
    {
        android.graphics.drawable.Drawable.ConstantState constantstate;
        constantstate = drawable.getConstantState();
        if (constantstate == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        Object obj = mDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        LongSparseArray longsparsearray = (LongSparseArray)mDrawableCaches.get(context);
        drawable = longsparsearray;
        if (longsparsearray != null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        drawable = new LongSparseArray();
        mDrawableCaches.put(context, drawable);
        drawable.put(l, new WeakReference(constantstate));
        obj;
        JVM INSTR monitorexit ;
        return true;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        return false;
    }

    private static boolean arrayContains(int ai[], int i)
    {
        boolean flag1 = false;
        int k = ai.length;
        int j = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (j < k)
                {
                    if (ai[j] != i)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            j++;
        } while (true);
    }

    private static ColorStateList createButtonColorStateList(Context context, int i)
    {
        int l = ThemeUtils.getThemeAttrColor(context, 0x7f0100c6);
        int j = ThemeUtils.getDisabledThemeAttrColor(context, 0x7f0100c7);
        context = ThemeUtils.DISABLED_STATE_SET;
        int ai[] = ThemeUtils.PRESSED_STATE_SET;
        int k = ColorUtils.compositeColors(l, i);
        int ai1[] = ThemeUtils.FOCUSED_STATE_SET;
        l = ColorUtils.compositeColors(l, i);
        return new ColorStateList(new int[][] {
            context, ai, ai1, ThemeUtils.EMPTY_STATE_SET
        }, new int[] {
            j, k, l, i
        });
    }

    public static AppCompatDrawableManager get()
    {
        if (INSTANCE == null)
        {
            AppCompatDrawableManager appcompatdrawablemanager = new AppCompatDrawableManager();
            INSTANCE = appcompatdrawablemanager;
            if (android.os.Build.VERSION.SDK_INT < 24)
            {
                Object obj = new VdcInflateDelegate();
                if (appcompatdrawablemanager.mDelegates == null)
                {
                    appcompatdrawablemanager.mDelegates = new ArrayMap();
                }
                appcompatdrawablemanager.mDelegates.put("vector", obj);
                obj = new AvdcInflateDelegate();
                if (appcompatdrawablemanager.mDelegates == null)
                {
                    appcompatdrawablemanager.mDelegates = new ArrayMap();
                }
                appcompatdrawablemanager.mDelegates.put("animated-vector", obj);
            }
        }
        return INSTANCE;
    }

    private final Drawable getCachedDrawable(Context context, long l)
    {
        Object obj = mDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        LongSparseArray longsparsearray = (LongSparseArray)mDrawableCaches.get(context);
        if (longsparsearray != null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
        Object obj1 = (WeakReference)longsparsearray.get(l);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        obj1 = (android.graphics.drawable.Drawable.ConstantState)((WeakReference) (obj1)).get();
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        context = ((android.graphics.drawable.Drawable.ConstantState) (obj1)).newDrawable(context.getResources());
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        int i = ContainerHelpers.binarySearch(longsparsearray.mKeys, longsparsearray.mSize, l);
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        if (longsparsearray.mValues[i] != LongSparseArray.DELETED)
        {
            longsparsearray.mValues[i] = LongSparseArray.DELETED;
            longsparsearray.mGarbage = true;
        }
        obj;
        JVM INSTR monitorexit ;
        return null;
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        PorterDuffColorFilter porterduffcolorfilter1 = (PorterDuffColorFilter)COLOR_FILTER_CACHE.get(Integer.valueOf((i + 31) * 31 + mode.hashCode()));
        PorterDuffColorFilter porterduffcolorfilter = porterduffcolorfilter1;
        if (porterduffcolorfilter1 == null)
        {
            porterduffcolorfilter = new PorterDuffColorFilter(i, mode);
            mode = (PorterDuffColorFilter)COLOR_FILTER_CACHE.put(Integer.valueOf((i + 31) * 31 + mode.hashCode()), porterduffcolorfilter);
        }
        return porterduffcolorfilter;
    }

    public static void installDefaultInflateDelegates(AppCompatDrawableManager appcompatdrawablemanager)
    {
        if (android.os.Build.VERSION.SDK_INT < 24)
        {
            Object obj = new VdcInflateDelegate();
            if (appcompatdrawablemanager.mDelegates == null)
            {
                appcompatdrawablemanager.mDelegates = new ArrayMap();
            }
            appcompatdrawablemanager.mDelegates.put("vector", obj);
            obj = new AvdcInflateDelegate();
            if (appcompatdrawablemanager.mDelegates == null)
            {
                appcompatdrawablemanager.mDelegates = new ArrayMap();
            }
            appcompatdrawablemanager.mDelegates.put("animated-vector", obj);
        }
    }

    static void tintDrawable(Drawable drawable, TintInfo tintinfo, int ai[])
    {
        Object obj1 = null;
        if (!DrawableUtils.canSafelyMutateDrawable(drawable) || drawable.mutate() == drawable)
        {
            if (tintinfo.mHasTintList || tintinfo.mHasTintMode)
            {
                ColorStateList colorstatelist;
                Object obj;
                if (tintinfo.mHasTintList)
                {
                    colorstatelist = tintinfo.mTintList;
                } else
                {
                    colorstatelist = null;
                }
                if (tintinfo.mHasTintMode)
                {
                    tintinfo = tintinfo.mTintMode;
                } else
                {
                    tintinfo = DEFAULT_MODE;
                }
                obj = obj1;
                if (colorstatelist != null)
                {
                    if (tintinfo == null)
                    {
                        obj = obj1;
                    } else
                    {
                        obj = getPorterDuffColorFilter(colorstatelist.getColorForState(ai, 0), tintinfo);
                    }
                }
                drawable.setColorFilter(((android.graphics.ColorFilter) (obj)));
            } else
            {
                drawable.clearColorFilter();
            }
            if (android.os.Build.VERSION.SDK_INT <= 23)
            {
                drawable.invalidateSelf();
                return;
            }
        }
    }

    static boolean tintDrawableUsingColorFilter(Context context, int i, Drawable drawable)
    {
        android.graphics.PorterDuff.Mode mode = DEFAULT_MODE;
        int j;
        boolean flag;
        if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, i))
        {
            j = 0x7f0100c4;
            flag = true;
            i = -1;
        } else
        if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, i))
        {
            j = 0x7f0100c5;
            flag = true;
            i = -1;
        } else
        if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, i))
        {
            mode = android.graphics.PorterDuff.Mode.MULTIPLY;
            flag = true;
            j = 0x1010031;
            i = -1;
        } else
        if (i == 0x7f020029)
        {
            j = 0x1010030;
            i = Math.round(40.8F);
            flag = true;
        } else
        if (i == 0x7f020011)
        {
            j = 0x1010031;
            flag = true;
            i = -1;
        } else
        {
            i = -1;
            j = 0;
            flag = false;
        }
        if (flag)
        {
            Drawable drawable1 = drawable;
            if (DrawableUtils.canSafelyMutateDrawable(drawable))
            {
                drawable1 = drawable.mutate();
            }
            drawable1.setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, j), mode));
            if (i != -1)
            {
                drawable1.setAlpha(i);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public final Drawable getDrawable(Context context, int i, boolean flag)
    {
label0:
        {
            if (mHasCheckedVectorDrawableSetup)
            {
                break label0;
            }
            mHasCheckedVectorDrawableSetup = true;
            Drawable drawable = getDrawable(context, 0x7f020053, false);
            if (drawable != null)
            {
                boolean flag1;
                if ((drawable instanceof VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName()))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    break label0;
                }
            }
            mHasCheckedVectorDrawableSetup = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
        Object obj1 = loadDrawableFromDelegates(context, i);
        Object obj = obj1;
        if (obj1 == null)
        {
            if (mTypedValue == null)
            {
                mTypedValue = new TypedValue();
            }
            TypedValue typedvalue = mTypedValue;
            context.getResources().getValue(i, typedvalue, true);
            long l = (long)typedvalue.assetCookie << 32 | (long)typedvalue.data;
            obj1 = getCachedDrawable(context, l);
            obj = obj1;
            if (obj1 == null)
            {
                if (i == 0x7f02000e)
                {
                    obj1 = new LayerDrawable(new Drawable[] {
                        getDrawable(context, 0x7f02000d, false), getDrawable(context, 0x7f02000f, false)
                    });
                }
                obj = obj1;
                if (obj1 != null)
                {
                    ((Drawable) (obj1)).setChangingConfigurations(typedvalue.changingConfigurations);
                    addDrawableToCache(context, l, ((Drawable) (obj1)));
                    obj = obj1;
                }
            }
        }
        obj1 = obj;
        if (obj == null)
        {
            obj1 = ContextCompat.getDrawable(context, i);
        }
        obj = obj1;
        if (obj1 != null)
        {
            obj = tintDrawable(context, i, flag, ((Drawable) (obj1)));
        }
        if (obj != null)
        {
            DrawableUtils.fixDrawable(((Drawable) (obj)));
        }
        return ((Drawable) (obj));
    }

    final ColorStateList getTintList(Context context, int i)
    {
        Object obj;
        if (mTintLists != null)
        {
            obj = (SparseArrayCompat)mTintLists.get(context);
            Object obj1;
            SparseArrayCompat sparsearraycompat;
            if (obj != null)
            {
                obj = (ColorStateList)((SparseArrayCompat) (obj)).get(i);
            } else
            {
                obj = null;
            }
        } else
        {
            obj = null;
        }
        obj1 = obj;
        if (obj != null) goto _L2; else goto _L1
_L1:
        if (i != 0x7f020012) goto _L4; else goto _L3
_L3:
        obj = AppCompatResources.getColorStateList(context, 0x7f0d0356);
_L6:
        if (obj != null)
        {
            if (mTintLists == null)
            {
                mTintLists = new WeakHashMap();
            }
            sparsearraycompat = (SparseArrayCompat)mTintLists.get(context);
            obj1 = sparsearraycompat;
            if (sparsearraycompat == null)
            {
                obj1 = new SparseArrayCompat();
                mTintLists.put(context, obj1);
            }
            ((SparseArrayCompat) (obj1)).append(i, obj);
        }
        obj1 = obj;
_L2:
        return ((ColorStateList) (obj1));
_L4:
        if (i == 0x7f020044)
        {
            obj = AppCompatResources.getColorStateList(context, 0x7f0d0359);
        } else
        if (i == 0x7f020043)
        {
            obj = new int[3][];
            int ai[] = new int[3];
            ColorStateList colorstatelist = ThemeUtils.getThemeAttrColorStateList(context, 0x7f0100c8);
            if (colorstatelist != null && colorstatelist.isStateful())
            {
                obj[0] = ThemeUtils.DISABLED_STATE_SET;
                ai[0] = colorstatelist.getColorForState(obj[0], 0);
                obj[1] = ThemeUtils.CHECKED_STATE_SET;
                ai[1] = ThemeUtils.getThemeAttrColor(context, 0x7f0100c5);
                obj[2] = ThemeUtils.EMPTY_STATE_SET;
                ai[2] = colorstatelist.getDefaultColor();
            } else
            {
                obj[0] = ThemeUtils.DISABLED_STATE_SET;
                ai[0] = ThemeUtils.getDisabledThemeAttrColor(context, 0x7f0100c8);
                obj[1] = ThemeUtils.CHECKED_STATE_SET;
                ai[1] = ThemeUtils.getThemeAttrColor(context, 0x7f0100c5);
                obj[2] = ThemeUtils.EMPTY_STATE_SET;
                ai[2] = ThemeUtils.getThemeAttrColor(context, 0x7f0100c8);
            }
            obj = new ColorStateList(((int [][]) (obj)), ai);
        } else
        if (i == 0x7f020007)
        {
            obj = createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, 0x7f0100c7));
        } else
        if (i == 0x7f020002)
        {
            obj = createButtonColorStateList(context, 0);
        } else
        if (i == 0x7f020006)
        {
            obj = createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, 0x7f0100c3));
        } else
        if (i == 0x7f020041 || i == 0x7f020042)
        {
            obj = AppCompatResources.getColorStateList(context, 0x7f0d0358);
        } else
        if (arrayContains(TINT_COLOR_CONTROL_NORMAL, i))
        {
            obj = ThemeUtils.getThemeAttrColorStateList(context, 0x7f0100c4);
        } else
        if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, i))
        {
            obj = AppCompatResources.getColorStateList(context, 0x7f0d0355);
        } else
        if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, i))
        {
            obj = AppCompatResources.getColorStateList(context, 0x7f0d0354);
        } else
        if (i == 0x7f02003e)
        {
            obj = AppCompatResources.getColorStateList(context, 0x7f0d0357);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    final Drawable loadDrawableFromDelegates(Context context, int i)
    {
        if (mDelegates == null || mDelegates.isEmpty())
        {
            break MISSING_BLOCK_LABEL_389;
        }
        if (mKnownDrawableIdTags == null) goto _L2; else goto _L1
_L1:
        Object obj = (String)mKnownDrawableIdTags.get(i);
        if (!"appcompat_skip_skip".equals(obj) && (obj == null || mDelegates.get(obj) != null)) goto _L4; else goto _L3
_L3:
        obj = null;
_L8:
        return ((Drawable) (obj));
_L2:
        mKnownDrawableIdTags = new SparseArrayCompat();
_L4:
        Drawable drawable;
        Drawable drawable1;
        TypedValue typedvalue;
        long l;
        if (mTypedValue == null)
        {
            mTypedValue = new TypedValue();
        }
        typedvalue = mTypedValue;
        obj = context.getResources();
        ((Resources) (obj)).getValue(i, typedvalue, true);
        l = (long)typedvalue.assetCookie << 32 | (long)typedvalue.data;
        drawable1 = getCachedDrawable(context, l);
        if (drawable1 != null)
        {
            return drawable1;
        }
        drawable = drawable1;
        if (typedvalue.string == null)
        {
            break MISSING_BLOCK_LABEL_263;
        }
        drawable = drawable1;
        if (!typedvalue.string.toString().endsWith(".xml"))
        {
            break MISSING_BLOCK_LABEL_263;
        }
        drawable = drawable1;
        android.content.res.XmlResourceParser xmlresourceparser = ((Resources) (obj)).getXml(i);
        drawable = drawable1;
        AttributeSet attributeset = Xml.asAttributeSet(xmlresourceparser);
_L6:
        drawable = drawable1;
        int j = xmlresourceparser.next();
        if (j != 2 && j != 1) goto _L6; else goto _L5
_L5:
        if (j == 2)
        {
            break; /* Loop/switch isn't completed */
        }
        drawable = drawable1;
        try
        {
            throw new XmlPullParserException("No start tag found");
        }
        // Misplaced declaration of an exception variable
        catch (Context context)
        {
            Log.e("AppCompatDrawableManag", "Exception while inflating drawable", context);
        }
        context = drawable;
_L9:
        obj = context;
        if (context == null)
        {
            mKnownDrawableIdTags.append(i, "appcompat_skip_skip");
            return context;
        }
        if (true) goto _L8; else goto _L7
_L7:
        drawable = drawable1;
        obj = xmlresourceparser.getName();
        drawable = drawable1;
        mKnownDrawableIdTags.append(i, obj);
        drawable = drawable1;
        InflateDelegate inflatedelegate = (InflateDelegate)mDelegates.get(obj);
        obj = drawable1;
        if (inflatedelegate == null)
        {
            break MISSING_BLOCK_LABEL_356;
        }
        drawable = drawable1;
        obj = inflatedelegate.createFromXmlInner(context, xmlresourceparser, attributeset, context.getTheme());
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_384;
        }
        drawable = ((Drawable) (obj));
        ((Drawable) (obj)).setChangingConfigurations(typedvalue.changingConfigurations);
        drawable = ((Drawable) (obj));
        addDrawableToCache(context, l, ((Drawable) (obj)));
        context = ((Context) (obj));
          goto _L9
        return null;
    }

    final Drawable tintDrawable(Context context, int i, boolean flag, Drawable drawable)
    {
        ColorStateList colorstatelist = getTintList(context, i);
        Object obj;
        if (colorstatelist != null)
        {
            obj = drawable;
            if (DrawableUtils.canSafelyMutateDrawable(drawable))
            {
                obj = drawable.mutate();
            }
            context = ((Context) (obj));
            if (android.os.Build.VERSION.SDK_INT < 23)
            {
                context = ((Context) (obj));
                if (!(obj instanceof TintAwareDrawable))
                {
                    context = new WrappedDrawableApi21(((Drawable) (obj)));
                }
            }
            context.setTintList(colorstatelist);
            drawable = null;
            if (i == 0x7f020043)
            {
                drawable = android.graphics.PorterDuff.Mode.MULTIPLY;
            }
            obj = context;
            if (drawable != null)
            {
                context.setTintMode(drawable);
                obj = context;
            }
        } else
        {
            if (i == 0x7f020040)
            {
                LayerDrawable layerdrawable = (LayerDrawable)drawable;
                Object obj1 = layerdrawable.findDrawableByLayerId(0x1020000);
                i = ThemeUtils.getThemeAttrColor(context, 0x7f0100c4);
                android.graphics.PorterDuff.Mode mode = DEFAULT_MODE;
                obj = obj1;
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj1))))
                {
                    obj = ((Drawable) (obj1)).mutate();
                }
                obj1 = mode;
                if (mode == null)
                {
                    obj1 = DEFAULT_MODE;
                }
                ((Drawable) (obj)).setColorFilter(getPorterDuffColorFilter(i, ((android.graphics.PorterDuff.Mode) (obj1))));
                obj1 = layerdrawable.findDrawableByLayerId(0x102000f);
                i = ThemeUtils.getThemeAttrColor(context, 0x7f0100c4);
                mode = DEFAULT_MODE;
                obj = obj1;
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj1))))
                {
                    obj = ((Drawable) (obj1)).mutate();
                }
                obj1 = mode;
                if (mode == null)
                {
                    obj1 = DEFAULT_MODE;
                }
                ((Drawable) (obj)).setColorFilter(getPorterDuffColorFilter(i, ((android.graphics.PorterDuff.Mode) (obj1))));
                obj = layerdrawable.findDrawableByLayerId(0x102000d);
                i = ThemeUtils.getThemeAttrColor(context, 0x7f0100c5);
                obj1 = DEFAULT_MODE;
                context = ((Context) (obj));
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj))))
                {
                    context = ((Drawable) (obj)).mutate();
                }
                obj = obj1;
                if (obj1 == null)
                {
                    obj = DEFAULT_MODE;
                }
                context.setColorFilter(getPorterDuffColorFilter(i, ((android.graphics.PorterDuff.Mode) (obj))));
                return drawable;
            }
            if (i == 0x7f020037 || i == 0x7f020036 || i == 0x7f020038)
            {
                LayerDrawable layerdrawable1 = (LayerDrawable)drawable;
                Object obj2 = layerdrawable1.findDrawableByLayerId(0x1020000);
                i = ThemeUtils.getDisabledThemeAttrColor(context, 0x7f0100c4);
                android.graphics.PorterDuff.Mode mode1 = DEFAULT_MODE;
                obj = obj2;
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj2))))
                {
                    obj = ((Drawable) (obj2)).mutate();
                }
                obj2 = mode1;
                if (mode1 == null)
                {
                    obj2 = DEFAULT_MODE;
                }
                ((Drawable) (obj)).setColorFilter(getPorterDuffColorFilter(i, ((android.graphics.PorterDuff.Mode) (obj2))));
                obj2 = layerdrawable1.findDrawableByLayerId(0x102000f);
                i = ThemeUtils.getThemeAttrColor(context, 0x7f0100c5);
                mode1 = DEFAULT_MODE;
                obj = obj2;
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj2))))
                {
                    obj = ((Drawable) (obj2)).mutate();
                }
                obj2 = mode1;
                if (mode1 == null)
                {
                    obj2 = DEFAULT_MODE;
                }
                ((Drawable) (obj)).setColorFilter(getPorterDuffColorFilter(i, ((android.graphics.PorterDuff.Mode) (obj2))));
                obj = layerdrawable1.findDrawableByLayerId(0x102000d);
                i = ThemeUtils.getThemeAttrColor(context, 0x7f0100c5);
                obj2 = DEFAULT_MODE;
                context = ((Context) (obj));
                if (DrawableUtils.canSafelyMutateDrawable(((Drawable) (obj))))
                {
                    context = ((Drawable) (obj)).mutate();
                }
                obj = obj2;
                if (obj2 == null)
                {
                    obj = DEFAULT_MODE;
                }
                context.setColorFilter(getPorterDuffColorFilter(i, ((android.graphics.PorterDuff.Mode) (obj))));
                return drawable;
            }
            obj = drawable;
            if (!tintDrawableUsingColorFilter(context, i, drawable))
            {
                obj = drawable;
                if (flag)
                {
                    return null;
                }
            }
        }
        return ((Drawable) (obj));
    }

    static 
    {
        DEFAULT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
    }

    private class VdcInflateDelegate
        implements InflateDelegate
    {

        public final Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            VectorDrawableCompat vectordrawablecompat;
            try
            {
                context = context.getResources();
                vectordrawablecompat = new VectorDrawableCompat();
                vectordrawablecompat.inflate(context, xmlpullparser, attributeset, theme);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", context);
                return null;
            }
            return vectordrawablecompat;
        }

        VdcInflateDelegate()
        {
        }
    }


    private class AvdcInflateDelegate
        implements InflateDelegate
    {

        public final Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            try
            {
                Resources resources = context.getResources();
                context = new AnimatedVectorDrawableCompat(context);
                context.inflate(resources, xmlpullparser, attributeset, theme);
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", context);
                return null;
            }
            return context;
        }

        AvdcInflateDelegate()
        {
        }
    }


    private class InflateDelegate
    {

        public abstract Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme);
    }


    private class ColorFilterLruCache extends LruCache
    {

        public ColorFilterLruCache(int i)
        {
            super(6);
        }
    }

}
