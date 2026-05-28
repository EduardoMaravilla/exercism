import java.time.LocalDate;
import java.time.LocalDateTime;

public class Gigasecond {
    private LocalDate dia;
    private LocalDateTime completo;

    public Gigasecond(LocalDate moment) {
        dia = moment;
    }

    public Gigasecond(LocalDateTime moment) {
        completo = moment;
    }

    public LocalDateTime getDateTime() {
        if (dia != null) {
            return dia.atStartOfDay().plusSeconds(1000000000);
        } else {
            return completo.plusSeconds(1000000000);
        }
    }
}