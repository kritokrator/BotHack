package bothack.classes;

import javax.xml.bind.annotation.*;

/**
 * Created by administrator on 10/29/14.
 */
@XmlRootElement
public class Coordinate {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate that = (Coordinate) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    private int x;
    private int y;

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(){
        this.x = 0;
        this.y = 0;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    @XmlElement
    public int getX(){
        return this.x;
    }
    @XmlElement
    public int getY(){
        return this.y;
    }
}
