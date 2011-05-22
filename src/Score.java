/**
 * En klass som håller koll på poängen
 */
public class Score {
	private static int score = 0;
	public static void increaseScore(){
		increaseScore(1);
	}
	public static void increaseScore(int increment){
		score += increment;
	}
	public static void decreaseScore(int decrement){
		score -= decrement;
	}
	public static int getScore(){
		return score;
	}
	public static void ballDropped(){
		// Never drop the ball, fuck are y'all thinking?
		decreaseScore(1000);
	}
}
