package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
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
            if (Util.distance(p, transformer.worldSpaceToScreenSpace(tokens.get(i).location)) < transformer.worldSpaceToScreenSpace(tokens.get(i).size/2)) {
                return tokens.get(i);
            }
        }
        return null;
    }

    public void addToken(Token t) {
        tokens.add(t);
    }

    public void clear() {
        tokens.clear();
    }
    public void remove(Token t) {
        tokens.remove(t);
    }

    //NOTE: Everything in grid space.
    public void placeTokenNearby(Token t, PointF point, Grid grid) {
        int attemptedDistance = 0;
        // Continually increment the attempted distance until an open grid space is found.  This is guaranteed to succeed.
        // Note that there are some inefficiencies here (the center point is tried four times, each corner of a square is tried
        // twice, etc).  I don't care.  This runs fast enough for reasonable token collections on screen.
        point = grid.getNearestSnapPoint(point, t.size);
        while (true) {
            // Go clockwise around the size of a square centered on the originally attempted point and
            // with sized of length attemptedDistance*2

            //Across the top
            for (int i = -attemptedDistance + 1; i <= attemptedDistance; ++i) { // The -attemptedDistance + 1 ensures a nice spiral pattern
                if (tryToPlaceHere(t, new PointF(point.x + i, point.y - attemptedDistance))) return;
            }

            //Down the right
            for (int i = -attemptedDistance; i <= attemptedDistance; ++i) {
                if (tryToPlaceHere(t, new PointF(point.x + attemptedDistance, point.y + i))) return;
            }
            //Across the bottom
            for (int i = attemptedDistance; i >= -attemptedDistance; --i) {
                if (tryToPlaceHere(t, new PointF(point.x + i, point.y + attemptedDistance))) return;
            }

            //Up the left
            for (int i = attemptedDistance; i >= -attemptedDistance; --i) {
                if (tryToPlaceHere(t, new PointF(point.x - attemptedDistance, point.y + i))) return;
            }
            attemptedDistance++;
        }
    }

    private boolean tryToPlaceHere(Token t, PointF point) {
        if (isLocationUnoccupied(point, t.size / 2)) {
            t.location = point;
            return true;
        }
        return false;
    }

    private boolean isLocationUnoccupied(PointF point, double radius) {
        for (Token t : tokens) {
            if (Util.distance(point, t.location) < radius + t.size / 2) {
                return false;
            }
        }
        return true;
    }
    public void drawAllTokens(Canvas canvas, CoordinateTransformer transformer) {
        for (int i = 0; i < tokens.size(); ++i){
            tokens.get(i).draw(canvas, transformer);
        }

    }
}