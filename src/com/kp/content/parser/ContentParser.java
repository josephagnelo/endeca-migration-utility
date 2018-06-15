package com.kp.content.parser;

import java.io.File;

public class ContentParser {

	public static void main(String[] args) {
		if((args == null) || (args.length != 2))
		{
			printUsage();
			return;
		}
		System.out.println("------------------------------------------------------------------------------------------------");
		DirectoryParser dirParser = new DirectoryParser();
		dirParser.parseDir(new File(args[0]));
		System.out.println("------------------------------------------------------------------------------------------------");
		dirParser.printResults();
		System.out.println("------------------------------------------------------------------------------------------------");
		dirParser.printDetailedResults();
		System.out.println("------------------------------------------------------------------------------------------------");
	}

	private static void printUsage() {
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("Please pass the root directory path and destination directory path(to place output file) as args");
		System.out.println("------------------------------------------------------------------------------------------------");
		
	}

}
