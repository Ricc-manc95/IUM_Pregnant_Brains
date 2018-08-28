// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            DefaultItemAnimator

final class val.moves
    implements Runnable
{

    private final DefaultItemAnimator this$0;
    private final ArrayList val$moves;

    public final void run()
    {
        ArrayList arraylist = (ArrayList)val$moves;
        int j = arraylist.size();
        for (int i = 0; i < j; i++)
        {
            Object obj = (veInfo)arraylist.get(i);
            DefaultItemAnimator defaultitemanimator = DefaultItemAnimator.this;
            r r = ((veInfo) (obj)).holder;
            int i1 = ((veInfo) (obj)).fromX;
            int k = ((veInfo) (obj)).fromY;
            int j1 = ((veInfo) (obj)).toX;
            int l = ((veInfo) (obj)).toY;
            obj = r.itemView;
            i1 = j1 - i1;
            k = l - k;
            if (i1 != 0)
            {
                ((View) (obj)).animate().translationX(0.0F);
            }
            if (k != 0)
            {
                ((View) (obj)).animate().translationY(0.0F);
            }
            ViewPropertyAnimator viewpropertyanimator = ((View) (obj)).animate();
            defaultitemanimator.mMoveAnimations.add(r);
            viewpropertyanimator.setDuration(((tor) (defaultitemanimator)).mMoveDuration).setListener(new <init>(defaultitemanimator, r, i1, ((View) (obj)), k, viewpropertyanimator)).start();
        }

        val$moves.clear();
        mMovesList.remove(val$moves);
    }

    tor()
    {
        this$0 = final_defaultitemanimator;
        val$moves = ArrayList.this;
        super();
    }
}
