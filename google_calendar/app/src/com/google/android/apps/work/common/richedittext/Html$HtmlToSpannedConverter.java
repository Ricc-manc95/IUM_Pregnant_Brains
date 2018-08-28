// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.work.common.richedittext;

import android.content.res.Resources;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

// Referenced classes of package com.google.android.apps.work.common.richedittext:
//            RichTextListSpan, RichTextBulletSpan

final class reader
    implements ContentHandler
{
    static class AbsoluteFontSize
    {

        public int size;

        public AbsoluteFontSize(int i)
        {
            size = i;
        }
    }

    static class Alignment
    {

        public android.text.Layout.Alignment alignment;

        public Alignment(android.text.Layout.Alignment alignment1)
        {
            alignment = alignment1;
        }
    }

    static class Background
    {

        public int backgroundColor;

        public Background(int i)
        {
            backgroundColor = i;
        }
    }

    static class Big
    {

        Big()
        {
        }
    }

    static class Blockquote
    {

        Blockquote()
        {
        }
    }

    static class Bold
    {

        Bold()
        {
        }
    }

    static class Bullet
    {

        Bullet()
        {
        }
    }

    static class Font
    {

        public String color;
        public String face;

        public Font(String s, String s1)
        {
            color = s;
            face = s1;
        }
    }

    static class Header
    {

        public int level;

        public Header(int i)
        {
            level = i;
        }
    }

    static class Indent
    {

        public int indent;

        public Indent(int i)
        {
            indent = i;
        }
    }

    static class Italic
    {

        Italic()
        {
        }
    }

    static class Link
    {

        public String href;

        public Link(String s)
        {
            href = s;
        }
    }

    static class List
    {

        public int itemCount;

        List()
        {
        }
    }

    static class Monospace
    {

        Monospace()
        {
        }
    }

    static class OrderedList extends List
    {

        OrderedList()
        {
        }
    }

    static class RelativeFontSize
    {

        public float size;

        public RelativeFontSize(float f)
        {
            size = f;
        }
    }

    static class Small
    {

        Small()
        {
        }
    }

    static class Strikethrough
    {

        Strikethrough()
        {
        }
    }

    static class Sub
    {

        Sub()
        {
        }
    }

    static class Super
    {

        Super()
        {
        }
    }

    static class TypefaceFamily
    {

        public String family;

        public TypefaceFamily(String s)
        {
            family = s;
        }
    }

    static class Underline
    {

        Underline