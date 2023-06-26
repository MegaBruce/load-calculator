import com.picard.load_calculator.helper.CalculateSD2;
import com.picard.load_calculator.model.Activity;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Test the calculation of standard derivation")
public class CalculateSD2Test {

    @DisplayName("Should calculate standard derivation with a given array")
    @Test
    public void shouldReturnSantardDerivation_WithAGivenIntegerArray (){
        List<Integer> arr = new ArrayList<>(Arrays.asList(135, 480, 0, 495, 525, 630, 600));
        int stadardDerivationVerification = 225;

        int standardDerivation = CalculateSD2.doStandardDerivation(arr);

        assertThat(standardDerivation).isEqualTo(stadardDerivationVerification);
    }
}
