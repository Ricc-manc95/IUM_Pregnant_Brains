// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.view.View;
import java.util.Stack;

final class arg._cls1
    implements Runnable
{

    private final ck arg$1;

    public final void run()
    {
        Object obj = arg$1;
        if (((arg._cls1) (obj)).ack.empty())
        {
            obj = ((ack) (obj)).ndCreateFab();
        } else
        {
            obj = ((ope)((ope) (obj)).ack.peek()).createFab;
        }
        if (obj != null)
        {
            ((View) (obj)).setTranslationY(((View)((View) (obj)).getParent()).getHeight() - ((View) (obj)).getTop());
        }
    }

    ck(ck ck)
    {
        arg$1 = ck;
    }
}
