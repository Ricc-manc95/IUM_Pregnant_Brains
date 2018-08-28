// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.note;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.work.common.richedittext.Html;
import com.google.common.base.Strings;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ccil.cowan.tagsoup.HTMLSchema;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class NoteHtmlConverter
{
    static final class HtmlToPlainTextConverter
        implements ContentHandler
    {

        public static final ImmutableSet IGNORED_TAGS = ImmutableSet.of("html", "body", "b", "big", "cite", "del", new String[] {
            "dfn", "em", "font", "h1", "h2", "h3", "h4", "h5", "h6", "i", 
            "u", "small", "span", "strike", "strong", "sub", "sup", "table"
        });
        public static final ImmutableSet PROCESSED_TAGS = ImmutableSet.of("br", "p", "ul", "ol", "li", "div", new String[] {
            "a", "img", "th", "tr", "td"
        });
        private final XMLReader reader;
        private final String source;
        private final SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        public final void characters(char ac[], int i, int j)
            throws SAXException
        {
            SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
            int l = 0;
            while (l < j) 
            {
                char c = ac[l + i];
                if (c == ' ' || c == '\n')
                {
                    int k = spannablestringbuilder.length();
                    if (k == 0)
                    {
                        k = stringBuilder.length();
                        if (k == 0)
                        {
                            k = 10;
                        } else
                        {
                            k = stringBuilder.charAt(k - 1);
                        }
                    } else
                    {
                        k = spannablestringbuilder.charAt(k - 1);
                    }
                    if (k != 32 && k != 10)
                    {
                        spannablestringbuilder.append(' ');
                    }
                } else
                {
                    spannablestringbuilder.append(c);
                }
                l++;
            }
            stringBuilder.append(spannablestringbuilder);
        }

        public final String convert()
        {
            reader.setContentHandler(this);
            SpannableStringBuilder spannablestringbuilder;
            int i;
            try
            {
                reader.parse(new InputSource(new StringReader(source)));
            }
            catch (IOException ioexception)
            {
                throw new RuntimeException(ioexception);
            }
            catch (SAXException saxexception)
            {
                throw new RuntimeException(saxexception);
            }
            spannablestringbuilder = stringBuilder;
            i = spannablestringbuilder.length();
            if (i > 0 && spannablestringbuilder.charAt(i - 1) == '\n')
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i != 0)
            {
                i = spannablestringbuilder.length();
                spannablestringbuilder.delete(i - 1, i);
            }
            return stringBuilder.toString();
        }

        public final void endDocument()
            throws SAXException
        {
        }

        public final void endElement(String s, String s1, String s2)
            throws SAXException
        {
            Object obj;
            Object obj1;
            boolean flag;
            int l1;
            boolean flag6;
            boolean flag7;
            boolean flag8;
            boolean flag9;
            boolean flag10;
            s2 = null;
            obj = null;
            obj1 = null;
            s = null;
            flag6 = true;
            flag7 = true;
            flag8 = true;
            flag9 = true;
            l1 = 1;
            flag10 = true;
            flag = true;
            if (!s1.equalsIgnoreCase("br")) goto _L2; else goto _L1
_L1:
            stringBuilder.append("\n");
_L4:
            return;
_L2:
            if (!s1.equalsIgnoreCase("p"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            l1 = s.length();
            if (l1 <= 0 || s.charAt(l1 - 1) != '\n')
            {
                flag = false;
            }
            if (!flag)
            {
                s.append('\n');
                return;
            }
            if (true) goto _L4; else goto _L3
_L3:
            if (s1.equalsIgnoreCase("ul"))
            {
                s1 = stringBuilder;
                s2 = ((String) (s1.getSpans(0, s1.length(), com/google/android/calendar/newapi/segment/note/NoteHtmlConverter$HtmlToPlainTextConverter$UnorderedList)));
                if (s2.length != 0)
                {
                    s = s2[s2.length - 1];
                }
                s1.removeSpan(s);
                return;
            }
            if (s1.equalsIgnoreCase("ol"))
            {
                s1 = stringBuilder;
                s = ((String) (s1.getSpans(0, s1.length(), com/google/android/calendar/newapi/segment/note/NoteHtmlConverter$HtmlToPlainTextConverter$OrderedList)));
                if (s.length == 0)
                {
                    s = s2;
                } else
                {
                    s = s[s.length - 1];
                }
                s1.removeSpan(s);
                return;
            }
            if (!s1.equalsIgnoreCase("li"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            int i = s.length();
            if (i > 0 && s.charAt(i - 1) == '\n')
            {
                i = 1;
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                s.append('\n');
            }
            i = s.length();
            if (i > 0 && s.charAt(i - 1) == '\n')
            {
                i = ((flag6) ? 1 : 0);
            } else
            {
                i = 0;
            }
            if (i == 0)
            {
                s.append('\n');
                return;
            }
            if (true) goto _L4; else goto _L5
_L5:
            if (!s1.equalsIgnoreCase("div"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            int j = s.length();
            boolean flag1;
            if (j > 0 && s.charAt(j - 1) == '\n')
            {
                flag1 = flag7;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                s.append('\n');
                return;
            }
            if (true) goto _L4; else goto _L6
_L6:
            if (!s1.equalsIgnoreCase("a"))
            {
                break; /* Loop/switch isn't completed */
            }
            s1 = stringBuilder;
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/calendar/newapi/segment/note/NoteHtmlConverter$HtmlToPlainTextConverter$Href)));
            if (s.length == 0)
            {
                s = obj;
            } else
            {
                s = s[s.length - 1];
            }
            s = (Href)s;
            if (s != null && ((Href) (s)).href != null)
            {
                int k = s1.length();
                boolean flag2;
                if (k > 0 && s1.charAt(k - 1) == ' ')
                {
                    flag2 = flag8;
                } else
                {
                    flag2 = false;
                }
                if (!flag2)
                {
                    s1.append(' ');
                }
                s1.append("(").append(((Href) (s)).href).append(")");
                return;
            }
            if (true) goto _L4; else goto _L7
_L7:
            if (!s1.equalsIgnoreCase("img"))
            {
                break; /* Loop/switch isn't completed */
            }
            s1 = stringBuilder;
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/calendar/newapi/segment/note/NoteHtmlConverter$HtmlToPlainTextConverter$Img)));
            if (s.length == 0)
            {
                s = obj1;
            } else
            {
                s = s[s.length - 1];
            }
            s = (Img)s;
            if (s != null && !TextUtils.isEmpty(((Img) (s)).src))
            {
                s1.append(((Img) (s)).src);
                return;
            }
            if (true) goto _L4; else goto _L8
_L8:
            if (s1.length() != 2 || Character.toLowerCase(s1.charAt(0)) != 'h' || !Character.isDigit(s1.charAt(1)))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            int l = s.length();
            boolean flag3;
            if (l > 0 && s.charAt(l - 1) == '\n')
            {
                flag3 = flag9;
            } else
            {
                flag3 = false;
            }
            if (!flag3)
            {
                s.append('\n');
                return;
            }
            if (true) goto _L4; else goto _L9
_L9:
            if (!s1.equalsIgnoreCase("tr"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            int i1 = s.length();
            if (i1 > 0 && s.charAt(i1 - 1) == ' ')
            {
                i1 = 1;
            } else
            {
                i1 = 0;
            }
            if (i1 != 0)
            {
                i1 = s.length();
                s.delete(i1 - 1, i1);
            }
            i1 = s.length();
            if (i1 > 0 && s.charAt(i1 - 1) == '\n')
            {
                i1 = l1;
            } else
            {
                i1 = 0;
            }
            if (i1 == 0)
            {
                s.append('\n');
                return;
            }
            if (true) goto _L4; else goto _L10
_L10:
            if (!s1.equalsIgnoreCase("th"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            int j1 = s.length();
            boolean flag4;
            if (j1 > 0 && s.charAt(j1 - 1) == ' ')
            {
                flag4 = flag10;
            } else
            {
                flag4 = false;
            }
            if (!flag4)
            {
                s.append(' ');
                return;
            }
            if (true) goto _L4; else goto _L11
_L11:
            if (!s1.equalsIgnoreCase("td"))
            {
                continue; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            int k1 = s.length();
            boolean flag5;
            if (k1 > 0 && s.charAt(k1 - 1) == ' ')
            {
                flag5 = true;
            } else
            {
                flag5 = false;
            }
            if (flag5) goto _L4; else goto _L12
_L12:
            s.append(' ');
            return;
            if (IGNORED_TAGS.contains(s1.toLowerCase())) goto _L4; else goto _L13
_L13:
            LogUtils.d(NoteHtmlConverter.TAG, "Unsupported endTag: %s", new Object[] {
                s1
            });
            return;
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
            int i = 1;
            if (s1.equalsIgnoreCase("br") || s1.equalsIgnoreCase("p")) goto _L2; else goto _L1
_L1:
            if (!s1.equalsIgnoreCase("ul")) goto _L4; else goto _L3
_L3:
            s = stringBuilder;
            s1 = new UnorderedList();
            i = s.length();
            s.setSpan(s1, i, i, 17);
_L2:
            return;
_L4:
            if (s1.equalsIgnoreCase("ol"))
            {
                s = stringBuilder;
                s1 = new OrderedList();
                i = s.length();
                s.setSpan(s1, i, i, 17);
                return;
            }
            if (!s1.equalsIgnoreCase("li"))
            {
                break; /* Loop/switch isn't completed */
            }
            s1 = stringBuilder;
            int l;
            if (!TextUtils.isEmpty(s1))
            {
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
                    s1.append('\n');
                }
            }
            l = ((List[])s1.getSpans(0, s1.length(), com/google/android/calendar/newapi/segment/note/NoteHtmlConverter$HtmlToPlainTextConverter$List)).length * 4;
            if (" " == null)
            {
                throw new NullPointerException();
            }
            if (l <= 1)
            {
                if (l >= 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    throw new IllegalArgumentException(Strings.lenientFormat("invalid count: %s", new Object[] {
                        Integer.valueOf(l)
                    }));
                }
                if (l == 0)
                {
                    s = "";
                } else
                {
                    s = " ";
                }
            } else
            {
                i = " ".length();
                long l1 = (long)i * (long)l;
                l = (int)l1;
                if ((long)l != l1)
                {
                    throw new ArrayIndexOutOfBoundsException((new StringBuilder(51)).append("Required array size too large: ").append(l1).toString());
                }
                s = new char[l];
                " ".getChars(0, i, s, 0);
                for (; i < l - i; i <<= 1)
                {
                    System.arraycopy(s, 0, s, i, i);
                }

                System.arraycopy(s, 0, s, i, l - i);
                s = new String(s);
            }
            s1.append(s);
            s = ((String) (s1.getSpans(0, s1.length(), com/google/android/calendar/newapi/segment/note/NoteHtmlConverter$HtmlToPlainTextConverter$List)));
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
                s.itemCount = ((List) (s)).itemCount + 1;
                if (s instanceof OrderedList)
                {
                    s1.append(String.format(Locale.getDefault(), "%d.", new Object[] {
                        Integer.valueOf(((List) (s)).itemCount)
                    }));
                } else
                {
                    s1.append("*");
                }
                s1.append(" ");
                return;
            }
            if (true) goto _L2; else goto _L5
_L5:
            if (!s1.equalsIgnoreCase("div"))
            {
                break; /* Loop/switch isn't completed */
            }
            s = stringBuilder;
            if (!TextUtils.isEmpty(s))
            {
                int i1 = s.length();
                if (i1 <= 0 || s.charAt(i1 - 1) != '\n')
                {
                    i = 0;
                }
                if (i == 0)
                {
                    s.append('\n');
                    return;
                }
            }
            if (true) goto _L2; else goto _L6
_L6:
            if (s1.equalsIgnoreCase("a"))
            {
                s = stringBuilder;
                s1 = attributes.getValue("", "href");
                int j = s.length();
                s.setSpan(new Href(s1), j, j, 17);
                return;
            }
            if (s1.equalsIgnoreCase("img"))
            {
                s = stringBuilder;
                s1 = attributes.getValue("", "src");
                int k = s.length();
                s.setSpan(new Img(s1), k, k, 17);
                return;
            }
            if ((s1.length() != 2 || Character.toLowerCase(s1.charAt(0)) != 'h' || !Character.isDigit(s1.charAt(1))) && !s1.equalsIgnoreCase("tr") && !s1.equalsIgnoreCase("th") && !s1.equalsIgnoreCase("td") && !IGNORED_TAGS.contains(s1.toLowerCase()))
            {
                LogUtils.d(NoteHtmlConverter.TAG, "Unsupported startTag: %s", new Object[] {
                    s1
                });
                return;
            }
            if (true) goto _L2; else goto _L7
_L7:
        }

        public final void startPrefixMapping(String s, String s1)
            throws SAXException
        {
        }


        HtmlToPlainTextConverter(String s, Parser parser)
        {
            source = s;
            reader = parser;
        }
    }

    static class HtmlToPlainTextConverter.Href
    {

        public String href;

        HtmlToPlainTextConverter.Href(String s)
        {
            href = s;
        }
    }

    static class HtmlToPlainTextConverter.Img
    {

        public String src;

        HtmlToPlainTextConverter.Img(String s)
        {
            src = s;
        }
    }

    static class HtmlToPlainTextConverter.List
    {

        public int itemCount;

        HtmlToPlainTextConverter.List()
        {
        }
    }

    static class HtmlToPlainTextConverter.OrderedList extends HtmlToPlainTextConverter.List
    {

        HtmlToPlainTextConverter.OrderedList()
        {
        }
    }

    static class HtmlToPlainTextConverter.UnorderedList extends HtmlToPlainTextConverter.List
    {

        HtmlToPlainTextConverter.UnorderedList()
        {
        }
    }


    private static final Pattern HTML_PATTERN;
    private static final HTMLSchema HTML_SCHEMA = new HTMLSchema();
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/newapi/segment/note/NoteHtmlConverter);

    public NoteHtmlConverter()
    {
    }

    public static boolean isHtml(String s)
    {
        return !TextUtils.isEmpty(s) && HTML_PATTERN.matcher(s).find();
    }

    public static CharSequence toFormattedText(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return s;
        } else
        {
            return Html.fromHtml(s.trim().replaceAll("(\r\n|\n\r|\r|\n)", "<br />").replaceAll("<((?i)(http|https):\\/\\/[^\\s\\\"\\>]*)>", "&lt;$1&gt;"), null);
        }
    }

    static String toPlainText(String s)
    {
        Parser parser;
        if (TextUtils.isEmpty(s))
        {
            return s;
        }
        parser = new Parser();
        parser.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", HTML_SCHEMA);
        return (new HtmlToPlainTextConverter(s.trim().replaceAll("(\r\n|\n\r|\r|\n)", "<br />").replaceAll("<((?i)(http|https):\\/\\/[^\\s\\\"\\>]*)>", "&lt;$1&gt;"), parser)).convert();
        s;
_L2:
        throw new RuntimeException(s);
        s;
        if (true) goto _L2; else goto _L1
_L1:
    }

    static 
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("&lt;|&gt;");
        String s;
        for (Iterator iterator = FluentIterable.concatNoDefensiveCopy(new Iterable[] {
    HtmlToPlainTextConverter.PROCESSED_TAGS, HtmlToPlainTextConverter.IGNORED_TAGS
}).iterator(); iterator.hasNext(); stringbuilder.append("</").append(s).append(">"))
        {
            s = (String)iterator.next();
            stringbuilder.append("|");
            stringbuilder.append("<").append(s).append(">");
            stringbuilder.append("|");
        }

        HTML_PATTERN = Pattern.compile(stringbuilder.toString(), 2);
    }
}
