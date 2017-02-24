package utilitaire;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class StringUtil {
	
	public static boolean fullLetter(String string){
		boolean isLetter=true;
		int i=0;
		while(isLetter&&i<string.length()){
			isLetter = Character.isLetter(string.charAt(i));
			if(Character.isSpaceChar(string.charAt(i)))isLetter = true;
			i++;
		}
		return isLetter;
	}
	public static boolean fullNumber(String string){
		boolean isNumber=true;
		int i=0;
		while(isNumber&&i<string.length()){
			isNumber = Character.isDigit(string.charAt(i));
			i++;
		}
		return isNumber;
	}
	public static boolean isEmail(String string){
		String [] split1 = string.split("@");
		if(split1.length != 2){
			return false;
		}
		String [] split2 = split1[1].split("\\.");
		if(split2.length != 2){
			return false;
		}
		return true;
	}
	public static boolean isTelephone(String string){
		if(string.startsWith("\\+")){
			return fullNumber(string.substring(1));
		}
		return fullNumber(string.substring(1));
	}
	public static boolean correctMdp(String string){
		
		int compteurMaj = 0;
		int compteurDigit = 0;
		int compteurSpec = 0;
		int i = 0;
		while(i<string.length()){
			char c = string.charAt(i);
			int cint = (int)c;
			if(Character.isDigit(c)){
				compteurDigit ++;
			}
			if(Character.isUpperCase(c)){
				compteurMaj ++;
			}
			if(cint <48 || (cint > 57 && cint < 65) || (cint > 90 && cint < 97) || cint > 122)
		    {
				compteurSpec ++;
		    }
		}
		return compteurMaj>0&&compteurDigit>0&&compteurSpec>0&&string.length()>=8;
	}
	
    public static LocalTime stringToTime(String heure, String minute){
    	return LocalTime.of(Integer.valueOf(heure), Integer.valueOf(minute));
    }
    public static String formatISO(String iso){
    	return formatDateTime(LocalDateTime.parse(iso, DateTimeFormatter.RFC_1123_DATE_TIME));
    }
    public static String formatDateTime(LocalDateTime local){
    	return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(local.toLocalDate())+" à "+
    			DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(local.toLocalTime());
    }
}
