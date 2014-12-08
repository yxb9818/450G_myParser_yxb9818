package edu.louisiana.cacs.csce450GProject;

import java.util.*;
import java.io.*;


public class Parser {
	public String tree1;
	/*
	 * YOUR CODE GOES HERE
	 * 
	 * You must implement two methods 1. parse 2. printParseTree Print the
	 * intermediate states of the parsing process, including the intermediate
	 * states of the parse tree,make as specified in the class handout. If the
	 * input is legal according to the grammar, print ACCEPT, else
	 * UNGRAMMATICAL. If the parse is successful, print the final parse tree.
	 * You can modify the input and output of these function but not the name
	 */
	public Parser(String fileName) {
		System.out.println("File to parse : " + fileName);
	}

	/*
	 * Dummy code
	 */
	public void printParseTree(String x) {
		
		System.out.println("Parse Tree:");
		String n1 = (x.replaceAll("[^a-zA-Z+*$ ]", ""));
		int c2 = 0;
		int j,k = 0;
		for(int c = 0; c < n1.length(); c++) {
		char t = n1.charAt(c);
		if( t == ' ') {
		continue;
		}
		if( t == '+' || t == '*') {
		c2++;
		k=c2;
		k++;
		} else {
			
			k++;
		
		}
		String ps2 = "";
		for(j = 1 ; j < k; j++) {
		ps2 = ps2 + "  ";
		}
		if( t == 'i') {
		ps2 = ps2 + "id";
		c++;
		} else {
		ps2 =ps2+t;
		
		}
		System.out.println(ps2);
		
		}
		System.out.println("$");
		
	}
	

