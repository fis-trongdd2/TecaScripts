package com.howtodoinjava.demo.poi;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		try {
			Script.generateAllScripts("map_test.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
