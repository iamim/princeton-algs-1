import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("PercolationStats tests")
public class PercolationStatsTests {

    @Nested
    @DisplayName("Constructor tests")
    class ConstructorTests {

        @Test
        @DisplayName("throws when n <= 0 or trials <= 0")
        void throwsTests() {
            assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, 0));
            assertThrows(IllegalArgumentException.class, () -> new PercolationStats(1, -1));
            assertThrows(IllegalArgumentException.class, () -> new PercolationStats(0, 1));
            assertThrows(IllegalArgumentException.class, () -> new PercolationStats(-1, 1));
        }
    }
}
