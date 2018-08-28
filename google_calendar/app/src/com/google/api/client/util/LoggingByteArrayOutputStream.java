// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class LoggingByteArrayOutputStream extends ByteArrayOutputStream
{

    private int bytesWritten;
    private boolean closed;
    private final Logger logger;
    private final Level loggingLevel;
    private final int maximumBytesToLog;

    public LoggingByteArrayOutputStream(Logger logger1, Level level, int i)
    {
        if (logger1 == null)
        {
            throw new NullPointerException();
        }
        logger = (Logger)logger1;
        if (level == null)
        {
            throw new NullPointerException();
        }
        loggingLevel = (Level)level;
        boolean flag;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException();
        } else
        {
            maximumBytesToLog = i;
            return;
        }
    }

    private static void appendBytes(StringBuilder stringbuilder, int i)
    {
        if (i == 1)
        {
            stringbuilder.append("1 byte");
            return;
        } else
        {
            stringbuilder.append(NumberFormat.getInstance().format(i)).append(" bytes");
            return;
        }
    }

    public final void close()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        if (!closed)
        {
            if (bytesWritten != 0)
            {
                StringBuilder stringbuilder = new StringBuilder("Total: ");
                appendBytes(stringbuilder, bytesWritten);
                if (count != 0 && count < bytesWritten)
                {
                    stringbuilder.append(" (logging first ");
                    appendBytes(stringbuilder, count);
                    stringbuilder.append(")");
                }
                logger.logp(Level.CONFIG, "com.google.api.client.util.LoggingByteArrayOutputStream", "close", stringbuilder.toString());
                if (count != 0)
                {
                    logger.logp(loggingLevel, "com.google.api.client.util.LoggingByteArrayOutputStream", "close", toString("UTF-8").replaceAll("[\\x00-\\x09\\x0B\\x0C\\x0E-\\x1F\\x7F]", " "));
                }
            }
            closed = true;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void write(int i)
    {
        this;
        JVM INSTR monitorenter ;
        Exception exception;
        boolean flag;
        if (!closed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        throw new IllegalArgumentException();
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        bytesWritten = bytesWritten + 1;
        if (count < maximumBytesToLog)
        {
            super.write(i);
        }
        this;
        JVM INSTR monitorexit ;
    }

    public final void write(byte abyte0[], int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag;
        if (!closed)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        throw new IllegalArgumentException();
        abyte0;
        this;
        JVM INSTR monitorexit ;
        throw abyte0;
        int l;
        bytesWritten = bytesWritten + j;
        if (count >= maximumBytesToLog)
        {
            break MISSING_BLOCK_LABEL_96;
        }
        l = count + j;
        int k = j;
        if (l > maximumBytesToLog)
        {
            k = j + (maximumBytesToLog - l);
        }
        super.write(abyte0, i, k);
        this;
        JVM INSTR monitorexit ;
    }
}
