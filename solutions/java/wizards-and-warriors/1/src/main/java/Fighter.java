abstract class Fighter {

    boolean isVulnerable() {
        return false;
    }

    abstract int damagePoints(Fighter fighter);
}

class Warrior extends Fighter {

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    int damagePoints(Fighter fighter) {
        if (fighter instanceof Wizard && fighter.isVulnerable()) {
            return 10;
        } else {
            return 6;
        }
    }
}

class Wizard extends Fighter {
    private boolean preparar = false;

    @Override
    boolean isVulnerable() {
        return !preparar;
    }

    @Override
    int damagePoints(Fighter fighter) {
        if (preparar) {
            return 12;
        } else {
            return 3;
        }
    }

    void prepareSpell() {
        preparar = true;
    }

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }
}
