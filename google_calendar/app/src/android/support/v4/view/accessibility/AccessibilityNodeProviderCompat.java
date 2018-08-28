// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view.accessibility;

import android.os.Bundle;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat

public class AccessibilityNodeProviderCompat
{

    public final Object mProvider;

    public AccessibilityNodeProviderCompat()
    {
        mProvider = new AccessibilityNodeProviderApi19();
    }

    public AccessibilityNodeProviderCompat(Object obj)
    {
        mProvider = obj;
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
    {
        return null;
    }

    public AccessibilityNodeInfoCompat findFocus(int i)
    {
        return null;
    }

    public boolean performAction(int i, int j, Bundle bundle)
    {
        return false;
    }

    private class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16
    {
        private class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider
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

            AccessibilityNodeProviderApi16()
            {
                mCompat = AccessibilityNodeProviderCompat.this;
            }
        }


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

        AccessibilityNodeProviderApi19()
        {
        }
    }

}
