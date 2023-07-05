import com.picard.load_calculator.controller.ActivityControllerImpl;
import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Period;
import com.picard.load_calculator.repository.ActivityRepository;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the Activity controller")
public class ActivityControllerTest {
    ActivityControllerImpl classUnderTest;

    static Bson query;
    static LocalDate startDate;
    static LocalDate now;
    static LocalDate endDate;
    @Mock
    ActivityRepository activityRepository;

    @BeforeAll
    public static void setUpBeforeAll () {
        startDate = LocalDate.of(2023, 7, 3);
        now = LocalDate.of(2023, 7, 4);
        endDate = LocalDate.of(2023, 7, 9);
        query = and(
                gte("dateTime", startDate),
                lt("dateTime", endDate)
        );
    }

    @BeforeEach
    public void setUp() {
        this.classUnderTest = new ActivityControllerImpl(this.activityRepository);
    }

    @Test
    @DisplayName("Should send the query for a given period")
    public void forAGivenPeriod_ShouldQueryToRepository() {
        when(activityRepository.search(query))
                .thenReturn(new ArrayList<>());

        try (MockedStatic<DateHelper> utilities = Mockito.mockStatic(DateHelper.class)) {
            utilities.when(() -> DateHelper.getWeekPeriod(now))
                    .thenReturn(new Period(startDate, endDate));
        }

        List<Activity> activities = this.classUnderTest.findActivitiesByPeriod(now);

        verify(activityRepository).search(query);
    }
}
