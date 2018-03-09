package declanbrophy.barrowrovers;

public class Team {

    private String systemAdmin;
    private String teamName;
    private String address;
    private String email;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Team(String teamId, String name) {
    }

    public Team(String systemAdmin, String teamName, String address, String email, String id) {
        this.systemAdmin = systemAdmin;
        this.teamName = teamName;
        this.address = address;
        this.email = email;
        this.id = id;
    }


    public String getSystemAdmin() {
        return systemAdmin;
    }

    public void setSystemAdmin(String systemAdmin) {
        this.systemAdmin = systemAdmin;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}


