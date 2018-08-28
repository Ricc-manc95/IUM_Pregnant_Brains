// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.quickcreate.text;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextWatcher;
import com.google.android.calendar.common.view.NinjaEditText;
import com.google.android.calendar.material.Material;
import com.google.android.calendar.newapi.common.ShinobiEditText;
import com.google.android.calendar.utils.rtl.RtlUtils;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.personalization.assist.annotate.AnnotationType;
import com.google.personalization.assist.annotate.api.AnnotationFragment;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

// Referenced classes of package com.google.android.calendar.newapi.quickcreate.text:
//            FragmentList, TextWatcherSanitizer, TextFormatter, ChipFactory, 
//            ChipDrawableFactory, AnnotatedText, Chip

public final class TextPresenter
    implements TextWatcher, com.google.android.calendar.newapi.common.ShinobiEditText.OnDeletePressedListener
{

    public FragmentList fragmentList;
    public Listener listener;
    public final ShinobiEditText text;
    public final TextFormatter textFormatter;
    public final TextWatcher textWatcher = new TextWatcherSanitizer(this);
    public final Stack undoStack = new Stack();

    public TextPresenter(ShinobiEditText shinobiedittext, ImmutableList immutablelist)
    {
        fragmentList = new FragmentList();
        text = shinobiedittext;
        text.addTextChangedListener(textWatcher);
        text.onDeletePressedListener = this;
        Context context = shinobiedittext.getContext();
        ShinobiEditText shinobiedittext1 = text;
        shinobiedittext = shinobiedittext1.getResources();
        Object obj = shinobiedittext1.getPaint().getFontMetricsInt();
        int i = shinobiedittext.getDimensionPixelSize(0x7f0e0338);
        obj = new ChipFactory.ChipLineHeightSpan(i, (i - ((android.graphics.Paint.FontMetricsInt) (obj)).top - ((android.graphics.Paint.FontMetricsInt) (obj)).bottom) / 2);
        shinobiedittext = shinobiedittext1.getContext();
        TextPaint textpaint1 = shinobiedittext1.getPaint();
        i = ((ChipFactory.ChipLineHeightSpan) (obj)).height;
        int j = ((ChipFactory.ChipLineHeightSpan) (obj)).baseline;
        Resources resources = shinobiedittext.getResources();
        boolean flag = RtlUtils.isLayoutDirectionRtl(shinobiedittext);
        android.graphics.drawable.Drawable drawable = ContextCompat.getDrawable(shinobiedittext, 0x7f0201bf);
        int k = resources.getDimensionPixelSize(0x7f0e0336);
        TextPaint textpaint = new TextPaint();
        textpaint.set(textpaint1);
        textpaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        textpaint.setColor(ContextCompat.getColor(shinobiedittext, 0x7f0d031d));
        if (flag)
        {
            shinobiedittext = android.graphics.Paint.Align.RIGHT;
        } else
        {
            shinobiedittext = android.graphics.Paint.Align.LEFT;
        }
        textpaint.setTextAlign(shinobiedittext);
        if (Material.robotoMedium != null)
        {
            shinobiedittext = Material.robotoMedium;
        } else
        {
            shinobiedittext = Typeface.create("sans-serif-medium", 0);
            Material.robotoMedium = shinobiedittext;
        }
        textpaint.setTypeface(shinobiedittext);
        textFormatter = new TextFormatter(context, new ChipFactory(shinobiedittext1, new ChipDrawableFactory(resources, flag, textpaint, drawable, i, j, k), ((ChipFactory.ChipLineHeightSpan) (obj)).baseline), immutablelist);
    }

    public final void afterTextChanged(Editable editable)
    {
    }

    public final void applyAnnotatedText(AnnotatedText annotatedtext)
    {
        TextFormatter textformatter = textFormatter;
        String s1 = annotatedtext.text;
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder(s1);
        Iterator iterator = annotatedtext.fragmentList.fragments.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            AnnotationFragment annotationfragment = (AnnotationFragment)iterator.next();
            ImmutableList immutablelist = textformatter.chipTypes;
            AnnotationType annotationtype1 = AnnotationType.forNumber(annotationfragment.annotationType_);
            AnnotationType annotationtype = annotationtype1;
            if (annotationtype1 == null)
            {
                annotationtype = AnnotationType.TEXT;
            }
            if (immutablelist.contains(annotationtype) && annotationfragment.beginPos_ >= 0 && annotationfragment.beginPos_ < annotationfragment.endPos_ && annotationfragment.endPos_ <= s1.length())
            {
                Object obj = textformatter.chipFactory;
                String s = s1.substring(annotationfragment.beginPos_, annotationfragment.endPos_);
                ChipDrawableFactory chipdrawablefactory = ((ChipFactory) (obj)).drawableFactory;
                int i = ((ChipFactory) (obj)).baseline;
                obj = ((ChipFactory) (obj)).editText;
                spannablestringbuilder.setSpan(new Chip(chipdrawablefactory, s, i, ((ShinobiEditText) (obj)).getWidth() - ((ShinobiEditText) (obj)).getPaddingLeft() - ((ShinobiEditText) (obj)).getPaddingRight()), annotationfragment.beginPos_, annotationfragment.endPos_, 33);
            }
        } while (true);
        ShinobiEditText shinobiedittext = text;
        shinobiedittext.stealth = true;
        shinobiedittext.setText(spannablestringbuilder);
        shinobiedittext.stealth = false;
        text.setSelection(spannablestringbuilder.length());
        fragmentList = annotatedtext.fragmentList;
        updateTextListener();
    }

    public final void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
    {
    }

    public final boolean onDeletePressed()
    {
        if (undoStack.isEmpty())
        {
            return false;
        } else
        {
            applyAnnotatedText((AnnotatedText)undoStack.pop());
            return true;
        }
    }

    public final void onTextChanged(CharSequence charsequence, int i, int j, int k)
    {
        FragmentList fragmentlist = fragmentList;
        if (j != 0 || k != 0)
        {
            int l = k - j;
            ArrayList arraylist = new ArrayList();
            k = 0;
            while (k < fragmentlist.fragments.size()) 
            {
                charsequence = (AnnotationFragment)fragmentlist.fragments.get(k);
                if (((AnnotationFragment) (charsequence)).endPos_ > i)
                {
                    if (((AnnotationFragment) (charsequence)).beginPos_ >= i + j)
                    {
                        Object obj = (com.google.protobuf.GeneratedMessageLite.Builder)charsequence.dynamicMethod$51666RRD5TJMURR7DHIIUS3IDTQ6UOJLCONKEPBECLP62T35CH6MASRJC5JMAJ39EHII8JB5EHK6UP2KDT4MSTJFDDIJMJ3AC5R62BRCC5N6EBQFC9L6AORK7D66KOBMC4NMOOBECSNKUOJACLHN8EP99HL62TJ15TM62RJ75T7M4QJ5CDQ3M___0$514KOQJ1EPGIUR31DPJIUJR2D9IM6T1R9HL62TJ15TM62RJ75T7M4QJ5CDQ3MAACD9GNCO9FDHGMSPPF9TH6KPB3EGTG____0(android.support.v4.content.ModernAsyncTask.Status.NEW_BUILDER$9HHMUR9FCTNMUPRCCKNN0SJFEHNM4TB65T3MARJ5E9GN8PB49LIN6SR1CTIKOQBKCKI4QPBKD1NM8L3F95N7CRRBCKTG____0, null);
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        Object obj1 = ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).instance;
                        Protobuf.INSTANCE.schemaFor(obj1.getClass()).mergeFrom(obj1, charsequence);
                        obj = (com.google.personalization.assist.annotate.api.AnnotationFragment.Builder)(com.google.protobuf.GeneratedMessageLite.Builder)obj;
                        int i1 = ((AnnotationFragment) (charsequence)).beginPos_;
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        obj1 = (AnnotationFragment)((com.google.personalization.assist.annotate.api.AnnotationFragment.Builder) (obj)).instance;
                        obj1.bitField0_ = ((AnnotationFragment) (obj1)).bitField0_ | 4;
                        obj1.beginPos_ = i1 + l;
                        i1 = ((AnnotationFragment) (charsequence)).endPos_;
                        ((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).copyOnWrite();
                        charsequence = (AnnotationFragment)((com.google.personalization.assist.annotate.api.AnnotationFragment.Builder) (obj)).instance;
                        charsequence.bitField0_ = ((AnnotationFragment) (charsequence)).bitField0_ | 8;
                        charsequence.endPos_ = i1 + l;
                        charsequence = (AnnotationFragment)(GeneratedMessageLite)((com.google.protobuf.GeneratedMessageLite.Builder) (obj)).build();
                    } else
                    {
                        charsequence = null;
                    }
                }
                if (charsequence != null)
                {
                    arraylist.add(charsequence);
                }
                k++;
            }
            fragmentlist.fragments = arraylist;
        }
        undoStack.clear();
        if (listener != null)
        {
            updateTextListener();
        }
    }

    public final void updateTextListener()
    {
        if (listener != null)
        {
            listener.onTextChanged(text.getText().toString(), fragmentList.fragments);
        }
    }

    private class Listener
    {

        public abstract void onTextChanged(String s, List list);
    }

}
