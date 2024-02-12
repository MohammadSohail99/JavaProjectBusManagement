package UserDataBase;

class InvalidCredentialsException extends Exception{
    @Override
    public String toString(){
        return "InvalidCredentialsException";
    }
}
