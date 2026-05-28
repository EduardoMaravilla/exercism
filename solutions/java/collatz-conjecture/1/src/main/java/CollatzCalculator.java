public class CollatzCalculator {

    int computeStepCount(int start) {
        if (start<=0) {
            throw new IllegalArgumentException("Only natural numbers are allowed");
        }
        int num=0;
        while (start>=2) {            
            if (start%2==0) {
                start /=2;
                num++;
            }else if(start%2!=0){
               start=start*3+1;
               num++;
            }   
        }
        
       return num;
    }

}

