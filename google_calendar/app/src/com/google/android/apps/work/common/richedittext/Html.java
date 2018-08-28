// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.work.common.richedittext;

import android.text.Spanned;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public final class Html
{

    public static Spanned fromHtml(String s, TagHandler taghandler)
    {
        taghandler = new Parser();
        taghandler.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", HtmlParser.schema);
        return (new HtmlToSpannedConverter(s, null, taghandler)).convert();
        s;
_L2:
        throw new AssertionError(s);
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private class HtmlParser
    {

        public static final HTMLSchema schema = new HTMLSchema();

    }


    private class HtmlToSpannedConverter
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

            Underline()
            {
            }
        }

        static class UnorderedList extends List
        {

            UnorderedList()
            {
            }
        }


        private static final float HEADER_SIZES[] = {
            1.5F, 1.4F, 1.3F, 1.2F, 1.1F, 1.0F
        };
        private static final HashMap colorNameMap;
        private XMLReader reader;
        private String source;
        private SpannableStringBuilder spannableStringBuilder;
        private TagHandler tagHandler;

        private static void end(SpannableStringBuilder spannablestringbuilder, Class class1, Object obj)
        {
            int i = spannablestringbuilder.length();
            class1 = ((Class) (spannablestringbuilder.getSpans(0, spannablestringbuilder.length(), class1)));
            int j;
            if (class1.length == 0)
            {
                class1 = null;
            } else
            {
                class1 = class1[class1.length - 1];
            }
            j = spannablestringbuilder.getSpanStart(class1);
            spannablestringbuilder.removeSpan(class1);
            if (j != i)
            {
                spannablestringbuilder.setSpan(obj, j, i, 33);
            }
        }

        private static void endP(SpannableStringBuilder spannablestringbuilder)
        {
            int i = spannablestringbuilder.length();
            Object obj;
            if (i > 0 && spannablestringbuilder.charAt(i - 1) == '\n')
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                spannablestringbuilder.append("\n");
            }
            i = spannablestringbuilder.length();
            obj = ((Object) (spannablestringbuilder.getSpans(0, spannablestringbuilder.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Alignment)));
            if (obj.length == 0)
            {
                obj = null;
            } else
            {
                obj = obj[obj.length - 1];
            }
            if (obj != null)
            {
                int j = spannablestringbuilder.getSpanStart(obj);
                spannablestringbuilder.removeSpan(obj);
                if (j != i)
                {
                    obj = (Alignment)obj;
                    if (((Alignment) (obj)).alignment != null)
                    {
                        spannablestringbuilder.setSpan(new android.text.style.AlignmentSpan.Standard(((Alignment) (obj)).alignment), j, i, 33);
                    }
                }
            }
            obj = ((Object) (spannablestringbuilder.getSpans(0, spannablestringbuilder.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Indent)));
            if (obj.length == 0)
            {
                obj = null;
            } else
            {
                obj = obj[obj.length - 1];
            }
            if (obj != null)
            {
                int k = spannablestringbuilder.getSpanStart(obj);
                spannablestringbuilder.removeSpan(obj);
                if (k != i)
                {
                    spannablestringbuilder.setSpan(new android.text.style.LeadingMarginSpan.Standard(((Indent)obj).indent), k, i, 33);
                }
            }
        }

        private static int getHtmlColor(String s)
        {
            int i;
            int j;
            byte byte0;
            byte byte1;
            i = 1;
            Integer integer = (Integer)colorNameMap.get(s.toLowerCase(Locale.ROOT));
            if (integer != null)
            {
                return integer.intValue();
            }
            if (s == null)
            {
                return -1;
            }
            try
            {
                s = s.toString();
                j = s.length();
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return -1;
            }
            byte1 = 10;
            if ('-' == s.charAt(0))
            {
                byte0 = -1;
            } else
            {
                byte0 = 1;
                i = 0;
            }
            if ('0' != s.charAt(i))
            {
                break MISSING_BLOCK_LABEL_118;
            }
            if (i == j - 1)
            {
                return 0;
            }
            j = s.charAt(i + 1);
            if ('x' == j || 'X' == j)
            {
                j = i + 2;
                i = 16;
            } else
            {
                j = i + 1;
                i = 8;
            }
            return Integer.parseInt(s.substring(j), i) * byte0;
            j = s.charAt(i);
            if ('#' == j)
            {
                j = i + 1;
                i = 16;
            } else
            {
                j = i;
                i = byte1;
            }
            if (false)
            {
            } else
            {
                break MISSING_BLOCK_LABEL_105;
            }
        }

        private static void setSpanFromMark(Spannable spannable, Object obj, Object obj1)
        {
            int i = spannable.getSpanStart(obj);
            spannable.removeSpan(obj);
            int j = spannable.length();
            if (i != j)
            {
                spannable.setSpan(obj1, i, j, 33);
            }
        }

        private static void startP(SpannableStringBuilder spannablestringbuilder, Attributes attributes)
        {
            Object obj;
            Object obj1;
            int i;
            boolean flag1 = false;
            if (spannablestringbuilder.length() != 0)
            {
                int k = spannablestringbuilder.length();
                boolean flag = flag1;
                if (k > 0)
                {
                    flag = flag1;
                    if (spannablestringbuilder.charAt(k - 1) == '\n')
                    {
                        flag = true;
                    }
                }
                if (!flag)
                {
                    spannablestringbuilder.append("\n");
                }
            }
            i = spannablestringbuilder.length();
            obj1 = attributes.getValue("", "align");
            obj = obj1;
            if (obj1 != null) goto _L2; else goto _L1
_L1:
            String s;
            s = attributes.getValue("", "style");
            obj = obj1;
            if (s == null) goto _L2; else goto _L3
_L3:
            obj = Pattern.compile(".*\\btext-align\\s*:\\s*(\\S*)\\b.*").matcher(s);
            attributes = ((Attributes) (obj1));
            if (((Matcher) (obj)).find())
            {
                attributes = ((Matcher) (obj)).group(1);
            }
            obj1 = Pattern.compile(".*\\btext-indent\\s*:\\s*(\\S*)\\b.*").matcher(s);
            obj = attributes;
            if (!((Matcher) (obj1)).find()) goto _L2; else goto _L4
_L4:
            obj = ((Matcher) (obj1)).group(1);
_L10:
            if (attributes == null) goto _L6; else goto _L5
_L5:
            if (!attributes.equalsIgnoreCase("left")) goto _L8; else goto _L7
_L7:
            spannablestringbuilder.setSpan(new Alignment(android.text.Layout.Alignment.ALIGN_NORMAL), i, i, 17);
_L6:
            if (obj == null || !((String) (obj)).endsWith("px"))
            {
                break MISSING_BLOCK_LABEL_256;
            }
            int j;
            try
            {
                j = Integer.valueOf(((String) (obj)).substring(0, ((String) (obj)).length() - 2)).intValue();
            }
            // Misplaced declaration of an exception variable
            catch (SpannableStringBuilder spannablestringbuilder)
            {
                return;
            }
            if (j <= 0)
            {
                break MISSING_BLOCK_LABEL_256;
            }
            spannablestringbuilder.setSpan(new Indent(j), i, i, 17);
            return;
_L8:
            if (attributes.equalsIgnoreCase("center"))
            {
                spannablestringbuilder.setSpan(new Alignment(android.text.Layout.Alignment.ALIGN_CENTER), i, i, 17);
            } else
            if (attributes.equalsIgnoreCase("right"))
            {
                spannablestringbuilder.setSpan(new Alignment(android.text.Layout.Alignment.ALIGN_OPPOSITE), i, i, 17);
            }
            if (true) goto _L6; else goto _L2
_L2:
            attributes = ((Attributes) (obj));
            obj = null;
            if (true) goto _L10; else goto _L9
_L9:
        }

        public final void characters(char ac[], int i, int j)
            throws SAXException
        {
            StringBuilder stringbuilder = new StringBuilder();
            int l = 0;
            while (l < j) 
            {
                char c = ac[l + i];
                if (c == ' ' || c == '\n')
                {
                    int k = stringbuilder.length();
                    if (k == 0)
                    {
                        k = spannableStringBuilder.length();
                        if (k == 0)
                        {
                            k = 10;
                        } else
                        {
                            k = spannableStringBuilder.charAt(k - 1);
                        }
                    } else
                    {
                        k = stringbuilder.charAt(k - 1);
                    }
                    if (k != 32 && k != 10)
                    {
                        stringbuilder.append(' ');
                    }
                } else
                {
                    stringbuilder.append(c);
                }
                l++;
            }
            spannableStringBuilder.append(stringbuilder);
        }

        public final Spanned convert()
        {
            boolean flag1 = false;
            reader.setContentHandler(this);
            Object obj;
            int i;
            try
            {
                reader.parse(new InputSource(new StringReader(source)));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                throw new RuntimeException(((Throwable) (obj)));
            }
            // Misplaced declaration of an exception variable
            catch (Object obj)
            {
                throw new RuntimeException(((Throwable) (obj)));
            }
            obj = ((Object) (spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), java/lang/Object)));
            i = 0;
            while (i < obj.length) 
            {
                int i1 = spannableStringBuilder.getSpanStart(obj[i]);
                int l = spannableStringBuilder.getSpanEnd(obj[i]);
                if (!(obj[i] instanceof ParagraphStyle))
                {
                    int j = 34;
                    if (obj[i] instanceof URLSpan)
                    {
                        j = 0xff0022;
                    }
                    spannableStringBuilder.setSpan(obj[i], i1, l, j);
                } else
                {
                    boolean flag;
                    int k;
                    if (l - 2 >= 0 && spannableStringBuilder.charAt(l - 1) == '\n' && spannableStringBuilder.charAt(l - 2) == '\n')
                    {
                        k = l - 1;
                    } else
                    {
                        k = l;
                    }
                    if (k == i1)
                    {
                        spannableStringBuilder.removeSpan(obj[i]);
                    } else
                    {
                        spannableStringBuilder.setSpan(obj[i], i1, k, 51);
                    }
                }
                i++;
            }
            obj = spannableStringBuilder;
            k = ((CharSequence) (obj)).length();
            flag = flag1;
            if (k > 0)
            {
                flag = flag1;
                if (((CharSequence) (obj)).charAt(k - 1) == '\n')
                {
                    flag = true;
                }
            }
            if (flag)
            {
                flag = ((Editable) (obj)).length();
                ((Editable) (obj)).delete(flag - 1, flag);
            }
            return spannableStringBuilder;
        }

        public final void endDocument()
            throws SAXException
        {
        }

        public final void endElement(String s, String s1, String s2)
            throws SAXException
        {
            int i;
            int j;
            int k;
            int l;
            s = null;
            s2 = null;
            i = 1;
            j = 1;
            l = 1;
            k = 0;
            if (!s1.equalsIgnoreCase("br")) goto _L2; else goto _L1
_L1:
            spannableStringBuilder.append("\n");
_L4:
            return;
_L2:
            if (s1.equalsIgnoreCase("p"))
            {
                endP(spannableStringBuilder);
                return;
            }
            if (s1.equalsIgnoreCase("ul"))
            {
                s = spannableStringBuilder;
                j = ((List[])s.getSpans(0, s.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$List)).length;
                i = j;
                if (j <= 0)
                {
                    i = 1;
                }
                end(s, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$UnorderedList, new RichTextListSpan(0, i));
                return;
            }
            if (s1.equalsIgnoreCase("ol"))
            {
                s = spannableStringBuilder;
                j = ((List[])s.getSpans(0, s.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$List)).length;
                i = j;
                if (j <= 0)
                {
                    i = 1;
                }
                end(s, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$OrderedList, new RichTextListSpan(1, i));
                return;
            }
            if (s1.equalsIgnoreCase("li"))
            {
                s1 = spannableStringBuilder;
                i = s1.length();
                if (i > 0 && s1.charAt(i - 1) == '\n')
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    s1.append("\n");
                }
                j = ((List[])s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$List)).length;
                if (j <= 0)
                {
                    j = 1;
                }
                s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$List)));
                if (s.length == 0)
                {
                    s = null;
                } else
                {
                    s = s[s.length - 1];
                }
                s = (List)s;
                if (s != null)
                {
                    if (s instanceof OrderedList)
                    {
                        i = l;
                    } else
                    {
                        i = 0;
                    }
                    l = ((List) (s)).itemCount;
                    s.itemCount = l + 1;
                    k = i;
                    i = l;
                } else
                {
                    boolean flag = false;
                    i = k;
                    k = ((flag) ? 1 : 0);
                }
                s = new RichTextBulletSpan(k, j);
                s.index = i;
                end(s1, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Bullet, s);
                endP(s1);
                return;
            }
            if (!s1.equalsIgnoreCase("div"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = spannableStringBuilder;
            j = s.length();
            if (j <= 0 || s.charAt(j - 1) != '\n')
            {
                i = 0;
            }
            if (i == 0)
            {
                s.append("\n");
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
            if (s1.equalsIgnoreCase("strong"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Bold, new StyleSpan(1));
                return;
            }
            if (s1.equalsIgnoreCase("b"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Bold, new StyleSpan(1));
                return;
            }
            if (s1.equalsIgnoreCase("em"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Italic, new StyleSpan(2));
                return;
            }
            if (s1.equalsIgnoreCase("cite"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Italic, new StyleSpan(2));
                return;
            }
            if (s1.equalsIgnoreCase("dfn"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Italic, new StyleSpan(2));
                return;
            }
            if (s1.equalsIgnoreCase("i"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Italic, new StyleSpan(2));
                return;
            }
            if (s1.equalsIgnoreCase("big"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Big, new RelativeSizeSpan(1.25F));
                return;
            }
            if (s1.equalsIgnoreCase("small"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Small, new RelativeSizeSpan(0.8F));
                return;
            }
            if (!s1.equalsIgnoreCase("span"))
            {
                break; /* Loop/switch isn't completed */
            }
            s1 = spannableStringBuilder;
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Background)));
            if (s.length == 0)
            {
                s = null;
            } else
            {
                s = s[s.length - 1];
            }
            if (s != null)
            {
                setSpanFromMark(s1, s, new BackgroundColorSpan(((Background)s).backgroundColor));
            }
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$TypefaceFamily)));
            if (s.length == 0)
            {
                s = null;
            } else
            {
                s = s[s.length - 1];
            }
            if (s != null)
            {
                setSpanFromMark(s1, s, new TypefaceSpan(((TypefaceFamily)s).family));
            }
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$AbsoluteFontSize)));
            if (s.length == 0)
            {
                s = null;
            } else
            {
                s = s[s.length - 1];
            }
            if (s != null)
            {
                setSpanFromMark(s1, s, new AbsoluteSizeSpan(((AbsoluteFontSize)s).size, true));
            }
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$RelativeFontSize)));
            if (s.length == 0)
            {
                s = s2;
            } else
            {
                s = s[s.length - 1];
            }
            if (s != null)
            {
                setSpanFromMark(s1, s, new RelativeSizeSpan(((RelativeFontSize)s).size));
                return;
            }
            if (true) goto _L4; else goto _L5
