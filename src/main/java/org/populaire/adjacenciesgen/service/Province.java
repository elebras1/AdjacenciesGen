package org.populaire.adjacenciesgen.service;


import java.awt.*;

public class Province {
    private int id;
    private Color color;

    public Province(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return this.id;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }

        if(!(o instanceof Province p)) {
            return false;
        }

        return this.color.equals(p.color);
    }
}
