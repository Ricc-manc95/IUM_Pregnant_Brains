// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.graphics.drawable;

import android.support.v4.graphics.PathParser;

class mNodes
{

    public int mChangingConfigurations;
    public android.support.v4.graphics.rams mNodes[];
    public String mPathName;

    public android.support.v4.graphics.VPath[] getPathData()
    {
        return mNodes;
    }

    public String getPathName()
    {
        return mPathName;
    }

    public boolean isClipPath()
    {
        return false;
    }

    public void setPathData(android.support.v4.graphics.VPath avpath[])
    {
        if (!PathParser.canMorph(mNodes, avpath))
        {
            mNodes = PathParser.deepCopyNodes(avpath);
        } else
        {
            android.support.v4.graphics.VPath avpath1[] = mNodes;
            int i = 0;
            while (i < avpath.length) 
            {
                avpath1[i].pe = avpath[i].pe;
                for (int j = 0; j < avpath[i].rams.length; j++)
                {
                    avpath1[i].rams[j] = avpath[i].rams[j];
                }

                i++;
            }
        }
    }

    public ()
    {
        mNodes = null;
    }

    public mNodes(mNodes mnodes)
    {
        mNodes = null;
        mPathName = mnodes.mPathName;
        mChangingConfigurations = mnodes.mChangingConfigurations;
        mNodes = PathParser.deepCopyNodes(mnodes.mNodes);
    }
}
