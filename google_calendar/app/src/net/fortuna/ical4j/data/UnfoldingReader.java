// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.fortuna.ical4j.data;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.Arrays;
import net.fortuna.ical4j.util.CompatibilityHints;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UnfoldingReader extends PushbackReader
{

    private static final char DEFAULT_FOLD_PATTERN_1[] = {
        '\r', '\n', ' '
    };
    private static final char DEFAULT_FOLD_PATTERN_2[] = {
        '\r', '\n', '\t'
    };
    private static final char RELAXED_FOLD_PATTERN_1[] = {
        '\n', ' '
    };
    private static final char RELAXED_FOLD_PATTERN_2[] = {
        '\n', '\t'
    };
    private char buffers[][];
    public int linesUnfolded;
    private Log log;
    private int maxPatternLength;
    private char patterns[][];

    public UnfoldingReader(Reader reader)
    {
        this(reader, DEFAULT_FOLD_PATTERN_1.length, CompatibilityHints.isHintEnabled("ical4j.unfolding.relaxed"));
    }

    private UnfoldingReader(Reader reader, int i, boolean flag)
    {
        boolean flag1 = false;
        super(reader, i);
        log = LogFactory.getLog(net/fortuna/ical4j/data/UnfoldingReader);
        maxPatternLength = 0;
        if (flag)
        {
            patterns = new char[4][];
            patterns[0] = DEFAULT_FOLD_PATTERN_1;
            patterns[1] = DEFAULT_FOLD_PATTERN_2;
            patterns[2] = RELAXED_FOLD_PATTERN_1;
            patterns[3] = RELAXED_FOLD_PATTERN_2;
        } else
        {
            patterns = new char[2][];
            patterns[0] = DEFAULT_FOLD_PATTERN_1;
            patterns[1] = DEFAULT_FOLD_PATTERN_2;
        }
        buffers = new char[patterns.length][];
        for (i = ((flag1) ? 1 : 0); i < patterns.length; i++)
        {
            buffers[i] = new char[patterns[i].length];
            maxPatternLength = Math.max(maxPatternLength, patterns[i].length);
        }

    }

    private final void unfold()
        throws IOException
    {
        boolean flag;
        do
        {
            int i = 0;
            flag = false;
            while (i < buffers.length) 
            {
                int j = 0;
                do
                {
                    if (j >= buffers[i].length)
                    {
                        break;
                    }
                    int k = super.read(buffers[i], j, buffers[i].length - j);
                    if (k < 0)
                    {
                        break;
                    }
                    j += k;
                } while (true);
                boolean flag1 = flag;
                if (j > 0)
                {
                    if (!Arrays.equals(patterns[i], buffers[i]))
                    {
                        unread(buffers[i], 0, j);
                        flag1 = flag;
                    } else
                    {
                        if (log.isTraceEnabled())
                        {
                            log.trace("Unfolding...");
                        }
                        linesUnfolded = linesUnfolded + 1;
                        flag1 = true;
                    }
                }
                i++;
                flag = flag1;
            }
        } while (flag);
    }

    public final int read()
        throws IOException
    {
        boolean flag1 = false;
        int j = super.read();
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (i < patterns.length)
                {
                    if (j != patterns[i][0])
                    {
                        break label0;
                    }
                    flag = true;
                }
                if (!flag)
                {
                    return j;
                } else
                {
                    unread(j);
                    unfold();
                    return super.read();
                }
            }
            i++;
        } while (true);
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        boolean flag1 = false;
        int l = super.read(ac, i, j);
        j = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if (j < patterns.length)
                {
                    if (l <= 0 || ac[0] != patterns[j][0])
                    {
                        break label0;
                    }
                    flag = true;
                }
                int k;
                if (!flag)
                {
                    return l;
                } else
                {
                    unread(ac, i, l);
                    unfold();
                    return super.read(ac, i, maxPatternLength);
                }
            }
            for (k = 0; k < l; k++)
            {
                if (ac[k] == patterns[j][0])
                {
                    unread(ac, k, l - k);
                    return k;
                }
            }

            j++;
        } while (true);
    }

}
