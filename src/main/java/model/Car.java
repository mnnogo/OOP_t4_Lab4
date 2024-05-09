package model;

import java.util.Objects;

public class Car
{
    private String brand;
    private String model;
    private String year;
    private String mileage;
    private String color;
    private String price;

    public Car(String brand, String model, String year, String mileage, String color, String price)
    {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.color = color;
        this.price = price;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public String getYear()
    {
        return year;
    }

    public String getMileage()
    {
        return mileage;
    }

    public String getColor()
    {
        return color;
    }

    public String getPrice()
    {
        return price;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!brand.equals(car.brand)) return false;
        if (!model.equals(car.model)) return false;
        if (!year.equals(car.year)) return false;
        if (!mileage.equals(car.mileage)) return false;
        if (!color.equals(car.color)) return false;
        return price.equals(car.price);
    }

    @Override
    public int hashCode()
    {
        int result = brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + mileage.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }
}
