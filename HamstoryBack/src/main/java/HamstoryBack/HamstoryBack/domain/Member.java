package HamstoryBack.HamstoryBack.domain;

public class Member {

    //private String id;
    private String nickName;
    private String email;
    private String pw;

    public String getNickname(){
        return nickName;
    }

    public void setNickname(String nickName){
        this.nickName = nickName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPw(){
        return pw;
    }

    public void setPw(String pw){
        this.pw = pw;
    }
}
