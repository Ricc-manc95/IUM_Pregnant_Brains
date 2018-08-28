// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            DefaultItemAnimator

final class val.changes
    implements Runnable
{

    private final DefaultItemAnimator this$0;
    private final ArrayList val$changes;

    public final void run()
    {
        ArrayList arraylist = (ArrayList)val$changes;
        int j = arraylist.size();
        int i = 0;
        while (i < j) 
        {
            angeInfo angeinfo = (angeInfo)arraylist.get(i);
            DefaultItemAnimator defaultitemanimator = DefaultItemAnimator.this;
            Object obj = angeinfo.oldHolder;
            Object obj1;
            if (obj == null)
            {
                obj = null;
            } else
            {
                obj = ((r) (obj)).itemView;
            }
            obj1 = angeinfo.newHolder;
            if (obj1 != null)
            {
                obj1 = ((r) (obj1)).itemView;
            } else
            {
                obj1 = null;
            }
            if (obj != null)
            {
                ViewPropertyAnimator viewpropertyanimator = ((View) (obj)).animate().setDuration(((tor) (defaultitemanimator)).mChangeDuration);
                defaultitemanimator.mChangeAnimations.add(angeinfo.oldHolder);
                viewpropertyanimator.translationX(angeinfo.toX - angeinfo.fromX);
                viewpropertyanimator.translationY(angeinfo.toY - angeinfo.fromY);
                viewpropertyanimator.alpha(0.0F).setListener(new <init>(defaultitemanimator, angeinfo, viewpropertyanimator, ((View) (obj)))).start();
            }
            if (obj1 != null)
            {
                obj = ((View) (obj1)).animate();
                defaultitemanimator.mChangeAnimations.add(angeinfo.newHolder);
                ((ViewPropertyAnimator) (obj)).translationX(0.0F).translationY(0.0F).setDuration(((tor) (defaultitemanimator)).mChangeDuration).alpha(1.0F).setListener(new <init>(defaultitemanimator, angeinfo, ((ViewPropertyAnimator) (obj)), ((View) (obj1)))).start();
            }
            i++;
        }
        val$changes.clear();
        mChangesList.remove(val$changes);
    }

    tor()
    {
        this$0 = final_defaultitemanimator;
        val$changes = ArrayList.this;
        super();
    }
}
