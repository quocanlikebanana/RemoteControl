package Protocols;

import java.util.ArrayList;
import java.util.List;

public class packet {
	public protocol p;
	public List<Object> data;

	public packet(protocol p) {
		this.p = p;
		this.data = null;
	}
	
	public packet(protocol p, Object o) {
		this.p = p;
		this.data = new ArrayList<>();
		this.data.add(o);
	}
}
