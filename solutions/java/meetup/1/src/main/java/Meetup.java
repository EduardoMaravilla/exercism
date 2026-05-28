import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.YearMonth;


class Meetup{
    private int mes;
    private int año;
    Meetup(int mes,int año){
        this.mes=mes;
        this.año=año;
    }

    public LocalDate day(DayOfWeek diasemana, MeetupSchedule meetupSchedule) {
        YearMonth yearMonth = YearMonth.of(año, mes);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate fecha = LocalDate.of(año,mes,1);
        DayOfWeek dia = fecha.getDayOfWeek();

        switch(meetupSchedule){
            case FIRST:       
              for(int i=1;i<=daysInMonth;i++){
                  fecha = LocalDate.of(año,mes,i);
                  dia = fecha.getDayOfWeek();
                  if(dia==diasemana){
                    break;  
                  }
              }
                return fecha;
            case SECOND:
                int csecond=0;
              for(int i=1;i<=daysInMonth;i++){
                  fecha = LocalDate.of(año,mes,i);
                  dia = fecha.getDayOfWeek();
                  if(dia==diasemana){
                      if(csecond==1){
                         break; 
                      }
                      csecond++;
                  }
              }
                return fecha;
            case THIRD:
                int cthird=0;
              for(int i=1;i<=daysInMonth;i++){
                  fecha = LocalDate.of(año,mes,i);
                  dia = fecha.getDayOfWeek();
                  if(dia==diasemana){
                      if(cthird==2){
                         break; 
                      }
                      cthird++;
                  }
              }
                return fecha;
            case FOURTH:
                int cfourth=0;
              for(int i=1;i<=daysInMonth;i++){
                  fecha = LocalDate.of(año,mes,i);
                  dia = fecha.getDayOfWeek();
                  if(dia==diasemana){
                      if(cfourth==3){
                         break; 
                      }
                      cfourth++;
                  }
              }
                return fecha;
            case LAST:
              for(int i=daysInMonth;i>=1;i--){
                  fecha = LocalDate.of(año,mes,i);
                  dia = fecha.getDayOfWeek();
                  if(dia==diasemana){    
                         break; 
                  }
              }
                return fecha;
            case TEENTH:       
              for(int i=13;i<=19;i++){
                  fecha = LocalDate.of(año,mes,i);
                  dia = fecha.getDayOfWeek();
                  if(dia==diasemana){
                    break;  
                  }
              }
                return fecha;    
            default:
                return fecha;
        }
     
  
   }

    
}
