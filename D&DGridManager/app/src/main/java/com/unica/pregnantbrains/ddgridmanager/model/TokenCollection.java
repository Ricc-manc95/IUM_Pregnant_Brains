package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.PointF;

import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Util;

import java.util.ArrayList;
import java.util.List;

public class TokenCollection {
    private List<Token> tokens = new ArrayList<Token>();

    public List<Token> list() {
        return tokens;
    }

    public Token getTokenUnderPoint(PointF p, CoordinateTransformer transformer) {
        for (int i=0;i<tokens.size();++i) {
            if (Util.distance(p, transformer.worldSpaceToScreenSpace(tokens.get(i).location)) < transformer.worldSpaceToScreenSpace(tokens.get(i).radius)) {
                return tokens.get(i);
            }
        }
        return null;
    }

    public void addToken(Token t) {
        tokens.add(t);
    }
}
