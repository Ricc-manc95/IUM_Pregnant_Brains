// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            DefaultItemAnimator

final class val.additions
    implements Runnable
{

    private final DefaultItemAnimator this$0;
    private final ArrayList val$additions;

    public final void run()
    {
        ArrayList arraylist = (ArrayList)val$additions;
        int j = arraylist.size();
        for (int i = 0; i < j;)
        {
            Object obj = arraylist.get(i);
            i++;
            obj = (r)obj;
            DefaultItemAnimator defaultitemanimator = DefaultItemAnimator.this;
            View view = ((r) (obj)).itemView;
            ViewPropertyAnimator viewpropertyanimator = view.animate();
            defaultitemanimator.mAddAnimations.add(obj);
            viewpropertyanimator.alpha(1.0F).setDuration(((tor) (defaultitemanimator)).mAddDuration).setListener(new <init>(defaultitemanimator, ((r) (obj)), view, viewpropertyanimator)).start();
        }

        val$additions.clear();
        mAdditionsList.remove(val$additions);
    }

    tor()
    {
        this$0 = final_defaultitemanimator;
        val$additions = ArrayList.this;
        super();
    }
}
