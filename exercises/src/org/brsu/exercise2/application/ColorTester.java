package org.brsu.exercise2.application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.brsu.exercise2.gui.MainFrame;
import org.brsu.exercise2.gui.MainPanel;

public class ColorTester {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
	    InvocationTargetException {
		new MainFrame();

		MainPanel mainPanel = new MainPanel();
		Method[] methods = mainPanel.getClass().getMethods();
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase("hasFocus")) {
				System.out.println(method.invoke(mainPanel, null));
			}
		}
	}
}
