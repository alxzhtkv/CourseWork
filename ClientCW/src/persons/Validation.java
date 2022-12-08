package persons;

public class Validation {

    public boolean passwordValidation(String pass1, String pass2){

        if(pass1.equals(pass2)){
            return true;
        }
        else return false;

    }
}