_L5:
            if (!s1.equalsIgnoreCase("font"))
            {
                break; /* Loop/switch isn't completed */
            }
            s1 = spannableStringBuilder;
            i = s1.length();
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Font)));
            if (s.length == 0)
            {
                s = null;
            } else
            {
                s = s[s.length - 1];
            }
            j = s1.getSpanStart(s);
            s1.removeSpan(s);
            if (j != i)
            {
                s = (Font)s;
                if (!TextUtils.isEmpty(((Font) (s)).color))
                {
                    if (((Font) (s)).color.startsWith("@"))
                    {
                        s2 = Resources.getSystem();
                        k = s2.getIdentifier(((Font) (s)).color.substring(1), "color", "android");
                        if (k != 0)
                        {
                            s1.setSpan(new TextAppearanceSpan(null, 0, 0, s2.getColorStateList(k), null), j, i, 33);
                        }
                    } else
                    {
                        k = getHtmlColor(((Font) (s)).color);
                        if (k != -1)
                        {
                            s1.setSpan(new ForegroundColorSpan(k | 0xff000000), j, i, 33);
                        }
                    }
                }
                if (((Font) (s)).face != null)
                {
                    s1.setSpan(new TypefaceSpan(((Font) (s)).face), j, i, 33);
                    return;
                }
            }
            if (true) goto _L4; else goto _L6
