import com.picard.load_calculator.controller.ActivityControllerImpl;
import com.picard.load_calculator.controller.FosterControllerImpl;
import com.picard.load_calculator.helper.CalculateSD2;
import com.picard.load_calculator.helper.DateHelper;
import com.picard.load_calculator.model.Activity;
import com.picard.load_calculator.model.Foster;
import com.picard.load_calculator.model.Period;
import com.picard.load_calculator.model.TrainningState;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Group of units tests for the Foster controller")
public class TestFosterControllerImpl {
    FosterControllerImpl classUnderTest;
    static List<Activity> activitiesForOneWeek;
    static List<Activity> activitiesForFourWeeks;
    @Mock
    ActivityControllerImpl activityController;

    static Date date = new Date();
    static Date startFourWeekDate = (Date) date.clone();
    static Date startOneWeekDate = (Date) date.clone();
    static double totalWeekLoad;
    static double totalPast4WeeksLoad;
    static Date endDate = (Date) date.clone();

    @BeforeAll
    public static void setUpBeforeAll(){
        activitiesForOneWeek = new ArrayList<>();
        activitiesForFourWeeks = new ArrayList<>();
        Date mondayDate4 = new Date();
        mondayDate4.setYear(2023 - 1900);
        mondayDate4.setMonth(5);
        mondayDate4.setDate(12);
        activitiesForOneWeek.add(
                new Activity(
                        "Footing souple",
                        45,
                        3,
                        135,
                        mondayDate4,
                        new ObjectId()
                )
        );
        Date tuesdayDate4 = (Date) mondayDate4.clone();
        tuesdayDate4.setMonth(5);
        tuesdayDate4.setDate(13);
        activitiesForOneWeek.add(
                new Activity(
                        "Endurance",
                        60,
                        4,
                        240,
                        tuesdayDate4,
                        new ObjectId()
                )
        );
        activitiesForOneWeek.add(
                new Activity(
                        "Renfo",
                        120,
                        2,
                        240,
                        tuesdayDate4,
                        new ObjectId()
                )
        );
        Date thursdayDate4 = (Date) mondayDate4.clone();
        thursdayDate4.setMonth(5);
        thursdayDate4.setDate(15);
        activitiesForOneWeek.add(
                new Activity(
                        "VMA",
                        55,
                        9,
                        495,
                        thursdayDate4,
                        new ObjectId()
                )
        );
        Date fridayDate4 = (Date) mondayDate4.clone();
        fridayDate4.setMonth(5);
        fridayDate4.setDate(16);
        activitiesForOneWeek.add(
                new Activity(
                        "Allure spécifique",
                        75,
                        7,
                        525,
                        fridayDate4,
                        new ObjectId()
                )
        );
        Date saturdayDate4 = (Date) mondayDate4.clone();
        saturdayDate4.setMonth(5);
        saturdayDate4.setDate(17);
        activitiesForOneWeek.add(
                new Activity(
                        "Sortie Vélo",
                        210,
                        3,
                        630,
                        saturdayDate4,
                        new ObjectId()
                )
        );
        Date sundayDate4 = (Date) mondayDate4.clone();
        sundayDate4.setMonth(5);
        sundayDate4.setDate(18);
        activitiesForOneWeek.add(
                new Activity(
                        "Sortie Longue",
                        150,
                        4,
                        600,
                        sundayDate4,
                        new ObjectId()
                )
        );

        Date mondayDate3 = (Date) mondayDate4.clone();
        mondayDate3.setMonth(5);
        mondayDate3.setDate(5);
        activitiesForFourWeeks.add(
                new Activity(
                        "Footing de récup'",
                        50,
                        2,
                        100,
                        mondayDate3,
                        new ObjectId()
                )
        );
        Date tuesdayDate3 = (Date) mondayDate4.clone();
        tuesdayDate3.setMonth(5);
        tuesdayDate3.setDate(6);
        activitiesForFourWeeks.add(
                new Activity(
                        "Natation",
                        80,
                        2,
                        160,
                        tuesdayDate3,
                        new ObjectId()
                )
        );
        activitiesForFourWeeks.add(
                new Activity(
                        "VMA courte",
                        50,
                        8,
                        400,
                        tuesdayDate3,
                        new ObjectId()
                )
        );
        Date wednesdayDate3 = (Date) mondayDate4.clone();
        wednesdayDate3.setMonth(5);
        wednesdayDate3.setDate(7);
        activitiesForFourWeeks.add(
                new Activity(
                        "Renfo",
                        120,
                        2,
                        240,
                        wednesdayDate3,
                        new ObjectId()
                )
        );
        Date thursdayDate3 = (Date) mondayDate4.clone();
        thursdayDate3.setMonth(5);
        thursdayDate3.setDate(8);
        activitiesForFourWeeks.add(
                new Activity(
                        "Seuil",
                        80,
                        6,
                        480,
                        thursdayDate3,
                        new ObjectId()
                )
        );
        Date fridayDate3 = (Date) mondayDate4.clone();
        fridayDate3.setMonth(5);
        fridayDate3.setDate(9);
        activitiesForFourWeeks.add(
                new Activity(
                        "VMA longue",
                        75,
                        9,
                        675,
                        fridayDate3,
                        new ObjectId()
                )
        );
        Date sundayDate3 = (Date) mondayDate4.clone();
        sundayDate3.setMonth(5);
        sundayDate3.setDate(11);
        activitiesForFourWeeks.add(
                new Activity(
                        "Sortie Longue",
                        130,
                        3,
                        390,
                        sundayDate3,
                        new ObjectId()
                )
        );


        Date mondayDate2 = (Date) mondayDate4.clone();
        mondayDate2.setMonth(4);
        mondayDate2.setDate(29);
        activitiesForFourWeeks.add(
                new Activity(
                        "Footing progressif",
                        60,
                        4,
                        240,
                        mondayDate2,
                        new ObjectId()
                )
        );
        Date tuesdayDate2 = (Date) mondayDate4.clone();
        tuesdayDate2.setMonth(4);
        tuesdayDate2.setDate(30);
        activitiesForFourWeeks.add(
                new Activity(
                        "Renfo'",
                        120,
                        2,
                        240,
                        tuesdayDate2,
                        new ObjectId()
                )
        );
        Date wednesdayDate2 = (Date) mondayDate4.clone();
        wednesdayDate2.setMonth(4);
        wednesdayDate2.setDate(31);
        activitiesForFourWeeks.add(
                new Activity(
                        "Allure spé",
                        60,
                        6,
                        360,
                        wednesdayDate2,
                        new ObjectId()
                )
        );
        Date thursdayDate2 = (Date) mondayDate4.clone();
        thursdayDate2.setMonth(5);
        thursdayDate2.setDate(1);
        activitiesForFourWeeks.add(
                new Activity(
                        "VMA",
                        60,
                        10,
                        600,
                        thursdayDate2,
                        new ObjectId()
                )
        );
        Date saturdayDate2 = (Date) mondayDate4.clone();
        saturdayDate2.setMonth(5);
        saturdayDate2.setDate(3);
        activitiesForFourWeeks.add(
                new Activity(
                        "Longue à vélo",
                        300,
                        2,
                        600,
                        saturdayDate2,
                        new ObjectId()
                )
        );
        Date sundayDate2 = (Date) mondayDate4.clone();
        sundayDate2.setMonth(5);
        sundayDate2.setDate(3);
        activitiesForFourWeeks.add(
                new Activity(
                        "Longue avec accélérations",
                        95,
                        4,
                        380,
                        sundayDate2,
                        new ObjectId()
                )
        );


        Date tuesdayDate1 = (Date) mondayDate4.clone();
        tuesdayDate1.setMonth(4);
        tuesdayDate1.setDate(23);
        activitiesForFourWeeks.add(
                new Activity(
                        "Footing récup",
                        60,
                        2,
                        120,
                        tuesdayDate1,
                        new ObjectId()
                )
        );
        activitiesForFourWeeks.add(
                new Activity(
                        "P'tit renfo",
                        90,
                        2,
                        180,
                        tuesdayDate1,
                        new ObjectId()
                )
        );
        Date thursdayDate1 = (Date) mondayDate4.clone();
        thursdayDate1.setMonth(4);
        thursdayDate1.setDate(25);
        activitiesForFourWeeks.add(
                new Activity(
                        "Petite VMA",
                        40,
                        8,
                        320,
                        thursdayDate1,
                        new ObjectId()
                )
        );
        Date fridayDate1 = (Date) mondayDate4.clone();
        fridayDate1.setMonth(4);
        fridayDate1.setDate(26);
        activitiesForFourWeeks.add(
                new Activity(
                        "Petit footing",
                        90,
                        3,
                        270,
                        fridayDate1,
                        new ObjectId()
                )
        );
        Date sundayDate1 = (Date) mondayDate4.clone();
        sundayDate1.setMonth(4);
        sundayDate1.setDate(28);
        activitiesForFourWeeks.add(
                new Activity(
                        "Petit vélo",
                        180,
                        2,
                        360,
                        sundayDate1,
                        new ObjectId()
                )
        );

        date.setYear(2023 - 1900);
        date.setMonth(5);
        date.setDate(16);

        startFourWeekDate.setYear(2023 - 1900);
        startFourWeekDate.setMonth(4);
        startFourWeekDate.setDate(22);

        startOneWeekDate.setYear(2023 - 1900);
        startOneWeekDate.setMonth(5);
        startOneWeekDate.setDate(12);

        endDate.setYear(2023 - 1900);
        endDate.setMonth(5);
        endDate.setDate(18);
        totalWeekLoad = activitiesForOneWeek
                .stream()
                .map(a -> a.getLoad())
                .reduce(0, (a, b) -> a + b);
        totalPast4WeeksLoad = activitiesForFourWeeks
                .stream()
                .map(a -> a.getLoad())
                .reduce(0, (a, b) -> a + b);
    }

