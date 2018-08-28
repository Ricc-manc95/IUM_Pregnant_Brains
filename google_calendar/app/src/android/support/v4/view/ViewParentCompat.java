// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

// Referenced classes of package android.support.v4.view:
//            NestedScrollingParent2

public final class ViewParentCompat
{

    public static boolean onNestedFling(ViewParent viewparent, View view, float f, float f1, boolean flag)
    {
        try
        {
            flag = viewparent.onNestedFling(view, f, f1, flag);
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onNestedFling").toString(), view);
            return false;
        }
        return flag;
    }

    public static boolean onNestedPreFling(ViewParent viewparent, View view, float f, float f1)
    {
        boolean flag;
        try
        {
            flag = viewparent.onNestedPreFling(view, f, f1);
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onNestedPreFling").toString(), view);
            return false;
        }
        return flag;
    }

    public static boolean onStartNestedScroll(ViewParent viewparent, View view, View view1, int i, int j)
    {
        if (viewparent instanceof NestedScrollingParent2)
        {
            return ((NestedScrollingParent2)viewparent).onStartNestedScroll(view, view1, i, j);
        }
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_70;
        }
        boolean flag = viewparent.onStartNestedScroll(view, view1, i);
        return flag;
        view;
        Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onStartNestedScroll").toString(), view);
        return false;
    }

    public static void onStopNestedScroll(ViewParent viewparent, View view, int i)
    {
        if (viewparent instanceof NestedScrollingParent2)
        {
            ((NestedScrollingParent2)viewparent).onStopNestedScroll(view, i);
        } else
        if (i == 0)
        {
            try
            {
                viewparent.onStopNestedScroll(view);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (View view)
            {
                Log.e("ViewParentCompat", (new StringBuilder("ViewParent ")).append(viewparent).append(" does not implement interface method onStopNestedScroll").toString(), view);
            }
            return;
        }
    }
}
