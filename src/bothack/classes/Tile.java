package bothack.classes;

import bothack.interfaces.Color;

/**
 * Created by administrator on 10/29/14.
 */
public class Tile {
    private Character symbol;
    private Color color;
    private Long glyph;

    public Tile(Character symbol, Color color, Long glyph){
        this.symbol = symbol;
        this.color = color;
        this.glyph = glyph;
    }
    public Tile(String symbol, String color, String glyph) {
        this.symbol = (char) Integer.parseInt(symbol);
        this.glyph = Long.parseLong(glyph);
        for (Color c : Color.values()) {
            if (c.getIndex().equals(color)) {
                this.color = c;
            }
        }
    }
    public void setSymbol(char c){
        symbol = c;
    }
    public void setSymbol(int c){
        symbol = (char)c;
    }
    public void setSymbol(String s){
        symbol = (char)Integer.parseInt(s);
    }
    public void setColor(Color c){
        color = c;
    }
    public void setColor(String s){
        for (Color c : Color.values()) {
            if (c.getIndex().equals(s)) {
                this.color = c;
            }
        }
    }
    public void setGlyph(Long g){
        glyph = g;
    }
    public void setGlyph(String s){
        glyph = Long.parseLong(s);
    }
    public Character getSymbol(){
        return symbol;
    }
    public Color getColor(){
        return color;
    }
    public Long getGlyph(){
        return glyph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (color != tile.color) return false;
        if (glyph != null ? !glyph.equals(tile.glyph) : tile.glyph != null) return false;
        if (!symbol.equals(tile.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + (glyph != null ? glyph.hashCode() : 0);
        return result;
    }
}