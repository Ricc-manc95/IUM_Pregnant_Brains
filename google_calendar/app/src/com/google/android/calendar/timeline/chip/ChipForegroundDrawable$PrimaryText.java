// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import com.google.android.calendar.utils.SpanUtils;
import com.google.android.calendar.utils.datatypes.StringBuilderList;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipForegroundDrawable, ChipViewModel, ChipConfig

static final class text
{

    public final CharSequence text;
    public final ChipViewModel viewModel;
    public final int width;

    static CharSequence formatText(ChipViewModel chipviewmodel, ChipConfig chipconfig, TextPaint textpaint, int i)
    {
        StringBuilderList stringbuilderlist;
        int k;
        k = 1;
        stringbuilderlist = new StringBuilderList(3);
        List list = chipviewmodel.getPrimaryText();
        if (!list.isEmpty()) goto _L2; else goto _L1
_L1:
        Object obj = "";
_L6:
        stringbuilderlist.add(((CharSequence) (obj)));
        if (chipviewmodel.getStrikeThroughText() != 0) goto _L4; else goto _L3
_L3:
        if (stringbuilderlist.size == 0)
        {
            throw new IndexOutOfBoundsException();
        }
          goto _L5
        chipconfig;
        throw chipconfig;
        chipviewmodel;
_L26:
        int j;
        if (chipconfig != null)
        {
            try
            {
                stringbuilderlist.close();
            }
            // Misplaced declaration of an exception variable
            catch (TextPaint textpaint)
            {
                ThrowableExtension.STRATEGY.addSuppressed(chipconfig, textpaint);
            }
        } else
        {
            stringbuilderlist.close();
        }
        throw chipviewmodel;
_L2:
        obj = ChipForegroundDrawable.prepInputString((String)list.get(0), chipviewmodel);
          goto _L6
_L5:
        obj = stringbuilderlist.list[stringbuilderlist.size - 1].ring;
        chipconfig = chipconfig.boldSpan;
        if (((Spannable) (obj)).length() != 0)
        {
            ((Spannable) (obj)).setSpan(chipconfig, 0, ((Spannable) (obj)).length(), 33);
        }
_L10:
        if (chipviewmodel.getIcon() != 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (j == 0) goto _L8; else goto _L7
_L7:
        chipconfig = new android.text.style.l.getIcon(chipviewmodel.getPrimaryTextInsetForIcon(), 0);
        if (stringbuilderlist.size == 0)
        {
            throw new IndexOutOfBoundsException();
        }
          goto _L9
_L4:
        if (chipviewmodel.getStrikeThroughText() == 2)
        {
            j = 34;
        } else
        {
            j = 33;
        }
        if (stringbuilderlist.size == 0)
        {
            throw new IndexOutOfBoundsException();
        }
        obj = stringbuilderlist.list[stringbuilderlist.size - 1].ring;
        ((Spannable) (obj)).setSpan(chipconfig.strikeThroughSpan, 0, ((Spannable) (obj)).length(), j);
          goto _L10
_L9:
        obj = stringbuilderlist.list[stringbuilderlist.size - 1].ring;
        if (((Spannable) (obj)).length() != 0)
        {
            ((Spannable) (obj)).setSpan(chipconfig, 0, ((Spannable) (obj)).length(), 33);
        }
          goto _L8
_L11:
        if (j >= list.size())
        {
            break MISSING_BLOCK_LABEL_356;
        }
        stringbuilderlist.add(ChipForegroundDrawable.prepInputString((String)list.get(j), chipviewmodel));
        j++;
          goto _L11
        chipviewmodel.getEllipsizeType();
        JVM INSTR tableswitch 1 2: default 597
    //                   1 481
    //                   2 496;
           goto _L12 _L13 _L14
_L12:
        chipconfig = new SpannableStringBuilder();
        j = 0;
_L22:
        if (j >= stringbuilderlist.size()) goto _L16; else goto _L15
_L15:
        if (j <= 0) goto _L18; else goto _L17
_L17:
        chipconfig.append('\n');
_L18:
        if (j != 0) goto _L20; else goto _L19
_L19:
        if (chipviewmodel.getIcon() != 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k == 0) goto _L20; else goto _L21
_L21:
        k = chipviewmodel.getPrimaryTextInsetForIcon();
_L25:
        chipconfig.append(TextUtils.ellipsize((SpannableStringBuilder)stringbuilderlist.get(j), textpaint, i - (k + 2), android.text.ngBuilderList.get));
        j++;
          goto _L22
_L13:
        chipviewmodel = SpanUtils.join("\n", stringbuilderlist);
        stringbuilderlist.close();
        return chipviewmodel;
_L14:
        if (!chipviewmodel.getIsRtl())
        {
            i = k;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L24; else goto _L23
_L23:
        throw new IllegalStateException();
_L24:
        chipviewmodel = SpanUtils.join("\n", stringbuilderlist);
        stringbuilderlist.close();
        return chipviewmodel;
_L20:
        k = 0;
          goto _L25
_L16:
        stringbuilderlist.close();
        return chipconfig;
        chipviewmodel;
        chipconfig = null;
          goto _L26
_L8:
        j = 1;
          goto _L11
    }

    DesugaringStrategy(ChipViewModel chipviewmodel, int i, CharSequence charsequence)
    {
        viewModel = chipviewmodel;
        width = i;
        text = charsequence;
    }
}
