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
	static LocalDateTime ldt06h8kr;
	static LocalDateTime ldt06h13kr;
	static LocalDateTime ldt07h18kr;
	static LocalDateTime ldt08h13kr;
	static LocalDateTime ldt08h8kr;
	static LocalDateTime ldt15h13kr;
	static LocalDateTime ldt15h18kr;
	static LocalDateTime ldt17h13kr;
	static LocalDateTime ldt18h8kr;
	static LocalDateTime ldt18h0kr;
	static LocalDateTime[] mockDates;

	@BeforeAll
	public static void setup() {
		tollFeeCalc = new TollFeeCalculator("src/test/resources/Lab4.txt");

		DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		dateFreeOfCharge = LocalDateTime.parse("2022-09-04 17:00", DTF);
		ldt06h8kr = LocalDateTime.parse("2022-09-05 06:10", DTF);
		ldt06h13kr = LocalDateTime.parse("2022-09-05 06:40", DTF);
		ldt07h18kr = LocalDateTime.parse("2022-09-05 07:10", DTF);
		ldt08h13kr = LocalDateTime.parse("2022-09-05 08:10", DTF);
		ldt08h8kr = LocalDateTime.parse("2022-09-05 10:10", DTF);
		ldt15h13kr = LocalDateTime.parse("2022-09-05 15:10", DTF);
		ldt15h18kr = LocalDateTime.parse("2022-09-05 16:10", DTF);
		ldt17h13kr = LocalDateTime.parse("2022-09-05 17:10", DTF);
		ldt18h8kr = LocalDateTime.parse("2022-09-05 18:10", DTF);
		ldt18h0kr = LocalDateTime.parse("2022-09-05 23:10", DTF);

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

	//
	//
	// TEST ACCESSABILITY TO VARIABLES FOR TESTING PURPOSES

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

	//
	//
	// TESTING getTotalFeeCost

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

	//
	//
	// TESTS FOR getTollFeePerPassing IF/ELSE inferno

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 6.00-6.29.")
	void getTollFeePerPassingShouldBe8forldt06h8kr() {
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(ldt06h8kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 6.30-6.59.")
	void getTollFeePerPassingShouldBe13forldt06h13kr() {
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(ldt06h13kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 7.00-7.59.")
	void getTollFeePerPassingShouldBe18forldt07h18kr() {
		assertEquals(18, TollFeeCalculator.getTollFeePerPassing(ldt07h18kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 8.00-8.29.")
	void getTollFeePerPassingShouldBe13forldt08h13kr() {
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(ldt08h13kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 8.30-14.59. IF FAILED, CHANGE minutes >= FROM 30 TO 0")
	void getTollFeePerPassingShouldBe8forldt08h8kr() {
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(ldt08h8kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 15.00-15.29.")
	void getTollFeePerPassingShouldBe13forldt15h13kr() {
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(ldt15h13kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 15.30-16.59.")
	void getTollFeePerPassingShouldBe18forldt15h18kr() {
		assertEquals(18, TollFeeCalculator.getTollFeePerPassing(ldt15h18kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 17.00-17.59.")
	void getTollFeePerPassingShouldBe13forldt17h13kr() {
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(ldt17h13kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 18.00-18.29.")
	void getTollFeePerPassingShouldBe8forldt18h8kr() {
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(ldt18h8kr));
	}

	@Test
	@DisplayName("Testing getTollFeePerPassing at interval 18.30-05.59.")
	void getTollFeePerPassingShouldBe0forldt18h0kr() {
		assertEquals(0, TollFeeCalculator.getTollFeePerPassing(ldt18h0kr));
	}
}
