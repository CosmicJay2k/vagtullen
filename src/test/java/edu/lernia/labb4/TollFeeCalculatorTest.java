package edu.lernia.labb4;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test suite")
class TollFeeCalculatorTests {

	LocalDateTime dateFreeOfCharge = LocalDateTime.parse("2022-09-04 17:00",
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

	LocalDateTime dateAndTime8kr = LocalDateTime.parse("2022-09-05 10:00",
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

	@Test
	@DisplayName("Testing isTollFreeDate by day(sunday)")
	void test() {
		assertEquals(true, TollFeeCalculator.isTollFreeDate(dateFreeOfCharge));
	}

	@Test
	@DisplayName("Testing getFeePerPassing at interval 8.30-14.59. If failed, change minutes >= to 0")
	void test3() {
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(dateAndTime8kr));
	}

}
