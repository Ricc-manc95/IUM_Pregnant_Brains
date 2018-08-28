// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeProviderCompat, AccessibilityNodeInfoCompat

class mCompat extends AccessibilityNodeProvider
{

    public final AccessibilityNodeProviderCompat mCompat;

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = mCompat.createAccessibilityNodeInfo(i);
        if (accessibilitynodeinfocompat == null)
        {
            return null;
        } else
        {
            return accessibilitynodeinfocompat.mInfo;
        }
    }

    public List findAccessibilityNodeInfosByText(String s, int i)
    {
        return null;
    }

    public boolean performAction(int i, int j, Bundle bundle)
    {
        return mCompat.performAction(i, j, bundle);
    }

    (AccessibilityNodeProviderCompat accessibilitynodeprovidercompat)
    {
        mCompat = accessibilitynodeprovidercompat;
    }
}