_L6:
            if (s1.equalsIgnoreCase("blockquote"))
            {
                s = spannableStringBuilder;
                i = s.length();
                if (i > 0 && s.charAt(i - 1) == '\n')
                {
                    i = j;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    s.append("\n");
                }
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Blockquote, new QuoteSpan());
                return;
            }
            if (s1.equalsIgnoreCase("tt"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Monospace, new TypefaceSpan("monospace"));
                return;
            }
            if (!s1.equalsIgnoreCase("a"))
            {
                break; /* Loop/switch isn't completed */
            }
            s1 = spannableStringBuilder;
            s2 = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Link)));
            if (s2.length != 0)
            {
                s = s2[s2.length - 1];
            }
            if (s != null)
            {
                s2 = (Link)s;
                if (((Link) (s2)).href != null)
                {
                    setSpanFromMark(s1, s, new URLSpan(((Link) (s2)).href));
                    return;
                }
            }
            if (true) goto _L4; else goto _L7
_L7:
            if (s1.equalsIgnoreCase("u"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Underline, new UnderlineSpan());
                return;
            }
            if (s1.equalsIgnoreCase("strike"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Strikethrough, new StrikethroughSpan());
                return;
            }
            if (s1.equalsIgnoreCase("del"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Strikethrough, new StrikethroughSpan());
                return;
            }
            if (s1.equalsIgnoreCase("sup"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Super, new SuperscriptSpan());
                return;
            }
            if (s1.equalsIgnoreCase("sub"))
            {
                end(spannableStringBuilder, com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Sub, new SubscriptSpan());
                return;
            }
            if (s1.length() == 2 && Character.toLowerCase(s1.charAt(0)) == 'h' && s1.charAt(1) >= '1' && s1.charAt(1) <= '6')
            {
                s = spannableStringBuilder;
                i = s.length();
                if (i > 0 && s.charAt(i - 1) == '\n')
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    s.append("\n");
                }
                s1 = spannableStringBuilder;
                i = s1.length();
                s = ((String) (s1.getSpans(0, s1.length(), com/google/android/apps/work/common/richedittext/Html$HtmlToSpannedConverter$Header)));
                if (s.length == 0)
                {
                    s = null;
                } else
                {
                    s = s[s.length - 1];
                }
                j = s1.getSpanStart(s);
                s1.removeSpan(s);
                for (; i > j && s1.charAt(i - 1) == '\n'; i--) { }
                if (j != i)
                {
                    s = (Header)s;
                    s1.setSpan(new RelativeSizeSpan(HEADER_SIZES[((Header) (s)).level]), j, i, 33);
                    s1.setSpan(new StyleSpan(1), j, i, 33);
                    return;
                }
            } else
            {
                if (tagHandler != null)
                {
                    tagHandler._mth51D4OQJ1EPGIUR31DPJIUKRKE9KMSPPR9HGMSP3IDTKM8BRKCLS78BQ5CHKN8OB2DHIJMJ3FE9JIUU3DDGNN6OBO5TC4QJ2ICLGM8PBI7CKLC___0();
                    return;
                }
                s = String.valueOf(s1);
                if (s.length() != 0)
                {
                    "unsupported tag: ".concat(s);
                    return;
                } else
                {
                    new String("unsupported tag: ");
                    return;
                }
            }
            if (true) goto _L4; else goto _L8
