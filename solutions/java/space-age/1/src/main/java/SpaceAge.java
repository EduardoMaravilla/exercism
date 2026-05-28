class SpaceAge {
     private double seconds;
    SpaceAge(double seconds) {
        this.seconds=seconds;
    }

    double getSeconds() {
       double valor=seconds/(31557600);
        return valor;
    }

    double onEarth() {
        return getSeconds();
    }

    double onMercury() {
        double mer=getSeconds()/0.2408467;
        return mer;
    }

    double onVenus() {
        double ven=getSeconds()/0.61519726;
        return ven;
    }

    double onMars() {
        double mar=getSeconds()/1.8808158;
        return mar;
    }

    double onJupiter() {
        double jup=getSeconds()/11.862615;
        return jup;
    }

    double onSaturn() {
        double sat=getSeconds()/29.447498;
        return sat;
    }

    double onUranus() {
        double ura=getSeconds()/84.016846;
        return ura;
    }

    double onNeptune() {
        double nep=getSeconds()/164.79132;
        return nep;
    }

}
