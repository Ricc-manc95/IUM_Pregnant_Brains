// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;


// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout:
//            AutoValue_AccessibilityVirtualView, AccessibilityVirtualView

final class  extends 
{

    private zOrder actionHandler;
    private Integer bottom;
    private Boolean canScrollBackward;
    private Boolean canScrollForward;
    private Runnable clickHandler;
    private CharSequence contentDescription;
    private Integer id;
    private Integer left;
    private Integer parentId;
    private Integer right;
    private Integer top;
    private Integer traversalAfter;
    private Integer traversalBefore;
    private zOrder type;
    private Integer zOrder;

    final  actionHandler( )
    {
        actionHandler = ;
        return this;
    }

    final actionHandler bottom(int i)
    {
        bottom = Integer.valueOf(i);
        return this;
    }

    public final AccessibilityVirtualView build()
    {
        String s2 = "";
        if (id == null)
        {
            s2 = String.valueOf("").concat(" id");
        }
        String s = s2;
        if (left == null)
        {
            s = String.valueOf(s2).concat(" left");
        }
        s2 = s;
        if (top == null)
        {
            s2 = String.valueOf(s).concat(" top");
        }
        s = s2;
        if (right == null)
        {
            s = String.valueOf(s2).concat(" right");
        }
        s2 = s;
        if (bottom == null)
        {
            s2 = String.valueOf(s).concat(" bottom");
        }
        s = s2;
        if (zOrder == null)
        {
            s = String.valueOf(s2).concat(" zOrder");
        }
        s2 = s;
        if (type == null)
        {
            s2 = String.valueOf(s).concat(" type");
        }
        s = s2;
        if (canScrollForward == null)
        {
            s = String.valueOf(s2).concat(" canScrollForward");
        }
        s2 = s;
        if (canScrollBackward == null)
        {
            s2 = String.valueOf(s).concat(" canScrollBackward");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_AccessibilityVirtualView(id.intValue(), parentId, contentDescription, left.intValue(), top.intValue(), right.intValue(), bottom.intValue(), zOrder.intValue(), type, actionHandler, canScrollForward, canScrollBackward, traversalBefore, traversalAfter, clickHandler);
        }
    }

    final clickHandler canScrollBackward(Boolean boolean1)
    {
        if (boolean1 == null)
        {
            throw new NullPointerException("Null canScrollBackward");
        } else
        {
            canScrollBackward = boolean1;
            return this;
        }
    }

    final canScrollBackward canScrollForward(Boolean boolean1)
    {
        if (boolean1 == null)
        {
            throw new NullPointerException("Null canScrollForward");
        } else
        {
            canScrollForward = boolean1;
            return this;
        }
    }

    final canScrollForward clickHandler(Runnable runnable)
    {
        clickHandler = runnable;
        return this;
    }

    final clickHandler contentDescription(CharSequence charsequence)
    {
        contentDescription = charsequence;
        return this;
    }

    final contentDescription id(int i)
    {
        id = Integer.valueOf(i);
        return this;
    }

    final id left(int i)
    {
        left = Integer.valueOf(i);
        return this;
    }

    final left parentId(Integer integer)
    {
        parentId = integer;
        return this;
    }

    final parentId right(int i)
    {
        right = Integer.valueOf(i);
        return this;
    }

    final right top(int i)
    {
        top = Integer.valueOf(i);
        return this;
    }

    final top traversalAfter(Integer integer)
    {
        traversalAfter = integer;
        return this;
    }

    final traversalAfter traversalBefore(Integer integer)
    {
        traversalBefore = integer;
        return this;
    }

    public final traversalBefore type(traversalBefore traversalbefore)
    {
        if (traversalbefore == null)
        {
            throw new NullPointerException("Null type");
        } else
        {
            type = traversalbefore;
            return this;
        }
    }

    final type zOrder(int i)
    {
        zOrder = Integer.valueOf(i);
        return this;
    }

    ()
    {
    }
}
