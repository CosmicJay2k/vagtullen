package edu.lernia.labb4;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test suite")
class Tests {
	LocalDateTime dateFreeOfCharge = LocalDateTime.parse("2022-09-04 10:00",
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

	@Test
	@DisplayName("Testing isTollFree Method")
	void test() {
		assertEquals(true, TollFeeCalculator.isTollFreeDate(dateFreeOfCharge));
	}

	@Test
	void test2() {

	}

}
