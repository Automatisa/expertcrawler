package org.ipen.miner.nlp;

/*
author:   Fotis Lazarinis (actually I translated from C to Java)
   date:     June 1997
   address:  Psilovraxou 12, Agrinio, 30100

   comments: Compile it, import the Porter class into you program and create an instance.
	     Then use the stripAffixes method of this method which takes a String as
             input and returns the stem of this String again as a String.

MODIFICATIONS

DG -- 10/19/99 --- slight mods to create a PorterString class with a
constructor that obtains the stem.  getStem is then the method used to get the
stem -- this hides the stripAffixes class and provides some more
flexibility

*/

public class PorterStemmer implements Stemmer {
	// Private constructor prevents instantiation from other classes
	//private PorterStemmer() {
	//}

	@Override
	public String stem(CharSequence word) {
		return stripAffixes(word.toString());
	}

	private static String Clean(String word) {
		int last = word.length();
		String temp = "";

		for (int i=0; i < last; i++) {
			if (Character.isLetterOrDigit(word.charAt(i)))
				temp += word.charAt(i);
		}
		return temp;
	}

	private static boolean hasSuffix(String word, String suffix, NewString stem) {
		String tmp;

		if (word.length() <= suffix.length())
			return false;
		if (suffix.length() > 1)
			if (word.charAt(word.length()-2) != suffix.charAt(suffix.length()-2))
				return false;

		stem.word = "";

		for (int i = 0; i < word.length() - suffix.length(); i++)
			stem.word += word.charAt(i);

		tmp = stem.word;

		for (int i = 0; i < suffix.length(); i++)
			tmp += suffix.charAt(i);

		if (tmp.compareTo(word) == 0)
			return true;
		else
			return false;
	}

	private static boolean vowel(char ch, char prev) {
		switch (ch) {
			case 'a': case 'e': case 'i': case 'o': case 'u':
				return true;
			case 'y': {
				switch (prev) {
					case 'a': case 'e': case 'i': case 'o': case 'u':
						return false;
					default:
						return true;
				}
			}
			default :
			return false;
		}
	}

	private static int measure(String stem) {
		int i = 0, count = 0;
		int length = stem.length();

		while (i < length) {
			for (; i < length ; i++) {
				if (i > 0) {
					if (vowel(stem.charAt(i),stem.charAt(i-1)))
						break;
				} else {
					if (vowel(stem.charAt(i),'a'))
						break;
				}
			}

			for (i++ ; i < length; i++) {
				if (i > 0) {
					if (!vowel(stem.charAt(i),stem.charAt(i-1)))
						break;
				} else {
					if (!vowel(stem.charAt(i),'?'))
						break;
				}
			}

			if (i < length) {
				count++;
				i++;
			}
		}
		return(count);
	}

	private static boolean containsVowel(String word) {
		for (int i=0 ; i < word.length(); i++)
			if (i > 0) {
				if (vowel(word.charAt(i),word.charAt(i-1)))
					return true;
			} else {
				if (vowel(word.charAt(0),'a'))
				return true;
			}
		return false;
	}

	private static boolean cvc(String word) {
		int length=word.length();

		if (length < 3)
			return false;

		if ((!vowel(word.charAt(length-1), word.charAt(length-2))) && (word.charAt(length-1) != 'w') && (word.charAt(length-1) != 'x') && (word.charAt(length-1) != 'y') && (vowel(word.charAt(length-2), word.charAt(length-3)))) {
			if (length == 3) {
				if (!vowel(word.charAt(0), '?'))
					return true;
				else
					return false;
			} else {
				if (!vowel(word.charAt(length-3),word.charAt(length-4)))
					return true;
				else
					return false;
			}
		}
		return false;
	}

	private static String step1(String word) {
		NewString stem = new NewString();

		if (word.charAt(word.length()-1) == 's') {
			if ((hasSuffix(word, "sses", stem)) || (hasSuffix(word, "ies", stem))) {
				String tmp = "";
				for (int i=0; i < word.length()-2; i++)
				tmp += word.charAt(i);
				word = tmp;
			} else {
				if ((word.length() == 1) && (word.charAt(word.length()-1) == 's')) {
					word = "";
					return word;
				}
				if (word.charAt(word.length()-2) != 's') {
					String tmp = "";
					for (int i=0; i < word.length()-1; i++)
						tmp += word.charAt(i);
					word = tmp;
				}
			}
		}

		if (hasSuffix(word, "eed", stem)) {
			if (measure(stem.word) > 0) {
				String tmp = "";
				for (int i = 0; i < word.length()-1; i++)
					tmp += word.charAt(i);
				word = tmp;
			}
		} else {
			if ((hasSuffix(word, "ed", stem)) || (hasSuffix(word, "ing", stem))) {
				if (containsVowel(stem.word))  {
					String tmp = "";
					for (int i = 0; i < stem.word.length(); i++)
						tmp += word.charAt(i);
					word = tmp;

					if (word.length() == 1)
						return word;

					if ((hasSuffix(word,"at",stem)) || (hasSuffix(word,"bl",stem)) || (hasSuffix(word,"iz",stem))) {
						word += "e";
					} else {
						int length = word.length();
						if ((word.charAt(length-1) == word.charAt(length-2)) && (word.charAt(length-1) != 'l') && (word.charAt(length-1) != 's') && (word.charAt(length-1) != 'z')) {
							tmp = "";
							for (int i=0; i<word.length()-1; i++)
								tmp += word.charAt(i);
							word = tmp;
						} else if (measure(word) == 1) {
							if (cvc(word))
								word += "e";
						}
					}
				}
			}
		}

		if (hasSuffix(word,"y",stem))
			if (containsVowel(stem.word)) {
				String tmp = "";
				for (int i=0; i<word.length()-1; i++)
					tmp += word.charAt(i);
				word = tmp + "i";
			}
		return word;
	}

