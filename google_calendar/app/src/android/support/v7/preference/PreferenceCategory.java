// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

// Referenced classes of package android.support.v7.preference:
//            PreferenceGroup, PreferenceViewHolder

public class PreferenceCategory extends PreferenceGroup
{

    public PreferenceCategory(Context context)
    {
        this(context, null);
    }

    public PreferenceCategory(Context context, AttributeSet attributeset)
    {
        int i = 0x7f010227;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f010227, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x101008c;
        }
        this(context, attributeset, i);
    }

    private PreferenceCategory(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private PreferenceCategory(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
    }

    public final boolean isEnabled()
    {
        return false;
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        super.onBindViewHolder(preferenceviewholder);
        if (android.os.Build.VERSION.SDK_INT >= 28)
        {
            preferenceviewholder.itemView.setAccessibilityHeading(true);
        }
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        Object obj;
label0:
        {
            super.onInitializeAccessibilityNodeInfo(accessibilitynodeinfocompat);
            boolean flag;
            if (android.os.Build.VERSION.SDK_INT >= 28)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                obj = accessibilitynodeinfocompat.mInfo.getCollectionItemInfo();
                if (obj != null)
                {
                    obj = new android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat(obj);
                } else
                {
                    obj = null;
                }
                if (obj != null)
                {
                    break label0;
                }
            }
            return;
        }
        android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectioniteminfocompat = new android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat(android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo.obtain(((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat) (obj)).mInfo).getRowIndex(), ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat) (obj)).mInfo).getRowSpan(), ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat) (obj)).mInfo).getColumnIndex(), ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat) (obj)).mInfo).getColumnSpan(), true, ((android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat) (obj)).mInfo).isSelected()));
        obj = accessibilitynodeinfocompat.mInfo;
        if (collectioniteminfocompat == null)
        {
            accessibilitynodeinfocompat = null;
        } else
        {
            accessibilitynodeinfocompat = (android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo)((android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat)collectioniteminfocompat).mInfo;
        }
        ((AccessibilityNodeInfo) (obj)).setCollectionItemInfo(accessibilitynodeinfocompat);
    }

    public final boolean shouldDisableDependents()
    {
        return !super.isEnabled();
    }
}
