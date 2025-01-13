package com.axonivy.utils.gdprconnector.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

import com.axonivy.utils.gdprconnector.enums.IvyVariable;

import ch.ivyteam.ivy.environment.Ivy;

public class IvyVariableUtils {
	private static final String COMMA = ",";

	public static int getIntFromVariable(IvyVariable ivyVariable, int defaultVal) {
		String variable = Ivy.var().get(ivyVariable.getVariableName());
		return NumberUtils.toInt(variable, defaultVal);
	}
	
	public static List<String> splitIvyVariableValuesToList(IvyVariable variable) {
		return Arrays.asList(Ivy.var().get(variable.getVariableName()).split(COMMA));
	}

	public static String getStringFromVariable(IvyVariable ivyVariable) {
		return Ivy.var().get(ivyVariable.getVariableName());
	}
}