    @BeforeEach
    public void setUp() {
        this.classUnderTest = new FosterControllerImpl(this.activityController);
    }

    @Test
    @DisplayName("Should return Trainning State")
    public void forAGivenDate_ShouldReturnTrainningState() {
        List<Integer> arr = new ArrayList<>(Arrays.asList(135, 480, 0, 495, 525, 630, 600));
        int stadardDerivation = 225;
        when(activityController.findActivitiesByPeriod(new Period(startFourWeekDate, endDate)))
                .thenReturn(this.activitiesForFourWeeks);
        when(activityController.findActivitiesByPeriod(new Period(startOneWeekDate, endDate)))
                .thenReturn(this.activitiesForOneWeek);

        try (MockedStatic<DateHelper> utilities = Mockito.mockStatic(DateHelper.class)) {
            utilities.when(() -> DateHelper.getWeekPeriod(date))
                    .thenReturn(new Period(startOneWeekDate, endDate));
            utilities.when(() -> DateHelper.get4WeeksPeriod(date))
                    .thenReturn(new Period(startFourWeekDate, endDate));
        }

        try (MockedStatic<CalculateSD2> utilities = Mockito.mockStatic(CalculateSD2.class)) {
            utilities.when(() -> CalculateSD2.doStandardDerivation(arr))
                    .thenReturn(stadardDerivation);
        }

        Foster fosterState = this.classUnderTest.getTrainningState(date);

        assertThat(fosterState.getLoad()).isEqualTo((int)totalWeekLoad);
        assertThat(fosterState.getAcwr()).isEqualTo(
                totalWeekLoad/(totalPast4WeeksLoad/4)
        );
        double monotony = (totalWeekLoad/7) / stadardDerivation;
        assertThat(fosterState.getMonotony()).isEqualTo(monotony);
        int strain = (int) (totalWeekLoad*monotony);
        assertThat(fosterState.getStrain()).isEqualTo(strain);
        assertThat(fosterState.getFitness()).isEqualTo((int)totalWeekLoad-strain);
    }

