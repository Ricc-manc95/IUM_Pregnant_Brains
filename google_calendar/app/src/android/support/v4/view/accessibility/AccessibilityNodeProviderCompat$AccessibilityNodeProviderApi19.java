// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeProviderCompat, AccessibilityNodeInfoCompat

final class <init> extends <init>
{

    public final AccessibilityNodeInfo findFocus(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = mCompat.findFocus(i);
        if (accessibilitynodeinfocompat == null)
        {
            return null;
        } else
        {
            return accessibilitynodeinfocompat.mInfo;
        }
    }

    (AccessibilityNodeProviderCompat accessibilitynodeprovidercompat)
    {
        super(accessibilitynodeprovidercompat);
    }
}
