import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Period;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;


public class DateHelperTest {

    @DisplayName ("Test de la méthode qui doit retourner la semaine de la date donnée")
    @Test
    public void getWeek_WithDate_ShouldReturnTheWeekOfThisDate(){
        Date date = new Date();
        date.setYear(2023 - 1900);
        date.setMonth(5);
        date.setDate(16);
        Date startDate = (Date) date.clone();
        startDate.setYear(2023 - 1900);
        startDate.setMonth(5);
        startDate.setDate(12);
        Date endDate = (Date) date.clone();
        endDate.setYear(2023 - 1900);
        endDate.setMonth(5);
        endDate.setDate(18);

        Period period = DateHelper.getWeekPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }

    @DisplayName ("Test de la méthode qui doit retourner les 4 dernières semaines de la date donnée")
    @Test
    public void getWeek_WithDate_ShouldReturnThe4PAstWeekOfThisDate(){
        Date date = new Date();
        date.setYear(2023 - 1900);
        date.setMonth(5);
        date.setDate(16);
        Date startDate = (Date) date.clone();
        startDate.setYear(2023 - 1900);
        startDate.setMonth(4);
        startDate.setDate(22);
        Date endDate = (Date) date.clone();
        endDate.setYear(2023 - 1900);
        endDate.setMonth(5);
        endDate.setDate(18);

        Period period = DateHelper.get4WeeksPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }

    @DisplayName ("Test de la méthode qui doit retourner la semaine de la date donnée, étant un dimanche")
    @Test
    public void getWeek_WithSundayDate_ShouldReturnTheWeekOfThisDate(){
        Date date = new Date();
        date.setYear(2023 - 1900);
        date.setMonth(5);
        date.setDate(18);
        Date startDate = (Date) date.clone();
        startDate.setYear(2023 - 1900);
        startDate.setMonth(5);
        startDate.setDate(12);
        Date endDate = (Date) date.clone();
        endDate.setYear(2023 - 1900);
        endDate.setMonth(5);
        endDate.setDate(18);

        Period period = DateHelper.getWeekPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }

    @DisplayName ("Test de la méthode qui doit retourner les 4 dernières semaines de la date donnée, étant un dimanche")
    @Test
    public void getWeek_WithSundayDate_ShouldReturnThe4PAstWeekOfThisDate(){
        Date date = new Date();
        date.setYear(2023 - 1900);
        date.setMonth(5);
        date.setDate(18);
        Date startDate = (Date) date.clone();
        startDate.setYear(2023 - 1900);
        startDate.setMonth(4);
        startDate.setDate(22);
        Date endDate = (Date) date.clone();
        endDate.setYear(2023 - 1900);
        endDate.setMonth(5);
        endDate.setDate(18);

        Period period = DateHelper.get4WeeksPeriod(date);

        assertThat(period.getStartDate())
                .isEqualTo(startDate);
        assertThat(period.getEndDate())
                .isEqualTo(endDate);
    }
}
