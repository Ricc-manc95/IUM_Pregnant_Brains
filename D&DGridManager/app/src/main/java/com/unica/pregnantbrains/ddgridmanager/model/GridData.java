package com.unica.pregnantbrains.ddgridmanager.model;

import com.unica.pregnantbrains.ddgridmanager.model.primitives.Line;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.Token;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * This is a data class that collects everything that makes up the current map state,
 * including drawing, tokens, and the current view transformation so that this can all
 * be stored independently of the view.
 * @author Tim
 *
 */
public class GridData {
    private GridData() {

    }
    private static GridData instance;

    public static GridData getInstance() {
        if (instance == null) {
            clear();
        }
        return instance;
    }

    public static void clear() {
        instance = new GridData();
    }

    public static void loadFromStream(InputStream input) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = new ObjectInputStream(input);
        instance = (GridData) objectIn.readObject();
        objectIn.close();
    }

    public static void saveToStream(OutputStream output) throws IOException {
        ObjectOutputStream objectOut = new ObjectOutputStream(output);
        objectOut.writeObject(instance);
        objectOut.close();
    }

    public CoordinateTransformer transformer = new CoordinateTransformer(0,0,64);
    public LineCollection mBackgroundLines = new LineCollection();
    public LineCollection mAnnotationLines = new LineCollection();
    public TokenCollection tokens = new TokenCollection();
    public Grid grid = new Grid();

    public BoundingRectangle getBoundingRectangle() {
        BoundingRectangle r = new BoundingRectangle();

        for (Line l : mBackgroundLines.lines) {
            r.updateBounds(l.getBoundingRectangle());
        }

        for (Line l : mAnnotationLines.lines) {
            r.updateBounds(l.getBoundingRectangle());
        }

        for (Token t: tokens.getTokens()) {
            r.updateBounds(t.getBoundingRectangle());
        }

        return r;
    }

    public void zoomToFit(int widthPixels, int heightPixels) {
        BoundingRectangle r = getBoundingRectangle();

        float scaleFactorX = widthPixels / r.getWidth();
        float scaleFactorY = heightPixels/ r.getHeight();
        //Find the optimal scale factor, and add a border.
        float scaleFactor = Math.min(scaleFactorX, scaleFactorY)/ 1.01f;

        this.transformer.setZoom(scaleFactor);
        this.transformer.setOriginInWorldSpace(r.getXMin(), r.getYMin());
    }
}
