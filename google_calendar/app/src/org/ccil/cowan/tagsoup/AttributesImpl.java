// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import org.xml.sax.Attributes;

public final class AttributesImpl
    implements Attributes
{

    public String data[];
    public int length;

    public AttributesImpl()
    {
        length = 0;
        data = null;
    }

    public AttributesImpl(Attributes attributes)
    {
        boolean flag = false;
        super();
        if (data != null)
        {
            for (int i = 0; i < length * 5; i++)
            {
                data[i] = null;
            }

        }
        length = 0;
        length = attributes.getLength();
        if (length > 0)
        {
            data = new String[length * 5];
            for (int j = ((flag) ? 1 : 0); j < length; j++)
            {
                data[j * 5] = attributes.getURI(j);
                data[j * 5 + 1] = attributes.getLocalName(j);
                data[j * 5 + 2] = attributes.getQName(j);
                data[j * 5 + 3] = attributes.getType(j);
                data[j * 5 + 4] = attributes.getValue(j);
            }

        }
    }

    public final int getIndex(String s)
    {
        int j = length;
        for (int i = 0; i < j * 5; i += 5)
        {
            if (data[i + 2].equals(s))
            {
                return i / 5;
            }
        }

        return -1;
    }

    public final int getIndex(String s, String s1)
    {
        int j = length;
        for (int i = 0; i < j * 5; i += 5)
        {
            if (data[i].equals(s) && data[i + 1].equals(s1))
            {
                return i / 5;
            }
        }

        return -1;
    }

    public final int getLength()
    {
        return length;
    }

    public final String getLocalName(int i)
    {
        if (i >= 0 && i < length)
        {
            return data[i * 5 + 1];
        } else
        {
            return null;
        }
    }

    public final String getQName(int i)
    {
        if (i >= 0 && i < length)
        {
            return data[i * 5 + 2];
        } else
        {
            return null;
        }
    }

    public final String getType(int i)
    {
        if (i >= 0 && i < length)
        {
            return data[i * 5 + 3];
        } else
        {
            return null;
        }
    }

    public final String getType(String s)
    {
        int j = length;
        for (int i = 0; i < j * 5; i += 5)
        {
            if (data[i + 2].equals(s))
            {
                return data[i + 3];
            }
        }

        return null;
    }

    public final String getType(String s, String s1)
    {
        int j = length;
        for (int i = 0; i < j * 5; i += 5)
        {
            if (data[i].equals(s) && data[i + 1].equals(s1))
            {
                return data[i + 3];
            }
        }

        return null;
    }

    public final String getURI(int i)
    {
        if (i >= 0 && i < length)
        {
            return data[i * 5];
        } else
        {
            return null;
        }
    }

    public final String getValue(int i)
    {
        if (i >= 0 && i < length)
        {
            return data[i * 5 + 4];
        } else
        {
            return null;
        }
    }

    public final String getValue(String s)
    {
        int j = length;
        for (int i = 0; i < j * 5; i += 5)
        {
            if (data[i + 2].equals(s))
            {
                return data[i + 4];
            }
        }

        return null;
    }

    public final String getValue(String s, String s1)
    {
        int j = length;
        for (int i = 0; i < j * 5; i += 5)
        {
            if (data[i].equals(s) && data[i + 1].equals(s1))
            {
                return data[i + 4];
            }
        }

        return null;
    }

    public final void removeAttribute(int i)
    {
        if (i >= 0 && i < length)
        {
            if (i < length - 1)
            {
                System.arraycopy(data, (i + 1) * 5, data, i * 5, (length - i - 1) * 5);
            }
            int j = (length - 1) * 5;
            String as[] = data;
            i = j + 1;
            as[j] = null;
            as = data;
            j = i + 1;
            as[i] = null;
            as = data;
            i = j + 1;
            as[j] = null;
            data[i] = null;
            data[i + 1] = null;
            length = length - 1;
            return;
        } else
        {
            throw new ArrayIndexOutOfBoundsException("Attempt to modify attribute at illegal index: " + i);
        }
    }
}
