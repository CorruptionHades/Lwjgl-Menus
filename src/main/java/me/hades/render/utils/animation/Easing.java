package me.hades.render.utils.animation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SIMULATAN (github.com/SIMULATAN)
 */
public enum Easing {

	LINEAR, EASE_IN_SINE, EASE_OUT_SINE,
	EASE_IN_OUT_SINE, EASE_IN_CUBIC, EASE_OUT_CUBIC,
	EASE_IN_OUT_CUBIC, EASE_IN_QUINT, EASE_OUT_QUINT, EASE_IN_OUT_QUINT,
	EASE_IN_CIRC, EASE_OUT_CIRC, EASE_IN_OUT_CIRC, EASE_IN_ELASTIC, EASE_OUT_ELASTIC,
	EASE_IN_OUT_ELASTIC, EASE_IN_QUAD, EASE_OUT_QUAD, EASE_IN_OUT_QUAD, EASE_IN_QUART,
	EASE_OUT_QUART, EASE_IN_OUT_QUART, EASE_IN_EXPONENTIAL, EASE_OUT_EXPONENTIAL,
	EASE_IN_OUT_EXPONENTIAL, EASE_IN_BACK, EASE_OUT_BACK, EASE_IN_OUT_BACK, EASE_IN_BOUNCE,
	EASE_OUT_BOUNCE, EASE_IN_OUT_BOUNCE;

	public static List<String> getValues() {
		List<String> values = new ArrayList<>();
		for (Easing value : Easing.values()) {
			values.add(value.name());
		}
		return values;
	}
}