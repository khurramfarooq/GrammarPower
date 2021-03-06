/**
 * 
 */
package com.android.grammarpower.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.text.TextUtils;

import android.content.res.AssetManager;

/**
 * @author khurram Farooq
 *
 */
public class TextParser {

	static public List<String> readFile(AssetManager am, String fileName) {
		List<String> returnString = null;
		try {
			InputStream is = am.open(fileName);
			returnString = new ArrayList<String>();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String stringLine = "";
			while ((stringLine = br.readLine()) != null) {
				if (!TextUtils.isEmpty(stringLine)) {
					returnString.add(stringLine);
				}
			}
		} catch (Exception ex) {

		}
		return returnString;
	}

	public static QuizData splitData(String randString) {
		QuizData returnData = null;
		String[] spliString;
		String[] option;
		try {
			if (randString != null) {
				spliString = randString.split(";");
				option = spliString[3].split("/");
				returnData = new QuizData();
				returnData.quizId = Integer.parseInt(spliString[0]);
				returnData.correctOption = spliString[1];
				returnData.name = spliString[2];
				returnData.option1 = option[0].trim();
				returnData.option2 = option[1].trim();
				returnData.option3 = option[2].trim();
			}
		} catch (Exception ex) {

		}
		return returnData;
	}
	
	public static String removeSpecialCharacter(String[] ls) {
		int lsSize = ls.length;
		StringBuilder returnString = new StringBuilder();
		try {
			if (ls != null) {
				for (int i = 0; i < lsSize; i++) {
					if (ls[i].startsWith("\"") && ls[i].endsWith("\"")) {
						ls[i] = ls[i].substring(1, (ls[i].length() - 1));
					} else if (ls[i].startsWith("\"")) {
						ls[i] = ls[i].substring(1, ls[i].length());
					} else if (ls[i].endsWith("\"")) {
						ls[i] = ls[i].substring(0, (ls[i].length() - 1));
					}
				}
			}

		} catch (Exception ex) {

		}

		for (int g = 0; g < lsSize; g++) {
			returnString.append(ls[g] + "\n");
		}

		return returnString.toString();
	}
}
