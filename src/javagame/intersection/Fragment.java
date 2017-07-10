//Matching One of a Group of Characters.  A string enclosed in brackets ([ and ]) matches any one of the characters in the string, unless the first character in the string is ^, in which case the match is to any character not in the string that follows the ^. For example, the string "[, /]" will match a comma, a space, or a slash, and the string "[^abc]" will match any character that is not a, b, or c. To match a range of characters, separate the start and end character of the range with a '–'. For example, "[a–z]" will match any lowercase letter. For the statement
//tokens = personData.split("[, /]");
//tokens[0] is "Doe", tokens[1] is an empty string because the space character in personData is matched immediately by the space in the delimiter string, tokens[2] is "John", tokens[3] is "5", tokens[4] is "15", and tokens[5] is "65", so we are closer to what we desire.
//Qualifiers.  Character groups match a single character. Qualifiers are applied to regular expressions to define a new regular expression that conditionally performs a match. These qualifiers are shown in Table A.8.
//In the statement.
//String[] tokens = personData.split("[, /]+");
//the argument "[, /]+" will match a string of one or more space, comma, and slash characters.
//Therefore, tokens[0] is "Doe", tokens[1] is "John", tokens[2] is "5", and so on. This is what
//we desire.
