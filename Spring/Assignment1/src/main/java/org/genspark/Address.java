package org.genspark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("add")
public class Address {
    @Value("#{'New York'}")
    private String city;
    @Value("#{'New York'}")
    private String state;
    @Value("#{'United States'}")
    private String country;
    @Value("#{'12012'}")
    private String zipcode;

    public Address() {

    }

    public Address(String city, String state, String country, String zipcode) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString(){
        return city + " " + state + " " + country + " " + zipcode;
    }
}
