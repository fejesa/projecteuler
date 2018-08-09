package net.projecteuler.java.util;

import java.util.ArrayList;
import java.util.List;

public class VariationGenerator {

	private List<Integer> elements;

	private int k;

	public VariationGenerator(int n, int k) {
		if (k > n) {
			throw new IllegalArgumentException ();
		}
		if (k < 1) {
			throw new IllegalArgumentException ();
		}
		elements = new ArrayList<Integer>();
		for (int i = 0; i <= n; i++) {
			elements.add(i);
		}
		this.k = k;
	}

	public List<List<Integer>> getVariations() {
		return getVariations(elements, k);
	}

	private List<List<Integer>> getVariations(List<Integer> input, int k) {

		if (k == 0) {
			return new ArrayList<List<Integer>>();
		}

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (input.size() == 1) {
			List<Integer> list = new ArrayList<Integer>(input);
			result.add(list);
			return result;
		}

		List<Integer> tmp = new ArrayList<Integer>(input);

		for (int i = 0; i < input.size(); i++) {
			int j = input.get(i);
			tmp.remove(i);
			List<List<Integer>> intermediate = getVariations(tmp, k-1);
			if (intermediate.isEmpty()) {
				List<Integer> l = new ArrayList<Integer>();
				l.add(j);
				result.add(l);
			} else {
				for (List<Integer> s : intermediate){
					s.add(j);
					result.add(s);
				}
			}
			tmp = new ArrayList<Integer>(input);
		}
		return result;
	}

	public static void main(String args[]) {

		VariationGenerator p = new VariationGenerator(9, 4);
		List<List<Integer>> finalResult = p.getVariations();
		for (List<Integer> i : finalResult) {
			System.out.println(i);
		}
		System.out.println(finalResult.size());
	}
}
