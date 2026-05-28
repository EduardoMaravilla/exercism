
class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        int[] Pajaros={0, 2, 5, 3, 7, 8, 4};
        return Pajaros;
    }

    public int getToday() {
        return birdsPerDay[6];
    }

    public void incrementTodaysCount() {
        birdsPerDay[6]++;
    }

    public boolean hasDayWithoutBirds() {
        int j=7;
        int control=0;
        for(int i=1; i<=j ; i++){
            if (birdsPerDay[i-1]==0){
                control++;
            }
        }
        if(control>0){
            return true;
        }else{
            return false;
        }
    }

    public int getCountForFirstDays(int numberOfDays) {
        int conteo=0;
        if(numberOfDays>=8){
            numberOfDays=7;
        }
        for(int i=1; i<=numberOfDays;i++){
            conteo=conteo+birdsPerDay[i-1];
        }
        return conteo;
    }

    public int getBusyDays() {
        int ocupado=0;
        for(int i=0; i<=6; i++){            
            if(birdsPerDay[i]>=5){
                ocupado++;
            }
        }
        return ocupado;
    }
}
