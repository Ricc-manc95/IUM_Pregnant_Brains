// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.widget;

import android.graphics.Rect;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.accessibility.AccessibilityNodeInfo;

final class tyNodeInfoCompat
    implements pter
{

    public final void obtainBounds(Object obj, Rect rect)
    {
        ((AccessibilityNodeInfoCompat)obj).mInfo.getBoundsInParent(rect);
    }

    tyNodeInfoCompat()
    {
    }
}
