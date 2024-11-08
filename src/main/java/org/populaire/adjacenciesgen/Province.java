package org.populaire.adjacenciesgen;


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

    @Override
    public String toString() {
        return "{ id : " + this.id + ", color " + this.color + " }";
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
