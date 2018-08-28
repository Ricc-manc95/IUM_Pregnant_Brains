// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.text.Editable;
import android.text.TextWatcher;

final class TextWatcherSanitizer
    implements TextWatcher
{

    private String lastText;
    private final TextWatcher textWatcher;

    TextWatcherSanitizer(TextWatcher textwatcher)
    {
        lastText = "";
        textWatcher = textwatcher;
    }

    public final void afterTextChanged(Editable editable)
    {
        textWatcher.afterTextChanged(editable);
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        String s;
        if (charsequence == null)
        {
            s = "";
        } else
        {
            s = charsequence.toString();
        }
        lastText = s;
        textWatcher.beforeTextChanged(charsequence, i, j, k);
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        int l;
        int i1;
        String s1 = lastText.substring(i, i + j);
        String s;
        if (charsequence == null)
        {
            s = "";
        } else
        {
            s = charsequence.toString();
        }
        s = s.substring(i, i + k);
        i1 = Math.min(s1.length(), s.length());
        l = 0;
_L3:
        if (l >= i1)
        {
            break MISSING_BLOCK_LABEL_119;
        }
        if (s1.charAt(l) == s.charAt(l)) goto _L2; else goto _L1
_L1:
        textWatcher.onTextChanged(charsequence, i + l, j - l, k - l);
        return;
_L2:
        l++;
          goto _L3
        l = i1;
          goto _L1
    }
}
