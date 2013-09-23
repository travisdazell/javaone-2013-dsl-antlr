grammar GolfScheduler;

options {
  language = Java;
}

@header {
	package net.travisdazell.javaone;
	
	import java.util.Date;
	import java.text.DateFormat;
	import net.travisdazell.javaone.model.*;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
}

@lexer::header {
	package net.travisdazell.javaone;
}

teetime	returns [TeeTime t]	:
		'new' 'tee' 'time' 'at' time 'for' golfer
 		{
			t = new TeeTime();
			
			t.setTime($time.t);
			t.setGolfer($golfer.g);
			
			return $t;
		}
		;

time returns [Date t]	:
			d=DATETIME
			{
				DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy hh:mm a");
				try {
					$t = (Date) (formatter.parse($d.text));
				} catch (ParseException e) {
					System.err.println(e.getMessage());
				}
				
				return $t;
			}
		;

golfer returns [Golfer g]	:
			f=firstName l=lastName 'and' n=numberOfGuests 'guests'
			{
				$g = new Golfer();
				$g.setFirstName($f.text);
				$g.setLastName($l.text);
				$g.setGuests(Integer.parseInt($n.text));
			}
		;
		
numberOfGuests	:
					INT
				;

firstName	:
				STRING
			;

lastName	:
				STRING
			;

STRING	:
			LETTER+
		;

INT :   '0'..'9'+
    ;

fragment
LETTER	:
			('a'..'z'|'A'..'Z')
		;

fragment
QUOTE	:
			'"'
		;
		
fragment
DIGIT	:
			'0'..'9'
		;

DATETIME	:
				(DIGIT DIGIT?) ('-') ('Jan'|'Feb'|'Mar'|'Apr'|'May'|'Jun'|'Jul'|'Aug'|'Sep'|'Oct'|'Nov'|'Dec') ('-') (DIGIT DIGIT DIGIT DIGIT) (' ') (DIGIT DIGIT?) (':')(DIGIT DIGIT) (' ') ('AM'|'PM')
			;

WS  :   (' '|'\r'|'\t'|'\n') {$channel=HIDDEN;}
    ;