package Attack;
public interface Attack{
	void execute(String target);
	String getAttackType();
	int getSeverrityLevel();
	String getDescription();
	boolean isSuccessful();
	long getAttackDuration();
	void resetAttack();
	int getSeverityLevel();
}