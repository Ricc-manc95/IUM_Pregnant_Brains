// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class FragmentTransitionImpl
{

    public FragmentTransitionImpl()
    {
    }

    public static void bfsAddViewChildren(List list, View view)
    {
        int i;
        int l;
        l = list.size();
        i = 0;
_L7:
        if (i >= l) goto _L2; else goto _L1
_L1:
        if (list.get(i) != view) goto _L4; else goto _L3
_L3:
        i = 1;
_L8:
        if (i == 0) goto _L6; else goto _L5
_L5:
        return;
_L4:
        i++;
          goto _L7
_L2:
        i = 0;
          goto _L8
_L6:
        list.add(view);
        i = l;
_L15:
        if (i >= list.size()) goto _L5; else goto _L9
_L9:
        int j;
        int i1;
        view = (View)list.get(i);
        if (!(view instanceof ViewGroup))
        {
            break MISSING_BLOCK_LABEL_175;
        }
        view = (ViewGroup)view;
        i1 = view.getChildCount();
        j = 0;
_L12:
        View view1;
        int k;
        if (j >= i1)
        {
            break MISSING_BLOCK_LABEL_175;
        }
        view1 = view.getChildAt(j);
        k = 0;
_L13:
        if (k >= l)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        if (list.get(k) != view1) goto _L11; else goto _L10
_L10:
        k = 1;
_L14:
        if (k == 0)
        {
            list.add(view1);
        }
        j++;
          goto _L12
_L11:
        k++;
          goto _L13
        k = 0;
          goto _L14
        i++;
          goto _L15
    }

    public static void getBoundsOnScreen(View view, Rect rect)
    {
        int ai[] = new int[2];
        view.getLocationOnScreen(ai);
        rect.set(ai[0], ai[1], ai[0] + view.getWidth(), ai[1] + view.getHeight());
    }

    public abstract void addTarget(Object obj, View view);

    public abstract void addTargets(Object obj, ArrayList arraylist);

    public abstract void beginDelayedTransition(ViewGroup viewgroup, Object obj);

    public abstract boolean canHandle(Object obj);

    final void captureTransitioningViews(ArrayList arraylist, View view)
    {
label0:
        {
            if (view.getVisibility() == 0)
            {
                if (!(view instanceof ViewGroup))
                {
                    break label0;
                }
                view = (ViewGroup)view;
                if (view.isTransitionGroup())
                {
                    arraylist.add(view);
                } else
                {
                    int j = view.getChildCount();
                    int i = 0;
                    while (i < j) 
                    {
                        captureTransitioningViews(arraylist, view.getChildAt(i));
                        i++;
                    }
                }
            }
            return;
        }
        arraylist.add(view);
    }

    public abstract Object cloneTransition(Object obj);

    final void findNamedViews(Map map, View view)
    {
        if (view.getVisibility() == 0)
        {
            String s = ViewCompat.getTransitionName(view);
            if (s != null)
            {
                map.put(s, view);
            }
            if (view instanceof ViewGroup)
            {
                view = (ViewGroup)view;
                int j = view.getChildCount();
                for (int i = 0; i < j; i++)
                {
                    findNamedViews(map, view.getChildAt(i));
                }

            }
        }
    }

    public abstract Object mergeTransitionsInSequence(Object obj, Object obj1, Object obj2);

    public abstract Object mergeTransitionsTogether(Object obj, Object obj1, Object obj2);

    public abstract void removeTarget(Object obj, View view);

    public abstract void replaceTargets(Object obj, ArrayList arraylist, ArrayList arraylist1);

    public abstract void scheduleHideFragmentView(Object obj, View view, ArrayList arraylist);

    public abstract void scheduleRemoveTargets(Object obj, Object obj1, ArrayList arraylist, Object obj2, ArrayList arraylist1, Object obj3, ArrayList arraylist2);

    public abstract void setEpicenter(Object obj, Rect rect);

    public abstract void setEpicenter(Object obj, View view);

    public abstract void setSharedElementTargets(Object obj, View view, ArrayList arraylist);

    public abstract void swapSharedElementTargets(Object obj, ArrayList arraylist, ArrayList arraylist1);

    public abstract Object wrapTransitionInSet(Object obj);
}
