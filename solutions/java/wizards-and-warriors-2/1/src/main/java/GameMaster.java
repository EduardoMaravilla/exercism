public class GameMaster {

    public String describe(Character character){
      return "You're a level "+character.getLevel()
              +" "+character.getCharacterClass()
              +" with "+character.getHitPoints()
              +" hit points.";
    }

    public String describe(Destination destination){
      return "You've arrived at "+destination.getName()
              +", which has "+destination.getInhabitants()+" inhabitants.";
    }

    public String describe(TravelMethod travelMethod){
        StringBuilder sb = new StringBuilder("You're traveling to your destination ");
        if(travelMethod.equals(TravelMethod.HORSEBACK)){
            sb.append("on ");
        } else if (travelMethod.equals(TravelMethod.WALKING)) {
            sb.append("by ");
        }
        sb.append(travelMethod.name().toLowerCase()).append(".");
        return sb.toString();
    }

   public String describe(Character character,Destination destination,TravelMethod travelMethod){
        return describe(character)+" "+describe(travelMethod)+" "+describe(destination);
    }

    public String describe(Character character,Destination destination){
        return describe(character,destination,TravelMethod.WALKING);
    }
}
