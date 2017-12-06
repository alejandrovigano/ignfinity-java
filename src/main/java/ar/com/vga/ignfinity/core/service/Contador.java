package ar.com.vga.ignfinity.core.service;

public class Contador {

	int count = 0;

	public int getCount() {
		return count;
	}

	public int plus() {
		return ++count;
	}
}
