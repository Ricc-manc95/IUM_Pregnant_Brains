// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            AccessibilityVirtualView

final class AutoValue_AccessibilityVirtualView extends AccessibilityVirtualView
{

    private final AccessibilityVirtualView.ActionHandler actionHandler;
    private final int bottom;
    private final Boolean canScrollBackward;
    private final Boolean canScrollForward;
    private final Runnable clickHandler;
    private final CharSequence contentDescription;
    private final int id;
    private final int left;
    private final Integer parentId;
    private final int right;
    private final int top;
    private final Integer traversalAfter;
    private final Integer traversalBefore;
    private final LayoutItemParams.Type type;
    private final int zOrder;

    AutoValue_AccessibilityVirtualView(int i, Integer integer, CharSequence charsequence, int j, int k, int l, int i1, 
            int j1, LayoutItemParams.Type type1, AccessibilityVirtualView.ActionHandler actionhandler, Boolean boolean1, Boolean boolean2, Integer integer1, Integer integer2, 
            Runnable runnable)
    {
        id = i;
        parentId = integer;
        contentDescription = charsequence;
        left = j;
        top = k;
        right = l;
        bottom = i1;
        zOrder = j1;
        type = type1;
        actionHandler = actionhandler;
        canScrollForward = boolean1;
        canScrollBackward = boolean2;
        traversalBefore = integer1;
        traversalAfter = integer2;
        clickHandler = runnable;
    }

    public final AccessibilityVirtualView.ActionHandler actionHandler()
    {
        return actionHandler;
    }

    public final int bottom()
    {
        return bottom;
    }

    public final Boolean canScrollBackward()
    {
        return canScrollBackward;
    }

    public final Boolean canScrollForward()
    {
        return canScrollForward;
    }

    public final Runnable clickHandler()
    {
        return clickHandler;
    }

    public final CharSequence contentDescription()
    {
        return contentDescription;
    }

    public final boolean equals(Object obj)
    {
        if (obj != this) goto _L2; else goto _L1
_L1:
        return true;
_L2:
label0:
        {
            if (!(obj instanceof AccessibilityVirtualView))
            {
                break MISSING_BLOCK_LABEL_314;
            }
            obj = (AccessibilityVirtualView)obj;
            if (id == ((AccessibilityVirtualView) (obj)).id() && (parentId != null ? parentId.equals(((AccessibilityVirtualView) (obj)).parentId()) : ((AccessibilityVirtualView) (obj)).parentId() == null) && (contentDescription != null ? contentDescription.equals(((AccessibilityVirtualView) (obj)).contentDescription()) : ((AccessibilityVirtualView) (obj)).contentDescription() == null) && (left == ((AccessibilityVirtualView) (obj)).left() && top == ((AccessibilityVirtualView) (obj)).top() && right == ((AccessibilityVirtualView) (obj)).right() && bottom == ((AccessibilityVirtualView) (obj)).bottom() && zOrder == ((AccessibilityVirtualView) (obj)).zOrder() && type.equals(((AccessibilityVirtualView) (obj)).type())) && (actionHandler != null ? actionHandler.equals(((AccessibilityVirtualView) (obj)).actionHandler()) : ((AccessibilityVirtualView) (obj)).actionHandler() == null) && (canScrollForward.equals(((AccessibilityVirtualView) (obj)).canScrollForward()) && canScrollBackward.equals(((AccessibilityVirtualView) (obj)).canScrollBackward())) && (traversalBefore != null ? traversalBefore.equals(((AccessibilityVirtualView) (obj)).traversalBefore()) : ((AccessibilityVirtualView) (obj)).traversalBefore() == null) && (traversalAfter != null ? traversalAfter.equals(((AccessibilityVirtualView) (obj)).traversalAfter()) : ((AccessibilityVirtualView) (obj)).traversalAfter() == null))
            {
                break label0;
            } else
            {
                break; /* Loop/switch isn't completed */
            }
        }
        if (clickHandler != null) goto _L4; else goto _L3
_L3:
        if (((AccessibilityVirtualView) (obj)).clickHandler() == null) goto _L1; else goto _L5
_L5:
        return false;
_L4:
        if (!clickHandler.equals(((AccessibilityVirtualView) (obj)).clickHandler())) goto _L5; else goto _L6
_L6:
        return true;
        return false;
    }