	/*
	 * Dummy code
	 */
	public void parse() {
		String[] formula = { "E=E+T", "E=T", "T=T*F", "T=F", "F=(E)", "F=id" };
		Stack treeStack = new Stack();
		String[][] Action = { { "S5", null, null, "S4", null, null },
				{ null, "S6", null, null, null, "accept" },
				{ null, "R2", "S7", null, "R2", "R2" },
				{ null, "R4", "R4", null, "R4", "R4" },
				{ "S5", null, null, "S4", null, null },
				{ null, "R6", "R6", null, "R6", "R6" },
				{ "S5", null, null, "S4", null, null },
				{ "S5", null, null, "S4", null, null },
				{ null, "S6", null, null, "S11", null },
				{ null, "R1", "S7", null, "R1", "R1" },
				{ null, "R3", "R3", null, "R3", "R3" } };
		int[][] Goto = { { 1, 2, 3 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
				{ 8, 2, 3 }, { 0, 0, 0 }, { 0, 9, 3 }, { 0, 0, 10 },
				{ 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		HashMap h = new HashMap();
		h.put("id", 0);
		h.put("+", 1);
		h.put("*", 2);
		h.put("(", 3);
		h.put(")", 4);
		h.put("$", 5);
		h.put("E", 0);
		h.put("T", 1);
		h.put("F", 2);
		HashMap k = new HashMap();
		k.put("Stack", 1);
		k.put("input tokens", 2);
		k.put("action lookup", 3);
		k.put("action value", 4);
		k.put("valueofLHS", 5);
		k.put("lengthofRHS", 6);
		k.put("tempstack", 7);
		k.put("gotolookup", 8);
		k.put("gotovalue", 9);
		k.put("stackaction", 10);
		Stack st = new Stack();
		st.push("0");
		// Get a set of the entries
		Set set = h.entrySet();
		try {
			System.out.println("Stack				Input Tokens				Action Lookup				Action Value				value of LHS				length of RHS				temp stack				goto lookup				goto value				stack action				parse tree stack");
			BufferedReader br = new BufferedReader(new FileReader("data/sample.txt"));
			String s1 = br.readLine();
			String[] s2 = s1.split("\\s");
			
			for (int j = 0; j < s2.length;) {
				System.out.println("");
				int x = Integer.parseInt( h.get(s2[j]).toString());
				
				String v2=st.toString();
				String v3=v2.replaceAll("[^0-9a-zA-Z+*]", "");
				System.out.print(v3);
				String v = (String) st.peek();
				int n = Integer.parseInt(v.replaceAll("[^0-9]", ""));
				String lookUp = Action[n][x];

				String input = "";
				for (int d = j; d < s2.length; d++) {
					input = input + s2[d];
				}
				char z = lookUp.charAt(0);

				System.out.print("\t\t\t\t" + input+"\t");

				if (z == 'S') {

					int m = Integer.parseInt(lookUp.replaceAll("[^0-9]", ""));
					String a = s2[j] + m;
				
					int p = Integer.parseInt(a.replaceAll("[^0-9]", ""));
					System.out.print("\t\t\t\t" + "[" + v.replaceAll("[^0-9]", "") + "," + s2[j] + "]");
					System.out.print("\t\t\t\t\t" + lookUp);
					System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
							+ "push " + a);
					st.push(a);
					String tree = "";
					if (s2[j].contains("id")) {
						treeStack.push("id");
					}
					tree = getString(treeStack);
					System.out.print("\t\t\t\t" + tree);
					j = j + 1;
				} else if (z == 'R') {

					int m = Integer.parseInt(lookUp.replaceAll("[^0-9]", ""));
					String a = s2[j] + m;
					int p = Integer.parseInt(a.replaceAll("[^0-9]", ""));

					System.out.print("\t\t\t\t" + "[" + n + "," + s2[j] + "]");
					System.out.print("\t\t\t\t\t" + lookUp);
					String e = formula[m - 1];
					char j1 = e.charAt(0);
					System.out.print("\t\t\t\t\t" + j1);

					int h1;
					if (m == 6)
						h1 = 1;
					else
						h1 = e.length() - 2;
					System.out.print("\t\t\t\t\t" + h1);
					String p1 = "";
					for (int i = 0; i < h1; i++) {
						p1 = (String) st.pop();
					}
					String v4=st.toString();
					String v5=v4.replaceAll("[^0-9a-zA-Z+*]", "");
					System.out.print("\t\t\t\t\t"+v5);

					String v1 = (String) st.peek();
					int j2 = Integer.parseInt(v1.replaceAll("[^0-9]", ""));
					System.out.print("\t\t\t\t\t" + "[" + j2 + "," + j1 + "]");
					int m1;
					if (j1 == 'F') {
						m1 = 2;
					} else if (j1 == 'T') {
						m1 = 1;
					} else
						m1 = 0;
					int b1 = Goto[j2][m1];
					System.out.print("\t\t\t\t\t" + b1);
					String str = "" + j1 + b1;
					System.out.print("\t\t\t\t\t push " + str);
					st.push(str);
					if (h1 == 1) {
						String pop = (String) treeStack.pop();
						treeStack.push("[ " + j1 + " " + pop + " ]");
					} else {
						String two = (String) treeStack.pop();
						String one = (String) treeStack.pop();
						if (lookUp.equals("R3")) {
							treeStack.push("[ " + j1 + " " + one + " * " + two+ " ]");
						} else {
							treeStack.push("[ " + j1 + " " + one + " + " + two+ " ]");

						}
					}
					String tree = getString(treeStack);
					System.out.print("\t\t\t\t" + tree);
				} else {

					String v1 = (String) st.peek();
					int j2 = Integer.parseInt(v1.replaceAll("[^0-9]", ""));
					System.out.print("\t\t\t\t\t [" + j2 + "," + s2[j]
							+ "]     \t\t\t\t accept   ");
					String tree1 = getString(treeStack);
					System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + tree1);
		
					printParseTree(tree1);
					break;

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public String getString(Stack s) {
		String str = "";
		Stack t = new Stack();
		while(!s.isEmpty()) {
			String a = (String)s.pop();
			str = str + " " + a;
			t.push(a);
		}
		while(!t.isEmpty()) {
			s.push(t.pop());
		}
		return str;
	}
}
