package com.sma.model;

public class Query {
	
	private int id;
	private int studentId;
	private String subject;
	private String statement;
	private String reply;
	private QueryStatus status;
	
	public Query(int id, int studentId, String subject, String statement, String reply, String status) {
		this.id = id;
		this.studentId = studentId;
		this.subject = subject;
		this.statement = statement;
		this.reply = reply;
		this.status = QueryStatus.valueOf(status);
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getStudentId() {
		return this.studentId;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public String getStatement() {
		return this.statement;
	}
	
	public String getReply() {
		return this.reply;
	}
	
	public QueryStatus getStatus() {
		return this.status;
	}
	
	

}
