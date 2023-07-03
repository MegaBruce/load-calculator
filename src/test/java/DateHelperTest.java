import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class DateHelperTest {

    @DisplayName ("Test de la méthode qui doit retourner la semaine de la date donnée")
    @Test
    public void getWeek_WithDate_ShouldReturnTheWeekOfThisDate(){
        LocalDate date = LocalDate.of(2023, 6, 16);
        LocalDate startDate = LocalDate.of(2023, 6, 12);
        LocalDate endDate = LocalDate.of(2023, 6, 18);

        Period period = DateHelper.getWeekPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }

    @DisplayName ("Test de la méthode qui doit retourner les 4 dernières semaines de la date donnée")
    @Test
    public void getWeek_WithDate_ShouldReturnThe4PAstWeekOfThisDate(){
        LocalDate date = LocalDate.of(2023, 6, 16);
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 6, 18);

        Period period = DateHelper.get4WeeksPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }

    @DisplayName ("Test de la méthode qui doit retourner la semaine de la date donnée, étant un dimanche")
    @Test
    public void getWeek_WithSundayDate_ShouldReturnTheWeekOfThisDate(){
        LocalDate date = LocalDate.of(2023, 6, 18);
        LocalDate startDate = LocalDate.of(2023, 6, 12);
        LocalDate endDate = LocalDate.of(2023, 6, 18);

        Period period = DateHelper.getWeekPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }

    @DisplayName ("Test de la méthode qui doit retourner les 4 dernières semaines de la date donnée, étant un dimanche")
    @Test
    public void getWeek_WithSundayDate_ShouldReturnThe4PAstWeekOfThisDate(){
        LocalDate date = LocalDate.of(2023, 6, 18);
        LocalDate startDate = LocalDate.of(2023, 5, 22);
        LocalDate endDate = LocalDate.of(2023, 6, 18);

        Period period = DateHelper.get4WeeksPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }
}
