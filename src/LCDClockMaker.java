public class LCDClockMaker {

	public static void main(String[] args) {
		LCDClock myClock = new LCDClock();
		myClock.setClockLEDs();
		myClock.printLCDTime(5, 12);
	}
}
