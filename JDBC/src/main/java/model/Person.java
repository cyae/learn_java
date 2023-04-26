package model;

import java.sql.Date;
import java.util.Objects;

public final class Person {
    private int Id;
    private String Name;
    private Date Birth;

    public Person() {
    }

    public Person(int Id, String Name, Date Birth) {
        this.Id = Id;
        this.Name = Name;
        this.Birth = Birth;
    }

    public int Id() {
        return Id;
    }

    public String Name() {
        return Name;
    }

    public Date Birth() {
        return Birth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Person) obj;
        return this.Id == that.Id &&
                Objects.equals(this.Name, that.Name) &&
                Objects.equals(this.Birth, that.Birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Name, Birth);
    }

    @Override
    public String toString() {
        return "Person[" +
                "Id=" + Id + ", " +
                "Name=" + Name + ", " +
                "Birth=" + Birth + ']';
    }

}