    public final int hashCode()
    {
        int j1 = 0;
        int k1 = id;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        if (parentId == null)
        {
            i = 0;
        } else
        {
            i = parentId.hashCode();
        }
        if (contentDescription == null)
        {
            j = 0;
        } else
        {
            j = contentDescription.hashCode();
        }
        l1 = left;
        i2 = top;
        j2 = right;
        k2 = bottom;
        l2 = zOrder;
        i3 = type.hashCode();
        if (actionHandler == null)
        {
            k = 0;
        } else
        {
            k = actionHandler.hashCode();
        }
        j3 = canScrollForward.hashCode();
        k3 = canScrollBackward.hashCode();
        if (traversalBefore == null)
        {
            l = 0;
        } else
        {
            l = traversalBefore.hashCode();
        }
        if (traversalAfter == null)
        {
            i1 = 0;
        } else
        {
            i1 = traversalAfter.hashCode();
        }
        if (clickHandler != null)
        {
            j1 = clickHandler.hashCode();
        }
        return (i1 ^ (l ^ (((k ^ (((((((j ^ (i ^ (k1 ^ 0xf4243) * 0xf4243) * 0xf4243) * 0xf4243 ^ l1) * 0xf4243 ^ i2) * 0xf4243 ^ j2) * 0xf4243 ^ k2) * 0xf4243 ^ l2) * 0xf4243 ^ i3) * 0xf4243) * 0xf4243 ^ j3) * 0xf4243 ^ k3) * 0xf4243) * 0xf4243) * 0xf4243 ^ j1;
    }

    public final int id()
    {
        return id;
    }

    public final int left()
    {
        return left;
    }

    public final Integer parentId()
    {
        return parentId;
    }

    public final int right()
    {
        return right;
    }

    public final String toString()
    {
        int i = id;
        String s = String.valueOf(parentId);
        String s1 = String.valueOf(contentDescription);
        int j = left;
        int k = top;
        int l = right;
        int i1 = bottom;
        int j1 = zOrder;
        String s2 = String.valueOf(type);
        String s3 = String.valueOf(actionHandler);
        String s4 = String.valueOf(canScrollForward);
        String s5 = String.valueOf(canScrollBackward);
        String s6 = String.valueOf(traversalBefore);
        String s7 = String.valueOf(traversalAfter);
        String s8 = String.valueOf(clickHandler);
        return (new StringBuilder(String.valueOf(s).length() + 278 + String.valueOf(s1).length() + String.valueOf(s2).length() + String.valueOf(s3).length() + String.valueOf(s4).length() + String.valueOf(s5).length() + String.valueOf(s6).length() + String.valueOf(s7).length() + String.valueOf(s8).length())).append("AccessibilityVirtualView{id=").append(i).append(", parentId=").append(s).append(", contentDescription=").append(s1).append(", left=").append(j).append(", top=").append(k).append(", right=").append(l).append(", bottom=").append(i1).append(", zOrder=").append(j1).append(", type=").append(s2).append(", actionHandler=").append(s3).append(", canScrollForward=").append(s4).append(", canScrollBackward=").append(s5).append(", traversalBefore=").append(s6).append(", traversalAfter=").append(s7).append(", clickHandler=").append(s8).append("}").toString();
    }

    public final int top()
    {
        return top;
    }

    public final Integer traversalAfter()
    {
        return traversalAfter;
    }

    public final Integer traversalBefore()
    {
        return traversalBefore;
    }

    public final LayoutItemParams.Type type()
    {
        return type;
    }

    public final int zOrder()
    {
        return zOrder;
    }
}
