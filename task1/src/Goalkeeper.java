public class Goalkeeper extends Player {

    private Integer numberGoals;

    public Goalkeeper(String name) {
        super(name);
    }

    public Goalkeeper(String name, String surname, String teamName, Integer numberGoals) {

        super(name, surname, teamName);
        this.numberGoals = numberGoals;
    }

    public Integer getNumberGoals() {
        return numberGoals;
    }

    public void setNumberGoals(Integer numberGoals) {
        this.numberGoals = numberGoals;
    }

}
