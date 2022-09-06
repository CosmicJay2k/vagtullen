package edu.lernia.labb4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DisplayName("Test suite")
class TollFeeCalculatorTest {
	static TollFeeCalculator tollFeeCalc;
	static LocalDateTime dateFreeOfCharge;
	static LocalDateTime dateAndTime8kr;
	static LocalDateTime[] mockDates;

	@BeforeAll
	public static void setup() {
		tollFeeCalc = new TollFeeCalculator("src/test/resources/Lab4.txt");

		DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		dateFreeOfCharge = LocalDateTime.parse("2022-09-04 17:00",
				DTF);

		dateAndTime8kr = LocalDateTime.parse("2022-09-05 06:10",
				DTF);

		String[] dateStrings = { "2022-09-05 06:50", "2022-09-05 07:25" };
		mockDates = new LocalDateTime[2];
		for (int i = 0; i < mockDates.length; i++) {
			mockDates[i] = LocalDateTime.parse(dateStrings[i], DTF);
		}

	}

	@Test
	@DisplayName("Testing isTollFreeDate by day(sunday) NO BUG THUS FAR, JUST TESTING")
	void dateFreeOfChargeShouldBeTrue() {
		assertEquals(true, TollFeeCalculator.isTollFreeDate(dateFreeOfCharge));
	}

	@Test
	@DisplayName("Test access to dates variable. If failed, move variable out of constructor scope")
	void datesArrayShouldBeAccessible() {
		assertEquals(LocalDateTime[].class, tollFeeCalc.dates.getClass());
	}

	@Test
	@DisplayName("Test access to dateStrings variable. If failed, move variable out of constructor scope")
	void dateStringsArrayShouldBeAccessible() {
		assertEquals(String[].class, tollFeeCalc.dateStrings.getClass());
	}

	@Test
	@DisplayName("Test array lengths in constructor. If failed, remove minus 1 from dateStrings.length")
	void datesArrayAndDateStringsArrayShouldBeTheSameLength() {
		assertTrue(tollFeeCalc.dateStrings.length == tollFeeCalc.dates.length);
	}

	@Test
	@DisplayName("Testing getFeePerPassing at interval 8.30-14.59. If failed, change minutes >= to 0")
	void getTollFeePerPassingShouldBe8forDateAndTime8kr() {
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(dateAndTime8kr));
	}

	@Test
	@DisplayName("Testing getTotalFeeCost. If failed, use min instead of max in the return")
	void getTotalFeeCostShouldBe60OrLower() {
		var tollFeeCalc = new TollFeeCalculator("src/test/resources/Lab4.txt");
		assertTrue(TollFeeCalculator.getTotalFeeCost(tollFeeCalc.dates) <= 60);
	}

	@Test
	@DisplayName("Testing getTotalFeeCost. Should be 18")
	void getTotalFeeCostShouldOnlyReturnHighestFee() {
		assertTrue(TollFeeCalculator.getTotalFeeCost(mockDates) == 18);
	}
}
