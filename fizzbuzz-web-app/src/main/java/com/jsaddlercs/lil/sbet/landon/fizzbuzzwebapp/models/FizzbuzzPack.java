package com.jsaddlercs.lil.sbet.landon.fizzbuzzwebapp.models;

public class FizzbuzzPack {
	private String input;
	private String answer;
	public FizzbuzzPack(String input, String answer) {
		this.input = input;
		this.answer = answer;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String toString() { 
		return String.format("%s: Output is: %s",input, answer);
	}
}
