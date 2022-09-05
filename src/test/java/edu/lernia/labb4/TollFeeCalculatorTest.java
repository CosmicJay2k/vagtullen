package edu.lernia.labb4;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test suite")
class TollFeeCalculatorTest {

	LocalDateTime dateFreeOfCharge = LocalDateTime.parse("2022-09-04 17:00",
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

	LocalDateTime dateAndTime8kr = LocalDateTime.parse("2022-09-05 10:00",
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

	@Test
	@DisplayName("Test array lengths in constructor. If failed, remove minus 1 from dateStrings.length")
	void datesArrayAndDateStringsArrayShouldBeTheSameLength() {
		var tollFeeCalc = new TollFeeCalculator("src/test/resources/Lab4.txt");
		var dates = tollFeeCalc.dates;
		var dateString = tollFeeCalc.dateStrings;
		assertTrue(dateString.length == dates.length);
	}

	@Test
	@DisplayName("Testing isTollFreeDate by day(sunday) NO BUG THIS FAR, JUST TESTING")
	void dateFreeOfChargeShouldBeTrue() {
		assertEquals(true, TollFeeCalculator.isTollFreeDate(dateFreeOfCharge));
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

}
