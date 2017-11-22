package com.github.trhura.kotler

import kotlin.test.assertEquals
import org.junit.Test

class TestSource {
	private val reset: String = "\u001B[0m"
	
	@Test fun test_individual_function () {
		val normal_string  = terminal_string { "s" } .toString()
		assertEquals(normal_string, "s" + reset.repeat(2))
		
		val black_string = terminal_string { black { "s" } } .toString()
		assertEquals(black_string, "\u001B[30m" + "s" + reset.repeat(3))
		
		val red_string = terminal_string { red { "s" } } .toString()
		assertEquals(red_string, "\u001B[31m" + "s" + reset.repeat(3))
		
		val green_string = terminal_string { green { "s" } } .toString()
		assertEquals(green_string, "\u001B[32m" + "s" + reset.repeat(3))
		
		val yellow_string = terminal_string { yellow { "s" } } .toString()
		assertEquals(yellow_string, "\u001B[33m" + "s" + reset.repeat(3))
		
		val blue_string = terminal_string { blue { "s" } } .toString()
		assertEquals(blue_string, "\u001B[34m" + "s" + reset.repeat(3))
		
		val magenta_string = terminal_string { magenta { "s" } } .toString()
		assertEquals(magenta_string, "\u001B[35m" + "s" + reset.repeat(3))
		
		val cyan_string = terminal_string { cyan { "s" } } .toString()
		assertEquals(cyan_string, "\u001B[36m" + "s" + reset.repeat(3))
		
		val white_string = terminal_string { white { "s" } } .toString()
		assertEquals(white_string, "\u001B[37m" + "s" + reset.repeat(3))

		val on_black_string = terminal_string { on_black { "s" } } .toString()
		assertEquals(on_black_string, "\u001B[40m" + "s" + reset.repeat(3))
		
		val on_red_string = terminal_string { on_red { "s" } } .toString()
		assertEquals(on_red_string, "\u001B[41m" + "s" + reset.repeat(3))
		
		val on_green_string = terminal_string { on_green { "s" } } .toString()
		assertEquals(on_green_string, "\u001B[42m" + "s" + reset.repeat(3))
		
		val on_yellow_string = terminal_string { on_yellow { "s" } } .toString()
		assertEquals(on_yellow_string, "\u001B[43m" + "s" + reset.repeat(3))
		
		val on_blue_string = terminal_string { on_blue { "s" } } .toString()
		assertEquals(on_blue_string, "\u001B[44m" + "s" + reset.repeat(3))
		
		val on_magenta_string = terminal_string { on_magenta { "s" } } .toString()
		assertEquals(on_magenta_string, "\u001B[45m" + "s" + reset.repeat(3))
		
		val on_cyan_string = terminal_string { on_cyan { "s" } } .toString()
		assertEquals(on_cyan_string, "\u001B[46m" + "s" + reset.repeat(3))
		
		val on_white_string = terminal_string { on_white { "s" } } .toString()
		assertEquals(on_white_string, "\u001B[47m" + "s" + reset.repeat(3))

		val bold_string = terminal_string { bold { "s" } } .toString()
		assertEquals(bold_string, "\u001B[1m" + "s" + reset.repeat(3))
		
		val dark_string = terminal_string { dark { "s" } } .toString()
		assertEquals(dark_string, "\u001B[2m" + "s" + reset.repeat(3))
		
		val underline_string = terminal_string { underline { "s" } } .toString()
		assertEquals(underline_string, "\u001B[4m" + "s" + reset.repeat(3))
		
		val blink_string = terminal_string { blink { "s" } } .toString()
		assertEquals(blink_string, "\u001B[5m" + "s" + reset.repeat(3))
		
		val reverse_color_string = terminal_string { reverse_color { "s" } } .toString()
		assertEquals(reverse_color_string, "\u001B[7m" + "s" + reset.repeat(3))
		
		val concealed_string = terminal_string { concealed { "s" } } .toString()
		assertEquals(concealed_string, "\u001B[8m"  + "s" + reset.repeat(3))
	}

	@Test fun test_nested_functions () {
		val red_on_white = terminal_string { red { on_white { "s" } } } .toString()
		assertEquals(red_on_white, "\u001B[31m" + "\u001B[47m" + "s" + reset.repeat(4))

		val red_on_white_bold = terminal_string {
			red { on_white { "s" }; bold { "s" } }
		} .toString()
		
		assertEquals(red_on_white_bold, "\u001B[31m" + "\u001B[47m" + "s" + reset.repeat(2) + "\u001B[31m" + "\u001B[1m" + "s" + reset.repeat(4))
	}
}
