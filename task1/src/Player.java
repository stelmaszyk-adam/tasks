public class Player {

    private String name;
    private String surname;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, String surname, String teamName) {
        this.name = name;
        this.surname = surname;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    private String teamName;
}