    @Test
    @DisplayName("Should return an Optimal trainning State for a foster object")
    public void givenAFoster_CalculateTrainningState_ShouldReturnOptimalTrainningState(){
        Foster foster = new Foster(2778,1.8,5000,-2222,0.95);
        TrainningState result = classUnderTest.calculateTrainningState(foster);
        assertThat(result).isEqualTo(TrainningState.OPTIMAL);
    }

    @Test
    @DisplayName("Should return a RAS trainning State for a foster object")
    public void givenAFoster_CalculateTrainningState_ShouldReturnRASTrainningState(){
        Foster foster = new Foster(2778,1.8,5000,-2222,1.4);
        TrainningState result = classUnderTest.calculateTrainningState(foster);
        assertThat(result).isEqualTo(TrainningState.RAS);
        assertThat(result).isNotEqualTo(TrainningState.OPTIMAL);
    }

    @Test
    @DisplayName("Should return a TIRED trainning State for a foster object")
    public void givenAFoster_CalculateTrainningState_ShouldReturnTiredTrainningState(){
        Foster foster = new Foster(4200,2.2,9240,-5040,1.5);
        TrainningState result = classUnderTest.calculateTrainningState(foster);
        assertThat(result).isEqualTo(TrainningState.TIRED);
    }

    @Test
    @DisplayName("Should return an INJURY trainning State for a foster object")
    public void givenAFoster_CalculateTrainningState_ShouldReturnInjuryTrainningState(){
        Foster foster = new Foster(4200,2.2,9240,-5040,1.8);
        TrainningState result = classUnderTest.calculateTrainningState(foster);
        assertThat(result).isEqualTo(TrainningState.INJURY);
    }
}
