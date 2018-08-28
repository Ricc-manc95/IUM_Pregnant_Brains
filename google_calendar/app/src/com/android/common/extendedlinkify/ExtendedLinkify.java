// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.common.extendedlinkify;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ExtendedLinkify
{

    private static final Pattern COORD_PATTERN = Pattern.compile("([-+NnSs](\\s)*)?[1-9]?[0-9](\260)(\\s)*([1-5]?[0-9]')?(\\s)*([1-5]?[0-9](\\.[0-9]+)?\")?((\\s)*[NnSs])?(\\s)*,(\\s)*([-+EeWw](\\s)*)?(1)?[0-9]?[0-9](\260)(\\s)*([1-5]?[0-9]')?(\\s)*([1-5]?[0-9](\\.[0-9]+)?\")?((\\s)*[EeWw])?|[+-]?[1-9]?[0-9](\\.[0-9]+)(\260)?(\\s)*,(\\s)*[+-]?(1)?[0-9]?[0-9](\\.[0-9]+)(\260)?");
    private static final Pattern WILD_CARD_PATTERN = Pattern.compile("^.*$");

    public static Spannable extendedLinkify(String s, boolean flag)
    {
        Object aobj[];
        SpannableString spannablestring1;
        spannablestring1 = SpannableString.valueOf(s);
        if (System.getProperty("user.region", "US").equals("US"))
        {
            break MISSING_BLOCK_LABEL_222;
        }
        Linkify.addLinks(spannablestring1, 15);
        aobj = (URLSpan[])spannablestring1.getSpans(0, spannablestring1.length(), android/text/style/URLSpan);
        if (aobj.length != 1) goto _L2; else goto _L1
_L1:
        int i;
        int j;
        int k;
        k = spannablestring1.getSpanStart(aobj[0]);
        j = spannablestring1.getSpanEnd(aobj[0]);
        i = 0;
_L13:
        if (i >= spannablestring1.length()) goto _L4; else goto _L3
_L3:
        if (Character.isWhitespace(spannablestring1.charAt(i))) goto _L6; else goto _L5
_L5:
        if (k > i) goto _L2; else goto _L7
_L7:
        i = spannablestring1.length() - 1;
_L14:
        if (i < 0) goto _L9; else goto _L8
_L8:
        if (Character.isWhitespace(spannablestring1.charAt(i))) goto _L11; else goto _L10
_L10:
        if (j < i + 1) goto _L2; else goto _L12
_L12:
        SpannableString spannablestring = spannablestring1;
_L16:
        return spannablestring;
_L6:
        i++;
          goto _L13
_L4:
        i = -1;
          goto _L5
_L11:
        i--;
          goto _L14
_L9:
        i = -1;
          goto _L10
_L2:
        Object obj;
        obj = SpannableString.valueOf(s);
        spannablestring = ((SpannableString) (obj));
        if (s.isEmpty()) goto _L16; else goto _L15
_L15:
        Linkify.addLinks(((Spannable) (obj)), WILD_CARD_PATTERN, "geo:0,0?q=");
        return ((Spannable) (obj));
        URLSpan aurlspan1[];
        int l;
        int j1;
        int i2;
        flag = Linkify.addLinks(spannablestring1, 11);
        URLSpan aurlspan[] = (URLSpan[])spannablestring1.getSpans(0, spannablestring1.length(), android/text/style/URLSpan);
        Matcher matcher = COORD_PATTERN.matcher(spannablestring1);
        j1 = 0;
        do
        {
            if (!matcher.find())
            {
                break;
            }
            i = matcher.start();
            j = matcher.end();
            if (!spanWillOverlap(spannablestring1, aurlspan, i, j))
            {
                String s1 = String.valueOf("geo:0,0?q=");
                String s2 = String.valueOf(matcher.group());
                if (s2.length() != 0)
                {
                    s1 = s1.concat(s2);
                } else
                {
                    s1 = new String(s1);
                }
                spannablestring1.setSpan(new URLSpan(s1), i, j, 33);
                j1++;
            }
        } while (true);
        aurlspan1 = (URLSpan[])spannablestring1.getSpans(0, spannablestring1.length(), android/text/style/URLSpan);
        aurlspan = new ArrayList();
        l = 0;
        i2 = (s.length() - 7) + 1;
        if (i2 >= 0) goto _L18; else goto _L17
_L17:
        s1 = new int[0];
_L42:
        j = 0;
        i = 0;
_L43:
        if (i >= s1.length / 2) goto _L20; else goto _L19
_L29:
        if ((i == 49 || j != 7 && j != 10) && (i != 49 || j != 11)) goto _L22; else goto _L21
_L21:
        j = i1;
_L32:
        i = l;
        if (j <= l) goto _L24; else goto _L23
_L23:
        aurlspan.add(Integer.valueOf(l));
        aurlspan.add(Integer.valueOf(j));
        l = j;
_L18:
        if (l >= i2) goto _L26; else goto _L25
_L25:
        for (; Character.isWhitespace(s.charAt(l)) && l < i2; l++) { }
        if (l == i2) goto _L26; else goto _L27
_L27:
        char c1;
        int k1;
        int l1;
        int j2;
        if (s.length() > l + 4 && s.subSequence(l, l + 4).toString().equalsIgnoreCase("tel:"))
        {
            i = l + 4;
        } else
        {
            i = l;
        }
        j2 = s.length();
        j = 0;
        k1 = 120;
        k = 0;
        i1 = i;
        i = k1;
_L35:
        if (i1 > j2) goto _L29; else goto _L28
_L28:
        if (i1 < j2)
        {
            c1 = s.charAt(i1);
        } else
        {
            c1 = '\033';
        }
        if (!Character.isDigit(c1)) goto _L31; else goto _L30
_L30:
        if (j == 0)
        {
            i = c1;
        }
        k1 = j + 1;
        if (k1 <= 11)
        {
            break MISSING_BLOCK_LABEL_1164;
        }
_L22:
        j = -1;
          goto _L32
_L31:
        if (!Character.isWhitespace(c1))
        {
            continue; /* Loop/switch isn't completed */
        }
        if ((i != 49 || j != 4) && j != 3) goto _L34; else goto _L33
_L33:
        k1 = 1;
        k = j;
        j = i;
        i = k1;
_L38:
        k1 = i1 + 1;
        i1 = k;
        k = i;
        i = j;
        j = i1;
        i1 = k1;
          goto _L35
_L34:
        if (i == 49 && j == 1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (k == 0) goto _L29; else goto _L36
_L36:
        if (i == 49 && j == 7)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j != 6) goto _L29; else goto _L37
_L37:
        k1 = i;
        l1 = j;
        i = k;
        j = k1;
        k = l1;
          goto _L38
        if ("()+-*#.".indexOf(c1) == -1) goto _L29; else goto _L39
_L39:
        k1 = i;
        l1 = j;
        i = k;
        j = k1;
        k = l1;
          goto _L38
_L24:
        l = i;
        if (Character.isWhitespace(s.charAt(i))) goto _L18; else goto _L40
_L40:
        l = i;
        if (i >= i2) goto _L18; else goto _L41
_L41:
        i++;
          goto _L24
_L26:
        s1 = new int[aurlspan.size()];
        i = aurlspan.size() - 1;
        while (i >= 0) 
        {
            s1[i] = ((Integer)aurlspan.get(i)).intValue();
            i--;
        }
          goto _L42
_L19:
        l = s1[i << 1];
        int i1 = s1[(i << 1) + 1];
        k = j;
        if (!spanWillOverlap(spannablestring1, aurlspan1, l, i1))
        {
            aurlspan = new StringBuilder();
            for (k = l; k < i1; k++)
            {
                char c = spannablestring1.charAt(k);
                if (c == '+' || Character.isDigit(c))
                {
                    aurlspan.append(c);
                }
            }

            aurlspan = String.valueOf(aurlspan.toString());
            if (aurlspan.length() != 0)
            {
                aurlspan = "tel:".concat(aurlspan);
            } else
            {
                aurlspan = new String("tel:");
            }
            spannablestring1.setSpan(new URLSpan(aurlspan), l, i1, 33);
            k = j + 1;
        }
        i++;
        j = k;
          goto _L43
_L20:
        if (!s.isEmpty() && !flag && j == 0 && j1 == 0)
        {
            Linkify.addLinks(spannablestring1, WILD_CARD_PATTERN, "geo:0,0?q=");
        }
        return spannablestring1;
        j = i;
        i = k;
        k = k1;
          goto _L38
    }

    private static boolean spanWillOverlap(Spannable spannable, URLSpan aurlspan[], int i, int j)
    {
        if (i != j)
        {
            int l = aurlspan.length;
            int k = 0;
            while (k < l) 
            {
                URLSpan urlspan = aurlspan[k];
                int i1 = spannable.getSpanStart(urlspan);
                int j1 = spannable.getSpanEnd(urlspan);
                if (i >= i1 && i < j1 || j > i1 && j <= j1)
                {
                    return true;
                }
                k++;
            }
        }
        return false;
    }

}
