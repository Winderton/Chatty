package com.winderton.messenger.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueID {

	private static List<Integer> ids = new ArrayList<Integer>();
	private static final int RANGE = 1000;
	private static int index = 0;
	
	static {
		for (int i = 0; i < RANGE; i++) {
			ids.add(i);
		}
		Collections.shuffle(ids);
	}
	
	private UniqueID() {
	}
	
	public static int getID() {
		if (index > ids.size() -1 ) index = 0;
		return ids.get(index++);
	}
}
