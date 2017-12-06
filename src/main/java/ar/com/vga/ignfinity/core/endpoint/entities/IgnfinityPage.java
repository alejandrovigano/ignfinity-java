package ar.com.vga.ignfinity.core.endpoint.entities;

import java.util.concurrent.Callable;

import ar.com.vga.ignfinity.core.exception.IgnfinityException;

public class IgnfinityPage<T> {

	private Boolean hasNextPage;
	private String endCursor;

	private T body;

	private Callable<IgnfinityPage<T>> next;

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public String getEndCursor() {
		return endCursor;
	}

	public void setEndCursor(String endCursor) {
		this.endCursor = endCursor;
	}

	public Boolean getHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public IgnfinityPage<T> next() throws IgnfinityException {
		try {
			return hasNextPage ? next.call() : null;
		} catch (Exception e) {
			throw new IgnfinityException(e);
		}
	}

	public void setNext(Callable<IgnfinityPage<T>> next) {
		this.next = next;
	}

}
