package com.github.trhura.kotler

open class TerminalString(var child_color: String = "", var output: String = "", var color: String = "") {
	internal val reset: String = "\u001B[0m"
	internal var children = arrayListOf<TerminalString>()

	protected fun <T: TerminalString> buildChild(child: T, build: T.() -> Any):  Unit {
		child.color = this.child_color
		children.add(child)

		val result = child.build()
		if (result is String) child.children.add(TerminalString(output=result, color=child.child_color))
	}

	override fun toString(): String {
		val buffer = StringBuilder()
		buffer.append("${color}")
		buffer.append("${output}")
		for (child in children) {
			buffer.append(child)
		}
		buffer.append("${reset}")
		return buffer.toString()
	}

	fun black (build: Black.() -> Any) = buildChild (Black(), build)
	fun red (build: Red.() -> Any) = buildChild (Red(), build)
	fun green (build: Green.() -> Any) = buildChild (Green(), build)
	fun yellow (build: Yellow.() -> Any) = buildChild (Yellow(), build)
	fun blue (build: Blue.() -> Any) = buildChild (Blue(), build)
	fun magenta (build: Magenta.() -> Any) = buildChild (Magenta(), build)
	fun cyan (build: Cyan.() -> Any) = buildChild (Cyan(), build)
	fun white (build: White.() -> Any) = buildChild (White(), build)
	
	fun on_black (build: OnBlack.() -> Any) = buildChild (OnBlack(), build)
	fun on_red (build: OnRed.() -> Any) = buildChild (OnRed(), build)
	fun on_green (build: OnGreen.() -> Any) = buildChild (OnGreen(), build)
	fun on_yellow (build: OnYellow.() -> Any) = buildChild (OnYellow(), build)
	fun on_blue (build: OnBlue.() -> Any) = buildChild (OnBlue(), build)
	fun on_magenta (build: OnMagenta.() -> Any) = buildChild (OnMagenta(), build)
	fun on_cyan (build: OnCyan.() -> Any) = buildChild (OnCyan(), build)
	fun on_white (build: OnWhite.() -> Any) = buildChild (OnWhite(), build)

	fun bold (build: Bold.() -> Any) = buildChild (Bold(), build)
	fun dark (build: Dark.() -> Any) = buildChild (Dark(), build)
	fun underline (build: Underline.() -> Any) = buildChild (Underline(), build)
	fun blink (build: Blink.() -> Any) = buildChild (Blink(), build)
	fun reverse_color (build: ReverseColor.() -> Any) = buildChild (ReverseColor(), build)
	fun concealed (build: Concealed.() -> Any) = buildChild (Concealed(), build)
}

class Black: TerminalString(child_color="\u001B[30m")
class Red: TerminalString(child_color="\u001B[31m")
class Green: TerminalString(child_color="\u001B[32m")
class Yellow: TerminalString(child_color="\u001B[33m")
class Blue: TerminalString(child_color="\u001B[34m")
class Magenta: TerminalString(child_color="\u001B[35m")
class Cyan: TerminalString(child_color="\u001B[36m")
class White: TerminalString(child_color="\u001B[37m")

class OnBlack: TerminalString(child_color="\u001B[40m")
class OnRed: TerminalString(child_color="\u001B[41m")
class OnGreen: TerminalString(child_color="\u001B[42m")
class OnYellow: TerminalString(child_color="\u001B[43m")
class OnBlue: TerminalString(child_color="\u001B[44m")
class OnMagenta: TerminalString(child_color="\u001B[45m")
class OnCyan: TerminalString(child_color="\u001B[46m")
class OnWhite: TerminalString(child_color="\u001B[47m")

class Bold: TerminalString(child_color="\u001B[1m")
class Dark: TerminalString(child_color="\u001B[2m")
class Underline: TerminalString(child_color="\u001B[4m")
class Blink: TerminalString(child_color="\u001B[5m")
class ReverseColor: TerminalString(child_color="\u001B[7m")
class Concealed: TerminalString(child_color="\u001B[8m")

fun terminal_string (build: TerminalString.() -> Any): TerminalString {
	var rootString = TerminalString()
	var result = rootString.build()
	
	if (result is String) rootString.children.add(TerminalString(output=result))
	return rootString
}
