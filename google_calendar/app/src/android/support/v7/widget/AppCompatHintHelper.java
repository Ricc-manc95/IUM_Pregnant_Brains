// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

// Referenced classes of package android.support.v7.widget:
//            WithHint

final class AppCompatHintHelper
{

    static InputConnection onCreateInputConnection(InputConnection inputconnection, EditorInfo editorinfo, View view)
    {
        if (inputconnection == null || editorinfo.hintText != null) goto _L2; else goto _L1
_L1:
        view = view.getParent();
_L7:
        if (!(view instanceof View)) goto _L2; else goto _L3
_L3:
        if (!(view instanceof WithHint)) goto _L5; else goto _L4
_L4:
        editorinfo.hintText = ((WithHint)view).getHint();
_L2:
        return inputconnection;
_L5:
        view = view.getParent();
        if (true) goto _L7; else goto _L6
_L6:
    }
}
