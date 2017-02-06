package com.domain;

public class Message {
	// TODO: Add stuff...
	WebUser from;
	WebUser to;
	String contents;
	
	public Message(WebUser from, WebUser to, String contents) {
		this.from = from;
		this.to = to;
		this.contents = contents;
	}
	
	/**
	 * @return the from
	 */
	public WebUser getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(WebUser from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public WebUser getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(WebUser to) {
		this.to = to;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
