public class Fighter {
    public Fighter() {        
    }
    boolean isVulnerable() {
        return false;
    }

    int getDamagePoints(Fighter fighter){
        return 0;
    }

}

class Warrior extends Fighter {
    
    @Override
    boolean isVulnerable() {
        return false;
    }

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    int getDamagePoints(Fighter fighter) {
        return fighter.isVulnerable()? 10 : 6 ;
    }
}

class Wizard extends Fighter {
    private boolean prepareSpell= false;
    
    @Override
    boolean isVulnerable() {
        return !prepareSpell;
    }

    @Override
    int getDamagePoints(Fighter fighter) {
        return prepareSpell? 12 : 3;
    }

    void prepareSpell() {
        this.prepareSpell = true;
    }

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }

}
