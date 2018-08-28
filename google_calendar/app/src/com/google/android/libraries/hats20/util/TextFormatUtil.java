// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TextFormatUtil
{

    private static Pattern boldRegexPattern = Pattern.compile("(?<=(['\"]|\\s|^))(\\*(\\w|[.!?,'\"#$*])+\\*)(?=([.!?,'\"]|\\s|$))");
    private static Pattern italicRegexPattern = Pattern.compile("(?<=(['\"]|\\s|^))(_(\\w|[.!?,'\"#$*])+_)(?=([.!?,'\"]|\\s|$))");

    public static Spannable format(String s)
    {
        if (TextUtils.isEmpty(s))
        {
            return new SpannableString("");
        }
        if (!s.contains(Character.toString('*')) && !s.contains(Character.toString('_')))
        {
            return new SpannableString(s);
        }
        SpannableStringBuilder spannablestringbuilder = new SpannableStringBuilder();
        Object obj = italicRegexPattern.matcher(s);
        Matcher matcher = boldRegexPattern.matcher(s);
        PriorityQueue priorityqueue = new PriorityQueue();
        ArrayList arraylist = new ArrayList();
        for (; ((Matcher) (obj)).find(); arraylist.add(new WordMatch(((Matcher) (obj))))) { }
        priorityqueue.addAll(arraylist);
        obj = new ArrayList();
        for (; matcher.find(); ((List) (obj)).add(new WordMatch(matcher))) { }
        priorityqueue.addAll(((java.util.Collection) (obj)));
        int i = 0;
        do
        {
            while (i < s.length()) 
            {
                boolean flag;
                if (!priorityqueue.isEmpty() && i == ((WordMatch)priorityqueue.peek()).start)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    WordMatch wordmatch = (WordMatch)priorityqueue.remove();
                    i = wordmatch.word.charAt(0);
                    if (i != 42 && i != 95)
                    {
                        spannablestringbuilder.append(wordmatch.word);
                    } else
                    {
                        spannablestringbuilder.append(wordmatch.word.substring(1, wordmatch.word.length() - 1));
                        int j = wordmatch.end;
                        int k = wordmatch.start;
                        if (i == 42)
                        {
                            i = 1;
                        } else
                        {
                            i = 2;
                        }
                        spannablestringbuilder.setSpan(new StyleSpan(i), spannablestringbuilder.length() - (j - k - 2), spannablestringbuilder.length(), 33);
                    }
                    i = wordmatch.end - 1;
                } else
                {
                    spannablestringbuilder.append(s.charAt(i));
                }
                i++;
            }
            return spannablestringbuilder;
        } while (true);
    }


    private class WordMatch
        implements Comparable
    {

        public final int end;
        public final int start;
        public final String word;

        public final int compareTo(Object obj)
        {
            obj = (WordMatch)obj;
            return Integer.compare(start, ((WordMatch) (obj)).start);
        }

        WordMatch(Matcher matcher)
        {
            start = matcher.start();
            end = matcher.end();
            word = matcher.group();
        }
    }

}
