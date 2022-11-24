package HandleListResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListToStringArray {
	public String list;
	public int num_line;
	public String[][] res_list;

	public ListToStringArray(String list) {
		// TODO Auto-generated constructor stub
		this.list = list;

		String[] listLine = this.list.split("\n");

		this.num_line = listLine.length - 3;
		this.res_list = new String[this.num_line][3];

		int cur_row = 0;
		for (int i = 3; i < listLine.length; i++) {
			if (listLine[i] != null && listLine[i].length() != 0) {

				listLine[i] = removeBlankSpace(listLine[i]);

				int posSecondSpaceInt = listLine[i].lastIndexOf(" ");
				this.res_list[cur_row][2] = listLine[i].substring(posSecondSpaceInt + 1);
				listLine[i] = listLine[i].substring(0, posSecondSpaceInt);
				
				int posFirstSpaceInt = listLine[i].lastIndexOf(" ");
				this.res_list[cur_row][1] = listLine[i].substring(posFirstSpaceInt + 1);
				listLine[i] = listLine[i].substring(0, posFirstSpaceInt);

				this.res_list[cur_row][0] = listLine[i];

				cur_row++;
			}
		}
	}

	String removeBlankSpace(String inputString) {
		while (inputString.contains("  ") == true) {
			inputString = inputString.replaceAll("  ", " ");
		}
		return inputString.trim();
	}
}
