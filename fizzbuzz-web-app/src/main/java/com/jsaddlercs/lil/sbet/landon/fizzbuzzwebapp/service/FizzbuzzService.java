package com.jsaddlercs.lil.sbet.landon.fizzbuzzwebapp.service;

import org.springframework.stereotype.Service;

@Service
public class FizzbuzzService {
	
	/** Take the input as a string, process it, and return the fizz buzz related result
	 *  If the input passed in is not an integer, return the string "error"
	 *  Otherwise, run the fizzbuzz algorithm on the input and return the proper fizzbuzz result. 
	 */
	public String getFizzbuzz(String input) { 
		String result = "";
		try {
			int parsedInput = Integer.parseInt(input);
			if(parsedInput % 3 != 0 && parsedInput % 5 != 0) { 
				result = input;
			}
			else { 
				if(parsedInput % 3 == 0) { 
					result += "Fizz";
				}
				if(parsedInput % 5 == 0) { 
					result += "Buzz";
				}
			}
		} catch(NumberFormatException e) { 
			result = "error"; 
		} 
		return result;
	}
}
