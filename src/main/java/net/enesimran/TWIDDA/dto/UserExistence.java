package net.enesimran.TWIDDA.dto;

public class UserExistence {
    
    private boolean usernameExists;
    private boolean mailExists;
    private boolean idExists;

    public UserExistence() {}

    public UserExistence(boolean usernameExists, boolean mailExists, boolean idExists) {
        this.usernameExists = usernameExists;
        this.mailExists = mailExists;
        this.idExists = idExists;
    }

    public boolean isUsernameExists() {
        return usernameExists;
    }
    public void setUsernameExists(boolean usernameExists) {
        this.usernameExists = usernameExists;
    }
    public boolean isMailExists() {
        return mailExists;
    }
    public void setMailExists(boolean mailExists) {
        this.mailExists = mailExists;
    }
    public boolean isIdExists() {
        return idExists;
    }
    public void setIdExists(boolean idExists) {
        this.idExists = idExists;
    }
    

}
