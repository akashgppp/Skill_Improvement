package com.facial.abhyudaya.technosys.pvt.attendance.facework.env;

import java.io.Serializable;

public class Size implements Comparable<Size>, Serializable {

    public static final long serialVersionUID = 7689808733290872361L;

    public final int width;
    public final int height;

    public Size(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public static final String dimensionsAsString(final int width, final int height) {
        return width + "x" + height;
    }

    @Override
    public int compareTo(final Size other) {
        return width * height - other.width * other.height;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof Size)) {
            return false;
        }

        final Size otherSize = (Size) other;
        return (width == otherSize.width && height == otherSize.height);
    }

    @Override
    public int hashCode() {
        return width * 32713 + height;
    }

    @Override
    public String toString() {
        return dimensionsAsString(width, height);
    }

}