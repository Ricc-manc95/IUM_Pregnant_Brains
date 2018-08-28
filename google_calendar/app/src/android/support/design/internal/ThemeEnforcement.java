// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;

public final class ThemeEnforcement
{

    private static final int APPCOMPAT_CHECK_ATTRS[] = {
        0x7f0100c1
    };
    private static final int MATERIAL_CHECK_ATTRS[] = {
        0x7f010008
    };

    public static void checkAppCompatTheme(Context context)
    {
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
    }

    private static void checkCompatibleTheme(Context context, AttributeSet attributeset, int i, int j)
    {
        attributeset = context.obtainStyledAttributes(attributeset, R.styleable.ThemeEnforcement, i, j);
        boolean flag = attributeset.getBoolean(R.styleable.ThemeEnforcement_enforceMaterialTheme, false);
        attributeset.recycle();
        if (flag)
        {
            checkTheme(context, MATERIAL_CHECK_ATTRS, "Theme.MaterialComponents");
        }
        checkTheme(context, APPCOMPAT_CHECK_ATTRS, "Theme.AppCompat");
    }

    private static transient void checkTextAppearance(Context context, AttributeSet attributeset, int ai[], int i, int j, int ai1[])
    {
        TypedArray typedarray;
        boolean flag;
        boolean flag1;
        flag1 = true;
        flag = false;
        typedarray = context.obtainStyledAttributes(attributeset, R.styleable.ThemeEnforcement, i, j);
        if (typedarray.getBoolean(R.styleable.ThemeEnforcement_enforceTextAppearance, false)) goto _L2; else goto _L1
_L1:
        typedarray.recycle();
_L4:
        return;
_L2:
        if (ai1 != null && ai1.length != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (typedarray.getResourceId(R.styleable.ThemeEnforcement_android_textAppearance, -1) != -1)
        {
            i = ((flag1) ? 1 : 0);
        } else
        {
            i = 0;
        }
_L5:
        typedarray.recycle();
        if (i == 0)
        {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
        if (true) goto _L4; else goto _L3
_L3:
        context = context.obtainStyledAttributes(attributeset, ai, i, j);
        j = ai1.length;
        i = 0;
_L6:
label0:
        {
            if (i >= j)
            {
                break MISSING_BLOCK_LABEL_141;
            }
            if (context.getResourceId(ai1[i], -1) != -1)
            {
                break label0;
            }
            context.recycle();
            i = ((flag) ? 1 : 0);
        }
          goto _L5
        i++;
          goto _L6
        context.recycle();
        i = 1;
          goto _L5
    }

    private static void checkTheme(Context context, int ai[], String s)
    {
        context = context.obtainStyledAttributes(ai);
        boolean flag = context.hasValue(0);
        context.recycle();
        if (!flag)
        {
            throw new IllegalArgumentException((new StringBuilder(String.valueOf(s).length() + 77)).append("The style on this component requires your app theme to be ").append(s).append(" (or a descendant).").toString());
        } else
        {
            return;
        }
    }

    public static boolean isMaterialTheme(Context context)
    {
        context = context.obtainStyledAttributes(MATERIAL_CHECK_ATTRS);
        boolean flag = context.hasValue(0);
        context.recycle();
        return flag;
    }

    public static transient TypedArray obtainStyledAttributes(Context context, AttributeSet attributeset, int ai[], int i, int j, int ai1[])
    {
        checkCompatibleTheme(context, attributeset, i, j);
        checkTextAppearance(context, attributeset, ai, i, j, ai1);
        return context.obtainStyledAttributes(attributeset, ai, i, j);
    }

    public static transient TintTypedArray obtainTintedStyledAttributes(Context context, AttributeSet attributeset, int ai[], int i, int j, int ai1[])
    {
        checkCompatibleTheme(context, attributeset, i, j);
        checkTextAppearance(context, attributeset, ai, i, j, ai1);
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeset, ai, i, j));
    }

}
