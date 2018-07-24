package com.cassdelacruzmunoz.library;

import java.util.Scanner;

public class ConsoleIO {
	static Scanner in=new Scanner(System.in);
	public static int getNumFromUser(String question) {
		print(question);
		int a=getNextIntSafe();
		if (a!=-1) {
			return a;
		}
		return getNumFromUser(question);
	}
	public static int generateMenu(String question, Object[] options) {
		print(question);
		for (int i=0;i<options.length;i++) {
			if (options.length <= 10) {
				System.out.printf("%d\n%s\n", i, options[i]);
			}
			else if (options.length<=100) {
				System.out.printf("%2d\n%s\n", i, options[i]);
			}
		}
		int a=getNextIntSafe();
		if (a!=-1) {
			return a;
		}
		return generateMenu(question,options);
	}
	public static int getNextIntSafe() {
		if (in.hasNextInt()) {
			int a=in.nextInt();
			in.nextLine();
			return a;
		}
		in.nextLine();
		return -1;
	}
	public static void print(Object s) {
		System.out.println(s);
	}
	public static boolean yesNo(String question) {
		print(question);
		String answer=in.nextLine();
		if(answer.equals("y")) {
			return true;
		}
		else if (answer.equals("n")) {
			return false;
		}
		return yesNo(question);
	}
}