_L8:
        }

        public final void endPrefixMapping(String s)
            throws SAXException
        {
        }

        public final void ignorableWhitespace(char ac[], int i, int j)
            throws SAXException
        {
        }

        public final void processingInstruction(String s, String s1)
            throws SAXException
        {
        }

        public final void setDocumentLocator(Locator locator)
        {
        }

        public final void skippedEntity(String s)
            throws SAXException
        {
        }

        public final void startDocument()
            throws SAXException
        {
        }

        public final void startElement(String s, String s1, String s2, Attributes attributes)
            throws SAXException
        {
            String s3;
            int i;
            int k2;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            s3 = null;
            k2 = 0;
            flag1 = true;
            flag2 = true;
            flag3 = true;
            flag4 = true;
            i = 1;
            if (s1.equalsIgnoreCase("br")) goto _L2; else goto _L1
_L1:
            if (!s1.equalsIgnoreCase("p")) goto _L4; else goto _L3
_L3:
            startP(spannableStringBuilder, attributes);
_L2:
            return;
_L4:
            if (s1.equalsIgnoreCase("ul"))
            {
                s = spannableStringBuilder;
                if (s.length() != 0)
                {
                    k2 = s.length();
                    if (k2 <= 0 || s.charAt(k2 - 1) != '\n')
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        s.append("\n");
                    }
                }
                s1 = new UnorderedList();
                i = s.length();
                s.setSpan(s1, i, i, 17);
                return;
            }
            if (s1.equalsIgnoreCase("ol"))
            {
                s = spannableStringBuilder;
                if (s.length() != 0)
                {
                    i = s.length();
                    if (i > 0 && s.charAt(i - 1) == '\n')
                    {
                        i = ((flag1) ? 1 : 0);
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        s.append("\n");
                    }
                }
                s1 = new OrderedList();
                i = s.length();
                s.setSpan(s1, i, i, 17);
                return;
            }
            if (s1.equalsIgnoreCase("li"))
            {
                s = spannableStringBuilder;
                if (s.length() != 0)
                {
                    i = s.length();
                    if (i > 0 && s.charAt(i - 1) == '\n')
                    {
                        i = ((flag2) ? 1 : 0);
                    } else
                    {
                        i = 0;
                    }
                    if (i == 0)
                    {
                        s.append("\n");
                    }
                }
                startP(s, attributes);
                s1 = new Bullet();
                i = s.length();
                s.setSpan(s1, i, i, 17);
                return;
            }
            if (!s1.equalsIgnoreCase("div"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = spannableStringBuilder;
            i = s.length();
            boolean flag;
            if (i > 0 && s.charAt(i - 1) == '\n')
            {
                flag = flag3;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                s.append("\n");
                return;
            }
            if (true) goto _L2; else goto _L5
_L5:
            if (s1.equalsIgnoreCase("strong"))
            {
                s = spannableStringBuilder;
                s1 = new Bold();
                int j = s.length();
                s.setSpan(s1, j, j, 17);
                return;
            }
            if (s1.equalsIgnoreCase("b"))
            {
                s = spannableStringBuilder;
                s1 = new Bold();
                int k = s.length();
                s.setSpan(s1, k, k, 17);
                return;
            }
            if (s1.equalsIgnoreCase("em"))
            {
                s = spannableStringBuilder;
                s1 = new Italic();
                int l = s.length();
                s.setSpan(s1, l, l, 17);
                return;
            }
            if (s1.equalsIgnoreCase("cite"))
            {
                s = spannableStringBuilder;
                s1 = new Italic();
                int i1 = s.length();
                s.setSpan(s1, i1, i1, 17);
                return;
            }
            if (s1.equalsIgnoreCase("dfn"))
            {
                s = spannableStringBuilder;
                s1 = new Italic();
                int j1 = s.length();
                s.setSpan(s1, j1, j1, 17);
                return;
            }
            if (s1.equalsIgnoreCase("i"))
            {
                s = spannableStringBuilder;
                s1 = new Italic();
                int k1 = s.length();
                s.setSpan(s1, k1, k1, 17);
                return;
            }
            if (s1.equalsIgnoreCase("big"))
            {
                s = spannableStringBuilder;
                s1 = new Big();
                int l1 = s.length();
                s.setSpan(s1, l1, l1, 17);
                return;
            }
            if (s1.equalsIgnoreCase("small"))
            {
                s = spannableStringBuilder;
                s1 = new Small();
                int i2 = s.length();
                s.setSpan(s1, i2, i2, 17);
                return;
            }
            if (!s1.equalsIgnoreCase("span"))
            {
                break MISSING_BLOCK_LABEL_1118;
            }
            SpannableStringBuilder spannablestringbuilder = spannableStringBuilder;
            s2 = attributes.getValue("", "style");
            if (s2 != null)
            {
                s = Pattern.compile(".*\\bbackground\\s*:\\s*(\\S*)\\b.*").matcher(s2);
                float f;
                Matcher matcher;
                int j2;
                int l2;
                if (s.find())
                {
                    s = s.group(1);
                } else
                {
                    s = null;
                }
                s1 = Pattern.compile(".*\\bfont-family\\s*:\\s*(\\S*)\\b.*").matcher(s2);
                if (s1.find())
                {
                    s1 = s1.group(1);
                } else
                {
                    s1 = null;
                }
                matcher = Pattern.compile(".*\\bfont-size\\s*:\\s*((\\S)*)(;|\").*").matcher(s2);
                s2 = s;
                attributes = s3;
                s3 = s1;
                if (matcher.find())
                {
                    attributes = matcher.group(1);
                    s3 = s1;
                    s2 = s;
                }
            } else
            {
                s = null;
                s2 = null;
                attributes = s3;
                s3 = s;
            }
            j2 = spannablestringbuilder.length();
            if (s2 != null)
            {
                spannablestringbuilder.setSpan(new Background(getHtmlColor(s2) | 0xff000000), j2, j2, 17);
            }
            if (s3 != null)
            {
                spannablestringbuilder.setSpan(new TypefaceFamily(s3), j2, j2, 17);
            }
            if (attributes == null) goto _L2; else goto _L6
_L6:
            if (!attributes.endsWith("em"))
            {
                break MISSING_BLOCK_LABEL_1005;
            }
            f = Float.valueOf(attributes.substring(0, attributes.length() - 2)).floatValue();
            if (f == 1.0F) goto _L2; else goto _L7
_L7:
            try
            {
                spannablestringbuilder.setSpan(new RelativeFontSize(f), j2, j2, 17);
                return;
            }
            // Misplaced declaration of an exception variable
            catch (String s)
            {
                return;
            }
            if (!attributes.endsWith("%"))
            {
                continue; /* Loop/switch isn't completed */
            }
            f = Float.valueOf(attributes.substring(0, attributes.length() - 1)).floatValue() / 100F;
            if (f == 1.0F) goto _L2; else goto _L8
_L8:
            spannablestringbuilder.setSpan(new RelativeFontSize(f), j2, j2, 17);
            return;
            if (!attributes.endsWith("px")) goto _L2; else goto _L9
_L9:
            spannablestringbuilder.setSpan(new AbsoluteFontSize(Integer.valueOf(attributes.substring(0, attributes.length() - 2)).intValue()), j2, j2, 17);
            return;
            if (s1.equalsIgnoreCase("font"))
            {
                s = spannableStringBuilder;
                s1 = attributes.getValue("", "color");
                s2 = attributes.getValue("", "face");
                j2 = s.length();
                s.setSpan(new Font(s1, s2), j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("blockquote"))
            {
                s = spannableStringBuilder;
                j2 = s.length();
                if (j2 > 0 && s.charAt(j2 - 1) == '\n')
                {
                    j2 = ((flag4) ? 1 : 0);
                } else
                {
                    j2 = 0;
                }
                if (j2 == 0)
                {
                    s.append("\n");
                }
                s = spannableStringBuilder;
                s1 = new Blockquote();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("tt"))
            {
                s = spannableStringBuilder;
                s1 = new Monospace();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("a"))
            {
                s = spannableStringBuilder;
                s1 = attributes.getValue("", "href");
                j2 = s.length();
                s.setSpan(new Link(s1), j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("u"))
            {
                s = spannableStringBuilder;
                s1 = new Underline();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("strike"))
            {
                s = spannableStringBuilder;
                s1 = new Strikethrough();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("del"))
            {
                s = spannableStringBuilder;
                s1 = new Strikethrough();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("sup"))
            {
                s = spannableStringBuilder;
                s1 = new Super();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.equalsIgnoreCase("sub"))
            {
                s = spannableStringBuilder;
                s1 = new Sub();
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (s1.length() == 2 && Character.toLowerCase(s1.charAt(0)) == 'h' && s1.charAt(1) >= '1' && s1.charAt(1) <= '6')
            {
                s = spannableStringBuilder;
                l2 = s.length();
                j2 = k2;
                if (l2 > 0)
                {
                    j2 = k2;
                    if (s.charAt(l2 - 1) == '\n')
                    {
                        j2 = 1;
                    }
                }
                if (j2 == 0)
                {
                    s.append("\n");
                }
                s = spannableStringBuilder;
                s1 = new Header(s1.charAt(1) - 49);
                j2 = s.length();
                s.setSpan(s1, j2, j2, 17);
                return;
            }
            if (tagHandler != null)
            {
                tagHandler._mth51D4OQJ1EPGIUR31DPJIUKRKE9KMSPPR9HGMSP3IDTKM8BRKCLS78BQ5CHKN8OB2DHIJMJ3FE9JIUU3DDGNN6OBO5TC4QJ2ICLGM8PBI7CKLC___0();
                return;
            }
            s = String.valueOf(s1);
            if (s.length() != 0)
            {
                "unsupported tag: ".concat(s);
                return;
            } else
            {
                new String("unsupported tag: ");
                return;
            }
        }

        public final void startPrefixMapping(String s, String s1)
            throws SAXException
        {
        }

        static 
        {
            HashMap hashmap = new HashMap();
            colorNameMap = hashmap;
            hashmap.put("black", Integer.valueOf(0xff000000));
            colorNameMap.put("darkgray", Integer.valueOf(0xffa9a9a9));
            colorNameMap.put("gray", Integer.valueOf(0xff808080));
            colorNameMap.put("lightgray", Integer.valueOf(0xffd3d3d3));
            colorNameMap.put("white", Integer.valueOf(-1));
            colorNameMap.put("red", Integer.valueOf(0xffff0000));
            colorNameMap.put("green", Integer.valueOf(0xff008000));
            colorNameMap.put("blue", Integer.valueOf(0xff0000ff));
            colorNameMap.put("yellow", Integer.valueOf(-256));
            colorNameMap.put("cyan", Integer.valueOf(0xff00ffff));
            colorNameMap.put("magenta", Integer.valueOf(-65281));
            colorNameMap.put("aqua", Integer.valueOf(0xff00ffff));
            colorNameMap.put("fuchsia", Integer.valueOf(-65281));
            colorNameMap.put("darkgrey", Integer.valueOf(0xffa9a9a9));
            colorNameMap.put("grey", Integer.valueOf(0xff808080));
            colorNameMap.put("lightgrey", Integer.valueOf(0xffd3d3d3));
            colorNameMap.put("lime", Integer.valueOf(0xff00ff00));
            colorNameMap.put("maroon", Integer.valueOf(0xff800000));
            colorNameMap.put("navy", Integer.valueOf(0xff000080));
            colorNameMap.put("olive", Integer.valueOf(0xff808000));
            colorNameMap.put("purple", Integer.valueOf(0xff800080));
            colorNameMap.put("silver", Integer.valueOf(0xffc0c0c0));
            colorNameMap.put("teal", Integer.valueOf(0xff008080));
        }

        HtmlToSpannedConverter(String s, TagHandler taghandler, Parser parser)
        {
            source = s;
            spannableStringBuilder = new SpannableStringBuilder();
            tagHandler = taghandler;
            reader = parser;
        }

        private class TagHandler
        {

            public abstract void handleTag$51D4OQJ1EPGIUR31DPJIUKRKE9KMSPPR9HGMSP3IDTKM8BRKCLS78BQ5CHKN8OB2DHIJMJ3FE9JIUU3DDGNN6OBO5TC4QJ2ICLGM8PBI7CKLC___0();
        }

    }

}
