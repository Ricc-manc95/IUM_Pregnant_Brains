// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.StateSet;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.reflect.Array;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class AppCompatResources
{

    private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();
    private static final Object sColorStateCacheLock = new Object();
    private static final WeakHashMap sColorStateCaches = new WeakHashMap(0);

    private static ColorStateList getCachedColorStateList(Context context, int i)
    {
        Object obj = sColorStateCacheLock;
        obj;
        JVM INSTR monitorenter ;
        SparseArray sparsearray = (SparseArray)sColorStateCaches.get(context);
        if (sparsearray == null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        ColorStateListCacheEntry colorstatelistcacheentry;
        if (sparsearray.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        colorstatelistcacheentry = (ColorStateListCacheEntry)sparsearray.get(i);
        if (colorstatelistcacheentry == null)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        if (!colorstatelistcacheentry.configuration.equals(context.getResources().getConfiguration()))
        {
            break MISSING_BLOCK_LABEL_71;
        }
        context = colorstatelistcacheentry.value;
        obj;
        JVM INSTR monitorexit ;
        return context;
        sparsearray.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return null;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static ColorStateList getColorStateList(Context context, int i)
    {
        if (android.os.Build.VERSION.SDK_INT < 23) goto _L2; else goto _L1
_L1:
        Object obj = context.getColorStateList(i);
_L4:
        return ((ColorStateList) (obj));
_L2:
        Object obj1;
        obj1 = getCachedColorStateList(context, i);
        obj = obj1;
        if (obj1 != null) goto _L4; else goto _L3
_L3:
        ColorStateList colorstatelist;
        colorstatelist = inflateColorStateList(context, i);
        if (colorstatelist == null)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        Object obj2 = sColorStateCacheLock;
        obj2;
        JVM INSTR monitorenter ;
        obj1 = (SparseArray)sColorStateCaches.get(context);
        obj = obj1;
        if (obj1 != null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        obj = new SparseArray();
        sColorStateCaches.put(context, obj);
        ((SparseArray) (obj)).append(i, new ColorStateListCacheEntry(colorstatelist, context.getResources().getConfiguration()));
        obj2;
        JVM INSTR monitorexit ;
        return colorstatelist;
        context;
        obj2;
        JVM INSTR monitorexit ;
        throw context;
        return ContextCompat.getColorStateList(context, i);
    }

    public static Drawable getDrawable(Context context, int i)
    {
        if (AppCompatDrawableManager.INSTANCE == null)
        {
            AppCompatDrawableManager appcompatdrawablemanager = new AppCompatDrawableManager();
            AppCompatDrawableManager.INSTANCE = appcompatdrawablemanager;
            AppCompatDrawableManager.installDefaultInflateDelegates(appcompatdrawablemanager);
        }
        return AppCompatDrawableManager.INSTANCE.getDrawable(context, i, false);
    }

    private static ColorStateList inflateColorStateList(Context context, int i)
    {
        Resources resources;
        android.content.res.XmlResourceParser xmlresourceparser;
        resources = context.getResources();
        TypedValue typedvalue1 = (TypedValue)TL_TYPED_VALUE.get();
        TypedValue typedvalue = typedvalue1;
        if (typedvalue1 == null)
        {
            typedvalue = new TypedValue();
            TL_TYPED_VALUE.set(typedvalue);
        }
        resources.getValue(i, typedvalue, true);
        boolean flag;
        if (typedvalue.type >= 28 && typedvalue.type <= 31)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return null;
        }
        resources = context.getResources();
        xmlresourceparser = resources.getXml(i);
        android.content.res.Resources.Theme theme;
        AttributeSet attributeset;
        theme = context.getTheme();
        attributeset = Xml.asAttributeSet(xmlresourceparser);
        do
        {
            i = xmlresourceparser.next();
        } while (i != 2 && i != 1);
        if (i != 2)
        {
            try
            {
                throw new XmlPullParserException("No start tag found");
            }
            // Misplaced declaration of an exception variable
            catch (Context context)
            {
                Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", context);
            }
            return null;
        }
        int ai[];
        int j1;
        context = xmlresourceparser.getName();
        if (!context.equals("selector"))
        {
            throw new XmlPullParserException((new StringBuilder()).append(xmlresourceparser.getPositionDescription()).append(": invalid color state list tag ").append(context).toString());
        }
        j1 = xmlresourceparser.getDepth() + 1;
        context = new int[20][];
        ai = new int[20];
        int j = 0;
_L5:
        i = xmlresourceparser.next();
        if (i == 1) goto _L2; else goto _L1
_L1:
        int k = xmlresourceparser.getDepth();
        if (k < j1 && i == 3) goto _L2; else goto _L3
_L3:
        if (i != 2 || k > j1) goto _L5; else goto _L4
_L4:
        if (!xmlresourceparser.getName().equals("item")) goto _L5; else goto _L6
_L6:
        int ai1[] = android.support.v7.appcompat.R.styleable.ColorStateListItem;
        if (theme != null) goto _L8; else goto _L7
_L7:
        ai1 = resources.obtainAttributes(attributeset, ai1);
_L13:
        int k1 = ai1.getColor(android.support.v7.appcompat.R.styleable.ColorStateListItem_android_color, -65281);
        if (!ai1.hasValue(android.support.v7.appcompat.R.styleable.ColorStateListItem_android_alpha)) goto _L10; else goto _L9
_L9:
        float f = ai1.getFloat(android.support.v7.appcompat.R.styleable.ColorStateListItem_android_alpha, 1.0F);
_L16:
        ai1.recycle();
        i = 0;
        int l1;
        l1 = attributeset.getAttributeCount();
        ai1 = new int[l1];
        k = 0;
_L17:
        if (k >= l1) goto _L12; else goto _L11
_L11:
        int l = attributeset.getAttributeNameResource(k);
        int i1;
        if (l == 0x10101a5 || l == 0x101031f || l == 0x7f010147)
        {
            break MISSING_BLOCK_LABEL_684;
        }
        i1 = i + 1;
        break MISSING_BLOCK_LABEL_419;
_L8:
        ai1 = theme.obtainStyledAttributes(attributeset, ai1, 0, 0);
          goto _L13
_L10:
        if (!ai1.hasValue(android.support.v7.appcompat.R.styleable.ColorStateListItem_alpha)) goto _L15; else goto _L14
_L14:
        f = ai1.getFloat(android.support.v7.appcompat.R.styleable.ColorStateListItem_alpha, 1.0F);
          goto _L16
_L12:
        ai3 = StateSet.trimStateSet(ai1, i);
        k = ColorUtils.setAlphaComponent(k1, Math.round((float)Color.alpha(k1) * f));
        if (j + 1 <= ai.length)
        {
            break MISSING_BLOCK_LABEL_537;
        }
        if (j <= 4)
        {
            i = 8;
        } else
        {
            i = j << 1;
        }
        ai1 = new int[i];
        System.arraycopy(ai, 0, ai1, 0, j);
        ai = ai1;
        ai[j] = k;
        if (j + 1 <= context.length)
        {
            break MISSING_BLOCK_LABEL_594;
        }
        ai1 = context.getClass().getComponentType();
        if (j <= 4)
        {
            i = 8;
        } else
        {
            i = j << 1;
        }
        ai1 = ((int []) ((Object[])Array.newInstance(ai1, i)));
        System.arraycopy(context, 0, ai1, 0, j);
        context = ai1;
        context[j] = ai3;
        context = (int[][])context;
        j++;
          goto _L5
_L2:
        ai1 = new int[j];
        int ai2[][] = new int[j][];
        System.arraycopy(ai, 0, ai1, 0, j);
        System.arraycopy(context, 0, ai2, 0, j);
        context = new ColorStateList(ai2, ai1);
        return context;
_L15:
        f = 1.0F;
          goto _L16
        int ai3[];
        if (!attributeset.getAttributeBooleanValue(k, false))
        {
            l = -l;
        }
        ai1[i] = l;
        i = i1;
        k++;
          goto _L17
    }


    private class ColorStateListCacheEntry
    {

        public final Configuration configuration;
        public final ColorStateList value;

        ColorStateListCacheEntry(ColorStateList colorstatelist, Configuration configuration1)
        {
            value = colorstatelist;
            configuration = configuration1;
        }
    }

}
