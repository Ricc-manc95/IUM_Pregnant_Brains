// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;


public final class mAction
{

    public static final <init> ACTION_ACCESSIBILITY_FOCUS = new <init>(64, null);
    public static final <init> ACTION_CLEAR_FOCUS = new <init>(2, null);
    public static final <init> ACTION_CLICK = new <init>(16, null);
    public static final <init> ACTION_COLLAPSE = new <init>(0x80000, null);
    public static final <init> ACTION_EXPAND = new <init>(0x40000, null);
    public static final <init> ACTION_FOCUS = new <init>(1, null);
    public static final <init> ACTION_SCROLL_BACKWARD = new <init>(8192, null);
    public static final <init> ACTION_SCROLL_FORWARD = new <init>(4096, null);
    public static final <init> ACTION_SHOW_ON_SCREEN;
    public final Object mAction;

    static 
    {
        Object obj = null;
        new <init>(4, null);
        new <init>(8, null);
        new <init>(32, null);
        new <init>(128, null);
        new <init>(256, null);
        new <init>(512, null);
        new <init>(1024, null);
        new <init>(2048, null);
        new <init>(16384, null);
        new <init>(32768, null);
        new <init>(0x10000, null);
        new <init>(0x20000, null);
        new <init>(0x100000, null);
        new <init>(0x200000, null);
        android.view.accessibility.tionCompat tioncompat;
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.ON_SCREEN;
        } else
        {
            tioncompat = null;
        }
        ACTION_SHOW_ON_SCREEN = new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.L_TO_POSITION;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.L_UP;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.L_LEFT;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.L_DOWN;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.L_RIGHT;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 23)
        {
            tioncompat = android.view.accessibility.XT_CLICK;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 24)
        {
            tioncompat = android.view.accessibility.ROGRESS;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 26)
        {
            tioncompat = android.view.accessibility.WINDOW;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        if (android.os..AccessibilityActionCompat >= 28)
        {
            tioncompat = android.view.accessibility.TOOLTIP;
        } else
        {
            tioncompat = null;
        }
        new <init>(tioncompat);
        tioncompat = obj;
        if (android.os..AccessibilityActionCompat >= 28)
        {
            tioncompat = android.view.accessibility.TOOLTIP;
        }
        new <init>(tioncompat);
    }

    private a(int i, CharSequence charsequence)
    {
        this(new android.view.accessibility.tionCompat(i, null));
    }

    private <init>(Object obj)
    {
        mAction = obj;
    }
}
