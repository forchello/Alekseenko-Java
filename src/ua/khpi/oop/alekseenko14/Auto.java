package ua.khpi.oop.alekseenko14;

public class Auto<Y extends Comparable<Y>, F extends Comparable<F>, P extends Comparable<P>> implements java.io.Serializable {
    private String model;
    private Y releaseYear;

    private F urbanFuel;
    private F suburbanFuel;

    private boolean technicalCondition;
    private P price;

    // КОНСТРУКТОР ------------

    public Auto () {
        model = "";
        releaseYear = null;
        urbanFuel = null;
        suburbanFuel = null;
        technicalCondition = false;
        price = null;
    }

    public Auto ( String car_name )  {
        model = car_name;
        releaseYear = null;
        urbanFuel = null;
        suburbanFuel = null;
        technicalCondition = false;
        price = null;
    }

    public Auto ( String car_name, Y car_release_year, F urban, F suburban, boolean car_state, P car_price )  {
        model = car_name;
        releaseYear = car_release_year;
        urbanFuel = urban;
        suburbanFuel = suburban;
        technicalCondition = car_state;
        price = car_price;
    }

    // МЕТОДЫ ДОСТУПА ---------

    public String getModel () {
        return model;
    }
    public void setModel ( String model ) {
        this.model = model;
    }

    // ------------------------

    public Y getReleaseYear () {
        return releaseYear;
    }
    public void setReleaseYear ( Y releaseYear ) {
        this.releaseYear = releaseYear;
    }

    // ------------------------

    public F getUrbanFuel () {
        return urbanFuel;
    }
    public void setUrbanFuel ( F urbanFuel ) {
        this.urbanFuel = urbanFuel;
    }

    // ------------------------

    public F getSubUrbanFuel () {
        return suburbanFuel;
    }
    public void setSubUrbanFuel ( F suburbanFuel ) {
        this.suburbanFuel = suburbanFuel;
    }

    // ------------------------

    public boolean getTechnicalCondition(boolean b) {
        return technicalCondition;
    }
    public void setTechnicalCondition ( boolean technicalCondition ) {
        this.technicalCondition = technicalCondition;
    }

    // ------------------------

    public P getPrice () {
        return price;
    }
    public void setPrice ( P price ) {
        this.price = price;
    }

    // ------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ( o == null || getClass() != o.getClass() ) {
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
