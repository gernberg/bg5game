/**
 * En klass som håller koll på poängen
 */
public class Score {
	private static int score = 0;
	public static void increase(){
		increase(1);
	}
	public static void increase(int increment){
		score += increment;
	}
	public static void decrease(int decrement){
		score -= decrement;
	}
	public static int getScore(){
		return score;
	}
	public static void ballDropped(){
		decrease(2000);
	}
}
