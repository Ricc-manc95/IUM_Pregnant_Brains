// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

public class EditTextWithImeDone extends EditText
{

    public EditTextWithImeDone(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public InputConnection onCreateInputConnection(EditorInfo editorinfo)
    {
        InputConnection inputconnection = super.onCreateInputConnection(editorinfo);
        editorinfo.imeOptions = editorinfo.imeOptions & 0xffffff00;
        editorinfo.imeOptions = editorinfo.imeOptions | 6;
        editorinfo.imeOptions = editorinfo.imeOptions & 0xbfffffff;
        editorinfo.actionId = 6;
        return inputconnection;
    }
}