	private static String step2(String word) {
		String[][] suffixes = {
			{"ational", "ate"},
			{"tional", "tion"},
			{"enci", "ence"},
			{"anci", "ance"},
			{"izer", "ize"},
			{"iser", "ize"},
			{"abli", "able"},
			{"alli", "al"},
			{"entli", "ent"},
			{"eli", "e"},
			{"ousli", "ous"},
			{"ization", "ize"},
			{"isation", "ize"},
			{"ation", "ate"},
			{"ator", "ate"},
			{"alism", "al"},
			{"iveness", "ive"},
			{"fulness", "ful"},
			{"ousness", "ous"},
			{"aliti", "al"},
			{"iviti", "ive"},
			{"biliti", "ble"}
		};

		NewString stem = new NewString();

		for (int index = 0 ; index < suffixes.length; index++) {
			if (hasSuffix (word, suffixes[index][0], stem)) {
				if (measure (stem.word) > 0) {
					word = stem.word + suffixes[index][1];
					return word;
				}
			}
		}

		return word;
	}

	private static String step3(String word) {
		String[][] suffixes = {
			{"icate", "ic"},
			{"ative", ""},
			{"alize", "al"},
			{"alise", "al"},
			{"iciti", "ic"},
			{"ical", "ic"},
			{"ful", ""},
			{"ness", ""}
		};

		NewString stem = new NewString();

		for (int index = 0 ; index<suffixes.length; index++) {
			if (hasSuffix(word, suffixes[index][0], stem))
				if (measure (stem.word) > 0) {
					word = stem.word + suffixes[index][1];
					return word;
				}
		}
		return word;
	}

	private static String step4(String word) {
		String[] suffixes = {"al", "ance", "ence", "er", "ic", "able", "ible", "ant", "ement", "ment", "ent", "sion", "tion", "ou", "ism", "ate", "iti", "ous", "ive", "ize", "ise"};
		NewString stem = new NewString();

		for (int index = 0; index < suffixes.length; index++) {
			if (hasSuffix (word, suffixes[index], stem)) {
				if (measure(stem.word) > 1) {
					word = stem.word;
					return word;
				}
			}
		}
		return word;
	}

	private static String step5(String word) {
		if (word.charAt(word.length()-1) == 'e') {
			/* measure(word)==measure(stem) if ends in vowel */
			if (measure(word) > 1) {
				String tmp = "";
				for (int i=0; i<word.length()-1; i++)
					tmp += word.charAt(i);
				word = tmp;
			} else if (measure(word) == 1) {
				String stem = "";
				for (int i=0; i<word.length()-1; i++)
					stem += word.charAt(i);

				if (!cvc(stem))
					word = stem;
			}
		}

		if (word.length() == 1)
		return word;
		if ((word.charAt(word.length()-1) == 'l') && (word.charAt(word.length()-2) == 'l') && (measure(word) > 1))
		if (measure(word) > 1) {/* measure(word)==measure(stem) if ends in vowel */
		String tmp = "";
		for (int i=0; i<word.length()-1; i++)
		tmp += word.charAt(i);
		word = tmp;
		}
		return word;
	}

	private static String stripPrefixes (String word) {
		String[] prefixes = {"kilo", "micro", "milli", "intra", "ultra", "mega", "nano", "pico", "pseudo"};

		int last = prefixes.length;
		for (int i=0 ; i<last; i++) {
			if (word.startsWith(prefixes[i])) {
				String temp = "";
				for (int j=0 ; j< word.length()-prefixes[i].length(); j++)
				temp += word.charAt(j+prefixes[i].length());
				return temp;
			}
		}
		return word;
	}

	private static String stripSuffixes(String word) {
		word = step1(word);

		if (word.length() >= 1)
			word = step2(word);

		if (word.length() >= 1)
			word = step3(word);

		if (word.length() >= 1)
			word = step4(word);

		if (word.length() >= 1)
			word = step5(word);

		return word;
	}

	private static String stripAffixes(String word) {
		word = word.toLowerCase();
		word = Clean(word);

		if ((!word.equals("")) && (word.length() > 2)) {
			word = stripPrefixes(word);

			if (!word.equals(""))
				word = stripSuffixes(word);
		}
		return word;
	}

	private static class NewString {
		public String word;

		NewString() {
			word = "";
		}
	}
}
