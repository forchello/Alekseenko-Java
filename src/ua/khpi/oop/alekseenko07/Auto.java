package ua.khpi.oop.alekseenko07;

public class Auto implements java.io.Serializable {
    private String model;
    private int releaseYear;

    private int urbanFuel;
    private int suburbanFuel;

    private boolean technicalCondition;
    private int price;

    // КОНСТРУКТОР ------------

    public Auto () {
        model = "";
        releaseYear = 0;
        urbanFuel = 0;
        suburbanFuel = 0;
        technicalCondition = false;
        price = 0;
    }

    // МЕТОДЫ ДОСТУПА ---------

    public String getModel () {
        return model;
    }
    public void setModel ( String model ) {
        this.model = model;
    }

    // ------------------------

    public int getReleaseYear () {
        return releaseYear;
    }
    public void setReleaseYear ( int releaseYear ) {
        this.releaseYear = releaseYear;
    }

    // ------------------------

    public int getUrbanFuel () {
        return urbanFuel;
    }
    public void setUrbanFuel ( int urbanFuel ) {
        this.urbanFuel = urbanFuel;
    }

    // ------------------------

    public int getSubUrbanFuel () {
        return suburbanFuel;
    }
    public void setSubUrbanFuel ( int suburbanFuel ) {
        this.suburbanFuel = suburbanFuel;
    }

    // ------------------------

    public boolean getTechnicalCondition () {
        return technicalCondition;
    }
    public void setTechnicalCondition ( boolean technicalCondition ) {
        this.technicalCondition = technicalCondition;
    }

    // ------------------------

    public int getPrice () {
        return price;
    }
    public void setPrice ( int price ) {
        this.price = price;
    }

    // ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Auto that = (Auto) o;       // приводим объект к классу

        return model.equals(that.model) &&
                releaseYear == that.releaseYear &&
                urbanFuel == that.urbanFuel &&
                suburbanFuel == that.suburbanFuel &&
                technicalCondition == that.technicalCondition &&
                price == that.price;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", urbanFuel=" + urbanFuel +
                ", suburbanFuel=" + suburbanFuel +
                ", technicalCondition=" + technicalCondition +
                ", price=" + price +
                '}';
    }

    // JavaBeans - cтандарт написания классов, предусматривающий
    // создание полей, методов доступа к ним и перезапись 3 стандратных методов
    // обработки ( equals(), hashCode() и toString() )
}
