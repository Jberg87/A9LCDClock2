import java.util.Calendar;


public class LCDClock {
	String curTime;
	int hourA, hourB, minA, minB, printedHorizontal, printedVertical;
	ClockLEDs LEDs;
	
	public void setClockLEDs() {
		LEDs = new ClockLEDs();
	}
		
	public void getTime() {
		Calendar cal = Calendar.getInstance();
		int hourCal = cal.get(Calendar.HOUR_OF_DAY);
		int minCal = cal.get(Calendar.MINUTE);
		curTime = String.format("%02d:%02d", hourCal, minCal);
	}
	
	public void setTime() {
		hourA = Character.getNumericValue(curTime.charAt(0));
		hourB = Character.getNumericValue(curTime.charAt(1));
		minA = Character.getNumericValue(curTime.charAt(3));
		minB = Character.getNumericValue(curTime.charAt(4));
	}
	
	public void printLCDTime(int size, int defineAMPM) {
		// if defineAMPM == 12 print AM/PM
		boolean printAMPM = false;
		String AMPMTime = "PM";
		
		if (size < 1 || size > 5) {
			size = 2;
		}
		
		setClockLEDs();
		getTime();
		setTime();
		
		if (defineAMPM == 12) {
			printAMPM = true;
			setAMPMHours();
		}
		
		printHorizontal(LEDs.x1[hourA], LEDs.x1[hourB], LEDs.x1[minA], LEDs.x1[minB], size, printAMPM, AMPMTime);
		printedHorizontal++;

		for (int i = 1; i <= size; i++) {
			printHourVertical(LEDs.y1[hourA], LEDs.y2[hourA], LEDs.y1[hourB], LEDs.y2[hourB], size, printAMPM, AMPMTime);
			printMinVertical(LEDs.y1[minA], LEDs.y2[minA], LEDs.y1[minB], LEDs.y2[minB], size);
		}
		printedVertical++;

		printHorizontal(LEDs.x2[hourA], LEDs.x2[hourB], LEDs.x2[minA], LEDs.x2[minB], size, printAMPM, AMPMTime);
		printedHorizontal++;

		for (int i = 1; i <= size; i++) {
			printHourVertical(LEDs.y3[hourA], LEDs.y4[hourA], LEDs.y3[hourB], LEDs.y4[hourB], size, printAMPM, AMPMTime);
			printMinVertical(LEDs.y3[minA], LEDs.y4[minA], LEDs.y3[minB], LEDs.y4[minB], size);
		}
		printedVertical++;

		printHorizontal(LEDs.x3[hourA], LEDs.x3[hourB], LEDs.x3[minA], LEDs.x3[minB], size, printAMPM, AMPMTime);
		printedHorizontal++;
	}
	
	private void setAMPMHours() {

			Calendar cal = Calendar.getInstance();
			int calHour = cal.get(Calendar.HOUR_OF_DAY);
			if (calHour >= 12) {
				calHour = calHour - 12;
			}
			curTime = String.format("%02d", calHour);
			hourA = Character.getNumericValue(curTime.charAt(0));
			hourB = Character.getNumericValue(curTime.charAt(1));
			
	}

	private void printMinVertical(int LEDleft_minA, int LEDright_minA, int LEDleft_minB, int LEDright_minB, int size) {
		if (LEDleft_minA == 1) {
			System.out.print("|");
		} else {
			space();
		}
		for (int i = 1; i <= size; i++) {
				space();
		}
		if (LEDright_minA == 1) {
			System.out.print("|");
		} else {
			space();
		}
		space();
		
		if (LEDleft_minB == 1) {
			System.out.print("|");
		} else {
			space();
		}
		for (int i = 1; i <= size; i++) {
				space();
		}
		if (LEDright_minB == 1) {
			System.out.print("|");
		} else {
			space();
		}
		System.out.println(" ");     //new line for/if next horizontal
	}
		
	private void printHourVertical(int LEDleft_hourA, int LEDright_hourA, int LEDleft_hourB, int LEDright_hourB, int size, boolean printAMPM, String AMPMTime) {
		if (printAMPM == true) {
			System.out.print("|");
			for (int i = 1; i <= size; i++) {
				space();
				}
			if (printedVertical == 0 || AMPMTime.equals("AM")) {
				System.out.print("|");
				} else {
				space();
				}
			space();
		}
		
		if (LEDleft_hourA == 1) {
			System.out.print("|");
		} else {
			space();
		}
		for (int i = 1; i <= size; i++) {
				space();
		}
		if (LEDright_hourA == 1) {
			System.out.print("|");
		} else {
			space();
		}
		space();
		
		if (LEDleft_hourB == 1) {
			System.out.print("|");
		} else {
			space();
		}
		for (int i = 1; i <= size; i++) {
				space();
		}
		if (LEDright_hourB == 1) {
			System.out.print("|");
		} else {
			space();
		}
		
		space();
		for (int i = 0; i < size ; i++) {		// space between hours and minutes
			System.out.print("-");
		}
		space();
		
		
	}
	
	private void printHorizontal(int LED_hourA, int LED_hourB, int LED_minA, int LED_minB, int size, boolean printAMPM, String AMPMTime) {
		if (printAMPM == true) {
			space();
			for (int i = 1; i <= size; i++) {
				if (printedHorizontal == 2) {
					space();
				} else {
					System.out.print("-");
				}
			}
			space();
			space();
		}
		
		// end of am/pm
		
		space(); 
		for (int i = 1; i <= size; i++) {
			if (LED_hourA == 1) {
				System.out.print("-");
			} else {
				space();
			}
		}
		space();
		space();
		space();
		for (int i = 1; i <= size; i++) {
			if (LED_hourB == 1) {
				System.out.print("-");
			} else {
				space();
			}
		}
		space();
		for (int i = 0; i < size + 2; i++) {		// space between hours and minutes
			space();
		}
		space();
		for (int i = 1; i <= size; i++) {
			if (LED_minA == 1) {
				System.out.print("-");
			} else {
				space();
			}
		}
		space();
		space();
		space();
		for (int i = 1; i <= size; i++) {
			if (LED_minB == 1) {
				System.out.print("-");
			} else {
				space();
			}
		}
		System.out.println(" ");
	}
	
	public void space() {
		System.out.print(" ");
	}
}
