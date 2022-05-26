package org.genspark;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Phone {
    @Value("#{'123123123'}")
    private String mob;

    public Phone() {

    }

    public Phone(String mob) {
        this.mob = mob;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    @Override
    public String toString(){
        return mob;
    }
}
